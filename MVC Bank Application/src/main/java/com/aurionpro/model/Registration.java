package com.aurionpro.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class Registration {
	private String registration_id;
	private String name;
	private String email;
	private String mobileNo;
	private Date dob;
	private int age;

	public String getRegistration_id() {
		return registration_id;
	}

	public void setRegistration_id(String registration_id) {
		this.registration_id = registration_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Registration(String registration_id, String name, String email, String mobileNo, Date dob) {
		super();
		this.registration_id = registration_id;
		this.name = name;
		this.email = email;
		this.mobileNo = mobileNo;
		this.dob = dob;
	}

	public Registration() {
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

	public void setAge(int age) {
		this.age = age;
	}
}
