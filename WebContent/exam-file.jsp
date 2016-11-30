<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<script>

var no = 1;

function addQuestion() {
	
    var questionForm = document.getElementById("question");
   
    var content = ""; 
    
    content += "<input type='text' name='questionNo' value='" + no + "' style='width:15px; font-weight:bold'> "
    		+ "<input type='text' name='question' style='width:300px'><br>"
 		    + "<div><img src='img/one.png' style='width:15px; height:16px; vertical-align:middle' onclick=''> "
 		    + "<input type='text' name='choice' style='width:300px'></div>"
 		    + "<div><img src='img/two.png' style='width:15px; height:16px; vertical-align:middle' onclick=''> "
 		    + "<input type='text' name='choice' style='width:300px'></div>"
 		    + "<div><img src='img/three.png' style='width:15px; height:16px; vertical-align:middle;' onclick=''> "
 		    + "<input type='text' name='choice' style='width:300px'></div>"
 		    + "<div><img src='img/four.png' style='width:15px; height:16px; vertical-align:middle;' onclick=''> "
 		    + "<input type='text' name='choice' style='width:300px'></div>"
 		    + "<br>";
 		    //+ "<br><input type='button' onclick='deleteQuestion(questionNo)' name='btn_delete' value='문제 삭제'>";
   
    var newDiv = document.createElement("div"); 
    newDiv.id = "question_" + no; 
    newDiv.innerHTML = content;
    questionForm.appendChild(newDiv); 

    no++;
    document.examForm.no.value = no;
}

function deleteQuestion() {
	
	var questionForm = document.getElementById("question");
	
	if(no > 2) {
		
		var deleteDiv = document.getElementById("question_" + (--no));
        questionForm.removeChild(deleteDiv);
        
    } else {
		document.examForm.reset(); // 폼 내용 삭제
	}
}


</script>

<form name="examForm" action="Exam.do" method="post">
	<input type="hidden" name="count" value="0">
	<div id="question" >
	</div>
	<br>
	<input type="Button" value="추가" onclick="addQuestion()">
	<input type="Button" value="삭제" onclick="deleteQuestion()">
	<input type="Submit" value="완료">
</form>

</body>
</html>