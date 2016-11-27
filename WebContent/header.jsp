<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%
   request.setCharacterEncoding("UTF-8");
%>

<!-- Navigation-->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="main.jsp"><font color="orange">Dongguk
					E-learning Platform.</font></a>
		</div>

		<form class="navbar-form navbar-left" role="search">
			<div class="input-group">
				<div class="input-group-btn">
					<button type="button" class="btn btn-default dropdown-toggle"
						data-toggle="dropdown" aria-expanded="false">
						<span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li id="1"><a href="#">강좌명</a></li>
						<li id="2"><a href="#">교수명</a></li>
						<li id="3"><a href="#">학수번호</a></li>
					</ul>
					
					<input type="text" class="form-control" placeholder="강좌명">
					
					<button type="submit" class="btn btn-default">
						<i class="fa fa-search"></i>
					</button>
				</div>
			</div>
		</form>

		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="" data-target="#modal_Login" type="button"
					data-toggle="modal">로그인</a></li>


				<li ><a href="course-list-search.jsp" type = "button">
				강좌목록</a>
				</li>

				<li ><a href="course-list-search.jsp" type = "button">
				강의평가</a>
				</li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Category <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="full-width.html">Full Width Page</a></li>
						<li><a href="sidebar.html">Sidebar Page</a></li>
						<li><a href="faq.html">FAQ</a></li>
						<li><a href="404.html">404</a></li>
						<li><a href="pricing.html">Pricing Table</a></li>
					</ul></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container -->
</nav>



