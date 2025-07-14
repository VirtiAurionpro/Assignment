package com.aurionpro.model.Exceptions;

public class FoodNotFound extends RuntimeException{
	private static final long serialVersionUID = 1L;
	String foodID;

	public FoodNotFound() {
		super();
	}

	public FoodNotFound(String foodID) {
		super();
		this.foodID = foodID;
	}

	public String getfoodID() {
		return foodID;
	}

	public void setfoodID(String foodID) {
		this.foodID = foodID;
	}
	public String getMessage() {
		return "\nNo item found with Food ID '" + foodID + "'. Please check and try again.";
	}
}
