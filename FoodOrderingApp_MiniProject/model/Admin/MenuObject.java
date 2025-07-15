package com.aurionpro.model.Admin;

import java.util.ArrayList;
import java.util.List;

public class MenuObject {
	private String menuID;
	private String menuType;
	private String description;
	private List<FoodObject> Food;

	public String getmenuType() {
		return menuType;
	}

	public void setmenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<FoodObject> getFood() {
		return Food;
	}

	public void setFood(List<FoodObject> food) {
		Food = food;
	}

	public String getmenuID() {
		return menuID;
	}

	public void setmenuID(String menuID) {
		this.menuID = menuID;
	}

	public MenuObject() {
		super();
	}

	public MenuObject(String menuID, String menuType,String description, List<FoodObject> foods) {
		super();
		this.menuID = menuID;
		this.menuType = menuType;
		this.description = description;
		Food = foods;
	}

//	@Override
//	public String toString() {
//	    StringBuilder sb = new StringBuilder();
//	    sb.append("--------------------------------------------------\n");
//	    sb.append("               Menu Details\n");
//	    sb.append("--------------------------------------------------\n");
//	    sb.append("Menu ID      : ").append(menuID).append("\n");
//	    sb.append("Menu Type    : ").append(menuType).append("\n");
//	    sb.append("Description  : ").append(description).append("\n");
//	    sb.append("Food Items   : ").append(Food).append("\n");
//	    sb.append("--------------------------------------------------");
//	    return sb.toString();
//	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("========================================================================================\n");
		sb.append("                                 MENU DETAILS\n");
		sb.append("========================================================================================\n");
		sb.append("Menu ID         : ").append(menuID).append("\n");
		sb.append("Menu Type       : ").append(menuType).append("\n");
		sb.append("Description     : ").append(description).append("\n");
		sb.append("----------------------------------------------------------------------------------------\n");
		sb.append("                                   FOOD ITEMS\n");
		sb.append("----------------------------------------------------------------------------------------\n");
		sb.append(String.format("%-12s %-15s %-20s %-10s %-30s%n", "Food ID", "Food Type", "Name", "Price",
				"Ingredients"));
		sb.append("----------------------------------------------------------------------------------------\n");

		for (FoodObject food : Food) {
			sb.append(String.format("%-12s %-15s %-20s ₹%-9.2f %-30s%n", food.getfoodID(), food.getFoodType(),
					food.getFoodName(), food.getPrice(), food.getIngredients()));
		}

		sb.append("----------------------------------------------------------------------------------------\n");

		return sb.toString();
	}

	public List<MenuObject> addItems() {
		List<MenuObject> menus = new ArrayList<>();
		List<FoodObject> indianFood = new ArrayList<>();
		indianFood.add(new FoodObject("F1", "Starters", "Paneer Tikka", 180.00,
		    "Grilled cottage cheese cubes", "Paneer, Yogurt, Spices", "Veg"));
		indianFood.add(new FoodObject("F2", "Main Course", "Dal Makhani", 200.00,
		    "Creamy lentil curry", "Black lentils, Cream, Butter", "Veg"));
		indianFood.add(new FoodObject("F3", "Dessert", "Gulab Jamun", 80.00,
		    "Milk dumplings in syrup", "Milk solids, Sugar, Rose essence", "Veg"));
		
		indianFood.add(new FoodObject("F4", "Starters", "Chicken Tikka", 200.00,
		    "Spicy grilled chicken", "Chicken, Yogurt, Spices", "Non-Veg"));
		indianFood.add(new FoodObject("F5", "Main Course", "Butter Chicken", 260.00,
		    "Rich chicken curry", "Chicken, Cream, Tomatoes", "Non-Veg"));
		indianFood.add(new FoodObject("F6", "Dessert", "Rasmalai", 90.00,
		    "Cheese patties in saffron milk", "Milk, Cardamom, Sugar", "Non-Veg"));
		menus.add(new MenuObject("M1", "Indian", "North Indian favorites with diverse flavors", indianFood));

		List<FoodObject> italianFood = new ArrayList<>();
		italianFood.add(new FoodObject("F7", "Starters", "Bruschetta", 120.00,
		    "Toasted bread with tomato topping", "Bread, Tomato, Basil", "Veg"));
		italianFood.add(new FoodObject("F8", "Main Course", "Pasta Alfredo", 240.00,
		    "Creamy pasta with parmesan", "Pasta, Cream, Cheese", "Veg"));
		italianFood.add(new FoodObject("F9", "Dessert", "Panna Cotta", 160.00,
		    "Silky custard dessert", "Cream, Sugar, Vanilla", "Veg"));
		
		italianFood.add(new FoodObject("F10", "Starters", "Calamari", 190.00,
		    "Fried squid rings", "Squid, Flour, Herbs", "Non-Veg"));
		italianFood.add(new FoodObject("F11", "Main Course", "Chicken Lasagna", 280.00,
		    "Layered chicken pasta", "Chicken, Pasta, Cheese", "Non-Veg"));
		italianFood.add(new FoodObject("F12", "Dessert", "Tiramisu", 150.00,
		    "Coffee-flavored dessert", "Mascarpone, Coffee, Cocoa", "Non-Veg"));
		menus.add(new MenuObject("M2", "Italian", "Classic Italian menu with sweet and savory dishes", italianFood));

		List<FoodObject> asianFood = new ArrayList<>();
		asianFood.add(new FoodObject("F13", "Starters", "Spring Rolls", 110.00,
		    "Crispy veggie rolls", "Cabbage, Carrot, Wrap", "Veg"));
		asianFood.add(new FoodObject("F14", "Main Course", "Thai Green Curry", 290.00,
		    "Coconut curry with tofu", "Coconut Milk, Basil, Tofu", "Veg"));
		asianFood.add(new FoodObject("F15", "Dessert", "Mango Sticky Rice", 130.00,
		    "Sweet rice with mango", "Sticky Rice, Mango, Coconut Milk", "Veg"));
		
		asianFood.add(new FoodObject("F16", "Starters", "Chicken Satay", 150.00,
		    "Grilled chicken skewers", "Chicken, Spices, Peanut Sauce", "Non-Veg"));
		asianFood.add(new FoodObject("F17", "Main Course", "Kung Pao Chicken", 270.00,
		    "Spicy stir-fried chicken", "Chicken, Chili, Peanuts", "Non-Veg"));
		asianFood.add(new FoodObject("F18", "Dessert", "Fried Banana Fritters", 110.00,
		    "Crispy banana treats", "Banana, Flour, Sugar", "Non-Veg"));
		menus.add(new MenuObject("M3", "Asian", "Fusion of Thai and Chinese culinary styles", asianFood));


		return menus;
	}
}
