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
 * ����һ��ͨ�õ��࣬��������ʱ���ַ���������ʱ�亯���ȵ�
 * @author Administrator
 *
 */
public class MyUtil {
	
	private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static SimpleDateFormat sf_hao =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	
	/**
	 * ���ڶ���ת����yyyy-MM-dd HH:mm:ss.SSS��ʽ
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
	 * ���ڶ���ת����yyyy-MM-dd HH:mm:ss��ʽ
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
	 * ��yyyy-MM-dd HH:mm:ss.SSS��ʽ���ַ���������Date
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
	 * ��yyyy-MM-dd HH:mm:ss��ʽ���ַ���������Date
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
	 * �ж���ͣ��¼�Ŀ�ʼʱ���Ƿ�Ϸ�
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
			System.out.println("����һ����Ч�������ַ���");
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
		 * 		ԭ�ȵ�ʵ�ַ�ʽ����һ���ľ�����
		
		Date d = MyUtil.getToDate(startTime) ;
		Calendar  ca = Calendar.getInstance() ;
		ca.setTime(d);
		int i = ca.get(Calendar.SECOND );	
		 //����ļ��ʺ�С��ѽ��������ֻ�жϵ���Сʱ��	��û���жϵ��µ���
		int seconds = ca.get(Calendar.SECOND);
		int minute = ca.get(Calendar.MINUTE);
		int hour = ca.get(Calendar.HOUR_OF_DAY);
		int day = ca.get(Calendar.DAY_OF_MONTH);
			
		if(i%10==0)
			return startTime ;
		else 	//�����������10��������
		{		
			if(i>50){		//��������50������Ҫ��1 ��		
				if(minute==59){ //������ӵ���59����Ҫ��1Сʱ��		
					if(hour==23){ //���Сʱ��23�㣬����ӡ�Сʱ����������Ϊ0			
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
