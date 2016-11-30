package com.active.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.active.dao.AnswerDao;

public class ReplyCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		//������ ����
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String questionNo = request.getParameter("questionNo");
					
		//�亯�ۼ�
		AnswerDao dao = new AnswerDao();
		dao.reply(writer, content, questionNo);
	}

}
