package com.active.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheck implements Filter {
	
	public void init(FilterConfig filterConfig) throws ServletException {
        // 필터 초기화 작업
     }
     
     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	 
    	 //System.out.println("test filter");
    	 HttpSession session = ((HttpServletRequest)request).getSession();
    	 
    	 if (session != null) {
    		 System.out.println("aaa");
    	 }
    	 else {
    		 System.out.println("bbb");
    	 }
    	 //HttpServletResponse res = (HttpServletResponse) response;
    	 
         //res.setHeader("Access-Control-Allow-Origin", "https://eclass.dongguk.edu/Main.do?cmd=viewHome");
         
        chain.doFilter(request, response);        // 3. response를 이용하여 응답의 필터링 작업 수행
     }
     
     public void destroy() {
        // 주로 필터가 사용한 자원을 반납
     }
}
