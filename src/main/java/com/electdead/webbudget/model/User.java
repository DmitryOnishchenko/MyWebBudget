package com.electdead.webbudget.model;

import java.io.Serializable;

import com.google.common.base.Joiner;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int userId;
	private String login;
	private String password;
	private String email;
	
	public int getUserId() {
		return userId;
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString() {
		return Joiner.on("").join("User.class [userId: ", userId, ", login: ",
				login, ", password: ", password, ", email: ", email, "]");
	}
}
