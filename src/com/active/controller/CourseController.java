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
			
		} else if (cmd.equals("search_course")) { // ���� �˻�
			searchCourse(request, response);
		} else if (cmd.equals("search_lecture")) { // ���ǿ� �ش��ϴ� ���� ��������
			searchLectureList(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * �˻�â�� �˻� ������ ���� ���� ã�� �Լ�
	 * @param request
	 * @param response
	 */
	public void searchCourse(HttpServletRequest request, HttpServletResponse response) {
	
		CourseDAO courseDao = CourseDAO.getInstance();
		
		String condition = request.getParameter("cond"); // ����
		String content = request.getParameter("content"); // �˻���
		
		ArrayList<Course> courseList = new ArrayList<Course>();
		
		if (condition.equals("name")) { // �̸����� �˻�
		
			courseList = courseDao.searchCourseName(content);
			
		} else if (condition.equals("number")) { // �м���ȣ�� �˻�
			
			courseList = courseDao.searchCourseNumber(content);
		
		} else if (condition.equals("professor")) { // ���������� �˻�
			
			courseList = courseDao.searchCourseProfessor(content);
		
		} else { // ������ �������� ���� ��� ��� ���Ǹ� �˻�
			
			courseList = courseDao.searchAllCourse();
		}
		
		// ��ȯ�� courseList�� course_list �� ����
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
	 * �˻��� ���� Ŭ�� ��, �ش� ���ǿ� ���ԵǴ� ��� lecture�� ������ ���� �Լ�
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