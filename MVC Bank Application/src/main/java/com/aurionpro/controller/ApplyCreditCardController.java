package com.aurionpro.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.aurionpro.dao.CustomerDao;
import com.aurionpro.model.Credit;
import com.aurionpro.model.Login;
import com.aurionpro.model.Registration;
import com.aurionpro.service.CreditService;
import com.aurionpro.service.CustomerService;

@WebServlet("/ApplyCreditCardController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 15 // 15 MB
)
public class ApplyCreditCardController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Login login2 = (Login) session.getAttribute("Login");
		String username = login2.getUsername();

		Registration customer = new CustomerDao().getCustomerByIdentifier(username);
		Part kycPart = request.getPart("kycDocument");
		Part addressPart = request.getPart("addressProof");
		Part incomePart = request.getPart("incomeProof");
		Part panPart = request.getPart("panOrForm60");
		Part photoPart = request.getPart("photograph");

		Credit credit = new Credit();
		credit.setCustomer(customer);
		credit.setKycDocument(kycPart.getInputStream().readAllBytes());
		credit.setAddressProof(addressPart.getInputStream().readAllBytes());
		credit.setIncomeProof(incomePart.getInputStream().readAllBytes());
		credit.setPanOrForm60(panPart.getInputStream().readAllBytes());
		credit.setPhotograph(photoPart.getInputStream().readAllBytes());
		credit.setApplication_date(new Date());
		credit.setIs_Approved(false);
		CreditService creditService = new CreditService();
		boolean success = creditService.saveDocuments(credit);

		if (success) {
			request.getSession().setAttribute("successMessage", "Documents uploaded successfully!");
		} else {
			request.getSession().setAttribute("failureMessage", "Upload failed.");
		}

		CustomerService customerService = new CustomerService();
		if (customerService.checkAccountExist(username)) {
			response.sendRedirect("customerWithAcc.jsp");

		} else {
			response.sendRedirect("customerWoAcc.jsp");

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
