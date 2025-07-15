package com.aurionpro.model.Admin;

import java.util.Scanner;
import com.aurionpro.model.Exceptions.InvalidChoice;

public class Admin {

	private AdminObject admin = new AdminObject("admin", null, null, null);

	public void start() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("==================================");
			System.out.println("        Admin Portal");
			System.out.println("==================================");
			System.out.println("Please select an option below:");
			System.out.println("1. Admin Login");
			System.out.println("2. Exit");
			System.out.print("Enter your choice (1-2): ");

			try {
				int choice = scanner.nextInt();

				switch (choice) {
				case 1: {
					System.out.println("==================================");
					System.out.println("         Admin Login");
					System.out.println("==================================");
					try {
						System.out.print("Enter your Admin ID: ");
						String adminID = scanner.next();
						AdminLogin alogin = new AdminLogin();
						AdminObject updatedAdmin = alogin.start(admin, adminID);
						if (updatedAdmin != null) {
							admin = updatedAdmin;
							new AdminDisplay().start(adminID);
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				case 2: {
					System.out.println("Exiting Admin Portal...");
					return;
				}
				default:
					throw new InvalidChoice();
				}
			} catch (InvalidChoice e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
