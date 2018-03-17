package com.lemon.app.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.lemon.app.po.pojo.Activity;
import com.lemon.app.po.pojo.Locator;

public class ActivityUtil {
	  public static List<Activity> activitiesList=new ArrayList<Activity>();
	    
	    static{
	    	//1创建解析器
	    	SAXReader reader=new SAXReader();
	    	try {
	    		//2.创建文档对象
				Document document =reader.read(LocatorUtil.class.getResourceAsStream("/UILibrary.xml"));
				//3.获取根元素
				Element root=document.getRootElement();
				List<Element>activities=root.elements("activity");
				for (Element activity : activities) {
					String key=activity.attributeValue("key");
					String value=activity.attributeValue("value");
					List<Element> locators=activity.elements("locator");
					List<Locator> list=new ArrayList<Locator>();
					for (Element locator : locators) {
						String by=locator.attributeValue("by");
						String locatorValue=locator.attributeValue("value");
						String desc=locator.attributeValue("desc");
						String index=locator.attributeValue("index");
						Locator lc=new Locator(by,locatorValue,desc,index);
						list.add(lc);
					}
					Activity ac =new Activity(key,value,list);
					activitiesList.add(ac);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
}
