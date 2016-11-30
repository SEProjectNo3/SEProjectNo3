<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%
   request.setCharacterEncoding("UTF-8");
%>

<div class="col-md-4">
			<br> <br> <br> <br> <br> <br> <br>
			<center>
				<div class="row">
					<div class="col-md-12">
						<c:if test="${quizList != null}">
							<c:forEach var="quiz" items="${quizList}">
								<table
									style="cursor: hand; width: 100%; height: 100px; margin: 2%">
									<tr>
										<td colspan="5" style="font-weight: bold">퀴즈번호:${quiz.quizNo}
										</td>
									</tr>
									<tr>
										<td colspan="5" style="font-weight: bold">문제:${quiz.question}
										</td>
									</tr>
									<tr>
										<td colspan="5" style="font-weight: bold">팝업시간:${quiz.quizTime}
										</td>
									</tr>
									<tr>
										<td colspan="5" style="font-weight: bold">정답:${quiz.answer}
										</td>
									</tr>
									<tr>
										<td colspan="5" style="font-weight: bold">강의번호:${quiz.lectureId}
										</td>
									</tr>
									<tr>
										<td>설명:${quiz.explanation}</td>
										<a href="Quiz.do?cmd=quiz_delete&quizNo=${quiz.quizNo}"><span class="glyphicon glyphicon-trash pull-right">
										</span>
										</a> 
										<a href="Quiz.do?cmd=quiz_modify_form&quizNo=${quiz.quizNo}"> <span class="glyphicon glyphicon-wrench pull-right">
										</span>
										</a>
									</tr>
								</table>
							</c:forEach>
						</c:if>
					</div>
					<div class="col=sm=2"></div>
				</div>
			</center>
		</div>