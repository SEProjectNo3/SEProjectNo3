package com.active.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.active.model.Lecture;

public class LectureDAO 
{
	private static LectureDAO lectureDao;
	private static ResourceBundle bundle;
	
	static
	{
		bundle = ResourceBundle.getBundle("config/jdbc");
	}
	
	public static synchronized LectureDAO getInstance()
	{
		if(lectureDao == null)
			lectureDao = new LectureDAO();
		
		return lectureDao;
	}
	
	private LectureDAO()
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
	
	public boolean insertLecture(Lecture tempLecture, String tempCourseNumber)
	{
		Connection conn = getConnection();
		
		String insertSQL = "Insert Into Lecture(lectureId,courseNumber,title,explanation,filePath,hits)"
				+ "Values(?,?,?,?,?,?)";
		
		PreparedStatement pstmt = null;
		
		try
		{
			pstmt = conn.prepareStatement(insertSQL);
			
			pstmt.setString(1, tempLecture.getLectureId());
			pstmt.setString(2, tempCourseNumber);
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
	
	public boolean deleteLecture(String tempLectureId)
	{
		Connection conn = getConnection();
		
		String deleteSQL = "delete from lecture where lectureId = ?";
		
		PreparedStatement pstmt = null;
		
		try
		{
			pstmt = conn.prepareStatement(deleteSQL);
			
			pstmt.setString(1, tempLectureId);
			
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
	
	public boolean updateLecture(String tempLectureId,String tempCourseNumber, String tempTitle, String tempExplanation, String tempFilePath, int tempHits)
	{
		Connection conn = getConnection();
		
		String updateSQL = "update lecture set lectureId = ?, title = ?, explanation = ?, filePath = ?, hits = ?";
		
		PreparedStatement pstmt = null;
		
		try
		{
			pstmt = conn.prepareStatement(updateSQL);
			
			pstmt.setString(1, tempLectureId);
			pstmt.setString(2, tempCourseNumber);
			pstmt.setString(3, tempTitle);
			pstmt.setString(4, tempExplanation);
			pstmt.setString(5, tempFilePath);
			pstmt.setInt(6, tempHits);
			
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
	
	public Lecture searchLecture(String tempLectureId)
	{
		Connection conn = getConnection();
		Lecture tLecture= new Lecture();

		String searchSQL = "Select * from Lecture where lectureId = "+tempLectureId+"";
		
		Statement stmt = null;
		
		try
		{
			stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(searchSQL);
			
			while(rSet.next())
			{
				tLecture.setLectureId(rSet.getString("lectureId"));
				tLecture.setTitle(rSet.getString("title"));
				tLecture.setExplanation(rSet.getString("explanation"));
				tLecture.setFilePath(rSet.getString("filePath"));
				tLecture.setHits(rSet.getInt("hits"));
			}
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeConnection(conn);
		}
		return tLecture;
	}
	
	public ArrayList<Lecture> searchLectureList(String tempCourseNumber)
	{
		Connection conn = getConnection();
		Lecture tLecture= new Lecture();
		ArrayList<Lecture> lectureList = new ArrayList<Lecture>();

		String searchSQL = "Select * from Lecture where courseNumber = "+tempCourseNumber+"";
		
		Statement stmt = null;
		
		try
		{
			stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(searchSQL);
			
			while(rSet.next())
			{
				tLecture.setLectureId(rSet.getString("lectureId"));
				tLecture.setTitle(rSet.getString("title"));
				tLecture.setExplanation(rSet.getString("explanation"));
				tLecture.setFilePath(rSet.getString("filePath"));
				tLecture.setHits(rSet.getInt("hits"));
				lectureList.add(tLecture);
			}
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeConnection(conn);
		}
		return lectureList;
	}	
	
	public ArrayList<Lecture> searchAllLecture()
	{
		Connection conn = getConnection();
		Lecture tLecture= new Lecture();
		ArrayList<Lecture> lectureList = new ArrayList<Lecture>();

		String searchSQL = "Select * from Lecture";
		
		Statement stmt = null;
		
		try
		{
			stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(searchSQL);
			
			while(rSet.next())
			{
				tLecture.setLectureId(rSet.getString("lectureId"));
				tLecture.setTitle(rSet.getString("title"));
				tLecture.setExplanation(rSet.getString("explanation"));
				tLecture.setFilePath(rSet.getString("filePath"));
				tLecture.setHits(rSet.getInt("hits"));
				lectureList.add(tLecture);
			}
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeConnection(conn);
		}
		return lectureList;
	}	
	
	public boolean updateMaterialList(ArrayList<String> tempMaterialList)
	{
		return true;
	}
}
