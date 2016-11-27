<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>강좌목록-교수용</title>
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
	<jsp:include page="/main-login.jsp" flush="true" />

	<center>
		<br> <br>
		<h2>강좌목록</h2>
	</center>
		<div class="text-center">
			<a href="lecture-evaluation.jsp" class="btn btn-primary">강좌추가</a>
		</div>
	<hr>
	<div class="container-fluid">

		<div class="row-fluid">

			<div class="col-sm-4"></div>
			<div class="col-sm-6">

				<div class="col-md-8">
					<br> <b><p>주니어디자인프로젝트</p></b>
					<div class="text-right">
						<a href="lecture-add.jsp" class="btn btn-primary">강의추가</a>
					</div>

					<hr>

					<b><p>컴퓨터구조</p></b>
					<div class="text-right">
						<a href="lecture-add.jsp" class="btn btn-primary">강의추가</a>
					</div>


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