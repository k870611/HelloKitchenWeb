package ControlServlet;

import java.io.IOException;
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
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/MemberUpdateServlet")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.processMemberUpdate(request, response);
	}

	protected void processMemberUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*Member(String member_id, String member_name,
		 *       String member_email, String member_password, 
		 *       String member_tel,String member_fb*/
		HttpSession mysession = request.getSession();
		SessionStamp ssp  = (SessionStamp) mysession.getAttribute(GeneralVarName.Session_LoginAccount);
		if(ssp != null && ssp.isLoginStatus()){
			//--- 有登入才能改 ---
			MemberFactory mf = (MemberFactory) this.getServletContext().getAttribute(GeneralVarName.Web_MemberFactory); 
			Member olddata = mf.sreachByPK(ssp.getLoginAccount());
			String member_name = request.getParameter("userName");
			String member_email = ssp.getLoginAccount();
			//String member_email = "test01@gmail.com";
			String member_password = request.getParameter("cpwd");
			String member_tel = request.getParameter("tel");
			String member_fb = request.getParameter("fb");
			Member newdata = new Member(ssp.getLoginAccount(),member_name,member_email,member_password,member_tel,member_fb);
			//-- 更新 --
			mf.Update(olddata, newdata);
			mf.refresh(newdata);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		
	}//-- method end
	
}//--- class end
