package com.github.cruiser.dto;

/**
 * Created by Qiming on 2017/2/1.
 */
public class WeixinError {
	private int errcode;
	private String errmsg;

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
}
