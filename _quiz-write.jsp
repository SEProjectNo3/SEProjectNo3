<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%
   request.setCharacterEncoding("UTF-8");
%>
<div class="container-fluid">

	<div class="row-fluid">

		<div class="col-sm-2"></div>
		<div class="col-sm-8">
			<h2>강의 평가하기</h2>
			<form action="Quiz.do" method="post">
				<input type="hidden" name="cmd" value="quiz_write">
				<table>
					<tr>
						<th>문제</th>
						<td><input type="text" name="question"></td>
					</tr>
					<tr>
						<th>퀴즈 팝업 시간</th>
						<td><input type="text" name="quizTime"></td>
					</tr>
					<tr>
						<th>정답</th>
						<td><input type="text" name="answer"></td>
					</tr>
					<tr>
						<th>보기 내용</th>
						<td><input type="text" name="choiceNumber1"> 1번 <input
							type="text" name="choiceNumber2"> 2번 <input type="text"
							name="choiceNumber3"> 3번 <input type="text"
							name="choiceNumber4"> 4번</td>
					</tr>
					<tr>
						<th>문제 설명</th>
						<td><textarea cols="70" rows="10" name="explanation"></textarea></td>
					</tr>
					<tr>
						<th>강좌 번호</th>
						<td><input type="text" name="lectureId"></td>
					</tr>
				</table>
				<br> <br> <input type="submit" value="등록"> <input
					type="reset" value="다시 작성"> <input type="button" value="목록"
					onclick="location.href='Quiz.do?cmd=quizList'">
			</form>
		</div>
	</div>
