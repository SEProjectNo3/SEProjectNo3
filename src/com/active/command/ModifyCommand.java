package com.active.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.active.dao.QuestionDao;


public class ModifyCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String bId = request.getParameter("bId");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		
		QuestionDao  dao = new QuestionDao();
		dao.modify(bId, bName, bTitle, bContent);
	}

}
