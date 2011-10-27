 package org.tbcc.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.tbcc.biz.HisRefBiz;
import org.tbcc.biz.ProjectBiz;
import org.tbcc.biz.RefInfoBiz;
import org.tbcc.entity.TbccBaseUser;
import org.tbcc.entity.TbccPrjType;
import org.tbcc.entity.TbccRefInfo;
import org.tbcc.util.BaseAction;
/**
 * 冷库历史action接受 hisref.do 请求 Extends baseAction
 * @author Administrator
 *
 */
public class HisRefAction extends BaseAction {

	
	
	private HisRefBiz 	hisRefbiz = null ;
	
	private ProjectBiz projectBiz = null ;
	
	private RefInfoBiz	refInfoBiz = null ;
	
	private Logger logger = Logger.getLogger(HisRefAction.class);

	/**
	 * 由spring容器注入历史冷库操作业务类
	 * @param hisrefBiz
	 */
	
	public void setHisRefbiz(HisRefBiz hisRefbiz) {
		this.hisRefbiz = hisRefbiz;
	}

	public void setProjectBiz(ProjectBiz projectBiz) {
		this.projectBiz = projectBiz;
	}

	
	public void setRefInfoBiz(RefInfoBiz refInfoBiz) {
		this.refInfoBiz = refInfoBiz;
	}


	/**
	 * 跳转到冷库历史数据查询页面,需要获取该分支下的所有冷库工程、以及某个冷库工程的下的所有冷库
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toHisRefList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String branchId = request.getParameter("branchId");
		
		if(branchId==null || branchId==""){
			request.setAttribute("errorMsg", "机构不存在或已经删除!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toHisRefList)传递了一个非法的机构标识！");
			new Exception("没有相应的资源！");
		}
		
		List<TbccPrjType> refPrjList = null ;
		List<TbccRefInfo> refList	 = null ;
		
		
		//获取冷库工程资源
		/**
		 * 为了解决浏览器选项卡session问题，每次进入冷库历史查询页面都重新查询数据
		 */
		//if(request.getSession().getAttribute("refPrjList")==null){
			refPrjList = projectBiz.getRefProjListBybId(new Long(branchId));
			request.getSession().setAttribute("refPrjList", refPrjList);
		//}else{
			refPrjList = (List<TbccPrjType>)request.getSession().getAttribute("refPrjList");
		//}
		
		//获取默认第一个冷库工程下的所有冷库列表
		if(refPrjList!=null && refPrjList.size()>0){
			refList = refInfoBiz.getRefListByPrj(refPrjList.get(0).getProjectId()) ;
		}
		
		request.setAttribute("refList", refList);
		request.setAttribute("flag", 1) ;
		
		
		
//		if(refList==null || refList.size()<=0){
//			new Exception("请求的资源不存在");
//		}else{
//			request.getSession().setAttribute("refProList", refList);  		//把所有的冷库项目放入session中
//			request.setAttribute("flag", 1);								//用于控制页面显示
//		}
		
		return mapping.findForward("hisref2");
	}
	
	/**
	 * 查询冷库历史数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doHisRefData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String myrid = request.getParameter("myrid");
		String startTime = request.getParameter("time1");
		String endTime = request.getParameter("time2");
		String type = request.getParameter("timeType");
		String value = request.getParameter("timevalue");
		
		String myrefprojlist = request.getParameter("myrefprojlist") ;
		
		
		if(myrid==null || myrid=="" || startTime==null || startTime=="" || endTime==null || endTime=="" || type==null || type=="" 
			|| value==null || value=="" || myrefprojlist==null || myrefprojlist.equals("")){
			request.setAttribute("errorMsg", "参数为空或非法!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (doHisRefData)获取历史数据，传递了非法的参数");
			throw new Exception("没有请求的资源!");
		}
		
		//重新获取冷库工程下的冷库列表
		List<TbccRefInfo> refList = refInfoBiz.getRefListByPrj(myrefprojlist) ;
		request.setAttribute("refList", refList);
		
		// 获取某个冷库的列头信息、历史数据信息
		List obj  = hisRefbiz.getRefHisData(Long.parseLong(myrid), startTime, endTime, type, value);
		
		//判断是否存在结果集对象
		if(obj!=null && obj.size()>0){
			
			request.setAttribute("aiInfoList", obj.get(0));		//获取探头信息
			
			request.setAttribute("refDataList", obj.get(1));	//获取数据信息
		
			Object[] result = (Object[])obj.get(2) ;	//获取最后的统计信息
		
			//保存
		if(result!=null && result.length>0){
			request.setAttribute("min", result[0]) ;
			request.setAttribute("max", result[1]) ;
			request.setAttribute("avg", result[2]) ;
		}
			
		}
		
		
		return mapping.findForward("hisref2");
	}
	
	/**
	 * 跳转的冷库历史曲线显示页面
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
		
		String branchId = request.getParameter("branchId");
		
		if(branchId==null || branchId==""){
			request.setAttribute("errorMsg", "机构不存在或已经删除!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toCurve)传递了一个非法的机构标识！");
			new Exception("没有相应的资源！");
		}
		
		return mapping.findForward("refCurve");
	}


	
	
	
		
}
