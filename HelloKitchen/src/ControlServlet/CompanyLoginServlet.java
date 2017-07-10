package ControlServlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Factorys.CompanyFactory;
import Modules.SessionStamp;
import Utility.GeneralVarName;

/**
 * Servlet implementation class CompanyLoginServlet
 */
@WebServlet("/CompanyLoginServlet")
public class CompanyLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CompanyLogin(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CompanyLogin(request,response);
	}

	private void CompanyLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = this.getServletContext();
		CompanyFactory cf = (CompanyFactory) sc.getAttribute(GeneralVarName.Web_CompanyFactory);
		String account = request.getParameter("account");
		String password = request.getParameter("pwd");
       
		HttpSession mySession = request.getSession();
		if(cf.companyLogin(account, password)){
			SessionStamp st = new SessionStamp(account);
			st.setLoginStatus(true);
			mySession.setAttribute(GeneralVarName.Session_CompanyLogin, st);
			mySession.removeAttribute(GeneralVarName.Session_LoginAccount);//將Member的session洗成null
			
			System.out.println("current login " + st.getLoginAccount());
			// -----------------------------
			System.out.println("登入成功");
			request.setAttribute("Message", "success");
			request.getRequestDispatcher("/ManageCompany.jsp").forward(request, response);
		}else{
			System.out.print("登入失敗");
			request.setAttribute("Message", "fail");
			request.getRequestDispatcher("/CompanyLogin.jsp").forward(request, response);
		}
	}
}
