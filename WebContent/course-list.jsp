<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>수강목록</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/modern-business.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
	<jsp:include page="/header.jsp" flush="true" />
	<br>
	<br>


	<center>
		<h2>수강목록</h2>
	</center>
	<div class="container-fluid">
		<hr>

		<div class="row-fluid">
			<div class="col-sm-4"></div>
			<div class="col-sm-6">


				<div class="col-md-8">
					<a href="studying.jsp"><b>소프트웨어공학</b></a> <a
						href="QnA.jsp" class="btn btn-default pull-right">
						Q&A
						</a> <br>
					<br>
					<p align="right">최은만</p>
					<div class="progress">
						<div class="progress-bar" role="progressbar" aria-valuenow="60"
							aria-valuemin="0" aria-valuemax="100" style="width: 100%;">
							100%</div>
					</div>
				</div>

				<div class="col-md-8">
					<hr>
					<a href="studying.jsp"><b><p>컴퓨터구조</p></b></a> <a
						href="QnA.jsp" class="btn btn-default pull-right">
						Q&A
						</a> <br>
					<br>
					<p align="right">장태무</p>
					<div class="progress-bar" role="progressbar" aria-valuenow="60"
						aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
						60%</div>
				</div>

				<div class="col-md-8">
					<hr>
					<a href="studying.jsp"><b><p>데이터통신입문</p></b></a> <a
						href="QnA.jsp" class="btn btn-default pull-right">
						Q&A
						</a> <br>
					<br>
					<p align="right">안종석</p>
					<div class="progress-bar" role="progressbar" aria-valuenow="60"
						aria-valuemin="0" aria-valuemax="100" style="width: 70%;">
						70%</div>
				</div>


			</div>
		</div>
	</div>


	<hr>

	<jsp:include page="/footer.jsp" flush="true" />
	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>