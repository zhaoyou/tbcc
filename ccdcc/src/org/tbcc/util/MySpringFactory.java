package org.tbcc.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *  ��������Զ����spring����(���ü��׵���ģʽ)����Ϊ�˽��flex �� Spring���ɵ���Ƶ�
 * @author Administrator
 *
 */
public class MySpringFactory {	
	
	private static ApplicationContext context = null ;
	private static MySpringFactory springfactory = null ;
	
	/**
	 * ˽�еĹ��캯��
	 */
	private MySpringFactory(){	
		if(context==null)
			context = MySpringFactoryProxy.getContext() ;
			//context =new ClassPathXmlApplicationContext("applicationContext-*.xml") ;
	}
	
	/**
	 * ��ȡspringfactory��ʵ��
	 * @return
	 */
	public static MySpringFactory getInstance(){
		if(springfactory==null)
			springfactory = new MySpringFactory();
		
		return springfactory ;
	}
	
	/**
	 * ��ȡspring�����ڵ� Bean 
	 * @param beanName
	 * @return
	 */
	public Object getBean(String beanName){
		return context.getBean(beanName);
	}
}
