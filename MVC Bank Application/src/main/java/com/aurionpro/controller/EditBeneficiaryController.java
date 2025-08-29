package com.aurionpro.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.dao.BeneficiaryDao;
import com.aurionpro.model.Beneficiary;
import com.aurionpro.service.BeneficiaryService;

@WebServlet("/EditBeneficiaryController")
public class EditBeneficiaryController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		HttpSession session = request.getSession();

		BeneficiaryService beneficiaryService = new BeneficiaryService();
		String beneficiaryName = request.getParameter("name");
		String accountNo = request.getParameter("accountNo");
		String confirmAccountNo = request.getParameter("confirmaccountNo");
		String bankName = request.getParameter("bankname");
		String ifsc = request.getParameter("ifsc");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		String nickName = request.getParameter("nickname");
		Beneficiary beneficiaryUpdated = new Beneficiary();

		beneficiaryUpdated = beneficiaryService.checkBeneficiaryDetails(request, beneficiaryUpdated, beneficiaryName,
				accountNo, confirmAccountNo, bankName, ifsc, mobile, email, nickName);
		beneficiaryUpdated.setBeneficiaryId(id);
		System.out.println(beneficiaryUpdated);
		if (beneficiaryUpdated != null && beneficiaryService.updateBeneficiary(id, request, beneficiaryUpdated)) {
			session.setAttribute("successMessage", "Beneficiary Updated Successfully");
			response.sendRedirect("ViewBeneficiary.jsp");
		} else {
			session.setAttribute("failureMessage", "Something went wrong");
			response.sendRedirect("ViewBeneficiary.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
