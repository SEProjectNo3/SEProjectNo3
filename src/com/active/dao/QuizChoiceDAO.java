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
 * 퀴즈 보기(1,2,3,4)에 대한 클래스
 */
public class QuizChoiceDAO {
	private static QuizChoiceDAO quizChoiceDao;
	private static ResourceBundle bundle;

	//디비 정보를 config/jdbc에서 찾아옴
	static {
		bundle = ResourceBundle.getBundle("config/jdbc");
	}
	
	public static synchronized QuizChoiceDAO getInstance() {
		if (quizChoiceDao == null)
			quizChoiceDao = new QuizChoiceDAO();

		return quizChoiceDao;
	}

	private QuizChoiceDAO() {
		try {
			// DriverManager에 등록
			Class.forName(bundle.getString("driver"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Connection getConnection() {
		try {
			// DriverManager 객체로부터 Connection 객체를 얻어옴
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

	//퀴즈 보기 삽입 메소드
	public boolean insertQuizChoice(QuizChoice quizChoice) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String query = "insert into quizchoice (choiceNumber, quizNo, answer)" + " values (?, ?, ?)";

		try {
			// DB connection
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, quizChoice.getChoiceNumber());
			pstmt.setInt(2, quizChoice.getQuizNo());
			pstmt.setString(3, quizChoice.getAnswer());

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

	//퀴즈 보기 삭제하는 메소드
	public boolean deleteQuizChoice(int choiceNumber, int quizNo) {
		// quizNo & choiceNumber primary key
		Connection conn = getConnection();

		String query = "delete from quizchoice where choiceNumber = ? and quizNo = ?";

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, choiceNumber);
			pstmt.setInt(2, quizNo);

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

	//퀴즈 보기 수정하는 메소드
	public boolean updateQuizChoice(QuizChoice quizChoice) {

		Connection conn = getConnection();

		String query = "update quizchoice set answer = ? where choiceNumber = ? and quizNo = ? ";

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, quizChoice.getAnswer());
			pstmt.setInt(2, quizChoice.getChoiceNumber());
			pstmt.setInt(3, quizChoice.getQuizNo());

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

	//퀴즈번호에 따른 보기 찾는 메소드
	public QuizChoice searchQuizChoice(int quizNo) {

		Connection conn = getConnection();

		String query = "select * from quizchoice where quizNo = ?";

		QuizChoice quizChoice = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, quizNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				quizChoice = new QuizChoice();

				quizChoice.setChoiceNumber(rs.getInt("choiceNumber"));
				quizChoice.setQuizNo(rs.getInt("quizNo"));
				quizChoice.setAnswer(rs.getString("answer"));
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			closeConnection(conn);
		}

		return quizChoice;
	}

	//퀴즈 번호를 통해 퀴즈 보기 리스트를 찾는 메소드
	public ArrayList<QuizChoice> searchQuizChoiceList(int quizNo) {
		Connection conn = getConnection();

		ArrayList<QuizChoice> quizChoiceList = new ArrayList<QuizChoice>();

		String query = "select * from quizchoice where quizNo = " + quizNo;

		Statement stmt = null;

		ResultSet rs = null;

		System.out.println("searchQuizChoiceList");

		try {
			stmt = conn.createStatement();

			rs = stmt.executeQuery(query);

			while (rs.next()) {
				QuizChoice quizChoice = new QuizChoice();

				quizChoice.setChoiceNumber(rs.getInt("choiceNumber"));
				quizChoice.setQuizNo(rs.getInt("quizNo"));
				quizChoice.setAnswer(rs.getString("answer"));

				quizChoiceList.add(quizChoice);
				System.out.println("add list");
			}

			return quizChoiceList;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			closeConnection(conn);
		}
	}
}
