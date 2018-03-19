package com.lemon.app;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.util.StringUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lemon.app.util.ExcelUtil;
public class WechatLoginCase extends Base {
	
	@Test(dataProvider="datas1")
	public void test(String nickname,String mobilephone,String password,String errortitle,String allmessage){
		//点击注册按钮
		androidDriver.startActivity("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
		getElement("登录注册页面","注册按钮").click();
		getElement("注册信息页面","昵称").sendKeys(nickname);
		getElement("注册信息页面","手机号输入框").sendKeys(mobilephone);
		getElement("注册信息页面","密码输入框").sendKeys(password);
		getElement("注册信息页面","注册按钮").click();
		getElement("同意协议页面","同意按钮").click();
		String actual_title=getElement("注册信息页面","弹框提示标题").getText();
		Assert.assertEquals(actual_title, errortitle);		
		String actual=getElement("注册信息页面","弹框提示内容").getText();
		Assert.assertEquals(actual, allmessage);
		getElement("注册信息页面","弹框确定").click();
	}
	@Test(dataProvider="datas2")
    //测试注册按钮不可点击
	public void test2(String nickname,String mobilephone,String password){
		//点击注册按钮
		androidDriver.startActivity("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
		getElement("登录注册页面","注册按钮").click();
        if(!StringUtils.isEmpty(nickname)){
        	getElement("注册信息页面","昵称").sendKeys(nickname);
		}
        if(!StringUtils.isEmpty(mobilephone)){
        	getElement("注册信息页面","手机号输入框").sendKeys(mobilephone);
        }
        if(!StringUtils.isEmpty(password)){
        	getElement("注册信息页面","密码输入框").sendKeys(password);
        }
        boolean actual=getElement("注册信息页面","注册按钮").isEnabled();
		Assert.assertEquals(actual, false);		
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
