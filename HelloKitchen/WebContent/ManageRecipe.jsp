<%@page import="EntityComponents.Recipe"%>
<%@page import="Factorys.RecipeFactory"%>
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
	
	<link rel="stylesheet" type="text/css" href="css/jquery.msgBox.min.css">
	
	
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
	
	.clickArea {
		cursor: pointer;
		color: darkblue;
		text-decoration: underline;
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
		url : "./ManageRecipeServlet",
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
			<div class="msgBox-testArea"></div>
			<div class='alert'></div>
		    <div id="fh5co-offcanvass" style="overflow: scroll;">
		        <a href="#" class="fh5co-offcanvass-close js-fh5co-offcanvass-close">Menu <i class="icon-cross"></i> </a>
		        <h1 class="fh5co-logo"><a class="navbar-brand" href="index.jsp">HelloKitchen</a></h1>
		        <ul>
		        <%
		        SessionStamp st = (SessionStamp)request.getSession().getAttribute(GeneralVarName.Session_LoginAccount);
	            String Id=st.getLoginAccount();
	            
	            MemberFactory mf = (MemberFactory) request.getServletContext().getAttribute(GeneralVarName.Web_MemberFactory);
				Member mb=mf.sreachByPK(Id);
	            
		        if(!st.getLoginAccount().equals("nonelog"))
	              {
	      			out.println("<li><a href='login.jsp'><img class='head' src='images/logo2.jpg'/></a></li>");
	      			out.println("<li style='text-align:center;'>Hi, "+mb.getMember_name()+" 您好</li>");
	      			out.println("<h4 style='color:#CD8500'>我的食譜</h4>");

	              }

		        RecipeFactory rc = (RecipeFactory) request.getServletContext().getAttribute(GeneralVarName.Web_RecipeFactory);
				
				ArrayList<Recipe> dataset = (ArrayList<Recipe>)rc.SearchPKbyMemberId(Id);
				int count=1;
				for( Recipe temp: dataset){
				%><li><a href="ManageRecipe.jsp?page=<%=temp.getRecipe_id() %>"><%=count %>. <%=temp.getRecipe_name() %></a></li>									
				<%
				count++;}%>          		            
		        </ul>
		        <h4 style='color:#CD8500'>廠商回覆</h4>
		        <%
		        MessageFactory msgf = (MessageFactory) request.getServletContext().getAttribute(GeneralVarName.Web_MessageFactory);		        
		        msgf.refreshByDataBase();
		        Set hs = msgf.getSendToMeNames(Id);
		        
		        for(Object v1:hs.toArray()){
		        	CompanyFactory cf = (CompanyFactory) request.getServletContext().getAttribute(GeneralVarName.Web_CompanyFactory);
			        Company ALcf = (Company)cf.sreachByPK(v1.toString());
		        %>
					<h4><a href="CompanyMessageServlet?receiver=<%=v1.toString() %>&sender=<%=Id %>" class="addClass" style='color:red'><%=ALcf.getCompany_name() %></a></h4>
					
				<%}	         		        
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
<div class="rewritedata" >
	<div class="image-container set-full-height" style="position:absolute;top:4%;z-index:-1;">
        <!--   Creative Tim Branding   -->
        <!--  Made With Get Shit Done Kit  -->
        <!--   Big container   -->
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2">

                    <!--      Wizard container        -->
                    <div class="wizard-container">

                        <div class="card wizard-card" data-color="orange" id="wizardProfile">

                   
                               <form action="./AddrecipeServlet" method="post" enctype="multipart/form-data">
                            
                                <!--        You can switch ' data-color="orange" '  with one of the next bright colors: "blue", "green", "orange", "red"          -->

                                <div class="wizard-header">
                                    <h3>
                                        <b>BUILD</b> Your Recipe <br>
                                        <small></small>
                                    </h3>
                                </div>

                                <div class="wizard-navigation">
                                    <ul>

                                        <li><a href="#about" data-toggle="tab">食譜</a></li>
                                        <li><a href="#account" data-toggle="tab">材料</a></li>
                                        <li><a href="#address" data-toggle="tab">步驟</a></li>
                                    </ul>

                                </div>

                                <div class="tab-content">
                                    <div class="tab-pane" id="about">
                                        <div class="row">
                                            <h4 class="info-text"> Let's start with the basic information (with validation)</h4>
                                            <div class="col-sm-4 col-sm-offset-1">

                                                <div class="picture-container">
                                                    <div class="picture">
                                                        <img src="assets/img/default-avatar.png" class="picture-src" id="wizardPicturePreview1" title="" />
                                                        <input type="file" name="Pic" id="wizard-picture1"><!--到時要送到資料庫時要在加Name-->
                                                    </div>
                                                    <h6>食譜代表圖片</h6>
                                                </div>
                                            </div>
                                            <div class="col-sm-6">
                                                <div class="form-group">
                                                    <label>標題 <small>(required)</small></label>
                                                    <input name="Name" type="text" class="form-control" placeholder="黃金開口笑" required>
                                                </div>
                                                <div class="form-group">
                                                    <label>上傳日期</label>
                                                    <input name="Date" type="text" class="form-control" placeholder="2017/05/31">
                                                </div>
<!--                                                 <div class="form-group"> -->
<!--                                                     <label>幾人份</label> -->
<!--                                                     <input name="Amount" type="text" class="form-control" placeholder="5人"> -->
<!--                                                 </div> -->
                                            </div>
                                            <div class="col-sm-10 col-sm-offset-1">
                                                <div class="form-group">
                                                    <label>Place description</label>
                                                    <textarea name="Detail" class="form-control" placeholder="" rows="9"></textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
<!--材料--------------------------------------------------------------------------------------------->
                                    <div class="tab-pane" id="account">
                                        <h4 class="info-text"> 需要那些材料?? </h4>
                                        <div class="row">
                                            <div class="col-sm-7 col-sm-offset-1">
                                                <div class="form-group">
												<label>名稱</label>
                                                    <input type="text" name="Material0" class="form-control" placeholder="麵粉"><!--到時要送到資料庫時要在加Name-->
                                                </div>
                                            </div>
											<div class="col-sm-3">
                                                <div class="form-group">
													<label>數量</label>
                                                    <input type="text" name="Material0" class="form-control" placeholder="麵粉 100克"><!--到時要送到資料庫時要在加Name-->
                                                </div>
                                            </div>
                                            <div id="showBlock"></div>
                                            <div class="col-sm-7 col-sm-offset-1">
                                                <input type="button" value="addItem" class='btn btn-fill btn-warning btn-wd btn-sm addItem' name='next' />
                                                <input type="button" value="del" class='btn btn-fill btn-default btn-wd btn-sm' onclick="deltxt('+txtId+')">
                                            </div>
                                        </div>
                                    </div>
<!--步驟--------------------------------------------------------------------------------------------->
                                    <div class="tab-pane" id="address">
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <h4 class="info-text"> 步驟 </h4>
                                            </div>

                                            <div class="col-sm-7 col-sm-offset-1">
                                                <div class="form-group">

                                                    <input type="text" name="Method0" class="form-control" placeholder="步驟說明">
                                                </div>
                                            </div>

                                            <div id="addProcess"></div>
                                            <div class="col-sm-7 col-sm-offset-1">
                                                <input type="button" value="addProcess" class='btn btn-fill btn-warning btn-wd btn-sm addProcess' name='next' />
                                                <input type="button" value="del" class='btn btn-fill btn-default btn-wd btn-sm' onclick="delprocess('+process+')">
                                            </div>
                                        </div>

                                    </div>
                                </div>
                                <div class="wizard-footer height-wizard">
                                    <div class="pull-right">
                                        <input type='button' class='btn btn-next btn-fill btn-warning btn-wd btn-sm' name='next' value='Next' />
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
    
</div>    	          
<!-------------------------------------------------------------------------------------------------------------->
                          
<!--設定篩選範圍(包在Container底下之物件)---------------------------------------------------------->                     
       
    </div>
</div>

<!--回覆starting------------------------------------------------------------------------->
   
    <div class="popup-box chat-popup" id="qnimate">
        <div class="popup-head">
            <div id="senName" class="popup-head-left pull-left"><%=mb.getMember_name() %> </div>
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
                        <span class="direct-chat-name pull-left"><%=mb.getMember_name() %> </span>
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

<!--第2則留言------------------------------------------------->
            <div class="direct-chat-messages">
                <div class="chat-box-single-line">
                    <abbr class="timestamp">October 28th, 2017</abbr>
                </div>
<!--本文--------------------------------------------------->
                <div class="direct-chat-msg doted-border">
<!--留言--------------------------------------------------->
                    <div class="direct-chat-info clearfix">
                        <span class="direct-chat-name pull-left">財團法人資訊工業策進會</span>
                    </div>

                    <div class="direct-chat-text">
                        Hey bro, how’s everything going ?
                        Hey bro, how’s everything going ?
                        Hey bro, how’s everything going ?
                        Hey bro, how’s everything going ?
                        Hey bro, how’s everything going ?
                        Hey bro, how’s everything going ?
                        Hey bro, how’s everything going ?
                        Hey bro, how’s everything going ?
                    </div>
                    <div class="direct-chat-info clearfix">
                        <span class="direct-chat-timestamp pull-right"></span>
                    </div>
<!--回覆--------------------------------------------------->
                    <div class="direct-chat-info clearfix">
                        <span class="direct-chat-name pull-right">財團法人資訊工業策進會2</span>
                        <span class="direct-chat-reply-name"></span>
                        <div class="direct-chat-text2">
                            Not your business.
                            Not your business.
                            Not your business.
                            Not your business.
                            Not your business.
                            Not your business.
                            Not your business.
                            Not your business.

                        </div>
                    </div>
                </div>
            </div>
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
			if ($('.addmessage').html() != "") {
	            $('#insertmessage').append('<div class="direct-chat-text">' + $('#status_message').val() + '</div>');
	           // $('#status_message').val("");     
	        } else {
	            $('.addmessage').append('<div class="direct-chat-messages"><div class="chat-box-single-line"><abbr class="timestamp">最新訊息</abbr></div><div class="direct-chat-msg doted-border"><div class="direct-chat-info clearfix"><span class="direct-chat-name pull-left">'+"<%=mb.getMember_name() %>"+'</span></div><div class="direct-chat-text">' + $('#status_message').val() + '</div><div id="insertmessage"></div><div class="direct-chat-info clearfix"><span class="direct-chat-timestamp pull-right"></span></div></div></div>');
	           // $('#status_message').val("");
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
    	  	$(".popup-messages").animate({"scrollTop": ($(".popup-messages")[0].scrollHeight)*10}, "slow");

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
		                $('.addmessage').append('<div class="direct-chat-messages"><div class="chat-box-single-line"><abbr class="timestamp">最新訊息</abbr></div><div class="direct-chat-msg doted-border"><div class="direct-chat-info clearfix"><span class="direct-chat-name pull-left">'+"<%=mb.getMember_name() %>"+'</span></div><div class="direct-chat-text">' + $(this).val() + '</div><div id="insertmessage"></div><div class="direct-chat-info clearfix"><span class="direct-chat-timestamp pull-right"></span></div></div></div>');
		                
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
	        	  	$(".popup-messages").animate({"scrollTop": ($(".popup-messages")[0].scrollHeight)*10}, "slow");

	            }
	            // do something
	            /* also can use return false; instead. */
	        }
	    });
	
	$(".addClass").click(function () {
		$('#senName').html($(this).text());
		
		var url = $(this).attr('href');
		$('.popup-messages').load(url);
		setInterval(function(){ $('.popup-messages').load(url);}, 3000);
		//$('.popup-messages').load(url);

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

    <!-- Waypoints -->
    <script src="js/jquery.waypoints.min.js"></script>
    <!-- Magnific Popup -->
    <script src="js/jquery.magnific-popup.min.js"></script>
    <!-- Salvattore -->
    <script src="js/salvattore.min.js"></script>
    <!-- Main JS -->
    <script src="js/main.js"></script>
    <script src="js/jquery.mixitup.min.js"></script>

	<script src="js/jquery.msgBox.min.js"></script>
	
	<script type="text/javascript">
		$('body').on('click','.clickArea', function() {
			var $msgBox = $('.msgBox-testArea').msgBox({
				type: 'question',
				title: '刪除確認?',
				message: '您確定要刪除這篇食譜?',
				buttons: [
					{
						text: '確定',
						callback: function() {
							$('p').fadeIn();
							var path=$('#path').val();
			          		location.href=path;
						}
					},
					{
						text: '取消',
						callback: function() {
							return false;
						}
					}
				],
				blend: true
			});
			return false;
		});
	</script>
	
    <script  type='text/javascript'>
    
    	$(document).ready(function () {
	    	$(".fh5co-menu-btn").get(0).click();
	    });
    	
    	 $(function () {
    		 if($(".rewritedata").html()==""){
    			 $(".rewritedata").html("<div class='image-container set-full-height' style='position:absolute;top:4%;z-index:-1;'>"
					+ "<div class='container'><div class='row'>"
					+ "<div class='col-sm-8 col-sm-offset-2'>"
					+ "<div class='wizard-container'>"
					+ "<div class='card wizard-card' data-color='orange' id='wizardProfile'></div></div></div></div></div></div>");
    		 }
    	 })
    	
	    //add input block in showBlock

		$('body').on('click','.addItem',function () {
            txtId++;
            $("#showBlock").append('<div class="col-sm-7 col-sm-offset-1" id="div' + txtId + '"><div class="form-group"><input name="Material' + txtId + '" type="text" class="form-control" placeholder="麵粉" required></div></div>');
            $("#showBlock").append('<div class="col-sm-3" id="divAmount' + txtId + '"><div class="form-group"><input name="Amount' + txtId + '" type="text" class="form-control" placeholder="100克" required></div></div>');  
        })
        
        $('body').on('click','.addProcess',function () {
        	process++;
            $("#addProcess").append('<div class="col-sm-7 col-sm-offset-1" id="process' + process + '"><div class="form-group"><input name="Method' + process + '" type="text" class="form-control" placeholder="步驟說明" required></div></div>');
        })
        
        //remove div        
        $('body').on('click','.delItem',function () {
            $("#div"+txtId).remove();
            $("#divAmount"+txtId).remove();
            txtId--;
        })
        
        $('body').on('click','.delProcess',function () {
            $("#process"+process).remove();
            process--;
        })   
    </script>
    <!-- Bootstrap -->

    
</body>
<!-- Bootstrap -->
<script src="js/bootstrap.min.js"></script>
<!--   Core JS Files   -->
<script src="assets/js/jquery.bootstrap.wizard.js" type="text/javascript"></script>

<!--  Plugin for the Wizard -->
<script src="assets/js/gsdk-bootstrap-wizard.js"></script>

<!--  More information about jquery.validate here: http://jqueryvalidation.org/	 -->
<script src="assets/js/jquery.validate.min.js"></script>





</html>