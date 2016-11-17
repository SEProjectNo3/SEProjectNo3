package com.active.model;
import java.util.HashMap;

/* It manages user who is registered in course
 * 
 */
public class Enroll 
{
	/* professor string object represents professor name of course
	 * studentList User type List object represents list of students registered in course 
	 * courseNumber object represents the primary code of course
	 */
	private String userId; // Primary key in Database
	private String courseNumber; // Primary key in Database
	private String state;
	private int enrollNo;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCourseNumber() {
		return courseNumber;
	}
	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getEnrollNo() {
		return enrollNo;
	}
	public void setEnrollNo(int enrollNo) {
		this.enrollNo = enrollNo;
	}
}
