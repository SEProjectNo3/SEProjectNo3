package com.active.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.active.dao.ReviewDAO;
import com.active.model.Review;
import com.active.model.User;
import javax.servlet.RequestDispatcher;
/**
 * Servlet implementation class ReviewController
 */
@WebServlet("/Review.do")
public class ReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewController() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		
		if(cmd.equals("evaluation")) {
			searchReview(request,response);
		}
		
		else if(cmd.equals("review_del_proc"))
		{
			deleteReview(request,response);
			searchReview(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		
		if(cmd.equals("review_proc")) 
		{
			insertReview(request, response);
			//searchReview(request, response);
		}
		else if(cmd.equals("review_modify_proc"))
		{
			updateReview(request,response);
			searchReview(request,response);
		}
	}

	/**
	 * 강의 평가 추가 부분
	 * @param request
	 * @param response
	 * @return
	 */
	public boolean insertReview(HttpServletRequest request, HttpServletResponse response) 
	{	
		/*
		 * Insert review information passed by application users from web to Database
		 * If succeeded insertion of review information into DB, it would return 'true' , otherwise 'false'
		 */
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		String courseNumber = request.getParameter("course_number");
		String content = request.getParameter("content");
		int rate = Integer.parseInt(request.getParameter("rate"));
		
		ReviewDAO reviewDao = ReviewDAO.getInstance();
		Review review = new Review();
		
		review.setContent(content);
		review.setRate(rate);
		review.setWriter(user.getUserId()); // Please write evaluator userId
		review.setCourseNumber(courseNumber);
		
		boolean res = reviewDao.insertReview(review);
		
		//RequestDispatcher rd = null;
		//rd = request.getRequestDispatcher("/Review.do?cmd=evaluation&course_number=" + courseNumber);
		
		try {
			//rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return res;
	}
	
	public boolean deleteReview(HttpServletRequest request, HttpServletResponse response) 
	{	
		/*
		 * Delete review information selected by application users from web in Database
		 * If succeeded deletion of review information into DB, it would return 'true' , otherwise 'false'
		 */
		
		ReviewDAO reviewDao = ReviewDAO.getInstance();
		
		int reviewNo = Integer.parseInt(request.getParameter("reviewId"));
		String reviewWriter = request.getParameter("reviewWriter");
		
		System.out.println(reviewNo);
		System.out.println(reviewWriter);
		
		boolean res = reviewDao.deleteReview(reviewNo);
	
		//RequestDispatcher rd = null;
		//rd = request.getRequestDispatcher("lecture-evaluation.jsp");
		
		//try {
		//	rd.forward(request, response);
		//	return res;
		//} catch (Exception e) {
		//	e.printStackTrace();
		//	return false;
		//}
		return res;
	}
	
	public boolean updateReview(HttpServletRequest request, HttpServletResponse response) 
	{	
		/*
		 * Modify review information selected by application users from web in Database
		 * It is two domains that it can modify in database with content and rate(evaluation)
		 * If succeeded modification of review information into DB, it would return 'true' , otherwise 'false'
		 */
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		ReviewDAO reviewDao = ReviewDAO.getInstance();
		
		String content = request.getParameter("content");
		int rate = Integer.parseInt(request.getParameter("rate"));
		
		int reviewNo = Integer.parseInt(request.getParameter("rId"));
		
		//System.out.println(reviewNo);
		
		//int reviewNo = Integer.parseInt(request.getParameter("reviewId"));
		//String content = request.getParameter("reviewContent");
		//int rate = Integer.parseInt(request.getParameter("reviewRate"));
		//String writer = request.getParameter("reviewWriter");
				
		boolean res = reviewDao.updateReview(reviewNo, content, rate);
		
		//RequestDispatcher rd = null;
		//rd = request.getRequestDispatcher("lecture-evaluation.jsp");
		
		//try {
		//	rd.forward(request, response);
			
		//} catch (Exception e) {
		//	e.printStackTrace();
		//}	
		return res;
	}
	
	public void searchReview(HttpServletRequest request, HttpServletResponse response) 
	{	
		/*
		 * Search all of reviews information in the course from Database
		 * If succeeded searching all of reviews information into DB, it would return 'list of review' , otherwise 'null'
		 */
		
		
		ReviewDAO reviewDao = ReviewDAO.getInstance();
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		String courseNumber = request.getParameter("course_number"); //"cse4036-01";
		String course[] = courseNumber.split("-");  // cse4036 과 같은 형태
		
		
		// 강의에 대한 모든 리뷰를 가져오는 부분
		ArrayList<Review> reviewList = new ArrayList<Review>();
		reviewList = reviewDao.searchReview(course[0]);
		
		request.setAttribute("course_number", courseNumber);
		request.setAttribute("review_list",reviewList);
		
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("lecture-evaluation.jsp");
		
		try {
			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public ArrayList<Review> searchWriterReview(HttpServletRequest request, HttpServletResponse response) 
	{	
		/*
		 * Search all of reviews information in the course by userId from Database
		 * If succeeded searching all of reviews information into DB, it would return 'list of review' , otherwise 'null'
		 */
		
		ReviewDAO reviewDao = ReviewDAO.getInstance();
		
		HttpSession session = request.getSession();
		String writer = (String)session.getAttribute("writer");
		
		ArrayList<Review> reviewList = new ArrayList<Review>();
		reviewList = reviewDao.searchReview(writer);
		
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("lecture-evaluation.jsp");
		
		try {
			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		if (reviewList.size() > 0) 
		{
			System.out.println("searching review by writer successes");
			return reviewList;
		} 
		else 
		{
			System.out.println("searching review by writer is failed");
			return null;
		}
	}
}
