<%@ page language="java" import="java.util.*,org.tbcc.entity.TbccBaseHisCar,java.text.DecimalFormat" pageEncoding="gbk"%>
<%@include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>�ƶ�������ʷ���ݲ鿴</title>
<link rel="Shortcut Icon" href="img/add/logo.ico" />
<link href="css/chezai/tcd.css" rel="stylesheet" type="text/css" />
<link href="css/chezai/input5.css" rel="stylesheet" type="text/css" />
<script src="DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="script/cExcel.js"></script>
<script src="script/common.js"></script>



<script type="text/javascript">
	function golist(url,operate){
		document.myform.time1.value = document.getElementById('t1').value ;
		document.myform.time2.value = document.getElementById('t2').value ;
		document.getElementById('ope').value = operate ;
		document.getElementById('myform').action = url ;
		document.myform.submit();
	}
	
		function query(){
			
				var val = document.getElementById("timevalue").value;
				var inter = document.getElementById("interval").value;
				
				if(val=="" || val ==0)
				{
					window.alert("��ѡ����ʼ����!");
					document.getElementById("timevalue").focus() ;
					return  ;
				}
				
				document.getElementById('myform').action = 'hiscar.do' ;
				document.getElementById('ope').value = 'doHisCarByStart' ;
				document.myform.submit() ;
			}
			
			/**
			*	��ʾ��ѯ�������Ϣ
			**/
			function showInfo(obj){
				var intervalValue = document.getElementById("interval").value ;
				var total = obj.value * intervalValue ;
				var str = "�� "+total+"(S)��ѯ"
				document.getElementById("Queryinfo").innerHTML = str ;
				document.getElementById("queryinfoTip").value = str ;
			}
	
	
</script>

</head>
<body>
<div>
  <iframe scrolling="no" src="common/header2.jsp" width=100% height=126 frameborder=0></iframe >
</div>
<form action="" method="post" name="myform" id="myform">	
<input type="hidden" name="branchId" id="branchId" value="${param.branchId }" />
<input type="hidden" id="ope" name="ope" value =""/>
<input type="hidden" name="t1" id="t1" value="${param.t1 }"/>
<input type="hidden" name="t2" id="t2"  value="${param.t2 }"/>
<input type="hidden" id="sid" name="sid" value="${param.sid}"/>
<input type="hidden" id="proId" name="proId" value="${project==null?param.proId:project.projectId }"/>

<div id="right">
  <div id="top"><a href="#"><img src="images/chezai/icon_c.gif" width="16" height="15"  class="tb4"/>
  <strong>λ��:��ҳ</strong>&lt;�����ڲ鿴�ƶ�������ʷ���ݲ鿴</a>
  <img src="images/chezai/back.gif" width="48" height="20" class="tb3"/>
  <img src="img/util/back.gif" width="48" height="20"  class="tb3" style="cursor: pointer;" onclick="javascript:golist('startup.do','doStartUpCar');"/>
  <img src="images/chezai/chaxun.gif" width="58" height="21"  class="tb5" style="cursor: pointer;" onclick="javascript:query();" />
  <img src="images/chezai/da_confirm.gif" width="58" height="21"  class="tb6" style="cursor: pointer; display:${hiscarList==null?'none':'inline' }" onclick="printData(time1.value,time2.value);"/></div>
  <div id="center"></div>
  <div id="center">
    <table width="100%" id="bd3">
      <tr>
        <td width="65" style="width:65px;">
        <h3 style="text-align:left; font-size:12px;padding-left:0px; padding-right:0px; width:65px; color:#454343;">�������ƣ�</h3></td>
        <td style="width:160px;">
        <input type="text"  id="proName" name="proName" value="${project==null?param.proName:project.projectName }" readonly="readonly" 
									></td>
									
        <td width="63" style="width:65px;">
        <h3 style="text-align:left; font-size:12px;padding-left:0px; padding-right:0px; width:65px; color:#454343;">��ʼʱ�䣺</h3></td>
        <td style="width:160px;">
        <input  name="time1" id="d4311" value="${startup==null?param.time1:startup.btimeStr}"  class="Wdate" type="text"
									 readonly="readonly"/> 
          
          </td>
        <td width="65" style="width:65px;">
        <h3 style="text-align:left; font-size:12px;padding-left:0px; padding-right:0px; width:65px; color:#454343;">��ֹʱ�䣺</h3></td>
        <td style="width:160px;">
       		<input name ="time2" id="d4312" value="${startup==null?param.time2:startup.etimeDisplay}" class="Wdate" type="text"
									 readonly="readonly"/>
          </td>
        <td style="width:85px;"><h3 style="text-align:left; font-size:12px; padding-left:0px; padding-right:0px; width:95px; color:#454343;">��ѯ���������</h3></td>
        <td width="183"><span style="float:left;">
         <input id="timevalue" name="timevalue" type="text" size="4"  onblur="showInfo(this);"
										value="${param.timevalue==null?1:param.timevalue }" onKeyPress="return isNumber(event)"/>
		 <input type="hidden" name="queryinfoTip" id="queryinfoTip" value="${param.queryinfoTip }"/>
						<span id="Queryinfo">
										${param.queryinfoTip }
									</span>			
          </span></td>
      </tr>
    </table>
  </div>
  <div id="center">
    <table width="100%" id="bd3">
      <tr>
        <td width="65" style="width:65px;"><h3 style="text-align:left; font-size:12px;padding-left:0px; padding-right:0px; width:65px; color:#454343;">�����أ�</h3></td>
        <td style="width:160px;">
        
        <input name="beginaddr" id="beginaddr" value="${startup==null?param.beginaddr:startup.beginAddress }" readonly="readonly"/>
        
        </td>
        <td width="63" style="width:65px;"><h3 style="text-align:left; font-size:12px;padding-left:0px; padding-right:0px; width:65px; color:#454343;">Ŀ�ĵأ�</h3></td>
        <td style="width:160px;">
        <input name="endaddr" id="endaddr" value="${startup==null?param.endaddr:startup.endAddress }" readonly="readonly" />
          </td>
          
        <td width="65" style="width:65px;"><h3 style="text-align:left; font-size:12px;padding-left:0px; padding-right:0px; width:65px; color:#454343;">�����ˣ�</h3></td>
        <td style="width:160px;">
        
        <input name="carrier" id="carrier" value="${startup==null?param.carrier:startup.carrier }" readonly="readonly" />
          
          </td>
        <td style="width:85px;"><h3 style="text-align:left; font-size:12px; padding-left:0px; padding-right:0px; width:95px; color:#454343;">��ͣ�����</h3></td>
        <td width="163"><span style="float:left;">
          <input id="interval" name="interval" value="${startup==null?param.interval:startup.recordInterval}" 
												readonly="readonly"  size="3" />(S)
          </span></td>
      </tr>
    </table>
  </div>
  <div id="bottom">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" id="tb"  >
      <tr id="tb1">
        <td width="120">ʱ�� </td>
        <td width="70">T1</td>
        <td width="70">T2</td>
        <td width="70">T3</td>
        <td width="70">RH1 </td>
        <td width="70">��С�¶� </td>
        <td width="70">����¶�</td>
        <td width="70">ƽ���¶� </td>
        <td width="70">��Сʪ�� </td>
        <td width="70">���ʪ�� </td>
        <td width="70">ƽ��ʪ�� </td>
        <td width="80">����״̬</td>
      </tr>
      <logic:notEmpty name="hiscarList">
				<logic:iterate id="hiscar" name="hiscarList" indexId="irow">
				
					<tr ${irow%2==0?"class='altrow'":"" }>
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
						
						<%-- 
						<td align="center" nowrap="nowrap">
						${hiscar.latitudeStr }
						
						</td>
						
						<td align="center" nowrap="nowrap">
						${hiscar.longitudeStr }				
						</td>
						 --%>
						 
						<td align="center" nowrap>
							${hiscar.minAi==-300?"--":hiscar.minAi }
						</td>
						<td align="center" nowrap>
							${hiscar.maxAi==-300?"--":hiscar.maxAi }
						</td>
						
						<td align="center" nowrap>
							<logic:equal value="-300" name="hiscar" property="avgAi" >
								--
							</logic:equal>
							<logic:notEqual value="-300" name="hiscar" property="avgAi" >
								<bean:write name="hiscar" property="avgAi" format="#.##"/>
							</logic:notEqual>
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
							${hiscar.alarmStatusStr}
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
							${max[2]==-300?"--":max[2]}
						</td>
						<td align="center" nowrap>
							${max[3]==-300?"--":max[3]}
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
    </table>
    <div>
    		<logic:empty name="hiscarList">
    			<div style="height: 450px">&nbsp;</div>
			</logic:empty>
			
				<%--Ϊ��Ҳû���ͳһ�����ݼ�¼���������и� --%>
			    <logic:notEmpty name="hiscarList">
			    	<bean:size id="rheight" name="hiscarList" />
			    	
			    	<c:if test="${rheight lt 10}">
			    		<div style="height: 250px">&nbsp;</div>
			    	</c:if>
			    	
			    	<c:if test="${rheight ge 10 }">
			    		<div style="height: 110px">&nbsp;</div>
			    	</c:if>
			    	
			    </logic:notEmpty>
    </div>
  
  </div>
</div>
</form>
<div class="clear"></div>
<div>
  <iframe scrolling="no" src="common/footer2.jsp" width=100% height=43 frameborder=0></iframe >
</div>
</body>
</html>
