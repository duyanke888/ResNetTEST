package edu.nkfust.im.imageprocess;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * ¨ÌRGB¤T­ì¦â¤À§O²£¥Í³æ¤@ÃC¦â¹ÏÀÉ
 * @author ³¯§ÓµØ
 */
public class GenerateRGBImage {
	private String filePath = "";
	private String fileName = "";
	
	/**
	 * «Øºc¤l
	 * @param filePath ¶}±Ò¹ÏÀÉ¤§ÀÉ®×¸ô®|
	 * @param fileName ¶}±Ò¹ÏÀÉ¤§ÀÉ®×¦WºÙ
	 */
	public GenerateRGBImage(String filePath, String fileName) {
		this.filePath = filePath;
		this.fileName = fileName;
	}

	/**
	 * ¥Dµ{¦¡
	 * @throws IOException ¶}±Ò©MÀx¦sÀÉ®×¿ù»~¨Ò¥~¨Æ¥ó		@ throws IOExceptionÎª¿ÉÄÜ»áÅ×³öÒì³£µÄÉùÃû
	 */
	public void exec() throws IOException {
		BufferedImage img = null;//Ô­Ê¼Í¼Æ¬»º³å
		BufferedImage output = null;//Êä³öÍ¼Æ¬»º³å
		
		//ÒÔÏÂ6¶ÎÓï¾ä·Ö±ð¶ÔºìÂÌÀ¶ÈýÖÖÑÕÉ«¹²6ÖÖ£¨È¥³ýÈ«trueºÍÈ«flaseÁ½ÖÖÇé¿ö£©Çé¿ö½øÐÐ½âÎö
		
		//²£¥Í¬õ¦â¤§¹ÏÀÉ
		img = ReadWriteImage.readImage(filePath + fileName);//µÃµ½Ä³Ò»ÕÅÍ¼Æ¬¼ÓÈë»º³å
		output = getRGBImage(img, true, false, false);
//		ReadWriteImage.writeImage(output, filePath + "r.png");
//		System.out.println("¤vÃ¸»s¬õ¦â¤§¹ÏÀÉ©ó" + filePath + "r.png");
		
		//²£¥Íºñ¦â¤§¹ÏÀÉ
		img = ReadWriteImage.readImage(filePath + fileName);
		output = getRGBImage(img, false, true, false);
//		ReadWriteImage.writeImage(output, filePath + "g.png");
//		System.out.println("¤vÃ¸»sºñ¦â¤§¹ÏÀÉ©ó" + filePath + "g.png");
		
		//²£¥ÍÂÅ¦â¤§¹ÏÀÉ
		img = ReadWriteImage.readImage(filePath + fileName);
		output = getRGBImage(img, false, false, true);
//		ReadWriteImage.writeImage(output, filePath + "b.png");
//		System.out.println("¤vÃ¸»sÂÅ¦â¤§¹ÏÀÉ©ó" + filePath + "b.png");
		
		//²£¥Í¬õ¦â+ºñ¦â(¶À¦â)¤§¹ÏÀÉ
		img = ReadWriteImage.readImage(filePath + fileName);		
		output = getRGBImage(img, true, true, false);
//		ReadWriteImage.writeImage(output, filePath + "r+g.png");
//		System.out.println("¤vÃ¸»s¬õ¦â+ºñ¦â(¶À¦â)¤§¹ÏÀÉ©ó" + filePath + "r+g.png");
		
		//²£¥Í¬õ¦â+ÂÅ¦â(µµ¦â)¤§¹ÏÀÉ
		img = ReadWriteImage.readImage(filePath + fileName);		
		output = getRGBImage(img, true, false, true);
//		ReadWriteImage.writeImage(output, filePath + "r+b.png");
//		System.out.println("¤vÃ¸»s¬õ¦â+ÂÅ¦â(µµ¦â)¤§¹ÏÀÉ©ó" + filePath + "r+b.png");
		
		//²£¥Íºñ¦â+ÂÅ¦â(ÂÅºñ¦â)¤§¹ÏÀÉ
		img = ReadWriteImage.readImage(filePath + fileName);		
		output = getRGBImage(img, false, true, true);
//		ReadWriteImage.writeImage(output, filePath + "g+b.png");
//		System.out.println("¤vÃ¸»sºñ¦â+ÂÅ¦â(ÂÅºñ¦â)¤§¹ÏÀÉ©ó" + filePath + "g+b.png");
		
		//µÃµ½»Ò¶ÈÍ¼Ïñ
		//²£¥Í¦Ç¶¥¤§¹ÏÀÉ
		img = ReadWriteImage.readImage(filePath + fileName);		
		output = getGrayImage(img);
//		ReadWriteImage.writeImage(output, filePath + "gray.png");
//		System.out.println("¤vÃ¸»s¦Ç¶¥¤§¹ÏÀÉ©ó" + filePath + "gray.png");
	}
	
	/**
	 * ¨ÌRGB¤T­ì¦â¤À§O²£¥Í³æ¤@ÃC¦â¹ÏÀÉ
	 * @param orginal ­ì©l¹ÏÀÉ¤§BufferedImageª«¥ó
	 * @param isRed ¬O§_Åã¥Ü¬õ¦â
	 * @param isGreen ¬O§_Åã¥Üºñ¦â
	 * @param isBlue ¬O§_Åã¥ÜÂÅ¦â
	 * @return
	 */
	/**
	 * µÃµ½RGBÍ¼Æ¬
	 * @param orginal ­ì»º³åÎÄ¼þ
	 * @param isRed ºìÉ«
	 * @param isGreen ÂÌÉ«
	 * @param isBlue À¶É«
	 * */
	private BufferedImage getRGBImage(BufferedImage orginal, boolean isRed, boolean isGreen, boolean isBlue) {
		BufferedImage img = orginal;
		
		//¨ú±o¹ÏÀÉ¤§ªø¼e
		int width = orginal.getWidth();
		int height = orginal.getHeight();
		
		for(int j = 0; j < height; j++) {
			for(int i = 0; i < width; i++) {
				//¨ú±o¹³¯À­È
				int p = img.getRGB(i, j);
				
				//  >> ½«ÔËËã·û×ó±ßµÄÔËËã¶ÔÏóÏòÓÒÒÆ¶¯ÔËËã·ûÓÒ²àÖ¸¶¨µÄÎ»Êý
				//  &0xFFÊÇÔõÃ´¸öÒâË¼ https://blog.csdn.net/zhaowei5210/article/details/70920711
				//¨ú±o³z©ú«×
				int a = (p >> 24) & 0xff;
				//¨ú±o¬õ¦â
				int r = (p >> 16) & 0xff;
				//¨ú±oºñ¦â
				int g = (p >> 8) & 0xff;
				//¨ú±oÂÅ¦â
				int b = p & 0xff;
				
				a = 255;
				if(isRed == false) r = 0;
				if(isGreen == false) g = 0;
				if(isBlue == false) b = 0;
				
				//²£¥Í¹³¯À­È
				p = (a << 24) | (r << 16) | (g << 8) | b;
				img.setRGB(i, j, p);
			}
		}
		
		return img;
	} //end getRGBImage
	
	private BufferedImage getGrayImage(BufferedImage orginal) {
		BufferedImage img = orginal;
		
		//¨ú±o¹ÏÀÉ¤§ªø¼e
		int width = orginal.getWidth();
		int height = orginal.getHeight();
		
		for(int j = 0; j < height; j++) {
			for(int i = 0; i < width; i++) {
				//¨ú±o¹³¯À­È
				int p = img.getRGB(i, j);
				
				//¨ú±o³z©ú«×
				int a = (p >> 24) & 0xff;
				//¨ú±o¬õ¦â
				int r = (p >> 16) & 0xff;
				//¨ú±oºñ¦â
				int g = (p >> 8) & 0xff;
				//¨ú±oÂÅ¦â
				int b = p & 0xff;
				
				int mean = (r + b + g) / 3;
				r = mean;
				g = mean;
				b = mean;
				
				//²£¥Í¹³¯À­È
				p = (a << 24) | (r << 16) | (g << 8) | b;
				img.setRGB(i, j, p);
			}
		}
		
		return img;
	} // end getGrayImage
}//end class GenerateRGBImage
