package org.tbcc.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.tbcc.biz.ProjectBiz;
import org.tbcc.entity.TbccBaseUser;
import org.tbcc.entity.TbccPrjType;
import org.tbcc.util.BaseAction;

/**
 * 这是
 * @author administrator
 *
 */
public class ParamOperateAction extends BaseAction {
	
	
	private ProjectBiz projectBiz = null ;
	
	private Logger logger = Logger.getLogger(ParamOperateAction.class);
	
	public void setProjectBiz(ProjectBiz projectBiz) {
		this.projectBiz = projectBiz;
	}

	
	/**
	 * 跳转到车站参数配置页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toParamConfig(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String branchId = request.getParameter("branchId");
			
			if(branchId==null || branchId.equals(""))
			{
				request.setAttribute("errorMsg", "分支标识不存在或已经删除!");
				logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+  " (toParamConfig)传递了一个非法的分支标识 ！");
				throw new Exception("分支标识不存在!!") ;
			}
			
			if(request.getSession().getAttribute("carprjList")==null){
				List<TbccPrjType> carList = projectBiz.getCarProjListBybId(new Long(branchId)) ;
				if(carList!=null && carList.size()>0)
					request.getSession().setAttribute("carprjList", carList);
			}
			
			
			return mapping.findForward("toparam") ;
	}
	
	
	/**
	 * 更改车载参数配置页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward changeParam(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			return mapping.findForward("toparam") ;
	}
	
	
	
	
}
