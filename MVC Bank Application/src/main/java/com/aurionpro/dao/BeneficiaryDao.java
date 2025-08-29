package com.aurionpro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.aurionpro.model.Beneficiary;
import com.aurionpro.util.DatabaseConnection;

public class BeneficiaryDao {
	private Connection connection = null;
	private DatabaseConnection dbConnection = null;
	PreparedStatement preparedStatement;
	ResultSet resultSet;

	public BeneficiaryDao() {
		if (dbConnection == null) {
			dbConnection = DatabaseConnection.getInstance();
			connection = dbConnection.getConnection();
		}
	}

	public List<Beneficiary> getAllBeneficiaries(HttpSession session) {
		List<Beneficiary> beneficiaries = new ArrayList<>();
		String sql = "select * from bank_app.beneficiary where is_Active=true and source_acc_no=?";
		try {
			String AccountNo=(String)session.getAttribute("AccountNo");
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, AccountNo);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Beneficiary beneficiary = new Beneficiary();
				beneficiary.setBeneficiaryId(resultSet.getString(1));
				beneficiary.setBeneficiaryAccount_no(resultSet.getString(2));
				beneficiary.setBeneficiaryName(resultSet.getString(3));
				beneficiary.setBeneficiaryBank_name(resultSet.getString(4));
				beneficiary.setBeneficiaryIfsc_code(resultSet.getString(5));
				beneficiary.setBeneficiaryMobile_no(resultSet.getString(6));
				beneficiary.setBeneficiaryNickname(resultSet.getString(7));
				beneficiary.setBeneficiaryEmail(resultSet.getString(8));
				beneficiaries.add(beneficiary);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return beneficiaries;

	}

	public boolean addBeneficiary(HttpServletRequest request, Beneficiary beneficiary) {
		HttpSession session = request.getSession();
		String newbeneficiaryId = "BE0001";
		String sql = "select beneficiary_id from bank_app.beneficiary order by beneficiary_id desc limit 1";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String lastId = resultSet.getString(1);
				int num = Integer.parseInt(lastId.substring(2));
				num++;
				newbeneficiaryId = String.format("BE%04d", num);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		beneficiary.setBeneficiaryId(newbeneficiaryId);
		String insertBeneficiary = "insert into bank_app.beneficiary values(?,?,?,?,?,?,?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(insertBeneficiary);
			preparedStatement.setString(1, newbeneficiaryId);
			preparedStatement.setString(2, beneficiary.getBeneficiaryAccount_no());
			preparedStatement.setString(3, beneficiary.getBeneficiaryName());
			preparedStatement.setString(4, beneficiary.getBeneficiaryBank_name());
			preparedStatement.setString(5, beneficiary.getBeneficiaryIfsc_code());
			preparedStatement.setString(6, beneficiary.getBeneficiaryMobile_no());
			preparedStatement.setString(7, beneficiary.getBeneficiaryNickname());
			preparedStatement.setString(8, beneficiary.getBeneficiaryEmail());
			preparedStatement.setBoolean(9, true);
			preparedStatement.setString(10, beneficiary.getSourceAccountNo());
			int update = preparedStatement.executeUpdate();
			return update > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Beneficiary getBeneficiaryByID(String id) {
		Beneficiary beneficiary = new Beneficiary();
		String sql = "select * from bank_app.beneficiary where beneficiary_id=? and is_Active=true";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				beneficiary = new Beneficiary();
				beneficiary.setBeneficiaryId(resultSet.getString(1));
				beneficiary.setBeneficiaryAccount_no(resultSet.getString(2));
				beneficiary.setBeneficiaryName(resultSet.getString(3));
				beneficiary.setBeneficiaryBank_name(resultSet.getString(4));
				beneficiary.setBeneficiaryIfsc_code(resultSet.getString(5));
				beneficiary.setBeneficiaryMobile_no(resultSet.getString(6));
				beneficiary.setBeneficiaryNickname(resultSet.getString(7));
				beneficiary.setBeneficiaryEmail(resultSet.getString(8));
				beneficiary.setSourceAccountNo(resultSet.getString(10));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return beneficiary;
	}

	public boolean updateBeneficiary(String id, HttpServletRequest request, Beneficiary beneficiary) {
		String sql = "update bank_app.beneficiary set beneficiary_account_no = ?, beneficiary_name = ?, beneficiary_bank_name = ?, beneficiary_ifsc_code = ?, beneficiary_mobile_no = ?, beneficiary_nickname = ?, beneficiary_email = ?, is_active = ? where beneficiary_id = ?;";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, beneficiary.getBeneficiaryAccount_no());
			preparedStatement.setString(2, beneficiary.getBeneficiaryName());
			preparedStatement.setString(3, beneficiary.getBeneficiaryBank_name());
			preparedStatement.setString(4, beneficiary.getBeneficiaryIfsc_code());
			preparedStatement.setString(5, beneficiary.getBeneficiaryMobile_no());
			preparedStatement.setString(6, beneficiary.getBeneficiaryNickname());
			preparedStatement.setString(7, beneficiary.getBeneficiaryEmail());
			preparedStatement.setBoolean(8, true);
			preparedStatement.setString(9, id);
			int update = preparedStatement.executeUpdate();
			return update > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteBeneficiary(String id) {
		String updateQuery = " update bank_app.beneficiary set is_Active=false where beneficiary_id=?";
		try {
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setString(1, id);
			int update = preparedStatement.executeUpdate();
			return update > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Beneficiary getBeneficiaryByAccount(String beneficiaryAccount) {
		Beneficiary beneficiary = new Beneficiary();
		String sql = "select * from bank_app.beneficiary where beneficiary_account_no=? and is_Active=true";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, beneficiaryAccount);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				beneficiary = new Beneficiary();
				beneficiary.setBeneficiaryId(resultSet.getString(1));
				beneficiary.setBeneficiaryAccount_no(resultSet.getString(2));
				beneficiary.setBeneficiaryName(resultSet.getString(3));
				beneficiary.setBeneficiaryBank_name(resultSet.getString(4));
				beneficiary.setBeneficiaryIfsc_code(resultSet.getString(5));
				beneficiary.setBeneficiaryMobile_no(resultSet.getString(6));
				beneficiary.setBeneficiaryNickname(resultSet.getString(7));
				beneficiary.setBeneficiaryEmail(resultSet.getString(8));
				beneficiary.setSourceAccountNo(resultSet.getString(10));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return beneficiary;
	}

}
