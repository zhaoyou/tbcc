<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="taglib.jsp" %>
<html>
  <head>

    
    <title>My JSP 'error.jsp' starting page</title>
    
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
		<script type="text/javascript" src="script/common.js"></script>
		<script type="text/javascript" src="script/titleTime.js"></script>
  </head>
  
  <body onload="showtime();">
  <jsp:include page="../header.jsp"></jsp:include>
  <table width="990" border="0" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#CCCCCC" bordercolor="#FFFFFF"
			bordercolordark="#FFFFFF">
			<tr align="center">
			<td  height="990" background="img/stock_index_08.gif" valign="top">				
				<img src="/ccdcc/img/fault_404.gif" align="middle" > 
				 &nbsp;&nbsp;&nbsp;<br>你请求的资源发生错误!
				<span onclick="javascript:window.history.back();"><font color="pink" style="cursor: hand" > 返回</font></span>		</td>
							</tr>
  		
  		<jsp:include page="../footer.jsp"></jsp:include>
  </body>
</html>
