package com.smartamd.utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.StringMap;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONArray;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class JsonUtil {
	private static Gson gson;

	private JsonUtil() {
	}

	static {
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
	}

	/**
	 * 把List集合转成数组
	 * @param list
	 * @return
	 */
	public static String listToString(List list){
			String str = null;
			JSONArray json= JSONArray.fromObject(list);
			 str =json.toString();
		  return str;
		 }


	/**
	 * 把Java对象转换成任意json格式字符串
	 *
	 * @param obj
	 * @return
	 */
	public static String toJsonString(Object obj) {
		return gson.toJson(obj);
	}

	/**
	 * 获得Gson对象
	 *
	 * @return
	 */
	public static Gson getGson() {
		return gson;
	}

	/**
	 * 把json字符串转换成任意javabean对象
	 * 
	 * @param json
	 * @return
	 */
	public static Object toBean(String json, Class cls) {
		return gson.fromJson(json, cls);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> toList(String param,Class<T> clazz)throws Exception {
//		List<StringMap<T>> list=gson.fromJson(param,
//				new TypeToken<List<StringMap<T>>>() {
//				}.getType());
		List<T> listT=new ArrayList<T>();
		List<StringMap<Object>> list=gson.fromJson(param,
				new TypeToken<List<StringMap<Object>>>() {
				}.getType());
		for (StringMap<Object> stringMap : list) {
//			Set<String> set=stringMap.keySet();
			T t=clazz.newInstance();
			Field[] fields=clazz.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				Object value=stringMap.get(field.getName());
				if (value!=null) {
					Object object=null;
					Class classField=field.getType();
					if (classField==Date.class) {
					object=java.sql.Date.valueOf(value.toString());
						//field.set(t, date);
					}else if (classField==String.class) {
						object=value.toString();
					}else if (classField==Integer.class) {
						Method method=classField.getDeclaredMethod("parseInt",String.class);
						String str=value.toString();
						if (str.indexOf(".")>0) {
							str=str.substring(0,str.indexOf("."));
						}
						object=method.invoke(t, str);
					}else {
						String methodName="parse"+classField.getSimpleName();
						Method method=classField.getDeclaredMethod(methodName,String.class);
						String str=value.toString();
						if (str.indexOf(".")>0) {
							str=str.substring(0,str.indexOf("."));
						}
						object=method.invoke(t,str);
//						String value=.toString();
//						System.err.println(value);
					}
					field.set(t,object);
				}
			}
			listT.add(t);
		}
		return listT; 
	}
}
