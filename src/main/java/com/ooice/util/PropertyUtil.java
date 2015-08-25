package com.ooice.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyUtil {
	private static Logger log = Logger.getLogger(PropertyUtil.class);

	  public static Properties getProperties(String propName)
	    throws Exception
	  {
	    log.debug("加载属性文件：" + propName);
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    InputStream in = classLoader.getResourceAsStream(propName);
	    return getProperties(in);
	  }

	  private static Properties getProperties(InputStream in)
	    throws Exception
	  {
	    Properties properties = new Properties();
	    try {
	      if (in != null) {
	        properties.load(in);
	        in.close();
	      }
	    } catch (IOException e) {
	      throw new Exception("400-000-010", e);
	    }

	    return properties;
	  }
}
