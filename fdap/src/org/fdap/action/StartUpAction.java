package org.fdap.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.fdap.biz.CarHisBiz;
import org.fdap.biz.OrgBiz;
import org.fdap.biz.RefConfigBiz;
import org.fdap.biz.StartUpBiz;
import org.fdap.entity.FdapStartUp;
import org.fdap.entity.Fdaporg;
import org.fdap.entity.Fdapref;
import org.fdap.util.BaseAction;

/**
 * 车载历史启停记录数据action
 * @author zhoukuanwei
 *
 */
public class StartUpAction  extends BaseAction{
	private static Logger logger = Logger.getLogger(CarRealAction.class);
	private final Integer PAGESIZE=15;
	private StartUpBiz startupbiz;
	private OrgBiz orgbiz;
	private CarHisBiz carhisbiz;
	
	private RefConfigBiz refconfigbiz;
	
	public RefConfigBiz getRefconfigbiz() {
		return refconfigbiz;
	}

	public void setRefconfigbiz(RefConfigBiz refconfigbiz) {
		this.refconfigbiz = refconfigbiz;
	}

	public StartUpBiz getStartupbiz() {
		return startupbiz;
	}

	public void setStartupbiz(StartUpBiz startupbiz) {
		this.startupbiz = startupbiz;
	}
	
	public OrgBiz getOrgbiz() {
		return orgbiz;
	}

	public void setOrgbiz(OrgBiz orgbiz) {
		this.orgbiz = orgbiz;
	}
	
	public CarHisBiz getCarhisbiz() {
		return carhisbiz;
	}

	public void setCarhisbiz(CarHisBiz carhisbiz) {
		this.carhisbiz = carhisbiz;
	}

	/**
	 * 根据车载refId和时间，来查询车载历史启停记录(第一次查询，即按下查询按钮)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doStartUpByRefAndtime(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String oid=request.getParameter("oid");
		String carrefid=request.getParameter("carrefId");
		String startTime=request.getParameter("startTime");
		String endTime=request.getParameter("endTime");
		
		if(oid==null||oid.equals("")||carrefid==null||carrefid.equals("")||startTime==null||
				startTime.equals("")||endTime==null||endTime.equals("")){
			logger.warn("传递了非法的oid参数");
		}
		
		String tableName="fdapstartup_"+oid;
		Integer suppage=1,suppagecount=1;
		//根据车载冷库Id和时间，查询启停记录的条数，并计算总页数
		Integer counts=startupbiz.getStartupCounts(tableName, carrefid, startTime, endTime);
		if(counts!=null&&counts>=1){
			suppagecount=counts/PAGESIZE+(counts%PAGESIZE==0?0:1);
		}
		else{
			logger.warn("获取启停记录总页数失败");
		}
		
		//根据车载冷库Id和时间，查新历史启停记录，从 0 条开始查，查 PAGESIZE 条
		List<FdapStartUp> list=startupbiz.getStartup(tableName,carrefid,startTime, endTime,0,PAGESIZE);
		Fdaporg fdaporg=orgbiz.getByOid(Long.parseLong(oid));
		//根据企业oid查询车载冷库列表
		List<Fdapref> carreflist=carhisbiz.getCarRefByOid(oid);
		
		//request.setAttribute("oid", oid);
		request.setAttribute("orgname", fdaporg.getName());
		request.setAttribute("carreflist", carreflist);
		request.setAttribute("pagesize", PAGESIZE);
		
		/*request.setAttribute("carrefId", carrefid);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);*/
		
		//记录下首次查询时的车载冷库Id、查询开始时间和结束时间
		request.setAttribute("checkedrefId", carrefid);
		request.setAttribute("checkedstartTime", startTime);
		request.setAttribute("checkedendTime", endTime);
		
		//保存与CCDCC数据库对应的工程编号
		request.setAttribute("pNO", this.getRefconfigbiz().getById(Long.parseLong(carrefid)).getFdapproject().getProjectNO());
		
		
		if(list!=null&&list.size()!=0&&fdaporg!=null&&carreflist!=null&&carreflist.size()!=0){
			request.setAttribute("startList", list);
			request.setAttribute("suppage", suppage);
			request.setAttribute("suppagecount", suppagecount);
			return mapping.findForward("StartUpList");
		}
		request.setAttribute("msg", "<font color='blue'>没有满足条件的启停记录</font>");
		return mapping.findForward("StartUpList");
	}
	
	/**
	 * 根据车载refId和时间，来查询车载历史启停记录(分页查询)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doStartUpPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String oid=request.getParameter("oid");
		//获取首次查询时的车载冷库Id，并用这个Id查询
		String checkedrefId=request.getParameter("checkedrefId");
		String carrefid=checkedrefId;
		//获取首次查询时的开始和结束时间，并用这个时间来查询
		String checkedstartTime=request.getParameter("checkedstartTime");
		String checkedendTime=request.getParameter("checkedendTime");
		String startTime=checkedstartTime;
		String endTime=checkedendTime;
		
		if(oid==null||oid.equals("")||carrefid==null||carrefid.equals("")||startTime==null||
				startTime.equals("")||endTime==null||endTime.equals("")){
			logger.warn("传递了非法的oid参数");
		}
		
		String tableName="fdapstartup_"+oid;
		//获取当前页和总页数
		Integer suppage=Integer.parseInt(request.getParameter("suppage"));
		Integer suppagecount=Integer.parseInt(request.getParameter("suppagecount"));
		
		Integer startRow=(suppage-1)*PAGESIZE;
		//根据车载冷库Id和时间，查新历史启停记录，从 startRow 条开始查，查 PAGESIZE 条
		List<FdapStartUp> list=startupbiz.getStartup(tableName,carrefid,startTime, endTime,startRow,PAGESIZE);
		Fdaporg fdaporg=orgbiz.getByOid(Long.parseLong(oid));
		//根据企业oid查询车载冷库列表
		List<Fdapref> carreflist=carhisbiz.getCarRefByOid(oid);
		
		//request.setAttribute("oid", oid);
		request.setAttribute("orgname", fdaporg.getName());
		request.setAttribute("carreflist", carreflist);
		request.setAttribute("pagesize", PAGESIZE);
		
		/*request.setAttribute("carrefId", carrefid);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);*/
		
		request.setAttribute("checkedrefId", checkedrefId);
		request.setAttribute("checkedstartTime", checkedstartTime);
		request.setAttribute("checkedendTime", checkedendTime);
		
		
		request.setAttribute("pNO", request.getParameter("pNO"));
		
		
		if(list!=null&&list.size()!=0&&fdaporg!=null&&carreflist!=null&&carreflist.size()!=0){
			request.setAttribute("suppage", suppage);
			request.setAttribute("suppagecount", suppagecount);
			request.setAttribute("startList", list);
			return mapping.findForward("StartUpList");
		}
		request.setAttribute("msg", "<font color='blue'>查询条件已被更改，请重新查询</font>");
		return mapping.findForward("StartUpList");
	}
	
	
}
