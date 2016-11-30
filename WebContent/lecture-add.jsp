<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%
   request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>강의추가</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/modern-business.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
   type="text/css">

</head>

<body>
   <jsp:include page="/header.jsp" flush="true" />
   <jsp:include page="/main-login.jsp" flush="true" />


   <div class="container-fluid">
      <div class="row-fluid">
         <div class="col-sm-2"></div>
         <div class="col-sm-8">
            <h2>강의 목록</h2>
            
            <div class="panel panel-default">

               <!-- 단원에 맞는 강좌의 목록을 뿌려주는 부분 -->
               <c:set var="check" value="true"/>
                <c:choose>
                	<c:when test="${unit == 0}">
                		<c:set var="check" value="false"/>
						<div class="panel-heading">현재 개설중인 강의 목록이 없습니다.</div>
                	</c:when>
                	
                	<c:otherwise>
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
								  			
								  			<a href=#><span class="glyphicon glyphicon-paperclip pull-right"> </span></a>
								  			</li>
										</c:if>
										<c:set var="count" value="${count+1 }"/>
									</c:forTokens>
								</c:forEach>						
							</ul>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
            </div>
   

		<form action="<%=request.getContextPath() %>/UpLoad.do" method="post" enctype="multipart/form-data" style="align:center">
			<input type="hidden" name="cmd" value="add_lecture"/> 
			<input type="hidden" name="course_number" value="${course_number}"/>
	
			<table style="align:center">
				<tr>
				
					<td width="100px"> 
					단원  <select name="unit" class="select-picker">
				            <option>1</option>
				            <option>2</option>
				            <option>3</option>
				            <option>4</option>
				            <option>5</option>
				       </select>
				    </td>
 
					<td width="100px"> 
					소단원  <select name="s_unit" class="select-picker">
				            <option>1</option>
				            <option>2</option>
				            <option>3</option>
				            <option>4</option>
				            <option>5</option>
				            <option>6</option>
				            <option>7</option>
				            <option>8</option>
				            <option>9</option>
				            <option>10</option>
				        </select>
				        </td>
					<td width="100px"><input type="file" name="material"></td>
				</tr>
				
				<tr>
					<td width="100px"><input type="file" name="lecture"></td>
					<td colspan="2">
					<td>강좌 제목 : <input type="text" name="title"></td>
				</tr>
				<tr>
				<td>
					<textarea class="form-control" cols="70" rows="10" name="lecture_info" placeholder="강의정보입력"></textarea>
                </td>
				<tr>
					<td colspan="3" style="align:center">
						<input type="submit" value="확인" class="btn btn-primary">
					</td>
				</tr>
			</table>
		 
		</form>
	
	    </div>
      </div>
   </div>   

   <!--  참고!!
   // 파일이 저장될 서버의 경로. 되도록이면 getRealPath를 이용하자.
// String savePath = "D:/Projects/workspace/projectName/WebContent/folderName";
String savePath = request.getServletContext().getRealPath("folderName");
 
// 파일 크기 15MB로 제한
int sizeLimit = 1024*1024*15;
 
//  ↓ request 객체,               ↓ 저장될 서버 경로,       ↓ 파일 최대 크기,    ↓ 인코딩 방식,       ↓ 같은 이름의 파일명 방지 처리
// (HttpServletRequest request, String saveDirectory, int maxPostSize, String encoding, FileRenamePolicy policy)
// 아래와 같이 MultipartRequest를 생성만 해주면 파일이 업로드 된다.(파일 자체의 업로드 완료)
MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
 
 
// --------------------------아래는 전송 받은 데이터들을 DB테이블에 저장시키기 위한 작업들이다.--------------------------
  
// MultipartRequest로 전송받은 데이터를 불러온다.
// enctype을 "multipart/form-data"로 선언하고 submit한 데이터들은 request객체가 아닌 MultipartRequest객체로 불러와야 한다.
String m_name = multi.getParameter("m_name");
 
// 전송받은 데이터가 파일일 경우 getFilesystemName()으로 파일 이름을 받아올 수 있다.
String fileName = multi.getFilesystemName("m_file");
 
// 업로드한 파일의 전체 경로를 DB에 저장하기 위함
String m_fileFullPath = savePath + "/" + fileName;
  
// 데이터들을 담을 그릇인 DTO(혹은 Bean) 객체를 생성 후, 데이터들을 set해준다.
MemberDTO memberDTO = new MemberDTO();
 
memberDTO.setM_name(m_name);
memberDTO.setM_nickname(m_nickname);
memberDTO.setM_fileFullPath(m_fileFullPath);
memberDTO.setM_fileName(fileName);
 
// Service 객체 생성.(서비스가 없고 DAO에서 직접 처리한다면 DAO 객체 생성)
MemberService service = MemberService.getInstance();
 
// 서비스에서 만들어놓은 insert 수행 메서드 사용. set으로 담아줬던 DTO를 넘겨서 insert 수행.
service.insertMember(memberDTO);
 
// 만약 return할 페이지에 방금 전송한 데이터들을 출력하고 싶다면 DTO를 속성에 담아준다.
request.setAttribute("memberDTO", memberDTO);
 
// ↓ 모든 것이 성공적으로 수행되었을 경우 return 될 page
return "blabla/blabla.jsp";
   
    -->


   <hr>

   <jsp:include page="/footer.jsp" flush="true" />
   <!-- jQuery -->
   <script src="js/jquery.js"></script>

   <!-- Bootstrap Core JavaScript -->
   <script src="js/bootstrap.min.js"></script>

</body>
</html>