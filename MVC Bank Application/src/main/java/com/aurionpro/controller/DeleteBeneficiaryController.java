package com.aurionpro.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.service.BeneficiaryService;

/**
 * Servlet implementation class DeleteBeneficiaryController
 */
@WebServlet("/DeleteBeneficiaryController")
public class DeleteBeneficiaryController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		BeneficiaryService beneficiaryService = new BeneficiaryService();
		if (beneficiaryService.deleteBeneficiary(id)) {
			session.setAttribute("successMessage", "Beneficiary Deleted Successfully");
		} else {
			session.setAttribute("failureMessage", "Something went wrong");
		}
		response.sendRedirect("ViewBeneficiary.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
