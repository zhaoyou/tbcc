<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${fdaporg.name }车载实时报警页面</title>
<link rel="Shortcut Icon" href="images/logo.ico" />
<link href="css/index/real_alarm.css" rel="stylesheet" type="text/css" />
 <script type='text/javascript' src='dwr/interface/realcar.js'></script>
 <script type='text/javascript' src='dwr/engine.js'></script>
 <script type='text/javascript' src='dwr/util.js'></script>
<script type="text/javascript" language="javascript">

	var i = 1 ;

	function goback(url,method){
			document.myform.action = url ;
			document.getElementById('ope').value = method ;
			document.myform.submit() ;
	}
	
	function init(){
		var p = document.getElementById("projectIds").value ;
		
		if(p==null || p==""){
			document.getElementById('okTip').innerHTML = "<font color='blue'>没有配置相应的工程!</font>" ;
			return ;		
		}
		
		
		
		realcar.getRealAlarm(p,{
			callback:resultHandler,
			errorHandler:errorHandler,
			timeout:10000
		});
		
		
		
		window.setInterval(function(){
				realcar.getRealAlarm(p,{
				callback:resultHandler,
				errorHandler:errorHandler,
				timeout:10000
			});
		},10000);
	}
	
	function errorHandler(err){
		
		document.getElementById('errorTip').style.display= "inline";
	}
	
	var cellfunction = [
			function (data){
				return i++ ;
			},
			function (data){
				return data.refName ;
			},
			function (data){
				if(data.alarmlevel==1){
						return "<img src='images/index/red.gif' /><font color='red'>严重报警</font>" ;
				}else if(data.alarmlevel==2){
						return "<img src='images/index/orange.gif' /><font color='pink'>普通报警</font>" ;
				}else{
						return "<img src='images/index/yellow.gif' /><font color='orange'>轻微报警</font>" ;
				}
			},
			function (data){
				return  data.aiName+":"+data.alarmdata+((data.alarmtype==1||data.alarmtype==2)?"℃":"％")+
				(data.alarmtype==1||data.alarmtype==3?"<font color='blue'>↓</font>":"<font color='red'>↑</font>")+"("+data.alarmstandard+
				((data.alarmtype==1||data.alarmtype==2)?"℃":"％")+")";
			},
			function (data){
				return data.alarmDate ;
			}
	] ;
	
	function resultHandler(result){
			
			//隐藏错误提示
			document.getElementById('errorTip').style.display= "none";
			document.getElementById('okTip').innerHTML = "" ;
			
			
			//存在报警信息
			
			if(result!=null && result.length>0){
			
				
			//	document.getElementById('okTip').style.display = "none" ;
				
				i = 1 ;
				DWRUtil.removeAllRows("container");
		
			//把数据添加到表格
			DWRUtil.addRows("container",result,cellfunction,{
				escapeHtml:false ,
				cellCreator:function(options){
					var td = document.createElement("td");
					td.align = "center";
					return td ;
				},
				rowCreator:function(options){
					var tr= document.createElement("tr");
					return tr ;
				}
			});
				
			}else{			//如果没有报警信息
			
				DWRUtil.removeAllRows("container");
				document.getElementById('okTip').innerHTML  = "<font color='blue'>当前没有报警数据</font>" ;
			
			}
	}
	
	
</script>
</head>
<body onload="init();">
<div>
  <iframe scrolling="no" src="head.jsp" width=100% height=128 frameborder=0></iframe >
</div>
<form name="myform" id="myform" action="" method="post">
<input  type="hidden" name="ope" id="ope" value=""/> 
<input type="hidden" name="oid"  id="oid" value="${fdaporg.oid }"/>
<input type="hidden" name="projectIds"  id="projectIds" value="${projectIds }"/>
</form>
<div id="content">
  <div id="center" >
    <div class="top" style="position:relative; display:block;">
      <table id="tb" border="0" cellspacing="0" cellpadding="0" >
        <tr id="tb1" >
          <td colspan="7"><span style="text-align:left; float:left;padding-left:4px; margin-left:4px; color:#141414;">
          <img src=images/index/icon.gif width="9" height="10" /> 当前位置： 查看${ fdaporg.name }车载实时报警页面</span>
          <a href="javascript:goback('org.do','toHigherOrg')"><img src="images/index/back.gif" width="58" height="21" 
        class="pho"/></a></td>
        </tr>
      </table>
    </div>
    <div class="bottom">
      <table id="tb" border="0" cellspacing="0" cellpadding="0">
           <tr>
          <td colspan="6">
          <table width="100%" style="height:35px;">
            <tr>
              <td width="110" style="border:none;"><a href="javascript:goback('realAlarm.do','toRealRefAlarm');"><img src="images/index/alarm_b.gif" width="100" height="24" /></a></td>
              <td width="110" style="border:none;"><a href="javascript:;"><img src="images/index/alarm_a.gif" width="100" height="24" /></a></td>
              <td width="110" style="border:none;"><a href="javascript:goback('realAlarm.do','toRealAlarm');"><img src="images/index/alarm_c.gif" width="100" height="24" /></a></td>
              <td width="608" style="border:none;">&nbsp;</td>
            </tr>
          </table>
          </td>
        </tr>
        <tr class="altrow_d">
          <td colspan="6">车载实时报警数据 <span  id="errorTip" style="color: red;display: none" >(无法获取到数据.....)</span></td>
        </tr>
    
        <tr class="altrow" >
          <td width="97">编号</td>
          <td width="205">车牌号码</td>
          <td width="150">严重级别</td>
          <td width="155">报警信息</td>
          <td width="221">报警时间</td>
        </tr>
        <%-- <tr id="waitTip"><td colspan="6">正在获取数据......</td></tr>--%>     
        <tbody id="container"></tbody>   	
      </table>
       <div align="center"> <div  id="okTip" >正在获取数据......</div></div>
    </div>
    <br />
    <div class="bottom" style="height: 310px;"></div>
  </div>
</div>

<div>
  <iframe scrolling="no" src="footer.jsp" width=100% height=26 frameborder=0></iframe >
</div>
</body>
</html>
