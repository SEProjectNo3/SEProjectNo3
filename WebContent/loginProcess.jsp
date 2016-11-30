<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="javax.sql.*"%>
<%@page import="javax.naming.*"%>
<%@page import="java.sql.*"%>
<%
 String id = request.getParameter("user_id");
 String pass =request.getParameter("pass");
 
 Connection conn = null;
 PreparedStatement pstmt =null;
 ResultSet rs = null;
 
 try{
  Context init=new InitialContext();
  DataSource ds =(DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
  conn = ds.getConnection();
  
  pstmt=conn.prepareStatement("select * from member2 where id=?");
  pstmt.setString(1,id);
  rs=pstmt.executeQuery();
  
  if(rs.next()){
   if(pass.equals(rs.getString("password"))){
    session.setAttribute("user_id",id);
    out.println("<script>");
    out.println("location.href='index.jsp'");
    out.println("</script>");
   }
  }
  out.println("<script>");
  out.println("location.href='loginForm.jsp'");
  out.println("</script>");
 }catch(Exception e){
  e.printStackTrace();
 }
%>

