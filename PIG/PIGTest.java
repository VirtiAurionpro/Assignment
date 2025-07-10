package com.aurionpro.test;

import java.util.Random;
import java.util.Scanner;

import com.aurionpro.model.PIG;

public class PIGTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int turns = 1;
		int totalScore = 0;
		PIG pig = playingGame(turns, totalScore);
		System.out.println(pig);
	}

	private static PIG playingGame(int turns, int totalScore) {
		// TODO Auto-generated method stub
		PIG pig = new PIG(totalScore, turns);
		while (true) {
			if (pig.totalScore >= 20)
				return pig;
			pig.totalScore = pig.RollorHold(turns, totalScore);
		}
	}
}
