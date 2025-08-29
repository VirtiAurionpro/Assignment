
package com.aurionpro.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.model.Login;
import com.aurionpro.service.CustomerService;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		CustomerService customerService = new CustomerService();
		Login login = new Login();
		if (role.equalsIgnoreCase("admin")) {
			System.out.println("inside admin");
			if (customerService.checkAdminCredentials(request, username, password)) {
				System.out.println("inside admin creds");
				login.setUsername(username);
				login.setPassword(password);
				login.setRole(role);
				session.setAttribute("Login", login);
				response.sendRedirect("AdminDashboard.jsp");
			}
		}
		if (role.equalsIgnoreCase("customer")) {
			System.out.println("insidecustomer");
			if (customerService.checkCredentials(request, username, password)) {
				System.out.println("insidecustomer2");
				login.setUsername(username);
				login.setPassword(password);
				login.setRole(role);
				System.out.println(username);
				session.setAttribute("Login", login);
				System.out.println(customerService.checkAccountExist(username));
				if (customerService.checkAccountExist(username)) {
					System.out.println("loginset");
					response.sendRedirect("CustomerAccDashboard.jsp");
				} else {
					response.sendRedirect("CustomerWoAccDashboard.jsp");
				}
			} else {
				response.sendRedirect("login.jsp");
//				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
//				dispatcher.forward(request, response);
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
