package com.aurionpro.exceptionss;

public class MinimumBalanceViolatedException extends RuntimeException {
	private double balance;

	public MinimumBalanceViolatedException(double balance) {
		super();
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getMessage() {
		return "Your balance is " + balance + " You cannot withdraw money as balance will go below minimum balance.";
	}
}
