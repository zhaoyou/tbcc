package org.fdap.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 监听容器的启动
 * @author zhaoyou
 *
 */
public class ContextStartListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
	}

	/**
	 * 在容器启动时，把spring容器的实例引用，赋值给dwr 的一个变量引用
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		SpringFactoryProxy.setSpringContext(WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext()));
	}

}
