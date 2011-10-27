package org.tbcc.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.tbcc.biz.HisCarBiz;
import org.tbcc.biz.HisStartUpBiz;
import org.tbcc.biz.ProjectBiz;
import org.tbcc.entity.TbccBaseHisCar;
import org.tbcc.entity.TbccBaseHisStartUp;
import org.tbcc.entity.TbccBaseUser;
import org.tbcc.entity.TbccPrjType;
import org.tbcc.util.BaseAction;

import sun.nio.cs.HistoricallyNamedCharset;

/**
 * ���action ���� hiscar.do ������ extends baseAction
 * @author Administrator
 *
 */
public class HisCarAction extends BaseAction {

	private HisCarBiz hiscarBiz = null ;
	
	private ProjectBiz proBiz = null ;
	
	private HisStartUpBiz startupBiz = null ;
	
	private Logger logger = Logger.getLogger(HisCarAction.class);
	
	public void setProBiz(ProjectBiz proBiz) {
		this.proBiz = proBiz;
	}

	public void setStartupBiz(HisStartUpBiz startupBiz) {
		this.startupBiz = startupBiz;
	}

	public void setHiscarBiz(HisCarBiz hiscarBiz) {
		this.hiscarBiz = hiscarBiz;
	}

	/**
	 * ����������ѯ�ƶ����ص���ʷ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doHisCarData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String startTime = request.getParameter("time1");
		String endTime = request.getParameter("time2");
		String proId  = request.getParameter("proId");
		String timeType = request.getParameter("timeType");
		String timeValue = request.getParameter("timevalue");
		if(startTime==null || endTime==null || proId==null || timeType==null || timeValue==null
				||startTime=="" || endTime=="" || proId=="" || timeType=="" || timeValue==""){
			request.setAttribute("msg", "��ȷ����Ϣ������!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (doHisCarData)��ȡ��ʷ���ݡ������˷Ƿ��Ĳ���!");
			return mapping.findForward("hcarlist") ;
		}
		
		//��ȡ��ʷ����
		List<TbccBaseHisCar> carList = hiscarBiz.getHisCarByProperty(proId, startTime, endTime, timeType,timeValue);
		
		if(carList!=null && carList.size()>0){
		//��ȡͳ����Ϣ
		
		Object[] calc = hiscarBiz.getCalcValue(carList);
		
		request.setAttribute("hiscarList", carList);  //�������ݼ�
		
		request.setAttribute("min", calc[0]); 		  //������Сֵ
		
		request.setAttribute("max", calc[1]) ;		//�������ֵ
		
		request.setAttribute("avg", calc[2]);		//����ƽ��ֵ
		
		}
		return mapping.findForward("hcarlist");
		
	}
	
	/**
	 * �����ƶ�������ʷ���ݲ�ѯҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toHisCarData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//��ȡ����Id
		String branchId = request.getParameter("branchId");
		
		
		
		if(request.getSession().getAttribute("carprjList")==null){	
			//��ȡ�û����µ������ƶ�������Ŀ
			List<TbccPrjType> carlist = hiscarBiz.getCarPrjList(new Long(branchId));
		
			// ����ط��Ұ����е��ƶ���Ŀ����session���в�Ҫÿ�β�ѯ��Ҫȥ�����ݿ⣬�Ժ�����ܿ��ǣ�Ҫ��һ��
			if(carlist!=null && carlist.size()>0)
				request.getSession().setAttribute("carprjList", carlist);  		
		}
		
		request.setAttribute("falg", "1") ;//���û��ʲô���ã���Ҫ���ƽ�����ʾ��
		
		return mapping.findForward("hcarlist");
	}
	
	
	
	//************************************��������������ԭ����ʵ�ַ�ʽ***************************************
	
	
	
	/**
	 * �����ƶ�������ʷ��ͣ��¼��ѯҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toHisCarStartUp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//��ȡ����Id
		String branchId = request.getParameter("branchId");
		
		
		if(branchId==null || branchId==""){
			request.setAttribute("errorMsg", "�������Ϊ�ջ�Ƿ�!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toHisCarStartUp)�����˷Ƿ��Ĳ����б�");
			throw new Exception("�������Դ��Ч!");
		}
		/**
		 * ��עһ�¡�������չʵ��ʵʱ���ݵ�ͼ��ʾ��ʱ��Ҳ��Ҫ��ȡ���е��ƶ����ع��̡�����Ҳ�ǹ���һ��carprjList
		 * �����������ж�һ�¡�session���Ƿ�����˳��ؼ��ϡ�������ڡ�����Ҫ��ѯ��
		 * Ϊ�˽�������ѡ�session���⣬����ÿ�ν���ҳ�涼��Ҫ���²�ѯ���ع���
		 */
		
		
		//if(request.getSession().getAttribute("carprjList")==null){	
			//��ȡ�û����µ������ƶ�������Ŀ
			List<TbccPrjType> carlist = hiscarBiz.getCarPrjList(new Long(branchId));
		
		
			// ����ط��Ұ����е��ƶ���Ŀ����session���в�Ҫÿ�β�ѯ��Ҫȥ�����ݿ⣬�Ժ�����ܿ��ǣ�Ҫ��һ��
				if(carlist!=null && carlist.size()>0)
					request.getSession().setAttribute("carprjList", carlist);  		
		//}
				
		return mapping.findForward("carstartup");
	}
	
	/**
	 * ��ת���ƶ�������ʷ���ݲ�ѯҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toHisCarByStart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String branchId = request.getParameter("branchId");
		String proId = request.getParameter("proId");
		String sid = request.getParameter("sid") ;
		
		//��֤���ݵķ�֧��ʾ�Ƿ���ȷ
		if(branchId==null || branchId.equals("")){
			request.setAttribute("errorMsg", "�������Ϊ�ջ�Ƿ�!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toHisCarByStart)�����˷Ƿ��Ĳ����б�");
			throw new Exception("�������Դ��Ч!");
		}
		
		
		//��֤���ݵĹ��̱�ʾ����ͣ��¼��ʾ�Ƿ���ȷ
		TbccPrjType project = this.proBiz.getByProId(proId);		//��ȡҪ��ѯ����Ŀ
		
		TbccBaseHisStartUp startup = this.startupBiz.getStartUp(proId, Long.parseLong(sid));		//��ȡѡ�����ͣ��¼
		
		if(branchId==null || branchId=="" || project==null || startup==null){
			request.setAttribute("errorMsg", "�������Ϊ�ջ�Ƿ�!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toHisCarByStart)�����˷Ƿ��Ĳ����б�");
			throw new Exception("�������Դ��Ч!");
		}
			
		
		request.setAttribute("project", project);		
		
		request.setAttribute("startup", startup);		
		
		
		
		request.setAttribute("flag", 1);			//����������ҳ����ʾ��
		
		return mapping.findForward("hiscar") ;
	}
	
	
	/**
	 * ������ͣ��¼��ȡ�ƶ����ص���ʷ���� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doHisCarByStart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String startTime = request.getParameter("time1");
		String endTime = request.getParameter("time2");
		String proId  = request.getParameter("proId");
		String sid = request.getParameter("sid") ;	
		String interval = request.getParameter("interval");
		String timeValue = request.getParameter("timevalue");
		
		Integer sid_int = null ;
		Long timevalue_long = null ;
		
		if(startTime==null || endTime==null || proId==null || interval==null || timeValue==null || sid==null ||
			startTime=="" || endTime=="" || proId=="" || interval=="" || timeValue=="" || sid.equals("")){
			request.setAttribute("msg", "��ȷ����Ϣ������!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (doHisCarByStart)��ȡ��ʷ���ݣ������˷Ƿ��Ĳ����б�!");
			return mapping.findForward("hcarlist2") ;
		}
		
		try {
			sid_int = Integer.parseInt(timeValue) ;
			timevalue_long = Long.parseLong(sid) ;
		} catch (Exception e) {
			request.setAttribute("msg", "�����˷Ƿ�����!") ;
			throw e ;
		}
		
		//��ȡ��ʷ����
		List<TbccBaseHisCar> carList =hiscarBiz.getHisCarBySidAndTime(proId, startTime, endTime, interval,sid_int,timevalue_long);
			//hiscarBiz.getHisCarByProperty(proId, startTime, endTime, interval,timeValue);
		
		if(carList!=null && carList.size()>0){
		//��ȡͳ����Ϣ
		
		Object[] calc = hiscarBiz.getCalcValue(carList);
		
		request.setAttribute("hiscarList", carList);  //�������ݼ�
		
		request.setAttribute("min", calc[0]); 		  //������Сֵ
		
		request.setAttribute("max", calc[1]) ;		//�������ֵ
		
		request.setAttribute("avg", calc[2]);		//����ƽ��ֵ
		
		}
		return mapping.findForward("hiscar") ;
	}
	
	/**
	 * ��ת���ƶ�������ʷ������ʾҳ��
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
		
		String branchId = request.getParameter("branchId") ;
		String sid = request.getParameter("sid") ;
		String t1 = request.getParameter("t1") ;
		String t2 = request.getParameter("t2") ;
		String proId = request.getParameter("proId") ;
		
		if(branchId==null || branchId.equals("") || sid==null || sid.equals("")||t1==null || t1.equals("")||
				t2==null || t2.equals("")|| proId==null || proId.equals("")){
			request.setAttribute("errorMsg", "�������Ϊ�ջ�Ƿ�!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toCurve)�����˷Ƿ��Ĳ����б�");
			throw new Exception("�������Դ��Ч!");
		}	
		return mapping.findForward("carCurve");
	}
	

	
	
	/**
	 * ��ת�ĳ�����ʷ���ݣ���ͼ��ʾҳ��,֧�ֻ��Ʋ�ͬ���ʵ���ʾ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toHisMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String branchId = request.getParameter("branchId");
		String proId = request.getParameter("proId") ;
		String sid = request.getParameter("sid") ;
		
		//��֤��֧����
		if(branchId==null || branchId.equals("")){
			request.setAttribute("errorMsg", "�������Ϊ�ջ�Ƿ�!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toHisMap)�����˷Ƿ��Ĺ��̱�ʾ����ͣ��ʾ");
			throw new Exception("�����Ƿ�...");
		}
		
		//��֤���̱�ʾ����
		if(proId==null || proId.equals("") || sid==null || sid.equals("")){
			request.setAttribute("errorMsg", "�������Ϊ�ջ�Ƿ�!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toHisMap)�����˷Ƿ��Ĺ��̱�ʾ����ͣ��ʾ");
			throw new Exception("�����Ƿ�...");
		}
		
		TbccPrjType project = this.proBiz.getByProId(proId);		//��ȡҪ��ѯ����Ŀ
		
		TbccBaseHisStartUp startup = this.startupBiz.getStartUp(proId, Long.parseLong(sid));		//��ȡѡ�����ͣ��¼

		request.setAttribute("project", project) ;
		
		request.setAttribute("startup", startup) ;
		
		return mapping.findForward("hismap2");
	}
	
	/**
	 * ��ת��������ʷ����ҳ�棬���������ĵ�ͼ���ߣ�֧����չ�����ߵ�������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toAllHisMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String branchId = request.getParameter("branchId");
		String proId = request.getParameter("proId") ;
		String sid = request.getParameter("sid") ;
		
		
		//��֤��֧����
		if(branchId==null || branchId.equals("")){
			request.setAttribute("errorMsg", "�������Ϊ�ջ�Ƿ�!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toAllHisMap)�����˷Ƿ��Ĺ��̱�ʾ����ͣ��ʾ");
			throw new Exception("�����Ƿ�...");
		}
		
		//��֤�������̱�ʾ����ͣ��ʾId
		if(proId==null || proId.equals("") || sid==null || sid.equals("")){
			request.setAttribute("errorMsg", "����Ϊ�ջ�Ƿ�!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toAllHisMap)�����˷Ƿ��Ĺ��̱�ʾ����ͣ��ʾ");
			throw new Exception("�����Ƿ�...");
		}
		
		TbccPrjType project = this.proBiz.getByProId(proId);		//��ȡҪ��ѯ����Ŀ
		
		TbccBaseHisStartUp startup = this.startupBiz.getStartUp(proId, Long.parseLong(sid));		//��ȡѡ�����ͣ��¼

		request.setAttribute("project", project) ;
		
		request.setAttribute("startup", startup) ;
		
		return mapping.findForward("hisallmap2") ;
	}
	
	
	
	
	
	
}
