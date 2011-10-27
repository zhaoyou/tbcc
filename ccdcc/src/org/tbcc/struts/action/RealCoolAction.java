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
 * ����һ��������ص�action
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
	 * ��ת������ʵʱ����ҳ��
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
			request.setAttribute("errorMsg", "���������ڻ��Ѿ�ɾ��!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRealCool)������һ����Ч�ķ�֧��ʶ");
			throw new Exception("�����Ƿ��쳣");
		}
			
		
		
		
		
		List<TbccCompressorSet> compressorSetList = null ;
		
		//�ж�session���Ƿ���ڸû��鼯��
		if(request.getSession().getAttribute("coollist")==null){
			//��ȡ��ǰ�����������豸�������ֵ�session����
			compressorSetList = compressorSetBiz.getByBranchId(new Long(branchId)); 
			request.getSession().setAttribute("coollist",compressorSetList);
		}else{
			compressorSetList = (List<TbccCompressorSet>)request.getSession().getAttribute("coollist") ;
		}
		
		
		
		if(request.getSession().getAttribute("refprojectids")==null){
			
			//��ȡ�÷�֧�µĶ�����⹤�̱�ʶId	,����ԭ�ȵ� ʵ�֡������Ѿ��ǻ�ȡ��⹤�̺����乤�̵��ܺ�
			//�����������䡢�ֿ�������ϡ�8di��24di�Ĳ�ͬϵͳ
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
		

		 
		//�����ǰ�Ĺ���projectId��Ӧ�������豸��δ��ʼ��...
		if(compressorSetList==null || compressorSetList.size()==0){
			request.setAttribute("noMsg", "<font color='red'>û�з�����Ӧ�������豸...</font>") ;
			return mapping.findForward("singlecool") ;		//���û�л��顢��Ĭ����ת����������ҳ�棬��ʾ
		}else{
			
			//�жϵ�һ����ʾ�����������顢���ǲ�������
			TbccCompressorSet comset = compressorSetList.get(0) ;
			
			request.setAttribute("compressorset", comset);
			
			//��Ҫ��ʾ�Ļ����µ����л�ͷ��ʶ����������ʶ��������ʶ����������
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
	 * ѡ��ͬ���������ʱ����ת����ͬ��ҳ����ʾ
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
			request.setAttribute("errorMsg", "����Ϊ�ջ�Ƿ�!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (changeRealCool)������һ���Ƿ��Ļ����ʾ");
			throw new Exception("�����Ƿ��쳣...");
		}
			
		
		TbccCompressorSet compressorset = compressorSetBiz.getById(new Integer(mysetid));
		
		if(compressorset==null){
			request.setAttribute("errorMsg", "�û��鲻���ڻ����Ѿ�ɾ��") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ "(changeRealCool)���õĻ��鲻����!");
			throw new Exception("ʵ������...");
		}
		request.setAttribute("compressorset", compressorset);
		
		//��Ҫ��ʾ�Ļ����µ����л�ͷ��ʶ����������ʶ��������ʶ����������
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
	 * ���ݱ�ʶId���ϣ�ת��Ϊһ���ַ�����ʽ
	 * @param list		��ʶId����
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
	 * ��ת������ϵͳ����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toRealCoolSys (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//��֤��֧�����Ƿ���ȷ
		String branchId = 	request.getParameter("branchId") ;
		
		if(branchId==null || branchId.equals("")){
			request.setAttribute("errorMsg", "���������ڻ��Ѿ�ɾ��!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+  " (toRealCoolSys)������һ���Ƿ��ķ�֧��ʶ��");
			throw new Exception("�����Ƿ��쳣");
		}
		
		//��ȡ�÷�֧�µ����л�����Ϣ
		List<TbccCompressorSet> list = compressorSetBiz.getByBranchId(new Long(branchId)) ;
		request.setAttribute("coollist", list);
		
		
		//��ȡ���е�����ϵͳ��صĹ��̱��
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
	 * ��ת����ͬ�Ļ���ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toRealCoolSingleMul (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//��֤��֧�����Ƿ���ȷ
		String branchId = 	request.getParameter("branchId") ;
		
		if(branchId==null || branchId.equals("")){
			request.setAttribute("errorMsg", "���������ڻ��Ѿ�ɾ��!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRealCoolSingleMul)������һ���Ƿ��ķ�֧��ʶ��");
			throw new Exception("�����Ƿ��쳣");
		}
		
		//��ȡ�÷�֧�µ����л�����Ϣ
		List<TbccCompressorSet> list = compressorSetBiz.getByBranchId(new Long(branchId)) ;
		request.setAttribute("coollist", list);
		
		
		
		//��ȡ��ǰѡ�еĻ����ʾ
		String mysetid = request.getParameter("mysetid") ;
		
		if(mysetid==null || mysetid.equals("")){
			request.setAttribute("errorMsg", "����Ϊ�ջ�Ƿ�!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRealCoolSingleMul)������һ���Ƿ��Ļ����ʾ��");
			throw new Exception("�����Ƿ��쳣...");
		} 
		
		//��ȡ��ǰ�������Ϣ
		TbccCompressorSet compressorset = compressorSetBiz.getById(new Integer(mysetid));
		if(compressorset==null){
			request.setAttribute("errorMsg", "�û��鲻���ڻ����Ѿ�ɾ��") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRealCoolSingleMul)��ǰ���õĻ��鲻���� ��");
			throw new Exception("ʵ������...");
		}
		request.setAttribute("compressorset", compressorset);
		
		//��Ҫ��ʾ�Ļ����µ����л�ͷ��ʶ����������ʶ��������ʶ����������
		request.setAttribute("compressorStr",getStr(compressorBiz.getIdsByCsId(compressorset.getId()))) ;
		request.setAttribute("aircoolerStr", getStr(aircoolerBiz.getIdsBycsId(compressorset.getId()))) ;
		request.setAttribute("condenserStr", getStr(condenserBiz.getIdsByCsid(compressorset.getId())));
		
		//���ݻ���Ĳ�ͬ������ת�Ĳ�ͬ��ҳ��
		if(compressorset.getCsType().toString().equals(CompressorSetBiz.SINGLECOMPRESSOR.toString())){
			return mapping.findForward("singlecool2") ;
		}else{
			return mapping.findForward("mulcool2") ;
		}
		
	}
}
