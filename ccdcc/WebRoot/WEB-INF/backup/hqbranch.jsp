<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>�ܲ���֧��ϵҳ��</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/style.css" rel="stylesheet" type="text/css">
	<LINK href="css/mainWin.css" type=text/css media=screen rel=stylesheet>
	<LINK href="css/mainWin2.css" type=text/css media=screen rel=stylesheet>
	<LINK href="css/desktop.css" type=text/css media=screen rel=stylesheet>
	<link rel="Shortcut Icon" href="img/add/logo.ico" >
	<script type="text/javascript" src="script/dtree/dtree.js"></script>
	<link rel="stylesheet" type="text/css" href="css/dtree/dtree.css">
	<link rel="stylesheet" type="text/css" href="css/dtree/hqbranch.css">
	<script type="text/javascript" src="script/titleTime.js"></script>
</head>
<center>
<jsp:include page="cheader.jsp" flush="true"></jsp:include>
</center>
<body onload="showtime();">
<div id="light" class="white_content">
	<div align="center"><h3>��ѡ��һ����֧����</h3></div>
      <div  class="con"> 
			<script type="text/javascript">
					
		<!--
		var d = new dTree('d');
		//����״̬��
		d.config.useStatusText=true;
		//�����ǲ��ǹر�ͬһ��������ڵ�
		d.config.closeSameLevel=false;
		//�ǲ��ǿ���ʹ��cookie
		d.config.useCookies=false;
		//Ĭ�����Ӹ��ڵ�
		d.add(0,-1,'�Ϻ�˼��Դ�����Ƽ����޹�˾');
		${tree}
		document.write(d);
		//-->
			</script>   
		</div>
		<div style="color: gray" align="right"><a href="javascript:window.location.href='user.do?ope=doLogout';">���µ�¼</a></div>
</div>

<div align="center" style="position: relative;top: 90%">
<div >
 <table width="990" border="0" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#CCCCCC" bordercolor="#FFFFFF"
			bordercolordark="#FFFFFF">
			
			<tr background="img/stock_index_49.gif">
				<td height="25" colspan="3" valign="top"
					background="img/stock_index_49.gif">
					<div  id="time" style="padding-top:8px; padding-left:30px; float:center; text-align: center;color: white; font-size: 14px">�Ϻ�˼��Դ�����Ƽ����޹�˾ ��Ȩ����(C)2008</div>
				</td>
			</tr>
			
	</table>
</div>
</div>
</body>
</html> 