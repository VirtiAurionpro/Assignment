package com.aurionpro.model.Admin;

import java.util.List;
import java.util.Scanner;

import com.aurionpro.model.Exceptions.AccountNotFound;

public class AdminLogin {

	public boolean start(List<AdminObject> adminList, String adminID) {
		int flag=0;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your username:");
		String adminUsername = scanner.next();
		System.out.println("Enter your password:");
		String adminPassword = scanner.next();
		for(AdminObject admin:adminList) {
			if(admin.getAdminID().equals(adminID)) {
				flag=1;
				if(!admin.getadminUsername().equals(adminUsername) || !admin.getadminPassword().equals(adminPassword)) {
					System.out.println("Invalid Credentials");
					System.out.println("Please try again");
					return false;
				}
			}
		}
		if(flag==0) {
//			System.out.println("Uh oh... Your account does not exist. Please register to continue.");
//			return false;
			throw new AccountNotFound(adminID);
		}
		System.out.println("Login successful. Redirecting to Admin Dashboard...");
		return true;
	}

}
