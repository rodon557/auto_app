package com.lemon.app.po.pojo;

public class Locator {
	  private String by;
	  private String value;
	  private String desc;
	  private String index;
	public String getBy() {
		return by;
	}
	public void setBy(String by) {
		this.by = by;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public Locator(String by, String value, String desc) {
		super();
		this.by = by;
		this.value = value;
		this.desc = desc;
	}
	public Locator(String by, String value, String desc, String index) {
		super();
		this.by = by;
		this.value = value;
		this.desc = desc;
		this.index = index;
	}
	public Locator() {
		
	}
	  
}
