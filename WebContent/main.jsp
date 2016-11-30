<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Dongguk University 2016-2 Web Project</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/modern-business.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="css/seungheon.css" rel="stylesheet">

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

	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
			<div class="item active">
				<img src="img/bg1.jpg" alt="Chania" class="fill">
				<div class="carousel-caption">
					<h2>베스트 강좌1</h2>
				</div>
			</div>

			<div class="item">
				<img src="img/bg1.jpg" alt="Chania" class="fill">
				<div class="carousel-caption">
					<h2>베스트 강좌2</h2>
				</div>
			</div>

			<div class="item">
				<img src="img/bg2.jpg" alt="Chania" class="fill">
				<div class="carousel-caption">
					<h2>베스트 강좌3</h2>
				</div>
			</div>
		</div>

		<!-- Left and right controls -->
		<a class="left carousel-control" href="#myCarousel" role="button"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
			aria-hidden="true"></span> <span class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#myCarousel" role="button"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>

	<br>

	<hr>

	<jsp:include page="/footer.jsp" flush="true" />

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

	<!-- Script to Activate the Carousel -->
	<script>
    $('.carousel').carousel({
        interval: 3000
    })
    </script>


</body>
</html>