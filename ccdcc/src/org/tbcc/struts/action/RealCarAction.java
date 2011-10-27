package org.tbcc.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.tbcc.biz.BranchBiz;
import org.tbcc.biz.HisCarBiz;
import org.tbcc.entity.TbccBaseUser;
import org.tbcc.entity.TbccBranchType;
import org.tbcc.entity.TbccPrjType;
import org.tbcc.util.BaseAction;

/**
 *  ����һ��action ���� realcar.do ������ extends baseAction
 * @author Administrator
 *
 */
public class RealCarAction extends BaseAction {
	
	private BranchBiz branchbiz = null;
	
	private HisCarBiz hiscarBiz = null ;
	
	private Logger logger = Logger.getLogger(RealCarAction.class);
	
	/**
	 * ��spring����ע��
	 * @param hiscarBiz
	 */
	public void setHiscarBiz(HisCarBiz hiscarBiz) {
		this.hiscarBiz = hiscarBiz;
	}
	
	public BranchBiz getBranchbiz() {
		return branchbiz;
	}

	public void setBranchbiz(BranchBiz branchbiz) {
		this.branchbiz = branchbiz;
	}

	/**
	 * ����ʵʱ���ݵ�ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toRealData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		String branchId = request.getParameter("branchId") ;
		
		if(branchId==null||branchId.equals("")){
			request.setAttribute("errorMsg", "���������ڻ��Ѿ�ɾ��!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+  "(toRealData)������һ���Ƿ��Ļ�����ʾ!");
			throw new Exception("�����Ƿ�!");
		}
		
		if(request.getParameter("exeget")==null){
			return mapping.findForward("reallist2");
		}else{
			TbccBranchType branch = branchbiz.getById(Long.valueOf(branchId));
			if(branch==null){
				return mapping.findForward("index");
			}else{
				request.setAttribute("exeget", request.getParameter("exeget"));
				return mapping.findForward("reallist2_exe");
			}
		}
		
	}
	
	/**
	 * ��ת�ĳ���ʵʱ���ݵ�ͼ��ʾҳ��
	 * @param mapping		����ӳ���ϵ����Ϣ
	 * @param form			��
	 * @param request		����
	 * @param response		��Ӧ
	 * @return				���ص� realMapӳ���jspҳ��
	 * @throws Exception
	 */
	public ActionForward toRealMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		String branchId = request.getParameter("branchId") ;
		
		if(branchId==null||branchId.equals("")){
			request.setAttribute("errorMsg", "���������ڻ��Ѿ�ɾ��!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+  "(toRealMap)������һ���Ƿ��Ļ�����ʾ!");
			throw new Exception("�����Ƿ�!");
		}
		
		/**
		 * ��עһ�¡�������չʵ��ʵʱ���ݵ�ͼ��ʾ��ʱ��Ҳ��Ҫ��ȡ���е��ƶ����ع��̡�����Ҳ�ǹ���һ��carprjList
		 * �����������ж�һ�¡�session���Ƿ�����˳��ؼ��ϡ�������ڡ�����Ҫ��ѯ��
		 * Ϊ�˽�������ѡ���session���⣬ÿ�ν���ҳ�涼���²�ѯ
		 */
		
	//	if(request.getSession().getAttribute("carprjList")==null){
			//��ȡ�û����µ������ƶ�������Ŀ
			List<TbccPrjType> carlist = hiscarBiz.getCarPrjList(new Long(branchId));
		
		
			// ����ط��Ұ����е��ƶ���Ŀ����session���в�Ҫÿ�β�ѯ��Ҫȥ�����ݿ⣬�Ժ�����ܿ��ǣ�Ҫ��һ��
				if(carlist!=null && carlist.size()>0)
					request.getSession().setAttribute("carprjList", carlist);  	
	//	}
		
		
		if(request.getParameter("exeget")==null){
			return mapping.findForward("realmap2");
		}else{
			request.setAttribute("exeget", request.getParameter("exeget"));
			return mapping.findForward("realmap2_exe");
		}
	}
	
	/**
	 * 		ˢ��ʵʱ����ҳ��ĵ�ͼ��ʾ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doRealMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {	
		
		
		
		String branchId = request.getParameter("branchId") ;
		
		if(branchId==null||branchId.equals("")){
			request.setAttribute("errorMsg", "���������ڻ��Ѿ�ɾ��!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+  "(doRealMap)������һ���Ƿ��Ļ�����ʾ!");
			throw new Exception("�����Ƿ�!");
		}
		
		if(request.getParameter("exeget")==null){
			return mapping.findForward("realmap2");
		}else{
			request.setAttribute("exeget", request.getParameter("exeget"));
			return mapping.findForward("realmap2_exe");
		}
	}
	
}
