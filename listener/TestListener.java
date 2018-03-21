package com.lemon.phoenix.listener;

import java.io.File;
import java.util.Date;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.lemon.phoenix.util.DateUtil;
import com.lemon.phoenix.util.ScreenUtil;

public class TestListener extends TestListenerAdapter {
    @Override
	public void onTestFailure(ITestResult tr){
		String outputDir=tr.getTestContext().getOutputDirectory();
		String surefireDir=outputDir.substring(0, outputDir.lastIndexOf("\\"));
		String testName=tr.getTestContext().getCurrentXmlTest().getName();
		String currentTimeStr=DateUtil.dateToYmdString(new Date());
		String destination=surefireDir+File.separator+"screenshot"+File.separator+testName+File.separator+currentTimeStr;
	    File screenImage=ScreenUtil.takeScreenShot(destination);
	    String path=screenImage.getAbsolutePath();
	    String accessPath=path.replace(path.substring(0,path.lastIndexOf("screenshot")), "http://localhost/").replace("\\", "/");
        Reporter.log("<img src='"+accessPath+"'hight='100' width='100'><a href='"+accessPath+"'target='_blank'>点击查看大图</a></img>");
    }
}
