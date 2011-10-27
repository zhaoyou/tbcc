package org.tbcc.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.tbcc.biz.HqBiz;
import org.tbcc.entity.TbccBaseUser;
import org.tbcc.util.BaseAction;

/**
 * ���action ���� hq.do���� extends baseAction
 * @author Administrator
 *
 */
public class HqAction extends BaseAction {

	private HqBiz hqBiz = null ;
	
	private Logger logger = Logger.getLogger(HqAction.class);
	
	public void setHqBiz(HqBiz hqBiz) {
		this.hqBiz = hqBiz;
	}

	/**
	 * ��½�������ܲ��û����ô˷���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toListByHq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String hqId = request.getParameter("hqId") ;
		
		if(hqId==null || hqId.equals(""))
		{
			request.setAttribute("msg", "�û���¼��Ϣ����!");
			logger.equals(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toListByHq)������һ���Ƿ����ܲ���ʶ!");
			return mapping.findForward("index");
		}
		
		String hqbranchTree = hqBiz.getHqBranchTree(new Long(hqId));
		request.setAttribute("tree", hqbranchTree);
		
		return mapping.findForward("hqbranch");
	}
	
}
