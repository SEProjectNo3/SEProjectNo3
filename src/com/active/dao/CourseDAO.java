package com.active.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.active.model.Course;

public class CourseDAO 
{
	private static CourseDAO courseDao;
	private static ResourceBundle bundle;
	
	static
	{
		bundle = ResourceBundle.getBundle("config/jdbc");
	}
	
	public static synchronized CourseDAO getInstance()
	{
		if(courseDao == null)
			courseDao = new CourseDAO();
		
		return courseDao;	
	}
	
	private CourseDAO()
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
	
	public boolean insertCourse(Course tempCourse)
	{
		Connection conn = getConnection();
		
		String insertSQL = "Insert Into Course(courseNumber,courseName,studentCount,major,professor)"
				+ "Values(?,?,?,?,?)";
		
		PreparedStatement pstmt = null;
		
		try
		{
			pstmt = conn.prepareStatement(insertSQL);
			
			pstmt.setString(1, tempCourse.getCourseNumber());
			pstmt.setString(2, tempCourse.getCourseName());
			pstmt.setInt(3, tempCourse.getStudentCount());
			pstmt.setString(4, tempCourse.getMajor());
			pstmt.setString (5, tempCourse.getProfessor());
			
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
	public boolean deleteCourse(String tempCourseNumber)
	{
		Connection conn = getConnection();
		
		String deleteSQL = "delete from course where courseNumber = ?";
		
		PreparedStatement pstmt = null;
		
		try
		{
			pstmt = conn.prepareStatement(deleteSQL);
			
			pstmt.setString(1, tempCourseNumber);
			
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
	
	/*
	 * This method replaces name and professor of course with what users want to change
	 * When users fill out mistakenly the information of courses, they can modify them
	 */
	public boolean updateCourse(String tempCourseNumber, String tempCourseName, String tempProfessor, String tempStudentCount, String tempMajor)
	{
		Connection conn = getConnection();
		
		String updateSQL = "update course set courseName = ? and professor = ? and studentCount = ? and major = ?"
				+ "where courseNumber = ?";
		
		PreparedStatement pstmt = null;
		
		try
		{
			pstmt = conn.prepareStatement(updateSQL);
			
			pstmt.setString(1, tempCourseName);
			pstmt.setString(2, tempProfessor);
			pstmt.setString(3, tempCourseNumber);
			pstmt.setString(4, tempStudentCount);
			pstmt.setString(5, tempMajor);
			
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
	
	public ArrayList<Course> searchCourseProfessor(String tempProfessor)
	{
		ArrayList<Course> courseList = new ArrayList<Course>();
		Connection conn = getConnection();
		
		String searchSQL = "Select * from course where professor = "+tempProfessor+"";
		
		Statement stmt = null;
		
		try
		{
			stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(searchSQL);
			
			while(rSet.next())
			{
				Course tCourse= new Course();
				tCourse.setCourseNumber(rSet.getString("courseNumber"));
				tCourse.setCourseName(rSet.getString("courseName"));
				tCourse.setStudentCount(rSet.getInt("studentCount"));
				tCourse.setMajor(rSet.getString("major"));
				tCourse.setProfessor(rSet.getString("professor"));
				courseList.add(tCourse);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeConnection(conn);
		}
		return courseList;
	}
	
	public Course searchCourseNumber(String tempCourseNumber)
	{
		Connection conn = getConnection();
		Course tCourse= new Course();
		
		String searchSQL = "Select * from course where courseNumber = "+tempCourseNumber+"";
		
		Statement stmt = null;
		
		try
		{
			stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(searchSQL);
			
			while(rSet.next())
			{
				tCourse.setCourseNumber(rSet.getString("courseNumber"));
				tCourse.setCourseName(rSet.getString("courseName"));
				tCourse.setStudentCount(rSet.getInt("studentCount"));
				tCourse.setMajor(rSet.getString("major"));
				tCourse.setProfessor(rSet.getString("professor"));
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeConnection(conn);
		}
		return tCourse;
	}
	
	public ArrayList<Course> searchCourseName(String tempCourseName)
	{
		ArrayList<Course> courseList = new ArrayList<Course>();
		Connection conn = getConnection();
		
		String searchSQL = "Select * from course where courseName = "+tempCourseName+"";
		
		Statement stmt = null;
		
		try
		{
			stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(searchSQL);
			
			while(rSet.next())
			{
				Course tCourse= new Course();
				tCourse.setCourseNumber(rSet.getString("courseNumber"));
				tCourse.setCourseName(rSet.getString("courseName"));
				tCourse.setStudentCount(rSet.getInt("studentCount"));
				tCourse.setMajor(rSet.getString("major"));
				tCourse.setProfessor(rSet.getString("professor"));
				courseList.add(tCourse);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeConnection(conn);
		}
		return courseList;
	}
	
	public ArrayList<Course> searchAllCourse()
	{
		ArrayList<Course> courseList = new ArrayList<Course>();
		Connection conn = getConnection();
	
		String searchSQL = "Select * from course;";
		
		Statement stmt = null;
		
		try
		{
			stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(searchSQL);
			
			while(rSet.next())
			{
				Course tCourse= new Course();
				tCourse.setCourseNumber(rSet.getString("courseNumber"));
				tCourse.setCourseName(rSet.getString("courseName"));
				tCourse.setStudentCount(rSet.getInt("studentCount"));
				tCourse.setMajor(rSet.getString("major"));
				tCourse.setProfessor(rSet.getString("professor"));
				courseList.add(tCourse);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeConnection(conn);
		}
		return courseList;
	}
}
