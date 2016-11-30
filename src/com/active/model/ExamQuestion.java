package com.active.model;

import java.util.ArrayList;

public class ExamQuestion {

	private int questionNo;
	private String question;
	private int answer;
	
	private ArrayList<ExamChoice> examChoice = new ArrayList<ExamChoice>();
	
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
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	public ArrayList<ExamChoice> getExamChoice() {
		return examChoice;
	}
	public void setExamChoice(ArrayList<ExamChoice> examChoice) {
		this.examChoice = examChoice;
	}
}
