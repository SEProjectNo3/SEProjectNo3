package com.active.model;

import java.util.ArrayList;
import java.util.Date;

public class QnA 
{
	private int questionNo;
	private String question;
	private String writer;
	private ArrayList<Response> respondList;
	private Date time;
	
	public int getQuestionNo() {
		return questionNo;
	}
	
	public void setQuestionNo(int questionNo) {
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
	
	public ArrayList<Response> getRespondList() {
		return respondList;
	}
	
	public void setRespondList(ArrayList<Response> respondList) {
		this.respondList = respondList;
	}
	
	public Date getTime() {
		return time;
	}
	
	public void setTime(Date time) {
		this.time = time;
	}
}