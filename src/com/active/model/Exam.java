package com.active.model;

import java.util.ArrayList;

public class Exam {
	
	private int examNo;
	private int chapter;
	private String courseNumber;
	
	private ArrayList<ExamQuestion> examQuestion = new ArrayList<ExamQuestion>();

	public int getExamNo() {
		return examNo;
	}
	
	public void setExamNo(int examNo) {
		this.examNo = examNo;
	}
	
	public int getChapter() {
		return chapter;
	}
	
	public void setChapter(int chapter) {
		this.chapter = chapter;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public ArrayList<ExamQuestion> getExamQuestion() {
		return examQuestion;
	}

	public void setExamQuestion(ArrayList<ExamQuestion> examQuestion) {
		this.examQuestion = examQuestion;
	}
}
