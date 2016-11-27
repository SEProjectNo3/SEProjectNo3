<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="en">

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<script src='css/star/jquery.js' type="text/javascript"></script>
<script src='css/star/jquery.MetaData.js' type="text/javascript" language="javascript"></script>
<script src='css/star/jquery.rating.js' type="text/javascript" language="javascript"></script>
<link href='css/star/jquery.rating.css' type="text/css" rel="stylesheet" />

<title>강의평가</title>

<link href="css/yeoeun.css" rel="stylesheet">

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/modern-business.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

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
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<div class="thumbnail">
					<img class="img-responsive" src="img/test.jpg" alt="Cinque Terre"
						width="800" height="300">
					<div class="caption-full">


						<h4>
							<a href="#">최은만 교수님</a>
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
						<p>전공분야 소프트웨어공학 세부연구분야 소프트웨어 테스팅, 소프트웨어 설계 학사학위과정 동국대학교 전자계산학과
							이학사 석사학위과정 KAIST 전산학과 공학 석사 대표저서 새로 쓴 소프트웨어공학, 정익사, 2014 UML을 이용한
							시스템 분석설계, 생능출판사, 2010 객체지향 소프트웨어 공학, 사이텍미디어. 2005</p>
					</div>
					<div class="ratings">
						<p class="pull-right">3 reviews</p>
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


	<!-- 강의 평가  -->
	<div class="container-fluid">
		
		<div class="row-fluid">
				
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
			<h2>강의 평가하기</h2>
			
				<form action ="Review.do" method="post">
					<input type="hidden" name="cmd" value="review_proc">
					
					<input class="star" type="radio" name="rate" value="1" />
					<input class="star"	type="radio" name="rate" value="2" /> 
					<input class="star" type="radio" name="rate" value="3" />
					<input class="star" type="radio" name="rate" value="4" /> 
					<input class="star" type="radio" name="rate" value="5" />
				
					<textarea class="form-control col-sm-5" name="content" rows="5"></textarea>
					<div class="well">
						<div class="text-right">
							<input type="submit" class="btn btn-success" value="평가하기"/>
						</div>
						<hr>
	
						<div class="row">
							<div class="col-md-12">
								<span class="glyphicon glyphicon-star"></span> <span
									class="glyphicon glyphicon-star"></span> <span
									class="glyphicon glyphicon-star"></span> <span
									class="glyphicon glyphicon-star"></span> <span
									class="glyphicon glyphicon-star-empty"></span> 고성욱 <span
									class="pull-right">12 days ago</span>
								<p>드디어 올렸다 시발~!</p>
							</div>
						</div>
	
						<hr>
	
						<div class="row">
							<div class="col-md-12">
								<span class="glyphicon glyphicon-star"></span> <span
									class="glyphicon glyphicon-star"></span> <span
									class="glyphicon glyphicon-star"></span> <span
									class="glyphicon glyphicon-star"></span> <span
									class="glyphicon glyphicon-star-empty"></span> 한승헌 <span
									class="pull-right">15 days ago</span>
								<p>오늘은 9500원씩 나왔습니다. 계산 부탁드립니다. 이따가 하자 ㅠㅠ</p>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<hr>
<jsp:include page="/footer.jsp" flush="true" />
	</div>
	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>