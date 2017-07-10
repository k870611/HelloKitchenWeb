package ControlServlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import AbstractAncestor.FileManipulateServlet;
import EntityComponents.Company;
import EntityComponents.CompanyPictures;
import Factorys.CompanyFactory;
import Factorys.CompanyPictureFactory;
import Utility.GeneralVarName;

/**
 * Servlet implementation class UpdateCompanyServlet
 */
@WebServlet("/UpdateCompanyServlet")
@MultipartConfig
public class UpdateCompanyServlet extends FileManipulateServlet {
	private static final long serialVersionUID = 1L;
	private String comlogoPath = "/comlogo";
	private String comcoverPath = "/comcover";
	private String compicPath = "/compic";
	private CompanyFactory cf;
	private CompanyPictureFactory cpf;
	private CompanyPictures cp;
	private ArrayList<Part> picSet;
	private String filename;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			UpdateCompany(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		cf = (CompanyFactory) this.getServletContext().getAttribute(GeneralVarName.Web_CompanyFactory);
		cpf = (CompanyPictureFactory) this.getServletContext().getAttribute(GeneralVarName.Web_CompanyPictureFactory);
		this.configPath();
		try {
			UpdateCompany(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void UpdateCompany(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
      		
		String Password=request.getParameter("password");
		String Email = request.getParameter("email");//id
		String Description=request.getParameter("Detail");
		String CompanyName=request.getParameter("CompanyName");
		String Owner=request.getParameter("Owner");
		String Address=request.getParameter("Address");
		String Tel=request.getParameter("Tel");
		

		
		Part logo =request.getPart("logo");
		Part cover=request.getPart("cover");
		picSet = new ArrayList<Part>();
		/*Part pic1=request.getPart("pic1");
		Part pic2=request.getPart("pic2");
		Part pic3=request.getPart("pic3");
		Part pic4=request.getPart("pic4");
		Part pic5=request.getPart("pic5");*/
		picSet.add(request.getPart("pic1"));
		picSet.add(request.getPart("pic2"));
		picSet.add(request.getPart("pic3"));
		picSet.add(request.getPart("pic4"));
		picSet.add(request.getPart("pic5"));
		
		Company oldcom = (Company)cf.sreachByPK(Email);
		
//		UploadLogo(logo);
//------ logo區 -------
		
		if (logo.getSize() > 0) {
			System.out.println("更新圖片開始");
			if (oldcom.getCompany_logo().equals("noData")) {
				filename = super.getFileNameByPart(logo);
				filename = cf.replaceFileName(filename, cf.IDGenerator());
				super.writeFile(logo, comlogoPath + File.separator + filename);
			} else {
				filename = super.extraFlieName(oldcom.getCompany_logo());
				System.out.println("update comlogoPath:"+comlogoPath + File.separator + filename);
				super.writeFile(logo, comlogoPath + File.separator + filename);
			}
		}	
//----------------------------------
//UploadCover(cover);
//-------- cover區 --------------
		if (cover.getSize() > 0) {
			System.out.println("更新圖片開始");
			if (oldcom.getCompany_cover().equals("noData")) {
				filename = super.getFileNameByPart(cover);
				filename = cf.replaceFileName(filename, cf.IDGenerator());
				super.writeFile(cover, comcoverPath + File.separator + filename);
			} else {
				filename = super.extraFlieName(oldcom.getCompany_cover());
				System.out.println("update comcoverPath:"+comcoverPath + File.separator + filename);
				super.writeFile(cover, comcoverPath + File.separator + filename);
			}
		}		
//------------------------------------------------------------

		//Company(String company_id, String company_name, String company_logo, String company_cover,String company_intro, String company_address, String company_tel, String company_email,String company_owner, String company_password, boolean company_status)
		Company newcom = new Company(Email,CompanyName,oldcom.getCompany_logo(),oldcom.getCompany_cover(),Description,Address,Tel,Email,Owner,Password,true);
		
		cf.Update(oldcom, newcom);		
		System.out.println("After Update"+newcom.toString());
		
		int count = 0;
		
		for(Part p:picSet){
			ArrayList<CompanyPictures>ALcp=(ArrayList<CompanyPictures>)cpf.sreachByPK(Email);
			if(p.getInputStream().available() <= 0){
				//--不做
				System.out.println("true串流大小"+p.getSize());
				continue;
			}else {
				System.out.println("false串流大小"+p.getSize());
				if(count < ALcp.size()){
					//-- 更新 --
					CompanyPictures oldObject = ALcp.get(count);
					CompanyPictures newObject = new CompanyPictures(oldObject.getPicture_id(),
							Email,oldObject.getPicture_path(),oldObject.getPicture_name(),oldObject.getPicture_description());
				    cpf.Update(oldObject, newObject);
				    cpf.refresh(newObject);
					String filename = super.extraFlieName(oldObject.getPicture_path());
					super.writeFile(p, compicPath+File.separator+filename);
					System.out.println("公司更新圖片: "+compicPath+File.separator+filename);
				}else{
					//--- 新增 ---
					String pid= String.format("p%03d", count+1);
					String fileName = super.getFileNameByPart(p); //--原始
					System.out.println("original  file Name :"+fileName);
					fileName = cpf.replaceFileName(fileName, cpf.IDGenerator());
					System.out.println("insert File Name By Replace: "+fileName);
					CompanyPictures newObject = new CompanyPictures(pid,Email,"compic"+File.separator+fileName,
							"noData","noData");
					cpf.Insert(newObject);
					cpf.add(newObject);
					super.writeFile(p, compicPath+File.separator+fileName);
					System.out.println("公司新增圖片: "+compicPath+File.separator+fileName);
				}
				count++;
			}
		}
		
		/*CompanyPictures(String picture_id,
		 *                String company_id,
		 *                String picture_path, 
		 *                String picture_name,
		                  String picture_description)*/
		
		
		out.println("<div id='alert' style='position: fixed;top:2%;left: 40%;min-width: 20%;z-index: 99999;padding: 15px;border: 1px solid transparent;border-radius: 4px;text-align:center;color: #000000;background-color:#808080;'></div>");
		out.println("<script type=\"text/javascript\">");
		out.println("document.getElementById('alert').innerHTML='檔案更新成功';");
		out.println("setTimeout(function(){location.href='index.jsp'}, 2000 );");
		out.println("</script>");
		
	}
	private void configPath(){
		comlogoPath = super.getWebContentRealPath("comlogo");
		comcoverPath =super.getWebContentRealPath("comcover");
		compicPath =super.getWebContentRealPath("compic");
	}

}
