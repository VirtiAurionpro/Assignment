package com.aurionpro.model.Exceptions;

public class InvalidChoice extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidChoice() {
		super();
	}
	public String getMessage() {
		return "\nInvalid Choice. Please try again.";
	}
}
