package com.lemon.app;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.util.StringUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lemon.app.util.Assertion;
import com.lemon.app.util.ExcelUtil;
public class WechatLoginCase extends Base {
	private Logger logger=Logger.getLogger(WechatLoginCase.class);
	@Test(dataProvider="datas1")
	public void test(String nickname,String mobilephone,String password,String errortitle,String allmessage){
		//点击注册按钮
		logger.info("测试输入数据不合法");
		androidDriver.startActivity("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
		click(getElement("登录注册页面","注册按钮"));
		sendKeys(getElement("注册信息页面","昵称"),nickname);
		sendKeys(getElement("注册信息页面","手机号输入框"),mobilephone);
		sendKeys(getElement("注册信息页面","密码输入框"),password);
		click(getElement("注册信息页面","注册按钮"));
		click(getElement("同意协议页面","同意按钮"));
		String actual_title=getText(getElement("注册信息页面","弹框提示标题"));
		Assertion.assertEquals(actual_title, errortitle);		
		String actual=getText(getElement("注册信息页面","弹框提示内容"));
		Assertion.assertEquals(actual, allmessage);
		click(getElement("注册信息页面","弹框确定"));
	}
	@Test(dataProvider="datas2")
    //测试注册按钮不可点击
	public void test2(String nickname,String mobilephone,String password){
		//点击注册按钮
		logger.info("测试注册按钮不可点击");
		androidDriver.startActivity("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
		click(getElement("登录注册页面","注册按钮"));
        if(!StringUtils.isEmpty(nickname)){
        	sendKeys(getElement("注册信息页面","昵称"),nickname);
		}
        if(!StringUtils.isEmpty(mobilephone)){
        	sendKeys(getElement("注册信息页面","手机号输入框"),mobilephone);
        }
        if(!StringUtils.isEmpty(password)){
        	sendKeys(getElement("注册信息页面","密码输入框"),password);
        }
        boolean actual=getElement("注册信息页面","注册按钮").isEnabled();
        Assertion.assertFalse(actual);		
	}
	
	@DataProvider
   public Object[][]datas1(){
	   Object[][]datas=ExcelUtil.read("/wechat_register.xlsx",2, 2, 3, 1, 5);
//		Object[][]datas={
//				{"lemon","18872","1887213vgt","手机号码错误","你输入的是一个无效的手机号码"},
//				{"lemon","18872134562","18872","注册失败","密码必须是8-16位的数字、字符组合（不能是纯数字）"}};
	   return datas;
   }
	@DataProvider
	public Object[][]datas2(){
		Object[][]datas=ExcelUtil.read("/wechat_register.xlsx",1, 2, 4, 2, 4);
		return datas;
	}

}
