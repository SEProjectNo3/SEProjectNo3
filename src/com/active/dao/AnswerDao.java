package com.active.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.active.dto.AnswerDto;


public class AnswerDao {

	DataSource dataSource;

	/*************************************************** 생성자 ***************************************************/
	public AnswerDao() 
	{		
		// DB connection
				try {
					Context context = new InitialContext();
					dataSource = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
	}


	
	/****************************************************답변 db에 저장***********************************************/
	public void reply(String writer, String content, String questionNo) {
		// TODO Auto-generated method stub
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		try {
			
			//디비 연결 
			connection = dataSource.getConnection();
			String query = "insert into response (writer, content, questionNo) values (?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);
			
			//퀘리 설정
			preparedStatement.setString(1, writer);
			preparedStatement.setString(2, content);
			preparedStatement.setString(3, questionNo);
			
			//실행
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
	
	
	
	/**************************************** 답변 리스트 db에서 가져오기***********************************************/
	public ArrayList <AnswerDto> reply_list(String questionNo) {

		// 선언부
		ArrayList<AnswerDto> dtos = new ArrayList<AnswerDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		
		try {
			connection = dataSource.getConnection();

			// 퀘리 설정
			String query = "select responseNo, writer, writeTime, content, questionNo from response where questionNo = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, questionNo);
			
			// 퀘리 실행하겨 resultSet에 저장
			resultSet = preparedStatement.executeQuery();

			// dtos 배열에 계속 추가
			while (resultSet.next()) {

				// 데이터 추출
				Integer responseNo1 = resultSet.getInt("responseNo");
				String writer1 = resultSet.getString("writer");
				//String writTime1 = resultSet.getString("writeTime");
				String content1 = resultSet.getString("content");
				
				// dto 추가
				AnswerDto dto = new AnswerDto(responseNo1, writer1, null, content1, null);
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
