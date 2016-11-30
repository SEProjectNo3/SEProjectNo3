package com.active.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.active.dao.ExamDAO;
import com.active.model.Exam;

/**
 * Servlet implementation class ExamController
 */
@WebServlet("/Exam.do")
public class ExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExamController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cmd = request.getParameter("cmd");
		
		RequestDispatcher rd = null;
	
		if (cmd == null) { // 문제 삽입 요청 -> examNo를 생성해서 해당 jsp 파일로 보냄
			
			// request.getParameter("courseNumber");
			// request.getParameter("chapter");
			
			int examNo = insertExam(request, response);
			
			request.setAttribute("examNo", examNo);
			
			rd = request.getRequestDispatcher("exam-file.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cmd = request.getParameter("cmd");
		
		if (cmd.equals("add_question")) {
			
			
		}
	}
	
	public int insertExam(HttpServletRequest request, HttpServletResponse response) {
		
		ExamDAO examDao = ExamDAO.getInstance();
		
		String courseNumber = request.getParameter("courseNumber");
		int chapter = Integer.parseInt(request.getParameter("chapter"));
		
		boolean res = examDao.insertExam(courseNumber, chapter);
		
		Exam exam = new Exam();
		exam.setCourseNumber(courseNumber);
		exam.setChapter(chapter);
		
		if (res) 
			return examDao.getExamNumber(exam); 
		else 
			return -1;
	}

	public boolean insertQuestion(HttpServletRequest request, HttpServletResponse response) {
		
		ExamDAO examDao = ExamDAO.getInstance();
		
		int questionNo = Integer.parseInt(request.getParameter("questionNo"));
		String question = request.getParameter("question");
		
		String choices[] = request.getParameterValues("choice");
		
		// answer 받기
	}
}
