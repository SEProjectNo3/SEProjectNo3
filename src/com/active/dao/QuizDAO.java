package com.active.dao;
import java.util.ArrayList;

public class QuizDAO
{
	private static QuizDAO quizDao = new QuizDAO();
	
	private QuizDAO(){
		
	}
	
	public static QuizDAO getInstance()
	{
		if(quizDao == null)
			quizDao = new QuizDAO();
		return quizDao;
	}
	
	public boolean insertQuiz(String tempLectureId, String tempQuestion, int tempAnswer, ArrayList<String> tempChoice)
	{
		
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


