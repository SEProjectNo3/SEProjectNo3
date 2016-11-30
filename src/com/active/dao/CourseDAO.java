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

public class CourseDAO {
	private static CourseDAO courseDao;
	private static ResourceBundle bundle;
	
	static {
		bundle = ResourceBundle.getBundle("config/jdbc");
	}
	
	public static synchronized CourseDAO getInstance() {
		if(courseDao == null)
			courseDao = new CourseDAO();
		
		return courseDao;	
	}
	
	private CourseDAO() {
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
			Connection conn = DriverManager.getConnection(bundle.getString("url"), bundle.getString("user_id"),bundle.getString("user_pwd"));
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
	
	public boolean insertCourse(Course tempCourse) {
		
		Connection conn = getConnection();
		
		String insertSQL = "Insert Into Course(courseNumber,courseName,major,professor)"
						+ " Values(?,?,?,?)";
		
		PreparedStatement pstmt = null;
		
		try {
			
			pstmt = conn.prepareStatement(insertSQL);
			
			pstmt.setString(1, tempCourse.getCourseNumber());
			pstmt.setString(2, tempCourse.getCourseName());
			//pstmt.setInt(3, tempCourse.getStudentCount());
			pstmt.setString(3, tempCourse.getMajor());
			pstmt.setString (4, tempCourse.getProfessor());
			
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
	
	public boolean deleteCourse(String tempCourseNumber) {
		
		Connection conn = getConnection();
		
		String deleteSQL = "delete from course where courseNumber = ?";
		
		PreparedStatement pstmt = null;
		
		try {
			
			pstmt = conn.prepareStatement(deleteSQL);
			pstmt.setString(1, tempCourseNumber);
			
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
	
	/*
	 * This method replaces name and professor of course with what users want to change
	 * When users fill out mistakenly the information of courses, they can modify them
	 */
	public boolean updateCourse(Course course) {
		
		Connection conn = getConnection();
		
		String updateSQL = "update COURSE set courseName = ? , professor = ? ,studentCount = ?, major = ?, unit = ?"
				+ " where courseNumber = ?";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(updateSQL);
			
			pstmt.setString(1, course.getCourseName());
			pstmt.setString(2, course.getProfessor());
			pstmt.setInt(3, course.getStudentCount());
			pstmt.setString(4, course.getMajor());
			pstmt.setInt(5, course.getUnit());
			pstmt.setString(6, course.getCourseNumber());
			
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
	 * 교원명으로 검색, search course with professor
	 * @param tempProfessor
	 * @return course included tempProfessor
	 */
	public ArrayList<Course> searchCourseProfessor(String tempProfessor) {
		
		Connection conn = getConnection();
		ArrayList<Course> courseList = new ArrayList<Course>();
		
		String searchSQL = "Select * from COURSE where professor IN "
						+ "(SELECT userId FROM USER WHERE USERTYPE = 1 AND USERNAME LIKE '%" + tempProfessor + "%')";
		
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(searchSQL);
			
			while(rs.next()) {
				Course course= new Course();
				course.setCourseNumber(rs.getString("courseNumber"));
				course.setCourseName(rs.getString("courseName"));
				course.setStudentCount(rs.getInt("studentCount"));
				course.setMajor(rs.getString("major"));
				course.setProfessor(rs.getString("professor"));
				course.setUnit(rs.getInt("unit"));
				
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
	
	/**
	 * 교수 id 로 현재 교수가 개설중인 course 목록을 가져오는 함수
	 * @param professorId
	 * @return
	 */
	public ArrayList<Course> searchProfessorCourse(String professorId) {
		
		Connection conn = getConnection();
		ArrayList<Course> courseList = new ArrayList<Course>();
		
		String searchSQL = "Select * from COURSE where professor = '" + professorId + "'";
		
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(searchSQL);
			
			while(rs.next()) {
				
				Course course= new Course();
				
				course.setCourseNumber(rs.getString("courseNumber"));
				course.setCourseName(rs.getString("courseName"));
				course.setStudentCount(rs.getInt("studentCount"));
				course.setMajor(rs.getString("major"));
				course.setProfessor(rs.getString("professor"));
				course.setUnit(rs.getInt("unit"));
				
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
	
	/**
	 * 학수번호로 검색, search course with courseNumber
	 * @param tempCourseNumber
	 * @return course included tempCourseNumber
	 */
	public ArrayList<Course> searchCourseNumber(String tempCourseNumber) {
		
		Connection conn = getConnection();

		Course course= new Course();
		ArrayList<Course> courseList = new ArrayList<Course>();
		
		String searchSQL = "Select * from COURSE where courseNumber LIKE '%" + tempCourseNumber + "%'";
		
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(searchSQL);
			
			while(rs.next()) {
				
				course.setCourseNumber(rs.getString("courseNumber"));
				course.setCourseName(rs.getString("courseName"));
				course.setStudentCount(rs.getInt("studentCount"));
				course.setMajor(rs.getString("major"));
				course.setProfessor(rs.getString("professor"));
				course.setUnit(rs.getInt("unit"));
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
	
	/**
	 * 강의명으로 검색, search course with courseName
	 * @param tempCourseName
	 * @return course included tempCourseName
	 */
	public ArrayList<Course> searchCourseName(String tempCourseName) {
		
		Connection conn = getConnection();
		
		ArrayList<Course> courseList = new ArrayList<Course>();
		
		String searchSQL = "Select * from course where courseName LIKE '%" + tempCourseName + "%'";
		
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(searchSQL);
			
			while(rs.next()) {
				Course course= new Course();
				course.setCourseNumber(rs.getString("courseNumber"));
				course.setCourseName(rs.getString("courseName"));
				course.setStudentCount(rs.getInt("studentCount"));
				course.setMajor(rs.getString("major"));
				course.setProfessor(rs.getString("professor"));
				course.setUnit(rs.getInt("unit"));
				
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
	
	/**
	 * 모든 강의 검색, search all courses
	 * @return all course
	 */
	public ArrayList<Course> searchAllCourse() {
		
		Connection conn = getConnection();
		
		ArrayList<Course> courseList = new ArrayList<Course>();
	
		String searchSQL = "Select * from COURSE";
		
		Statement stmt = null;
		
		try	{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(searchSQL);
			
			while(rs.next()) {
				
				Course course= new Course();
				course.setCourseNumber(rs.getString("courseNumber"));
				course.setCourseName(rs.getString("courseName"));
				course.setStudentCount(rs.getInt("studentCount"));
				course.setMajor(rs.getString("major"));
				course.setProfessor(rs.getString("professor"));
				course.setUnit(rs.getInt("unit"));
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
}
