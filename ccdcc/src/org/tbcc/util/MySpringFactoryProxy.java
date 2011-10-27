package org.tbcc.util;

import org.springframework.context.ApplicationContext;

/**
 * 这是自定义spring容器代理对象类
 * @author Administrator
 *
 */
public class MySpringFactoryProxy {
	
	private static  ApplicationContext applicationcontext = null ;
	
	private MySpringFactoryProxy(){}
	
	
	public static void setContext(ApplicationContext context){
		if(applicationcontext==null)
			applicationcontext = context ;
	}
	
	public static  ApplicationContext getContext(){
		return applicationcontext ;
	}
}
