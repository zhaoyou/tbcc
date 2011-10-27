package org.tbcc.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.tbcc.biz.HisCarBiz;
import org.tbcc.biz.HisStartUpBiz;
import org.tbcc.biz.ProjectBiz;
import org.tbcc.entity.TbccBaseHisCar;
import org.tbcc.entity.TbccBaseHisStartUp;
import org.tbcc.entity.TbccBaseUser;
import org.tbcc.entity.TbccPrjType;
import org.tbcc.util.BaseAction;

import sun.nio.cs.HistoricallyNamedCharset;

/**
 * 这个action 处理 hiscar.do 的请求 extends baseAction
 * @author Administrator
 *
 */
public class HisCarAction extends BaseAction {

	private HisCarBiz hiscarBiz = null ;
	
	private ProjectBiz proBiz = null ;
	
	private HisStartUpBiz startupBiz = null ;
	
	private Logger logger = Logger.getLogger(HisCarAction.class);
	
	public void setProBiz(ProjectBiz proBiz) {
		this.proBiz = proBiz;
	}

	public void setStartupBiz(HisStartUpBiz startupBiz) {
		this.startupBiz = startupBiz;
	}

	public void setHiscarBiz(HisCarBiz hiscarBiz) {
		this.hiscarBiz = hiscarBiz;
	}

	/**
	 * 根据条件查询移动车载的历史数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doHisCarData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String startTime = request.getParameter("time1");
		String endTime = request.getParameter("time2");
		String proId  = request.getParameter("proId");
		String timeType = request.getParameter("timeType");
		String timeValue = request.getParameter("timevalue");
		if(startTime==null || endTime==null || proId==null || timeType==null || timeValue==null
				||startTime=="" || endTime=="" || proId=="" || timeType=="" || timeValue==""){
			request.setAttribute("msg", "请确保信息的完整!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (doHisCarData)获取历史数据、传递了非法的参数!");
			return mapping.findForward("hcarlist") ;
		}
		
		//获取历史数据
		List<TbccBaseHisCar> carList = hiscarBiz.getHisCarByProperty(proId, startTime, endTime, timeType,timeValue);
		
		if(carList!=null && carList.size()>0){
		//获取统计信息
		
		Object[] calc = hiscarBiz.getCalcValue(carList);
		
		request.setAttribute("hiscarList", carList);  //存入数据集
		
		request.setAttribute("min", calc[0]); 		  //存入最小值
		
		request.setAttribute("max", calc[1]) ;		//存入最大值
		
		request.setAttribute("avg", calc[2]);		//存入平均值
		
		}
		return mapping.findForward("hcarlist");
		
	}
	
	/**
	 * 跳到移动车载历史数据查询页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toHisCarData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//获取机构Id
		String branchId = request.getParameter("branchId");
		
		
		
		if(request.getSession().getAttribute("carprjList")==null){	
			//获取该机构下的所有移动车载项目
			List<TbccPrjType> carlist = hiscarBiz.getCarPrjList(new Long(branchId));
		
			// 这个地方我把所有的移动项目放入session当中不要每次查询都要去查数据库，以后从性能考虑，要改一下
			if(carlist!=null && carlist.size()>0)
				request.getSession().setAttribute("carprjList", carlist);  		
		}
		
		request.setAttribute("falg", "1") ;//这个没有什么作用，主要控制界面显示的
		
		return mapping.findForward("hcarlist");
	}
	
	
	
	//************************************上面两个方法是原来的实现方式***************************************
	
	
	
	/**
	 * 跳到移动车载历史启停记录查询页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toHisCarStartUp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//获取机构Id
		String branchId = request.getParameter("branchId");
		
		
		if(branchId==null || branchId==""){
			request.setAttribute("errorMsg", "请求参数为空或非法!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toHisCarStartUp)传递了非法的参数列表");
			throw new Exception("请求的资源无效!");
		}
		/**
		 * 标注一下、后来扩展实现实时数据地图显示的时候、也需要获取所有的移动车载工程、所有也是公用一个carprjList
		 * 所有在这里判断一下、session中是否存在了车载集合、如果存在、则不需要查询了
		 * 为了解决浏览器选项卡session问题，现在每次进入页面都需要重新查询车载工程
		 */
		
		
		//if(request.getSession().getAttribute("carprjList")==null){	
			//获取该机构下的所有移动车载项目
			List<TbccPrjType> carlist = hiscarBiz.getCarPrjList(new Long(branchId));
		
		
			// 这个地方我把所有的移动项目放入session当中不要每次查询都要去查数据库，以后从性能考虑，要改一下
				if(carlist!=null && carlist.size()>0)
					request.getSession().setAttribute("carprjList", carlist);  		
		//}
				
		return mapping.findForward("carstartup");
	}
	
	/**
	 * 跳转到移动车载历史数据查询页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toHisCarByStart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String branchId = request.getParameter("branchId");
		String proId = request.getParameter("proId");
		String sid = request.getParameter("sid") ;
		
		//验证传递的分支标示是否正确
		if(branchId==null || branchId.equals("")){
			request.setAttribute("errorMsg", "请求参数为空或非法!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toHisCarByStart)传递了非法的参数列表");
			throw new Exception("请求的资源无效!");
		}
		
		
		//验证传递的工程标示、起停记录标示是否正确
		TbccPrjType project = this.proBiz.getByProId(proId);		//获取要查询的项目
		
		TbccBaseHisStartUp startup = this.startupBiz.getStartUp(proId, Long.parseLong(sid));		//获取选择的启停记录
		
		if(branchId==null || branchId=="" || project==null || startup==null){
			request.setAttribute("errorMsg", "请求参数为空或非法!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toHisCarByStart)传递了非法的参数列表");
			throw new Exception("请求的资源无效!");
		}
			
		
		request.setAttribute("project", project);		
		
		request.setAttribute("startup", startup);		
		
		
		
		request.setAttribute("flag", 1);			//是用来控制页面显示的
		
		return mapping.findForward("hiscar") ;
	}
	
	
	/**
	 * 根据启停记录获取移动车载的历史数据 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doHisCarByStart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String startTime = request.getParameter("time1");
		String endTime = request.getParameter("time2");
		String proId  = request.getParameter("proId");
		String sid = request.getParameter("sid") ;	
		String interval = request.getParameter("interval");
		String timeValue = request.getParameter("timevalue");
		
		Integer sid_int = null ;
		Long timevalue_long = null ;
		
		if(startTime==null || endTime==null || proId==null || interval==null || timeValue==null || sid==null ||
			startTime=="" || endTime=="" || proId=="" || interval=="" || timeValue=="" || sid.equals("")){
			request.setAttribute("msg", "请确保信息的完整!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (doHisCarByStart)获取历史数据，传递了非法的参数列表!");
			return mapping.findForward("hcarlist2") ;
		}
		
		try {
			sid_int = Integer.parseInt(timeValue) ;
			timevalue_long = Long.parseLong(sid) ;
		} catch (Exception e) {
			request.setAttribute("msg", "传递了非法参数!") ;
			throw e ;
		}
		
		//获取历史数据
		List<TbccBaseHisCar> carList =hiscarBiz.getHisCarBySidAndTime(proId, startTime, endTime, interval,sid_int,timevalue_long);
			//hiscarBiz.getHisCarByProperty(proId, startTime, endTime, interval,timeValue);
		
		if(carList!=null && carList.size()>0){
		//获取统计信息
		
		Object[] calc = hiscarBiz.getCalcValue(carList);
		
		request.setAttribute("hiscarList", carList);  //存入数据集
		
		request.setAttribute("min", calc[0]); 		  //存入最小值
		
		request.setAttribute("max", calc[1]) ;		//存入最大值
		
		request.setAttribute("avg", calc[2]);		//存入平均值
		
		}
		return mapping.findForward("hiscar") ;
	}
	
	/**
	 * 跳转的移动车载历史曲线显示页面
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
		
		String branchId = request.getParameter("branchId") ;
		String sid = request.getParameter("sid") ;
		String t1 = request.getParameter("t1") ;
		String t2 = request.getParameter("t2") ;
		String proId = request.getParameter("proId") ;
		
		if(branchId==null || branchId.equals("") || sid==null || sid.equals("")||t1==null || t1.equals("")||
				t2==null || t2.equals("")|| proId==null || proId.equals("")){
			request.setAttribute("errorMsg", "请求参数为空或非法!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toCurve)传递了非法的参数列表");
			throw new Exception("请求的资源无效!");
		}	
		return mapping.findForward("carCurve");
	}
	

	
	
	/**
	 * 跳转的车载历史数据，地图显示页面,支持绘制不同速率的显示
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toHisMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String branchId = request.getParameter("branchId");
		String proId = request.getParameter("proId") ;
		String sid = request.getParameter("sid") ;
		
		//验证分支参数
		if(branchId==null || branchId.equals("")){
			request.setAttribute("errorMsg", "请求参数为空或非法!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toHisMap)传递了非法的工程标示或起停标示");
			throw new Exception("参数非法...");
		}
		
		//验证工程标示参数
		if(proId==null || proId.equals("") || sid==null || sid.equals("")){
			request.setAttribute("errorMsg", "请求参数为空或非法!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toHisMap)传递了非法的工程标示或起停标示");
			throw new Exception("参数非法...");
		}
		
		TbccPrjType project = this.proBiz.getByProId(proId);		//获取要查询的项目
		
		TbccBaseHisStartUp startup = this.startupBiz.getStartUp(proId, Long.parseLong(sid));		//获取选择的启停记录

		request.setAttribute("project", project) ;
		
		request.setAttribute("startup", startup) ;
		
		return mapping.findForward("hismap2");
	}
	
	/**
	 * 跳转到车载历史数据页面，绘制完整的地图曲线，支持扩展该曲线的其他信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toAllHisMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String branchId = request.getParameter("branchId");
		String proId = request.getParameter("proId") ;
		String sid = request.getParameter("sid") ;
		
		
		//验证分支参数
		if(branchId==null || branchId.equals("")){
			request.setAttribute("errorMsg", "请求参数为空或非法!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toAllHisMap)传递了非法的工程标示或起停标示");
			throw new Exception("参数非法...");
		}
		
		//验证参数工程标示、起停标示Id
		if(proId==null || proId.equals("") || sid==null || sid.equals("")){
			request.setAttribute("errorMsg", "参数为空或非法!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toAllHisMap)传递了非法的工程标示或起停标示");
			throw new Exception("参数非法...");
		}
		
		TbccPrjType project = this.proBiz.getByProId(proId);		//获取要查询的项目
		
		TbccBaseHisStartUp startup = this.startupBiz.getStartUp(proId, Long.parseLong(sid));		//获取选择的启停记录

		request.setAttribute("project", project) ;
		
		request.setAttribute("startup", startup) ;
		
		return mapping.findForward("hisallmap2") ;
	}
	
	
	
	
	
	
}
