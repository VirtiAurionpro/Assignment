package com.aurionpro.model.Admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.aurionpro.model.Exceptions.AlreadyExists;
import com.aurionpro.model.Exceptions.InvalidChoice;
import com.aurionpro.model.Exceptions.MenuNotFound;

public class MenuOperations {
	private static int counter = 0;

	public MenuOperations() {
		super();
	}

	public void createMenu(Scanner scanner, List<MenuObject> menus) {
		List<FoodObject> foods = new ArrayList<>();

		System.out.println("=======================================");
		System.out.println("         Create New Menu");
		System.out.println("=======================================");

		System.out.print("Enter Menu Type: ");
		String menuType = scanner.next();
		for (MenuObject menu : menus) {
			if (menu.getmenuType().equalsIgnoreCase(menuType)) {
				throw new AlreadyExists(menuType);
			}
		}
		System.out.print("Enter a brief description for the menu: ");
		String description = scanner.next();

		System.out.println("\nEnter Food Details for the Menu:");
		FoodOperations food = new FoodOperations();
		foods = food.addFood(scanner, foods);

		String menuID = generateID();
		menus.add(new MenuObject(menuID, menuType, description, foods));

		System.out.println("\nNew Menu created successfully!");
		System.out.println("---------------------------------------");
		System.out.println("Menu ID   : " + menuID);
		System.out.println("Type      : " + menuType);
		System.out.println("Description: " + description);
		System.out.println("---------------------------------------");
	}

	private String generateID() {
		counter += 1;
		return "MN" + counter;
	}

	public void displayMenu(Scanner scanner, List<MenuObject> menus) {
		System.out.println("--------------------------------------------------");
		System.out.println("                  View Menu");
		System.out.println("--------------------------------------------------");
		System.out.print("Enter the menu ID you wish to view: ");
		String newmenuID = scanner.next();
		int flag = 0;
		for (MenuObject menu : menus) {
			if (newmenuID.equalsIgnoreCase(menu.getmenuID())) {
				System.out.println("\nFetching Menu Details:");
				System.out.println(menu);
				flag = 1;
				break;
			}
		}
		if (flag == 0) {
//			System.out.println("\nMenu type '" + newmenuType + "' does not exist.");
//			System.out.println("Please check the spelling or try another type.");
			throw new MenuNotFound(newmenuID);
		}
		scanner.nextLine();
	}

	public void editMenu(Scanner scanner, List<MenuObject> menus) {
		while (true) {
			System.out.println("=======================================");
			System.out.println("         Edit Menu Parameters");
			System.out.println("=======================================");
			System.out.println("Choose what you'd like to update:");
			System.out.println("1. Menu Type");
			System.out.println("2. Menu Description");
			System.out.println("3. Food Details");
			System.out.println("4. Exit");
			System.out.print("Enter your choice (1-4): ");
			try {
				int choice = scanner.nextInt();
				if (choice == 4)
					break;
				System.out.print("Enter the Menu ID to update type: ");
				String menuID = scanner.next();
				switch (choice) {
				case 1: {
					int flag = 0;
					System.out.println("\n--------------------------------------------------");
					for (MenuObject menu : menus) {
						if (menu.getmenuID().equalsIgnoreCase(menuID)) {
							System.out.println("Enter the new menu type:");
							String newMenuType = scanner.next();
							menu.setmenuType(newMenuType);
							System.out.println("Menu Type updated successfully!!");
							System.out.println(menu);
							flag = 1;
							break;
						}
					}
					if (flag == 0)
						throw new MenuNotFound(menuID);
					break;
				}
				case 2: {
					int flag = 0;
					System.out.println("\n--------------------------------------------------");
					for (MenuObject menu : menus) {
						if (menu.getmenuID().equalsIgnoreCase(menuID)) {
							System.out.println("Enter the new description");
							String newMenuDescription = scanner.next();
							menu.setDescription(newMenuDescription);
							System.out.println("Menu Description updated successfully!!");
							System.out.println(menu);
							flag = 1;
							break;
						}
					}
					if (flag == 0)
						throw new MenuNotFound(menuID);
					break;
				}
				case 3: {
					int flag = 0;
					System.out.println("\n--------------------------------------------------");
					for (MenuObject menu : menus) {
						try {
							if (menu.getmenuID().equalsIgnoreCase(menuID)) {
								flag = 1;
								menu.setFood(new FoodOperations().editFood(scanner, menu.getFood()));
								System.out.println("Food details updated successfully!!");
								System.out.println(menu);
								break;
							}
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					}
					if (flag == 0)
						throw new MenuNotFound(menuID);
					break;
				}
				case 4:
					return;
				default:
					throw new InvalidChoice();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void removeItems(Scanner scanner, List<MenuObject> menus) {
		scanner.nextLine();
		System.out.println("Enter the Menu ID to remove:");
		String menuID = scanner.next();
		boolean removed = menus.removeIf(item -> item.getmenuID().equalsIgnoreCase(menuID));
		if (!removed)
			throw new MenuNotFound(menuID);
		System.out.println("\nMenu with ID '" + menuID + "' was successfully removed.");
	}
}
