package com.aurionpro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.model.Registration;
import com.aurionpro.util.DatabaseConnection;

public class CustomerDao {

	private Connection connection = null;
	private DatabaseConnection dbConnection = null;
	PreparedStatement preparedStatement;
	ResultSet resultSet;

	public CustomerDao() {
		if (dbConnection == null) {
			dbConnection = DatabaseConnection.getInstance();
			connection = dbConnection.getConnection();
			setTrigger();
		}
	}

	public void setTrigger() {
	}

	public boolean checkUsername(String username) {
		String sql = "select 1 from bank_app.login l join bank_app.customer c on l.login_id = c.registration_id where (l.login_id = ? or l.login_email = ?)  and c.is_Active = true";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, username);
			resultSet = preparedStatement.executeQuery();
//			System.out.println(resultSet.getInt(1));
			return resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkPassword(String username, String password) {
		String sql = "select 1 from bank_app.login l join bank_app.customer c on l.login_id = c.registration_id where (login_id = ? or login_email = ?)  and password=? and c.is_Active = true";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, username);
			preparedStatement.setString(3, password);
			resultSet = preparedStatement.executeQuery();
			return resultSet.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkAccountExist(String username) {
		boolean accountExist = false;
		String sql = "select has_Account from bank_app.customer where registration_id=? and is_Active=true";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				accountExist = resultSet.getBoolean("has_Account");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return accountExist;
	}

	public String RegisterCustomer(String name, String email, String mobile, LocalDate dob, String password) {
		String newRegistrationId = "RU0001";
		boolean isActive = true;
		boolean hasAccount = false;
		boolean isFirstLogin = false;
		String role = "customer";
		System.out.println("lkhj");
		try {

			String sql = "select registration_id from bank_app.customer order by registration_id DESC LIMIT 1";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				String lastId = resultSet.getString("registration_id");
				int num = Integer.parseInt(lastId.substring(2));
				num++;
				newRegistrationId = String.format("RU%04d", num);
			}

			String insertCustomer = "insert into bank_app.customer (registration_id, customer_name, customer_email, customer_dob, customer_mobile_no, is_active, has_account) VALUES (?, ?, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(insertCustomer);
			preparedStatement.setString(1, newRegistrationId);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, email);
			preparedStatement.setDate(4, java.sql.Date.valueOf(dob));
			preparedStatement.setString(5, mobile);
			preparedStatement.setBoolean(6, isActive);
			preparedStatement.setBoolean(7, hasAccount);
			int update1 = preparedStatement.executeUpdate();

			System.out.println("update1" + update1);

			String insertLogin = "insert into bank_app.login (login_id, login_email, password, role, is_first_login) VALUES (?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(insertLogin);
			preparedStatement.setString(1, newRegistrationId);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, password);
			preparedStatement.setString(4, role);
			preparedStatement.setBoolean(5, isFirstLogin);
			int update2 = preparedStatement.executeUpdate();

			System.out.println("update2" + update2);

			if (update1 > 0 && update2 > 0)
				;
			return newRegistrationId;

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	public Registration getCustomerByIdentifier(String identifier) {
		Registration customer = null;
		String sql = "select c.registration_id,c.customer_name,c.customer_email,c.customer_dob,c.customer_mobile_no \r\n"
				+ "from bank_app.login l join bank_app.customer c on l.login_id = c.registration_id \r\n"
				+ "where (l.login_id = ? or l.login_email = ? ) and c.is_Active = true";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, identifier);
			preparedStatement.setString(2, identifier);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				customer = new Registration();
				customer.setRegistration_id(resultSet.getString(1));
				customer.setName(resultSet.getString(2));
				customer.setEmail(resultSet.getString(3));
				customer.setDob(resultSet.getDate(4));
				customer.setMobileNo(resultSet.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}

	public List<Registration> getAllCustomers() {
		String sql = "select * from bank_app.customer where is_Active=true";
		List<Registration> customers = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Registration customer = new Registration();
				customer.setRegistration_id(resultSet.getString(1));
				customer.setName(resultSet.getString(2));
				customer.setEmail(resultSet.getString(3));
				customer.setDob(resultSet.getDate(4));
				customer.setMobileNo(resultSet.getString(5));
				customers.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}

	public boolean updateCustomerDetails(Registration customer) {
		String sql = "update bank_app.customer set customer_name=?,customer_email=?,customer_dob=?,customer_mobile_no=? where registration_id=? and is_Active=true";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(2, customer.getEmail());
			preparedStatement.setDate(3, new java.sql.Date(customer.getDob().getTime()));
			preparedStatement.setString(4, customer.getMobileNo());
			preparedStatement.setString(5, customer.getRegistration_id());
			int update = preparedStatement.executeUpdate();
			return update > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int getActiveCustomers() {
		String sql = "select count(registration_id) from bank_app.customer where is_Active=true";
		int count = 0;
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				count = resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
}
