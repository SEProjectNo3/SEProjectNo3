<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   request.setCharacterEncoding("UTF-8");
   request.getParameter("enroll_list");
%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>수강목록</title>

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
   <br>
   <br>


   <center>
      <h2>수강목록</h2>
   </center>
   <div class="container-fluid">
      <hr>

      <div class="row-fluid">
         <div class="col-sm-4"></div>
         <div class="col-sm-6">
         
          <div class="col-md-8">
          <c:if test = "${enroll_list != null }" >
            <c:forEach var="enroll" items="${enroll_list}">
               <table style="cursor:hand; width:100%; height:100px; margin:2%">
                  <tr>
                     <td colspan="5" style="font-weight:bold">${enroll.courseNumber}</td>
                  </tr>
                  <tr>
                     <td>${enroll.courseNumber}</td>
                  </tr>
                  	<td>
                     <form action="Enroll.do" method="post">
								<input type="hidden" name="cmd" value="enroll_del_proc"> 
								<input type="hidden" name="courseNumber" value="${enroll.courseNumber}">
								<input type="submit" class="btn btn-success" value="드랍하기" />
					 </form>
                    </td>
                  <tr>
                     <td rowspan="2" colspan="5" style="border-bottom:2px solid #C3C3C3">
                       <h6>${enroll.courseNumber}</h6>
                     </td>
                  </tr>
               </table>
            </c:forEach>
            </c:if>  
            </div>                                 
         </div>
         <div class="col-sm-2"></div>
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
