package org.tbcc.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.tbcc.biz.HisBoxBiz;
import org.tbcc.biz.HisStartUpBiz;
import org.tbcc.biz.ProjectBiz;
import org.tbcc.entity.TbccBaseHisBox;
import org.tbcc.entity.TbccBaseHisStartUp;
import org.tbcc.entity.TbccBaseUser;
import org.tbcc.entity.TbccPrjType;
import org.tbcc.util.BaseAction;

/**
 * С������ʷaction ���� hisbox.do ������ extends baseAction
 * @author Administrator
 *
 */
public class HisBoxAction extends BaseAction {
		
	private HisBoxBiz hisboxBiz = null ;

	private HisStartUpBiz startupBiz = null ;
	
	private ProjectBiz proBiz = null ;
	
	private Logger logger = Logger.getLogger(HisBoxAction.class);
	
	public void setStartupBiz(HisStartUpBiz startupBiz) {
		this.startupBiz = startupBiz;
	}


	public void setProBiz(ProjectBiz proBiz) {
		this.proBiz = proBiz;
	}


	/**
	 * ��springע��
	 * @param hisboxBiz
	 */
	public void setHisboxBiz(HisBoxBiz hisboxBiz) {
		this.hisboxBiz = hisboxBiz;
	}

	
	/**
	 * ��ת����ʷС�����ѯ����
	 */
	public ActionForward toBoxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String branchId = request.getParameter("branchId");
		
		//���ݻ���Id����ȡ���е�С������Ŀ
		List<TbccPrjType> boxList = this.hisboxBiz.getBoxPrjByBranchID(new Long(branchId));
		
		if(boxList!=null && boxList.size()>0)
			request.getSession().setAttribute("boxprjList", boxList);
		
		request.setAttribute("flag", 1) ;		//û��ʲô���ã����ƽ�����ʾ
		
		return mapping.findForward("hisbox");
	}
	
	
	/**
	 *  ����������ѯС��������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doBoxListByProperty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String startTime = request.getParameter("time1");
		String endTime = request.getParameter("time2");
		String proId  = request.getParameter("proId");
		String timeType = request.getParameter("timeType");
		String timeValue = request.getParameter("timevalue");
		
		if(startTime==null || endTime==null || proId==null || timeType==null || timeValue==null||
				startTime=="" || endTime==""|| proId==""|| timeType=="" || timeValue==""){
			request.setAttribute("msg", "��ȷ����Ϣ������!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (doBoxListByProperty)�����˷Ƿ��Ĳ����б�");
			return mapping.findForward("hisbox") ;
		}
		//��ȡ���е��ƶ����ص���ʷ����
		List<TbccBaseHisBox> hisboxList = this.hisboxBiz.getByProperty(proId, startTime, endTime, timeType, timeValue);
		
		request.setAttribute("hisboxList", hisboxList);
		//��ȡͳ����Ϣ
		if(hisboxList!=null && hisboxList.size()>0){
			Object[] result = this.hisboxBiz.getCalcValue(hisboxList);
			
			request.setAttribute("min", result[0]);
			request.setAttribute("max", result[1]); 
			request.setAttribute("avg", result[2]);
			
			
		}
		return mapping.findForward("hisbox");
	}
	
	//***************************�����ǵ�һ��ʵ�ַ�ʽ��������ͨ����ͣ��¼ȥ��ѯ��******************************
	
	
	/**
	 * ת��С�������ͣ��¼��ѯҳ��
	 */
	public ActionForward toHisBoxStart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String branchId = request.getParameter("branchId");
		
		//���ݻ���Id����ȡ���е�С������Ŀ
		List<TbccPrjType> boxList = this.hisboxBiz.getBoxPrjByBranchID(new Long(branchId));
		
		if(boxList!=null && boxList.size()>0)
			request.getSession().setAttribute("boxprjList", boxList);
		
		request.setAttribute("flag", 1) ;		//û��ʲô���ã����ƽ�����ʾ
		
		return mapping.findForward("hboxStart");
	}
	
	
	/**
	 * ת��С�����ѯҳ��(������ͣ��¼)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toHisBoxByStart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String branchId = request.getParameter("branchId");
		String proId = request.getParameter("proId");
		String sid = request.getParameter("sid") ;
		
		TbccPrjType project = this.proBiz.getByProId(proId);
		
		TbccBaseHisStartUp startup = this.startupBiz.getStartUp(proId, Long.parseLong(sid));
		
		if(branchId==null || branchId=="" || project==null || startup==null){
			request.setAttribute("errorMsg", "�����Ƿ��򲻴���!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toHisBoxByStart)�����˷Ƿ��ķ�֧��ʾ�򹤳̱�ʾ") ;
			throw new Exception("�������Դ��Ч!");
		}
			
		
		request.setAttribute("project", project);
		
		request.setAttribute("startup", startup);
		
		request.setAttribute("flag", 1);
			
		return mapping.findForward("hisbox2") ;
	}
	
	/**
	 * ��������������ͣ��¼��ѯС�������ʷ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doHisBoxByStart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String startTime = request.getParameter("time1");
		String endTime = request.getParameter("time2");
		String proId  = request.getParameter("proId");
		String interval = request.getParameter("interval");
		String timeValue = request.getParameter("timevalue");
		
		if(startTime==null || endTime==null || proId==null || interval==null || timeValue==null||
				startTime=="" || endTime==""|| proId==""|| interval=="" || timeValue==""){
			request.setAttribute("msg", "��ȷ����Ϣ������!");
			logger.error( ((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (doHisBoxByStart)�����˷Ƿ��Ĳ����б�!");
			return mapping.findForward("hisbox2") ;
		}
		//��ȡ���е��ƶ����ص���ʷ����
		List<TbccBaseHisBox> hisboxList = this.hisboxBiz.getByProperty(proId, startTime, endTime, interval, timeValue);
		
		request.setAttribute("hisboxList", hisboxList);
		//��ȡͳ����Ϣ
		if(hisboxList!=null && hisboxList.size()>0){
			Object[] result = this.hisboxBiz.getCalcValue(hisboxList);
			
			request.setAttribute("min", result[0]);
			request.setAttribute("max", result[1]); 
			request.setAttribute("avg", result[2]);
			
			
		}
		return mapping.findForward("hisbox2") ;
	}
	
	/**
	 * ��ת��С������ʷ������ʾҳ��
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
		return mapping.findForward("boxCurve");
	}
	
	
	
	
	
	
	
	
}
