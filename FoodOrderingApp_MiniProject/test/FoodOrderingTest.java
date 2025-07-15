package com.aurionpro.test;

import java.util.Scanner;

import com.aurionpro.model.Admin.Admin;
import com.aurionpro.model.Customer.Customer;
import com.aurionpro.model.DeliveryType.DeliveryAgent;
import com.aurionpro.model.Exceptions.InvalidChoice;

public class FoodOrderingTest {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		Admin adminModule = new Admin();
		System.out.println("==============================================");
		System.out.println("Welcome to Shah's Food Delivery App");
		System.out.println("==============================================");
		System.out.println("Premium quality food delivered fast. Lowest prices guaranteed.");
		System.out.println("We're pleased to have you here.");
		System.out.println("----------------------------------------------");
		while (true) {
			System.out.println("\nSelect Your Role");
			System.out.println("1. Admin Login");
			System.out.println("2. User Login");
			System.out.println("3. Delivery Agent Login");
			System.out.println("4. Exit");
			System.out.print("Enter your choice (1-3): ");
			try {
				int choice = scanner.nextInt();
				switch (choice) {
				case 1: {
					System.out.println("Redirecting to Admin Login...");
					adminModule.start();
					break;
				}
				case 2: {
					System.out.println("Redirecting to User Login...");
					new Customer().start();
					break;
				}
				case 3: {
					System.out.println("Redirecting to Agent Login...");
					new DeliveryAgent().start();
					break;
				}
				case 4: {
					System.out.println("Thank you for using Shah's Food Delivery App. See you again soon!");
					scanner.close();
					return;
				}
				default: {
					throw new InvalidChoice();
				}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
