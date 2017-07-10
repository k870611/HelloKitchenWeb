package ControlServlet;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class DetailProductLoadServlet
 */
@WebServlet("/DetailProductLoadServlet")
public class DetailProductLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processProductLoad(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processProductLoad(request, response);
	}

	private void processProductLoad(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		String mpd = request.getParameter("pId");
		System.out.println(mpd);
		int num=1;
		
		RecipeFactory rc = (RecipeFactory) request.getServletContext().getAttribute(GeneralVarName.Web_RecipeFactory);
		RecipeMaterialFactory rmaterial = (RecipeMaterialFactory) request.getServletContext().getAttribute(GeneralVarName.Web_RecipeMaterialFactory);
		RecipeMethodFactory rmethod = (RecipeMethodFactory) request.getServletContext().getAttribute(GeneralVarName.Web_RecipeMethodFactory);
		
		String recipeName="";
		String material="";
		String method="";
		String updateTime="";
		String Author="";
		String pic="";
		
		Recipe ALrc=(Recipe)rc.sreachByPK(mpd);
		ArrayList<RecipeMaterial>ALrmaterial=(ArrayList<RecipeMaterial>)rmaterial.sreachByPK(mpd);
		ArrayList<RecipeMethod>ALrmethod=(ArrayList<RecipeMethod>)rmethod.sreachByPK(mpd);
		
		for(RecipeMaterial rm :ALrmaterial){
			//out.write(rm.getMaterial_name());
			material+=(num+". "+rm.getMaterial_name()+"  "+rm.getMaterial_amount()+"<br/>");
			num++;
			System.out.println(num+"."+material);
		}	
	
		recipeName=ALrc.getRecipe_name();
		Author=ALrc.getMember_id();
		updateTime=ALrc.getUpload_date().toString();
		pic=ALrc.getRecipe_picture();
		String detail=ALrc.getRecipe_detail();
		
		//System.out.println(mpd+"...success");
		
/*Name*/		out.write("<div class='hint columns one' style='margin-top:3%;'>"+recipeName+"</div>");
/*cover*/		out.write("<div class='columns one featuredimage'><div class='image'><img class='picborder' src='"+pic+"' alt='food_single' /></div></div>");
/*cover*/		out.write("<div class='columns two-thirds'><div class='single_post'><div class='postinfo'><h2>"+recipeName+"</h2><p>"+updateTime+"  -  <span>by</span>  <a href='#'>"+Author+"</a></p></div>");
/*cover*/		out.write("<div class='postdescription'><p>"+detail+"</p>");

/*material*/	out.write("<div class='recipe'><h2>所需材料</h2><div class='ingredients'>"+material+"</div></div>");
/*cover*/		out.write("<div class='comments'><h3>步驟</h3>");
				
				for(RecipeMethod rm:ALrmethod){
					method=rm.getMethod_detail();
					out.write("<div class='comment'><div class='commenttext'>"+method+"</div></div>");

					
				}

///*cover*/		out.write("<div class='comment'><div class='commenttext'></div></div>");
///*cover*/		out.write("<div class='comment'><div class='commenttext'></div></div>");
				out.write("</div></div><div class='clear'></div></div></div>");
				
				out.close();				
		
	}

}
