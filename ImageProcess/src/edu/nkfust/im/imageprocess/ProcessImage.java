package edu.nkfust.im.imageprocess;

import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * ¶i¦æ¹ÏÀÉÃC¦â­°ºû©M´yÃ¸½ü¹ø
 * @author ³¯§ÓµØ
 */
public class ProcessImage {
	private String filePath = "";
	private String fileName = "";
	
	//©w¸q¶Â¦â©M¥Õ¦â¤§¹³¯À­ÈfinalÎª¹Ì¶¨±äÁ¿
	final int black = (255 << 24) | (0 << 16) | (0 << 8) | 0;
	final int white = (0 << 24) | (255 << 16) | (255 << 8) | 255;
	
	//©w¸qÃC¦â­°ºû°Ñ¼Æ,¨Ì­°ºû°Ñ¼Æ¶i¦æ¦â±m­°¶¥
	public int dimension = 64;
	
	//©w¸q®t²§«×ªùÂe­È,¨Ì®t²§«×ªùÂe­È¤ºªº¹³¯Àµø¬°¬Û¦PÃC¦â
	public int different = 5;
	
	/**
	 * «Øºc¤l
	 * @param filePath ¶}±Ò¹ÏÀÉ¤§ÀÉ®×¸ô®|
	 * @param fileName ¶}±Ò¹ÏÀÉ¤§ÀÉ®×¦WºÙ
	 */
	public ProcessImage(String filePath, String fileName) {
		this.filePath = filePath;
		this.fileName = fileName;
	}
	
	/**
	 * ¥Dµ{¦¡
	 * @throws IOException
	 */
	public void exec() throws IOException {
		BufferedImage img = null;
		BufferedImage output = null;
		
		/**
		 * ÐÂ½¨Â·¾¶´¢´æÁÙÊ±ÎÄ¼þ¼°Éú³ÉÍ¼Ïñ£¬¶ø²»¸Ä±äÔ­ÓÐµÄÍ¼Ïñ
		 * ÐèÒª½«ÎÄ¼þÂ·¾¶ÖÐµÄÀà±ðÃû¸´ÖÆ³öÀ´
		 * eg. 
		 * Ô´ÎÄ¼þÂ·¾¶£ºD:\duyanke888\º½±êÍ¼Ïñ·ÖÀà\DCZYFB
		 * ÁÙÊ±ÎÄ¼þÂ·¾¶£ºD:\duyanke888\º½±êÍ¼Ïñ·ÖÀà\Temporary @TemporaryPath
		 * Éú³ÉµÄÍ¼ÏñÎÄ¼þÂ·¾¶£ºD:\duyanke888\BuoyImage\DCZYFB @ImagePath
		 * 
		 * 1. ½âÎöÔ­Â·¾¶µÃµ½º½±êÀà±ð
		 * 2. ºÏ³ÉÁÙÊ±ÎÄ¼þÂ·¾¶ºÍÉú³ÉµÄÍ¼ÏñÎÄ¼þÂ·¾¶
		 * */
		String shangcengPath=getThirdLocation(filePath);
		//ÀàÐÍ
		String typePath=filePath.substring(filePath.lastIndexOf("\\",filePath.lastIndexOf("\\")-1)+1);
//		System.out.println("Ô­Ê¼Â·¾¶:"+filePath);
//		System.out.println("ÀàÐÍ:"+typePath);
//		System.out.println("dddddd:"+shangcengPath);
		String TemporaryPathString = shangcengPath+"\\Temporary";
		File TemporaryPath = new File(TemporaryPathString);
		if (!TemporaryPath.exists()){//Èô´ËÄ¿Â¼²»´æÔÚ£¬Ôò´´½¨Ö®
			TemporaryPath.mkdir();
//			System.out.println("´´½¨ÁÙÊ±Í¼ÏñÎÄ¼þ¼ÐÂ·¾¶Îª£º"+ TemporaryPathString);
		}
		String ImagePathshangcengString = shangcengPath+"\\º½±ê¼ÓÂÖÀªÍ¼Æ¬\\";
		File ImagePathshangceng = new File(ImagePathshangcengString);
		if (!ImagePathshangceng.exists()){//Èô´ËÄ¿Â¼²»´æÔÚ£¬Ôò´´½¨Ö®
			ImagePathshangceng.mkdir();
//			System.out.println("´´½¨ÎÄ¼þ¼ÐÂ·¾¶Îª£º"+ ImagePathshangcengString);
		}
		String ImagePathString = ImagePathshangceng+"\\"+typePath;
		File ImagePath = new File(ImagePathString);
		if (!ImagePath.exists()){//Èô´ËÄ¿Â¼²»´æÔÚ£¬Ôò´´½¨Ö®
			ImagePath.mkdir();
//			System.out.println("´´½¨ÎÄ¼þ¼ÐÂ·¾¶Îª£º"+ ImagePathString);
		}
		
		//¶i¦æ¦â±m­°¶¥	Í¼Æ¬½µÎ¬
		img = ReadWriteImage.readImage(filePath + fileName);
		output = reduceDimension(img);
		ReadWriteImage.writeImage(output, TemporaryPath +"\\"+ fileName+"_"+"reduce.png");
//		System.out.println("¤vÃ¸»s¦â±m­°¶¥µ²ªG¤§¹ÏÀÉ©ó" + filePath + "reduce.png");
		
		//¨Ì¦â±m­°¶¥µ²ªGÃ¸»s¿é¹ø	Í¼Æ¬½µÎ¬ºóµÄÂÖÀª
		output = getOutlineWithReduce(output);
		ReadWriteImage.writeImage(output, TemporaryPath +"\\"+ fileName+"_"+"outlineWithReduce.png");
//		System.out.println("¤v¨Ì¦â±m­°¶¥µ²ªGÃ¸»s¿é¹ø¤§¹ÏÀÉ©ó" + filePath + "outlineWithReduce.png");
		
		//¨ÌRBG®t²§Ã¸»s½ü¹ø	RGBÍ¼ÏñµÄÂÖÀª
		img = ReadWriteImage.readImage(filePath + fileName);
		output = getOutlineWithRGB(img);
//		ReadWriteImage.writeImage(output, filePath + fileName.subSequence(0, fileName.length()-4)+"_"+"outlineWithRGB.png");
		ReadWriteImage.writeImage(output, TemporaryPath +"\\"+ fileName +"_"+"outlineWithRGB.png");
//		System.out.println("¤v¨ÌRBG®t²§Ã¸»s½ü¹ø¤§¹ÏÀÉ©ó" + filePath + "outlineWithRGB.png");
		
		//±N½ü¹ø¥[¤J¦Ü­ì©l¹ÏÀÉ¤¤		½«RGBÍ¼ÏñÂÖÀªÓëÔ­Í¼½øÐÐºÏ³É
		img = ReadWriteImage.readImage(filePath + fileName);
		output = ReadWriteImage.readImage(TemporaryPath +"\\"+ fileName+"_"+"outlineWithRGB.png");
		output = generatImageWitheOutline(img, output);

		ReadWriteImage.writeImage(output, ImagePath +"\\"+ fileName + "imageWithOutline.png");
//		System.out.println("¤v±N½ü¹ø¥[¤J¦Ü­ì©l¹ÏÀÉ¤¤©ó" + filePath + "imageWithOutline.png");
		System.out.println("Êä³öÂÖÀªÍ¼Æ¬£º" + ImagePath +"\\"+ fileName + "imageWithOutline.png");
	}
	public static String getThirdLocation(String url){
		//indexÎª×îºóµÄ¡°/¡±×Ö·ûËùÔÚµÄÎ»ÖÃ
        int index=url.lastIndexOf(File.separator);
        //´Ó×îºóµÄ¡°/¡±×Ö·ûµÄÇ°Ò»¸öÎ»ÖÃÏòÇ°ÕÒ¡°/¡±µÄÎ»ÖÃÎª´Ëindex
        index=url.lastIndexOf(File.separator,index-1);
        //´Óµ¹ÊýµÚ¶þµÄ¡°/¡±×Ö·ûµÄÇ°Ò»¸öÎ»ÖÃÏòÇ°ÕÒ¡°/¡±µÄÎ»ÖÃÎª´Ëindex
        index=url.lastIndexOf(File.separator,index-1);
        //µÃµ½µ¹ÊýµÚÈý¸ö¡°/¡±Ö®ºóµÄ×Ö·û´®
        String location=url.substring(0,index+1);
        return location;
    }
	/**
	 * ¦â±m­°¶¥	Í¼Æ¬½µÎ¬
	 * @param orginal ­ì©l¹ÏÀÉBufferedImageª«¥ó	»º³åÍ¼Æ¬
	 * @return ¦â±m­°¶¥«á¹ÏÀÉ¤§BufferedImageª«¥ó	·µ»Ø»º³åÍ¼Æ¬
	 */
	private BufferedImage reduceDimension(BufferedImage orginal) {
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
				
				a = 255;
				//¨Ì­°ºû°Ñ¼Æ¶i¦æ¦â±m­°¶¥		
				r = (r / dimension) * dimension;
				g = (g / dimension) * dimension;
				b = (b / dimension) * dimension;
				
				//²£¥Í¹³¯À­È
				p = (a << 24) | (r << 16) | (g << 8) | b;
				img.setRGB(i, j, p);
			}
		}
		
		return img;
	}
	
	/**
	 * ¨Ì¦â±m­°¶¥µ²ªGÃ¸»s¿é¹ø	Í¼Æ¬½µÎ¬ºóµÄÂÖÀª
	 * @param orginal ­ì©l¹ÏÀÉBufferedImageª«¥ó
	 * @return ½ü¹ø¹ÏÀÉ¤§BufferedImageª«¥ó
	 */
	private BufferedImage getOutlineWithReduce(BufferedImage orginal) {
		BufferedImage img = orginal;
		
		//¨ú±o¹ÏÀÉ¤§ªø¼e
		int width = orginal.getWidth();
		int height = orginal.getHeight();
		
		final int black = (255 << 24) | (0 << 16) | (0 << 8) | 0;
		final int white = (255 << 24) | (255 << 16) | (255 << 8) | 255;
		int i, j;
		
		for(j = 0; j < height - 1; j++) {
			for(i = 0; i < width - 1; i++) {
				//¨ú±o¹³¯À­È
				int p = img.getRGB(i, j);
				//¨ú±o¥k¤è¹³¯À­È
				int right = img.getRGB(i + 1, j);
				//¨ú±o¤U¤è¹³¯À­È
				int down = img.getRGB(i, j + 1);
				
				//¦pªG¹³¯À­È»P¥k¤è¹³¯À­È¥B©M¤U¤è¹³¯À­È¬Û¦P,«h¥Nªí¸òÁ{ªñÃC¦â¬Û¦P,¬G«D½ü¹ø,³]©w¬°¥Õ¦â
				if(p == right && p == down) {
					p = white;
				}
				//¤Ï¤§,­Y¤£²Å¦X±ø¥ó,«h¥Nªí¬°½ü¹ø,³]©w¬°¶Â¦â
				else {
					p = black;
				}
				
				img.setRGB(i, j, p);
			}
			
			int left = img.getRGB(i - 1, j);
			img.setRGB(i, j, left);
		}
		
		return img;
	}
	
	/**
	 * ¨ÌRBG®t²§Ã¸»s½ü¹ø
	 * @param orginal ­ì©l¹ÏÀÉBufferedImageª«¥ó
	 * @return ½ü¹ø¹ÏÀÉ¤§BufferedImageª«¥ó
	 */
	private BufferedImage getOutlineWithRGB(BufferedImage orginal) {
		BufferedImage img = copyBufferedImage(orginal);		
		
		//¨ú±o¹ÏÀÉ¤§ªø¼e
		int width = orginal.getWidth();
		int height = orginal.getHeight();
		
		//³]©w¬°³z©ú­I´º
		Graphics2D g2d = img.createGraphics();//createGraphics»ùÓÚÍ¼Æ¬¶ÔÏó´ò¿ª»æÍ¼
		img = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);//ÉèÖÃÍ¸Ã÷±³¾°
		g2d.dispose();
		
		g2d = img.createGraphics();
		
		int i, j;
		for(j = 0; j < height - 1; j++) {
			for(i = 0; i < width - 1; i++) {
				//¨ú±o¹³¯À­È
				int p = orginal.getRGB(i, j);
				//¨ú±o¥k¤è¹³¯À­È
				int right = orginal.getRGB(i + 1, j);
				//¨ú±o¤U¤è¹³¯À­È
				int down = orginal.getRGB(i, j + 1);
				
				//¨ú±o¬õ¦â
				int r = (p >> 16) & 0xff;
				//¨ú±oºñ¦â
				int g = (p >> 8) & 0xff;
				//¨ú±oÂÅ¦â
				int b = p & 0xff;
				
				//¨ú±o¥k¤è¹³¯À­È¤§¬õ¦â
				int right_r = (right >> 16) & 0xff;
				//¨ú±o¥k¤è¹³¯À­È¤§ºñ¦â
				int right_g = (right >> 8) & 0xff;
				//¨ú±o¥k¤è¹³¯À­È¤§ÂÅ¦â
				int right_b = right & 0xff;
				
				//¨ú±o¤U¤è¹³¯À­È¤§¬õ¦â
				int down_r = (down >> 16) & 0xff;
				//¨ú±o¤U¤è¹³¯À­È¤§ºñ¦â
				int down_g = (down >> 8) & 0xff;
				//¨ú±o¤U¤è¹³¯À­È¤§ÂÅ¦â
				int down_b = down & 0xff;
				
				//¦pªG¹³¯À­È»P¥k¤è¹³¯À­È¥B©M¤U¤è¹³¯À­È³£¦b¥i±µ¨ü¤§®t²§ªùÂe­È¤º,«h¥Nªí¸òÁ{ªñÃC¦â¬Û¦ü,¬G«D½ü¹ø,³]©w¬°¥Õ¦â
				if(r > right_r - different && r < right_r + different && g > right_g - different && g < right_g + different && b > right_b - different && b < right_b + different && r > down_r - different && r < down_r + different && g > down_g - different && g < down_g + different && b > down_b - different && b < down_b + different) {
					p = white;
				}
				//¤Ï¤§,­Y¤£²Å¦X±ø¥ó,«h¥Nªí¬°½ü¹ø,³]©w¬°¶Â¦â
				else {
					p = black;
				}
				
				img.setRGB(i, j, p);
			}
			
			int left = img.getRGB(i - 1, j);
			img.setRGB(i, j, left);
		}
		
		return img;
	}
	
	/**
	 * ±N½ü¹ø¥[¤J¦Ü­ì©l¹ÏÀÉ
	 * @param orginal ­ì©l¹ÏÀÉBufferedImageª«¥ó
	 * @param outline ½ü¹ø¹ÏÀÉBufferedImageª«¥ó
	 * @return ¦X¦¨«áBufferedImageª«¥ó
	 */
	private BufferedImage generatImageWitheOutline(BufferedImage orginal, BufferedImage outline) {
		BufferedImage img = orginal;
		
		//¨ú±o¹ÏÀÉ¤§ªø¼e
		int width = orginal.getWidth();
		int height = orginal.getHeight();
		
		for(int j = 0; j < height; j++) {
			for(int i = 0; i < width; i++) {
				//¨ú±o­ì©l¹ÏÀÉ¹³¯À­È
				int p = img.getRGB(i, j);
				//¨ú±o¿é¹ø¹ÏÀÉ¹³¯À­È
				int check = outline.getRGB(i, j);
				
				//¦pªG¿é¹ø¹ÏÀÉ¹³¯À­È¬°¶Â¦â¥Nªí¬°½ü¹ø,¬G³]©w¹³¯À­È¬°¶Â¦â
				if(check == black) p = black;
				img.setRGB(i, j, p);
			}
		}
		
		return img;
	}
	
	/**
	 * ½Æ»s¹ÏÀÉBufferedImageª«¥ó
	 * @param oldBufferedImage ­ì©l¹ÏÀÉBufferedImageª«¥ó
	 * @return ½Æ»s«á¹ÏÀÉBufferedImageª«¥ó
	 */
	private BufferedImage copyBufferedImage(BufferedImage oldBufferedImage) {
		BufferedImage newBufferedImage = null;
		
		//¨ú±o¹ÏÀÉ¤§ªø¼e
		int width = oldBufferedImage.getWidth();
		int height = oldBufferedImage.getHeight();
		
		//±N­ì©l¹ÏÀÉ¤º®eÃ¸»s©ó·sªºBufferedImageª«¥ó
		newBufferedImage = new BufferedImage(width, height, oldBufferedImage.getType());
	    Graphics2D g2d = newBufferedImage.createGraphics();
	    g2d.drawImage(oldBufferedImage, 0, 0, null);
	    g2d.dispose();
		
		return newBufferedImage;
	}
}//end class ProcessImage
