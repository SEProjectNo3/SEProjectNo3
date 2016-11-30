package com.active.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

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
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class UpLoadController
 */
@WebServlet("/UpLoad.do")
public class UpLoadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpLoadController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String savePath = request.getServletContext().getRealPath("/lecture/");
		System.out.println(savePath);
		MultipartRequest multi = new MultipartRequest(request, savePath, 15*1024*1024,"utf-8",new DefaultFileRenamePolicy());
		addLecture(request, response, multi, savePath);
	}

	/**
	 * 교수가 특정 강좌에 해당하는 새로운 강의를 추가하고자 할 때 호출되는 메소드
	 * @param request
	 * @param response
	 */
	public void addLecture(HttpServletRequest request, HttpServletResponse response, MultipartRequest multi, String savePath) {
	
		String unit = multi.getParameter("unit"); // 대단원
		String s_unit = multi.getParameter("s_unit"); // 소단원
		String title = multi.getParameter("title");
		String explanation = multi.getParameter("lecture_info");
		String courseNumber = multi.getParameter("course_number");
		
		System.out.println(unit + ", " + s_unit + ", " + explanation + ", " + courseNumber);
		
		try {
			// ======================== 강의 동영상 ====================== 
			String filePath = multi.getOriginalFileName("lecture");
			String fileName = multi.getOriginalFileName("material");
	
			LinkedHashMap<String, String> materialList = new LinkedHashMap<String, String>();
			
			if (fileName != null) 
				materialList.put("c://", fileName);
		
			LectureDAO lectureDao = LectureDAO.getInstance();
			
			Lecture lecture = new Lecture();
			
			ArrayList<Lecture> lectureList = lectureDao.searchLectureList(courseNumber);
			int size = lectureList.size() + 1;
			
			// ============== lecture number 조정 부분 =============== //
			if (size == 0) 
				lecture.setLectureId(courseNumber + "-01");
			else if (size < 10) 
				lecture.setLectureId(courseNumber + "-0" + size);
			else
				lecture.setLectureId(courseNumber + "-" + size);
			
			lecture.setChapter(unit + "-" + s_unit);
			lecture.setTitle(title);
			lecture.setMaterialList(materialList);
			lecture.setExplanation(explanation);
			lecture.setFilePath(filePath);
			
			boolean res = lectureDao.insertLecture(courseNumber, lecture, materialList);
			
			if (res) {
				
				// course 에 해당하는 unit 변경 부분
				CourseDAO courseDao = CourseDAO.getInstance();
				Course course = courseDao.searchCourseNumber(courseNumber).get(0);
				
				course.setUnit(Integer.parseInt(unit));
				courseDao.updateCourse(course);
				
				System.out.println("강좌 업로드 성공");
			}
			else 
				System.out.println("강좌 업로드 실패");
		
			RequestDispatcher rd = request.getRequestDispatcher("/MyPage.do?cmd=show_lecture");
			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
