package org.tbcc.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.tbcc.biz.BranchUserAlarmLogsBiz;
import org.tbcc.entity.TbccBaseUser;
import org.tbcc.entity.TbccBranchUserAlarmLogs;
import org.tbcc.util.BaseAction;

/**
 * ��֧�û���¼������־action
 * @author zhaoyou
 *
 */
public class BranchUserAlarmLogsAction extends BaseAction {
	
	private BranchUserAlarmLogsBiz branchUserAlarmLogsBiz = null ;
	
	

	
	public BranchUserAlarmLogsBiz getBranchUserAlarmLogsBiz() {
		return branchUserAlarmLogsBiz;
	}

	public void setBranchUserAlarmLogsBiz(
			BranchUserAlarmLogsBiz branchUserAlarmLogsBiz) {
		this.branchUserAlarmLogsBiz = branchUserAlarmLogsBiz;
	}

	/**
	 *  ��ת����־�б�ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toLogsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String userName = request.getParameter("userName") ;
		
		String clientName = ((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getClient().getClientName() ;
		
		Integer allCount = 0 ;			//������	
		Integer allNumber = 0 ;			//��ҳ��
		Integer pageNumber = 15 ;		//ÿҳ����
		
		List<TbccBranchUserAlarmLogs > list = null ;
		
		allCount  =  this.getBranchUserAlarmLogsBiz().getAllLogsNumber(clientName, userName); 
		
		//��ȡ��¼
		if(allCount>0){
			list = this.getBranchUserAlarmLogsBiz().getLogs(clientName, userName, 1, 15) ;
		}
		//��ȡ��ҳ��
		allNumber = allCount%pageNumber==0?allCount/pageNumber:(allCount/pageNumber+1) ;
		
		
		//������ҳ��������������ǰ����
		request.setAttribute("allCount", allCount) ;
		request.setAttribute("index", 1) ;
		request.setAttribute("allNumber", allNumber	) ;
		
		request.setAttribute("logsList", list) ;
		
		return mapping.findForward("logslist");
	}
	
	/**
	 * ��ҳ��־�б�ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pageLogsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		String userName = request.getParameter("userName") ;
		
		String clientName = ((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getClient().getClientName() ;
		
		String allCount = request.getParameter("allCount") ;
		
		String index = request.getParameter("index") ;
		
		String allNumber = request.getParameter("allNumber") ;
		
		List<TbccBranchUserAlarmLogs > list = null ;
		
		list = this.getBranchUserAlarmLogsBiz().getLogs(clientName, userName, new Integer(index), 15) ;
		
		
		//������ҳ��������������ǰ����
		request.setAttribute("allCount", allCount) ;
		request.setAttribute("index", index) ;
		request.setAttribute("allNumber",allNumber	) ;
		
		request.setAttribute("logsList", list) ;
		
		
		return mapping.findForward("logslist") ;
	}

	
	
}
