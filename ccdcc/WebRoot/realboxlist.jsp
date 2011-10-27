<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="common/taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>小批零实时数据查看</title>
    
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

		<script type="text/javascript">
				var mytimeout = null ;
			function getBoxData(){
				var branchId = document.all.branchId.value ;
				real.getRealBox(branchId,{
				callback:resultHandler,
				errorHandler:errorHandler,
				timeout:15000
				}) ;
			}
			
			//错误处理
			function errorHandler(message){
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
					return "小批零系统"
				},
				function(data){
					return data.projectId ;
				},
				function(data){
				if(data.connectStatus==1)
						return "* * *" ;
					if(data.alarmStatus==0){	
						 return "<img src='img/menu/win/yellow.gif' width='12' height='12'  title='表示终端处于预警状态' class='op_button' /> ";	
					}else if(data.alarmStatus==1){  //报警 1
						return "<img src='img/menu/win/red.gif' width='12' height='12'  title='表示终端处于报警状态' class='op_button' /> ";
					}else{					  //正常 2
						return "<img src='img/menu/win/blue.gif'  width='12' height='12'  title='表示终端处于正常状态' class='op_button' />" ;
					}
				},
				function(data){
				if(data.connectStatus==1)  
						return "* * *" ;
					if(data.runStatus==1){    //断开 1 
						return "<img src='img/menu/msie_doc_mo.gif'  width='18' height='16'  title='表示数据记录停止' class='op_button' />" ;
					}else{
						return "<img src='img/menu/doc2.gif'   width='18' height='16' title='表示数据记录启动' class='op_button' />" ;
					}
				},
				function(data){
					if(data.connectStatus==1){
						return "断开" ;
					}else{
						return "连接" ;
					}
				},
				
				function(data){
					if(data.connectStatus==1)
						return "* * *" ;
					return data.latitudeStr ;
				},
				
				
				function(data){
					if(data.connectStatus==1)
						return "* * *" ;
					return data.longitudeStr ;
				},
				
				function(data){
					if(data.connectStatus==1)
						return "* * *" ;
					else{
						if(data.ai1==-300)
							return "--" ;
						return data.ai1 ;
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
						
						//提示信息
						document.getElementById("mymsg").style.display = 'none';	
					}
				}else{
					window.alert("没有相应的数据");
					document.getElementById("mymsg").style.display = 'inline';			
				}
			}
			
			 /**
			  *	页面初始化
			  */
			
			function pageInit(){
				getBoxData() ;
				mytimeout = window.setInterval("getBoxData()",10000);// window.setTimeout("getCarData",10000);
			}
			
			
		</script>
		
  </head>
  
  <body onload="showtime(),pageInit();">
    	<jsp:include page="header.jsp" flush="true"></jsp:include>
    	
    	<!-- 定义一个变量保存机构Id -->
 	  <input type="hidden" id="branchId" name="branchId" value="${param.branchId }"/>
   
   	<table width="990" border="0" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#CCCCCC" bordercolor="#FFFFFF"
			bordercolordark="#FFFFFF">
	     <tr>
				<td width="990" height="550" colspan="3" valign="top"
					background="img/stock_index_08.gif">
						<div id="main" style="width:990px; height:100%; float:left" >
						<div class="page_title">您正在做的业务是：<img src="img/add/club.JPG" alt=">>"><font size="2px">&nbsp;小批零实时数据查看</font>&nbsp;&nbsp;&nbsp;
						<button class="common_button" type="button" onclick="javascript:window.location.href='branch.do?ope=toListByBranch&branchId=${param.branchId }'">
									返回
						</button>
						
						</div>
					 <div align="center"> <div id="net" style="background-color:rgb(227,222,28);font-family:'Arial Rounded MT Bold';width:125px;display:block;"> &nbsp;&nbsp;当前网络繁忙...</div>
					  </div> 	
							<table width="990" id="tableContainer">
								<thead>
								
								<tr valign='top' HEIGHT='23' bgcolor='#DEDEDE' CLASS=Page_tools_bar>
								  <td align='center' CLASS=Page_title valign='middle'>序号 </td>
								  <td align='center' CLASS=Page_title valign='middle'>终端类型</td>
								  <td align='center' CLASS=Page_title valign='middle'>名称编号</td>
								  <td align='center' CLASS=Page_title valign='middle'>报警状态</td>
								  <td align='center' CLASS=Page_title valign='middle'>运行状态</td>
								  <td align='center' CLASS=Page_title valign='middle'>连接状态</td>
								  <td align='center' CLASS=Page_title valign='middle'>纬度信息</td>
								  <td align='center' CLASS=Page_title valign='middle'>经度信息</td>
								  <td align='center' CLASS=Page_title valign='middle'>T1</td>
								</tr>
								
								</thead>
								<tbody id="container">
								
								</tbody>
								<tr id="mymsg" style="display:none;font: 14;color: red" align="center"><td colspan="12" >没有发现相应的移动设备！</td></tr>
							</table>
						</div> 
				</td>
			</tr>		
	</table>
   
   
    	<jsp:include page="footer.jsp" flush="true"></jsp:include>
  </body>
</html>
