package com.lemon.app.po.pojo;

import java.util.List;

public class Activity {
    private String key;
    private String value;
    private List<Locator> locators;
	public List<Locator> getLocators() {
		return locators;
	}
	public void setLocators(List<Locator> locators) {
		this.locators = locators;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Activity(String key, String value, List<Locator> locators) {
		super();
		this.key = key;
		this.value = value;
		this.locators = locators;
	}
    
    
}
