package org.fdap.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.fdap.biz.EngineerBiz;
import org.fdap.biz.OrgBiz;
import org.fdap.biz.SysadminBiz;
import org.fdap.biz.UserConfigBiz;
import org.fdap.entity.Fdaporg;
import org.fdap.entity.Fdaprole;
import org.fdap.entity.Fdapuser;
import org.fdap.util.BaseAction;

/**
 * 系统用户配置和用户管理的action
 * @author zhoukuanwei
 *
 */
public class UserConfigAction extends BaseAction {
	
	private EngineerBiz engineerbiz;
	
	private SysadminBiz sysadminbiz;
	
	private UserConfigBiz userconfigbiz;
	private OrgBiz orgbiz;
	
	public UserConfigBiz getUserconfigbiz() {
		return userconfigbiz;
	}

	public void setUserconfigbiz(UserConfigBiz userconfigbiz) {
		this.userconfigbiz = userconfigbiz;
	}

	public OrgBiz getOrgbiz() {
		return orgbiz;
	}

	public void setOrgbiz(OrgBiz orgbiz) {
		this.orgbiz = orgbiz;
	}
	
	public SysadminBiz getSysadminbiz() {
		return sysadminbiz;
	}

	public void setSysadminbiz(SysadminBiz sysadminbiz) {
		this.sysadminbiz = sysadminbiz;
	}
	
	public EngineerBiz getEngineerbiz() {
		return engineerbiz;
	}

	public void setEngineerbiz(EngineerBiz engineerbiz) {
		this.engineerbiz = engineerbiz;
	}

	/**
	 * 跳转到工程人员配置页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toEngineer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获取工程人员信息
		Fdapuser engineer=engineerbiz.getEngineer();
		if(engineer!=null){
			request.setAttribute("engineername", engineer.getName());
			//request.setAttribute("oldpassword", engineer.getPassword());
		}
		else{
			request.setAttribute("msg", "<font color='red'>没有工程人员，请联系开发人员配置工程人员</font>");
		}
		return mapping.findForward("engineerConfig");
	}
	
	/**
	 * 修改工程人员用户信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doEngineer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oldpassword=request.getParameter("oldpassword");
		String password=request.getParameter("password");
		
		Fdapuser engineer=engineerbiz.getEngineer();
		if(engineer==null){
			request.setAttribute("msg", "<font color='red'>没有工程人员，请联系相关人员配置</font>");
			request.setAttribute("engineername", request.getParameter("engineername"));
			return mapping.findForward("engineerConfig");
		}
		if(oldpassword.equals(engineer.getPassword())){
			engineer.setPassword(password);
			//修改工程人员密码
			if(engineerbiz.updateEngineer(engineer)){
				request.setAttribute("msg", "<font color='blue'>修改成功</font>");
			}
			else{
				request.setAttribute("msg", "<font color='red'>修改失败</font>");
			}
		}
		else{
			request.setAttribute("msg", "<font color='red'>原密码输入错误</font>");
		}
		
		request.setAttribute("engineername", request.getParameter("engineername"));
		return mapping.findForward("engineerConfig");
	}
	
	/**
	 * 跳转到系统管理员配置页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toSysadmin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获取系统管理员信息
		Fdapuser sysadminuser=sysadminbiz.getSysadmin();
		if(sysadminuser!=null){
			request.setAttribute("sysadminname", sysadminuser.getName());
//			request.setAttribute("oldpassword", sysadminuser.getPassword());
		}
		else{
			request.setAttribute("msg", "<font color='red'>没有系统管理员，请联系相关人员配置</font>");
		}
		return mapping.findForward("sysAdminConfig");
	}
	
	/**
	 * 修改系统管理员用户信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doSysadmin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oldpassword=request.getParameter("oldpassword");
		String password=request.getParameter("password");
		
		Fdapuser sysadminuser=sysadminbiz.getSysadmin();
		if(sysadminuser==null){
			request.setAttribute("msg", "<font color='red'>没有系统管理员，请联系开发人员配置</font>");
			request.setAttribute("sysadminname", request.getParameter("sysadminname"));
			return mapping.findForward("sysAdminConfig");
		}
		if(oldpassword.equals(sysadminuser.getPassword())){
			sysadminuser.setPassword(password);
			//修改系统管理员密码
			if(sysadminbiz.updateSysadmin(sysadminuser)){
				request.setAttribute("msg", "<font color='blue'>修改成功</font>");
//				request.setAttribute("oldpassword", password);
			}
			else{
				request.setAttribute("msg", "<font color='red'>修改失败</font>");
//				request.setAttribute("oldpassword",oldpassword );
			}
		}
		else{
			request.setAttribute("msg", "<font color='red'>原密码输入错误</font>");
		}
		
		request.setAttribute("sysadminname", request.getParameter("sysadminname"));
		return mapping.findForward("sysAdminConfig");
	}
	
	/**
	 * 跳转到查看用户管理页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toOrgUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获取机构和企业的关系树
		String orgtree=userconfigbiz.getOrgTree();
		if(orgtree==null||orgtree.equals("")){
			orgtree = "alert('没有顶层机构，请确认是否已配置顶层机构');";
		}
		request.setAttribute("tree", orgtree);
		return mapping.findForward("orgUserList");
	}
	
	/**
	 * 跳转到某企业下(企业标识oid)添加用户的页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toOrgUserAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oid=request.getParameter("oid");
		Fdaporg org=null;
		if(oid!=null&&!oid.equals("")){
			//根据企业标识id获取企业信息
			org=orgbiz.getByOid(Long.valueOf(oid));
			request.setAttribute("org", org);
		}else{
			//logger.warn("传递了非法参数");
		}
		request.setAttribute("ope", "doOrgUserAdd");
		request.setAttribute("oid", oid);
		return mapping.findForward("orgUserAdd");
	}
	
	/**
	 * 给某企业(企业标识oid)添加用户信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doOrgUserAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oid=request.getParameter("oid");
		String name=request.getParameter("name");
		String password = request.getParameter("password");
		String rid = request.getParameter("rid");
		
		if(oid==null||oid.equals("")||name==null||name.equals("")||password==null||password.equals("")||rid==null||rid.equals("")){
			request.setAttribute("tip", "<font color='red'>添加失败,请尽快联系系统开发人员</font>");
			request.setAttribute("name", name);
			if(oid!=null&&!oid.equals("")){
				//如果企业标识id获取到了而其他参数可能没获取到，就跳转到添加用户页面
				return new ActionForward("/userconfig.do?ope=toOrgUserAdd&oid="+oid);
			}
			else{
				//如果企业标识id没获取到，则跳转到登陆后的首页
				return new ActionForward("/userconfig.do?ope=toOrgUser");
			}
		}
		
		//根据企业标识id，获取该企业下所有非系统管理员、非工程人员的用户列表。以查询是否存在该用户名
		List<Fdapuser> orguserlist = userconfigbiz.getOrguser(oid);
		if(orguserlist!=null&&orguserlist.size()!=0){
			for(Fdapuser fu : orguserlist){
				if(name.equals(fu.getName())){
					request.setAttribute("tip", "<font color='blue'>该企业(或机构)下已存在这个用户名</font>");
					request.setAttribute("name", name);
					return new ActionForward("/userconfig.do?ope=toOrgUserAdd&oid="+oid);
				}
			}
		}
		
		//根据系统角色标识id，获取系统角色信息
		Fdaprole frole = userconfigbiz.getRoleByRid(rid);
		if(frole==null){
			request.setAttribute("tip", "<font color='red'>新建失败</font>");
			request.setAttribute("name", name);
			return new ActionForward("/userconfig.do?ope=toOrgUserAdd&oid="+oid);
		}
		
		Fdaporg forg = new Fdaporg();
		forg.setOid(Long.valueOf(oid));
		//构建要添加的用户信息对象
		Fdapuser fuser = new Fdapuser();
		fuser.setFdaporg(forg);
		fuser.setFdaprole(frole);
		fuser.setName(name);
		fuser.setPassword(password);
		
		//添加用户信息
		if(!userconfigbiz.addUser(fuser)){
			request.setAttribute("tip", "<font color='red'>新建失败，请联系开发人员</font>");
		}
		
		return new ActionForward("/userconfig.do?ope=backToOrgUser&oid="+oid);
	}
	
	/**
	 * 删除某企业下的某个用户信息或某几个用户信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doOrgUserDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oid=request.getParameter("oid");
		//获取要删除的用户信息的标识id数组
		String delStr = request.getParameter("delStr");
		String delId[] = delStr.split("-");
		/*for(int i=0;i<delId.length;i++){
			System.out.println(delId[i]);
		}*/
		//删除用户信息
		if(!userconfigbiz.delUser(delId)){
			request.setAttribute("tip","<font color='red'>删除失败，请联系开发人员</font>");
		}
		
		return new ActionForward("/userconfig.do?ope=backToOrgUser&oid="+oid);
	}
	
	/**
	 * 跳回用户管理页面(新建、更改、删除用户时用到的，另外这几个功能中的"取消"按钮直接用此方法)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward backToOrgUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oid = request.getParameter("oid");
		//根据企业标识id，获取该企业下所有非系统管理员、非工程人员的用户列表
		List<Fdapuser> userlist = userconfigbiz.getOrguser(oid);
		if(userlist!=null&&userlist.size()!=0){
			request.setAttribute("orguserlist", userlist);
		}
		else{
			request.setAttribute("tip", "<font color='blue'>该企业下暂时还没有用户</font>");
		}
		//获取所有机构和企业的JS关系树
		String orgtree=userconfigbiz.getOrgTree();
		request.setAttribute("tree", orgtree);
		request.setAttribute("open", "d.openTo("+oid+",true);");
		
		request.setAttribute("oid", oid);
		return mapping.findForward("orgUserList");
	}
	
	/**
	 * 跳转到某企业下(企业标识oid)修改用户信息的页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toOrgUserUp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oid=request.getParameter("oid");
		String uid = request.getParameter("uid");
		if(oid==null||oid.equals("")||uid==null||uid.equals("")){
			request.setAttribute("tip", "<font color='red'>跳转失败,请尽快联系系统开发人员</font>");
			if(oid!=null&&!oid.equals("")){
				return new ActionForward("/userconfig.do?ope=backToOrgUser&oid="+oid);
			}
			else{
				return new ActionForward("/userconfig.do?ope=toOrgUser");
			}
		}
		request.setAttribute("uid", uid);
		//根据用户标识id，获取用户信息
		Fdapuser fuser = userconfigbiz.getUserById(uid);
		request.setAttribute("name", fuser.getName());
		request.setAttribute("oldpassword", fuser.getPassword());
		//根据企业标识id，获取企业信息
		Fdaporg org=orgbiz.getByOid(Long.valueOf(oid));
		request.setAttribute("org", org);
		request.setAttribute("ope", "doOrgUserUp");
		request.setAttribute("oid", oid);
		return mapping.findForward("orgUserAdd");
	}
	
	/**
	 * 修改某企业(企业标识oid)下某个用户信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doOrgUserUp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String uid = request.getParameter("uid");
		
		String oid=request.getParameter("oid");
		String name=request.getParameter("name");
		String password = request.getParameter("password");
		String rid = request.getParameter("rid");
		String oldname = request.getParameter("oldname");
		
		if(oid==null||oid.equals("")||name==null||name.equals("")||password==null||password.equals("")
				||uid==null||uid.equals("")||rid==null||rid.equals("")||oldname==null||oldname.equals("")){
			request.setAttribute("tip", "<font color='red'>修改失败,请尽快联系系统开发人员</font>");
			if(oid!=null&&!oid.equals("")){
				return new ActionForward("/userconfig.do?ope=toOrgUserUp&oid="+oid+"&uid="+uid);
			}
			else{
				return new ActionForward("/userconfig.do?ope=toOrgUser");
			}
		}
		if(!name.equals(oldname)){
			//根据企业标识id，获取该企业下所有非系统管理员、非工程人员的用户列表
			List<Fdapuser> orguserlist = userconfigbiz.getOrguser(oid);
			if(orguserlist!=null&&orguserlist.size()!=0){
				for(Fdapuser fu : orguserlist){
					if(name.equals(fu.getName())){
						request.setAttribute("tip", "<font color='blue'>该企业下已存在这个用户名</font>");
						return new ActionForward("/userconfig.do?ope=toOrgUserUp&oid="+oid+"&uid="+uid);
					}
				}
			}
		}
		//根据系统角色标识id，获取系统角色信息
		Fdaprole frole = userconfigbiz.getRoleByRid(rid);
		if(frole==null){
			request.setAttribute("tip", "<font color='red'>修改失败</font>");
			return new ActionForward("/userconfig.do?ope=toOrgUserUp&oid="+oid+"&uid="+uid);
		}
		
		Fdaporg forg = new Fdaporg();
		forg.setOid(Long.valueOf(oid));
		
		//构建要修改的用户信息对象
		Fdapuser fuser = new Fdapuser();
		fuser.setUserid(Long.valueOf(uid));
		fuser.setFdaporg(forg);
		fuser.setFdaprole(frole);
		fuser.setName(name);
		fuser.setPassword(password);
		
		if(!userconfigbiz.updateUser(fuser)){
			request.setAttribute("tip", "<font color='red'>修改失败，请联系开发人员</font>");
		}
		
		return new ActionForward("/userconfig.do?ope=backToOrgUser&oid="+oid);
	}
	
}
