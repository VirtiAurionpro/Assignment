package com.aurionpro.exceptions;

public class InvalidInputException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidInputException() {
		super();
	}
	public String getMessage() {
		return "Invalid Input. Row and Coloumn must be between 0 and 2";
	}
}
