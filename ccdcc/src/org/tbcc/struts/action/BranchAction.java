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
 * ������������branch.do�����action extends baseAction
 * @author Administrator
 *
 */
public class BranchAction extends BaseAction {
	
	/**
	 * ����ҵ�������spring����ע��
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
	 * ��¼���棬��֧�û����ô˷���
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
		
		//����branchId ,��ȡ�÷�֧�µ�������Ŀ
		
		String branchId = request.getParameter("branchId");
		
		if(branchId==null || branchId.equals("")){
			request.setAttribute("errorMsg", "��֧��ʶ�����ڻ��Ѿ�ɾ��!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toListByBranch)�����˷Ƿ��ķ�֧��ʾ��");
			throw new Exception("��֧��ʶ������!!") ;
		}
			
		
		TbccBranchType branchType = this.branchBiz.getById(new Long(branchId));
		
		
		
		if(branchType==null){
			request.setAttribute("errorMsg", "��֧��ʶ�����ڻ��Ѿ�ɾ��!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+" (toListByBranch)��ǰ���õķ�֧��ʶ������ ��");
			throw new Exception("��֧��ʶ�����ڣ�");
		}
			
		
		
		//���������������ֱ𱣴���⹤�̡����ع��̡�С���㹤��
		List<TbccPrjType> rList = new ArrayList<TbccPrjType>();
		List<TbccPrjType> cList = new ArrayList<TbccPrjType>();
		List<TbccPrjType> bList = new ArrayList<TbccPrjType>();
		
		
		
		for (TbccPrjType tbccPrjType : branchType.getPrjTypes()) {
			//���Ǻ������ӵİ����乤�̺���⹤����ʱ�ȼ���һ��
			//�����������Ļ��Ҳ���뵽�˹���֮��
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
		

		
		
		//*************�����Ҫ��Ȩ�޹���
		if(request.getSession().getAttribute("power")==null){
			List<TbccFunction> functionlist = userBiz.getUserFunction((TbccBaseUser)request.getSession().getAttribute("LoginUser")) ;							
			request.getSession().setAttribute("power",userBiz.getPower(functionlist) ) ;
		}

		
		
		
		//����branchId ����ȡ��ÿͻ���ؿͻ���branchId	
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
	 * ��½ҳ�桢�ܲ��û���¼��ѡ��ĳ����֧ʱ����
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
		//����branchId ,��ȡ�÷�֧�µ�������Ŀ
		
		String branchId = request.getParameter("branchId");
		
		if(branchId==null || branchId.equals("")){
			request.setAttribute("errorMsg", "��֧��ʶ�����ڻ��Ѿ�ɾ��!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toListByHqAndBranch)�����˷Ƿ��ķ�֧��ʶ");
			throw new Exception("��֧��ʶ�����ڣ�");
		}
			
		
		TbccBranchType branchType = this.branchBiz.getById(new Long(branchId));
		
		
		
		if(branchType==null)
		{
			request.setAttribute("errorMsg", "��֧��ʶ�����ڻ��Ѿ�ɾ��!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toListByHqAndBranch)��ǰ���õķ�֧��ʶ������ ��");
			throw new Exception("��֧��ʶ�����ڣ�");
		}
		
		//���������������ֱ𱣴���⹤�̡����ع��̡�С���㹤��
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
		
		
		

		
		//*************�����Ҫ��Ȩ�޹���,��Ҫ�ܲ��û��Ľ�ɫ������Ҫ�鿴�ķ�֧���
		if(request.getSession().getAttribute("power")==null){
		List<TbccFunction> functionlist = userBiz.getHqUserFunction((TbccBaseUser)request.getSession().getAttribute("LoginUser"), new Long(branchId));
		request.getSession().setAttribute("power",userBiz.getPower(functionlist));
		}
		
		//����branchId ����ȡ��ÿͻ���ؿͻ���branchId	
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
	 * ���Ǻ����޸���ҳ�������ӵġ���֧�û���½֮��ֱ����ת����ҳ��
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
		//��ȡ��½�û������ķ�֧���branchId
		String branchId = request.getParameter("branchId") ;
		
		//��֤��֧�Ƿ�Ϸ�
		if(branchId==null || branchId.equals("")){
			request.setAttribute("errorMsg", "��֧��ʶ�����ڻ��Ѿ�ɾ��!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toMain)�����˷Ƿ��ķ�֧��ʶ");
			throw new Exception("��֧��ʶ�����ڻ��Ѿ�ɾ����");
		}
		
		//�����֧�û���Ȩ��,����ʱ��������Ȩ��
		List<TbccFunction> functionlist = userBiz.getUserFunction((TbccBaseUser)request.getSession().getAttribute("LoginUser")) ;							
		request.getSession().setAttribute("power",userBiz.getPower(functionlist) ) ;
		
		
		return mapping.findForward("main") ;
	}
	
	
	/**
	 * �ܲ��û���½������ת����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toHqMain(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		//��ȡ��½�û���ѡ��������ķ�֧���branchId
		String branchId = request.getParameter("branchId");
		
		if(branchId==null || branchId.equals("")){
			request.setAttribute("errorMsg", "��֧��ʶ�����ڻ��Ѿ�ɾ��!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toHqMain)�����˷Ƿ��ķ�֧��ʶ");
			throw new Exception("��֧��ʶ�����ڻ��Ѿ�ɾ����");
		}
		
		//�����ܲ��û���Ȩ�ޡ�����ʱ������Ҫ����
		List<TbccFunction> functionlist = userBiz.getHqUserFunction((TbccBaseUser)request.getSession().getAttribute("LoginUser"), new Long(branchId));
		request.getSession().setAttribute("power",userBiz.getPower(functionlist));
		return mapping.findForward("main") ;
	}
	
	
}
