package com.lemon.app.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;


import com.lemon.app.Base;




public class ScreenUtil {
   public static File takeScreenShot(String dir){
	   Date date=new Date();
	   long time =date.getTime();
		   File from =Base.androidDriver.getScreenshotAs(OutputType.FILE);
		   File to =new File(dir+File.separator+time+".jpg");
		   try {
			FileUtils.copyFile(from, to);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   return to;
   }
}
