package com.aurionpro.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.dao.BeneficiaryDao;
import com.aurionpro.model.Beneficiary;

/**
 * Servlet implementation class PaymentController
 */
@WebServlet("/PaymentController")
public class PaymentController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String beneficiaryAccount = request.getParameter("accountNo");
		BeneficiaryDao beneficiaryDao = new BeneficiaryDao();
		Beneficiary beneficiary = beneficiaryDao.getBeneficiaryByAccount(beneficiaryAccount);
		request.setAttribute("beneficiaryPayment", beneficiary);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("DoPayment.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
