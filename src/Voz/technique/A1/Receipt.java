package Voz.technique.A1;
public class Receipt {
	private FoodInformation purchased;
	private double moneyInserted;
	private double change;

	public Receipt(double moneyInserted, FoodInformation purchased) throws Exception{
		this.moneyInserted = moneyInserted;
		this.purchased = purchased;
		this.change = moneyInserted - purchased.getPrice();
		if (this.change < 0)
			throw new Exception("Not enough money.");
	}

	public FoodInformation getFood() {
		return purchased;
	}

	public double getChange() {
		return change;
	}

	public String toString() {
		return "Money inserted: $" + String.format("%.2f", moneyInserted) + "\nItem Purchased: " + purchased.getName()+ "\nItem Nutrition: " + purchased.getNutrition() + "\nItem Price: $" + String.format("%.2f", purchased.getPrice()) + "\nChange: $" + String.format("%.2f", change);
	}
}