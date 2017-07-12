package ControlServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import EntityComponents.Member;
import Factorys.MemberFactory;
import Modules.SessionStamp;
import Utility.GeneralVarName;

/**
 * Servlet implementation class MemberLogin
 */
@WebServlet("/MemberLogin")
public class MemberLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberLogin() {
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
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		this.processMemberLogin(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		this.processMemberLogin(request, response);
	}

	// -------------
	private void processMemberLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext sc = this.getServletContext();
		MemberFactory mf = (MemberFactory) sc.getAttribute(GeneralVarName.Web_MemberFactory);
		String account = request.getParameter("account");
		String password = request.getParameter("pwd");
		// String operation = "logFromWeb";
		String operation = request.getParameter("operation");
		HttpSession mySession = request.getSession();
		
		System.out.println("into memberlogin page ");
		System.out.println("取得 operation ==>" + operation);
		System.out.println("取得 account ==>" + account);
		System.out.println("取得 password ==>" + password);
		if (operation.equals("logFromWeb")) {
			System.out.println("come to here logFromWeb");
			if (mf.memberLoginByPassword(account, password)) {
				// --- 成功
				//HttpSession mySession = request.getSession();
				// -- 記錄登入帳號----
				SessionStamp st = new SessionStamp(account);
				st.setLoginStatus(true);
				mySession.setAttribute(GeneralVarName.Session_LoginAccount, st);
				mySession.removeAttribute(GeneralVarName.Session_CompanyLogin);//將company的session洗成null
				System.out.println("current login " + st.getLoginAccount());
				// -----------------------------
				System.out.println("登入成功");
//				request.setAttribute("SetMemberID", account);
				request.setAttribute("Message", "success");
				
				
				out.println("<div id='alert' style='position: fixed;top:2%;left: 40%;min-width: 20%;z-index: 99999;padding: 15px;border: 1px solid transparent;border-radius: 4px;text-align:center;color: #000000;background-color:#e5e5e5;'></div>");
				out.println("<script type=\"text/javascript\">");
				out.println("document.getElementById('alert').innerHTML='登入成功';");
				out.println("setTimeout(function(){location.href='index.jsp'}, 2000 );");
				out.println("</script>");
			} else {
				// --- 失敗
				System.out.print("登入失敗");
				request.setAttribute("Message", "fail");
				out.println("<div id='alert' style='position: fixed;top:2%;left: 40%;min-width: 20%;z-index: 99999;padding: 15px;border: 1px solid transparent;border-radius: 4px;text-align:center;color: #000000;background-color:#e5e5e5;'></div>");
				out.println("<script type=\"text/javascript\">");
				out.println("document.getElementById('alert').innerHTML='登入失敗';");
				out.println("setTimeout(function(){location.href='index.jsp'}, 2000 );");
				out.println("</script>");
			}
		}
		//-----------------------
		if (operation.equals("createAccount")) {
			System.out.println("come to here createAccount");
			if (mf.isExistByPK(account)) {
				// --- 帳號已存在---
				return;//-- 斷點
			} else {
				System.out.println("come to here FcreateAccount");
				/*
				 * 建帳號 * @param member_id 會員帳號
				 * @param member_name 會員名稱
				 * @param member_email 會員電子帳號
				 * @param member_password 會員密碼
				 * @param member_tel 會員電話
				 * @param member_fb 會員臉書
				 */
				account = request.getParameter("account");
				String name = request.getParameter("userName");
				password = request.getParameter("cpwd");
				String tel = request.getParameter("tel");
				String fb = request.getParameter("fb");
				Member newmember = new Member(account, name, account, password, tel, fb);
				try {
					mf.establishMemberByPassword(newmember);
					SessionStamp st = new SessionStamp(account);
					st.setLoginStatus(true);
					mySession.setAttribute(GeneralVarName.Session_LoginAccount, st);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// --------- 創建成功 -----
				out.println("<div id='alert' style='position: fixed;top:2%;left: 40%;min-width: 20%;z-index: 99999;padding: 15px;border: 1px solid transparent;border-radius: 4px;text-align:center;color: #000000;background-color:#e5e5e5;'></div>");
				out.println("<script type=\"text/javascript\">");
				out.println("document.getElementById('alert').innerHTML='註冊成功';");
				out.println("setTimeout(function(){location.href='index.jsp'}, 2000 );");
				out.println("</script>");
			}
		} 
		
    //-------------- JSON 比對登入/電子郵件模板---------------------------------  
		if(operation.equals("test")){
    	   account = request.getParameter("account");
    	   System.out.println("come to here Test");
    	   out.println(mf.getCheckAccountResult(account));
    	   return;//--- 斷點
		}
	//------------------ 登出 ----------------
		if(operation.equals("logout")){
			request.getSession().invalidate();
			//request.getSession().removeAttribute(GeneralVarName.Session_LoginAccount);
			/// 回轉首頁 ----
			response.sendRedirect("/index.jsp");
			return ;//-- 斷點
		}
	}
}// --- class end
