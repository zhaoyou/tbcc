package org.tbcc.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.tbcc.biz.AirCoolerBiz;
import org.tbcc.biz.CompressorBiz;
import org.tbcc.biz.CompressorSetBiz;
import org.tbcc.biz.CondenserBiz;
import org.tbcc.biz.ProjectBiz;
import org.tbcc.entity.TbccBaseUser;
import org.tbcc.entity.cool.TbccCompressorSet;
import org.tbcc.util.BaseAction;

/**
 * 这是一个制冷相关的action
 * @author Administrator
 *
 */
public class RealCoolAction extends BaseAction {
	
	private CompressorSetBiz compressorSetBiz = null ;
	
	private ProjectBiz 		projectBiz = null ;
	
	private AirCoolerBiz 	aircoolerBiz = null ;
	
	private CondenserBiz	condenserBiz = null ; 
	
	private CompressorBiz	compressorBiz = null ;
	
	private Logger logger = Logger.getLogger(RealCoolAction.class);
	
	public void setAircoolerBiz(AirCoolerBiz aircoolerBiz) {
		this.aircoolerBiz = aircoolerBiz;
	}


	public void setCondenserBiz(CondenserBiz condenserBiz) {
		this.condenserBiz = condenserBiz;
	}


	public void setCompressorBiz(CompressorBiz compressorBiz) {
		this.compressorBiz = compressorBiz;
	}


	public void setProjectBiz(ProjectBiz projectBiz) {
		this.projectBiz = projectBiz;
	}


	public void setCompressorSetBiz(CompressorSetBiz compressorSetBiz) {
		this.compressorSetBiz = compressorSetBiz;
	}
	
	
	/**
	 * 跳转的制冷实时数据页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toRealCool(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String branchId = 	request.getParameter("branchId") ;
		
		if(branchId==null || branchId.equals("")){
			request.setAttribute("errorMsg", "机构不存在或已经删除!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRealCool)传递了一个无效的分支标识");
			throw new Exception("参数非法异常");
		}
			
		
		
		
		
		List<TbccCompressorSet> compressorSetList = null ;
		
		//判断session中是否存在该机组集合
		if(request.getSession().getAttribute("coollist")==null){
			//获取当前的所有制冷设备、并保持到session当中
			compressorSetList = compressorSetBiz.getByBranchId(new Long(branchId)); 
			request.getSession().setAttribute("coollist",compressorSetList);
		}else{
			compressorSetList = (List<TbccCompressorSet>)request.getSession().getAttribute("coollist") ;
		}
		
		
		
		if(request.getSession().getAttribute("refprojectids")==null){
			
			//获取该分支下的多有冷库工程标识Id	,这是原先的 实现、现在已经是获取冷库工程和制冷工程的总和
			//包括独立制冷、仓库和制冷混合、8di、24di的不同系统
			//List<String> 	refProjectids = projectBiz.getRefProjects(new Long(branchId)) ;
			
			
			List<String> 	refProjectids = projectBiz.getCoolerProjects(new Long(branchId)) ;
			
			StringBuffer sb = new StringBuffer();
			for (int i=0;i<refProjectids.size();i++) {
					sb.append(refProjectids.get(i));
				if(i!=refProjectids.size()-1)
					sb.append(",");
			}
				request.getSession().setAttribute("refprojectids", sb.toString());
		}
		

		 
		//如果当前的工程projectId对应的制冷设备尚未初始化...
		if(compressorSetList==null || compressorSetList.size()==0){
			request.setAttribute("noMsg", "<font color='red'>没有发现相应的制冷设备...</font>") ;
			return mapping.findForward("singlecool") ;		//如果没有机组、则默认跳转的冷凝机组页面，提示
		}else{
			
			//判断第一次显示的是冷凝机组、还是并联机组
			TbccCompressorSet comset = compressorSetList.get(0) ;
			
			request.setAttribute("compressorset", comset);
			
			//把要显示的机组下的所有机头标识、冷凝器标识、冷风机标识都保存起来
			request.setAttribute("compressorStr",getStr(compressorBiz.getIdsByCsId(comset.getId()))) ;
			request.setAttribute("aircoolerStr", getStr(aircoolerBiz.getIdsBycsId(comset.getId()))) ;
			request.setAttribute("condenserStr", getStr(condenserBiz.getIdsByCsid(comset.getId())));
			
			if(comset.getCsType().toString().equals(CompressorSetBiz.SINGLECOMPRESSOR.toString())){		
				return mapping.findForward("singlecool") ;
			}else{
				return mapping.findForward("mulcool") ;
			}		
		}
		
	}
	
	/**
	 * 选择不同的制冷机组时，跳转到不同的页面显示
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward changeRealCool(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mysetid = request.getParameter("mysetid") ;
		
		if(mysetid==null || mysetid.equals("")){
			request.setAttribute("errorMsg", "参数为空或非法!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (changeRealCool)传递了一个非法的机组标示");
			throw new Exception("参数非法异常...");
		}
			
		
		TbccCompressorSet compressorset = compressorSetBiz.getById(new Integer(mysetid));
		
		if(compressorset==null){
			request.setAttribute("errorMsg", "该机组不存在或者已经删除") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ "(changeRealCool)引用的机组不存在!");
			throw new Exception("实例错误...");
		}
		request.setAttribute("compressorset", compressorset);
		
		//把要显示的机组下的所有机头标识、冷凝器标识、冷风机标识都保存起来
		request.setAttribute("compressorStr",getStr(compressorBiz.getIdsByCsId(compressorset.getId()))) ;
		request.setAttribute("aircoolerStr", getStr(aircoolerBiz.getIdsBycsId(compressorset.getId()))) ;
		request.setAttribute("condenserStr", getStr(condenserBiz.getIdsByCsid(compressorset.getId())));
		
		if(compressorset.getCsType().toString().equals(CompressorSetBiz.SINGLECOMPRESSOR.toString())){	
			return mapping.findForward("singlecool") ;
		}		
		else{
			return mapping.findForward("mulcool") ;
		}
			
	}
	
	/**
	 * 根据标识Id集合，转化为一个字符串格式
	 * @param list		标识Id集合
	 * @return			12,14,15
	 */
	public String getStr(List<Integer> list){
		
		if(list==null || list.size()==0)
			return null ;
	
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i)) ;
			if(i!=list.size()-1)
				sb.append(",") ;
		}
		
		return	sb.toString() ;
	}
	
	
	/**
	 * 跳转的制冷系统属性页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toRealCoolSys (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//验证分支参数是否正确
		String branchId = 	request.getParameter("branchId") ;
		
		if(branchId==null || branchId.equals("")){
			request.setAttribute("errorMsg", "机构不存在或已经删除!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+  " (toRealCoolSys)传递了一个非法的分支标识！");
			throw new Exception("参数非法异常");
		}
		
		//获取该分支下的所有机组信息
		List<TbccCompressorSet> list = compressorSetBiz.getByBranchId(new Long(branchId)) ;
		request.setAttribute("coollist", list);
		
		
		//获取所有的制冷系统相关的工程编号
		List<String> coolerprojects = projectBiz.getCoolerProjects(new Long(branchId)) ;
		
		
		StringBuffer sb = new StringBuffer("");
		for (int i = 0 ;i<coolerprojects.size();i++) {
			sb.append(coolerprojects.get(i));
			if(i!=coolerprojects.size()-1)
				sb.append(",");
		}
		
		request.setAttribute("refprojectids", sb.toString());
		
	
		return mapping.findForward("realsys") ;
	}
	
	/**
	 * 跳转到不同的机组页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toRealCoolSingleMul (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//验证分支参数是否正确
		String branchId = 	request.getParameter("branchId") ;
		
		if(branchId==null || branchId.equals("")){
			request.setAttribute("errorMsg", "机构不存在或已经删除!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRealCoolSingleMul)传递了一个非法的分支标识！");
			throw new Exception("参数非法异常");
		}
		
		//获取该分支下的所有机组信息
		List<TbccCompressorSet> list = compressorSetBiz.getByBranchId(new Long(branchId)) ;
		request.setAttribute("coollist", list);
		
		
		
		//获取当前选中的机组标示
		String mysetid = request.getParameter("mysetid") ;
		
		if(mysetid==null || mysetid.equals("")){
			request.setAttribute("errorMsg", "参数为空或非法!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRealCoolSingleMul)传递了一个非法的机组标示！");
			throw new Exception("参数非法异常...");
		} 
		
		//获取当前机组的信息
		TbccCompressorSet compressorset = compressorSetBiz.getById(new Integer(mysetid));
		if(compressorset==null){
			request.setAttribute("errorMsg", "该机组不存在或者已经删除") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRealCoolSingleMul)当前引用的机组不存在 ！");
			throw new Exception("实例错误...");
		}
		request.setAttribute("compressorset", compressorset);
		
		//把要显示的机组下的所有机头标识、冷凝器标识、冷风机标识都保存起来
		request.setAttribute("compressorStr",getStr(compressorBiz.getIdsByCsId(compressorset.getId()))) ;
		request.setAttribute("aircoolerStr", getStr(aircoolerBiz.getIdsBycsId(compressorset.getId()))) ;
		request.setAttribute("condenserStr", getStr(condenserBiz.getIdsByCsid(compressorset.getId())));
		
		//根据机组的不同类型跳转的不同的页面
		if(compressorset.getCsType().toString().equals(CompressorSetBiz.SINGLECOMPRESSOR.toString())){
			return mapping.findForward("singlecool2") ;
		}else{
			return mapping.findForward("mulcool2") ;
		}
		
	}
}
