package ControlServlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EntityComponents.Company;
import Factorys.CompanyFactory;
import Utility.GeneralVarName;

/**
 * Servlet implementation class CompanyServlet
 */
@WebServlet("/CompanyServlet")
public class CompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CompanyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.processCompany(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.processCompany(request, response);
	}

	protected void processCompany(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
///---------- 網頁流程需要定義 ---
		CompanyFactory cf = (CompanyFactory) request.getServletContext()
				.getAttribute(GeneralVarName.Web_CompanyFactory);
		String operation = request.getParameter("");
		switch (operation) {
		case "addCompany":
			/*public Company(String company_id, String company_name,
			 *  String company_logo, String company_cover,
			    String company_intro, String company_address, 
			    String company_tel, String company_email,
			    String company_owner, String company_password, 
			    boolean company_status)*/
			String company_id="";
			String company_name = request.getParameter("");
			String company_logo = request.getParameter("");
			String company_cover = request.getParameter("");
			String company_intro = request.getParameter("");
			String company_address = request.getParameter("");
			String company_tel = request.getParameter("");
			String company_email = request.getParameter("");
			String company_owner = request.getParameter("");
			String company_password = request.getParameter("");
			//boolean company_password = request.getParameter("");
			Company cp = new Company(company_id,company_name,
					                 company_logo,company_cover,
					                 company_intro,company_address,
					                 company_tel,company_email,
					                 company_owner,company_password,true);
			try {
				cf.Insert(cp);
				cf.add(cp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}// --- class end
