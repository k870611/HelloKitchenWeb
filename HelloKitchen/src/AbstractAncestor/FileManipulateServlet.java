package AbstractAncestor;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Utility.PressPicture;
/**
 * Servlet implementation class FileManipulateServlet
 */
@WebServlet("/FileManipulateServlet")
public class FileManipulateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FileManipulateServlet() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * 刪除檔案
	 * 
	 * @param filePathAndName
	 *            檔路徑及名稱 如c:/fqf.txt
	 * @return boolean
	 */
	protected boolean delFile(String filePathAndName) throws IOException {
		String fileName = filePathAndName.toString();
		boolean isDel;
		java.io.File delfile = new java.io.File(fileName);
		System.out.println("欲刪除-檔案路徑:" + fileName + " 是否存在: " + delfile.exists());
		isDel = delfile.delete();
		return isDel;
	}

	protected String getWebContentRealPath(String dir) {
		String WebPath = this.getServletContext().getRealPath(File.separator + dir);
		return WebPath;
	}

	/**/
	protected void writeFile(final Part part, String fileDirAndName) throws IOException {
		if (part.getSize() <= 0) {
			return; // --無檔案
		}
		InputStream ios = part.getInputStream();
		int mb = ios.available() / 1024 / 1024;
		if (mb >= 1) {
			System.out.println("圖片壓縮開始");
			PressPicture pp = new PressPicture(ios);
			pp.reSize(800, 800, fileDirAndName);
			System.out.println("圖片壓縮結束");
			ios.close();
		} else {
			byte[] buffers = new byte[ios.available()];
			ios.read(buffers);// --位元組讀入暫時存放
			FileOutputStream fos = new FileOutputStream(fileDirAndName);
			fos.write(buffers);// --寫出該Stream
			System.out.println("檔案存放: " + fileDirAndName);
			ios.close();
			fos.close();
		}
	}

	protected String getFileNameByPart(final Part part) {
		String filename = "";
		if (part.getSize() <= 0) {
			return filename;
		}
		String header = part.getHeader("Content-Disposition");
		filename = header.substring(header.indexOf("filename=\"") + 10, header.lastIndexOf("\""));
		return filename;
	}

	protected void createDir(String path) {
		File myFile = new File(path);
		if (myFile.exists()) {
			System.out.println("路徑存在");
		} else {
			myFile.mkdirs();
			System.out.println("路徑創建-->" + path);
		}
	}

	/*Android 圖片上傳*/
	protected void androidRecipePictureUpload(String jsonString, String recipeId, String filePath) throws IOException {
		String recipeImgBytesBase64 = jsonString;
		byte[] recipeImgBytes = Base64.getDecoder().decode(recipeImgBytesBase64);
		BufferedImage recipeImg = ImageIO.read(new ByteArrayInputStream(recipeImgBytes));
		File outputfile = new File(filePath , recipeId + ".jpg");
		ImageIO.write(recipeImg, "jpg", outputfile);
	}
    /*資料庫檔案路徑的檔名抽取*/
	protected String extraFlieName(String databaseFilePath) {
		String[] sp = databaseFilePath.split("/");
		String last = sp[sp.length - 1];
		return last;
	}
	
	
}/// -- class end
