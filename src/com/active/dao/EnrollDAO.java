package com.active.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.active.constant.State;
import com.active.model.Course;
import com.active.model.Enroll;
import com.active.model.User;

public class EnrollDAO {
	private static EnrollDAO enrollDao;
	private static ResourceBundle bundle;
	
	static {
		bundle = ResourceBundle.getBundle("config/jdbc");
	}
	
	public static synchronized EnrollDAO getInstance() {
		if(enrollDao == null)
			enrollDao = new EnrollDAO();
		
		return enrollDao;	
	}
	
	private EnrollDAO() {
		try {
			//DriverManager에 등록
			Class.forName(bundle.getString("driver"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private Connection getConnection() {
		try {
			// DriverManager 객체로부터 Connection 객체를 얻어온다.
			Connection conn = DriverManager.getConnection(bundle.getString("url")
					,bundle.getString("user_id"),bundle.getString("user_pwd"));
			return conn;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void closeConnection(Connection conn) {
		try {
			if(conn != null)
				conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 학생이 강의를 수강 신청할 때 사용되는 메소드
	 * insertEnroll 의 경우 courseStatus 는 default 로 "begin" 으로 설정
	 */
	public boolean insertEnroll(Enroll tempEnroll) {
		
		Connection conn = getConnection();
		
		String insertSQL = "insert into enroll(userId,courseNumber)"
				+ " values(?,?)";
		
		PreparedStatement pstmt = null;
		
		try
		{
			pstmt = conn.prepareStatement(insertSQL);
			
			pstmt.setString(1, tempEnroll.getUserId());
			pstmt.setString(2, tempEnroll.getCourseNumber());
			
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
	 * 현재 수강과목 지우는 메소드, delete enroll
	 * @param userId
	 * @param courseNumber
	 * @return
	 */
	public boolean deleteEnroll(String userId, String courseNumber) {
		
		// userId 와 courseNumber 가 복합 기본키
		Connection conn = getConnection();
		
		String deleteSQL = "delete from enroll where userId = ? and courseNumber = ?";
		
		PreparedStatement pstmt = null;
		
		try {
			
			pstmt = conn.prepareStatement(deleteSQL);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, courseNumber);
			
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
	 * 학생의 수강 상태를 변경, update courseStatus. enroll 객체를 받아서 수정
	 * @param enroll
	 * @return
	 */
	public boolean updateEnroll(Enroll enroll) {
		
		Connection conn = getConnection();
		
		String updateSQL = "update enroll set courseStatus = ? where userId = ? and courseNumber = ?";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(updateSQL);
			
			pstmt.setString(1, enroll.getState());
			pstmt.setString(2, enroll.getUserId());
			pstmt.setString(3, enroll.getCourseNumber());
			
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
	 * 학생의 수강 과목 찾는 메소드, search user's enroll subject
	 * @param userId
	 * @return
	 */
	public ArrayList<Course> searchUserCourse(String userId) {
		
		Connection conn = getConnection();
		
		ArrayList<Course> courseList = new ArrayList<Course>();
			
		String searchSQL = "Select * from COURSE WHERE courseNumber In "
						+ "(Select courseNumber from ENROLL where userId = '" + userId + "'";
		//String searchSQL = "select * from enroll where userId = '"+userId+"'";
						
		Statement stmt = null;
		
		try {
			
			stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(searchSQL);
			
			while(rSet.next()) 
			{
				Course course = new Course();
				course.setCourseName(rSet.getString("courseName"));
				course.setCourseNumber(rSet.getString("courseNumber"));
				course.setMajor(rSet.getString("major"));
				course.setProfessor(rSet.getString("professor"));
				course.setStudentCount(rSet.getInt("studentCount"));
				
				courseList.add(course);
			}
			
			return courseList;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			closeConnection(conn);
		}
	}
	
	public ArrayList<Enroll> searchUserEnroll(String userId) {
		
		Connection conn = getConnection();
		
		ArrayList<Enroll> enrollList = new ArrayList<Enroll>();
			
		String searchSQL = "select * from enroll where userId = '"+userId+"'";
						
		Statement stmt = null;
		
		try
		{
			stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(searchSQL);
			
			while(rSet.next()) {
				Enroll tEnroll = new Enroll();
				tEnroll.setUserId(rSet.getString("userId"));
				tEnroll.setCourseNumber(rSet.getString("courseNumber"));
				tEnroll.setState(State.BEGIN);
				
				enrollList.add(tEnroll);
			}
			
			return enrollList;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			closeConnection(conn);
		}
	}
	
	/**
	 * 학수번호로 수강상태를 찾는 메소드
	 * @param tempCourseNumber
	 * @return
	 */
	public ArrayList<Enroll> searchEnroll(String tempCourseNumber) {
		
		Connection conn = getConnection();
		
		ArrayList<Enroll> enrollList = new ArrayList<Enroll>();
	
		String searchSQL = "Select * from enroll where courseNumber = '" + tempCourseNumber + "'";
		
		Statement stmt = null;
		
		try {
			
			stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(searchSQL);
			
			while(rSet.next()) 
			{
				Enroll tEnroll= new Enroll();
				tEnroll.setUserId(rSet.getString("userId"));
				tEnroll.setCourseNumber(rSet.getString("courseNumber"));
				tEnroll.setState(State.valueOf(rSet.getString("courseStatus")));
				
				enrollList.add(tEnroll);
			}
			
			return enrollList;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
			
		} finally {
			closeConnection(conn);
		}
	}
	
	/**
	 * 모든 수강 상태를 검색, search all enroll 
	 * @return
	 */
	public ArrayList<Enroll> searchAllEnrolls() {
		
		Connection conn = getConnection();
		
		ArrayList<Enroll> enrollList = new ArrayList<Enroll>();
		Enroll tEnroll= new Enroll();
	
		String searchSQL = "Select * from enroll";
		
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(searchSQL);
			
			while(rSet.next()) {
				tEnroll.setUserId(rSet.getString("userId"));
				tEnroll.setCourseNumber(rSet.getString("courseNumber"));
				tEnroll.setState(State.valueOf(rSet.getString("courseStatus")));
				
				enrollList.add(tEnroll);
			}
			
			return enrollList;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			closeConnection(conn);
		}
	}
	
	/**
	 * 수강생 조회 시 이용
	 * @param tempCourseNumber
	 * @return
	 */
	public ArrayList<User> searchStudentList(String tempCourseNumber) {
		
		Connection conn = getConnection();
		ArrayList<User> userList= new ArrayList<User>();

		String searchSQL = "Select * from user where userId IN"
				+ " (select userId from enroll where courseNumber = '" + tempCourseNumber + "'";
		
		Statement stmt = null;
		
		try {
			
			stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(searchSQL);
			
			while(rSet.next()) {
				
				User tUser = new User();
				
				tUser.setUserId(rSet.getString("userId"));
				tUser.setPwd(rSet.getString("pwd"));
				tUser.setUserName(rSet.getString("userName"));
				tUser.setPhone(rSet.getString("phone"));
				tUser.setEmail(rSet.getString("email"));
				tUser.setMajor(rSet.getString("major"));
				tUser.setUserType(rSet.getInt("userType"));
				
				userList.add(tUser);
			}
			
			return userList;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			closeConnection(conn);
		}
	}
}
