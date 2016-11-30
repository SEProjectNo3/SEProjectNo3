package com.active.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.active.dao.QuestionDao;

public class WriteCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		//request에 저장된 데이터 가져오기
		String question = request.getParameter("question");
		String writer = request.getParameter("writer");
		String courseNo = request.getParameter("courseNumber");
		String questionTitle = request.getParameter("questionTitle");
		
		//DAO -> WRITE on DB
		QuestionDao dao = new QuestionDao();
		dao.insert(question, writer, courseNo, questionTitle);
	}
}

