package com.active.dto;

import java.sql.Timestamp;

public class QuestionDto {

	Integer questionNo=null;
	String question=null;
	String writer=null;
	Timestamp writeTime=null;
	String courseNumber=null;
	String questionTitle=null;
	
	//생성자
	public QuestionDto(Integer questionNo, String question, String writer, Timestamp writeTime, String courseNumber,
			String questionTitle) {
		super();
		this.questionNo = questionNo;
		this.question = question;
		this.writer = writer;
		this.writeTime = writeTime;
		this.courseNumber = courseNumber;
		this.questionTitle = questionTitle;
	}
	
	//여기부터 getter, setter 
	public Integer getQuestionNo() {
		return questionNo;
	}
	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public void setQuestionNo(Integer questionNo) {
		this.questionNo = questionNo;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Timestamp getWriteTime() {
		return writeTime;
	}
	public void setWriteTime(Timestamp writeTime) {
		this.writeTime = writeTime;
	}
	public String getCourseNumber() {
		return courseNumber;
	}
	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}
	

	
}
