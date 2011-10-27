package org.tbcc.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tbcc.biz.HqBiz;

public class TreeTest {
	
	
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext-dao.xml",
				"applicationContext-biz.xml","applicationContext-action.xml"}) ;
		
		HqBiz hqbiz = (HqBiz)context.getBean("hqBiz");
		System.out.println(hqbiz.getHqBranchTree(new Long(33)));
//		for(int i=1;i<14;i++){
//			System.out.println("**************总部标识为"+ i+"*************");
//			System.out.println(hqbiz.getHqBranchTree(new Long(i)));
//			
//		}
	}
}
