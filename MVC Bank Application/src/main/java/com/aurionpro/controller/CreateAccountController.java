package com.aurionpro.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.model.Account;
import com.aurionpro.model.Login;
import com.aurionpro.service.AccountService;

@WebServlet("/CreateAccountController")
public class CreateAccountController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Login login = (Login) session.getAttribute("Login");
		String identifier = login.getUsername();
		String aadhar = request.getParameter("aadhar_no");
		String pan = request.getParameter("pan_no");
		String account_type = request.getParameter("account_type");
		double balance = Double.parseDouble(request.getParameter("balance"));
		AccountService accountService = new AccountService();
		Account account = new Account();
		account.setAddharno(aadhar);
		account.setPanNo(pan);
		account.setAccountType(account_type);
		account.setBalance(balance);
		if (accountService.checkDetails(account)) {
			account = accountService.createAccount(identifier, account);
			if (account != null) {
				session.setAttribute("successMessage",
						"Account Created Successfully. You will have to wait till the admin approves the account in order to use our extended banking services");
				response.sendRedirect("customerWoAcc.jsp");

			} else {
				session.setAttribute("failureMessage", "Something went wrong");
				response.sendRedirect("createAccount.jsp");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
