package org.fdap.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.fdap.biz.OrgBiz;
import org.fdap.biz.OrgConfigBiz;
import org.fdap.entity.Fdaporg;
import org.fdap.util.BaseAction;

/**
 * 机构配置的action
 * @author zhaoyou
 *
 */
public class OrgConfigAction extends BaseAction {
	
	private Logger logger = Logger.getLogger(OrgConfigAction.class) ;
	
	private OrgConfigBiz orgConfigBiz  = null ;
	
	private OrgBiz orgBiz = null ;
	
	public OrgBiz getOrgBiz() {
		return orgBiz;
	}

	public void setOrgBiz(OrgBiz orgBiz) {
		this.orgBiz = orgBiz;
	}

	public OrgConfigBiz getOrgConfigBiz() {
		return orgConfigBiz;
	}

	public void setOrgConfigBiz(OrgConfigBiz orgConfigBiz) {
		this.orgConfigBiz = orgConfigBiz;
	}

	
	/**
	 * 跳转到后台主页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goAdmin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("main");
	}
	
	/**
	 * 跳转到顶级机构信息页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toTopOrg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("topOrg", this.getOrgConfigBiz().getTopOrg()) ;
		return mapping.findForward("top");
	}
	
	/**
	 * 保存顶级机构的信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doTopOrg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oid  = request.getParameter("oid") ;
		String orgName = request.getParameter("orgName") ;
		String orgAccount = request.getParameter("orgAccount") ;
		String orgPhone = request.getParameter("orgPhone") ;
		String orgEmail  = request.getParameter("orgEmail") ;
		
		if(orgName==null || orgName.equals("") || orgAccount==null || orgAccount.equals("") 
				||oid==null || oid.equals("")){
			logger.warn("传递了非法的参数(null)") ;
			throw new Exception("传递了非法的参数(null)");
		}
		
		Fdaporg org = new Fdaporg() ;
		org.setOid(new Long(oid)) ;
		org.setAccount(orgAccount) ;
		if(orgEmail!=null&&!orgEmail.equals("")){
			org.setEmail(orgEmail) ;
		}
		org.setName(orgName) ;
		if(orgPhone!=null&&!orgPhone.equals("")){
			org.setTelephone(orgPhone) ;
		}
		org.setNodetype(new Integer(0)) ;
		org.setParentId(new Long(-1)) ;
		
		if(this.getOrgConfigBiz().updateTopOrg(org)){
			request.setAttribute("tip", "<font color='blue'>更新成功!</font>") ;
		}else{
			request.setAttribute("tip", "<font color='red'>更新失败!</font>") ;
		}
		
		return new ActionForward("/orgconfig.do?ope=toTopOrg");
	}
	
	
	/**
	 * 跳转到机构列表页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toOrgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		
		//获取机构列表
		String oid = request.getParameter("oid") ;
		Fdaporg org = null ;
		
		//获取机构下的下级机构或企业
		List<Fdaporg> list = null ;
		if(oid==null || oid.equals("")){
			org = this.getOrgBiz().getByOid(new Long(1)) ;
			 list = this.getOrgConfigBiz().getOrgList(new Long(1)) ;
		}else{
			org = this.getOrgBiz().getByOid(new Long(oid)) ;
			list = this.getOrgConfigBiz().getOrgList(new Long(oid)) ;
		}
		
		request.setAttribute("orgObj", org) ;
		request.setAttribute("orgList", list) ;
		
		//获取机构树形菜单
		String treeStr =  this.getOrgConfigBiz().getOrgTree() ;
		request.setAttribute("tree", treeStr) ;
		
		return mapping.findForward("orgList");
	}
	
	/**
	 * 跳转到增加机构页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toAddOrg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			// 获取上级的机构标识Id
			String oid = request.getParameter("oid") ;
			
			if(oid==null || oid.equals("")){
				logger.warn("oid arguments is null") ;
				throw new Exception("传递了非法的参数(null)");
			}
			//为保证添加的全部是企业，或者全部是机构
			List<Fdaporg> orglist = this.getOrgBiz().getByParentId(new Long(oid));
			if(orglist!=null&&orglist.size()!=0){
				request.setAttribute("orgtype", orglist.get(0).getNodetype());
			}
			
			//保存上级的机构
			request.setAttribute("orgObj", this.getOrgBiz().getByOid(new Long(oid))) ;
			
			//保存当前操作的 
			request.setAttribute("operater","add") ;
			
			//保存所有机构树形菜单供用户选择
			request.setAttribute("tree", this.getOrgConfigBiz().getOnlyOrgTree());
			
			return mapping.findForward("orgConfig") ;
	}
	
	/**
	 * 保存需要添加的机构
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doAddOrg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
			String orgName = request.getParameter("orgName") ;
			String orgPhone = request.getParameter("orgPhone") ;
			String orgAccount = request.getParameter("orgAccount") ;
			String orgEmail = request.getParameter("orgEmail") ;
			String nodeType = request.getParameter("nodetype") ;
			String oid = request.getParameter("oid") ;	// 实际代表上级机构标识id
//			String isshowmap = request.getParameter("showmap");
			
			Fdaporg org = new Fdaporg();
			
			org.setName(orgName) ;
			org.setTelephone(orgPhone) ;
			org.setAccount(orgAccount) ;
			org.setEmail(orgEmail) ;
			org.setFlag(new Integer(0)) ;
			org.setNodetype(new Integer(nodeType) ) ;
			org.setParentId(new Long(oid)) ;
			
			//默认为列表显示
			org.setIsshowmap(0);
			
			this.getOrgConfigBiz().addOrg(org);
			
			return new ActionForward("/orgconfig.do?ope=toOrgList") ;
	}
	
	/**
	 * 跳转到编辑机构页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toEditOrg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String oid = request.getParameter("oid") ;				//上级机构标识id
			String [] chkOrg = request.getParameterValues("ckbOrg") ;
			
			if(oid==null || oid.equals("")||chkOrg==null || chkOrg.length==0){
				logger.warn("params is null (oid,chkOrg)") ;
				throw new Exception("参数非法(oid,chkOrg is null)");
			}
			//获取需要编辑的机构，以及上级机构
			Fdaporg orgEditObj = this.getOrgBiz().getByOid(new Long(chkOrg[0]));
			Fdaporg orgObj  = this.getOrgBiz().getByOid(new Long(oid));
			
			request.setAttribute("orgEditObj", orgEditObj) ;
			request.setAttribute("orgObj", orgObj) ;
			request.setAttribute("operater", "edit");
			
			//保存所有机构树形菜单供用户选择
			request.setAttribute("tree", this.getOrgConfigBiz().getOnlyOrgTree());
			
			return mapping.findForward("orgConfig") ;
	}
	
	/**
	 * 保存编辑机构信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doEditOrg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String orgName = request.getParameter("orgName") ;
		String orgPhone = request.getParameter("orgPhone") ;
		String orgAccount = request.getParameter("orgAccount") ;
		String orgEmail = request.getParameter("orgEmail") ;
		String nodeType = request.getParameter("nodetype") ;
		String oid = request.getParameter("oid") ;	// 实际代表上级机构标识id
		String editId = request.getParameter("editId") ;
		
		//String isshowmap = request.getParameter("showmap");
		
		Fdaporg org = new Fdaporg();
		org.setName(orgName) ;
		org.setTelephone(orgPhone) ;
		org.setAccount(orgAccount) ;
		org.setEmail(orgEmail) ;
		org.setFlag(new Integer(0)) ;
		org.setNodetype(new Integer(nodeType) ) ;
		org.setParentId(new Long(oid)) ;
		org.setOid(new Long(editId));
		
		
		this.getOrgConfigBiz().updateOrg(org);
		
		return new ActionForward("/orgconfig.do?ope=toOrgList");
	}
	
	/**
	 * 删除机构信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doDelOrg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String []	 orgIds = request.getParameterValues("ckbOrg") ;
			String oid = request.getParameter("oid") ;
			
			if(orgIds==null || orgIds.length==0 || oid==null || oid.equals("")){
				logger.warn("param is null (orgIds)");
				throw new Exception("param is null (orgIds)");
			}
			
			//如果存在下级机构、则提示先删除下级机构在删除当前机构
			if(this.orgConfigBiz.getExistsLowerOrg(orgIds)){
				request.setAttribute("msg", "<font color='red'>删除失败,请先删除当前机构的下级机构或企业!</font>") ;
			}else{
				this.orgConfigBiz.delOrg(orgIds);
			}
			
			return new ActionForward("/orgconfig.do?ope=toOrgList") ;
	}
	

}
