package com.aurionpro.exceptionss;

public class OverdraftLimitReachedException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Overdraft Limit Reached.Cannot withdraw money";
	}

	public OverdraftLimitReachedException() {
		super();
	}
}
