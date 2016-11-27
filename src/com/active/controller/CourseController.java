package com.active.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.active.dao.CourseDAO;
import com.active.dao.LectureDAO;
import com.active.model.Course;
import com.active.model.Lecture;

/**
 * Servlet implementation class CourseController
 */
@WebServlet("/Course.do")
public class CourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cmd = request.getParameter("cmd");
		
		if (cmd == null) {
			
		} else if (cmd.equals("search_course")) { // 강의 검색
			searchCourse(request, response);
		} else if (cmd.equals("search_lecture")) { // 강의에 해당하는 강좌 가져오기
			searchLectureList(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * 검색창에 검색 조건을 통해 강의 찾는 함수
	 * @param request
	 * @param response
	 */
	public void searchCourse(HttpServletRequest request, HttpServletResponse response) {
	
		CourseDAO courseDao = CourseDAO.getInstance();
		
		String condition = request.getParameter("cond"); // 조건
		String content = request.getParameter("content"); // 검색명
		
		ArrayList<Course> courseList = new ArrayList<Course>();
		
		if (condition.equals("name")) { // 이름으로 검색
		
			courseList = courseDao.searchCourseName(content);
			
		} else if (condition.equals("number")) { // 학수번호로 검색
			
			courseList = courseDao.searchCourseNumber(content);
		
		} else if (condition.equals("professor")) { // 교수명으로 검색
			
			courseList = courseDao.searchCourseProfessor(content);
		
		} else { // 조건을 선택하지 않은 경우 모든 강의를 검색
			
			courseList = courseDao.searchAllCourse();
		}
		
		// 반환된 courseList를 course_list 에 저장
		request.setAttribute("course_list", courseList);
		
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("course-list.jsp");
		
		try {
			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 검색된 강의 클릭 시, 해당 강의에 포함되는 모든 lecture를 가지고 오는 함수
	 * @param request
	 * @param response
	 */
	public void searchLectureList(HttpServletRequest request, HttpServletResponse response) { 
		
		String courseNumber = request.getParameter("course_number");
		
		LectureDAO lectureDao = LectureDAO.getInstance();
		
		ArrayList<Lecture> lectureList = lectureDao.searchLectureList(courseNumber);
		
		request.setAttribute("lecture_list", lectureList);
		
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("course.jsp");
		
		try {
			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}