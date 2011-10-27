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
 * 这个action用于接收startup.do 的请求 extends
 * 用来操作启停记录的action
 * @author Administrator
 *
 */
public class StartUpAction extends BaseAction {

	
	private Logger logger = Logger.getLogger(StartUpAction.class);
	
	private HisStartUpBiz startUpBiz = null ;
	
	/**
	 * 由spring注入启停操作类
	 * @param startUpBiz
	 */
	public void setStartUpBiz(HisStartUpBiz startUpBiz) {
		this.startUpBiz = startUpBiz;
	}

	/**
	 * 这个方法，根据启停时间，和项目获取启停记录
	 */
	public ActionForward doStartUpCar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String proId = request.getParameter("proId");
		String time1 = request.getParameter("time1");
		String time2 = request.getParameter("time2");
		
		if(proId==null || proId=="" || time1==null || time1=="" || time2==null || time2==""){
			request.setAttribute("errorMsg", "参数为空或非法!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (doStartUpCar)请求的参数为空或非法!");
			new Exception("指定资源不存在...");
		}
		
		
		//根据条件获取启停记录
		List<TbccBaseHisStartUp> startList = this.startUpBiz.getStartUpListByTime(proId, time1, time2) ;// this.startUpBiz.getStartUpList(proId, time1, time2);
		
		request.setAttribute("startList", startList);
		
		
		request.setAttribute("flag", 1);
		return mapping.findForward("car_startup2");
	}
	
	/**
	 * 这个方法根据启停时间、和项目获取启停记录
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
			request.setAttribute("errorMsg", "参数为空或非法!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (doStartUpBox)请求的参数为空或非法!");
			new Exception("指定资源不存在...");
		}
		
		
		//根据条件获取启停记录
		List<TbccBaseHisStartUp> startList = this.startUpBiz.getStartUpList(proId, time1, time2);
		
		request.setAttribute("startList", startList);
		
		return mapping.findForward("box_startup");
	}

}
