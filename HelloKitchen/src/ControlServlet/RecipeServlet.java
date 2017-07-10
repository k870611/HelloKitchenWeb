package ControlServlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import EntityComponents.Recipe;
import Factorys.RecipeFactory;
import Modules.SessionStamp;
import Utility.GeneralVarName;
import Utility.ProcessDate;

public class RecipeServlet extends HttpServlet{
	public RecipeServlet(){
		
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.processRecipe(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.processRecipe(request, response);
		
	}
	
	private void processRecipe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
     	HttpSession hs = request.getSession();
     	SessionStamp st = (SessionStamp) hs.getAttribute(GeneralVarName.Session_LoginAccount);
		if(st.isLoginStatus()){
			String operation = request.getParameter("");
			RecipeFactory rc = (RecipeFactory) request.getServletContext().getAttribute(GeneralVarName.Web_RecipeFactory);
		    
			//--- 外層決定動作 ----
			switch(operation){
			  case "addRecipe":
				 /* public Recipe(String recipe_id, String recipe_name,
				  *  String member_id, String recipe_amount, 
				  *  String recipe_cooktime,String recipe_picture, 
				  *  boolean recipe_status, Date upload_date)
				  *  String recipe_detail*/
				  String recipe_name = request.getParameter("");
				  String recipe_amount = request.getParameter("");
				  String recipe_cooktime = request.getParameter("");
				  String recipe_picture = request.getParameter("");
				  String recipe_status = request.getParameter("");
				  String recipe_detail = request.getParameter("");
				//-------------------------------------  
				  String recipe_id  = ProcessDate.getCurrentLocalDateString() + recipe_name;
				  String member_id = st.getLoginAccount();
				  Recipe addnewRc = new Recipe(recipe_id,recipe_name,member_id,recipe_amount
						                       ,recipe_cooktime,recipe_picture,true,ProcessDate.getCurrentLocalDate(),recipe_detail);
			    try {
					rc.Insert(addnewRc);
					rc.add(addnewRc);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    request.getRequestDispatcher("").forward(request, response);
		      //------------------------------------------------------------
		    }//--- case "addRecipe" end
			
		}else{
			///------ 使用者沒有登入  ----
			
			
		}
		//----------------------------------
	}
	
	
	
}//--- class end
