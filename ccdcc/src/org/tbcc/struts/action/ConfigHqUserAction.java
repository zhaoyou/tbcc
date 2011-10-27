package org.tbcc.struts.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.tbcc.biz.UserBiz;
import org.tbcc.entity.TbccBaseUser;
import org.tbcc.entity.TbccClient;
import org.tbcc.entity.TbccHqRoles;
import org.tbcc.entity.TbccHqUser;
import org.tbcc.util.BaseAction;

public class ConfigHqUserAction extends BaseAction {
	private UserBiz userBiz;
	
	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}

		//��ת���ܲ��û�ҳ��
	public ActionForward forwordConfigHqUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TbccBaseUser user = (TbccBaseUser) request.getSession().getAttribute("LoginUser");
		
		List<TbccHqUser> list= userBiz.getHqUserbyClientId(user.getClient().getId());
		request.setAttribute("userList", list);
		return mapping.findForward("listHqUser2");
	}
	
	
	//��ת�������ܲ��û�ҳ��
	public ActionForward forwordAddHqUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TbccBaseUser user = (TbccBaseUser) request.getSession().getAttribute("LoginUser");
		List<TbccHqRoles> rolesList = userBiz.getHqRolesByHqId(user.getClient().getTransactionId());
		request.setAttribute("rolesList", rolesList);
		return mapping.findForward("addHqUser2");
	}
	
	//�����ܲ��û�
	@SuppressWarnings("unchecked")
	public ActionForward addHqUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String clientId = request.getParameter("clientId"); 
		String uenable = request.getParameter("uEnable");
		String uname = request.getParameter("uName");
		String upassword = request.getParameter("uPassword");
		String hqRoleId = request.getParameter("hqRoleId");
		
		if(userValidate(clientId, uname, upassword, uenable, hqRoleId)){
			TbccClient client = new TbccClient();
			client.setId(Long.valueOf(clientId));
			
			TbccHqRoles hqRoles = new TbccHqRoles();
			hqRoles.setId(Long.valueOf(hqRoleId));
			String ucreated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			
			TbccHqUser user = new TbccHqUser();
			user.setClient(client);
			user.setHqRoles(hqRoles);
			user.setUcreated(ucreated);
			user.setUenable(Integer.valueOf(uenable));
			user.setUname(uname);
			user.setUpassword(upassword);
			
			
			TbccHqUser existUser = userBiz.getHqUserByClientIdAndUserName(Long.valueOf(clientId), uname);
			
			TbccBaseUser temp = (TbccBaseUser) request.getSession().getAttribute("LoginUser");
			List<TbccHqRoles> rolesList = userBiz.getHqRolesByHqId(temp.getClient().getTransactionId());
			request.setAttribute("rolesList", rolesList);
			if(existUser!=null){
				request.setAttribute("tip", "�û���������������ѡ��һ���µ��û�����");
			}else{
				if(userBiz.addHqUser(user)){
					request.setAttribute("tip", "����ɹ���");
				}else{
					request.setAttribute("tip", "����ʧ�ܣ�");
				}
			}
		}
		return mapping.findForward("addHqUser2");
	}
	
	//ɾ���ܲ��û�
	public ActionForward delHqUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String delString = request.getParameter("delString");
		String[] delId = delString.split("-");
		Long[] id = new Long[delId.length];
		for(int i=0; i<delId.length; i++){
			id[i] = Long.valueOf(delId[i]);
		}
		if(userBiz.delHqUser(id)){
			return new ActionForward("/configHqUser.do?ope=forwordConfigHqUser");
		}else{
			request.setAttribute("tip","ɾ��ʧ�ܣ�");
			return new ActionForward("/configHqUser.do?ope=forwordConfigHqUser");
		}
	}
	
	//��ת�������ܲ��û�ҳ��
	public ActionForward forwordUpHqUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TbccBaseUser temp = (TbccBaseUser) request.getSession().getAttribute("LoginUser");
		List<TbccHqRoles> rolesList = userBiz.getHqRolesByHqId(temp.getClient().getTransactionId());
		request.setAttribute("rolesList", rolesList);
		
		String upId = request.getParameter("upId");
		TbccHqUser upUser = userBiz.getHqUserById(Long.valueOf(upId));
		request.setAttribute("upUser", upUser);
		
		upUser = null;
		return mapping.findForward("upHqUser2");
	}
	
	//�����ܲ��û�
	@SuppressWarnings("unchecked")
	public ActionForward upHqUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		String clientId = request.getParameter("clientId"); 
		String uenable = request.getParameter("uEnable");
		String uname = request.getParameter("uName");
		String upassword = request.getParameter("uPassword");
		String hqRoleId = request.getParameter("hqRoleId");
		
		boolean pass = false;
		if(id != "" && id != null){
			pass = userValidate(clientId, uname, upassword, uenable, hqRoleId);
		}
		if(pass){
			TbccClient client = new TbccClient();
			client.setId(Long.valueOf(clientId));
			
			TbccHqRoles hqRoles = userBiz.getHqRoleById(Long.valueOf(hqRoleId)); 
			String ucreated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			
			TbccHqUser user = new TbccHqUser();
			user.setId(Long.valueOf(id));
			user.setClient(client);
			user.setHqRoles(hqRoles);
			user.setUcreated(ucreated);
			user.setUenable(Integer.valueOf(uenable));
			user.setUname(uname);
			user.setUpassword(upassword);
			
			TbccHqUser temp = userBiz.getHqUserByClientIdAndUserName(Long.valueOf(clientId), uname);
			
			TbccBaseUser tuser = (TbccBaseUser) request.getSession().getAttribute("LoginUser");
			List<TbccHqRoles> rolesList = userBiz.getHqRolesByHqId(tuser.getClient().getTransactionId());
			request.setAttribute("rolesList", rolesList);
			
			
			if(temp==null || String.valueOf(temp.getId()).equals(id)){
				if(userBiz.upHqUser(user)){
					request.setAttribute("upUser", user);
					request.setAttribute("tip", "�޸ĳɹ���");
				}else{
					request.setAttribute("upUser", userBiz.getHqUserById(Long.valueOf(id)));
					request.setAttribute("tip", "�޸�ʧ�ܣ�");
				}
			}else{
				request.setAttribute("upUser", userBiz.getHqUserById(Long.valueOf(id)));
				request.setAttribute("tip", "�޸ĺ���û���������������ѡ��һ���µ��û�����");
			}
		}
		return mapping.findForward("upHqUser2");
	}
	
	//��֤�û���Ϣ 
private boolean userValidate(String clientId,String uname,String upassword,String uenable,String hqRoleId){
		
		if (clientId == null || clientId.equals(""))
		{
			return false;
		}
		if(uname == null || uname.equals(""))
		{
			return false;
		}
		else if (!Pattern.matches("\\w{4,25}", uname))
		{
			return false;
		}
		if (upassword == null || upassword.equals(""))
		{
			return false;
		}
		else if (!Pattern.matches("\\w{4,25}",upassword))
		{
			return false;
		}
		if(uenable == null || uenable.equals(""))
		{
			return false;
		}
		if(hqRoleId == null || hqRoleId.equals(""))
		{
			return false;
		}
		return true;
	}
}

