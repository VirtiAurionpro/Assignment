package com.aurionpro.model.Customer;

public class PaymentObject {
	private double paymentAmount;
	private String paymentMode;
	private String paymentStatus;
	private String customerName;

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getcustomerName() {
		return customerName;
	}

	public void setcustomerName(String customerName) {
		this.customerName = customerName;
	}

	public PaymentObject(String customerName, double paymentAmount, String paymentMode, String paymentStatus) {
		super();
		this.customerName = customerName;
		this.paymentAmount = paymentAmount;
		this.paymentMode = paymentMode;
		this.paymentStatus = paymentStatus;
	}

	public PaymentObject() {
		super();
	}
}
