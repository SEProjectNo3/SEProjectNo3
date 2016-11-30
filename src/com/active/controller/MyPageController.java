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

import com.active.dao.CourseDAO;
import com.active.dao.LectureDAO;
import com.active.model.Course;
import com.active.model.Lecture;
import com.active.model.User;

/**
 * Servlet implementation class MyPageController
 */
@WebServlet("/MyPage.do")
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String cmd = request.getParameter("cmd");
	
		if (cmd.equals("show_lecture")) { // ���°���
			showLecture(request, response);
		} else if (cmd.equals("exam")) { // ��������
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	/**
	 * ������ ���� ���� �޴��� Ŭ������ �� ����Ǵ� �޼ҵ� (���� ������ ���� ���� ������ ������ ��)
	 * @param request
	 * @param response
	 */
	public void showLecture(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		CourseDAO courseDao = CourseDAO.getInstance();
		ArrayList<Course> courseList = courseDao.searchProfessorCourse(user.getUserId());
		
		request.setAttribute("course_list", courseList);
		
		RequestDispatcher rd = request.getRequestDispatcher("course-list-prof.jsp");
		
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
