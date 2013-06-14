package com.platform.domain;

public class Users extends BaseDomain{

	private String number;
	private String password;
	private String schoolId;
	private String begindate;
	private int remindtimes;
	private int reminddays;
	private String identity;
	private String name;
	private String nickname;
	private String cartype;
	private String phonenumber;
	private int age;
	private String sex;
	private String role;
	private String activeTime;
	
	
	public String getActiveTime() {
		return activeTime;
	}
	public void setActiveTime(String activeTime) {
		this.activeTime = activeTime;
	}
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
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getBegindate() {
		return begindate;
	}
	public void setBegindate(String begindate) {
		this.begindate = begindate;
	}

	public int getReminddays() {
		return reminddays;
	}
	public void setReminddays(int reminddays) {
		this.reminddays = reminddays;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getCartype() {
		return cartype;
	}
	public void setCartype(String cartype) {
		this.cartype = cartype;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRole() {
		return role;
	}
	
	public void SetRole(String role) {
		this.role = role;
	}
	public int getRemindtimes() {
		return remindtimes;
	}
	public void setRemindtimes(int remindtimes) {
		this.remindtimes = remindtimes;
	}
	
}
