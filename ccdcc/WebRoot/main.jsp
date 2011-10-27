<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎进入药品冷链系统环境监管体系</title>
<link rel="Shortcut Icon" href="img/add/logo.ico" />
<link href="css/index/layout.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.STYLE1 {color: #454343}
.STYLE2 {color: #00FFCC}
.STYLE3 {	font-size: 12px;
	color: #2b7dbb;
	font-weight: bold;
}
-->
</style>
<script type="text/javascript">
	function golist(operate){
		document.getElementById('ope').value= operate ;
		document.myform.submit() ;
	}
</script>
</head>

<body >
<div>
 <iframe scrolling="no" src="common/header2.jsp" width=100% height=126 frameborder=0></iframe > </div>
<form name="myform" id="myform" method="post" action="pro.do">
	<input type="hidden" name="branchId" id="branchId" value="${param.branchId }"/>
	<input type="hidden" name="ope" id="ope" value=""/>
</form>
<div id="left">
  <ul id="nav">
  <li class="tab"><a href="#"></a></li>
<li class="tab_a"> <a href="#"><img src="images/index/icon_a.gif" width="20" height="22" />首页 </a> </li>
<li class="tab_b"><a href="javascript:golist('toRefList');"><img src="images/index/ref.gif" width="20" height="21" />仓库工程</a></li>
<li class="tab_b"><a href="javascript:golist('toCarList')"><img src="images/index/icon_bb.gif" width="20" height="21" />车载工程</a></li>
<%--<li class="tab_b"><a href="javascript:window.alert('暂不提供小批零操作!');"><img src="images/index/icon.gif" width="20" height="21" />小批零工程</a></li> --%>
<li class="tab_c"><a href="#">&nbsp;</a></li>
</ul>



</div>
<div id="line"><img src="images/index/icon2_090.gif" /></div>
<div id="right"></div>

<div>
<iframe scrolling="no" src="common/footer2.jsp" width=100% height=43 frameborder=0></iframe ></div>
</body>
</html>

