package com.lemon.app;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.lemon.app.po.pojo.Activity;
import com.lemon.app.po.pojo.Locator;
import com.lemon.app.util.ActivityUtil;

public class Base {
	private Logger logger=Logger.getLogger(Base.class);
	//deviceName:127.0.0.1:62001
	//	private AppiumDriver androidDriver;
		public static AndroidDriver<WebElement> androidDriver;
		
		
		@BeforeSuite
		@Parameters({"deviceName","appPackage","appActivity","restApi"})
		public void setUp(String deviceName,String appPackage,String appActivity,String restApi) throws MalformedURLException{
			//配置对象，添加deviceName,appPackage,appActivity信息
			logger.info("******************测试套件执行开始******************************");
			logger.info("执行驱动对象初始化，详细的配置信息：{\"deviceName\":\""+deviceName+"\",\"appPackage\":\""+appPackage+"\",\"appActivity\":\""+appActivity+"\"}");
			DesiredCapabilities desiredCapabilities =new DesiredCapabilities();
			desiredCapabilities.setCapability("deviceName", deviceName);
			desiredCapabilities.setCapability("appPackage", appPackage);
			desiredCapabilities.setCapability("appActivity", appActivity);
			//appium接受请求的rest接口
			URL url=new URL(restApi);
			//创建驱动对象
			logger.info("接受请求的api地址为："+url);
			androidDriver=new AndroidDriver(url,desiredCapabilities);
			logger.info("驱动对象创建完毕");
		}
		  @AfterSuite
			public void tearDown() {
			  logger.info("******************测试套件执行结束******************************");
				androidDriver.quit();
				
				
			}
		     
		     public WebElement getElement(final String page,final String keyword){
		    	 WebDriverWait wait=new WebDriverWait(androidDriver,30);
		    	 WebElement webElement =wait.until(new ExpectedCondition<WebElement>(){
		    		 public WebElement apply(WebDriver androidDriver){
		    			 String tips="";
		    			 WebElement element=null;
		    			 try {
		    				 Locator neededLocator=null;
			    			 for (Activity activity : ActivityUtil.activitiesList) {
									if(page.equals(activity.getKey())){
										List<Locator>locators=activity.getLocators();
										for(Locator locator:locators){
											if(locator.getDesc().equals(keyword)){
												neededLocator=locator;
											}
										}
									}
								};
								  tips="获取元素：【"+neededLocator.getDesc()+"】{'by','"+neededLocator.getBy()+"',{'value','"+neededLocator.getValue()+"'}'";
								By by=null;
								if("id".equals(neededLocator.getBy())){
									by=By.id(neededLocator.getValue());
									element= androidDriver.findElement(by);
								}else if("className".equals(neededLocator.getBy())){
									by=By.className(neededLocator.getValue());
									element= androidDriver.findElements(by).get(Integer.valueOf(neededLocator.getIndex()));
								}
								tips+="【成功】";
						} catch (Exception e) {
							tips+="【失败】";
						}
		    			logger.info(tips);
		    			 return element;
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
		     
		     public void click(WebElement element){
		    	 logger.info("完成点击事件");
		    	 element.click();
		     }
		     
		     public String getText(WebElement element){
		    	 logger.info("完成文本信息获取");
		    	 return element.getText();
		     }
		     
		     public void sendKeys(WebElement element,String keys){
		    	 logger.info("输入"+keys);
		    	 element.sendKeys(keys);
		     }

}
