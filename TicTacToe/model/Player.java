package com.aurionpro.model;

import java.util.Scanner;

public class Player {
	Scanner scanner = new Scanner(System.in);
	private String name;
	private MarkType choice;
	private Player player1;
	private Player player2;

	public Player(String name, MarkType choice) {
		super();
		this.name = name;
		this.choice = choice;
	}

	public Player() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MarkType getChoice() {
		return choice;
	}

	public void setChoice(MarkType choice) {
		this.choice = choice;
	}

	public void start() {
		player1 = getPlayer1Details();
		player2 = getPlayer2Details();
		printDetails();
		Cell[][] board=new Board().loadBoard(3);
		new Game().start(player1,player2,board,scanner);
	}

	private void printDetails() {
		System.out.println("Player 1 Details");
		System.out.println("Name:" + player1.getName());
		System.out.println("Choice:" + player1.getChoice());
		System.out.println();
		System.out.println("Player 2 Details");
		System.out.println("Name:" + player2.getName());
		System.out.println("Choice:" + player2.getChoice());
	}

	private Player getPlayer1Details() {
		System.out.println("Player 1");
		System.out.println("Enter Name:");
		String player1Name = scanner.next();
		System.out.println("Enter choice(X/O):");
		char choice = scanner.next().charAt(0);
		if (choice == 'X') {
			player1 = new Player(player1Name, MarkType.X);
			return player1;
		}
		return player1 = new Player(player1Name, MarkType.O);
	}

	private Player getPlayer2Details() {
		System.out.println("Player 2");
		System.out.println("Enter Name:");
		String player2Name = scanner.next();
		if (player1.getChoice().equals(MarkType.O)) {
			player2 = new Player(player2Name, MarkType.X);
			return player2;
		}
		return player2 = new Player(player2Name, MarkType.O);
	}

}
