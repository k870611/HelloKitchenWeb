<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<title>廠商註冊 &mdash; HelloKitchen</title>

	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />

	<!--     Fonts and icons     -->

	<!-- CSS Files -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
	<link href="assets/css/gsdk-bootstrap-wizard.css" rel="stylesheet" />

	<!-- CSS Just for demo purpose, don't include it in your project -->
	<link href="assets/css/demo.css" rel="stylesheet" />
	<script type="text/javascript">
	function checkpwd(){
		var p1=document.form1.password.value;//获取密码框的值
		var p2=document.form1.chkpwd.value;//获取重新输入的密码值
		
		if(p1!=p2){//判断两次输入的值是否一致，不一致则显示错误信息
		 document.getElementById("msg").innerHTML="密碼不一致，請重新輸入";//在div显示错误信息
		 $('#msg').show().delay(1500).fadeOut();
		 document.form1.password.value="";
		 document.form1.chkpwd.value="";
		 return false;
		}else{
		 //密码一致，可以继续下一步操作 
		}
	}
</script>
	
</head>

<body>
<div class="image-container set-full-height" style="background-image: url('assets/img/wizard.jpg')">
    <!--   Creative Tim Branding   -->
	<a href="index.jsp">
         <div class="logo-container">
            <div class="logo">
                <img src="images/logo.png" style='width:100%; height:100%;'>
            </div>
            <div class="brand">
                Go Home
            </div>
        </div>
    </a>

	<!--  Made With Get Shit Done Kit  -->
		

    <!--   Big container   -->
    <div class="container">
        <div class="row">
        <div class="col-sm-8 col-sm-offset-2">

            <!--      Wizard container        -->
            <div class="wizard-container">

                <div class="card wizard-card" data-color="orange" id="wizardProfile">
                    <form name="form1" action="./AddcompanyServlet" method="post" enctype="multipart/form-data">
                <!--        You can switch ' data-color="orange" '  with one of the next bright colors: "blue", "green", "orange", "red"          -->

                    	<div class="wizard-header">
                        	<h3>
                        	   <b style='font-family: 微軟正黑體'>創建您的專屬頁面</b>  <br>
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
                                  <h4 class="info-text"> 請輸入您的基本資料</h4>
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
                                        <label>帳號 <small>(必填)</small></label>
                                        <input name="email" type="email" class="form-control" placeholder="請使用email，如:andrew@creative-tim.com" required>
                                      </div>
                                      <div class="form-group">
                                        <label>密碼 <small>(必填)</small></label>
                                        <input name="password" type="password" class="form-control" placeholder="請輸入密碼" required>
                                      </div>
                                      <div class="form-group">
                                        <label>確認密碼 <small>(必填)</small></label>
                                        <input name="chkpwd" onchange="checkpwd()" type="password" class="form-control" placeholder="請再次輸入密碼" required>
                                      <div id="msg" style="color:red;"></div>
                                      </div>
                                  </div>
                                  <div class="col-sm-10 col-sm-offset-1">
	            					  <div class="form-group">
		                                  <label>公司介紹</label>
		                                  <small>(必填)</small>
		                                  <textarea name="description" class="form-control" placeholder="" rows="9" required></textarea>
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
                                            <small>(必填)</small>
                                            <input type="text" name="CompanyName" class="form-control" placeholder="HlloKitcen設計公司" required><!--到時要送到資料庫時要在加Name-->
                                          </div>
                                    </div>
                                    <div class="col-sm-3">
                                         <div class="form-group">
                                            <label>負責人</label>
                                            <small>(必填)</small>
                                            <input type="text" name="Owner" class="form-control" placeholder="王小明" required><!--到時要送到資料庫時要在加Name-->
                                          </div>
                                    </div>
                                    <div class="col-sm-10 col-sm-offset-1">
                                         <div class="form-group">
                                            <label>地址</label>
                                            <small>(必填)</small>
                                            <input type="text" name="Address" class="form-control" placeholder="高雄市..." required><!--到時要送到資料庫時要在加Name-->
                                          </div>
                                    </div>
                                    <div class="col-sm-10 col-sm-offset-1">
                                         <div class="form-group">
                                            <label>連絡電話</label>
                                            <small>(必填)</small>
                                            <input type="text" name="Tel" class="form-control" required><!--到時要送到資料庫時要在加Name-->
                                          </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="wizard-footer height-wizard">
                            <div class="pull-right">
                                <input id="next" type='button' class='btn btn-next btn-fill btn-warning btn-wd btn-sm' name='next' value='下一頁' />
                                <input type='submit' class='btn btn-finish btn-fill btn-warning btn-wd btn-sm' name='finish' value='送出' />

                            </div>

                            <div class="pull-left">
                                <input type='button' class='btn btn-previous btn-fill btn-default btn-wd btn-sm' name='previous' value='上一頁' />
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

</body>

	<!--   Core JS Files   -->
	<script src="assets/js/jquery-2.2.4.min.js" type="text/javascript"></script>
	<script src="assets/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="assets/js/jquery.bootstrap.wizard.js" type="text/javascript"></script>

	<!--  Plugin for the Wizard -->
	<script src="assets/js/gsdk-bootstrap-wizard.js"></script>

	<!--  More information about jquery.validate here: http://jqueryvalidation.org/	 -->
	<script src="assets/js/jquery.validate.min.js"></script>

	<script type="text/javascript">
	$('input').keypress(function(e) {
	    code = e.keyCode ? e.keyCode : e.which; // in case of browser compatibility
	    if(code == 13) {
	        e.preventDefault();
	        $('#next').click();
	        // do something
	        /* also can use return false; instead. */
	        }
	    });
	</script>
</html>