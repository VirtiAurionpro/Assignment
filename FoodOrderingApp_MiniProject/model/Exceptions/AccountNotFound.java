package com.aurionpro.model.Exceptions;

public class AccountNotFound extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String accountID;
	public AccountNotFound() {
		super();
	}

	public AccountNotFound(String accountID) {
		super();
		this.accountID = accountID;
	}

	public String getaccountID() {
		return accountID;
	}

	public void setaccountID(String accountID) {
		this.accountID = accountID;
	}
	public String getMessage() {
		return "\nAccount with account ID '" + accountID + "'. does not exist. Please register to continue.";
	}
}
