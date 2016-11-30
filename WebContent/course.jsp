<%@page import="com.active.model.Lecture"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.active.constant.*" %>
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

</head>

<script>

function onEvaluation(courseNumber) {
	
	alert(courseNumber);
	location.href = "Review.do?cmd=evaluation&course_number=" + courseNumber;
}

</script>

<body>
	<jsp:include page="/header.jsp" flush="true" />

	<!-- Page Content -->
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<table style="border:2px solid #c0c0c0; border-radius:10px; width:100%; height:100px; margin-top:10%; margin-bottom:2%">
					<tr>
						<td colspan="4" style="font-size:20pt; font-weight:bold; width:88%">${course.courseName }</td>
						<td rowspan="2"> 
							<input type="button" onclick="onEvaluation('${course.courseNumber}')" class="btn btn-success" value="강의평가"/>
						</td>
					</tr>
					<tr>
						<td width="30%"><h4 style="color:#0080c0">${course.professorName } 교수님</h4></td>
						<td align="left">
							<c:forEach varStatus="i" begin="1" end="${Constants.MAX_RATE }">
								<c:choose>
									<c:when test="${course.rate >= i.index}">
										<span class="glyphicon">
											<img src="css/star/star-gold32.png" width="18px" height="50%">
										</span>
									</c:when>
									
									<c:otherwise>
										<span class="glyphicon">
											<img src="css/star/star-white32.png" width="18px" height="50%">
										</span>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td colspan="5"><h6>${course.explanation }</h6></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	
	<!-- 강좌 목록 부분 -->
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<h2>강좌 목록</h2>
				
				<!-- 단원에 맞는 강좌의 목록을 뿌려주는 부분 -->
				<c:forEach varStatus="i" begin="1" end="${unit }">
					<div class="panel panel-default">
					<div class="panel-heading">${i.index} 단원</div>
					
					<ul class="list-group">
						<c:forEach var="lecture" items="${lecture_list }">
							<c:set var="count" value="1"/>
							<c:forTokens var="units" items="${lecture.chapter}" delims="-">
								
								<!-- lecture의 chapter 가 1-2 와 같이 구성되어 있음
								     1은 i.index의 큰 단원을 의미하며, 2는 소단원을 의미함 -->
								     
								<c:if test="${i.index == units and count == 1}">
						  			<li class="list-group-item">
						  			<a href="Course.do?cmd=sugang&id=${lecture.lectureId }">${lecture.title }</a>
						  			</li>
								</c:if>
								<c:set var="count" value="${count+1 }"/>
							</c:forTokens>
						</c:forEach>						
					</ul>
					</div>
				</c:forEach>
				
			</div>
		</div>
	</div>

	<!-- 댓글 기능 -->
	<div class="container">
		<div class="well">
			<div class="text-right">
            <a class="btn btn-success" href="Review.do?cmd=evaluation&course_number=${course.courseNumber }">강의평가하기</a>
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