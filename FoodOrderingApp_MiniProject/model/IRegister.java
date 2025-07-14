package com.aurionpro.model;

import java.util.Scanner;

public interface IRegister {
	String generateID();
	String validateUsername(Scanner scanner);
	String validatePassword(Scanner scanner);
}
