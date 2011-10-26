package org.fdap.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.fdap.biz.ProjectConfigBiz;
import org.fdap.biz.RefConfigBiz;
import org.fdap.biz.StoreConfigBiz;
import org.fdap.entity.Fdapproject;
import org.fdap.entity.Fdapref;
import org.fdap.entity.Fdapstoretype;
import org.fdap.util.BaseAction;

/**
 * 冷库配置的action 
 * @author zhaoyou
 *
 */
public class RefConfigAction extends BaseAction {
	
	private Logger logger = Logger.getLogger(RefConfigAction.class) ;
	
	private RefConfigBiz refConfigBiz = null ;

	private ProjectConfigBiz projectConfigBiz = null ;
	
	private StoreConfigBiz storeConfigBiz = null ;
	
	public StoreConfigBiz getStoreConfigBiz() {
		return storeConfigBiz;
	}

	public void setStoreConfigBiz(StoreConfigBiz storeConfigBiz) {
		this.storeConfigBiz = storeConfigBiz;
	}

	public ProjectConfigBiz getProjectConfigBiz() {
		return projectConfigBiz;
	}

	public void setProjectConfigBiz(ProjectConfigBiz projectConfigBiz) {
		this.projectConfigBiz = projectConfigBiz;
	}

	public RefConfigBiz getRefConfigBiz() {
		return refConfigBiz;
	}

	public void setRefConfigBiz(RefConfigBiz refConfigBiz) {
		this.refConfigBiz = refConfigBiz;
	}

	/**
	 * 跳转到冷库列表页面
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
		
		String oid = request.getParameter("oid");
		String projectId = request.getParameter("projectId_to_ref") ;
		
		if(oid==null || oid.equals("")|| projectId==null || projectId.equals("")){
			logger.warn("param is null (oid,projectId)") ;
			throw new Exception("param is nul (oid,projectId )");
		}
		
		request.setAttribute("project", this.getProjectConfigBiz().getById(new Long(projectId))) ;
		request.setAttribute("refList", this.getRefConfigBiz().getByProjectId(new Long(projectId)));
		
		return mapping.findForward("list") ;
	}
	
	/**
	 * 跳转到增加冷库页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toAddRef(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String oid = request.getParameter("oid") ;
		String projectId_to_ref = request.getParameter("projectId_to_ref") ;
		
		if(oid==null || oid.equals("")|| projectId_to_ref==null || projectId_to_ref.equals("")){
			logger.warn("param is null ");
			throw new Exception("param is null");
		}
		
		request.setAttribute("project", this.getProjectConfigBiz().getById(new Long(projectId_to_ref)));
		request.setAttribute("storeTypeList", this.getStoreConfigBiz().getAll());
		request.setAttribute("operater", "doAddRef") ;
		return mapping.findForward("update") ;
	}
	
	/**
	 * 保存新增冷库信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doAddRef(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String oid = request.getParameter("oid") ;
		String projectId_to_ref  = request.getParameter("projectId_to_ref") ;
		String refName = request.getParameter("refName") ;
		String storeTypeId = request.getParameter("storeTypeId") ;
		String storeTypeName = request.getParameter("storeTypeName") ;
		String floorTypeId = request.getParameter("floorTypeId") ;
		String floorNo = request.getParameter("floorNo") ;
		String remark = request.getParameter("refRemark");
		String isactive = request.getParameter("isactive") ;
		String projectType = request.getParameter("projectType");
		
		if(oid==null || oid.equals("") || projectId_to_ref==null || projectId_to_ref.equals("")||refName==null || refName.equals("")||
				storeTypeId==null || storeTypeId.equals("")|| (!projectType.equals("2")&&(floorTypeId==null || floorTypeId.equals("")|| floorNo==null || floorNo.equals("")))){
			logger.warn("param is null") ;
			throw new Exception("param is null");
		}
		
		Fdapref ref =new Fdapref();
		ref.setFdapproject(new Fdapproject(new Long(projectId_to_ref)));
		ref.setFdapstoretype(new Fdapstoretype(new Integer(storeTypeId),storeTypeName));
		if(projectType.equals("2")){
			ref.setFloorNo(1);
			ref.setFloorType(1);
		}else{
			ref.setFloorNo(new Integer(floorNo));
			ref.setFloorType(new Integer(floorTypeId));
		}
		ref.setIsactive(new Integer(isactive==null?"1":isactive));
		
			ref.setRemark(remark);
		
		ref.setName(refName);
		
		this.getRefConfigBiz().addRef(ref);
		
		return new ActionForward("/refconfig.do?ope=toList");
	}
	
	/**
	 *  跳转到编辑冷库页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toEditRef(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String oid = request.getParameter("oid") ;
		String projectId_to_ref = request.getParameter("projectId_to_ref") ;
		String[] chkRef = request.getParameterValues("chkRef") ;
		
		if(oid==null || oid.equals("")||projectId_to_ref==null || projectId_to_ref.equals("")||chkRef==null || chkRef.length==0){
			logger.warn("param is null (oid,projectId_to_ref,chkRef)") ;
			throw new Exception("param is null (oid,projectId_to_ref,chkRef)");
		}
		
		request.setAttribute("project", this.getProjectConfigBiz().getById(new Long(projectId_to_ref)));
		request.setAttribute("editObj",this.refConfigBiz.getById(new Long(chkRef[0])) ) ;
		request.setAttribute("storeTypeList", this.getStoreConfigBiz().getAll());
		request.setAttribute("operater", "doEditRef") ;
		
		return mapping.findForward("update") ;
	}
	
	/**
	 * 保存需要更新的冷库信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doEditRef(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String oid = request.getParameter("oid") ;
		String projectId_to_ref  = request.getParameter("projectId_to_ref") ;
		String refName = request.getParameter("refName") ;
		String storeTypeId = request.getParameter("storeTypeId") ;
		String storeTypeName = request.getParameter("storeTypeName") ;
		String floorTypeId = request.getParameter("floorTypeId") ;
		String floorNo = request.getParameter("floorNo") ;
		String isactive = request.getParameter("isactive") ;
		String refId = request.getParameter("refId") ;
		String remark = request.getParameter("refRemark");
		
		String storeid_compare = request.getParameter("storeid_compare");
		
		if(oid==null || oid.equals("") || projectId_to_ref==null || projectId_to_ref.equals("")||refName==null || refName.equals("")||
				storeTypeId==null || storeTypeId.equals("")|| floorTypeId==null || floorTypeId.equals("")|| floorNo==null || floorNo.equals("")
				||refId==null || refId.equals("") || refId==null || refId.equals("")||storeid_compare==null||storeid_compare.equals("")){
			logger.warn("param is null") ;
			throw new Exception("param is null");
		}
		
		if(!storeTypeId.equals(storeid_compare)){
			//更新该冷库下所有探头的上下限
			refConfigBiz.updateRefAi(Long.parseLong(refId), Long.parseLong(storeTypeId));
		}
		
		Fdapref ref =new Fdapref();
		ref.setFdapproject(new Fdapproject(new Long(projectId_to_ref)));
		ref.setFdapstoretype(new Fdapstoretype(new Integer(storeTypeId),storeTypeName));
		ref.setFloorNo(new Integer(floorNo));
		ref.setFloorType(new Integer(floorTypeId));
		ref.setIsactive(new Integer(isactive==null?"1":isactive));
		ref.setName(refName);
		ref.setRemark(remark);
		ref.setRefId(new Long(refId));
		this.getRefConfigBiz().updateRef(ref);
		
		return new ActionForward("/refconfig.do?ope=toList");
	}
	
	/**
	 * 批量删除冷库信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toDelRef(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String oid = request.getParameter("oid");
		String projectId = request.getParameter("projectId_to_ref") ;
		String[] chkRef = request.getParameterValues("chkRef") ;
		
		if(oid==null || oid.equals("")|| projectId==null || projectId.equals("")||chkRef==null || chkRef.length==0){
			logger.warn("param is null (oid,projectId)") ;
			throw new Exception("param is nul (oid,projectId,chkRef )");
		}
		
		this.getRefConfigBiz().delRef(chkRef);
		
		return new ActionForward("/refconfig.do?ope=toList");
	}
	
	

}
