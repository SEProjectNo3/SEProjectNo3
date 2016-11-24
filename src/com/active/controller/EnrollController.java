package com.active.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.active.constant.State;
import com.active.dao.CourseDAO;
import com.active.dao.EnrollDAO;
import com.active.dao.ReviewDAO;
import com.active.dao.UserDAO;
import com.active.model.Course;
import com.active.model.Enroll;
import com.active.model.Review;
import com.active.model.User;

/**
 * Servlet implementation class EnrollController
 */
@WebServlet("/EnrollController")
public class EnrollController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cmd = request.getParameter("cmd");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String cmd = request.getParameter("cmd");
		if(cmd.equals("enroll_proc"))
		{
			
		}
	}
	
	public boolean insertEnroll(HttpServletRequest request, HttpServletResponse response) {
		
		/*
		 * Insert review information passed by application users from web to Database
		 * If succeeded insertion of review information into DB, it would return 'true' , otherwise 'false'
		 */
		
		EnrollDAO enrollDao = EnrollDAO.getInstance();
		UserDAO userDao = UserDAO.getInstance();
		CourseDAO courseDAO = CourseDAO.getInstance();
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Course course = (Course)session.getAttribute("course");
		
		State state = null;
		// State 작성해야함.
		
		Enroll enroll = new Enroll();
		
		enroll.setUserId(user.getUserId());
		enroll.setCourseNumber(course.getCourseNumber());
		// 학수번호 얻어오는 작업 필요
		enroll.setState(state);
		
		boolean res = enrollDao.insertEnroll(enroll);
		
		if (res) {
			System.out.println("insert enroll success");
			return true;
		} else {
			System.out.println("insert enroll failed");
			return false;
		}
	}
	
	public boolean deleteEnroll(HttpServletRequest request, HttpServletResponse response) {
		
		/*
		 * Delete enroll information selected by application users from web in Database
		 * If succeeded deletion of enroll information into DB, it would return 'true' , otherwise 'false'
		 */
		EnrollDAO enrollDao = EnrollDAO.getInstance();
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Course course = (Course)session.getAttribute("course");
		
		String userId = user.getUserId();
		String courseNumber = course.getCourseNumber();
		
		boolean res = enrollDao.deleteEnroll(userId,courseNumber);
		
		if (res) {
			System.out.println("delete enroll success");
			return true;
		} else {
			System.out.println("delete enroll failed");
			return false;
		}
	}
	
	public boolean updateReview(HttpServletRequest request, HttpServletResponse response) {
		
		/*
		 * Modify enroll information selected by application users from web in Database
		 * It is a domain that it can modify in database with state of course
		 * If succeeded modification of enroll information into DB, it would return 'true' , otherwise 'false'
		 */
		EnrollDAO enrollDao = EnrollDAO.getInstance();
		UserDAO userDao = UserDAO.getInstance();
		CourseDAO courseDAO = CourseDAO.getInstance();
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Course course = (Course)session.getAttribute("course");
		State state = null;
		// State 작성해야함.
		
		Enroll enroll = new Enroll();
		enroll.setUserId(user.getUserId());
		enroll.setCourseNumber(course.getCourseNumber());
		// 학수번호 얻어오는 작업 필요
		enroll.setState(state);
		
		boolean res = enrollDao.updateEnroll(enroll);
		
		if (res) {
			System.out.println("update enroll success");
			return true;
		} else {
			System.out.println("update enroll failed");
			return false;
		}
	}
	
	public ArrayList<Enroll> searchEnroll(HttpServletRequest request, HttpServletResponse response) {
		
		/*
		 * Search all of enrolls by userId information from Database
		 * If succeeded searching all of enrolls by userId information into DB, it would return 'list of enroll' , otherwise 'null'
		 */
		
		EnrollDAO enrollDao = EnrollDAO.getInstance();
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		ArrayList<Enroll> enrollList = new ArrayList<Enroll>();
		enrollList = enrollDao.searchEnroll(user.getUserId());
		
		if (enrollList.size() > 0) {
			System.out.println("searching enroll by userId successes");
			return enrollList;
		} else {
			System.out.println("searching review by userId is failed");
			return null;
		}
	}
	
public ArrayList<Enroll> searchAllEnrolls(HttpServletRequest request, HttpServletResponse response) {
		
		/*
		 * Search all of enrolls information from Database
		 * If succeeded searching all of enrolls information into DB, it would return 'list of enroll' , otherwise 'null'
		 */
		
		EnrollDAO enrollDao = EnrollDAO.getInstance();
		
		ArrayList<Enroll> enrollList = new ArrayList<Enroll>();
		enrollList = enrollDao.searchAllEnrolls();
		
		if (enrollList.size() > 0) {
			System.out.println("searching enroll by userId successes");
			return enrollList;
		} else {
			System.out.println("searching review by userId is failed");
			return null;
		}
	}
	
}
