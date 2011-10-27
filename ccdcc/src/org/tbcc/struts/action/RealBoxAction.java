package org.tbcc.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.tbcc.util.BaseAction;

/**
 * ����ʵʱС����action ���� realbox.do ���� extends  BaseAction
 * @author Administrator
 *
 */
public class RealBoxAction extends BaseAction {

	
	/**
	 * ��ת��С�����ʵʱ����ҳ��
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
