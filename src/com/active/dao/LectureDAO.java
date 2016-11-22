package com.active.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;

import com.active.model.Lecture;

public class LectureDAO {
	private static LectureDAO lectureDao;
	private static ResourceBundle bundle;
	
	static {
		bundle = ResourceBundle.getBundle("config/jdbc");
	}
	
	public static synchronized LectureDAO getInstance() {
		if(lectureDao == null)
			lectureDao = new LectureDAO();
		
		return lectureDao;
	}
	
	private LectureDAO() {
		try {
			//DriverManager�� ���
			Class.forName(bundle.getString("driver"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private Connection getConnection() {
		
		try {
			// DriverManager ��ü�κ��� Connection ��ü�� ���´�.
			Connection conn = DriverManager.getConnection(bundle.getString("url"), bundle.getString("user_id"),bundle.getString("user_pwd"));
			return conn;
		} catch(Exception e)	{
			e.printStackTrace();
			return null;
		}
	}
	
	private void closeConnection(Connection conn) {
		try {
			if(conn != null)
				conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * courseNumber �� �ش��ϴ� ���¸� �߰��ϴ� �޼ҵ�
	 * @param courseNumber
	 * @param lecture
	 * @param materialList
	 * @return
	 */
	public boolean insertLecture(String courseNumber, Lecture lecture, LinkedHashMap<String, String> materialList) {
		
		Connection conn = getConnection();
		
		String lectureSQL = "Insert Into Lecture(lectureId, courseNumber, title, explanation, filePath)"
							+ " Values(?,?,?,?,?)";
				
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(lectureSQL);
			
			pstmt.setString(1, lecture.getLectureId());
			pstmt.setString(2, courseNumber);
			pstmt.setString(3, lecture.getTitle());
			pstmt.setString(4, lecture.getExplanation());
			pstmt.setString(5, lecture.getFilePath());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				
				// ���¿� �ʿ��� �ڷṰ�� Material �����ͺ��̽��� �߰�
				return insertMaterial(lecture.getLectureId(), materialList);
			}
			else
				return false;
		
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
			
		} finally {
			closeConnection(conn);
		}
	}
	
	public boolean deleteLecture(String tempLectureId) {
		
		Connection conn = getConnection();
		
		String deleteSQL = "delete from lecture where lectureId = ?";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(deleteSQL);
			
			pstmt.setString(1, tempLectureId);
			
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
	 * ���¸� update �ϴ� �κ�. ������ ���� ������ ��û�� �� ȣ��Ǵ� �޼ҵ�
	 * @param lecture
	 * @return
	 */
	public boolean updateLecture(Lecture lecture, LinkedHashMap<String, String> materialList) {
		
		Connection conn = getConnection();
		
		String updateSQL = "update lecture set title = ?, explanation = ?, filePath = ?, hits = ?"
						+ " where LectureId = ?";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(updateSQL);
			
			pstmt.setString(1, lecture.getTitle());
			pstmt.setString(2, lecture.getExplanation());
			pstmt.setString(3, lecture.getFilePath());
			pstmt.setInt(4, lecture.getHits());
			pstmt.setString(5, lecture.getLectureId());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				
				// Material ���� ��, ���� Material DB �� ����ִ� lecturdId �� �ش��ϴ� material ���� �����
				// �ٽ� ������ materialList �� insert ��
				Statement stmt = conn.createStatement();
				
				String deleteSQL = "DELETE FROM MATERIAL WHERE LECTUREID = '" + lecture.getLectureId() + "'";
				
				int res = stmt.executeUpdate(deleteSQL);
				
				if (res > 0) {
				
					closeConnection(conn);
					return insertMaterial(lecture.getLectureId(), materialList);
				}
				else 
					return false;
			}
			else
				return false;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeConnection(conn);
		}
	}
	
	/**
	 * �м���ȣ�� ���¸� ã�� �Լ� (�ش� ���ǿ� �ش��ϴ� ���� search)
	 * @param tempCourseNumber
	 * @return
	 */
	public ArrayList<Lecture> searchLectureList(String courseNumber) {
		
		Connection conn = getConnection();
		
		Lecture lecture= new Lecture();
		ArrayList<Lecture> lectureList = new ArrayList<Lecture>();
		
		String searchSQL = "Select * FROM LECTURE WHERE courseNumber = '" + courseNumber + "'";
		
		Statement stmt = null;
		
		try {
			
			// courseNumber ���ǿ� �ش��ϴ� ��� ���¸�� ã��
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(searchSQL);
			
			while(rs.next()) {
				
				lecture.setLectureId(rs.getString("lectureId"));
				lecture.setTitle(rs.getString("title"));
				lecture.setExplanation(rs.getString("explanation"));
				lecture.setFilePath(rs.getString("filePath"));
				lecture.setHits(rs.getInt("hits"));
				
				lectureList.add(lecture);
			}
			
			// �� lecture �� �ʿ��� �ڷ� ã��
			if (lectureList != null) {
				
				int idx = 0;
				
				while (idx < lectureList.size()) {
					
					Lecture temp = lectureList.get(idx);
					
					temp.setMaterialList(searchMaterial(temp.getLectureId()));
					lectureList.set(idx, temp);
					
					idx++;
				}
			}
			
			return lectureList;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			closeConnection(conn);
		}
	}
	
	/**
	 * ���� ���̵�� ���¸� ã�� �Լ�. ���� ���� ���� �� �̿�.
	 * @param tempLectureId
	 * @return
	 */
	public Lecture searchLecture(String tempLectureId) {
		
		Connection conn = getConnection();
		
		Lecture tLecture= new Lecture();

		String searchSQL = "Select * from Lecture where lectureId = '" + tempLectureId + "'";
		
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(searchSQL);
			
			while(rSet.next()) {
				tLecture.setLectureId(rSet.getString("lectureId"));
				tLecture.setTitle(rSet.getString("title"));
				tLecture.setExplanation(rSet.getString("explanation"));
				tLecture.setFilePath(rSet.getString("filePath"));
				tLecture.setHits(rSet.getInt("hits"));
			}
			
			return tLecture;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			closeConnection(conn);
		}
	}
	
	/**
	 * ��� ������ ����� ã�� �Լ�
	 * @return
	 */
	public ArrayList<Lecture> searchAllLectures() {
		
		Connection conn = getConnection();
		
		Lecture tLecture= new Lecture();
		ArrayList<Lecture> lectureList = new ArrayList<Lecture>();

		String searchSQL = "Select * from Lecture";
		
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(searchSQL);
			
			while(rSet.next()) {
				
				tLecture.setLectureId(rSet.getString("lectureId"));
				tLecture.setTitle(rSet.getString("title"));
				tLecture.setExplanation(rSet.getString("explanation"));
				tLecture.setFilePath(rSet.getString("filePath"));
				tLecture.setHits(rSet.getInt("hits"));
				lectureList.add(tLecture);
			}
			
			return lectureList;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			closeConnection(conn);
		}
	}
	
	/**
	 * ���¿� �ش��ϴ� �ڷṰ���� Material database �� �����ϴ� �Լ�
	 * @param lectureId
	 * @param materialList
	 * @return
	 */
	public boolean insertMaterial(String lectureId, LinkedHashMap<String, String> materialList) {
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;

		String materialSQL = null;
		
		try {
		
			Iterator<String> iterator = materialList.keySet().iterator();
			
			while (iterator.hasNext()) {
				
				String key = (String) iterator.next();
					
				// Material DB�� primary key�� auto_increment
				materialSQL = "INSERT INTO MATERIAL (PATH, TITLE, LECTUREID) VALUES (?,?,?)";
				
				pstmt = conn.prepareStatement(materialSQL);
			        
				pstmt.setString(1, key);
				pstmt.setString(2, materialList.get(key));
				pstmt.setString(3, lectureId);
				
				int res = pstmt.executeUpdate();
				
				if(res < 0)
					return false;
			}
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeConnection(conn);
		}
	}
	
	/**
	 * lecturdId �� �ʿ��� �ڷṰ ã�� �޼ҵ�
	 * @param lectureId
	 * @return
	 */
	public LinkedHashMap<String, String> searchMaterial(String lectureId) {
		
		Connection conn = getConnection();
		
		LinkedHashMap<String, String> materialList = new LinkedHashMap<String, String>();
		
		String searchSQL = "SELECT TITLE, PATH From MATERIAL WHERE LECTUREID = '" + lectureId + "'";
	
		Statement stmt = null;
		
		try {
			
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(searchSQL);
			
			while (rs.next()) {
				
				materialList.put(rs.getString("path"), rs.getString("title"));
			}
			
			return materialList;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
			
		} finally {
			closeConnection(conn);
		}
	}
}