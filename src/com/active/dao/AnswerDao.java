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

	/*************************************************** ������ ***************************************************/
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


	
	/****************************************************�亯 db�� ����***********************************************/
	public void reply(String writer, String content, String questionNo) {
		// TODO Auto-generated method stub
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		try {
			
			//��� ���� 
			connection = dataSource.getConnection();
			String query = "insert into response (writer, content, questionNo) values (?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);
			
			//���� ����
			preparedStatement.setString(1, writer);
			preparedStatement.setString(2, content);
			preparedStatement.setString(3, questionNo);
			
			//����
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
	
	
	
	/**************************************** �亯 ����Ʈ db���� ��������***********************************************/
	public ArrayList <AnswerDto> reply_list(String questionNo) {

		// �����
		ArrayList<AnswerDto> dtos = new ArrayList<AnswerDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		
		try {
			connection = dataSource.getConnection();

			// ���� ����
			String query = "select responseNo, writer, writeTime, content, questionNo from response where questionNo = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, questionNo);
			
			// ���� �����ϰ� resultSet�� ����
			resultSet = preparedStatement.executeQuery();

			// dtos �迭�� ��� �߰�
			while (resultSet.next()) {

				// ������ ����
				Integer responseNo1 = resultSet.getInt("responseNo");
				String writer1 = resultSet.getString("writer");
				//String writTime1 = resultSet.getString("writeTime");
				String content1 = resultSet.getString("content");
				
				// dto �߰�
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
