<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%
	request.setCharacterEncoding("UTF-8");
%>

<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script>


function saveLogin() {
	var f = document.userForm;
	validate(f);
	var textVal = document.getElementById("userId").value;
	var checkBox = document.getElementById("mLgIdS");		
	if(checkBox.checked == true) {
		setSave("uid", textVal, 30);
	} else if(checkBox.checked == false) {
		setSave("uid", textVal, -1);
	}
}
function setSave(name, value, expiredays) {
	var today = new Date();
	today.setDate(today.getDate()+expiredays);
	document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + today.toGMTString() + ";"
}



</script>

<div class="xe-widget-wrapper ">
   <div style="*zoom: 1; padding: 0px 0px 0px 0px !important;">
      <div class="modal" id="modal_Login">
         <div class="modal-dialog modal-sm">
            <div class="modal-content">
               <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal"
                     aria-hidden="true">&times;</button>
                  <h2 class="modal-title text-center fc-orange">로그인</h2>
               </div>
               
               <div class="modal-body">
               
               <form id="userForm" name="userForm" method="post" action="<%=request.getContextPath()%>/Login.do"> <!-- onsubmit="saveLogin(); return true">-->		
					<input type="hidden" name="gubun" value="S">
					<input type="hidden" name="cmd" value="login_check" >
					
					<div>
                       <div class="form-group">
         
                          <label for="user_id">아이디</label>
                          <input type="text" name="userDTO.userId" id="userId" class="form-control" placeholder="Your ID">
                           
                          <!-- 
                          <input type="text" name="userDTO.id" id="userDTO.id" class="form-control" placeholder="Your ID" />
                          -->
                       </div>
                       <div class="form-group">
                          <label for="user_pwd">비밀번호</label> 
                          <input type="password" name="userDTO.password" id="password" class="form-control" placeholder="Password" />
                       </div>
                       
                       <!-- <a href="javascript:fo_login_widget.submit()"> -->
                          <input type="submit" id="btn_login" class="butn butn-warning butn-block" value="로그인" />
                         
                       <!-- </a>-->
                       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                       <input type="checkbox" name="mLgIdS" id="mLgIdS" value="Y" /> 
                       <label for="keep_signed">로그인 유지</label>
                    </div>
				</form>
				
				<!-- 
                  <form id="fo_login_widget" action="<%=request.getContextPath()%>/LoginController.do" method="post" class="clearfix">
            
                     <input type="hidden" name="cmd" value="login_check" >
                     
                        <div>
                           <div class="form-group"></div>
             
                              <label for="user_id">아이디</label> 
                              <input name="user_id" id="user_id" class="form-control" placeholder="Your ID" />
                           </div>
                           <div class="form-group">
                              <label for="user_pwd">비밀번호</label> 
                              <input name="user_pwd" id="user_pwd" class="form-control" type="password" placeholder="Password" />
                           </div>
                           
                           <a href="javascript:fo_login_widget.submit()">
                              <button type="submit" class="butn butn-warning butn-block">로그인</button>
                           </a>
                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                           <input type="checkbox" name="keep_signed" id="keep_signed"
                              value="Y" /> <label for="keep_signed">로그인 유지</label>
                        </div>
                  </form>
                  -->
               </div>
            </div>
            <!-- modal-content -->
         </div>
         <!-- modal-dialog -->
      </div>
      <!-- modal -->
     </div>
</div>