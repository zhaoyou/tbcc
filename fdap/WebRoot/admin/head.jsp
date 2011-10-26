<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>admin head</title>
<link href="../css/admin/head/head.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/admin/script.js""></script>
<script type="text/javascript" src="../js/common.js"></script>
<link href="../css/admin/menu/style.css" rel="stylesheet" type="text/css" />
</head>
<body onload="showInfo();" >
<div id="head">
  <div id="banner">
    <p><span>欢迎您:${loginUser.name }</span></p>
    <li class="nav_b"><a href="../user.do?ope=doLogout" target="_parent"></a></li>
  </div>
 </div>
</body>
</html>
