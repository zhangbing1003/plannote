package com.ooice.util;

import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

public class Ch2Pinyin {
	public static String convertChineseToInitialOfPinyinExceptOtherChar(String source)
	  {
	    return convertChineseToPinyin(source, false, false);
	  }

	  public static String convertChineseToInitialOfPinyin(String source)
	  {
	    return convertChineseToPinyin(source, false, true);
	  }

	  public static String convertChineseToPinyinExceptOtherChar(String source)
	  {
	    return convertChineseToPinyin(source, true, false);
	  }

	  public static String convertChToPinyin(String source)
	  {
	    return convertChineseToPinyin(source, true, true);
	  }

	  public static String convertChineseToPinyin(String source, boolean isQuanPin, boolean includeOtherChar)
	  {
	    if ((source == null) || ("".equals(source))) {
	      return "";
	    }

	    char[] charArray = source.toCharArray();
	    String pinyin = "";
	    HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
	    format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
	    format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
	    format.setVCharType(HanyuPinyinVCharType.WITH_V);

	    for (char ch : charArray) {
	      try
	      {
	        if (isQuanPin)
	          pinyin = pinyin + net.sourceforge.pinyin4j.PinyinHelper.toHanyuPinyinStringArray(ch, format)[0];
	        else
	          pinyin = pinyin + net.sourceforge.pinyin4j.PinyinHelper.toHanyuPinyinStringArray(ch, format)[0].substring(0, 1);
	      }
	      catch (Exception e)
	      {
	        if (includeOtherChar) {
	          pinyin = pinyin + ch;
	        }
	      }
	    }

	    return pinyin;
	  }

	  public static void main(String[] args) {
	  }
}
