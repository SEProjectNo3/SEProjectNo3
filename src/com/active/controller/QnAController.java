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

	/*************************************************** ������ ***************************************************/
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

	/***********************************************  command ȣ��  *******************************************/
	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// ���ڵ�, ���� ����
		request.setCharacterEncoding("EUC-KR");
		String viewPage = null;
		Command command = null;

		// ��� �ڸ���
		String uri = request.getRequestURI();	//       /sf_JunHee/modify.do
		String conPath = request.getContextPath();	//   /sf_JunHee
		String com = uri.substring(conPath.length());
		System.out.println(uri);
		System.out.println(conPath);
		
		System.out.println("---------------------------");
		System.out.println(com);
		System.out.println("---------------------------");
		
		
		// ���� �ۼ� �������� �̵�
		if (com.equals("/write_view.do")) {
			viewPage = "write_view.jsp";
		}

		// ���� �ۼ��ϰ� �Է� ��ư ������ �Է� ��ư ������ ���� ����Ʈ �������� ����
		else if (com.equals("/write.do")) {
			command = new WriteCommand();
			command.execute(request, response);
			viewPage = "question_list.do";
		}
		// ���� ��� ������
		else if (com.equals("/question_list.do")) {
			command = new ListCommand();
			command.execute(request, response);
			viewPage = "question_list.jsp";
		}

		// ���� ����
		else if (com.equals("/modify.do")) {
			command = new ModifyCommand();
			command.execute(request, response);
			viewPage = "list.do";
		}

		// ������ ���� ����
		else if (com.equals("/content_view.do")) {
			command = new ContentCommand();
			command.execute(request, response);
			viewPage = "content_view.jsp";
		}

		// ���� �����ϱ�
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
		/* ���� ����
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
		
		// ������ �̵�
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);

	}

}
