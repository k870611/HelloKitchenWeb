package ControlServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import AbstractAncestor.FileManipulateServlet;
import Factorys.RecipeFactory;
import Factorys.RecipeMaterialFactory;
import Factorys.RecipeMethodFactory;
import Modules.CompositeKey;
import Utility.GeneralVarName;

/**
 * Servlet implementation class AndroidRecipeDeleteServlet
 */
@WebServlet("/AndroidRecipeDeleteServlet")
public class AndroidRecipeDeleteServlet extends FileManipulateServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
	private String filePath;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AndroidRecipeDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processAndroidRecipeDeleteServlet(request, response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processAndroidRecipeDeleteServlet(request, response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processAndroidRecipeDeleteServlet(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("html/text;charset=utf-8");
		
		
		out = response.getWriter();
		
		String recipe_Id = request.getParameter(GeneralVarName.Android_JSON_Key_Recipe_id);
		String member_Id = request.getParameter(GeneralVarName.Android_JSON_Key_Member_id);
		System.out.println(recipe_Id);
		System.out.println(member_Id);
		
		ServletContext sc = this.getServletContext();
		RecipeFactory myRecipeFactory = (RecipeFactory) sc.getAttribute(GeneralVarName.Web_RecipeFactory);
		RecipeMaterialFactory myRecipeMaterialFactory = (RecipeMaterialFactory) sc.getAttribute(GeneralVarName.Web_RecipeMaterialFactory);
		RecipeMethodFactory myRecipeMethodFactory = (RecipeMethodFactory) sc.getAttribute(GeneralVarName.Web_RecipeMethodFactory);
	
		JSONArray myRecipeMaterialJsonArray = new JSONArray(myRecipeMaterialFactory.JSONsreachByPK(recipe_Id).toString());
		JSONArray myRecipeMethodJsonArray = new JSONArray(myRecipeMethodFactory.JSONsreachByPK(recipe_Id).toString());
		
		
		/*Eclipse本機端路徑*/ 
		filePath = request.getServletContext().getRealPath("/recipepic/");
		//filepath_part = GeneralVarName.Linun_TomcatServer_Path;

		try {
			if (myRecipeMaterialFactory.deleteAboutRecipe("trecipe_material", recipe_Id) > 0) {
				for (int i = 0; i < myRecipeMaterialJsonArray.length(); i++) {
					JSONObject jsonObject = new JSONObject(myRecipeMaterialJsonArray.get(i).toString());
					String material_id = jsonObject.get(GeneralVarName.Android_JSON_Key_Recipe_Material_id).toString();
					myRecipeMaterialFactory.remove(new CompositeKey(recipe_Id, material_id));
				}
			}
			
			if (myRecipeMethodFactory.deleteAboutRecipe("tmethod", recipe_Id) > 0) {
				for (int i = 0; i < myRecipeMethodJsonArray.length(); i++) {
					JSONObject jsonObject = new JSONObject(myRecipeMethodJsonArray.get(i).toString());
					String method_id = jsonObject.get(GeneralVarName.Android_JSON_Key_Recipe_Method_id).toString();
					myRecipeMethodFactory.remove(new CompositeKey(recipe_Id, method_id));
				}
			}
			if (myRecipeFactory.deleteAboutRecipe("trecipe", recipe_Id) > 0) {
				myRecipeFactory.remove(recipe_Id);
				super.delFile(filePath + recipe_Id + ".jpg");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONArray myJsonArray = new JSONArray(myRecipeFactory.getAllParseToJSON(member_Id));
		System.out.println(myJsonArray.toString());
		out.write(myJsonArray.toString());
		out.close();
	}
	
}
