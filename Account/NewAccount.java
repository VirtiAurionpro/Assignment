package com.aurionpro.newmodel;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import com.aurionpro.exceptionss.MinimumBalanceViolatedException;
import com.aurionpro.exceptionss.NegativeInputException;
import com.aurionpro.exceptionss.OverdraftLimitReachedException;

public class NewAccount {
	private String accountNumber;
	private String name;
	private double balance;
	private AccountType accountType;
	private Set<Integer> uniqueAccountNumber = new HashSet<>();

	public NewAccount() {

	}

	public NewAccount(String name, double balance, AccountType accountType) {
		super();
		this.name = name;
		this.balance = balance;
		this.accountType = accountType;
	}

	public void generateAccountNumber() {
		Random random = new Random();
		int min = 100000;
		int max = 999999;
		int randomNumber = random.nextInt(max - min + 1) + min;
		if (uniqueAccountNumber.contains(randomNumber))
			generateAccountNumber();
		else
			uniqueAccountNumber.add(randomNumber);
		accountNumber = "IDBI1000" + Integer.toString(randomNumber);
	}

	@Override
	public String toString() {
		return "Account Details\nAccount Number=" + accountNumber + "\nName=" + name + "\nCurrent balance=" + balance
				+ "\nAccount Type=" + accountType;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public NewAccount createAccount(Scanner scanner) {
		System.out.println("Enter Name:");
		String name = scanner.next();
		double balance;
		while (true) {
			System.out.println("Enter initial amount:");
			balance = scanner.nextDouble();
			if (balance < 0) {
				throw new NegativeInputException();
			}
			if (balance < 500.0)
				System.out.println("Balance is insufficient.Minimum 500 is required.");
			else
				break;
		}
		AccountType accountType = null;
		System.out.println("Account Type:1.Savings or 2.Current\nEnter your choice:");
		int choice = scanner.nextInt();
		switch (choice) {
		case 1:
			accountType = AccountType.Savings;
			break;
		case 2:
			accountType = AccountType.Current;
			break;
		}
		NewAccount acc = new NewAccount(name, balance, accountType);
		acc.generateAccountNumber();
		return acc;
	}

	public void depositMoney(NewAccount account, Scanner scanner, String accountNumberInput) {
		if (account.getAccountType().equals(AccountType.Savings)) {
			SavingsAccount sa = new SavingsAccount(name, balance, accountType);
			balance = sa.credit(scanner, accountNumberInput);
			System.out.println("Balance:" + balance);
		}
		if (account.getAccountType().equals(AccountType.Current)) {
			CurrentAccount cu = new CurrentAccount(name, balance, accountType);
			System.out.println("Balance:" + balance);
			balance = cu.credit(scanner, accountNumberInput);
		}
	}

	public void withdrawMoney(NewAccount account, Scanner scanner, String accountNumberInput) {
		if (account.getAccountType().equals(AccountType.Savings)) {
			SavingsAccount sa = new SavingsAccount(name, balance, accountType);
			try {
				balance = sa.debit(scanner, accountNumberInput);
			} catch (MinimumBalanceViolatedException exception) {
				System.out.println(exception.getMessage());
			}
		}
		if (account.getAccountType().equals(AccountType.Current)) {
			CurrentAccount cu = new CurrentAccount(name, balance, accountType);
			try {
				balance = cu.debit(scanner, accountNumberInput);
//				if(balance<0)
//					balance=0;
			} catch (OverdraftLimitReachedException exception) {
				System.out.println(exception.getMessage());
			}
		}
	}
}
