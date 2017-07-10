package ControlServlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import AbstractAncestor.FileManipulateServlet;
import Factorys.RecipeFactory;
import Factorys.RecipeMaterialFactory;
import Factorys.RecipeMethodFactory;
import Utility.GeneralVarName;

/**
 * Servlet implementation class AndroidRecipeUploadServlet
 */
@WebServlet("/AndroidRecipeUploadServlet")
public class AndroidRecipeUploadServlet extends FileManipulateServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
	private RecipeFactory myRecipeFactory;
	private RecipeMaterialFactory myRecipeMaterialFactory;
	private RecipeMethodFactory myRecipeMethodFactory;
	private String recipeId;
	private String filePath;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AndroidRecipeUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processAndroidRecipeUploadServlet(request, response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processAndroidRecipeUploadServlet(request, response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void processAndroidRecipeUploadServlet(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("html/text;charset=utf-8");

		boolean ifRecipe , ifRecipeMaterial, ifRecipeMethod;
		myRecipeFactory = (RecipeFactory) this.getServletContext().getAttribute(GeneralVarName.Web_RecipeFactory);
		myRecipeMaterialFactory = (RecipeMaterialFactory) this.getServletContext()
				.getAttribute(GeneralVarName.Web_RecipeMaterialFactory);
		myRecipeMethodFactory = (RecipeMethodFactory) this.getServletContext()
				.getAttribute(GeneralVarName.Web_RecipeMethodFactory);

		out = response.getWriter();
		
		BufferedReader reader = request.getReader();
		String jsonString = reader.readLine();
		System.out.println(jsonString);
		
		JSONObject jsonObject = new JSONObject(jsonString);

		System.out.println(jsonObject);
		
		recipeId = myRecipeFactory.IDGenerator();   //產生id

		/*解析食譜總表JsonFile並存入資料庫*/
		ifRecipe = myRecipeFactory.androidRecipeInsert(
				jsonObject.get(GeneralVarName.Android_JSON_Key_Recipe).toString(),
				recipeId);
		
		if (ifRecipe) {
			
			/*解析食譜照片JsonFile*/ 
			filePath = request.getServletContext().getRealPath("/recipepic/");
			//filePath = GeneralVarName.Linun_TomcatServer_Path + "recipepic/";
			
			super.androidRecipePictureUpload(
					jsonObject.get(GeneralVarName.Android_JSON_Key_Recipe_picture_file).toString(), 
					recipeId, filePath);
			
			/*解析和新增食譜食材JsonFile*/
			ifRecipeMaterial = myRecipeMaterialFactory.androidRecipeMaterialInsert(
					jsonObject.get(GeneralVarName.Android_JSON_Key_Recipe_Material).toString(), 
					recipeId);
			if (!ifRecipeMaterial) {
				out.write(GeneralVarName.Android_JSON_Value_Fail);
				out.close();
				return;
			}
			/*解析和新增食譜製作方法JsonFile*/
			ifRecipeMethod = myRecipeMethodFactory.androidRecipeMethodInsert(
					jsonObject.get(GeneralVarName.Android_JSON_Key_Recipe_Method).toString(), 
					recipeId);
			if (!ifRecipeMethod) {
				out.write(GeneralVarName.Android_JSON_Value_Fail);
				out.close();
				return;
			}
			out.write(GeneralVarName.Android_JSON_Value_Success);
		} else {
			out.write(GeneralVarName.Android_JSON_Value_Fail);
			out.close();
			return;
		}

		out.close();
	}
	

}
