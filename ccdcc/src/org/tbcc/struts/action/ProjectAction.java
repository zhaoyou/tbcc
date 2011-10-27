package org.tbcc.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.tbcc.biz.BranchBiz;
import org.tbcc.biz.CrmBiz;
import org.tbcc.biz.ProjectBiz;
import org.tbcc.entity.TbccBaseUser;
import org.tbcc.entity.TbccBranchType;
import org.tbcc.entity.TbccCrm;
import org.tbcc.entity.TbccPrjType;
import org.tbcc.util.BaseAction;

/**
 * 这是用来处理pro.do请求的Action extends baseAction
 * @author Administrator
 *
 */
public class ProjectAction extends BaseAction {
		
	private ProjectBiz proBiz = null ;
	
	private BranchBiz branchBiz = null ;
	
	private CrmBiz crmBiz = null ;
	
	private Logger logger  = Logger.getLogger(ProjectAction.class) ;

	
	public void setBranchBiz(BranchBiz branchBiz) {
		this.branchBiz = branchBiz;
	}


	public void setProBiz(ProjectBiz proBiz) {
		this.proBiz = proBiz;
	}
	
	public void setCrmBiz(CrmBiz crmBiz) {
		this.crmBiz = crmBiz;
	}
	
	
	/**
	 * 跳转到冷库工程列表页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toRefList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获取分支标示Id
		String branchId = request.getParameter("branchId") ;
		
		//判断分支标示Id有效性
		if(branchId==null || branchId.equals("")){
			request.setAttribute("errorMsg", "分支标识不存在或已经删除!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRefList)传递了非法的分支标示！");
			throw new Exception("分支标识不存在!!") ;
		}
		
		
		//获取分支对象
		TbccBranchType branchType = this.branchBiz.getById(new Long(branchId));
		
		
		//判断分支对象是否存在
		if(branchType==null){
			request.setAttribute("errorMsg", "分支标识不存在或已经删除!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+" (toRefList)当前引用的分支标识不存在 ！");
			throw new Exception("分支标识不存在！");
		}
			
		
		
		//定义变量保存所有的冷库工程
		List<TbccPrjType> rList = new ArrayList<TbccPrjType>();

		
		
		//循环保存所有的冷库工程列表
		for (TbccPrjType tbccPrjType : branchType.getPrjTypes()) {
			//这是后来增加的把制冷工程和冷库工程暂时先加在一起
			//把冷库与制冷的混合也加入到了工程之中
			if(tbccPrjType.getProjectType().equals(ProjectBiz.REF) || tbccPrjType.getProjectType().equals(ProjectBiz.COOLER)
					|| tbccPrjType.getProjectType().equals(ProjectBiz.REF_COOLER)|| tbccPrjType.getProjectType().equals(ProjectBiz.COOLER_BASE)
					|| tbccPrjType.getProjectType().equals(ProjectBiz.REF_COOLER_BASE)){
				rList.add(tbccPrjType) ;
				continue ;
			}	
			
			
		}	
		request.setAttribute("rList", rList);
		
		
	
		// 获取客户相关联的冷库工程列表 根据branchId ，获取与该客户相关客户的branchId	
		List<TbccCrm> crmList = crmBiz.getByHid(branchId);
				
		if(crmList!=null && crmList.size()>0){
			
			List<String> branchIdList = new ArrayList<String>();
		
			for (TbccCrm tbccCrm : crmList) {
				branchIdList.add(tbccCrm.getCid());
			}
			
			List<TbccBranchType> branchList = branchBiz.getByIds(branchIdList) ;
			
			request.setAttribute("branchList", branchList);
		}
		
		
		return mapping.findForward("reflist") ;
	}
	
	/**
	 * 跳转到车载工程列表页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toCarList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获取分支标示Id
		String branchId = request.getParameter("branchId") ;
		
		//判断分支标示Id有效性
		if(branchId==null || branchId.equals("")){
			request.setAttribute("errorMsg", "分支标识不存在或已经删除!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toCarList)传递了非法的分支标示！");
			throw new Exception("分支标识不存在!!") ;
		}
		
		
		//获取分支对象
		TbccBranchType branchType = this.branchBiz.getById(new Long(branchId));
		
		
		//判断分支对象是否存在
		if(branchType==null){
			request.setAttribute("errorMsg", "分支标识不存在或已经删除!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+" (toCarList)当前引用的分支标识不存在 ！");
			throw new Exception("分支标识不存在！");
		}
			
		
		
		//定义变量保存所有的车载工程
		List<TbccPrjType> cList = new ArrayList<TbccPrjType>();
		
		for (TbccPrjType tbccPrjType : branchType.getPrjTypes()) {
			//保存所有的车载工程
			if(tbccPrjType.getProjectType().equals(ProjectBiz.CAR)){
				cList.add(tbccPrjType) ;
				continue ;
			}							
		}
		
		request.setAttribute("cList", cList);
		return mapping.findForward("carlist") ;
	}
	
	/**
	 * 跳转到小批零工程列表页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toBoxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return null ;
	}


	
	
}
