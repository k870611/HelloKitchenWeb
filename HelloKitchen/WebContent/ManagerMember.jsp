<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="Utility.GeneralVarName"%>
<%@page import="Factorys.*"%>
<%@page import="Modules.SessionStamp"%>
<%@page import="EntityComponents.Member"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>會員資料更新</title>
<link rel="stylesheet" type="text/css" href="css/login.css">
<script src="js/modernizr-2.6.2.min.js"></script>
<script src="js/jquery-2.1.1.js"></script>

<style type="text/css">
.alert {
	display: none;
	position: fixed;
	top: 0;
	left: 40%;
	min-width: 20%;
	z-index: 99999;
	padding: 15px;
	border: 1px solid transparent;
	border-radius: 4px;
	text-align: center;
}

.alert-success {
	color: #000000;
	background-color:#e5e5e5;
}
</style>


<script type="text/javascript">
$(document).ready(function () {

	$("#reppass").focusout(function(){
		var firstpwd = $("#pass").val();
		var checkpwd = $("#reppass").val();
		if(firstpwd !== checkpwd){
			$('.alert').html('兩次密碼輸入不同').addClass('alert-success').show().delay(1500).fadeOut();
			$("#reppass").val("");
			$("#pass").val("");
		}
	});
});
</script>
</head>
<body>
<%
  MemberFactory mf = (MemberFactory)application.getAttribute(GeneralVarName.Web_MemberFactory);
  System.out.println("mySession "+session.getId());
  SessionStamp ssp = (SessionStamp)session.getAttribute(GeneralVarName.Session_LoginAccount);
  if(ssp==null || !ssp.isLoginStatus()){
	  request.getRequestDispatcher("login.jsp").forward(request, response);
  }
  Member m=  mf.sreachByPK(ssp.getLoginAccount());
 
%>
	<div class='alert'></div>
	<div class="login-wrap">
		<div class="login-html">
			<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label
				for="tab-1" class="tab">會員資料更新</label> <input id="tab-2" type="radio"
				name="tab" class="sign-up"><label for="tab-2" class="tab"></label>
			<div class="login-form">
				<div class="sign-in-htm">
					<form action="./MemberUpdateServlet" method="post">
						
						<div class="group">
							<label for="user" class="label">姓名</label> <input name="userName"
								id="user" type="text" class="input" value=<%=m.getMember_name()%>>
						</div>
						<div class="group">
							<label for="pass" class="label">新密碼</label> <input id="pass"
								type="password" class="input" data-type="password" required>
						</div>
						<div class="group">
							<label for="pass" class="label">確認密碼</label> <input name="cpwd"
								id="reppass" type="password" class="input" data-type="password" required>
						</div>

						<div class="group">
							<label for="user" class="label">電話</label> <input name="tel"
								id="user" type="text" class="input" value=<%=m.getMember_tel()%>>
						</div>
						<div class="group">
							<label for="user" class="label">fb</label> <input name="fb"
								id="user" type="text" class="input" value=<%=m.getMember_fb()%>>
						</div>

						<div class="group">
							<input type="submit" class="button" value="送出">
						</div>
						<div class="foot-lnk">
						<br /> <a href="index.jsp">回首頁</a>
					</div>
					</form>

				</div>
				<!-- //************************** 創帳號 ******************************* -->
				<div class="sign-up-htm"></div>
			</div>
		</div>
	</div>




</body>
</html>