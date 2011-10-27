<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>列表</title>
<link href="css/list/list.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="img/add/logo.ico" />
<script type="text/javascript">
		function goback(){
			window.location.href = 'configUser.do?ope=forwordConfigUser' ;
		}
		
		function gopage(i){
			document.getElementById('index').value = i ;
			document.myform.submit() ;
		}
</script>
</head>
<body>
<div>
  <iframe scrolling="no" src="common/header3.jsp" width=100% height=126 frameborder=0></iframe >
</div>
<form action="alarmlogs.do?ope=pageLogsList" method="post" name="myform" id="myform">
	<input type="hidden" name="allCount" id="allCount" value="${allCount }"/>
	<input type="hidden" name="allNumber" id="allNumber" value="${allNumber }"/>
	<input type="hidden" name="index" id="index" value="${index }"/>
	<input type="hidden" name="userName" id="userName" value="${param.userName eq null?userName:param.userName}"/>
	
</form>
<div id="right">
  <div id="top"><a href="#"><img src="images/canku/icon_c.gif" width="16" height="15"  class="tb4"/>
  <strong>位置:首页</strong>&lt;查看用户的登录报警日志</a>
  <img src="images/admin/back_a.gif" style="cursor: pointer;" width="58" height="20"  class="tb3" onclick="javascript:goback();"/></div>
  <div id="center">
    <div>${param.userName }登录报警日志</div>
  </div>
  <div id="bottom">
    <table width="77%" border="0" align="center" cellpadding="0" cellspacing="0" id="tb">
      <tr id="tb22">
        <td >用户名</td>
        <td >登录时间</td>
        <td >登录时报警状态</td>
        <td >注销时间</td>
        <td >注销时报警状态</td>

      </tr>
      <c:forEach var="logs" items="${logsList}" varStatus="i">
      	<tr ${i.index%2==0?" class='altrow'":"" }>
        <td>${logs.userName }</td>
        <td>${logs.loginTimeStr }</td>
        <td>${logs.loginAlarmStateStr }</td>
        <td>${logs.logoutTimeStr }</td>
        <td>${logs.logoutAlarmStateStr }</td>
      </tr>
      </c:forEach>
      
      <logic:empty name="logsList">
      <tbody>
      		<tr><td colspan="5"><div style="color: red">当前用户没有日志...</div></td></tr>
      	</tbody>
      </logic:empty>
      	
     
      <tr>
        <td height="31" colspan="5" align="left" style="background-image:url(images/admin/button_d.gif)"><table width="50%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin:0 auto; text-align:left;" >
          <tr >
            <td width="50" height="19" style="border: none;">
            	<c:if test="${index>1}">
            		<img alt="首页" src="images/admin/but_aa.gif" style="cursor: pointer;" onclick="javascript:gopage('1')"/>
            	</c:if>
            	
            	<c:if test="${index<=1}">
            		<img alt="首页" src="images/admin/but_aaa.gif"/>
            	</c:if>
            	
            </td>
            <td width="50" style="border: none;">
            	<c:if test="${index>1}">
            		<img alt="上一页" src="images/admin/show_sss.gif" style="cursor: pointer;" onclick="javascript:gopage('${index-1}')"/>
            	</c:if>
            	
            	<c:if test="${index<=1}">
            		<img alt="上一页" src="images/admin/show_ssss.gif"/>
            	</c:if>
            	        	
            </td>
            <td width="50" style="border: none;">
            	<c:if test="${index<allNumber}">
            		<img alt="下一页" src="images/admin/show_xx.gif" style="cursor: pointer;" onclick="javascript:gopage('${index+1}')" />
            	</c:if>
            	<c:if test="${index>=allNumber}">
            		<img alt="下一页" src="images/admin/show_xxx.gif"/>
            	</c:if>
            	        	
            </td>
            <td width="50" style="border: none;">
          	    <c:if test="${index<allNumber}">
          	    	<img alt="尾页" src="images/admin/but_bb.gif" style="cursor: pointer;" onclick="javascript:gopage('${allNumber}')"/>
          	    </c:if>
            	<c:if test="${index>=allNumber}">
            		<img alt="尾页" src="images/admin/but_bbb.gif"/>
            	</c:if>
            	
            </td>
            </tr>
        </table></td>
      </tr>
    </table>
  </div>
  
</div>
<div class="clear"></div>
<div>
  <iframe scrolling="no" src="common/footer2.jsp" width=100% height=43 frameborder=0></iframe >
</div>
</body>
</html>
