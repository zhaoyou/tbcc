package org.fdap.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.fdap.biz.PhoneConfigBiz;
import org.fdap.entity.Fdapphone;
import org.fdap.util.BaseAction;

public class PhoneConfigAction extends BaseAction {
	private Logger logger = Logger.getLogger(RefConfigAction.class) ;
	
	private PhoneConfigBiz phoneconfigBiz;
	
	public PhoneConfigBiz getPhoneconfigBiz() {
		return phoneconfigBiz;
	}

	public void setPhoneconfigBiz(PhoneConfigBiz phoneconfigBiz) {
		this.phoneconfigBiz = phoneconfigBiz;
	}
	
	public ActionForward toPhone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Fdapphone phoneinfo = phoneconfigBiz.getPhone();
		request.setAttribute("phoneinfo", phoneinfo);
		return mapping.findForward("phonePage");
	}
	
	
	public ActionForward doSavePhone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String phoneid = request.getParameter("phoneid");
		String phonenumber = request.getParameter("phonenumber");
		String messagenumber = request.getParameter("messagenumber");
		
		String[] phoneCh = request.getParameterValues("phonenumberCh");
		String[] messageCh = request.getParameterValues("messagenumberCh");
//		System.out.println((phoneCh!=null&&phoneCh.length!=0?phoneCh[0]:"")+" "+messageCh);
		if(phoneid==null||phoneid.equals("")||phonenumber==null||phonenumber.equals("")||
				messagenumber==null||messagenumber.equals("")){
			logger.warn("param is null") ;
			return new ActionForward("/phoneconfig.do?ope=toPhone");
		}
		Fdapphone phoneinfo = phoneconfigBiz.getPhone();
		if(!phoneinfo.getPhoneid().toString().equals(phoneid)){
			logger.warn("系统有误，修改的电话ID与查询出的不同") ;
			return new ActionForward("/phoneconfig.do?ope=toPhone");
		}
		
		phoneinfo.setPhonenumber(phonenumber);
		phoneinfo.setMessagenumber(messagenumber);
		//获取电话激活标识和短信激活标识
		Integer phoneActive = (phoneCh!=null&&phoneCh.length!=0?Integer.parseInt(phoneCh[0]):0);
		Integer messageActive = (messageCh!=null&&messageCh.length!=0?Integer.parseInt(messageCh[0]):0);
		phoneinfo.setPhoneActive(phoneActive);
		phoneinfo.setMessageActive(messageActive);
		
		if(phoneconfigBiz.savePhone(phoneinfo)){
			request.setAttribute("tip", "修改成功");
		}else{
			request.setAttribute("tip", "修改失败");
		}
		return new ActionForward("/phoneconfig.do?ope=toPhone");
	}
}
