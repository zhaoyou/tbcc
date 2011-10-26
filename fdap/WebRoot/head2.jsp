<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>页头</title>
<link href="css/head/head.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="images/logo.ico" />
<script type="text/javascript" src="js/common.js"></script>
</head>
<body onload="showtime();">
<div id="head">
  <div id="banner">
    <p><span >欢迎您:${loginUser.name }</span></p>
    <li class="nav_b"><a href="user.do?ope=doLogout" target="_parent"></a></li>
  </div>
  <div id="center">
    <ul id="nav_mail">
      <span id="time"></span>
    </ul>
  </div>
</div>
</body>
</html>
