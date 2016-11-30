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

	/*************************************************** ������ ***************************************************/
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

	/**************************************** ��� ���� *********************************************************/
	public void insert(String question, String writer, String courseNumber, String questionTitle) {
		// TODO Auto-generated method stub

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// DB connection
			connection = dataSource.getConnection();
			String query = "insert into question (question, writer, courseNumber, questionTitle) values (?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);

			// ? �� ����
			preparedStatement.setString(1, question);
			preparedStatement.setString(2, writer);
			preparedStatement.setString(3, courseNumber);
			preparedStatement.setString(4, questionTitle);

			// ���� ����
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

	/**************************************** ���� ����Ʈ �������� *********************************************************/
	public ArrayList<QuestionDto> list() {

		// �����
		ArrayList<QuestionDto> dtos = new ArrayList<QuestionDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();

			// ���� ����
			String query = "select questionNo, question, writer, writeTime, courseNumber, questionTitle from question";
			preparedStatement = connection.prepareStatement(query);

			// ���� �����ϰ� resultSet�� ����
			resultSet = preparedStatement.executeQuery();

			// dtos �迭�� ��� �߰�
			while (resultSet.next()) {

				// ������ ����
				int questionNo2 = resultSet.getInt("questionNo");
				String question2 = resultSet.getString("question");
				String writer2 = resultSet.getString("writer");
				Timestamp writeTime2 = resultSet.getTimestamp("writeTime");
				String courseNumber2 = resultSet.getString("courseNumber");
				String questionTitle2 = resultSet.getString("questionTitle");

				// dto �߰�
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

	
	/********************************************************* ������ ���� **************************************************/
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
	
	/************************************************************���� ���뺸��***********************************************/
	
	public QuestionDto contentView(String questionNo) {
		// TODO Auto-generated method stub
		
		//���� ����
		QuestionDto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			//��� ����
			connection = dataSource.getConnection();
			
			//���� ���� �� ����
			String query = "select * from question where questionNo = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, questionNo);
			resultSet = preparedStatement.executeQuery();
			
			
			if(resultSet.next()) {
				// ������ ����
				int questionNo2 = resultSet.getInt("questionNo");
				String question2 = resultSet.getString("question");
				String writer2 = resultSet.getString("writer");
				Timestamp writeTime2 = resultSet.getTimestamp("writeTime");
				String courseNumber2 = resultSet.getString("courseNumber");
				String questionTitle2 = resultSet.getString("questionTitle");

				// dto �߰�
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
	
	
	
	/************************************************************���� ����***********************************************/
	public void delete(String questionNo) {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			//��� ���� �� ���� ����
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
