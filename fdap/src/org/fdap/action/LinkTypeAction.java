package org.fdap.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.fdap.biz.LinkTypeBiz;
import org.fdap.entity.Fdaplinktype;
import org.fdap.util.BaseAction;
/**
 * 菜单组配置的Action
 * @author KW_zhou
 *
 */
public class LinkTypeAction extends BaseAction {
	private Logger logger = Logger.getLogger(LinkTypeAction.class) ;
	private LinkTypeBiz linktypebiz;
	public LinkTypeBiz getLinktypebiz() {
		return linktypebiz;
	}

	public void setLinktypebiz(LinkTypeBiz linktypebiz) {
		this.linktypebiz = linktypebiz;
	}

	public ActionForward toLinktype(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Fdaplinktype> list = this.getLinktypebiz().getAllLinktype();
		request.setAttribute("typeList", list);
		return mapping.findForward("typelist");
	}
	
	public ActionForward toTypeAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("ope", "doTypeAdd");
		return mapping.findForward("typeadd");
	}
	
	public ActionForward doTypeAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ltname = request.getParameter("ltname");
		if(ltname==null||ltname.equals("")){
			logger.warn("param is null") ;
			return new ActionForward("/linktype.do?ope=toTypeAdd");
		}
		List<Fdaplinktype> list = this.getLinktypebiz().getAllLinktype();
		if(list!=null&&list.size()!=0){
			for(Fdaplinktype link : list ){
				if(ltname.equals(link.getLtname())){
					request.setAttribute("tip", "已经存在菜单名称'"+ltname+"'");
					return new ActionForward("/linktype.do?ope=toTypeAdd");
				}
			}
		}
		
		Fdaplinktype flink = new Fdaplinktype();
		flink.setLtname(ltname);
		//保存菜单信息
		if(!this.getLinktypebiz().addLinktype(flink)){
			request.setAttribute("tip", "添加失败，尽快联系开发人员");
			return new ActionForward("/linktype.do?ope=toTypeAdd");
		}
		return new ActionForward("/linktype.do?ope=toLinktype");
	}
	
	public ActionForward toTypeUp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ltid = request.getParameter("typeid");
		
		if(ltid==null||ltid.equals("")){
			logger.warn("param is null") ;
			return new ActionForward("/linktype.do?ope=toLinktype");
		}
		Fdaplinktype linktype = this.getLinktypebiz().getById(new Long(ltid));
		request.setAttribute("linktype", linktype);
		request.setAttribute("ope", "doTypeUp");
		return mapping.findForward("typeadd");
	}
	
	public ActionForward doTypeUp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String linkid = request.getParameter("typeid");
		String ltname = request.getParameter("ltname");
		if(linkid==null||linkid.equals("")||ltname==null||ltname.equals("")){
			logger.warn("param is null") ;
			throw new Exception("param is null");
		}
		
		Long ltid = Long.valueOf(linkid);
		Fdaplinktype flink = this.getLinktypebiz().getById(ltid);
		//System.out.println(lid==flink.getLid());
		if(!ltname.equals(flink.getLtname())){
			List<Fdaplinktype> list = linktypebiz.getAllLinktype();
			if(list!=null&&list.size()!=0){
				for(Fdaplinktype linktype : list ){
					if(ltname.equals(linktype.getLtname())&&linktype.getLtid().longValue()!=ltid){
						request.setAttribute("tip", "已经存在菜单名称'"+ltname+"'");
						return new ActionForward("/linktype.do?ope=toTypeUp&typeid="+ltid);
					}
				}
			}
		}
		flink.setLtname(ltname);
		//保存菜单信息
		if(!linktypebiz.updateLinktype(flink)){
			request.setAttribute("tip", "修改失败，尽快联系开发人员");
			return new ActionForward("/linktype.do?ope=toTypeUp&typeid="+ltid);
		}
		
		return new ActionForward("/linktype.do?ope=toLinktype");
	}
	
	public ActionForward doTypeDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String delStr = request.getParameter("delStr");
		
		if(delStr==null||delStr==""){
			logger.warn("param is null") ;
			throw new Exception("param is null");
		}
		String delTypes[] = delStr.split(",");
		
		if(!linktypebiz.DelLinktype(delTypes)){
			request.setAttribute("tip","<font color='red'>删除失败，请联系开发人员</font>");
		}
		return new ActionForward("/linktype.do?ope=toLinktype");
	}
	
}
