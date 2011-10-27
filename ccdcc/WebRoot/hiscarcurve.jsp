<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>车载历史曲线查看</title>
<link rel="Shortcut Icon" href="img/add/logo.ico" />
<link href="css/chezai/tc.css" rel="stylesheet" type="text/css" />
<link href="css/chezai/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function golist(url,operate){
			document.getElementById('ope').value = operate ;
			document.myform.action = url ;
			document.myform.submit() ;
	}
</script>
</head>
 
<body>
<div>
  <iframe scrolling="no" src="common/header2.jsp" width=100% height=126 frameborder=0></iframe >
</div>
<form action="" name="myform" id="myform" method="post">
	<input type="hidden" name="ope" id="ope" value=""/>
	<input type="hidden" name="branchId" id="branchId" value="${param.branchId }" />
	<input type="hidden" name="time1" id="time1" value="${param.t1 }" />
	<input type="hidden" name="time2" id="time2" value="${param.t2 }" />
	<input type="hidden" name="proId" id="proId" value="${param.proId }"/>
</form>
<div id="right">
  <div id="top"><a href="#"><img src="images/chezai/icon_c.gif" width="16" height="15"  class="tb4"/><strong>位置:首页</strong>&lt;您正在查看车载历史曲线</a>
  <img src="img/util/back.gif" width="48" height="20" style="cursor:pointer" class="tb3" onclick="golist('startup.do','doStartUpCar');"/></div>
  <div id="bottom">
  	<div>
  		<iframe scrolling="no" src="ccdcc/hiscar3.html?branchId=${param.branchId }&proId=${param.proId }&sid=${param.sid }&time1=${param.t1 }&time2=${paramt2 }" width=100% height="630"  frameborder=0></iframe >
	<div>
  </div>
</div>
<div class="clear"></div>
<div>
  <iframe scrolling="no" src="common/footer2.jsp" width=100% height=43 frameborder=0></iframe >
</div>
</body>
</html>

