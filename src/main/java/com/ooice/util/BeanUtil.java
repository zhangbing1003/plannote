package com.ooice.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

public class BeanUtil {
	public static <T> T getBean(String beanName)
	  {
	    return null;
	  }

	  public static <T> T getBeanByName(String beanName)
	  {
	    return null;
	  }

	  public static <T> T getBeanByName(String beanName, ServletContext servletContext)
	  {
	    return null;
	  }

	  public static String[] getPropertyFromBean(Object obj)
	  {
	    Field[] fieldAry = getFieldFromBean(obj);
	    String[] fieldStr = new String[fieldAry.length];

	    Arrays.fill(fieldStr, fieldAry);

	    return fieldStr;
	  }

	  public static Field[] getFieldFromBean(Object obj)
	  {
	    Field[] fieldAry = obj.getClass().getDeclaredFields();
	    return fieldAry;
	  }

	  public static String getValueFromBean(Object obj, String propertyName) {
	    if ((obj instanceof Map)) {
	      return getValueByNameFromMap(obj, propertyName);
	    }

	    return getValueFromPropertyName(obj, propertyName);
	  }

	  public static String getValueFromPropertyName(Object obj, String propertyName)
	  {
	    String methodName = getMethodNameFromProperty(propertyName);
	    Method method = getMethod(obj, methodName);
	    return getValueFromMethod(obj, method);
	  }

	  public static String getValueByNameFromMap(Object obj, String propertyName) {
	    String beanValue = "";
	    try {
	      Object tmpValue = ((Map)obj).get(propertyName);
	      if (tmpValue == null) {
	        tmpValue = "";
	      }
	      beanValue = String.valueOf(tmpValue);
	    } catch (Exception e) {
	      beanValue = "";
	    }

	    return beanValue;
	  }

	  public static void setPropertyValue(Object obj, String propertyName, Object[] objs)
	  {
	    String methodName = getMethodNameFromProperty(propertyName);
	    Method method = getMethod(obj, methodName);
	    setPropertyValueFromMethod(obj, method, objs);
	  }

	  public static void setPropertyValueFromMethod(Object obj, Method method, Object[] objs)
	  {
	    try
	    {
	      method.invoke(obj, objs);
	    } catch (IllegalArgumentException e) {
	      e.printStackTrace();
	    } catch (IllegalAccessException e) {
	      e.printStackTrace();
	    } catch (InvocationTargetException e) {
	      e.printStackTrace();
	    }
	  }

	  public static String getValueFromMethod(Object obj, Method method)
	  {
	    String value = "";
	    try {
	      Object object = method.invoke(obj, new Object[0]);
	      if (object != null) {
	        if ((object instanceof String))
	          value = (String)object;
	        else if ((object instanceof Date))
	          value = DateUtil.formatDate((Date)object);
	        else if ((object instanceof BigDecimal))
	          value = ((BigDecimal)object).toString();
	        else if ((object instanceof Integer))
	          value = ((Integer)object).toString();
	        else if ((object instanceof Long))
	          value = ((Long)object).toString();
	        else if ((object instanceof Double))
	          value = ((Double)object).toString();
	        else if ((object instanceof Float))
	          value = ((Float)object).toString();
	        else
	          value = (String)object;
	      }
	    }
	    catch (IllegalArgumentException e)
	    {
	      e.printStackTrace();
	    } catch (IllegalAccessException e) {
	      e.printStackTrace();
	    } catch (InvocationTargetException e) {
	      e.printStackTrace();
	    }
	    return value;
	  }

	  public static Method getMethod(Object obj, String methodName)
	  {
	    Method method = null;
	    try {
	      method = obj.getClass().getMethod(methodName, new Class[0]);
	    } catch (SecurityException e) {
	      e.printStackTrace();
	    } catch (NoSuchMethodException e) {
	      e.printStackTrace();
	    }
	    return method;
	  }

	  public static Method getMethodFromProperty(Object obj, String propertyName)
	  {
	    return getMethod(obj, getMethodNameFromProperty(propertyName));
	  }

	  public static String getMethodNameFromProperty(String propertyName)
	  {
	    return "get" + propertyName.substring(0, 1).toUpperCase() + 
	      propertyName.substring(1);
	  }

	  public static List entityToArr(Class cls, Object o, List excludeFields)
	  {
	    if (o == null) return null;

	    List values = new ArrayList();
	    try
	    {
	      Field[] fields = cls.getDeclaredFields();
	      for (Field f : fields)
	      {
	        if (f.getModifiers() == 2)
	        {
	          if ((excludeFields == null) || (!excludeFields.contains(f.getName())))
	          {
	            f.setAccessible(true);
	            Object value = f.get(o);
	            values.add(value);
	          }
	        }
	      } } catch (Exception e) { e.printStackTrace(); }


	    return values;
	  }
}
