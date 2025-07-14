package com.aurionpro.model.Customer;

import java.util.Scanner;

public class basicDetails {
	Scanner scanner = new Scanner(System.in);
	private String paymentID;
	public String getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(String paymentID) {
		this.paymentID = paymentID;
	}

	public void basicDetails() {
		System.out.println("Enter Payment ID/Number:");
		paymentID = scanner.next();
	}

//	public basicDetails(String paymentID) {
//		super();
//		this.paymentID = paymentID;
//	}
}
