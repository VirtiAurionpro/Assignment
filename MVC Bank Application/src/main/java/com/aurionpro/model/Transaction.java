package com.aurionpro.model;

import java.util.Date;

public class Transaction {
	private String transaction_id;
	private String sourceAcc;
	private String desticationAcc;
	private double amount;
	private String transactionType;
	private Date transactionDate;

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getSourceAcc() {
		return sourceAcc;
	}

	public void setSourceAcc(String sourceAcc) {
		this.sourceAcc = sourceAcc;
	}

	public String getDesticationAcc() {
		return desticationAcc;
	}

	public void setDesticationAcc(String desticationAcc) {
		this.desticationAcc = desticationAcc;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amouont) {
		this.amount = amouont;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Transaction(String transaction_id, String sourceAcc, String desticationAcc, double amount,
			String transactionType, Date transactionDate) {
		super();
		this.transaction_id = transaction_id;
		this.sourceAcc = sourceAcc;
		this.desticationAcc = desticationAcc;
		this.amount = amount;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
	}

	public Transaction() {
		super();
	}
}
