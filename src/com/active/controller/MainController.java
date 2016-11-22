package com.active.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.active.model.User;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/Main.do")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String cmd = request.getParameter("cmd");
		
		HttpSession session = request.getSession();
	    User user = (User)session.getAttribute("user");
	       
		RequestDispatcher rd = null;
		String dispatcher = null;
		
		// 처음 실행 시
		if(cmd == null) {
			
			dispatcher = "/main.jsp";
			
			rd = request.getRequestDispatcher(dispatcher);
			rd.forward(request, response);
		
		} else if (cmd == "login") {
		
			dispatcher = "/LoginController.do";
			
			rd = request.getRequestDispatcher(dispatcher);
			rd.forward(request, response);
			
		} else if (cmd == "search") {
			
			dispatcher = "/CourseController.do";
			
			rd = request.getRequestDispatcher(dispatcher);
			rd.forward(request, response);
		}
		
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
