<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   request.setCharacterEncoding("UTF-8");
   request.getParameter("comment_list");
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

   <form>
      <center>
         <h2>강의 이름</h2>
      </center>
   </form>
   <hr>
   <div class="container-fluid">
      <div class="row-fluid">
         <div class="col-sm-2">
            <!-- Single button -->
            <center>

               <ul class="nav nav-pills-vertical">
                  <li role="presentation" class="inactive"><b>단원명</b></li>
                  <li role="presentation">
                     <div class="btn-group-vertical">
                        <button class="btn btn-default btn dropdown-toggle"
                           type="button" data-toggle="dropdown" aria-expanded="false">
                           1단원 <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu pull-left" role="menu">
                           <li role="presentation"></li>
                           <li role="presentation"><a href="#">1-2</a></li>
                           <li role="presentation"><a href="#">1-3</a></li>
                        </ul>
                     </div>
                  </li>
                  <li role="presentation">
                     <div class="btn-group-vertical">
                        <button class="btn btn-default btn dropdown-toggle"
                           type="button" data-toggle="dropdown" aria-expanded="false">
                           2단원 <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu pull-left" role="menu">
                           <li><a href="#">2-1</a></li>
                           <li><a href="#">2-2</a></li>
                           <li><a href="#">2-3</a></li>
                        </ul>
                     </div>
                  </li>

                  <li role="presentation">
                     <div class="btn-group-vertical">
                        <button class="btn btn-default btn dropdown-toggle"
                           type="button" data-toggle="dropdown" aria-expanded="false">
                           3단원 <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu pull-left" role="menu">
                           <li><a href="#">3-1</a></li>
                           <li><a href="#">3-2</a></li>
                           <li><a href="#">3-3</a></li>
                        </ul>
                     </div>
                  </li>

                  <li role="presentation">
                     <div class="btn-group-vertical">
                        <button class="btn btn-default btn dropdown-toggle"
                           type="button" data-toggle="dropdown" aria-expanded="false">
                           4단원 <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu pull-left" role="menu">
                           <li><a href="#">4-1</a></li>
                           <li><a href="#">4-2</a></li>
                           <li><a href="#">4-3</a></li>
                        </ul>

                     </div>
                  </li>

               </ul>

            </center>
         </div>

         <div class="col-sm-10">

            <video width="1000" height="500" controls loop>
               <source src="video/test.mp4" type="video/mp4">
               <source src="video/test.ogg" type="video/ogg">

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
               <form action="Comment.do" method="post">
					<input type="hidden" name="cmd" value="comment_proc"> 
					<textarea class="form-control col-sm-6" rows="5" name="content"></textarea>
					<div class="text-right">
						<input type="submit" class="btn btn-success" value="댓글쓰기" />
					</div>
				</form>
               <hr>
            </div>

            <div class="col-md-8">
            		<c:if test = "${comment_list != null}">
                  	<c:forEach var="comment" items="${comment_list}">
                  		<table style="cursor:hand; width:100%; height:100px; margin:2%">
                  		<tr>
                  			<td colspan="5" style="font-weight:bold">${comment.writer} </td>
                  		</tr>
                  		<tr>
                  			<td>${comment.content}</td>
            							<a href="Comment.do?cmd=comment_del_proc&commentId=${comment.commentNo}&commentWriter=${comment.writer}&commentLectureId=${comment.lectureId}" ><span
											class="glyphicon glyphicon-trash pull-right"> </span> 
										</a>
									
										<a href="commentUpdate.jsp?commentId=${comment.commentNo}"> <span
											class="glyphicon glyphicon-wrench pull-right"> </span>
										</a>
										</form>
										<td colspan="4"> </td>
                      	<tr>
                    <td rowspan="2" colspan="5" style="border-bottom:2px solid #C3C3C3">
                      <h6> ${comment.writeTime}</h6>
                    </td>
                  </tr>
                 </table>
               </c:forEach>
              </c:if>
              </div>
              <div class="col=sm=2"></div>
              </div>
              
              </div>
			
   <jsp:include page="/footer.jsp" flush="true" />
   <!-- /.container -->

   <!-- jQuery -->
   <script src="js/jquery.js"></script>

   <!-- Bootstrap Core JavaScript -->
   <script src="js/bootstrap.min.js"></script>


</body>
</html>