package com.active.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.active.model.QnA;
import com.active.model.Response;

public class QnADAO 
{
	private static QnADAO qnaDao;
	private static ResourceBundle bundle;
	
	static
	{
		bundle = ResourceBundle.getBundle("config/jdbc");
	}
	
	public static synchronized QnADAO getInstance()
	{
		if(qnaDao == null)
			qnaDao = new QnADAO();
		
		return qnaDao;
	}
	
	
	private QnADAO()
	{
		try
		{
			//DriverManager에 등록
			Class.forName(bundle.getString("driver"));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private Connection getConnection()
	{
		try
		{
			// DriverManager 객체로부터 Connection 객체를 얻어온다.
			Connection conn = DriverManager.getConnection(bundle.getString("url")
					,bundle.getString("user_id"),bundle.getString("user_pwd"));
			return conn;
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	private void closeConnection(Connection conn)
	{
		try
		{
			if(conn != null)
				conn.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean insertQuestion(String tempQuestion, String tempWriter, Date tempTime)
	{
		Connection conn = getConnection();
		
		String insertSQL = "Insert Into Question(question,writer,writeTime,courseNumber)"
				+ "Values(?,?,?,?,?)";
		
		PreparedStatement pstmt = null;
		
		try
		{
			pstmt = conn.prepareStatement(insertSQL);
			
			pstmt.setString(1, tempLecture.getLectureId());
			pstmt.setString(2, tempLecture.getCourseNumber());
			pstmt.setString(3, tempLecture.getTitle());
			pstmt.setString(4, tempLecture.getExplanation());
			pstmt.setString(5, tempLecture.getFilePath());
			pstmt.setInt(6, tempLecture.getHits());
			
			int result = pstmt.executeUpdate();
			
			if(result>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		finally{
			closeConnection(conn);
		}
	}
	public boolean insertResponse(int tempQuestionNo, Response tempResponse)
	{
		
	}
	public boolean deleteQuestion(int tempQuestionNo)
	{
		
	}
	public boolean deleteResponse(int tempQuestionNo, int tempResponseNo)
	{
		
	}
	public boolean updateQuestion(int tempQuestion)
	{
		
	}
	public boolean updateResponse(int tempQuestionNo, int tempResponseNo)
	{
		
	}
	public ArrayList<QnA> searchQuestionCourse(String tempCourseNumber)
	{
		
	}
	public ArrayList<QnA> searchQuestionWriter(String tempWriter)
	{
		
	}
}
