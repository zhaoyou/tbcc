package org.tbcc.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.tbcc.biz.HisBoxBiz;
import org.tbcc.biz.HisStartUpBiz;
import org.tbcc.biz.ProjectBiz;
import org.tbcc.entity.TbccBaseHisBox;
import org.tbcc.entity.TbccBaseHisStartUp;
import org.tbcc.entity.TbccBaseUser;
import org.tbcc.entity.TbccPrjType;
import org.tbcc.util.BaseAction;

/**
 * 小批零历史action 接受 hisbox.do 的请求 extends baseAction
 * @author Administrator
 *
 */
public class HisBoxAction extends BaseAction {
		
	private HisBoxBiz hisboxBiz = null ;

	private HisStartUpBiz startupBiz = null ;
	
	private ProjectBiz proBiz = null ;
	
	private Logger logger = Logger.getLogger(HisBoxAction.class);
	
	public void setStartupBiz(HisStartUpBiz startupBiz) {
		this.startupBiz = startupBiz;
	}


	public void setProBiz(ProjectBiz proBiz) {
		this.proBiz = proBiz;
	}


	/**
	 * 由spring注入
	 * @param hisboxBiz
	 */
	public void setHisboxBiz(HisBoxBiz hisboxBiz) {
		this.hisboxBiz = hisboxBiz;
	}

	
	/**
	 * 跳转到历史小批零查询界面
	 */
	public ActionForward toBoxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String branchId = request.getParameter("branchId");
		
		//根据机构Id，获取所有的小批零项目
		List<TbccPrjType> boxList = this.hisboxBiz.getBoxPrjByBranchID(new Long(branchId));
		
		if(boxList!=null && boxList.size()>0)
			request.getSession().setAttribute("boxprjList", boxList);
		
		request.setAttribute("flag", 1) ;		//没有什么作用，控制界面显示
		
		return mapping.findForward("hisbox");
	}
	
	
	/**
	 *  根据条件查询小批零数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doBoxListByProperty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String startTime = request.getParameter("time1");
		String endTime = request.getParameter("time2");
		String proId  = request.getParameter("proId");
		String timeType = request.getParameter("timeType");
		String timeValue = request.getParameter("timevalue");
		
		if(startTime==null || endTime==null || proId==null || timeType==null || timeValue==null||
				startTime=="" || endTime==""|| proId==""|| timeType=="" || timeValue==""){
			request.setAttribute("msg", "请确保信息的完整!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (doBoxListByProperty)传递了非法的参数列表");
			return mapping.findForward("hisbox") ;
		}
		//获取所有的移动车载的历史数据
		List<TbccBaseHisBox> hisboxList = this.hisboxBiz.getByProperty(proId, startTime, endTime, timeType, timeValue);
		
		request.setAttribute("hisboxList", hisboxList);
		//获取统计信息
		if(hisboxList!=null && hisboxList.size()>0){
			Object[] result = this.hisboxBiz.getCalcValue(hisboxList);
			
			request.setAttribute("min", result[0]);
			request.setAttribute("max", result[1]); 
			request.setAttribute("avg", result[2]);
			
			
		}
		return mapping.findForward("hisbox");
	}
	
	//***************************上面是第一种实现方式、下面是通过启停记录去查询的******************************
	
	
	/**
	 * 转到小批零的启停记录查询页面
	 */
	public ActionForward toHisBoxStart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String branchId = request.getParameter("branchId");
		
		//根据机构Id，获取所有的小批零项目
		List<TbccPrjType> boxList = this.hisboxBiz.getBoxPrjByBranchID(new Long(branchId));
		
		if(boxList!=null && boxList.size()>0)
			request.getSession().setAttribute("boxprjList", boxList);
		
		request.setAttribute("flag", 1) ;		//没有什么作用，控制界面显示
		
		return mapping.findForward("hboxStart");
	}
	
	
	/**
	 * 转到小批零查询页面(根据启停记录)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toHisBoxByStart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String branchId = request.getParameter("branchId");
		String proId = request.getParameter("proId");
		String sid = request.getParameter("sid") ;
		
		TbccPrjType project = this.proBiz.getByProId(proId);
		
		TbccBaseHisStartUp startup = this.startupBiz.getStartUp(proId, Long.parseLong(sid));
		
		if(branchId==null || branchId=="" || project==null || startup==null){
			request.setAttribute("errorMsg", "参数非法或不存在!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toHisBoxByStart)传递了非法的分支标示或工程标示") ;
			throw new Exception("请求的资源无效!");
		}
			
		
		request.setAttribute("project", project);
		
		request.setAttribute("startup", startup);
		
		request.setAttribute("flag", 1);
			
		return mapping.findForward("hisbox2") ;
	}
	
	/**
	 * 根据条件、和启停记录查询小批零的历史数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doHisBoxByStart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String startTime = request.getParameter("time1");
		String endTime = request.getParameter("time2");
		String proId  = request.getParameter("proId");
		String interval = request.getParameter("interval");
		String timeValue = request.getParameter("timevalue");
		
		if(startTime==null || endTime==null || proId==null || interval==null || timeValue==null||
				startTime=="" || endTime==""|| proId==""|| interval=="" || timeValue==""){
			request.setAttribute("msg", "请确保信息的完整!");
			logger.error( ((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (doHisBoxByStart)传递了非法的参数列表!");
			return mapping.findForward("hisbox2") ;
		}
		//获取所有的移动车载的历史数据
		List<TbccBaseHisBox> hisboxList = this.hisboxBiz.getByProperty(proId, startTime, endTime, interval, timeValue);
		
		request.setAttribute("hisboxList", hisboxList);
		//获取统计信息
		if(hisboxList!=null && hisboxList.size()>0){
			Object[] result = this.hisboxBiz.getCalcValue(hisboxList);
			
			request.setAttribute("min", result[0]);
			request.setAttribute("max", result[1]); 
			request.setAttribute("avg", result[2]);
			
			
		}
		return mapping.findForward("hisbox2") ;
	}
	
	/**
	 * 跳转的小批零历史曲线显示页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toCurve(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		return mapping.findForward("boxCurve");
	}
	
	
	
	
	
	
	
	
}
