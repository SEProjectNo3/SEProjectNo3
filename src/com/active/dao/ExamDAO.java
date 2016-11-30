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
import com.active.model.ExamChoice;
import com.active.model.ExamQuestion;

public class ExamDAO {
	private static ExamDAO examDao;
	private static ResourceBundle bundle;
	
	static {
		bundle = ResourceBundle.getBundle("config/jdbc");
	}
	
	public static synchronized ExamDAO getInstance() {
		if(examDao == null)
			examDao = new ExamDAO();
		
		return examDao;
	}
	
	private ExamDAO() {
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
	
	
	public boolean insertExam(String courseNumber, int chapter) {
		
		Connection conn = getConnection();
		
		String insertSQL = "Insert Into Exam(courseNumber,chapter) Values(?,?)";
		
		PreparedStatement pstmt = null;
		
		try {
			
			pstmt = conn.prepareStatement(insertSQL);
			
			pstmt.setString(1, courseNumber);
			pstmt.setInt(2, chapter);
			
			int res = pstmt.executeUpdate();
			
			if(res>0) 			
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
	
	public boolean insertExamQuestion(Exam exam, ExamQuestion question, ExamChoice answer) {
		
		Connection conn = getConnection();
		
		String quesQuery = "INSERT INTO EXAMQUESTION (examQuestionNo, examNo, courseNumber, question, answer)"
							+ " VALUES (?, ?, ?, ?, ?)";
		
		PreparedStatement pstmt = null;
		
		try {
		
			//int examNo = getExamNumber(exam);
			
			pstmt = conn.prepareStatement(quesQuery);
			
			pstmt.setInt(1, question.getQuestionNo());
			pstmt.setInt(2, examNo);
			pstmt.setString(3, exam.getCourseNumber());
			pstmt.setString(4, question.getQuestion());
			pstmt.setInt(5, question.getAnswer());
			
			int res = pstmt.executeUpdate();
			
			if (res > 0) 
				return true;
			else 
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeConnection(conn);
		}
	}
	
	public int getExamNumber(Exam exam) {
		
		Connection conn = getConnection();
		
		String searchSQL = "SELECT examNo FROM EXAM WHERE COURSENUMBER = ? AND CHAPTER = ?";
		
		PreparedStatement pstmt = null;
		
		try {
			
			pstmt = conn.prepareStatement(searchSQL);
			
			pstmt.setString(1, exam.getCourseNumber());
			pstmt.setInt(2, exam.getChapter());
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) 
				return rs.getInt("examNo");
			else 
				return -1;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			closeConnection(conn);
		}
	}
	
	public boolean insertAnswer(ExamChoice answer, int examNo, int questionNo) {
		
		Connection conn = getConnection();
		
		String insertSQL = "INSERT INTO EXAMCHOICE (examNo, examQuestionNo, examChoiceNo, choice) "
							+ "VALUES (?,?,?,?)";
		
		PreparedStatement pstmt = null;
		
		try {
			
			pstmt = conn.prepareStatement(insertSQL);
			
			pstmt.setInt(1, examNo);
			pstmt.setInt(2, questionNo);
			pstmt.setInt(3, answer.getChoiceNo());
			pstmt.setString(4, answer.getExample());
			
			int res = pstmt.executeUpdate();
			
			if (res > 0)
				return true;
			else 
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally { 
			closeConnection(conn);
		}
	}
		
	public boolean deleteExam(int tempExamNo) {
		
		Connection conn = getConnection();
		
		String deleteSQL = "delete from exam where examNo = ?";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(deleteSQL);
			
			pstmt.setInt(1, tempExamNo);
			
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
	
	public boolean updateExam(int tempExamNo, int tempChapter) {
		
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
	
	public ArrayList<Exam> searchExam(int examNo) {
		
		Connection conn = getConnection();
		
		String searchSQL = "SELECT * FROM EXAM WHERE EXAMNO = " + examNo;
		
		Statement stmt = null;
		
		try {
			
		}
	}
	
	public ArrayList<Exam> searchAllExams(String tempLectureId, int tempNumber) {
	
		Connection conn = getConnection();
		
		ArrayList<Exam> examList = new ArrayList<Exam>();
	
		String searchSQL = "Select * from enroll where lectureId = '" + tempLectureId + "'";
		
		Statement stmt = null;
		
		try {
			
			stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(searchSQL);
			
			while(rSet.next()) {
				
				Exam exam = new Exam()
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
	
	public markingExam(String tempLectureId, int tempExamNo) {
		
	}
	
}
