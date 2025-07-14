package com.aurionpro.model.Payment;

import java.util.Scanner;

import com.aurionpro.model.IPayment;
import com.aurionpro.model.Customer.basicDetails;

public class UPIPayment extends basicDetails implements IPayment {

	private int upiPin;
	private int userUpiPin;
	Scanner scanner = new Scanner(System.in);

	public int getUpiPin() {
		return upiPin;
	}

	public void setUpiPin(int upiPin) {
		this.upiPin = upiPin;
	}

	public int getUserUpiPin() {
		return userUpiPin;
	}

	public void setUserUpiPin(int userUpiPin) {
		this.userUpiPin = userUpiPin;
	}

	public UPIPayment() {
		super();
	}

	@Override
	public void getDetails() {
		CheckPin();
		super.basicDetails();
	}

	private void CheckPin() {
		System.out.println("Do you have UPI pin?Press 1 for yes 0 for no");
		int choice = scanner.nextInt();
		switch (choice) {
		case 0: {
			System.out.println("Enter UPI pin:");
			upiPin = scanner.nextInt();
			CheckPin();
			break;
		}
		case 1: {
			System.out.println("Enter your UPI pin:");
			userUpiPin = scanner.nextInt();
			break;
		}
		}
	}

	@Override
	public boolean validate() {
		if (upiPin == 0) {
			System.out.println("UPI Pin does not exist.Please create one.");
			getDetails();
		}
		return userUpiPin == upiPin;
	}

	@Override
	public boolean donePayment(double billAmount) {
		if (validate()) {
			System.out.println("Payemnt done of " + billAmount + " using UPI.");
			return true;
		}
		if (!validate())
			System.out.println("Payment failed!Please Try Again");
		return false;
	}
}
