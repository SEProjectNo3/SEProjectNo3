package com.active.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.active.dao.ReviewDAO;
import com.active.model.Course;
import com.active.model.Review;
import com.active.model.User;

/**
 * Servlet implementation class ReviewController
 */
@WebServlet("/Review.do")
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
		
		String cmd = request.getParameter("cmd");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String cmd = request.getParameter("cmd");
		
		if(cmd.equals("review_proc")) {
			insertReview(request, response);
		}
	}

	/**
	 * 강의 평가 추가 부분
	 * @param request
	 * @param response
	 * @return
	 */
	public boolean insertReview(HttpServletRequest request, HttpServletResponse response) {
		
		/*
		 * Insert review information passed by application users from web to Database
		 * If succeeded insertion of review information into DB, it would return 'true' , otherwise 'false'
		 */
		
		ReviewDAO reviewDao = ReviewDAO.getInstance();
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		String content = request.getParameter("content");
		int rate = Integer.parseInt(request.getParameter("rate"));
		
		Review review = new Review();
		
		review.setContent(content);
		review.setRate(rate);
		review.setWriter(user.getUserId());
		
		// 학수번호 얻어오는 작업 필요
		review.setCourseNumber("cse4036-01");
		
		boolean res = reviewDao.insertReview(review);
		
		if (res) {
			System.out.println("insert review success");
			return true;
		} else {
			System.out.println("insert review failed");
			return false;
		}
	}
	
	public boolean deleteReview(HttpServletRequest request, HttpServletResponse response) {
		
		/*
		 * Delete review information selected by application users from web in Database
		 * If succeeded deletion of review information into DB, it would return 'true' , otherwise 'false'
		 */
		ReviewDAO reviewDao = ReviewDAO.getInstance();
		int reviewNo = Integer.parseInt(request.getParameter("reviewId"));
		boolean res = reviewDao.deleteReview(reviewNo);
		
		if (res) {
			System.out.println("delete review success");
			return true;
		} else {
			System.out.println("delete review failed");
			return false;
		}
	}
	
	public boolean updateReview(HttpServletRequest request, HttpServletResponse response) {
		
		/*
		 * Modify review information selected by application users from web in Database
		 * It is two domains that it can modify in database with content and rate(evaluation)
		 * If succeeded modification of review information into DB, it would return 'true' , otherwise 'false'
		 */
		ReviewDAO reviewDao = ReviewDAO.getInstance();
		int reviewNo = Integer.parseInt(request.getParameter("reviewId"));
		String content = request.getParameter("reviewContent");
		int rate = Integer.parseInt(request.getParameter("reviewRate"));
				
		boolean res = reviewDao.updateReview(reviewNo,content,rate);
		
		if (res) {
			System.out.println("update review success");
			return true;
		} else {
			System.out.println("update review failed");
			return false;
		}
	}
	
	public ArrayList<Review> searchReview(HttpServletRequest request, HttpServletResponse response) {
		
		/*
		 * Search all of reviews information in the course from Database
		 * If succeeded searching all of reviews information into DB, it would return 'list of review' , otherwise 'null'
		 */
		
		ReviewDAO reviewDao = ReviewDAO.getInstance();
		
		HttpSession session = request.getSession();
		String courseNumber = (String)session.getAttribute("courseNumber");
		
		ArrayList<Review> reviewList = new ArrayList<Review>();
		reviewList = reviewDao.searchReview(courseNumber);
		
		if (reviewList.size() > 0) {
			System.out.println("searching review by coursenumber successes");
			return reviewList;
		} else {
			System.out.println("searching review by coursenumber is failed");
			return null;
		}
	}
	
	public ArrayList<Review> searchWriterReview(HttpServletRequest request, HttpServletResponse response) {
		
		ReviewDAO reviewDao = ReviewDAO.getInstance();
		
		HttpSession session = request.getSession();
		String writer = (String)session.getAttribute("writer");
		
		ArrayList<Review> reviewList = new ArrayList<Review>();
		reviewList = reviewDao.searchReview(writer);
		
		if (reviewList.size() > 0) {
			System.out.println("searching review by writer successes");
			return reviewList;
		} else {
			System.out.println("searching review by writer is failed");
			return null;
		}
	}
}
