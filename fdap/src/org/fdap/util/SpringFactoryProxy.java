package org.fdap.util;

import org.springframework.context.ApplicationContext;

/**
 * spring容器代理类，用于实现spring容器的共享
 * @author zhaoyou
 *
 */
public class SpringFactoryProxy {
	
		private static SpringFactoryProxy factory = null ;
		
		private static  ApplicationContext context = null ;
		
		private SpringFactoryProxy(){
			
		}
		
		public static void setSpringContext(ApplicationContext applicationContext){
			context = applicationContext ;
		}
		
		public static SpringFactoryProxy getInstance(){
			if(factory==null)
				factory = new SpringFactoryProxy();
			return factory  ;
		}
		
		public Object getBeanObject(String beanName){
			if(context!=null)
				return context.getBean(beanName);
			return null; 
		}
}
