package com.aurionpro.model.Admin;

public class AdminObject {
	private String name;
	private String adminUsername;
	private String adminPassword;
	private int age;
	private String AdminID;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getadminUsername() {
		return adminUsername;
	}

	public void setadminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}

	public String getadminPassword() {
		return adminPassword;
	}

	public void setadminPassword(String password) {
		this.adminPassword = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAdminID() {
		return AdminID;
	}

	public void setAdminID(String adminID) {
		AdminID = adminID;
	}

	public AdminObject(String name, int age, String adminID, String adminUsername, String adminPassword) {
		super();
		this.name = name;
		this.adminUsername = adminUsername;
		this.adminPassword = adminPassword;
		this.age = age;
		AdminID = adminID;
	}

	public AdminObject() {
		super();
	}
}
