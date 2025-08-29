package com.aurionpro.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.model.Registration;
import com.aurionpro.service.CustomerService;

/**
 * Servlet implementation class UpdateProfileController
 */
@WebServlet("/UpdateProfileController")
public class UpdateProfileController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobileNo");
		LocalDate dob = LocalDate.parse(request.getParameter("dob"));
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(dob.atStartOfDay(defaultZoneId).toInstant());
		CustomerService customerService = new CustomerService();
		Registration customer = new Registration(id, name, email, mobile, date);
		if (customerService.updateCustomerDetails(customer)) {
			session.setAttribute("successMessage", "Details Updated Successfully");
			response.sendRedirect("Profile.jsp");

		} else {
			session.setAttribute("failureMessage", "Something went wrong");
			response.sendRedirect("Profile.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
