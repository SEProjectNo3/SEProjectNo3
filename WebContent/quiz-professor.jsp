<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.active.constant.*"%>

<%
	request.setCharacterEncoding("UTF-8");
	request.getParameter("review_list");
%>
<!DOCTYPE html>
<html lang="en">

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>강의평가</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/modern-business.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
	<jsp:include page="/header.jsp" flush="true" />

	<!-- Page Content -->
	<!-- Page Heading/Breadcrumbs -->
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				소프트웨어공학 <small>테스팅</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="course-prof.jsp">강의목록</a>으로 돌아가기</li>
			</ol>
		</div>
	</div>
	<div class="row">
		<div class="col-md-8">

			<video width="750" height="500" controls loop>
				<source src="video/test.mp4" type="video/mp4">
				<source src="video/test.ogg" type="video/ogg">

			</video>
			<!-- <video src="video/test.mp4" width="800" height="500" controls></video> -->

		</div>

		<!-- 등록된 퀴즈 리스트 보여주는 컨테이너 -->
		<div class="col-md-4">
			<br> <br> <br> <br> <br> <br> <br>
			<center>
				<div class="row">

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
									<c:set var="lectureId" value="${quiz.lectureId}" />
								</tr>
								<tr>
									<td>설명:${quiz.explanation}</td>
									<a href="Quiz.do?cmd=quiz_delete&quizNo=${quiz.quizNo}"><span
										class="glyphicon glyphicon-trash pull-right"> </span> </a>
									<a href="Quiz.do?cmd=quiz_modify_form&quizNo=${quiz.quizNo}">
										<span class="glyphicon glyphicon-wrench pull-right"> </span>
									</a>
								</tr>
								<tr>
									<c:forEach var="quizChoice" items="${quizChoiceList}">
										<tr class="record">
											<td>${quizChoice.quizNo}:QuizNo</td>
											<td>-${quizChoice.choiceNumber}번 보기-&nbsp&nbsp&nbsp</td>
											<td>-> ${quizChoice.answer}</td>
										</tr>
									</c:forEach>
							</table>
						</c:forEach>
					</c:if>

					<div class="col=sm=2"></div>
				</div>
			</center>
		</div>

		<!-- 퀴즈 등록 partial 페이지 -->
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="col-sm-2"></div>
				<div class="col-sm-8">
					<h1 class="page-header">퀴즈 추가하기</h1>
					<ol class="breadcrumb">
						<strong><li>${lectureId}</li></strong>
					</ol>
					<form action="Quiz.do" method="post">
						<input type="hidden" name="cmd" value="quiz_write"> <input
							type="hidden" name="lectureId" value="${lectureId}">
						<table class="table table_type_normal">
							<colgroup>
								<col width="20%" />
								<col width="80%" />
							</colgroup>
							<tr>
								<th>퀴즈</th>
								<td><input class="text" type="text" name="question"
									placeholder="퀴즈 제목"></td>
							</tr>
							<tr>
								<th>퀴즈 팝업 시간</th>
								<td><input class="text" type="text" name="quizTime"
									placeholder="초단위로 입력해주세요"></td>
							</tr>

							<tr>
								<th>보기 내용</th>
								<td>1번:&nbsp;<input type="text" name="choiceNumber1"
									placeholder="문제내용" size="80"><br> 2번:&nbsp;<input
									type="text" name="choiceNumber2" placeholder="문제내용" size="80"><br>
									3번:&nbsp;<input type="text" name="choiceNumber3"
									placeholder="문제내용" size="80"><br> 4번:&nbsp;<input
									type="text" name="choiceNumber4" placeholder="문제내용" size="80">
								</td>
							</tr>
							<tr>
								<th>정답</th>
								<td><label class="radio-inline"> <input
										type="radio" name="answer" value="1" checked> 1번
								</label> <label class="radio-inline"> <input type="radio"
										name="answer" value="2" checked> 2번
								</label> <label class="radio-inline"> <input type="radio"
										name="answer" value="3" checked> 3번
								</label> <label class="radio-inline"> <input type="radio"
										name="answer" value="4" checked> 4번
								</label></td>
							</tr>
							<tr>
								<th>해설</th>
								<td><textarea cols="100" rows="10" name="explanation"
										placeholder="문제설명"></textarea></td>
							</tr>
						</table>
						<br> <br>
						<center>
							<input type="submit" value="등록"> <input type="reset"
								value="다시 작성"> <input type="button" value="목록"
								onclick="location.href='Quiz.do?cmd=quizList'">
						</center>
					</form>
				</div>
			</div>
		</div>

		<jsp:include page="/footer.jsp" flush="true" />
	</div>
	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>