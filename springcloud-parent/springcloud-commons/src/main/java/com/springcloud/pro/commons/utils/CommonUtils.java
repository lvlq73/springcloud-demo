package com.springcloud.pro.commons.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ning.http.client.Response;
import com.springcloud.pro.commons.entity.http.AcceptType;
import com.springcloud.pro.commons.entity.http.ErrInfo;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * 通用工具类
 */
public class CommonUtils {

	/**
	 * 设定错误信息
	 * 
	 * @param errStatus
	 *            错误状态
	 * @param errMsg
	 *            错误信息
	 * @param response
	 *            HttpServletResponse
	 * @return 错误信息对象
	 */
	public static ErrInfo setErrInfo(String errStatus, String errMsg, HttpServletResponse response) {
		ErrInfo errInfo = new ErrInfo();
		errInfo.setErrMsg(errMsg);
		errInfo.setErrStatus(errStatus);
		response.setHeader("errStatus", errStatus);
		return errInfo;
	}

	/**
	 * 根据http的ContextType或Accpet传输的类型经过序列化为二进制数据
	 * @param value
	 * @param contextType
	 * @return
	 */
	public static <T> byte[] getBytesByContextType(Object value, String contextType) {
		byte[] resultData = null;
		if(StringUtils.isEmpty(contextType)){
			contextType = AcceptType.App_Json;
		}
		switch (contextType) {
		/*case AcceptType.ProtoBuf:
			resultData = FSTUtil.encodeBuf(value, value.getClass());
			break;
		case AcceptType.ProtoStuff:
			resultData = FSTUtil.encodeStuff(value, value.getClass());
			break;
		case AcceptType.App_FST:
			resultData = FSTUtil.encodeFST(value,null);
			break;*/
		case AcceptType.App_Json:
			resultData = SerializeUtils.objectToJSONBytes(value);
			break;
		case AcceptType.Text_Json:
			resultData = SerializeUtils.objectToJSONBytes(value);
			break;
		default:
			resultData = SerializeUtils.objectToJSONBytes(value);
			break;
		}
		return resultData;
	}
	
	/**
	 * 判断Response中的Header是否存在错误信息
	 * @param resp
	 * @return
	 */
	public static boolean hasHttpErrStatus(Response resp){
		return getHttpErrStatus(resp) != null;
	}
	
	/**
	 * 获取Response中的Header是否存在的错误信息
	 * @param resp
	 * @return
	 */
	public static String getHttpErrStatus(Response resp){
		if(resp.getHeaders().containsKey("errStatus")){
			String errStatus = resp.getHeader("errStatus");
			return errStatus;
		}
		return null;
	}
	/**
	 * 生成随机码
	 * @return
	 */
	public static String getRandomString(int length) { //length表示生成字符串的长度  
	    String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 }     
	/**
	 * 生成随机数
	 * @return
	 */
	public static String getRandom(int length){
		Random random = new Random();  
		String result="";  
		for (int i=0;i<length;i++)  
		{  
		    result+=random.nextInt(10);  
		}  
		return result;
	}
	
	/**
	 * 设置cookie
	 * @param response
	 * @param name  cookie名字
	 * @param value cookie值
	 * @param maxAge cookie生命周期  以秒为单位
	 */
	public static void addCookie(HttpServletResponse response,String name,String value,int maxAge){
		try {
			if(value != null){
				value = URLEncoder.encode(value, "utf-8");
			}
			Cookie cookie = new Cookie(name,value);
			//设置路径，这个路径即该工程下都可以访问该cookie 如果不设置路径，那么只有设置该cookie路径及其子路径可以访问
			cookie.setPath("/");
			cookie.setMaxAge(maxAge);
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取相应cookie name的值
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request,String name){
		try {
			Cookie cookie = getCookieByName(request ,name );
			if(cookie!=null){
				return URLDecoder.decode(cookie.getValue(),"utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 根据名字获取cookie
	 * @param request
	 * @param name cookie名字
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request,String name){
	    Map<String,Cookie> cookieMap = ReadCookieMap(request);
	    if(cookieMap.containsKey(name)){
	        Cookie cookie = (Cookie)cookieMap.get(name);
	        return cookie;
	    }else{
	        return null;
	    }   
	}
	 
	/**
	 * 将cookie封装到Map里面
	 * @param request
	 * @return
	 */
	private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){  
	    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
	    Cookie[] cookies = request.getCookies();
	    if(null!=cookies){
	        for(Cookie cookie : cookies){
	            cookieMap.put(cookie.getName(), cookie);
	        }
	    }
	    return cookieMap;
	}
	/**
	 * 流转化成文件
	 * @param ins
	 * @param file
	 * @throws IOException
	 */
	public static void inputstreamToFile(InputStream ins,File file) throws IOException{
		   OutputStream os = new FileOutputStream(file);
		   int bytesRead = 0;
		   byte[] buffer = new byte[8192];
		   while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
		      os.write(buffer, 0, bytesRead);
		   }
		   os.close();
		   ins.close();
	}
	
	/**
	 * 获取服务器webapps下地址
	 * @param request
	 * @return
	 */
	public static String getUploadRealPath(HttpServletRequest request){
		return request.getSession().getServletContext().getRealPath("/").substring(0, request.getSession().getServletContext().getRealPath("/").lastIndexOf(request.getContextPath().replace("/", "")));
	}
	/**
	 * java去除list重复值（循环元素删除、通过HashSet删除） 
	 * @param list
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void removeDuplicateWithOrder(List list)  {
		Set set  =   new  HashSet();
		List newList  =   new  ArrayList();
		for  (Iterator iter  =  list.iterator(); iter.hasNext();)  {
			 Object element  =  iter.next();
			 if(set.add(element)){
				 newList.add(element);
			 }
		} 
		list.clear();
		list.addAll(newList);
		System.out.println( " remove duplicate "   +  list);
	}

	/**
	 * 将数据存入session或cookie
	 * @param session
	 * @param response
	 * @param name
	 * @param value
	 */
	public static void setSessionCookieParams(HttpSession session, HttpServletResponse response,String name,String value){
		session.setAttribute(name, value);
		CommonUtils.addCookie(response, name, value, 24*60*60);
	}
}
