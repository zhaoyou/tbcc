package org.tbcc.test;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tbcc.log.LogOperate;

/**
 * »’÷æ≤‚ ‘
 * @author zhaoyou
 *
 */
public class LogTest {
	
	private static String a[]	 = { "applicationContext-dao.xml", "applicationContext-biz.xml", "applicationContext-action.xml" } ;
	
	private static  ApplicationContext context = new ClassPathXmlApplicationContext(a);
	
	private static LogOperate logOperate = (LogOperate)context.getBean("logOperate") ;
	
	public static void main(String[] args) throws Exception {				
		test2();
	}
	
	public static  void test2(){
		System.out.println("ni");
		System.out.println("n22");
	}
	
	
	public static void testAddLog() throws Exception {
		String uid = null ;
		String uname = null ;
		String opType = null ;
		String opModule = null ;
		Date opTime =null ;
		String message = null ;
		String machineName = "localhost";
		String ext01 = null ;
		String ext02 = null ;
		String ext03 = null ;
		
		System.out.println(logOperate.addLog(uid, uname, opType, 
				opModule, message, machineName, ext01, ext02, ext03));
		
	}
	
	public static void testLogJar(){
		
	}
}
