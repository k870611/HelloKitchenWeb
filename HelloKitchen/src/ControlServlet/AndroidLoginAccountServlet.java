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

import EntityComponents.Company;
import EntityComponents.Member;
import EntityComponents.Token;
import Factorys.CompanyFactory;
import Factorys.MemberFactory;
import Factorys.TokenFactory;
import Utility.GeneralVarName;

/**
 * Servlet implementation class AndroidLoginAccountServlet
 */
@WebServlet("/AndroidLoginAccountServlet")
public class AndroidLoginAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
	ServletContext sc;
	MemberFactory myMemberFactory;
	CompanyFactory myCompanyFactory;
	TokenFactory myTokenFactory;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AndroidLoginAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processLoginAccountServlet(request,response);
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
			processLoginAccountServlet(request,response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processLoginAccountServlet(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException, SQLException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("html/text;charset=utf-8");
		
		out = response.getWriter();
		BufferedReader reader = request.getReader();
		String jsonString = reader.readLine();
		System.out.println(jsonString);

		JSONObject jsonObject = new JSONObject(jsonString);
		System.out.println(jsonObject);
		
		
		
		String member_id = jsonObject.getString(GeneralVarName.Android_JSON_Key_Member_Id);
		String member_password = jsonObject.getString(GeneralVarName.Android_JSON_Key_Member_Password);
		String phoneToken = jsonObject.getString(GeneralVarName.Android_User_Phone_Token);
		
		JSONObject jsonInfo = new JSONObject(); //回傳json 資訊;
		ServletContext sc = this.getServletContext();
		myMemberFactory = (MemberFactory) sc.getAttribute(GeneralVarName.Web_MemberFactory);
		myCompanyFactory = (CompanyFactory) sc.getAttribute(GeneralVarName.Web_CompanyFactory);
		myTokenFactory = (TokenFactory) sc.getAttribute(GeneralVarName.Web_TokenFactory);
		
		if (myMemberFactory.memberLoginByPassword(member_id, member_password)) {
			processToken(member_id, phoneToken);
			
			Member myMember = myMemberFactory.sreachByPK(member_id);
			jsonInfo.put(GeneralVarName.LOGIN_USER_IDENT, "user");
			jsonInfo.put(GeneralVarName.Android_JSON_Key_Information, GeneralVarName.Android_JSON_Value_Success);
			jsonInfo.put(GeneralVarName.Android_JSON_Key_Member_Name, myMember.getMember_name());
		} else {
			if (myCompanyFactory.companyLogin(member_id, member_password)) {
				processToken(member_id, phoneToken);
				
				Company myCompany = (Company) myCompanyFactory.sreachByPK(member_id);
				jsonInfo.put(GeneralVarName.LOGIN_USER_IDENT, "company");
				jsonInfo.put(GeneralVarName.Android_JSON_Key_Information, GeneralVarName.Android_JSON_Value_Success);
				jsonInfo.put(GeneralVarName.Android_JSON_Key_Member_Name, myCompany.getCompany_name());
			} else {
				jsonInfo.put(GeneralVarName.LOGIN_USER_IDENT, GeneralVarName.Android_JSON_Value_Null);
				jsonInfo.put(GeneralVarName.Android_JSON_Key_Information, GeneralVarName.Android_JSON_Value_Fail);
				jsonInfo.put(GeneralVarName.Android_JSON_Key_Member_Name, GeneralVarName.Android_JSON_Value_Null);
			}
		}
		
		System.out.println(jsonInfo.toString());
		out.write(jsonInfo.toString());
		out.close();
	}

	/*處理userToken(firebase)*/
	private void processToken (String member_id, String phoneToken) throws SQLException {
		Token myToken = new Token(member_id, phoneToken);
		if (!myTokenFactory.isExistByPK(myToken)) {
			if (myTokenFactory.Insert(myToken)) {
				myTokenFactory.add(myToken);
			}
		} else {
			Token oldToken = (Token) myTokenFactory.sreachByPK(member_id);
			myTokenFactory.Update(oldToken, myToken);
		}
	}
	
}
