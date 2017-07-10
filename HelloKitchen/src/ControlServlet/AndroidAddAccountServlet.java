package ControlServlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import EntityComponents.Member;
import Factorys.MemberFactory;
import Utility.GeneralVarName;

/**
 * Servlet implementation class AndroidAddAccountServlet
 */
@WebServlet("/AndroidAddAccountServlet")
public class AndroidAddAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AndroidAddAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processAddAccount(request,response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processAddAccount(request,response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processAddAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException, SQLException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("html/text;charset=utf-8");
		
		out = response.getWriter();
		BufferedReader reader = request.getReader();
		String jsonString = reader.readLine();
		System.out.println(jsonString);

		Member myMember = new Member();
		JSONObject jsonObject = new JSONObject(jsonString);
		
		System.out.println(jsonObject);
		
		myMember.setMember_id(jsonObject.getString(GeneralVarName.Android_JSON_Key_Member_Id));
		myMember.setMember_name(jsonObject.getString(GeneralVarName.Android_JSON_Key_Member_Name));
		myMember.setMember_password(jsonObject.getString(GeneralVarName.Android_JSON_Key_Member_Password));
		myMember.setMember_tel(jsonObject.getString(GeneralVarName.Android_JSON_Key_Member_Tel));
		myMember.setMember_email(jsonObject.getString(GeneralVarName.Android_JSON_Key_Member_Email));
		myMember.setMember_fb(jsonObject.getString(GeneralVarName.Android_JSON_Key_Member_FB_Id));

		JSONObject jsonInfo = new JSONObject(); //回傳json 資訊;
		
		ServletContext sc = this.getServletContext();
		MemberFactory myMemberFactory = (MemberFactory) sc.getAttribute(GeneralVarName.Web_MemberFactory);

		if (myMemberFactory.isExistByPK(myMember.getMember_id())) {
			jsonInfo.put(GeneralVarName.Android_JSON_Key_Information, GeneralVarName.Android_JSON_Value_Fail);
		} else {
			myMemberFactory.establishMemberByPassword(myMember);
			jsonInfo.put(GeneralVarName.Android_JSON_Key_Information, GeneralVarName.Android_JSON_Value_Success);
		}
		
		System.out.println(jsonInfo.toString());
		out.write(jsonInfo.toString());
		out.close();
	}

}
