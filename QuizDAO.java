package com.active.dao;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.active.dto.Quiz;
import com.mysql.jdbc.Connection;

public class QuizDAO
{
	private static QuizDAO quizDao = new QuizDAO();
	private static ResourceBundle bundle;
	
	static
	{
		bundle = ResourceBundle.getBundle("config/jdbc");
	}
	private QuizDAO(){
		try
		{
			Class.forName(bundle.getString("driver"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	public static synchronized QuizDAO getInstance()
	{
		if(quizDao == null)
			quizDao = new QuizDAO();
		
		return quizDao;
	}
	
	private Connection getConnection()
	{
		try{
			Connection conn = DriverManager.getConnection(bundle.getString("url"))
					,bundle.getString("user_id"),bundle.getString("user_pwd"));
					return conn;
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	private void closeConnection(Connection conn)
	{
		try{
			if(conn != null)
			{
				conn.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public boolean insertQuiz(String tempLectureId, String tempQuestion, int tempAnswer, ArrayList<String> tempChoice)
	{
		Connection conn = getConnection();
		
	}
	public boolean deleteQuiz(String tempLectureId, int TempQuizNo)
	{
		
	}
	public boolean updateQuiz(String tempLectureId, int tempQuizNo)
	{
		
	}
	public ArrayList<Quiz> searchQuizList(String tempLectureId)
	{
		
	}
	public Quiz searchQuiz(String tempLectureId, int tempQuizNo)
	{
		
	}
}


