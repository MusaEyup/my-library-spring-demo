package com.spring.app.model;

public class UserModel {
	
	private String name;
	private String lastName;
	private String userName;
	private String password;
	
	public UserModel() {};
	public UserModel(String name, String lastName, String userName, String password) {
		this.name = name;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
	}
public UserModel(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public String getUserName() { return userName; }
	public void setUserName(String userName) { this.userName = userName; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
}
