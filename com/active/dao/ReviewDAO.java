package com.active.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	
	public boolean insertReview(Review tempReview)
	{
		Connection conn = getConnection();
		
		String insertSQL = "Insert Into Review(reviewNo,writer,content,rate,time,courseNumber)"
				+ " Values(?,?,?,?,?,?)";
		
		PreparedStatement pstmt = null;
		
		try
		{
			pstmt = conn.prepareStatement(insertSQL);
			
			pstmt.setInt(1, tempReview.getReviewNo());
			pstmt.setString(2, tempReview.getWriter());
			pstmt.setString(3, tempReview.getContent());
			pstmt.setInt(4, tempReview.getRate());
			pstmt.setDate(5, tempReview.getTime());
			pstmt.setString(6, tempReview.getCourseNumber());
			
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
	
	public boolean deleteReview(int tempReviewNo)
	{
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
	
	public boolean updateReview(String tempContent, String tempRate, int tempReviewNo)
	{
		Connection conn = getConnection();
		
		String updateSQL = "update review set content = ?, rate = ? where reviewNo = ?";
		
		PreparedStatement pstmt = null;
		
		try
		{
			pstmt = conn.prepareStatement(updateSQL);
			
			pstmt.setString(1, tempContent);
			pstmt.setString(2, tempRate);
			pstmt.setInt(3, tempReviewNo);
			
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
	
	public ArrayList<Review> searchReview(String tempCourseNumber)
	{
		ArrayList<Review> reviewList = new ArrayList<Review>();
		Connection conn = getConnection();
		Review tReview= new Review();

		String searchSQL = "Select * from review where courseNumber = "+tempCourseNumber+"";
		
		Statement stmt = null;
		
		try
		{
			stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(searchSQL);
			
			while(rSet.next())
			{
				tReview.setReviewNo(rSet.getInt("reviewNo"));
				tReview.setWriter(rSet.getString("writer"));
				tReview.setContent(rSet.getString("content"));
				tReview.setRate(rSet.getInt("rate"));
				tReview.setTime(rSet.getDate("time"));
				tReview.setCourseNumber(rSet.getString("courseNumber"));
				reviewList.add(tReview);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeConnection(conn);
		}
		return reviewList;
	}
	
	public ArrayList<Review> searchWriterReview(String tempWriter)
	{
		ArrayList<Review> reviewList = new ArrayList<Review>();
		Connection conn = getConnection();
		Review tReview= new Review();

		String searchSQL = "Select * from review where writer = "+tempWriter+"";
		
		Statement stmt = null;
		
		try
		{
			stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(searchSQL);
			
			while(rSet.next())
			{
				tReview.setReviewNo(rSet.getInt("reviewNo"));
				tReview.setWriter(rSet.getString("writer"));
				tReview.setContent(rSet.getString("content"));
				tReview.setRate(rSet.getInt("rate"));
				tReview.setTime(rSet.getDate("time"));
				tReview.setCourseNumber(rSet.getString("courseNumber"));
				reviewList.add(tReview);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeConnection(conn);
		}
		return reviewList;
	}
}
