<%@ page language="java"  pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>医院信息配置列表</title>
<link href="../css/admin/index/hospitalAdd.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="../images/logo.ico" />
<script type="text/javascript" src="../js/jquery-1.4.min.js"></script>
<script type="text/javascript" src="../js/admin/hospitaladd.js"></script>
<style type="text/css">
/* common styling */
/* set up the overall width of the menu div, the font and the margins */
.menu {
	font-family: "宋体";
	width:1024px;
	font-size: 12px;
	margin-top: 0px;
	margin-right: 0;
	margin-bottom: 0px;
	margin-left: 0;
	background-image: url(../images/admin/head/bg_head03.jpg);
	background-repeat: repeat-x;
	height: 30px;
	padding: 0px;
	font-weight: normal;
	position:relative;
}
/* remove the bullets and set the margin and padding to zero for the unordered list */
.menu ul {
	padding:0;
	margin:0;
	list-style-type: none;
}
/* float the list so that the items are in a line and their position relative so that the drop down list will appear in the right place underneath each list item */
.menu ul li {
	float:left;
	position:relative;
}
/* style the links to be 104px wide by 30px high with a top and right border 1px solid white. Set the background color and the font size. */
.menu ul li a, .menu ul li a:visited {
	display:block;
	text-align:center;
	text-decoration:none;
	width:125px;
	height:28px;
	color:#2B2B2B;
	border:1px solid #fff;
	border-width:1px 1px 0 0;

	line-height:28px;
	font-size:12px;
	font-family: "宋体";
}
.menu ul li a {
	background-image: url(../images/admin/head/bg_head03.jpg);
	background-repeat: repeat-x;
	height: 28px;
}
.menu ul li a:visited {
	display:block;
	color:#000;
	font-weight: normal;
}
/* make the dropdown ul invisible */
.menu ul li ul {
	display: none;
	font-weight:bold;
}
/* specific to non IE browsers */
/* set the background and foreground color of the main menu li on hover */
.menu ul li:hover a {
	color:#000;
	background-image: url(../images/admin/head/bg_head02.jpg);
	background-repeat: repeat-x;
	font-weight: bold;
}
/* make the sub menu ul visible and position it beneath the main menu list item */
.menu ul li:hover ul {
	display:block;
	position:absolute;
	top:29px;
	left:0;
	width:105px;
}
/* style the background and foreground color of the submenu links */
.menu ul li:hover ul li a {
	display:block;
	color:#000;
	background: #E6E6E6;
	font-weight:normal;
	font-family:"宋体";
}
/* style the background and forground colors of the links on hover */
.menu ul li:hover ul li a:hover {
	color:#000;
	background: #029AFF;
	font-weight:normal;
}
</style>
<!--[if lte IE 6]>
<style type="text/css">
/* styling specific to Internet Explorer IE5.5 and IE6. Yet to see if IE7 handles li:hover */
/* Get rid of any default table style */
table {
border-collapse:collapse;
margin:0; 
padding:0;
}
/* ignore the link used by 'other browsers' */
.menu ul li a.hide, .menu ul li a:visited.hide {
display:none;
}
/* set the background and foreground color of the main menu link on hover */
.menu ul li a:hover {
color:#000;
	background-image: url(../images/admin/head/bg_head02.jpg);
	background-repeat: repeat-x;
	font-weight: bold;
}
/* make the sub menu ul visible and position it beneath the main menu list item */
.menu ul li a:hover ul {
display:block; 
position:absolute; 
top:29px; 
left:0; 
width:150px;
}
/* style the background and foreground color of the submenu links */
.menu ul li a:hover ul li a {
background:#E6E6E6;
color:#000;
font-weight:normal;
}
/* style the background and forground colors of the links on hover */
.menu ul li a:hover ul li a:hover {
background:#09f; 
color:#000;
font-weight:normal;
font-family:"宋体";
}
</style>
<![endif]-->

</head>
<body>
<div style="margin:0 auto; width:1024px;">
  <iframe scrolling="no" src="head.jsp" width=100% height=99 frameborder=0></iframe >
</div>
<div style="padding:0; margin:0; width:1024px; margin:0 auto;">
<div class="menu">
  <ul>
     <li><a class="hide" href="userconfig.do?ope=toOrgUser"><strong>用户管理</strong></a>
     
        <!--[if lte IE 6]>
<a href="userconfig.do?ope=toOrgUser"><strong>用户管理</strong>
<table><tr><td>
<![endif]-->
        <!--[if lte IE 6]>
</td></tr></table>
</a>
<![endif]-->
      </li>
       <li><a class="hide" href="#"><strong>医院配置</strong></a>
       <ul>
        <li><a href="hospital.do?ope=toList" >医院列表</a></li>
        <li><a href="hospital.do?ope=toAdd" >新增医院</a></li>
      </ul>
      <!--[if lte IE 6]>
</td></tr></table>
</a>
<![endif]-->
</li>
      <li style="float:right; height:28px; line-height:28px; margin-right:14px;color: #3a434a;"><span id="time"></span></li>
    </ul>
  <!-- clear the floats if required -->
  <div class="clear"> </div>
</div>
<div id="content">
  <div id="center" >
   <div class="nav_hospital"><img src="../images/admin/index/icon.gif"/>当前的位置:${ h==null?"新增医院信息":"更新医院信息" } </div>
   <hr/>
   <div class="content_list">
   	
   	  <form id="myform" action="hospital.do" method="post">
   	  	<div class="line"><span class="hospitalName">医院名称: <input name="hospitalName" id="hospitalName" value="${ h.hospitalName}"/></span>
   	  	<span class="projectId">工程编号: <input name="projectId" id="projectId" value="${h.projectId }"/> </span></div>
   	  	<div class="line"><span class="latitude">纬&nbsp;&nbsp;度: <input name="latitude" id="latitude" maxlength="8" value="${h.latitude }"/>(N)</span>
   	  	<span class="longitude">&nbsp;&nbsp;经  度: <input name="longitude" id="longitude" maxlength="10" value="${h.longitude }"/>(E)</span></div>
   	  	<div class="line"><input id="saveButton" type="button" value=" 保   存 "/><input type="hidden" name="ope" value="${ h==null?"doSave":"doUpdate" }"/></div>
   	 	<input  type="hidden" name="id" value="${h.id }"/>
   	  </form>
   </div>
  </div>
</div>
<div>
  <iframe scrolling="no" src="footer.jsp" width=100% height=26 frameborder=0></iframe >
</div>
</div>
</body>
</html>
