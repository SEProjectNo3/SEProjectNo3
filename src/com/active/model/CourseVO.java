package com.active.model;

public class CourseVO {

	private String courseNumber;
	private int studentCount;
	private String courseName;
	private String major;
	
	private String professor;
	private String professorName;
	
	private String explanation;
	private int unit;
	
	private int rate; // ∆Ú¡°

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

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public String getProfessorName() {
		return professorName;
	}

	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}

	public String getExplanation() {
		return explanation;
	}
	
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	
	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
}
