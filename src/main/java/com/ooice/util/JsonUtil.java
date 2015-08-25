package com.ooice.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Collection;
import java.util.Map;

public class JsonUtil {
	public static <T> String bean2Json(T entity)
	{
		StringBuilder json = new StringBuilder();
		if (entity == null)
			json.append("\"\"");
		else if (((entity instanceof String)) || 
				((entity instanceof Integer)) || 
				((entity instanceof Float)) || 
				((entity instanceof Boolean)) || 
				((entity instanceof Short)) || 
				((entity instanceof Double)) || 
				((entity instanceof Long)) || 
				((entity instanceof BigDecimal)) || 
				((entity instanceof BigInteger)) || 
				((entity instanceof Byte)))
			json.append("\"").append(string2Json(entity.toString())).append("\"");
		else if ((entity instanceof Object[]))
			json.append(array2Json((Object[])entity));
		else if ((entity instanceof Collection))
			json.append(collection2Json((Collection)entity));
		else if ((entity instanceof Map))
			json.append(map2Json((Map)entity));
		else if ((entity instanceof Date))
			json.append("\"").append(string2Json(DateUtil.formatDate((Date)entity))).append("\"");
		else {
			json.append(self2Json(entity));
		}

		return json.toString();
	}

	public static <T> String self2Json(T entity)
	{
		StringBuilder json = new StringBuilder();
		json.append("{");
		PropertyDescriptor[] props = (PropertyDescriptor[])null;
		try
		{
			props = Introspector.getBeanInfo(entity.getClass(), Object.class).getPropertyDescriptors();
		} catch (IntrospectionException localIntrospectionException) {
		}
		if (props != null) {
			for (int i = 0; i < props.length; i++)
				try {
					String name = bean2Json(props[i].getName());
					String value = bean2Json(props[i].getReadMethod().invoke(entity, new Object[0]));
					json.append(name);
					json.append(":");
					json.append(value);
					json.append(",");
				} catch (Exception localException) {
				}
			if (props.length == 0) {
				json.append(",");
			}
			json.setCharAt(json.length() - 1, '}');
		} else {
			json.append("}");
		}
		return json.toString();
	}

	public static <K, V> String map2Json(Map<K, V> map)
	{
		StringBuilder json = new StringBuilder();
		json.append("{");
		if ((map != null) && (map.size() > 0)) {
			for (Map.Entry entity : map.entrySet()) {
				json.append(bean2Json(entity.getKey()));
				json.append(":");
				json.append(bean2Json(entity.getValue()));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, '}');
		} else {
			json.append("}");
		}
		return json.toString();
	}

	public static <T> String collection2Json(Collection<T> collection)
	{
		StringBuilder json = new StringBuilder();
		json.append("[");
		if ((collection != null) && (collection.size() > 0)) {
			for (Object t : collection) {
				json.append(bean2Json(t));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}

		return json.toString();
	}

	public static <T> String array2Json(T[] array)
	{
		StringBuilder json = new StringBuilder();
		json.append("[");
		if ((array != null) && (array.length > 0)) {
			Object[] arrayOfObject = array; int j = array.length; for (int i = 0; i < j; i++) { Object t = arrayOfObject[i];
			json.append(bean2Json(t));
			json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	private static String string2Json(String s)
	{
		if (s == null)
			return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case '"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			case '/':
				sb.append("\\/");
				break;
			default:
				if ((ch >= 0) && (ch <= '\037')) {
					String ss = Integer.toHexString(ch);
					sb.append("\\u");
					for (int k = 0; k < 4 - ss.length(); k++) {
						sb.append('0');
					}
					sb.append(ss.toUpperCase());
				} else {
					sb.append(ch);
				}break;
			}
		}
		return sb.toString();
	}
}

