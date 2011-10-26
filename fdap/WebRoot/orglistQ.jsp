<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>公司信息页面</title>
<link href="css/index/company.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="images/logo.ico" />
<script type="text/javascript" src="js/common.js"></script>

 <script type='text/javascript' src='dwr/interface/realcar.js'></script>
 <script type='text/javascript' src='dwr/engine.js'></script>
 <script type='text/javascript' src='dwr/util.js'></script>
 
<script type="text/javascript" language="javascript">
			
				//返回上级地图
				function goHighOrg(){
					document.myform.submit() ;
				}
				
				//企业数据查看
				function goData(url,method,id){
						document.myform.action = url ;
						document.getElementById("ope").value = method ;
						document.getElementById("oid").value = id ;
						document.myform.submit();
				}
				
				//页面初始化
				function init(){
					var oid = document.getElementById('oid').value ;
					if(oid==null || oid==""){
						return  ;
					}
					
					realcar.getAlarmOrgTip(oid,{
						callback:resultHandler,
						errorHandler:error_Handler,
						timeout:10000
					});
					
					
					window.setInterval(function (){
						realcar.getAlarmOrgTip(oid,{
						callback:resultHandler,
						errorHandler:error_Handler,
						timeout:10000
					});
					},10000);
					
				
				}
				
				//处理结果
				function resultHandler(result){
					document.getElementById('errorTip').style.display = "none" ;
					var oid = document.getElementById('oid').value ;
					if(result!=null){
						var b = document.getElementById("alarmLightDiv"+oid);
						if(result=="1"){
							b.innerHTML = "<img src='images/index/28.gif' width='16' height='10' alt='正常状态' title='正常状态'/>"
						}else{
							b.innerHTML = "<img src='images/index/29.gif' width='16' height='10' alt='报警状态' title='报警状态'/>" ;
						}
					}											
				}
				
				//错误处理程序
				function error_Handler(err){
						document.getElementById('errorTip').style.display = "inline" ;		
				}
				
</script>
</head>
<body onload="init();">
<div>
  <iframe scrolling="no" src="head.jsp" width=100% height=128 frameborder=0></iframe >
</div>
<form action="org.do" name="myform" id="myform" method="post">
<input type="hidden" name="ope"  id="ope"  value="toHigherOrg"/>
<input  type="hidden" name="oid"  id="oid" value="${oid }"/>
</form>
<div id="content">
  <div id="center" >
    <div class="top" style="position:relative; display:block;">
      <table id="tb" border="0" cellspacing="0" cellpadding="0" >
        <tr id="tb1" >
          <td colspan="7"><span style="text-align:left; float:left;padding-left:4px; margin-left:4px; color:#141414;">
          <img src=images/index/icon.gif width="9" height="10" /> 当前位置：${org.name }的企业信息
          	<span id="errorTip" style="color: red;display: none" >(获取报警数据失败 ...)</span>
          </span>
          <span style="display:  ${loginUser.fdaporg.oid==oid?'none':'inline' }">
          	<a href="javascript:goHighOrg();"><img src="images/index/back.gif" width="38" height="21" 
        	class="pho"/></a>
          </span>
        </td>
        </tr>
      </table>
    </div>
    <div class="bottom">
      
      <table id="tb" border="0" cellspacing="0" cellpadding="0">
        <tr class="altrow" >
          <td width="81">编号</td>
          <td width="175">企业名称</td>
          <td width="164">企业账户</td>
          <td width="141">当前报警状态</td>
          <td width="417">操作</td>
        </tr>
        
        	 <tr style="height:28px;" >
		          <td>1</td>
			          <td>${org.name }</td>
			          <td>${org.account }</td>
			          <td>
			          <div id="alarmLightDiv${org.oid }">
			          	<img src="images/index/28.gif" width="16" height="10" alt='正常状态' title='正常状态'/>
			          </div>			  
			          </td>
			          <td><table width="100%" border="0" cellpadding="3" cellspacing="5">
			            <tr>
			              <td width="65"  style="border:none; text-align:center;"><a href="javascript:goData('refReal.do','toRealRef',${org.oid });"><img src="images/index/show_s.jpg" width="75" height="21" /></a></td>
			              <td width="65" align="center" style="border:none; text-align:center;"><a href="javascript:goData('realAlarm.do','toRealAlarm',${org.oid });"><img src="images/index/show_b.gif" width="75" height="21" /></a></td>
			               <td width="65" align="center" style="border:none; text-align:center;"><a href="javascript:goData('refHis.do','toRefHis',${org.oid });"><img src="images/index/show_cc.gif" width="75" height="21" /></a></td>
			              <td width="65" style="border:none; text-align:center;"><a href="javascript:goData('hisAlarm.do','toAlarmHis',${org.oid });"><img src="images/index/show_g.gif" width="75" height="21" border="0" /></a></td>
			            </tr>
			          </table></td>
		        </tr>
      </table>
    </div>
    <%--控制页面的显示高度 --%>
 
   	<div style="height: 380px"></div>
    
    
  </div>
</div>
<div>
  <iframe scrolling="no" src="footer.jsp" width=100% height=26 frameborder=0></iframe >
</div>
</body>
</html>
