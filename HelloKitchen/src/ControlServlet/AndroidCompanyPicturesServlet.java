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

import Factorys.CompanyPictureFactory;
import Utility.GeneralVarName;

/**
 * Servlet implementation class AndroidCompanyPicturesServlet
 */
@WebServlet("/AndroidCompanyPicturesServlet")
public class AndroidCompanyPicturesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AndroidCompanyPicturesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processAndroidCompanyPicturesServlet(request, response);
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
			processAndroidCompanyPicturesServlet(request, response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void processAndroidCompanyPicturesServlet(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("html/text;charset=utf-8");
		
		out = response.getWriter();
		
		BufferedReader reader = request.getReader();
		String jsonString = reader.readLine();
		System.out.println(jsonString);

		JSONObject jsonObject = new JSONObject(jsonString);

		System.out.println(jsonObject);
		
		String company_id = jsonObject.getString(GeneralVarName.Android_JSON_Key_Company_id);
		
		ServletContext sc = this.getServletContext();
		CompanyPictureFactory myCompanyPictureFactory = (CompanyPictureFactory) sc.getAttribute(GeneralVarName.Web_CompanyPictureFactory);

		JSONArray myCompanyPictureJsonArray = new JSONArray(myCompanyPictureFactory.JSONsreachByPK(company_id).toString());

		
		System.out.println(myCompanyPictureJsonArray.toString());
		
		out.write(myCompanyPictureJsonArray.toString());
		out.close();
	}

}
