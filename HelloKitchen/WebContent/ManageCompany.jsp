<%@page import="EntityComponents.Member"%>
<%@page import="Factorys.MemberFactory"%>
<%@page import="EntityComponents.Company"%>
<%@page import="Factorys.CompanyFactory"%>
<%@page import="EntityComponents.Message"%>
<%@page import="Factorys.MessageFactory"%>
<%@page import="Utility.GeneralVarName"%>
<%@page import="Modules.SessionStamp"%>
<%@page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String message = (String) request.getAttribute ("Message");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>HelloKitchen &mdash; A free HTML5 Template by FREEHTML5.CO</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Free HTML5 Template by FREEHTML5.CO" />
    <meta name="keywords" content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
    <meta name="author" content="FREEHTML5.CO" />
    <!--
      //////////////////////////////////////////////////////

      FREE HTML5 TEMPLATE
      DESIGNED & DEVELOPED by FREEHTML5.CO

      Website: 		http://freehtml5.co/
      Email: 			info@freehtml5.co
      Twitter: 		http://twitter.com/fh5co
      Facebook: 		https://www.facebook.com/fh5co

      //////////////////////////////////////////////////////
       -->
    <!-- Facebook and Twitter integration -->
    <meta property="og:title" content="" />
    <meta property="og:image" content="" />
    <meta property="og:url" content="" />
    <meta property="og:site_name" content="" />
    <meta property="og:description" content="" />
    <meta name="twitter:title" content="" />
    <meta name="twitter:image" content="" />
    <meta name="twitter:url" content="" />
    <meta name="twitter:card" content="" />
    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <link rel="shortcut icon" href="favicon.ico">
    <!-- Google Webfonts -->
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,300,100,500' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>

    <!-- Animate.css -->
    <link rel="stylesheet" href="css/animate.css">
    <!-- Icomoon Icon Fonts-->
    <link rel="stylesheet" href="css/icomoon.css">
    <!-- Magnific Popup -->
    <link rel="stylesheet" href="css/magnific-popup.css">
    <!-- Salvattore -->
    <link rel="stylesheet" href="css/salvattore.css">
    <!-- Theme Style -->
    <link rel="stylesheet" href="css/style.css">
    <!-- Modernizr JS -->
    <script src="js/modernizr-2.6.2.min.js"></script>
    <!-- FOR IE9 below -->
    <!--[if lt IE 9]>
    <script src="js/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" href="css/filter.css" />
    <!--slider-->
	<link rel="stylesheet" href="css/app.css"/>
	
	<link rel="stylesheet" type="text/css" href="css/demo.css" />
	<link rel="stylesheet" type="text/css" href="css/style2.css" />
	
	<!--     Fonts and icons     -->
    <!-- CSS Files -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
    <link href="assets/css/gsdk-bootstrap-wizard.css" rel="stylesheet" />

    <!-- CSS Just for demo purpose, don't include it in your project -->
    <link href="assets/css/demo.css" rel="stylesheet" />
	
	
	<script>document.documentElement.className = 'js';</script>
	
    <!---->
	<script src="js/jquery-2.1.1.js"></script>
    <style type="text/css">
    .head{
        margin-left:40px;
        width:60%;
        height:60%;
    }
    
	.provincesList1 {
	   width: 240px;
	   height: 34px;
	   overflow: hidden;
	   background:#ddd;
	}
	.filterfont{
		font-size:15px;
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
	    background-color:  #808080;
	}

	
	.popup-box {
	   	background-color: #ffffff;
	    border: 1px solid #b0b0b0;
	    bottom: 0;
	    display: none;
	    height: 415px;
	    position: fixed;
	    right: 2%;
	    width: 300px;
	    font-family: 'Open Sans', sans-serif;
	}
	.round.hollow {
	    margin: 40px 0 0;
	}
	.round.hollow a {
	    border: 2px solid #ff6701;
	    border-radius: 35px;
	    color: red;
	    color: #ff6701;
	    font-size: 23px;
	    padding: 10px 21px;
	    text-decoration: none;
	    font-family: 'Open Sans', sans-serif;
	}
	.round.hollow a:hover {
	    border: 2px solid #000;
	    border-radius: 35px;
	    color: red;
	    color: #000;
	    font-size: 23px;
	    padding: 10px 21px;
	    text-decoration: none;
	}
	.popup-box-on {
	    display: block !important;
	}
	.popup-box .popup-head {
	    background-color: #fff;
	    clear: both;
	    color: #7b7b7b;
	    display: inline-table;
	    font-size: 21px;
	    padding: 7px 10px;
	    width: 100%;
	    font-family: Oswald;
	}
	.bg_none i {
	    border: 1px solid #ff6701;
	    border-radius: 25px;
	    color: #ff6701;
	    font-size: 17px;
	    height: 33px;
	    line-height: 30px;
	    width: 33px;
	}
	.bg_none:hover i {
	    border: 1px solid #000;
	    border-radius: 25px;
	    color: #000;
	    font-size: 17px;
	    height: 33px;
	    line-height: 30px;
	    width: 33px;
	}
	.bg_none {
	    background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
	    border: medium none;
	}
	.popup-box .popup-head .popup-head-right {
	    margin: 11px 7px 0;
	}
	.popup-box .popup-messages {
	}
	.popup-head-left img {
	    border: 1px solid #7b7b7b;
	    border-radius: 50%;
	    width: 44px;
	}
	.popup-messages-footer > textarea {
	    border-bottom: 1px solid #b2b2b2 !important;
	    border: medium none;
	    padding-top:2px;
	    margin-left:2%;
	    width: 78% !important;
	}
	.popup-messages-footer {
	    background: #fff none repeat scroll 0 0;
	    bottom: 0;
	    position: absolute;
	    width: 100%;
	}
	.popup-messages-footer .btn-footer {
	    overflow: hidden;
	    padding-bottom:10%;
	    width: 100%;
	}
	.simple_round {
	    background: #d1d1d1 none repeat scroll 0 0;
	    border-radius: 50%;
	    color: #4b4b4b !important;
	    height: 21px;
	    padding: 0 0 0 1px;
	    width: 21px;
	}
	
	.popup-box .popup-messages {
	    background: #3f9684 none repeat scroll 0 0;
	    height: 275px;
	    overflow: auto;
	}
	.direct-chat-messages {
	    overflow: auto;
	    padding: 10px;
	    transform: translate(0px, 0px);
	    
	}
	.popup-messages .chat-box-single-line {
	    border-bottom: 1px solid #a4c6b5;
	    height: 12px;
	    margin: 7px 0 20px;
	    position: relative;
	    text-align: center;
	}
	.popup-messages abbr.timestamp {
	    background: #3f9684 none repeat scroll 0 0;
	    color: #fff;
	    padding: 0 11px;
	}
	
	.popup-head-right .btn-group {
	    display: inline-flex;
		margin: 0 8px 0 0;
		vertical-align: top !important;
	}
	.chat-header-button {
	    background: transparent none repeat scroll 0 0;
	    border: 1px solid #636364;
	    border-radius: 50%;
	    font-size: 14px;
	    height: 30px;
	    width: 30px;
	}
	.popup-head-right .btn-group .dropdown-menu {
	    border: medium none;
	    min-width: 122px;
		padding: 0;
	}
	.popup-head-right .btn-group .dropdown-menu li a {
	    font-size: 12px;
	    padding: 3px 10px;
		color: #303030;
	}
	
	.popup-messages abbr.timestamp {
	    background: #3f9684  none repeat scroll 0 0;
	    color: #fff;
	    padding: 0 11px;
	}
	.popup-messages .chat-box-single-line {
	    border-bottom: 1px solid #a4c6b5;
	    height: 12px;
	    margin: 7px 0 20px;
	    position: relative;
	    text-align: center;
	}
	.popup-messages .direct-chat-messages {
	    height: auto;
	}
	.popup-messages .direct-chat-text {
	    background: #dfece7 none repeat scroll 0 0;
	    border: 1px solid #dfece7;
	    border-radius: 2px;
	    color: #1f2121;
	}
	
	.popup-messages .direct-chat-timestamp {
	    color: #fff;
	    opacity: 0.6;
	}
	
	.popup-messages .direct-chat-name {
		font-size: 15px;
		font-weight: 600;
		margin: 0 0 0 49px !important;
		color: #fff;
		opacity: 0.9;
	}
	.popup-messages .direct-chat-info {
	    display: block;
	    font-size: 12px;
	    margin-bottom: 0;
	}
	.popup-messages  .big-round {
	    margin: -9px 0 0 !important;
	}
	.popup-messages  .direct-chat-img {
	    border: 1px solid #fff;
		background: #3f9684  none repeat scroll 0 0;
	    border-radius: 50%;
	    float: left;
	    height: 40px;
	    margin: -21px 0 0;
	    width: 40px;
	}
	.direct-chat-reply-name {
	    color: #fff;
	    font-size: 15px;
	    margin: 0 0 0 10px;
	    opacity: 0.9;
	}
	
	.direct-chat-img-reply-small
	{
	    float: left;
		font-size: 15px;
		color: #fff;
	    height: 20px;
	    margin: 0 15px;
	    width: 20px;
		background:#3f9684;
	}
	
	.popup-messages .direct-chat-msg {
	    margin-bottom: 10px;
	    position: relative;
	}
	
	.popup-messages .doted-border::after {
		background: transparent none repeat scroll 0 0 !important;
	    border-right: 2px dotted #fff !important;
		bottom: 0;
	    content: "";
	    left: 17px;
	    margin: 0;
	    position: absolute;
	    top: 0;
	    width: 2px;
		display: inline;
		z-index: -2;
	}
	
	.popup-messages .direct-chat-msg::after {
	    background: #fff none repeat scroll 0 0;
	    border-right: medium none;
	    bottom: 0;
	    content: "";
	    left: 17px;
	    margin: 0;
	    position: absolute;
	    top: 0;
	    width: 2px;
		display: inline;
		z-index: -2;
	}
	.direct-chat-text::after, .direct-chat-text::before {
	   
	    border-color: transparent #dfece7 transparent transparent;
	    
	}
	.direct-chat-text::after, .direct-chat-text::before {
	    -moz-border-bottom-colors: none;
	    -moz-border-left-colors: none;
	    -moz-border-right-colors: none;
	    -moz-border-top-colors: none;
	    border-color: transparent #d2d6de transparent transparent;
		border-style: solid;
	    border-image: none;
	    border-width: medium;
	    content: " ";
	    height: 0;
	    pointer-events: none;
	    position: absolute;
	    right: 101%;
	    top: 15px;
	    width: 0;
	}
	
	
	.direct-chat-text::after {
	    border-width: 5px;
	    margin-top: -5px;
	}
	.popup-messages .direct-chat-text {
	    background: #dfece7 none repeat scroll 0 0;
	    border: 1px solid #dfece7;
	    border-radius: 2px;
	    color: #1f2121;
		width:77%;
	}
	.direct-chat-text {
	    background: #d2d6de none repeat scroll 0 0;
	    border: 1px solid #d2d6de;
	    border-radius: 5px;
	    color: #444;
	    margin: 5px 0 0 50px;
	    padding: 5px 10px;
	    position: relative;
	}
	
	.direct-chat-text2::after, .direct-chat-text2::before {
	   
	    border-color: transparent #dfece7 transparent transparent;
	    
	}
	.direct-chat-text2::after, .direct-chat-text2::before {
	    -moz-border-bottom-colors: none;
	    -moz-border-left-colors: none;
	    -moz-border-right-colors: none;
	    -moz-border-top-colors: none;
	    border-color: transparent transparent transparent #d2d6de;
		border-style: solid;
	    border-image: none;
	    border-width: medium;
	    content: " ";
	    height: 0;
	    pointer-events: none;
	    position: absolute;
	    right: -5.5%;
	    top: 12px;
	    width: 0;
	}
	
	
	.direct-chat-text2::after {
	    border-width: 5px;
	    margin-top: -5px;
	}
	.popup-messages .direct-chat-text2 {
		font-size:14px;
	    background: #dfece7 none repeat scroll 0 0;
	    border: 1px solid #dfece7;
	    border-radius: 2px;
	    color: #1f2121;
		width:77%;
		margin-top: 10%;
	}
	.direct-chat-text2 {
	    background: #d2d6de none repeat scroll 0 0;
	    border: 1px solid #d2d6de;
	    border-radius: 5px;
	    color: #444;
	    margin: 5px 0 0 50px;
	    padding: 5px 10px;
	    position: relative;
	}
	
	</style>

    <link rel="stylesheet" href="slider/css/sangarSlider.css" type="text/css" media="all">
	<link rel="stylesheet" href="slider/themes/default/default.css" type="text/css" media="all">
	
<script type="text/javascript">

$(document).ready(function($) {
	//alert("aaa");
	$.UrlParam = function(name) {
		//宣告正規表達式
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		/*
		 * window.location.search 獲取URL ?之後的參數(包含問號)
		 * substr(1) 獲取第一個字以後的字串(就是去除掉?號)
		 * match(reg) 用正規表達式檢查是否符合要查詢的參數
		 */
		var r = window.location.search.substr(1).match(reg);
		//如果取出的參數存在則取出參數的值否則回穿null
		if (r != null)
			return unescape(r[2]);
		return null;
	}
	
	var param = $.UrlParam("page");

	$.ajax({
		data : "&pId=" + param,
		async: false,
		dataType : "text",
		type : "post",
		url : "./ManageCompanyServlet",
		success : function(data) {
			if (data == "null") {
				//document.location.href="index.jsp";
			}
			else {
				$(".rewritedata").html(data);
			}
		}
	})
});
</script>	
	
	
</head>
<!-- -------------------------------------------------------------------- -->
<body style="background-color:#e5e5e5;">
			<div class='alert'></div>
		    <div id="fh5co-offcanvass" style="overflow: scroll;">
		        <a href="#" class="fh5co-offcanvass-close js-fh5co-offcanvass-close">Menu <i class="icon-cross"></i> </a>
		        <h1 class="fh5co-logo"><a class="navbar-brand" href="index.jsp">HelloKitchen</a></h1>
		        <ul>
		            
		        <%
		        	SessionStamp st = (SessionStamp)request.getSession().getAttribute(GeneralVarName.Session_CompanyLogin);
		        	String Id=st.getLoginAccount();
			        CompanyFactory cf = (CompanyFactory) request.getServletContext().getAttribute(GeneralVarName.Web_CompanyFactory);
			        Company ALcf = (Company)cf.sreachByPK(Id);
			        if(!st.getLoginAccount().equals("nonelog"))
		              {
			        	out.println("<li><img class='head' src='"+ALcf.getCompany_logo()+"' style='width:60%;height:120%;'/></li><br/>");
		      			out.println("<li style='text-align:center;'>Hi, "+ALcf.getCompany_name()+" 您好</li>");
		              }
				%>    
		            <li><br/><a href="ManageCompany.jsp?page=<%=Id %>">資料更新</a></li>	           		            
		        </ul>
		        
		        <h4 style='color:#CD8500'>會員回覆</h4>
		        
		        <%
		        MessageFactory mf = (MessageFactory) request.getServletContext().getAttribute(GeneralVarName.Web_MessageFactory);		        
		        mf.refreshByDataBase();
		        Set hs = mf.getSendToMeNames(Id);
		        
		        for(Object v1:hs.toArray()){
		        	MemberFactory Memberf = (MemberFactory) request.getServletContext().getAttribute(GeneralVarName.Web_MemberFactory);
					Member mb=Memberf.sreachByPK(v1.toString());
		        %>
					<h4><a href="CompanyMessageServlet?receiver=<%=v1.toString() %>&sender=<%=Id %>" class="addClass" style='color:red'><%=mb.getMember_name() %></a></h4>
					
				<% }	         		        
		        %>
		        
		    </div>
    <header id="fh5co-header" role="banner" style='padding-bottom:0%;'>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <a href="#" class="fh5co-menu-btn js-fh5co-menu-btn">Menu <i class="icon-menu"></i></a>
                    <a class="navbar-brand" href="index.jsp">HelloKitchen</a>
                </div>
            </div>
        </div>
    </header>
    <!-- END .header -->
    <div id="fh5co-main">
        <div class="container">
<!-------------------------------------------------------------------------------------------------------------->                     

	<div>&nbsp;</div>        
<!-------------------------------------------------------------------------------------------------------------->



<!--rewirtedate Starting  -->
<div class="rewritedata">




<div class="image-container set-full-height" style="position:absolute;top:4%;z-index:-1;">
    <!--   Creative Tim Branding   -->


	<!--  Made With Get Shit Done Kit  -->
		

    <!--   Big container   -->
    <div class="container">
        <div class="row">
        <div class="col-sm-8 col-sm-offset-2">

            <!--      Wizard container        -->
            <div class="wizard-container">

                <div class="card wizard-card" data-color="green" id="wizardProfile">
                    <form name="form1" action="./AddcompanyServlet" method="post" enctype="multipart/form-data">
                <!--        You can switch ' data-color="orange" '  with one of the next bright colors: "blue", "green", "orange", "red"          -->

                    	<div class="wizard-header">
                        	<h3>
                        	   <b>BUILD</b> YOUR PROFILE <br>
                        	   <small>This information will let us know more about you.</small>
                        	</h3>
                    	</div>

						<div class="wizard-navigation">
							<ul>         
                                <li><a href="#about" data-toggle="tab">帳戶</a></li>
	                            <li><a href="#account" data-toggle="tab">作品集</a></li>
	                            <li><a href="#address" data-toggle="tab">細項</a></li>
	                        </ul>

						</div>

                        <div class="tab-content">
                            <div class="tab-pane" id="about">
                              <div class="row">
                                  <h4 class="info-text"> Let's start with the basic information (with validation)</h4>
                                  <div class="col-sm-4 col-sm-offset-1">
                                     
                                      <div class="picture-container">
                                          <div class="picture">
                                              <img src="assets/img/default-avatar.png" class="picture-src" id="wizardPicturePreview1" title=""/>
                                              <input type="file" name="logo" id="wizard-picture1">
                                          </div>
                                          <h6>Logo</h6>
                                      </div>
                                  </div>
                                  <div class="col-sm-6">
                                      <div class="form-group">
                                        <label>帳號 <small>(required)</small></label>
                                        <input name="email" type="email" class="form-control" placeholder="請使用email，如:andrew@creative-tim.com">
                                      </div>
                                      <div class="form-group">
                                        <label>密碼 <small>(required)</small></label>
                                        <input name="password" type="password" class="form-control" placeholder="請輸入密碼" required>
                                      </div>
                                      <div class="form-group">
                                        <label>確認密碼 <small>(required)</small></label>
                                        <input name="chkpwd" onchange="checkpwd()" type="password" class="form-control" placeholder="請再次輸入密碼" required>
                                      <div id="msg" style="color:red;"></div>
                                      </div>
                                  </div>
                                  <div class="col-sm-10 col-sm-offset-1">
	            					  <div class="form-group">
		                                  <label>公司介紹</label>
		                                  <textarea name="Detail" class="form-control" placeholder="" rows="9"></textarea>
	                              	  </div>
                                  </div>
                              </div>
                            </div>
                            <div class="tab-pane" id="account">
                                <h4 class="info-text"> 分享你的作品 </h4>
                                <div class="row">
                                    <div class="col-sm-10 col-sm-offset-1">
                                        <div class="col-sm-4 col-sm-offset-1">
                                            <div class="picture-container">
                                                <div class="picture">
                                                    <img src="assets/img/default-avatar.png" class="picture-src" id="wizardPicturePreview7" title="" />
                                                    <input type="file" name="cover" id="wizard-picture7"><!--到時要送到資料庫時要在加Name-->
                                                </div>
                                                <h6>網站封面</h6>
                                            </div>
                                        </div>

                                        <div class="col-sm-4 col-sm-offset-1">
                                            <div class="picture-container">
                                                <div class="picture">
                                                    <img src="assets/img/default-avatar.png" class="picture-src" id="wizardPicturePreview2" title="" />
                                                    <input type="file" name="pic1" id="wizard-picture2"><!--到時要送到資料庫時要在加Name-->
                                                </div>
                                                <h6>作品1</h6>
                                            </div>
                                        </div>

                                        <div class="col-sm-4 col-sm-offset-1">
                                            <div class="picture-container">
                                                <div class="picture">
                                                    <img src="assets/img/default-avatar.png" class="picture-src" id="wizardPicturePreview3" title="" />
                                                    <input type="file" name="pic2" id="wizard-picture3"><!--到時要送到資料庫時要在加Name-->
                                                </div>
                                                <h6>作品2</h6>
                                            </div>
                                        </div>

                                        <div class="col-sm-4 col-sm-offset-1">
                                            <div class="picture-container">
                                                <div class="picture">
                                                    <img src="assets/img/default-avatar.png" class="picture-src" id="wizardPicturePreview4" title="" />
                                                    <input type="file" name="pic3" id="wizard-picture4">
                                                </div>
                                                <h6>作品3</h6>
                                            </div>
                                        </div>

                                        <div class="col-sm-4 col-sm-offset-1">
                                            <div class="picture-container">
                                                <div class="picture">
                                                    <img src="assets/img/default-avatar.png" class="picture-src" id="wizardPicturePreview5" title="" />
                                                    <input type="file" name="pic4" id="wizard-picture5">
                                                </div>
                                                <h6>作品4</h6>
                                            </div>
                                        </div>

                                        <div class="col-sm-4 col-sm-offset-1">
                                            <div class="picture-container">
                                                <div class="picture">
                                                    <img src="assets/img/default-avatar.png" class="picture-src" id="wizardPicturePreview6" title="" />
                                                    <input type="file" name="pic5" id="wizard-picture6">
                                                </div>
                                                <h6>作品5</h6>
                                            </div>
                                        </div>
                                        
                                       
                                    </div>

                                </div>
                            </div>

                            <div class="tab-pane" id="address">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <h4 class="info-text"> 讓我們更認識你 </h4>
                                    </div>
                                    <div class="col-sm-7 col-sm-offset-1">
                                         <div class="form-group">
                                            <label>公司名稱</label>
                                            <input type="text" name="CompanyName" class="form-control" placeholder="XXX設計公司"><!--到時要送到資料庫時要在加Name-->
                                          </div>
                                    </div>
                                    <div class="col-sm-3">
                                         <div class="form-group">
                                            <label>負責人</label>
                                            <input type="text" name="Owner" class="form-control" placeholder="王小明"><!--到時要送到資料庫時要在加Name-->
                                          </div>
                                    </div>
                                    <div class="col-sm-10 col-sm-offset-1">
                                         <div class="form-group">
                                            <label>地址</label>
                                            <input type="text" name="Address" class="form-control" placeholder="New York..."><!--到時要送到資料庫時要在加Name-->
                                          </div>
                                    </div>
                                    <div class="col-sm-10 col-sm-offset-1">
                                         <div class="form-group">
                                            <label>連絡電話</label>
                                            <input type="text" name="Tel" class="form-control"><!--到時要送到資料庫時要在加Name-->
                                          </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="wizard-footer height-wizard">
                            <div class="pull-right">
                                <input id="next" type='button' class='btn btn-next btn-fill btn-warning btn-wd btn-sm' name='next' value='Next' />
                                <input type='submit' class='btn btn-finish btn-fill btn-warning btn-wd btn-sm' name='finish' value='Finish' />

                            </div>

                            <div class="pull-left">
                                <input type='button' class='btn btn-previous btn-fill btn-default btn-wd btn-sm' name='previous' value='Previous' />
                            </div>
                            <div class="clearfix"></div>
                        </div>

                    </form>
                </div>
            </div> <!-- wizard container -->
        </div>
        </div><!-- end row -->
    </div> <!--  big container -->

    <div class="footer">
        <div class="container">
             
        </div>
    </div>

</div>



<!--rewirtedate Ending  -->
</div>                    
<!--設定篩選範圍(包在Container底下之物件)---------------------------------------------------------->                     
       
    </div>
</div>

<!--回覆starting------------------------------------------------------------------------->
   
   
    <div class="popup-box chat-popup" id="qnimate">
        <div class="popup-head">
            <div id="senName" class="popup-head-left pull-left"> <%=ALcf.getCompany_name() %></div>
            <div class="popup-head-right pull-right">
                <button data-widget="remove" id="removeClass" class="chat-header-button pull-right" type="button"><i class="glyphicon glyphicon-off"></i></button>
            </div>
        </div>

        <div class="popup-messages">
<!--第1則留言------------------------------------------------->         
            <div class="direct-chat-messages">
                <div class="chat-box-single-line">
                    <abbr class="timestamp">October 8th, 2015</abbr>
                </div>
<!--本文--------------------------------------------------->
                <div class="direct-chat-msg doted-border">
<!--留言--------------------------------------------------->
                    <div class="direct-chat-info clearfix">
                        <span id= class="direct-chat-name pull-left"><%=ALcf.getCompany_name() %></span>
                    </div>

                    <div class="direct-chat-text">
                        Hey bro, how’s everything going ?
                    </div>
                    <div class="direct-chat-info clearfix">
                        <span class="direct-chat-timestamp pull-right"></span>
                    </div>
<!--回覆--------------------------------------------------->
                    <div class="direct-chat-info clearfix">
                        <span class="direct-chat-name pull-right">Singh</span>
                        <span class="direct-chat-reply-name"></span>
                        <div class="direct-chat-text2">
                            Today is a good day.
                        </div>
                    </div>
                </div>
            
            
            
            </div>
<!--第1則留言 Ending------------------------------------------------->


<!--第2則留言 Ending------------------------------------------------->
            <div class="addmessage"></div>
             
        </div>
        <div class="popup-messages-footer" style='margin-bottom:5%'>
            <textarea id="status_message" placeholder="Type a message..." rows="2" cols="40" name="message" style="width:300px;"></textarea>
            <button id="sendmessage" class="bg_none pull-right" style='margin-right:4%;'><i class="glyphicon glyphicon-envelope"></i> </button>	
        </div>
    </div>
	




<script type="text/javascript">       

$(function(){

	
	$('#sendmessage').click(function(){
		//alert($('#status_message').val());
		
		if($('#status_message').val()!=""){		
			//alert($('#receiver').val());
			
			if ($('.addmessage').html() != "") {
				
	            $('#insertmessage').append('<div class="direct-chat-text">' + $('#status_message').val() + '</div>');
	                
	        } else {
	            $('.addmessage').append('<div class="direct-chat-messages"><div class="chat-box-single-line"><abbr class="timestamp">最新訊息</abbr></div><div class="direct-chat-msg doted-border"><div class="direct-chat-info clearfix"><span class="direct-chat-name pull-left">'+"<%=ALcf.getCompany_name() %>"+'</span></div><div class="direct-chat-text">' + $('#status_message').val() + '</div><div id="insertmessage"></div><div class="direct-chat-info clearfix"><span class="direct-chat-timestamp pull-right"></span></div></div></div>');
	        }
			
			$.ajax({
				type : "POST",
				url : "./CompanyMessageServlet",
				data : {
					msg:"sendmsg",
					receiver:$('#receiver').val(),
					sender:"<%=Id%>",
					contact_message:$('#status_message').val()
				},
				//dataType: "text",
				//dataType: "html",
				//dataType: "json",
				 async:false
			});	
			$('#status_message').val('');
    	  	$(".popup-messages").animate({"scrollTop": ($(".popup-messages")[0].scrollHeight)*3}, "slow");

		}
	});
	
	 $('#status_message').keypress(function (e) {

	        code = e.keyCode ? e.keyCode : e.which; // in case of browser compatibility
	        if (code == 13) {
	            e.preventDefault();

	            if($(this).val()!=""){
	            	if ($('.addmessage').html() != "") {
		            	
		                $('#insertmessage').append('<div class="direct-chat-text">' + $(this).val() + '</div>');
		                
		            } else {
		                $('.addmessage').append('<div class="direct-chat-messages"><div class="chat-box-single-line"><abbr class="timestamp">最新訊息</abbr></div><div class="direct-chat-msg doted-border"><div class="direct-chat-info clearfix"><span class="direct-chat-name pull-left">'+"<%=ALcf.getCompany_name() %>"+'</span></div><div class="direct-chat-text">' + $(this).val() + '</div><div id="insertmessage"></div><div class="direct-chat-info clearfix"><span class="direct-chat-timestamp pull-right"></span></div></div></div>');
		            }
	            			
	    			$.ajax({
	    				type : "POST",
	    				url : "./CompanyMessageServlet",
	    				data : {
	    					msg:"sendmsg",
	    					receiver:$('#receiver').val(),
	    					sender:"<%=Id%>",
	    					contact_message:$('#status_message').val()
	    				},
	    				//dataType: "text",
	    				//dataType: "html",
	    				//dataType: "json",
	    				 async:false
	    			});
	    			$('#status_message').val('');
	        	  	$(".popup-messages").animate({"scrollTop": ($(".popup-messages")[0].scrollHeight)*3}, "slow");

	            }
	            // do something
	            /* also can use return false; instead. */
	        }
	    });
	
	$(".addClass").click(function () {
		$('#senName').html($(this).text());

		
		url = $(this).attr('href');
		$('.popup-messages').load(url);
		setInterval(function(){ $('.popup-messages').load(url);}, 3000);
	//	$('.popup-messages').load(url);

	  	$('#qnimate').addClass('popup-box-on');
	  	$(".popup-messages").animate({"scrollTop": ($(".popup-messages")[0].scrollHeight)*10}, "slow");

		return false;
	});
	  
		$("#removeClass").click(function () {
	  	$('#qnimate').removeClass('popup-box-on');
	});
})


</script>




<!--回覆Ending-->


       

    <!-- jQuery -->
    <script src="js/jquery.min.js"></script>
    <!-- jQuery Easing -->
    <script src="js/jquery.easing.1.3.js"></script>
    <!-- Bootstrap -->
    <script src="js/bootstrap.min.js"></script>
    <!-- Waypoints -->
    <script src="js/jquery.waypoints.min.js"></script>
    <!-- Magnific Popup -->
    <script src="js/jquery.magnific-popup.min.js"></script>
    <!-- Salvattore -->
    <script src="js/salvattore.min.js"></script>
    <!-- Main JS -->
    <script src="js/main.js"></script>
    <script src="js/jquery.mixitup.min.js"></script>
	

	
	
	
    <script  type='text/javascript'>

        $(document).ready(function () {
        	$(".fh5co-menu-btn").get(0).click();
            
            $(window).resize(function() {
            	
            	x=$(window).width();		
        		if(x>=750){//寬版      
        			$('#resizenav').removeClass('nav');
        			$('#resizenav').removeClass('navbar-nav');
        			$('#resizenavdiv1').addClass('cd-tab-filter-wrapper');
        			$('#resizenavdiv2').addClass('cd-tab-filter');
        			$('#navbar').removeClass('collapse');
        			$('#navbar').removeClass('navbar-collapse');
               }else{//寬版      
        			$('#resizenav').addClass('nav');
        			$('#resizenav').addClass('navbar-nav');
        			$('#resizenavdiv1').removeClass('cd-tab-filter-wrapper');
        			$('#resizenavdiv2').removeClass('cd-tab-filter');
        			$('#navbar').addClass('collapse');
        			$('#navbar').addClass('navbar-collapse');
               }
            });
        });
    </script>
    
    <script>
        //set the default value
        var txtId = 0;
        var process=0;

        //add input block in showBlock
        $(".addItem").click(function () {
            txtId++;
            $("#showBlock").append('<div class="col-sm-7 col-sm-offset-1" id="div' + txtId + '"><div class="form-group"><input name="Material' + txtId + '" type="text" class="form-control" placeholder="麵粉 100克"></div></div>');//到時要送到資料庫時要在加Name
            });

        $(".addProcess").click(function () {
            process++;
            $("#addProcess").append('<div class="col-sm-7 col-sm-offset-1" id="process' + process + '"><div class="form-group"><input name="Method' + process + '" type="text" class="form-control" placeholder="步驟說明"></div></div>');//到時要送到資料庫時要在加Name
            });


        //remove div
        function deltxt(id) {
            $("#div"+txtId).remove();
            txtId--;
        }
        function delprocess(id) {
            $("#process"+process).remove();
            process--;
        }
    </script>
    
    
</body>

<!--   Core JS Files   -->
<script src="assets/js/jquery.bootstrap.wizard.js" type="text/javascript"></script>

<!--  Plugin for the Wizard -->
<script src="assets/js/gsdk-bootstrap-wizard.js"></script>

<!--  More information about jquery.validate here: http://jqueryvalidation.org/	 -->
<script src="assets/js/jquery.validate.min.js"></script>


</html>