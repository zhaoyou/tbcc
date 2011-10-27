package org.tbcc.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.tbcc.biz.HisCarAlarmBiz;
import org.tbcc.biz.HisStartUpBiz;
import org.tbcc.biz.ProjectBiz;
import org.tbcc.entity.TbccBaseHisCarAlarm;
import org.tbcc.entity.TbccBaseHisStartUp;
import org.tbcc.entity.TbccBaseUser;
import org.tbcc.entity.TbccPrjType;
import org.tbcc.util.BaseAction;

public class HisCarAlarmAction extends BaseAction {
	private Logger logger = Logger.getLogger(HisCarAction.class);
	private ProjectBiz proBiz = null;
	private HisStartUpBiz startupBiz = null;
	
	private HisCarAlarmBiz hiscaralarmbiz = null;
	
	public HisCarAlarmBiz getHiscaralarmbiz() {
		return hiscaralarmbiz;
	}

	public void setHiscaralarmbiz(HisCarAlarmBiz hiscaralarmbiz) {
		this.hiscaralarmbiz = hiscaralarmbiz;
	}

	public ProjectBiz getProBiz() {
		return proBiz;
	}

	public HisStartUpBiz getStartupBiz() {
		return startupBiz;
	}

	public void setProBiz(ProjectBiz proBiz) {
		this.proBiz = proBiz;
	}

	public void setStartupBiz(HisStartUpBiz startupBiz) {
		this.startupBiz = startupBiz;
	}

	public ActionForward toHisCarAlarm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String branchId = request.getParameter("branchId");
		String proId = request.getParameter("proId");
		String sid = request.getParameter("sid") ;
		
		//验证传递的分支标示是否正确
		if(branchId==null || branchId.equals("")){
			request.setAttribute("errorMsg", "请求参数为空或非法!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toHisCarAlarm)传递了非法的参数列表");
			throw new Exception("请求的资源无效!");
		}
		
		//验证传递的工程标示、起停记录标示是否正确
		TbccPrjType project = this.proBiz.getByProId(proId);		//获取要查询的项目
		
		TbccBaseHisStartUp startup = this.startupBiz.getStartUp(proId, Long.parseLong(sid));		//获取选择的启停记录
		
		if(branchId==null || branchId=="" || project==null || startup==null){
			request.setAttribute("errorMsg", "请求参数为空或非法!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toHisCarAlarm)传递了非法的参数列表");
			throw new Exception("请求的资源无效!");
		}
		request.setAttribute("project", project);		
		
		request.setAttribute("startup", startup);		
		
		return mapping.findForward("hiscaralarm") ;
	}

	public ActionForward doHisCarAlarm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String startTime = request.getParameter("time1");
		String endTime = request.getParameter("time2");
		String proId  = request.getParameter("proId");
		String sid = request.getParameter("sid") ;	
		
		Long sidvalue_long = null ;
		
		if(startTime==null || endTime==null || proId==null || sid==null ||
			startTime=="" || endTime=="" || proId=="" ||  sid.equals("")){
			request.setAttribute("msg", "请确保信息的完整!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (doHisCarByStart)获取历史数据，传递了非法的参数列表!");
			return mapping.findForward("hcarlist2") ;
		}
		
		try {
			sidvalue_long = Long.parseLong(sid) ;
		} catch (Exception e) {
			request.setAttribute("msg", "传递了非法参数!") ;
			throw e ;
		}
		
		//获取车载历史报警
		//List<TbccBaseHisCar> carList =hiscaralarmbiz.insertAndGetHisCarAlarm(proId, startTime, endTime, timevalue_long);
		//查询的同时判断处理历史数据，获取历史报警数据并插入表
		List<TbccBaseHisCarAlarm> caralarmList =hiscaralarmbiz.insertAndGetHisCarAlarm(proId, startTime, endTime, sidvalue_long);
		if(caralarmList!=null && caralarmList.size()>0){
			request.setAttribute("hiscaralarmList", caralarmList);  //存入数据集
		}
		return mapping.findForward("hiscaralarm");
	}

	public ActionForward doSaveCarHisAlarm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String startTime = request.getParameter("time1");
		String endTime = request.getParameter("time2");
		String proId  = request.getParameter("proId");
		String sid = request.getParameter("sid") ;	
		
		String saveStr = request.getParameter("saveStr");
		
		Long sidvalue_long = null ;
		
		if(startTime==null || endTime==null || proId==null || sid==null || saveStr==null||
			startTime=="" || endTime=="" || proId=="" ||  sid.equals("") || saveStr.equals("")){
			request.setAttribute("msg", "请确保信息的完整!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (doHisCarByStart)获取历史数据，传递了非法的参数列表!");
			return mapping.findForward("hcarlist2") ;
		}
		
		try {
			sidvalue_long = Long.parseLong(sid) ;
		} catch (Exception e) {
			request.setAttribute("msg", "传递了非法参数!") ;
			throw e ;
		}
		
		//更新车载历史报警处理信息
		if(hiscaralarmbiz.updateHisCarAlarm(saveStr)){
			List<TbccBaseHisCarAlarm> caralarmList =hiscaralarmbiz.getHisCarAlarm(proId, sidvalue_long);
			if(caralarmList!=null && caralarmList.size()>0){
				request.setAttribute("hiscaralarmList", caralarmList);  //存入数据集
			}
		}else{
			System.out.println("更新车载历史报警处理信息失败");
			request.setAttribute("msg", "更新车载历史报警处理信息失败");
		}
		
		return mapping.findForward("hiscaralarm");
	}
	
}
