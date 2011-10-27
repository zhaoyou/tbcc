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
 * ����
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
	 * ��ת����վ��������ҳ��
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
				request.setAttribute("errorMsg", "��֧��ʶ�����ڻ��Ѿ�ɾ��!");
				logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+  " (toParamConfig)������һ���Ƿ��ķ�֧��ʶ ��");
				throw new Exception("��֧��ʶ������!!") ;
			}
			
			if(request.getSession().getAttribute("carprjList")==null){
				List<TbccPrjType> carList = projectBiz.getCarProjListBybId(new Long(branchId)) ;
				if(carList!=null && carList.size()>0)
					request.getSession().setAttribute("carprjList", carList);
			}
			
			
			return mapping.findForward("toparam") ;
	}
	
	
	/**
	 * ���ĳ��ز�������ҳ��
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
