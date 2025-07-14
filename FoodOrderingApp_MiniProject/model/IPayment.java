package com.aurionpro.model;

public interface IPayment {
	void getDetails();
	boolean validate();
	boolean donePayment(double billAmount);
}
