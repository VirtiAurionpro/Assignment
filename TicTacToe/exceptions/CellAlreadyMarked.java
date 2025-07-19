package com.aurionpro.exceptions;

public class CellAlreadyMarked extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int row;
	private int coloumn;

	public CellAlreadyMarked(int row, int coloumn) {
		super();
		this.row = row;
		this.coloumn = coloumn;
	}

	public CellAlreadyMarked() {
		super();
	}

	public String getMessage() {
		return "The cell at "+row+" "+coloumn+" is already marked";
	}

}
