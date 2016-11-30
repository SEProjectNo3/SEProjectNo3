package com.active.model;

public class QuizChoice {
	private int choiceNumber;
	private int quizNo;
	private String answer;
	public int getChoiceNumber() {
		return choiceNumber;
	}
	public void setChoiceNumber(int choiceNumber) {
		this.choiceNumber = choiceNumber;
	}
	public int getQuizNo() {
		return quizNo;
	}
	public void setQuizNo(int quizNo) {
		this.quizNo = quizNo;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "QuizChoice [choiceNumber=" + choiceNumber + ", quizNo=" + quizNo + ", answer=" + answer + "]";
	}
}
