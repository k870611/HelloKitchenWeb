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

import EntityComponents.Recipe;
import EntityComponents.RecipeMaterial;
import EntityComponents.RecipeMethod;
import Factorys.RecipeFactory;
import Factorys.RecipeMaterialFactory;
import Factorys.RecipeMethodFactory;
import Utility.GeneralVarName;

/**
 * Servlet implementation class ManageRecipeServlet
 */
@WebServlet("/ManageRecipeServlet")
public class ManageRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String mpd;
	private RecipeFactory rc;
	private RecipeMaterialFactory rmaterial;
	private RecipeMethodFactory rmethod;
	private PrintWriter out;
	private int txtId;
	private int process;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRecipe(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRecipe(request, response);	
	}

	private void processRecipe(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		rc = (RecipeFactory) request.getServletContext().getAttribute(GeneralVarName.Web_RecipeFactory);
		rmaterial = (RecipeMaterialFactory) request.getServletContext().getAttribute(GeneralVarName.Web_RecipeMaterialFactory);
		rmethod = (RecipeMethodFactory) request.getServletContext().getAttribute(GeneralVarName.Web_RecipeMethodFactory);
		
		mpd = request.getParameter("pId");

		if(!mpd.equalsIgnoreCase("null")){
			SelectRecipe();
		}else{
			out.write("<div class='image-container set-full-height' style='position:absolute;top:4%;z-index:-1;'>"
					+ "<div class='container'><div class='row'>"
					+ "<div class='col-sm-8 col-sm-offset-2'>"
					+ "<div class='wizard-container'>"
					+ "<div class='card wizard-card' data-color='orange' id='wizardProfile'>");
			out.write("<img src='images/logo.png' style='position:absolute;left:35%;top:33%'/> ");
			out.write("</div></div></div></div></div></div>");
		}
		
	}

	private void SelectRecipe() {
		
		Recipe ALrc=(Recipe)rc.sreachByPK(mpd);
		ArrayList<RecipeMaterial>ALrmaterial=(ArrayList<RecipeMaterial>)rmaterial.sreachByPK(mpd);
		ArrayList<RecipeMethod>ALrmethod=(ArrayList<RecipeMethod>)rmethod.sreachByPK(mpd);
		
		String pic=ALrc.getRecipe_picture();
		String name=ALrc.getRecipe_name();
		String amount=ALrc.getRecipe_amount();
		String cooktime=ALrc.getRecipe_cooktime();
		String detail=ALrc.getRecipe_detail();
		Boolean status=ALrc.isRecipe_status();
		
		ArrayList<String> material= new ArrayList<String>();
		ArrayList<String> mAmount=new ArrayList<String>();
		ArrayList<String> method=new ArrayList<String>();
		
		for(int i=0;i<ALrmaterial.size();i++){
			material.add(i,ALrmaterial.get(i).getMaterial_name());
			mAmount.add(i,ALrmaterial.get(i).getMaterial_amount());
		}

		for(int i=0;i<ALrmethod.size();i++){
			method.add(i, ALrmethod.get(i).getMethod_detail());
		}
				
				out.write("<div class='image-container set-full-height' style='position:absolute;top:4%;z-index:-1;'>"
						+ "<div class='container'><div class='row'>"
						+ "<div class='col-sm-8 col-sm-offset-2'>"
						+ "<div class='wizard-container'>"
						+ "<div class='card wizard-card' data-color='orange' id='wizardProfile'>");
		
/*表頭*/			out.write("<form action='./UpdateRecipeServlet' method='post' enctype='multipart/form-data'>"
						+ "<div class='wizard-header'><h3><b style='font-family: 微軟正黑體'>分享您的私房密笈</b><small></small></h3></div>"
						+ "<div class='wizard-navigation'><ul>"
						+ "<li><a href='#about' data-toggle='tab'>食譜</a></li>"
						+ "<li><a href='#account' data-toggle='tab'>材料</a></li>"
						+ "<li><a href='#address' data-toggle='tab'>步驟</a></li></ul>"
						+ "</div><div class='tab-content'><div class='tab-pane' id='about'>"
						+ "<div class='row'><h4 class='info-text'> 創建我的食譜</h4>");
			
/*圖片*/			out.write("<div class='col-sm-4 col-sm-offset-1'><div class='picture-container'><div class='picture'>"
						+ "<img src='"+pic+"' class='picture-src' id='wizardPicturePreview1' title='' />"
						+ "<input type='file' name='Pic' id='wizard-picture1'></div><h6>食譜代表圖片</h6>");

				if(status){
					out.write("<div style='padding:5%;'><label class='radio-inline'>"
							+ "<input type='radio' name='status' value='open' CHECKED>公布食譜</label>"
							+ "<label class='radio-inline'>"
							+ "<input type='radio' name='status' value='close'>私藏密笈</label></div></div></div>");
				}else if(!status){
					out.write("<div style='padding:5%;'><label class='radio-inline'>"
							+ "<input type='radio' name='status' value='open'>公布食譜</label>"
							+ "<label class='radio-inline'>"
							+ "<input type='radio' name='status' value='close' CHECKED>私藏密笈</label></div></div></div>");
				}

/*食譜*/			out.write("<div class='col-sm-6'><div class='form-group'><label>標題 <small>(必填)</small></label>"
						+ "<input name='Name' type='text' class='form-control' value='"+name+"' required></div>"
						+ "<div class='form-group'><label>幾人份</label>"
						+ "<input name='amount' type='text' class='form-control' value='"+amount+"'></div>"
						+ "<div class='form-group'><label>大約料理時間</label>"
						+ "<input name='cooktime' type='text' class='form-control' value='"+cooktime+"'></div></div>");

/*細項*/			out.write("<div class='col-sm-10 col-sm-offset-1'><div class='form-group'><label>食譜簡介 <small>(必填)</small></label>"
						+ "<textarea name='Detail' class='form-control' rows='9' required>"+detail+"</textarea></div></div></div></div>");

/*材料*/			out.write("<div class='tab-pane' id='account'><h4 class='info-text'> 需要那些材料?? </h4>"
						+ "<div class='row'>");

				out.write("<div class='col-sm-7 col-sm-offset-1'id='div0'>"
						+ "<div class='form-group'><label>名稱</label>"
						+ "<input type='text' name='Material0' class='form-control' value='"+material.get(0)+"' required></div></div>");
				
				out.write("<div class='col-sm-3'id='divAmount0'>"
						+ "<div class='form-group'><label>數量</label><input type='text' name='Amount0' class='form-control' value='"+mAmount.get(0)+"' required></div></div>");

				for(int i=1;i<material.size();i++){
					out.write("<div class='col-sm-7 col-sm-offset-1'id='div"+i+"'>"
							+ "<div class='form-group'>"
							+ "<input type='text' name='Material"+i+"' class='form-control' value='"+material.get(i)+"' required></div></div>");
					
					out.write("<div class='col-sm-3'id='divAmount"+i+"'>"
							+ "<div class='form-group'><input type='text' name='Amount"+i+"' class='form-control' value='"+mAmount.get(i)+"' required></div></div>");
					txtId=i;
				}
				
				out.write("<div id='showBlock'></div><div class='col-sm-4 col-sm-offset-1'>"
						+ "<input type='button' value='新增' class='btn btn-fill btn-warning btn-wd btn-sm pull-left addItem' name='next' /></div>"
						+ "<div class='col-sm-3'><input type='button' value='刪除' class='btn btn-fill btn-default btn-wd btn-sm pull-right delItem'/></div></div></div>");

/*步驟*/			out.write("<div class='tab-pane' id='address'><div class='row'>"
						+ "<div class='col-sm-12'><h4 class='info-text'> 步驟 </h4></div>");
					
				for(int i=0;i<method.size();i++){
					out.write("<div class='col-sm-7 col-sm-offset-1' id='process"+i+"'><div class='form-group'>"
							+ "<input type='text' name='Method"+i+"' class='form-control' value='"+method.get(i)+"' required></div></div>");
					process=i;
				}
			
				out.write("<div id='addProcess'></div><div class='col-sm-4 col-sm-offset-1'>"
						+ "<input type='button' value='addProcess' class='btn btn-fill btn-warning btn-wd btn-sm addProcess' name='next' /></div>"
						+ "<div class='col-sm-3'><input type='button' value='del' class='btn btn-fill btn-default btn-wd btn-sm delProcess'></div></div></div></div>");
				
				out.write("<div class='wizard-footer height-wizard'><div class='pull-right'>"
						+ "<input type='button' class='btn btn-next btn-fill btn-warning btn-wd btn-sm' name='next' value='Next' />"
						+ "<input type='submit' class='btn btn-finish btn-fill btn-warning btn-wd btn-sm' name='finish' value='Finish' /></div>"
						+ "<div class='pull-left'><input type='button' class='btn btn-previous btn-fill btn-default btn-wd btn-sm' name='previous' value='Previous' /></div><div class='clearfix'></div></div>"
						+ "<input type='hidden' name='rid' value='"+ALrc.getRecipe_id()+"'/></form>");
					
				out.write("</div></div></div></div></div>"
						+ "<div class='footer'>"
						+ "<div class='container'><a class='clickArea' href='ManageRecipe.jsp?page="+ALrc.getRecipe_id()+"'><img id='del' src='images/del.png'style='opacity:0.7' /></a></div></div></div>");
					
				out.write("<input type='hidden' id='path' value='DeleteRecipeServlet?rid="+ALrc.getRecipe_id()+"'>");
				
				out.println("<script type=\"text/javascript\">");
				out.println("var txtId ="+txtId);
				out.println("var process ="+process);
				out.println("</script>");
				System.out.println(txtId+":"+process);
	}

}
