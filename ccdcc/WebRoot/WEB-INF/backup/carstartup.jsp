<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>�鿴�ƶ����ص���ͣ��¼</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link href="css/style.css" rel="stylesheet" type="text/css">
		<LINK href="css/mainWin.css" type=text/css media=screen rel=stylesheet>
		<LINK href="css/mainWin2.css" type=text/css media=screen rel=stylesheet>
		<LINK href="css/desktop.css" type=text/css media=screen rel=stylesheet>
		 <link rel="Shortcut Icon" href="img/add/logo.ico" >
		<script src="script/common.js"></script>
		<script type="text/javascript" src="script/Exce.js"></script>
		<script src="DatePicker/WdatePicker.js"></script>
		
		<script type="text/javascript" src="script/titleTime.js"></script>
	
		<script type="text/javascript">
			function getStartData(){
				var time1 = document.getElementById("d4311").value ;
				var time2 = document.getElementById("d4312").value ;
				if(time1=="" || time2=="" ){
					window.alert("��ѡ����ʼʱ��!");
					return  ;
				}
				
				if(!(isvalid(time1) && isvalid(time2)))
				{
					window.alert("������Ϸ������ڸ�ʽ!");
					return  ;
				}
							
				var t1 = buildDate(time1);
				var t2 = buildDate(time2);
				
				if((t2-t1)/1000/60/60/24>30){
					window.alert("��ʼʱ�������ܳ���30��!");
					return  ;
				}
				
				document.myform.submit() ;
			}
			
			function isvalid(str){
				if(str.charAt(4)=="-" && str.charAt(7)=="-" &&
				   str.charAt(10)==" " && str.charAt(13)==":" && str.charAt(16)==":")
					return true ;
				return false ;
			}
			
			/**
			*	����һ���ַ���yyyy-MM-dd HH:mm:ss����һ��Date����
			*/
			function buildDate(str){
			var t2 = str.split(" ");
			var date1 = t2[0].split("-");
			var date2 = t2[1].split(":");								
		    var day = new Date(date1[0],date1[1],date1[2],date2[0],date2[1],date2[2]);
			return day ;
		}
		
		
			/*** ת����ʷ����ҳ��
			*/
			
			function goHis(sid,flag){
				if(flag==true){
					document.getElementById("sid").value = sid ;
					document.selectForm.submit() ;
				}
			}
			
				/**
   				 *	ʵ��Ȩ�޵���ת
   				 */
   				 function goPowerURL(url,flag){
   				 	if(flag==true){
   				 		window.location.href = url ;
   				 	}
   				 }
		</script>
	
  </head>
  
  <body onload="showtime();">
    	<jsp:include page="header.jsp" flush="true"></jsp:include>
		<table width="990" border="0" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#CCCCCC" bordercolor="#FFFFFF"
			bordercolordark="#FFFFFF">
			
			<td width="990" height="550" colspan="3" valign="top"
					background="img/stock_index_08.gif">
					<div id="main" style="width:990px; height:100%; float:left" >
						<div class="page_title">����������ҵ���ǣ�<img src="img/add/club.JPG" alt=">>"><font size="2px">&nbsp;������ͣ���ݲ鿴</font></div>
							<form  name="myform" action="startup.do?ope=doStartUpCar" method="post">
							<div class="button_bar">
								<input type="hidden" id="branchId"  name="branchId" value="${param.branchId }">
								<input class="common_button"  onClick="getStartData()" type="button" value="��ѯ��ͣ��¼"/>
								<input class="common_button" onClick="javascript:window.location.href='branch.do?ope=toListByBranch&branchId=${param.branchId }'"
								type="button" value="������ҳ��"/>
								
							</div>
							<table class="query_form_table">
								<tr>
								<th>��ѡ���أ�</th>
								<td>
									<select name="proId" id="proId">
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
									<%--
									WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})
									 --%>
									</td>
									
									<th>
										��ֹʱ��:
									</th>
									<td>
										<input name ="time2" id="d4312" value="${param.time2}" class="Wdate" type="text"
									 onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01'})"/>
										<%--
											WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'#F{$dp.$D(\'d4311\',{d:7});}'})
										 --%>
									</td>
								</tr>
								</table>
								</form>
								<% int i = 1 ; %>
								<form method="post" name="selectForm" action="hiscar.do?ope=toHisCarByStart">
			<TABLE BORDER=0 CELLPADDING=0 CELLSPACING=0 WIDTH="990"  align="center"  id="data">
				<TR HEIGHT=23 CLASS=Page_tools_bar>
					<TD CLASS=Page_title align="center" width="50"> 
						���
					</TD>
					<TD CLASS=Page_title align="center"> 
						����ʱ��
					</TD>
					<TD CLASS=Page_title align="center">
						����ʱ��
					</TD>
					<TD CLASS=Page_title align="center">
						������
					</TD>
					<TD CLASS=Page_title align="center">
						Ŀ�ĵ�
					</TD>
					<TD CLASS=Page_title align="center">
						������
					</TD>	
					<TD CLASS=Page_title align="center">
						������
					</TD>
					<TD CLASS=Page_title align="center">
						�ջ���
					</TD>	
					<Td CLASS=Page_title align="center">
						״̬
					</Td>
					<TD CLASS=Page_title align="center" width="100">
						����
					</TD>
				</TR>
				<c:forEach var="startup" items="${startList}">
					<tr align="center" onMouseOver="this.style.backgroundColor = '#EEEEEE';"
						onMouseOut="this.style.backgroundColor = ''">
						<td><%=(i++) %></td>
						<td>${startup.btimeStr }</td>
						<td>${startup.etimeStr }</td>
						<td>${startup.beginAddress }</td>
						<td>${startup.endAddress }</td>
						<td>${startup.shipper }</td>
						<td>${startup.carrier }</td>
						<td>${startup.receiver }</td>
						<td>${startup.updateStatus==2?"�ϴ����":"δ����ϴ�" }</td>
						<td>
						
							<img alt="��ѯ��ʷ����" src="img/menu/bt_plan.gif"  style="cursor: pointer"
							onclick="javascript:goHis(${startup.id},true)"/>
							<%--window.location.href='hiscar.do?ope=toHisCarByStart&branchId=${param.branchId}&proId=${param.proId }&sid=${startup.id}&t1=${param.time1}&t2=${param.time2}' --%>
							<img alt="�鿴��ʷ����" src="img/menu/bt_orders.gif" style="cursor:pointer;display:${fn:contains(power,"������ʷ����")==true?'inline':'none'}"  onclick="javascript:goPowerURL('ccdcc/hiscar2.html?branchId=${param.branchId }&proId=${param.proId}&sid=${startup.id}&time1=${param.time1 }&time2=${param.time2 }',${fn:contains(power,"������ʷ����") })"/>
							<img alt="������ʷ�켣�ط�" src="img/menu/bt_deal.gif" style="cursor:pointer;display:${fn:contains(power,"������ʷ�ط�")==true?'inline':'none'}"  onclick="javascript:goPowerURL('hiscar.do?ope=toHisMap&branchId=${param.branchId }&proId=${param.proId}&sid=${startup.id}&time1=${param.time1 }&time2=${param.time2 }',${fn:contains(power,"������ʷ�ط�")})"/>
							<img alt="������ʷ�켣׷��" src="img/menu/bt_relay.gif" style="cursor:pointer;display:${fn:contains(power,"������ʷ׷��")==true?'inline':'none'} "  onclick="javascript:goPowerURL('hiscar.do?ope=toAllHisMap&branchId=${param.branchId }&proId=${param.proId}&sid=${startup.id}&time1=${param.time1 }&time2=${param.time2 }',${fn:contains(power,"������ʷ׷��")})"/>
							<%-- --%>	
						
						</td>
					</tr>
				</c:forEach>
				<logic:empty name="startList">
					<tr align="center"><td colspan="9">${flag==null?" ":"<font color='red' size='2'>ѡ��ʱ�䷶Χ��û����Ӧ����ͣ��¼</font>" }</td></tr>
				</logic:empty>
					<tr><td height="400"></td></tr>
				</TABLE>
				<input type="hidden" id="t1"  name="t1" value="${param.time1 }"/>
				<input type="hidden" id="t2" name="t2" value="${param.time2 }"/>
				<input type="hidden" id="branchId" name="branchId" value="${param.branchId }"/>
				<input type="hidden" id="sid" name="sid" value=""/>
				<input type="hidden" id="proId" name="proId" value="${param.proId }"/>
				</form>			
  </body>
  <jsp:include page="footer.jsp" flush="true"></jsp:include>
</html>
