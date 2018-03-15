package com.lemon.app;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
public class WechatLoginCase extends Base {
	
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
   

}
