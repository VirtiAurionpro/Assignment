package com.aurionpro.model.Exceptions;

public class AlreadyExists extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String existing;

	public String getExisting() {
		return existing;
	}

	public void setExisting(String existing) {
		this.existing = existing;
	}

	public AlreadyExists(String existing) {
		super();
		this.existing = existing;
	}
	public AlreadyExists() {
		super();
	}
	public String getMessage() {
		return "\n'"+existing+"' already exists. Please use an unique name.";
	}
}
