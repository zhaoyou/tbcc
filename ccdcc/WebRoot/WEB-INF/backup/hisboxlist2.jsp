<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="common/taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>历史小批零数据查询</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
		<link href="css/style.css" rel="stylesheet" type="text/css">
		<LINK href="css/mainWin.css" type=text/css media=screen rel=stylesheet>
		<LINK href="css/mainWin2.css" type=text/css media=screen rel=stylesheet>
		<LINK href="css/desktop.css" type=text/css media=screen rel=stylesheet>
		 <link rel="Shortcut Icon" href="img/add/logo.ico" >
		 
		<script src="script/common.js"></script>

		<script src="DatePicker/WdatePicker.js"></script>
		
		<script type="text/javascript" src="script/titleTime.js"></script>
		<script type="text/javascript" src="script/queryTime.js"></script> 
        <script type="text/javascript">
        	//查询数据
			function query(){
				
				var val = document.getElementById("timevalue").value;
				var inter = document.getElementById("interval").value;
				
				if(val=="" || val==0){
					window.alert("请选择合适的时间间隔!");
					document.getElementById("timevalue").focus();
					return ;
				}
				
				
				
				document.myform.submit() ;
			}
			
			function showInfo(obj){
				var intervalValue = document.getElementById("interval").value ;
				var total = obj.value * intervalValue ;
				var str = "隔 "+total+"(S)查询"
				document.getElementById("Queryinfo").innerHTML = str ;
			}
			
			/**
			  *返回历史数据查询页面
			  */
			function goback(){
				document.myform.time1.value = document.myform.t1.value ;
				document.myform.time2.value = document.myform.t2.value ;
				document.myform.action = 'startup.do?ope=doStartUpBox' ;
				document.myform.submit() ;
			}
        </script>
  
<script type="text/javascript" src="script/bExcel3.js"></script></head>
  
 <body onload="showtime();" bgcolor="#FFFFFF" leftmargin="0" topmargin="4" marginwidth="0" marginheight="0">
 		<jsp:include page="header.jsp" flush="true"></jsp:include>		
		<table width="990" border="0" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#CCCCCC" bordercolor="#FFFFFF"
			bordercolordark="#FFFFFF">		
			<td width="990" height="550" colspan="3" valign="top"
					background="img/stock_index_08.gif">
					<div id="main" style="width:990px; height:100%; float:left" >
						<div class="page_title">您正在做的业务是：<img src="img/add/club.JPG" alt=">>"><font size="2px">&nbsp;小批零历史数据查看</font></div>
						<form name="myform" action="hisbox.do?ope=doHisBoxByStart" method="post">
							<div class="button_bar">
							<input type="hidden" id="branchId" name="branchId" value="${param.branchId }">
								<input type="hidden" id="t1" name="t1" value="${param.t1 }"/>
								<input type="hidden" id="t2" name="t2" value="${param.t2 }"/>
								<button class="common_button" onclick="query()" type="button">
									查询
								</button>
								&nbsp;
								<input type="button" value="打印" class="common_button" ${hisboxList==null ?"disabled='disabled'":"" } name="out_word1" 
								onclick="printData(time1.value,time2.value);"/>
								&nbsp;
								<button class="common_button" onclick="javascript:goback();" type="button">
									返回
									<%--window.location.href='startup.do?ope=doStartUpBox&branchId=${param.branchId}&proId=${param.proId }&time1=${param.time1 }&time2=${param.time2 }' --%>
								</button>
								&nbsp;
								<button class="common_button" type="button" onclick="javascript:window.location.href='branch.do?ope=toListByBranch&branchId=${param.branchId }'">
									返回主页面
								</button>
							</div>
							
							<table class="query_form_table">
								<tr>
								
									<th>
										小批零名称:
									</th>
									<td>
									  <input type="hidden" id="proId" name="proId" value="${project==null?param.proId:project.projectId }"/>
									  <input type="text" id="proName"  name="proName" value="${project==null?param.proName:project.projectName }" readonly="readonly" 
									  style="border-style: solid">
									</td>
									<th>
										起始时间:
									</th>
									<td >
										<input  name="time1" id="d4311" value="${startup==null?param.time1:startup.btimeStr}"  class="Wdate" type="text"
										 readonly="readonly" />	</td>
									<%-- 默认时间为启停时间段
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})"
									 --%>
									<th>
										终止时间:
									</th>
									<td >
										<input name ="time2" id="d4312" value="${startup==null?param.time2:startup.etimeDisplay}" class="Wdate" type="text"
									 readonly="readonly" />
									<%-- 默认为启停时间段
									onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'#F{$dp.$D(\'d4311\',{d:7});}'})"
									--%>
									</td>
									<th>
										间隔数量:
									</th>
									<td>
									<input id="timevalue" name="timevalue" type="text" size="4" value="${param.timevalue==null?1:param.timevalue}"
									onblur="showInfo(this);" onKeyPress="return isNumber(event)"/>
										<span id="Queryinfo"></span>
									</td>
								</tr>
								<tr>
									<th>出发地:</th>
											<td >
												<input name="beginaddr" id="beginaddr" value="${startup==null?param.beginaddr:startup.beginAddress }" readonly="readonly" style="border-style: solid;"/>
											</td>
											<th >目的地:</th>
											<td>
												<input name="endaddr" id="endaddr" value="${startup==null?param.endaddr:startup.endAddress }" readonly="readonly" style="border-style: solid;"/>
											</td>
											<th >承运人:</th>
											<td>
												<input name="carrier" id="carrier" value="${startup==null?param.carrier:startup.carrier }" readonly="readonly" style="border-style: solid;">
											</td>
											<th>启停间隔:</th>
											<td >
												<input id="interval" name="interval" value="${startup==null?param.interval:startup.recordInterval}" 
												readonly="readonly" style="border-style: solid" size="3" >(S)
											</td>			
								</tr>
							</table>
				</form>
			<!-- 列表从这开始 -->
			<TABLE BORDER=0 CELLPADDING=0 CELLSPACING=0 WIDTH="990" align="center"  id="data" >
				<TR HEIGHT=23 CLASS=Page_tools_bar>
					<TD CLASS=Page_title align="center"> 
						时间
					</TD>
					<TD CLASS=Page_title align="center"> 
						温度
					</TD>
					<TD CLASS=Page_title align="center">
						经度方向
					</TD>
					<TD CLASS=Page_title align="center">
						经度数据
					</TD>
					<TD CLASS=Page_title align="center">
						纬度方向
					</TD>
					<TD CLASS=Page_title align="center">
						纬度数据
					</TD>	
					<TD CLASS=Page_title align="center">
						报警状态
					</TD>	
				</TR>		
				<logic:notEmpty name="hisboxList">
				<logic:iterate id="hisbox" name="hisboxList">
					<tr onMouseOver="this.style.backgroundColor = '#EEEEEE';"
						onMouseOut="this.style.backgroundColor = ''">
						<td align="center" >
							<bean:write name="hisbox" property="updateTime" format="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td align="center" >
							<bean:write name="hisbox" property="ai1"/>
						</td>
						<td align="center" >
							${hisbox.longitude_dir==0?"东经":"西经" }						
						</td>
						<td align="center" >
							${hisbox.longitudeStr}
						</td>
						<td align="center" > 
							${hisbox.longitude_dir==0?"南纬":"北纬"}
						</td>
						<td align="center" >
							${hisbox.latitudeStr }
						</td>
						<td align="center" >
						<%-- ${hisbox.alarmStatus==1?"<font color='red'>报警</font>":"正常"}--%>	
							${hisbox.alarmStatusStr }			
						</td>
					</tr>
				</logic:iterate>
				<!-- 控制数据和统计信息的分开 --> 
				<tr>
					<td colspan="7">
						<hr color="pink">
					</td>
				</tr>
				<tr>
					<td align="center"><font color="blue" size="4">统计</font></td>
					<td align="center"><font color="blue" size="3">温度</font></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td align="center"><font color="blue" size="3">最大值</font></td>
					<td align="center">${max==-300?"--":max}</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td align="center"><font color="blue" size="3">最小值</font></td>
					<td align="center">${min==-300?"--":min}</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td align="center"><font color="blue" size="3">平均值</font></td>
					<td align="center">${avg==-300?"--":avg}</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				
				
				
				
			</logic:notEmpty>
			<logic:empty name="hisboxList">
					<tr>
						<td height="25" colspan="7" align="center" class="STYLE1"
							style="border-style:inherit;">
							<span style="color:red">${flag==null?"选择的时间范围内没有的数据！":""}</span>
						</td>			
					</tr>
			</logic:empty>
			
			</TABLE>
				<%--</div> 
				</td> --%>		
			</table>
			<jsp:include page="footer.jsp" flush="true"></jsp:include>
	</body>
</html>
