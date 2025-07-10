package com.aurionpro.model;

import java.util.Random;
import java.util.Scanner;

public class PIG {
	public int totalScore;
	private int turns;

	public PIG() {
		this.totalScore = totalScore;
		this.turns = turns;
	}

	public PIG(int totalScore, int turns) {
		super();
		this.totalScore = totalScore;
		this.turns = turns;
	}

	@Override
	public String toString() {
		return "You finished in " + turns + " turns.\nGame Over.";
	}

	public int RollorHold(int turns2, int totalScore2) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int score = 0;
		for (;;) {
			System.out.println("Roll or hold?(r/h):");
			char choice = scanner.next().charAt(0);
			score = checkChoice(score, choice, turns);
			System.out.println("Score for turn:" + score);
			System.out.println("Total score:" + (totalScore + score));
			return totalScore + score;
		}
	}

	private  int checkChoice(int score, char choice, int turns) {
		// TODO Auto-generated method stub
		Random r = new Random();
		if (choice == 'r') {
			int die = r.nextInt(6) + 1;
			score = checkDie(die, score, turns);
			return score;
		}
		turns = turns + 1;
		this.turns = turns;
		return score;
	}

	private int checkDie(int die, int score, int turns) {
		// TODO Auto-generated method stub
		if (die == 1) {
			score = 0;
			System.out.println("Turn Over. No score");
			turns = turns + 1;
			this.turns = turns;
			return score;
		}
		score = score + die;
		System.out.println("Die:" + die);
		return score;
	}
}
