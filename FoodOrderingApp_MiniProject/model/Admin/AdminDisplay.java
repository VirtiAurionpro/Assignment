package com.aurionpro.model.Admin;

import java.util.List;
import java.util.Scanner;

import com.aurionpro.model.MenuRepository;
import com.aurionpro.model.Exceptions.InvalidChoice;

public class AdminDisplay {

	public void start(String adminID) {
		List<MenuObject> availableMenus = MenuRepository.menus;
		MenuObject menuObject = new MenuObject();
		availableMenus = menuObject.addItems();
		MenuOperations newMenu = new MenuOperations();
		DeliveryAgentOperations newDeliveryAgent = new DeliveryAgentOperations();

		System.out.println("==============================================");
		System.out.println("Welcome, " + adminID + "!");
		System.out.println("==============================================");
		System.out.println("Please select one of the tasks below:");
		System.out.println("----------------------------------------------");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("\nWhat would you like to do?");
			System.out.println("1. Menu Operations");
			System.out.println("2. Delivery Agent Operations");
			System.out.println("3. Logout");
			System.out.print("Enter your choice (1-3): ");
			int ch = scanner.nextInt();

			switch (ch) {

			case 1: {
				System.out.println("\nRedirecting to Menu Operations...");
				while (true) {
					System.out.println("=======================================");
					System.out.println("           Menu Management");
					System.out.println("=======================================");
					System.out.println("Please choose an operation:");
					System.out.println("1. Create a Menu");
					System.out.println("2. View Menu");
					System.out.println("3. Edit Menu");
					System.out.println("4. Remove Item");
					System.out.println("5. Exit");
					System.out.print("Enter your choice (1-5): ");
					try {
						int choice = scanner.nextInt();
						scanner.nextLine();
						switch (choice) {
						case 1:
							newMenu.createMenu(scanner, availableMenus);
							break;
						case 2:
							newMenu.displayMenu(scanner, availableMenus);
							break;
						case 3:
							newMenu.editMenu(scanner, availableMenus);
							break;
						case 4:
							newMenu.removeItems(scanner, availableMenus);
							break;
						case 5:
							return;
						default:
							throw new InvalidChoice();
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			}
			case 2: {
				while (true) {
					System.out.println("=======================================");
					System.out.println("      Delivery Agent Operations");
					System.out.println("=======================================");
					System.out.println("Please choose an option below:");
					System.out.println("1. Add Delivery Agent");
					System.out.println("2. View Agents");
					System.out.println("3. Edit Agent Details");
					System.out.println("4. Remove Agent");
					System.out.println("5. Exit");
					System.out.print("Enter your choice (1-5): ");
					try {
						int choice = scanner.nextInt();

						switch (choice) {
						case 1:
							newDeliveryAgent.addAgent(scanner);
							break;
						case 2:
							newDeliveryAgent.displayAgents(scanner);
							break;
						case 3:
							newDeliveryAgent.editAgent(scanner);
							break;
						case 4:
							newDeliveryAgent.removeAgent(scanner);
							break;
						case 5:
							return;
						default:
							throw new InvalidChoice();
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			}

			case 3: {
				System.out.println("--------------------------------------------------");
				System.out.println("Do you wish to stay logged in? (yes/no)");
				System.out.print("Enter your choice: ");

				String ans = scanner.next();
				if (!ans.equalsIgnoreCase("yes")) {
					System.out.println("Logging out. See you again!");
					return;
				}
				System.out.println("Continuing session...");
				break;
			}
			}
		}
	}
}
