package com.active.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.active.model.Exam;

public class ExamDAO 
{
	private static ExamDAO examDao;
	private static ResourceBundle bundle;
	
	static
	{
		bundle = ResourceBundle.getBundle("config/jdbc");
	}
	
	public static synchronized ExamDAO getInstance()
	{
		if(examDao == null)
			examDao = new ExamDAO();
		
		return examDao;
	}
	
	private ExamDAO()
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
	
	public boolean insertExam(Exam tempExam)
	{
		Connection conn = getConnection();
		
		String insertSQL = "Insert Into Exam(examNo,courseNumber,chapter)"
				+ " Values(?,?,?)";
		
		PreparedStatement pstmt = null;
		
		try
		{
			pstmt = conn.prepareStatement(insertSQL);
			
			pstmt.setInt(1, tempExam.getExamNo());
			pstmt.setString(2, tempExam.getCourseNumber());
			pstmt.setInt(3, tempExam.getChapter());
			
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
	
	public boolean deleteExam(int tempExamNo)
	{
		Connection conn = getConnection();
		
		String deleteSQL = "delete from exam where examNo = ?";
		
		PreparedStatement pstmt = null;
		
		try
		{
			pstmt = conn.prepareStatement(deleteSQL);
			
			pstmt.setInt(1, tempExamNo);
			
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
	
	public boolean updateExam(int tempExamNo, int tempChapter)
	{
		Connection conn = getConnection();
		
		String updateSQL = "update exam set 
		
		PreparedStatement pstmt = null;
		
		try
		{
			pstmt = conn.prepareStatement(updateSQL);
			
			pstmt.setInt(1, tempExam.getExamNo());
			pstmt.setString(2, tempExam.getCourseNumber());
			pstmt.setInt(3, tempExam.getChapter());
			
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
	
	public ArrayList<Question> searchQuestion(String tempLectureId, int tempNumber)
	{
		
	}
	
	public ArrayList<Exam> searchAllExams(String tempLectureId, int tempNumber)
	{
		ArrayList<Exam> examList = new ArrayList<Exam>();
		Connection conn = getConnection();
		Exam tExam= new Exam();
		
		String searchSQL = "Select * from enroll where lectureId = "+tempLectureId+"";
		
		Statement stmt = null;
		
		try
		{
			stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(searchSQL);
			
			while(rSet.next())
			{
				tExam.setExamNo(rSet.getInt("userNo"));
				tExam.setCourseNumber(rSet.getString("courseNumber"));
				tExam.setChapter(rSet.getInt("courseStatus"));
				examList.add(tExam);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeConnection(conn);
		}
		return examList;
	}
	
	public markingExam(String tempLectureId, int tempExamNo)
	{
		
	}
}
