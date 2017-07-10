package ControlServlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import AbstractAncestor.FileManipulateServlet;
import EntityComponents.Recipe;
import Factorys.RecipeFactory;
import Factorys.RecipeMaterialFactory;
import Factorys.RecipeMethodFactory;
import Utility.GeneralVarName;
import Utility.ProcessDate;

/**
 * Servlet implementation class AndroidRecipeUpdateServlet
 */
@WebServlet("/AndroidRecipeUpdateServlet")
public class AndroidRecipeUpdateServlet extends FileManipulateServlet {
	private static final long serialVersionUID = 1L;
	private RecipeFactory myRecipeFactory;
	private RecipeMaterialFactory myRecipeMaterialFactory;
	private RecipeMethodFactory myRecipeMethodFactory;
	private PrintWriter out;
	private Recipe myNewRecipe;
	private Recipe myOldRecipe;
	private String recipeId;
	private String filePath;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AndroidRecipeUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAndroidRecipeUpdateServlet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAndroidRecipeUpdateServlet(request, response);
	}

	private void processAndroidRecipeUpdateServlet(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		try {
			request.setCharacterEncoding("utf-8");

			response.setContentType("html/text;charset=utf-8");
			out = response.getWriter();

			myRecipeFactory = (RecipeFactory) this.getServletContext().getAttribute(GeneralVarName.Web_RecipeFactory);
			myRecipeMaterialFactory = (RecipeMaterialFactory) this.getServletContext()
					.getAttribute(GeneralVarName.Web_RecipeMaterialFactory);
			myRecipeMethodFactory = (RecipeMethodFactory) this.getServletContext()
					.getAttribute(GeneralVarName.Web_RecipeMethodFactory);

			BufferedReader reader = request.getReader();
			String jsonString = reader.readLine();
			System.out.println(jsonString);

			JSONObject jsonObject = new JSONObject(jsonString);

			System.out.println(jsonObject);

			JSONObject myJsonRecipe = new JSONObject(jsonObject.get(GeneralVarName.Android_JSON_Key_Recipe).toString());
			java.sql.Date sqlDate = ProcessDate.getCurrentLocalDate();
			System.out.println("myJsonRecipe:" + myJsonRecipe);
			System.out.println("Recipe_id:" + myJsonRecipe.getString(GeneralVarName.Android_JSON_Key_Recipe_id));

			recipeId = myJsonRecipe.getString(GeneralVarName.Android_JSON_Key_Recipe_id);

			myOldRecipe = (Recipe) myRecipeFactory
					.sreachByPK(myJsonRecipe.getString(GeneralVarName.Android_JSON_Key_Recipe_id));

			myNewRecipe = new Recipe(myJsonRecipe.getString(GeneralVarName.Android_JSON_Key_Recipe_id),
					myJsonRecipe.getString(GeneralVarName.Android_JSON_Key_Recipe_name),
					myJsonRecipe.getString(GeneralVarName.Android_JSON_Key_Member_id),
					myJsonRecipe.getString(GeneralVarName.Android_JSON_Key_Recipe_amount),
					myJsonRecipe.getString(GeneralVarName.Android_JSON_Key_Recipe_cooktime),
					myJsonRecipe.getString(GeneralVarName.Android_JSON_Key_Recipe_picture),
					myJsonRecipe.getBoolean(GeneralVarName.Android_JSON_Key_Recipe_status), sqlDate,
					myJsonRecipe.getString(GeneralVarName.Android_JSON_Key_Recipe_detail));

			myNewRecipe.setRecipe_picture(myOldRecipe.getRecipe_picture());
			myRecipeFactory.Update(myOldRecipe, myNewRecipe);
			myRecipeFactory.remove(myOldRecipe);
			myRecipeFactory.add(myNewRecipe);

			myRecipeMaterialFactory.setJSON(jsonObject.get(GeneralVarName.Android_JSON_Key_Recipe_Material).toString()); // 解析Json

			myRecipeMethodFactory.setJSON(jsonObject.get(GeneralVarName.Android_JSON_Key_Recipe_Method).toString()); // 解析Json

			// 判斷新資料與舊資料

			myRecipeMaterialFactory.UpdateForMobile(recipeId);
			myRecipeMethodFactory.UpdateForMobile(recipeId);
			
			/*解析食譜照片JsonFile*/
			filePath = request.getServletContext().getRealPath("/recipepic/");
			//filePath = GeneralVarName.Linun_TomcatServer_Path + "recipepic/";
			
			if (!jsonObject.get(GeneralVarName.Android_JSON_Key_Recipe_picture_file).toString().equals("")) {
				super.androidRecipePictureUpload(
						jsonObject.get(GeneralVarName.Android_JSON_Key_Recipe_picture_file).toString(), 
						recipeId, filePath);
			}
			out.write(GeneralVarName.Android_JSON_Value_Success);
			
		} catch (SQLException e) {
			out.write(GeneralVarName.Android_JSON_Value_Fail);
			e.printStackTrace();
		} catch (JSONException e) {
			out.write(GeneralVarName.Android_JSON_Value_Fail);
			e.printStackTrace();
		} catch (IOException e) {
			out.write(GeneralVarName.Android_JSON_Value_Fail);
			e.printStackTrace();
		}
		out.close();

	}

}
