package org.fdap.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.fdap.biz.ConfigInfoBiz;
import org.fdap.biz.OrgBiz;
import org.fdap.entity.FdapConfigInfo;
import org.fdap.entity.Fdaporg;
import org.fdap.util.BaseAction;

public class ConfigInfoAction extends BaseAction {
	private Logger logger = Logger.getLogger(ConfigInfoAction.class) ;
	private ConfigInfoBiz configinfobiz;
	private OrgBiz orgbiz;
	public ConfigInfoBiz getConfiginfobiz() {
		return configinfobiz;
	}

	public void setConfiginfobiz(ConfigInfoBiz configinfobiz) {
		this.configinfobiz = configinfobiz;
	}
	
	public OrgBiz getOrgbiz() {
		return orgbiz;
	}

	public void setOrgbiz(OrgBiz orgbiz) {
		this.orgbiz = orgbiz;
	}

	public ActionForward toGetConfig(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String oidstr = request.getParameter("oid");
		List<Fdaporg> orgList = configinfobiz.getAllOrg();
		if(orgList==null||orgList.size()==0){
			request.setAttribute("tip", "请确认是否配置了企业");
			return mapping.findForward("config");
		}
		Long oid;
		if(oidstr==null||oidstr.equals("")){
			oid = orgList.get(0).getOid();
		}else{
			oid = Long.parseLong(oidstr);
		}
		request.setAttribute("orglist", orgList);
		return new ActionForward("/configinfo.do?ope=doGetConfig&oid="+oid);
	}
	
	public ActionForward doGetConfig(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oidstr = request.getParameter("oid");
		if(oidstr==null||oidstr.equals("")){
			logger.warn("param is null");
			throw new Exception("param is null");
		}
		Long oid = Long.parseLong(oidstr);
		//获取oid对应企业下仓库所有的配置信息集合
		List<FdapConfigInfo> reflist = configinfobiz.getConfigInfo(oid, 1);
		//获取oid对应企业下车载所有的配置信息集合
		List<FdapConfigInfo> carlist = configinfobiz.getConfigInfo(oid, 2);
		
		Fdaporg org = orgbiz.getByOid(oid);
		//复制到EXCEL内容
		String str = buildStr(org, reflist, carlist);
		
		request.setAttribute("refconfiglist", reflist);
		request.setAttribute("carconfiglist", carlist);
		request.setAttribute("str", str);
		request.setAttribute("org", org);
		
		return mapping.findForward("config");
	}
	
	
	public String buildStr(Fdaporg org,List<FdapConfigInfo> reflist,List<FdapConfigInfo> carlist){
		StringBuffer sb=new StringBuffer("");
		sb.append("企业名称\t"+org.getName()+"\t"+"企业授权码\t"+org.getAuthcode().getCode()+"@@");
		sb.append("冷库名称\t探头名称\t编号@");
		for(FdapConfigInfo ref:reflist){
			sb.append(ref.getRefName()+"\t"+ref.getAiName()+"\t"+ref.getReid()+"@");
		}
		sb.append("@");
		sb.append("车载名称\t探头名称\t编号\t工程编号@");
		for(FdapConfigInfo car:carlist){
			sb.append(car.getRefName()+"\t"+car.getAiName()+"\t"+car.getReid()+"\t"+car.getProjectNo()+"@");
		}
		return sb.toString();
	}
}
