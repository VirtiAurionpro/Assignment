package com.aurionpro.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static DatabaseConnection instance;
	private Connection connection = null;

	private DatabaseConnection() {
		connect();
	}

	public static synchronized DatabaseConnection getInstance() {
		if (instance == null) {
			instance = new DatabaseConnection();
		}
		return instance;
	}

	public Connection getConnection() {
		if (connection == null) {
			connect();
		}
		return connection;
	}

	private void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_app", "root", "Julyroot#345");
			System.out.println("Connected Successfully.");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
