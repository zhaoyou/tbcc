package org.fdap.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.fdap.biz.HisAlarmBiz;
import org.fdap.biz.OrgBiz;
import org.fdap.entity.FdapHisAlarm;
import org.fdap.entity.Fdaporg;
import org.fdap.util.BaseAction;

/**
 * 历史报警数据action
 * @author zhoukuanwei
 *
 */
public class HisAlarmAction  extends BaseAction {
	private static Logger logger = Logger.getLogger(CarRealAction.class);
	private final Integer PAGESIZE=15;
	
	private HisAlarmBiz hisalarmbiz;
	private OrgBiz orgbiz;
	
	public HisAlarmBiz getHisalarmbiz() {
		return hisalarmbiz;
	}

	public void setHisalarmbiz(HisAlarmBiz hisalarmbiz) {
		this.hisalarmbiz = hisalarmbiz;
	}

	public OrgBiz getOrgbiz() {
		return orgbiz;
	}

	public void setOrgbiz(OrgBiz orgbiz) {
		this.orgbiz = orgbiz;
	}
	
	/**
	 * 跳转到历史报警查询页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toAlarmHis(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oid=request.getParameter("oid");
		//根据企业oid查询企业信息
		Fdaporg fdaporg=orgbiz.getByOid(Long.parseLong(oid));
		request.setAttribute("oid", oid);
		if(fdaporg!=null){
			request.setAttribute("orgname", fdaporg.getName());
			return mapping.findForward("hisAlarm");
		}
		request.setAttribute("msg", "<font color='blue'>查询企业详细信息失败</font>");
		return mapping.findForward("hisAlarm");
	}
	
	/**
	 * 根据探头类型、报警级别和时间，搜索历史报警数据(第一次查询，即按下搜索按钮)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doAlarmHis(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oid=request.getParameter("oid");
		String alarmStartTime=request.getParameter("alarmStartTime");
		String alarmEndTime=request.getParameter("alarmEndTime");
		String orgname=request.getParameter("orgname");
		//探头类型
		String aiType=request.getParameter("aiType");
		//报警级别
		//String alarmLevel=request.getParameter("alarmLevel");
		//暂时获取所有的报警级别的历史报警数据
		String alarmLevel = "4";
		//工程类型(存储类型)
		String projectType=request.getParameter("projectType");
		if(oid==null||oid.equals("")||alarmStartTime==null||alarmStartTime.equals("")||alarmEndTime==null||alarmEndTime.equals("")
			||projectType==null||projectType.equals("")){
			logger.warn("传递了非法参数");
		}
		String tableName="fdaphisalarm_"+oid;
		Integer alarmpage=1,alarmpagecount=1;
		//获取满足条件的历史报警数据条数，并计算总页数
		Integer counts=hisalarmbiz.getHisAlarmCount(tableName, alarmStartTime, alarmEndTime,aiType,alarmLevel,oid,projectType);
		if(counts!=null)
		alarmpagecount=counts/PAGESIZE+(counts%PAGESIZE==0?0:1);
		
		
		//根据条件查询历史报警数据，从第 0 条开始查，查 PAGESIZE 条
		List<FdapHisAlarm> list=hisalarmbiz.getHisAlarm(tableName, alarmStartTime, alarmEndTime,0,PAGESIZE,oid,aiType,alarmLevel,projectType);
		
		request.setAttribute("oid", oid);
		request.setAttribute("orgname", orgname);
		
		/*request.setAttribute("aiType", aiType);
		request.setAttribute("alarmLevel", alarmLevel);
		request.setAttribute("projectType", projectType);
		request.setAttribute("alarmStartTime", alarmStartTime);
		request.setAttribute("alarmEndTime", alarmEndTime);
		*/
		//记录下首次查询的探头类型、报警级别和工程类型
		request.setAttribute("chaiType", aiType);
		request.setAttribute("chalarmLevel", alarmLevel);
		request.setAttribute("chprojectType", projectType);
		
		//记录下查询的开始和结束时间
		request.setAttribute("checkedalarmStartTime", alarmStartTime);
		request.setAttribute("checkedalarmEndTime", alarmEndTime);
		
		if(list!=null&&list.size()!=0){
			request.setAttribute("alarmpage", alarmpage);
			request.setAttribute("alarmpagecount", alarmpagecount);
			request.setAttribute("alarmList", list);
			return mapping.findForward("hisAlarm");
		}
		request.setAttribute("msg", "<font color='blue'>没有满足条件的历史报警数据</font>");
		return mapping.findForward("hisAlarm");
	}

	/**
	 * 根据探头类型、报警级别和时间，搜索历史报警数据(分页查询)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doAlarmHisPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oid=request.getParameter("oid");
		String orgname=request.getParameter("orgname");
		//获取第一次查询的开始和结束时间，并以这个时间查询数据
		String checkedalarmStartTime=request.getParameter("checkedalarmStartTime");
		String checkedalarmEndTime=request.getParameter("checkedalarmEndTime");
		String alarmStartTime=checkedalarmStartTime;
		String alarmEndTime=checkedalarmEndTime;
		
		//获取首次查询的探头类型、工程类型和报警级别。并根据这几个条件来查询
		String chaiType=request.getParameter("chaiType");
		String chalarmLevel=request.getParameter("chalarmLevel");
		String chprojectType=request.getParameter("chprojectType");
		String aiType=chaiType;
		String alarmLevel=chalarmLevel;
		String projectType=chprojectType;
		
		if(oid==null||oid.equals("")||alarmStartTime==null||alarmStartTime.equals("")||alarmEndTime==null||alarmEndTime.equals("")
				||projectType==null||projectType.equals("")){
			logger.warn("传递了非法参数");
		}
		String tableName="fdaphisalarm_"+oid;
		//获取当前页和总页数
		Integer alarmpage=Integer.parseInt(request.getParameter("alarmpage"));
		Integer alarmpagecount=Integer.parseInt(request.getParameter("alarmpagecount"));
		
		Integer startrow=(alarmpage-1)*PAGESIZE;
		//根据条件查询历史报警数据，从 startrow 条开始，查 PAGESIZE 条
		List<FdapHisAlarm> list=hisalarmbiz.getHisAlarm(tableName, alarmStartTime, alarmEndTime,startrow,PAGESIZE,oid,aiType,alarmLevel,projectType);
		
		request.setAttribute("oid", oid);
		request.setAttribute("orgname", orgname);
		
		/*request.setAttribute("alarmStartTime", alarmStartTime);
		request.setAttribute("alarmEndTime", alarmEndTime);
		request.setAttribute("aiType", aiType);
		request.setAttribute("alarmLevel", alarmLevel);
		request.setAttribute("projectType", projectType);*/
		
		request.setAttribute("chaiType", chaiType);
		request.setAttribute("chalarmLevel", chalarmLevel);
		request.setAttribute("chprojectType", chprojectType);
		request.setAttribute("checkedalarmStartTime", checkedalarmStartTime);
		request.setAttribute("checkedalarmEndTime", checkedalarmEndTime);
		
		request.setAttribute("pagesize", PAGESIZE);
		if(list!=null&&list.size()!=0){
			request.setAttribute("alarmpage", alarmpage);
			request.setAttribute("alarmpagecount", alarmpagecount);
			request.setAttribute("alarmList", list);
			return mapping.findForward("hisAlarm");
		}
		request.setAttribute("msg", "<font color='blue'>查询条件已被更改，请重新查询</font>");
		return mapping.findForward("hisAlarm");
	}
	
	public ActionForward tohisAlarmCount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oid = request.getParameter("oid");
		if(oid==null||oid.equals("")){
			logger.warn("传递了非法参数");
		}
		request.setAttribute("oid", oid);
		return mapping.findForward("statistics");
	}
	
	/*public Long hiscount(Long count,Fdaporg org){
		if(org.getNodetype()==2){
			String tableName = "fdaphisalarm_"+org.getOid();
			count=count+hisalarmbiz.getAlarmCounts(tableName);
		}else{
			List<Fdaporg> orglist = orgbiz.getByParentId(org.getOid());
			for(Fdaporg forg : orglist){
				count = hiscount(count,forg);
			}
		}
		return count;
	}*/
}
