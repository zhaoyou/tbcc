<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="common/taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>��ʷС�������ݲ�ѯ</title>
    
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
        	//��ѯ����
			function query(){
				
				var val = document.getElementById("timevalue").value;
				var inter = document.getElementById("interval").value;
				
				if(val=="" || val==0){
					window.alert("��ѡ����ʵ�ʱ����!");
					document.getElementById("timevalue").focus();
					return ;
				}
				
				
				
				document.myform.submit() ;
			}
			
			function showInfo(obj){
				var intervalValue = document.getElementById("interval").value ;
				var total = obj.value * intervalValue ;
				var str = "�� "+total+"(S)��ѯ"
				document.getElementById("Queryinfo").innerHTML = str ;
			}
			
			/**
			  *������ʷ���ݲ�ѯҳ��
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
						<div class="page_title">����������ҵ���ǣ�<img src="img/add/club.JPG" alt=">>"><font size="2px">&nbsp;С������ʷ���ݲ鿴</font></div>
						<form name="myform" action="hisbox.do?ope=doHisBoxByStart" method="post">
							<div class="button_bar">
							<input type="hidden" id="branchId" name="branchId" value="${param.branchId }">
								<input type="hidden" id="t1" name="t1" value="${param.t1 }"/>
								<input type="hidden" id="t2" name="t2" value="${param.t2 }"/>
								<button class="common_button" onclick="query()" type="button">
									��ѯ
								</button>
								&nbsp;
								<input type="button" value="��ӡ" class="common_button" ${hisboxList==null ?"disabled='disabled'":"" } name="out_word1" 
								onclick="printData(time1.value,time2.value);"/>
								&nbsp;
								<button class="common_button" onclick="javascript:goback();" type="button">
									����
									<%--window.location.href='startup.do?ope=doStartUpBox&branchId=${param.branchId}&proId=${param.proId }&time1=${param.time1 }&time2=${param.time2 }' --%>
								</button>
								&nbsp;
								<button class="common_button" type="button" onclick="javascript:window.location.href='branch.do?ope=toListByBranch&branchId=${param.branchId }'">
									������ҳ��
								</button>
							</div>
							
							<table class="query_form_table">
								<tr>
								
									<th>
										С��������:
									</th>
									<td>
									  <input type="hidden" id="proId" name="proId" value="${project==null?param.proId:project.projectId }"/>
									  <input type="text" id="proName"  name="proName" value="${project==null?param.proName:project.projectName }" readonly="readonly" 
									  style="border-style: solid">
									</td>
									<th>
										��ʼʱ��:
									</th>
									<td >
										<input  name="time1" id="d4311" value="${startup==null?param.time1:startup.btimeStr}"  class="Wdate" type="text"
										 readonly="readonly" />	</td>
									<%-- Ĭ��ʱ��Ϊ��ͣʱ���
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})"
									 --%>
									<th>
										��ֹʱ��:
									</th>
									<td >
										<input name ="time2" id="d4312" value="${startup==null?param.time2:startup.etimeDisplay}" class="Wdate" type="text"
									 readonly="readonly" />
									<%-- Ĭ��Ϊ��ͣʱ���
									onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'#F{$dp.$D(\'d4311\',{d:7});}'})"
									--%>
									</td>
									<th>
										�������:
									</th>
									<td>
									<input id="timevalue" name="timevalue" type="text" size="4" value="${param.timevalue==null?1:param.timevalue}"
									onblur="showInfo(this);" onKeyPress="return isNumber(event)"/>
										<span id="Queryinfo"></span>
									</td>
								</tr>
								<tr>
									<th>������:</th>
											<td >
												<input name="beginaddr" id="beginaddr" value="${startup==null?param.beginaddr:startup.beginAddress }" readonly="readonly" style="border-style: solid;"/>
											</td>
											<th >Ŀ�ĵ�:</th>
											<td>
												<input name="endaddr" id="endaddr" value="${startup==null?param.endaddr:startup.endAddress }" readonly="readonly" style="border-style: solid;"/>
											</td>
											<th >������:</th>
											<td>
												<input name="carrier" id="carrier" value="${startup==null?param.carrier:startup.carrier }" readonly="readonly" style="border-style: solid;">
											</td>
											<th>��ͣ���:</th>
											<td >
												<input id="interval" name="interval" value="${startup==null?param.interval:startup.recordInterval}" 
												readonly="readonly" style="border-style: solid" size="3" >(S)
											</td>			
								</tr>
							</table>
				</form>
			<!-- �б���⿪ʼ -->
			<TABLE BORDER=0 CELLPADDING=0 CELLSPACING=0 WIDTH="990" align="center"  id="data" >
				<TR HEIGHT=23 CLASS=Page_tools_bar>
					<TD CLASS=Page_title align="center"> 
						ʱ��
					</TD>
					<TD CLASS=Page_title align="center"> 
						�¶�
					</TD>
					<TD CLASS=Page_title align="center">
						���ȷ���
					</TD>
					<TD CLASS=Page_title align="center">
						��������
					</TD>
					<TD CLASS=Page_title align="center">
						γ�ȷ���
					</TD>
					<TD CLASS=Page_title align="center">
						γ������
					</TD>	
					<TD CLASS=Page_title align="center">
						����״̬
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
							${hisbox.longitude_dir==0?"����":"����" }						
						</td>
						<td align="center" >
							${hisbox.longitudeStr}
						</td>
						<td align="center" > 
							${hisbox.longitude_dir==0?"��γ":"��γ"}
						</td>
						<td align="center" >
							${hisbox.latitudeStr }
						</td>
						<td align="center" >
						<%-- ${hisbox.alarmStatus==1?"<font color='red'>����</font>":"����"}--%>	
							${hisbox.alarmStatusStr }			
						</td>
					</tr>
				</logic:iterate>
				<!-- �������ݺ�ͳ����Ϣ�ķֿ� --> 
				<tr>
					<td colspan="7">
						<hr color="pink">
					</td>
				</tr>
				<tr>
					<td align="center"><font color="blue" size="4">ͳ��</font></td>
					<td align="center"><font color="blue" size="3">�¶�</font></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td align="center"><font color="blue" size="3">���ֵ</font></td>
					<td align="center">${max==-300?"--":max}</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td align="center"><font color="blue" size="3">��Сֵ</font></td>
					<td align="center">${min==-300?"--":min}</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td align="center"><font color="blue" size="3">ƽ��ֵ</font></td>
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
							<span style="color:red">${flag==null?"ѡ���ʱ�䷶Χ��û�е����ݣ�":""}</span>
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
