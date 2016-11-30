package com.active.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.active.model.Comment;


public class CommentDAO {
	/*
	 * It is a class that has the only one object by using singleton object pattern
	 * DAO means that it is database access object
	 * That is, it is made only one time and a object that accesses the database
	 */
	private static CommentDAO commentDao; 
	private static ResourceBundle bundle;
	
	static {
		bundle = ResourceBundle.getBundle("config/jdbc");
	}
	
	public static synchronized CommentDAO getInstance() {
		if(commentDao == null)
			commentDao = new CommentDAO();
		
		return commentDao;	
	}
	
	private CommentDAO() {
		try {
			//DriverManager에 등록
			Class.forName(bundle.getString("driver"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Connecting Java Eclipse to our Database
	 */
	private Connection getConnection()
	{
		try
		{
			// DriverManager 객체로부터 Connection 객체를 얻어온다.
			Connection conn = DriverManager.getConnection(bundle.getString("url")
					,bundle.getString("user_id"),bundle.getString("user_pwd"));
			return conn;
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * Disconnecting Java Eclipse to our Database. 
	 */
	
	private void closeConnection(Connection conn) {
		try
		{
			if(conn != null)
				conn.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
		
	/**
	 * Insert values into comment table in the Database
	 * This method makes character string query to insert values into the database
	 * @param tempComment
	 * @return
	 */
	public boolean insertComment(Comment tempComment) {
		
		Connection conn = getConnection(); 
		
		String insertSQL = "Insert Into Comments(writer,content, lectureId) Values(?,?,?)";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(insertSQL);
			
			pstmt.setString(1, tempComment.getWriter());
			pstmt.setString(2, tempComment.getContent());
			pstmt.setString (3, tempComment.getLectureId());
			
			int result = pstmt.executeUpdate();
			
			if(result>0)
				return true;
			else
				return false;
		
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeConnection(conn);
		}
	}
	
	public boolean deleteComment(int tempCommentNo) {
		
		Connection conn = getConnection();
		
		String deleteSQL = "Delete from comments where commentNo = ?";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(deleteSQL);
			
			pstmt.setInt(1, tempCommentNo);
			
			int result = pstmt.executeUpdate();
			
			if(result>0)
				return true;
			else
				return false;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally{
			closeConnection(conn);
		}
	}
	
	// 현재 시간 적용!!
	public boolean updateComment(int tempCommentNo, String tempContent) {
		
		Connection conn = getConnection();
		
		String updateSQL = "update comments set content = ?, writeTime = ? where commentNo = ?";
		
		PreparedStatement pstmt = null;
		
		try {
			
			pstmt = conn.prepareStatement(updateSQL);
			
			pstmt.setString(1, tempContent);
			//pstmt.setTime(2, x); // 현재 시간 적용 필요
			Date date = new Date(System.currentTimeMillis());
			pstmt.setDate(2, date);
			pstmt.setInt(3, tempCommentNo);
			
			
			int result = pstmt.executeUpdate();
			
			if(result>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally{
			closeConnection(conn);
		}
	}
	
	/**
	 * 현재 강좌에 대한 댓글 가져오는 메소드
	 * @param tempLectureId
	 * @return
	 */
	public ArrayList<Comment> searchCommentLecture(String tempLectureId) {
		
		Connection conn = getConnection();
		
		ArrayList<Comment> commentList = new ArrayList<Comment>();
				
		String searchSQL = "Select * from comments where lectureId = '" + tempLectureId + "'";
		
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(searchSQL);
			
			while(rSet.next()) 
			{
				Comment tComment = new Comment();
				tComment.setCommentNo(rSet.getInt("commentNo"));
				tComment.setWriter(rSet.getString("writer"));
				tComment.setContent(rSet.getString("content"));
				tComment.setWriteTime(rSet.getDate("writeTime"));
				tComment.setLectureId(rSet.getString("lectureId"));
				
				commentList.add(tComment);
			}
			
			return commentList;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
			
		} finally {
			closeConnection(conn);
		}
	}
	
	/**
	 * 댓글 작성자로 comment 찾기
	 * @param tempWriter
	 * @return
	 */
	public ArrayList<Comment> searchCommentWriter(String tempWriter) {
		
		Connection conn = getConnection();
		
		ArrayList<Comment> commentList = new ArrayList<Comment>();
		Comment tComment = new Comment();

		String searchSQL = "Select * from comments where writer LIKE '%" + tempWriter + "%'";
		
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(searchSQL);
			
			while(rSet.next()) {
				tComment.setCommentNo(rSet.getInt("commentNo"));
				tComment.setWriter(rSet.getString("writer"));
				tComment.setContent(rSet.getString("content"));
				tComment.setWriteTime(rSet.getDate("writeTime"));
				tComment.setLectureId(rSet.getString("lecturdId"));
				commentList.add(tComment);
			}
			
			return commentList;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
			
		} finally {
			closeConnection(conn);
		}
	}
	
	public ArrayList<Comment> searchAllComments() {
		
		Connection conn = getConnection();
		
		ArrayList<Comment> commentList = new ArrayList<Comment>();
		Comment tComment = new Comment();

		String searchSQL = "Select * from comments";
		
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(searchSQL);
			
			while(rSet.next()) {
				tComment.setCommentNo(rSet.getInt("commentNo"));
				tComment.setWriter(rSet.getString("writer"));
				tComment.setContent(rSet.getString("content"));
				tComment.setWriteTime(rSet.getDate("writeTime"));
				tComment.setLectureId(rSet.getString("lecturdId"));
				commentList.add(tComment);
			}
			
			return commentList;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			closeConnection(conn);
		}
	}
}
