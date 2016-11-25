package com.active.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.active.command.Command;
import com.active.command.LoginCheckCommand;

/**
 * Servlet implementation class QuizController
 */
@WebServlet("/QuizController.do")
public class QuizController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuizController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	// ACTIONDO
	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("actionDo");

		request.setCharacterEncoding("UTF-8");

		String viewPage = null;
		Command command = null;

		// PATH CHECK
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());

		// CALL COMMAND
		if (com.equals("/QuizController.do")) {
			command = new LoginCheckCommand();
			command.execute(request, response);
			viewPage = "mainAfterLogin.jsp";
		}

		// MOVE PAGE
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);

	}

	public Quiz searchQuiz(Date tempTime) {

	}

	public boolean checkAnswer(int tempQuizNo, int tempAnswer) {

	}

	public boolean insertQuiz(String tempLectureId) {

	}

	public boolean deleteQuiz(String tempLectureId) {

	}

	public boolean updateQuiz(String tempLectureId) {

	}

}
