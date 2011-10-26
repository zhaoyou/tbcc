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
import org.fdap.entity.Fdaplink;
import org.fdap.entity.Fdaplinktype;
import org.fdap.util.BaseAction;
/**
 * 链接配置的Action
 * @author KW_zhou
 *
 */
public class LinkConfigAction extends BaseAction {
	private Logger logger = Logger.getLogger(LinkConfigAction.class) ;
	private LinkConfigBiz linkconfigbiz;
	private LinkTypeBiz linktypebiz;
	
	public LinkTypeBiz getLinktypebiz() {
		return linktypebiz;
	}

	public void setLinktypebiz(LinkTypeBiz linktypebiz) {
		this.linktypebiz = linktypebiz;
	}

	public LinkConfigBiz getLinkconfigbiz() {
		return linkconfigbiz;
	}

	public void setLinkconfigbiz(LinkConfigBiz linkconfigbiz) {
		this.linkconfigbiz = linkconfigbiz;
	}
	
	public ActionForward toLinkConfig(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ltid = request.getParameter("typeid");
		if(ltid==null||ltid.equals("")){
			logger.warn("param is null") ;
			throw new Exception("param is null");
		}
		
		List<Fdaplink> list = linkconfigbiz.getLinkByType(new Long(ltid));
		request.setAttribute("linkList", list);
		request.setAttribute("linktype", linktypebiz.getById(new Long(ltid)));
		return mapping.findForward("linklist");
	}
	
	public ActionForward tolinkAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Fdaplinktype> typelist = linktypebiz.getAllLinktype();
		if(typelist==null||typelist.size()==0){
			logger.warn("系统有误，获取菜单组失败") ;
			return new ActionForward("/linkconfig.do?ope=toLinkConfig");
		}
		request.setAttribute("typelist", typelist);
		request.setAttribute("ope", "dolinkAdd");
		return mapping.findForward("linkadd");
	}
	
	public ActionForward dolinkAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String name = request.getParameter("linkname");
		String url = request.getParameter("linkurl");
		//菜单组标识
		String typeid = request.getParameter("ltid");
		
		if(name==null||name.equals("")||url==null||url.equals("")||typeid==null||typeid.equals("")){
			logger.warn("param is null") ;
			throw new Exception("param is null");
		}
		
		Long ltid = new Long(typeid);
		
		List<Fdaplink> list = linkconfigbiz.getAllLink();
		if(list!=null&&list.size()!=0){
			for(Fdaplink link : list ){
				if(name.equals(link.getName())){
					request.setAttribute("tip", "已经存在菜单'"+name+"'");
					return new ActionForward("/linkconfig.do?ope=tolinkAdd");
				}
			}
		}
		
		if(linkconfigbiz.isBeyond(ltid)){
			String ltname = linktypebiz.getById(ltid).getLtname();
			request.setAttribute("tip", ltname+"的菜单数已满4个了");
			return new ActionForward("/linkconfig.do?ope=tolinkAdd");
		}
		
		
		Fdaplink flink = new Fdaplink();
		flink.setName(name);
		flink.setUrl(url);
		flink.setLtid(ltid);
		//保存菜单信息
		if(!this.getLinkconfigbiz().addLink(flink)){
			request.setAttribute("tip", "添加失败，尽快联系开发人员");
			return new ActionForward("/linkconfig.do?ope=tolinkAdd");
		}
		return new ActionForward("/linkconfig.do?ope=toLinkConfig");
	}
	
	public ActionForward tolinkUp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String lid = request.getParameter("linkid");
		List<Fdaplinktype> typelist = linktypebiz.getAllLinktype();
		
		if(lid==null||lid.equals("")||typelist==null||typelist.size()==0){
			logger.warn("param is null") ;
			throw new Exception("param is null");
		}
		Fdaplink link = this.getLinkconfigbiz().getById(new Long(lid));
		request.setAttribute("typelist", typelist);
		request.setAttribute("linkinfo", link);
//		System.out.println(typelist.size()+" "+link+" "+lid);
		request.setAttribute("ope", "dolinkUp");
		return mapping.findForward("linkadd");
	}
	
	
	public ActionForward dolinkUp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String linkid = request.getParameter("linkid");
		String name = request.getParameter("linkname");
		String url = request.getParameter("linkurl");
		
		//菜单组标识
		String typeid = request.getParameter("ltid");
		
		if(linkid==null||linkid.equals("")||name==null||name.equals("")||url==null||url.equals("")
				||typeid==null||typeid.equals("")){
			logger.warn("param is null") ;
			throw new Exception("param is null");
		}
		
		Long ltid = Long.valueOf(typeid);
		Long lid = Long.valueOf(linkid);
		Fdaplink flink = this.getLinkconfigbiz().getById(lid);
//		System.out.println(lid==flink.getLid());
		if(!name.equals(flink.getName())){
			List<Fdaplink> list = linkconfigbiz.getAllLink();
			if(list!=null&&list.size()!=0){
				for(Fdaplink link : list ){
					if(name.equals(link.getName())&&link.getLid().longValue()!=lid){
						request.setAttribute("tip", "已经存在菜单'"+name+"'");
						return new ActionForward("/linkconfig.do?ope=tolinkUp&linkid="+lid);
					}
				}
			}
		}
		if(!typeid.equals(flink.getLtid().toString())){
			if(linkconfigbiz.isBeyond(ltid)){
				String ltname = linktypebiz.getById(ltid).getLtname();
				request.setAttribute("tip", ltname+"的菜单数已满4个了");
				return new ActionForward("/linkconfig.do?ope=tolinkUp&linkid="+lid);
			}
		}
		
		flink.setName(name);
		flink.setUrl(url);
		flink.setLtid(ltid);
		//保存菜单信息
		if(!linkconfigbiz.updateLink(flink)){
			request.setAttribute("tip", "修改失败，尽快联系开发人员");
			return new ActionForward("/linkconfig.do?ope=tolinkUp&linkid="+lid);
		}
		
		return new ActionForward("/linkconfig.do?ope=toLinkConfig");
	}
	
	public ActionForward dolinkDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String delStr = request.getParameter("delStr");
		
		if(delStr==null||delStr==""){
			logger.warn("param is null") ;
			throw new Exception("param is null");
		}
		String delLinks[] = delStr.split(",");
		
		if(!linkconfigbiz.DelLink(delLinks)){
			request.setAttribute("tip","<font color='red'>删除失败，请联系开发人员</font>");
		}
		return new ActionForward("/linkconfig.do?ope=toLinkConfig");
	}
	
	
//	public ActionForward 
}
