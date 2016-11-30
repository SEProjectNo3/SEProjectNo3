package com.active.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.active.model.Review;

public class ReviewDAO 
{
	private static ReviewDAO reviewDao;
	private static ResourceBundle bundle;
	
	static
	{
		bundle = ResourceBundle.getBundle("config/jdbc");
	}
	
	
	public static ReviewDAO getInstance()
	{
		if(reviewDao == null)
			reviewDao = new ReviewDAO();
		
		return reviewDao;
	}
	
	private ReviewDAO()
	{
		try
		{
			//DriverManager에 등록
			Class.forName(bundle.getString("driver"));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
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
	
	private void closeConnection(Connection conn)
	{
		try
		{
			if(conn != null)
				conn.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean insertReview(Review tempReview) {
		Connection conn = getConnection();
		
		String insertSQL = "Insert Into Review(writer,content,rate,courseNumber)"
				+ " Values(?,?,?,?)";
		
		PreparedStatement pstmt = null;
		System.out.println(tempReview.getCourseNumber());
		try {
			pstmt = conn.prepareStatement(insertSQL);
			
			pstmt.setString(1, tempReview.getWriter());
			pstmt.setString(2, tempReview.getContent());
			pstmt.setInt(3, tempReview.getRate());
			pstmt.setString(4, tempReview.getCourseNumber());
			
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
	
	public boolean deleteReview(int tempReviewNo) {
		
		Connection conn = getConnection();
		
		String deleteSQL = "delete from review where reviewNo = ?";
		
		PreparedStatement pstmt = null;
		
		try
		{
			pstmt = conn.prepareStatement(deleteSQL);
			
			pstmt.setInt(1, tempReviewNo);
		
			int result = pstmt.executeUpdate();
			
			if(result>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		finally{
			closeConnection(conn);
		}
	}
	
	public boolean updateReview(int tempReviewNo, String tempContent, int tempRate) {
		
		Connection conn = getConnection();
		
		String updateSQL = "update review set content = ?, rate = ?, writeTime = ? where reviewNo = ?";
		
		PreparedStatement pstmt = null;
		
		try {

			pstmt = conn.prepareStatement(updateSQL);
			
			pstmt.setString(1, tempContent);
			pstmt.setInt(2, tempRate);
			
			Date date = new Date(System.currentTimeMillis());
			//SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			//String strNow = sdfNow.format(date);
			
			pstmt.setDate(3, date);
			pstmt.setInt(4, tempReviewNo);
			
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
	
	/**
	 * 해당 강의에 해당하는 모든 강의평가 가져오는 함수. (학수번호로 검색)
	 * @param tempCourseNumber
	 * @return
	 */
	public ArrayList<Review> searchReview(String tempCourseNumber) 
	{
		Connection conn = getConnection();	
		ArrayList<Review> reviewList = new ArrayList<Review>();
		String searchSQL = "Select * from review where courseNumber LIKE '%" + tempCourseNumber + "%'" ;
		Statement stmt = null;
		
		try
		{
			stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(searchSQL);
			
			while(rSet.next()) 
			{
				// Make array list of review object until rSet.next() done
				// And return it to represent list of review
				
				Review tReview= new Review();
				tReview.setReviewNo(rSet.getInt("reviewNo"));
				tReview.setWriter(rSet.getString("writer"));
				tReview.setContent(rSet.getString("content"));
				tReview.setRate(rSet.getInt("rate"));
				tReview.setTime(rSet.getDate("writeTime"));
				tReview.setCourseNumber(rSet.getString("courseNumber"));
				reviewList.add(tReview);
			}	
			
			return reviewList;
			
		}catch(SQLException e) 
		{
			e.printStackTrace();
			return null;
		} finally 
		{
			closeConnection(conn);
		}
	}
	
	/**
	 * 작성자로 강의평가 검색
	 * @param tempWriter
	 * @return
	 */
	public ArrayList<Review> searchWriterReview(String tempWriter) {
		
		Connection conn = getConnection();
		
		ArrayList<Review> reviewList = new ArrayList<Review>();
		Review tReview= new Review();

		String searchSQL = "Select * from review where writer LIKE '%" + tempWriter + "%'";
		
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(searchSQL);
			
			while(rSet.next())
			{
				tReview.setReviewNo(rSet.getInt("reviewNo"));
				tReview.setWriter(rSet.getString("writer"));
				tReview.setContent(rSet.getString("content"));
				tReview.setRate(rSet.getInt("rate"));
				tReview.setTime(rSet.getDate("writeTime"));
				tReview.setCourseNumber(rSet.getString("courseNumber"));
				
				reviewList.add(tReview);
			}
			
			return reviewList;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
			
		} finally {
			closeConnection(conn);
		}
	}
}
