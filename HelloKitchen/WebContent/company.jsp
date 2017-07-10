<%@page import="Utility.GeneralVarName"%>
<%@page import="Modules.SessionStamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>HelloKitchen</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->

    <link rel="stylesheet" href="css/magnific-popup.css">                                     <!-- Magnific pop up style -->
    <link rel="stylesheet" href="css/templatemo-style.css">  
	<script type="text/javascript" src="js/jquery-2.1.1.js"></script>
    
    <style type="text/css">
	.sidebar{
	    width:200px;
	    height:170px;
	    margin-left:20px;
	    text-align:center;
	    padding-right:0px;
	}
	.sidebarborder{
	    padding-top:25px;
	    width:300px;
	}
	.border{
	width:90%;
	height:70%;
	}
	
	.htmleaf-content {	
	  margin-left:16%;
	  padding: .5vw;
	  font-size: 0;
	  display: -ms-flexbox;
	  -ms-flex-wrap: wrap;
	  -ms-flex-direction: column;
	  -webkit-flex-flow: row wrap; 
	  flex-flow: row wrap; 
	  display: -webkit-box;
	  display: flex;
	}
	.htmleaf-content div { 
	  -webkit-box-flex: auto;
	  -ms-flex: auto;
	  width: 200px; 
	  margin: .5vw; 
	}
	.htmleaf-content div img { 
	  width: 100%; 
	  height: auto; 
	}
	@media screen and (max-width: 400px) {
	  .htmleaf-content div { margin: 0; }
	  .htmleaf-content { padding: 0; }
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

<script type="text/javascript">

$(document).ready(function($) {
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
		dataType : "text",
		type : "post",
		url : "./DetailCompanyLoadServlet",
		success : function(data) {
			if (data == "null") {
				document.location.href="index.jsp";
			}
			else {
				$(".tm-main-content").html(data);
			}
		}
	});
});
</script>



</head>

<body>
	<%           
		String message = (String) request.getAttribute ("Message");   
	
		if((SessionStamp)request.getSession().getAttribute(GeneralVarName.Session_LoginAccount)!=null){
	       	SessionStamp st = (SessionStamp)request.getSession().getAttribute(GeneralVarName.Session_LoginAccount);
	
	       	message = st.getLoginAccount();
       }
	%>


<div class='alert'></div>

        <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">HelloKitchen</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="index.jsp">回首頁</a>
                    </li>
                    <li>
                        <a class='addrecipe' href="addrecipe.jsp">新增食譜</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Content -->
    <div class="container">

        <div class="row">

            <div class="tm-main-content">

                <div class="col-md-3 sidebarborder">
                    <div class="list-group">
                        <a href="#" class="list-group-item"><img src="img/menu-top.jpg" class="sidebar" /></a>
                        <a href="#" class="list-group-item">Category 1(項目錨點)</a>
                        <a href="#" class="list-group-item">Category 2</a>
                        <a href="#" class="list-group-item">Category 3</a>
                    </div>
                </div>

                <section id="home" class="tm-content-box tm-banner margin-b-10">
                    <div class="tm-banner-inner border">
                        <h1 class="tm-banner-title">Elevate</h1>
                        <p class="tm-banner-text">free bootstrap 4.0 theme(廠商照片或介紹) <br>by templatemo</p>
                    </div>
                </section>

                <section>
                    <div class="tm-content-box flex-2-col">

                        <div class="pad flex-item tm-team-description-container">
                            <h2 class="tm-section-title">Our Team</h2>
                            <p class="tm-section-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel nisi pharetra nibh varius pharetra ac sagittis nisi. Etiam pharetra vestibulum hendrerit.</p>
                            <p class="tm-section-description">Donec ex libero, fringilla vitae purus sit amet, rhoncus pharetra lorem. Pellentesque id sem id lacus ultricies vehicula. Aliquam rutrum mi non.</p>
                        </div>
                        <div class="flex-item">
                            <img src="img/about.jpg" alt="">
                        </div>


                    </div>
                    <div id="about" class="tm-content-box">

                        <ul class="boxes gallery-container">
                            <li class="box box-bg">
                                <h2 class="tm-section-title tm-section-title-box tm-box-bg-title">Creative</h2>
                                <img src="img/white-bg.jpg" alt="Image" class="img-fluid">
                            </li>
                            <li class="box">
                                <a href="img/idea-large-01.jpg"><img src="img/idea-01.jpg" alt="Image" class="img-fluid"></a>
                            </li>
                            <li class="box box-bg">
                                <h2 class="tm-section-title tm-section-title-box tm-box-bg-title">Develop</h2>
                                <img src="img/white-bg.jpg" alt="Image" class="img-fluid">
                            </li>
                            <li class="box">
                                <a href="img/idea-large-02.jpg"><img src="img/idea-02.jpg" alt="Image" class="img-fluid"></a>
                            </li>
                            <li class="box box-bg">
                                <h2 class="tm-section-title tm-section-title-box tm-box-bg-title">Design</h2>
                                <img src="img/white-bg.jpg" alt="Image" class="img-fluid">
                            </li>
                            <li class="box">
                                <a href="img/idea-large-03.jpg"><img src="img/idea-03.jpg" alt="Image" class="img-fluid"></a>
                            </li>
                            <li class="box box-bg">
                                <h2 class="tm-section-title tm-section-title-box tm-box-bg-title">Support</h2>
                                <img src="img/white-bg.jpg" alt="Image" class="img-fluid">
                            </li>
                            <li class="box">
                                <a href="img/idea-large-04.jpg"><img src="img/idea-04.jpg" alt="Image" class="img-fluid"></a>
                            </li>
                            <li class="box box-bg">
                                <h2 class="tm-section-title tm-section-title-box tm-box-bg-title">Think</h2>
                                <img src="img/white-bg.jpg" alt="Image" class="img-fluid">
                            </li>
                        </ul>

                    </div>

                </section>

                <!-- slider -->
                <section id="ideas">
                    <div id="tmCarousel" class="carousel slide tm-content-box" data-ride="carousel">

                        <div class="carousel-inner" role="listbox">

                            <div class="carousel-item active">
                                <div class="carousel-content">
                                    <div class="flex-item">
                                        <h2 class="tm-section-title">Our Email</h2>
                                        <p class="tm-section-description carousel-description">liumingtang1@gmail.com</p>
                                    </div>
                                </div>
                            </div>

                            <div class="carousel-item">
                                <div class="carousel-content">
                                    <div class="flex-item">
                                        <h2 class="tm-section-title">Our Address</h2>
                                        <p class="tm-section-description carousel-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel nisi pharetra nibh varius pharetra ac sagittis nisi. Etiam pharetra vestibulum hendrerit. Pellentesque interdum metus sed massa rutrum.</p>
                                    </div>
                                </div>
                            </div>

                            
                        </div>

                    </div>
                </section>

                <section class="tm-content-box">
                    <img src="img/contact.jpg" alt="Contact image" class="img-fluid">

                    <div id="contact" class="pad">
                        <h2 class="tm-section-title">Contact Us</h2>
                        <form action="#contact" method="get" class="contact-form">

                            <div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6 form-group-2-col-left">
                                <input type="text" id="contact_name" name="contact_name" class="form-control" placeholder="Name" required />
                            </div>
                            <div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6 form-group-2-col-right">
                                <input type="email" id="contact_email" name="contact_email" class="form-control" placeholder="Email" required />
                            </div>
                            <div class="form-group">
                                <input type="text" id="contact_subject" name="contact_subject" class="form-control" placeholder="Subject" required />
                            </div>
                            <div class="form-group">
                                <textarea id="contact_message" name="contact_message" class="form-control" rows="9" placeholder="Message" required></textarea>
                            </div>

                            <button type="submit" class="btn btn-primary submit-btn addrecipe">Submit</button>

                        </form>
                    </div>

                </section>

                <footer class="tm-footer">
                    <p class="text-xs-center">
                        Copyright &copy; 2017 HelloKitchen
                    </p>
                </footer>

            </div>

        </div>

    </div>
    <!-- /.container -->


    <!-- /.container -->

    <!-- jQuery -->
    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>




    <!-- load JS files -->

    <script src="js/jquery-2.1.1.js"></script>             <!-- jQuery (https://jquery.com/download/) -->
    <script src="js/jquery.magnific-popup.min.js"></script>     <!-- Magnific pop-up (http://dimsemenov.com/plugins/magnific-popup/) -->
    <script src="js/jquery.singlePageNav.min.js"></script>      <!-- Single Page Nav (https://github.com/ChrisWojcik/single-page-nav) -->
    <script src="js/jquery.touchSwipe.min.js"></script>         <!-- https://github.com/mattbryson/TouchSwipe-Jquery-Plugin -->
    <!-- Templatemo scripts -->
    <script>


        $(document).ready(function(){
        	var chk="<%=message%>";
        	<%System.out.println("Id is: "+message);%>
        	
        	if(chk =="nonelog"||chk=="null"){
        		$('body').on('click','.addrecipe',function(){
        			$('.alert').html('您尚未登入').addClass('alert-success').show().delay(1500).fadeOut();
			        setTimeout(function() { $(".alert").hide(); }, 1500);
					return false;
                });

    		}
        	
        	
        	

            // Single page nav
            if($(window).width() <= 1139) {
                $('.tm-main-nav').singlePageNav({
                    'currentClass' : "active",
                    offset : 100
                });
            } else {
                $('.tm-main-nav').singlePageNav({
                    'currentClass' : "active",
                    offset : 80
                });
            }

            // Handle nav offset upon window resize
            $(window).resize(function(){
                if($(window).width() <= 1139) {
                    $('.tm-main-nav').singlePageNav({
                        'currentClass' : "active",
                        offset : 100
                    });
                } else {
                    $('.tm-main-nav').singlePageNav({
                        'currentClass' : "active",
                        offset : 80
                    });
                }
            });

            // Magnific pop up
            $('.gallery-container').magnificPopup({
              delegate: 'a', // child items selector, by clicking on it popup will open
              type: 'image',
              gallery: {enabled:true}
              // other options
            });

            $('.carousel').carousel({
              interval: 3000
            })

            // Enable carousel swiping (http://lazcreative.com/blog/adding-swipe-support-to-bootstrap-carousel-3-0/)
            $(".carousel-inner").swipe( {
                //Generic swipe handler for all directions
                swipeLeft:function(event, direction, distance, duration, fingerCount) {
                    $(this).parent().carousel('next');
                },
                swipeRight: function() {
                    $(this).parent().carousel('prev');
                }
            });

        });

    </script>             
</body>

</html>