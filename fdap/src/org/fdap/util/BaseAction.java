package org.fdap.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * 这是一个通用的action继承DispatchAction,用来控制权限控制
 * @author zhaoyou
 *
 */
public class BaseAction extends DispatchAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(request.getSession().getAttribute("loginUser")==null){
			String ope = request.getParameter("ope");
			if(ope!=null && (ope.equals("doLogin")||ope.equals("toMapInfo")))
				return super.execute(mapping, form, request, response);
			return mapping.findForward("index");
		}
		
		return super.execute(mapping, form, request, response);
	}

	
}
