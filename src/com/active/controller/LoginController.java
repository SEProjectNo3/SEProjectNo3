package com.active.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.active.dao.UserDAO;
import com.active.model.User;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/Login.do")
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
		HttpSession session = request.getSession();
		
		RequestDispatcher rd = null;
		
		if(cmd.equals("logout")) {
			
			System.out.println("do logout");
			session.removeAttribute("user");
			response.sendRedirect("Main.do");
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cmd = request.getParameter("cmd");
		HttpSession session = request.getSession();
		
		String userId = request.getParameter("userDTO.userId");
		String userPwd = request.getParameter("userDTO.password");
		if (cmd.equals("login_check")) {
			
			try {
				
//				UserDAO userDao = UserDAO.getInstance();
//				User user = userDao.searchUser(userId, userPwd);
//				
//				if (user.getUserId() != null) {
//					System.out.println(user.getUserId());
//					session.setAttribute("user", user);
//					response.sendRedirect("Main.do");
//				}
				
				
				String u = "https://eclass.dongguk.edu/User.do?cmd=loginUser";
				// http://eclass.dongguk.edu/index.jsp
				Connection con = Jsoup.connect(u);
				con.timeout(1000 * 5);
				
				Document doc = con
						  .data("userDTO.userId", userId)
						  .data("userDTO.password", userPwd)
						  .userAgent("Mozilla")
						  .post();
				
				//System.out.println(doc);
		        
				// 사용자 이름 가져오는 부분 
				Elements rows = doc.select("p span.user strong");
				
				// rows.size() == 0 인 경우 : 아이디나 비밀번호가 잘못된 경우
				if (rows.size() == 0) {
					
					response.setCharacterEncoding("UTF-8");
					response.setContentType("text/html; charset=UTF-8");
					
					PrintWriter out = response.getWriter();
				
					out.println("<script>");
					out.println("alert('사용자 정보가 존재하지 않습니다.');");
					out.println("location.href=<%=request.getContextPath()/Main.do"); // 수정 필요!
					out.println("</script>");
					
					out.close();
					
				} else {
					
					String name = rows.first().text();
					
					// user 정보 저장
					User user = new User();
					
					user.setUserName(name);
					user.setUserId(userId);
					user.setPwd(userPwd);
					user.setMajor("cse");
					
					if (userId.length() == 10) 
						user.setUserType(0);
					else 
						user.setUserType(1);
					
					// 이미 user 데이터베이스에 저장된 user 면 저장하지 않음
					UserDAO userDao = UserDAO.getInstance();
					User tempUser = userDao.searchUser(userId, userPwd);
					
					if (tempUser.getUserId() == null)
						userDao.insertUser(user);
					
					session.setAttribute("user", user);
					response.sendRedirect("Main.do");
					
				}
				
			} catch(Exception e) {
				e.printStackTrace();
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
