package ControlServlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import AbstractAncestor.FileManipulateServlet;
import EntityComponents.Recipe;
import EntityComponents.RecipeMaterial;
import EntityComponents.RecipeMethod;
import Factorys.RecipeFactory;
import Factorys.RecipeMaterialFactory;
import Factorys.RecipeMethodFactory;
import Modules.SessionStamp;
import Utility.GeneralVarName;
import Utility.ProcessDate;

/**
 * Servlet implementation class UpdateRecipeServlet
 */
@WebServlet("/UpdateRecipeServlet")
@MultipartConfig
public class UpdateRecipeServlet extends FileManipulateServlet {
	private static final long serialVersionUID = 1L;

	private RecipeMaterial rMaterial;

	private RecipeFactory rf;
	private RecipeMaterialFactory rfMaterial;
	private RecipeMethodFactory rfMethod;
	// private String filepath_part2;
	private String recPath;
	private String Id;
	// private ArrayList<RecipeMaterial> //OldMaterial;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			UpdateRecipe(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		rfMaterial = (RecipeMaterialFactory) this.getServletContext()
				.getAttribute(GeneralVarName.Web_RecipeMaterialFactory);
		rf = (RecipeFactory) this.getServletContext().getAttribute(GeneralVarName.Web_RecipeFactory);
		rfMethod = (RecipeMethodFactory) this.getServletContext().getAttribute(GeneralVarName.Web_RecipeMethodFactory);
		recPath = request.getServletContext().getRealPath("/recipepic");
		try {
			UpdateRecipe(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void UpdateRecipe(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter outer = response.getWriter();
		// outer.write("into success"+"<br/>");
		Part Pic = request.getPart("Pic");
		String Name = request.getParameter("Name");
		String amount = request.getParameter("amount");
		String cooktime = request.getParameter("cooktime");
		String Detail = request.getParameter("Detail");
		String status = request.getParameter("status");
		Id = request.getParameter("rid");
		Boolean statuschk = false;

		if ("open".equals(status)) {
			statuschk = true;
		} else if ("close".equals(status)) {
			statuschk = false;
		}

		Recipe Oldrecipe = (Recipe) rf.sreachByPK(Id);
		/*
		 * public Recipe(String recipe_id, String recipe_name, String member_id,
		 * String recipe_amount, String recipe_cooktime, String recipe_picture,
		 * boolean recipe_status, Date upload_date, String recipe_detail)
		 */
		Date sqDate = ProcessDate.getCurrentLocalDate();
		Recipe Newrecipe = new Recipe(Oldrecipe.getRecipe_id(), Name, Oldrecipe.getMember_id(), amount, cooktime,
				Oldrecipe.getRecipe_picture(), statuschk, sqDate, Detail);

		// outer.write(Oldrecipe.toString()+"<br/>");
		// outer.write(Newrecipe.toString()+"<br/>");

		ArrayList<RecipeMaterial> RecipeMaterialSet = createRecipeMaterialSet(request);
		rfMaterial.UpdateForWeb(Id, RecipeMaterialSet);
		// Method---------------------------------------------------------------
		ArrayList<RecipeMethod> MethodSet = new ArrayList<RecipeMethod>();
		int MethodCount = 0;
		while (!"".equals(request.getParameter("Method" + MethodCount))
				&& (request.getParameter("Method" + MethodCount)) != null) {
			String MethodDetail = request.getParameter("Method" + MethodCount);

			// RecipeMethod(String recipe_id, String method_id, String
			// method_detail, String method_picture)
			MethodCount++;
			RecipeMethod NewMethod = new RecipeMethod(Id, String.format("m%03d", MethodCount), MethodDetail, "noMEpic");
			MethodSet.add(NewMethod);
		}
		// System.out.println(MethodSet.toString());
		rfMethod.UpdateForWeb(Id, MethodSet);
		// Method
		// UpdateEnd---------------------------------------------------------------
		// ----- pic upload ----------------------------
		if (Pic.getSize() > 0) {
			System.out.println("更新圖片開始");
			if (Oldrecipe.getRecipe_picture().equals("noPic")) {
				String filename = super.getFileNameByPart(Pic);
				filename = rf.replaceFileName(filename, rf.IDGenerator());
				super.writeFile(Pic, recPath + File.separator + filename);
				Newrecipe.setRecipe_picture("recipepic" + File.separator + filename);
				System.out.println("圖片更新new=>" + recPath + File.separator + filename);
			} else {
				String filename = super.extraFlieName(Oldrecipe.getRecipe_picture());
				super.writeFile(Pic, recPath + File.separator + filename);
				System.out.println("圖片更新old=>" + recPath + File.separator + filename);
			}
		}
		// ----------------------------------------
		rf.Update(Oldrecipe, Newrecipe);
		rf.refresh(Newrecipe);
		// -------------------------------------------------------------
		outer.println(
				"<div id='alert' style='position: fixed;top:2%;left: 40%;min-width: 20%;z-index: 99999;padding: 15px;border: 1px solid transparent;border-radius: 4px;text-align:center;color: #000000;background-color:#808080;'></div>");
		outer.println("<script type=\"text/javascript\">");
		outer.println("document.getElementById('alert').innerHTML='食譜更新成功';");
		outer.println("setTimeout(function(){location.href='ManageRecipe.jsp'}, 1500 );");
		outer.println("</script>");
	}

	private ArrayList<RecipeMaterial> createRecipeMaterialSet(HttpServletRequest request) {
		ArrayList<RecipeMaterial> reciver = new ArrayList<RecipeMaterial>();
		int count = 0;
		while (!"".equals(request.getParameter("Material" + count))
				&& request.getParameter("Material" + count) != null) {
			String MaterialName = request.getParameter("Material" + count);
			String Amount = request.getParameter("Amount" + count);
			/*
			 * public RecipeMaterial(String recipe_id, String material_id,
			 * String material_name, String material_amount, String
			 * material_picture)
			 */
			rMaterial = new RecipeMaterial(Id, String.format("m%03d", count + 1), MaterialName, Amount, "no Data");
			reciver.add(rMaterial);
			System.out.println("第 " + count + " 從網頁抓取資料產生食譜材料物件=>" + rMaterial.toString());
			count++;
		}
		return reciver;
	}

	
}// --- class end
