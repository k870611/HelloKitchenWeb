package Utility;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class PressPicture {

	    
	    private BufferedImage image;
		private int imgWidth;
		private int imgHeight;
	
	public PressPicture(InputStream ins) throws IOException{
		image = ImageIO.read(ins);
		imgWidth = image.getWidth();
		imgHeight = image.getHeight();
	}
	public void reSize(int width,int height,String path) throws IOException{
		if(imgWidth/imgHeight > width/height){
			reSizeDependsWidth(width,path);
		}else{
			reSizeDependsHeight(height,path);
		}
	}
	private void reSizeDependsHeight(int y,String path) throws IOException{
    	int x = (int)(imgWidth * y / imgHeight) ;
    	System.out.printf("SelectHeight x %d y %d\n",x,y);
    	processShrink(x,y,path);
	}
	
	private void reSizeDependsWidth(int x,String path) throws IOException{
		int y = (int)(imgHeight * x / imgWidth);
		System.out.printf("SelectWidth x %d y %d\n",x,y);
		processShrink(x,y,path);
		
	}
    private void processShrink(int x, int y,String outFilePath) throws IOException {
		// TODO Auto-generated method stub
    	BufferedImage img = new BufferedImage(x, y,image.getType());   
        Graphics2D g2d = img.createGraphics();
        g2d.drawImage(image, 0, 0, x, y, null); 
        g2d.dispose();
        File destFile = new File(outFilePath);  
        String formatName = outFilePath.substring(outFilePath.lastIndexOf(".") + 1);
        ImageIO.write(img, formatName, destFile);
        
		
	}
	
}//--class end
