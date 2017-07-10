package ControlServlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Factorys.RecipeMaterialFactory;
import Factorys.RecipeMethodFactory;
import Utility.GeneralVarName;

/**
 * Servlet implementation class AndroidRecipeDetailServlet
 */
@WebServlet("/AndroidRecipeDetailServlet")
public class AndroidRecipeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AndroidRecipeDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processAndroidRecipeDetailServlet(request, response);
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
			processAndroidRecipeDetailServlet(request, response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processAndroidRecipeDetailServlet(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("html/text;charset=utf-8");
		
		out = response.getWriter();
		
		BufferedReader reader = request.getReader();
		String jsonString = reader.readLine();
		System.out.println(jsonString);

		JSONObject jsonObject = new JSONObject(jsonString);

		System.out.println(jsonObject);
		
		String recipe_id = jsonObject.getString(GeneralVarName.Android_JSON_Key_Recipe_id);
		
		ServletContext sc = this.getServletContext();
		RecipeMaterialFactory myRecipeMaterialFactory = (RecipeMaterialFactory) sc.getAttribute(GeneralVarName.Web_RecipeMaterialFactory);
		RecipeMethodFactory myRecipeMethodFactory = (RecipeMethodFactory) sc.getAttribute(GeneralVarName.Web_RecipeMethodFactory);
		
		JSONArray myRecipeMaterialJsonArray = new JSONArray(myRecipeMaterialFactory.JSONsreachByPK(recipe_id).toString());
		JSONArray myRecipeMethodJsonArray = new JSONArray(myRecipeMethodFactory.JSONsreachByPK(recipe_id).toString());

		
		System.out.println(myRecipeMaterialJsonArray.toString());
		System.out.println(myRecipeMethodJsonArray.toString());
		
		JSONObject myJsonObject = new JSONObject();
		myJsonObject.put(GeneralVarName.Android_JSONArray_Key_Recipe_Material, myRecipeMaterialJsonArray);
		myJsonObject.put(GeneralVarName.Android_JSONArray_Key_Recipe_Method, myRecipeMethodJsonArray);
		
		System.out.println(myJsonObject.toString());
		
		out.write(myJsonObject.toString());
		out.close();
	}

}
