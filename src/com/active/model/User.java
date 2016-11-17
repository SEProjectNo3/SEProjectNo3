package com.active.model;
import java.util.HashMap;

public class User
{
	private String userId;
	private String pwd;
	private String userName;
	private String phone;
	private String email;
	private String major;
	private int userType; // information which checkout whether it is Professor or Student
	private HashMap<String,String> courseStatus;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public HashMap<String, String> getCourseStatus() {
		return courseStatus;
	}
	public void setCourseStatus(HashMap<String, String> courseStatus) {
		this.courseStatus = courseStatus;
	}
}

