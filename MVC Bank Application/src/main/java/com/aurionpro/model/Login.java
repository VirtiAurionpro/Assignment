package com.aurionpro.model;

public class Login {
	private String username;
	private String password;
	private String role;
	private boolean isFirstLogin;

	public Login(String username, String password, String role, boolean isFirstLogin) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.isFirstLogin = isFirstLogin;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isFirstLogin() {
		return isFirstLogin;
	}

	public void setFirstLogin(boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}

	public Login() {
		super();
	}

}
