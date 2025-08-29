package com.aurionpro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.model.Nominee;
import com.aurionpro.util.DatabaseConnection;

public class NomineeDao {

	private Connection connection = null;
	private DatabaseConnection dbConnection = null;
	PreparedStatement preparedStatement;
	ResultSet resultSet;

	public NomineeDao() {
		if (dbConnection == null) {
			dbConnection = DatabaseConnection.getInstance();
			connection = dbConnection.getConnection();
		}
	}

	public boolean addnominee(String sourceAccountNo, String nomineeName, String relation, int age,
			String identification, String mobile) {
		String sql = "insert into bank_app.nominee values(?,?,?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, sourceAccountNo);
			preparedStatement.setString(2, nomineeName);
			preparedStatement.setString(3, relation);
			preparedStatement.setInt(4, age);
			preparedStatement.setString(5, identification);
			preparedStatement.setString(6, mobile);
			int update = preparedStatement.executeUpdate();
			return update > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public List<Nominee> getAllNominees(String accountNo) {
		List<Nominee> nominees = new ArrayList<>();
		String sql = "select * from bank_app.nominee where account_no=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, accountNo);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Nominee nominee = new Nominee();
				nominee.setAccount_no(resultSet.getString(1));
				nominee.setNominee_name(resultSet.getString(2));
				nominee.setRelation(resultSet.getString(3));
				nominee.setNominee_age(resultSet.getInt(4));
				nominee.setNominee_identification_no(resultSet.getString(5));
				nominee.setNominee_mobile_no(resultSet.getString(6));
				nominees.add(nominee);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nominees;
	}
}
