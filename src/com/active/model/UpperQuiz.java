package com.active.model;

import java.util.ArrayList;

public class UpperQuiz {
	private int quizNo;
	private String question;
	private String quizTime;
	private int answer;
	private String explanation;
	private String lectureId;
	private ArrayList<String> choice;
	public int getQuizNo() {
		return quizNo;
	}
	public void setQuizNo(int quizNo) {
		this.quizNo = quizNo;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getQuizTime() {
		return quizTime;
	}
	public void setQuizTime(String quizTime) {
		this.quizTime = quizTime;
	}
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getLectureId() {
		return lectureId;
	}
	public void setLectureId(String lectureId) {
		this.lectureId = lectureId;
	}
	public ArrayList<String> getChoice() {
		return choice;
	}
	public void setChoice(ArrayList<String> choice) {
		this.choice = choice;
	}
	@Override
	public String toString() {
		return "UpperQuiz [quizNo=" + quizNo + ", question=" + question + ", quizTime=" + quizTime + ", answer="
				+ answer + ", explanation=" + explanation + ", lectureId=" + lectureId + ", choice=" + choice + "]";
	}
	
}
