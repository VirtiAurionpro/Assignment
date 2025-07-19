package com.aurionpro.model;

public class CheckWinner {

	public boolean start(Cell[][] board, int row, int column) {

		if (checkHorizontalLine(board, row, column))
			return true;
		if (checkVerticalLine(board, row, column))
			return true;
		if (checkDiagonals(board, row, column))
			return true;
		return false;
	}
	private boolean checkDiagonals(Cell[][] board, int row, int column) {
		MarkType mark = board[row][column].getMark();
		if (row == column) {
			if ((board[0][0].getMark()).equals(mark) 
				&& (board[1][1].getMark()).equals(mark)
				&& (board[2][2].getMark()).equals(mark))
				return true;
		}
		if (row + column == 2)
			if ((board[0][2].getMark()).equals(mark) 
				&& (board[1][1].getMark()).equals(mark)
				&& (board[2][0].getMark()).equals(mark))
				return true;
		return false;
	}

	private boolean checkVerticalLine(Cell[][] board, int row, int column) {
		MarkType mark = board[row][column].getMark();
		if (row == 0) {
			if ((board[row + 1][column].getMark()).equals(mark) 
				&& (board[row + 2][column].getMark()).equals(mark))
				return true;
		}
		if (row == 1) {
			if ((board[row - 1][column].getMark()).equals(mark) 
				&& (board[row + 1][column].getMark()).equals(mark))
				return true;
		}
		if (row == 2) {
			if ((board[row - 1][column].getMark()).equals(mark) 
				&& (board[row - 2][column].getMark()).equals(mark))
				return true;
		}
		return false;
	}

	private boolean checkHorizontalLine(Cell[][] board, int row, int column) {
		MarkType mark = board[row][column].getMark();
		if (column == 0) {
			if ((board[row][column + 1].getMark()).equals(mark) 
				&& (board[row][column + 2].getMark()).equals(mark))
				return true;
		}
		if (column == 1) {
			if ((board[row][column - 1].getMark()).equals(mark) 
					&& (board[row][column + 1].getMark()).equals(mark))
				return true;
		}
		if (column == 2) {
			if ((board[row][column - 1].getMark()).equals(mark) 
					&& (board[row][column - 2].getMark()).equals(mark))
				return true;
		}
		return false;
	}


}
