<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>교수 소개 및 강좌 목록</title>

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

	<!-- Page Content -->
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<div class="thumbnail">
					<img class="img-responsive" src="img/test.jpg" alt="Cinque Terre"
						width="800" height="300">
					<div class="caption-full">
						<div class="text-right">
							<a href="update-courseinfo.jsp" class="btn btn-success">내용
								수정하기</a>
						</div>

						<h4>
							<a href="#">최은만 교수님</a>`
						</h4>
						<p>
							See more snippets like these online store reviews at <a
								target="_blank" href="http://bootsnipp.com">Bootsnipp -
								http://bootsnipp.com</a>.
						</p>
						<p>
							Want to make these reviews work? Check out <strong><a
								href="http://maxoffsky.com/code-blog/laravel-shop-tutorial-1-building-a-review-system/">this
									building a review system tutorial</a> </strong>over at maxoffsky.com!
						</p>
						<p id="content">전공분야 소프트웨어공학 세부연구분야 소프트웨어 테스팅, 소프트웨어 설계 학사학위과정 동국대학교 전자계산학과
							이학사 석사학위과정 KAIST 전산학과 공학 석사 대표저서 새로 쓴 소프트웨어공학, 정익사, 2014 UML을 이용한
							시스템 분석설계, 생능출판사, 2010 객체지향 소프트웨어 공학, 사이텍미디어. 2005</p>
					</div>
					<div class="ratings">
						<p class="pull-right"><a href=#review>3 reviews</a></p>
						<p>
							<span class="glyphicon glyphicon-star"></span> <span
								class="glyphicon glyphicon-star"></span> <span
								class="glyphicon glyphicon-star"></span> <span
								class="glyphicon glyphicon-star"></span> <span
								class="glyphicon glyphicon-star-empty"></span> 4.0 stars
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<h2>
					강좌 목록&nbsp;<a href="lecture-evaluation.jsp" class="btn btn-default">강의 수정</a>
				</h2>
				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">
						1단원&nbsp;<a href="lecture-evaluation.jsp" class="btn btn-default">진단 평가 추가</a>
					</div>

					<ul class="list-group">
						<li class="list-group-item">사용 사례&nbsp;<a
							href="lecture-evaluation.jsp" class="btn btn-default">퀴즈 추가</a></li>
						<li class="list-group-item">사용 다이어그램&nbsp;<a
							href="lecture-evaluation.jsp" class="btn btn-default">퀴즈 추가</a></li>
					</ul>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						2단원&nbsp;<a href="lecture-evaluation.jsp" class="btn btn-default">진단평가 추가</a>
					</div>
					<!-- List group -->
					<ul class="list-group">

						<li class="list-group-item"><a href="studying.jsp">객체지향개념(1)사용사례&nbsp;</a>
							<a href="lecture-evaluation.jsp" class="btn btn-default">퀴즈
								추가</a></li>
						<li class="list-group-item"><a href="studying.jsp">객체지향개념(2)사용사례&nbsp;</a>
							<a href="lecture-evaluation.jsp" class="btn btn-default">퀴즈
								추가</a></li>
						<li class="list-group-item"><a href="studying.jsp">객체지향개념(3)사용사례&nbsp;</a>
							<a href="lecture-evaluation.jsp" class="btn btn-default">퀴즈
								추가</a></li>
						<li class="list-group-item"><a href="studying.jsp">클래스다이어그램&nbsp;</a> 
						<a href="lecture-evaluation.jsp" class="btn btn-default">퀴즈 추가</a>
						</li>

					</ul>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						3단원<a href="lecture-evaluation.jsp" class="btn btn">진단 평가 추가&nbsp;</a>
					</div>

					<ul class="list-group">
						<li class="list-group-item">아키텍쳐설계(1)사용 사례&nbsp;<a
							href="lecture-evaluation.jsp" class="btn btn-default">퀴즈 추가</a></li>
						<li class="list-group-item">아키텍쳐설계(2)사용 사례&nbsp;<a
							href="lecture-evaluation.jsp" class="btn btn-default">퀴즈 추가</a></li>
						<li class="list-group-item">아키텍쳐설계(3)사용 사례&nbsp;<a
							href="lecture-evaluation.jsp" class="btn btn-default">퀴즈 추가</a></li>
						<li class="list-group-item">시퀀스 다이어그램사용 사례&nbsp;<a
							href="lecture-evaluation.jsp" class="btn btn-default">퀴즈 추가</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<div class="container" id="review">
		<div class="well">
			<textarea class="form-control col-sm-5" rows="5"></textarea>
			<div class="text-right">
				<a class="btn btn-success">댓글쓰기?</a>
			</div>

			<hr>

			<div class="row">
				<div class="col-md-12">
					<span class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star-empty"></span> Anonymous <span
						class="pull-right">10 days ago</span>
					<p>This product was great in terms of quality. I would
						definitely buy another!</p>
				</div>
			</div>

			<hr>

			<div class="row">
				<div class="col-md-12">
					<span class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star-empty"></span> Anonymous <span
						class="pull-right">12 days ago</span>
					<p>I've alredy ordered another one!</p>
				</div>
			</div>

			<hr>

			<div class="row">
				<div class="col-md-12">
					<span class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star"></span> <span
						class="glyphicon glyphicon-star-empty"></span> Anonymous <span
						class="pull-right">15 days ago</span>
					<p>I've seen some better than this, but not at this price. I
						definitely recommend this item.</p>
				</div>
			</div>
		</div>
	</div>
	<!-- /.container -->

	<hr>

	<jsp:include page="/footer.jsp" flush="true" />
	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

</body>
</html>