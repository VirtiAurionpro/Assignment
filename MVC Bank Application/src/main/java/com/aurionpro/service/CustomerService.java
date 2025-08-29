package com.aurionpro.service;

import java.time.LocalDate;
import java.time.Period;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.aurionpro.dao.CustomerDao;
import com.aurionpro.model.Registration;

public class CustomerService {

	private CustomerDao customerDao;

	public CustomerService() {
		if (customerDao == null) {
			customerDao = new CustomerDao();
		}
	}

	public boolean checkUsername(String username) {
		return customerDao.checkUsername(username);
	}

	public boolean checkPassword(String username, String password) {
		return customerDao.checkPassword(username, password);
	}

	public boolean checkAccountExist(String username) {
		return customerDao.checkAccountExist(username);
	}

	public boolean checkCredentials(HttpServletRequest request, String username, String password) {
		HttpSession session = request.getSession();
		if (checkUsername(username)) {
			if (checkPassword(username, password)) {
				session.setAttribute("successMessage", "Login Successfully");
				return true;
			}
		}
		session.setAttribute("failureMessage", "Invalid Credentails");
		return false;
	}

	public boolean checkAdminCredentials(HttpServletRequest request, String username, String password) {
		HttpSession session = request.getSession();
		if (username.equals("admin@gmail.com") && password.equals("admin@123")) {
			session.setAttribute("successMessage", "Login Successfully");
			return true;
		}
		session.setAttribute("failureMessage", "Invalid admin Credentails");
		return false;
	}

	public boolean checkRegistrationDetails(HttpServletRequest request, String name, String email, String mobile,
			LocalDate dob, String password, String confirmpassword) {
		HttpSession session = request.getSession();
		LocalDate currentDate = LocalDate.now();
		Period age = Period.between(dob, currentDate);
		if (!password.equals(confirmpassword)) {
			session.setAttribute("failureMessage", "Passwords do not match");
			return false;
		}
		if (dob.isAfter(currentDate)) {
			session.setAttribute("failureMessage", "Birthdate cannot be in the future.");
			return false;
		}
		if (age.getYears() < 18) {
			session.setAttribute("warningMessage", "Age is below 18");
			return true;
		}
		if (mobile.length() != 10) {
			session.setAttribute("failureMessage", "Mobile number should be exacylt 10 digits");
			return false;
		}
		return true;
	}

	public String registerUser(String name, String email, String mobile, LocalDate dob, String password) {
		return customerDao.RegisterCustomer(name, email, mobile, dob, password);
	}

	public boolean updateCustomerDetails(Registration customer) {
		return customerDao.updateCustomerDetails(customer);
	}

}
