package com.active.controller;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CourseController
 */
@WebServlet("/CourseController")
public class CourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseController() {
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
	
	public Course searchCourseNumber(String tempCourseId)
	{
		
	}
	public ArrayList<String> searchCourseProfessor(String tempProfessor)
	{
		
	}
	public ArrayList<String> searchCourseName(String tempCourseName)
	{
		
	}
	public ArrayList<String> searchAllCourses()
	{
		
	}
	public Lecture searchLecture(String tempLectureId)
	{
	
	}
	public boolean insertCourse(String tempCourseId, String tmepUserId)
	{
		
	}
	public boolean deleteCourse(String tempCourseId, String tempUserId)
	{
		
	}

}
