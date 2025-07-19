package com.aurionpro.model;

public class Cell<Marktype> {
	private int row;
	private int column;
	private boolean isMarked;
	private MarkType mark;

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getcolumn() {
		return column;
	}

	public void setcolumn(int column) {
		this.column = column;
	}

	public boolean isMarked() {
		return isMarked;
	}

	public void setMarked(boolean isMarked) {
		this.isMarked = isMarked;
	}

	

	public Cell(int row, int column, boolean isMarked, MarkType mark) {
		super();
		this.row = row;
		this.column = column;
		this.isMarked = isMarked;
		this.mark = mark;
	}

	public Cell() {
		super();
	}

	public MarkType getMark() {
		return mark;
	}

	public void setMark(MarkType mark) {
		this.mark = mark;
	}
}
