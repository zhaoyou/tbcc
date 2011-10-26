package org.fdap.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.fdap.biz.OrgBiz;
import org.fdap.entity.Fdaporg;
import org.fdap.entity.Fdapuser;
import org.fdap.util.BaseAction;

/**
 * 机构的action
 * @author zhaoyou
 *
 */
public class OrgAction extends BaseAction {

	private Logger logger = Logger.getLogger(OrgAction.class);
	
	private OrgBiz orgBiz = null ;

	public OrgBiz getOrgBiz() {
		return orgBiz;
	}
	public void setOrgBiz(OrgBiz orgBiz) {
		this.orgBiz = orgBiz;
	}

	/**
	 * 进入各级机构的地图管理页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toOrg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oid = request.getParameter("oid");
		
		if(oid==null || oid.equals("")){
			logger.warn("传递了非法的org参数!") ;
			throw new Exception("传递了非法的org参数");
		}
		
		Fdaporg org = this.getOrgBiz().getByOid(new Long(oid));
		request.setAttribute("oid", oid);
		if(org.getNodetype()==2){
			request.setAttribute("org", org);
			return mapping.findForward("orglistQ");
		}else{
			List<Long> oids = this.getOrgBiz().getLowerOrg(new Long(oid));
			//保存企业的上级区域的名字
			request.setAttribute("orgName", org.getName());
			//下级没有机构或企业，则进入企业管理页面
			if(oids==null || oids.size()==0){
				return mapping.findForward("leaf");
			}else{
				List<Fdaporg> orglist = this.getOrgBiz().getByParentId(new Long(oid));
				request.setAttribute("orgList", orglist);
				//下级如果是企业，则跳到企业管理页面。
				if(orglist.get(0).getNodetype()==2){
					
					return mapping.findForward("leaf");
				}else{
					request.setAttribute("isshowmap", org.getIsshowmap());
					//获取该机构的下级机构的标识和名称
					request.setAttribute("ids", buildOrg(oids));
					if(org.getIsshowmap()==2){
						//进入机构地图页面
						return new ActionForward("/map_"+oid+".jsp?oid="+oid);
					}else{
						return mapping.findForward("orglistJ");
					}
				}
				
				/*//获取该机构的下级机构的标识和名称
				request.setAttribute("ids", buildOrg(oids));
				if(org.getIsshowmap()==1){
					//进入机构地图页面
					return new ActionForward("/map_"+oid+".jsp?oid="+oid);
				}else{
					List<Fdaporg> orglist = this.getOrgBiz().getByParentId(new Long(oid));
					request.setAttribute("orgList", orglist);
					request.setAttribute("orgName", org.getName());
					//下级如果是企业，则跳到企业管理页面。否则跳到机构管理页面
					if(orglist.get(0).getNodetype()==2){
						return mapping.findForward("leaf");
					}else{
						return mapping.findForward("orglistJ");
					}
				}*/
			}
		}
	}
	
	/**
	 *由机构获取企业 返回到上级机构
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toHigherOrg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String oid = request.getParameter("oid");
		
		if(oid==null || oid.equals("")){
			logger.warn("传递了非法的org参数!") ;
			throw new Exception("传递了非法的org参数");
		}
		
		Fdaporg org = this.orgBiz.getByOid(new Long(oid));
		
		if(org==null){
			logger.warn("当前机构不存在或已经删除!") ;
			throw new Exception("当前机构不存在或已经删除");
		}
		Fdapuser loginuser = (Fdapuser)request.getSession().getAttribute("loginUser");
		if(loginuser.getFdaporg().getNodetype()==2)
			return new ActionForward("/org.do?ope=toOrg&oid="+oid);
		else
			return new ActionForward("/org.do?ope=toOrg&oid="+org.getParentId());
	}
	
	/**
	 * 进入企业地图管理页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toEnter(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String oid = request.getParameter("oid");
		
		if(oid==null || oid.equals("")){
			logger.warn("传递了非法的org参数!") ;
			throw new Exception("传递了非法的org参数");
		}
		
		List<Fdaporg> list = this.getOrgBiz().getByParentId(new Long(oid));
		
		request.setAttribute("orgList", list);
		
		return mapping.findForward("leaf");
	}
	
	
	/**
	 * 机构地图显示和列表显示的切换
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toChangeDisplay(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oid = request.getParameter("oid");
		String isshowmap = request.getParameter("showmap");
		
		if(oid==null || oid.equals("")||isshowmap==null||isshowmap.equals("")){
			logger.warn("传递了非法的org参数!") ;
			throw new Exception("传递了非法的org参数");
		}
		Integer display = Integer.parseInt(isshowmap);
		Fdaporg org = this.getOrgBiz().getByOid(new Long(oid));
		request.setAttribute("oid", oid);
		
			List<Long> oids = this.getOrgBiz().getLowerOrg(new Long(oid));
			//保存企业的上级区域的名字
			request.setAttribute("orgName", org.getName());
			//下级没有机构或企业，则进入企业管理页面
			if(oids==null || oids.size()==0){
				return mapping.findForward("leaf");
			}else{
				request.setAttribute("isshowmap", org.getIsshowmap());
				//获取该机构的下级机构的标识和名称
				request.setAttribute("ids", buildOrg(oids));
				if(display==2){
					//进入机构地图页面
					return new ActionForward("/map_"+oid+".jsp?oid="+oid);
				}else{
					List<Fdaporg> orglist = this.getOrgBiz().getByParentId(new Long(oid));
					request.setAttribute("orgList", orglist);
					return mapping.findForward("orglistJ");
				}
			}
	}
	
	
	/**
	 * 根据机构集合构造一个  id,name;id,name   的字符串
	 * @param list			机构集合
	 * @return
	 */
	private String buildOrg(List<Long> list){
		StringBuffer sb = new StringBuffer();
		
		if(list==null || list.size()==0){
			return sb.toString() ;
		}
		
		for (int i = 0; i < list.size(); i++) {
				
				sb.append(list.get(i));
					if(i!=list.size()-1){
						sb.append(",");
					}
		}
		return sb.toString() ;
	}

			
}
