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

import Factorys.CompanyFactory;
import Utility.GeneralVarName;

/**
 * Servlet implementation class AndroidCompanyServlet
 */
@WebServlet("/AndroidCompanyServlet")
public class AndroidCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AndroidCompanyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processAndroidCompanyServlet(request, response);
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
			processAndroidCompanyServlet(request, response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
private void processAndroidCompanyServlet(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("html/text;charset=utf-8");
		
		//System.out.println("1111111111");
		
		out = response.getWriter();
		
		ServletContext sc = this.getServletContext();
		CompanyFactory myCompanyFactory = (CompanyFactory) sc.getAttribute(GeneralVarName.Web_CompanyFactory);
		
		JSONArray myJsonArray = new JSONArray(myCompanyFactory.getAllParseToJSON().toString());
		
		System.out.println(myJsonArray.toString());
		
		out.write(myJsonArray.toString());
		out.close();
		
	}

}
