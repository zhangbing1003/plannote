package com.ooice.util;

import java.math.BigDecimal;

public class StringUtil {
	public static boolean isNull(Object sourceStr)
	  {
	    if ((sourceStr == null) || ("".equals(sourceStr))) {
	      return true;
	    }

	    return false;
	  }

	  public static boolean isNotNull(Object sourceStr)
	  {
	    return !isNull(sourceStr);
	  }

	  public static String[] splitZtree(String ztreeValue) {
	    String[] rstArray = (String[])null;

	    isNull(ztreeValue);

	    return rstArray;
	  }

	  public static String fill2Left(String sourceStr, String appendChar, int maxLength)
	  {
	    return appendCharToString(sourceStr, appendChar, maxLength, true, true);
	  }

	  public static String fill2Right(String sourceStr, String appendChar, int maxLength)
	  {
	    return appendCharToString(sourceStr, appendChar, maxLength, true, false);
	  }

	  public static String appendCharToString(String sourceStr, String appendChar, int maxLength, boolean isAppendChar, boolean isAppendToLeft)
	  {
	    if (sourceStr == null) {
	      return sourceStr;
	    }

	    sourceStr = sourceStr.trim();
	    int strLength = sourceStr.length();

	    if (strLength > maxLength)
	      return sourceStr.substring(strLength - maxLength);
	    if (strLength == maxLength) {
	      return sourceStr;
	    }
	    if (!isAppendChar) {
	      return sourceStr;
	    }
	    StringBuffer sBuffer = new StringBuffer();
	    int appendLength = maxLength - strLength;
	    while (appendLength > 0) {
	      sBuffer.append(appendChar);
	      appendLength--;
	    }
	    appendLength = maxLength - strLength;
	    int tmpLength = sBuffer.length();
	    if (tmpLength > appendLength) {
	      sBuffer.delete(appendLength, tmpLength);
	    }
	    if (isAppendToLeft) {
	      return sBuffer.toString() + sourceStr;
	    }
	    return sourceStr + sBuffer.toString();
	  }

	  public static String htmlDecode(String sourceStr)
	  {
	    if (isNull(sourceStr)) {
	      return "";
	    }
	    sourceStr = sourceStr.replaceAll("&amp;", "&")
	      .replaceAll("&lt;", "<")
	      .replaceAll("&gt;", ">")
	      .replaceAll("&quot;", "\"")
	      .replaceAll("&#39;", "'")
	      .replaceAll("&nbsp; &nbsp; ", "\t")
	      .replaceAll("<p></p>", "\r\n\r\n")
	      .replaceAll("<br/>", "\r\n");
	    return sourceStr;
	  }

	  public static String htmlEncode(String sourceStr) {
	    if (isNull(sourceStr)) {
	      return "";
	    }
	    sourceStr = sourceStr.replaceAll("&", "&amp;")
	      .replaceAll("<", "&lt;")
	      .replaceAll(">", "&gt;")
	      .replaceAll("\"", "&quot;")
	      .replaceAll("'", "&#39;")
	      .replaceAll(" ", "&nbsp;")
	      .replaceAll("\t", "&nbsp; &nbsp; ")
	      .replaceAll("\r\n\r\n", "<p></p>")
	      .replaceAll("\r\n", "<br/>")
	      .replaceAll("\r", "<br/>")
	      .replaceAll("\n", "<br/>");
	    return sourceStr;
	  }

	  public static String textEncode(String sourceStr) {
	    if (isNull(sourceStr)) {
	      return "";
	    }
	    sourceStr = sourceStr.replaceAll("&", "&amp;")
	      .replaceAll("<", "&lt;")
	      .replaceAll(">", "&gt;")
	      .replaceAll("\"", "&quot;")
	      .replaceAll("'", "&#39;");
	    return sourceStr;
	  }



	  public static String formatYuanToFen(String amt)
	  {
	    return formatYuanToFen(Double.parseDouble(amt));
	  }

	  public static String formatYuanToFen(double amt)
	  {
	    return String.valueOf(Math.round(amt * 100.0D));
	  }


	  public static String fill(String str, int length) {
	    return fillLeft(str, length, '0');
	  }

	  public static String fillLeft(String str, int length, char c)
	  {
	    StringBuffer buffer = new StringBuffer("");

	    if (str == null) {
	      str = "";
	    }

	    if (length <= str.length()) {
	      return str;
	    }

	    int strLen = length - str.length();

	    for (int i = 0; i < strLen; i++) {
	      buffer.append(c);
	    }

	    buffer.append(str);
	    return buffer.toString();
	  }
}
