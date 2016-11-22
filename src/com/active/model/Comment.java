package com.active.model;
import java.sql.Date;

public class Comment {
	
	private int commentNo;
	private String writer;
	private String content;
	private Date writeTime;
	private String letureId;
	
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getWriteTime() {
		return writeTime;
	}
	public void setWriteTime(Date writeTime) {
		this.writeTime = writeTime;
	}
	public String getLetureId() {
		return letureId;
	}
	public void setLetureId(String letureId) {
		this.letureId = letureId;
	}
}
