<%@ page language="java" import="java.util.*,org.tbcc.entity.TbccBaseHisCar,java.text.DecimalFormat" pageEncoding="gbk"%>
<%@include file="common/taglib.jsp" %>

<html>
	<head>
		<title>�ƶ�������ʷ���ݲ鿴</title>
		<link href="css/style.css" rel="stylesheet" type="text/css">
		<LINK href="css/mainWin.css" type=text/css media=screen rel=stylesheet>
		<LINK href="css/mainWin2.css" type=text/css media=screen rel=stylesheet>
		<LINK href="css/desktop.css" type=text/css media=screen rel=stylesheet>
		 <link rel="Shortcut Icon" href="img/add/logo.ico" >
		<script src="script/common.js"></script>
		<script type="text/javascript" src="script/Exce.js"></script>
		<script src="DatePicker/WdatePicker.js"></script>
		
		<script type="text/javascript" src="script/titleTime.js"></script>
		<script type="text/javascript" src="script/queryTime.js"></script>
		<style type="text/css">
			.STYLE1 {font-size: 12px}
        </style>

	<style type="css/text">
			.div_top{
				border-top: solid 1px #4986D4;
			}
		</style>
	</head>
	
	<body onload="showtime();" bgcolor="#FFFFFF" leftmargin="0" topmargin="4" marginwidth="0" marginheight="0">
		<%@include file="header.jsp" %>
		<table width="990" border="0" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#CCCCCC" bordercolor="#FFFFFF"
			bordercolordark="#FFFFFF">
			
			<td width="990" height="550" colspan="3" valign="top"
					background="img/stock_index_08.gif">
					<div id="main" style="width:990px; height:100%; float:left" >
						<div class="page_title">����������ҵ���ǣ���ʷ������Ϣ�鿴&gt;&gt;&gt;</div>
							<form  name="myform" action="hiscar.do?ope=doHisCarData" method="post">
							<div class="button_bar">
								<input type="hidden" id="branchId"  name="branchId" value="${param.branchId }">
								<button class="common_button"  onClick="Interval();" type="button">
									��ѯ
								</button>		
								<button type="button" class="common_button" ${hiscarList==null?"disabled='disabled'":"" }  name="out_word1" onclick="printData(time1.value,time2.value)">
									��ӡ
								</button>
								<button class="common_button" type="button" onclick="javascript:window.location.href='branch.do?ope=toListByBranch&branchId=${param.branchId }'">
									����
								</button>
							</div>
							<table class="query_form_table">
							
								
							
								<tr>
								<th>��ѡ���أ�</th>
								<td>
									<select name="proId">
											<c:forEach var="proj" items="${carprjList}">		
												<option value='${proj.projectId}' ${param.proId==proj.projectId?"selected=selected":"" }>${proj.projectName }</option>"
											</c:forEach>
									</select>
								</td>
									<th>
										��ʼʱ��:
									</th>
									<td>
										<input  name="time1" id="d4311" value="${param.time1}"  class="Wdate" type="text"
										 onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})"/> 
									</td>
									
									<th>
										��ֹʱ��:
									</th>
									<td>
										<input name ="time2" id="d4312" value="${param.time2}" class="Wdate" type="text"
									 onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'#F{$dp.$D(\'d4311\',{d:7});}'})"/>
									</td>
									<th>
										���:
									</th>
									<td>
									<input id="timevalue" name="timevalue" type="text" size="4" value="${param.timevalue }" onkeypress="return isNumber()"/>
										<select name="timeType">
												<option value="3"  ${param.timeType==3?"selected=selected":" " }>
													��
												</option>
												<option value="2"  ${param.timeType==2?"selected=selected":" " }>
													����
												</option>
												<option value="1"  ${param.timeType==1?"selected=selected":" " }>
													Сʱ
												</option>
										</select>
									</td>
								</tr>
							</table>
				</form>
			<!-- �б���⿪ʼ -->
			<TABLE BORDER=0 CELLPADDING=0 CELLSPACING=0 WIDTH="990" align="center" id="data">
				<TR HEIGHT=23 CLASS=Page_tools_bar>
					<TD CLASS=Page_title align="center"> 
						ʱ��
					</TD>
					<TD CLASS=Page_title align="center"> 
						T1
					</TD>
					<TD CLASS=Page_title align="center">
						T2
					</TD>
					<TD CLASS=Page_title align="center">
						T3
					</TD>
					<TD CLASS=Page_title align="center">
						RH1
					</TD>	
					<TD CLASS=Page_title align="center">
						γ����Ϣ
					</TD>
					<TD CLASS=Page_title align="center">
						������Ϣ
					</TD>
					
					<TD CLASS=Page_title align="center">
						��С�¶�
					</TD>
					<TD CLASS=Page_title align="center"> 
						����¶�
					</TD>
					<TD CLASS=Page_title align="center">
						ƽ���¶�
					</TD>
					
					<TD CLASS=Page_title align="center">
						��Сʪ��
					</TD>
					
					<TD CLASS=Page_title align="center">
						���ʪ��
					</TD>
					
					<TD CLASS=Page_title align="center">
						ƽ��ʪ��
					</TD>	
					<TD CLASS=Page_title align="center">
						����״̬
					</TD>	
				</TR>
							
			<logic:notEmpty name="hiscarList">
				<logic:iterate id="hiscar" name="hiscarList">
				
					<tr onMouseOver="this.style.backgroundColor = '#EEEEEE';"
						onMouseOut="this.style.backgroundColor = ''">
						<td align="center"  nowrap="nowrap">
							<bean:write name="hiscar" property="updateTime" format="yyyy-MM-dd HH:mm:ss"/>
						</td>
						
						<td align="center" nowrap>	
							${hiscar.ai1==-300?"--":hiscar.ai1 }
						</td>
						
						<td align="center" nowrap="nowrap">
							${hiscar.ai2==-300?"--":hiscar.ai2 }
						</td>
						
						<td align="center"  nowrap="nowrap">
							${hiscar.ai3==-300?"--":hiscar.ai3 }
						</td>
						
						<td align="center"  nowrap="nowrap">
							${hiscar.ai4==-300?"--":hiscar.ai4 }
						</td>
						
						<td align="center" nowrap="nowrap">
						${hiscar.latitudeStr }
						
						</td>
						
						<td align="center" nowrap="nowrap">
						${hiscar.longitudeStr }
						
						</td>
						
						<td align="center" nowrap>
							${hiscar.minAi==-300?"--":hiscar.minAi }
						</td>
						<td align="center" nowrap>
							${hiscar.maxAi==-300?"--":hiscar.maxAi }
						</td>
						
						<td align="center" nowrap>
							<bean:write name="hiscar" property="avgAi" format="#.##"/>
						</td>
						<td align="center" nowrap>
							${hiscar.ai4==-300?"--":hiscar.ai4 }						
						</td>
						<td align="center" nowrap>
							${hiscar.ai4==-300?"--":hiscar.ai4 }
						</td>
						<td align="center" nowrap>
							${hiscar.ai4==-300?"--":hiscar.ai4 }
						</td>
						<td align="center" nowrap>
							${hiscar.alarmStatus==1?"<span style='color:red;'>����</span>":"<font color='blue'>����</font>" }
						</td>
					</tr>
				</logic:iterate>			
				<!-- ����������ͳ�Ƶ����� -->		
				<%

					double avg[] = null  ;
					if(request.getAttribute("avg") != null)
					   avg = (double[])(request.getAttribute("avg"));
				 %>	
				<tr>
					<td colspan="14"><hr color="pink"></td>
				</tr>
				
				<TR >
					
					<TD align="center" nowrap> 
						<font color="blue" size="4">ͳ��</font>
					</TD>
					<TD align="center" nowrap > 
						<font color="blue" size="3">T1</font>
					</TD>
					<TD align="center" nowrap > 
						<font color="blue" size="3">T2</font>
					</TD>
					<TD align="center" nowrap>
						<font color="blue" size="3">T3</font>
					</TD>
					<TD align="center" nowrap>
						<font color="blue" size="3">RH1</font>
					</TD>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<TD>&nbsp;</TD>
				</TR>
				
				
					<tr >
						
						<td align="center" nowrap>
							<font color="blue" size="2">���</font>
						</td>
						<td align="center" nowrap>
							${max[0]==-300?"--":max[0]}
						</td>
						<td align="center" nowrap>
							${max[1]==-300?"--":max[1]}
						</td>
						<td align="center" nowrap>
							${max[2]==-300?"--":max[1]}
						</td>
						<td align="center" nowrap>
							${max[3]==-300?"--":max[1]}
						</td>
						<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					</tr>
					<tr >
					
						<td align="center" nowrap>
							<font color="blue" size="2">��С</font>
						</td>
						<td align="center" nowrap>
							${min[0]==-300?"--":min[0]}
						</td>
						<td align="center" nowrap>
							${min[1]==-300?"--":min[1]}
						</td>
						<td align="center" nowrap>
							${min[2]==-300?"--":min[2]}
						</td>
						<td align="center" nowrap>
							${min[3]==-300?"--":min[3]}
						</td>
						<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					</tr>
					<tr >
					
						<td align="center" nowrap>
							<font color="blue" size="2">ƽ��</font>
						</td>
						
						<td align="center" nowrap>
							<%=avg[0]==-300?"--": new DecimalFormat("#.##").format(avg[0]) %>
						</td>
						<td align="center" nowrap>
							<%= avg[1]==-300?"--":new DecimalFormat("#.##").format(avg[1]) %>
						</td>
						<td align="center" nowrap>
							<%= avg[2]==-300?"--":new DecimalFormat("#.##").format(avg[2]) %>
						</td>
						<td align="center" nowrap>
							<%= avg[3]==-300?"--":new DecimalFormat("#.##").format(avg[3]) %>
						</td>
						<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					</tr>
			</logic:notEmpty>
				<logic:empty name="hiscarList">
				
					<tr>
						<td height="25" colspan="14" align="center" class="STYLE1"
							style="border-style:inherit;">
							<span style="font-size: 16px;color: blue">${falg!=null?" ":"û�е�ǰʱ�䷶Χ�ڵ��йص����ݣ�"}</span>
						</td>
							
					</tr>
			</logic:empty> 
			</TABLE>
				<%-- 		</div> 
				</td>--%>
				
			</table>	
			<%@include file="footer.jsp" %>	
	</body>
</HTML>
