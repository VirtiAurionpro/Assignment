package com.aurionpro.model.Customer;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aurionpro.model.IRegister;

public class CustomerRegister implements IRegister {
	private static int counter = 0;

	public List<CustomerObject> start(List<CustomerObject> CustomerList) {
		System.out.println("========================================");
		System.out.println("         Customer Registration");
		System.out.println("========================================");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your name:");
		String name = scanner.next();
		String email = validateEmail(scanner);
		System.out.println("Enter your address details");
		scanner.nextLine();
		String address = scanner.nextLine();
		long phoneNumber = validatephone(scanner);
		String CustomerUsername = validateUsername(scanner);
		while (true) {
			for (CustomerObject Customer : CustomerList) {
				if (Customer.getCustomerUsername() == CustomerUsername) {
					System.out.println("\nUsername already exists. Please choose a different one.");
				}
			}
			break;
		}
		String CustomerPassword = validatePassword(scanner);
		String CustomerID = generateID();
		CustomerList.add(
				new CustomerObject(CustomerID, name, address, email, phoneNumber, CustomerUsername, CustomerPassword));
		System.out.println("\n Your customer account has been created successfully!");
		System.out.println("----------------------------------------");
		System.out.println("Customer ID    : " + CustomerID);
		System.out.println("Name           : " + name);
		System.out.println("Email          : " + email);
		System.out.println("Address        : " + address);
		System.out.println("Phone Number   : " + phoneNumber);
		System.out.println("Username       : " + CustomerUsername);
		System.out.println("----------------------------------------");
		return CustomerList;
	}

	private String validateEmail(Scanner scanner) {
		while (true) {
			System.out.println("--------------------------------------------------");
			System.out.print("Please enter your email ID: ");
			String email = scanner.next();
			String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
			Pattern p = Pattern.compile(emailRegex);
			if (email != null && p.matcher(email).matches()) {
				return email;
			}
			System.out.println("Invalid email format. Please try again.");
		}
	}

	private long validatephone(Scanner scanner) {
		long phoneNumber;
		while (true) {
			System.out.println("--------------------------------------------------");
			System.out.print("Enter your 10-digit phone number: ");
			phoneNumber = scanner.nextLong();
			String phoneStr = String.valueOf(phoneNumber);
			System.out.println();
			if (phoneStr.length() != 10 || phoneStr.startsWith("0")) {
				System.out.println("Invalid phone number. It must be 10 digits and cannot start with 0.");
				continue;
			}
			return phoneNumber;
		}
	}

	@Override
	public String generateID() {
		counter += 1;
		return "CU" + counter;
	}

	@Override
	public String validateUsername(Scanner scanner) {
		System.out.println("\nWould you like to use your email ID as your username?");
		System.out.print("Press Y to confirm, or any other key to skip: ");
		String ans = scanner.next().toLowerCase();
		String username;
		if (ans.equals("y")) {
//			System.out.println("Please enter your email ID");
//			username = scanner.next();
//			String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
//			Pattern p = Pattern.compile(emailRegex);
//			if (username != null && p.matcher(username).matches()) {
//				return username;
//			}
//			System.out.println("Email not valid.");
//			System.out.println("Please try another email");
//			validateUsername(scanner);
			username = validateEmail(scanner);
		}
		System.out.println("Enter your username:");
		username = scanner.next();
		return username;
	}

	@Override
	public String validatePassword(Scanner scanner) {
		System.out.println("\nCreate your password:");
		String password = scanner.next();
		String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,20}$";
		Pattern pattern = Pattern.compile(regex);
		if (password == null) {
			System.out.println("Password field cannot be empty.");
			validatePassword(scanner);
		}
		Matcher matcher = pattern.matcher(password);
		if (matcher.matches()) {
			System.out.println("Confirm your password:");
			while (true) {
				String rePassword = scanner.next();
				if (password.equals(rePassword)) {
					return password;
				}
				System.out.println("Passwords do not match. Try again:");
			}
		}
		System.out.println("\nInvalid password format.");
		System.out.println("Password must contain:");
		System.out.println("- At least 8 characters");
		System.out.println("- At least 1 uppercase letter");
		System.out.println("- At least 1 lowercase letter");
		System.out.println("- At least 1 digit");
		System.out.println("- At least 1 special character (!@#$%^&+=)");
		validatePassword(scanner);
		return password;
	}

}
