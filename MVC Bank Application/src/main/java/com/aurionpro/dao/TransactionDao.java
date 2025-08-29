package com.aurionpro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aurionpro.model.Transaction;
import com.aurionpro.util.DatabaseConnection;

public class TransactionDao {
	private Connection connection = null;
	private DatabaseConnection dbConnection = null;
	PreparedStatement preparedStatement;
	ResultSet resultSet;

	public TransactionDao() {
		if (dbConnection == null) {
			dbConnection = DatabaseConnection.getInstance();
			connection = dbConnection.getConnection();
		}
	}

	public double getBalance(String accountNo) {
		double balance = 0;
		String sql = "select balance from bank_app.account where account_no=? and is_approved=true";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, accountNo);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				balance = resultSet.getDouble(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return balance;
	}

	public String transactionID() {
		String newTransactionId = "TS0001";
		String lastId = "select transaction_id from bank_app.transaction order by transaction_id desc limit 1";
		try {
			preparedStatement = connection.prepareStatement(lastId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String lastTransactionId = resultSet.getString("transaction_id");
				int num = Integer.parseInt(lastTransactionId.substring(2));
				num++;
				newTransactionId = String.format("TS%04d", num);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newTransactionId;
	}

	public boolean makePayment(String beneficiaryAccount, String accountNo, double amount) {
		Date date = new Date();
		double balance = getBalance(accountNo);
		String debitTransactionId = transactionID();
		int update1 = 0, update2 = 0, update3 = 0, update4 = 0;
		String transaction = "insert into bank_app.transaction values(?,?,?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(transaction);
			preparedStatement.setString(1, debitTransactionId);
			preparedStatement.setString(2, accountNo);
			preparedStatement.setString(3, beneficiaryAccount);
			preparedStatement.setDouble(4, amount);
			preparedStatement.setString(5, "debit");
			preparedStatement.setDate(6, new java.sql.Date(date.getTime()));
			update1 = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String updateAcc = "update bank_app.account set balance=? where account_no=? and is_approved=true";
		try {
			preparedStatement = connection.prepareStatement(updateAcc);
			preparedStatement.setDouble(1, balance - amount);
			preparedStatement.setString(2, accountNo);
			update2 = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (beneficiaryAccountExist(beneficiaryAccount)) {
			double balanceBeneficiary = getBalance(beneficiaryAccount);
			String creditTransactionId = transactionID();

			String updateBeneficiaryAcc = "update bank_app.account set balance=? where account_no=? and is_approved=true";
			try {
				preparedStatement = connection.prepareStatement(updateBeneficiaryAcc);
				preparedStatement.setDouble(1, balanceBeneficiary + amount);
				preparedStatement.setString(2, beneficiaryAccount);
				update3 = preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			String transactionBeneficiary = "insert into bank_app.transaction values(?,?,?,?,?,?)";
			try {
				preparedStatement = connection.prepareStatement(transactionBeneficiary);
				preparedStatement.setString(1, creditTransactionId);
				preparedStatement.setString(2, beneficiaryAccount);
				preparedStatement.setString(3, accountNo);
				preparedStatement.setDouble(4, amount);
				preparedStatement.setString(5, "credit");
				preparedStatement.setDate(6, new java.sql.Date(date.getTime()));
				update4 = preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return (update1 > 0 && update2 > 0);
	}

	private boolean beneficiaryAccountExist(String beneficiaryAccount) {
		String sql = "select a.account_no from bank_app.account a join bank_app.beneficiary b on b.beneficiary_account_no=a.account_no where b.beneficiary_account_no=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, beneficiaryAccount);
			resultSet = preparedStatement.executeQuery();
			return resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Transaction> getAlltransactions(String AccountNo) {
		List<Transaction> transactions = new ArrayList<>();
		String sql = "select * from bank_app.transaction where source_acc=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, AccountNo);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Transaction transaction = new Transaction();
				transaction.setTransaction_id(resultSet.getString(1));
				transaction.setSourceAcc(resultSet.getString(2));
				transaction.setDesticationAcc(resultSet.getString(3));
				transaction.setAmount(resultSet.getDouble(4));
				transaction.setTransactionType(resultSet.getString(5));
				transaction.setTransactionDate(resultSet.getDate(6));
				transactions.add(transaction);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transactions;
	}

	public List<Transaction> getTimetransactions(String AccountNo, String from, String to) {
		List<Transaction> transactions = new ArrayList<>();
		String sql = "select * from bank_app.transaction where source_acc=? ";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		ZoneId defaultZoneId = ZoneId.systemDefault();
		java.sql.Date dateFrom = null;
		java.sql.Date dateTo = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, AccountNo);

			if (from != null && !from.isEmpty()) {
				LocalDate fromDate = LocalDate.parse(from, formatter);
				dateFrom = java.sql.Date.valueOf(fromDate);
				sql = sql + "where transaction_date>=?";
				preparedStatement.setDate(2, dateFrom);
			}

			if (to != null && !to.isEmpty()) {
				LocalDate toDate = LocalDate.parse(to, formatter);
				dateTo = java.sql.Date.valueOf(toDate);
				sql = sql + "where transaction_date<=?";
				preparedStatement.setDate(3, dateTo);
			}

			resultSet = preparedStatement.executeQuery();

			System.out.println("from" + dateFrom);
			System.out.println("to" + dateTo);
			while (resultSet.next()) {
				Transaction transaction = new Transaction();
				transaction.setTransaction_id(resultSet.getString(1));
				transaction.setSourceAcc(resultSet.getString(2));
				transaction.setDesticationAcc(resultSet.getString(3));
				transaction.setAmount(resultSet.getDouble(4));
				transaction.setTransactionType(resultSet.getString(5));
				transaction.setTransactionDate(resultSet.getDate(6));
				transactions.add(transaction);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transactions;
	}

	public int numberOfTransactions(String AccountNo) {
		String sql = "select count(transaction_id) from bank_app.transaction where source_acc=?";
		int count = 0;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, AccountNo);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				count = resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public Date getlastDate(String AccountNo) {
		String sql = "select transaction_date from bank_app.transaction where source_acc=? order by transaction_date desc limit 1";
		Date date = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, AccountNo);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				date = resultSet.getDate(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return date;
	}
}
