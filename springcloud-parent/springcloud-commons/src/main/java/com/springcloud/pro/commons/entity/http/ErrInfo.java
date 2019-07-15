package com.springcloud.pro.commons.entity.http;

import java.io.Serializable;

/**
 * 错误信息
 * 
 */
public class ErrInfo implements Serializable {
	private String errStatus;// 错误状态
	private String errMsg;// 错误信息

	public String getErrStatus() {
		return errStatus;
	}

	public void setErrStatus(String errStatus) {
		this.errStatus = errStatus;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
}
