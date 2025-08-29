package com.aurionpro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aurionpro.model.Account;
import com.aurionpro.model.Registration;
import com.aurionpro.util.DatabaseConnection;

public class AccountDao {
	private Connection connection = null;
	private DatabaseConnection dbConnection = null;
	PreparedStatement preparedStatement;
	ResultSet resultSet;

	public AccountDao() {
		if (dbConnection == null) {
			dbConnection = DatabaseConnection.getInstance();
			connection = dbConnection.getConnection();
		}
	}

	public Account createAccount(String identifier, Account account) {
		String ifsc = "ABC0000345";
		String newAccountNo = "ACCABC0001";
		String newCustomerId = "NCU0001";
		String sql = "select account_no,customer_id from bank_app.account order by account_no desc limit 1";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String lastAccountNo = resultSet.getString("account_no");
				String lastCustomerId = resultSet.getString("customer_id");
				int numA = Integer.parseInt(lastAccountNo.substring(6));
				int numC = Integer.parseInt(lastCustomerId.substring(3));
				numA++;
				numC++;
				newAccountNo = String.format("ACCABC%04d", numA);
				newCustomerId = String.format("NCU%04d", numC);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Registration customer = new CustomerDao().getCustomerByIdentifier(identifier);
		Date date = new Date();
		account.setDob(customer.getDob());
		account.setName(customer.getName());
		account.setEmail(customer.getEmail());
		account.setMobileNo(customer.getMobileNo());
		account.setOpening_date(date);
		account.setIfscCode(ifsc);
		account.setAccountNo(newAccountNo);
		account.setCustomerID(newCustomerId);

		String insertAccount = "insert into account values (?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(insertAccount);
			preparedStatement.setString(1, newAccountNo);
			preparedStatement.setString(2, newCustomerId);
			preparedStatement.setString(3, account.getName());
			preparedStatement.setDate(4, new java.sql.Date(account.getDob().getTime()));
			preparedStatement.setString(5, account.getEmail());
			preparedStatement.setString(6, account.getMobileNo());
			preparedStatement.setString(7, account.getMobileNo());
			preparedStatement.setString(8, account.getPanNo());
			preparedStatement.setDate(9, new java.sql.Date(account.getOpening_date().getTime()));
			preparedStatement.setString(10, account.getAccountType());
			preparedStatement.setDouble(11, account.getBalance());
			preparedStatement.setBoolean(12, false);
			int update = preparedStatement.executeUpdate();
			if (update > 0)
				return account;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getAccountNumber(String identifier) {
		String sql = "select a.account_no from bank_app.account a join bank_app.login l on a.email=l.login_email where (l.login_id=? or l.login_email=?) and a.is_approved=true";
		String accountNo = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, identifier);
			preparedStatement.setString(2, identifier);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				accountNo = resultSet.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountNo;
	}

	public int getNotApprovedAccounts() {
		String sql = "select count(account_no) from bank_app.account where is_approved=false;";
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

	public int getApprovedAccounts() {
		String sql = "select count(account_no) from bank_app.account where is_approved=true;";
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

	public Map<String, Account> getAlltransactionAccounts() {
		Map<String, Account> accounts = new HashMap<>();
		String sql = "select account_no,name,opening_date,balance from bank_app.account where is_approved=true";

		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Account acc = new Account();
				acc.setName(resultSet.getString(2));
				acc.setOpening_date(resultSet.getDate(3));
				acc.setBalance(resultSet.getDouble(4));
				accounts.putIfAbsent(resultSet.getString(1), acc);

//				Map<String, List<Object>> accounts = new HashMap<>();
//
//				List<Object> accounts = new ArrayList<>();
//				accounts.add(resultSet.getString(2)); 
//				accounts.add(resultSet.getDate(3));
//				accounts.add(resultSet.getDouble(4));
//
//				accounts.put(resultSet.getString(1), accounts);
			}
		} catch (

		SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}

	public List<Account> getPendingAccount() {
		List<Account> pendingAccounts = new ArrayList<>();
		String sql = "select * from bank_app.account where is_approved=false";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Account account = new Account();
				account.setAccountNo(resultSet.getString(1));
				account.setName(resultSet.getString(3));
				account.setEmail(resultSet.getString(5));
				account.setOpening_date(resultSet.getDate(9));
				account.setAccountType(resultSet.getString(10));
				pendingAccounts.add(account);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pendingAccounts;
	}

	public List<Account> getAllAccount() {
		List<Account> allAccounts = new ArrayList<>();
		String sql = "select * from bank_app.account";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Account account = new Account();
				account.setAccountNo(resultSet.getString(1));
				account.setName(resultSet.getString(3));
				account.setEmail(resultSet.getString(5));
				account.setOpening_date(resultSet.getDate(9));
				account.setAccountType(resultSet.getString(10));
				account.setApproved(resultSet.getBoolean(12));
				allAccounts.add(account);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allAccounts;
	}

	public boolean approveAccount(String pendingaccountNo) {
		String emailquery = "select email from bank_app.account where account_no=? and is_approved=false";
		String email = null;
		String sql = "update bank_app.account set is_approved=true where account_no=?";
		try {
			preparedStatement = connection.prepareStatement(emailquery);
			preparedStatement.setString(1, pendingaccountNo);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				email = resultSet.getString(1);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, pendingaccountNo);
			int update = preparedStatement.executeUpdate();
			System.out.println("update dao" + update);

			System.out.print("account dao email" + email);
			String updateCustomer = "update bank_app.customer set has_Account=true where customer_email=? and has_Account=false limit 1";
			preparedStatement = connection.prepareStatement(updateCustomer);
			preparedStatement.setString(1, email);
			int update2 = preparedStatement.executeUpdate();
			System.out.println("update 2 dao" + update);

			return (update > 0) && (update2 > 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public List<Account> getAllAccountsbyUsername(String username) {
		String email = null;
		List<Account> accounts = new ArrayList<>();
		String sql = "select customer_email from bank_app.customer where registration_id=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				email = resultSet.getString(1);
			String account = "select * from bank_app.account where email=? and is_approved=true";
			preparedStatement = connection.prepareStatement(account);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Account accountObj = new Account();
				accountObj.setAccountNo(resultSet.getString(1));
				accountObj.setCustomerID(resultSet.getString(2));
				accountObj.setOpening_date(resultSet.getDate(9));
				accountObj.setAccountType(resultSet.getString(10));
				accountObj.setBalance(resultSet.getDouble(11));

				accounts.add(accountObj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}

	public String getAccountType(String AccountNo) {
		String sql = "select account_type from bank_app.account where account_no=? and is_approved=true";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, AccountNo);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				return resultSet.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
