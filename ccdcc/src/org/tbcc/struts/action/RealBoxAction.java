package org.tbcc.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.tbcc.util.BaseAction;

/**
 * 这是实时小批零action 接受 realbox.do 请求 extends  BaseAction
 * @author Administrator
 *
 */
public class RealBoxAction extends BaseAction {

	
	/**
	 * 跳转到小批零的实时数据页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toRealData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("realbox");
	}
	
	
}
