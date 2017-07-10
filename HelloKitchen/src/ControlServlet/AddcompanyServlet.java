package ControlServlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
 * Servlet implementation class AddcompanyServlet
 */
@WebServlet("/AddcompanyServlet")
@MultipartConfig
public class AddcompanyServlet extends FileManipulateServlet {
	private static final long serialVersionUID = 1L;

	private String comlogoPath = "/comlogo";
	private String comcoverPath = "/comcover";
	private String compicPath = "/compic";
	//private String filepath_part2 = "C:\\DataSource\\workspace\\HelloKitchen0623\\WebContent\\comlogo";
	//private String filepath_part3 = "C:\\DataSource\\workspace\\HelloKitchen0623\\WebContent\\comcover";
	//private String filepath_part4 = "C:\\DataSource\\workspace\\HelloKitchen0623\\WebContent\\compic";
	private CompanyFactory cf ;
	private Company c; //-- 物件
	private CompanyPictures cp; //--物件
	private CompanyPictureFactory cpf ;
	ArrayList<Part> picSet ;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddcompanyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processAddcompany(request,response);
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
			cf = (CompanyFactory) this.getServletContext().getAttribute(GeneralVarName.Web_CompanyFactory);
			cpf = (CompanyPictureFactory) this.getServletContext().getAttribute(GeneralVarName.Web_CompanyPictureFactory);
			this.configPath(); //---初始化路徑
			
			processAddcompany(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processAddcompany(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter outer = response.getWriter();
      		
		String Password=request.getParameter("password");
		String Email=request.getParameter("email");
		String Description=request.getParameter("description");
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
		
		//---------------------------
		c = new Company();
		
	//	UploadLogo(logo);
		//------ logo區 -------
		String logoName = super.getFileNameByPart(logo);//--原始檔名
		logoName = cf.replaceFileName(logoName, cf.IDGenerator());//--重製檔名
		super.writeFile(logo, this.comlogoPath+File.separator+logoName);//寫檔
		c.setCompany_logo("comlogo"+File.separator+logoName);
		System.out.println("1."+comlogoPath+("->comlogo"+File.separator+logoName));
		//----------------------------------
		//UploadCover(cover);
	//-------- cover區 --------------
		String coverName = super.getFileNameByPart(cover);//--原始檔名
		coverName = cf.replaceFileName(coverName, cf.IDGenerator());//--重製檔名
		super.writeFile(cover, comcoverPath+File.separator+coverName);
		c.setCompany_cover("comcover"+File.separator+coverName);
		System.out.println("2."+comcoverPath+("->comcover"+File.separator+coverName));
	//------------------------------------------------------------	
		c.setCompany_id(Email);//--??
		c.setCompany_email(Email);
		c.setCompany_password(Password);
		c.setCompany_intro(Description);
		c.setCompany_name(CompanyName);
		c.setCompany_owner(Owner);
		c.setCompany_address(Address);
		c.setCompany_tel(Tel);
		System.out.println(c.toString());		
		cf.Insert(c);
		cf.add(c);
		//-----------------------------------
		/*
		UploadPic(pic1);
		UploadPic(pic2);
		UploadPic(pic3);
		UploadPic(pic4);
		UploadPic(pic5);
		*/
		int count = 1;
		for(Part p:picSet){
			/*CompanyPictures(String picture_id,
			 *                String company_id,
			 *                String picture_path, 
			 *                String picture_name,
			                  String picture_description)*/
			String picName = super.getFileNameByPart(p);
			if(!"".equals(picName) && p.getContentType()!=null){
				cp = new CompanyPictures(null,Email,null,"noData","noData");//-->???
				picName = cpf.replaceFileName(picName,cpf.IDGenerator());
				super.writeFile(p, compicPath+File.separator+picName);
				cp.setPicture_id(String.format("p%03d", count));
				cp.setPicture_path("compic"+File.separator+picName);
				cpf.Insert(cp);
				cpf.add(cp);
				System.out.println("3."+compicPath+("->compic"+File.separator+picName));
				count++;
 			}
		}
		outer.println("<div id='alert' style='position: fixed;top:2%;left: 40%;min-width: 20%;z-index: 99999;padding: 15px;border: 1px solid transparent;border-radius: 4px;text-align:center;color: #000000;background-color:#e5e5e5;'></div>");
		outer.println("<script type=\"text/javascript\">");
		outer.println("document.getElementById('alert').innerHTML='廠商加入成功';");
		outer.println("setTimeout(function(){location.href='index.jsp'}, 2000 );");
		outer.println("</script>");
		//request.getRequestDispatcher("index.jsp").forward(request, response);
		
		
//		LinkedList<Part> myParts = new LinkedList<Part>();
//		for(int i=0;i<=4;i++){
//			if(pic[i]!=null){
//				myParts.add(pic[i]);
//			}
//		}
//		System.out.println(myParts.toString());
//		
//		for(Part part: myParts){
//			String headerValue = part.getHeader("Content-Disposition");
//			String fileName =headerValue.substring(headerValue.indexOf("filename=\"")+10, headerValue.lastIndexOf("\""));
//
//			in=part.getInputStream();
//			out=new FileOutputStream(filepath_part1+filepath_part4+fileName);
//		    if(!"".equals(fileName) && part.getContentType()!=null){
//		    	buffer = new byte[1024];
//				length = -1;
//				while((length = in.read(buffer))!=-1)
//				{
//					out.write(buffer, 0, length);
//				}
//		    }
//		}			System.out.println("Multiple Files Upload Success !!<br/>");
//	
	}

	/*private void UploadLogo(Part logo) throws IOException {
		String logoheader = logo.getHeader("Content-Disposition");
		String logoname = logoheader.substring(logoheader.indexOf("filename=\"")+10,logoheader.lastIndexOf("\""));
		//String logoPath= this.getServletContext().getRealPath(this.filepath_part2);
		
		if(!"".equals(logoname) && logo.getContentType()!=null){
			
			String logoFileName = cf.replaceFileName(logoname, Id);
	
			in = logo.getInputStream();
		//	out = new FileOutputStream(logoPath+File.separator+logoFileName);
			out = new FileOutputStream(filepath_part2+File.separator+logoFileName);
			buffer = new byte[1024];
			length = -1;
			while((length = in.read(buffer))!=-1)
			{
				out.write(buffer, 0, length);
			}	
			in.close();
			out.close();
			c.setCompany_logo("comlogo"+File.separator+logoFileName);
			System.out.println("1."+filepath_part2+("->comlogo"+File.separator+logoFileName));
		}
	}*/
/*
	private void UploadCover(Part cover) throws IOException {
		String covorheader=cover.getHeader("Content-Disposition");
		String covorname=covorheader.substring(covorheader.indexOf("filename=\"")+10,covorheader.lastIndexOf("\""));
		//String coverPath = this.getServletContext().getRealPath(filepath_part3);
		
		if(!"".equals(covorname) && cover.getContentType()!=null){
			String covorFileName = cf.replaceFileName(covorname, Id);
			in=cover.getInputStream();
			//out=new FileOutputStream(coverPath+File.separator+covorFileName);
			out=new FileOutputStream(filepath_part3+File.separator+covorFileName);
			buffer = new byte[1024];
			length = -1;
			while((length = in.read(buffer))!=-1)
			{
				out.write(buffer, 0, length);
			}

			in.close();
			out.close();
			c.setCompany_cover("comcover"+File.separator+covorFileName);
			System.out.println("2."+filepath_part3+("->comcover"+File.separator+covorFileName));
		}
	}
*/
	/*
	private void UploadPic(Part part) throws IOException, SQLException {
		String header = part.getHeader("Content-Disposition");
		String filename = header.substring(header.indexOf("filename=\"")+10,header.lastIndexOf("\""));
		String picID  = cpf.IDGenerator();
		while(true){
			if(currentpid.contains(picID)){
				picID = cpf.IDGenerator();
			}else{
				currentpid.add(picID);
				break;
			}
		}

		if(!"".equals(filename) && part.getContentType()!=null){
			String picFileName = cpf.replaceFileName(filename, picID);
			//String picPath = this.getServletContext().getRealPath(filepath_part4);
			InputStream in = part.getInputStream();
			//out = new FileOutputStream(picPath+File.separator+picFileName);
			out = new FileOutputStream(filepath_part4+File.separator+picFileName);
				byte[] buffer = new byte[1024];
				int length = -1;
				while((length = in.read(buffer))!=-1)
				{
					out.write(buffer, 0, length);
				}
				in.close();
				out.close();
				 cp = new CompanyPictures();
				 cp.setPicture_id("p"+picID);
				 cp.setCompany_id(Id);
				 cp.setPicture_path("compic"+File.separator+picFileName);
				 System.out.println("3."+filepath_part4+("->compic"+File.separator+picFileName));
				 cpf.Insert(cp);
				 cpf.add(cp);
		 }

	}*/
	private void configPath(){
		comlogoPath = super.getWebContentRealPath("comlogo");
		comcoverPath =super.getWebContentRealPath("comcover");
		compicPath =super.getWebContentRealPath("compic");
	}
}//--- class end
