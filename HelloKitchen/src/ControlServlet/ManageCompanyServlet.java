package ControlServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import EntityComponents.Company;
import EntityComponents.CompanyPictures;
import Factorys.CompanyFactory;
import Factorys.CompanyPictureFactory;
import Modules.SessionStamp;
import Utility.GeneralVarName;

/**
 * Servlet implementation class ManageCompanyServlet
 */
@WebServlet("/ManageCompanyServlet")
public class ManageCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CompanyFactory cf;
	private CompanyPictureFactory cpf;
	private PrintWriter out;
	private String mpd;



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processCompany(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processCompany(request, response);
	}

	private void processCompany(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();	
		
		cf = (CompanyFactory) request.getServletContext().getAttribute(GeneralVarName.Web_CompanyFactory);		
		cpf = (CompanyPictureFactory) request.getServletContext().getAttribute(GeneralVarName.Web_CompanyPictureFactory);
		cf.refreshByDataBase();
		cpf.refreshByDataBase();
		mpd = request.getParameter("pId");
		
		//out.write("into success:"+mpd+"<br/>");
	
		if(!mpd.equalsIgnoreCase("null")){
			//out.write("into success:"+mpd+"is not null"+"<br/>");
			UpdateCompany();
		}else{			
			HttpSession mySession = request.getSession();
			SessionStamp st = (SessionStamp)request.getSession().getAttribute(GeneralVarName.Session_CompanyLogin);
			String CompanyId=st.getLoginAccount();
			
			Company ALcf=(Company)cf.sreachByPK(CompanyId);
			
			out.write("<div class='image-container set-full-height' style='position:absolute;top:4%;z-index:-1;'>"
					+ "<div class='container'><div class='row'>"
					+ "<div class='col-sm-8 col-sm-offset-2'>"
					+ "<div class='wizard-container'>"
					+ "<div class='card wizard-card' data-color='orange' id='wizardProfile'>");
			out.write("<img src='"+ALcf.getCompany_logo()+"' style='width:226px;height:224px;position:absolute;left:35%;top:33%'/> ");
			out.write("</div></div></div></div></div></div>");
		}
		
	}

	private void UpdateCompany() {	
		Company ALcf=(Company)cf.sreachByPK(mpd);
		ArrayList<CompanyPictures>ALcp=(ArrayList<CompanyPictures>)cpf.sreachByPK(mpd);
		
		String CName="";
		String CPwd="";
		String CLogo="";
		String CCover="";
		String CIntro="";
		String CAddress="";
		String CTel="";
		String CEmail="";
		String COwner="";
		
		CName=ALcf.getCompany_name();
		CPwd=ALcf.getCompany_password();
		CLogo=ALcf.getCompany_logo();
		CCover=ALcf.getCompany_cover();
		CIntro=ALcf.getCompany_intro();
		CAddress=ALcf.getCompany_address();
		CTel=ALcf.getCompany_tel();
		CEmail=ALcf.getCompany_email();
		COwner=ALcf.getCompany_owner();
		
		out.write("<div class='image-container set-full-height' style='position:absolute;top:4%;z-index:-1;'>"
				+ "<div class='container'><div class='row'>"
				+ "<div class='col-sm-8 col-sm-offset-2'>"
				+ "<div class='wizard-container'>"
				+ "<div class='card wizard-card' data-color='green' id='wizardProfile'>");
		
		out.write("<form name='form1' action='./UpdateCompanyServlet' method='post' enctype='multipart/form-data'>"
				+ "<div class='wizard-header'><h3><b style='font-family: 微軟正黑體'>更新您的專屬頁面</b>  <br></h3></div>"
				+ "<div class='wizard-navigation'>"
				+ "<ul><li><a href='#about' data-toggle='tab'>帳戶</a></li>"
				+ "<li><a href='#account' data-toggle='tab'>作品集</a></li>"
				+ "<li><a href='#address' data-toggle='tab'>詳細資料</a></li></ul></div>");
		
		out.write("<div class='tab-content'>"
				+ "<div class='tab-pane' id='about'>"
				+ "<div class='row'><h4 class='info-text'> 請輸入您的基本資料</h4>"
				+ "<div class='col-sm-4 col-sm-offset-1'>"
				+ "<div class='picture-container'>"
				+ "<div class='picture'><img src='"+CLogo+"' class='picture-src' id='wizardPicturePreview1' title=''/>"
				+ "<input type='file' name='logo' id='wizard-picture1'></div><h6>Logo</h6></div></div>");
		
		out.write("<div class='col-sm-6'>"
				+ "<div class='form-group'><label>帳號 <small>(必填)</small></label>"
				+ "<input name='email' type='email' value='"+CEmail+"' class='form-control' placeholder='請使用email，如:andrew@creative-tim.com' required readonly></div>"
				+ "<div class='form-group'><label>密碼  <small>(必填)</small></label>"
				+ "<input name='password' type='password' value='"+CPwd+"' class='form-control' placeholder='請輸入密碼' required></div>"
				+ "<div class='form-group'><label>確認密碼 <small>(必填)</small></label>"
				+ "<input name='chkpwd' onchange='checkpwd()' type='password' value='"+CPwd+"' class='form-control' placeholder='請再次輸入密碼' required>"
				+ "<div id='msg' style='color:red;'></div></div></div>");
		
		out.write("<div class='col-sm-10 col-sm-offset-1'>"
				+ "<div class='form-group'><label>公司介紹</label> <small>(必填)</small>"
				+ "<textarea name='Detail' class='form-control' placeholder='' rows='9' required>"+CIntro+"</textarea>"
				+ "</div></div></div></div>");
		
		out.write("<div class='tab-pane' id='account'>"
				+ "<h4 class='info-text'> 分享你的作品 </h4><div class='row'>"
				+ "<div class='col-sm-10 col-sm-offset-1'>"
				+ "<div class='col-sm-4 col-sm-offset-1'>"
				+ "<div class='picture-container'><div class='picture'>"
				+ "<img src='"+CCover+"' class='picture-src' id='wizardPicturePreview7' title='' />"
				+ "<input type='file' name='cover' id='wizard-picture7'></div>"
				+ "<h6>網站封面</h6></div></div>");
		
		int i =1;
		if(ALcp.size()>0 && ALcp!=null){
		for(CompanyPictures rm:ALcp){
			String cp=rm.getPicture_path();
			
			
			out.write("<div class='col-sm-4 col-sm-offset-1'>"
					+ "<div class='picture-container'><div class='picture'>"
					+ "<img src='"+cp+"' class='picture-src' id='wizardPicturePreview"+(i+1)+"' title='' />"
					+ "<input type='file' name='pic"+i+"' id='wizard-picture"+(i+1)+"'></div><h6>作品"+i+"</h6></div></div>");
			i++;
		}
		
		int j=i;
		for(int a=j;a<=5;a++){
			out.write("<div class='col-sm-4 col-sm-offset-1'>"
					+ "<div class='picture-container'><div class='picture'>"
					+ "<img src='assets/img/default-avatar.png' class='picture-src' id='wizardPicturePreview"+(a+1)+"' title='' />"
					+ "<input type='file' name='pic"+a+"' id='wizard-picture"+(a+1)+"'></div><h6>作品"+a+"</h6></div></div>");
		}
		}	
		out.write("</div></div></div><div class='tab-pane' id='address'><div class='row'>"
				+ "<div class='col-sm-12'><h4 class='info-text'> 讓我們更認識你 </h4></div>"
				+ "<div class='col-sm-7 col-sm-offset-1'>"
				+ "<div class='form-group'><label>公司名稱</label> <small>(必填)</small>"
				+ "<input type='text' name='CompanyName' value='"+CName+"' class='form-control' placeholder='HelloKitty設計公司' required></div></div>"
				+ "<div class='col-sm-3'><div class='form-group'><label>負責人</label> <small>(必填)</small>"
				+ "<input type='text' name='Owner' value='"+COwner+"' class='form-control' placeholder='王小明' required></div></div>"
				+ "<div class='col-sm-10 col-sm-offset-1'><div class='form-group'><label>地址</label> <small>(必填)</small>"
				+ "<input type='text' name='Address' value='"+CAddress+"' class='form-control' placeholder='台北市...'required></div></div>"
				+ "<div class='col-sm-10 col-sm-offset-1'><div class='form-group'><label>連絡電話</label><small>(必填)</small>"
				+ "<input type='text' name='Tel' value='"+CTel+"' class='form-control' required></div></div></div></div></div>");
		
		
		out.write("<div class='wizard-footer height-wizard'><div class='pull-right'>"
				+ "<input id='next' type='button' class='btn btn-next btn-fill btn-warning btn-wd btn-sm' name='next' value='下一頁' />"
				+ "<input type='submit' class='btn btn-finish btn-fill btn-warning btn-wd btn-sm' name='finish' value='送出' /></div>"
				+ "<div class='pull-left'>"
				+ "<input type='button' class='btn btn-previous btn-fill btn-default btn-wd btn-sm' name='previous' value='上一頁' /></div>"
				+ "<div class='clearfix'></div></div></form></div></div></div></div></div>");
		
        out.write("<div class='footer'><div class='container'></div></div></div>");
		
	}

}
