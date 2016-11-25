package com.active.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuizCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		//GET ID AND PASSWORD 
		int quizNo = Integer.parseInt(request.getParameter("quizNo"));
		String lectureId = request.getParameter("lectureId;");
		String question = request.getParameter("question;");
		int answer = Integer.parseInt(request.getParameter("answer"));
		ArrayList<String> choice;
		 
		String userid = request.getParameter("id");
		
		String lectureId;
		String  question;
		 int answer;
		 ArrayList<String> choice;
		 Date time;
		//DAO -> SEARCH USER
		UserDao dao = new UserDao();	//getInstance로 해야해 
		UserDto dto = dao.searchUser(userid, password);		
		request.setAttribute("mainAfterLogin", dto);
	}
}

