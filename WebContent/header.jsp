<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ page import="java.util.*"%>
<%
   request.setCharacterEncoding("UTF-8");
%>

<link href="css/yeoeun.css" rel="stylesheet">

<script>

function logout() {
	
	alert("성공적으로 로그아웃 되었습니다.");
}

</script>

<!-- Navigation-->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<font color="orange">
				<a class="navbar-brand" href="index.jsp">Dongguk E-learning Platform.</a>
			</font>
		</div>
		
		<!-- 강의 검색 부분 -->
		<form class="navbar-form navbar-left" role="search" action="Course.do">
			
			<input type="hidden" name="cmd" value="search_course">
			<div class="input-group">
		
				<select name="cond" class="selectbox" >
					<option value="all">검색조건</option>
				    <option value="name">강좌명</option>
				    <option value="number">학수번호</option>
				    <option value="professor">교수명</option>
				</select>
					
				<div class="input-group-btn">
					<span class="input-group-btn">
					<input type="text" name="content" class="form-control" placeholder="검색명">
						<button type="submit" class="btn btn-default">
							<i class="fa fa-search"></i>
						</button>
					</span>
					
				</div>
				
			</div>
		</form>

		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li style="color:#fff">
					<c:if test="${user != null }">
						<a href="#">${user.userName } 님 환영합니다 .</a>  
					</c:if>
				</li>
				
				<li>
					<c:choose>
						<c:when test="${user != null }">
							<a href="<%=request.getContextPath()%>/Login.do?cmd=logout" onClick="logout()">로그아웃</a>
						</c:when>
					
						<c:otherwise>
							<a href="" data-target="#modal_Login" type="button" data-toggle="modal">로그인</a>
						</c:otherwise>
					</c:choose>
				</li>
				
				<li class="dropdown">
					<c:if test="${user != null }">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">마이페이지 <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="course-list.jsp">수강목록</a></li>
						<c:if test="${user != null and user.userType == 1 }" >
							<li><a href="MyPage.do?cmd=show_lecture">강좌관리</a></li>
							<li><a href="MyPage.do?cmd=exam">문제관리</a></li>
						</c:if>
					</ul>
					</c:if>
				</li>
				
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container -->
</nav>



