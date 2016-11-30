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
 * ���� ����(1,2,3,4)�� ���� Ŭ����
 */
public class QuizChoiceDAO {
	private static QuizChoiceDAO quizChoiceDao;
	private static ResourceBundle bundle;

	//��� ������ config/jdbc���� ã�ƿ�
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
			// DriverManager�� ���
			Class.forName(bundle.getString("driver"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Connection getConnection() {
		try {
			// DriverManager ��ü�κ��� Connection ��ü�� ����
			Connection conn = DriverManager.getConnection(bundle.getString("url"), bundle.getString("user_id"),
					bundle.getString("user_pwd"));
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	//DB connection�� �ݴ� �޼ҵ�
	private void closeConnection(Connection conn) {
		try {
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//���� ���� ���� �޼ҵ�
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

	//���� ���� �����ϴ� �޼ҵ�
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

	//���� ���� �����ϴ� �޼ҵ�
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

	//�����ȣ�� ���� ���� ã�� �޼ҵ�
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

	//���� ��ȣ�� ���� ���� ���� ����Ʈ�� ã�� �޼ҵ�
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
