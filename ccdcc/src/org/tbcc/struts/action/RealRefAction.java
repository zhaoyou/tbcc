package org.tbcc.struts.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.tbcc.biz.AiInfoBiz;
import org.tbcc.biz.IGetImageEachControlAllDatas;
import org.tbcc.biz.ProjectBiz;
import org.tbcc.biz.RealRefBiz;
import org.tbcc.biz.RefInfoBiz;
import org.tbcc.entity.TbccAiInfo;
import org.tbcc.entity.TbccBaseRealRef;
import org.tbcc.entity.TbccBaseUser;
import org.tbcc.entity.TbccBranchType;
import org.tbcc.entity.TbccPrjType;
import org.tbcc.entity.TbccProjectImages;
import org.tbcc.entity.TbccRefInfo;
import org.tbcc.util.BaseAction;

/**
 * 冷库实时action 接受 realref.do 请求 Extends BaseAction
 * @author Administrator
 *
 */
public class RealRefAction extends BaseAction {

	
	/**
	 * 由spirng注入的三个业务接口
	 */
	private RefInfoBiz refinfoBiz = null ;
	
	private AiInfoBiz aiInfoBiz = null ;
	
	private RealRefBiz realrefBiz = null ;
	
	private IGetImageEachControlAllDatas imageBiz = null ;
	
	private ProjectBiz	projectBiz = null ;
	
	private Logger logger = Logger.getLogger(RealRefAction.class);

	/**
	 * 由spring 容器注入
	 * @param imageBiz
	 */
	public void setImageBiz(IGetImageEachControlAllDatas imageBiz) {
		this.imageBiz = imageBiz;
	}

	public void setRefinfoBiz(RefInfoBiz refinfoBiz) {
		this.refinfoBiz = refinfoBiz;
	}

	public void setAiInfoBiz(AiInfoBiz aiInfoBiz) {
		this.aiInfoBiz = aiInfoBiz;
	}

	public void setRealrefBiz(RealRefBiz realrefBiz) {
		this.realrefBiz = realrefBiz;
	}
	
	public void setProjectBiz(ProjectBiz projectBiz) {
		this.projectBiz = projectBiz;
	}

			/**
			 * 转到默认冷库系统实时数据的页面
			 * @param mapping
			 * @param form
			 * @param request
			 * @param response
			 * @return
			 * @throws Exception
			 */
			public ActionForward toDefaultRefSys(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				String branchId = request.getParameter("branchId") ;
				
				if(branchId==null|| branchId.equals("")){
					request.setAttribute("errorMsg", "参数为空或非法!");
					logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toDefaultRefSys)参数为空或非法!");
					throw new Exception("参数非法..");
				}
				
				//获取所有的冷库工程列表，保存至session中,此时每次进入都需要重新查询
				//if(request.getSession().getAttribute("refPrjList")==null){
					List<TbccPrjType> refprojectList = projectBiz.getRefProjListBybId(new Long(branchId));
					request.getSession().setAttribute("refPrjList", refprojectList);
				//}
					
					
					
					//获取楼层信息
					List<Map<String, Object>> maplist = realrefBiz.getRefFloorInfo(refprojectList);	
					request.setAttribute("floorList", maplist);
					
					
					//保存第一个冷库工程的表示Id
					request.setAttribute("projectId", refprojectList.get(0).getProjectId()) ;
					request.setAttribute("projectName", refprojectList.get(0).getProjectName());
				
				
					return mapping.findForward("realrefsys");
			}
			
			
			/**
			 * 跳转到仓库的系统实时数据页面
			 * @param mapping
			 * @param form
			 * @param request
			 * @param response
			 * @return
			 * @throws Exception
			 */
			
			public ActionForward toRefSys(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				
				
				//获取分支标示
				String branchId = request.getParameter("branchId") ;
				
				if(branchId==null|| branchId.equals("")){
					request.setAttribute("errorMsg", "参数为空或非法!");
					logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRefSys)参数为空或非法!");
					throw new Exception("参数非法..");
				}
				
				
				//获取选中的工程标示
				String projectId = request.getParameter("projectId");
				
				if(projectId==null || projectId.equals("")){
					request.setAttribute("errorMsg", "参数为空或非法") ;
					logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRefSys)参数为空或非法!");
					throw new Exception("参数非法!");
				}
				
				//获取所有的冷库工程列表，保存至session中,此时每次进入都需要重新查询
				//if(request.getSession().getAttribute("refPrjList")==null){
					List<TbccPrjType> refprojectList = projectBiz.getRefProjListBybId(new Long(branchId));
					request.getSession().setAttribute("refPrjList", refprojectList);
				//}
					//exeget查看参数是否非法,默认是正常的
					boolean find = false;
					if(refprojectList==null||refprojectList.size()==0){
						find = true;
					}
					
					//获取楼层信息
					List<Map<String, Object>> maplist = realrefBiz.getRefFloorInfo(refprojectList);	
					request.setAttribute("floorList", maplist);
					
					
					
					//把当前需要选择的仓库工程显示出来
					for (TbccPrjType tbccPrjType : refprojectList) {
						if(projectId.equals(tbccPrjType.getProjectId())){
							request.setAttribute("projectId", projectId);
							request.setAttribute("projectName", tbccPrjType.getProjectName());
							find = true;
							break ;
						}
					}
				
				//判断是否是exeget
				if(request.getParameter("exeget")==null){
					return mapping.findForward("realrefsys") ;
				}else{
					if(find == false)
						return mapping.findForward("index");
					else{
						request.setAttribute("exeget", request.getParameter("exeget"));
						return mapping.findForward("realrefsys_exe");
					}
				}
			}
			
			/**
			 * 跳转到冷库楼层的实时数据页面
			 * @param mapping
			 * @param form
			 * @param request
			 * @param response
			 * @return
			 * @throws Exception
			 */
			public ActionForward toRefFloor(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				//获取分支标示
				String branchId = request.getParameter("branchId") ;
				
				if(branchId==null|| branchId.equals("")){
					request.setAttribute("errorMsg", "参数为空或非法!");
					logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRefFloor)参数为空或非法!");
					throw new Exception("参数非法..");
				}
				
				
				//获取选中的工程标示
				String projectId = request.getParameter("projectId");
				
				if(projectId==null || projectId.equals("")){
					request.setAttribute("errorMsg", "参数为空或非法") ;
					logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRefFloor)参数为空或非法!");
					throw new Exception("参数非法!");
				}
				
				//获取楼层类型和楼层编号
				String floorType = request.getParameter("floorType");
				String floorNo 	= request.getParameter("floorNo");
				
				if(floorType==null || floorType.equals("") || floorNo==null || floorNo.equals("")){
					request.setAttribute("errorMsg", "参数为空或非法") ;
					logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRefFloor)参数为空或非法!");
					throw new Exception("参数非法!");
				}
				
				
				//获取仓库工程
				List<TbccPrjType> refprojectList = projectBiz.getRefProjListBybId(new Long(branchId));
				request.getSession().setAttribute("refPrjList", refprojectList);
		
				
				
				
				//获取楼层信息
				List<Map<String, Object>> maplist = realrefBiz.getRefFloorInfo(refprojectList);	
				request.setAttribute("floorList", maplist);
				
				//保存选中的楼层名称
				request.setAttribute("floorName", getFloorInfo(new Integer(floorNo), new Integer(floorType)));
				
				
				
				if(request.getParameter("exeget")==null){
					return mapping.findForward("realref2") ;
				}else{
					request.setAttribute("exeget", request.getParameter("exeget"));
					return mapping.findForward("realref2_exe");
				}
			}
			
			/********************处理每个楼层的实时数据**********************************/
			/**
			 * 处理每个楼层的实时数据
			 * @param mapping
			 * @param form
			 * @param request
			 * @param response
			 * @return
			 * @throws Exception
			 */
			public ActionForward doRefFloor(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				
				response.setCharacterEncoding("gbk");
				
				PrintWriter out = response.getWriter() ;
				
				//获取参数列表,验证是否合法
				String branchId = request.getParameter("branchId") ;
				String projectId = request.getParameter("projectId") ;
				String floorType = request.getParameter("floorType");
				String floorNo = request.getParameter("floorNo") ;
				
				if(branchId==null || branchId.equals("") || projectId==null || projectId.equals("")||
						floorType==null || floorType.equals("")|| floorNo==null || floorNo.equals("")){
					out.print("<h5 style=\"color='red'\">获取实时数据失败,缺少相应的参数信息...</h5>");
					return  null;
				}
				
				//获取楼层对应的冷库信息
				List<TbccRefInfo> refList = realrefBiz.getRefByFloorInfo(projectId, new Integer(floorType), new Integer(floorNo));
				if(refList==null || refList.size()==0){
					out.print("<h5 style=\"color='red'\">获取实时数据失败,没有配置相应的冷库...</h5>");
					return  null;
				}
					
				//获取冷库对应的探头信息
				List<TbccAiInfo> aiList = realrefBiz.getAiByRef(refList);
				if(aiList==null || aiList.size()==0){
					out.print("<h5 style=\"color='red'\">获取实时数据失败,没有配置相应的探头...</h5>");
					return  null;
				}
				
				//获取仓库对应的实时数据
				List<TbccBaseRealRef> baseRealList = realrefBiz.getRealRefData(projectId);
				
				int count = 0 ;		//定义统计冷库个数的变量
				
				int maxAiStr = 0;	//定义一个冷库的探头名称字符串最长的字节数
				int aiStr = 0;
				
				StringBuffer sb_0;//定义左边表格第一行的字符串
				StringBuffer sb0;//定义左边表格数据输出的字符串
				
				StringBuffer sb1;//定义激活状态输出的字符串
				
				StringBuffer sb2;//定义实时数据输出的字符串中第一行
				StringBuffer sb3;//定义激活状态输出的字符串中第三行
				StringBuffer sb4;//定义实时数据输出的字符串中第二行
				StringBuffer sb5;//定义实时数据输出的字符串中第四行
				
				//外循环遍历所有的冷库
				for (TbccRefInfo tbccRefInfo : refList) {
					
					int count1 = 0 ;   //定义统计每个冷库对应探头实时数据等于-300的个数
					
					maxAiStr = 0;		//每个冷库探头名称字符串最长字节数初始化为0
					
					sb1 = new StringBuffer("");
					//构造标题行的前几列
					out.println("<table id='tb' style='border: none;margin-top: 2px;'>");
					out.println("<tr><td style='border: none;' valign='bottom'>");
					out.println("<table width='270' style='border: none;' border='0' cellspacing='0' cellpadding='0'>");
					
					
					
					
					
					
					//每一个冷库，两行数据
					sb2 = new StringBuffer("</tr>");
					sb2.append("</table>");
					sb2.append("</td>");
				
				
					sb2.append("<td style='border: none;' valign='top'>");
				
				
				
				
					//定义探头列表集合保存当前冷库探头
					List<Integer> 	portList = new ArrayList<Integer>();
					sb2.append("<table width='515' style='border: none;padding-top: 0px;margin-top: 0px;' border='0' cellspacing='0' cellpadding='0'>");
					sb2.append("<tr class='altrow' style='height:23px;'>");
					int i = 0;
					sb3 = new StringBuffer("");
					for (TbccAiInfo tbccAiInfo : aiList) {
						//如果探头属于当前冷库、则添加到集合中
						if(tbccRefInfo.getId().equals(tbccAiInfo.getRid())){
							aiStr = 0;
							aiStr = tbccAiInfo.getPortName().getBytes().length;		//获取探头名称字符串字节数
							if(aiStr>maxAiStr) maxAiStr = aiStr;
							if(i<12){
								sb2.append("<td width='42'>"+tbccAiInfo.getPortName()+"</td>");
							}else{
								if(i==12){
									sb3.append("</tr><tr class='altrow' style='height:23px;'>");
								}
								sb3.append("<td width='42'>"+tbccAiInfo.getPortName()+"</td>");
							}
							portList.add(tbccAiInfo.getPortNo());
							i++;
						}
					}
					
					//补列头后面的空白默认一个冷库12个探头
					for(int n=0;n<12-portList.size();n++){
						sb2.append("<td width='42'>&nbsp;&nbsp;</td>");
					}
					
					sb_0 = new StringBuffer("<tr class='altrow' style='height:"+(((maxAiStr/6)+(maxAiStr%6==0?0:1))*23)+"px;'>");
					
					sb_0.append("<td width='30'>序号</td>");
					sb_0.append("<td width='120'>冷库名称</td>");
					sb_0.append("<td width='60'>报警状态</td>");
					sb_0.append("<td width='60'>激活状态</td>");
					sb_0.append("</tr>");
					
					
					//左边表格列标题加载完毕，第二行显示数据
					sb0 = new StringBuffer("");
					
//					sb0.append("<tr class='tb2' "+(portList.size()>12?"style='height:75px;'":"")+">");
					sb0.append("<tr class='tb2' style='height:"+(((portList.size()/12)*(portList.size()%12==0?1:2)+(portList.size()%12==0?0:1))*25-2)+"px;'>");
					
					sb0.append("<td width='30'>"+(++count)+"</td>");
					sb0.append("<td width='120'>"+tbccRefInfo.getRefName()+"</td>");
					
					//定义一个变量用来保存，是否找到实时数据
					boolean isFind0 = false ;
					
					for (TbccBaseRealRef tbccBaseRealRef : baseRealList) {
						
						//判断当前冷库的设备id是否与实时数据设备id相同
						if(tbccRefInfo.getNetid().equals(tbccBaseRealRef.getNeiId())){
							isFind0 = true ;			//匹配到了实时数据
							//获取报警状态				
							Integer alarmState = tbccBaseRealRef.getAllRefAlarmStatus()[tbccRefInfo.getRefid()-1];
							
							
							if(tbccBaseRealRef.getConnectStatus().equals(RealRefBiz.CONNECTION)){
								if(alarmState.equals(RealRefBiz.ALARM)){				//报警
									sb0.append("<td width='60' ><img src='img/menu/win/red.gif' title='表示处于报警状态' /></td>");						
									
								}else if(alarmState.equals(RealRefBiz.NORMAL)){		   //正常
									sb0.append("<td  width='60'><img src='img/menu/win/blue.gif' title='表示处于正常状态'/></td>");						
								}else if (alarmState.equals(RealRefBiz.PREALARM)){		//预警
									sb0.append("<td width='60' ><img src='img/menu/win/orange.gif' title='表示处于预警状态' /></td>");
								}else													//非法状态值
									sb0.append("<td width='60'>* * *</td>");
							}else{													//断开连接了
								sb0.append("<td width='60'>* * *</td>");
							}
							
							
							//获取冷库激活状态
							
							Integer runstate = tbccBaseRealRef.getAllRefRunStatus()[tbccRefInfo.getRefid()-1];
							
							if(tbccBaseRealRef.getConnectStatus().equals(RealRefBiz.CONNECTION)){
								if(runstate.equals(RealRefBiz.STOP))  				//停止
									sb1.append("<td width='60' ><img src='img/menu/msie_doc_mo.gif' title='表示冷库处于未激活状态'  /></td>");						
								else if (runstate.equals(RealRefBiz.RUN))			//运行
									sb1.append("<td width='60'><img src='img/menu/msie_doc.GIF' title='表示冷库处于激活状态'  /></td>");
								else												
									sb1.append("<td width='60'>* * *</td>");
							}else{													//断开了连接
								sb1.append("<td width='60'>* * *</td>");
							}
						}
					}
					//如果没有匹配到的探头的实时数据。则输出****填充
					if(!isFind0){
						for(int k = 0 ;k<2;k++)
							sb0.append("<td width='60'>* * *</td>");
					}
					
					
					
					/*out.println("<tr class='tb2' "+(aiList.size()>12?"style='height:75px;'":"")+">");
					
					out.println("<td width='30'>"+(++count)+"</td>");
					out.println("<td width='70'>"+tbccRefInfo.getRefName()+"</td>");
					
					//定义一个变量用来保存，是否找到实时数据
					boolean isFind0 = false ;
					
					for (TbccBaseRealRef tbccBaseRealRef : baseRealList) {
						
						//判断当前冷库的设备id是否与实时数据设备id相同
						if(tbccRefInfo.getNetid().equals(tbccBaseRealRef.getNeiId())){
							isFind0 = true ;			//匹配到了实时数据
							//获取报警状态				
							Integer alarmState = tbccBaseRealRef.getAllRefAlarmStatus()[tbccRefInfo.getRefid()-1];
							
							
							if(tbccBaseRealRef.getConnectStatus().equals(RealRefBiz.CONNECTION)){
								if(alarmState.equals(RealRefBiz.ALARM)){				//报警
									out.println("<td width='57' ><img src='img/menu/win/red.gif' title='表示处于报警状态' /></td>");						
									
								}else if(alarmState.equals(RealRefBiz.NORMAL)){		   //正常
									out.println("<td  width='57'><img src='img/menu/win/blue.gif' title='表示处于正常状态'/></td>");						
								}else if (alarmState.equals(RealRefBiz.PREALARM)){		//预警
									out.println("<td width='57' ><img src='img/menu/win/orange.gif' title='表示处于预警状态' /></td>");
								}else													//非法状态值
									out.println("<td width='57'>* * *</td>");
							}else{													//断开连接了
									out.println("<td width='57'>* * *</td>");
							}
							
							
							//获取冷库激活状态
							
							Integer runstate = tbccBaseRealRef.getAllRefRunStatus()[tbccRefInfo.getRefid()-1];
							
							if(tbccBaseRealRef.getConnectStatus().equals(RealRefBiz.CONNECTION)){
								if(runstate.equals(RealRefBiz.STOP))  				//停止
									sb1.append("<td width='57' ><img src='img/menu/msie_doc_mo.gif' title='表示冷库处于未激活状态'  /></td>");						
								else if (runstate.equals(RealRefBiz.RUN))			//运行
									sb1.append("<td width='57'><img src='img/menu/msie_doc.GIF' title='表示冷库处于激活状态'  /></td>");
								else												
									sb1.append("<td width='57'>* * *</td>");
							}else{													//断开了连接
								sb1.append("<td width='57'>* * *</td>");
							}
						}
					}
					//如果没有匹配到的探头的实时数据。则输出****填充
					if(!isFind0){
						for(int k = 0 ;k<2;k++)
							out.println("<td width='57'>* * *</td>");
					}*/
					
					
					
					sb4 = new StringBuffer("</tr>");
//					sb2.append("</tr>");
					
					
//					sb2.append("<tr class='tb2'>");
					sb4.append("<tr class='tb2'>");
					//定义一个变量用来保存，是否找到实时数据
					boolean isFind = false ;
					sb5 = new StringBuffer("");
					for (TbccBaseRealRef tbccBaseRealRef : baseRealList) {
						
						//判断当前冷库的设备id是否与实时数据设备id相同
						if(tbccRefInfo.getNetid().equals(tbccBaseRealRef.getNeiId())){
							
							isFind = true ;			//匹配到了实时数据
							
							int ii = 0;
							//获取探头实时数据
							for(Integer integer:portList){
								String str="* * *";
								if(tbccBaseRealRef.getConnectStatus().equals(RealRefBiz.CONNECTION)){
									Double data = tbccBaseRealRef.getAllAi()[integer-1];
									if(data==-300)
									{
										str="---";
										count1++;
									}
									else str=data.toString();
								}
								if(ii<12){
//									sb2.append("<td>"+str+"</td>");
									sb4.append("<td>"+str+"</td>");
								}else{
									if(ii==12){
										sb5.append("</tr><tr class='tb2'>");
									}
									sb5.append("<td>"+str+"</td>");
								}
								ii++;
							}
							//只要冷库与设备满足NetId相等了，相等就跳出循环就OK				
							break ;  					
							
						}
					}
					
					//如果没有匹配到的探头的实时数据。则输出****填充
					if(!isFind){
						int j = 0;
						for(int k = 0 ;k<portList.size();k++){
							if(j<12){
//								sb2.append("<td>* * *</td>");
								sb4.append("<td>* * *</td>");
							}else{
								sb5.append("<td>* * *</td>");
							}
							j++;
						}
					}
					
					//每一个冷库，两行数据
						/*sb2.append("</tr>");
						sb2.append("</table>");
						sb2.append("</td>");
						sb2.append("</tr>");
						sb2.append("</table>");*/
					sb5.append("</tr>");
					sb5.append("</table>");
					sb5.append("</td>");
					sb5.append("</tr>");
					sb5.append("</table>");
					
					/*if(isFind0){
						if(count1==portList.size()){
							out.println("<td width='60' ><img src='img/menu/msie_doc_mo.gif' title='表示冷库处于未激活状态'  /></td>"+sb2);
						}else{
							out.println(sb1);
							out.println(sb2);
						}
					}else{
						out.println(sb2);
					}*/
					if(isFind0){
						if(count1==portList.size()){
							out.println(sb_0);
							out.println(sb0+"<td width='60' ><img src='img/menu/msie_doc_mo.gif' title='表示冷库处于未激活状态'  /></td>"+sb2+sb4+sb3+sb5);
						}else{
							out.println(sb_0);
							out.println(sb0);
							out.println(sb1);
							out.println(sb2);
							out.println(sb4);
							out.println(sb3);
							out.println(sb5);
						}
					}else{
						out.println(sb_0);
						out.println(sb0);
						out.println(sb2);
						out.println(sb4);
						out.println(sb3);
						out.println(sb5);
					}
				}
				return null ;
			}
			/********************原先的处理客户仓库实时数据(2010年12月24日注释ZKW)**********************************/
			/*public ActionForward doRefFloor(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				
				response.setCharacterEncoding("gbk");
				
				PrintWriter out = response.getWriter() ;
				
				//获取参数列表,验证是否合法
				String branchId = request.getParameter("branchId") ;
				String projectId = request.getParameter("projectId") ;
				String floorType = request.getParameter("floorType");
				String floorNo = request.getParameter("floorNo") ;
				
				if(branchId==null || branchId.equals("") || projectId==null || projectId.equals("")||
						floorType==null || floorType.equals("")|| floorNo==null || floorNo.equals("")){
					out.print("<h5 style=\"color='red'\">获取实时数据失败,缺少相应的参数信息...</h5>");
					return  null;
				}
				
				//获取楼层对应的冷库信息
				List<TbccRefInfo> refList = realrefBiz.getRefByFloorInfo(projectId, new Integer(floorType), new Integer(floorNo));
				if(refList==null || refList.size()==0){
					out.print("<h5 style=\"color='red'\">获取实时数据失败,没有配置相应的冷库...</h5>");
					return  null;
				}
					
				//获取冷库对应的探头信息
				List<TbccAiInfo> aiList = realrefBiz.getAiByRef(refList);
				if(aiList==null || aiList.size()==0){
					out.print("<h5 style=\"color='red'\">获取实时数据失败,没有配置相应的探头...</h5>");
					return  null;
				}
				
				//获取仓库对应的实时数据
				List<TbccBaseRealRef> baseRealList = realrefBiz.getRealRefData(projectId);
				
				int count = 0 ;		//定义统计冷库个数的变量
				
				
				//外循环遍历所有的冷库
				for (TbccRefInfo tbccRefInfo : refList) {
					//构造标题行的前几列
					out.println("<table id='tb' width='100%' border='0' cellspacing='0' cellpadding='0'>");
					out.println("<tr class='altrow'>");
					out.println("<td width='30'>序号</td>");
					out.println("<td width='100'>冷库名称</td>");
					out.println("<td width='65'>报警状态</td>");
					out.println("<td width='65'>激活状态</td>");
					
					//定义探头列表集合保存当前冷库探头
					List<Integer> 	portList = new ArrayList<Integer>();
					
					for (TbccAiInfo tbccAiInfo : aiList) {
						//如果探头属于当前冷库、则添加到集合中
						if(tbccRefInfo.getId().equals(tbccAiInfo.getRid())){
							out.println("<td width='42'>"+tbccAiInfo.getPortName()+"</td>");
							portList.add(tbccAiInfo.getPortNo());
						}
					}
					
					//补列头后面的空白默认一个冷库10个探头
					for(int n=0;n<12-portList.size();n++){
						out.println("<td width='42'>&nbsp;&nbsp;</td>");
					}
					
					out.println("</tr>");
					
					//列标题加载完毕，第二行显示数据
					
					out.println("<tr class='tb2'>");
					out.println("<td width='30'>"+(++count)+"</td>");
					out.println("<td width='100'>"+tbccRefInfo.getRefName()+"</td>");
					
					//定义一个变量用来保存，是否找到实时数据
					boolean isFind = false ;
					
					for (TbccBaseRealRef tbccBaseRealRef : baseRealList) {
						
						//判断当前冷库的设备id是否与实时数据设备id相同
						if(tbccRefInfo.getNetid().equals(tbccBaseRealRef.getNeiId())){
							
							isFind = true ;			//匹配到了实时数据
							
							//获取报警状态				
							Integer alarmState = tbccBaseRealRef.getMyAlarm(tbccRefInfo.getRefid());
							
							
							if(tbccBaseRealRef.getConnectStatus().equals(RealRefBiz.CONNECTION)){
								if(alarmState.equals(RealRefBiz.ALARM)){				//报警
									out.println("<td width='65' ><img src='img/menu/win/red.gif' title='表示处于报警状态' /></td>");						
									
								}else if(alarmState.equals(RealRefBiz.NORMAL)){		   //正常
									out.println("<td  width='65'><img src='img/menu/win/blue.gif' title='表示处于正常状态'/></td>");						
								}else if (alarmState.equals(RealRefBiz.PREALARM)){		//预警
									out.println("<td width='65' ><img src='img/menu/win/orange.gif' title='表示处于预警状态' /></td>");
								}else													//非法状态值
									out.println("<td width='65'>* * *</td>");
							}else{													//断开连接了
									out.println("<td width='65'>* * *</td>");
							}
							
							
							//获取冷库激活状态
							
							Integer runstate = tbccBaseRealRef.getMyState(tbccRefInfo.getRefid());
							
							if(tbccBaseRealRef.getConnectStatus().equals(RealRefBiz.CONNECTION)){
								if(runstate.equals(RealRefBiz.STOP))  				//停止
									out.println("<td width='65' ><img src='img/menu/msie_doc_mo.gif' title='表示冷库处于未激活状态'  /></td>");						
								else if (runstate.equals(RealRefBiz.RUN))			//运行
									out.println("<td width='65'><img src='img/menu/msie_doc.GIF' title='表示冷库处于激活状态'  /></td>");
								else												
									out.println("<td width='65'>* * *</td>");
							}else{													//断开了连接
									out.println("<td width='65'>* * *</td>");
							}
							
							
							//获取探头实时数据
							for (Integer integer : portList) {
								if(integer.equals(1))
									out.println("<td>"+tbccBaseRealRef.getAi1Str()+"</td>");
								else if(integer.equals(2))
									out.println("<td>"+tbccBaseRealRef.getAi2Str()+"</td>");
								else if(integer.equals(3))
									out.println("<td>"+tbccBaseRealRef.getAi3Str()+"</td>");
								else if(integer.equals(4))
									out.println("<td>"+tbccBaseRealRef.getAi4Str()+"</td>");
								else if(integer.equals(5))
									out.println("<td>"+tbccBaseRealRef.getAi5Str()+"</td>");
								else if(integer.equals(6))
									out.println("<td>"+tbccBaseRealRef.getAi6Str()+"</td>");
								else if(integer.equals(7))
									out.println("<td>"+tbccBaseRealRef.getAi7Str()+"</td>");
								else if(integer.equals(8))
									out.println("<td>"+tbccBaseRealRef.getAi8Str()+"</td>");
								else if(integer.equals(9))
									out.println("<td>"+tbccBaseRealRef.getAi9Str()+"</td>");
								else if(integer.equals(10))
									out.println("<td>"+tbccBaseRealRef.getAi10Str()+"</td>");
								else if(integer.equals(11))
									out.println("<td>"+tbccBaseRealRef.getAi11Str()+"</td>");
								else if(integer.equals(12))
									out.println("<td>"+tbccBaseRealRef.getAi12Str()+"</td>");	
								
								//探头个数由原来的12个扩展至32个探头
								else if(integer.equals(13))
									out.println("<td>"+tbccBaseRealRef.getAi13Str()+"</td>");
								else if(integer.equals(14))
									out.println("<td>"+tbccBaseRealRef.getAi14Str()+"</td>");
								else if(integer.equals(15))
									out.println("<td>"+tbccBaseRealRef.getAi15Str()+"</td>");
								else if(integer.equals(16))
									out.println("<td>"+tbccBaseRealRef.getAi16Str()+"</td>");
								else if(integer.equals(17))
									out.println("<td>"+tbccBaseRealRef.getAi17Str()+"</td>");
								else if(integer.equals(18))
									out.println("<td>"+tbccBaseRealRef.getAi18Str()+"</td>");
								else if(integer.equals(19))
									out.println("<td>"+tbccBaseRealRef.getAi19Str()+"</td>");
								else if(integer.equals(20))
									out.println("<td>"+tbccBaseRealRef.getAi20Str()+"</td>");
								else if(integer.equals(21))
									out.println("<td>"+tbccBaseRealRef.getAi21Str()+"</td>");
								else if(integer.equals(22))
									out.println("<td>"+tbccBaseRealRef.getAi22Str()+"</td>");
								else if(integer.equals(23))
									out.println("<td>"+tbccBaseRealRef.getAi23Str()+"</td>");
								else if(integer.equals(24))
									out.println("<td>"+tbccBaseRealRef.getAi24Str()+"</td>");
								else if(integer.equals(25))
									out.println("<td>"+tbccBaseRealRef.getAi25Str()+"</td>");
								else if(integer.equals(26))
									out.println("<td>"+tbccBaseRealRef.getAi26Str()+"</td>");
								else if(integer.equals(27))
									out.println("<td>"+tbccBaseRealRef.getAi27Str()+"</td>");
								else if(integer.equals(28))
									out.println("<td>"+tbccBaseRealRef.getAi28Str()+"</td>");
								else if(integer.equals(29))
									out.println("<td>"+tbccBaseRealRef.getAi29Str()+"</td>");
								else if(integer.equals(30))
									out.println("<td>"+tbccBaseRealRef.getAi30Str()+"</td>");
								else if(integer.equals(31))
									out.println("<td>"+tbccBaseRealRef.getAi31Str()+"</td>");
								if(integer.equals(32))
									out.println("<td>"+tbccBaseRealRef.getAi32Str()+"</td>");
							}
							//只要冷库与设备满足NetId相等了，相等就跳出循环就OK				
							break ;  					
							
						}
					}
					
					//如果没有匹配到的探头的实时数据。则输出****填充
					if(!isFind){
						for(int k = 0 ;k<2+portList.size();k++)
							out.println("<td>****</td>");
					}
					
					//每一个冷库，两行数据
						out.println("</tr>");
						out.println("</table>");
					
					
				}
				return null ;
			}
			*/
			
			
			/********************处理客户仓库实时数据**********************************/
			
			
			/**
			 * 跳转到客户默认的系统实时数据页面
			 * @param mapping
			 * @param form
			 * @param request
			 * @param response
			 * @return
			 * @throws Exception
			 */
			
			
			public ActionForward toDefaultRefSysCus(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				
				//保存当前分支标示，和客户分支标示
				String branchId = request.getParameter("branchId") ;
				
				String sbranchId = request.getParameter("sbranchId");
				
				if(branchId==null|| branchId.equals("")|| sbranchId==null ||sbranchId.equals("")){
					request.setAttribute("errorMsg", "参数为空或非法!");
					logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toDefaultRefSys)参数为空或非法!");
					throw new Exception("参数非法..");
				}
				
				//获取所有的冷库工程列表，保存至session中,此时每次进入都需要重新查询
				//if(request.getSession().getAttribute("refPrjList")==null){
					List<TbccPrjType> refprojectList = projectBiz.getRefProjListBybId(new Long(branchId));
					request.getSession().setAttribute("refPrjList", refprojectList);
				//}
					
					
					
					//获取楼层信息
					List<Map<String, Object>> maplist = realrefBiz.getRefFloorInfo(refprojectList);	
					request.setAttribute("floorList", maplist);
					
					
					//保存第一个冷库工程的表示Id
					request.setAttribute("projectId", refprojectList.get(0).getProjectId()) ;
					request.setAttribute("projectName", refprojectList.get(0).getProjectName());
				
				
					return mapping.findForward("realrefsysCus");
			}
			
			/**
			 * 跳转到客户系统实时数据页面
			 * @param mapping
			 * @param form
			 * @param request
			 * @param response
			 * @return
			 * @throws Exception
			 */
			public ActionForward toRefSysCus(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				//获取分支标示，客户分支标示
				String branchId = request.getParameter("branchId") ;
				
				String sbranchId = request.getParameter("sbranchId") ;
				
				if(branchId==null|| branchId.equals("")||sbranchId==null || sbranchId.equals("")){
					request.setAttribute("errorMsg", "参数为空或非法!");
					logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRefSys)参数为空或非法!");
					throw new Exception("参数非法..");
				}
				
				
				//获取选中的工程标示
				String projectId = request.getParameter("projectId");
				
				if(projectId==null || projectId.equals("")){
					request.setAttribute("errorMsg", "参数为空或非法") ;
					logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRefSys)参数为空或非法!");
					throw new Exception("参数非法!");
				}
				
				//获取所有的冷库工程列表，保存至session中,此时每次进入都需要重新查询
				//if(request.getSession().getAttribute("refPrjList")==null){
					List<TbccPrjType> refprojectList = projectBiz.getRefProjListBybId(new Long(branchId));
					request.getSession().setAttribute("refPrjList", refprojectList);
				//}
					
					
					
					//获取楼层信息
					List<Map<String, Object>> maplist = realrefBiz.getRefFloorInfo(refprojectList);	
					request.setAttribute("floorList", maplist);
					
					
					//把当前需要选择的仓库工程显示出来
					for (TbccPrjType tbccPrjType : refprojectList) {
						if(projectId.equals(tbccPrjType.getProjectId())){
							request.setAttribute("projectId", projectId);
							request.setAttribute("projectName", tbccPrjType.getProjectName());
							break ;
						}
					}
									
				return mapping.findForward("realrefsysCus") ;
			}
			
			/**
			 * 跳转到客户楼层实时数据
			 * @param mapping
			 * @param form
			 * @param request
			 * @param response
			 * @return
			 * @throws Exception
			 */
			public ActionForward toRefFloorCus(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				//获取分支标示
				String branchId = request.getParameter("branchId") ;
				
				String sbranchId = request.getParameter("sbranchId");
				
				if(branchId==null|| branchId.equals("")||sbranchId==null || sbranchId.equals("")){
					request.setAttribute("errorMsg", "参数为空或非法!");
					logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRefFloor)参数为空或非法!");
					throw new Exception("参数非法..");
				}
				
				
				//获取选中的工程标示
				String projectId = request.getParameter("projectId");
				
				if(projectId==null || projectId.equals("")){
					request.setAttribute("errorMsg", "参数为空或非法") ;
					logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRefFloor)参数为空或非法!");
					throw new Exception("参数非法!");
				}
				
				//获取楼层类型和楼层编号
				String floorType = request.getParameter("floorType");
				String floorNo 	= request.getParameter("floorNo");
				
				if(floorType==null || floorType.equals("") || floorNo==null || floorNo.equals("")){
					request.setAttribute("errorMsg", "参数为空或非法") ;
					logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRefFloor)参数为空或非法!");
					throw new Exception("参数非法!");
				}
				
				
				//获取仓库工程
				List<TbccPrjType> refprojectList = projectBiz.getRefProjListBybId(new Long(branchId));
				request.getSession().setAttribute("refPrjList", refprojectList);
		
				
				
				
				//获取楼层信息
				List<Map<String, Object>> maplist = realrefBiz.getRefFloorInfo(refprojectList);	
				request.setAttribute("floorList", maplist);
				
				//保存选中的楼层名称
				request.setAttribute("floorName", getFloorInfo(new Integer(floorNo), new Integer(floorType)));
				
				
				return mapping.findForward("realrefCus") ;
			}
			
			
			
			
			
			/******************************************************************************
			 * 下面是原先的实现方式
			 */
			
			
			/**
			 * 获取实时数据刷新调用的方法
			 * @param mapping
			 * @param form
			 * @param request
			 * @param response
			 * @return
			 * @throws Exception
			 */
			public ActionForward doRealList(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				response.setCharacterEncoding("gbk");
				//获取输出对象
				PrintWriter out = response.getWriter() ;
				
				//首先获取所有的数据
				String branchId = request.getParameter("branchId");	
				String projectId = request.getParameter("projectId") ;
				
				//用来设置播放音乐的路径
				String basePath = request.getScheme() + "://"
						+ request.getServerName() + ":" + request.getServerPort()
						+ request.getContextPath() + "/";
				//用来判断是否需要播放报警音乐
				boolean isAlarm = false ;
				
			//	String projectId = this.realrefBiz.getRefPrjId(new Long(branchId));	
				
				if(projectId==null || projectId==""){
						out.println("<h5 style=\"color='red'\">没有找到相应的冷库工程</h5>");
					return null ;					
				}
							
				List<TbccRefInfo> refList = this.refinfoBiz.getRefListByPrj(projectId);

				List<TbccAiInfo> aiList = this.aiInfoBiz.getListByProId(projectId);

				List<TbccBaseRealRef> realList = this.realrefBiz.getRealRefData(projectId);

				
				if(refList==null || refList.size()==0 || aiList==null || aiList.size()==0){
						out.println("<h5 style=\"color='red'\">没有找到相应的冷库、探头...</h5>");
						return null ;
				}
				
				List<Integer> portList = null  ;   //用来存放每一个冷库的对应的探头号
				
				int number = 0 ;					//显示每一条记录编号
				
				/**
				 * 为了控制不同楼层的数据显示
				 */
				
				int floorNo = 0 ;					//定义一个变量用来保存楼层的信息，用于控制楼层信息
				int floorType = 0 ;
				
				TbccRefInfo r = refList.get(0) ;    //默认赋值给第一个了
				
				floorNo = r.getFloorNo() ;
				floorType= r.getFloorType() ;
				
				
				out.println("<h5>"+getFloorInfo(r.getFloorNo(), r.getFloorType())+"</h5>");
				out.println("<hr color='pink' width='100%'>");
				
				//首先循环遍历所有的冷库
				for (TbccRefInfo refInfo : refList) {
					
					 //如果不属于同一个楼层，则分隔开来了
					  if(!refInfo.getFloorNo().equals(floorNo) || !refInfo.getFloorType().equals(floorType)){
						   out.println("<div align='center'><h5>"+getFloorInfo(refInfo.getFloorNo(), refInfo.getFloorType())+"</h5></div>");
						   out.println("<hr color='pink' width='100%'>");
						   
					  } 
				
					  floorNo = refInfo.getFloorNo() ;
					  floorType = refInfo.getFloorType() ;
					  
					out.println("<table width='990' border='0'align='center' cellpadding='0'cellspacing='0' valign='top'>");
					out.println("<tr valign='top' HEIGHT='23' bgcolor='#DEDEDE' CLASS=Page_tools_bar>");
					out.println("<td align='center' CLASS=Page_title width='36' >序号</td>");
					out.println("<td align='center' CLASS=Page_title valign='middle' width='110'>冷库名称</td>");
					out.println("<td align='center' CLASS=Page_title valign='middle' width='90'>报警状态</td>");
					out.println("<td align='center' CLASS=Page_title valign='middle' width='90'>激活状态</td>");
					out.println("<td align='center' CLASS=Page_title valign='middle' width='90'>连接状态</td>");
					
					portList = new ArrayList<Integer>();
					   //循环探头
						for (TbccAiInfo aiInfo : aiList) {
								//如果netId，和 refId 都相同，则添加列头
							if(refInfo.getNetid().equals(aiInfo.getNetid()) && refInfo.getRefid().equals(aiInfo.getRefid())){
								out.println("<td align='center' CLASS=Page_title valign='middle' width='65' >"+aiInfo.getPortName()+"</td>");
								portList.add(aiInfo.getPortNo());
							}
						}
						for(int n=0;n<12-portList.size();n++){
							out.println("<td >&nbsp;&nbsp;</td>");
						}
						out.println("</tr>");
						
					//列头添加完毕!!!!!!!!!!
					
					//遍历整个实时数据的集合
						
						 //保证有数据的情况了
						  out.println("<tr align='center'>");
							out.println("<td>"+(++number)+"</td>");
							out.println("<td>"+refInfo.getRefName()+"</td>");
						
						boolean flag = true ; //定义一个变量用来控制有没有找的相应的数据
						
					  for (TbccBaseRealRef realData : realList) {
						  
						  //如果属于同一个NetId,数据都从在这里那去
						if(refInfo.getNetid().equals(realData.getNeiId())){
							
							flag = false ;
							//报警状态
							Integer alarmstate = realData.getAllRefAlarmStatus()[refInfo.getRefid()-1];
							
							if(realData.getConnectStatus().equals(RealRefBiz.CONNECTION)){
								if(alarmstate.equals(RealRefBiz.ALARM)){				//报警
									out.println("<td align='center'><img src='img/menu/win/red.gif' title='表示处于报警状态' class='op_button' /></td>");						
									isAlarm = true ;
								}else if(alarmstate.equals(RealRefBiz.NORMAL)){		//正常
									out.println("<td align='center'><img src='img/menu/win/blue.gif' title='表示处于正常状态' class='op_button' /></td>");						
								}else if (alarmstate.equals(RealRefBiz.PREALARM)){
									out.println("<td align='center'><img src='img/menu/win/orange.jpg' title='表示处于预警状态' class='op_button' /></td>");
									isAlarm = true ;
								}else												
									out.println("<td>* * *</td>");
							}else{													//断开连接了
									out.println("<td>* * *</td>");
							}
							
							
							
							
							//运行状态---改成了激活状态
							Integer runstate = realData.getAllRefRunStatus()[refInfo.getRefid()-1];
							
							if(realData.getConnectStatus().equals(RealRefBiz.CONNECTION)){
								if(runstate.equals(RealRefBiz.STOP))  				//停止
									out.println("<td align='center'><img src='img/menu/msie_doc_mo.gif' title='表示冷库处于未激活状态' class='op_button' /></td>");						
								else if (runstate.equals(RealRefBiz.RUN))			//运行
									out.println("<td align='center'><img src='img/menu/msie_doc.GIF' title='表示冷库处于激活状态' class='op_button' /></td>");
								else												
									out.println("<td>* * *</td>");
							}else{													//断开了连接
									out.println("<td>* * *</td>");
							}
							
							
							//连接状态
							
							if(realData.getConnectStatus().equals(RealRefBiz.CONNECTION)){
								out.println("<td>连接</td>");
							}else{
								out.println("<td>断开</td>");
								isAlarm = true ;
							}
								
							//处理数据的状态
							for(Integer integer:portList){
								String str="* * *";
								if(realData.getConnectStatus().equals(RealRefBiz.CONNECTION)){
									Double data = realData.getAllAi()[integer-1];
									if(data==-300)
									{
										str="---";
									}
									else str=data.toString();
								}
								
								out.println("<td>"+str+"</td>");
							}
							
							//只要满足一个NetId相等了，相等就跳出循环就OK
							
							break ;  
						}   //end netId the same 
						
					}  //end realdataList ;
					  
					if(flag)			//加入flag = true 表明该冷库现在处于初始阶段，没有任何数据
					{
						for(int k = 0 ;k<3+portList.size();k++)
							out.println("<td>****</td>");
					}
					 out.println("</tr>");
					out.println("</table>");
						
				} 	//end of refList	
				
				if(isAlarm){	
					out.println("<div id='playerContainer'>" +
							"<embed id='wo' src='"+basePath+"music/alarm.WAV'  width='0' height='0' autostart='true' loop='true'  hidden='true'></embed>" +
							"</div>");
				}
				return null ;			
			}
			
			
			/**
			 *  当选择不同的冷库工程时，切换不同的楼层
			 * @param mapping
			 * @param form
			 * @param request
			 * @param response
			 * @return
			 * @throws Exception
			 */
			public ActionForward changeRealList(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				return null ;
			}
			
			
			/**
			 * 处理楼层编号的显示信息
			 * @param floorNo		楼层编号
			 * @param floorType		楼层类型
			 * @return				楼层显示信息
			 */
			public String getFloorInfo(Integer floorNo,Integer floorType){
				if(floorType.equals(RefInfoBiz.FLOORDOWN))
					return "地下室B" + floorNo + "层" ;
				return "地面F" +floorNo+"层";
			}
			
			
			/**
			 * 跳转到楼层实时页面
			 * @param mapping
			 * @param form
			 * @param request
			 * @param response
			 * @return
			 * @throws Exception
			 */
			public ActionForward toRealFloorfuck (ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				
				
				String branchId = request.getParameter("branchId");
				
				if(branchId==null || branchId==""){
					request.setAttribute("errorMsg", "参数为空或非法!");
					logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRealFloor)传递分支标示无效!");
					new Exception("请求资源错误");
				}
				
				
				
				
				String projectId = null;
				List<TbccPrjType> projList = projectBiz.getHasImagesProjListBybId(new Long(branchId));
				if(projList!=null && projList.size()>0){
					request.setAttribute("projList", projList);
					projectId = projList.get(0).getProjectId();
				}
				
//				String projectId = realrefBiz.getRefPrjId(new Long(branchId));		//获取工程下的项目名
//				
//				if(projectId==null || projectId==""){
//					new Exception("请求的资源无效!");
//				}
				
				
			
				
				List<TbccProjectImages> images = imageBiz.getImagesByPid(projectId);	//根据项目名获取所有的图层信息
				
				
				request.setAttribute("imageList", images);					//获取所有的图层信息
				
				if(images!=null && images.size()>0){		//如果存在图层
					TbccProjectImages image =  images.get(0) ;								//默认获取第一个图层
					
					String info = imageBiz.getEachControlAllDates(image.getId(),projectId);			//获取第一个信息 
					request.setAttribute("info", info);										//把数据存放到request
				}
							
				request.setAttribute("projectId", projectId);
				
				return mapping.findForward("realfloor2");
			}
			
			/**
			 * 改变图层时接受的请求
			 * @param mapping
			 * @param form
			 * @param request
			 * @param response
			 * @return
			 * @throws Exception
			 */
			public ActionForward toChangeImage(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				//获取图层Id
				String imageId = request.getParameter("myimage");
				
				String projectId = request.getParameter("projectId");
				
				if(imageId==null || imageId==""){
					request.setAttribute("errorMsg", "参数为空或非法!");
					logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+  " (toChangeImage)获取图层标示Id无效");
					throw new Exception("请求的资源无效!");
				}
				
				String info = imageBiz.getEachControlAllDates(Long.parseLong(imageId),projectId);
				request.setAttribute("info", info);
				return mapping.findForward("realfloor2");
			}
			
			
			/**
			 * 跳转到客户的冷库实时数据刷新页面
			 * @param mapping
			 * @param form
			 * @param request
			 * @param response
			 * @return
			 * @throws Exception
			 */
			public ActionForward toCustomerReal(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response) throws Exception{
				String branchId = request.getParameter("branchId") ;
				
				TbccBranchType b = this.realrefBiz.getById(new Long(branchId));	
				if(b==null){
					request.setAttribute("errorMsg", "该分支机构不存在或已经删除!");
					logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toCustomerReal)该分支机构不存在或已经删除!");
					throw new Exception("参数非法...");
				}
					
				
				//保存客户分支信息
				request.setAttribute("b", b) ;
				
				//保存客户分支冷库工程
				List<TbccPrjType> clientRefPrjList = projectBiz.getRefProjListBybId(new Long(branchId));
				request.setAttribute("clientRefPrjList", clientRefPrjList);
				
				return mapping.findForward("customer") ;
				
			}
			
	
	
}
