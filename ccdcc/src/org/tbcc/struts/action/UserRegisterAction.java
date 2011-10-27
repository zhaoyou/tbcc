package org.tbcc.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.tbcc.biz.UserRegisterBiz;
import org.tbcc.entity.TbccUserRegister;

public class UserRegisterAction extends DispatchAction {

	private UserRegisterBiz userregisterbiz;
	
	public UserRegisterBiz getUserregisterbiz() {
		return userregisterbiz;
	}

	public void setUserregisterbiz(UserRegisterBiz userregisterbiz) {
		this.userregisterbiz = userregisterbiz;
	}
	
	public ActionForward doUserRegister(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String rname = request.getParameter("rname");
		String sex = request.getParameter("sex");
		String position = request.getParameter("position");
		String profession = request.getParameter("profession");
		String companyname = request.getParameter("companyname");
		String companyAddress = request.getParameter("companyAddress");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String fax = request.getParameter("fax");
		String mobilephone = request.getParameter("mobilephone");
		String remark = request.getParameter("remark");
		
		if(rname==null||rname.equals("")||sex==null||sex.equals("")||position==null||position.equals("")||
			profession==null||profession.equals("")||companyname==null||companyname.equals("")||companyAddress==null
			||companyAddress.equals("")||email==null||email.equals("")||phone==null||phone.equals("")||fax==null||
			fax.equals("")||mobilephone==null||mobilephone.equals("")){
			request.setAttribute("tip", "请填写完整");
			return mapping.findForward("userregister");
		}
		TbccUserRegister user = new TbccUserRegister();
		user.setRname(rname);
		user.setSex(Integer.parseInt(sex));
		user.setPosition(position);
		user.setProfession(profession);
		user.setCompanyname(companyname);
		user.setCompanyAddress(companyAddress);
		user.setEmail(email);
		user.setPhone(phone);
		user.setFax(fax);
		user.setMobilephone(mobilephone);
		user.setRemark(remark);
		
		if(!userregisterbiz.getExists(rname,companyname)){
			if(userregisterbiz.addregister(user)){
				request.setAttribute("tip", "注册成功");
				return mapping.findForward("registerSucceed");
			}else{
				request.setAttribute("tip", "注册失败");
				return mapping.findForward("userregister");
			}
		}
		request.setAttribute("tip", companyname+"已注册了该用户名");
		return mapping.findForward("userregister");
	}
}
