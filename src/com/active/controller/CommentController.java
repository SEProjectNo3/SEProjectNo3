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

import com.active.dao.CommentDAO;
import com.active.dao.LectureDAO;
import com.active.dao.QuizDAO;
import com.active.model.Comment;
import com.active.model.Lecture;
import com.active.model.User;

/**
 * Servlet implementation class CommentController
 */
@WebServlet("/Comment.do")
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
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		
		if(cmd == null)
		{
			searchComment(request,response);
		}
		else if(cmd.equals("comment_del_proc"))
		{
			deleteComment(request,response);
			searchComment(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		
		if(cmd.equals("comment_proc"))
		{
			insertComment(request,response);
			searchComment(request,response);
		}
		else if(cmd.equals("comment_del_proc"))
		{
			deleteComment(request,response);
			searchComment(request,response);
		}
		else if(cmd.equals("comment_modify_proc"))
		{
			updateComment(request,response);
			searchComment(request,response);
		}
	}

	public boolean insertComment(HttpServletRequest request, HttpServletResponse response)
	{
		/*
		 * Insert comment information written by application users from web to Database
		 * If succeeded insertion of comment information into DB, it would return 'true' , otherwise 'false'
		 */
		
		//LectureDAO lectureDao = LectureDAO.getInstance();
		//QuizDAO quizDao = QuizDAO.getInstance();
		CommentDAO commentDao = CommentDAO.getInstance();
		
		//HttpSession session = request.getSession();
		//User user = (User)session.getAttribute("user");
		//Lecture lecture = (Lecture)session.getAttribute("lecture");
		
		String content = request.getParameter("content");
		
		Comment comment = new Comment();
		
		comment.setContent(content);
		//writer, lectureId 받아오는 로직 필요
		comment.setWriter("2013112023");
		comment.setLectureId("cse4036-01-01");
		
		boolean res = commentDao.insertComment(comment);
	
		return res;
	}
	
	public boolean deleteComment(HttpServletRequest request, HttpServletResponse response)
	{
		/*
		 * Delete comment information selected by application users from web in Database
		 * If succeeded deletion of comment information into DB, it would return 'true' , otherwise 'false'
		 */
		
		//LectureDAO lectureDao = LectureDAO.getInstance();
		//QuizDAO quizDao = QuizDAO.getInstance();
		CommentDAO commentDao = CommentDAO.getInstance();
		
		int commentNo = Integer.parseInt(request.getParameter("commentId"));

		boolean res = commentDao.deleteComment(commentNo);
		
		return res;
	}
	
	public boolean updateComment(HttpServletRequest request, HttpServletResponse response)
	{
		/*
		 * Modify comment information selected by application users from web in Database
		 * It is a domain that it can modify in database with content
		 * If succeeded modification of comment information into DB, it would return 'true' , otherwise 'false'
		 */
		
		CommentDAO commentDao = CommentDAO.getInstance();
		
		int commentNo = Integer.parseInt(request.getParameter("cId"));
		String content = request.getParameter("content");
				
		boolean res = commentDao.updateComment(commentNo,content);
		
		return res;
	}
	
	public void searchComment(HttpServletRequest request, HttpServletResponse response)
	{
		/*
		 * Search all of comments information in the course from Database
		 * If succeeded searching all of comments information into DB, it would return 'list of comment' , otherwise 'null'
		 */
		
		CommentDAO commentDao = CommentDAO.getInstance();
		
		//HttpSession session = request.getSession();
		//String lectureId = (String)session.getAttribute("lectureId");
		
		ArrayList<Comment> commentList = new ArrayList<Comment>();
		commentList = commentDao.searchCommentLecture("cse4036-01-01");
		
		request.setAttribute("comment_list",commentList);
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("studying.jsp");
		
		try{
			rd.forward(request, response);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
