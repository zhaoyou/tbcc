package org.fdap.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.fdap.biz.LinkConfigBiz;
import org.fdap.biz.LinkTypeBiz;
import org.fdap.biz.UserBiz;
import org.fdap.entity.Fdaplink;
import org.fdap.entity.Fdaplinktype;
import org.fdap.entity.Fdaporg;
import org.fdap.entity.Fdaprole;
import org.fdap.entity.Fdapuser;
import org.fdap.util.BaseAction;

/**
 * 用户操作action
 * @author zhaoyou
 *
 */
public class UserAction extends BaseAction {

	private UserBiz userBiz = null ;
	
	private Logger logger  = Logger.getLogger(UserAction.class);
	
	private LinkConfigBiz linkconfigbiz;
	
	private LinkTypeBiz linktypebiz;
	
	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}
	
	public UserBiz getUserBiz() {
		return userBiz;
	}

	public LinkConfigBiz getLinkconfigbiz() {
		return linkconfigbiz;
	}

	public void setLinkconfigbiz(LinkConfigBiz linkconfigbiz) {
		this.linkconfigbiz = linkconfigbiz;
	}
	
	public LinkTypeBiz getLinktypebiz() {
		return linktypebiz;
	}

	public void setLinktypebiz(LinkTypeBiz linktypebiz) {
		this.linktypebiz = linktypebiz;
	}

	/**
	 * 用户登录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doLogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String  clientName = request.getParameter("clientName");
		String  userName  = request.getParameter("userName");
		String password = request.getParameter("password");
		String checkCode = request.getParameter("checkCode");
		
		//验证参数
		if(clientName==null || clientName.equals("")|| userName==null || userName.equals("")|| password==null || password.equals("")
				||checkCode==null || checkCode.equals("")){
			logger.warn("登录参数非法!") ;
			request.setAttribute("msg", "请输入正确的参数!");
			return mapping.findForward("index");
		}
		
		//验证授权码
		if(!checkCode.equals(request.getSession().getAttribute("rand"))){
			logger.warn("验证码错误！");
			request.setAttribute("msg", "请输入正确的验证码!");
			return mapping.findForward("index") ;
		}
		Fdapuser user  = null;
		//超级用户，只有公司内部人员知道
		if(clientName.equals("THERMOBERG")&&userName.equals("THERMOBERG")&&password.equals("THERMOBERG-PROJECT")){
			user = new Fdapuser();
			Fdaprole fr = new Fdaprole();
			Fdaporg fo = new Fdaporg();
			fo.setOid(Long.parseLong("1"));
			fr.setRid(Long.parseLong("1"));
			user.setFdaprole(fr);
			user.setFdaporg(fo);
			user.setName(userName);
			user.setPassword(password);
		}else{
			//判断用户是否登录成功
			user  = userBiz.getLoginUser(clientName, userName, password);		
			if(user==null){
				System.out.println("user is null");
				logger.warn("用户登录失败!") ;
				request.setAttribute("msg", "用户登录失败!");
				return mapping.findForward("index");
			}
		}
		
		request.getSession().setAttribute("loginUser", user);
		
		List<Fdaplink> linklist = linkconfigbiz.getAllLink();
		List<Fdaplinktype> typelist = linktypebiz.getAllLinktype();
		request.getSession().setAttribute("alllinkList", linklist);
		request.getSession().setAttribute("alltypeList", typelist);
		
		/**
		 * 根据用户的角色跳转到相应的页面
		 */
		
		//工程角色
		if(user.getFdaprole().getRid().toString().equals("1")){	
			return new ActionForward("/admin/orgconfig.do?ope=goAdmin",true);
		//系统管理员	
		}else if (user.getFdaprole().getRid().toString().equals("2")){
			return new ActionForward("/admin/userconfig.do?ope=toOrgUser",true) ;
		//普通查看角色
		}else{
			//跳转到机构管理页面
//			System.out.println(request.getParameter("ope"));
			return new ActionForward("/org.do?ope=toOrg&oid="+user.getFdaporg().getOid(),true);
		}
	}

	public ActionForward doLogout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.getSession().invalidate() ;
		return mapping.findForward("index");
	}

}
