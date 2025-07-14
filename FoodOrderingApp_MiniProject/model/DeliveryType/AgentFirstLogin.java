package com.aurionpro.model.DeliveryType;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AgentFirstLogin {

	public String start(String agentID) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("==============================================");
		System.out.println("            Agent Password Setup");
		System.out.println("==============================================");
		System.out.println("Hello " + agentID + "! Let's secure your account.");
		String agentPassword = validatePassword(scanner);
		return agentPassword;
	}

	private String validatePassword(Scanner scanner) {
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
