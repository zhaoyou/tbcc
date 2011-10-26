package org.fdap.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.fdap.biz.OrgBiz;
import org.fdap.biz.RefRealBiz;
import org.fdap.entity.Fdapaiinfo;
import org.fdap.entity.Fdaporg;
import org.fdap.entity.Fdapproject;
//import org.fdap.entity.Fdaprealalarm;
import org.fdap.entity.Fdapref;
import org.fdap.entity.Fdaprefrealdata;
import org.fdap.util.BaseAction;

/**
 * 仓库实时数据action
 * @author zhaoyou
 *
 */
public class RefRealAction extends BaseAction {

	
	private Logger logger = Logger.getLogger(RefRealAction.class);
	
	private RefRealBiz refRealBiz = null ;

	private OrgBiz orgBiz = null ;
	
	
	
	public OrgBiz getOrgBiz() {
		return orgBiz;
	}

	public void setOrgBiz(OrgBiz orgBiz) {
		this.orgBiz = orgBiz;
	}

	public RefRealBiz getRefRealBiz() {
		return refRealBiz;
	}

	public void setRefRealBiz(RefRealBiz refRealBiz) {
		this.refRealBiz = refRealBiz;
	}

	/**
	 * 跳转到仓库实时数据页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toRealRef(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		String oid = request.getParameter("oid") ;
		
		//验证参数
		if(oid==null || oid.equals("")){
			logger.warn("传递了非法的oid参数");
			throw new Exception("传递了非法的oid参数");
		}
		
		//保存企业的信息，用于显示名称
		Fdaporg fdaporg = this.getOrgBiz().getByOid(new Long(oid));
		request.setAttribute("fdaporg", fdaporg);
		
		
		//保存所有的仓库工程参数
		List<Fdapproject> list = this.getRefRealBiz().getRefProjectByOid(new Long(oid));
		String projectIds = buildStr(list);
		request.setAttribute("projectIds", projectIds);
		
		//获取所有冷库的数量用以动态调整页面高度
		List<Fdapref > refList = refRealBiz.getRefByProjectIds(projectIds) ;
		if(refList!=null && refList.size()==0){
			request.setAttribute("refsize", refList.size());
		}
		
		return mapping.findForward("realdata");
	}
	
	/**
	 * 更新仓库实时数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doRealRef(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter() ;
		
		String projectIds = request.getParameter("p");
		
		if(projectIds==null || projectIds.equals("")){
			out.print("当前没有发现仓库....");
			return null ;
		}
		
		//获取冷库
		List<Fdapref > refList = refRealBiz.getRefByProjectIds(projectIds) ;
		
		if(refList==null || refList.size()==0){
			out.print("当前没有发现冷库...");
			return null ;
		}
		
		//获取冷库对应的探头
		List<Fdapaiinfo> aiList = refRealBiz.getAiByProjectIds(projectIds);
		
		if(aiList==null || aiList.size()==0){
			out.print("当前没有配置探头...");
			return null ;
		}
		
		//获取探头对应的实时数据
		List<Fdaprefrealdata> realList = refRealBiz.getRealByProjectIds(projectIds);
		
		//需要把探头对应的实时数据，放入的map结构中
		Map<String,Double> map = new HashMap<String,Double>();
		for (Fdaprefrealdata fdaprefrealdata : realList) {
			map.put(fdaprefrealdata.getFdapaiinfo().getAiguid(), fdaprefrealdata.getData());
		}
		
		/**
		 *  <tr class='altrow' >
          <td width='58' height='24'>仓库名称</td>
          <td width='82'>仓库是否启用</td>
          <td width='54'>T1</td>
          <td width='59'>T2</td>
          <td width='67'>T3</td>
          <td width='58'>T4</td>
          <td width='65'>T5</td>
          <td width='65'>T6</td>
          <td width='56'>T7</td>
          <td width='70'>T8</td>
          <td width='72'>T9</td>
          <td width='66'>RH10</td>
          <td width='59'>RH11</td>
          <td width='49'>RH12</td>
        </tr>
        <tr>
          <td>F1C1</td>
          <td><img src='images/index/web_icon_006.gif' width='16' height='16' /></td>
          <td><span id='t1'>28.3℃</span></td>
          <td><span id='t2'>27.3℃</span></td>
          <td><span id='t3'>28.3℃</span></td>
          <td><span id='t4'>29.3℃</span></td>
          <td><span id='t5'>12.3℃</span></td>
          <td><span id='t6'>28.3℃</span></td>
          <td><span id='t7'>28.3℃</span></td>
          <td><span id='t8'>28.3℃</span></td>
          <td><span id='t9'>28.3℃</span></td>
          <td><span id='r0'>87.0%</span></td>
          <td><span id='r1'>86.0%</span></td>
          <td><span id='r2'>81.0%</span></td>
        </tr>
		 */
		//循环遍历冷库
		for (Fdapref fdapref : refList) {
			
			//标题行
			out.print("<table><tr class='altrow' >");
			out.print("<td width='88' height='24'>仓库名称</td>");
			out.print("<td width='72'>仓库是否启用</td>");
			out.print("<td width='65'>是否报警</td>");
			
			List<String> idList = new ArrayList<String>();
			
			for (Fdapaiinfo fdapaiinfo : aiList) {
				if(fdapaiinfo.getFdapref().getRefId().equals(fdapref.getRefId())){
					out.print("<td width='65'>"+fdapaiinfo.getName()+"</td>");
					idList.add(fdapaiinfo.getAiguid());
				}
			}
			
			out.print("</tr>");
			
			//内容行
			out.print("<tr><td>"+fdapref.getName()+"</td>");
			
			if(fdapref.getIsactive().equals(0)){
				out.print("<td><img src='images/index/web_icon_006.gif' width='16' height='16' alt='已启用' title='已启用' /></td>");
			}else{
				out.print(" <td><img src='images/index/web_icon_009.gif' width='16' height='16' alt='已停用' title='已停用'  /></td>");
			}
			boolean isalarm = false;
			//用来判断当前冷库所有探头是否全都没有实时数据
			int count = 0;
			for(String str : idList){
				if(!map.containsKey(str)){
					count++;
				}else if(map.get(str)==null||map.get(str)==-300){
					count++;
				}else{
					for(Fdaprefrealdata frf : realList){
						if(str.equals(frf.getFdapaiinfo().getAiguid())){
							if(frf.getIsalarm()==0){
								isalarm = true;
							}
							break;
						}
					}
				}
				if(isalarm) break;
			}
			
			if(count==idList.size()){
				out.print("<td>----</td>");
			}else{
				if(!isalarm){
					out.print("<td><img src='images/index/msie_doc2.gif' width='18' height='16' alt='正常状态'  title='正常状态' /></td>");
				}else{
					out.print("<td><img src='images/index/msie_doc_sel.gif' width='18' height='16' alt='报警状态' title='报警状态' /></td>");
				}
			}
			
			for (String string : idList) {
				if(!map.containsKey(string)){
					out.print("<td>----</td>");
				}else{
					Double v = map.get(string);
					if(v==null || v==-300){
						out.print("<td>----</td>");
					}else{
						out.print("<td>"+v+"</td>");
					}
				}
			}
			out.print("</tr></table>");
			//结束内容行
			
		}
		
		return null ;
	}
	
	
	/**
	 * 封装工程标识Id字符串 1001,1002,1003
	 * @param list
	 * @return
	 */
	private String buildStr(List<Fdapproject> list){
		StringBuffer sb = new StringBuffer();
		
		if(list==null || list.size()==0)
			return sb.toString() ;
		
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i).getProjectid());
			if(i!=list.size()-1)
				sb.append(",");
		}
		return sb.toString();
	}
		
			
}
