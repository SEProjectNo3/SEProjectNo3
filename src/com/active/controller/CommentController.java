package com.active.controller;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.active.dao.CommentDAO;
import com.active.dao.LectureDAO;
import com.active.dao.QuizDAO;
import com.active.model.Comment;
import com.active.model.Lecture;
import com.active.model.User;

/**
 * Servlet implementation class CommentController
 */
@WebServlet("/CommentController")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentController() {
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
		
		String cmd = request.getParameter("cmd");
	}

	public boolean insertComment(HttpServletRequest request, HttpServletResponse response)
	{
		LectureDAO lectureDao = LectureDAO.getInstance();
		QuizDAO quizDao = QuizDAO.getInstance();
		CommentDAO commentDao = CommentDAO.getInstance();
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Lecture lecture = (Lecture)session.getAttribute("lecture");
		
		String content = request.getParameter("content");
		
		Comment comment = new Comment();
		
		comment.setContent(content);
		comment.setWriter(user.getUserName());
		comment.setLetureId(lecture.getLectureId());
		
		boolean res = commentDao.insertComment(comment);
		
		if (res) {
			System.out.println("insert comment success");
			return true;
		} else {
			System.out.println("insert comment failed");
			return false;
		}
	}
	
	public boolean deleteComment(HttpServletRequest request, HttpServletResponse response)
	{
		LectureDAO lectureDao = LectureDAO.getInstance();
		QuizDAO quizDao = QuizDAO.getInstance();
		CommentDAO commentDao = CommentDAO.getInstance();
		
		int commentNo = Integer.parseInt(request.getParameter("commentId"));

		boolean res = commentDao.deleteComment(commentNo);
		
		if (res) {
			System.out.println("delete comment success");
			return true;
		} else {
			System.out.println("delete comment failed");
			return false;
		}
	}
	
	public boolean updateComment(HttpServletRequest request, HttpServletResponse response)
	{
		/*
		 * Modify comment information selected by application users from web in Database
		 * It is a domain that it can modify in database with content
		 * If succeeded modification of comment information into DB, it would return 'true' , otherwise 'false'
		 */
		CommentDAO commentDao = CommentDAO.getInstance();
		
		int commentNo = Integer.parseInt(request.getParameter("commentId"));
		String content = request.getParameter("commentContent");
				
		boolean res = commentDao.updateComment(commentNo,content);
		
		if (res) {
			System.out.println("update comment success");
			return true;
		} else {
			System.out.println("update comment failed");
			return false;
		}
	}
	
	public ArrayList<Comment> searchComment(HttpServletRequest request, HttpServletResponse response)
	{
		/*
		 * Search all of comments information in the course from Database
		 * If succeeded searching all of comments information into DB, it would return 'list of comment' , otherwise 'null'
		 */
		
		CommentDAO commentDao = CommentDAO.getInstance();
		
		HttpSession session = request.getSession();
		String lectureId = (String)session.getAttribute("lectureId");
		
		ArrayList<Comment> commentList = new ArrayList<Comment>();
		commentList = commentDao.searchCommentLecture(lectureId);
		
		if (commentList.size() > 0) {
			System.out.println("searching comment by lectureId successes");
			return commentList;
		} else {
			System.out.println("searching comment by lectureId is failed");
			return null;
		}
	}
}
