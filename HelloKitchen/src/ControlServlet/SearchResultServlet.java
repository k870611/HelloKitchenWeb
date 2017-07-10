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
import Factorys.RecipeFactory;
import Utility.GeneralVarName;

/**
 * Servlet implementation class SearchResultServlet
 */
@WebServlet("/SearchResultServlet")
public class SearchResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SearchResult(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SearchResult(request,response);
	}

	private void SearchResult(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		String search=request.getParameter("search");
		
		RecipeFactory rc = (RecipeFactory) request.getServletContext().getAttribute(GeneralVarName.Web_RecipeFactory);
		
		ArrayList<Recipe> dataset = rc.SearchResult(search);
		System.out.println("index current data count==> "+dataset.size());
		System.out.println("data ==> "+dataset.toString());
		
		request.setAttribute("search", dataset);
		request.getRequestDispatcher("/SearchResult.jsp").forward(request, response);
		
	}

}
