package com.aurionpro.model.Customer;

import java.util.List;
import java.util.Scanner;

import com.aurionpro.model.Admin.FoodObject;
import com.aurionpro.model.Admin.MenuObject;
import com.aurionpro.model.Exceptions.FoodNotFound;
import com.aurionpro.model.Exceptions.MenuNotFound;

public class LineItemOperations {
	private int counter = 0;

	public String generateId() {
		counter += 1;
		if (counter < 10)
			return "LI" + "0" + counter;
		return "LI" + counter;
	}

	public List<LineItem> addItem(List<LineItem> lineItems, List<MenuObject> availableMenus, Scanner scanner) {
		System.out.print("Enter the Menu ID you’d like to order from: ");
		String menuID = scanner.next();
		boolean menuFound = false;
		MenuObject selectedMenu = null;

		for (MenuObject availableMenu : availableMenus) {
		    if (availableMenu.getmenuID().equalsIgnoreCase(menuID)) {
		        menuFound = true;
		        selectedMenu = availableMenu;
		        break;
		    }
		}

		if (!menuFound) {
			throw new MenuNotFound(menuID);
//		    return lineItems;
		}

		System.out.println("\nMenu Selected: " + selectedMenu.getmenuType());
		System.out.println("\nDescription   : " + selectedMenu.getDescription());
		String selectedType = "";
		System.out.println("\nWhat would you like to order?");
		System.out.println("1. Starters\n2. Main Course\n3. Dessert\n4. Exit");

		while (true) {
		    int ch = scanner.nextInt();
		    switch (ch) {
		        case 1: selectedType = "Starter"; break;
		        case 2: selectedType = "Main Course"; break;
		        case 3: selectedType = "Dessert"; break;
		        case 4: return lineItems;
		        default:
		            System.out.println("Invalid choice. Please try again.");
		            continue;
		    }
		    break;
		}

		System.out.println("\nShowing " + selectedType + "s from the menu:");
		System.out.println("=========================================================================================================");
		System.out.printf("| %-10s | %-20s | %-10s | %-30s |\n", "Food ID", "Name", "Price", "Ingredients");
		System.out.println("=========================================================================================================");

		boolean itemsDisplayed = false;
		for (FoodObject food : selectedMenu.getFood()) {
		    if (food.getFoodType().equalsIgnoreCase(selectedType)) {
		        System.out.printf("| %-10s | %-20s | ₹%-9.2f | %-30s |\n",
		            food.getfoodID(), food.getFoodName(), food.getPrice(), food.getIngredients());
		        System.out.println("-----------------------------------------------------------------------------------------");
		        itemsDisplayed = true;
		    }
		}

		if (!itemsDisplayed) {
		    System.out.println("No " + selectedType + " items found in this menu.");
		    return lineItems;
		}

		System.out.print("\nEnter the Food ID of the item you'd like to order: ");
		String foodID = scanner.next();

		System.out.print("Enter the number of units you wish to order: ");
		double units = scanner.nextDouble();
		String LineItemId = generateId();

		boolean itemAdded = false;
		for (FoodObject food : selectedMenu.getFood()) {
		    if (food.getfoodID().equalsIgnoreCase(foodID) &&
		        food.getFoodType().equalsIgnoreCase(selectedType)) {
		        lineItems.add(new LineItem(LineItemId, foodID, food.getFoodName(), units, food.getPrice(),
		                units * food.getPrice()));
		        itemAdded = true;
		        break;
		    }
		}

		if(!itemAdded)
			throw new FoodNotFound(foodID);
		System.out.println("\nItem added to cart successfully!");
		return lineItems;

	}

	public List<LineItem> editItem(List<LineItem> lineItems, Scanner scanner) {
		System.out.println("===================================================");
		System.out.println("              Edit Item in Your Cart");
		System.out.println("===================================================");

		System.out.print("Enter the Food ID of the item you wish to update: ");
		String foodID = scanner.next();

		System.out.print("Enter the new number of units: ");
		double units = scanner.nextDouble();

		boolean updated = false;
		for (LineItem item : lineItems) {
			if (item.getItemID().equals(foodID)) {
				item.setQuantity(units);
				item.setlineTotal(units * item.getUnitPrice());
				System.out.println("Cart Updated Successfully");
				updated = true;
				return lineItems;
			}
		}
		if (!updated) 
			throw new FoodNotFound(foodID);
//			System.out.println("\nNo item found with Food ID '" + foodID + "'. Please check and try again.");
			
		System.out.println("===================================================");
		return lineItems;
	}

	public List<LineItem> removeItem(List<LineItem> lineItems, Scanner scanner) {
		System.out.println("===================================================");
		System.out.println("             Remove Item From Cart");
		System.out.println("===================================================");

		System.out.print("Enter the Food ID you wish to remove: ");
		String foodID = scanner.next();

		boolean removed = lineItems.removeIf(item -> item.getItemID().equalsIgnoreCase(foodID));

//		if (removed) {
//			System.out.println("\n Item with Food ID '" + foodID + "' removed successfully from your cart.");
//		} else {
//			System.out.println("\nNo item found with Food ID '" + foodID + "'. Please check and try again.");
//		}
		if(!removed)
			throw new FoodNotFound(foodID);
		System.out.println("\n Item with Food ID '" + foodID + "' removed successfully from your cart.");
		System.out.println("===================================================");
		return lineItems;
	}
}
