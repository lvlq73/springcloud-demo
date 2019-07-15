package com.springcloud.pro.commons.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 序列化工具，包含json、protobuf、xml序列化方法
 */
public class SerializeUtils {
	/**
	 * 获取预先配置好的ObjectMapper
	 * 
	 * @return
	 */
	public static ObjectMapper getDefaultObjectMapper() {
		ObjectMapper om = new ObjectMapper();
		om.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		om.disable(SerializationFeature.WRITE_NULL_MAP_VALUES);
		om.setSerializationInclusion(Include.NON_NULL);
		om.setDateFormat(Usual.mfAll);
		om.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		om.setLocale(Locale.CHINA);
		return om;
	}

	/**
	 * 获取预先配置好的ObjectMapper
	 * 
	 * @return
	 */
	public static ObjectMapper getObjectMapper() {
		return getDefaultObjectMapper();
	}

	/**
	 * 获取预先配置好的ObjectMapper
	 * 
	 * @return
	 */
	public static XmlMapper getXmlMapper() {
		XmlMapper mMapper = new XmlMapper();
		mMapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		mMapper.setLocale(Locale.CHINA);
		mMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		mMapper.disable(SerializationFeature.WRITE_NULL_MAP_VALUES);
		mMapper.setSerializationInclusion(Include.NON_NULL);
		mMapper.setDateFormat(Usual.mfAll);
		return mMapper;
	}

	/**
	 * 将对象转化为json字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static <T> String objectToJSONString(T obj) {
		ObjectMapper om = getDefaultObjectMapper();
		try {
			String json = om.writeValueAsString(obj);
			om = null;
			obj = null;
			return json;
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将对象转化为json二进制数组
	 * 
	 * @param obj
	 * @return
	 */
	public static <T> byte[] objectToJSONBytes(T obj) {
		ObjectMapper om = getObjectMapper();
		byte[] bytes = null;
		try {
			bytes = om.writeValueAsBytes(obj);
			om = null;
			obj = null;
			return bytes;
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json字符串转化为指定的对象----泛型
	 * 
	 * @param json
	 *            json字符串
	 * @param cla
	 *            指定的对象的类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T jsonToObject(String json, T t) {
		ObjectMapper om = getObjectMapper();
		try {
			return (T) om.readValue(json, t.getClass());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param json
	 * @param cla
	 * @return
	 */
	public static <T> T jsonToObject(String json, Class<?> cla) {
		ObjectMapper om = getObjectMapper();
		try {
			return (T) om.readValue(json, cla);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param json
	 * @param typeReference
	 * @return
	 */
	public static <T> T jsonToObject(String json, TypeReference<T> typeReference) {
		ObjectMapper om = getObjectMapper();
		try {
			return (T) om.readValue(json, typeReference);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * xmlString转换为Object对象
	 * 
	 * @param xmlString
	 * @param t
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 */
	public static <T> T xmlToObj(String xmlString, T t) throws JsonParseException, JsonMappingException {
		XmlMapper mapper = getXmlMapper();
		try {
			t = (T) mapper.readValue(xmlString, t.getClass());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 
	 * @param xmlString
	 * @param cls
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 */
	public static <T> T xmlToObj(String xmlString, Class<?> cls) throws JsonParseException, JsonMappingException {
		XmlMapper mapper = getXmlMapper();
		try {
			return (T) mapper.readValue(xmlString, cls);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*public static <T> T decode(String contentType, byte[] datas, Class<?> cls) {
		T t = null;
		switch (contentType) {
		case ContentType.ProtoBuf:
			t = (T) FSTUtil.<T>decodeBuf(datas, cls);
			break;
		case ContentType.App_FST:
			t = (T) FSTUtil.<T>decodeFST(datas, cls);
			break;
		case ContentType.ProtoStuff:
			t = (T) FSTUtil.<T>decodeStuff(datas, cls);
			break;
		default:
			break;
		}
		return t;
	}*/
}
