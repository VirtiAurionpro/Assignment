package com.aurionpro.newmodel;

import java.util.Scanner;

import com.aurionpro.exceptionss.MinimumBalanceViolatedException;


public class SavingsAccount extends NewAccount {

	private final static double balance = 500;

	public SavingsAccount(String name, double balance, AccountType accountType) {
		super(name, balance, accountType);
	}

	public SavingsAccount() {
	}

	public double debit(Scanner scanner, String accountNumberInput) {
		System.out.println("Enter money to withdraw:");
		double withdraw = scanner.nextDouble();
//		if ((super.getBalance() - withdraw < balance)) {
//			System.out.println("You cannot withdraw money as balance will go below minimum balance.");
//			return super.getBalance();
//		}
		if ((super.getBalance() - withdraw < balance)) {
			throw new MinimumBalanceViolatedException(balance);
//			return super.getBalance();
		}
		System.out.println("Money has been debited from account " + accountNumberInput);
		return (super.getBalance() - withdraw);
	}

	public double credit(Scanner scanner, String accountNumberInput) {
		System.out.println("Enter money to deposit:");
		double deposit = scanner.nextDouble();
		System.out.println("Money has been credited into account " + accountNumberInput);
		return (deposit + super.getBalance());
	}

}
