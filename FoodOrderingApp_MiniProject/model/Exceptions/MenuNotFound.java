package com.aurionpro.model.Exceptions;

public class MenuNotFound extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String menuID;

	public MenuNotFound() {
		super();
	}

	public MenuNotFound(String menuID) {
		super();
		this.menuID = menuID;
	}

	public String getMenuID() {
		return menuID;
	}

	public void setMenuID(String menuID) {
		this.menuID = menuID;
	}
	public String getMessage() {
		return "Invalid Menu ID."+menuID+" does not exist. Please try again";
	}
}
