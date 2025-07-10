package com.aurionpro.newmodel;

import java.util.Scanner;

import com.aurionpro.exceptionss.OverdraftLimitReachedException;

public class CurrentAccount extends NewAccount {
	private final double overdraft = 50000;;

	public CurrentAccount() {
		super();
	}

	public double getOverdraft() {
		return overdraft;
	}

	public CurrentAccount(String name, double balance, AccountType accountType) {
		super(name, balance, accountType);
	}

	public double credit(Scanner scanner, String accountNumberInput) {
		System.out.println("Enter money to deposit:");
		double deposit = scanner.nextDouble();
		System.out.println("Money has been credited into account " + accountNumberInput);
		return (deposit + super.getBalance());
	}

	public double debit(Scanner scanner, String accountNumberInput) {
		System.out.println("Enter money to withdraw:");
		double withdraw = scanner.nextDouble();
		if (super.getBalance() - withdraw < (-overdraft)) {
			throw new OverdraftLimitReachedException();
		}
		System.out.println("Money has been debited from account " + accountNumberInput);
		super.setBalance(super.getBalance() - withdraw);
		return super.getBalance();
	}
}
