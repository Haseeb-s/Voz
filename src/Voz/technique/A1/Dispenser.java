package Voz.technique.A1;

import java.util.*;
import java.io.*;

public class Dispenser {
	private FoodInformation item;
	private int quantity;

	public Dispenser( FoodInformation item ) {
		this.item = item;
		quantity = 20;
	}

	public Dispenser(String readLine) {
		Scanner line = new Scanner(readLine);
		line.useDelimiter("\\,|\\}|\\:|\\n");
		line.next();
		line.next();
		line.next();
		line.next();
		line.next();
		line.next();
		line.next();
		line.next();
		this.quantity = Integer.parseInt(line.next());
		this.item = new FoodInformation(readLine);
	}

	public Dispenser(int quantity, String name, String nutrition, double price) {
		item = new FoodInformation(name, nutrition, price);
		this.quantity = quantity;
	}

	public FoodInformation fetch(int quantity) throws Exception {
		if (quantity >= this.quantity)
			throw new Exception("Not enough products.");
		else {
			this.quantity = this.quantity - quantity;
			return item;
		}
	}

	public String toString() {
		return item.toString() + ",quantity:"+quantity+"}\n";
	}
}