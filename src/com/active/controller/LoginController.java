package com.active.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.active.dao.UserDAO;
import com.active.model.User;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/LoginController.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cmd = request.getParameter("cmd");
		
		if(cmd == null || cmd.equals("login")) {
			
		} 
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cmd = request.getParameter("cmd");
		HttpSession session = request.getSession();
		
		// check user login
		if (cmd.equals("login_check")) {
			
			String id = request.getParameter("user_id");
			String pwd = request.getParameter("user_pwd");			

			System.out.println("로그인 한 사용자 id : " + id + ", pwd : " + pwd);
			
			User user = checkUser(id, pwd);
			
			if (user != null) {
				
				System.out.println("login success.");
				
				session = request.getSession();
				session.setAttribute("user", user);
				
				response.sendRedirect("Main.do");
				
			} else {
				System.out.println("login failed.");
			}
		}
	}
	
	/**
	 * 
	 * @param id
	 * @param pwd
	 * @return
	 */
	public User checkUser(String id, String pwd) {
	
		UserDAO userDao = UserDAO.getInstance();
		
		User user = userDao.searchUser(id, pwd);
		
		if (user.getUserId() != null) {
			System.out.println(user.getUserId());
			return user;
		}
		
		return null;
	}
	
	public String findPassword(String id) {
		return null;
	}

}
