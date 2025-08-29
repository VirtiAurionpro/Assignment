package com.aurionpro.service;

import com.aurionpro.dao.TransactionDao;
import com.aurionpro.model.Beneficiary;

public class TransactionService {

	private TransactionDao transactionDao;

	public TransactionService() {
		if (transactionDao == null)
			transactionDao = new TransactionDao();
	}

	public boolean checkBalance(double amount, String accountNo) {
		double balance = transactionDao.getBalance(accountNo);
		if (balance - amount < 0)
			return false;
		return true;
	}

	public boolean makePayment(Beneficiary beneficiary, double amount, String accountNo) {
		String beneficiaryAccount = beneficiary.getBeneficiaryAccount_no();
		return transactionDao.makePayment(beneficiaryAccount, accountNo, amount);
	}

}
