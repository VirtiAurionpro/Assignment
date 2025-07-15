package com.aurionpro.model.Admin;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aurionpro.model.IRegister;

public class AdminFirstLogin implements IRegister {

	public AdminObject start(AdminObject admin,String adminID) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your name:");
		String name = scanner.next();
		String adminUsername;
		adminUsername = validateUsername(scanner);
		String adminPassword = validatePassword(scanner);
		admin.setName(name);
		admin.setadminUsername(adminUsername);
		admin.setadminPassword(adminPassword);
		System.out.println("\nYour Admin Account has been successfully created.");
		System.out.println("--------------------------------------------------");
		System.out.println("Admin ID   : " + adminID);
		System.out.println("Name       : " + name);
		System.out.println("Username   : " + adminUsername);
		System.out.println("--------------------------------------------------");
		return admin;
	}
	
	@Override
	public String validateUsername(Scanner scanner) {
		System.out.println("\nWould you like to use your email ID as your username?");
		System.out.print("Press Y to confirm, or any other key to skip: ");
		String ans = scanner.next().toLowerCase();
		String username;
		if (ans.equals("y")) {
			System.out.println("Enter your email ID");
			username = scanner.next();
			String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
			Pattern p = Pattern.compile(emailRegex);
			if (username != null && p.matcher(username).matches()) {
				return username;
			}
			System.out.println("Invalid email format. Please try again.");
			validateUsername(scanner);
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

	@Override
	public String generateID() {
		return null;
	}

}
