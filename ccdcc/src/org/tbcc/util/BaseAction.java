package org.tbcc.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * 这是一个基础Action类，所有的Action都继承该DispatchAction类，用来实现权限的控制
 * @author Administrator
 *
 */
public class BaseAction extends DispatchAction {

	/**
	 * 用来实现权限的控制
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//如果session里面没有当期登陆用户的信息，则跳到登陆界面
		if(request.getSession().getAttribute("LoginUser")==null){
			if((request.getQueryString()!=null && request.getQueryString().equals("ope=doLogin"))||request.getParameter("exeget")!=null){
				return super.execute(mapping, form, request, response);
			}
				return mapping.findForward("index");	
		}
		return super.execute(mapping, form, request, response);
	}
	
}
