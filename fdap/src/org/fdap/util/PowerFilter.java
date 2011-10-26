package org.fdap.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 这是用来处理直接访问jsp页面的过滤器
 * @author zhaoyou
 *
 */
public class PowerFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain arg2) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)req ;
		String uri = request.getRequestURI() ;
		String name = uri.substring(uri.lastIndexOf("/"));
		if( name.equals("/") || name.equals("/index.jsp") || name.equals("/404.jsp") || name.equals("/500.jsp") ||  name.equals("/head.jsp") || name.equals("/footer.jsp")||name.equals("/image.jsp")||name.equals("/ditu.jsp")||name.equals("/Test.jsp")||name.equals("/mapReal.jsp")){
			arg2.doFilter(req, res);
		}else{
			((HttpServletResponse)res).sendRedirect("/fdap/index.jsp");
			
		}
			
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
