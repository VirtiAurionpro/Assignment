package com.aurionpro.model.Admin;

import java.util.List;
import java.util.Scanner;

import com.aurionpro.model.Exceptions.AlreadyExists;
import com.aurionpro.model.Exceptions.FoodNotFound;
import com.aurionpro.model.Exceptions.InvalidChoice;

public class FoodOperations {
	private static int counter = 0;

	public FoodOperations() {
		super();
	}

	public List<FoodObject> addFood(Scanner scanner, List<FoodObject> foods) {
		while (true) {
			System.out.println("=======================================");
			System.out.println("          Add New Food Item");
			System.out.println("=======================================");
			scanner.nextLine();
			System.out.print("Enter Food Type       : ");
			String foodType = scanner.nextLine();
			System.out.print("Enter Food Name       : ");
			String foodName = scanner.nextLine();
			for (FoodObject food : foods) {
				if (food.getFoodName().equalsIgnoreCase(foodName)) {
					throw new AlreadyExists(foodName);
				}
			}
			System.out.print("Enter Price           : ₹");
			double price = scanner.nextDouble();
			scanner.nextLine();

			System.out.print("Enter Description     : ");
			String description = scanner.nextLine();

			System.out.print("Enter Ingredients     : ");
			String ingredients = scanner.nextLine();

			System.out.println("Enter label(Veg/Non-Veg) :");
			String label=scanner.next();
			
			String foodID = generateID();
			FoodObject food = new FoodObject(foodID, foodType, foodName, price, description, ingredients,label);
			foods.add(food);
			System.out.println(
					"=============================================================================================================");
			System.out.printf("| %-10s | %-15s | %-20s | %-10s | %-30s |\n", "Food ID", "Food Type", "Name", "Price",
					"Ingredients");
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------");
			System.out.println(food);
			System.out.println("\nFood item added successfully!");
			System.out.print("Would you like to add another item? (Y/N): ");
			String ans = scanner.next();
			if (!ans.equalsIgnoreCase("y"))
				break;
		}
		return foods;
	}

	private String generateID() {
		counter += 1;
		return "FD" + counter;
	}

	public List<FoodObject> editFood(Scanner scanner, List<FoodObject> list) {
		while (true) {
			System.out.println("==========================================");
			System.out.println("           Current Food Items");
			System.out.println("==========================================");
			System.out.println(
					"===========================================================================================================");
			System.out.printf("| %-10s | %-15s | %-20s | %-10s | %-30s |\n", "Food ID", "Food Type", "Name", "Price",
					"Ingredients");
			System.out.println(
					"============================================================================================================");

			for (FoodObject food : list)
				System.out.println(food);

			System.out.println("\nWhat would you like to do?");
			System.out.println("1. Add Food");
			System.out.println("2. Edit Food");
			System.out.println("3. Remove Food");
			System.out.println("4. Exit");
			System.out.print("Enter your choice (1-4): ");
			try {
				int ch = scanner.nextInt();

				switch (ch) {
				case 1: {
					list = addFood(scanner, list);
					break;
				}
				case 2: {
					System.out.println("\nWhich parameter would you like to edit?");
					System.out.println("1. Food Type");
					System.out.println("2. Food Name");
					System.out.println("3. Food Price");
					System.out.println("4. Food Description");
					System.out.println("5. Food Ingredients");
					System.out.println("6. Exit");
					System.out.print("Enter your choice (1-6): ");
					int choice = scanner.nextInt();
					scanner.nextLine();
					if(choice==6)
						break;
					System.out.print("\nEnter the Food ID: ");
					String foodID = scanner.nextLine();
					boolean found = false;

					for (FoodObject food : list) {
						if (foodID.equalsIgnoreCase(food.getfoodID())) {
							found = true;
							switch (choice) {
							case 1:
								System.out.print("Enter new Food Type: ");
								food.setFoodType(scanner.nextLine());
								break;
							case 2:
								System.out.print("Enter new Food Name: ");
								food.setFoodName(scanner.nextLine());
								break;
							case 3:
								System.out.print("Enter new Food Price: ₹");
								food.setPrice(scanner.nextDouble());
								break;
							case 4:
								System.out.print("Enter new Description: ");
								food.setDescription(scanner.nextLine());
								break;
							case 5:
								System.out.print("Enter new Ingredients: ");
								food.setIngredients(scanner.nextLine());
								break;
							case 6:
								break;
							}
							System.out.println("\nFood details updated successfully.");
							break;
						}
					}
					if (!found)
						throw new FoodNotFound(foodID);
					break;
				}
				case 3: {
					System.out.println("Enter the Food ID:");
					String foodID = scanner.next();
					boolean removed = list.removeIf(item -> item.getfoodID().equalsIgnoreCase(foodID));
					if (!removed)
						throw new FoodNotFound(foodID);
					break;
				}
				case 4: {
					return list;
				}
				default:
					throw new InvalidChoice();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
