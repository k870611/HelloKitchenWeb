<%@page import="EntityComponents.Recipe"%>
<%@page import="Factorys.RecipeFactory"%>
<%@page import="EntityComponents.Company"%>
<%@page import="Factorys.CompanyFactory"%>
<%@page import="EntityComponents.Member"%>
<%@page import="Factorys.MemberFactory"%>
<%@page import="Utility.GeneralVarName"%>
<%@page import="Modules.SessionStamp"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String message = (String) request.getAttribute ("Message");%>
<%String CompanyId="none";String showrecipe= (String)request.getParameter("show");

	if((SessionStamp)request.getSession().getAttribute(GeneralVarName.Session_CompanyLogin)!=null){
		SessionStamp st = (SessionStamp)request.getSession().getAttribute(GeneralVarName.Session_CompanyLogin);
		CompanyId=st.getLoginAccount();
	} 
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>HelloKitchen &mdash; 輕鬆解決廚房大小事</title>
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
	<script>document.documentElement.className = 'js';</script>
	
    <!---->
	<script src="js/jquery-2.1.1.js"></script>
    <style type="text/css">
    #Container .mix {
        display: none;
    }

    ul.ChangeColor li.current a {
        color: #41307c;
    }
    
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
	    background-color:  #e5e5e5;
	}
			
	input::-webkit-input-placeholder {  /* WebKit browsers */
    font-size: 40px;
	}
	input:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
	    font-size: 24px;
	}
	input::-moz-placeholder { /* Mozilla Firefox 19+ */
	    font-size: 24px;
	}
	input :-ms-input-placeholder { /* Internet Explorer 10+ */
	    font-size: 24px;
	}		
	
/* 	.category-2 {display:none!important;} */
	</style>
    <script type="text/javascript">
    
    
//     function showPagination() {
		
// 		$.ajax({
// 			dataType : "text",
// 			type : "post",
// 			url : "./IndexProductLoadServlet",
// 			success : function(data) {
// 				if (data == "null") {
// 					document.location.href="index.jsp";
// 				}
// 				else {
// 					$("#fh5co-board").html(data);
// 				}
// 			}
// 		});
		
// 		$(document).on('submit','#search', function(){
// 			var formData = $(this).serialize();
// 			$.ajax({
// 				data : formData,
// 				dataType : "text",
// 				type : "post",
// 				url : "ProductSearch",
// 				success : function(data) {
// 					if (data == "null") {
// 						alert('查無資料');
// 						document.location.href="index.jsp";
// 					}
// 					else {
// 						$(".section").html(data);
// 					}
// 				}
// 			});
// 			return false;
// 		});
// 	}
   
        $(document).ready(function () {
            $(".ChangeColor li").first().addClass("current");
            $(".filter a").first().addClass("selected");
            $(".filter a").click(function () {
            	$(".filter a").removeClass("selected");
            	$(this).addClass("selected")            
            });
        	$('.demo-2').hide();
        });
    </script>
    <link rel="stylesheet" href="slider/css/sangarSlider.css" type="text/css" media="all">
	<link rel="stylesheet" href="slider/themes/default/default.css" type="text/css" media="all">
</head>
<!-- -------------------------------------------------------------------- -->
<body style="background-color:#fff;">
<div class='alert'></div>
	<div class="demo-2">
		<svg class="hidden">
			<defs>
				<symbol id="icon-arrow" viewBox="0 0 24 24">
					<title>arrow</title>
					<polygon points="6.3,12.8 20.9,12.8 20.9,11.2 6.3,11.2 10.2,7.2 9,6 3.1,12 9,18 10.2,16.8 "/>
				</symbol>
				<symbol id="icon-drop" viewBox="0 0 24 24">
					<title>drop</title>
					<path d="M12,21c-3.6,0-6.6-3-6.6-6.6C5.4,11,10.8,4,11.4,3.2C11.6,3.1,11.8,3,12,3s0.4,0.1,0.6,0.3c0.6,0.8,6.1,7.8,6.1,11.2C18.6,18.1,15.6,21,12,21zM12,4.8c-1.8,2.4-5.2,7.4-5.2,9.6c0,2.9,2.3,5.2,5.2,5.2s5.2-2.3,5.2-5.2C17.2,12.2,13.8,7.3,12,4.8z"/><path d="M12,18.2c-0.4,0-0.7-0.3-0.7-0.7s0.3-0.7,0.7-0.7c1.3,0,2.4-1.1,2.4-2.4c0-0.4,0.3-0.7,0.7-0.7c0.4,0,0.7,0.3,0.7,0.7C15.8,16.5,14.1,18.2,12,18.2z"/>
				</symbol>
				<symbol id="icon-search" viewBox="0 0 24 24">
					<title>search</title>
					<path d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"/>
				</symbol>
				<symbol id="icon-cross" viewBox="0 0 24 24">
					<title>cross</title>
					<path d="M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z"/>
				</symbol>
			</defs>
		</svg>
			<div class="search" style="position:fixed; top:33%">
				<button id="btn-search-close" class="btn btn--search-close" aria-label="Close search form"><svg class="icon icon--cross"><use xlink:href="#icon-cross"></use></svg></button>
				<form class="search__form" action="./SearchResultServlet">
					<input id="search-input" class="search__input" name="search" type="search" placeholder="搜尋食材、食譜、作者" />
					<button class="btn btn--search"><svg class="icon icon--search"><use xlink:href="#icon-search"></use></svg></button>
				</form>
				<div class="search__suggestion">
					<h3>May We Suggest?</h3>
					<p>#drone #funny #catgif #broken #lost #love #hilarious #good #red #blue #nono #why #yes #yesyes #aliens #green #fancy #pants #trees</p>
				</div>
			</div><!-- /search -->
        </div>	

		    <div id="fh5co-offcanvass">
		        <a href="#" class="fh5co-offcanvass-close js-fh5co-offcanvass-close">Menu <i class="icon-cross"></i> </a>
		        <h1 class="fh5co-logo"><a class="navbar-brand" href="index.jsp">HelloKitchen</a></h1>
		        <ul>
		            <%
		            if(((SessionStamp)request.getSession().getAttribute(GeneralVarName.Session_LoginAccount))!=null&&((SessionStamp)request.getSession().getAttribute(GeneralVarName.Session_LoginAccount)).getLoginAccount()=="nonelog"){
		      			out.println("<li><a href='login.jsp'><img class='head' src='images/head.jpg'/></a></li>");
		            }else if((SessionStamp)request.getSession().getAttribute(GeneralVarName.Session_LoginAccount)==null &&
		            		(SessionStamp)request.getSession().getAttribute(GeneralVarName.Session_CompanyLogin)==null){
		      			out.println("<li><a href='login.jsp'><img class='head' src='images/head.jpg'/></a></li>");

		            }
		            if((SessionStamp)request.getSession().getAttribute(GeneralVarName.Session_LoginAccount)!=null){

		            	SessionStamp st = (SessionStamp)request.getSession().getAttribute(GeneralVarName.Session_LoginAccount);

		            	String MemberId=st.getLoginAccount();
		        		MemberFactory mf = (MemberFactory) request.getServletContext().getAttribute(GeneralVarName.Web_MemberFactory);
						Member mb=mf.sreachByPK(MemberId);
		            	
		            	if(!st.getLoginAccount().equals("nonelog") )
			              {
			      			out.println("<li><a href='login.jsp'><img class='head' src='images/logo2.jpg'/></a></li>");
			      			out.println("<li style='text-align:center;'>Hi, "+mb.getMember_name()+" 您好</li>");
			              }
			            message = st.getLoginAccount();
		            }else if((SessionStamp)request.getSession().getAttribute(GeneralVarName.Session_CompanyLogin)!=null){
		        		SessionStamp st = (SessionStamp)request.getSession().getAttribute(GeneralVarName.Session_CompanyLogin);
		        		CompanyId=st.getLoginAccount();
				        CompanyFactory cf = (CompanyFactory) request.getServletContext().getAttribute(GeneralVarName.Web_CompanyFactory);
				        Company ALcf = (Company)cf.sreachByPK(CompanyId);
		        		
		        		if(!st.getLoginAccount().equals("nonelog") ){
			      			out.println("<li><a href='login.jsp'><img class='head' src='"+ALcf.getCompany_logo()+"'/></a></li>");
			      			out.println("<li style='text-align:center;'>Hi, "+ALcf.getCompany_name()+" 您好</li>");
			            }
		        	} 
		        	%>
		            <li><a class="addrecipe" href="addrecipe.jsp">新增食譜</a></li>
		            <li><a class="addrecipe" href="ManageRecipe.jsp">會員專區</a></li>   
		            <li><a class="comlogin" href="CompanyLogin.jsp">廠商專區</a></li> 
		        </ul>
		        <h3 class="fh5co-lead">Connect with us</h3>
		        <p class="fh5co-social-icons">
		            <a href="#"><i class="icon-twitter"></i></a>
		            <a href="#"><i class="icon-facebook"></i></a>
		            <a href="#"><i class="icon-instagram"></i></a>
		            <a href="#"><i class="icon-dribbble"></i></a>
		            <a href="#"><i class="icon-youtube"></i></a>
		        </p>
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
        <div class='sangar-slideshow-container' id='sangar-example'>
			<div class='sangar-content-wrapper'>			
				<div class='sangar-content'><img id="searchnav" src='images/sshot-1.png' /></div>
				<div class='sangar-content'><img id="recipenav" src='images/sshot-2.jpg' /></div>				
				<div class='sangar-content'><img id="comnav" src='images/slider-3.png' /></div>			
			</div>
		</div>	          
<!-------------------------------------------------------------------------------------------------------------->
                            
        <!--設定篩選範圍(包在Container底下之物件)---------------------------------------------------------->                     
        <div id="Container">
            <!------------------------------------------------------------------------------------------------->
            <div id="fh5co-board" data-columns>
                <!--篩選物件(Class需帶 "mix" 條件為category-1 )---------------------------------------------------------------->         
            <%CompanyFactory cc = (CompanyFactory) request.getServletContext().getAttribute(GeneralVarName.Web_CompanyFactory);         
          	if(cc!=null){
            	ArrayList<Company> dataset = (ArrayList<Company>) cc.getAll();
            	for( Company temp: dataset){
            		%>
        			<div class="item mix category-2">
                    <div class="animate-box company"  style="display:none;">
                        <a href="company.jsp?page=<%=temp.getCompany_id() %>" class="fh5co-board-img" title="<%=temp.getCompany_intro() %>"><img  style="height:170px" src="<%=temp.getCompany_cover() %>" alt="Free HTML5 Bootstrap template"></a>
                        <div class="fh5co-desc"><%=temp.getCompany_name()%></div>
                    </div>
                </div>
            	<%	
            	}
           }%> 
            
            <%RecipeFactory rc = (RecipeFactory) request.getServletContext().getAttribute(GeneralVarName.Web_RecipeFactory);
         	 if(rc!=null){
               	ArrayList<Recipe> dataset = (ArrayList<Recipe>) rc.getAll();
				
            	for( Recipe temp: dataset){
            		if(temp.isRecipe_status()){
            		%>
        			<div class="item mix category-1" >
                    <div class="animate-box recipe" style="display:none;">
                        <a href="recipe.jsp?page=<%=temp.getRecipe_id() %>" class="fh5co-board-img" title="<%=temp.getRecipe_detail() %>"><img src="<%=temp.getRecipe_picture()%>" alt="Free HTML5 Bootstrap template"></a>
                        <div class="fh5co-desc"><%=temp.getRecipe_name()%></div>
                    </div>
                </div>
            	<%	
            	}}
           }%> 
                       
            </div>
        </div>
    </div>
</div>

        <div class="container" style="padding:2%;">
            <div class="row row-padded">
                <div class="col-md-12 text-center">
                    <p class="fh5co-social-icons">
                        <a href="#"><i class="icon-twitter"></i></a>
                        <a href="#"><i class="icon-facebook"></i></a>
                        <a href="#"><i class="icon-instagram"></i></a>
                        <a href="#"><i class="icon-dribbble"></i></a>
                        <a href="#"><i class="icon-youtube"></i></a>
                    </p>
                    <p><small>&copy; HelloKitchen &mdash; All Rights Reserved.</small></p>
                </div>
            </div>
        </div>

    <!-- jQuery -->
    <script src="js/jquery-ui.js"></script>
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
	
	<script type="text/javascript" src="slider/js/jquery.js"></script>
	<script type="text/javascript" src="slider/js/jquery.touchSwipe.min.js"></script>
	<script type="text/javascript" src="slider/js/imagesloaded.min.js"></script>
	<!-- jQuery Sangar Slider -->
	<script type="text/javascript" src="slider/js/sangarSlider/sangarBaseClass.js"></script>
	<script type="text/javascript" src="slider/js/sangarSlider/sangarSetupLayout.js"></script>
	<script type="text/javascript" src="slider/js/sangarSlider/sangarSizeAndScale.js"></script>
	<script type="text/javascript" src="slider/js/sangarSlider/sangarShift.js"></script>
	<script type="text/javascript" src="slider/js/sangarSlider/sangarSetupBulletNav.js"></script>
	<script type="text/javascript" src="slider/js/sangarSlider/sangarSetupNavigation.js"></script>
	<script type="text/javascript" src="slider/js/sangarSlider/sangarSetupSwipeTouch.js"></script>
	<script type="text/javascript" src="slider/js/sangarSlider/sangarSetupTimer.js"></script>
	<script type="text/javascript" src="slider/js/sangarSlider/sangarBeforeAfter.js"></script>
	<script type="text/javascript" src="slider/js/sangarSlider/sangarLock.js"></script>
	<script type="text/javascript" src="slider/js/sangarSlider/sangarResponsiveClass.js"></script>
	<script type="text/javascript" src="slider/js/sangarSlider/sangarResetSlider.js"></script>
	<script type="text/javascript" src="slider/js/sangarSlider/sangarTextbox.js"></script>
	<script type="text/javascript" src="slider/js/sangarSlider.js"></script>
	
	<script src="js/demo2.js"></script>
	
	
	<script type='text/javascript'>
		jQuery(document).ready(function($) {
			/**
			 * identifier variable must be unique ID
			 */
			var sangar = $('#sangar-example').sangarSlider({
				animation : 'fade', // horizontal-slide, vertical-slide, fade
				animationSpeed : 1000, // how fast animtions are
				advanceSpeed : 3000, 
				timer : true, // true or false to have the timer
				width : 850, // slideshow width
    			height : 400 // slideshow height				
			});
		})				
	</script>
	
    <script  type='text/javascript'>

        $(function () {
            $('#Container').mixItUp();	
        });
        
        $(document).ready(function () { 

        	$('#btn-search-close').click(function(){
        		$('.search').css("top","33%");
        	});
        	
        	$('#search-input').click(function(){
        		$('.search').css("top","0%");
        		//style="position:fixed; top:33%"
        	});
        	
        	$("#btn-search-close").click(function(){
        		$('.demo-2').hide();
        	});
        	
        	$('body').on('click','#searchnav',function(){
        		$('.demo-2').show();
        		$("#search-input").focus();
        		$('.search').css("top","0%");
            });
        	
            $('body').on('click','#recipenav',function(){
            	$('.sangar-slideshow-container').animate({ height: "0px", width: "900px" }, 2000, 'easeOutBounce');
            	setTimeout(function() {$('.sangar-slideshow-container').hide();}, 1950);
            	setTimeout(function() {$('.recipe').show();}, 2500);
            	$('.company').hide();
            });
            //--自動觸發---
            var show = "<%=showrecipe%>";
            if( show == 'show'){
            	setTimeout(function() {$('.sangar-slideshow-container').hide();}, 00);
            	setTimeout(function() {$('.recipe').show();}, 1000);
            	$('.company').hide();
            }
            
            $('body').on('click','#comnav',function(){
            	$('.sangar-slideshow-container').animate({ height: "0px", width: "900px" }, 2000, 'easeOutBounce');
            	setTimeout(function() {$('.sangar-slideshow-container').hide();}, 1950);
            	setTimeout(function() {$('.company').show();}, 2500);
            	$('.recipe').hide();	
            });
            
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

		var chk="<%=message%>";
		
		if(chk =="nonelog"||chk=="null"){
			$('.addrecipe').click(
				function() {
				$('.alert').html('您尚未登入').addClass('alert-success').show().delay(1500).fadeOut();
		        setTimeout(function() { $(".alert").hide(); }, 1500);
				return false; 
			});
		}

		var comlogin="<%=CompanyId%>"
		if(comlogin !="none"){
			$('.head').click(function(){
				location.href='ManageCompany.jsp';
				return false;
			});

		}
    </script>
</body>
</html>