package org.tbcc.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *  这个是我自定义的spring容器(采用简易单例模式)，是为了解决flex 与 Spring集成的设计的
 * @author Administrator
 *
 */
public class MySpringFactory {	
	
	private static ApplicationContext context = null ;
	private static MySpringFactory springfactory = null ;
	
	/**
	 * 私有的构造函数
	 */
	private MySpringFactory(){	
		if(context==null)
			context = MySpringFactoryProxy.getContext() ;
			//context =new ClassPathXmlApplicationContext("applicationContext-*.xml") ;
	}
	
	/**
	 * 获取springfactory的实例
	 * @return
	 */
	public static MySpringFactory getInstance(){
		if(springfactory==null)
			springfactory = new MySpringFactory();
		
		return springfactory ;
	}
	
	/**
	 * 获取spring容器内的 Bean 
	 * @param beanName
	 * @return
	 */
	public Object getBean(String beanName){
		return context.getBean(beanName);
	}
}
