package Voz.technique.A1;
import java.util.Random;

public class User {
	private double wallet;
	private int arrivalTime;

	public User(double money) {

		//set the money to wallet
		wallet = money;

		//create a random number for arrival time
		Random rand = new Random();
	    int randomNum = rand.nextInt(24) + 1;
	    arrivalTime = randomNum;
	}

	public double getWallet() {
		return this.wallet;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public String toString() {
		return "The client arrived at " + arrivalTime + "H (in 24 hour time), and has $" + String.format("%.2f", wallet) + " left.";
	}
}