package com.aurionpro.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;

import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.service.CustomerService;

import jakarta.mail.Authenticator;
import jakarta.mail.MessagingException;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@WebServlet("/RegistrationController")
public class RegistrationController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		LocalDate dob = LocalDate.parse(request.getParameter("dob"));
//		java.sql.Date sqlDob = java.sql.Date.valueOf(dob);
		CustomerService customerService = new CustomerService();
		String password = request.getParameter("password");
		String confirmpassword = request.getParameter("confirmpassword");
		System.out.println("outside1");
		if (customerService.checkRegistrationDetails(request, name, email, mobile, dob, password, confirmpassword)) {
			System.out.println("inside1");
			String registrationId = customerService.registerUser(name, email, mobile, dob, password);
			if (registrationId != null) {

				String recipient = email;
				String subject = "Registration SUccessfull for ABC Bank";
				String messageContent = "Dear " + name
						+ ",\n\nThank you for registering with our bank.\nYour registration id=" + registrationId
						+ "Hope you enjoy our services.\nYour username for logging into our application is either your email or your registration id"
						+ "Regards,ABC Bank.";

				// Sender's email credentials
				final String senderEmail = "virtipers2@gmail.com";
				final String senderPassword = "lwehkqrxjqivwicp";

				// SMTP server configuration
				Properties properties = new Properties();
				properties.put("mail.smtp.auth", "true");
				properties.put("mail.smtp.starttls.enable", "true");
				properties.put("mail.smtp.host", "smtp.gmail.com");
				properties.put("mail.smtp.port", "587");

				// Create a session with the SMTP server
				Session session = Session.getInstance(properties, new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(senderEmail, senderPassword);
					}
				});

				try {
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(senderEmail));
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
					message.setSubject(subject);
					message.setText(messageContent);

					Transport.send(message);
					System.out.println("Email sent successfully to: " + recipient);
//					response.getWriter().println("Email sent successfully!");
				} catch (MessagingException e) {
					e.printStackTrace();
					System.out.println("Error sending email: " + e.getMessage());
					response.getWriter().println("Error sending email: " + e.getMessage());
				}

				httpSession.setAttribute("successMessage", "Registered Successfully");
				response.sendRedirect("login.jsp");

			} else {
				httpSession.setAttribute("failureMessage", "Something went wrong");
				response.sendRedirect("registration.jsp");
			}
		} else {
			response.sendRedirect("registration.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
