package ControlServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import EntityComponents.Member;
import Factorys.MemberFactory;
import Modules.SessionStamp;
import Utility.GeneralVarName;

/**
 * Servlet implementation class FacebookLogin
 */
@WebServlet("/FacebookLogin")
public class FacebookLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FacebookLogin() {
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
		try {
			this.processFacebookLogin(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			this.processFacebookLogin(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void processFacebookLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");	
	PrintWriter out = response.getWriter();
	MemberFactory mf = (MemberFactory) this.getServletContext().getAttribute(GeneralVarName.Web_MemberFactory);
    String operation = request.getParameter("operation");
    String email = request.getParameter("email");
    String name = request.getParameter("name");
    String id = request.getParameter("id");
    
    System.out.println("進入臉書登入功能呼叫"+ operation+" ** "+email+" ** "+name);
    
    if(operation.equals("facelogin")){
    	boolean loginsuccess = mf.isExistByPK(email);
    	if(loginsuccess){
    		HttpSession mysession = request.getSession();
    		SessionStamp ssp = new SessionStamp(email);
    		ssp.setLoginStatus(true);
    		System.out.println("進入臉書登入");
    		mysession.setAttribute(GeneralVarName.Session_LoginAccount, ssp);
    	    out.println(mf.JSONCheckMemberID(email));
    	    
    	}else{	
    		//如果資料庫不存在就寫入資料庫。
    		
    		
			Member newmember = new Member(email, name, email, id, "FbTel", email);
			mf.establishMemberByPassword(newmember);

			HttpSession mysession = request.getSession();
    		SessionStamp ssp = new SessionStamp(email);
    		ssp.setLoginStatus(true);
    		System.out.println("進入臉書登入");
    		mysession.setAttribute(GeneralVarName.Session_LoginAccount, ssp);
    	    out.println(mf.JSONCheckMemberID(email));
 		
    	}
     }
    //-----------  登入狀態    ---------------------
    if(operation.equals("getLoginStatus")){
    	System.out.println("確認登入狀態");
    	out.println(getLoginStatus(request));
    }
    //-----------   臉書登出   ----------------
    if(operation.equals("faceLogout")){
//    	HttpSession mySession = request.getSession();
//    	Enumeration names=mySession.getAttributeNames();
//    	while(names.hasMoreElements()){
//    		mySession.removeAttribute((String) names.nextElement());
//    	}
    	//---mySession.invalidate();
    }
//--------------------------------------------------------		
	}//-----method end
  private JSONObject getLoginStatus(HttpServletRequest request){
	  JSONObject jsobj = new JSONObject();
	  HttpSession mySession = request.getSession();
	  SessionStamp ssp = (SessionStamp) mySession.getAttribute(GeneralVarName.Session_LoginAccount);
      if(ssp == null || !ssp.isLoginStatus()){
    	  try {
			jsobj.put("loginStatus", false);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }else{
    	  try {
			jsobj.put("loginStatus", true);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
      return jsobj;
  }
  //------- 
}//----class end
