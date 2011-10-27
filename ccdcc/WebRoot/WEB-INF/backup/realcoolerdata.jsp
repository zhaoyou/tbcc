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
		

		<title>制冷实时数据显示...</title>

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
		
			//定义五个变量保存操作成功与否	
			var mul_flag = true  ;
			var compress_flag = true ;
			var comdenser_flag = true ;
			var aircooler_flag = true ;
			var sys_flag = true ;
			
			/**
			*	点击返回按钮
			*/
			function goback(){
				document.myform.action = "branch.do?ope=toListByBranch" ;
				document.myform.submit() ;
			}
			
			/**
			*	选择不同的机组类型
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
			*	页面初始化
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
			*	出错处理程序
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
			*	获取制冷系统实时数据
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
			*	处理制冷系统实时数据
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
				
				//当前操作完成
				sys_flag = true ;
				
				//判断整个操作是否完成
				isAllOK() ;
				
			}
			
			
			
			/**
			*	获取并联机组的实时数据
			*/
			function getMulCompressorSetData(){
				realcool.getMultiData(id,{
					callback:doMulCompressorSetData,
					errorHandler:doError,
					timeout:15000
				}) ;
			}
			
			/**
			*	根据状态显示状态文字
			*/
			function getDisplayState(state){
				if(state==2)
					return "开启" ;
				else if(state==1)
					return "关闭" ;
				else 
					return "----" ;
			}
			
			/**
			*	根据报警状态显示报警文本
			*/
			function getDisplayAlarm(alarm){
				if(alarm==2)
					return "正常" ;
				else if (alarm==0)
					return "<font color='orange'>预警</font>" ;
				else
					return "----" ;
			}
			
			
			/**
			*	处理并联机组的实时数据
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
				
				//当前操作完成
				mul_flag = true ;
				
				//判断整个操作是否完成
				isAllOK() ;
			}
			
			
			
			
			/**
			*	获取压缩机的实时数据
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
						return "运行" ;
					else if (data.activeState==1)
						return "停止" ;
					else 
						return "----" ;
				},
				function(data){
					if(data.lowpresState==2)
						return "开启" ;
					else if (data.lowpresState==1)
						return "关闭" ;
					else
						return "----" ;
				},
				function(data){
					if(data.lowpresAlarm==2)
						return "正常" ;
					else if (data.lowpresAlarm==0)
						return "<font color='orange'>预警</font>" ;
					else 
						return "----"  ;
				},
				function(data){
					if(data.highpresState==2)
						return "开启" ;
					else if (data.highpresState==1)
						return "关闭" ;
					else 
						return "----" ;
				},
				function(data){
					if(data.highpresAlarm==2)
						return "正常" ;
					else if (data.highpresAlarm==0)
						return "<font color='orange'>预警</font>" ;
					else
						return "----" ;
				},
				
				function(data){
					if(data.exhaustValue==-300 || data.exhaustValue==null)
						return "----" ;
					return data.exhaustValue+"℃" ;
				},
				
				function(data){
					if(data.exhaustAlarm==2)
						return "正常" ;
					else if(data.exhaustAlarm==0)
						return "<font color='orange'>预警</font>" ;
					else
						return "----" ;
				},
				
				function(data){
					if(data.oilpresState==2)
						return "开启" ;
					else if (data.oilpresState==1)
						return "关闭" ;
					else 
						return "----" ;
				},
				function(data){
					if(data.oilpresAlarm==2)
						return "正常" ;
					else if (data.oilpresAlarm==0)
						return "<font color='orange'>预警</font>" ;
					return "----" ;
				},
				
				function(data){
					if(data.overloadState==2)
						return "开启" ;
					else if (data.overloadState==1)
						return "关闭" ;
					else 
						return "----" ;
				},
				
				function(data){
					if(data.overloadAlarm==2)
						return "正常" ;
					else if (data.overloadAlarm==0)
						return "<font color='orange'>预警</font>" ;
					return "----" ;
				}
				
			] ;
			
			/**
			*	处理压缩机的实时数据
			*/
			function doCompressorData(data){
				if(data!=null){
					if(data.length>0){
						DWRUtil.removeAllRows("Compressorcontainer");
						
						//把数据添加到表格
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
				
				//标识当前操作完成
				compress_flag = true ;
				//判断整个操作是否完成
				isAllOK() ;
			}
			
			/**
			*	获取冷凝器的实时数据
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
						return "开启" ;
					else if (pState==1)
						return "关闭" ;
					else 
						return "----" ;
				},
				function(data){
					var pAlarm = data.pressureAlarm ;
					if(pAlarm==2)
						return "正常" ;
					else if (pAlarm==0)
						return "<font color='orange'>预警</font>" ; 
					else 
						return "----" ;
				}
			] ;
			
			/**
			*	处理冷凝器的实时数据
			*/
			function doCondenserData(data){
				if(data!=null){
					if(data.length!=0){
						DWRUtil.removeAllRows("Condensercontainer");
						
						//把数据添加到表格
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
				
					//标识当前操作完成
					comdenser_flag = true ;
					//判断整个操作是否完成
					isAllOK() ;
					
			}
			
			/**
			*	获取冷风机的实时数据
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
						return "开启" ;
					else if (ds==1)
						return "关闭" ;
					else 
						return "----" ;
				},
				
				function(data){
					var da = data.defrostAlarm ;
					if(da==2)
						return "正常" ;
					else if (da==0)
						return "<font color='orange'>预警</font>" ; 
					else 
						return "----" ;
				}
			] ;
			
			/**
			*	处理冷风机的实时数据
			*/
			
			function doAirCoolerData(data){
				if(data!=null){
					if(data.length!=0){
						DWRUtil.removeAllRows("AirCoolercontainer");
						
						//把数据添加到表格
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
				
					//标识当前操作成功
					aircooler_flag = true ;
				
					//判断整个操作是否完成
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
								您正在做的业务是：
								<img src="img/add/club.JPG" alt=">>">
								<font size="2px">&nbsp;制冷实时数据查看</font>&nbsp;&nbsp;&nbsp;
								<button class="common_button" onclick="javascript:goback();" type="button">
									返回
								</button>
							</div>
							请选择查看的机组名称:
							<select name="mysetid" style="width: 150px" onchange="changeValue(this);">
							<c:forEach var="comset" items="${coollist}">
								<option value="${comset.id }" ${param.mysetid == comset.id?"selected=selected":" "}>${comset.csName }</option>
							</c:forEach>
							</select>
							 <div align="center">
						 <div id="net" style="background-color:rgb(227,222,28);font-family:'Arial Rounded MT Bold';width:125px;display:none;"> &nbsp;&nbsp;当前网络繁忙...</div>
							 </div>  							
							<hr color="gray">							
							<table width="990" id="tablsystem">
								<caption>制冷系统实时数据</caption>
								<tr valign='top' HEIGHT='20' bgcolor='#DEDEDE' CLASS="Page_tools_bar">
								<td align='center' CLASS=Page_title valign='middle'>发电机启动故障保护</td>
								<td align='center' CLASS=Page_title valign='middle'>发电机启动故障预警</td>
								<td align='center' CLASS=Page_title valign='middle'>系统断电保护</td>
								<td align='center' CLASS=Page_title valign='middle'>系统断电预警</td>
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
								 	 <td align='center' CLASS=Page_title valign='middle'>机组名称 </td>
								 	 <td align='center' CLASS=Page_title valign='middle'>机组类型</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>压缩机个数</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>吸气压力</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>预警状态</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>低液位</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>预警状态</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>缺项保护</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>预警状态</td>
							      </tr>
								</thead>
									<tbody id="">
										<tr align="center">
											<td>${compressorset.csName }</td>
											<td>并联机组</td>
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
								 	 <td align='center' CLASS=Page_title valign='middle'>压缩机标识 </td>
								 	 <td align='center' CLASS=Page_title valign='middle'>运行状态</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>低压保护</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>低压预警</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>高压保护</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>高压预警</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>排气温度</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>排气预警</td>
								 	 
								 	 <td align='center' CLASS=Page_title valign='middle'>油压保护</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>油压预警</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>过载保护</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>过载预警</td>								 	 								
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
								 	 <td align='center' CLASS=Page_title valign='middle'>冷凝器标识 </td>
								 	 <td align='center' CLASS=Page_title valign='middle'>压力保护</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>压力预警</td>
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
								 	 <td align='center' CLASS=Page_title valign='middle'>冷风机标识 </td>
								 	 <td align='center' CLASS=Page_title valign='middle'>除霜保护</td>
								 	 <td align='center' CLASS=Page_title valign='middle'>除霜预警</td>
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
