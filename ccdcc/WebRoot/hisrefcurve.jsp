<%@ page language="java"  pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>仓库历史曲线</title>
<link rel="Shortcut Icon" href="img/add/logo.ico" />
<link href="css/cangku/tc.css" rel="stylesheet" type="text/css" />
<link href="css/cangku/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function golist(url,operate){
		document.getElementById('myform').action = url ;
		document.getElementById('ope').value = operate ;
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
	<input type="hidden" name="branchId" id="branchId" value="${param.branchId }"/>
</form>
<div id="right">
  <div id="top"><a href="#">&nbsp;<img src="images/canku/icon_c.gif" width="16" height="15"  class="tb4"/><strong>位置:首页</strong>&lt;您正在查看冷库历史曲线显示</a>
  <img src="img/util/back.gif" style="cursor: pointer;" onclick="javascript:golist('pro.do','toRefList');" width="48" height="20"  class="tb3"/>
  </div>
  <div id="bottom">
 	 <div>
  		<iframe scrolling="no" src="ccdcc/hisref2.html?branchId=${param.branchId }" width=100% height="600"  frameborder=0></iframe >
	<div>
  </div>
 	 
</div>
</div>
<div class="clear"></div>
<div>
  <iframe scrolling="no" src="common/footer2.jsp" width=100% height=43 frameborder=0></iframe >
</div>
</body>
</html>
