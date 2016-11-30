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
	<jsp:include page="/index-login.jsp" flush="true" />

	<!-- Header Carousel -->
	<header id="myCarousel" class="carousel slide">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner">
			<div class="item active">
				<div class="fill" style="background-image: url('img/bg1.jpg');"></div>
				<div class="carousel-caption">
					<h2>
						<font color="black">베스트 강좌 1</font>
					</h2>
				</div>
			</div>
			<div class="item">
				<div class="fill" style="background-image: url('img/bg2.jpg');"></div>
				<div class="carousel-caption">
					<h2>
						<font color="black">베스트 강좌 2</font>
					</h2>
				</div>
			</div>
			<div class="item">
				<div class="fill" style="background-image: url('img/bg3.jpg');"></div>
				<div class="carousel-caption">
					<h2>
						<font color="black">베스트 강좌 3</font>
					</h2>
				</div>
			</div>
		</div>

		<!-- Controls -->
		<a class="left carousel-control" href="#myCarousel" data-slide="prev">
			<span class="icon-prev"></span>
		</a> <a class="right carousel-control" href="#myCarousel"
			data-slide="next"> <span class="icon-next"></span>
		</a>
	</header>
	<br>

	<center>
		<h2>강좌목록</h2>
	</center>
	<div class="container-fluid">
		<hr>

		<div class="row-fluid">
			<div class="col-sm-4"></div>
			<div class="col-sm-6">
				<div class="col-md-8">
					<span class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star"></span> <span class="pull-right">최은만</span>
					<p>소프트웨어공학</p>

					<hr>

					<span class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star-empty"></span> <span
						class="pull-right">장태무</span>
					<p>컴퓨터구조</p>

					<hr>

					<span class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star-empty"></span> <span
						class="pull-right"> 안종석</span>
					<p>데이터통신입문</p>

				</div>
			</div>
		</div>
	</div>
	<nav align="center">
		<ul class="pagination">
			<li><a href="#" aria-label="Previous"> <span
					aria-hidden="true">&laquo;</span>
			</a></li>
			<li><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">5</a></li>
			<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</ul>
	</nav>
	<hr>

	<jsp:include page="/footer.jsp" flush="true" />

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

	<!-- Script to Activate the Carousel -->
	<script>
    $('.carousel').carousel({
        interval: 5000 
    })
    </script>
</body>
</html>