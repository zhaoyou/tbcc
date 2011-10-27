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
 * ���ʵʱaction ���� realref.do ���� Extends BaseAction
 * @author Administrator
 *
 */
public class RealRefAction extends BaseAction {

	
	/**
	 * ��spirngע�������ҵ��ӿ�
	 */
	private RefInfoBiz refinfoBiz = null ;
	
	private AiInfoBiz aiInfoBiz = null ;
	
	private RealRefBiz realrefBiz = null ;
	
	private IGetImageEachControlAllDatas imageBiz = null ;
	
	private ProjectBiz	projectBiz = null ;
	
	private Logger logger = Logger.getLogger(RealRefAction.class);

	/**
	 * ��spring ����ע��
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
			 * ת��Ĭ�����ϵͳʵʱ���ݵ�ҳ��
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
					request.setAttribute("errorMsg", "����Ϊ�ջ�Ƿ�!");
					logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toDefaultRefSys)����Ϊ�ջ�Ƿ�!");
					throw new Exception("�����Ƿ�..");
				}
				
				//��ȡ���е���⹤���б�������session��,��ʱÿ�ν��붼��Ҫ���²�ѯ
				//if(request.getSession().getAttribute("refPrjList")==null){
					List<TbccPrjType> refprojectList = projectBiz.getRefProjListBybId(new Long(branchId));
					request.getSession().setAttribute("refPrjList", refprojectList);
				//}
					
					
					
					//��ȡ¥����Ϣ
					List<Map<String, Object>> maplist = realrefBiz.getRefFloorInfo(refprojectList);	
					request.setAttribute("floorList", maplist);
					
					
					//�����һ����⹤�̵ı�ʾId
					request.setAttribute("projectId", refprojectList.get(0).getProjectId()) ;
					request.setAttribute("projectName", refprojectList.get(0).getProjectName());
				
				
					return mapping.findForward("realrefsys");
			}
			
			
			/**
			 * ��ת���ֿ��ϵͳʵʱ����ҳ��
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
				
				
				//��ȡ��֧��ʾ
				String branchId = request.getParameter("branchId") ;
				
				if(branchId==null|| branchId.equals("")){
					request.setAttribute("errorMsg", "����Ϊ�ջ�Ƿ�!");
					logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRefSys)����Ϊ�ջ�Ƿ�!");
					throw new Exception("�����Ƿ�..");
				}
				
				
				//��ȡѡ�еĹ��̱�ʾ
				String projectId = request.getParameter("projectId");
				
				if(projectId==null || projectId.equals("")){
					request.setAttribute("errorMsg", "����Ϊ�ջ�Ƿ�") ;
					logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRefSys)����Ϊ�ջ�Ƿ�!");
					throw new Exception("�����Ƿ�!");
				}
				
				//��ȡ���е���⹤���б�������session��,��ʱÿ�ν��붼��Ҫ���²�ѯ
				//if(request.getSession().getAttribute("refPrjList")==null){
					List<TbccPrjType> refprojectList = projectBiz.getRefProjListBybId(new Long(branchId));
					request.getSession().setAttribute("refPrjList", refprojectList);
				//}
					//exeget�鿴�����Ƿ�Ƿ�,Ĭ����������
					boolean find = false;
					if(refprojectList==null||refprojectList.size()==0){
						find = true;
					}
					
					//��ȡ¥����Ϣ
					List<Map<String, Object>> maplist = realrefBiz.getRefFloorInfo(refprojectList);	
					request.setAttribute("floorList", maplist);
					
					
					
					//�ѵ�ǰ��Ҫѡ��Ĳֿ⹤����ʾ����
					for (TbccPrjType tbccPrjType : refprojectList) {
						if(projectId.equals(tbccPrjType.getProjectId())){
							request.setAttribute("projectId", projectId);
							request.setAttribute("projectName", tbccPrjType.getProjectName());
							find = true;
							break ;
						}
					}
				
				//�ж��Ƿ���exeget
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
			 * ��ת�����¥���ʵʱ����ҳ��
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
				//��ȡ��֧��ʾ
				String branchId = request.getParameter("branchId") ;
				
				if(branchId==null|| branchId.equals("")){
					request.setAttribute("errorMsg", "����Ϊ�ջ�Ƿ�!");
					logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRefFloor)����Ϊ�ջ�Ƿ�!");
					throw new Exception("�����Ƿ�..");
				}
				
				
				//��ȡѡ�еĹ��̱�ʾ
				String projectId = request.getParameter("projectId");
				
				if(projectId==null || projectId.equals("")){
					request.setAttribute("errorMsg", "����Ϊ�ջ�Ƿ�") ;
					logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRefFloor)����Ϊ�ջ�Ƿ�!");
					throw new Exception("�����Ƿ�!");
				}
				
				//��ȡ¥�����ͺ�¥����
				String floorType = request.getParameter("floorType");
				String floorNo 	= request.getParameter("floorNo");
				
				if(floorType==null || floorType.equals("") || floorNo==null || floorNo.equals("")){
					request.setAttribute("errorMsg", "����Ϊ�ջ�Ƿ�") ;
					logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRefFloor)����Ϊ�ջ�Ƿ�!");
					throw new Exception("�����Ƿ�!");
				}
				
				
				//��ȡ�ֿ⹤��
				List<TbccPrjType> refprojectList = projectBiz.getRefProjListBybId(new Long(branchId));
				request.getSession().setAttribute("refPrjList", refprojectList);
		
				
				
				
				//��ȡ¥����Ϣ
				List<Map<String, Object>> maplist = realrefBiz.getRefFloorInfo(refprojectList);	
				request.setAttribute("floorList", maplist);
				
				//����ѡ�е�¥������
				request.setAttribute("floorName", getFloorInfo(new Integer(floorNo), new Integer(floorType)));
				
				
				
				if(request.getParameter("exeget")==null){
					return mapping.findForward("realref2") ;
				}else{
					request.setAttribute("exeget", request.getParameter("exeget"));
					return mapping.findForward("realref2_exe");
				}
			}
			
			/********************����ÿ��¥���ʵʱ����**********************************/
			/**
			 * ����ÿ��¥���ʵʱ����
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
				
				//��ȡ�����б�,��֤�Ƿ�Ϸ�
				String branchId = request.getParameter("branchId") ;
				String projectId = request.getParameter("projectId") ;
				String floorType = request.getParameter("floorType");
				String floorNo = request.getParameter("floorNo") ;
				
				if(branchId==null || branchId.equals("") || projectId==null || projectId.equals("")||
						floorType==null || floorType.equals("")|| floorNo==null || floorNo.equals("")){
					out.print("<h5 style=\"color='red'\">��ȡʵʱ����ʧ��,ȱ����Ӧ�Ĳ�����Ϣ...</h5>");
					return  null;
				}
				
				//��ȡ¥���Ӧ�������Ϣ
				List<TbccRefInfo> refList = realrefBiz.getRefByFloorInfo(projectId, new Integer(floorType), new Integer(floorNo));
				if(refList==null || refList.size()==0){
					out.print("<h5 style=\"color='red'\">��ȡʵʱ����ʧ��,û��������Ӧ�����...</h5>");
					return  null;
				}
					
				//��ȡ����Ӧ��̽ͷ��Ϣ
				List<TbccAiInfo> aiList = realrefBiz.getAiByRef(refList);
				if(aiList==null || aiList.size()==0){
					out.print("<h5 style=\"color='red'\">��ȡʵʱ����ʧ��,û��������Ӧ��̽ͷ...</h5>");
					return  null;
				}
				
				//��ȡ�ֿ��Ӧ��ʵʱ����
				List<TbccBaseRealRef> baseRealList = realrefBiz.getRealRefData(projectId);
				
				int count = 0 ;		//����ͳ���������ı���
				
				int maxAiStr = 0;	//����һ������̽ͷ�����ַ�������ֽ���
				int aiStr = 0;
				
				StringBuffer sb_0;//������߱���һ�е��ַ���
				StringBuffer sb0;//������߱������������ַ���
				
				StringBuffer sb1;//���弤��״̬������ַ���
				
				StringBuffer sb2;//����ʵʱ����������ַ����е�һ��
				StringBuffer sb3;//���弤��״̬������ַ����е�����
				StringBuffer sb4;//����ʵʱ����������ַ����еڶ���
				StringBuffer sb5;//����ʵʱ����������ַ����е�����
				
				//��ѭ���������е����
				for (TbccRefInfo tbccRefInfo : refList) {
					
					int count1 = 0 ;   //����ͳ��ÿ������Ӧ̽ͷʵʱ���ݵ���-300�ĸ���
					
					maxAiStr = 0;		//ÿ�����̽ͷ�����ַ�����ֽ�����ʼ��Ϊ0
					
					sb1 = new StringBuffer("");
					//��������е�ǰ����
					out.println("<table id='tb' style='border: none;margin-top: 2px;'>");
					out.println("<tr><td style='border: none;' valign='bottom'>");
					out.println("<table width='270' style='border: none;' border='0' cellspacing='0' cellpadding='0'>");
					
					
					
					
					
					
					//ÿһ����⣬��������
					sb2 = new StringBuffer("</tr>");
					sb2.append("</table>");
					sb2.append("</td>");
				
				
					sb2.append("<td style='border: none;' valign='top'>");
				
				
				
				
					//����̽ͷ�б��ϱ��浱ǰ���̽ͷ
					List<Integer> 	portList = new ArrayList<Integer>();
					sb2.append("<table width='515' style='border: none;padding-top: 0px;margin-top: 0px;' border='0' cellspacing='0' cellpadding='0'>");
					sb2.append("<tr class='altrow' style='height:23px;'>");
					int i = 0;
					sb3 = new StringBuffer("");
					for (TbccAiInfo tbccAiInfo : aiList) {
						//���̽ͷ���ڵ�ǰ��⡢����ӵ�������
						if(tbccRefInfo.getId().equals(tbccAiInfo.getRid())){
							aiStr = 0;
							aiStr = tbccAiInfo.getPortName().getBytes().length;		//��ȡ̽ͷ�����ַ����ֽ���
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
					
					//����ͷ����Ŀհ�Ĭ��һ�����12��̽ͷ
					for(int n=0;n<12-portList.size();n++){
						sb2.append("<td width='42'>&nbsp;&nbsp;</td>");
					}
					
					sb_0 = new StringBuffer("<tr class='altrow' style='height:"+(((maxAiStr/6)+(maxAiStr%6==0?0:1))*23)+"px;'>");
					
					sb_0.append("<td width='30'>���</td>");
					sb_0.append("<td width='120'>�������</td>");
					sb_0.append("<td width='60'>����״̬</td>");
					sb_0.append("<td width='60'>����״̬</td>");
					sb_0.append("</tr>");
					
					
					//��߱���б��������ϣ��ڶ�����ʾ����
					sb0 = new StringBuffer("");
					
//					sb0.append("<tr class='tb2' "+(portList.size()>12?"style='height:75px;'":"")+">");
					sb0.append("<tr class='tb2' style='height:"+(((portList.size()/12)*(portList.size()%12==0?1:2)+(portList.size()%12==0?0:1))*25-2)+"px;'>");
					
					sb0.append("<td width='30'>"+(++count)+"</td>");
					sb0.append("<td width='120'>"+tbccRefInfo.getRefName()+"</td>");
					
					//����һ�������������棬�Ƿ��ҵ�ʵʱ����
					boolean isFind0 = false ;
					
					for (TbccBaseRealRef tbccBaseRealRef : baseRealList) {
						
						//�жϵ�ǰ�����豸id�Ƿ���ʵʱ�����豸id��ͬ
						if(tbccRefInfo.getNetid().equals(tbccBaseRealRef.getNeiId())){
							isFind0 = true ;			//ƥ�䵽��ʵʱ����
							//��ȡ����״̬				
							Integer alarmState = tbccBaseRealRef.getAllRefAlarmStatus()[tbccRefInfo.getRefid()-1];
							
							
							if(tbccBaseRealRef.getConnectStatus().equals(RealRefBiz.CONNECTION)){
								if(alarmState.equals(RealRefBiz.ALARM)){				//����
									sb0.append("<td width='60' ><img src='img/menu/win/red.gif' title='��ʾ���ڱ���״̬' /></td>");						
									
								}else if(alarmState.equals(RealRefBiz.NORMAL)){		   //����
									sb0.append("<td  width='60'><img src='img/menu/win/blue.gif' title='��ʾ��������״̬'/></td>");						
								}else if (alarmState.equals(RealRefBiz.PREALARM)){		//Ԥ��
									sb0.append("<td width='60' ><img src='img/menu/win/orange.gif' title='��ʾ����Ԥ��״̬' /></td>");
								}else													//�Ƿ�״ֵ̬
									sb0.append("<td width='60'>* * *</td>");
							}else{													//�Ͽ�������
								sb0.append("<td width='60'>* * *</td>");
							}
							
							
							//��ȡ��⼤��״̬
							
							Integer runstate = tbccBaseRealRef.getAllRefRunStatus()[tbccRefInfo.getRefid()-1];
							
							if(tbccBaseRealRef.getConnectStatus().equals(RealRefBiz.CONNECTION)){
								if(runstate.equals(RealRefBiz.STOP))  				//ֹͣ
									sb1.append("<td width='60' ><img src='img/menu/msie_doc_mo.gif' title='��ʾ��⴦��δ����״̬'  /></td>");						
								else if (runstate.equals(RealRefBiz.RUN))			//����
									sb1.append("<td width='60'><img src='img/menu/msie_doc.GIF' title='��ʾ��⴦�ڼ���״̬'  /></td>");
								else												
									sb1.append("<td width='60'>* * *</td>");
							}else{													//�Ͽ�������
								sb1.append("<td width='60'>* * *</td>");
							}
						}
					}
					//���û��ƥ�䵽��̽ͷ��ʵʱ���ݡ������****���
					if(!isFind0){
						for(int k = 0 ;k<2;k++)
							sb0.append("<td width='60'>* * *</td>");
					}
					
					
					
					/*out.println("<tr class='tb2' "+(aiList.size()>12?"style='height:75px;'":"")+">");
					
					out.println("<td width='30'>"+(++count)+"</td>");
					out.println("<td width='70'>"+tbccRefInfo.getRefName()+"</td>");
					
					//����һ�������������棬�Ƿ��ҵ�ʵʱ����
					boolean isFind0 = false ;
					
					for (TbccBaseRealRef tbccBaseRealRef : baseRealList) {
						
						//�жϵ�ǰ�����豸id�Ƿ���ʵʱ�����豸id��ͬ
						if(tbccRefInfo.getNetid().equals(tbccBaseRealRef.getNeiId())){
							isFind0 = true ;			//ƥ�䵽��ʵʱ����
							//��ȡ����״̬				
							Integer alarmState = tbccBaseRealRef.getAllRefAlarmStatus()[tbccRefInfo.getRefid()-1];
							
							
							if(tbccBaseRealRef.getConnectStatus().equals(RealRefBiz.CONNECTION)){
								if(alarmState.equals(RealRefBiz.ALARM)){				//����
									out.println("<td width='57' ><img src='img/menu/win/red.gif' title='��ʾ���ڱ���״̬' /></td>");						
									
								}else if(alarmState.equals(RealRefBiz.NORMAL)){		   //����
									out.println("<td  width='57'><img src='img/menu/win/blue.gif' title='��ʾ��������״̬'/></td>");						
								}else if (alarmState.equals(RealRefBiz.PREALARM)){		//Ԥ��
									out.println("<td width='57' ><img src='img/menu/win/orange.gif' title='��ʾ����Ԥ��״̬' /></td>");
								}else													//�Ƿ�״ֵ̬
									out.println("<td width='57'>* * *</td>");
							}else{													//�Ͽ�������
									out.println("<td width='57'>* * *</td>");
							}
							
							
							//��ȡ��⼤��״̬
							
							Integer runstate = tbccBaseRealRef.getAllRefRunStatus()[tbccRefInfo.getRefid()-1];
							
							if(tbccBaseRealRef.getConnectStatus().equals(RealRefBiz.CONNECTION)){
								if(runstate.equals(RealRefBiz.STOP))  				//ֹͣ
									sb1.append("<td width='57' ><img src='img/menu/msie_doc_mo.gif' title='��ʾ��⴦��δ����״̬'  /></td>");						
								else if (runstate.equals(RealRefBiz.RUN))			//����
									sb1.append("<td width='57'><img src='img/menu/msie_doc.GIF' title='��ʾ��⴦�ڼ���״̬'  /></td>");
								else												
									sb1.append("<td width='57'>* * *</td>");
							}else{													//�Ͽ�������
								sb1.append("<td width='57'>* * *</td>");
							}
						}
					}
					//���û��ƥ�䵽��̽ͷ��ʵʱ���ݡ������****���
					if(!isFind0){
						for(int k = 0 ;k<2;k++)
							out.println("<td width='57'>* * *</td>");
					}*/
					
					
					
					sb4 = new StringBuffer("</tr>");
//					sb2.append("</tr>");
					
					
//					sb2.append("<tr class='tb2'>");
					sb4.append("<tr class='tb2'>");
					//����һ�������������棬�Ƿ��ҵ�ʵʱ����
					boolean isFind = false ;
					sb5 = new StringBuffer("");
					for (TbccBaseRealRef tbccBaseRealRef : baseRealList) {
						
						//�жϵ�ǰ�����豸id�Ƿ���ʵʱ�����豸id��ͬ
						if(tbccRefInfo.getNetid().equals(tbccBaseRealRef.getNeiId())){
							
							isFind = true ;			//ƥ�䵽��ʵʱ����
							
							int ii = 0;
							//��ȡ̽ͷʵʱ����
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
							//ֻҪ������豸����NetId����ˣ���Ⱦ�����ѭ����OK				
							break ;  					
							
						}
					}
					
					//���û��ƥ�䵽��̽ͷ��ʵʱ���ݡ������****���
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
					
					//ÿһ����⣬��������
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
							out.println("<td width='60' ><img src='img/menu/msie_doc_mo.gif' title='��ʾ��⴦��δ����״̬'  /></td>"+sb2);
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
							out.println(sb0+"<td width='60' ><img src='img/menu/msie_doc_mo.gif' title='��ʾ��⴦��δ����״̬'  /></td>"+sb2+sb4+sb3+sb5);
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
			/********************ԭ�ȵĴ���ͻ��ֿ�ʵʱ����(2010��12��24��ע��ZKW)**********************************/
			/*public ActionForward doRefFloor(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				
				response.setCharacterEncoding("gbk");
				
				PrintWriter out = response.getWriter() ;
				
				//��ȡ�����б�,��֤�Ƿ�Ϸ�
				String branchId = request.getParameter("branchId") ;
				String projectId = request.getParameter("projectId") ;
				String floorType = request.getParameter("floorType");
				String floorNo = request.getParameter("floorNo") ;
				
				if(branchId==null || branchId.equals("") || projectId==null || projectId.equals("")||
						floorType==null || floorType.equals("")|| floorNo==null || floorNo.equals("")){
					out.print("<h5 style=\"color='red'\">��ȡʵʱ����ʧ��,ȱ����Ӧ�Ĳ�����Ϣ...</h5>");
					return  null;
				}
				
				//��ȡ¥���Ӧ�������Ϣ
				List<TbccRefInfo> refList = realrefBiz.getRefByFloorInfo(projectId, new Integer(floorType), new Integer(floorNo));
				if(refList==null || refList.size()==0){
					out.print("<h5 style=\"color='red'\">��ȡʵʱ����ʧ��,û��������Ӧ�����...</h5>");
					return  null;
				}
					
				//��ȡ����Ӧ��̽ͷ��Ϣ
				List<TbccAiInfo> aiList = realrefBiz.getAiByRef(refList);
				if(aiList==null || aiList.size()==0){
					out.print("<h5 style=\"color='red'\">��ȡʵʱ����ʧ��,û��������Ӧ��̽ͷ...</h5>");
					return  null;
				}
				
				//��ȡ�ֿ��Ӧ��ʵʱ����
				List<TbccBaseRealRef> baseRealList = realrefBiz.getRealRefData(projectId);
				
				int count = 0 ;		//����ͳ���������ı���
				
				
				//��ѭ���������е����
				for (TbccRefInfo tbccRefInfo : refList) {
					//��������е�ǰ����
					out.println("<table id='tb' width='100%' border='0' cellspacing='0' cellpadding='0'>");
					out.println("<tr class='altrow'>");
					out.println("<td width='30'>���</td>");
					out.println("<td width='100'>�������</td>");
					out.println("<td width='65'>����״̬</td>");
					out.println("<td width='65'>����״̬</td>");
					
					//����̽ͷ�б��ϱ��浱ǰ���̽ͷ
					List<Integer> 	portList = new ArrayList<Integer>();
					
					for (TbccAiInfo tbccAiInfo : aiList) {
						//���̽ͷ���ڵ�ǰ��⡢����ӵ�������
						if(tbccRefInfo.getId().equals(tbccAiInfo.getRid())){
							out.println("<td width='42'>"+tbccAiInfo.getPortName()+"</td>");
							portList.add(tbccAiInfo.getPortNo());
						}
					}
					
					//����ͷ����Ŀհ�Ĭ��һ�����10��̽ͷ
					for(int n=0;n<12-portList.size();n++){
						out.println("<td width='42'>&nbsp;&nbsp;</td>");
					}
					
					out.println("</tr>");
					
					//�б��������ϣ��ڶ�����ʾ����
					
					out.println("<tr class='tb2'>");
					out.println("<td width='30'>"+(++count)+"</td>");
					out.println("<td width='100'>"+tbccRefInfo.getRefName()+"</td>");
					
					//����һ�������������棬�Ƿ��ҵ�ʵʱ����
					boolean isFind = false ;
					
					for (TbccBaseRealRef tbccBaseRealRef : baseRealList) {
						
						//�жϵ�ǰ�����豸id�Ƿ���ʵʱ�����豸id��ͬ
						if(tbccRefInfo.getNetid().equals(tbccBaseRealRef.getNeiId())){
							
							isFind = true ;			//ƥ�䵽��ʵʱ����
							
							//��ȡ����״̬				
							Integer alarmState = tbccBaseRealRef.getMyAlarm(tbccRefInfo.getRefid());
							
							
							if(tbccBaseRealRef.getConnectStatus().equals(RealRefBiz.CONNECTION)){
								if(alarmState.equals(RealRefBiz.ALARM)){				//����
									out.println("<td width='65' ><img src='img/menu/win/red.gif' title='��ʾ���ڱ���״̬' /></td>");						
									
								}else if(alarmState.equals(RealRefBiz.NORMAL)){		   //����
									out.println("<td  width='65'><img src='img/menu/win/blue.gif' title='��ʾ��������״̬'/></td>");						
								}else if (alarmState.equals(RealRefBiz.PREALARM)){		//Ԥ��
									out.println("<td width='65' ><img src='img/menu/win/orange.gif' title='��ʾ����Ԥ��״̬' /></td>");
								}else													//�Ƿ�״ֵ̬
									out.println("<td width='65'>* * *</td>");
							}else{													//�Ͽ�������
									out.println("<td width='65'>* * *</td>");
							}
							
							
							//��ȡ��⼤��״̬
							
							Integer runstate = tbccBaseRealRef.getMyState(tbccRefInfo.getRefid());
							
							if(tbccBaseRealRef.getConnectStatus().equals(RealRefBiz.CONNECTION)){
								if(runstate.equals(RealRefBiz.STOP))  				//ֹͣ
									out.println("<td width='65' ><img src='img/menu/msie_doc_mo.gif' title='��ʾ��⴦��δ����״̬'  /></td>");						
								else if (runstate.equals(RealRefBiz.RUN))			//����
									out.println("<td width='65'><img src='img/menu/msie_doc.GIF' title='��ʾ��⴦�ڼ���״̬'  /></td>");
								else												
									out.println("<td width='65'>* * *</td>");
							}else{													//�Ͽ�������
									out.println("<td width='65'>* * *</td>");
							}
							
							
							//��ȡ̽ͷʵʱ����
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
								
								//̽ͷ������ԭ����12����չ��32��̽ͷ
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
							//ֻҪ������豸����NetId����ˣ���Ⱦ�����ѭ����OK				
							break ;  					
							
						}
					}
					
					//���û��ƥ�䵽��̽ͷ��ʵʱ���ݡ������****���
					if(!isFind){
						for(int k = 0 ;k<2+portList.size();k++)
							out.println("<td>****</td>");
					}
					
					//ÿһ����⣬��������
						out.println("</tr>");
						out.println("</table>");
					
					
				}
				return null ;
			}
			*/
			
			
			/********************����ͻ��ֿ�ʵʱ����**********************************/
			
			
			/**
			 * ��ת���ͻ�Ĭ�ϵ�ϵͳʵʱ����ҳ��
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
				
				//���浱ǰ��֧��ʾ���Ϳͻ���֧��ʾ
				String branchId = request.getParameter("branchId") ;
				
				String sbranchId = request.getParameter("sbranchId");
				
				if(branchId==null|| branchId.equals("")|| sbranchId==null ||sbranchId.equals("")){
					request.setAttribute("errorMsg", "����Ϊ�ջ�Ƿ�!");
					logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toDefaultRefSys)����Ϊ�ջ�Ƿ�!");
					throw new Exception("�����Ƿ�..");
				}
				
				//��ȡ���е���⹤���б�������session��,��ʱÿ�ν��붼��Ҫ���²�ѯ
				//if(request.getSession().getAttribute("refPrjList")==null){
					List<TbccPrjType> refprojectList = projectBiz.getRefProjListBybId(new Long(branchId));
					request.getSession().setAttribute("refPrjList", refprojectList);
				//}
					
					
					
					//��ȡ¥����Ϣ
					List<Map<String, Object>> maplist = realrefBiz.getRefFloorInfo(refprojectList);	
					request.setAttribute("floorList", maplist);
					
					
					//�����һ����⹤�̵ı�ʾId
					request.setAttribute("projectId", refprojectList.get(0).getProjectId()) ;
					request.setAttribute("projectName", refprojectList.get(0).getProjectName());
				
				
					return mapping.findForward("realrefsysCus");
			}
			
			/**
			 * ��ת���ͻ�ϵͳʵʱ����ҳ��
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
				//��ȡ��֧��ʾ���ͻ���֧��ʾ
				String branchId = request.getParameter("branchId") ;
				
				String sbranchId = request.getParameter("sbranchId") ;
				
				if(branchId==null|| branchId.equals("")||sbranchId==null || sbranchId.equals("")){
					request.setAttribute("errorMsg", "����Ϊ�ջ�Ƿ�!");
					logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRefSys)����Ϊ�ջ�Ƿ�!");
					throw new Exception("�����Ƿ�..");
				}
				
				
				//��ȡѡ�еĹ��̱�ʾ
				String projectId = request.getParameter("projectId");
				
				if(projectId==null || projectId.equals("")){
					request.setAttribute("errorMsg", "����Ϊ�ջ�Ƿ�") ;
					logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRefSys)����Ϊ�ջ�Ƿ�!");
					throw new Exception("�����Ƿ�!");
				}
				
				//��ȡ���е���⹤���б�������session��,��ʱÿ�ν��붼��Ҫ���²�ѯ
				//if(request.getSession().getAttribute("refPrjList")==null){
					List<TbccPrjType> refprojectList = projectBiz.getRefProjListBybId(new Long(branchId));
					request.getSession().setAttribute("refPrjList", refprojectList);
				//}
					
					
					
					//��ȡ¥����Ϣ
					List<Map<String, Object>> maplist = realrefBiz.getRefFloorInfo(refprojectList);	
					request.setAttribute("floorList", maplist);
					
					
					//�ѵ�ǰ��Ҫѡ��Ĳֿ⹤����ʾ����
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
			 * ��ת���ͻ�¥��ʵʱ����
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
				//��ȡ��֧��ʾ
				String branchId = request.getParameter("branchId") ;
				
				String sbranchId = request.getParameter("sbranchId");
				
				if(branchId==null|| branchId.equals("")||sbranchId==null || sbranchId.equals("")){
					request.setAttribute("errorMsg", "����Ϊ�ջ�Ƿ�!");
					logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRefFloor)����Ϊ�ջ�Ƿ�!");
					throw new Exception("�����Ƿ�..");
				}
				
				
				//��ȡѡ�еĹ��̱�ʾ
				String projectId = request.getParameter("projectId");
				
				if(projectId==null || projectId.equals("")){
					request.setAttribute("errorMsg", "����Ϊ�ջ�Ƿ�") ;
					logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRefFloor)����Ϊ�ջ�Ƿ�!");
					throw new Exception("�����Ƿ�!");
				}
				
				//��ȡ¥�����ͺ�¥����
				String floorType = request.getParameter("floorType");
				String floorNo 	= request.getParameter("floorNo");
				
				if(floorType==null || floorType.equals("") || floorNo==null || floorNo.equals("")){
					request.setAttribute("errorMsg", "����Ϊ�ջ�Ƿ�") ;
					logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRefFloor)����Ϊ�ջ�Ƿ�!");
					throw new Exception("�����Ƿ�!");
				}
				
				
				//��ȡ�ֿ⹤��
				List<TbccPrjType> refprojectList = projectBiz.getRefProjListBybId(new Long(branchId));
				request.getSession().setAttribute("refPrjList", refprojectList);
		
				
				
				
				//��ȡ¥����Ϣ
				List<Map<String, Object>> maplist = realrefBiz.getRefFloorInfo(refprojectList);	
				request.setAttribute("floorList", maplist);
				
				//����ѡ�е�¥������
				request.setAttribute("floorName", getFloorInfo(new Integer(floorNo), new Integer(floorType)));
				
				
				return mapping.findForward("realrefCus") ;
			}
			
			
			
			
			
			/******************************************************************************
			 * ������ԭ�ȵ�ʵ�ַ�ʽ
			 */
			
			
			/**
			 * ��ȡʵʱ����ˢ�µ��õķ���
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
				//��ȡ�������
				PrintWriter out = response.getWriter() ;
				
				//���Ȼ�ȡ���е�����
				String branchId = request.getParameter("branchId");	
				String projectId = request.getParameter("projectId") ;
				
				//�������ò������ֵ�·��
				String basePath = request.getScheme() + "://"
						+ request.getServerName() + ":" + request.getServerPort()
						+ request.getContextPath() + "/";
				//�����ж��Ƿ���Ҫ���ű�������
				boolean isAlarm = false ;
				
			//	String projectId = this.realrefBiz.getRefPrjId(new Long(branchId));	
				
				if(projectId==null || projectId==""){
						out.println("<h5 style=\"color='red'\">û���ҵ���Ӧ����⹤��</h5>");
					return null ;					
				}
							
				List<TbccRefInfo> refList = this.refinfoBiz.getRefListByPrj(projectId);

				List<TbccAiInfo> aiList = this.aiInfoBiz.getListByProId(projectId);

				List<TbccBaseRealRef> realList = this.realrefBiz.getRealRefData(projectId);

				
				if(refList==null || refList.size()==0 || aiList==null || aiList.size()==0){
						out.println("<h5 style=\"color='red'\">û���ҵ���Ӧ����⡢̽ͷ...</h5>");
						return null ;
				}
				
				List<Integer> portList = null  ;   //�������ÿһ�����Ķ�Ӧ��̽ͷ��
				
				int number = 0 ;					//��ʾÿһ����¼���
				
				/**
				 * Ϊ�˿��Ʋ�ͬ¥���������ʾ
				 */
				
				int floorNo = 0 ;					//����һ��������������¥�����Ϣ�����ڿ���¥����Ϣ
				int floorType = 0 ;
				
				TbccRefInfo r = refList.get(0) ;    //Ĭ�ϸ�ֵ����һ����
				
				floorNo = r.getFloorNo() ;
				floorType= r.getFloorType() ;
				
				
				out.println("<h5>"+getFloorInfo(r.getFloorNo(), r.getFloorType())+"</h5>");
				out.println("<hr color='pink' width='100%'>");
				
				//����ѭ���������е����
				for (TbccRefInfo refInfo : refList) {
					
					 //���������ͬһ��¥�㣬��ָ�������
					  if(!refInfo.getFloorNo().equals(floorNo) || !refInfo.getFloorType().equals(floorType)){
						   out.println("<div align='center'><h5>"+getFloorInfo(refInfo.getFloorNo(), refInfo.getFloorType())+"</h5></div>");
						   out.println("<hr color='pink' width='100%'>");
						   
					  } 
				
					  floorNo = refInfo.getFloorNo() ;
					  floorType = refInfo.getFloorType() ;
					  
					out.println("<table width='990' border='0'align='center' cellpadding='0'cellspacing='0' valign='top'>");
					out.println("<tr valign='top' HEIGHT='23' bgcolor='#DEDEDE' CLASS=Page_tools_bar>");
					out.println("<td align='center' CLASS=Page_title width='36' >���</td>");
					out.println("<td align='center' CLASS=Page_title valign='middle' width='110'>�������</td>");
					out.println("<td align='center' CLASS=Page_title valign='middle' width='90'>����״̬</td>");
					out.println("<td align='center' CLASS=Page_title valign='middle' width='90'>����״̬</td>");
					out.println("<td align='center' CLASS=Page_title valign='middle' width='90'>����״̬</td>");
					
					portList = new ArrayList<Integer>();
					   //ѭ��̽ͷ
						for (TbccAiInfo aiInfo : aiList) {
								//���netId���� refId ����ͬ���������ͷ
							if(refInfo.getNetid().equals(aiInfo.getNetid()) && refInfo.getRefid().equals(aiInfo.getRefid())){
								out.println("<td align='center' CLASS=Page_title valign='middle' width='65' >"+aiInfo.getPortName()+"</td>");
								portList.add(aiInfo.getPortNo());
							}
						}
						for(int n=0;n<12-portList.size();n++){
							out.println("<td >&nbsp;&nbsp;</td>");
						}
						out.println("</tr>");
						
					//��ͷ������!!!!!!!!!!
					
					//��������ʵʱ���ݵļ���
						
						 //��֤�����ݵ������
						  out.println("<tr align='center'>");
							out.println("<td>"+(++number)+"</td>");
							out.println("<td>"+refInfo.getRefName()+"</td>");
						
						boolean flag = true ; //����һ����������������û���ҵ���Ӧ������
						
					  for (TbccBaseRealRef realData : realList) {
						  
						  //�������ͬһ��NetId,���ݶ�����������ȥ
						if(refInfo.getNetid().equals(realData.getNeiId())){
							
							flag = false ;
							//����״̬
							Integer alarmstate = realData.getAllRefAlarmStatus()[refInfo.getRefid()-1];
							
							if(realData.getConnectStatus().equals(RealRefBiz.CONNECTION)){
								if(alarmstate.equals(RealRefBiz.ALARM)){				//����
									out.println("<td align='center'><img src='img/menu/win/red.gif' title='��ʾ���ڱ���״̬' class='op_button' /></td>");						
									isAlarm = true ;
								}else if(alarmstate.equals(RealRefBiz.NORMAL)){		//����
									out.println("<td align='center'><img src='img/menu/win/blue.gif' title='��ʾ��������״̬' class='op_button' /></td>");						
								}else if (alarmstate.equals(RealRefBiz.PREALARM)){
									out.println("<td align='center'><img src='img/menu/win/orange.jpg' title='��ʾ����Ԥ��״̬' class='op_button' /></td>");
									isAlarm = true ;
								}else												
									out.println("<td>* * *</td>");
							}else{													//�Ͽ�������
									out.println("<td>* * *</td>");
							}
							
							
							
							
							//����״̬---�ĳ��˼���״̬
							Integer runstate = realData.getAllRefRunStatus()[refInfo.getRefid()-1];
							
							if(realData.getConnectStatus().equals(RealRefBiz.CONNECTION)){
								if(runstate.equals(RealRefBiz.STOP))  				//ֹͣ
									out.println("<td align='center'><img src='img/menu/msie_doc_mo.gif' title='��ʾ��⴦��δ����״̬' class='op_button' /></td>");						
								else if (runstate.equals(RealRefBiz.RUN))			//����
									out.println("<td align='center'><img src='img/menu/msie_doc.GIF' title='��ʾ��⴦�ڼ���״̬' class='op_button' /></td>");
								else												
									out.println("<td>* * *</td>");
							}else{													//�Ͽ�������
									out.println("<td>* * *</td>");
							}
							
							
							//����״̬
							
							if(realData.getConnectStatus().equals(RealRefBiz.CONNECTION)){
								out.println("<td>����</td>");
							}else{
								out.println("<td>�Ͽ�</td>");
								isAlarm = true ;
							}
								
							//�������ݵ�״̬
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
							
							//ֻҪ����һ��NetId����ˣ���Ⱦ�����ѭ����OK
							
							break ;  
						}   //end netId the same 
						
					}  //end realdataList ;
					  
					if(flag)			//����flag = true ������������ڴ��ڳ�ʼ�׶Σ�û���κ�����
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
			 *  ��ѡ��ͬ����⹤��ʱ���л���ͬ��¥��
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
			 * ����¥���ŵ���ʾ��Ϣ
			 * @param floorNo		¥����
			 * @param floorType		¥������
			 * @return				¥����ʾ��Ϣ
			 */
			public String getFloorInfo(Integer floorNo,Integer floorType){
				if(floorType.equals(RefInfoBiz.FLOORDOWN))
					return "������B" + floorNo + "��" ;
				return "����F" +floorNo+"��";
			}
			
			
			/**
			 * ��ת��¥��ʵʱҳ��
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
					request.setAttribute("errorMsg", "����Ϊ�ջ�Ƿ�!");
					logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toRealFloor)���ݷ�֧��ʾ��Ч!");
					new Exception("������Դ����");
				}
				
				
				
				
				String projectId = null;
				List<TbccPrjType> projList = projectBiz.getHasImagesProjListBybId(new Long(branchId));
				if(projList!=null && projList.size()>0){
					request.setAttribute("projList", projList);
					projectId = projList.get(0).getProjectId();
				}
				
//				String projectId = realrefBiz.getRefPrjId(new Long(branchId));		//��ȡ�����µ���Ŀ��
//				
//				if(projectId==null || projectId==""){
//					new Exception("�������Դ��Ч!");
//				}
				
				
			
				
				List<TbccProjectImages> images = imageBiz.getImagesByPid(projectId);	//������Ŀ����ȡ���е�ͼ����Ϣ
				
				
				request.setAttribute("imageList", images);					//��ȡ���е�ͼ����Ϣ
				
				if(images!=null && images.size()>0){		//�������ͼ��
					TbccProjectImages image =  images.get(0) ;								//Ĭ�ϻ�ȡ��һ��ͼ��
					
					String info = imageBiz.getEachControlAllDates(image.getId(),projectId);			//��ȡ��һ����Ϣ 
					request.setAttribute("info", info);										//�����ݴ�ŵ�request
				}
							
				request.setAttribute("projectId", projectId);
				
				return mapping.findForward("realfloor2");
			}
			
			/**
			 * �ı�ͼ��ʱ���ܵ�����
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
				//��ȡͼ��Id
				String imageId = request.getParameter("myimage");
				
				String projectId = request.getParameter("projectId");
				
				if(imageId==null || imageId==""){
					request.setAttribute("errorMsg", "����Ϊ�ջ�Ƿ�!");
					logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+  " (toChangeImage)��ȡͼ���ʾId��Ч");
					throw new Exception("�������Դ��Ч!");
				}
				
				String info = imageBiz.getEachControlAllDates(Long.parseLong(imageId),projectId);
				request.setAttribute("info", info);
				return mapping.findForward("realfloor2");
			}
			
			
			/**
			 * ��ת���ͻ������ʵʱ����ˢ��ҳ��
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
					request.setAttribute("errorMsg", "�÷�֧���������ڻ��Ѿ�ɾ��!");
					logger.error(((TbccBaseUser)request.getSession().getAttribute("LoginUser")).getUname()+ " (toCustomerReal)�÷�֧���������ڻ��Ѿ�ɾ��!");
					throw new Exception("�����Ƿ�...");
				}
					
				
				//����ͻ���֧��Ϣ
				request.setAttribute("b", b) ;
				
				//����ͻ���֧��⹤��
				List<TbccPrjType> clientRefPrjList = projectBiz.getRefProjListBybId(new Long(branchId));
				request.setAttribute("clientRefPrjList", clientRefPrjList);
				
				return mapping.findForward("customer") ;
				
			}
			
	
	
}
