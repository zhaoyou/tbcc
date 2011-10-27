<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="common/taglib.jsp" %>
<%@page import="org.hibernate.Session"%>
<%@page import="org.tbcc.util.HibernateSessionFactory"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>这是显示所有客户仓库报警的简易平台</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
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
			
			
	</script>
	
	<script type="text/javascript">
			//采用原生态Ajax代码请求，返回冷库的历史数据
			var xmlHttpRequest = null  ;
			
			
			//创建xmlhttprequest  对象
			function createXMLHttpRequest(){
				 if (window.XMLHttpRequest) { 
     			   		xmlHttpRequest = new XMLHttpRequest();  // Mozilla、Firefox、Safari 
  				  }else if (window.ActiveXObject) { 
      				  xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP"); // Internet Explorer
  				  }		
  				  	
			}
			
			//获取请求的路径
			function getURL(){
				//var branchId = document.getElementById("branchId").value ;
				//var pid = document.getElementById("myrid").value ;
				//var url = "realref.do?ope=doRealList&projectId="+pid+"&branchId="+branchId+"&time="+new Date().getTime(); ;
				return "Alarm.jsp?loop=1&time="+new Date().getTime(); 
			}
			
			
			
			//请求状态发生改变时处理函数
			function statusChangeHandler(){
				//表示服务器端已经处理完毕
				if(xmlHttpRequest.readyState==4 ){
					if(xmlHttpRequest.status==200){
						var r = xmlHttpRequest.responseText ;
						
						if(r.indexOf("nodata")==-1){
							var re = r.split(",") ;
							var str = "" ;
							for(var i=0;i<re.length;i++){
								
								str  = str +re[i]+"</br>" ;
							}
							document.getElementById("myalarm").innerHTML = "" ;
							document.getElementById("myalarm").innerHTML = str ;
							document.getElementById("isok").style.display = "none" ;
							document.getElementById("ismygod").style.display = "block" ;
							var c = document.getElementById("myknow") ;
		
							stopAlarm();
							if(c.checked)
								beginAlarm();
						}else{
							document.getElementById("myalarm").innerHTML = "" ;
							document.getElementById("isok").style.display = "block" ;
							document.getElementById("ismygod").style.display = "none" ;
							var c = document.getElementById("myknow") ;
							c.style.display = "none" ;
							c.checked = false ;
							stopAlarm();
						}
				    }
			    }
			}
			
			
			
			//每个10秒调用的方法
			function getData(){
			    createXMLHttpRequest() ;			    
				xmlHttpRequest.onreadystatechange = statusChangeHandler ;
				xmlHttpRequest.open("GET",getURL());
				xmlHttpRequest.send(null);	
				window.setTimeout("getData()",10000); //每隔10秒钟刷新一次页面		
				
			}
			
			function stopAlarm(){
				if(document.getElementById("player")!=null){	
					document.getElementById("playerContainer").removeChild(document.getElementById("player"));
				}		
			}
			</script>
  </head>
  
  <body onload="getData();">
  <input type="hidden" name="contextPath" value=<%=basePath %>/>
  <center>
   <h3>上海思博源仓库报警监管平台</h3>
   <%
   		Session s = HibernateSessionFactory.getSession() ;
   		String sql = "select branchName from tbccbranchtype where branchId in ("+
					"select branchid from TbccBranchPrjRelation where projectId in ("+
					"select projectId from TbccRealData_Ref where AlarmStatus_Ref1=1 or "+ 
					" AlarmStatus_Ref2=1  or  AlarmStatus_Ref3=1 or AlarmStatus_Ref4=1 or "+
					" ConnectStatus = 1 ))" ;
					
		List<String> list = s.createSQLQuery(sql).list() ;
		
		request.setAttribute("Alarmlist",list);
		
		if(request.getParameter("loop")!=null){
		
		if(list==null || list.size()==0)
		{
			out.clear();
			out.println("this is nodata");
			return  ;
		}
		
			
		StringBuffer sb = new StringBuffer();
			for(int j=0;j<list.size();j++){
				sb.append(list.get(j));
				if(j!=list.size()-1)
					sb.append(",");
			}
			out.clear();
			out.println(sb.toString());
			return  ;
		}
		%>
		
		
				<div id="isok" style="display: ${empty Alarmlist?'block':'none'  }" ><font color="blue">所有仓库一切正常!</font></div>
		
		
		<table id="mytable" border="1">
			<tbody >	
				<tr>		<td><div id="myalarm">
			<logic:notEmpty name="Alarmlist">		
					<logic:iterate id="n" name="Alarmlist">
							${n} <br>
  					</logic:iterate>			
				</logic:notEmpty>
				</div></td></tr>
			</tbody>
		</table>
		
		
		
			<div id="ismygod" style="display: ${empty Alarmlist?'none':'block'  }"><h5 style='color:red'>以上机构仓库发生了报警或连接已经断开!</h5></div>
			<div id="iknow" style="display: ${empty Alarmlist?'none':'block'  }" >
				报警声音:<input type="checkbox" name="myknow"  id="myknow"/>我已经处理
			</div>
    </center>
    <div id="playerContainer"></div>
  </body>
</html>
