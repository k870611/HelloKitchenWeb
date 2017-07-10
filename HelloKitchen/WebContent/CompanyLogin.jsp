<%@page import="Utility.GeneralVarName"%>
<%@page import="Modules.SessionStamp"%>
<%@page import="EntityComponents.Company"%>
<%@page import="Factorys.CompanyFactory"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	String CompanyId="none";
  	String pwd="";
  	String email="";

	if((SessionStamp)request.getSession().getAttribute(GeneralVarName.Session_CompanyLogin)!=null){
		SessionStamp st = (SessionStamp)request.getSession().getAttribute(GeneralVarName.Session_CompanyLogin);
		CompanyId=st.getLoginAccount();
		
		CompanyFactory cf = (CompanyFactory) request.getServletContext().getAttribute(GeneralVarName.Web_CompanyFactory);
        Company ALcf = (Company)cf.sreachByPK(CompanyId);
        
        pwd=ALcf.getCompany_password();
        email=ALcf.getCompany_email();
	} 
%>
<%
String message = (String) request.getAttribute ("Message");
%>
<!doctype html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>HelloKitchen &mdash; 廠商登入</title>
	<!-- <link rel="stylesheet" type="text/css" href="css/normalize.css" /> -->
	<link rel="stylesheet" type="text/css" href="css/default.css">
	<link href="css/bootstrap.min.css" rel="stylesheet" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.1/css/materialize.min.css">
	<style type="text/css">
	html,
	body {
	    height: 100%;
	}
	html {
	    display: table;
	    margin: auto;
	}
	body {
	    display: table-cell;
	    vertical-align: middle;
	}

	.margin {
	  margin: 0 !important;
	}
	
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
	    text-align:center;
	}
	
	.alert-success {
	    color: #000000;
	    background-color:  #e5e5e5;
	}
	</style>
	
	
</head>
<body style="background-color:#e5e5e5;">
<div class='alert'></div>
	<div id="login-page" class="row">
	    <div class="col s12 z-depth-6 card-panel">
	      <form method="post" action="./CompanyLoginServlet" class="login-form">
	        <div class="row">
	          <div class="input-field col s12 center">
	            <a href='index.jsp'><img src="images/logo.png"  alt="" class="responsive-img valign profile-image-login" style="width:50%;height:50%;"></a>
	            <div><br/></div>
	            <p class="center login-form-text" style="font-size:130%;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;HelloKitchen &mdash; 廠商登入&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
	          </div>
	        </div>
	        <div class="row margin">
	          <div class="input-field col s12">
	            <i class="mdi-social-person-outline prefix"></i>
	            <input class="validate" id="email" name='account' type="text" placeholder='Email'>
	            <label for="email" data-error="wrong" data-success="right" class="center-align"></label>
	          </div>
	        </div>
	        <div class="row margin">
	          <div class="input-field col s12">
	            <i class="mdi-action-lock-outline prefix"></i>
	            <input id="password" name='pwd' type="password" placeholder='Password'>
	            <label for="password"></label>
	          </div>
	        </div>
	        <div class="row">          
	          <div class="input-field col s12 m12 l12  login-text">
	              
	              <label for="remember-me"></label>
	          </div>
	        </div>
	        <div class="row">
	          <div class="input-field col s12">
	            <button type="submit" class="btn waves-effect waves-light col s12">登　入</button>
	          </div>
	        </div>
	        <div class="row">
	          <div class="input-field col s6 m6 l6">
	            <p class="margin medium-small"><a id="signup" href="addcompany.jsp" style='font-size:120%'>現在註冊!</a></p>
	          </div>
	          <div class="input-field col s6 m6 l6">
	              <p class="margin right-align medium-small"><a id="cominfo" href="ManageCompany.jsp" style='font-size:120%'>個人資訊</a></p>
	          </div>
	          </div>          
	      </form>
	    </div>
	  </div>
	<script type="text/javascript" src="js/jquery-2.1.1.js"></script>
	<script src="js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
	$(document).ready(function () {
		var chk="<%=message%>";
		
		if(chk!="null"){
	        $('.alert').html('登入失敗').addClass('alert-success').show().delay(1500).fadeOut();
		}
	});
	
	var comlogin="<%=CompanyId%>"
	var pwd="<%=pwd%>";
	var email="<%=email%>"
	
	if(comlogin =="none"){
		$('#cominfo').css("color","#e5e5e5");
		$('#cominfo').click(function(){
			return false;		
		})
		
		
	}else{
		$('#cominfo').css("color","#2fa0ec");
		
		$('#email').val(email);
		$('#password').val(pwd);
		
		$('#email').attr('readonly', true);
		$('#password').attr('readonly', true);
		
		$('.btn').html('登出');
		$('.btn').click(function(){
			//alert("登出");
			location.href='./LogOutServlet';
			return false;

		});
		
		$('#signup').css("color","#e5e5e5");
		$('#signup').click(function(){
			return false;
		})
	}
  </script>
</body>
</html>