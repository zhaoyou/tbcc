<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
  <head>
    
    <title>ҳ�浼������</title>
    
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
					<img id="hqimage" src="img/stock_index_002.gif" style="cursor:pointer;display: ${sessionScope.hqId==null?'none':'inline' }" onclick="javascript:window.location.href = 'user.do?ope=doHqLogout';" alt="�����ܲ���֧ѡ��" title="�����ܲ���֧ѡ��" border="0"/><img border="0" alt="ע��"  title="ע��" style="cursor: pointer;" onclick="javascript:window.location.href='user.do?ope=doLogout'" src="img/stock_index_02.gif"/><span style="cursor: pointer;"><img border="0" alt="�ر�ϵͳ" title="�ر�ϵͳ" onclick="if(confirm('ȷ���˳�ϵͳ��'))window.close();" src="img/exit.JPG" /></span>
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
			<%--����������վ��ͳ�Ƶ�javascript --%>
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
