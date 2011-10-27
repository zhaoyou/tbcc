package org.tbcc.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.tbcc.biz.HisCarAlarmBiz;
import org.tbcc.biz.HisStartUpBiz;
import org.tbcc.biz.ProjectBiz;
import org.tbcc.entity.TbccBaseHisCarAlarm;
import org.tbcc.entity.TbccBaseHisStartUp;
import org.tbcc.entity.TbccBaseUser;
import org.tbcc.entity.TbccPrjType;
import org.tbcc.util.BaseAction;

public class HisCarAlarmAction extends BaseAction {
	private Logger logger = Logger.getLogger(HisCarAction.class);
	private ProjectBiz proBiz = null;
	private HisStartUpBiz startupBiz = null;
	
	private HisCarAlarmBiz hiscaralarmbiz = null;
	
	public HisCarAlarmBiz getHiscaralarmbiz() {
		return hiscaralarmbiz;
	}

	public void setHiscaralarmbiz(HisCarAlarmBiz hiscaralarmbiz) {
		this.hiscaralarmbiz = hiscaralarmbiz;
	}

	public ProjectBiz getProBiz() {
		return proBiz;
	}

	public HisStartUpBiz getStartupBiz() {
		return startupBiz;
	}

	public void setProBiz(ProjectBiz proBiz) {
		this.proBiz = proBiz;
	}

	public void setStartupBiz(HisStartUpBiz startupBiz) {
		this.startupBiz = startupBiz;
	}

	public ActionForward toHisCarAlarm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String branchId = request.getParameter("branchId");
		String proId = request.getParameter("proId");
		String sid = request.getParameter("sid") ;
		
		//��֤���ݵķ�֧��ʾ�Ƿ���ȷ
		if(branchId==null || branchId.equals("")){
			request.setAttribute("errorMsg", "�������Ϊ�ջ�Ƿ�!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toHisCarAlarm)�����˷Ƿ��Ĳ����б�");
			throw new Exception("�������Դ��Ч!");
		}
		
		//��֤���ݵĹ��̱�ʾ����ͣ��¼��ʾ�Ƿ���ȷ
		TbccPrjType project = this.proBiz.getByProId(proId);		//��ȡҪ��ѯ����Ŀ
		
		TbccBaseHisStartUp startup = this.startupBiz.getStartUp(proId, Long.parseLong(sid));		//��ȡѡ�����ͣ��¼
		
		if(branchId==null || branchId=="" || project==null || startup==null){
			request.setAttribute("errorMsg", "�������Ϊ�ջ�Ƿ�!") ;
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toHisCarAlarm)�����˷Ƿ��Ĳ����б�");
			throw new Exception("�������Դ��Ч!");
		}
		request.setAttribute("project", project);		
		
		request.setAttribute("startup", startup);		
		
		return mapping.findForward("hiscaralarm") ;
	}

	public ActionForward doHisCarAlarm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String startTime = request.getParameter("time1");
		String endTime = request.getParameter("time2");
		String proId  = request.getParameter("proId");
		String sid = request.getParameter("sid") ;	
		
		Long sidvalue_long = null ;
		
		if(startTime==null || endTime==null || proId==null || sid==null ||
			startTime=="" || endTime=="" || proId=="" ||  sid.equals("")){
			request.setAttribute("msg", "��ȷ����Ϣ������!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (doHisCarByStart)��ȡ��ʷ���ݣ������˷Ƿ��Ĳ����б�!");
			return mapping.findForward("hcarlist2") ;
		}
		
		try {
			sidvalue_long = Long.parseLong(sid) ;
		} catch (Exception e) {
			request.setAttribute("msg", "�����˷Ƿ�����!") ;
			throw e ;
		}
		
		//��ȡ������ʷ����
		//List<TbccBaseHisCar> carList =hiscaralarmbiz.insertAndGetHisCarAlarm(proId, startTime, endTime, timevalue_long);
		//��ѯ��ͬʱ�жϴ�����ʷ���ݣ���ȡ��ʷ�������ݲ������
		List<TbccBaseHisCarAlarm> caralarmList =hiscaralarmbiz.insertAndGetHisCarAlarm(proId, startTime, endTime, sidvalue_long);
		if(caralarmList!=null && caralarmList.size()>0){
			request.setAttribute("hiscaralarmList", caralarmList);  //�������ݼ�
		}
		return mapping.findForward("hiscaralarm");
	}

	public ActionForward doSaveCarHisAlarm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String startTime = request.getParameter("time1");
		String endTime = request.getParameter("time2");
		String proId  = request.getParameter("proId");
		String sid = request.getParameter("sid") ;	
		
		String saveStr = request.getParameter("saveStr");
		
		Long sidvalue_long = null ;
		
		if(startTime==null || endTime==null || proId==null || sid==null || saveStr==null||
			startTime=="" || endTime=="" || proId=="" ||  sid.equals("") || saveStr.equals("")){
			request.setAttribute("msg", "��ȷ����Ϣ������!");
			logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (doHisCarByStart)��ȡ��ʷ���ݣ������˷Ƿ��Ĳ����б�!");
			return mapping.findForward("hcarlist2") ;
		}
		
		try {
			sidvalue_long = Long.parseLong(sid) ;
		} catch (Exception e) {
			request.setAttribute("msg", "�����˷Ƿ�����!") ;
			throw e ;
		}
		
		//���³�����ʷ����������Ϣ
		if(hiscaralarmbiz.updateHisCarAlarm(saveStr)){
			List<TbccBaseHisCarAlarm> caralarmList =hiscaralarmbiz.getHisCarAlarm(proId, sidvalue_long);
			if(caralarmList!=null && caralarmList.size()>0){
				request.setAttribute("hiscaralarmList", caralarmList);  //�������ݼ�
			}
		}else{
			System.out.println("���³�����ʷ����������Ϣʧ��");
			request.setAttribute("msg", "���³�����ʷ����������Ϣʧ��");
		}
		
		return mapping.findForward("hiscaralarm");
	}
	
}
