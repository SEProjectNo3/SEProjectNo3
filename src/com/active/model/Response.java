package com.active.model;

import java.util.Date;

public class Response 
{
	private int responseNo;
	private int writer;
	private Date time;
	private String content;
	
	public int getResponseNo() {
		return responseNo;
	}
	
	public void setResponseNo(int responseNo) {
		this.responseNo = responseNo;
	}
	
	public int getWriter() {
		return writer;
	}
	
	public void setWriter(int writer) {
		this.writer = writer;
	}
	
	public Date getTime() {
		return time;
	}
	
	public void setTime(Date time) {
		this.time = time;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}