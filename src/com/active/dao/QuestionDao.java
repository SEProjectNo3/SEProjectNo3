package com.active.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.active.dto.QuestionDto;
public class QuestionDao {

	DataSource dataSource;

	/*************************************************** 생성자 ***************************************************/
	public QuestionDao() {
		// DB connection
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**************************************** 디비에 저장 *********************************************************/
	public void insert(String question, String writer, String courseNumber, String questionTitle) {
		// TODO Auto-generated method stub

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// DB connection
			connection = dataSource.getConnection();
			String query = "insert into question (question, writer, courseNumber, questionTitle) values (?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);

			// ? 값 설정
			preparedStatement.setString(1, question);
			preparedStatement.setString(2, writer);
			preparedStatement.setString(3, courseNumber);
			preparedStatement.setString(4, questionTitle);

			// 퀘리 실행
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}

	/**************************************** 질문 리스트 가져오기 *********************************************************/
	public ArrayList<QuestionDto> list() {

		// 선언부
		ArrayList<QuestionDto> dtos = new ArrayList<QuestionDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();

			// 퀘리 설정
			String query = "select questionNo, question, writer, writeTime, courseNumber, questionTitle from question";
			preparedStatement = connection.prepareStatement(query);

			// 퀘리 실행하겨 resultSet에 저장
			resultSet = preparedStatement.executeQuery();

			// dtos 배열에 계속 추가
			while (resultSet.next()) {

				// 데이터 추출
				int questionNo2 = resultSet.getInt("questionNo");
				String question2 = resultSet.getString("question");
				String writer2 = resultSet.getString("writer");
				Timestamp writeTime2 = resultSet.getTimestamp("writeTime");
				String courseNumber2 = resultSet.getString("courseNumber");
				String questionTitle2 = resultSet.getString("questionTitle");

				// dto 추가
				QuestionDto dto = new QuestionDto(questionNo2, question2, writer2, writeTime2, courseNumber2,
						questionTitle2);
				dtos.add(dto);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return dtos;
	}

	
	/********************************************************* 질문글 수정 **************************************************/
	public void modify(String bId, String bName, String bTitle, String bContent) {
		// TODO Auto-generated method stub

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dataSource.getConnection();

			String query = "update mvc_board set bName = ?, bTitle = ?, bContent = ? where bId = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setInt(4, Integer.parseInt(bId));
			int rn = preparedStatement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	/************************************************************질문 내용보기***********************************************/
	
	public QuestionDto contentView(String questionNo) {
		// TODO Auto-generated method stub
		
		//변수 설정
		QuestionDto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			//디비 연결
			connection = dataSource.getConnection();
			
			//퀘리 설정 및 실행
			String query = "select * from question where questionNo = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, questionNo);
			resultSet = preparedStatement.executeQuery();
			
			
			if(resultSet.next()) {
				// 데이터 추출
				int questionNo2 = resultSet.getInt("questionNo");
				String question2 = resultSet.getString("question");
				String writer2 = resultSet.getString("writer");
				Timestamp writeTime2 = resultSet.getTimestamp("writeTime");
				String courseNumber2 = resultSet.getString("courseNumber");
				String questionTitle2 = resultSet.getString("questionTitle");

				// dto 추가
				dto = new QuestionDto(questionNo2, question2, writer2, writeTime2, courseNumber2, questionTitle2);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	
	
	/************************************************************질문 삭제***********************************************/
	public void delete(String questionNo) {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			//디비 연결 및 퀘리 실행
			connection = dataSource.getConnection();
			String query = "delete from question where questionNo = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, questionNo);
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	

}
