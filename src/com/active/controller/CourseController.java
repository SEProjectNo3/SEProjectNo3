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
			
		} else if (cmd.equals("search_course")) { // 강의 검색
			searchCourse(request, response);
		} else if (cmd.equals("search_lecture")) { // 강의에 해당하는 강좌 가져오기
			searchLectureList(request, response);
		} else if (cmd.equals("sugang")) { // 강의 수강
			doLecture(request, response);
		} else if (cmd.equals("delete_course")) {
			deleteCourse(request, response);
		} else if (cmd.equals("show_lecture")) {
			showLecture(request, response);
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cmd = request.getParameter("cmd");

		if (cmd.equals("add_course")) { // 강좌 추가
			insertCourse(request, response);
		} 
	}

	/**
	 * 검색창에 검색 조건을 통해 강의 찾는 함수
	 * @param request
	 * @param response
	 */
	public void searchCourse(HttpServletRequest request, HttpServletResponse response) {
	
		VoController voController = VoController.getInstance();
	
		String condition = request.getParameter("cond"); // 조건
		String content = request.getParameter("content"); // 검색명
		
		ArrayList<CourseVO> courseList = new ArrayList<CourseVO>();
		
		if (condition.equals("name")) { // 이름으로 검색
		
			courseList = voController.searchCourseName(content);
			
		} else if (condition.equals("number")) { // 학수번호로 검색
			
			courseList = voController.searchCourseNumber(content);
		
		} else if (condition.equals("professor")) { // 교수명으로 검색
			
			courseList = voController.searchCourseProfessor(content);
		
		} else { // 조건을 선택하지 않은 경우 모든 강의를 검색
			
			courseList = voController.searchAllCourse();
		}
		
		// 반환된 courseList를 course_list 에 저장
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
	 * 검색된 강의 클릭 시, 해당 강의에 포함되는 모든 lecture를 가지고 오는 함수
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
		int unit = courseVO.getUnit(); // 강좌가 이루어진 총 단원 수
		
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
	 * 강좌 수강 버튼을 클릭한 경우 (해당 강좌에 대한 아이디를 받아오게 되어있음)
	 * @param request
	 * @param response
	 */
	public void doLecture(HttpServletRequest request, HttpServletResponse response) {
		
		String lectureId = request.getParameter("lectureId");
		
		LectureDAO lectureDao = LectureDAO.getInstance();
		Lecture lecture = lectureDao.searchLecture(lectureId);
		
		// lecture 에 해당하는 quiz 도 같이 가지고 와야 함 
	
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
		
		// 한글 깨짐 문제
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
//				out.println("alert('성공적으로 강좌가 개설되었습니다.');");
//				out.println("location.href=<%=request.getContextPath()/MyPage.do?cmd=show_lecture"); // 수정 필요!
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
	 * 해당 courseNumber 에 존재하는 강좌가 있는지 확인하는 함수
	 * @param courseNumber
	 * @return
	 */
	public boolean isSameCourseExist(String courseNumber) {
		
		CourseDAO courseDao = CourseDAO.getInstance();
		
		ArrayList<Course> courseList = courseDao.searchCourseNumber(courseNumber);
		
		if(courseList.size() > 0) {
			
			System.out.println("이미 존재하는 강좌번호 입니다.");
			return true;
		}
		
		return false;
	}
	
	/**
	 * cse4036-03 이라는 학수번호를 입력받은 경우 cse4036-02 강좌까지만 있는지 확인하고,
	 * 앞까지의 학수번호와 동일한 학수번호가 존재하는 경우 강좌 이름이 같은지 확인하는 메소드
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
			
			// courseName 이 같은지 확인하는 부분
			if (courseName.equals(courseList.get(0).getCourseName()))
				return false;
			
			System.out.println("강좌의 이름이 같지 않습니다.");
			return true;
		}
		
		System.out.println("개설 강좌 번호는 순차적으로 해야 합니다.");
		return true;
	}
	
	/**
	 * 교수가 개설된 강좌를 삭제할 때 호출되는 메소드
	 * @param request
	 * @param response
	 */
	public void deleteCourse(HttpServletRequest request, HttpServletResponse response) {
		
		String courseNumber = request.getParameter("number");
		CourseDAO courseDao = CourseDAO.getInstance();
		
		boolean res = courseDao.deleteCourse(courseNumber);
		
		if (res) {
			System.out.println(courseNumber + " 강좌 삭제 완료");
		} else {
			System.out.println(courseNumber + " 강좌 삭제 실패");
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