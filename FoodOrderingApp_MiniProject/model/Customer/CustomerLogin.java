package com.aurionpro.model.Customer;

import java.util.List;
import java.util.Scanner;

import com.aurionpro.model.Exceptions.AccountNotFound;

public class CustomerLogin {

	public boolean start(List<CustomerObject> customerList, String customerID) {
		int flag = 0;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your username:");
		String CustomerUsername = scanner.next();
		System.out.println("Enter your password:");
		String CustomerPassword = scanner.next();
		for (CustomerObject Customer : customerList) {
			if (Customer.getCustomerID().equals(customerID)) {
				flag = 1;
				if (!Customer.getCustomerUsername().equals(CustomerUsername)
						|| !Customer.getCustomerPassword().equals(CustomerPassword)) {
					System.out.println("Invalid Credentials");
					System.out.println("Please try again");
					return false;
				}
			}
		}
		if (flag == 0) {
//			System.out.println("Uh oh... Your account does not exist. Please register to continue.");
			throw new AccountNotFound(customerID);
//			return false;
		}
		System.out.println("Login successful. Redirecting to Customer Dashboard...");
		return true;
	}

}
