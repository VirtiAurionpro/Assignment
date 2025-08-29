package com.aurionpro.service;

import java.util.List;

import com.aurionpro.dao.CreditDao;
import com.aurionpro.model.Credit;

public class CreditService {

	private CreditDao creditDao = null;

	public CreditService() {
		if (creditDao == null)
			creditDao = new CreditDao();
	}

	public boolean saveDocuments(Credit credit) {
		return creditDao.saveDocuments(credit);
	}

	public List<Credit> getRequest() {
		return creditDao.getPendingRequests();
	}

	public Credit getCreditById(String id) {
		return creditDao.getCreditById(id);
	}

	public boolean approveCreditCard(String id) {
		return creditDao.approveCreditCard(id);
	}

}
