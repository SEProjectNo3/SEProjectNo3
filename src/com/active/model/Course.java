package com.active.model;

public class Course 
{
	private String courseNumber;
	private int studentCount;
	private String courseName;
	private String professor;
	private String major;
	private String explanation;
	private int unit;
	
	public String getCourseNumber() {
		return courseNumber;
	}
	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}
	public int getStudentCount() {
		return studentCount;
	}
	public void setStudentCount(int studentCount) {
		this.studentCount = studentCount;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getProfessor() {
		return professor;
	}
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
}
