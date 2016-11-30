package com.active.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.active.model.Quiz;
import com.active.model.QuizChoice;


/*
 * Quiz DB에 접근하기 위한 클래스
 */
public class QuizDAO {
	private static QuizDAO quizDao;
	private static ResourceBundle bundle;

	//디비 정보를 config/jdbc에서 찾아옴
	static {
		bundle = ResourceBundle.getBundle("config/jdbc");
	}

	//싱글톤 처리 방식
	public static QuizDAO getInstance() { 
		if (quizDao == null)
			quizDao = new QuizDAO();
		return quizDao;
	}

	private QuizDAO() {
		try {
			// DriverManager 객체로부터 Connection 객체를 얻어옴
			Class.forName(bundle.getString("driver"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Connection getConnection() {
		try {
			// DriverManager를 통해 url,user_id,user_pwd를 받아와 처리
			Connection conn = DriverManager.getConnection(bundle.getString("url"), bundle.getString("user_id"),
					bundle.getString("user_pwd"));
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	//DB connection을 닫는 메소드
	private void closeConnection(Connection conn) {
		try {
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//퀴즈 삽입 메소드
	public int insertQuiz(Quiz quiz) {
		Connection conn = getConnection();

		PreparedStatement pstmt = null;

		String query = "insert into quiz (question, quizTime, answer, explanation, lectureId)"
				+ " values(?, ?, ?, ?, ?)";

		try {
			// DB connection
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, quiz.getQuestion());
			pstmt.setString(2, quiz.getQuizTime());
			pstmt.setInt(3, quiz.getAnswer());
			pstmt.setString(4, quiz.getExplanation());
			pstmt.setString(5, quiz.getLectureId());

			int res = pstmt.executeUpdate();
			
			if(res>0){
				System.out.println("insertQuiz res suc");
				return getQuizNo(quiz.getQuizTime(),quiz.getLectureId());
			}
				
			else {
				System.out.println("insertQuiz res fail");
				return -1;
			}
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}

	/*
	 * 퀴즈 팝업시간과 강의 정보를 통해
	 * quizNo의 값을 반환해줌
	 */
	public int getQuizNo(String quizTime, String lectureId) {
		Connection conn = getConnection();

		String query = "select quizNo from quiz where quizTime = ? and lectureId = ?";

		PreparedStatement pstmt = null;

		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, quizTime);
			pstmt.setString(2, lectureId);
			
			rs = pstmt.executeQuery();
	
			if(rs.next()){
				return rs.getInt("quizNo");
			}
			else{
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("got no quizNo in try-catch");
			return -1;
		} finally {
			closeConnection(conn);
		}
	}
	
	public ArrayList<Integer> getQuizNoList(String lectureId) {
		Connection conn = getConnection();

		ArrayList<Integer> getQuizNoList = new ArrayList<Integer>();

		String query = "select quizNo from quiz where lectureId = '" + lectureId + "'";

		Statement stmt = null;

		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(query);
	
			while (rs.next()) {
				int quizNo;

				quizNo = rs.getInt("quizNo");
		
				getQuizNoList.add(quizNo);
			}
			
			return getQuizNoList;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("got no quizNo in try-catch");
			return null;
		} finally {
			closeConnection(conn);
		}
	}
	
	//퀴즈 삭제하는 메소드
	public boolean deleteQuiz(int quizNo) {
		Connection conn = getConnection();

		String query = "delete from quiz where quizNo = ?";

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, quizNo);

			int result = pstmt.executeUpdate();

			if (result > 0)
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

	//퀴즈 수정해주는 메소드
	public boolean updateQuiz(Quiz quiz) {
		Connection conn = getConnection();

		String query = "update quiz set question = ?, quizTime = ?, "
				+ "answer = ?, explanation = ? where quizNo = ? and lectureId = ?";
		
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, quiz.getQuestion());
			pstmt.setString(2, quiz.getQuizTime());
			pstmt.setInt(3, quiz.getAnswer());
			pstmt.setString(4, quiz.getExplanation());
			pstmt.setInt(5, quiz.getQuizNo());
			pstmt.setString(6, quiz.getLectureId());
			
			int result = pstmt.executeUpdate();
			
			if (result > 0)
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

	//강의에 존재하는 모든 퀴즈 리스트를 반환하는 메소드
	public ArrayList<Quiz> searchQuizList(String lectureId) {
		Connection conn = getConnection();

		ArrayList<Quiz> quizList = new ArrayList<Quiz>();

		String query = "select * from quiz where lectureId = '" + lectureId + "'";
		
		Statement stmt = null;

		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				Quiz quiz = new Quiz();

				quiz.setQuizNo(rs.getInt("quizNo"));
				quiz.setQuestion(rs.getString("question"));
				quiz.setQuizTime(rs.getString("quizTime"));
				quiz.setAnswer(rs.getInt("answer"));
				quiz.setExplanation(rs.getString("explanation"));
				quiz.setLectureId(rs.getString("lectureId"));

				quizList.add(quiz);
			}

			return quizList;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			closeConnection(conn);
		}
	}

	//퀴즈 번호를 통해 하나의 퀴즈 문제를 반환하는 메소드
	public Quiz searchQuiz(int quizNo) {
		Connection conn = getConnection();

		String query = "select * from quiz where quizNo = ?";

		Quiz quiz = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, quizNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				quiz = new Quiz();

				quiz.setQuizNo(rs.getInt("quizNo"));
				quiz.setQuestion(rs.getString("question"));
				quiz.setQuizTime(rs.getString("quizTime"));
				quiz.setAnswer(rs.getInt("answer"));
				quiz.setExplanation(rs.getString("explanation"));
				quiz.setLectureId(rs.getString("lectureId"));
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			closeConnection(conn);
		}

		return quiz;
	}

	//DB에 저장된 모든 퀴즈 리스트를 반환해주는 메소드
	public ArrayList<Quiz> searchQuizListAll() {
		Connection conn = getConnection();

		ArrayList<Quiz> quizList = new ArrayList<Quiz>();

		String query = "select * from quiz ";

		Statement stmt = null;

		ResultSet rs = null;

		try {

			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				Quiz quiz = new Quiz();

				quiz.setQuizNo(rs.getInt("quizNo"));
				quiz.setQuestion(rs.getString("question"));
				quiz.setQuizTime(rs.getString("quizTime"));
				quiz.setAnswer(rs.getInt("answer"));
				quiz.setExplanation(rs.getString("explanation"));
				quiz.setLectureId(rs.getString("lectureId"));

				quizList.add(quiz);
			}

			return quizList;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			closeConnection(conn);
		}
	}
}
