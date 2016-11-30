package com.active.controller;

import java.sql.Timestamp;

public class AnswerDto {
	Integer responseNo;
	String writer;
	Timestamp writerTime;
	String content;
	Integer questionNo;
	
	//************************************************»ý¼ºÀÚ *************************************************//
	public AnswerDto(Integer responseNo, String writer, Timestamp writerTime, String content, Integer questionNo) {
		super();
		this.responseNo = responseNo;
		this.writer = writer;
		this.writerTime = writerTime;
		this.content = content;
		this.questionNo = questionNo;
	}
	
	//************************************************getter/ setter**********************************************//
	public Integer getResponseNo() {
		return responseNo;
	}
	public void setResponseNo(Integer responseNo) {
		this.responseNo = responseNo;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Timestamp getWriterTime() {
		return writerTime;
	}
	public void setWriterTime(Timestamp writerTime) {
		this.writerTime = writerTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(Integer questionNo) {
		this.questionNo = questionNo;
	}
	
	
	
}
