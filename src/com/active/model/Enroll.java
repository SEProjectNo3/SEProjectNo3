package com.active.model;
import java.util.ArrayList;

/* It manages user who is registered in course
 * 
 */
public class Enroll 
{
	/* professor string object represents professor name of course
	 * studentList User type List object represents list of students registered in course 
	 * courseNumber object represents the primary code of course
	 */
	
	private String userId;
	private String courseNumber;
	private int courseStatus;
	private ArrayList<User> studentList;
	
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
	public int getCourseStatus() {
		return courseStatus;
	}
	public void setCourseStatus(int courseStatus) {
		this.courseStatus = courseStatus;
	}
	public ArrayList<User> getStudentList() {
		return studentList;
	}
	public void setStudentList(ArrayList<User> studentList) {
		this.studentList = studentList;
	}
}
