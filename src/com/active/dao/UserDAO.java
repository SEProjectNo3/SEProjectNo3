package com.active.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.active.model.User;

public class UserDAO 
{
	private static UserDAO userDao;
	private static ResourceBundle bundle;
	
	static
	{
		bundle = ResourceBundle.getBundle("config/jdbc");
	}
	
	private UserDAO()
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
	
	public static UserDAO getInstance()
	{
		if(userDao == null)
			userDao = new UserDAO();
		return userDao;
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
	
	public boolean insertUser(User tempUser)
	{
		Connection conn = getConnection();
		
		String insertSQL = "Insert Into user(userid,pwd,userName,phone,email,major,userType)"
				+ "Values(?,?,?,?,?,?,?)";
		
		PreparedStatement pstmt = null;
		
		try
		{
			pstmt = conn.prepareStatement(insertSQL);
			
			pstmt.setString(1, tempUser.getUserId());
			pstmt.setString(2, tempUser.getPwd());
			pstmt.setString(3, tempUser.getUserName());
			pstmt.setString(4, tempUser.getPhone());
			pstmt.setString(5, tempUser.getEmail());
			pstmt.setString(6, tempUser.getMajor());
			pstmt.setInt(7, tempUser.getUserType());
			
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
	
	public boolean deleteUser(String tempUserId)
	{
		Connection conn = getConnection();
		
		String deleteSQL = "Delete from user where userId = ?";
		
		PreparedStatement pstmt = null;
		
		try
		{
			pstmt = conn.prepareStatement(deleteSQL);
			
			pstmt.setString(1, tempUserId);
			
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
	 * Don't be used in our system
	 * Because it is derived from U-DRims and e-class server pages.
	 */
	public boolean updateUser(User tempUser)
	{
		Connection conn = getConnection();
		
		String updateSQL = "";
		
		PreparedStatement pstmt = null;
		
		try
		{
			pstmt = conn.prepareStatement(updateSQL);
			
			pstmt.setString(1, tempUser.getUserId());
			
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
	
	public User searchUser(String tempUserId,String tempPwd)
	{
		Connection conn = getConnection();
		User tUser= new User();
		
		String searchSQL = "Select * from user where tempUserId = "+tempUserId+"";
		
		Statement stmt = null;
		
		try
		{
			stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(searchSQL);
			
			while(rSet.next())
			{
				tUser.setUserId(rSet.getString("userId"));
				tUser.setPwd(rSet.getString("pwd"));
				tUser.setUserName(rSet.getString("userName"));
				tUser.setPhone(rSet.getString("phone"));
				tUser.setEmail(rSet.getString("email"));
				tUser.setMajor(rSet.getString("major"));
				tUser.setUserType(rSet.getInt("userType"));
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeConnection(conn);
		}
		return tUser;
	}
	
	public ArrayList<User> searchUser(String tempCourseNumber)
	{
		Connection conn = getConnection();
		ArrayList<User> userList = new ArrayList<User>();
		User tUser= new User();
		
		String searchSQL = "Select * from user where courseNumber = "+tempCourseNumber+"";
		
		Statement stmt = null;
		
		try
		{
			stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(searchSQL);
			
			while(rSet.next())
			{
				tUser.setUserId(rSet.getString("userId"));
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
	
	public ArrayList<User> searchAllUsers()
	{
		Connection conn = getConnection();
		ArrayList<User> userList = new ArrayList<User>();
		User tUser= new User();
		
		String searchSQL = "Select * from user;";
		
		Statement stmt = null;
		
		try
		{
			stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(searchSQL);
			
			while(rSet.next())
			{
				tUser.setUserId(rSet.getString("userId"));
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
	
	public String findPassword(String tempUserId)
	{
		// fill out udrims path
		return "";
	}
	
}
