package com.aurionpro.model.Customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.aurionpro.model.MenuRepository;
import com.aurionpro.model.Admin.MenuObject;
import com.aurionpro.model.Discount.Discount;
import com.aurionpro.model.Payment.Payment;

public class CustomerDisplay {
	Scanner scanner = new Scanner(System.in);
	List<OrderList> orders = new ArrayList<>();
	List<LineItem> lineItems = new ArrayList<>();
	public static List<InvoiceObject> invoices = new ArrayList<>();
	PaymentObject paymentObject;

	public void start(List<CustomerObject> customerList, String customerID) {
		List<MenuObject> availableMenus = MenuRepository.menus;
		String customerName = null;
		MenuObject menuObject = new MenuObject();
		availableMenus = menuObject.addItems();

		for (CustomerObject customer : customerList) {
			if (customer.getCustomerID().equalsIgnoreCase(customerID)) {
				customerName = customer.getName();
				break;
			}
		}

		System.out.println("==================================================");
		System.out.println("         Welcome, " + customerName + "!");
		System.out.println("==================================================");
		System.out.println("Here are the various cuisines available:");
		System.out.println("--------------------------------------------------");

		for (MenuObject availableMenu : availableMenus) {
			System.out.println("\nMenu: " + availableMenu.getmenuType());
			System.out.println("\nMenu ID: " + availableMenu.getmenuID());
			System.out.println("Description: " + availableMenu.getDescription());
			System.out.println(
					"=========================================================================================================");
//		    System.out.printf("| %-10s | %-15s | %-20s | %-10s | %-30s |\n",
//		        "Food ID", "Food Type", "Name", "Price", "Ingredients");
//		    System.out.println("=========================================================================================================");
//		    for (FoodObject availableFood : availableMenu.getFood()) {
//		        System.out.println(availableFood);
//		    }
//		    System.out.println("=========================================================================================================\n");
		}
		LineItemOperations lineItem = new LineItemOperations();
		int flag = 0;
		while (flag == 0) {
			System.out.println("\nWould you like to place an order?");
			System.out.print("Press 'Y' for Yes or 'N' for No: ");
			String choice = scanner.next().toUpperCase();
			switch (choice) {
			case "Y": {
				try {
					System.out.println("\nRedirecting to food ordering...");
					lineItems = lineItem.addItem(lineItems, availableMenus, scanner);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case "N": {
				System.out.println("\nThank you for visiting!");
				System.out.println("Press 5 to exit.");
				System.out.println("Press any other key to view the menu again.");
				int choice2 = scanner.nextInt();
				if (choice2 == 5)
					flag = 1;
				break;
			}
			}
		}

		int flag2 = 0;
		while (flag2 == 0) {
			boolean orderLoaded = false;
			System.out.println("==========================================");
			System.out.println("              Customer Menu");
			System.out.println("==========================================");
			System.out.println("Do you wish to:");
			System.out.println("1. View Cart");
			System.out.println("2. Edit Cart");
			System.out.println("3. View Order");
			System.out.println("4. Place Order");
			System.out.println("5. Logout");
			System.out.print("Enter your choice (1-5): ");
			try {
				int choice = scanner.nextInt();
				switch (choice) {
				case 1: {
					if (lineItems.isEmpty()) {
						System.out.println("\nYour cart is currently empty.");
						System.out.println(
								"========================================================================================");
						break;
					}
					System.out.println(
							"======================================================================================");
//				System.out.println(String.format("\n%-12s %-20s %-15s %-12s %-15s %-10s", "LineItemID", "FoodID",
//						"Item Name", "Item Units", "Item Price", "Total"));
					System.out.println(String.format("\n%-12s %-10s %-15s %-12s %-12s %-10s", "LineItemID", "FoodID",
							"Item Name", "Item Units", "Item Price", "Total"));

					for (LineItem item : lineItems) {
						System.out.println(item);
					}
					System.out.println(
							"========================================================================================");
					break;
				}

				case 2: {
					System.out.println("==========================================");
					System.out.println("             Edit Your Cart");
					System.out.println("==========================================");
					System.out.println("What would you like to do?");
					System.out.println("1. Add Item");
					System.out.println("2. Edit Item");
					System.out.println("3. Remove Item");
					System.out.println("4. Exit");
					System.out.print("Enter your choice (1-4): ");

					int choice2 = scanner.nextInt();

					switch (choice2) {
					case 1: {
						System.out.println("\nRedirecting to Add Item...");
						lineItems = lineItem.addItem(lineItems, availableMenus, scanner);
						break;
					}
					case 2: {
						System.out.println("\nRedirecting to Edit Item...");
						lineItems = lineItem.editItem(lineItems, scanner);
						break;
					}
					case 3: {
						System.out.println("\nRedirecting to Remove Item...");
						lineItems = lineItem.removeItem(lineItems, scanner);
						break;
					}
					case 4: {
						System.out.println("Returning to previous menu...");
						break;
					}
					}
					break;
				}

				case 3: {
					String id = new OrderList().generateId();
					LocalDate date = LocalDate.now();
					OrderList tempOrder = new OrderList();
					double orderTotal = tempOrder.calculateTotal(lineItems);
					OrderList order = new OrderList(id, date, lineItems, orderTotal, customerName);
					Discount discountObj = new Discount();
					discountObj.loadDiscounts();
					double discountAmount = discountObj.calculateDiscountedValue(orderTotal);
					order.setDiscount(discountAmount);
					orders.add(order);
					System.out.println(order);
					orderLoaded = true;
					break;
				}
				case 4: {
					if (!orderLoaded) {
						String id = new OrderList().generateId();
						LocalDate date = LocalDate.now();
						OrderList tempOrder = new OrderList();
						double orderTotal = tempOrder.calculateTotal(lineItems);
						OrderList order = new OrderList(id, date, lineItems, orderTotal, customerName);
						Discount discountObj = new Discount();
						discountObj.loadDiscounts();
						double discountAmount = discountObj.calculateDiscountedValue(orderTotal);
						order.setDiscount(discountAmount);
						orders.add(order);
					}
					System.out.println("==================================================");
					System.out.println("              Placing Your Order...");
					System.out.println("==================================================");
					System.out.println("\nRedirecting to payment...");
					OrderList order = orders.get(orders.size() - 1);
//				paymentObject = new Payment().start(orders.get(orders.size() - 1));
					paymentObject = new Payment().start(order);
					System.out.println("\nOrder has been placed successfully!");
//				new InvoiceGeneration().start(customerList, customerID, paymentObject, orders, invoices);
					new InvoiceGeneration().start(customerList, customerID, paymentObject, order, invoices);
					lineItems.clear();
					break;
				}
				case 5: {
					System.out.println("--------------------------------------------------");
					System.out.print("Do you wish to stay logged in? (yes/no): ");
					String ans = scanner.next();
					if (!ans.equalsIgnoreCase("yes")) {
						System.out.println("\nYou have been logged out. Have a great day!");
						return;
					}
					System.out.println("Journey continues...");
					break;
				}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
