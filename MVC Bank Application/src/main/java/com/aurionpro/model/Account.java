package com.aurionpro.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class Account {
	private String accountNo;
	private String customerID;
	private String name;
	private Date dob;
	private String email;
	private String mobileNo;
	private String panNo;
	private String addharno;
	private String accountType;
	private Date opening_date;
	private Double balance;
	private String ifscCode;
	private boolean isApproved;
	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getAddharno() {
		return addharno;
	}

	public void setAddharno(String addharno) {
		this.addharno = addharno;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Date getOpening_date() {
		return opening_date;
	}

	public void setOpening_date(Date opening_date) {
		this.opening_date = opening_date;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public Account(String accountNo, String customerID, String name, Date dob, String email, String mobileNo,
			String panNo, String addharno, String accountType, Date opening_date, Double balance, String ifscCode,boolean isApproved) {
		super();
		this.accountNo = accountNo;
		this.customerID = customerID;
		this.name = name;
		this.dob = dob;
		this.email = email;
		this.mobileNo = mobileNo;
		this.panNo = panNo;
		this.addharno = addharno;
		this.accountType = accountType;
		this.opening_date = opening_date;
		this.balance = balance;
		this.ifscCode = ifscCode;
		this.isApproved=isApproved;
	}

	public Account() {
		super();
	}

	public int getAge() {
		if (dob == null)
			return 0;

		LocalDate dobLocal;

		if (dob instanceof java.sql.Date) {
			dobLocal = ((java.sql.Date) dob).toLocalDate();
		} else {
			dobLocal = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		}

		return Period.between(dobLocal, LocalDate.now()).getYears();
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
}
