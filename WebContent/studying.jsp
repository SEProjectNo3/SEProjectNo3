<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html lang="en">

<head>
<title>강의수강</title>
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

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="col-sm-2"></div>
			<div class="col-sm-10">
				<div class="col-md-8" align="center">
					<h2>강의 이름</h2>
				</div>
			</div>
		</div>
	</div>
	<hr>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="col-sm-2">
				<!-- Single button -->
				
					<div class="btn-group-vertical">
						<center>
							<a href="#" class="list-group-item ">단원명</a>
						</center>
						
						<button class="btn btn-default btn dropdown-toggle" type="button"
							data-toggle="dropdown" aria-expanded="false">
							1단원 <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">1-1</a></li>
							<li><a href="#">1-2</a></li>
							<li><a href="#">1-3</a></li>
						</ul>
					</div>
					
										<div class="btn-group-vertical">
						<button class="btn btn-default btn dropdown-toggle" type="button"
							data-toggle="dropdown" aria-expanded="false">
							2단원 <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">2-1</a></li>
							<li><a href="#">2-2</a></li>
							<li><a href="#">2-3</a></li>
						</ul>
						
					</div>
					
										<div class="btn-group-vertical">
						<button class="btn btn-default btn dropdown-toggle" type="button"
							data-toggle="dropdown" aria-expanded="false">
							3단원 <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">3-1</a></li>
							<li><a href="#">3-2</a></li>
							<li><a href="#">3-3</a></li>
						</ul>
				</div>
					<div class="btn-group-vertical">
						<button class="btn btn-default btn dropdown-toggle" type="button"
							data-toggle="dropdown" aria-expanded="false">
							4단원 <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">4-1</a></li>
							<li><a href="#">4-2</a></li>
							<li><a href="#">4-3</a></li>
						</ul>

					</div>
				
			</div>

			<div class="col-sm-10">

				<video width="800" height="500" controls loop>
					<source src="video/test.mp4" type="video/mp4">
					<source src="video/test.ogg" type="video/ogg">
					머래시불
				</video>

				<!-- <video src="video/test.mp4" width="800" height="500" controls></video> -->
			</div>
		</div>
	</div>
	<hr>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="col-sm-4"></div>
			<div class="col-sm-6">
				<div class="col-sm-8">
					<textarea class="form-control col-sm-6" rows="5"></textarea>
					<div class="text-right">
						<a class="btn btn-success">댓글쓰기</a>
					</div>
					<hr>
				</div>

				<div class="col-md-8">
					<span class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star-empty"></span> Anonymous <span
						class="pull-right">10 days ago</span>
					<p>This product was great in terms of quality. I would
						definitely buy another!</p>
					<a href="삭제"><span class="glyphicon glyphicon-trash pull-right">
					</span> </a> <a href="수정"> <span
						class="glyphicon glyphicon-wrench pull-right"> </span>
					</a>
				</div>


				<div class="col-md-8">
					<hr>
					<span class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star-empty"></span> Anonymous <span
						class="pull-right">12 days ago</span>
					<p>I've alredy ordered another one!</p>
					<a href="삭제"><span class="glyphicon glyphicon-trash pull-right">
					</span> </a> <a href="수정"><span
						class="glyphicon glyphicon-wrench pull-right"> </span> </a>

				</div>


				<div class="col-md-8">
					<hr>
					<span class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star-empty"></span> Anonymous <span
						class="pull-right">15 days ago</span>
					<p>I've seen some better than this, but not at this price. I
						definitely recommend this item.</p>
					<a href="삭제"><span class="glyphicon glyphicon-trash pull-right">
					</span> </a> <a href="수정"><span
						class="glyphicon glyphicon-wrench pull-right"> </span> </a>
				</div>
			</div>
		</div>
	</div>
	<hr>

	<jsp:include page="/footer.jsp" flush="true" />
	<!-- /.container -->

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>


</body>
</html>