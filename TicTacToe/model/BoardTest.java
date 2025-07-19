package com.aurionpro.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.aurionpro.exceptions.CellAlreadyMarked;

class BoardTest {

	private Cell[][] board;
	private MarkType mark;
	private Game game;

	@BeforeEach
	void init() {
		board = new Board().loadBoard(3);
		game = new Game();
	}

	@Test
	void testEmpty() {
		assertEquals(board[0][0].getMark(), mark.NULL);
		assertEquals(board[1][2].getMark(), mark.NULL);
		assertEquals(board[2][2].getMark(), mark.NULL);
	}

	@Test
	void testMark() {
		Player player = new Player("Virti", mark.O);

		board = game.markBoard(board, 1, 0, player);
		board = game.markBoard(board, 2, 1, player);
		board = game.markBoard(board, 0, 2, player);

		assertEquals(board[1][0].getMark(), mark.O);
		assertEquals(board[2][1].getMark(), mark.O);
		assertEquals(board[0][2].getMark(), mark.O);
	}

	@Test
	void testSameMark() {
		Player player1 = new Player("Virti", mark.O);
		Player player2 = new Player("VK", mark.X);

		board = game.markBoard(board, 1, 0, player1);
		assertEquals(board[1][0].getMark(), mark.O);

		assertThrows(CellAlreadyMarked.class, () -> {
			game.markBoard(board, 1, 0, player2);
		});
	}
}
