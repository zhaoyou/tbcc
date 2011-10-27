package org.tbcc.util;

import java.util.Calendar;
import java.util.Date;

public class MyTest {
	public static void main(String[] args) {
		String str = "2008-03-12 23:59:59";
		//System.out.println(test(str));
		//test3();
//		String[]data = new String[]{"yiren","goods~"};
//		test4(data);
//		System.out.println(data[0]+data[1]);
//		System.out.println("OK!");
		test5() ;
	}
	
	public static String test(String str){
		Date d = MyUtil.getToDate(str) ;
		Calendar  ca = Calendar.getInstance() ;
		ca.setTime(d);
	
		int i = ca.get(Calendar.SECOND );
		
		 //这里的几率好小了呀，哈哈。只判断到了小时了	
		int seconds = ca.get(Calendar.SECOND);
		int minute = ca.get(Calendar.MINUTE);
		int hour = ca.get(Calendar.HOUR_OF_DAY);
		int day = ca.get(Calendar.DAY_OF_MONTH);
			
		if(i%10==0)
			return str ;
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
	}
	
	public static void test2(){
		String a = "12345.9";
		String b = "5678.89" ;
		System.out.println(a.indexOf("."));
		
	}
	
	public static int test3(){
		Calendar  c = Calendar.getInstance();
		System.out.println(c.get(Calendar.YEAR));
		return 0 ;
	}
	
	public static void test4(String[]data){
		data[0] = "zhao" ;
		data[1] = "you" ;
	}
	
	public static void test5(){
//		double str = 31232.75 ;
//		int du =(int)str/100 ;
//		double fen = str -du*100 ;
//		System.out.println("du "+du + " fen :"+fen);
		
		String a = "abcde" ;
		System.out.println(a.substring(0,5));
		
	}
}
