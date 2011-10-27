<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
  <head>
    
    <title>页面导航部分</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	 <link rel="Shortcut Icon" href="img/add/logo.ico" >
  </head>
  
  <body>
    <table width="990" border="0" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#CCCCCC" bordercolor="#FFFFFF"
			bordercolordark="#FFFFFF">
			<tr>
				<td height="90" colspan="3" align="right"
					background="img/stock_index_01.gif">		
					<img id="hqimage" src="img/stock_index_002.gif" style="cursor:pointer;display: ${sessionScope.hqId==null?'none':'inline' }" onclick="javascript:window.location.href = 'user.do?ope=doHqLogout';" alt="返回总部分支选择" title="返回总部分支选择" border="0"/><img border="0" alt="注销"  title="注销" style="cursor: pointer;" onclick="javascript:window.location.href='user.do?ope=doLogout'" src="img/stock_index_02.gif"/><span style="cursor: pointer;"><img border="0" alt="关闭系统" title="关闭系统" onclick="if(confirm('确定退出系统吗？'))window.close();" src="img/exit.JPG" /></span>
					</td>
			</tr>
			<tr align="center">
				<td height="35" colspan="3" align="left" 
					background="img/stock_index_06.gif">
					
				<div id="time" style="padding-top:6px; padding-right:20px; float: right; text-align: left;color: white;"></div>
				<div id="userInfo" style="padding-top:6px; padding-right:28px; float: right; text-align: left;color: white;">${userInfo}</div>
				</td>
			</tr>
			</table>
			<%--下面是增加站点统计的javascript --%>
<script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
try {
var pageTracker = _gat._getTracker("UA-15622024-1");
pageTracker._trackPageview();
} catch(err) {}</script>
  </body>
</html>
