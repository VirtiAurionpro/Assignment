package com.aurionpro.model.Admin;

public class FoodObject {
	private String foodID;
	private String foodType;
	private String foodName;
	private double price;
	private String description;
	private String ingredients;

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getfoodID() {
		return foodID;
	}

	public void setfoodID(String foodID) {
		this.foodID = foodID;
	}

	public FoodObject() {
		super();
	}

	public FoodObject(String foodID, String foodType, String foodName, double price, String description,
			String ingredients) {
		super();
		this.foodID = foodID;
		this.foodType = foodType;
		this.foodName = foodName;
		this.price = price;
		this.description = description;
		this.ingredients = ingredients;
	}
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append(String.format("| %-10s | %-15s | %-20s | ₹%-9.2f | %-30s |\n",
	        foodID, foodType, foodName, price, ingredients));
	    sb.append("----------------------------------------------------------------------------------------------------------------------");
	    return sb.toString();
	}
}
	
