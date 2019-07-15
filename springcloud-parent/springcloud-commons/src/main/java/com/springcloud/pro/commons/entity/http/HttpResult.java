package com.springcloud.pro.commons.entity.http;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * http 请求返回封装类
 */
@SuppressWarnings("serial")
public class HttpResult<T> implements Serializable{
	
	
	public HttpResult(){
		
	}
	
	public static <T> HttpResult<T> newResult(Boolean success,T result,HttpServletResponse response){
		return HttpResult.newResult(success,result,"",null,response);
	}
	
	public static <T> HttpResult<T> newResult(Boolean success,T result,String errorCode,String errorMessage,HttpServletResponse response){
		HttpResult<T> httpResult = new HttpResult<T>();
		httpResult.success = success;
		httpResult.result = result;
		httpResult.errorCode = errorCode;
		httpResult.errorMessage = errorMessage;
		return httpResult;
	}
	/**
	 * 是否成功
	 */
	public Boolean success = true;
	/**
	 * 状态编码
	 */
	public int stateCode;
	/**
	 * 状态信息
	 */
	public String stateMessage;
	/**
	 * 返回数据结果
	 */
	public T result;
	/**
	 * 错误编码
	 */
	public String errorCode;
	/**
	 * 错误信息
	 */
	public String errorMessage;
}
