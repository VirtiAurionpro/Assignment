package com.aurionpro.model.Customer;

public class CustomerObject {
	private String name;
	private String address;
	private String email;
	private long phoneNumber;
	private String customerID;
	private String customerUsername;
	private String customerPassword;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getCustomerUsername() {
		return customerUsername;
	}

	public void setCustomerUsername(String customerUsername) {
		this.customerUsername = customerUsername;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public CustomerObject(String customerID, String name, String address, String email, long phoneNumber,
			String customerUsername, String customerPassword) {
		super();
		this.name = name;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.customerID = customerID;
		this.customerUsername = customerUsername;
		this.customerPassword = customerPassword;
	}

	public CustomerObject() {
		super();
	}

	@Override
	public String toString() {
		return "CustomerObject [name=" + name + ", address=" + address + ", email=" + email + ", customerID="
				+ customerID + ", customerUsername=" + customerUsername;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
