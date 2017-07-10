package ControlServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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

import AbstractAncestor.FileManipulateServlet;

/**
 * Servlet implementation class DeleteRecipeServlet
 */
@WebServlet("/DeleteRecipeServlet")
public class DeleteRecipeServlet extends FileManipulateServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DeleteRecipe(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DeleteRecipe(request,response);
	}

	private void DeleteRecipe(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html;charset=UTF-8");
		
		RecipeFactory recipe = (RecipeFactory) request.getServletContext().getAttribute(GeneralVarName.Web_RecipeFactory);
		RecipeMaterialFactory rmaterial = (RecipeMaterialFactory) request.getServletContext().getAttribute(GeneralVarName.Web_RecipeMaterialFactory);
		RecipeMethodFactory rmethod = (RecipeMethodFactory) request.getServletContext().getAttribute(GeneralVarName.Web_RecipeMethodFactory);
		PrintWriter out = response.getWriter();
		
		//String filepath_part2 ="C:\\DataSource\\workspace\\HelloKitchen0623\\WebContent\\";
		String filepath_part2=this.getWebContentRealPath("RecPic");
		System.out.println(filepath_part2);
		String RecipeId=request.getParameter("rid");

		ArrayList<RecipeMaterial> ALrmaterial = (ArrayList<RecipeMaterial>)rmaterial.sreachByPK(RecipeId);
		ArrayList<RecipeMethod> ALrmethod = (ArrayList<RecipeMethod>)rmethod.sreachByPK(RecipeId);
		Recipe R=(Recipe)recipe.sreachByPK(RecipeId);
		
		try {
			rmaterial.deleteAboutRecipe("trecipe_material", RecipeId);
			rmethod.deleteAboutRecipe("tmethod", RecipeId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(RecipeMaterial rm:ALrmaterial){
			rmaterial.remove(rm);
		}
		
		String filename = this.extraFlieName(R.getRecipe_picture());
		super.delFile(filepath_part2+"\\"+filename);
		
		
		for(RecipeMethod rm:ALrmethod){
			rmethod.remove(rm);
		}
		//super.delFile(filepath_part2+R.getRecipe_picture());

		recipe.Delete(R);
		recipe.remove(R);
		
		out.println("<div id='alert' style='position: fixed;top:2%;left: 40%;min-width: 20%;z-index: 99999;padding: 15px;border: 1px solid transparent;border-radius: 4px;text-align:center;color: #000000;background-color:#808080;'></div>");
		out.println("<script type=\"text/javascript\">");
		out.println("document.getElementById('alert').innerHTML='食譜刪除成功';");
		out.println("setTimeout(function(){location.href='ManageRecipe.jsp'}, 1500 );");
		out.println("</script>");		
	}
	
	
	
}
