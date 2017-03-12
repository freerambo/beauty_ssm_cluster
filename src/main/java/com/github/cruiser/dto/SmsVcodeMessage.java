package com.github.cruiser.dto;

/**
 * Created by Qiming on 2017/2/1.
 */
public class SmsVcodeMessage {
	private String custom_name;
	private String receive_number;

	public String getCustom_name() {
		return custom_name;
	}

	public void setCustom_name(String custom_name) {
		this.custom_name = custom_name;
	}

	public String getReceive_number() {
		return receive_number;
	}

	public void setReceive_number(String receive_number) {
		this.receive_number = receive_number;
	}
}
