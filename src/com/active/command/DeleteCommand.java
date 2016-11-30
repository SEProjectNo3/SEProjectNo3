package com.active.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.active.dao.QuestionDao;

public class DeleteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	
		String questionNo = request.getParameter("questionNo");
		QuestionDao dao = new QuestionDao();
		dao.delete(questionNo);
	}

}
