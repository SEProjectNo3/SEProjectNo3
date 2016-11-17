package com.active.controller;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReviewController
 */
@WebServlet("/ReviewController")
public class ReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewController() {
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
	
	public boolean insertReview(String tempCourseId, String tempUserId, int tempRate)
	{
		
	}
	public boolean deleteReview(String tempCourseId, String tempUserId, int tempReviewNo)
	{
		
	}
	public Review searchReview(String courseId, int tempReviewNo)
	{
		
	}
	public ArrayList<Review> searchReviews(String tempCourseId, String tempUserId)
	{
		
	}
	public ArrayList<Review> searchAllReview(String tempCourseId)
	{
		
	}
	public boolean updateReview(String tempCourseId, String tempUserId, String tempContent, int tempRate)
	{
		
	}
}
