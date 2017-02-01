package com.github.cruiser.dto;

public class WeixinTemplateStringPair {
	private String value;
	private String color;

	public WeixinTemplateStringPair(String value, String color){
		setValue(value);
		setColor(color);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
