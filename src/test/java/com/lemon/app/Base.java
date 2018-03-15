package com.lemon.app;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class Base {
	//deviceName:127.0.0.1:62001
		private AppiumDriver androidDriver;
		
		@BeforeClass
		@Parameters({"deviceName","appPackage","appActivity","restApi"})
		public void setUp(String deviceName,String appPackage,String appActivity,String restApi) throws MalformedURLException{
			//配置对象，添加deviceName,appPackage,appActivity信息
			DesiredCapabilities desiredCapabilities =new DesiredCapabilities();
			desiredCapabilities.setCapability("deviceName", deviceName);
			desiredCapabilities.setCapability("appPackage", appPackage);
			desiredCapabilities.setCapability("appActivity", appActivity);
			//appium接受请求的rest接口
			URL url=new URL(restApi);
			//创建驱动对象
			androidDriver=new AndroidDriver(url,desiredCapabilities);
		}
		  @AfterClass
			public void tearDown() {
				androidDriver.quit();
				
			}
		     
		     public WebElement getElement(final By by){
		    	 WebDriverWait wait=new WebDriverWait(androidDriver,30);
		    	 WebElement webElement =wait.until(new ExpectedCondition<WebElement>(){
		    		 public WebElement apply(WebDriver androidDriver){
		    			 return androidDriver.findElement(by);
		    		 }
		    	 });
		    	 return webElement;
		     }
		     
		     public List<WebElement> getElements(final By by){
		    	 WebDriverWait wait=new WebDriverWait(androidDriver,30);
		    	 List<WebElement> webElements =wait.until(new ExpectedCondition<List<WebElement>>(){
		    		 public List<WebElement> apply(WebDriver androidDriver){
		    			 return androidDriver.findElements(by);
		    		 }
		    	 });
		    	 return webElements;
		     }

}
