package com.active.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.active.dao.AnswerDao;

public class ReplyCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		//데이터 추출
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String questionNo = request.getParameter("questionNo");
					
		//답변작성
		AnswerDao dao = new AnswerDao();
		dao.reply(writer, content, questionNo);
	}

}
