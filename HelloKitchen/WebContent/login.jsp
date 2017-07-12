<%@page import="EntityComponents.Member"%>
<%@page import="Factorys.MemberFactory"%>
<%@page import="Modules.SessionStamp"%>
<%@page import="Utility.GeneralVarName"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String message = (String) request.getAttribute("Message");
%>
<%

String email="";
String pwd="";

if((SessionStamp)request.getSession().getAttribute(GeneralVarName.Session_LoginAccount)!=null){
	SessionStamp st = (SessionStamp)request.getSession().getAttribute(GeneralVarName.Session_LoginAccount);

	String MemberId=st.getLoginAccount();
	System.out.println("The Member Id is "+MemberId);
	
	if(MemberId != "nonelog" && MemberId != null){
		MemberFactory mf = (MemberFactory) request.getServletContext().getAttribute(GeneralVarName.Web_MemberFactory);
		Member mb=mf.sreachByPK(MemberId);
		
		message=st.getLoginAccount();
		
	   	email=mb.getMember_email();
	   	pwd=mb.getMember_password();
	}

}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
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

	$(".sign-up-htm #reppass").focusout(function(){
		var firstpwd = $(".sign-up-htm #pass").val();
		var checkpwd = $(".sign-up-htm #reppass").val();
		//alert("first"+checkpwd);
		//alert("secoend"+checkpwd);
		if(firstpwd !== checkpwd){
			$('.alert').html('兩次密碼輸入不同').addClass('alert-success').show().delay(1500).fadeOut();
			$(".sign-up-htm #reppass").val("");
			$(".sign-up-htm #pass").val("");
		}
	});
	
	var chk="<%=message%>";
				if (chk == "fail") {
					$('.alert').html('登入失敗').addClass('alert-success').show().delay(1500).fadeOut();
				}
			});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".sign-up-htm #userE").focusout(function() {
			var keyinaccount = $(this).val();
			$.ajax({
				type : "GET",
				url : "./MemberLogin",
				data : {
					operation : "test",
					account : keyinaccount
				},
				//dataType: "text",
				//dataType: "html",
				//dataType: "json",
				cache : false,
				success : function(response) {
					//--array?
					var data = $.parseJSON(response);//obj
					if (data.myStatus == true) {
						$('.alert').html('帳號格式錯誤或已有人使用').addClass('alert-success').show().delay(1500).fadeOut();
						$(".sign-up-htm #userE").val("");
					}

				},
				error : function(xhr, ajaxOptions, thrownError) {
					$('.alert').html('帳號格式錯誤或已有人使用').addClass('alert-success').show().delay(1500).fadeOut();
					$(".sign-up-htm #userE").val("");
				}
			});
		});
	});
</script>

<script>
	//----------------臉書登入實作區 -----------------
	
	// Load the SDK asynchronously
	(function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0];
		if (d.getElementById(id))
			return;
		js = d.createElement(s);
		js.id = id;
		js.src = "//connect.facebook.net/en_US/sdk.js";
		fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));
	
	window.fbAsyncInit = function() {
		FB.init({
			appId : '1860958914163832',
			cookie : true, // enable cookies to allow the server to access 
			// the session
			xfbml : true, // parse social plugins on this page
			version : 'v2.9' // use graph api version 2.8
		});

		// Now that we've initialized the JavaScript SDK, we call 
		// FB.getLoginStatus().  This function gets the state of the
		// person visiting this page and can return one of three states to
		// the callback you provide.  They can be:
		//
		// 1. Logged into your app ('connected')
		// 2. Logged into Facebook, but not your app ('not_authorized')
		// 3. Not logged into Facebook and can't tell if they are logged into
		//    your app or not.
		//
		// These three cases are handled in the callback function.


	};

	function statusChangeCallback(response) {
		console.log('statusChangeCallback');
		console.log(response);

		if (response.status === 'connected') {
			// Logged into your app and Facebook.
			checkemail();

		} else {
			// The person is not logged into your app or we are unable to tell.
			fblogout();
		}
	}
	
	
	function checkemail() {
		
		console.log('Welcome!  Fetching your information.... ');
		var loginStatus = false;
		//---------- 先行取得登入狀態 -------------
		$.ajax({
			type : "POST",
			url : "./FacebookLogin",
			data : {
				operation : "getLoginStatus"
			},
			//dataType: "text",
			//dataType: "html",
			//dataType: "json",
			cache : false,
			success : function(response) {
				//--array?
				var data = $.parseJSON(response);
				loginStatus = data.loginStatus;
				//alert("取得登入狀態 "+loginStatus);
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert(xhr.status);
				alert(thrownError.Error);
			}
		});
		if (loginStatus) {

		} else {
			FB.api('/me', {
				fields : 'name, email, id'
			}, function(response) {
				console.log('Good to see you, ' + response.name + '.'
						+ ' Email: ' + response.email + ' Facebook ID: '
						+ response.id);
				var logEmail = response.email;
				var logName = response.name;
				var logId = response.id;

				//------------ 執行臉書登入功能 -----------------	
				$.ajax({
					type : "POST",
					url : "./FacebookLogin",
					data : {
						operation : "facelogin",
						email : logEmail,
						name : logName,
						id : logId
					},
					//dataType: "text",
					//dataType: "html",
					//dataType: "json",
					cache : false,
					success : function(response) {
						//--array?
						var data = $.parseJSON(response);
						if (data.checkReslut == true) {
							//location.href = "index.jsp";
							$('#signin').css('background-color', '#c0c0c0');
							$('#signin').val("FB已登入");

							$('#user').val(logName);
							$('#user').attr('readonly', true);

							$('#pass').val(logId);
							$('#pass').attr('readonly', true);

							//alert("signin");
						}
					},
					error : function(xhr, ajaxOptions, thrownError) {
						alert(xhr.status);
						alert(thrownError.Error);
					}
				});
				//----------------------------------	
			});
		}
	}
	
	function fblogout() {
		$.ajax({
			type : "POST",
			url : "./FacebookLogin",
			data : {
				operation : "faceLogout",

			},
			//dataType: "text",
			//dataType: "html",
			//dataType: "json",
			cache : false,
			success : function(response) {
				$('#signin').css('background-color', '#1161ee');
				$('#signin').val("登入");

				$('#user').val("");
				$('#user').attr('readonly', false);

				$('#pass').val("");
				$('#pass').attr('readonly', false);
				location.href = "./LogOutServlet";

			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert(xhr.status);
				alert(thrownError.Error);
			}
		});
	}

	// This function is called when someone finishes with the Login
	// Button.  See the onlogin handler attached to it in the sample
	// code below.
	function checkLoginState() {
		FB.getLoginStatus(function(response) {
			statusChangeCallback(response);
		});
	}


	//--------------- 臉書登入實作區結束  ----------------
	//--------------- 臉書CSS  ----------------
	var finished_rendering = function() {
		console.log("finished rendering plugins");
		var spinner = document.getElementById("spinner");
		spinner.removeAttribute("style");
		spinner.removeChild(spinner.childNodes[0]);
	}
	FB.Event.subscribe('xfbml.render', finished_rendering);
	//--------------- 臉書CSS 結束  ----------------	
</script>
</head>
<body>
	<div class='alert'></div>
	<div class="login-wrap">
		<div class="login-html">
			<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label
				for="tab-1" class="tab">登入</label> <input id="tab-2" type="radio"
				name="tab" class="sign-up"><label for="tab-2" class="tab">註冊</label>
			<div class="login-form">
				<div class="sign-in-htm">

					<form action="./MemberLogin" method="post">
						<div class="group">
							<label for="user" class="label">Email</label> 
							<input
								name="account" id="user" type="text" class="input"> 
							<input
								name="operation" type="hidden" class="input"
								value="logFromWeb">
						</div>
						<div class="group">
							<label for="pass" class="label">密碼</label> <input name="pwd"
								id="pass" type="password" class="input" data-type="password">
						</div>

						<div class="group">
							<input id="check" type="checkbox" class="check" checked>
							<label for="check"><span class="icon"></span></label>
						</div>
						<div class="group">
							<input id="signin" type="submit" class="button" value="登入">
						</div>
						
						<div id="spinner"
						    style="
						        background: #4267b2;
						        border-radius: 5px;
						        color: white;
						        height: 40px;
						        text-align: center;
						        width: 100%;">
						    <div
						    scope="public_profile, email"
						    onlogin="checkLoginState();"
						    data-auto-logout-link="false"
						    class="fb-login-button"
						    data-max-rows="1"
						    data-size="large"
						    data-button-type="login_with"
						    data-show-faces="false" 
						    data-use-continue-as="false"
						    ></div>
						</div>

					</form>

					<div class="hr"></div>
					<div class="foot-lnk">
						<a id='ManagerMember' href="ManagerMember.jsp">修改會員資料?</a>
					</div>
					<div class="foot-lnk">
						<br /> <a href="index.jsp">回首頁</a>
					</div>
				</div>
				<!-- //************************** 創帳號 ******************************* -->
				<div class="sign-up-htm">

					<form action="./MemberLogin" method="post">
						<div class="group">
							<label for="user" class="label">Email</label> 
							<input name="account" id="userE" type="text" class="input" required> 
							<input name="operation" id="user" type="hidden" class="input"
								value="createAccount">
						</div>
						<div class="group">
							<label for="user" class="label">姓名</label> 
							<input name="userName" id="user" type="text" class="input" required>
						</div>
						<div class="group">
							<label for="pass" class="label">密碼</label> 
							<input id="pass" type="password" class="input" data-type="password" required>
						</div>
						<div class="group">
							<label for="pass" class="label">確認密碼</label> <input name="cpwd"
								id="reppass" type="password" class="input" data-type="password">
						</div>

						<div class="group">
							<label for="user" class="label">電話</label> <input name="tel"
								id="user" type="text" class="input">
						</div>
						<div class="group">
							<label for="user" class="label">fb</label> <input name="fb"
								id="user" type="text" class="input">
						</div>

						<div class="group">
							<input type="submit" class="button" value="送出">
						</div>

					</form>

					<div class="hr"></div>
					<div class="foot-lnk">
						<label for="tab-1"><a>Already Member?</a></label>
					</div>
					<div class="foot-lnk">
						<br /> <a href="index.jsp">回首頁</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
	var chk="<%=message%>";
	var email="<%=email%>";
	var pwd="<%=pwd%>";

	if(chk!="null"&&chk!='fail'){
		$('#signin').css('background-color', '#1161ee');
		$('#signin').val("登出");

		$('#user').val(email);
		$('#user').attr('readonly', true);

		$('#pass').val(pwd);
		$('#pass').attr('readonly', true);
		
		$('#signin').click(function(){
			location.href='./LogOutServlet';
			return false;
		});
	}else{
		$('#signin').css('background-color', '#1161ee');
		$('#signin').val("登入");
		$('#ManagerMember').click(function(){
			$('.alert').html('您尚未登入').addClass('alert-success').show().delay(1500).fadeOut();
			return false;
		})
	}	
	
	
	</script>
	
</body>

</html>