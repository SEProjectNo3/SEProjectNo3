package com.active.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.active.controller.AnswerDto;
import com.active.dao.AnswerDao;
import com.active.dao.QuestionDao;
import com.active.model.QuestionDto;



public class ContentCommand implements Command 
{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		//질문
		String questionNo = request.getParameter("questionNo");
		QuestionDao dao = new QuestionDao();
		QuestionDto dto = dao.contentView(questionNo);
		request.setAttribute("content_view", dto);
		
		//답변 
		AnswerDao dao2 = new AnswerDao();
		ArrayList<AnswerDto> dtos = dao2.reply_list(questionNo);
		request.setAttribute("reply_list", dtos);
		
	}

}
