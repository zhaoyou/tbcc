package org.fdap.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.fdap.biz.OrgBiz;
import org.fdap.biz.RefHisBiz;
import org.fdap.entity.Fdapaiinfo;
import org.fdap.entity.Fdaporg;
import org.fdap.entity.Fdapproject;
import org.fdap.entity.Fdapref;
import org.fdap.util.BaseAction;

/**
 * 仓库冷库历史数据action
 * @author zhoukuanwei
 *
 */
public class RefHisAction  extends BaseAction {
	private static Logger logger = Logger.getLogger(CarRealAction.class);
	private final Integer PAGESIZE=15;

	private RefHisBiz refHisBiz;
	private OrgBiz orgbiz;
	
	public RefHisBiz getRefHisBiz() {
		return refHisBiz;
	}

	public void setRefHisBiz(RefHisBiz refHisBiz) {
		this.refHisBiz = refHisBiz;
	}
	
	public OrgBiz getOrgbiz() {
		return orgbiz;
	}

	public void setOrgbiz(OrgBiz orgbiz) {
		this.orgbiz = orgbiz;
	}

	/**
	 * 根据冷库和时间，来查询仓库冷库历史数据(第一次查询，即按下查询按钮)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doRefHisData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oid=request.getParameter("oid");
		//获取查询冷库历史数据的开始和结束时间
		String refstartTime=request.getParameter("refstartTime");
		String refendTime=request.getParameter("refendTime");
		
		String refId=request.getParameter("refId");
		String projectid=request.getParameter("projectid");
		
		if(oid==null||oid.equals("")||refId==null||refId.equals("")||refstartTime==null||
				refstartTime.equals("")||refendTime==null||refendTime.equals("")){
			logger.warn("传递了非法参数");
		}
		
		String tableName="fdaprefhisdata_"+oid;
		
		Integer refpage=1,refPagecount=1;
		//查询满足条件的冷库历史数据的条数，并计算总页数
		Integer counts=refHisBiz.getRefHisCount(tableName, refstartTime, refendTime,refId);
		if(counts!=null&&counts>=1)
			refPagecount=counts/PAGESIZE+(counts%PAGESIZE==0?0:1);
		//根据冷库Id获取探头列表
		List<Fdapaiinfo> Ailist=refHisBiz.getAiByRefId(refId);
		//如果总页数只有一页，且满足条件的历史数据不止一条，则计算相应探头的最大、最小和平均值
		if(refPagecount==1&&counts!=null&&counts>=1){
			List<Object> refListMMA=refHisBiz.getRefHisMMA(tableName, refstartTime, refendTime, Ailist,refId);
			request.setAttribute("refListMMA", refListMMA);
		}
		
		//根据条件查询冷库历史数据，从 0 条开始查，查 PAGESIZE 条
		List<Object> list=refHisBiz.getRefHisData(tableName, refstartTime, refendTime,0,PAGESIZE,Ailist,refId);
		
		Fdaporg fdaporg=orgbiz.getByOid(Long.parseLong(oid));
		//根据企业oid查询仓库工程列表
		List<Fdapproject> prjList=refHisBiz.getProjectByOid(Long.parseLong(oid));
		//根据工程Id查询冷库列表
		List<Fdapref> refList=null;
		if(projectid!=null&&!projectid.equals(""))
			refList=refHisBiz.getRefByProjectId(Long.parseLong(projectid));
		
		//request.setAttribute("oid", oid);
		request.setAttribute("refAiList", Ailist);
		request.setAttribute("orgname", fdaporg.getName());
		request.setAttribute("prolist", prjList);
		request.setAttribute("reflist", refList);
		
		/*request.setAttribute("projectid", projectid);
		request.setAttribute("refstartTime", refstartTime);
		request.setAttribute("refendTime", refendTime);
		request.setAttribute("refId", refId);*/
		
		//记录下首次查询的冷库Id、工程Id、查询开始时间和结束时间
		request.setAttribute("checkedrefId", refId);
		request.setAttribute("checkedprojectid", projectid);
		request.setAttribute("checkedrefstartTime", refstartTime);
		request.setAttribute("checkedrefendTime", refendTime);
		
		if(list!=null&&list.size()!=0&&Ailist!=null&&Ailist.size()!=0&&fdaporg!=null)
		{
			request.setAttribute("refhisList", list);
			request.setAttribute("refpagecount", refPagecount);
			request.setAttribute("refpage", refpage);
			
			return mapping.findForward("refhis");
		}
		request.setAttribute("msg", "<font color='blue'>没有满足条件的仓库历史数据</font>");
		return mapping.findForward("refhis");
	}

	/**
	 * 跳转到仓库冷库历史数据查询页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toRefHis(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oid=request.getParameter("oid");
		
		/*//暂时把时间写死
		String refstartTime="2010-01-04 10:39:20";
		String refendTime="2010-01-04 11:05:20";
		request.setAttribute("refstartTime", refstartTime);
		request.setAttribute("refendTime", refendTime);*/
		
		if(oid==null || oid.equals("")){
			logger.warn("传递了非法的oid参数");
		}
		Fdaporg fdaporg=orgbiz.getByOid(Long.parseLong(oid));
		//根据企业oid查询仓库工程列表
		List<Fdapproject> prjList=refHisBiz.getProjectByOid(Long.parseLong(oid));
		List<Fdapref> refList=null;
		if(prjList!=null&&prjList.size()!=0){
			//根据工程列表中第一个工程的Id查询冷库列表
			refList=refHisBiz.getRefByProjectId(prjList.get(0).getProjectid());
			if(refList==null||refList.size()==0){
				request.setAttribute("msg", "<font color='blue'>该仓库下没有冷库</font>");
			}
		}
		else {
			request.setAttribute("msg", "<font color='blue'>该企业下没有仓库工程</font>");
		}
		if(fdaporg!=null){
			//request.setAttribute("oid", oid);
			request.setAttribute("orgname", fdaporg.getName());
			request.setAttribute("prolist", prjList);
			request.setAttribute("reflist", refList);
			return mapping.findForward("refhis");
		}
		request.setAttribute("msg", "<font color='blue'>获取企业信息失败，请确认该企业数据无误</font>");
		return mapping.findForward("refhis");
	}

	
	
	
	
	/**
	 * 根据冷库和时间，来查询仓库冷库历史数据(分页查询)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doRefPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oid=request.getParameter("oid");
		//获取首次查询时的开始时间和结束时间，并根据这个时间来查询
		String checkedrefstartTime=request.getParameter("checkedrefstartTime");
		String checkedrefendTime=request.getParameter("checkedrefendTime");
		String refstartTime=checkedrefstartTime;
		String refendTime=checkedrefendTime;
		//获取首次查询时的工程Id和冷库Id，并根据这两个Id来查询
		String checkedprojectid=request.getParameter("checkedprojectid");
		String checkedrefId=request.getParameter("checkedrefId");
		String refId=checkedrefId;
		String projectid=checkedprojectid;
		
		if(oid==null||oid.equals("")||refId==null||refId.equals("")||refstartTime==null||
				refstartTime.equals("")||refendTime==null||refendTime.equals("")){
			logger.warn("传递了非法的oid参数");
		}
		
		String tableName="fdaprefhisdata_"+oid;
		
		//获得当前页和总页数
		Integer refpage=Integer.parseInt(request.getParameter("refpage"));
		Integer refPagecount=Integer.parseInt(request.getParameter("refpagecount"));
		//根据冷库Id查询探头列表
		List<Fdapaiinfo> Ailist=refHisBiz.getAiByRefId(refId);
		//如果当前页是最后一页，则计算历史数据中相应探头的最大、最小和平均值
		if(refPagecount==refpage){
			List<Object> refListMMA=refHisBiz.getRefHisMMA(tableName, refstartTime, refendTime, Ailist,refId);
			request.setAttribute("refListMMA", refListMMA);
		}
		
		Integer startrow=(refpage-1)*PAGESIZE;
		//根据条件查询冷库历史数据，从 startrow 条开始查，查 PAGESIZE 条
		List<Object> list=refHisBiz.getRefHisData(tableName, refstartTime, refendTime,startrow,PAGESIZE,Ailist,refId);
		
		Fdaporg fdaporg=orgbiz.getByOid(Long.parseLong(oid));
		//根据企业oid查询工程列表
		List<Fdapproject> prjList=refHisBiz.getProjectByOid(Long.parseLong(oid));
		//根据工程Id查询冷库列表
		List<Fdapref> refList=refHisBiz.getRefByProjectId(Long.parseLong(projectid));
		
		request.setAttribute("orgname", fdaporg.getName());
		request.setAttribute("prolist", prjList);
		request.setAttribute("reflist", refList);
		//request.setAttribute("oid", oid);
		request.setAttribute("pagesize", PAGESIZE);
		
		/*request.setAttribute("refstartTime", refstartTime);
		request.setAttribute("refendTime", refendTime);
		request.setAttribute("refId", refId);
		request.setAttribute("projectid", projectid);*/
		
		//记录下首次查询的冷库Id、工程Id、查询开始时间和结束时间
		request.setAttribute("checkedrefId", checkedrefId);
		request.setAttribute("checkedprojectid", checkedprojectid);
		request.setAttribute("checkedrefstartTime", checkedrefstartTime);
		request.setAttribute("checkedrefendTime", checkedrefendTime);
		
		if(list!=null&&list.size()!=0&&Ailist!=null&&Ailist.size()!=0&&fdaporg!=null)
		{
			request.setAttribute("refAiList", Ailist);
			request.setAttribute("refhisList", list);
			request.setAttribute("refpagecount", refPagecount);
			request.setAttribute("refpage", refpage);
			
			return mapping.findForward("refhis");
		}
		request.setAttribute("msg", "<font color='blue'>查询条件已被更改，请重新查询</font>");
		return mapping.findForward("refhis");
	}
	
	
	
	/**
	 * 根据冷库和时间，来查询仓库冷库历史曲线
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doRefHisCurve(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oid=request.getParameter("oid");
		//获取查询冷库历史数据的开始和结束时间
		String refstartTime=request.getParameter("refstartTime");
		String refendTime=request.getParameter("refendTime");
		
		String refId=request.getParameter("refId");
		String projectid=request.getParameter("projectid");
		
		if(oid==null||oid.equals("")||refId==null||refId.equals("")||refstartTime==null||
				refstartTime.equals("")||refendTime==null||refendTime.equals("")){
			logger.warn("传递了非法参数");
		}
		
		Fdaporg fdaporg=orgbiz.getByOid(Long.parseLong(oid));
		//根据企业oid查询仓库工程列表
		List<Fdapproject> prjList=refHisBiz.getProjectByOid(Long.parseLong(oid));
		//根据工程Id查询冷库列表
		List<Fdapref> refList=null;
		if(projectid!=null&&!projectid.equals(""))
			refList=refHisBiz.getRefByProjectId(Long.parseLong(projectid));
		
		request.setAttribute("orgname", fdaporg.getName());
		request.setAttribute("prolist", prjList);
		request.setAttribute("reflist", refList);
		
		//记录下首次查询的冷库Id、工程Id、查询开始时间和结束时间
		request.setAttribute("checkedrefId", refId);
		request.setAttribute("checkedprojectid", projectid);
		//为了解决谷歌游览器下flex获取url参数中时间数据时空格的BUG问题
		request.setAttribute("checkedrefstartTime", refstartTime.replace(" ", "_"));
		request.setAttribute("checkedrefendTime", refendTime.replace(" ", "_"));
		
		return mapping.findForward("refhiscurve");
	}
}
