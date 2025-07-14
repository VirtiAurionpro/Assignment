package com.aurionpro.model.Payment;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

import com.aurionpro.model.IPayment;
import com.aurionpro.model.Customer.basicDetails;

public class CreditCardPayment extends basicDetails implements IPayment{

	private int CVV;
	private String accountHolderName;
	private String accountValidity;
	private int OTP;
	private int userOTP;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
	Random random = new Random();
	Scanner scanner = new Scanner(System.in);

	public CreditCardPayment(int cVV, String accountHolderName, String accountValidity, int oTP, int userOTP) {
		super();
		CVV = cVV;
		this.accountHolderName = accountHolderName;
		this.accountValidity = accountValidity;
		OTP = oTP;
		this.setUserOTP(userOTP);
	}

	public CreditCardPayment() {
		super();
	}

	public int getOTP() {
		return OTP;
	}

	public void setOTP(int oTP) {
		OTP = oTP;
	}

	public int getUserOTP() {
		return userOTP;
	}

	public void setUserOTP(int userOTP) {
		this.userOTP = userOTP;
	}

	public int getCVV() {
		return CVV;
	}

	public void setCVV(int cVV) {
		CVV = cVV;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getAccountValidity() {
		return accountValidity;
	}

	public void setAccountValidity(String accountValidity) {
		this.accountValidity = accountValidity;
	}

	public void getDetails() {
		super.basicDetails();
		System.out.println("Enter Account Holder Name");
		accountHolderName = scanner.next();
		System.out.println("Enter Account Validity");
		accountValidity = scanner.next();
		System.out.println("Enter CVV");
		CVV = scanner.nextInt();
		OTP = generateOTP();
		System.out.println(OTP);
		System.out.println("Enter OTP");
		userOTP = scanner.nextInt();
	}

	private int generateOTP() {
		return 1000 + random.nextInt(9999);
	}

	@SuppressWarnings("unused")
	@Override
	public boolean validate() {
		try {
			YearMonth yearMonth = YearMonth.parse(accountValidity, formatter);
		} catch (Exception e) {
			System.out.println("Invalid Account Validity");
			return false;
		}
		if (OTP != userOTP || ((super.getPaymentID()).length()) != 16)
			return false;
		return true;
	}

	@Override
	public boolean donePayment(double billAmount) {
		if (validate()) {
			System.out.println("Payemnt done of " + billAmount + " using credit card.");
			return true;
		}
		if (!validate())
			System.out.println("Payment failed!Please Try Again");
		return false;
	}

}
