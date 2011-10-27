package org.tbcc.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.tbcc.biz.HisStartUpBiz;
import org.tbcc.entity.TbccBaseHisStartUp;
import org.tbcc.entity.TbccBaseUser;
import org.tbcc.util.BaseAction;

/**
 * ���action���ڽ���startup.do ������ extends
 * ����������ͣ��¼��action
 * @author Administrator
 *
 */
public class StartUpAction extends BaseAction {

	
	private Logger logger = Logger.getLogger(StartUpAction.class);
	
	private HisStartUpBiz startUpBiz = null ;
	
	/**
	 * ��springע����ͣ������
	 * @param startUpBiz
	 */
	public void setStartUpBiz(HisStartUpBiz startUpBiz) {
		this.startUpBiz = startUpBiz;
	}

	/**
	 * ���������������ͣʱ�䣬����Ŀ��ȡ��ͣ��¼
	 */
	public ActionForward doStartUpCar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String proId = request.getParameter("proId");
		String time1 = request.getParameter("time1");
		String time2 = request.getParameter("time2");
		
		if(proId==null || proId=="" || time1==null || time1=="" || time2==null || time2==""){
			request.setAttribute("errorMsg", "����Ϊ�ջ�Ƿ�!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (doStartUpCar)����Ĳ���Ϊ�ջ�Ƿ�!");
			new Exception("ָ����Դ������...");
		}
		
		
		//����������ȡ��ͣ��¼
		List<TbccBaseHisStartUp> startList = this.startUpBiz.getStartUpListByTime(proId, time1, time2) ;// this.startUpBiz.getStartUpList(proId, time1, time2);
		
		request.setAttribute("startList", startList);
		
		
		request.setAttribute("flag", 1);
		return mapping.findForward("car_startup2");
	}
	
	/**
	 * �������������ͣʱ�䡢����Ŀ��ȡ��ͣ��¼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doStartUpBox(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String proId = request.getParameter("proId");
		String time1 = request.getParameter("time1");
		String time2 = request.getParameter("time2");
		
		if(proId==null || proId=="" || time1==null || time1=="" || time2==null || time2==""){
			request.setAttribute("errorMsg", "����Ϊ�ջ�Ƿ�!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (doStartUpBox)����Ĳ���Ϊ�ջ�Ƿ�!");
			new Exception("ָ����Դ������...");
		}
		
		
		//����������ȡ��ͣ��¼
		List<TbccBaseHisStartUp> startList = this.startUpBiz.getStartUpList(proId, time1, time2);
		
		request.setAttribute("startList", startList);
		
		return mapping.findForward("box_startup");
	}

}
