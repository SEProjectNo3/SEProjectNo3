<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>강좌목록-교수용</title>
<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/modern-business.css" rel="stylesheet">
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="css/yeoeun.css" rel="stylesheet">
</head>

<script>

function checkInput() {

	var courseName = document.getElementById("course_name");
	var courseNumber = document.getElementById("course_number");
	var major = document.getElementById("major");
	
	if (courseName.value == "") {
		alert("개설하고자 하는 강좌의 이름을 입력해주시기 바랍니다.");
		return false;
	} else if (courseNumber.value == "") {
		alert("개설하고자 하는 강좌의 번호를 입력해주시기 바랍니다.");
		return false;
	} else if (major.value == "") {
		alert("개설하고자 하는 강좌의 전공을 입력해주시기 바랍니다.");
		return false;
	} else {
		alert(courseName);
		return true;
	}
}

function onDeleteCourse(courseName, courseNumber) {
	
	if (confirm(courseName + "(" + courseNumber + ") 강좌를 정말 삭제하시겠습니까?") == true) {
		
		alert("성공적으로 삭제되었습니다.");
		location.href="Course.do?cmd=delete_course&number=" + courseNumber;
	} else {
		return;
	}
}

function onUpdateCourse(courseNumber) {
	
	location.href="Course.do?cmd=show_lecture&course_number=" + courseNumber;
}

</script>

<body>
	<jsp:include page="/header.jsp" flush="true" />
	<jsp:include page="/main-login.jsp" flush="true" />

	<center>
		<br> <br>
		<h2>현재 개설중인 강좌 목록</h2>
	</center>
	
	<div class="text-center">
		<button type="button" class="btn btn-basic btn-lg" data-toggle="modal" 
		data-target="#course_add">강좌추가</button>
		
		<div class="modal fade" id="course_add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
         <div class="modal-dialog modal-md">
            <div class="modal-content">
               <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal">
                     <span aria-hidden="true">×</span><span class="sr-only">Close</span>
                  </button>
                  <h3 class="modal-title" id="myModalLabel">
                     <b>추가할 강좌</b>
                  </h3>
               </div>
               <div class="modal-body">
                  <form name="addForm" action="Course.do" method="post">
                  	 <input type="hidden" name="cmd" value="add_course"/>
                     <div>
                        <div class="form-group"></div>
                        <div class="form-group">
                           <label for="course_name pull-left">강좌명</label> 
                           <input id="course_name" name="course_name" class="form-control" placeholder="강좌명" />
                        </div>
                        
                        <div class="form-group">
                           <label for="course_id">학수번호</label> 
                           <input id="course_number" name="course_number" class="form-control" placeholder="예)CSE4401-01" />
                        </div>
                        
                        <div class="form-group">
                           <label for="course_id">전공정보</label> 
                           <input id="major" name="major" class="form-control" placeholder="예)컴퓨터공학과" />
                        </div>
                        
                        <div class="form-group">
                           <label for="course_info">강의정보</label>
                           <textarea class="form-control" cols="70" rows="10" name="course_info" placeholder="강의정보입력"></textarea>
                        </div>
                     </div>
                     
	                 <div class="modal-footer">
	                  	<button type="submit" class="btn btn-primary"  onsubmit="return checkInput()">추가</button>
	                  	<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
	               	 </div>
               	</form>
            </div>
         </div>
      </div>
   </div>
		
	</div>
	
	<hr>
	<div class="container-fluid">

		<div class="row-fluid">

			<div class="col-sm-3"></div>
			<div class="col-sm-8">

					<c:if test="${course_list != null }">
						<c:forEach var="course" items="${course_list }">
							<table style="width:600px">
								<tr>
									<td colspan="5" style="font-weight:bold; font-size:12pt">${course.courseName }</td>
								</tr>
								
								<tr>
									<td colspan="2" width="50%" style="font-size:10pt">${course.courseNumber }</td>
									<td width="30%" style="align:center">
										<input type="button" class="btn btn-basic" value="수강생 조회">
									</td>
									<td width="30%" style="align:center">
										<input type="button" class="btn btn-basic" value="강좌 수정" 
											onClick="onUpdateCourse('${course.courseNumber}')">
									</td>
									<td width="30%" style="align:center">
										<input type="button" class="btn btn-basic" value="강좌 삭제" 
											onClick="onDeleteCourse('${course.courseName}', '${course.courseNumber }')">
									</td>
								</tr>
								
								<tr>
									<td colspan="5">현재 개설 강의 수 : </td>
								</tr>
							</table>
							
							<hr>
						</c:forEach>
					</c:if>

			</div>
		</div>
	</div>
	<hr>
	<jsp:include page="/footer.jsp" flush="true" />

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>