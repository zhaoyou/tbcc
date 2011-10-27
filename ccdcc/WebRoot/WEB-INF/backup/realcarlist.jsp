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
    <title>移动车载实时数据查看</title>
    
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
		<script type="text/javascript" src="script/titleTime.js"></script>
		
		  <script type='text/javascript' src='dwr/interface/real.js'></script>
        <script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		
		
		<script type="text/javascript" language="javascript">
			//获取数据
			var mytimeout = null ;
			function getCarData(){
				var branchId = document.getElementById("branchId").value ;
				real.getRealCar(branchId,{
				callback:resultHandler,
				errorHandler:doerror,
				timeout:15000
				}) ;	
			}
			
			//错误处理
			function doerror(message){
				document.getElementById("net").style.display = "block" ;
			}
			
			var i = 1 ;
			//处理单元格的值
			var  cellsFunction  = 
			[
				function(data){
					return i++ ;
				},
				
				function(data){
					return "移动车载"
				},
				
				function(data){
					return data.projectName ;
				},
				
				function(data){				
				if(data.connectStatus==null || data.connectStatus==1)
					return "* * *" ;
				
				if(data.alarmStatus==null)
					return "* * *" ;	
					
					if(data.alarmStatus==0){	//预警 0 
						return "<img src='img/menu/win/orange.jpg' width='12' height='12'  title='表示终端处于预警状态' class='op_button' /><input type='hidden' name='isAlarm' id='isAlarm' value='0'/>";
					}else if(data.alarmStatus==1){  //报警 1
						return "<img src='img/menu/win/red.gif' width='12' height='12'    title='表示终端处于报警状态' class='op_button' /><input type='hidden' name='isAlarm' id='isAlarm' value='1'/>";
					}else{					  //正常  2
						return "<img src='img/menu/win/blue.gif'  width='12' height='12'  title='表示终端处于正常状态' class='op_button' /><input type='hidden' name='isAlarm' id='isAlarm' value='2'/>" ;
					}
				},
				function(data){			
					if(data.connectStatus==null || data.connectStatus==1)
						return "* * *" ;
					if(data.runStatus==null)	
						return "* * * " ;
					if(data.runStatus==1){
						return "<img src='img/menu/msie_doc_mo.gif'  width='18' height='16'  title='表示数据记录停止' class='op_button' />" ;
					}else{
						return "<img src='img/menu/doc2.gif'   width='18' height='16' title='表示数据记录启动' class='op_button' />" ;
					}
				},
				function(data){
					if(data.connectStatus==null || data.connectStatus==1){
						return "断开" ;
					}else{
						return "连接" ;
					}
				},
				
				/**
				function(data){
					if(data.connectStatus==1)
						return "* * *" ;
					return data.latitudeStr ;
				},
				*/
				
				function(data){
					if(data.connectStatus==null || data.connectStatus==1)
						return "* * *" ;
						
					if(data.address==null)
						return "* * *" ;
					return data.address ;
				},
				
				function(data){
					
					if(data.ai1==null)
						return "* * *" ;
				
					if(data.connectStatus==null || data.connectStatus==1)
						return "* * *" ;
					else{
						if(data.ai1==-300)
							return "--" ;
						return data.ai1 ;
					}
					
				},
				function(data){
					if(data.ai2==null)
						return "* * *" ;
						
					if(data.connectStatus==null || data.connectStatus==1)
						return "* * *" ;
					else{
						if(data.ai2==-300)
							return "--" ;
						return data.ai2 ;
					}	
				},
				function(data){
					if(data.ai3==null)
						return "* * *" ;
					if(data.connectStatus==null || data.connectStatus==1)
						return "* * *" ;
					else{
						if(data.ai3==-300)
							return "--" ;
						return data.ai3 ;
					}
				
				},
				function(data){
					if(data.ai4==null)
						return "* * *" ;
					if(data.connectStatus==null || data.connectStatus==1)
						return "* * *" ;
					else{
						if(data.ai4==-300)
							return "--" ;
						return data.ai4 ;	
					}		
				},
				
				function(data){
				  if(document.getElementById("p").value=="true"){
				  
					if(data.connectStatus==null || data.connectStatus==1)
						return "<img title='实时地图显示' alt='实时地图显示' style='cursor:pointer' src='img/menu/map.JPG' align='middle' />" ;
					else
					   return "<img title='实时地图显示' alt='实时地图显示' style='cursor:pointer' src='img/menu/map.JPG' align='middle' onclick=gotoMap('"+data.projectId+"') />" 
				   }else{
				   		document.getElementById("shishizhuizong").innerHTML = "" ;
				   		return "" ;
				   }
				}

			] ;
			
			//把返回的结果
			function resultHandler(data){
			  //数据库中有相应的数据
				if(data!=null){
					if(data.length>0){
					document.getElementById("net").style.display = "none" ;
						//清空数据表格
						DWRUtil.removeAllRows("container");
						
						i = 1 ;
						
						//把数据添加到表格
						DWRUtil.addRows("container",data,cellsFunction,{escapeHtml:false ,
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
						document.getElementById("mymsg").style.display = 'none';	
						checkAlarm() ;			//检验是否需要播放音频文件					
					}
				}else{
					window.alert("没有发现相应的车载!...");
					document.getElementById("mymsg").style.display = 'inline';			
				}
			}
			
			
			/**
			*	页面初始化
			*/
			
			function pageInit(){
				getCarData() ;
				mytimeout = window.setInterval("getCarData()",10000);// window.setTimeout("getCarData",10000);
			}
			
			/**
			*	跳转到地图显示
			*/
			function gotoMap(pid){
				var f = document.getElementById("p").value ;

				if(f=="true"){
					document.getElementById("proId").value = pid ;
					document.myform.submit() ;
				}
				
			}
			/**
			*	返回上一级功能目录
			*/
			function goback(){
				document.myform.action = "branch.do?ope=toListByBranch"
				document.myform.submit();
			}
			
			/**
			*	停止报警音频
			*/
			function stopAlarm(){
				if(document.getElementById("player")!=null){	
					document.getElementById("playerContainer").removeChild(document.getElementById("player"));
				}		
			}
			/**
			*	开启报警音频
			*/
			function beginAlarm(){	
				 var p = document.createElement("embed") ;	
				 var path = document.getElementById("contextPath").value ;
				 p.setAttribute("id","player");
				 p.setAttribute("src",path+"music/alarm.WAV");
				 p.setAttribute("autostart",true); 
				 p.setAttribute("height",0); 
				 p.setAttribute("width",0);	
				 p.setAttribute("hidden",true);	
				 p.setAttribute("loop",true);
				 document.getElementById("playerContainer").appendChild(p);			
			}
			
			/**
			*	依次检测是否需要报警了	
			*/
			function checkAlarm(){
				var s = document.getElementsByName("isAlarm");
				if(s==null)
				{
					stopAlarm();
					return ;
				}
				
				var flag = false ;
				for(var i=0 ;i<s.length;i++)
				{
					if(s[i].value!=2)
					{
						flag = true ;
						break ;
					}
				}	
		
				if(flag){
					stopAlarm();	
					beginAlarm();
				}else{
					stopAlarm();
				}			
		}
		
			
			
			
		</script>
  </head>
  
  <body onload="showtime(),pageInit();">
  <jsp:include page="header.jsp" flush="true"></jsp:include>
  <!-- 定义一个变量保存机构Id -->
  <form action="realcar.do?ope=toRealMap" method="post" name="myform">
  <input type="hidden" id="contextPath" name="contextPath" value="<%=basePath %>"/>
   <input type="hidden" id="branchId" name="branchId" value="${param.branchId }"/>
   <input type="hidden" name="proId" id="proId" value=""/>
   <input type="hidden" name="p" id="p" value="${fn:contains(power,"车载实时地图")}" />
    <table width="990" border="0" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#CCCCCC" bordercolor="#FFFFFF"
			bordercolordark="#FFFFFF">
	     <tr>
				<td width="990" height="550" colspan="3" valign="top"
					background="img/stock_index_08.gif">
						<div id="main" style="width:990px; height:100%; float:left" >
						<div class="page_title">您正在做的业务是：<img src="img/add/club.JPG" alt=">>"><font size="2px">&nbsp;车载实时数据查看</font>&nbsp;&nbsp;&nbsp;  
					<button class="common_button" onclick="javascript:goback()" type="button">
									返回
					</button>
						</div>						
                         <div align="center"> <div id="net" style="background-color:rgb(227,222,28);font-family:'Arial Rounded MT Bold';width:125px;display:none;"> &nbsp;&nbsp;当前网络繁忙...</div> </div>                    
						<table width="990" id="tableContainer">
								<thead>
								
								<tr valign='top' HEIGHT='23' bgcolor='#DEDEDE' CLASS=Page_tools_bar>
								  <td align='center' CLASS=Page_title valign='middle'>序号 </td>
								  <td align='center' CLASS=Page_title valign='middle'>终端类型</td>
								  <td align='center' CLASS=Page_title valign='middle'>项目名称</td>
								  <td align='center' CLASS=Page_title valign='middle'>报警状态</td>
								  <td align='center' CLASS=Page_title valign='middle'>运行状态</td>
								  <td align='center' CLASS=Page_title valign='middle'>连接状态</td>
								  <%--
								  		采用map，根据经纬度信心显示地图的地址
								  		 <td align='center' CLASS=Page_title valign='middle'>经度信息</td>
								   --%>
								  <td align='center' CLASS=Page_title valign='middle'>当前地址</td>
								 
								  <td align='center' CLASS=Page_title valign='middle'>T1</td>
								  <td align='center' CLASS=Page_title valign='middle'>T2</td>
								  <td align='center' CLASS=Page_title valign='middle'>T3</td>
								  <td align='center' CLASS=Page_title valign='middle'>RH1</td>
								  <td align='center' CLASS=Page_title valign='middle'><span id="shishizhuizong">实时追踪<span></td>
								</tr>
								
								</thead>
								<tbody id="container">
								
								</tbody>
								<tr id="mymsg" style="display:none;font: 14;color: red" align="center"><td colspan="12" >移动设备未初始化...</td></tr>
							</table>
						</div> 
				</td>
			</tr>		
	</table>
	<div id="playerContainer"></div>
	</form>
	<jsp:include page="footer.jsp" flush="true"></jsp:include>
  </body>
</html>
