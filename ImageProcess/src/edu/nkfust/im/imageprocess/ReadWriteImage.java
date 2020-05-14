package edu.nkfust.im.imageprocess;

import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 開啟和儲存圖檔
 * @author 陳志華
 */
public class ReadWriteImage {
	
	/**
	 * 讀取圖檔
	 * @param filePathName 欲開啟圖檔之檔名資訊
	 * @return 回傳圖檔BufferedImage物件
	 * @throws IOException 讀檔錯誤例外事件
	 */
	public static BufferedImage readImage(String filePathName) throws IOException {
		BufferedImage img = null;
		File file = null;

		//開啟圖檔
		file = new File(filePathName);
		img = ImageIO.read(file);
		
		return img;
	}
	
	/**
	 * 
	 * @param img 欲儲存之圖檔BufferedImage物件
	 * @param filePathName 欲儲存圖檔之檔名資訊
	 * @throws IOException 存檔錯誤例外事件
	 */
	public static void writeImage(BufferedImage img, String filePathName) throws IOException {
		File file = null;
		
		//儲存圖檔
		file = new File(filePathName);
		ImageIO.write(img, "png", file);
	}
}
