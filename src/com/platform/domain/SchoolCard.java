package com.platform.domain;

public class SchoolCard extends BaseDomain implements BaseRole{

	private String number;
	private String password;
	private String role;
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String getRole() {
		return role;
	}
	@Override
	public void SetRole(String role) {
		this.role = role;
	}
	
}
