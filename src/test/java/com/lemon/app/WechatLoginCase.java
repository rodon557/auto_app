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
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WechatLoginCase {
	//deviceName:127.0.0.1:62001
	private AppiumDriver androidDriver;
	
	@BeforeClass
	public void setUp() throws MalformedURLException{
		//配置对象，添加deviceName,appPackage,appActivity信息
		DesiredCapabilities desiredCapabilities =new DesiredCapabilities();
		desiredCapabilities.setCapability("deviceName", "127.0.0.1:62001");
		desiredCapabilities.setCapability("appPackage", "com.tencent.mm");
		desiredCapabilities.setCapability("appActivity", "com.tencent.mm.ui.LauncherUI");
		//appium接受请求的rest接口
		URL url=new URL("http://127.0.0.1:4723/wd/hub");
		//创建驱动对象
		androidDriver=new AndroidDriver(url,desiredCapabilities);
	}
	
	@Test
	public void test(){
		//点击注册按钮
		getElement(By.id("d36")).click();
		List<WebElement> elements=getElements(By.id("ht"));
		
		//用户名输入框
		WebElement nickName=elements.get(0);
		nickName.sendKeys("lemon");
		//手机号
		WebElement mobilePhone=elements.get(1);
		mobilePhone.sendKeys("13872156476");
		//密码
		WebElement password=elements.get(2);
		password.sendKeys("13872156476");
		//点击注册
		getElement(By.id("cw1")).click();
		//隐私保护协议
		getElement(By.id("d91")).click();
		//注册失败提示语
		String actual=getElement(By.id("c_n")).getText();
		String expected="注册失败";
		Assert.assertEquals(actual, expected);
		
		
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
