<%@page import="EntityComponents.Recipe"%>
<%@page import="Factorys.RecipeFactory"%>
<%@page import="EntityComponents.Company"%>
<%@page import="Factorys.CompanyFactory"%>
<%@page import="Utility.GeneralVarName"%>
<%@page import="Modules.SessionStamp"%>
<%@page import="java.util.ArrayList"%>
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
		    <div id="fh5co-offcanvass">
		        <a href="#" class="fh5co-offcanvass-close js-fh5co-offcanvass-close">Menu <i class="icon-cross"></i> </a>
		        <h1 class="fh5co-logo"><a class="navbar-brand" href="index.jsp">HelloKitchen</a></h1>
		        <ul>
		            <li><a href="login.jsp"><img class="head" src="images/head.jpg"/></a></li>
		            <%           
		            if((SessionStamp)request.getSession().getAttribute(GeneralVarName.Session_LoginAccount)!=null){
		            	SessionStamp st = (SessionStamp)request.getSession().getAttribute(GeneralVarName.Session_LoginAccount);
			            System.out.println(st.getLoginAccount()+"....before");
  
		            	if(!st.getLoginAccount().equals("nonelog") )
			              {
			      			out.println("<li style='text-align:center;'>Hi, "+st.getLoginAccount()+" 您好</li>");
			              }
			            System.out.println(st.getLoginAccount());
			            message = st.getLoginAccount();
		            }
		        	%>
		            <li><a href="ManageCompany.jsp">廠商登入</a></li> 
		        	<li><a href="addcompany.jsp">廠商註冊</a></li>
		            <li><a class="addrecipe" href="addrecipe.jsp">新增食譜</a></li>
		            <li><a class="addrecipe" href="ManageRecipe.jsp">管理食譜</a></li>       
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
		<div class='sangar-slideshow-container' id='sangar-example' style="display:none;">
			<div class='sangar-content-wrapper'>			
				<div class='sangar-content'><img src='images/search.png' /></div>			
			</div>
		</div>	          
<!-------------------------------------------------------------------------------------------------------------->
                            
        <!--設定篩選範圍(包在Container底下之物件)---------------------------------------------------------->                     
        <div id="Container">
            <!------------------------------------------------------------------------------------------------->
            <div id="fh5co-board" data-columns>
                <!--篩選物件(Class需帶 "mix" 條件為category-1 )---------------------------------------------------------------->         
            <%
               	ArrayList<Recipe> dataset =  (ArrayList<Recipe>)request.getAttribute("search");
               	
            	if(dataset.size()<=0){
            		%>
            		

                <%
            	}else{
				
            	for( Recipe temp: dataset){
            		%>
        			<div class="item mix category-1" >
                    <div class="recipe">
                        <a href="recipe.jsp?page=<%=temp.getRecipe_id() %>" class="fh5co-board-img" title="<%=temp.getRecipe_detail() %>"><img src="<%=temp.getRecipe_picture()%>" alt="Free HTML5 Bootstrap template"></a>
                        <div class="fh5co-desc"><%=temp.getRecipe_name()%></div>
                    </div>
                </div>
            	<%	
            	}}
           %> 
               
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
                    <p><small>&copy; Hydrogen Free HTML5 Template. All Rights Reserved. <br>Designed by: <a href="http://freehtml5.co/" target="_blank">FREEHTML5.co</a> | Images by: <a href="http://pexels.com" target="_blank">Pexels</a> </small></p>
                </div>
            </div>
        </div>

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
				//animation : 'fade', // horizontal-slide, vertical-slide, fade
				//animationSpeed : 1000, // how fast animtions are
				//advanceSpeed : 3000, 
				//timer : true, // true or false to have the timer
				width : 500, // slideshow width
    			height : 300 // slideshow height				
			});
		})				
	</script>
	
	
	
    <script  type='text/javascript'>

        $(function () {
            $('#Container').mixItUp();		
        });
        
        $(document).ready(function () {
        	if($('#fh5co-board').text()!=""){
        		
        		$('.sangar-slideshow-container').animate({ height: "0px", width: "900px" }, 0, 'easeOutBounce');
            	setTimeout(function() {$('.sangar-slideshow-container').hide();}, 0);
        	}
    		

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

    </script>
</body>
</html>