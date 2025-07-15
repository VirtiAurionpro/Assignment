package com.aurionpro.model.Admin;

import java.util.Scanner;

import com.aurionpro.model.Exceptions.AccountNotFound;

public class AdminLogin {

	public AdminObject start(AdminObject admin, String adminID) {
		if(!admin.getAdminID().equalsIgnoreCase(adminID)) {
			throw new AccountNotFound(adminID);
		}
		if(admin.getadminPassword()==null) {
			admin=new AdminFirstLogin().start(admin,adminID);
			return admin;
		}
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your username:");
		String adminUsername = scanner.next();
		System.out.println("Enter your password:");
		String adminPassword = scanner.next();
		if(!admin.getadminUsername().equals(adminUsername) || !admin.getadminPassword().equals(adminPassword)) {
			System.out.println("Invalid Credentials");
			System.out.println("Please try again");
			return null;
		}
		System.out.println("Login successful. Redirecting to Admin Dashboard...");
		return admin;
	}

}
