package com.aurionpro.exceptionss;

public class NegativeInputException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Negative value cannot be entered";
	}

	public NegativeInputException() {
		super();
	}
}
