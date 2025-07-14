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

	public MenuObject(String menuID, String menuType, String description, List<FoodObject> foods) {
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
		indianFood.add(new FoodObject("F1", "Starter", "Paneer Tikka", 180.00,
		    "Grilled cottage cheese cubes marinated with spices", "Paneer, Yogurt, Spices"));
		indianFood.add(new FoodObject("F2", "Main Course", "Butter Chicken", 260.00,
		    "Creamy tomato-based chicken curry loved nationwide", "Chicken, Butter, Cream, Tomatoes"));
		indianFood.add(new FoodObject("F3", "Dessert", "Gulab Jamun", 80.00,
		    "Sweet milk dumplings soaked in rose-scented syrup", "Milk solids, Flour, Sugar, Rose essence"));

		menus.add(new MenuObject("M1", "Indian", "North Indian classics featuring rich curries and desserts", indianFood));
		
		List<FoodObject> italianFood = new ArrayList<>();
		italianFood.add(new FoodObject("F4", "Starter", "Bruschetta", 120.00,
		    "Toasted bread topped with fresh tomatoes and basil", "Bread, Tomato, Garlic, Basil, Olive Oil"));
		italianFood.add(new FoodObject("F5", "Main Course", "Pasta Alfredo", 240.00,
		    "Classic creamy pasta with parmesan and herbs", "Pasta, Cream, Parmesan Cheese, Herbs"));
		italianFood.add(new FoodObject("F6", "Dessert", "Tiramisu", 150.00,
		    "Coffee-flavored layered Italian dessert", "Mascarpone, Coffee, Cocoa, Ladyfingers"));

		menus.add(new MenuObject("M2", "Italian", "Elegant Italian fare with savory starters and creamy desserts", italianFood));
		
		List<FoodObject> asianFood = new ArrayList<>();
		asianFood.add(new FoodObject("F7", "Starter", "Spring Rolls", 110.00,
		    "Crispy veggie rolls served with sweet chili dip", "Cabbage, Carrot, Flour Wrap, Sauce"));
		asianFood.add(new FoodObject("F8", "Main Course", "Thai Green Curry", 290.00,
		    "Fragrant coconut curry with herbs and tofu", "Coconut Milk, Basil, Tofu, Zucchini"));
		asianFood.add(new FoodObject("F9", "Dessert", "Mango Sticky Rice", 130.00,
		    "Sweet glutinous rice served with fresh mango and coconut milk", "Sticky Rice, Mango, Coconut Milk"));

		menus.add(new MenuObject("M3", "Asian", "Fusion of Thai and Chinese delicacies served with zest", asianFood));
		return menus;
	}
}
