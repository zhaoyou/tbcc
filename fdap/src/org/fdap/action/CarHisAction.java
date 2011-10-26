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
import org.fdap.entity.FdapCarHisData;
import org.fdap.entity.Fdaporg;
import org.fdap.entity.Fdapref;
import org.fdap.util.BaseAction;


/**
 * 车载历史数据action
 * @author zhoukuanwei
 *
 */
public class CarHisAction  extends BaseAction {
	private static Logger logger = Logger.getLogger(CarHisAction.class);
	private final Integer PAGESIZE=15;

	private CarHisBiz carhisbiz;
	private OrgBiz orgbiz;
	
	public CarHisBiz getCarhisbiz() {
		return carhisbiz;
	}

	public void setCarhisbiz(CarHisBiz carhisbiz) {
		this.carhisbiz = carhisbiz;
	}
	
	public OrgBiz getOrgbiz() {
		return orgbiz;
	}

	public void setOrgbiz(OrgBiz orgbiz) {
		this.orgbiz = orgbiz;
	}

	/**
	 * 根据启停记录查询移动车载的历史数据(查询按钮调用)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doCarhisbyStartup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		//车载冷库refId
		String carrefId=request.getParameter("checkedrefId");
		//启停记录sid
		Integer sid=Integer.parseInt(request.getParameter("sid"));
				
		//企业Id
		String oid=request.getParameter("oid");
		String tableName="fdapcarhisdata_"+oid;
		
		if(sid==null||oid==null||oid==""||sid<=0){
			logger.warn("传递了非法参数");
		}
		//总页数和当前页
		Integer pageCount=1,page=1;
		//该启停记录下车载历史数据的条数
		Integer counts=carhisbiz.getCarHisCount(tableName, sid);
		if(counts!=null&&counts>=1){
			pageCount=counts/PAGESIZE+(counts%PAGESIZE==0?0:1);
		}
		else{
			logger.warn("获取总页数失败");
		}
		//如果总页数只有一页，且至少有一条历史数据，计算历史数据中对应探头的最大、最小和平均值
		if(pageCount==1&&counts!=null&&counts>=1){
			//获得探头的最大、最小和平均(M、M、A分别代表)
			List<Object> listMMA=carhisbiz.getCarHisMMA(tableName, sid);
			if(listMMA!=null||listMMA.size()!=0){
				request.setAttribute("carlistMMA", listMMA);
			}
			else{
				logger.warn("获取统计值失败");
			}
		}
				
		//根据启停Id查询车载历史数据，从第0条开始查，查PAGESIZE条
		List<FdapCarHisData> list=carhisbiz.getCarHisbyStartup(tableName, sid,0,PAGESIZE);
				
//		System.out.println("carrefId "+carrefId);
		//根据车载冷库Id查询冷库信息，获取车载名称
		Fdapref carref=carhisbiz.getCarRefById(carrefId);
		if(carref!=null)
			request.setAttribute("carrefname", carref.getName());
		else{
			logger.warn("获取车载冷库信息失败");
		}
		
		request.setAttribute("pagesize", PAGESIZE);
		if(list!=null&&list.size()!=0)
		{
			request.setAttribute("page",page );
			request.setAttribute("carpagecount", pageCount);
			
			request.setAttribute("hiscarList",list);
			return mapping.findForward("carhisList");
		}
		else{
			System.out.println("获取车载历史记录失败");
			//当历史数据为空时，在页面上显示消息
			request.setAttribute("msg", "<font color='blue'>该启停记录下没有车载历史数据</font>");
			return mapping.findForward("carhisList");
		}
	}
	
	/**
	 * 根据启停记录查询移动车载的历史数据(分页调用)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doCarHisPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Integer sid=Integer.parseInt(request.getParameter("sid"));
		
		String oid=request.getParameter("oid");
		//车载名称
		String carrefname=request.getParameter("carrefname");
		String tableName="fdapcarhisdata_"+oid;
		
		if(sid==null||oid==null||oid==""||sid<=0){
			logger.warn("启停记录ID以及企业ID没获取到");
		}
		
		//获取当前页和总页数
		Integer page=Integer.parseInt(request.getParameter("page"));
		Integer pageCount=Integer.parseInt(request.getParameter("carpagecount"));
		
		//当前页是最后一页的时候，计算历史数据中对应探头的最大、最小和平均值
		if(pageCount==page){
			//获得探头的最大、最小和平均(M、M、A分别代表)
			List<Object> listMMA=carhisbiz.getCarHisMMA(tableName, sid);
			if(listMMA!=null||listMMA.size()!=0){
				request.setAttribute("carlistMMA", listMMA);
			}
			else{
				logger.warn("获取统计值失败");
			}
		}
		
		Integer startRow=(page-1)*PAGESIZE;
		//根据启停Id查询车载历史数据，从第 startRow 条开始查，查 PAGESIZE 条
		List<FdapCarHisData> list=carhisbiz.getCarHisbyStartup(tableName, sid,startRow,PAGESIZE);
				
		request.setAttribute("carrefname", carrefname);
		request.setAttribute("pagesize", PAGESIZE);
		
		if(list!=null&&list.size()!=0)
		{
			request.setAttribute("page",page );
			request.setAttribute("carpagecount", pageCount);
			
			request.setAttribute("hiscarList",list);
			return mapping.findForward("carhisList");
		}
		else{
			System.out.println("获取车载历史记录失败");
			//当历史数据为空时，在页面上显示消息
			request.setAttribute("msg", "<font color='blue'>没有满足条件的车载历史数据</font>");
			return mapping.findForward("carhisList");
		}
	}
	
	
	/**
	 * 跳转到车载启停记录查询页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toHisStartUp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oid=request.getParameter("oid");
		
		if(oid==null || oid.equals("")){
			logger.warn("传递了非法的oid参数");
		}
		//根据企业oid查询企业信息
		Fdaporg fdaporg=orgbiz.getByOid(Long.parseLong(oid));
		//根据企业oid查询车载冷库信息列表
		List<Fdapref> carreflist=carhisbiz.getCarRefByOid(oid);
		//request.setAttribute("oid", oid);
		if(fdaporg!=null){
			request.setAttribute("orgname", fdaporg.getName());
		}
		if(carreflist!=null&&carreflist.size()!=0){
			request.setAttribute("carreflist", carreflist);
			return mapping.findForward("getStartup");
		}
		else{
			logger.warn("该企业下没有车载工程");
			//没有获取到企业信息
			request.setAttribute("msg", "<font color='blue'>该企业下没有车载工程</font>");
			return mapping.findForward("getStartup");
		}
	}
	
	
	public ActionForward toHisMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//验证参数
		String oid = request.getParameter("oid");
		//String sid = request.getParameter("sid");
//		String sid = "294";
		String startTime  = request.getParameter("mapstartTime");
		String pNO = request.getParameter("pNO");
		if(oid==null || oid.equals("")||pNO==null || pNO.equals("")||startTime==null || startTime.equals("")){
			logger.warn("传递了非法参数");
			throw new Exception("传递了非法参数");
		}
		
		//保存企业的信息，用于显示名称
		Fdaporg fdaporg = this.getOrgbiz().getByOid(new Long(oid));
		request.setAttribute("fdaporg", fdaporg);
		
//		request.setAttribute("sid", sid);
		request.setAttribute("startTime", startTime);
		request.setAttribute("pNO", pNO);
		
		return mapping.findForward("maphis");
	}
	
	
	public ActionForward toHisMapall(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//验证参数
		String oid = request.getParameter("oid");
		//String sid = request.getParameter("sid");
//		String sid = "294";
		String startTime = request.getParameter("mapstartTime");
		String pNO = request.getParameter("pNO");
		
		if(oid==null || oid.equals("")||pNO==null || pNO.equals("")||startTime==null || startTime.equals("")){
			logger.warn("传递了非法参数");
			throw new Exception("传递了非法参数");
		}
		
		
		
		//保存企业的信息，用于显示名称
		Fdaporg fdaporg = this.getOrgbiz().getByOid(new Long(oid));
		request.setAttribute("fdaporg", fdaporg);
		
//		request.setAttribute("sid", sid);
		request.setAttribute("startTime", startTime);
		request.setAttribute("pNO", pNO);
		
		return mapping.findForward("maphisall");
	}
	
	
	public ActionForward toHisCarcurve(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//车载冷库refId
		String carrefId=request.getParameter("checkedrefId");
		//验证参数
		String oid = request.getParameter("oid");
		//启停记录sid
		Integer sid=Integer.parseInt(request.getParameter("sid"));
		
		if(oid==null || oid.equals("")||sid==null || sid<0||carrefId==null || carrefId.equals("")){
			logger.warn("传递了非法参数");
			throw new Exception("传递了非法参数");
		}
		
		return mapping.findForward("carcurve");
	}
}
