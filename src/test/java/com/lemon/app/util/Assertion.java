package com.lemon.app.util;

import org.apache.log4j.Logger;
import org.testng.Assert;

public class Assertion {
	private static Logger logger=Logger.getLogger(Assertion.class);
    public static void assertEquals(String actual,String expected){
    	logger.info("实际值为："+actual);
    	logger.info("期望值为："+expected);
    	Assert.assertEquals(actual, expected);
    	
    }
    public static void assertTrue(boolean actual){
    	logger.info("实际值为："+actual);
    	logger.info("期望值为：true");
    	Assert.assertTrue(actual);
    	
    }
    public static void assertFalse(boolean actual){
    	logger.info("实际值为："+actual);
    	logger.info("期望值为：false");
    	Assert.assertFalse(actual);
    	
    }
}
