package com.aurionpro.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.aurionpro.dao.BeneficiaryDao;
import com.aurionpro.model.Beneficiary;

public class BeneficiaryService {
	private BeneficiaryDao beneficiaryDao = null;

	public BeneficiaryService() {
		if (beneficiaryDao == null)
			beneficiaryDao = new BeneficiaryDao();
	}

	public Beneficiary checkBeneficiaryDetails(HttpServletRequest request, Beneficiary beneficiary,
			String beneficiaryName, String accountNo, String confirmAccountNo, String bankName, String ifsc,
			String mobile, String email, String nickName) {
		HttpSession session = request.getSession();
		String sourceAccountNo = (String) session.getAttribute("AccountNo");
		System.out.println(accountNo.equals(confirmAccountNo));
		if (!accountNo.equals(confirmAccountNo)) {
			session.setAttribute("failureMessage", "Account Number should be same");
			return null;
		}
		beneficiary.setBeneficiaryName(beneficiaryName);
		beneficiary.setBeneficiaryAccount_no(accountNo);
		beneficiary.setBeneficiaryBank_name(bankName);
		beneficiary.setBeneficiaryIfsc_code(ifsc);
		beneficiary.setBeneficiaryMobile_no(mobile);
		beneficiary.setBeneficiaryEmail(email);
		beneficiary.setBeneficiaryNickname(nickName);
		beneficiary.setSourceAccountNo(sourceAccountNo);
		System.out.println(sourceAccountNo);
		return beneficiary;
	}

	public boolean addBeneficiary(HttpServletRequest request, Beneficiary beneficiary) {
		return beneficiaryDao.addBeneficiary(request,beneficiary);
	}

	public boolean updateBeneficiary(String id, HttpServletRequest request, Beneficiary beneficiary) {
		return beneficiaryDao.updateBeneficiary(id,request,beneficiary);

	}

	public boolean deleteBeneficiary(String id) {
		return beneficiaryDao.deleteBeneficiary(id);
	}

}
