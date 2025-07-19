package com.aurionpro.model;

import java.util.Scanner;

import com.aurionpro.exceptions.CellAlreadyMarked;
import com.aurionpro.exceptions.InvalidInputException;

public class Game {

	boolean winner = false;
	int count = 0;

	public void start(Player player1, Player player2, Cell[][] board, Scanner scanner) {
		Player currentPlayer = player1;
		while (count < 9) {
			System.out.println(currentPlayer.getName() + "'s turn");
			if (setMark(currentPlayer, board, scanner)) {
				System.out.println(currentPlayer.getName() + " WON!!!");
				System.out.println("Game Over");
				break;
			}
			currentPlayer = (currentPlayer.equals(player1)) ? player2 : player1;
		}
		if (!winner) {
			System.out.println("GAME DRAW");
		}
		System.out.println("WOULD YOU LIKE TO PLAY AGAIN(Y/N)?");
		char ans = scanner.next().charAt(0);
		if (ans == 'Y') {
			board = new Board().loadBoard(3);
			count = 0;
			winner = false;
			start(player1, player2, board, scanner);
		}
		if (ans == 'N')
			System.out.println("THANK YOU FOR PLAYING");
	}

	private boolean setMark(Player player, Cell[][] board, Scanner scanner) {
		System.out.println("Enter position: (Enter in row,col between 0 and 2)");

		int row = -1, column = -1;
		boolean validInput = false;

		while (!validInput) {
			try {
				if (!scanner.hasNextInt()) {
					scanner.next();
					throw new InvalidInputException();
				}
				row = scanner.nextInt();
				if (!scanner.hasNextInt()) {
					scanner.next();
					throw new InvalidInputException();
				}
				column = scanner.nextInt();
				if (row < 0 || row > 2 || column < 0 || column > 2) {
					throw new InvalidInputException();
				}
				board = markBoard(board, row, column, player);
				validInput = true;
				if (new CheckWinner().start(board, row, column)) {
					winner = true;
					return true;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return false;
	}

	public Cell[][] markBoard(Cell[][] board, int row, int column, Player player) {
		if (board[row][column].isMarked()) {
			throw new CellAlreadyMarked();
		}
		board[row][column].setMark(player.getChoice());
		board[row][column].setMarked(true);
		count++;
		new Board().printBoard(board);
		return board;
	}

}
