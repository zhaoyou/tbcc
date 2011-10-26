package org.fdap.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.fdap.biz.HospitalBiz;
import org.fdap.entity.FdapHospital;
import org.fdap.util.BaseAction;

public class HospitalAction extends BaseAction {
	private Logger logger = Logger.getLogger(HospitalAction.class);
	
	private HospitalBiz hospitalBiz = null;

	public void setHospitalBiz(HospitalBiz hospitalBiz) {
		this.hospitalBiz = hospitalBiz;
	}

	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("list", this.hospitalBiz.getAll());
		return mapping.findForward("list");
	}
	
	public ActionForward toAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("edit");
	}
	
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		FdapHospital h = hospitalBiz.get(Integer.parseInt(id));
		request.setAttribute("h", h);
		return mapping.findForward("edit");
	}
	
	public ActionForward doSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String name = request.getParameter("hospitalName");
		String lat = request.getParameter("latitude");
		String log = request.getParameter("longitude");
		String projectId = request.getParameter("projectId");
		logger.info("name: " + name + " lat: " + lat + " long: " + log + " projectId: " + projectId);
		FdapHospital hospital = new FdapHospital();
		hospital.setHospitalName(name);
		hospital.setIsReal(0);
		hospital.setLatitude(Double.parseDouble(lat));
		hospital.setLongitude(Double.parseDouble(log));
		hospital.setProjectId(projectId);
		
		hospitalBiz.add(hospital);
		return new ActionForward("/hospital.do?ope=toList");//mapping.findForward("list");
	}
	
	public ActionForward doUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String name = request.getParameter("hospitalName");
		String lat = request.getParameter("latitude");
		String log = request.getParameter("longitude");
		String id = request.getParameter("id");
		String projectId = request.getParameter("projectId");
		logger.info("name: " + name + " lat: " + lat + " long: " + log + " projectId: " + projectId);
		FdapHospital hospital = new FdapHospital();
		hospital.setHospitalName(name);
		hospital.setIsReal(0);
		hospital.setLatitude(Double.parseDouble(lat));
		hospital.setLongitude(Double.parseDouble(log));
		hospital.setProjectId(projectId);
		hospital.setId(Integer.parseInt(id));
		
		hospitalBiz.update(hospital);
		return new ActionForward("/hospital.do?ope=toList");
	}
	
	public ActionForward toDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		FdapHospital h = new FdapHospital();
		h.setId(Integer.parseInt(id));
		this.hospitalBiz.delete(h);
		return new ActionForward("/hospital.do?ope=toList");
	}
}
