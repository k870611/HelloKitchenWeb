<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>發布食譜 &mdash; HelloKitchen</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />

    <!--     Fonts and icons     -->
    <!-- CSS Files -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
    <link href="assets/css/gsdk-bootstrap-wizard.css" rel="stylesheet" />

    <!-- CSS Just for demo purpose, don't include it in your project -->
    <link href="assets/css/demo.css" rel="stylesheet" />
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
                            <form action="./AddrecipeServlet" method="post" enctype="multipart/form-data">
                            
                                <!--        You can switch ' data-color="orange" '  with one of the next bright colors: "blue", "green", "orange", "red"          -->

                                <div class="wizard-header">
                                    <h3>
                                        <b style='font-family: 微軟正黑體'>分享您的私房密笈</b>
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
                                            <h4 class="info-text"> 創建我的食譜</h4>
                                            <div class="col-sm-4 col-sm-offset-1">

                                                <div class="picture-container">
                                                    <div class="picture">
                                                        <img src="assets/img/default-avatar.png" class="picture-src" id="wizardPicturePreview1" title="" />
                                                        <input type="file" name="Pic" id="wizard-picture1">
                                                    </div>
                                                    <h6>食譜代表圖片</h6>
                                                    <div style="padding:5%;">
                                                    	<label class="radio-inline"><input type="radio" name="status" value="open">公布食譜</label>
														<label class="radio-inline"><input type="radio" name="status" value="close" CHECKED>私藏密笈</label>
                                                	</div>
                                                </div>
                                            </div>
                                            <div class="col-sm-6">
                                                <div class="form-group">
                                                    <label>標題 <small>(必填)</small></label>
                                                    <input name="Name" type="text" class="form-control" placeholder="黃金開口笑" required>
                                                </div>
                                                <div class="form-group">
                                                    <label>幾人份</label>
                                                    <input name="amount" type="text" class="form-control" value="1人份">
                                                </div>
                                                <div class="form-group">
                                                    <label>大約料理時間</label>
                                                    <input name="cooktime" type="text" class="form-control" value="5分鐘">
                                                </div>
                                            </div>
                                            <div class="col-sm-10 col-sm-offset-1">
                                                <div class="form-group">
                                                    <label>食譜簡介 <small>(必填)</small></label>
                                                    <textarea name="Detail" class="form-control" placeholder="寫下關於這道菜的創作源起、秘訣提示" rows="9" required></textarea>
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
                                                    <input type="text" name="Material0" class="form-control" placeholder="麵粉" required><!--到時要送到資料庫時要在加Name-->
                                                </div>
                                            </div>
											<div class="col-sm-3">
                                                <div class="form-group">
                                                	<label>數量</label>
                                                    <input type="text" name="Amount0" class="form-control" placeholder="100克" required><!--到時要送到資料庫時要在加Name-->
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

                                                    <input type="text" name="Method0" class="form-control" placeholder="步驟說明" required>
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

    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    
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
    
    <script>
        //set the default value
        var txtId = 0;
        var process=0;

        //add input block in showBlock
        $(".addItem").click(function () {
            txtId++;
            $("#showBlock").append('<div class="col-sm-7 col-sm-offset-1" id="div' + txtId + '"><div class="form-group"><input name="Material' + txtId + '" type="text" class="form-control" placeholder="麵粉" required></div></div>');//到時要送到資料庫時要在加Name
            $("#showBlock").append('<div class="col-sm-3" id="divAmount' + txtId + '"><div class="form-group"><input name="Amount' + txtId + '" type="text" class="form-control" placeholder="100克" required></div></div>');//到時要送到資料庫時要在加Name
        });

        $(".addProcess").click(function () {
            process++;
            $("#addProcess").append('<div class="col-sm-7 col-sm-offset-1" id="process' + process + '"><div class="form-group"><input name="Method' + process + '" type="text" class="form-control" placeholder="步驟說明" required></div></div>');//到時要送到資料庫時要在加Name
            });


        //remove div
        function deltxt(id) {
            $("#div"+txtId).remove();
            $("#divAmount"+txtId).remove();
            txtId--;
        }
        function delprocess(id) {
            $("#process"+process).remove();
            process--;
        }
    </script>


</body>

<!--   Core JS Files   -->
<script src="assets/js/jquery-2.2.4.min.js" type="text/javascript"></script>
<script src="assets/js/bootstrap.min.js" type="text/javascript"></script>
<script src="assets/js/jquery.bootstrap.wizard.js" type="text/javascript"></script>

<!--  Plugin for the Wizard -->
<script src="assets/js/gsdk-bootstrap-wizard.js"></script>

<!--  More information about jquery.validate here: http://jqueryvalidation.org/	 -->
<script src="assets/js/jquery.validate.min.js"></script>

</html>