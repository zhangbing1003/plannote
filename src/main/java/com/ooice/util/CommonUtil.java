package com.ooice.util;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 通用工具类
 * @author Administrator
 *
 */
public class CommonUtil {
	
	public static String contextPath = "";
	public static final String DATE_REG = "^[0-9]{4}-(0[1-9]|1[0-2]|[1-9]){1}-(0[1-9]|1[0-9]|2[0-9]|3[0-1])$";
	
	/**
	 * 设置Ajax提交响应信息的相关属性
	 * @param response
	 */
	public static void setAjaxResponseAttr(HttpServletResponse response){
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
	}
	
	/**
	 * 获得运行环境路径
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getContextPath(){
		if (StringUtil.isNull(contextPath)) {
			URL url = CommonUtil.class.getResource("/");//获取该class所在的路径
			contextPath = URLDecoder.decode(url.toString());
			contextPath = contextPath.substring(6, contextPath.lastIndexOf("WEB-INF"));//截取第6个字符后到WEB-INF的路径
		}
		return contextPath;
	}
	/**
	 * 校验日期字符串是否是指定的格式(yyyy-MM-dd)
	 * @param pattern
	 * @return
	 */
	public static boolean checkDateStr(String dateStr){
		//创建模版
		Pattern pattern = Pattern.compile(DATE_REG);
		//创建匹配器
		return pattern.matcher(dateStr).find();
	}
	
	/**
	 * 将数字从字符串中分离 如：“111sfds”分离为 111 和 sfds，必须数字在前才能分离！
	 * @return
	 */
	public static String[] splitNumberFromString(String str){
		if (StringUtil.isNull(str)) {
			return null;
		}
		String [] result = new String[2];
		int index = 0;
		boolean needBreak = false;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != '.' && !(str.charAt(i) >= '0' && str.charAt(i) <= '9')) {
				index = i;
				needBreak = true;
				break;
			}
		}
		if (!needBreak) {
			index = str.length();
		}
		if (index == 0) {
			result[0] = "";
		}else {
			result[0] = str.substring(0,index);
		}
		result[1] = str.substring(index);
		return result;	
	}
	
	/**
	 * 用于删除Excel尾部空行
	 * @param list
	 * @return
	 */
	public static void removeNullFromList(List<?> list){
		if (list == null || list.size() < 1) {
			return;
		}
		for (int i = list.size() - 1; i >= 0; i--) {
			Object [] obj=(Object[])list.get(i);
			boolean needBreak = false;
			for (int j = 0; j < obj.length; j++) {
				if (obj[j] != null && !"".equals(obj[j].toString())) {
					needBreak = true;
					break;
				}
			}
			if (needBreak) {
				break;
			}
			list.remove(i);
		}
	}
	/**
	 * 用于删除Excel导入数据的注释行和空行
	 * @param list
	 * @return
	 */
	public static void removeNoteAndNullFromList(List<?> list){
		if (list != null && list.size()>0) {
			Object [] obj=(Object[])list.get(list.size() - 1);
			if (obj[0] != null && !"".equals(obj[0].toString()) && obj[0].toString().startsWith("注:①")) {
				list.remove(list.size() - 1);
			}
		}
		CommonUtil.removeNullFromList(list);
	}
	
	/**
	 * 下载文件
	 * @param path			欲下载的文件的路径
	 * @param response
	 */
	public static void download(String path, HttpServletResponse response) {
		try {
			File file = new File(path);
			// 取得文件名。
			String filename = file.getName();

			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(filename.getBytes("GBK"), "iso-8859-1"));
			response.addHeader("Content-Length", "" + file.length());
			response.setContentType("application/msexcel;charset=UTF-8");
			OutputStream toClient = new BufferedOutputStream(response
					.getOutputStream());
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 移除指定的字符串
	 * @author KCJ
	 * @param str			1,2,3,4,5
	 * @param target		2,4
	 * @param splitStr		,
	 * @return				1,3,5
	 */
	public static String removeStr(String str,String target,String splitStr){
		if (StringUtil.isNull(str)||StringUtil.isNull(target)||StringUtil.isNull(splitStr)) {
			return null;
		}
		String []oldStds = str.split(splitStr);
		String []newStds = target.split(splitStr);
		
		LinkedHashSet<String> set = new LinkedHashSet<String>();  
		Collections.addAll(set, oldStds);  
		
		for (String newstr : newStds) {
			set.remove(newstr);
		}
		return toStringBySplit(set.toArray(), splitStr);
	}
	/**
	 * 合并指定的字符串,不重复
	 * @author KCJ
	 * @param str			1,2,3,4,5
	 * @param target		2,4,6
	 * @param splitStr		,
	 * @return				1,2,3,4,5,6
	 */
	public static String mergeStr(String str,String target,String splitStr){
		if (StringUtil.isNull(splitStr)) {
			return null;
		}
		LinkedHashSet<String> set = new LinkedHashSet<String>(); 
		if (StringUtil.isNotNull(str)) {
			Collections.addAll(set, str.split(splitStr));  
		}
		if (StringUtil.isNotNull(target)) {
			Collections.addAll(set, target.split(splitStr));  
		}
		return toStringBySplit(set.toArray(), splitStr);
	}
	/**
	 * 将数组转化成特定分割的字符串
	 * @author KCJ
	 * @date 2013-4-13 下午01:38:22
	 * @param str
	 * @param splitStr
	 * @return
	 */
	public static String toStringBySplit(Object[] str,String splitStr){
		if (StringUtil.isNull(str)||StringUtil.isNull(splitStr)) {
			return null;
		}
		StringBuffer result = new StringBuffer();
		for (int i = 0,j = str.length; i < j; i++) {
			if (i == j - 1) {
				result.append(str[i]);
			}else {
				result.append(str[i]).append(",");
			}
		}
		return result.toString();
	}
	
	/**
	 * 移除重复的分割,,,,,,,,11,,,,,11112,321,321,,3213,321,3,21,,,321,, --> 11,11112,321,3213,3,21
	 * @author KCJ
	 * @date 2013-4-13 下午02:47:36
	 * @param str
	 * @param target
	 * @return
	 */
	public static String removeDuplicateSplit(String str,String split){
		if (StringUtil.isNull(str)||StringUtil.isNull(split)) {
			return null;
		}
		while (str.indexOf(split+split) != -1) {
			str = str.replaceAll(split+split, split);
		} 
		if (str.startsWith(split)) {
			str = str.length() == 1 ? null : str.substring(1);
		}
		if (StringUtil.isNull(str)) {
			return null;
		}
		if (str.endsWith(split)) {
			str = str.length() == 1 ? null : str.substring(0 , str.length() - 1);
		}
		if (StringUtil.isNull(str)) {
			return null;
		}
		LinkedHashSet<String> set = new LinkedHashSet<String>(); 
		String [] strs = str.split(split);
		for (String string : strs) {
			set.add(string);
		}
		return toStringBySplit(set.toArray(), split);
	}
	/**
	 * string经特定分割转化为list
	 * @author KCJ
	 * @date 2013-4-27 上午11:37:16
	 * @param str
	 * @param split
	 * @return
	 */
	public static List<String> strToList(String str,String split){
		if (StringUtil.isNull(str)||StringUtil.isNull(split)) {
			return null;
		}
		List<String> list = new ArrayList<String>(); 
		String [] strs = str.split(split);
		for (String string : strs) {
			list.add(string);
		}
		return list;
	}
	/**
	 * Obj转化成string
	 * @author KCJ
	 * @date 2013-4-26 下午01:30:31
	 * @param obj
	 * @param defaultValue
	 * @return
	 */
	public static String objectToString(Object obj,String defaultValue){
		return obj == null ? defaultValue : obj.toString();
	}
	/**
	 * Obj转化成string
	 * @author KCJ
	 * @date 2013-4-26 下午01:30:31
	 * @param obj
	 * @param defaultValue
	 * @return
	 */
	public static String objectToString(Object obj){
		return obj == null ? null : obj.toString();
	}
	/**
	 * 将字符串转化成IN SQL:如1,2,3 --> ('1','2','3')
	 * @author KCJ
	 * @date 2013-4-27 下午12:16:24
	 * @param str
	 * @return
	 */
	public static String stringToInSql(String str){
		if (StringUtil.isNull(str)) {
			return "('')";
		}else {
			return "('"+str.replaceAll(",", "','")+"')";
		}
	}
	
	/**
	 * 获得request请求IP
	 * @author KCJ
	 * @date 2013-5-30 上午10:47:34
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (StringUtil.isNull(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtil.isNull(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtil.isNull(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}     
	
	/**
	 * 将字符串str开始部分的字符串searchStr全部替换成自定的字符串replaceStr
	 * @author KCJ
	 * @date 2013-6-17 下午05:19:10
	 * @param str
	 * @param searchStr
	 * @param replaceStr
	 * @return
	 */
	public static String replaceAllFirst(String str, String searchStr, String replaceStr) {
		return str.replaceFirst("(" + searchStr + ")*", replaceStr);
	}     
	
	public static void main(String[] args) {
		System.out.println(replaceAllFirst("0000000001000000200000300004000000500000000","0",""));
	}
	
	/**
	 * 去除字符串字符前后全角、半角、tab
	 * @author ICE
	 * @param str
	 * @return
	 */
	public static String removeTab(String strValue){
			 
			         String str = "";
			         strValue.trim();
			         if (strValue!=null) {
			             Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			             Matcher m = p.matcher(strValue);
			             str = m.replaceAll("");
			         }
			         
        return str;
	}
	
	
	/**
	 * 规格型号关键字查询，将查询结果中的关键字用颜色标出
	 * @param str
	 * @param keyword
	 * @return
	 */
	public static String replaceKeyword(String str, String keyword) {
		if(StringUtil.isNull(keyword.trim())){
			return str;
		}
		LinkedHashSet<String> keySet = new LinkedHashSet<String>();
		for(String c : keyword.split(" ")){
			c = c.trim();
			if(StringUtil.isNotNull(c)){
				keySet.add(c);
			}
		}
		
		for(String key:keySet)
		{
			str = str.replaceAll(key,"<b style='background: #FFFF96;'>" + key + "</b>");
		}
		return str;
	}
	/**
	 * 高亮显示结果集 List类型
	 * @param maps
	 * @param keyword
	 * @param key
	 */
	public static void highlightKeyword(List<Map> maps, String keyword, String key) {
		if(StringUtil.isNull(keyword) || StringUtil.isNull(key)){
			return;
		}
		for(Map map:maps)
		{
			Object value = map.get(key);
			if(value != null)
			{
				map.put(key,replaceKeyword(value.toString(),keyword));
			}
		}
	}
	
	/**
	 * 多关键字查询的模糊匹配sql语句
	 * @param columnName
	 * @param keyword
	 * @return
	 */
	public static String toLikeSql(String columnName, String keyword) {
		if(StringUtil.isNull(keyword.trim())){
			return " ";
		}
		StringBuffer ret = new StringBuffer();
		String []keywords = keyword.split(" ");
		for(String c : keywords){
			c = c.trim();
			if(StringUtil.isNotNull(c)){
				ret.append(" AND " + columnName + " LIKE '%" + c + "%' ");
			}
		}
		return ret.toString();
	}
	
}
