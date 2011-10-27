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
 * ������������user.do�����action extends baseAction
 * @author Administrator
 *
 */
public class UserAction extends BaseAction {

	/**
	 * ��springע��ҵ����ʽӿ�
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
	 * �û���½
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
		
		//��ȡ��֤�롢�ͻ�����
		String code = request.getParameter("checkCode");
		String clientName = request.getParameter("clientName");	
		String sessionCode = (String)request.getSession().getAttribute("rand");
		
		
		//�û���������
		if(uname==null || uname.equals("")||
			upassword ==null || upassword.equals("")){
				request.setAttribute("msg", "��ȷ����Ϣ����!") ;
				return mapping.findForward("index");
		}
		
		//��֤�롢�ͻ�����
		if(code==null || code==""|| clientName==null ||clientName==""){
			request.setAttribute("msg", "��ȷ����Ϣ��������");
			return mapping.findForward("index");
		}
		
		//������֤��
		if(!code.equalsIgnoreCase(sessionCode)){
			request.setAttribute("msg", "��֤�����");
			return mapping.findForward("index");
		}
		
		 List<TbccClient> clientList = this.clientBiz.getByName(clientName);
		
		//�ж��˻��Ƿ����
		 if(clientList==null || clientList.size()==0 || clientList.get(0)==null)
		 {
			 request.setAttribute("msg", "�û���¼��Ϣ����!");
			 return mapping.findForward("index");
		 }
		 
		 
		 TbccClient loginClient = clientList.get(0) ;		//��¼���˺Ŷ���
		 
		 //�жϵ�¼�û��������˺�����
		 
		//********************************�����û�******************************************** 
		 if(loginClient.getTransactionRole().getRoleName().equals("�����û�")){					 
			 return  new ActionForward("/area.do?ope=toListByArea&araeId="+loginClient.getTransactionId());		
			 
			 
		//**************************************�ܲ��û�************************************
		 }else if (loginClient.getTransactionRole().getRoleName().equals("�ܲ��û�")){		
			 
			 TbccBaseUser u = null ;
			 String roleName = "" ;
			 List<TbccHqUser> userlist = userbiz.getHqUserByClientId(loginClient.getId()) ;
			 
			 if(userlist==null || userlist.size()==0)
			 {
				 request.setAttribute("msg", "�û���¼��Ϣ����!");
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
				 request.setAttribute("msg", "�û���¼��Ϣ����!");
				 return mapping.findForward("index");
			 }
			 
			 	//*************�����Ҫ�ĵ�¼��Ϣ
			 	request.getSession().setAttribute("LoginUser", u);
				String userInfo = "�˻�����: "+loginClient.getClientName()+" &nbsp;  �û���:"+u.getUname() ;
				request.getSession().setAttribute("userInfo", userInfo);
			
				//Ϊ���û�����ѡ��ͬ�ķ�֧�ṹ��������ܲ�
			    request.getSession().setAttribute("hqId", loginClient.getTransactionId());
				
			    
			    /**
			     * ������ܲ��û���Logo��Ϣ���Լ�ͼƬ��Ϣ
			     */
			    
			    TbccHqType hq = this.hqBiz.getById(loginClient.getTransactionId());
			    
			    if(hq==null	){
			    	 request.setAttribute("msg", "�û���¼��Ϣ����!");
			    	 logger.warn("�ܲ��û�"+uname+"��¼ʧ��! ��Ӧ���ܲ�������");
			    	 return mapping.findForward("index");
			    }
			    
			    
			    TbccLogo logo  = new TbccLogo() ;
			    logo.setLogoDisplayName(hq.getHqDisplayName());
			    //�ж��Ƿ���ʾLogo
			    
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
			    
			    
			    
			    //���ֹ���Ա�û��ͷǹ���Ա�û� userbiz.getHqRoleById(u.getHqRoles().getId()))
			    if(roleName.equals("����Ա")){
					return new ActionForward("/configHqUser.do?ope=forwordConfigHqUser");
				}else{
					 return new ActionForward("/hq.do?ope=toListByHq&hqId="+loginClient.getTransactionId());
				}
			    
				
			// return new ActionForward("/hq.do?ope=toListByHq&hqId="+loginClient.getTransactionId());
			 
			 
			 
			//***********************��֧�û�*******************************************	 
		 }else if (loginClient.getTransactionRole().getRoleName().equals("��֧�û�")){	
			 
			 TbccBaseUser u = null ;
			 String roleName = "" ;
			 
			 List<TbccUser> userlist = userbiz.getBranchUserByClientId(loginClient.getId()) ;
			 
			 if(userlist==null || userlist.size()==0 || userlist.get(0)==null ){
				 request.setAttribute("msg", "�û���¼��Ϣ����!");
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
				 request.setAttribute("msg", "�û���¼��Ϣ����!");
				 return mapping.findForward("index");
			 }
			 
			 
			 	//*************�����Ҫ�ĵ�¼��Ϣ
			 	request.getSession().setAttribute("LoginUser", u);
				String userInfo = "�˻�����: "+loginClient.getClientName()+" &nbsp;  �û���:"+u.getUname() ;
				request.getSession().setAttribute("userInfo", userInfo);
				
				
				//*************�����Ҫ��Ȩ�޹��ܡ�Ȩ�޹���ģ���Ѿ�������branch��������
				//List<TbccFunction> functionlist = userbiz.getUserFunction(u) ;							
				//request.getSession().setAttribute("power", userbiz.getPower(functionlist)) ;			
			 
				
				
				/**
				 * ����÷�֧������Logo �Լ�ͼƬ
				 */
				TbccBranchType branchType = this.branchBiz.getById(loginClient.getTransactionId());
				
				if(branchType==null){
					request.setAttribute("msg", "�û���¼��Ϣ����!");
					logger.warn("��֧�û� "+uname+" ��¼ʧ��! �û����ڵķ�֧������ ") ;
					return mapping.findForward("index");
				}
				
				TbccLogo logo = new TbccLogo() ;
				logo.setLogoDisplayName(branchType.getBranchDisplayName()) ;
				
				/**
				 * �ж��Ƿ���ʾ��֧��Logo
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
				
				//���ݽ�ɫ���ơ��ж��Ƿ��ǹ���Ա�û�u.getDataRoles().getRoleName()
			 
				if(roleName.equals("����Ա")){
					return new ActionForward("/configUser.do?ope=forwordConfigUser");
				}else{
					//ֻ��ԭ����ת��ҳ��
					//return  new ActionForward("/branch.do?ope=toListByBranch&branchId="+loginClient.getTransactionId());
					
					
					//���ӷ�֧�û���¼��¼״̬
					TbccBranchUserAlarmLogs logs =new TbccBranchUserAlarmLogs() ;
					logs.setClientName(u.getClient().getClientName()) ;
					logs.setUserName(u.getUname()) ;
					logs.setLoginAddr(request.getHeader("real_IP")) ;
					logs.setLoginTime(new java.util.Date(request.getSession().getCreationTime()));
					logs.setLoginAlarmState(this.branchUserAlarmLogsBiz.getStoreSysAlarmState(u.getClient().getTransactionId())) ;
					this.branchUserAlarmLogsBiz.addLogs(logs);
					
					
					//��ת���µ���ҳ��
					return new ActionForward("/branch.do?ope=toMain&branchId="+loginClient.getTransactionId());
					
				}
				 
		 }else{
			 request.setAttribute("msg", "�û���¼��Ϣ����!");
			 return mapping.findForward("index");
		 }
		
	}
	
	/**
	 * ע����֧�û������ص���½ҳ��
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
		//����Ƿ�֧�û�ע��������Ҫ���������ֿ�ϵͳ�ı���״̬
		TbccBaseUser loginUser =(TbccBaseUser) request.getSession().getAttribute("LoginUser") ;
		if(loginUser.getClient().getTransactionRole().getRoleName().equals("��֧�û�")){
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
	 * ע���ܲ��û������ص�����ѡ��ҳ��
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
		
		//���Ȼ�ȡ�Ự����ԭ�еĵ�¼��Ϣ��Ȼ���ǻỰʧЧ��Ȼ�����±���
		TbccBaseUser h = (TbccBaseUser)request.getSession().getAttribute("LoginUser") ;
		Long hqId = (Long)request.getSession().getAttribute("hqId");
		String userInfo = (String)request.getSession().getAttribute("userInfo") ;
		TbccLogo l = (TbccLogo)request.getSession().getAttribute("logo");
	
		if(hqId==null || hqId.equals("")){
			request.setAttribute("errorMsg", "����Ϊ�ջ�ǿ�");
			logger.warn("�ܲ��û�ע����������(doHqLogout)!");
			throw new Exception("�ܲ���ʶ�Ƿ�!");
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
