package com.active.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.active.model.Enroll;
import com.active.model.User;

public class EnrollDAO 
{
	private static EnrollDAO enrollDao;
	private static ResourceBundle bundle;
	
	static
	{
		bundle = ResourceBundle.getBundle("config/jdbc");
	}
	
	public static synchronized EnrollDAO getInstance()
	{
		if(enrollDao == null)
			enrollDao = new EnrollDAO();
		
		return enrollDao;	
	}
	
	private EnrollDAO()
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
	
	public boolean insertEnroll(Enroll tempEnroll)
	{
		Connection conn = getConnection();
		
		String insertSQL = "Insert Into Course(userId,courseNumber,courseStatus)"
				+ "Values(?,?,?)";
		
		PreparedStatement pstmt = null;
		
		try
		{
			pstmt = conn.prepareStatement(insertSQL);
			
			pstmt.setString(1, tempEnroll.getUserId());
			pstmt.setString(2, tempEnroll.getCourseNumber());
			pstmt.setInt(3, tempEnroll.getCourseStatus());
			
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
	
	public boolean deleteEnroll(String tempCourseNumber)
	{
		Connection conn = getConnection();
		
		String deleteSQL = "delete from enroll where courseNumber = ?";
		
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
	 * 코스명과
	 */
	public boolean updateEnroll(String tempCourseNumber, String tempProfessor)
	{
		Connection conn = getConnection();
		
		String updateSQL = "Delete From enroll(userId,courseNumber,courseStatus)"
				+ "Values(?,?,?)";
		
		PreparedStatement pstmt = null;
		
		try
		{
			pstmt = conn.prepareStatement(updateSQL);
			
			pstmt.setString(1, tempEnroll.getUserId());
			pstmt.setString(2, tempEnroll.getCourseNumber());
			pstmt.setInt(3, tempEnroll.getCourseStatus());
			
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
	
	public Enroll searchEnroll(String tempCourseNumber, User tempUser)
	{
		Connection conn = getConnection();
		Enroll tEnroll= new Enroll();

		String searchSQL = "Select * from enroll where courseNumber = "+tempCourseNumber+"";
		
		Statement stmt = null;
		
		try
		{
			stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(searchSQL);
			
			while(rSet.next())
			{
				tEnroll.setUserId(rSet.getString("userId"));
				tEnroll.setCourseNumber(rSet.getString("courseNumber"));
				tEnroll.setCourseStatus(rSet.getInt("courseStatus"));
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeConnection(conn);
		}
		return tEnroll;
	}
	
	public ArrayList<Enroll> searchAllEnroll(String tempCourseNumber, User tempUser)
	{
		ArrayList<Enroll> enrollList = new ArrayList<Enroll>();
		Connection conn = getConnection();
		Enroll tEnroll= new Enroll();
	
		String searchSQL = "Select * from enroll where courseNumber = "+tempCourseNumber+"";
		
		Statement stmt = null;
		
		try
		{
			stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(searchSQL);
			
			while(rSet.next())
			{
				tEnroll.setUserId(rSet.getString("userId"));
				tEnroll.setCourseNumber(rSet.getString("courseNumber"));
				tEnroll.setCourseStatus(rSet.getInt("courseStatus"));
				enrollList.add(tEnroll);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeConnection(conn);
		}
		return enrollList;
	}
	
	public ArrayList<User> searchStudentList(String tempCourseNumber)
	{
		Connection conn = getConnection();
		ArrayList<User> userList= new ArrayList<User>();

		String searchSQL = "Select * from user where courseNumber = "+tempCourseNumber+"";
		
		Statement stmt = null;
		
		try
		{
			stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(searchSQL);
			
			while(rSet.next())
			{
				User tUser = new User();
				tUser.setUserId(rSet.getString("userid"));
				tUser.setPwd(rSet.getString("pwd"));
				tUser.setUserName(rSet.getString("userName"));
				tUser.setPhone(rSet.getString("phone"));
				tUser.setEmail(rSet.getString("email"));
				tUser.setMajor(rSet.getString("major"));
				tUser.setUserType(rSet.getInt("userType"));
				userList.add(tUser);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeConnection(conn);
		}
		return userList;
	}
}
