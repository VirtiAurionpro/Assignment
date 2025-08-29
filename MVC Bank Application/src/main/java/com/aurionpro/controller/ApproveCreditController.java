package com.aurionpro.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.model.Credit;
import com.aurionpro.service.CreditService;

@WebServlet("/ApproveCreditController")
public class ApproveCreditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CreditService creditService = new CreditService();
		String id = request.getParameter("applicationNo");
		HttpSession session = request.getSession();

		if (creditService.approveCreditCard(id)) {
			session.setAttribute("successMessage", "Approved Credit Card request");
			response.sendRedirect("ApproveCCReq.jsp");
		} else

		{
			session.setAttribute("failureMessage", "Something went wrong");
			response.sendRedirect("ApproveCCReq.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
