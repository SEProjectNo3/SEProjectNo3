package com.active.model;

import java.util.ArrayList;
import java.util.Date;

public class Quiz 
{
	private int quizNo;
	private String lectureId;
	private String question;
	private int answer;
	private ArrayList<String> choice;
	private Date time;
	
	public int getQuizNo() {
		return quizNo;
	}
	
	public void setQuizNo(int quizNo) {
		this.quizNo = quizNo;
	}
	
	public String getLectureId() {
		return lectureId;
	}
	
	public void setLectureId(String lectureId) {
		this.lectureId = lectureId;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public int getAnswer() {
		return answer;
	}
	
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	
	public ArrayList<String> getChoice() {
		return choice;
	}
	
	public void setChoice(ArrayList<String> choice) {
		this.choice = choice;
	}
	
	public Date getTime() {
		return time;
	}
	
	public void setTime(Date time) {
		this.time = time;
	}
}
