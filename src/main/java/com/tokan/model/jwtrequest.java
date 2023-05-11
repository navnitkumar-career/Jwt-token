package com.tokan.model;

public class jwtrequest {
	
	String username;
	String password;
	public jwtrequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public jwtrequest() {
		super();
		// TODO Auto-generated constructor stub
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
	@Override
	public String toString() {
		return "jwtrequest [username=" + username + ", password=" + password + "]";
	}

	

}
