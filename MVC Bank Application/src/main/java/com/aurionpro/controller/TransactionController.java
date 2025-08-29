package com.aurionpro.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.dao.BeneficiaryDao;
import com.aurionpro.model.Beneficiary;
import com.aurionpro.service.TransactionService;

@WebServlet("/TransactionController")
public class TransactionController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		double amount = Double.parseDouble(request.getParameter("amount"));
		System.out.println(amount);
		String beneficiaryAccount = request.getParameter("accountNo");
		BeneficiaryDao beneficiaryDao = new BeneficiaryDao();
		Beneficiary beneficiary = beneficiaryDao.getBeneficiaryByAccount(beneficiaryAccount);
		HttpSession session = request.getSession();
		TransactionService transactionService = new TransactionService();
		String AccountNo = (String) session.getAttribute("AccountNo");
//		Beneficiary beneficiary = (Beneficiary) request.getAttribute("beneficiaryPayment");
		System.out.println(beneficiary);
		if (!transactionService.checkBalance(amount, AccountNo)) {
			session.setAttribute("failureMessage", "Insufficient Balance");
			response.sendRedirect("customerWithAcc.jsp");
		}

		if (transactionService.makePayment(beneficiary, amount, AccountNo)) {
			session.setAttribute("successMessage", "Payment Done Successfully");
			response.sendRedirect("MakePayment.jsp");
		} else {
			session.setAttribute("failureMessage", "Something went wrong");
			response.sendRedirect("MakePayment.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
