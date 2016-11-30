package com.active.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.active.command.Command;
import com.active.command.ContentCommand;
import com.active.command.DeleteCommand;
import com.active.command.ListCommand;
import com.active.command.ModifyCommand;
import com.active.command.ReplyCommand;
import com.active.command.WriteCommand;

@WebServlet("*.do")
public class QnAController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*************************************************** 생성자 ***************************************************/
	public QnAController() {
		super();
	}

	/*************************************************** doGet *************************************************/
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
	}

	/*************************************************** doPost ***********************************************/
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);

	}

	/***********************************************  command 호출  *******************************************/
	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 인코딩, 변수 선언
		request.setCharacterEncoding("EUC-KR");
		String viewPage = null;
		Command command = null;

		// 경로 자르기
		String uri = request.getRequestURI();	//       /sf_JunHee/modify.do
		String conPath = request.getContextPath();	//   /sf_JunHee
		String com = uri.substring(conPath.length());
		System.out.println(uri);
		System.out.println(conPath);
		
		System.out.println("---------------------------");
		System.out.println(com);
		System.out.println("---------------------------");
		
		
		// 질문 작성 페이지로 이동
		if (com.equals("/write_view.do")) {
			viewPage = "write_view.jsp";
		}

		// 질문 작성하고 입력 버튼 누르면 입력 버튼 누르면 질문 리스트 페이지가 보임
		else if (com.equals("/write.do")) {
			command = new WriteCommand();
			command.execute(request, response);
			viewPage = "question_list.do";
		}
		// 질문 목록 페이지
		else if (com.equals("/question_list.do")) {
			command = new ListCommand();
			command.execute(request, response);
			viewPage = "question_list.jsp";
		}

		// 질문 수정
		else if (com.equals("/modify.do")) {
			command = new ModifyCommand();
			command.execute(request, response);
			viewPage = "list.do";
		}

		// 질문의 내용 보기
		else if (com.equals("/content_view.do")) {
			command = new ContentCommand();
			command.execute(request, response);
			viewPage = "content_view.jsp";
		}

		// 질문 삭제하기
		else if (com.equals("/delete.do")) {
			command = new DeleteCommand();
			command.execute(request, response);
			viewPage = "question_list.do";
		}
		
		else if (com.equals("/reply.do")) {
			command = new ReplyCommand();
			command.execute(request, response);
			viewPage = "content_view.do";
		}
		/* 질의 응답
		if (com.equals("/reply_view.do")) {
			command = new ReplyViewCommand();
			command.execute(request, response);
			viewPage = "reply_view.jsp";
		}

		else if (com.equals("/reply.do")) {
			command = new BReplyCommand();
			command.execute(request, response);
			viewPage = "list.do";
		}
		 */
		
		// 페이지 이동
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);

	}

}
