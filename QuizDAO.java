package com.active.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.active.dto.QuizDto;

public class QuizDAO {
	private static QuizDAO enrollDao;
	private static ResourceBundle bundle;

	static {
		bundle = ResourceBundle.getBundle("config/jdbc");
	}

	public static synchronized QuizDAO getInstance() {
		if (enrollDao == null)
			enrollDao = new QuizDAO();

		return enrollDao;
	}

	private QuizDAO() {
		try {
			// DriverManager에 등록
			Class.forName(bundle.getString("driver"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Connection getConnection() {
		try {
			// DriverManager 객체로부터 Connection 객체를 얻어온다.
			Connection conn = DriverManager.getConnection(bundle.getString("url"), bundle.getString("user_id"),
					bundle.getString("user_pwd"));
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private void closeConnection(Connection conn) {
		try {
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean insertQuiz(QuizDto quiz) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "insert into question (quizNo, question, quizTime, answer, explanation, lectureId)"
				+ " values (?, ?, ?, ?, ?, ?, ?)";

		try {
			// DB connection
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, quiz.getQuizNo());
			pstmt.setString(2, quiz.getQuestion());
			pstmt.setString(3, quiz.getQuizTime());
			pstmt.setInt(4, quiz.getAnswer());
			pstmt.setString(5, quiz.getExplanation());
			pstmt.setString(6, quiz.getLectureId());

			int result = pstmt.executeUpdate();

			if (result > 0)
				return true;
			else
				return false;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
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

	public boolean deleteQuiz(int quizNo) {
		// 'quizNo' is primary key
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

	public boolean updateQuiz(QuizDto quiz) {

		Connection conn = getConnection();

		String query = "update quiz set question = ?, set quizTime = ?, "
				+ "set answer = ?, set explanation = ?, set lectureId = ? where quizNo = ?";

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(2, quiz.getQuestion());
			pstmt.setString(3, quiz.getQuizTime());
			pstmt.setInt(4, quiz.getAnswer());
			pstmt.setString(5, quiz.getExplanation());
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

	public QuizDto searchQuiz(int quizNo) {

		Connection conn = getConnection();

		String query = "select * from quiz where quizNo = ?";

		QuizDto quiz = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, quizNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				quiz = new QuizDto();

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

	public ArrayList<QuizDto> searchQuizList(String lectureId) {
		Connection conn = getConnection();

		ArrayList<QuizDto> quizList = new ArrayList<QuizDto>();
		QuizDto quiz = new QuizDto();

		String query = "select * from quiz where lectureId = ?";

		Statement stmt = null;

		ResultSet rs = null;

		try {

			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {

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
