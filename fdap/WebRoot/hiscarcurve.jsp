<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>车载历史曲线查看</title>
<link rel="Shortcut Icon" href="img/add/logo.ico" />
<link href="css/index/car_curve.css" rel="stylesheet" type="text/css" />
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
  <iframe scrolling="no" src="head.jsp" width=100% height=126 frameborder=0></iframe >
</div>
<form action="" name="myform" id="myform" method="post">
	<input type="hidden" name="suppagecount" id="suppagecount" value="${param.suppagecount }"/>
    <input type="hidden" name="suppage" id="suppage" value="${param.suppage }"/>
	<input type="hidden" name="checkedstartTime" id="checkedstartTime" value="${param.checkedstartTime }"/>
    <input type="hidden" name="checkedendTime" id="checkedendTime" value="${param.checkedendTime }"/>
	<input type="hidden" id="sid" name="sid" value="${param.sid }"/>
 	<input type="hidden" id="oid" name="oid" value="${param.oid }"/>
   	<input type="hidden" id="checkedrefId" name="checkedrefId" value="${param.checkedrefId }"/>
   	<input type="hidden" id="pNO" name="pNO" value="${param.pNO }" />
   	<input type="hidden" name="carrier" id="carrier" value="${param.carrier }" />

	<input type="hidden" name="ope" id="ope" value=""/>
	<input type="hidden" name="time1" id="time1" value="${param.time1 }" />
	<input type="hidden" name="time2" id="time2" value="${param.time2 }" />
</form>
<div id="right">
  <div id="top"><a href="#"><img src="images/index/icon.gif" width="16" height="15"  class="tb4"/><strong>位置:首页</strong>&lt;您正在查看车载历史曲线</a>
  <img src="images/index/back.gif" width="58" height="21" style="cursor:pointer" class="tb3" onclick="golist('startUp.do','doStartUpPage');"/></div>
  <div id="bottom">
  	<div>
  		<iframe scrolling="no" src="fdap/hiscar3.html?oid=${param.oid }&sid=${param.sid }&refId=${param.checkedrefId }" width=100% height="630"  frameborder=0></iframe >
	</div>
  </div>
</div>
<div class="clear"></div>
<div>
  <iframe scrolling="no" src="footer.jsp" width=100% height=43 frameborder=0></iframe >
</div>
</body>
</html>

