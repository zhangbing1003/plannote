package com.ooice.util;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static final int[] addTypeAry = { 1, 2, 5, 10, 12, 13 };

	  public static long getIntervalMS(Calendar startday, Calendar endday)
	  {
	    if (startday.after(endday)) {
	      Calendar cal = startday;
	      startday = endday;
	      endday = cal;
	    }

	    long sl = startday.getTimeInMillis();
	    long el = endday.getTimeInMillis();

	    long ei = el - sl;

	    return ei;
	  }

	  public static int getIntervalMin(Calendar startday, Calendar endday)
	  {
	    return (int)(getIntervalMS(startday, endday) / 60000L);
	  }

	  public static int getIntervalMin(Date startday, Date endday)
	  {
	    return getIntervalMin(convertDate2Calendar(startday), convertDate2Calendar(endday));
	  }

	  public static int getIntervalHour(Calendar startday, Calendar endday)
	  {
	    return (int)(getIntervalMS(startday, endday) / 3600000L);
	  }

	  public static int getIntervalHour(Date startday, Date endday)
	  {
	    return getIntervalHour(convertDate2Calendar(startday), convertDate2Calendar(endday));
	  }

	  public static int getIntervalDays(Calendar startday, Calendar endday)
	  {
	    return (int)(getIntervalMS(startday, endday) / 86400000L);
	  }

	  public static int getIntervalDays(Date startday, Date endday)
	  {
	    return getIntervalDays(convertDate2Calendar(startday), convertDate2Calendar(endday));
	  }

	  public static int getDaysBetween(Calendar startday, Calendar endday)
	  {
	    if (startday.after(endday)) {
	      Calendar swap = startday;
	      startday = endday;
	      endday = swap;
	    }

	    int days = endday.get(6) - startday.get(6);
	    int y2 = endday.get(1);

	    if (startday.get(1) != y2) {
	      startday = (Calendar)startday.clone();
	      do {
	        days += startday.getActualMaximum(6);
	        startday.add(1, 1);
	      }while (startday.get(1) != y2);
	    }

	    return days;
	  }

	  public static int getDaysBetween(Date startday, Date endday)
	  {
	    return getDaysBetween(convertDate2Calendar(startday), convertDate2Calendar(endday));
	  }

	  public static int getIntervalWholeMonth(Calendar startday, Calendar endday)
	  {
	    if (startday.after(endday)) {
	      Calendar cal = startday;
	      startday = endday;
	      endday = cal;
	    }

	    endday.add(5, 1);

	    int intervalDay = endday.get(5) - startday.get(5);

	    if (intervalDay != 0) {
	      return -1;
	    }

	    int intervalMonth = endday.get(2) - startday.get(2);

	    int intervalYear = endday.get(1) - startday.get(1);

	    return intervalYear * 12 + intervalMonth;
	  }

	  public static int getIntervalWholeMonth(Date startday, Date endday)
	  {
	    return getIntervalWholeMonth(convertDate2Calendar(startday), convertDate2Calendar(endday));
	  }

	  public static int getIntervalWholeYear(Calendar startday, Calendar endday)
	  {
	    int intervalMonth = getIntervalWholeMonth(startday, endday);

	    if (intervalMonth == -1) {
	      return -1;
	    }

	    if (intervalMonth % 12 != 0) {
	      return -1;
	    }

	    return intervalMonth / 12;
	  }

	  public static int getIntervalWholeYear(Date startday, Date endday)
	  {
	    return getIntervalWholeYear(convertDate2Calendar(startday), convertDate2Calendar(endday));
	  }

	  public static int getDateByType(Date date, int dateType)
	  {
	    if ((date == null) || (Arrays.binarySearch(addTypeAry, dateType) < 0)) {
	      return -1;
	    }

	    return Calendar.getInstance().get(dateType);
	  }

	  public static int getYear(Date date)
	  {
	    return getDateByType(date, 1);
	  }

	  public static int getMonth(Date date)
	  {
	    return getDateByType(date, 2);
	  }

	  public static int getDayOfMonth(Date date)
	  {
	    return getDateByType(date, 5);
	  }

	  public static int getDayOfYear(Date date)
	  {
	    return getDateByType(date, 6);
	  }

	  public static Date addDateByType(Date date, int addType, int addCount)
	  {
	    if ((date == null) || (Arrays.binarySearch(addTypeAry, addType) < 0)) {
	      return null;
	    }

	    Calendar calr = convertDate2Calendar(date);

	    calr.add(addType, addCount);

	    return calr.getTime();
	  }

	  public static Date addYear(Date date, int addYear)
	  {
	    return addDateByType(date, 1, addYear);
	  }

	  public static Date addMonth(Date date, int addMonth)
	  {
	    return addDateByType(date, 2, addMonth);
	  }

	  public static Date addDay(Date date, int addDay)
	  {
	    return addDateByType(date, 5, addDay);
	  }

	  public static Date getDate(int year, int month, int day)
	  {
	    if ((year < 0) || (month <= 0) || (day < 0)) {
	      return null;
	    }

	    Calendar calr = Calendar.getInstance();

	    calr.set(1, year);
	    calr.set(2, month - 1);
	    calr.set(5, day);

	    return calr.getTime();
	  }

	  public static Date convertCalendar2Date(Calendar calr)
	  {
	    if (calr == null) {
	      return null;
	    }

	    return calr.getTime();
	  }

	  public static Calendar convertDate2Calendar(Date date)
	  {
	    if (date == null) {
	      return null;
	    }

	    Calendar calr = Calendar.getInstance();
	    calr.setTime(date);

	    return calr;
	  }

	  public static String formatDate(Date date, String pattern)
	  {
	    if (date == null) {
	      return "";
	    }

	    SimpleDateFormat sdf = null;
	    try
	    {
	      sdf = new SimpleDateFormat(pattern);
	    } catch (Exception e) {
	      sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    }

	    return sdf.format(date);
	  }

	  public static String formatDate(Date date)
	  {
	    if (date == null) {
	      return "";
	    }

	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    return sdf.format(date);
	  }

	  public static String formatTime(Date date)
	  {
	    if (date == null) {
	      return "";
	    }

	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    return sdf.format(date);
	  }

	  public static Date parseDate(String date, String pattern)
	  {
	    if ((date == null) || ("".equals(date.trim()))) {
	      return null;
	    }
	    try
	    {
	      SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	      return sdf.parse(date); } catch (Exception e) {
	    }
	    return null;
	  }

	  public static String getCurrentDate(String pattern)
	  {
	    return formatDate(Calendar.getInstance().getTime(), pattern);
	  }

	  public static String getCurrentDate()
	  {
	    return getCurrentDate("yyyy-MM-dd");
	  }

	  public static String getCurrentTime()
	  {
	    return getCurrentDate("yyyy-MM-dd HH:mm:ss");
	  }

	  public static Date getSpecifyDate(String dateStr)
	  {
	    return parseDate(dateStr, "yyyy-MM-dd");
	  }

	  public static Date getSpecifyTime(String dateStr)
	  {
	    return parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");
	  }

	  public static Date getCurrentCalendarTime()
	  {
	    return Calendar.getInstance().getTime();
	  }

	  public static String get86Date()
	  {
	    return formatDate(new Date(), "yyyyMMddHHmmss");
	  }

	  public static String get8Date()
	  {
	    return formatDate(new Date(), "yyyyMMdd");
	  }

	  public static boolean checkDateStr(String dateStr, String pattern)
	  {
	    if (StringUtil.isNull(pattern)) {
	      pattern = "yyyy-MM-dd";
	    }
	    return parseDate(dateStr, pattern) != null;
	  }
}
