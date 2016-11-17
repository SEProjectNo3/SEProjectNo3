package com.active.controller;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ExamController
 */
@WebServlet("/ExamController")
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public boolean insertExam(String tempLectureId)
	{
		
	}
	public boolean deleteExam(String tempLectureId, int tempExamNo)
	{
		
	}
	public boolean updateExam(String tempLectureId, int tempExamNo)
	{
		
	}
	public Exam searchExam(String tempLectureId, int tempExamNo)
	{
		
	}
	public ArrayList<Exam> searchAllExam(String tempLectureId)
	{
		
	}
	public int markingExam(String tempLectureId, int tempExamNo)
	{
		
	}
	public boolean setScoreExam(String tempLectureId, String tempUserId, int tempScore)
	{
		
	}
}
