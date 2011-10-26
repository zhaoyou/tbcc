package org.fdap.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.fdap.biz.OrgMapInfoBiz;
import org.fdap.util.BaseAction;

/**
 * 机构地图信息的action
 * @author zhoukuanwei
 *
 */
public class OrgMapInfoAction extends BaseAction {

	private OrgMapInfoBiz orgmapinfoBiz;
	public OrgMapInfoBiz getOrgmapinfoBiz() {
		return orgmapinfoBiz;
	}
	public void setOrgmapinfoBiz(OrgMapInfoBiz orgmapinfoBiz) {
		this.orgmapinfoBiz = orgmapinfoBiz;
	}
	
	public ActionForward toMapInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String str = orgmapinfoBiz.getOrgMapStr();
		request.setAttribute("Str", str);
		return mapping.findForward("mapInfo");
	}
	
}
