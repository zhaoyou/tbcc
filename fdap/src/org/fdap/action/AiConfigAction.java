package org.fdap.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.fdap.biz.AiConfigBiz;
import org.fdap.biz.RefConfigBiz;
import org.fdap.biz.StoreConfigBiz;
import org.fdap.entity.Fdapaiinfo;
import org.fdap.entity.Fdapref;
import org.fdap.entity.Fdapstoretype;
import org.fdap.util.BaseAction;

/**
 * 探头配置的action
 * @author zhaoyou
 *
 */
public class AiConfigAction extends BaseAction {
	private Logger logger = Logger.getLogger(RefConfigAction.class) ;
	private RefConfigBiz refconfigbiz;
	private AiConfigBiz aiconfigbiz;
	private StoreConfigBiz storeconfigbiz;

	public AiConfigBiz getAiconfigbiz() {
		return aiconfigbiz;
	}

	public void setAiconfigbiz(AiConfigBiz aiconfigbiz) {
		this.aiconfigbiz = aiconfigbiz;
	}
	
	public RefConfigBiz getRefconfigbiz() {
		return refconfigbiz;
	}

	public void setRefconfigbiz(RefConfigBiz refconfigbiz) {
		this.refconfigbiz = refconfigbiz;
	}
	
	public StoreConfigBiz getStoreconfigbiz() {
		return storeconfigbiz;
	}

	public void setStoreconfigbiz(StoreConfigBiz storeconfigbiz) {
		this.storeconfigbiz = storeconfigbiz;
	}

	/**
	 * 跳转到探头列表页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toAiList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oid = request.getParameter("oid");
		String refId = request.getParameter("refId_to_ai");
		String projectId = request.getParameter("projectId_to_ref");
		if(oid==null||oid.equals("")||refId==null||refId.equals("")||projectId==null||projectId.equals("")){
			logger.warn("param is null") ;
			throw new Exception("param is null");
		}
		request.setAttribute("ref", this.getRefconfigbiz().getById(new Long(refId)));
		request.setAttribute("aiList", this.getAiconfigbiz().getByref(new Long(refId)));
		return mapping.findForward("aiList");
	}

	/**
	 * 跳转到增加探头页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toAiAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oid = request.getParameter("oid");
		String refId = request.getParameter("refId_to_ai");
		String projectId = request.getParameter("projectId_to_ref");
		if(oid==null||oid.equals("")||refId==null||refId.equals("")||projectId==null||projectId.equals("")){
			logger.warn("param is null") ;
			throw new Exception("param is null");
		}
		Fdapref ref = this.getRefconfigbiz().getById(new Long(refId));
		request.setAttribute("ref", ref);
		request.setAttribute("storetype", ref.getFdapstoretype());
		request.setAttribute("storetypeStr", buildTypeStr(ref.getFdapstoretype()));
		
		request.setAttribute("ope", "doAiAdd");
		
		return mapping.findForward("aiAdd");
	}

	/**
	 * 增加探头信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doAiAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oid = request.getParameter("oid");
		String refId = request.getParameter("refId_to_ai");
		String projectId = request.getParameter("projectId_to_ref");
		
		String name = request.getParameter("name");
		String selftype = request.getParameter("selftype");
		String lowerlimit = request.getParameter("lowerlimit");
		String lowerdelay = request.getParameter("lowerdelay");
		//String minlowerlimit = request.getParameter("minlowerlimit");
		//String minlowerdelay = request.getParameter("minlowerdelay");
		String highlimit = request.getParameter("highlimit");
		String highdelay = request.getParameter("highdelay");
		//String maxhighlimit = request.getParameter("maxhighlimit");
		//String maxhighdelay = request.getParameter("maxhighdelay");
		
		String reid = request.getParameter("reid");
		String reidSelect = request.getParameter("reidSelect");
		
		if(oid==null||oid.equals("")||refId==null||refId.equals("")||projectId==null||projectId.equals("")||name==null||name.equals("")
				||selftype==null||selftype.equals("")||lowerlimit==null||lowerlimit.equals("")||lowerdelay==null||lowerdelay.equals("")
				||highlimit==null||highlimit.equals("")||highdelay==null||highdelay.equals("")||reidSelect==null||reidSelect.equals("")||
				(reidSelect.equals("2")&&(reid==null||reid.equals("")))){
			logger.warn("param is null") ;
			throw new Exception("param is null");
		}
		/*if(Integer.parseInt(selftype)==0){
			if(minlowerlimit==null||minlowerlimit==""||minlowerdelay==null||minlowerdelay.equals("")||maxhighlimit==null||maxhighlimit.equals("")
					||maxhighdelay==null||maxhighdelay.equals("")){
				logger.warn("param is null") ;
				throw new Exception("param is null");
			}
		}*/
		
		List<Fdapaiinfo> aiList = aiconfigbiz.getByref(new Long(refId));
		Fdapref ref = this.getRefconfigbiz().getById(new Long(refId));
		
		for(Fdapaiinfo fa : aiList){
			if(name.equals(fa.getName())){
				//已经存在该名称的探头
				request.setAttribute("tip", "该冷库下已存在探头名称'"+name+"'");
				return new ActionForward("/aiconfig.do?ope=toAiAdd&oid="+oid+"&refId_to_ai="+refId+"&projectId_to_ref="+projectId);
			}
		}
		
		Fdapaiinfo aiinfo = new Fdapaiinfo();
		aiinfo.setFdapref(ref);
		aiinfo.setName(name);
		aiinfo.setSelftype(Integer.parseInt(selftype));
		aiinfo.setLowerlimit(Double.valueOf(lowerlimit));
		aiinfo.setLowerdelay(Integer.parseInt(lowerdelay));
		aiinfo.setHighlimit(Double.valueOf(highlimit));
		aiinfo.setHighdelay(Integer.parseInt(highdelay));
		
		/*if(Integer.parseInt(selftype)==0){
			aiinfo.setMinlowerlimit(Double.valueOf(minlowerlimit));
			aiinfo.setMinlowerdelay(Integer.parseInt(minlowerdelay));
			aiinfo.setMaxhighlimit(Double.valueOf(maxhighlimit));
			aiinfo.setMaxhighdelay(Integer.parseInt(maxhighdelay));
		}*/
		
		if(ref.getFdapproject().getType()==1){
			//冷库给location赋为空
			aiinfo.setLocation(null);
		}
		else{
			aiinfo.setLocation(1);
		}
		
		if(reidSelect.equals("1")){
			Integer maxreid = this.getAiconfigbiz().getMaxReid(new Long(oid),0);
			if(maxreid==null || maxreid.equals("")||maxreid==0){
				aiinfo.setReid(1);
			}else{
				aiinfo.setReid(maxreid+1);
			}
		}else if(reidSelect.equals("2")){
			List<Fdapaiinfo> orgaiList = this.getAiconfigbiz().getAiByOid(new Long(oid));
			Integer aireid = Integer.parseInt(reid);
			if(orgaiList!=null&&orgaiList.size()!=0){
				for(Fdapaiinfo fai:orgaiList){
					//System.out.println(aireid+" "+fai.getReid()+" "+(aireid==fai.getReid().intValue()));
					if(aireid==fai.getReid().intValue()){
						//已经存在该探头编号
						request.setAttribute("tip", "该企业下已存在探头编号'"+aireid+"'");
						return new ActionForward("/aiconfig.do?ope=toAiAdd&oid="+oid+"&refId_to_ai="+refId+"&projectId_to_ref="+projectId);
					}
				}
			}
			aiinfo.setReid(aireid);
		}
		
		//保存探头信息
		if(!aiconfigbiz.addAi(aiinfo)){
			request.setAttribute("tip", "添加失败，尽快联系开发人员");
			return new ActionForward("/aiconfig.do?ope=toAiAdd&oid="+oid+"&refId_to_ai="+refId+"&projectId_to_ref="+projectId);
		}
		
		return new ActionForward("/aiconfig.do?ope=toAiList&oid="+oid+"&refId_to_ai="+refId+"&projectId_to_ref="+projectId);
	}
	
	
	/**
	 * 删除某冷库下的几个探头
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doAiDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oid = request.getParameter("oid");
		String refId = request.getParameter("refId_to_ai");
		String projectId = request.getParameter("projectId_to_ref");
		
		String delStr = request.getParameter("delStr");
		if(oid==null||oid.equals("")||refId==null||refId.equals("")||projectId==null||projectId.equals("")
				||delStr==null||delStr.equals("")){
			logger.warn("param is null") ;
			throw new Exception("param is null");
		}
		
		String delAi[] = delStr.split(",");
		/*for(int i=0;i<delAi.length;i++){
			System.out.println(delAi[i]);
		}*/
		
		if(!aiconfigbiz.DelAi(delAi)){
			request.setAttribute("tip","<font color='red'>删除失败，请联系开发人员</font>");
		}
		
		request.setAttribute("ref", this.getRefconfigbiz().getById(new Long(refId)));
		request.setAttribute("aiList", this.getAiconfigbiz().getByref(new Long(refId)));
		return mapping.findForward("aiList");
	}
	
	/**
	 * 跳转到修改探头页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toAiUp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oid = request.getParameter("oid");
		String refId = request.getParameter("refId_to_ai");
		String projectId = request.getParameter("projectId_to_ref");
		
		String aiguid = request.getParameter("aiguid");
		
		if(oid==null||oid.equals("")||refId==null||refId.equals("")||projectId==null||projectId.equals("")
				||aiguid==null||aiguid.equals("")){
			logger.warn("param is null") ;
			throw new Exception("param is null");
		}
		
		Fdapaiinfo aiinfo = aiconfigbiz.getByAiguid(aiguid);
		
		Fdapref ref = this.getRefconfigbiz().getById(new Long(refId));
		request.setAttribute("ref", ref);
		request.setAttribute("aiinfo", aiinfo);
		
		request.setAttribute("ope", "doAiUp");
		
		return mapping.findForward("aiAdd");
	}

	/**
	 * 修改探头信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doAiUp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oid = request.getParameter("oid");
		String refId = request.getParameter("refId_to_ai");
		String projectId = request.getParameter("projectId_to_ref");
		
		String aiguid = request.getParameter("aiguid");
		
		String name = request.getParameter("name");
		String lowerlimit = request.getParameter("lowerlimit");
		String lowerdelay = request.getParameter("lowerdelay");
		String highlimit = request.getParameter("highlimit");
		String highdelay = request.getParameter("highdelay");
		
		String reid = request.getParameter("reid");
		String reidSelect = request.getParameter("reidSelect");
		
		if(oid==null||oid.equals("")||refId==null||refId.equals("")||projectId==null||projectId.equals("")||name==null||name.equals("")
				||aiguid==null||aiguid.equals("")||lowerlimit==null||lowerlimit.equals("")||lowerdelay==null||lowerdelay.equals("")
				||highlimit==null||highlimit.equals("")||highdelay==null||highdelay.equals("")||reidSelect==null||reidSelect.equals("")||
				(reidSelect.equals("2")&&(reid==null||reid.equals("")))){
			logger.warn("param is null") ;
			throw new Exception("param is null");
		}
		
		Fdapaiinfo aiinfo = aiconfigbiz.getByAiguid(aiguid);
		
		if(!name.equals(aiinfo.getName())){
			List<Fdapaiinfo> aiList = aiconfigbiz.getByref(new Long(refId));
			
			for(Fdapaiinfo fa : aiList){
				if(name.equals(fa.getName())){
					//已经存在该名称的探头
					request.setAttribute("tip", "该冷库下已存在探头名称'"+name+"'");
					return new ActionForward("/aiconfig.do?ope=toAiUp&oid="+oid+"&refId_to_ai="+refId+"&projectId_to_ref="+projectId+"&aiguid="+aiguid);
				}
			}
		}
		
		
		
		/*if(aiinfo.getSelftype()==0){
			String minlowerlimit = request.getParameter("minlowerlimit");
			String minlowerdelay = request.getParameter("minlowerdelay");
			String maxhighlimit = request.getParameter("maxhighlimit");
			String maxhighdelay = request.getParameter("maxhighdelay");
			if(minlowerlimit==null||minlowerlimit==""||minlowerdelay==null||minlowerdelay.equals("")||maxhighlimit==null||maxhighlimit.equals("")
					||maxhighdelay==null||maxhighdelay.equals("")){
				logger.warn("param is null") ;
				throw new Exception("param is null");
			}
			aiinfo.setMinlowerlimit(Double.valueOf(minlowerlimit));
			aiinfo.setMinlowerdelay(Integer.parseInt(minlowerdelay));
			aiinfo.setMaxhighlimit(Double.valueOf(maxhighlimit));
			aiinfo.setMaxhighdelay(Integer.parseInt(maxhighdelay));
		}*/
		
		aiinfo.setName(name);
		aiinfo.setLowerlimit(Double.valueOf(lowerlimit));
		aiinfo.setLowerdelay(Integer.parseInt(lowerdelay));
		aiinfo.setHighlimit(Double.valueOf(highlimit));
		aiinfo.setHighdelay(Integer.parseInt(highdelay));
		
		
		if(reidSelect.equals("1")){
			Integer maxreid = this.getAiconfigbiz().getMaxReid(new Long(oid),aiinfo.getReid());
			if(maxreid==null || maxreid.equals("")||maxreid==0){
				aiinfo.setReid(1);
			}else{
				aiinfo.setReid(maxreid+1);
			}
		}else{
			Integer aireid = Integer.parseInt(reid);
			//System.out.println(aireid+" "+aiinfo.getReid()+" "+(aireid.intValue()!=aiinfo.getReid().intValue()));
			if(aireid.intValue()!=aiinfo.getReid().intValue()){
				List<Fdapaiinfo> orgaiList = this.getAiconfigbiz().getAiByOid(new Long(oid));
				if(orgaiList!=null&&orgaiList.size()!=0){
					for(Fdapaiinfo fai : orgaiList){
						if(fai.getReid().intValue()==aireid){
							//已经存在该探头编号
							request.setAttribute("tip", "该企业下已存在探头编号'"+aireid+"'");
							return new ActionForward("/aiconfig.do?ope=toAiUp&oid="+oid+"&refId_to_ai="+refId+"&projectId_to_ref="+projectId+"&aiguid="+aiguid);
						}
					}
				}
			}
			aiinfo.setReid(aireid);
		}
		
		//保存探头信息
		if(!aiconfigbiz.updateAi(aiinfo)){
			request.setAttribute("tip", "修改失败，尽快联系开发人员");
			return new ActionForward("/aiconfig.do?ope=toAiUp&oid="+oid+"&refId_to_ai="+refId+"&projectId_to_ref="+projectId+"&aiguid="+aiguid);
		}
		
		return new ActionForward("/aiconfig.do?ope=toAiList&oid="+oid+"&refId_to_ai="+refId+"&projectId_to_ref="+projectId);
	}
	
	/**
	 * 构造存储类型的温湿度标准值的字符串(方便传参)
	 * @param 	storetype	存储类型对象
	 * @return
	 */
	public String buildTypeStr(Fdapstoretype storetype){
		StringBuffer sb = new StringBuffer("");
		sb.append(storetype.getTemplowerlimit()+","+storetype.getTemplowerdelay()+","
				+storetype.getTemphighlimit()+","+storetype.getTemphighdelay()+","
				+storetype.getHumlowerlimit()+","+storetype.getHumlowerdelay()+","
				+storetype.getHumhighlimit()+","+storetype.getHumhighdelay());
		/*sb.append(storetype.getTemplowerlimit()+","+storetype.getTemplowerdelay()+","+storetype.getTempminlowerlimit()+","
				+storetype.getTempminlowerdelay()+","+storetype.getTemphighlimit()+","+storetype.getTemphighdelay()+","
				+storetype.getTempmaxhighlimit()+","+storetype.getTempmaxhighdelay()+","+storetype.getHumlowerlimit()+","
				+storetype.getHumlowerdelay()+","+storetype.getHumhighlimit()+","+storetype.getHumhighdelay());*/
		return sb.toString();
	}
	
}
