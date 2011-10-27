package org.tbcc.struts.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.tbcc.biz.ClientBiz;
import org.tbcc.biz.UserBiz;
import org.tbcc.entity.TbccBaseUser;
import org.tbcc.entity.TbccBranchType;
import org.tbcc.entity.TbccClient;
import org.tbcc.entity.TbccDataRoles;
import org.tbcc.entity.TbccTransactionRoles;
import org.tbcc.entity.TbccUser;
import org.tbcc.util.AdminMod;
import org.tbcc.util.BaseAction;

public class ConfigUserAction extends BaseAction {
	private UserBiz userBiz;
	private ClientBiz clientBiz;
	
	public void setClientBiz(ClientBiz clientBiz) {
		this.clientBiz = clientBiz;
	}

	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}

	public ActionForward forwordConfigUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TbccBaseUser user = (TbccBaseUser) request.getSession().getAttribute("LoginUser");
		List<TbccClient> clientList = clientBiz.getByName(user.getClient().getClientName());
		TbccClient client = null;
		if(clientList!=null && clientList.size()>0){
			 client = clientList.get(0);
		}
		
		List<TbccUser> list=new ArrayList<TbccUser>(client.getUsers());
		request.setAttribute("userList", list);
		
		//return mapping.findForward("listUser");
		//����ҳ�����ת��ҳ��
		return mapping.findForward("listUser2");
		
	}
	
	//��ת��ϵͳadminҳ�棬��ҳ�治�ṩ��
	public ActionForward forwordSysUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		List<TbccUser> sysAdminList = userBiz.getSysAdmin();
//		List<TbccBranchType> branchList = new ArrayList<TbccBranchType>();
//		for(int i=0; i<sysAdminList.size(); i++){ 
//			branchList.add(userBiz.getBranchById(sysAdminList.get(i).getTransactionId()));
//		}
//		List<AdminMod> adminList = new ArrayList<AdminMod>();
//		
//		for(int j=0; j<sysAdminList.size(); j++){
//			AdminMod adminMod = new AdminMod();
//			adminMod.setBranchType(branchList.get(j));
//			adminMod.setUser(sysAdminList.get(j));
//			adminList.add(adminMod);
//		}
//		
//		request.setAttribute("adminList", adminList);
		return mapping.findForward("listAdmin");
	}
	
	//��ת�������û�ҳ��
	public ActionForward forwordAddUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TbccBaseUser user = (TbccBaseUser) request.getSession().getAttribute("LoginUser");
		List<TbccDataRoles> rolesList = userBiz.getDataRolesByBranchId(user.getClient().getTransactionId());
		request.setAttribute("rolesList", rolesList);
		
		
		return mapping.findForward("addUser2");
	}
	
	
	//�����û�ҳ��
	@SuppressWarnings("unchecked")
	public ActionForward addUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String clientId = request.getParameter("clientId");
		String uenable = request.getParameter("uEnable");
		String uname = request.getParameter("uName");
		String upassword = request.getParameter("uPassword");
		String dataRoleId = request.getParameter("dataRoleId");
		
		if(userValidate(clientId, uname, upassword, uenable, dataRoleId)){
			TbccClient client = new TbccClient();
			client.setId(Long.valueOf(clientId));
			
			TbccDataRoles dataRoles = new TbccDataRoles();
			dataRoles.setRoleId(Integer.valueOf(dataRoleId));
			String ucreated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			
			
			
			TbccUser user = new TbccUser();
			user.setClient(client);
			user.setDataRoles(dataRoles);
			user.setUcreated(ucreated);
			user.setUenable(Integer.valueOf(uenable));
			user.setUname(uname);
			user.setUpassword(upassword);
			
			String clientName = ((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getClient().getClientName();
			TbccClient logClient = clientBiz.getByName(clientName).get(0);
			Set set = logClient.getUsers();
			
			TbccUser temp = null;
			boolean flag = false;
			for(Iterator it=set.iterator();it.hasNext();){
				temp = (TbccUser) it.next();
				if(temp.getUname().equals(uname)){
					flag=true;
					break;
				}
			}
			
			TbccBaseUser rtemp = (TbccBaseUser) request.getSession().getAttribute("LoginUser");
			List<TbccDataRoles> rolesList = userBiz.getDataRolesByBranchId(rtemp.getClient().getTransactionId());
			request.setAttribute("rolesList", rolesList);
			if(flag){
				request.setAttribute("tip", "�û���������������ѡ��һ���µ��û�����");
			}else{
				if(userBiz.addUser(user)){
					request.setAttribute("tip", "����ɹ���");
				}else{
					request.setAttribute("tip", "����ʧ�ܣ�");
				}
			}
		}
		
		return mapping.findForward("addUser2");
	}
	
	//ɾ��һ���û�
	public ActionForward delUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String delString = request.getParameter("delString");
		String[] delId = delString.split("-");
		Long[] id = new Long[delId.length];
		for(int i=0; i<delId.length; i++){
			id[i] = Long.valueOf(delId[i]);
		}
		if(userBiz.delUser(id)){
			return new ActionForward("/configUser.do?ope=forwordConfigUser");
		}else{
			request.setAttribute("tip","ɾ��ʧ�ܣ�");
			return new ActionForward("/configUser.do?ope=forwordConfigUser");
		}
	}
	
	//��ת�������û�ҳ��
	public ActionForward forwordUpUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TbccBaseUser user = (TbccBaseUser) request.getSession().getAttribute("LoginUser");
		List<TbccDataRoles> rolesList = userBiz.getDataRolesByBranchId(user.getClient().getTransactionId());
		request.setAttribute("rolesList", rolesList);
		
		String upId = request.getParameter("upId");
		TbccUser upUser = userBiz.getById(Long.valueOf(upId));
		request.setAttribute("upUser", upUser);
		
		upUser = null;
		return mapping.findForward("upUser2");
	}
	
	//�����û�
	@SuppressWarnings("unchecked")
	public ActionForward upUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		String clientId = request.getParameter("clientId"); 
		String uenable = request.getParameter("uEnable");
		String uname = request.getParameter("uName");
		String upassword = request.getParameter("uPassword");
		String dataRoleId = request.getParameter("dataRoleId");
		
		boolean pass = false;
		if(id != "" && id != null){
			pass = userValidate(clientId, uname, upassword, uenable, dataRoleId);
		}
		if(pass){
			TbccClient client = new TbccClient();
			client.setId(Long.valueOf(clientId));
			
			TbccDataRoles dataRoles = userBiz.getDataRolesById(Integer.valueOf(dataRoleId)); 
			String ucreated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			
			TbccUser user = new TbccUser();
			user.setId(Long.valueOf(id));
			user.setClient(client);
			user.setDataRoles(dataRoles);
			user.setUcreated(ucreated);
			user.setUenable(Integer.valueOf(uenable));
			user.setUname(uname);
			user.setUpassword(upassword);
			
			Long cId = ((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getClient().getId();
			List<TbccUser> userList = userBiz.getUserByClientId(cId);
			TbccUser temp = null;
			for(int i=0; i<userList.size(); i++){
				if(userList.get(i).getUname().equals(uname)){
					temp = userList.get(i);
				}
			}
			TbccBaseUser tuser = (TbccBaseUser) request.getSession().getAttribute("LoginUser");
			List<TbccDataRoles> rolesList = userBiz.getDataRolesByBranchId(tuser.getClient().getTransactionId());
			request.setAttribute("rolesList", rolesList);
			
			request.setAttribute("upUser", user);
			if(temp==null || String.valueOf(temp.getId()).equals(id)){
				if(userBiz.upUser(user)){
					//TbccDataRoles dataRole = userBiz.getDataRolesById(Integer.valueOf(dataRoleId));
					//if(dataRole!=null && (dataRole.getRoleName().equals("����Ա")||dataRole.getRoleName().equals("����Ա"))){
					//	TbccUser upUser = new TbccUser();
					//	upUser.setDataRoles(dataRole);
					//	request.setAttribute("upUser", user);
					//}
					request.setAttribute("upUser", user);
					request.setAttribute("tip", "�޸ĳɹ���");
				}else{
					request.setAttribute("upUser", userBiz.getById(Long.valueOf(id)));
					request.setAttribute("tip", "�޸�ʧ�ܣ�");
				}
			}else{
				request.setAttribute("upUser", userBiz.getById(Long.valueOf(id)));
				request.setAttribute("tip", "�޸ĺ���û���������������ѡ��һ���µ��û�����");
			}
		}
		
		return mapping.findForward("upUser2");
	}
	
	
		//��֤�û���Ϣ
		private boolean userValidate(String clientId,String uname,String upassword,String uenable,String dataRoleId){
			
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
			if(dataRoleId == null || dataRoleId.equals(""))
			{
				return false;
			}
			return true;
		}
}
