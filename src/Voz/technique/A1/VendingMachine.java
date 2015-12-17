package Voz.technique.A1;
import java.util.*;
import java.io.*;

public class VendingMachine {
	private Dispenser[] slots = new Dispenser[10];
	private int[] offHours = new int[3];
	private int number;
	private List<Receipt> receipts = new ArrayList<Receipt>();

	public VendingMachine(int number) {

		//set machine number
		this.number = number;

		//find off hours starting time
		Random rand = new Random();
	    int randomNum = rand.nextInt(21) + 1;
	    for (int index = 0; index < 3; index++)
			offHours[index] = randomNum + index;

		try{
			//read file
			// FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader("Machine-"+number+".txt");

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int lineIndex = 0;
            String line;
            try {
	            while((line = bufferedReader.readLine()) != null) {
	            	try {
	            		slots[lineIndex] = new Dispenser(line);
	            		lineIndex++;
	            	}	catch(Exception e) {
	            		System.out.println("Error: " + e);
	            	}
	            }
            }	catch (IOException e) {
            	System.out.println("Error: " + e);
            } 

            // Always close files.
            try {
            	bufferedReader.close();
            }	catch(Exception e) {
            	System.out.println("Error: " + e);
            }
		} catch(FileNotFoundException ex) {
			//initialize dispansers
			String[] names = { "Coke", "Skittles", "Vose", "Doritoes", "Cheetoes", "M&Ms", "Orange Fanta", "Pretzles", "5 Gum", "Iced Tea", "Twix", "Gummy Worms"};
			String[] nutrition = { "|  Serving Size- 1 can  |  Total Fat- 0g  |  Sugar- 39g  |", "|  Serving Size- 20 pc  |  Total Fat- 1g  |  Sugar- 20g  |", "|  Serving Size- 1 bottle  |  Total Fat- 0g  |  Sugar- 0g  |", "|  Serving Size- 1 bag  |  Total Fat- 2g  |  Sugar- 12g  |", "|  Serving Size- 1 bag  |  Total Fat- 3g  |  Sugar- 9g  |", "|  Serving Size- 1 bag  |  Total Fat- 5g  |  Sugar- 22g  |", "|  Serving Size- 1 can  |  Total Fat- 0g  |  Sugar- 32g  |", "|  Serving Size- 1 bag  |  Total Fat- 0g  |  Sugar- 2g  |", "|  Serving Size- 1 piece  |  Total Fat- 0g  |  Sugar- 12g  |", "|  Serving Size- 1 can  |  Total Fat- 1g  |  Sugar- 27g  |", "|  Serving Size- 1 bar  |  Total Fat- 3g  |  Sugar- 38g  |", "|  Serving Size- 1 bag  |  Total Fat- 1g  |  Sugar- 14g  |" };
			double[] prices = { 1.75, 1.25, 2.00, 1.50, 1.50, 1.25, 1.75, 1.50, 0.75, 1.75, 1.50, 1.75 };
			slots = new Dispenser[10];
			for (int i = 0; i < 10; i++){
				Random randd = new Random();
		    	int num = randd.nextInt(2) + 1;
		    	int choice = i + num;
				slots[i] = new Dispenser(20, names[choice], nutrition[choice], prices[choice]);
			}
		}

	}

	public boolean buy(double money, int slotNumber, int arrivalTime) throws Exception{
		if (slotNumber < 0 || slotNumber > 10)
			throw new Exception("Number ouside of slot range.");
		else {
			try{
				if(Arrays.asList(offHours).contains(offHours[0])){
					System.out.println("Its currently: " + arrivalTime + "\nSorry, machine is closed from " + offHours[0] + " to " + offHours[2]);
					return true;
				}
				else {
					Receipt result = new Receipt( money , slots[slotNumber].fetch(1));
					receipts.add( result );
					System.out.println(result);
					return true;
				}
			}
			catch(Exception e){
				System.out.println(e);
				return false;
			}
		}
	} 

	public boolean save() {
		// The name of the file to open.
        String fileName = "Machine-"+number+".txt";

        try {
            // Assume default encoding.
            FileWriter fileWriter = new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            for(int j = 0; j < 10; j++){
            	bufferedWriter.write(slots[j].toString());
            }

            // Always close files.
            bufferedWriter.close();

	        fileName = "Machine-"+number+"-totals-" + new java.util.Date() + ".txt";

	        try {
	            // Assume default encoding.
	            fileWriter = new FileWriter(fileName);

	            // Always wrap FileWriter in BufferedWriter.
	            bufferedWriter = new BufferedWriter(fileWriter);
	            double salesTotal = 0;
	            for (Receipt receiptItem : receipts) {
	            	salesTotal = salesTotal + receiptItem.getFood().getPrice();
				}
				bufferedWriter.write("Total: $" + String.format("%.2f", salesTotal));

	            // Always close files.
	            bufferedWriter.close();
	            return true;
	        }
	        catch(IOException ex) {
	            System.out.println(
	                "Error writing to file '"
	                + fileName + "'");
	            return false;
	        }
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
            return false;
        }

	}

	public int[] getOffHours() {
		return offHours;
	}
}