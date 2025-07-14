package com.aurionpro.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer {
	List<CustomerObject> CustomerList = new ArrayList<>();
	Scanner scanner = new Scanner(System.in);

	@SuppressWarnings("resource")
	public void start() {
		System.out.println("=======================================");
		System.out.println("          Customer Portal");
		System.out.println("=======================================");
		for (CustomerObject Customer : CustomerList)
			System.out.println(Customer);
		while (true) {
			System.out.println("\nPlease choose one of the options below:");
			System.out.println("1. User Login");
			System.out.println("2. User Register");
			System.out.println("3. Exit");
			System.out.print("Enter your choice (1-3): ");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1: {
				CustomerList.add(new CustomerObject("C1", "virti", "bhayandar", "email", 1234567890, "virti", "shah"));
				try {
					System.out.println("========================================");
					System.out.println("         Customer Login");
					System.out.println("========================================");
					System.out.print("Enter your Customer ID: ");
					String CustomerID = scanner.next();
					CustomerLogin clogin = new CustomerLogin();
					if (clogin.start(CustomerList, CustomerID))
						new CustomerDisplay().start(CustomerList, CustomerID);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case 2: {
				CustomerList = new CustomerRegister().start(CustomerList);
				break;
			}
			case 3: {
				System.out.println("Thank you for visiting. Exiting Customer Portal...");
				return;
			}
			}
		}
	}
}
