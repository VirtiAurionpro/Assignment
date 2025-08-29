package com.aurionpro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.aurionpro.model.Credit;
import com.aurionpro.model.Registration;
import com.aurionpro.util.DatabaseConnection;

public class CreditDao {

	private Connection connection = null;
	private DatabaseConnection dbConnection = null;
	PreparedStatement preparedStatement;
	ResultSet resultSet;

	public CreditDao() {
		if (dbConnection == null) {
			dbConnection = DatabaseConnection.getInstance();
			connection = dbConnection.getConnection();
		}
	}

	public boolean saveDocuments(Credit credit) {

		String newapplicationId = "APCC0001";
		String sql = "select application_id from bank_app.credit_card order by application_id desc limit 1";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String lastId = resultSet.getString(1);
				int num = Integer.parseInt(lastId.substring(4));
				num++;
				newapplicationId = String.format("APCC%04d", num);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String name = credit.getCustomer().getName();
		String email = credit.getCustomer().getEmail();
		int age = credit.getCustomer().getAge();
		byte[] address = credit.getAddressProof();
		byte[] income = credit.getIncomeProof();
		byte[] kyc = credit.getKycDocument();
		byte[] panorform = credit.getPanOrForm60();
		byte[] photograph = credit.getPhotograph();
		java.sql.Date date = new java.sql.Date(credit.getApplication_date().getTime());
		boolean is_Approved = credit.isIs_Approved();
		String insertquery = "insert into bank_app.credit_card values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(insertquery);
			preparedStatement.setString(1, newapplicationId);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, email);
			preparedStatement.setInt(4, age);
			preparedStatement.setBytes(5, kyc);
			preparedStatement.setBytes(6, address);
			preparedStatement.setBytes(7, income);
			preparedStatement.setBytes(8, panorform);
			preparedStatement.setBytes(9, photograph);
			preparedStatement.setDate(10, date);
			preparedStatement.setBoolean(11, is_Approved);

			int update = preparedStatement.executeUpdate();
			return update > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public List<Credit> getPendingRequests() {
		List<Credit> requests = new ArrayList<>();
		String sql = "select * from bank_app.credit_card where is_Approved=false";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Credit credit = new Credit();
				Registration customer = new Registration();
				credit.setApplication_id(resultSet.getString(1));
				customer.setName(resultSet.getString(2));
				customer.setEmail(resultSet.getString(3));
				customer.setAge(resultSet.getInt(4));
				credit.setKycDocument(resultSet.getBytes(5));
//				Blob kyc = resultSet.getBlob(5);
//				credit.setKycDocument(kyc.getBytes(1l, (int) kyc.length()));
				credit.setAddressProof(resultSet.getBytes(6));
				credit.setIncomeProof(resultSet.getBytes(7));
				credit.setPanOrForm60(resultSet.getBytes(8));
				credit.setPhotograph(resultSet.getBytes(9));
				credit.setApplication_date(resultSet.getDate(10));
				credit.setIs_Approved(resultSet.getBoolean(11));
				credit.setCustomer(customer);
				requests.add(credit);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return requests;
	}

	public Credit getCreditById(String id) {
		String sql = "select * from bank_app.credit_card where application_id=?";
		try {
			Credit credit = new Credit();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Registration customer = new Registration();
				customer.setName(resultSet.getString(2));
				customer.setEmail(resultSet.getString(3));
				credit.setCustomer(customer);
				credit.setKycDocument(resultSet.getBytes(5));
				credit.setAddressProof(resultSet.getBytes(6));
				credit.setIncomeProof(resultSet.getBytes(7));
				credit.setPanOrForm60(resultSet.getBytes(8));
				credit.setPhotograph(resultSet.getBytes(9));
			}
			return credit;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean approveCreditCard(String id) {
		String sql = "update bank_app.credit_card set is_Approved=true where application_id=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			int update = preparedStatement.executeUpdate();

			String insert = "insert into bank_app.approved_credit_cards values(?,?,?,?,?)";
			Credit credit = getCreditById(id);
			String creditCardNo = generateCreditCardNo();
			String expiryDate = getExpiryDate();
			preparedStatement = connection.prepareStatement(insert);
			preparedStatement.setString(1, creditCardNo);
			preparedStatement.setString(2, id);
			preparedStatement.setString(3, expiryDate);
			preparedStatement.setString(4, credit.getCustomer().getName());
			preparedStatement.setString(5, credit.getCustomer().getEmail());
			int update2 = preparedStatement.executeUpdate();
			return update > 0 && update2 > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private String getExpiryDate() {
		LocalDate today = LocalDate.now();
		LocalDate futureDate = today.plusYears(5);
		int year = futureDate.getYear();
		int month = futureDate.getMonthValue();
		return String.format("%02d/%02d", month, year % 100);
	}

	private String generateCreditCardNo() {
		Random random = new Random();
		String number;

		do {
			int firstDigit = random.nextInt(9) + 1;
			StringBuilder builder = new StringBuilder();
			builder.append(firstDigit);
			for (int i = 0; i < 15; i++) {
				builder.append(random.nextInt(10));
			}
			number = builder.toString();
		} while (cardNumberExists(number));

		return number;
	}

	private boolean cardNumberExists(String cardNumber) {
		String sql = "select credit_card_no from bank_app.approved_credit_cards where credit_card_no = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, cardNumber);
			resultSet = preparedStatement.executeQuery();
			return resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Credit> getAllCards(String username) {
		String email = null;
		List<Credit> cards = new ArrayList<>();
		String sql = "select customer_email from bank_app.customer where registration_id=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				email = resultSet.getString(1);
			String card = "select * from bank_app.approved_credit_cards where card_holder_email=?";
			preparedStatement = connection.prepareStatement(card);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Credit credit = new Credit();
				Registration customer = new Registration();
				credit.setCreditCardNo(resultSet.getString(1));
				credit.setApplication_id(resultSet.getString(2));
				credit.setCardExpiry(resultSet.getString(3));
				customer.setName(resultSet.getString(4));
				customer.setEmail(resultSet.getString(5));
				credit.setCustomer(customer);
				cards.add(credit);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cards;
	}

	public int getNotApprovedRequests() {
		String sql = "select count(application_id) from bank_app.credit_card where is_Approved=false;";
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
