package com.aurionpro.model;

public class Board {

	private Cell[][] board;

	public Cell[][] getBoard() {
		return board;
	}

	public void setBoard(Cell[][] board) {
		this.board = board;
	}

	public Cell[][] loadBoard(int size) {
		board = new Cell[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = new Cell(i, j, false, MarkType.NULL);
			}
		}
		printBoard(board);
		return board;
	}

	public void printBoard(Cell[][] board) {
		int size = board.length;
		System.out.println("\nCurrent Board:\n");

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				String cellContent = board[i][j].isMarked() ? board[i][j].getMark().toString() : " ";
				System.out.print(" " + cellContent + " ");
				if (j < size - 1)
					System.out.print("|");
			}
			System.out.println();
			if (i < size - 1) {
				System.out.println("---+---+---");
			}
		}
		System.out.println();
	}

}
