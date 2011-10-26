package org.fdap.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.fdap.biz.OrgBiz;
import org.fdap.biz.RealAlarmBiz;
import org.fdap.entity.Fdaporg;
import org.fdap.entity.Fdapproject;
import org.fdap.util.BaseAction;

/**
 * 实时报警action
 * @author zhaoyou
 *
 */
public class RealAlarmAction extends BaseAction {

	private Logger logger = Logger.getLogger(RealAlarmAction.class);
	
	private RealAlarmBiz realAlarmBiz = null ;
	
	private OrgBiz orgBiz = null ;
	
	
	
	public OrgBiz getOrgBiz() {
		return orgBiz;
	}

	public void setOrgBiz(OrgBiz orgBiz) {
		this.orgBiz = orgBiz;
	}

	public RealAlarmBiz getRealAlarmBiz() {
		return realAlarmBiz;
	}

	public void setRealAlarmBiz(RealAlarmBiz realAlarmBiz) {
		this.realAlarmBiz = realAlarmBiz;
	}

	/**
	 * 跳转到实时报警页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toRealAlarm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//验证参数
		String oid = request.getParameter("oid") ;
		if(oid==null || oid.equals("")){
				logger.warn("传递了非法参数oid");
				throw new Exception("传递了非法的参数oid");
		}
		
		
		//保存企业的信息，用于显示名称
		Fdaporg fdaporg = this.getOrgBiz().getByOid(new Long(oid));
		request.setAttribute("fdaporg", fdaporg);
		
		//获取车载工程集合和仓库工程集合
		List<Fdapproject> clist = this.realAlarmBiz.getCarProjectByOid(new Long(oid));
		request.setAttribute("cprojectIds", buildStr(clist));
		
		List<Fdapproject> rlist = this.realAlarmBiz.getRefProjectByOid(new Long(oid));
		request.setAttribute("rprojectIds",buildStr(rlist));
		
		
		return mapping.findForward("alarmall");
	}
	
	/**
	 * 跳转到冷库实时报警页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toRealRefAlarm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String oid = request.getParameter("oid") ;
		if(oid==null || oid.equals("")){
				logger.warn("传递了非法参数oid");
				throw new Exception("传递了非法的参数oid");
		}
		
		
		//保存企业的信息，用于显示名称
		Fdaporg fdaporg = this.getOrgBiz().getByOid(new Long(oid));
		request.setAttribute("fdaporg", fdaporg);
		
		//获取仓库工程集合	
		List<Fdapproject> rlist = this.realAlarmBiz.getRefProjectByOid(new Long(oid));
		request.setAttribute("projectIds",buildStr(rlist));
		
		
		
		return mapping.findForward("alarmref");
	}
		
	/**
	 *  跳转到车载实时报警页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toRealCarAlarm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oid = request.getParameter("oid") ;
		if(oid==null || oid.equals("")){
				logger.warn("传递了非法参数oid");
				throw new Exception("传递了非法的参数oid");
		}
		
		
		//保存企业的信息，用于显示名称
		Fdaporg fdaporg = this.getOrgBiz().getByOid(new Long(oid));
		request.setAttribute("fdaporg", fdaporg);
		
		//获取车载工程集合
		List<Fdapproject> clist = this.realAlarmBiz.getCarProjectByOid(new Long(oid));
		request.setAttribute("projectIds", buildStr(clist));
		
		return mapping.findForward("alarmcar");
	}
	
	
	public ActionForward toTest(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		realAlarmBiz.getAlarmTip(request.getParameter("oid"));
		return null;
	}
	/**
	 * 封装工程标识Id字符串 1001,1002,1003
	 * @param list
	 * @return
	 */
	private String buildStr(List<Fdapproject> list){
		StringBuffer sb = new StringBuffer();
		
		if(list==null || list.size()==0)
			return sb.toString() ;
		
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i).getProjectid());
			if(i!=list.size()-1)
				sb.append(",");
		}
		return sb.toString();
	}
		
	
	
			
}
