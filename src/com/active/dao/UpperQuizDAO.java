package com.active.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.active.model.Quiz;
import com.active.model.QuizChoice;

public class UpperQuizDAO {
	private static UpperQuizDAO UpperQuizDAO;
	private static ResourceBundle bundle;

	//디비 정보를 config/jdbc에서 찾아옴
	static {
		bundle = ResourceBundle.getBundle("config/jdbc");
	}

	//싱글톤 처리 방식
	public static UpperQuizDAO getInstance() { 
		if (UpperQuizDAO == null)
			UpperQuizDAO = new UpperQuizDAO();
		return UpperQuizDAO;
	}

	private UpperQuizDAO() {
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
	
	public void searchQuizList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//String url = "/Quiz.do";
		String url = "/quiz-professor.jsp";
		
		System.out.println("get in searchQuizList");

		QuizDAO quizDAO = QuizDAO.getInstance();

		QuizChoiceDAO quizChoiceDAO = QuizChoiceDAO.getInstance();
		
		ArrayList<Quiz> quizList = quizDAO.searchQuizList("cse4036-01-01");
		request.setAttribute("quizList", quizList);

		ArrayList<Integer> quizNoList = quizDAO.getQuizNoList("cse4036-01-01");
		
		request.setAttribute("quizNoList", quizNoList);
		
		int i=1;
		for(int quizNo : quizNoList){
			ArrayList<QuizChoice> quizChoiceList = quizChoiceDAO.searchQuizChoiceList(quizNo);
			
			request.setAttribute("quizChoiceList"+i, quizChoiceList);
		}
		

		//RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		//dispatcher.forward(request, response);
	}
}
