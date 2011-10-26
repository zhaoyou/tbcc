package org.fdap.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.fdap.biz.DefaultDisplayBiz;
import org.fdap.entity.Fdaporg;
import org.fdap.util.BaseAction;

public class DefaultDisplayAction extends BaseAction {
	
	private DefaultDisplayBiz defaultdisplaybiz;
	
	public DefaultDisplayBiz getDefaultdisplaybiz() {
		return defaultdisplaybiz;
	}
	
	public void setDefaultdisplaybiz(DefaultDisplayBiz defaultdisplaybiz) {
		this.defaultdisplaybiz = defaultdisplaybiz;
	}

	public ActionForward toDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oid =request.getParameter("oid");
		Fdaporg org = null;
		if(oid==null||oid.equals("")){
			org = this.getDefaultdisplaybiz().getByOid(new Long(1)) ;
		}else{
			org = this.getDefaultdisplaybiz().getByOid(new Long(oid));
		}
		request.setAttribute("orgObj", org);
		
		String tree = this.getDefaultdisplaybiz().getTree();
		request.setAttribute("tree", tree);
		return mapping.findForward("defaultDisplay");
	}
	
	public ActionForward doDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oid =request.getParameter("oid");
		String isshowmap = request.getParameter("isShowmap");
		
		boolean b=this.getDefaultdisplaybiz().updateOrg(new Long(oid), Integer.parseInt(isshowmap));
		if(b){
			request.setAttribute("tip", "更新成功");
		}else{
			request.setAttribute("tip", "更新失败");
		}
		
		return new ActionForward("/defaultdisplay.do?ope=toDefault&oid="+oid);
	}
	
}
