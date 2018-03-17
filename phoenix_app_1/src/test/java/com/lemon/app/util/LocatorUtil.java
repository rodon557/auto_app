package com.lemon.app.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.lemon.app.po.pojo.Locator;

public class LocatorUtil {
    public static Map<String,Map<String,Locator>> locators=new HashMap<String,Map<String,Locator>>();
    
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
				
				List<Element> locators=activity.elements("locator");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
