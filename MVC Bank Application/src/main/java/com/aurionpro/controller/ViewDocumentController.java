package com.aurionpro.controller;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.model.Credit;
import com.aurionpro.service.CreditService;

@WebServlet("/ViewDocumentController")
public class ViewDocumentController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		Credit credit = new CreditService().getCreditById(id);
		byte[] doc = switch (type) {
		case "kyc" -> credit.getKycDocument();
		case "address" -> credit.getAddressProof();
		case "income" -> credit.getIncomeProof();
		case "pan" -> credit.getPanOrForm60();
		case "photo" -> credit.getPhotograph();
		default -> null;
		};
		System.out.println("controller:" + doc);
		response.setContentType("application/pdf");
		response.setContentLength(doc.length);
		response.getOutputStream().write(doc);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
