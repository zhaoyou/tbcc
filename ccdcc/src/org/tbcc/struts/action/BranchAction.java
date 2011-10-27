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
import org.tbcc.biz.UserBiz;
import org.tbcc.entity.TbccBaseUser;
import org.tbcc.entity.TbccBranchType;
import org.tbcc.entity.TbccCrm;
import org.tbcc.entity.TbccFunction;
import org.tbcc.entity.TbccHqUser;
import org.tbcc.entity.TbccPrjType;
import org.tbcc.entity.TbccUser;
import org.tbcc.util.BaseAction;

/**
 * 这是用来处理branch.do请求的action extends baseAction
 * @author Administrator
 *
 */
public class BranchAction extends BaseAction {
	
	/**
	 * 定义业务对象，由spring容器注入
	 */
	private BranchBiz branchBiz =null ;

	private ProjectBiz projectBiz = null ;
	
	private CrmBiz crmBiz = null ;
	
	private UserBiz	userBiz = null ;
	
	private Logger logger = Logger.getLogger(BranchAction.class);
	
	
	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}


	public void setCrmBiz(CrmBiz crmBiz) {
		this.crmBiz = crmBiz;
	}


	public void setProjectBiz(ProjectBiz projectBiz) {
		this.projectBiz = projectBiz;
	}


	public void setBranchBiz(BranchBiz branchBiz) {
		this.branchBiz = branchBiz;
	}
	
	
	/**
	 * 登录界面，分支用户调用此方法
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toListByBranch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//根据branchId ,获取该分支下的所有项目
		
		String branchId = request.getParameter("branchId");
		
		if(branchId==null || branchId.equals("")){
			request.setAttribute("errorMsg", "分支标识不存在或已经删除!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toListByBranch)传递了非法的分支标示！");
			throw new Exception("分支标识不存在!!") ;
		}
			
		
		TbccBranchType branchType = this.branchBiz.getById(new Long(branchId));
		
		
		
		if(branchType==null){
			request.setAttribute("errorMsg", "分支标识不存在或已经删除!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+" (toListByBranch)当前引用的分支标识不存在 ！");
			throw new Exception("分支标识不存在！");
		}
			
		
		
		//定义三个变量、分别保存冷库工程、车载工程、小批零工程
		List<TbccPrjType> rList = new ArrayList<TbccPrjType>();
		List<TbccPrjType> cList = new ArrayList<TbccPrjType>();
		List<TbccPrjType> bList = new ArrayList<TbccPrjType>();
		
		
		
		for (TbccPrjType tbccPrjType : branchType.getPrjTypes()) {
			//这是后来增加的把制冷工程和冷库工程暂时先加在一起
			//把冷库与制冷的混合也加入到了工程之中
			if(tbccPrjType.getProjectType().equals(ProjectBiz.REF) || tbccPrjType.getProjectType().equals(ProjectBiz.COOLER)
					|| tbccPrjType.getProjectType().equals(ProjectBiz.REF_COOLER)|| tbccPrjType.getProjectType().equals(ProjectBiz.COOLER_BASE)
					|| tbccPrjType.getProjectType().equals(ProjectBiz.REF_COOLER_BASE)){
				rList.add(tbccPrjType) ;
				continue ;
			}	
			
			if(tbccPrjType.getProjectType().equals(ProjectBiz.CAR))
			{
				cList.add(tbccPrjType) ;
				continue ;
			}
			if(tbccPrjType.getProjectType().equals(ProjectBiz.BOX))
			{
				bList.add(tbccPrjType) ;
				continue ;
			}
		}
		
		request.setAttribute("rList", rList);
		request.setAttribute("cList", cList);
		request.setAttribute("bList", bList);
		

		
		
		//*************保存必要的权限功能
		if(request.getSession().getAttribute("power")==null){
			List<TbccFunction> functionlist = userBiz.getUserFunction((TbccBaseUser)request.getSession().getAttribute("LoginUser")) ;							
			request.getSession().setAttribute("power",userBiz.getPower(functionlist) ) ;
		}

		
		
		
		//根据branchId ，获取与该客户相关客户的branchId	
		List<TbccCrm> crmList = crmBiz.getByHid(branchId);
				
		if(crmList!=null && crmList.size()>0){
			
			List<String> branchIdList = new ArrayList<String>();
		
			for (TbccCrm tbccCrm : crmList) {
				branchIdList.add(tbccCrm.getCid());
			}
			
			List<TbccBranchType> branchList = branchBiz.getByIds(branchIdList) ;
			
			request.setAttribute("branchList", branchList);
		}
			
		
		return  mapping.findForward("plist");
		
	}
	
	
	
	/**
	 * 登陆页面、总部用户登录，选择某个分支时操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toListByHqAndBranch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//根据branchId ,获取该分支下的所有项目
		
		String branchId = request.getParameter("branchId");
		
		if(branchId==null || branchId.equals("")){
			request.setAttribute("errorMsg", "分支标识不存在或已经删除!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toListByHqAndBranch)传递了非法的分支标识");
			throw new Exception("分支标识不存在！");
		}
			
		
		TbccBranchType branchType = this.branchBiz.getById(new Long(branchId));
		
		
		
		if(branchType==null)
		{
			request.setAttribute("errorMsg", "分支标识不存在或已经删除!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toListByHqAndBranch)当前引用的分支标识不存在 ！");
			throw new Exception("分支标识不存在！");
		}
		
		//定义三个变量、分别保存冷库工程、车载工程、小批零工程
		List<TbccPrjType> rList = new ArrayList<TbccPrjType>();
		List<TbccPrjType> cList = new ArrayList<TbccPrjType>();
		List<TbccPrjType> bList = new ArrayList<TbccPrjType>();
		
		
		
		for (TbccPrjType tbccPrjType : branchType.getPrjTypes()) {
			if(tbccPrjType.getProjectType().equals(ProjectBiz.REF) ||tbccPrjType.getProjectType().equals(ProjectBiz.COOLER)||
					tbccPrjType.equals(ProjectBiz.REF_COOLER)|| tbccPrjType.getProjectType().equals(ProjectBiz.COOLER_BASE)
					|| tbccPrjType.getProjectType().equals(ProjectBiz.REF_COOLER_BASE)){
				rList.add(tbccPrjType) ;
				continue ;
			}	
			if(tbccPrjType.getProjectType().equals(ProjectBiz.CAR))
			{
				cList.add(tbccPrjType) ;
				continue ;
			}
			if(tbccPrjType.getProjectType().equals(ProjectBiz.BOX))
			{
				bList.add(tbccPrjType) ;
				continue ;
			}
		}
		
		request.setAttribute("rList", rList);
		request.setAttribute("cList", cList);
		request.setAttribute("bList", bList);
		
		
		

		
		//*************保存必要的权限功能,需要总部用户的角色、和所要查看的分支编号
		if(request.getSession().getAttribute("power")==null){
		List<TbccFunction> functionlist = userBiz.getHqUserFunction((TbccBaseUser)request.getSession().getAttribute("LoginUser"), new Long(branchId));
		request.getSession().setAttribute("power",userBiz.getPower(functionlist));
		}
		
		//根据branchId ，获取与该客户相关客户的branchId	
		List<TbccCrm> crmList = crmBiz.getByHid(branchId);
				
		if(crmList!=null && crmList.size()>0){
			
			List<String> branchIdList = new ArrayList<String>();
		
			for (TbccCrm tbccCrm : crmList) {
				branchIdList.add(tbccCrm.getCid());
			}
			
			List<TbccBranchType> branchList = branchBiz.getByIds(branchIdList) ;
			
			request.setAttribute("branchList", branchList);
		}	
		
		return mapping.findForward("plist") ;
	}

	/**
	 * 这是后面修改主页面所增加的。分支用户登陆之后直接跳转的主页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toMain(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获取登陆用户所属的分支编号branchId
		String branchId = request.getParameter("branchId") ;
		
		//验证分支是否合法
		if(branchId==null || branchId.equals("")){
			request.setAttribute("errorMsg", "分支标识不存在或已经删除!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toMain)传递了非法的分支标识");
			throw new Exception("分支标识不存在或已经删除！");
		}
		
		//保存分支用户的权限,测试时，不考虑权限
		List<TbccFunction> functionlist = userBiz.getUserFunction((TbccBaseUser)request.getSession().getAttribute("LoginUser")) ;							
		request.getSession().setAttribute("power",userBiz.getPower(functionlist) ) ;
		
		
		return mapping.findForward("main") ;
	}
	
	
	/**
	 * 总部用户登陆后所跳转的主页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toHqMain(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取登陆用户所选择的所属的分支编号branchId
		String branchId = request.getParameter("branchId");
		
		if(branchId==null || branchId.equals("")){
			request.setAttribute("errorMsg", "分支标识不存在或已经删除!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toHqMain)传递了非法的分支标识");
			throw new Exception("分支标识不存在或已经删除！");
		}
		
		//保存总部用户的权限、测试时，不需要考虑
		List<TbccFunction> functionlist = userBiz.getHqUserFunction((TbccBaseUser)request.getSession().getAttribute("LoginUser"), new Long(branchId));
		request.getSession().setAttribute("power",userBiz.getPower(functionlist));
		return mapping.findForward("main") ;
	}
	
	
}
