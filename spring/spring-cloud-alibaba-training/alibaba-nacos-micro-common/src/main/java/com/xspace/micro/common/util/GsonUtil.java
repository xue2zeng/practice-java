package com.xspace.micro.common.util;

import com.google.common.base.Objects;
import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import com.xspace.micro.common.enumerate.GsonSerializerFeature;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GsonUtil {
	// 线程安全的
	private static final Gson GSON;
	// 不过滤空值
	private static final Gson GSON_NULL;

	static {
		GSON = new GsonBuilder().enableComplexMapKeySerialization() // 当Map的key为复杂对象时,需要开启该方法
//              .serializeNulls() //当字段值为空或null时，依然对该字段进行转换
//              .excludeFieldsWithoutExposeAnnotation()//打开Export注解，但打开了这个注解,副作用，要转换和不转换都要加注解
				.setDateFormat("yyyy-MM-dd HH:mm:ss") // 序列化日期格式 "yyyy-MM-dd"
//				.setPrettyPrinting() // 自动格式化换行
				.disableHtmlEscaping() // 防止特殊字符出现乱码
				.create();

		GSON_NULL = new GsonBuilder().enableComplexMapKeySerialization() // 当Map的key为复杂对象时,需要开启该方法
				.serializeNulls() // 当字段值为空或null时，依然对该字段进行转换
//	            .excludeFieldsWithoutExposeAnnotation()//打开Export注解，但打开了这个注解,副作用，要转换和不转换都要加注解
				.setDateFormat("yyyy-MM-dd HH:mm:ss")// 序列化日期格式 "yyyy-MM-dd"
//	            .setPrettyPrinting() //自动格式化换行
				.disableHtmlEscaping() // 防止特殊字符出现乱码
				.create();
	}

	/**
	 * 获取Gson解析器
	 * @return
	 */
	public static Gson getGson() {
		return GSON;
	}

	/**
	 * 获取Gson解析器， 有空值解析
	 * @return
	 */
	public static Gson getWriteNullGson() {
		return GSON_NULL;
	}

	/**
	 * 根据对象返回Json，过滤空值字段
	 * @param object
	 * @return
	 */
	public static String toJsonString(Object object) {
		return GSON.toJson(object);
	}

	/**
	 * 根据对象返回Json，不过滤空值字段
	 * @param object
	 * @param ser
	 * @return
	 */
	public static String toJsonString(Object object, GsonSerializerFeature ser) {
		if (Objects.equal(ser, GsonSerializerFeature.WriteMapNullValue)) {
			return GSON_NULL.toJson(object);
		}
		return GSON.toJson(object);
	}

	/**
	 * 将字符串转化对象
	 *
	 * @param json     源字符串
	 * @param classOfT 目标对象类型
	 * @param <T>
	 * @return
	 */
	public static <T> T strToJavaBean(String json, Class<T> classOfT) {
		return GSON.fromJson(json, classOfT);
	}

	/**
	 * 将json转化为对应的实体对象<br>
	 * new TypeToken<List<T>>() {}.getType()<br>
	 * new TypeToken<Map<String, T>>() {}.getType()<br>
	 * new TypeToken<List<Map<String, T>>>() {}.getType()<br>
	 */
	public static <T> T fromJson(String json, Type typeOfT) {
		return GSON.fromJson(json, typeOfT);
	}

	/**
	 * 转成List
	 * 
	 * @param gsonString Json字符串
	 * @param targetClass 转换目标对象
	 * @return
	 */
	@SuppressWarnings("serial")
	public static <T> List<T> strToList(String gsonString, Class<T> targetClass) {
		return GSON.fromJson(gsonString, new TypeToken<List<T>>() {
		}.getType());
	}

	public static <T> List<T> fromJsonList(String json,Class<T> targetClass){
		final List<T> mList = new LinkedList<T>();
		JsonArray array  = JsonParser.parseString(json).getAsJsonArray();
		for(final JsonElement elemt : array) {
			mList.add(GSON.fromJson(elemt, targetClass));
		}
		
		return mList;
	}
	/**
	 * 转成List中有Map的
	 * 
	 * @param gsonString Json字符串
	 * @return
	 */
	@SuppressWarnings("serial")
	public static <T> List<Map<String, T>> strToListMaps(String gsonString) {
		return GSON.fromJson(gsonString, new TypeToken<List<Map<String, String>>>() {
		}.getType());
	}

	/**
	 * 转成Map
	 * 
	 * @param gsonString Json字符串
	 * @return
	 */
	@SuppressWarnings("serial")
	public static <T> Map<String, T> strToMaps(String gsonString) {
		return GSON.fromJson(gsonString, new TypeToken<Map<String, T>>() {
		}.getType());
	}
}