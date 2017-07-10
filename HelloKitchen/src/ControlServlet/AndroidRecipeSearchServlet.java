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

import Factorys.RecipeFactory;
import Utility.GeneralVarName;

/**
 * Servlet implementation class AndroidRecipeSearchServlet
 */
@WebServlet("/AndroidRecipeSearchServlet")
public class AndroidRecipeSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AndroidRecipeSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processAndroidRecipeSearchServlet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processAndroidRecipeSearchServlet(request, response);
	}

	private void processAndroidRecipeSearchServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("html/text;charset=utf-8");
		
		out = response.getWriter();
		
		BufferedReader reader = request.getReader();
		String jsonString = reader.readLine();
		System.out.println(jsonString);
		
		if (jsonString.equals("[]")) {
			out.write(GeneralVarName.Android_JSON_Value_Fail);
		} else {
			ServletContext sc = this.getServletContext();
			RecipeFactory myRecipeFactory = (RecipeFactory) sc.getAttribute(GeneralVarName.Web_RecipeFactory);
			
			String searchString = myRecipeFactory.materialSearchRecipe(jsonString);
			
			if (!searchString.equals("[]")) {
				System.out.println("結果: " + searchString);
				out.write(searchString);
			} else {
				System.out.println("結果: " + "查無相關食材食譜");
				out.write(GeneralVarName.Android_JSON_Value_Fail);
			}
		}
		out.close();
		
	}
}
