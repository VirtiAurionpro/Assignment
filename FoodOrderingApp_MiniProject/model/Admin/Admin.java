package com.aurionpro.model.Admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.aurionpro.model.Exceptions.InvalidChoice;

public class Admin {
	List<AdminObject> AdminList = new ArrayList<>();

	@SuppressWarnings("resource")
	public void start() {
		Scanner scanner = new Scanner(System.in);
		for (AdminObject admin : AdminList)
			System.out.println(admin);
		while (true) {
			System.out.println("==================================");
			System.out.println("         Admin Portal");
			System.out.println("==================================");
			System.out.println("Please select an option below:");
			System.out.println("1. Admin Login");
			System.out.println("2. Admin Register");
			System.out.println("3. Exit");
			System.out.print("Enter your choice (1-3): ");
			try {
			int choice = scanner.nextInt();
			switch (choice) {
			case 1: {
				AdminList.add(new AdminObject("virti", 21, "A1", "virti", "shah"));
				System.out.println("--------------------------------------------------");
				System.out.println("              Admin Login");
				System.out.println("--------------------------------------------------");
				try {
					System.out.println("Enter your Admin ID:");
					String adminID = scanner.next();
					AdminLogin alogin = new AdminLogin();
					if (alogin.start(AdminList, adminID))
						new AdminDisplay().start(adminID);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case 2: {
				System.out.println("--------------------------------------------------");
				System.out.println("              Admin Registration");
				System.out.println("--------------------------------------------------");
				AdminList=new AdminRegister().start(AdminList);
				break;
			}
			case 3: {
				System.out.println("Thank you for visiting. Exiting Admin Portal...");
				return;
			}
			default: throw new InvalidChoice();
			}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
