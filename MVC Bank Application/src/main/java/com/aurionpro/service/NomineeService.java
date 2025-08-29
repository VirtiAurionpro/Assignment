package com.aurionpro.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.aurionpro.dao.NomineeDao;

public class NomineeService {

	private NomineeDao nomineeDao = null;

	public NomineeService() {
		if (nomineeDao == null)
			nomineeDao = new NomineeDao();
	}

	public boolean checkNomineeDetails(HttpServletRequest request, String mobile, int age) {
		HttpSession session = request.getSession();

		if (mobile.length() != 10) {
			session.setAttribute("failureMessage", "Mobile Number should be exactly 10 digits");
			return false;
		}
		if (age < 18) {
			session.setAttribute("failureMessage", "Nominee cannot be less than 18");
			return false;
		}
		return true;
	}

	public boolean addnominee(String sourceAccountNo, String nomineeName, String relation, int age,
			String identification, String mobile) {
		return nomineeDao.addnominee(sourceAccountNo, nomineeName, relation, age, identification, mobile);
	}

}
