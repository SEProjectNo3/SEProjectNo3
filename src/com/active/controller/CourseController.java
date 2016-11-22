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
			
			LectureDAO lectureDao = LectureDAO.getInstance();
			
			ArrayList<Lecture> list = lectureDao.searchLectureList("cse4036-01");
			
			System.out.println(list.get(0).getLectureId());
			System.out.println(list.get(0).getTitle());
			System.out.println(list.get(0).getExplanation());
			System.out.println(list.get(0).getFilePath());
			System.out.println(list.get(0).getHits());
			System.out.println(list.get(0).getMaterialList().values());
			
			
		} else if (cmd.equals("search")) {
			
			searchCourse(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	public void searchCourse(HttpServletRequest request, HttpServletResponse response) {
	
		CourseDAO courseDao = CourseDAO.getInstance();
		
		String condition = request.getParameter("cond");
		String content = request.getParameter("content");
		
		ArrayList<Course> courseList = new ArrayList<Course>();
		
		if (condition.equals("name")) {
		
			courseList = courseDao.searchCourseName(content);
			
		} else if (condition.equals("number")) {
			
			courseList = courseDao.searchCourseNumber(content);
		
		} else if (condition.equals("professor")) {
			
			courseList = courseDao.searchCourseProfessor(content);
		
		} else {
			
			courseList = courseDao.searchAllCourse();
		}
		
		try {
			RequestDispatcher rd = null;
			
			rd = request.getRequestDispatcher("");
			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
