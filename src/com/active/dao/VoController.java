package com.active.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.active.constant.Constants;
import com.active.model.Course;
import com.active.model.CourseVO;

public class VoController {

	private static VoController voDao;
	private static ResourceBundle bundle;
	
	static {
		bundle = ResourceBundle.getBundle("config/jdbc");
	}
	
	public static synchronized VoController getInstance() {
		if(voDao == null)
			 voDao = new VoController();
		
		return voDao;	
	}
	
	private VoController() {
		try {
			//DriverManager�� ���
			Class.forName(bundle.getString("driver"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private Connection getConnection() {
		try {
			// DriverManager ��ü�κ��� Connection ��ü�� ���´�.
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
	
	/*
	 * main ȭ�鿡�� ���� �˻� ���� �޼ҵ�
	 */
	
	/**
	 * ���������� �˻�, search course with professor
	 * @param tempProfessor
	 * @return course included tempProfessor
	 */
	public ArrayList<CourseVO> searchCourseProfessor(String tempProfessor) {
		
		Connection conn = getConnection();
		ArrayList<CourseVO> courseVOList = new ArrayList<CourseVO>();
		
		String searchSQL = "Select * from USER u, COURSE c where u.userId = c.professor "
						+ " AND u.usertype = 1 AND USERNAME LIKE '%" + tempProfessor + "%'";
		
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(searchSQL);
			
			while(rs.next()) {
				
				CourseVO courseVO = new CourseVO();
				
				courseVO.setCourseNumber(rs.getString("courseNumber"));
				courseVO.setCourseName(rs.getString("courseName"));
				courseVO.setStudentCount(rs.getInt("studentCount"));
				courseVO.setMajor(rs.getString("major"));
				courseVO.setExplanation(rs.getString("explanation"));
				courseVO.setProfessor(rs.getString("professor"));
				courseVO.setProfessorName(rs.getString("userName"));
				courseVO.setUnit(rs.getInt("unit"));
				
				courseVOList.add(courseVO);
			}
			
			searchCourseRate(courseVOList);
			return courseVOList;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			closeConnection(conn);
		}
	}
	
	/**
	 * �м���ȣ�� �˻�, search course with courseNumber
	 * @param tempCourseNumber
	 * @return course included tempCourseNumber
	 */
	public ArrayList<CourseVO> searchCourseNumber(String tempCourseNumber) {
		
		Connection conn = getConnection();

		ArrayList<CourseVO> courseVOList = new ArrayList<CourseVO>();
		
		String searchSQL = "Select * from USER u, COURSE c where u.usertype = 1 AND u.userId = c.professor "
							+ "AND c.courseNumber LIKE '%" + tempCourseNumber + "%'";
		
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(searchSQL);
			
			while(rs.next()) {
				
				CourseVO courseVO = new CourseVO();
				
				courseVO.setCourseNumber(rs.getString("courseNumber"));
				courseVO.setCourseName(rs.getString("courseName"));
				courseVO.setStudentCount(rs.getInt("studentCount"));
				courseVO.setMajor(rs.getString("major"));
				courseVO.setExplanation(rs.getString("explanation"));
				courseVO.setProfessor(rs.getString("professor"));
				courseVO.setProfessorName(rs.getString("userName"));
				courseVO.setUnit(rs.getInt("unit"));
				
				courseVOList.add(courseVO);
			}
			
			searchCourseRate(courseVOList);
			return courseVOList;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
			
		} finally {
			closeConnection(conn);
		}
	}
	
	/**
	 * ���Ǹ����� �˻�, search course with courseName
	 * @param tempCourseName
	 * @return course included tempCourseName
	 */
	public ArrayList<CourseVO> searchCourseName(String tempCourseName) {
		
		Connection conn = getConnection();
		
		ArrayList<CourseVO> courseVOList = new ArrayList<CourseVO>();
		
		String searchSQL = "Select * from USER u, COURSE c where u.usertype = 1 AND "
							+ "u.userId = c.professor AND c.courseName LIKE '%" + tempCourseName + "%'";
		
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(searchSQL);
			
			while(rs.next()) {
				CourseVO courseVO = new CourseVO();
				
				courseVO.setCourseNumber(rs.getString("courseNumber"));
				courseVO.setCourseName(rs.getString("courseName"));
				courseVO.setStudentCount(rs.getInt("studentCount"));
				courseVO.setMajor(rs.getString("major"));
				courseVO.setExplanation(rs.getString("explanation"));
				courseVO.setProfessor(rs.getString("professor"));
				courseVO.setProfessorName(rs.getString("userName"));
				courseVO.setUnit(rs.getInt("unit"));
				
				courseVOList.add(courseVO);
			}
			
			searchCourseRate(courseVOList);
			return courseVOList;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			closeConnection(conn);
		}
	}
	
	/**
	 * ��� ���� �˻�, search all courses
	 * @return all course
	 */
	public ArrayList<CourseVO> searchAllCourse() {
		
		Connection conn = getConnection();
		
		ArrayList<CourseVO> courseVOList = new ArrayList<CourseVO>();
	
		String searchSQL = "Select * from USER u, COURSE c WHERE u.userId = c.professor AND u.usertype = 1";
		
		Statement stmt = null;
		
		try	{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(searchSQL);
			
			while(rs.next()) {
				
				CourseVO courseVO = new CourseVO();
				
				courseVO.setCourseNumber(rs.getString("courseNumber"));
				courseVO.setCourseName(rs.getString("courseName"));
				courseVO.setStudentCount(rs.getInt("studentCount"));
				courseVO.setMajor(rs.getString("major"));
				courseVO.setExplanation(rs.getString("explanation"));
				courseVO.setProfessor(rs.getString("professor"));
				courseVO.setProfessorName(rs.getString("userName"));
				courseVO.setUnit(rs.getInt("unit"));
				
				courseVOList.add(courseVO);
			}
			
			searchCourseRate(courseVOList);
			return courseVOList;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
			
		} finally {
			closeConnection(conn);
		}
	}
	
	/**
	 * �ش� ���ǿ� �ش��ϴ� ������ ����� ���ϴ� �Լ�
	 * @param list (courseVOList)
	 */
	public void searchCourseRate(ArrayList<CourseVO> list) {
		
		Connection conn = getConnection();
		
		String searchSQL = "";
		Statement stmt = null;
		
		try {
			
			for (int i = 0; i<list.size(); i++) {
			
				String courseNumber = list.get(i).getCourseNumber();
				
				// �� ���ǿ� ���� �й��� �����ϹǷ� (ex. ����Ʈ������� 01, ����Ʈ������� 02)
				// courseNumber �� ã��, ���� �й��� ��Ÿ���ִ� 01, 02 �� �����ؾ� ��
				// split �� ���� ���ǹ�ȣ�θ� rate �� ã��
				String course[] = courseNumber.split("-");
				
				searchSQL = "Select AVG(rate) from review where courseNumber LIKE '%" + course[0] + "%'";
				
				stmt = conn.createStatement();
				
				ResultSet rs = stmt.executeQuery(searchSQL);
				
				if(rs.next()) {
					list.get(i).setRate(rs.getInt("AVG(rate)"));
				} else {
					list.get(i).setRate(Constants.MAX_RATE);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
	}
}
