package com.aurionpro.controller;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.service.AccountService;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@WebServlet("/ApproveAccountController")
public class ApproveAccountController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pendingaccountNo = request.getParameter("pendingaccountNo");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		System.out.println("pendingaccountNo controller" + pendingaccountNo);
		System.out.println("email controller" + email);
		AccountService accountService = new AccountService();
		HttpSession session = request.getSession();
		if (accountService.approveAccount(pendingaccountNo)) {

			String recipient = email;
			String subject = "Account Approved in ABC Bank";
			String messageContent = "Dear " + name + ",\n\nYour account No" + pendingaccountNo
					+ "has been approved by our bank.\nPlease log in again in order to use our extended services."
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
			Session session1 = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(senderEmail, senderPassword);
				}
			});

			try {
				Message message = new MimeMessage(session1);
				message.setFrom(new InternetAddress(senderEmail));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
				message.setSubject(subject);
				message.setText(messageContent);

				Transport.send(message);
				System.out.println("Email sent successfully to: " + recipient);
//				response.getWriter().println("Email sent successfully!");
			} catch (MessagingException e) {
				e.printStackTrace();
				System.out.println("Error sending email: " + e.getMessage());
				response.getWriter().println("Error sending email: " + e.getMessage());
			}

			session.setAttribute("successMessage", "Beneficiary Added Successfully");
			response.sendRedirect("ApproveAccount.jsp");
		} else {
			session.setAttribute("failureMessage", "Something went wrong");
			response.sendRedirect("ApproveAccount.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
