<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="common/taglib.jsp" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		

		<title>����ʵʱ������ʾ...</title>

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
		<LINK href="css/mainWin2.css" type=text/css media=screen
			rel=stylesheet>
		<LINK href="css/desktop.css" type=text/css media=screen rel=stylesheet>
		<link rel="Shortcut Icon" href="img/add/logo.ico">
		<script src="script/common.js"></script>
		<script type="text/javascript" src="script/titleTime.js"></script>
		
		 <script type='text/javascript' src='dwr/interface/realcool.js'></script>
  	    <script type='text/javascript' src='dwr/engine.js'></script>
  		<script type='text/javascript' src='dwr/util.js'></script>
  		
		<script type="text/javascript">
			
			var id  = null ;
		
			//�������������������ɹ����	
			var mul_flag = true  ;
			var compress_flag = true ;
			var comdenser_flag = true ;
			var aircooler_flag = true ;
			var sys_flag = true ;
			
			/**
			*	������ذ�ť
			*/
			function goback(){
				document.myform.action = "branch.do?ope=toListByBranch" ;
				document.myform.submit() ;
			}
			
			/**
			*	ѡ��ͬ�Ļ�������
			*/
			function changeValue(obj){
				document.myform.action = "realcool.do?ope=changeRealCool" ;
				document.myform.submit() ;
			}
			
			function isAllOK(){	
				 if(sys_flag && mul_flag && comdenser_flag && compress_flag && aircooler_flag)
						document.getElementById("net").style.display = "none" ;
			}
			
			/**
			*	ҳ���ʼ��
			*/
			function pageInit(){
				id   = DWRUtil.getValue("mysetid");
				if(id==null|| id=="")
					return ;
				getCompressorData() ;
				getCondenserData();
				getAirCoolerData();
				getMulCompressorSetData() ;
				getCoolerSystem() ;
				window.setInterval("getCompressorData()",10000);
				window.setInterval("getCondenserData()",10000);
				window.setInterval("getAirCoolerData()",10000);
				window.setInterval("getMulCompressorSetData()",10000);
				window.setInterval("getCoolerSystem()",10000);
			}
			
			
			/**
			*	���������
			*/
			function doError(str){
				document.getElementById("net").style.display = "block" ;
				 mul_flag = false  ;
			     compress_flag = false ;
				 comdenser_flag = false ;
				 aircooler_flag = false ;
			 	 sys_flag = false ;
			}
			
			
			/**
			*	��ȡ����ϵͳʵʱ����
			*/
			function getCoolerSystem(){
				var parrayobj = document.getElementById("parray").value ;
					if(parrayobj==null || parrayobj.length==0){
						return  ;
					}
					var v = parrayobj.split(",") ;	
					realcool.getCoolerSysRealData(v,{
					callback:doCoolerSystem,
					timeout:15000,
					errorHandler:doError
				});
			}
			
			/**
			*	��������ϵͳʵʱ����
			*/
			function doCoolerSystem(data){
				if(data!=null){
					document.getElementById("fadianji").innerHTML = getDisplayState(data.dynamoState);
					document.getElementById("fadianjiyujin").innerHTML = getDisplayAlarm(data.dynamoAlarm);
					document.getElementById("xitongduandian").innerHTML = getDisplayState(data.sysoutageState);
					document.getElementById("xitongduandianyujin").innerHTML = getDisplayAlarm(data.sysoutageAlarm);	
				}else{
					document.getElementById("fadianji").innerHTML = "----";
					document.getElementById("fadianjiyujin").innerHTML = "----";
					document.getElementById("xitongduandian").innerHTML = "----";
					document.getElementById("xitongduandianyujin").innerHTML = "----";
				}
				
				//��ǰ�������
				sys_flag = true ;
				
				//�ж����������Ƿ����
				isAllOK() ;
				
			}
			
			
			
			/**
			*	��ȡ���������ʵʱ����
			*/
			function getMulCompressorSetData(){
				realcool.getMultiData(id,{
					callback:doMulCompressorSetData,
					errorHandler:doError,
					timeout:15000
				}) ;
			}
			
			/**
			*	����״̬��ʾ״̬����
			*/
			function getDisplayState(state){
				if(state==2)
					return "����" ;
				else if(state==1)
					return "�ر�" ;
				else 
					return "----" ;
			}
			
			/**
			*	���ݱ���״̬��ʾ�����ı�
			*/
			function getDisplayAlarm(alarm){
				if(alarm==2)
					return "����" ;
				else if (alarm==0)
					return "<font color='orange'>Ԥ��</font>" ;
				else
					return "----" ;
			}
			
			
			/**
			*	�����������ʵʱ����
			*/
			function doMulCompressorSetData(data){
				if(data!=null){
					document.getElementById("xiqi").innerHTML = getDisplayState(data.suctionState)  ;
					document.getElementById("xiqiyj").innerHTML = getDisplayAlarm(data.suctionAlarm) ;
					document.getElementById("diyewei").innerHTML =getDisplayState(data.lowliquidState)  ;
					document.getElementById("diyeweiyj").innerHTML = getDisplayAlarm(data.lowliquidAlarm) ;
					document.getElementById("duandian").innerHTML = getDisplayState(data.outageState) ;
					document.getElementById("duandianyj").innerHTML = getDisplayAlarm(data.outageAlarm) ;
				}else{
					document.getElementById("xiqi").innerHTML = "----"  ;
					document.getElementById("xiqiyj").innerHTML = "----" ;
					document.getElementById("diyewei").innerHTML ="----"  ;
					document.getElementById("diyeweiyj").innerHTML = "----" ;
					document.getElementById("duandian").innerHTML = "----" ;
					document.getElementById("duandianyj").innerHTML = "----" ;
				}
				
				//��ǰ�������
				mul_flag = true ;
				
				//�ж����������Ƿ����
				isAllOK() ;
			}
			
			
			
			
			/**
			*	��ȡѹ������ʵʱ����
			*/
			function getCompressorData(){
			var ids = document.getElementById("compressorids").value ;
				if(ids==null || ids=="")
					return  ;
				realcool.getCompressorDataByIds(ids,{
					callback:doCompressorData ,
					timeout:15000,
					errorHandler:doError
				}) ;
			}
			
			var cellsFunction = 
			[
				function(data){
					return "&nbsp;" ;
				},
				function(data){
					return data.name ;
				},
				
				function(data){
					if(data.activeState==2)
						return "����" ;
					else if (data.activeState==1)
						return "ֹͣ" ;
					else 
						return "----" ;
				},
				function(data){
					if(data.lowpresState==2)
						return "����" ;
					else if (data.lowpresState==1)
						return "�ر�" ;
					else
						return "----" ;
				},
				function(data){
					if(data.lowpresAlarm==2)
						return "����" ;
					else if (data.lowpresAlarm==0)
						return "<font color='orange'>Ԥ��</font>" ;
					else 
						return "----"  ;
				},
				function(data){
					if(data.highpresState==2)
						return "����" ;
					else if (data.highpresState==1)
						return "�ر�" ;
					else 
						return "----" ;
				},
				function(data){
					if(data.highpresAlarm==2)
						return "����" ;
					else if (data.highpresAlarm==0)
						return "<font color='orange'>Ԥ��</font>" ;
					else
						return "----" ;
				},
				
				function(data){
					if(data.exhaustValue==-300 || data.exhaustValue==null)
						return "----" ;
					return data.exhaustValue+"��" ;
				},
				
				function(data){
					if(data.exhaustAlarm==2)
						return "����" ;
					else if(data.exhaustAlarm==0)
						return "<font color='orange'>Ԥ��</font>" ;
					else
						return "----" ;
				},
				
				function(data){
					if(data.oilpresState==2)
						return "����" ;
					else if (data.oilpresState==1)
						return "�ر�" ;
					else 
						return "----" ;
				},
				function(data){
					if(data.oilpresAlarm==2)
						return "����" ;
					else if (data.oilpresAlarm==0)
						return "<font color='orange'>Ԥ��</font>" ;
					return "----" ;
				},
				
				function(data){
					if(data.overloadState==2)
						return "����" ;
					else if (data.overloadState==1)
						return "�ر�" ;
					else 
						return "----" ;
				},
				
				function(data){
					if(data.overloadAlarm==2)
						return "����" ;
					else if (data.overloadAlarm==0)
						return "<font color='orange'>Ԥ��</font>" ;
					return "----" ;
				}
				
			] ;
			
			/**
			*	����ѹ������ʵʱ����
			*/
			function doCompressorData(data){
				if(data!=null){
					if(data.length>0){
						DWRUtil.removeAllRows("Compressorcontainer");
						
						//��������ӵ����
						DWRUtil.addRows("Compressorcontainer",data,cellsFunction,{escapeHtml:false ,
						cellCreator:function(options){
							var td = document.createElement("td");
							td.align = "center" ;
							return td ;
						},
						rowCreator:function(options){
							var tr = document.createElement("tr");
							return tr ;
						}
						});	
					}
				}
				
				//��ʶ��ǰ�������
				compress_flag = true ;
				//�ж����������Ƿ����
				isAllOK() ;
			}
			
			/**
			*	��ȡ��������ʵʱ����
			*/
			function getCondenserData(){
				var ids = document.getElementById("condenserids").value;
				if(ids==null || ids=="")
					return  ;
				realcool.getCondenserDataByIds(ids,{
					callback:doCondenserData,
					errorHandler:doError,
					timeout:15000
				}) ;
			}
			
			
			var cellsFunction2 = 
			[
				function(data){
					return data.name ;
				},
				function(data){
					var pState = data.pressureState ;
					if(pState==2)
						return "����" ;
					else if (pState==1)
						return "�ر�" ;
					else 
						return "----" ;
				},
				function(data){
					var pAlarm = data.pressureAlarm ;
					if(pAlarm==2)
						return "����" ;
					else if (pAlarm==0)
						return "<font color='orange'>Ԥ��</font>" ; 
					else 
						return "----" ;
				}
			] ;
			
			/**
			*	������������ʵʱ����
			*/
			function doCondenserData(data){
				if(data!=null){
					if(data.length!=0){
						DWRUtil.removeAllRows("Condensercontainer");
						
						//��������ӵ����
						DWRUtil.addRows("Condensercontainer",data,cellsFunction2,{escapeHtml:false ,
						cellCreator:function(options){
							var td = document.createElement("td");
							td.align = "center" ;
							return td ;
						},
						rowCreator:function(options){
							var tr = document.createElement("tr");
							return tr ;
						}
						});	
					}
				}
				
					//��ʶ��ǰ�������
					comdenser_flag = true ;
					//�ж����������Ƿ����
					isAllOK() ;
					
			}
			
			/**
			*	��ȡ������ʵʱ����
			*/
			function getAirCoolerData(){
			var ids = document.getElementById("aircoolerids").value ;
				if(ids==null || ids=="")
					return  ;
				realcool.getAirCoolerDataByIds(ids,{
					callback:doAirCoolerData,
					errorHandler:doError,
					timeout:15000
				}) ;
			}
			
			var cellsFunction3 = 
			[
				function(data){
					return data.name ;
				},
				
				function(data){
					var ds = data.defrostState ;
					if(ds==2)
						return "����" ;
					else if (ds==1)
						return "�ر�" ;
					else 
						return "----" ;
				},
				
				function(data){
					var da = data.defrostAlarm ;
					if(da==2)
						return "����" ;
					else if (da==0)
						return "<font color='orange'>Ԥ��</font>" ; 
					else 
						return "----" ;
				}
			] ;
			
			/**
			*	����������ʵʱ����
			*/
			
			function doAirCoolerData(data){
				if(data!=null){
					if(data.length!=0){
						DWRUtil.removeAllRows("AirCoolercontainer");
						
						//��������ӵ����
						DWRUtil.addRows("AirCoolercontainer",data,cellsFunction3,{escapeHtml:false ,
						cellCreator:function(options){
							var td = document.createElement("td");
							td.align = "center" ;
							return td ;
						},
						rowCreator:function(options){
							var tr = document.createElement("tr");
							return tr ;
						}
						});	
					}
				}
				
					//��ʶ��ǰ�����ɹ�
					aircooler_flag = true ;
				
					//�ж����������Ƿ����
					isAllOK() ;
			}
			
			
			
		</script>
		
	</head>

	<body onload="showtime();pageInit();">
		<jsp:include page="header.jsp" flush="true"></jsp:include>
		<form action="" method="post" name="myform">
			<input type="hidden" id="branchId" name="branchId" value="${param.branchId }" />
			<input type="hidden" id="projectId" name="projectId" value="" />
			<table width="990" border="0" align="center" cellpadding="0"
				cellspacing="0" bordercolorlight="#CCCCCC" bordercolor="#FFFFFF"
				bordercolordark="#FFFFFF">
				<tr>
					<td width="990" height="550" colspan="3" valign="top"
						background="img/stock_index_08.gif">
						<div id="main" style="width: 990px; height: 100%; float: left">
							<div class="page_title">
								����������ҵ���ǣ�
								<img src="img/add/club.JPG" alt=">>">
								<font size="2px">&nbsp;����ʵʱ���ݲ鿴</font>&nbsp;&nbsp;&nbsp;
								<button class="common_button" onclick="javascript:goback();" type="button">
									����
								</button>
							</div>
							��ѡ��鿴�Ļ�������:
							<select name="mysetid" style="width: 150px" onchange="changeValue(this);">
							<c:forEach var="comset" items="${coollist}">
								<option value="${comset.id }" ${param.mysetid == comset.id?"selected=selected":" "}>${comset.csName }</option>
							</c:forEach>
							</select>
							 <div align="center">
						 <div id="net" style="background-color:rgb(227,222,28);font-family:'Arial Rounded MT Bold';width:125px;display:none;"> &nbsp;&nbsp;��ǰ���緱æ...</div>
							 </div>  							
							<hr color="gray">							
							<table width="990" id="tablsystem">
								<caption>����ϵͳʵʱ����</caption>
								<tr valign='top' HEIGHT='20' bgcolor='#DEDEDE' CLASS="Page_tools_bar">
								<td align='center' CLASS=Page_title valign='middle'>������������ϱ���</td>
								<td align='center' CLASS=Page_title valign='middle'>�������������Ԥ��</td>
								<td align='center' CLASS=Page_title valign='middle'>ϵͳ�ϵ籣��</td>
								<td align='center' CLASS=Page_title valign='middle'>ϵͳ�ϵ�Ԥ��</td>
								</tr>
								<tbody id="systemContainer">
									<tr align="center">
										<td><div id="fadianji">----</div></td>
										<td><div id="fadianjiyujin">----</div></td>
										<td><div id="xitongduandian">----</div></td>
										<td><div id="xitongduandianyujin">----</div></td>
								</tr>
								</tbody>
								
							</table>
							<table width="990" id="tableContainer">
								<tr>
									<td>
									<table width="990">									
								<thead>	
								  <tr valign='top' HEIGHT='20' bgcolor='#DEDEDE' CLASS=Page_tools_bar>
								 	 <td align='center' CLASS=Page_title valign='middle'>�������� </td>
								 	 <td align='center' CLASS=Page_title valign='middle'>��������</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>ѹ��������</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>����ѹ��</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>Ԥ��״̬</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>��Һλ</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>Ԥ��״̬</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>ȱ���</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>Ԥ��״̬</td>
							      </tr>
								</thead>
									<tbody id="">
										<tr align="center">
											<td>${compressorset.csName }</td>
											<td>��������</td>
											<td>${compressorset.csCount }</td>
											<td ><div id="xiqi">----</div></td>
											<td ><div id="xiqiyj">----</div></td>
											<td><div  id="diyewei">----</div></td>
											<td ><div id="diyeweiyj">----</div></td>
											<td ><div id="duandian">----</div></td>
											<td><div  id="duandianyj">----</div></td>
										</tr>
									</tbody>
								</table>
									</td>
								</tr>
								<tr>
									<td>
										&nbsp;
										<table width="990">									
								<thead>	
								  <tr valign='top' HEIGHT='20' bgcolor='#DEDEDE' CLASS=Page_tools_bar>
								  	<td>&nbsp;</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>ѹ������ʶ </td>
								 	 <td align='center' CLASS=Page_title valign='middle'>����״̬</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>��ѹ����</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>��ѹԤ��</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>��ѹ����</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>��ѹԤ��</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>�����¶�</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>����Ԥ��</td>
								 	 
								 	 <td align='center' CLASS=Page_title valign='middle'>��ѹ����</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>��ѹԤ��</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>���ر���</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>����Ԥ��</td>								 	 								
							      </tr>
								</thead>
									<tbody id="Compressorcontainer">
										<tr align="center">
											<td>&nbsp;</td>
											<td>----</td>
											<td>----</td>
											<td>----</td>
											<td>----</td>
											<td>----</td>
											<td>-----</td>
											<td>----</td>
											<td>----</td>
											<td>----</td>
											<td>----</td>
											<td>----</td>
											<td>----</td>	
										</tr>
									</tbody>
								</table>
									</td>
								</tr>
								<tr>
									<td>
										&nbsp;
										<table width="990">									
								<thead>	
								  <tr valign='top' HEIGHT='20' bgcolor='#DEDEDE' CLASS=Page_tools_bar>
								 	 <td align='center' CLASS=Page_title valign='middle'>��������ʶ </td>
								 	 <td align='center' CLASS=Page_title valign='middle'>ѹ������</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>ѹ��Ԥ��</td>
							      </tr>
								</thead>
									<tbody id="Condensercontainer">
										<tr align="center">
											<td>----</td>
											<td>----</td>
											<td>----</td>
										</tr>
									</tbody>
								</table>
									</td>
								</tr>
								<tr>
									<td>
										&nbsp;
										<table width="990">									
								<thead>	
								  <tr valign='top' HEIGHT='20' bgcolor='#DEDEDE' CLASS=Page_tools_bar>
								 	 <td align='center' CLASS=Page_title valign='middle'>������ʶ </td>
								 	 <td align='center' CLASS=Page_title valign='middle'>��˪����</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>��˪Ԥ��</td>
							      </tr>
								</thead>
									<tbody id="AirCoolercontainer">
										<tr align="center">
											<td>----</td>
											<td>----</td>
											<td>----</td>
										</tr>
									</tbody>
								</table>
									</td>
								</tr>

							</table>
						</div>
					</td>
				</tr>
			</table>
			<input type="hidden" name="parray" id="parray" value="${param.parray==null?refprojectids:param.parray}"/>
			<input type="hidden" name="compressorids" id="compressorids" value="${compressorStr}"/> 
			<input type="hidden" name="aircoolerids"  id="aircoolerids" value="${aircoolerStr}"/> 
			<input type="hidden" name="condenserids"  id="condenserids" value="${condenserStr}"/> 
		</form>
		<jsp:include page="footer.jsp" flush="true"></jsp:include>
	</body>
</html>
