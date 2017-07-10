package ControlServlet;

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

import Factorys.RecipeFactory;
import Utility.GeneralVarName;

/**
 * Servlet implementation class AndroidRecipeServlet
 */
@WebServlet("/AndroidRecipeServlet")
public class AndroidRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AndroidRecipeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processAndroidRecipeServlet(request, response);
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
			processAndroidRecipeServlet(request, response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processAndroidRecipeServlet(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("html/text;charset=utf-8");
		
		//System.out.println("1111111111");
		
		out = response.getWriter();
		
		ServletContext sc = this.getServletContext();
		RecipeFactory myRecipeFactory = (RecipeFactory) sc.getAttribute(GeneralVarName.Web_RecipeFactory);
		
		JSONArray myJsonArray = new JSONArray(myRecipeFactory.getAllParseToJSON().toString());
		
		System.out.println(myJsonArray.toString());
		
		out.write(myJsonArray.toString());
		out.close();
		
	}


}
