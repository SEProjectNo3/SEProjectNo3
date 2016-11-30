package com.active.model;
import java.sql.Date;

public class Comment 
{
	
	/*
	 * private int commentNo : primary key of Comment object
	 * private String writer : writer's name of comment
	 * private String content : content written by writers
	 * private Date writeTime : The time when DB has its tuple
	 * private String lectureId : Each lecture can have Comment list, so it is a foreign key
	 */
	private int commentNo;
	private String writer;
	private String content;
	private Date writeTime;
	private String lectureId;
	
	public int getCommentNo() 
	{
		return commentNo;
	}
	public void setCommentNo(int commentNo) 
	{
		this.commentNo = commentNo;
	}
	public String getWriter() 
	{
		return writer;
	}
	public void setWriter(String writer) 
	{
		this.writer = writer;
	}
	public String getContent() 
	{
		return content;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}
	public Date getWriteTime() 
	{
		return writeTime;
	}
	public void setWriteTime(Date writeTime) 
	{
		this.writeTime = writeTime;
	}
	public String getLectureId() 
	{
		return lectureId;
	}
	public void setLectureId(String lectureId) 
	{
		this.lectureId = lectureId;
	}
}
