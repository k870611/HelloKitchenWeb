<%@page import="Utility.GeneralVarName"%>
<%@page import="Modules.SessionStamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href='http://fonts.googleapis.com/css?family=Oswald:400,700,300|Bitter:400,700,400italic' rel='stylesheet' type='text/css'>
<script type="text/javascript" src="js/jquery-2.1.1.js"></script>
<!-- Mobile Specific Metas -->
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<!-- CSS -->

<link href="css/recipe.css" rel="stylesheet" type="text/css">
<link href="css/bootstrap.min.css" rel="stylesheet">


<title>HelloKitchen</title>
<style type="text/css">
	.picborder{
		width:50%;
		height:25%;
		margin-left:25%;
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
		url : "./DetailProductLoadServlet",
		success : function(data) {
			if (data == "null") {
				document.location.href="index.jsp";
			}
			else {
				$(".rewritedata").html(data);
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

 <!-- START CONTENT -->
<div id="box">
	<div class="container">
<!-- START HEADER -->
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
                        <a href="index.jsp">Home</a>
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
<!-- END HEADER -->
<!-- -------------------------------------------------------------------- -->
<div class="rewritedata">
<!-- START HINTS -->
<!-- <div class="hint columns one"> -->
<!-- 	(食譜標題)Your recipe is at a click away. Choose your favorite recipe and cooking with -->
<!-- iCOOK. Enjoy flavors with iCOOK. -->
<!-- </div> -->
<!-- <!-- END HINTS --> -->

<!-- <div class="columns one featuredimage"> -->
<!-- 	<div class="image"><img src="images/1.jpg" alt="food_single" /></div> -->
<!-- </div> -->


<!-- <!-- START LEFT CONTENT --> -->
<!-- <div class="columns two-thirds"> -->

<!--   <div class="single_post"> -->
<!--   <div class="postinfo"> -->
<!--   <h2>barbecue with vegetables</h2> -->
<!--   <p>November  26,  2013  -  <span>by</span>  <a href="#">John Doe</a> -->
<!--   </div> -->
  
<!--   <div class="postdescription"> -->
<!--   <p>Proin blandit aliquam dapibus. Aliquam erat volutpat. Vivamus gravida justo sed nisl viverra in venenatis lacus posuere. Suspendisse iaculis turpis gravida neque ullamcorper malesuada. Aenean ac velit in dolor blandit bibendum. <a href="#">Donec a lacus</a> ac libero pulvinar condimentum sed id nulla. Aliquam erat volutpat. Vivamus gravida justo sed nisl viverra in venenatis lacus posuere. Suspendisse iaculis turpis gravida neque ullamcorper malesuada. Aenean ac velit in dolor blandit bibendum. Donec a lacus ac libero pulvinar condimentum sed id nulla.</p> -->
   
<!--   <div class="recipe"> -->
<!--         <h2>(材料)Stone Bowl Recipe</h2> -->
      
      
<!--       <div class="ingredients"> -->
<!--    	    1/2 cup white sugar<br/> -->
<!--         1/4 cup margarine, melted<br/> -->
<!--         3/4 teaspoon ground nutmeg<br/> -->
<!--         1/2 cup milk<br/> -->
<!--         1 teaspoon baking powder<br/> -->
<!--         1 cup all-purpose flour -->
<!--       </div> -->
          
<!--   </div> -->

<!--       <div class="comments"> -->
<!--       	<h3>步驟</h3> -->
        
<!--       	<div class="comment"> -->
<!--         <div class="commenttext">(步驟1)Aliquam erat volutpat. Vivamus gravida justo sed nisl viverra in venenatis lacus posuere. Suspendisse iaculis turpis gravida neque ullamcorper malesuada. </div> -->
<!--         </div> -->
      
      
<!--       	<div class="comment"> -->
<!--         <div class="commenttext">(步驟2)Aliquam erat volutpat. Vivamus gravida justo sed nisl viverra in venenatis lacus posuere. </div> -->
<!--         </div> -->
        
<!--         <div class="comment"> -->
<!--         <div class="commenttext">(步驟3)Aliquam erat volutpat. Vivamus gravida justo sed nisl viverra in venenatis lacus posuere. Suspendisse iaculis turpis gravida neque ullamcorper malesuada.</div> -->
<!--         </div>      -->
<!--       </div>   -->
<!--   </div> -->
  
<!--     	<div class="clear"></div>   -->
<!--     </div> -->
    
</div>
<!-- END LEFT CONTENT -->
</div>
<!--Main End-------------------------------------------------------------------- -->

<!-- START PAGINATION -->
<!-- <div class="pagination columns one"> -->
<!-- 	<a href="#">&laquo; Porchetta recipe</a> <span><a href="#">Earl grey medeleines recipe &raquo;</a></span> -->
<!-- </div> -->
<!-- END PAGINATION -->
<div class="clear"></div>

<!-- START FOOTER -->
<div id="footer">
<a href="index.jsp?show=show" style='font-size:200%;font-family:微軟正黑體; '>回食譜區</a>
<p>&copy; Copyright &copy; 2017.HelloKitchen All rights reserved.</p>
</div>
<!-- END FOOTER -->

	</div>
</div>
<!-- END CONTENT -->

<script>
        $(document).ready(function(){
        	var chk="<%=message%>";
        	
        	if(chk =="nonelog"||chk=="null"){
        		$('body').on('click','.addrecipe',function(){
        			$('.alert').html('您尚未登入').addClass('alert-success').show().delay(1500).fadeOut();
			        setTimeout(function() { $(".alert").hide(); }, 1500);
					return false;
                });

    		};
        	
        	
        });
</script>
    
</body>

</html>