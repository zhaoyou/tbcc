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
 * �����ʷaction���� hisref.do ���� Extends baseAction
 * @author Administrator
 *
 */
public class HisRefAction extends BaseAction {

	
	
	private HisRefBiz 	hisRefbiz = null ;
	
	private ProjectBiz projectBiz = null ;
	
	private RefInfoBiz	refInfoBiz = null ;
	
	private Logger logger = Logger.getLogger(HisRefAction.class);

	/**
	 * ��spring����ע����ʷ������ҵ����
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
	 * ��ת�������ʷ���ݲ�ѯҳ��,��Ҫ��ȡ�÷�֧�µ�������⹤�̡��Լ�ĳ����⹤�̵��µ��������
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
			request.setAttribute("errorMsg", "���������ڻ��Ѿ�ɾ��!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toHisRefList)������һ���Ƿ��Ļ�����ʶ��");
			new Exception("û����Ӧ����Դ��");
		}
		
		List<TbccPrjType> refPrjList = null ;
		List<TbccRefInfo> refList	 = null ;
		
		
		//��ȡ��⹤����Դ
		/**
		 * Ϊ�˽�������ѡ�session���⣬ÿ�ν��������ʷ��ѯҳ�涼���²�ѯ����
		 */
		//if(request.getSession().getAttribute("refPrjList")==null){
			refPrjList = projectBiz.getRefProjListBybId(new Long(branchId));
			request.getSession().setAttribute("refPrjList", refPrjList);
		//}else{
			refPrjList = (List<TbccPrjType>)request.getSession().getAttribute("refPrjList");
		//}
		
		//��ȡĬ�ϵ�һ����⹤���µ���������б�
		if(refPrjList!=null && refPrjList.size()>0){
			refList = refInfoBiz.getRefListByPrj(refPrjList.get(0).getProjectId()) ;
		}
		
		request.setAttribute("refList", refList);
		request.setAttribute("flag", 1) ;
		
		
		
//		if(refList==null || refList.size()<=0){
//			new Exception("�������Դ������");
//		}else{
//			request.getSession().setAttribute("refProList", refList);  		//�����е������Ŀ����session��
//			request.setAttribute("flag", 1);								//���ڿ���ҳ����ʾ
//		}
		
		return mapping.findForward("hisref2");
	}
	
	/**
	 * ��ѯ�����ʷ����
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
			request.setAttribute("errorMsg", "����Ϊ�ջ�Ƿ�!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (doHisRefData)��ȡ��ʷ���ݣ������˷Ƿ��Ĳ���");
			throw new Exception("û���������Դ!");
		}
		
		//���»�ȡ��⹤���µ�����б�
		List<TbccRefInfo> refList = refInfoBiz.getRefListByPrj(myrefprojlist) ;
		request.setAttribute("refList", refList);
		
		// ��ȡĳ��������ͷ��Ϣ����ʷ������Ϣ
		List obj  = hisRefbiz.getRefHisData(Long.parseLong(myrid), startTime, endTime, type, value);
		
		//�ж��Ƿ���ڽ��������
		if(obj!=null && obj.size()>0){
			
			request.setAttribute("aiInfoList", obj.get(0));		//��ȡ̽ͷ��Ϣ
			
			request.setAttribute("refDataList", obj.get(1));	//��ȡ������Ϣ
		
			Object[] result = (Object[])obj.get(2) ;	//��ȡ����ͳ����Ϣ
		
			//����
		if(result!=null && result.length>0){
			request.setAttribute("min", result[0]) ;
			request.setAttribute("max", result[1]) ;
			request.setAttribute("avg", result[2]) ;
		}
			
		}
		
		
		return mapping.findForward("hisref2");
	}
	
	/**
	 * ��ת�������ʷ������ʾҳ��
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
			request.setAttribute("errorMsg", "���������ڻ��Ѿ�ɾ��!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toCurve)������һ���Ƿ��Ļ�����ʶ��");
			new Exception("û����Ӧ����Դ��");
		}
		
		return mapping.findForward("refCurve");
	}


	
	
	
		
}
