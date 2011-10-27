package org.tbcc.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * ����һ������Action�࣬���е�Action���̳и�DispatchAction�࣬����ʵ��Ȩ�޵Ŀ���
 * @author Administrator
 *
 */
public class BaseAction extends DispatchAction {

	/**
	 * ����ʵ��Ȩ�޵Ŀ���
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//���session����û�е��ڵ�½�û�����Ϣ����������½����
		if(request.getSession().getAttribute("LoginUser")==null){
			if((request.getQueryString()!=null && request.getQueryString().equals("ope=doLogin"))||request.getParameter("exeget")!=null){
				return super.execute(mapping, form, request, response);
			}
				return mapping.findForward("index");	
		}
		return super.execute(mapping, form, request, response);
	}
	
}
