package com.active.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.active.dao.QuestionDao;
import com.active.dto.QuestionDto;

public class ListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
	
		QuestionDao dao = new QuestionDao();
		ArrayList<QuestionDto> dtos = dao.list();
		request.setAttribute("question_list", dtos);
	}

}
