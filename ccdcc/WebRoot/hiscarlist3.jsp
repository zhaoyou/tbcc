<%@ page language="java" import="java.util.*,org.tbcc.entity.TbccBaseHisCar,java.text.DecimalFormat" pageEncoding="gbk"%>
<%@include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>移动车载历史数据查看</title>
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
					window.alert("请选择合适间隔数!");
					document.getElementById("timevalue").focus() ;
					return  ;
				}
				
				document.getElementById('myform').action = 'hiscar.do' ;
				document.getElementById('ope').value = 'doHisCarByStart' ;
				document.myform.submit() ;
			}
			
			/**
			*	提示查询间隔的信息
			**/
			function showInfo(obj){
				var intervalValue = document.getElementById("interval").value ;
				var total = obj.value * intervalValue ;
				var str = "隔 "+total+"(S)查询"
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
  <strong>位置:首页</strong>&lt;您正在查看移动车载历史数据查看</a>
  <img src="images/chezai/back.gif" width="48" height="20" class="tb3"/>
  <img src="img/util/back.gif" width="48" height="20"  class="tb3" style="cursor: pointer;" onclick="javascript:golist('startup.do','doStartUpCar');"/>
  <img src="images/chezai/chaxun.gif" width="58" height="21"  class="tb5" style="cursor: pointer;" onclick="javascript:query();" />
  <img src="images/chezai/da_confirm.gif" width="58" height="21"  class="tb6" style="cursor: pointer; display:${hiscarList==null?'none':'inline' }" onclick="printData(time1.value,time2.value);"/></div>
  <div id="center"></div>
  <div id="center">
    <table width="100%" id="bd3">
      <tr>
        <td width="65" style="width:65px;">
        <h3 style="text-align:left; font-size:12px;padding-left:0px; padding-right:0px; width:65px; color:#454343;">车载名称：</h3></td>
        <td style="width:160px;">
        <input type="text"  id="proName" name="proName" value="${project==null?param.proName:project.projectName }" readonly="readonly" 
									></td>
									
        <td width="63" style="width:65px;">
        <h3 style="text-align:left; font-size:12px;padding-left:0px; padding-right:0px; width:65px; color:#454343;">起始时间：</h3></td>
        <td style="width:160px;">
        <input  name="time1" id="d4311" value="${startup==null?param.time1:startup.btimeStr}"  class="Wdate" type="text"
									 readonly="readonly"/> 
          
          </td>
        <td width="65" style="width:65px;">
        <h3 style="text-align:left; font-size:12px;padding-left:0px; padding-right:0px; width:65px; color:#454343;">终止时间：</h3></td>
        <td style="width:160px;">
       		<input name ="time2" id="d4312" value="${startup==null?param.time2:startup.etimeDisplay}" class="Wdate" type="text"
									 readonly="readonly"/>
          </td>
        <td style="width:85px;"><h3 style="text-align:left; font-size:12px; padding-left:0px; padding-right:0px; width:95px; color:#454343;">查询间隔数量：</h3></td>
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
        <td width="65" style="width:65px;"><h3 style="text-align:left; font-size:12px;padding-left:0px; padding-right:0px; width:65px; color:#454343;">出发地：</h3></td>
        <td style="width:160px;">
        
        <input name="beginaddr" id="beginaddr" value="${startup==null?param.beginaddr:startup.beginAddress }" readonly="readonly"/>
        
        </td>
        <td width="63" style="width:65px;"><h3 style="text-align:left; font-size:12px;padding-left:0px; padding-right:0px; width:65px; color:#454343;">目的地：</h3></td>
        <td style="width:160px;">
        <input name="endaddr" id="endaddr" value="${startup==null?param.endaddr:startup.endAddress }" readonly="readonly" />
          </td>
          
        <td width="65" style="width:65px;"><h3 style="text-align:left; font-size:12px;padding-left:0px; padding-right:0px; width:65px; color:#454343;">承运人：</h3></td>
        <td style="width:160px;">
        
        <input name="carrier" id="carrier" value="${startup==null?param.carrier:startup.carrier }" readonly="readonly" />
          
          </td>
        <td style="width:85px;"><h3 style="text-align:left; font-size:12px; padding-left:0px; padding-right:0px; width:95px; color:#454343;">启停间隔：</h3></td>
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
        <td width="120">时间 </td>
        <td width="70">T1</td>
        <td width="70">T2</td>
        <td width="70">T3</td>
        <td width="70">RH1 </td>
        <td width="70">最小温度 </td>
        <td width="70">最大温度</td>
        <td width="70">平均温度 </td>
        <td width="70">最小湿度 </td>
        <td width="70">最大湿度 </td>
        <td width="70">平均湿度 </td>
        <td width="80">报警状态</td>
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
				<!-- 控制数据与统计的数据 -->		
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
						<font color="blue" size="4">统计</font>
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
							<font color="blue" size="2">最大</font>
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
							<font color="blue" size="2">最小</font>
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
							<font color="blue" size="2">平均</font>
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
			
				<%--为了也没风格统一，根据记录条数设置行高 --%>
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
