<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html lang="en">

<head>


<title>QnA</title>

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
	<jsp:include page="/main-login.jsp" flush="true" />

	<!-- Page Content -->
	<div class="container">

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Q&A</h1>
				<ol class="breadcrumb">
					<li><a href="main.jsp">Home</a></li>
				</ol>
			</div>
		</div>
		<!-- /.row -->

		<!-- Content Row -->
		<div class="row">
			<div class="col-lg-12">
				<div class="panel-group" id="accordion">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a class="accordion-toggle" data-toggle="collapse"
									data-parent="#accordion" href="#content1"> 질문있어요!</a>
							</h4>
						</div>
						<div id="content1" class="panel-collapse collapse">
							<div class="panel-body">내용은 이거에요</div>
						</div>
					</div>

					<!-- /.panel -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a class="accordion-toggle" data-toggle="collapse"
									data-parent="#accordion" href="#content2"> 박여은은 왜 까만건가요??</a>
							</h4>
						</div>
						<div id="content2" class="panel-collapse collapse">
							<div class="panel-body">몇년동안 봤는데 계속 까맣더라고요</div>
						</div>
					</div>
					<!-- /.panel -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a class="accordion-toggle" data-toggle="collapse"
									data-parent="#accordion" href="#content3">고준희 여친은 민주인가요
									은주인가요?</a>
							</h4>
						</div>
						<div id="content3" class="panel-collapse collapse">
							<div class="panel-body">헷갈리네요...</div>
						</div>
					</div>
					<!-- /.panel -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a class="accordion-toggle" data-toggle="collapse"
									data-parent="#accordion" href="#content4"> 리준은 회색을 왜그렇게
									좋아하나요?</a>
							</h4>
						</div>
						<div id="content4" class="panel-collapse collapse">
							<div class="panel-body">근데 저도좋아해요..</div>
						</div>
					</div>
					<!-- /.panel -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a class="accordion-toggle" data-toggle="collapse"
									data-parent="#accordion" href="#content5"> 이제 슬슬 귀찮네요</a>
							</h4>
						</div>
						<div id="content5" class="panel-collapse collapse">
							<div class="panel-body">ㅎㅁㄴㅇㄻㅇㄴ</div>
						</div>
					</div>
					<!-- /.panel -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a class="accordion-toggle" data-toggle="collapse"
									data-parent="#accordion" href="#content6"> 하나만더!</a>
							</h4>
						</div>
						<div id="content6" class="panel-collapse collapse">
							<div class="panel-body">ㅎㅁㅇㄴㄻㅇㄴㅂㅈㄷ갸ㅓㄷㅈㄱ</div>
						</div>
					</div>
				</div>
				<!-- /.panel-group -->
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->

		<hr>

		<jsp:include page="/footer.jsp" flush="true" />

	</div>
	<!-- /.container -->

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

</body>

</html>
