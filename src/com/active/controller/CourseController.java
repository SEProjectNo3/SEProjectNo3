package com.active.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.active.dao.CourseDAO;
import com.active.dao.LectureDAO;
import com.active.dao.VoController;
import com.active.model.Course;
import com.active.model.CourseVO;
import com.active.model.Lecture;
import com.active.model.User;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
		} else if (cmd.equals("sugang")) { // ���� ����
			doLecture(request, response);
		} else if (cmd.equals("delete_course")) {
			deleteCourse(request, response);
		} else if (cmd.equals("show_lecture")) {
			showLecture(request, response);
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cmd = request.getParameter("cmd");

		if (cmd.equals("add_course")) { // ���� �߰�
			insertCourse(request, response);
		} 
	}

	/**
	 * �˻�â�� �˻� ������ ���� ���� ã�� �Լ�
	 * @param request
	 * @param response
	 */
	public void searchCourse(HttpServletRequest request, HttpServletResponse response) {
	
		VoController voController = VoController.getInstance();
	
		String condition = request.getParameter("cond"); // ����
		String content = request.getParameter("content"); // �˻���
		
		ArrayList<CourseVO> courseList = new ArrayList<CourseVO>();
		
		if (condition.equals("name")) { // �̸����� �˻�
		
			courseList = voController.searchCourseName(content);
			
		} else if (condition.equals("number")) { // �м���ȣ�� �˻�
			
			courseList = voController.searchCourseNumber(content);
		
		} else if (condition.equals("professor")) { // ���������� �˻�
			
			courseList = voController.searchCourseProfessor(content);
		
		} else { // ������ �������� ���� ��� ��� ���Ǹ� �˻�
			
			courseList = voController.searchAllCourse();
		}
		
		// ��ȯ�� courseList�� course_list �� ����
		request.setAttribute("course_list", courseList);
		
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("course-list-search.jsp");
		
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
		
		String courseNumber = request.getParameter("number");
		
		VoController voController = VoController.getInstance();
		LectureDAO lectureDao = LectureDAO.getInstance();
		
		ArrayList<CourseVO> courseList = voController.searchCourseNumber(courseNumber);
		ArrayList<Lecture> lectureList = lectureDao.searchLectureList(courseNumber);
		
		request.setAttribute("lecture_list", lectureList);
		
		CourseVO courseVO = courseList.get(0);
		int unit = courseVO.getUnit(); // ���°� �̷���� �� �ܿ� ��
		
		System.out.println(unit);
		
		request.setAttribute("course", courseVO);
		request.setAttribute("unit", unit);
		
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("course.jsp");
		
		try {
			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ���� ���� ��ư�� Ŭ���� ��� (�ش� ���¿� ���� ���̵� �޾ƿ��� �Ǿ�����)
	 * @param request
	 * @param response
	 */
	public void doLecture(HttpServletRequest request, HttpServletResponse response) {
		
		String lectureId = request.getParameter("lectureId");
		
		LectureDAO lectureDao = LectureDAO.getInstance();
		Lecture lecture = lectureDao.searchLecture(lectureId);
		
		// lecture �� �ش��ϴ� quiz �� ���� ������ �;� �� 
	
	}
	
	public void insertCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		String courseName = request.getParameter("course_name");
		String courseNumber = request.getParameter("course_number");
		String major = request.getParameter("major");
		String explanation = request.getParameter("course_info");
		
		// �ѱ� ���� ����
		courseName = new String(courseName.getBytes("8859_1"), "utf-8"); 
		courseNumber = new String(courseNumber.getBytes("8859_1"), "utf-8"); 
		major = new String(major.getBytes("8859_1"), "utf-8"); 
		explanation = new String(explanation.getBytes("8859_1"), "utf-8"); 
		
		System.out.println("add course : " + courseName + ", " + courseNumber + ", " + major + ", " + explanation);
		
		boolean flag = true;
		
		if (isSameCourseExist(courseNumber)) 
			flag = false;
		else if (isCourseExist(courseNumber, courseName))
			flag = false;
		
		if (flag) {
			
			CourseDAO courseDao = CourseDAO.getInstance();
			Course course = new Course();
			
			course.setCourseName(courseName);
			course.setCourseNumber(courseNumber);
			course.setExplanation(explanation);
			course.setMajor(major);
			course.setProfessor(user.getUserId());
	
			courseDao.insertCourse(course);
			
			try {
				
				PrintWriter out = response.getWriter();
//			
//				out.println("<script>");
//				out.println("alert('���������� ���°� �����Ǿ����ϴ�.');");
//				out.println("location.href=<%=request.getContextPath()/MyPage.do?cmd=show_lecture"); // ���� �ʿ�!
//				out.println("</script>");
//				
//				out.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		
		response.sendRedirect("MyPage.do?cmd=show_lecture");
	}
	
	/** 
	 * �ش� courseNumber �� �����ϴ� ���°� �ִ��� Ȯ���ϴ� �Լ�
	 * @param courseNumber
	 * @return
	 */
	public boolean isSameCourseExist(String courseNumber) {
		
		CourseDAO courseDao = CourseDAO.getInstance();
		
		ArrayList<Course> courseList = courseDao.searchCourseNumber(courseNumber);
		
		if(courseList.size() > 0) {
			
			System.out.println("�̹� �����ϴ� ���¹�ȣ �Դϴ�.");
			return true;
		}
		
		return false;
	}
	
	/**
	 * cse4036-03 �̶�� �м���ȣ�� �Է¹��� ��� cse4036-02 ���±����� �ִ��� Ȯ���ϰ�,
	 * �ձ����� �м���ȣ�� ������ �м���ȣ�� �����ϴ� ��� ���� �̸��� ������ Ȯ���ϴ� �޼ҵ�
	 * @param courseNumber
	 * @return
	 */
	public boolean isCourseExist(String courseNumber, String courseName) {
		
		CourseDAO courseDao = CourseDAO.getInstance();
		
		ArrayList<Course> courseList = courseDao.searchCourseNumber(courseNumber);
		
		if (courseList.size() == 0) 
			return false;
		
		String units[] = courseList.get(0).getCourseNumber().split("-");
		int maxUnit = Integer.parseInt(units[1]);
		
		String temp[];
		
		for (int i=1; i<courseList.size(); i++) {
			
			temp = courseList.get(i).getCourseNumber().split("-");
			if (maxUnit < Integer.parseInt(temp[1]))
					maxUnit = Integer.parseInt(temp[1]);
		}
		
		if (maxUnit + 1 == Integer.parseInt(units[1])) {
			
			// courseName �� ������ Ȯ���ϴ� �κ�
			if (courseName.equals(courseList.get(0).getCourseName()))
				return false;
			
			System.out.println("������ �̸��� ���� �ʽ��ϴ�.");
			return true;
		}
		
		System.out.println("���� ���� ��ȣ�� ���������� �ؾ� �մϴ�.");
		return true;
	}
	
	/**
	 * ������ ������ ���¸� ������ �� ȣ��Ǵ� �޼ҵ�
	 * @param request
	 * @param response
	 */
	public void deleteCourse(HttpServletRequest request, HttpServletResponse response) {
		
		String courseNumber = request.getParameter("number");
		CourseDAO courseDao = CourseDAO.getInstance();
		
		boolean res = courseDao.deleteCourse(courseNumber);
		
		if (res) {
			System.out.println(courseNumber + " ���� ���� �Ϸ�");
		} else {
			System.out.println(courseNumber + " ���� ���� ����");
		}
		
		try {
			response.sendRedirect("MyPage.do?cmd=show_lecture");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showLecture(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("show");
		String courseNumber = request.getParameter("course_number");
		
		CourseDAO courseDao = CourseDAO.getInstance();
		ArrayList<Course> courseList = courseDao.searchCourseNumber(courseNumber);
		
		int unit = courseList.get(0).getUnit();
		
		LectureDAO lectureDao = LectureDAO.getInstance();
		ArrayList<Lecture> lectureList = lectureDao.searchLectureList(courseNumber);
		
		request.setAttribute("course_number", courseNumber);
		request.setAttribute("unit", unit);
		request.setAttribute("lecture_list", lectureList);
		
		RequestDispatcher rd = request.getRequestDispatcher("lecture-add.jsp");
		
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}