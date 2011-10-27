<%@ page language="java"  pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title></title>
<link rel="Shortcut Icon" href="../img/add/logo.ico" />
<link href="../css/head/head630.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../script/common.js"></script>
<script type="text/javascript">
	
	function doExit(){
		if(confirm('确定退出系统吗？'))
			{
				window.opener = null ;
				window.top.close() ;
			}
	}
</script>
</head>

<body onload="showtime();" >
<div id="head">
   <div id="banner">
   	<ul>
 	<li class="logo"><img src="../img/logo/${sessionScope.logo.logoImg }" width="110" height="65" style="display: ${sessionScope.logo.isShow==1?"none":"inline" }"  /></li>
	<li class="nav_a" style="display: none"><a  target="_parent"></a></li>
    <li class="nav_b"><a href="../user.do?ope=doLogout" target="_parent"></a></li>
    <li class="nav_c"><a href="javascript:doExit();" ></a></li>
    <li class="font_a"> ${sessionScope.logo.logoDisplayName }</li>
    <li class="font_b"> ${sessionScope.logo.logoDisplayName }</li>
      </ul>
  </div>
   
   <div id="top">
      <p><img src="../images/head/icon_b.gif" border="none" align="top" />  ${userInfo}</p>
   <span id="time"></span>   </div>
</div><!--head end-->
</body>
</html>