package Voz.technique.A5;
/**
*	<h1>Author: Jose Canahui</h1>
*	<P>Priority queue driver</p>
**/

public class Tester {

	private static Heap<String> myHeap = new Heap<String>(10);

	public static void main(String[ ]args) {
		System.out.println("Welcome to the Priotity Queue test class!");
		
		Boolean finished = false;
		while(!finished){
			switch(MoralSupport.menu(new String[] {"Enqueue string to priority queue", "Dequeue from priority queue", "Check if its empty", "Check if its full", "Print all elements in queue", "Exit"})){
				case 1:
					enqueue();
					break;
				case 2:
					dequeue();
					break;
				case 3:
					isEmpty();
					break;
				case 4:
					isFull();
					break;
				case 5:
					print();
					break;
				case 6:
					exit();
					break;
				default:
					System.out.println("Please enter number 1 - 5");
			}
		}

	}

	/**
	* Precondition: queue is not full. Queue is made. Item to be inserted must implement comparable.
	* Postcondition: item will be inserted into its correct priority.
	*/
	public static void enqueue(){
		String toAdd = MoralSupport.queryString("Enter string to enqueue:", false);
		try{
			myHeap.enqueue(toAdd);
			System.out.println("Successfully added " + toAdd);
		} catch (PriQOverflowException e) {
			System.out.println("Could not add " + toAdd + " because: " + e);
		}
	}

	/**
	* Precondition: list is not empty.
	* Postcondition: the item with the most priority in the queue will be printed.
	*/
	public static void dequeue(){
		try{
			String dequeued = myHeap.dequeue();
			System.out.println("Successfully dequeued " + dequeued + " from the priority queue..\n");
		} catch(PriQUnderflowException e){
			System.out.println("Could not dequeue because: " + e);
		}

	}

	/**
	* 
	*/
	public static void isEmpty(){
		if(myHeap.isEmpty())
			System.out.println("The queue is empty..\n");
		else
			System.out.println("The queue is not empty..\n");
	}

	/**
	* 
	*/
	public static void isFull(){
		if(myHeap.isFull())
			System.out.println("The queue is full..\n");
		else
			System.out.println("The queue is not full..\n");
	}

	/**
	* 
	*/
	public static void print(){
		System.out.println("The full priority queue is:\n " + myHeap);
	}

	/**
	* 
	*/
	public static void exit(){
		System.exit(0);
	}
}