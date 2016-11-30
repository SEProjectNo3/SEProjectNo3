<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<script src='assets/star/jquery.js' type="text/javascript"></script>
<script src='assets/star/jquery.MetaData.js' type="text/javascript"	language="javascript"></script>
<script src='assets/star/jquery.rating.js' type="text/javascript"	language="javascript"></script>
<link href='assets/star/jquery.rating.css' type="text/css" rel="stylesheet" />

<link type="text/css" href="/@/js/jquery/ui/jquery.ui.css"	rel="stylesheet" />
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.1/jquery-ui.min.js"	type="text/javascript"></script>
</head>
<style>
	div.person_table {
		margin-top: 100px;
	}
</style>

<body>
<div class="person_table">
	
	<input type="hidden" name="cmd" value="giveStarProc">
	
		
		<table class="table" width="100%" border="1" >
			<thead>
				<tr>
					<th style="width:50%"> 사용자 </th>
					<th> 평가 </th>
				</tr>
			</thead>
			

			
					<input class="star" type="radio" name="point" value="1" />
					<input class="star"	type="radio" name="point" value="2" /> 
					<input class="star" type="radio" name="point" value="3" />
					<input class="star" type="radio" name="point" value="4" /> 
					<input class="star" type="radio" name="point" value="5" />
					<input class="btn btn-default" type="submit" value="평가해주세요">


		</table>
		
		
		
	</div>
</body>

</html>