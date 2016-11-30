<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.active.constant.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	request.getParameter("course_list");
%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>강좌목록-학생용</title>
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

<script>

function onSearch(courseNumber) {

	location.href='Course.do?cmd=search_lecture';
}

</script>

<body>
	<jsp:include page="/header.jsp" flush="true" />
	<jsp:include page="/main-login.jsp" flush="true" />
	
	<h2 align="center">강좌목록</h2>

	<div class="container-fluid">

		<hr>
		<div class="row-fluid">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<!-- 검색을 통해 나온 강의의 목록을 보여주는 부분 -->
				<c:if test = "${course_list != null }" >
				<c:forEach var="course" items="${course_list }">
		
					<table onclick="location.href='Course.do?cmd=search_lecture&number=${course.courseNumber}'" style="cursor:hand; width:100%; height:100px; margin:2%">
						<tr>
							<td colspan="5" style="font-weight:bold">${course.courseName }</td>
						</tr>
						<tr>
							<td width="120px">${course.professorName }</td>
							<td colspan="3">
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
							<td width="80px">
								<form action="Enroll.do" method="post">
									<input type="hidden" name="cmd" value="enroll_proc"> 
									<input type="hidden" name="courseNumber" value="${course.courseNumber}">
									<input type="submit" class="btn btn-success" value="수강하기" />
							 	</form>
							</td>
						</tr>
					 
						<tr style="border-bottom:2px solid #C3C3C3">
							<td rowspan="2" colspan="4">
								<h6>${course.explanation }</h6>
							</td>
							<td rowspan="2"></td>
						</tr>
					</table>
				</c:forEach>
				</c:if>      			              		
			</div>
				<div class="col-sm-2"></div>
			</div>
			
	</div>

	<jsp:include page="/footer.jsp" flush="true" />
	
		<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
	</body>
</html>