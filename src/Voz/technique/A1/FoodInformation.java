package Voz.technique.A1;

import java.io.*;
import java.util.*;

public class FoodInformation {
	private String name;
	private String nutrition;
	private double price;

	public FoodInformation(String name, String nutrition, double price) {
		this.name = name;
		this.nutrition = nutrition;
		this.price = price;
	}

	public FoodInformation(String readString) {
		String[] obj = new String[3];
		Scanner parser = new Scanner(readString);
		parser.useDelimiter("\\,|\\}|\\:|\\n");
		parser.next();
		parser.next();
		this.name = parser.next();
		parser.next();
		this.nutrition = parser.next();
		parser.next();
		this.price = Double.parseDouble(parser.next());
	}

	public double getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public String getNutrition() {
		return nutrition;
	}

	public String toString() {
		return "FoodInformation: {name:" + name + ",nutrition:" + nutrition + ",price:" + price ;//ends eith + "quantity:#}\n"
	}
}