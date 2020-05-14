package edu.nkfust.im.imageprocess;

import java.io.File;
import java.util.Scanner;

/**
 * 紇钩矪瞶祘Α
 * @author 朝в地
 */
public class Main {	
	/**
	 * 祘Α
	 */
	public static void main(String args[]) {
		//训练图片所在的地址
		String fileAbsolutePath = "D:\\duyanke888\\航标图像分类";
		int dimension = 128;//尺度
		int different = 16;
		File file = new File(fileAbsolutePath);
		File[] lblist = file.listFiles();
		for(int i=0;i<lblist.length;i++) {
			if (lblist[i].isDirectory()){
				File pic = new File(lblist[i].getAbsolutePath());//getAbsolutePath()返回的是定义时的路径对应的相对路径
				File[] pics = pic.listFiles();//java.io.File.listFiles() 返回抽象路径名的定义中表示此抽象路径名的目录中的文件的数组
				for(int j=0;j<pics.length;j++) {
					String picAbsolutePath = pics[j].getAbsolutePath();//每一张图片的绝对路径
					//瓜郎隔畖
					String filePath = picAbsolutePath.substring(0, picAbsolutePath.lastIndexOf("\\") + 1);
					String fileName = picAbsolutePath.substring(picAbsolutePath.lastIndexOf("\\") + 1);
					
					try {
						//ㄌRGB︹だ玻ネ虫肅︹瓜郎	生成RGB文件		
						GenerateRGBImage generator = new GenerateRGBImage(filePath, fileName);
						generator.exec();
						
						//秈︽瓜郎肅︹蝴㎝磞酶近锅		生成过程图
						ProcessImage processor = new ProcessImage(filePath, fileName);
						processor.dimension = dimension;
						processor.different = different;
						processor.exec();
					}
					catch(Exception e) {
						//岿粇癟
						System.out.println(e.getMessage());
					}
				}//end 某张图
			}//end if 某一类
		}//end for
		
        
//		//瓜郎隔畖
//		String filePath = fileAbsolutePath.substring(0, fileAbsolutePath.lastIndexOf("\\") + 1);
//		String fileName = fileAbsolutePath.substring(fileAbsolutePath.lastIndexOf("\\") + 1);
//		
//		try {
//			//ㄌRGB︹だ玻ネ虫肅︹瓜郎
//			GenerateRGBImage generator = new GenerateRGBImage(filePath, fileName);
//			generator.exec();
//			
//			//秈︽瓜郎肅︹蝴㎝磞酶近锅
//			ProcessImage processor = new ProcessImage(filePath, fileName);
//			processor.dimension = dimension;
//			processor.different = different;
//			processor.exec();
//		}
//		catch(Exception e) {
//			//岿粇癟
//			System.out.println(e.getMessage());
//		}
	}
}