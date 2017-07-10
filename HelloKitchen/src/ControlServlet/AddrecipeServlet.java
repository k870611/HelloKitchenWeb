package ControlServlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import AbstractAncestor.FileManipulateServlet;
import EntityComponents.Recipe;
import EntityComponents.RecipeMaterial;
import EntityComponents.RecipeMethod;
import Factorys.RecipeFactory;
import Factorys.RecipeMaterialFactory;
import Factorys.RecipeMethodFactory;
import Modules.SessionStamp;
import Utility.GeneralVarName;
import Utility.ProcessDate;

/**
 * Servlet implementation class AddrecipeServlet
 */
@WebServlet("/AddrecipeServlet")
@MultipartConfig
public class AddrecipeServlet extends FileManipulateServlet {
	private static final long serialVersionUID = 1L;
	private Recipe recipe;
	private RecipeMaterial rMaterial;
	private RecipeMethod rMethod;
	private RecipeFactory rf;
	private RecipeMaterialFactory RfMaterial;
	private RecipeMethodFactory RfMethod;
	//private String filepath_part2;
	private String ricPath;
	private String cid;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processAddRecipe(request, response);
		} catch (Exception e) {
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
		try {
			RfMaterial = (RecipeMaterialFactory) this.getServletContext()
					.getAttribute(GeneralVarName.Web_RecipeMaterialFactory);
			rf = (RecipeFactory) this.getServletContext().getAttribute(GeneralVarName.Web_RecipeFactory);
			RfMethod = (RecipeMethodFactory) this.getServletContext()
					.getAttribute(GeneralVarName.Web_RecipeMethodFactory);
			ricPath = request.getServletContext().getRealPath("/recipepic");
			processAddRecipe(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processAddRecipe(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ParseException, SQLException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter outer = response.getWriter();
		Part Pic = request.getPart("Pic");
		String Name = request.getParameter("Name");
		String status=request.getParameter("status");
		String amount = request.getParameter("amount");
		String cooktime = request.getParameter("cooktime");
		String Detail = request.getParameter("Detail");
		HttpSession mySession = request.getSession();
		SessionStamp st = (SessionStamp) mySession.getAttribute(GeneralVarName.Session_LoginAccount);
		String MemberId = st.getLoginAccount();

	//----------------------------------------------------
		ArrayList<String> MethodSet = new ArrayList<String>();
		int MethodCount = 0;
			while (!"".equals(request.getParameter("Method" + MethodCount))
				&& (request.getParameter("Method" + MethodCount)) != null) {
			String MethodName = request.getParameter("Method" + MethodCount);
			if (MethodName != "") {
				MethodSet.add(MethodName);
			}
			MethodCount++;
		}
		// -- pass by reference
		// rf = (RecipeFactory)
		// this.getServletContext().getAttribute(GeneralVarName.Web_RecipeFactory);
		
		java.sql.Date sqlDate = ProcessDate.getCurrentLocalDate();
		cid = rf.IDGenerator();
		recipe = new Recipe();
		recipe.setMember_id(MemberId);
		recipe.setRecipe_detail(Detail);
		recipe.setRecipe_id(cid);
		recipe.setUpload_date(sqlDate);
		recipe.setRecipe_name(Name);
		recipe.setRecipe_amount(amount);
		recipe.setRecipe_cooktime(cooktime);
		
		if("open".equals(status)){
			recipe.setRecipe_status(true);
		}else if("close".equals(status)){
			recipe.setRecipe_status(false);
		}

		UploadPic(Pic);
		System.out.println(recipe.toString());
		rf.Insert(recipe);
		rf.add(recipe);
        //---------食譜材料集合產生-------------
		ArrayList<RecipeMaterial> RecipeMaterialSet= this.createRecipeMaterialSet(request);
		for(RecipeMaterial RM:RecipeMaterialSet){
			RfMaterial.Insert(RM);
			RfMaterial.add(RM);
			System.out.println("2. " + RM.toString());
		}
		System.out.println("食譜材料新增完畢");
		//--------------------------
		uploadMethod(MethodSet);
			
		request.setAttribute("reload", true);
		outer.println(
				"<div id='alert' style='position: fixed;top:2%;left: 40%;min-width: 20%;z-index: 99999;padding: 15px;border: 1px solid transparent;border-radius: 4px;text-align:center;color: #000000;background-color:#808080;'></div>");
		outer.println("<script type=\"text/javascript\">");
		outer.println("document.getElementById('alert').innerHTML='食譜加入成功';");
		outer.println("setTimeout(function(){location.href='index.jsp'},2000);");
		outer.println("</script>");

	}

	private void UploadPic(Part pic) throws IOException {
		String fileName = super.getFileNameByPart(pic);
		       fileName = rf.replaceFileName(fileName, cid);
		String path = ricPath+File.separator+fileName;
		super.writeFile(pic, path);
		recipe.setRecipe_picture("recipepic"+File.separator+fileName);
		System.out.println("1. RrcipePath=>" + (ricPath +File.separator +fileName));
		System.out.println("1. " + "recipe.setRecipe_picture-->" + (ricPath+File.separator+fileName));

	}

	private void uploadMethod(ArrayList<String> methodSet) throws SQLException {
		int MethodId = 1;
		for (String MethodDetail : methodSet) {
			rMethod = new RecipeMethod();
			rMethod.setMethod_detail(MethodDetail);
			rMethod.setRecipe_id(cid);
			rMethod.setMethod_id(String.format("m%03d", MethodId));
			MethodId++;
			RfMethod.Insert(rMethod);
			RfMethod.add(rMethod);
			System.out.println("3. " + rMethod.toString());
		}
	}
	
	private ArrayList<RecipeMaterial> createRecipeMaterialSet(HttpServletRequest request){
		ArrayList<RecipeMaterial> reciver = new ArrayList<RecipeMaterial>();
		int count = 0;
		while (!"".equals(request.getParameter("Material" + count))
				&& request.getParameter("Material" + count) != null) {
			String MaterialName = request.getParameter("Material" + count);
			String Amount = request.getParameter("Amount" + count);
			/*public RecipeMaterial(String recipe_id, 
			                      * String material_id, 
			                      * String material_name, 
			                      * String material_amount,
			                        String material_picture)*/
			rMaterial = new RecipeMaterial(cid,String.format("m%03d", count+1),
					                       MaterialName,Amount,"no Data");
			reciver.add(rMaterial);
			System.out.println("第 "+count+" 從網頁抓取資料產生食譜材料物件=>"+rMaterial.toString());
			count++;
		}
		return reciver;		
	}
	
}//--- class end
