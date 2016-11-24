package com.active.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.active.dao.ReviewDAO;
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
}
