package com.springcloud.pro.commons.config;

import com.springcloud.pro.commons.utils.Usual;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * 一些配置信息
 */
//@Component
//@PropertySource(value={"classpath:sysconfig.properties"})
//@ConfigurationProperties(prefix="web")
public class SysConfig {

	private static Integer isConnectDB;
	//是否合并
	private static Integer isMergeJs;
	//版本号
	private static String webVersion;
	@Deprecated
	private static Integer compressEnable;
	@Deprecated
	private static Integer compressJs;
	@Deprecated
	private static Integer compressCss;
	@Deprecated
	private static Integer removeIntertagSpaces;
	//webapp地址url
	public static String webAppHttpRoot;
	//微信小程序appid
	private static String wecatAppid;
	//微信小程序秘钥
	private static String wecatSecret;
	//微信小程序key
	private static String wecatKey;
	//微信小程序商户号
	private static String wecatMchid;
	//	文件上传地址
	private static String uploadPath;
	//	文件保存的物理地址
	private static String wuliPath;
	//后台登录账户
	private static String userName;
	//后台登录密码
	private static String password;
	//新用户默认密码
	private static String newUsrPassword;

	private static HashMap<String,Object> params = new HashMap<>();

	public static Boolean getIsMergeJs() {
		if(isConnectDB.equals(1) && params.get("isMergeJs")!=null){
			isMergeJs = Usual.f_getInteger(params.get("isMergeJs"));
		}
		if(isMergeJs.equals(1)){
			return true;
		}else{
			return false;
		}
	}

	public void setIsMergeJs(Integer isMergeJs) {
		SysConfig.isMergeJs = isMergeJs;
	}

	public static Boolean getIsConnectDB() {
		if(isConnectDB.equals(1)){
			return true;
		}else{
			return false;
		}
	}

	public  void setIsConnectDB(Integer isConnectDB) {
		SysConfig.isConnectDB = isConnectDB;
	}

	public static String getWecatKey() {
		return wecatKey;
	}

	public static String getUploadPath() {
		if(isConnectDB.equals(1) && params.get("uploadPath")!=null){
			uploadPath = Usual.f_getString(params.get("uploadPath"));
		}
		return uploadPath;
	}

	public static String getUserName() {
		return userName;
	}

	public static String getPassword() {
		return password;
	}

	public static String getWuliPath() {
		if(isConnectDB.equals(1) && params.get("wuliPath")!=null){
			wuliPath = Usual.f_getString(params.get("wuliPath"));
		}
		return wuliPath;
	}

	public static String getNewUsrPassword() {
		return newUsrPassword;
	}

	public void setNewUsrPassword(String newUsrPassword) {
		SysConfig.newUsrPassword = newUsrPassword;
	}

	public  void setWuliPath(String wuliPath) {
		SysConfig.wuliPath = wuliPath;
	}

	public  void setPassword(String password) {
		SysConfig.password = password;
	}

	public void setUserName(String userName) {
		SysConfig.userName = userName;
	}

	public void setUploadPath(String uploadPath) {
		SysConfig.uploadPath = uploadPath;
	}

	public void setWecatKey(String wecatKey) {
		SysConfig.wecatKey = wecatKey;
	}

	public static String getWecatMchid() {
		return wecatMchid;
	}

	public void setWecatMchid(String wecatMchid) {
		SysConfig.wecatMchid = wecatMchid;
	}

	public static String getWebVersion() {
		return webVersion;
	}

	public void setWebVersion(String webVersion) {
		SysConfig.webVersion = webVersion;
	}
	@Deprecated
	public static Boolean compressEnable() {
		if(compressEnable.equals(1)){
			return true;
		}else{
			return false;
		}
	}
	@Deprecated
	public void setCompressEnable(Integer compressEnable) {
		SysConfig.compressEnable = compressEnable;
	}
	@Deprecated
	public static Boolean compressJs() {
		if(compressJs.equals(1)){
			return true;
		}else{
			return false;
		}
	}
	@Deprecated
	public void setCompressJs(Integer compressJs) {
		SysConfig.compressJs = compressJs;
	}
	@Deprecated
	public static Boolean compressCss() {
		if(compressCss.equals(1)){
			return true;
		}else{
			return false;
		}
	}
	@Deprecated
	public void setCompressCss(Integer compressCss) {
		SysConfig.compressCss = compressCss;
	}
	@Deprecated
	public static Boolean removeIntertagSpaces() {
		if(removeIntertagSpaces.equals(1)){
			return true;
		}else{
			return false;
		}
	}
	@Deprecated
	public void setRemoveIntertagSpaces(Integer removeIntertagSpaces) {
		SysConfig.removeIntertagSpaces = removeIntertagSpaces;
	}

	public static String getWecatSecret() {
		return wecatSecret;
	}

	public void setWecatSecret(String wecatSecret) {
		SysConfig.wecatSecret = wecatSecret;
	}

	public static String getWecatAppid() {
		return wecatAppid;
	}

	public void setWecatAppid(String wecatAppid) {
		SysConfig.wecatAppid = wecatAppid;
	}



}
