package org.fdap.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.fdap.biz.OrgBiz;
import org.fdap.biz.ProjectConfigBiz;
import org.fdap.entity.Fdaporg;
import org.fdap.entity.Fdapproject;
import org.fdap.util.BaseAction;

/**
 * 工程配置的action
 * @author zhaoyou
 *
 */
public class ProjectConfigAction extends BaseAction {
	
	private Logger logger = Logger.getLogger(ProjectConfigAction.class) ;
	
	private ProjectConfigBiz projectConfigBiz = null ;
	
	private OrgBiz orgBiz = null ;

	
	
	public OrgBiz getOrgBiz() {
		return orgBiz;
	}

	public void setOrgBiz(OrgBiz orgBiz) {
		this.orgBiz = orgBiz;
	}

	public ProjectConfigBiz getProjectConfigBiz() {
		return projectConfigBiz;
	}

	public void setProjectConfigBiz(ProjectConfigBiz projectConfigBiz) {
		this.projectConfigBiz = projectConfigBiz;
	}

	
	/**
	 * 跳转到企业工程列表页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String oid = request.getParameter("oid") ;
		List<Fdapproject> list = null ;
		Fdaporg org = null ;
		
		if(oid!=null && !oid.equals("") ){
			org = this.getOrgBiz().getByOid(new Long(oid)) ;
			list = this.getProjectConfigBiz().getByOid(new Long(oid));
		}
		
		//获取企业下的工程列表
		request.setAttribute("orgObj", org);
		request.setAttribute("projectList", list) ;
		request.setAttribute("tree", this.getProjectConfigBiz().getOrgTree()) ;
		
		return mapping.findForward("list") ;
	}
	
	/**
	 * 跳转到增加企业工程列表页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toAddProject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oid = request.getParameter("oid") ;
		if(oid==null || oid.equals("")){
			logger.warn("param is null (oid)") ;
			throw new Exception("param is null (oid)");
		}
		//保存当前的企业信息
		request.setAttribute("org", this.getOrgBiz().getByOid(new Long(oid)));
		request.setAttribute("operater", "doAddProject") ;
		return mapping.findForward("update") ;
	}
	
	/**
	 * 保存一个工程
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doAddProject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String  projectName = request.getParameter("projectName") ;
		String projectType = request.getParameter("projectType") ;
		String projectRemark 	= request.getParameter("projectRemark") ;
		String oid = request.getParameter("oid") ;
		String projectNO = request.getParameter("projectNO");
		
		if(projectName==null || projectName.equals("") || projectType==null || projectType.equals("") 
				|| oid==null || oid.equals("")){
			logger.warn("param is null") ;
			throw new Exception("param is null");
		}
		
		if(projectType.equals("2")){
			if(projectNO==null||projectNO.equals(""))
			{
				logger.warn("projectNO is null");
				request.setAttribute("tip", "车载工程工程编号不能为空");
				return new ActionForward("/projectconfig.do?ope=toAddProject&oid="+oid);
			}
		}
		
		Fdapproject project = new Fdapproject();
		project.setName(projectName);
		project.setType(new Integer(projectType));
		project.setRemark(projectRemark) ;
		project.setFdaporg(new Fdaporg(new Long(oid)));
		project.setProjectNO(projectNO);
		
		this.getProjectConfigBiz().addProject(project);
		
		return new ActionForward("/projectconfig.do?ope=toList");
	}
	
	/**
	 * 跳转到工程编辑页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toEditProject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String[] chkproject = request.getParameterValues("chkproject") ;		
		String oid = request.getParameter("oid") ;
			
		if(chkproject==null || chkproject.length==0 || oid==null || oid.equals("")){
			logger.warn("param is null (chkproject,oid)") ;
			throw new Exception("param is null (chkproject,oid)");
		}
		
		//保存当前企业信息、需要更新的工程信息
		request.setAttribute("org", this.getOrgBiz().getByOid(new Long(oid)));
		request.setAttribute("editObj", this.getProjectConfigBiz().getById(new Long(chkproject[0])));
		request.setAttribute("operater", "doEditProject");
		
		return mapping.findForward("update") ;
	}
	
	/**
	 *  更新一个工程的信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doEditProject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String  projectName = request.getParameter("projectName") ;
		String projectType = request.getParameter("projectType") ;
		String projectRemark 	= request.getParameter("projectRemark") ;
		String oid = request.getParameter("oid") ;
		String projectId = request.getParameter("projectId");
		String projectNO = request.getParameter("projectNO");
		
		if(projectName==null || projectName.equals("") || projectType==null || projectType.equals("") || 
				oid==null || oid.equals("")||projectId==null || projectId.equals("")){
			logger.warn("param is null") ;
			throw new Exception("param is null");
		}
		
		if(projectType.equals("2")){
			if(projectNO==null||projectNO.equals(""))
			{
				logger.warn("projectNO is null");
				throw new Exception("projectNO is null");
			}
		}
		
		Fdapproject project = new Fdapproject();
		project.setName(projectName);
		project.setType(new Integer(projectType));
		project.setRemark(projectRemark) ;
		project.setProjectid(new Long(projectId));
		project.setProjectNO(projectNO);
		
		this.getProjectConfigBiz().updateProject(project);
		
		return new ActionForward("/projectconfig.do?ope=toList");
	}
	
	
	/**
	 * 删除企业工程信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toDelProject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] chkproject = request.getParameterValues("chkproject") ;
		
		if(chkproject==null || chkproject.length==0){
			logger.warn("param is null") ;
			throw new Exception("param is null");
		}
		
		this.getProjectConfigBiz().delProjectByIds(chkproject);
		
		return new ActionForward("/projectconfig.do?ope=toList");
	}
	

}
