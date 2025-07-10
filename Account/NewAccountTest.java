package com.aurionpro.newtest;

import java.util.Scanner;
import com.aurionpro.newmodel.NewAccount;

public class NewAccountTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		NewAccount accounts[] = new NewAccount[5];
		String accountNumberInput = null;
		int numberOfAccounts = 0;

		int flag = 0;
		while (flag == 0) {
			System.out.println("\n1.Create Account\n2.View Balance\n3.Deposit\n4.Withdraw\n5.Exit");
			System.out.println("Enter your choice");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1: {try {
				NewAccount acc = new NewAccount();
				accounts[numberOfAccounts] = acc.createAccount(scanner);
				System.out.println(accounts[numberOfAccounts]);
				numberOfAccounts += 1;
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
				break;
			}
			case 2: {
				System.out.println("Enter you account number:");
				accountNumberInput = scanner.next();
				for (int i = 0; i < numberOfAccounts; i++) {
					if (accounts[i].getAccountNumber().equals(accountNumberInput)) {
						System.out.println("Balance is:" + accounts[i].getBalance());
						break;
					}
				}
				break;
			}
			case 3: {
				depositMoney(scanner, accounts, accountNumberInput, numberOfAccounts);
				break;
			}
			case 4: {
				withdrawMoney(scanner, accounts, accountNumberInput, numberOfAccounts);
				break;
			}
			}
		}
	}

	private static void withdrawMoney(Scanner scanner, NewAccount[] accounts, String accountNumberInput,
			int numberOfAccounts) {
		System.out.println("Enter you account number:");
		accountNumberInput = scanner.next();
		for (int i = 0; i < numberOfAccounts; i++) {
			if (accounts[i].getAccountNumber().equals(accountNumberInput)) {
				accounts[i].withdrawMoney(accounts[i], scanner, accountNumberInput);
				break;
			}
		}
	}

	private static void depositMoney(Scanner scanner, NewAccount[] accounts, String accountNumberInput,
			int numberOfAccounts) {
		System.out.println("Enter you account number:");
		accountNumberInput = scanner.next();
		for (int i = 0; i < numberOfAccounts; i++) {
			if (accounts[i].getAccountNumber().equals(accountNumberInput)) {
				accounts[i].withdrawMoney(accounts[i], scanner, accountNumberInput);
				break;
			}
		}
	}
}
