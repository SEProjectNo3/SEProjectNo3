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

public class UserDAO {

	private static ResourceBundle bundle;
	private static UserDAO userDao;  
	
	static {
		bundle = ResourceBundle.getBundle("config/jdbc");
	}

	public static synchronized UserDAO getInstance() {
		
		if(userDao == null) {
			userDao = new UserDAO();
	    }
		
		return userDao;
	}
	
	private UserDAO() {  
		try {
			// DriverManager에 등록
			Class.forName(bundle.getString("driver"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private Connection getConnection() {
		try {
			// DriverManager 객체로부터 Connection 객체를 얻어온다.
			Connection conn = DriverManager.getConnection(bundle.getString("url"), bundle.getString("user_id"), bundle.getString("user_pwd"));
			return conn;
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void closeConnection(Connection conn){

		try {
			if(conn != null)
				conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean insertUser(User user) {
		
		Connection conn = getConnection();
	      
	    String insertSQL = "Insert Into USER(userId, pwd, userName, phone, email, major, userType) "
	    		  			+ "Values(?,?,?,?,?,?,?)";
	      
	    PreparedStatement pstmt = null;
	      
	    try {
	         
	    	pstmt = conn.prepareStatement(insertSQL);
	    	  
	    	pstmt.setString(1, user.getUserId());  
	    	pstmt.setString(2, user.getPwd());  
	  	  	pstmt.setString(3, user.getUserName());  
	    	pstmt.setString(4, user.getPhone());  
	    	pstmt.setString(5, user.getEmail());  
	    	pstmt.setString(6, user.getMajor());
	    	  
	    	// user Type check
	    	// if type == 0 : student
	    	// if type == 1 : professor
	    	pstmt.setInt(7, user.getUserType());  
    	  
	    	int result = pstmt.executeUpdate();
	    	if (result > 0) 
	    		return true;
	    	else
	    		return false;
      
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    	return false;
	    } finally {
	    	closeConnection(conn);
	    }
	}
	
	/**
	 * this method used to login
	 * @param id
	 * @param pwd
	 * @return User
	 */
	public User searchUser(String id, String pwd) {
		
	    Connection conn = getConnection();
	    
	    String searchSQL = "Select * from user where userid = '" + id + "' and pwd = '" + pwd + "'";
	      
	    Statement stmt = null;
	    User user = new User();
	      
	    try {

    	  stmt = conn.createStatement();
    	  ResultSet rs = stmt.executeQuery(searchSQL);
    	  
    	  if (rs.next()) {

    		  user.setUserId(rs.getString("userId"));
    		  user.setPwd(rs.getString("pwd"));
    		  user.setUserName(rs.getString("userName"));
    		  user.setPhone(rs.getString("phone"));
    		  user.setEmail(rs.getString("email"));
    		  user.setMajor(rs.getString("major"));
    		  user.setUserType(rs.getInt("userType"));
    	  }
    	  
      } catch (SQLException e) {
    	  
         e.printStackTrace();
         return null;
         
      }	finally {
         closeConnection(conn);
      }
      
      return user;
	}
	
	/**
	 * 수강생 조회, search users in specific course
	 * @param courseNumber
	 * @return
	 */
	public ArrayList<User> searchUser(String courseNumber) {
		
		Connection conn = getConnection();
		
		ArrayList<User> userList = new ArrayList<User>();
		User user= new User();
		
		String searchSQL = "Select * from USER where courseNumber = '" + courseNumber + "'";
		
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(searchSQL);
			
			while(rs.next()) {
				user.setUserId(rs.getString("userId"));
				user.setPwd(rs.getString("pwd"));
				user.setUserName(rs.getString("userName"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setMajor(rs.getString("major"));
				user.setUserType(rs.getInt("userType"));
				userList.add(user);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		return userList;
	}
	
	/**
	 * search all users in this program.
	 * @return
	 */
	public ArrayList<User> searchAllUsers()
	{
		Connection conn = getConnection();
		
		ArrayList<User> userList = new ArrayList<User>();
		User user= new User();
		
		String searchSQL = "Select * from user;";
		
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(searchSQL);
			
			while(rs.next()) {
				user.setUserId(rs.getString("userId"));
				user.setPwd(rs.getString("pwd"));
				user.setUserName(rs.getString("userName"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setMajor(rs.getString("major"));
				user.setUserType(rs.getInt("userType"));
				userList.add(user);
			}
		
			return userList;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
			
		} finally {
			closeConnection(conn);
		}
	}
	
	/**
	 * 비밀번호 변경에 사용, update user password (based on eclass)
	 * @param userId
	 * @param pwd
	 * @return boolean
	 */
	public boolean updateUser(String userId, String pwd) {
		
		Connection conn = getConnection();
		
		String updateSQL = "update user set pwd = ? where userId = ?";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(updateSQL);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, pwd);
			
			int result = pstmt.executeUpdate();
			
			if(result>0)
				return true;
			else
				return false;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		
		} finally{
			closeConnection(conn);
		}
	}
	
	/**
	 * delete user in this program
	 * @param userId
	 * @return
	 */
	public boolean deleteUser(String userId) {
		
		Connection conn = getConnection();
		
		String deleteSQL = "Delete from USER where userId = ?";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(deleteSQL);
			
			pstmt.setString(1, userId);
			
			int result = pstmt.executeUpdate();
			
			if(result > 0)
				return true;
			else
				return false;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally{
			closeConnection(conn);
		}
	}
	
	/**
	 * user request find user's password. 
	 * this method returns temporary password. And user should change the password.
	 * @param userId
	 * @return
	 */
	public String findPassword(String userId) {
		
		Connection conn = getConnection();
		
		String searchSQL = "Select * from USER where userId = '" + userId + "'";
		
		try {
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(searchSQL);
			
			if (rs.next()) {
				
				// 임시 비밀번호 발급 과정 필요
			} else {
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			closeConnection(conn);
		}
		
		return "";
	}
}
