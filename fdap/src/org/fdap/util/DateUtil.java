package org.fdap.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期通用操作类
 * @author zhaoyou
 *
 */
public class DateUtil {
private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 日期对象转化成yyyy-MM-dd HH:mm:ss格式
	 * @param date
	 * @return
	 */
	public static String getToString(Date date){
		Calendar  c = Calendar.getInstance() ;
		c.setTime(date);
		if(c.get(Calendar.YEAR)<=1970||c.get(Calendar.YEAR)>=9999)
			return "--------" ;
		return sf.format(date);
	}
	
	/**
	 * 把yyyy-MM-dd HH:mm:ss格式的字符串解析成Date
	 * @param source
	 * @return
	 */
	public static Date getToDate(String source){
		try {
			return  sf.parse(source);
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return null ;
	}
}
