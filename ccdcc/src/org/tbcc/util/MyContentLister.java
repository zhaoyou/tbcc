package org.tbcc.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Web容器启动监听器
 * @author Administrator
 *
 */
public class MyContentLister implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void contextInitialized(ServletContextEvent arg0) {
		ApplicationContext springContent = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
		MySpringFactoryProxy.setContext(springContent) ;							//设置容器
		LogoOperate.setPrefixPath(arg0.getServletContext().getRealPath("/"));		//设置项目所在的物理路径
	}

}
