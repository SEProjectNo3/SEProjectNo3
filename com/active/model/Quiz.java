package com.active.model;

public class Quiz 
{
	private int quizNo;
	private String question;
	private String quizTime;
	private int answer;
	private String explanation;
	private String lectureId;
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
	@Override
	public String toString() {
		return "Quiz [quizNo=" + quizNo + ", question=" + question + ", quizTime=" + quizTime + ", answer=" + answer
				+ ", explanation=" + explanation + ", lectureId=" + lectureId + "]";
	}
	
	
}
