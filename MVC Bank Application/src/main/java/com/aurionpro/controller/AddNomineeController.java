package com.aurionpro.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.model.Nominee;
import com.aurionpro.service.NomineeService;

@WebServlet("/AddNomineeController")
public class AddNomineeController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		NomineeService nomineeService = new NomineeService();
		String sourceAccountNo = (String) session.getAttribute("AccountNo");
		String nomineeName = request.getParameter("name");
		String relation = request.getParameter("relation");
		int age = Integer.parseInt(request.getParameter("age"));
		String identification = request.getParameter("identification");
		String mobile = request.getParameter("mobile");

		if (nomineeService.checkNomineeDetails(request, mobile, age)) {
			if (nomineeService.addnominee(sourceAccountNo, nomineeName, relation, age, identification, mobile)) {
				session.setAttribute("successMessage", "Nominee Added Successfully");
				response.sendRedirect("Profile.jsp");
			} else {
				session.setAttribute("failureMessage", "Something went wrong");
				response.sendRedirect("AddNominee.jsp");
			}
		} else {
			session.setAttribute("failureMessage", "Something went wrong");
			response.sendRedirect("AddNominee.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
