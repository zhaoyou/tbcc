package org.tbcc.struts.action;

import java.text.DateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.registry.infomodel.User;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.directwebremoting.util.LogErrorHandler;
import org.dom4j.swing.BranchTreeNode;
import org.tbcc.biz.BranchBiz;
import org.tbcc.biz.BranchUserAlarmLogsBiz;
import org.tbcc.biz.ClientBiz;
import org.tbcc.biz.HqBiz;
import org.tbcc.biz.UserBiz;
import org.tbcc.entity.TbccBaseUser;
import org.tbcc.entity.TbccBranchType;
import org.tbcc.entity.TbccBranchUserAlarmLogs;
import org.tbcc.entity.TbccClient;
import org.tbcc.entity.TbccFunction;
import org.tbcc.entity.TbccHqType;
import org.tbcc.entity.TbccHqUser;
import org.tbcc.entity.TbccLogo;
import org.tbcc.entity.TbccUser;
import org.tbcc.struts.form.UserForm;
import org.tbcc.util.BaseAction;
import org.tbcc.util.LogoOperate;
import org.tbcc.util.MyUtil;

/**
 * 这是用来处理user.do请求的action extends baseAction
 * @author Administrator
 *
 */
public class UserAction extends BaseAction {

	/**
	 * 由spring注入业务访问接口
	 */
	private UserBiz userbiz = null ;

	private ClientBiz clientBiz = null ;
	
	private BranchBiz branchBiz = null ;
	
	private HqBiz hqBiz = null ;
	
	private Logger logger = Logger.getLogger(UserAction.class);
	
	private BranchUserAlarmLogsBiz branchUserAlarmLogsBiz = null ;
	
	
	
	public void setBranchUserAlarmLogsBiz(
			BranchUserAlarmLogsBiz branchUserAlarmLogsBiz) {
		this.branchUserAlarmLogsBiz = branchUserAlarmLogsBiz;
	}


	public void setClientBiz(ClientBiz clientBiz) {
		this.clientBiz = clientBiz;
	}


	public void setUserbiz(UserBiz userbiz) {
		this.userbiz = userbiz;
	}
	
	public void setBranchBiz(BranchBiz branchBiz) {
		this.branchBiz = branchBiz;
	}


	public void setHqBiz(HqBiz hqBiz) {
		this.hqBiz = hqBiz;
	}
	

	/**
	 * 用户登陆
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
		String uname = request.getParameter("uname") ;
		String upassword = request.getParameter("upassword");
		
		//获取验证码、客户类型
		String code = request.getParameter("checkCode");
		String clientName = request.getParameter("clientName");	
		String sessionCode = (String)request.getSession().getAttribute("rand");
		
		
		//用户名、密码
		if(uname==null || uname.equals("")||
			upassword ==null || upassword.equals("")){
				request.setAttribute("msg", "请确保信息完整!") ;
				return mapping.findForward("index");
		}
		
		//验证码、客户名称
		if(code==null || code==""|| clientName==null ||clientName==""){
			request.setAttribute("msg", "请确保信息的完整！");
			return mapping.findForward("index");
		}
		
		//检验验证码
		if(!code.equalsIgnoreCase(sessionCode)){
			request.setAttribute("msg", "验证码错误");
			return mapping.findForward("index");
		}
		
		 List<TbccClient> clientList = this.clientBiz.getByName(clientName);
		
		//判断账户是否存在
		 if(clientList==null || clientList.size()==0 || clientList.get(0)==null)
		 {
			 request.setAttribute("msg", "用户登录信息错误!");
			 return mapping.findForward("index");
		 }
		 
		 
		 TbccClient loginClient = clientList.get(0) ;		//登录的账号对象
		 
		 //判断登录用户所属的账号类型
		 
		//********************************区域用户******************************************** 
		 if(loginClient.getTransactionRole().getRoleName().equals("区域用户")){					 
			 return  new ActionForward("/area.do?ope=toListByArea&araeId="+loginClient.getTransactionId());		
			 
			 
		//**************************************总部用户************************************
		 }else if (loginClient.getTransactionRole().getRoleName().equals("总部用户")){		
			 
			 TbccBaseUser u = null ;
			 String roleName = "" ;
			 List<TbccHqUser> userlist = userbiz.getHqUserByClientId(loginClient.getId()) ;
			 
			 if(userlist==null || userlist.size()==0)
			 {
				 request.setAttribute("msg", "用户登录信息错误!");
				 return mapping.findForward("index");
			 }
			 
			 for (TbccHqUser tbccHqUser : userlist) {
				if(tbccHqUser.getUname().equals(uname) && tbccHqUser.getUpassword().equals(upassword)){
					u = tbccHqUser	 ;
					u.setClient(tbccHqUser.getClient());
					roleName = tbccHqUser.getHqRoles().getName() ;
					break ;
				}
			}
			 
			 if(u==null){
				 request.setAttribute("msg", "用户登录信息错误!");
				 return mapping.findForward("index");
			 }
			 
			 	//*************保存必要的登录信息
			 	request.getSession().setAttribute("LoginUser", u);
				String userInfo = "账户名称: "+loginClient.getClientName()+" &nbsp;  用户名:"+u.getUname() ;
				request.getSession().setAttribute("userInfo", userInfo);
			
				//为了用户返回选择不同的分支结构，保存的总部
			    request.getSession().setAttribute("hqId", loginClient.getTransactionId());
				
			    
			    /**
			     * 保存该总部用户的Logo信息，以及图片信息
			     */
			    
			    TbccHqType hq = this.hqBiz.getById(loginClient.getTransactionId());
			    
			    if(hq==null	){
			    	 request.setAttribute("msg", "用户登录信息错误!");
			    	 logger.warn("总部用户"+uname+"登录失败! 对应的总部不存在");
			    	 return mapping.findForward("index");
			    }
			    
			    
			    TbccLogo logo  = new TbccLogo() ;
			    logo.setLogoDisplayName(hq.getHqDisplayName());
			    //判断是否显示Logo
			    
			    if(hq.getHqLogoEnable()!=null && hq.getHqLogoEnable().equals(HqBiz.LogoEnable) && hq.getHqLogo()!=null){    	
			    	if(LogoOperate.buildImage(hq.getHqLogo(),"h"+hq.getHqId()+".png")==0){
			    		logo.setIsShow(HqBiz.LogoDisable);
			    		logo.setLogoImg("logo.png");
			    	}else{
			    		logo.setIsShow(HqBiz.LogoEnable);
			    		logo.setLogoImg("h"+hq.getHqId()+".png");
			    	}
			    }else{
			    	logo.setIsShow(HqBiz.LogoDisable);
			    	logo.setLogoImg("logo.png");
			    }
			    
			    request.getSession().setAttribute("logo",logo);
			    
			    
			    
			    //区分管理员用户和非管理员用户 userbiz.getHqRoleById(u.getHqRoles().getId()))
			    if(roleName.equals("管理员")){
					return new ActionForward("/configHqUser.do?ope=forwordConfigHqUser");
				}else{
					 return new ActionForward("/hq.do?ope=toListByHq&hqId="+loginClient.getTransactionId());
				}
			    
				
			// return new ActionForward("/hq.do?ope=toListByHq&hqId="+loginClient.getTransactionId());
			 
			 
			 
			//***********************分支用户*******************************************	 
		 }else if (loginClient.getTransactionRole().getRoleName().equals("分支用户")){	
			 
			 TbccBaseUser u = null ;
			 String roleName = "" ;
			 
			 List<TbccUser> userlist = userbiz.getBranchUserByClientId(loginClient.getId()) ;
			 
			 if(userlist==null || userlist.size()==0 || userlist.get(0)==null ){
				 request.setAttribute("msg", "用户登录信息错误!");
				 return mapping.findForward("index");
			 }
			 
			 for (TbccUser tbccUser : userlist) {
				if(tbccUser.getUname().equals(uname) && tbccUser.getUpassword().equals(upassword)){
					u = tbccUser ;
					u.setClient(tbccUser.getClient());
					roleName = tbccUser.getDataRoles().getRoleName() ;
					break ;
				}
			}
			 
			 if(u==null){
				 request.setAttribute("msg", "用户登录信息错误!");
				 return mapping.findForward("index");
			 }
			 
			 
			 	//*************保存必要的登录信息
			 	request.getSession().setAttribute("LoginUser", u);
				String userInfo = "账户名称: "+loginClient.getClientName()+" &nbsp;  用户名:"+u.getUname() ;
				request.getSession().setAttribute("userInfo", userInfo);
				
				
				//*************保存必要的权限功能、权限功能模块已经放在了branch里面配置
				//List<TbccFunction> functionlist = userbiz.getUserFunction(u) ;							
				//request.getSession().setAttribute("power", userbiz.getPower(functionlist)) ;			
			 
				
				
				/**
				 * 保存该分支机构的Logo 以及图片
				 */
				TbccBranchType branchType = this.branchBiz.getById(loginClient.getTransactionId());
				
				if(branchType==null){
					request.setAttribute("msg", "用户登录信息错误!");
					logger.warn("分支用户 "+uname+" 登录失败! 用户所在的分支不存在 ") ;
					return mapping.findForward("index");
				}
				
				TbccLogo logo = new TbccLogo() ;
				logo.setLogoDisplayName(branchType.getBranchDisplayName()) ;
				
				/**
				 * 判断是否显示分支的Logo
				 */
				if(branchType.getBranchLogoEnable()!=null && branchType.getBranchLogoEnable().equals(BranchBiz.LogoEnable) && branchType.getBranchLogo()!=null){
					
					if(LogoOperate.buildImage(branchType.getBranchLogo(), "b"+branchType.getBranchId()+".png")==0){
						logo.setIsShow(BranchBiz.LogoDisable);
						logo.setLogoImg("logo.png");
					}else{
						logo.setIsShow(BranchBiz.LogoEnable);
						logo.setLogoImg("b"+branchType.getBranchId()+".png") ;
					}
					
				}else{
					logo.setIsShow(BranchBiz.LogoDisable);
					logo.setLogoImg("logo.png");
				}
				
				
				request.getSession().setAttribute("logo", logo);
				
				//根据角色名称、判断是否是管理员用户u.getDataRoles().getRoleName()
			 
				if(roleName.equals("管理员")){
					return new ActionForward("/configUser.do?ope=forwordConfigUser");
				}else{
					//只是原先跳转的页面
					//return  new ActionForward("/branch.do?ope=toListByBranch&branchId="+loginClient.getTransactionId());
					
					
					//增加分支用户记录登录状态
					TbccBranchUserAlarmLogs logs =new TbccBranchUserAlarmLogs() ;
					logs.setClientName(u.getClient().getClientName()) ;
					logs.setUserName(u.getUname()) ;
					logs.setLoginAddr(request.getHeader("real_IP")) ;
					logs.setLoginTime(new java.util.Date(request.getSession().getCreationTime()));
					logs.setLoginAlarmState(this.branchUserAlarmLogsBiz.getStoreSysAlarmState(u.getClient().getTransactionId())) ;
					this.branchUserAlarmLogsBiz.addLogs(logs);
					
					
					//跳转的新的主页面
					return new ActionForward("/branch.do?ope=toMain&branchId="+loginClient.getTransactionId());
					
				}
				 
		 }else{
			 request.setAttribute("msg", "用户登录信息错误!");
			 return mapping.findForward("index");
		 }
		
	}
	
	/**
	 * 注销分支用户、返回到登陆页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doLogout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//如果是分支用户注销，则需要更新整个仓库系统的报警状态
		TbccBaseUser loginUser =(TbccBaseUser) request.getSession().getAttribute("LoginUser") ;
		if(loginUser.getClient().getTransactionRole().getRoleName().equals("分支用户")){
				TbccBranchUserAlarmLogs logs = new TbccBranchUserAlarmLogs();
				logs.setClientName(loginUser.getClient().getClientName()) ;
				logs.setUserName(loginUser.getUname()) ;
				logs.setLoginTime(new java.util.Date(request.getSession().getCreationTime()));
				logs.setLogoutTime(new java.util.Date());
				logs.setLogoutAlarmState(this.branchUserAlarmLogsBiz.getStoreSysAlarmState(loginUser.getClient().getTransactionId())) ;
				this.branchUserAlarmLogsBiz.updateLogs(logs) ;
		}
		request.getSession().invalidate() ;
		
		return mapping.findForward("index");
	}
	
	
	/**
	 * 注销总部用户、返回到树形选择页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doHqLogout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TbccBaseUser	basehq = new TbccBaseUser();
		TbccLogo logo = new TbccLogo();
		
		//首先获取会话里面原有的登录信息、然后是会话失效、然后重新保存
		TbccBaseUser h = (TbccBaseUser)request.getSession().getAttribute("LoginUser") ;
		Long hqId = (Long)request.getSession().getAttribute("hqId");
		String userInfo = (String)request.getSession().getAttribute("userInfo") ;
		TbccLogo l = (TbccLogo)request.getSession().getAttribute("logo");
	
		if(hqId==null || hqId.equals("")){
			request.setAttribute("errorMsg", "参数为空或非空");
			logger.warn("总部用户注销发生错误(doHqLogout)!");
			throw new Exception("总部标识非法!");
		}
		basehq.setClient(h.getClient());
		basehq.setId(h.getId());
		basehq.setUcreated(h.getUcreated());
		basehq.setUenable(h.getUenable());
		basehq.setRoleId(h.getRoleId());
		basehq.setUname(h.getUname());
		basehq.setUpassword(h.getUpassword());
		
		
		logo.setLogoDisplayName(l.getLogoDisplayName());
		logo.setLogoImg(l.getLogoImg());
		
		request.getSession().invalidate() ;
	
		request.getSession().setAttribute("LoginUser", basehq);
		request.getSession().setAttribute("userInfo", userInfo);
		request.getSession().setAttribute("hqId", hqId);
		request.getSession().setAttribute("logo", logo);
		
		return new ActionForward("/hq.do?ope=toListByHq&hqId="+hqId);
	}




}
