package ControlServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EntityComponents.Company;
import EntityComponents.CompanyPictures;
import EntityComponents.Recipe;
import EntityComponents.RecipeMethod;
import Factorys.CompanyFactory;
import Factorys.CompanyPictureFactory;
import Factorys.RecipeFactory;
import Factorys.RecipeMethodFactory;
import Utility.GeneralVarName;

/**
 * Servlet implementation class DetailCompanyLoadServlet
 */
@WebServlet("/DetailCompanyLoadServlet")
public class DetailCompanyLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processCompanyLoad(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processCompanyLoad(request, response);
	}

	private void processCompanyLoad(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		String mpd = request.getParameter("pId");
		String cp="";

		
		CompanyFactory cf = (CompanyFactory) request.getServletContext().getAttribute(GeneralVarName.Web_CompanyFactory);		
		CompanyPictureFactory cpf = (CompanyPictureFactory) request.getServletContext().getAttribute(GeneralVarName.Web_CompanyPictureFactory);
		cpf.refreshByDataBase();
		Company ALcf=(Company)cf.sreachByPK(mpd);
		ArrayList<CompanyPictures>ALrmethod=(ArrayList<CompanyPictures>)cpf.sreachByPK(mpd);	
		
		String CName="";
		String CLogo="";
		String CCover="";
		String CIntro="";
		String CAddress="";
		String CTel="";
		String CEmail="";
		String COwner="";
		
		CName=ALcf.getCompany_name();
		CLogo=ALcf.getCompany_logo();
		CCover=ALcf.getCompany_cover();
		CIntro=ALcf.getCompany_intro();
		CAddress=ALcf.getCompany_address();
		CTel=ALcf.getCompany_tel();
		CEmail=ALcf.getCompany_email();
		COwner=ALcf.getCompany_owner();
		
		out.write("<div class='col-md-3 sidebarborder'><div class='list-group'><a href='#' class='list-group-item'><img src='"+CLogo+"' class='sidebar' /></a><a href='#' class='list-group-item'>"+CName+"</a><a href='#' class='list-group-item'>"+COwner+"</a><a href='#' class='list-group-item'>"+CTel+"</a></div></div>");
		out.write("<section id='home' class='tm-content-box tm-banner margin-b-10'><div class='tm-banner-inner'><p class='tm-banner-text'><img class='border' src='"+CCover+"' style='width:90%;height:70%;'/></p></div></section>");
		out.write("<section>"
				+ "<div class='tm-content-box flex-2-col'>"
				+ "<div class='pad flex-item tm-team-description-container'>"
				+ "<h2 class='tm-section-title'>關於我們</h2>"
				+ "<p class='tm-section-description'>"+CIntro+"</p></div>"
				+ "<div class='flex-item'><img src='img/about.jpg' alt=''></div></div></section>");		
		
/*作品集*/out.write("<section id='ideas'>"
		+ "<div id='tmCarousel' class='carousel slide tm-content-box' data-ride='carousel'>"
		+ "<div class='carousel-inner' role='listbox'>"
		+ "<div class='carousel-item active'>"
		+ "<div class='carousel-content'>"
		+ "<div class='flex-item'><h2 class='tm-section-title'>作品集</h2>"
		+ "<div class='htmleaf-content bgcolor-8'>");

			for(CompanyPictures rm:ALrmethod){
				cp=rm.getPicture_path();
				out.write("<div><img src='"+cp+"' alt style='midth:20%;height:60%;'></div>");
				System.out.println(cp);
			}


		
out.write("</div></div></div></div></div></div></section>");
		
/*郵件　*/out.write("<section id='ideas'><div id='tmCarousel' class='carousel slide tm-content-box' data-ride='carousel'><div class='carousel-inner' role='listbox'><div class='carousel-item active'><div><div class='flex-item'><h2 class='tm-section-title'>Email</h2><p class='tm-section-description carousel-description'>"+CEmail+"</p></div></div></div>");
/*地址　*/out.write("<div class='carousel-item'><div ><div class='flex-item'><h2 class='tm-section-title'>公司地址</h2><p class='tm-section-description carousel-description'>"+CAddress+"</p></div></div></div></div></div></section>");
		
		out.write("<section class='tm-content-box'><img src='img/contact.jpg' alt='Contact image' class='img-fluid'><div id='contact' class='pad'><h2 class='tm-section-title'>Contact Us</h2>"
				+ "<form action='./CompanyMessageServlet' method='post' class='contact-form'><div class='form-group col-xs-12 col-sm-12 col-md-6 col-lg-6 form-group-2-col-left'>"
				+ "</div>"
				+ "<div class='form-group col-xs-12 col-sm-12 col-md-6 col-lg-6 form-group-2-col-right'></div>"
				+ "<div class='form-group'></div>"
				+ "<div class='form-group'><textarea id='contact_message' name='contact_message' class='form-control' rows='9' placeholder='Message' required></textarea></div>"
				+ "<button type='submit' class='btn btn-primary submit-btn addrecipe'>送出表單</button>"
				+ "<input type='hidden' name='receiver' value='"+mpd+"'>"
				+ "<input type='hidden' name='msg' value='sendmsg'></form></div></section>");
		
		out.write("<footer class='tm-footer'><p class='text-xs-center'>Copyright &copy; 2016 Hellokitchen</p></footer>");
		out.close();
	}

}
