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
 * ������������pro.do�����Action extends baseAction
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
	 * ��ת����⹤���б�ҳ��
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
		//��ȡ��֧��ʾId
		String branchId = request.getParameter("branchId") ;
		
		//�жϷ�֧��ʾId��Ч��
		if(branchId==null || branchId.equals("")){
			request.setAttribute("errorMsg", "��֧��ʶ�����ڻ��Ѿ�ɾ��!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRefList)�����˷Ƿ��ķ�֧��ʾ��");
			throw new Exception("��֧��ʶ������!!") ;
		}
		
		
		//��ȡ��֧����
		TbccBranchType branchType = this.branchBiz.getById(new Long(branchId));
		
		
		//�жϷ�֧�����Ƿ����
		if(branchType==null){
			request.setAttribute("errorMsg", "��֧��ʶ�����ڻ��Ѿ�ɾ��!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+" (toRefList)��ǰ���õķ�֧��ʶ������ ��");
			throw new Exception("��֧��ʶ�����ڣ�");
		}
			
		
		
		//��������������е���⹤��
		List<TbccPrjType> rList = new ArrayList<TbccPrjType>();

		
		
		//ѭ���������е���⹤���б�
		for (TbccPrjType tbccPrjType : branchType.getPrjTypes()) {
			//���Ǻ������ӵİ����乤�̺���⹤����ʱ�ȼ���һ��
			//�����������Ļ��Ҳ���뵽�˹���֮��
			if(tbccPrjType.getProjectType().equals(ProjectBiz.REF) || tbccPrjType.getProjectType().equals(ProjectBiz.COOLER)
					|| tbccPrjType.getProjectType().equals(ProjectBiz.REF_COOLER)|| tbccPrjType.getProjectType().equals(ProjectBiz.COOLER_BASE)
					|| tbccPrjType.getProjectType().equals(ProjectBiz.REF_COOLER_BASE)){
				rList.add(tbccPrjType) ;
				continue ;
			}	
			
			
		}	
		request.setAttribute("rList", rList);
		
		
	
		// ��ȡ�ͻ����������⹤���б� ����branchId ����ȡ��ÿͻ���ؿͻ���branchId	
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
	 * ��ת�����ع����б�ҳ��
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
		//��ȡ��֧��ʾId
		String branchId = request.getParameter("branchId") ;
		
		//�жϷ�֧��ʾId��Ч��
		if(branchId==null || branchId.equals("")){
			request.setAttribute("errorMsg", "��֧��ʶ�����ڻ��Ѿ�ɾ��!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toCarList)�����˷Ƿ��ķ�֧��ʾ��");
			throw new Exception("��֧��ʶ������!!") ;
		}
		
		
		//��ȡ��֧����
		TbccBranchType branchType = this.branchBiz.getById(new Long(branchId));
		
		
		//�жϷ�֧�����Ƿ����
		if(branchType==null){
			request.setAttribute("errorMsg", "��֧��ʶ�����ڻ��Ѿ�ɾ��!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+" (toCarList)��ǰ���õķ�֧��ʶ������ ��");
			throw new Exception("��֧��ʶ�����ڣ�");
		}
			
		
		
		//��������������еĳ��ع���
		List<TbccPrjType> cList = new ArrayList<TbccPrjType>();
		
		for (TbccPrjType tbccPrjType : branchType.getPrjTypes()) {
			//�������еĳ��ع���
			if(tbccPrjType.getProjectType().equals(ProjectBiz.CAR)){
				cList.add(tbccPrjType) ;
				continue ;
			}							
		}
		
		request.setAttribute("cList", cList);
		return mapping.findForward("carlist") ;
	}
	
	/**
	 * ��ת��С���㹤���б�ҳ��
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
