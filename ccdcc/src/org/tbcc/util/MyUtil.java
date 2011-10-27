package org.tbcc.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.tbcc.entity.TbccBaseHisCar;



/**
 * 这是一个通用的类，用来解析时间字符串、构造时间函数等等
 * @author Administrator
 *
 */
public class MyUtil {
	
	private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static SimpleDateFormat sf_hao =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	
	/**
	 * 日期对象转化成yyyy-MM-dd HH:mm:ss.SSS格式
	 * @param date
	 * @return
	 */
	public static String getToString_hao(Date date){
		Calendar  c = Calendar.getInstance() ;
		c.setTime(date);
		if(c.get(Calendar.YEAR)<=1970||c.get(Calendar.YEAR)>=9999)
			return "--------" ;
		return sf_hao.format(date);
	}
	
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
	 * 把yyyy-MM-dd HH:mm:ss.SSS格式的字符串解析成Date
	 * @param source
	 * @return
	 */
	public static Date getToDate_hao(String source){
		try {
			return  sf_hao.parse(source);
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return null ;
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
	
	/**
	 * 判断启停记录的开始时间是否合法
	 * @param startTime
	 * @return
	 */
	public static  String getValid(String startTime){
		
		Date d = null ;
		int s = 0  ;
		//SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
		try {
			d =sf.parse(startTime) ;
		} catch (Exception e) {
			System.out.println("不是一个有效的日期字符串");
			return	sf.format(new java.util.Date()) ;
		}
		Calendar c = Calendar.getInstance() ;
		c.setTime(d) ;
		s = c.get(Calendar.SECOND) ;
		if(s%10!=0){
			d.setTime(d.getTime()+(10-s%10)*1000);
		}
		
		return sf.format(d) ;
			
		/**
		 * 		原先的实现方式，有一定的局限性
		
		Date d = MyUtil.getToDate(startTime) ;
		Calendar  ca = Calendar.getInstance() ;
		ca.setTime(d);
		int i = ca.get(Calendar.SECOND );	
		 //这里的几率好小了呀，哈哈。只判断到了小时了	，没有判断到月底了
		int seconds = ca.get(Calendar.SECOND);
		int minute = ca.get(Calendar.MINUTE);
		int hour = ca.get(Calendar.HOUR_OF_DAY);
		int day = ca.get(Calendar.DAY_OF_MONTH);
			
		if(i%10==0)
			return startTime ;
		else 	//如果秒数不是10的整数倍
		{		
			if(i>50){		//秒数大于50，分钟要加1 了		
				if(minute==59){ //如果分钟等于59，则要加1小时了		
					if(hour==23){ //如果小时是23点，则分钟、小时、秒数都置为0			
						ca.set(Calendar.DAY_OF_MONTH, day+1);
						ca.set(Calendar.HOUR_OF_DAY, 0);
						ca.set(Calendar.MINUTE, 0);
						ca.set(Calendar.SECOND, 0);				
					}else{
					ca.set(Calendar.HOUR_OF_DAY,hour+1);
					ca.set(Calendar.MINUTE, 0);
					ca.set(Calendar.SECOND, 0);	
					}
				}else{
					ca.set(ca.MINUTE, minute+1);
					ca.set(Calendar.SECOND, 0);				
				}			
			}else{	
			i = (i/10)*10 +10;	 
			ca.set(Calendar.SECOND, i);
			}	
			 return MyUtil.getToString(ca.getTime());
		}
		 */
	}
	
//	public static void main(String[] args) throws Exception {
//		Date s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("9999-12-31 23:59:59");
//		Calendar c = Calendar.getInstance() ;
//		c.setTime(s);
//		System.out.println(c.get(Calendar.YEAR));
//	}
	
	public static  List<TbccBaseHisCar> distinctData(List<TbccBaseHisCar> list){
		if(list==null || list.size()==0)
			return  list;
		
		Map<Long,TbccBaseHisCar> tempMap = new HashMap<Long, TbccBaseHisCar>();
		
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			TbccBaseHisCar hiscar = (TbccBaseHisCar) iterator.next();
			if(tempMap.containsKey(hiscar.getUpdateTime().getTime()))
				iterator.remove();
			else 
				tempMap.put(hiscar.getUpdateTime().getTime(), hiscar);
		}
		
		return list ;
	}
}
