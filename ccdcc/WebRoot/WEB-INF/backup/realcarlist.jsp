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
    <title>�ƶ�����ʵʱ���ݲ鿴</title>
    
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
			//��ȡ����
			var mytimeout = null ;
			function getCarData(){
				var branchId = document.getElementById("branchId").value ;
				real.getRealCar(branchId,{
				callback:resultHandler,
				errorHandler:doerror,
				timeout:15000
				}) ;	
			}
			
			//������
			function doerror(message){
				document.getElementById("net").style.display = "block" ;
			}
			
			var i = 1 ;
			//����Ԫ���ֵ
			var  cellsFunction  = 
			[
				function(data){
					return i++ ;
				},
				
				function(data){
					return "�ƶ�����"
				},
				
				function(data){
					return data.projectName ;
				},
				
				function(data){				
				if(data.connectStatus==null || data.connectStatus==1)
					return "* * *" ;
				
				if(data.alarmStatus==null)
					return "* * *" ;	
					
					if(data.alarmStatus==0){	//Ԥ�� 0 
						return "<img src='img/menu/win/orange.jpg' width='12' height='12'  title='��ʾ�ն˴���Ԥ��״̬' class='op_button' /><input type='hidden' name='isAlarm' id='isAlarm' value='0'/>";
					}else if(data.alarmStatus==1){  //���� 1
						return "<img src='img/menu/win/red.gif' width='12' height='12'    title='��ʾ�ն˴��ڱ���״̬' class='op_button' /><input type='hidden' name='isAlarm' id='isAlarm' value='1'/>";
					}else{					  //����  2
						return "<img src='img/menu/win/blue.gif'  width='12' height='12'  title='��ʾ�ն˴�������״̬' class='op_button' /><input type='hidden' name='isAlarm' id='isAlarm' value='2'/>" ;
					}
				},
				function(data){			
					if(data.connectStatus==null || data.connectStatus==1)
						return "* * *" ;
					if(data.runStatus==null)	
						return "* * * " ;
					if(data.runStatus==1){
						return "<img src='img/menu/msie_doc_mo.gif'  width='18' height='16'  title='��ʾ���ݼ�¼ֹͣ' class='op_button' />" ;
					}else{
						return "<img src='img/menu/doc2.gif'   width='18' height='16' title='��ʾ���ݼ�¼����' class='op_button' />" ;
					}
				},
				function(data){
					if(data.connectStatus==null || data.connectStatus==1){
						return "�Ͽ�" ;
					}else{
						return "����" ;
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
						return "<img title='ʵʱ��ͼ��ʾ' alt='ʵʱ��ͼ��ʾ' style='cursor:pointer' src='img/menu/map.JPG' align='middle' />" ;
					else
					   return "<img title='ʵʱ��ͼ��ʾ' alt='ʵʱ��ͼ��ʾ' style='cursor:pointer' src='img/menu/map.JPG' align='middle' onclick=gotoMap('"+data.projectId+"') />" 
				   }else{
				   		document.getElementById("shishizhuizong").innerHTML = "" ;
				   		return "" ;
				   }
				}

			] ;
			
			//�ѷ��صĽ��
			function resultHandler(data){
			  //���ݿ�������Ӧ������
				if(data!=null){
					if(data.length>0){
					document.getElementById("net").style.display = "none" ;
						//������ݱ��
						DWRUtil.removeAllRows("container");
						
						i = 1 ;
						
						//��������ӵ����
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
						checkAlarm() ;			//�����Ƿ���Ҫ������Ƶ�ļ�					
					}
				}else{
					window.alert("û�з�����Ӧ�ĳ���!...");
					document.getElementById("mymsg").style.display = 'inline';			
				}
			}
			
			
			/**
			*	ҳ���ʼ��
			*/
			
			function pageInit(){
				getCarData() ;
				mytimeout = window.setInterval("getCarData()",10000);// window.setTimeout("getCarData",10000);
			}
			
			/**
			*	��ת����ͼ��ʾ
			*/
			function gotoMap(pid){
				var f = document.getElementById("p").value ;

				if(f=="true"){
					document.getElementById("proId").value = pid ;
					document.myform.submit() ;
				}
				
			}
			/**
			*	������һ������Ŀ¼
			*/
			function goback(){
				document.myform.action = "branch.do?ope=toListByBranch"
				document.myform.submit();
			}
			
			/**
			*	ֹͣ������Ƶ
			*/
			function stopAlarm(){
				if(document.getElementById("player")!=null){	
					document.getElementById("playerContainer").removeChild(document.getElementById("player"));
				}		
			}
			/**
			*	����������Ƶ
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
			*	���μ���Ƿ���Ҫ������	
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
  <!-- ����һ�������������Id -->
  <form action="realcar.do?ope=toRealMap" method="post" name="myform">
  <input type="hidden" id="contextPath" name="contextPath" value="<%=basePath %>"/>
   <input type="hidden" id="branchId" name="branchId" value="${param.branchId }"/>
   <input type="hidden" name="proId" id="proId" value=""/>
   <input type="hidden" name="p" id="p" value="${fn:contains(power,"����ʵʱ��ͼ")}" />
    <table width="990" border="0" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#CCCCCC" bordercolor="#FFFFFF"
			bordercolordark="#FFFFFF">
	     <tr>
				<td width="990" height="550" colspan="3" valign="top"
					background="img/stock_index_08.gif">
						<div id="main" style="width:990px; height:100%; float:left" >
						<div class="page_title">����������ҵ���ǣ�<img src="img/add/club.JPG" alt=">>"><font size="2px">&nbsp;����ʵʱ���ݲ鿴</font>&nbsp;&nbsp;&nbsp;  
					<button class="common_button" onclick="javascript:goback()" type="button">
									����
					</button>
						</div>						
                         <div align="center"> <div id="net" style="background-color:rgb(227,222,28);font-family:'Arial Rounded MT Bold';width:125px;display:none;"> &nbsp;&nbsp;��ǰ���緱æ...</div> </div>                    
						<table width="990" id="tableContainer">
								<thead>
								
								<tr valign='top' HEIGHT='23' bgcolor='#DEDEDE' CLASS=Page_tools_bar>
								  <td align='center' CLASS=Page_title valign='middle'>��� </td>
								  <td align='center' CLASS=Page_title valign='middle'>�ն�����</td>
								  <td align='center' CLASS=Page_title valign='middle'>��Ŀ����</td>
								  <td align='center' CLASS=Page_title valign='middle'>����״̬</td>
								  <td align='center' CLASS=Page_title valign='middle'>����״̬</td>
								  <td align='center' CLASS=Page_title valign='middle'>����״̬</td>
								  <%--
								  		����map�����ݾ�γ��������ʾ��ͼ�ĵ�ַ
								  		 <td align='center' CLASS=Page_title valign='middle'>������Ϣ</td>
								   --%>
								  <td align='center' CLASS=Page_title valign='middle'>��ǰ��ַ</td>
								 
								  <td align='center' CLASS=Page_title valign='middle'>T1</td>
								  <td align='center' CLASS=Page_title valign='middle'>T2</td>
								  <td align='center' CLASS=Page_title valign='middle'>T3</td>
								  <td align='center' CLASS=Page_title valign='middle'>RH1</td>
								  <td align='center' CLASS=Page_title valign='middle'><span id="shishizhuizong">ʵʱ׷��<span></td>
								</tr>
								
								</thead>
								<tbody id="container">
								
								</tbody>
								<tr id="mymsg" style="display:none;font: 14;color: red" align="center"><td colspan="12" >�ƶ��豸δ��ʼ��...</td></tr>
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
