package com.aurionpro.service;

import com.aurionpro.dao.AccountDao;
import com.aurionpro.model.Account;

public class AccountService {

	private AccountDao accountDao = null;

	public AccountService() {
		if (accountDao == null) {
			accountDao = new AccountDao();
		}
	}

	public Account createAccount(String identifier, Account account) {
		return accountDao.createAccount(identifier, account);
	}

	public boolean checkDetails(Account account) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean approveAccount(String pendingaccountNo) {
		return accountDao.approveAccount(pendingaccountNo);
	}
}
