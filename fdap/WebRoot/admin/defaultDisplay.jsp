<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>机构企业列表</title>
<link href="../css/admin/index/orgList.css" rel="stylesheet" type="text/css" />
<link href="../css/admin/dtree.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="../images/logo.ico" />
<script type="text/javascript"  src="../js/admin/dtree.js"></script>
<script type="text/javascript" src="../js/jquery-1.4.min.js"></script>
<script type="text/javascript" src="../js/jquery-tool.js"></script>
<script type="text/javascript">
	function gocancel(){
		$('#myform').attr('action','orgconfig.do') ;
		$('#ope').val("goAdmin");
		$('#myform')[0].submit();
	}
	
	function godefault(oid,name,isshowmap){
		d.openTo(oid,true);
		$('#oid').val(oid);
		$('#wz').html("你当前所在位置为： "+name+" 默认显示方式的选择");
		$('#tip').html("");
		if(isshowmap==0){
			//$('#Nmap').val('0');
			//$('#Nmap').attr('checked',true);
			//$('#Ymap').attr('checked',false).attr('disabled',true);
			//$('#dtfont').attr('color','#dddddd');
			$('#showmap').css('display','none');
		}else{
			$('#showmap').css('display','block');
			$('#Nmap').val('1');
			if(isshowmap==2){
				$('#Nmap').attr('checked',false);
				$('#Ymap').attr('disabled',false).attr('checked',true);
				//$('#dtfont').attr('color','#000000');
			}else{
				$('#Nmap').attr('checked',true);
				$('#Ymap').attr('disabled',false).attr('checked',false);
				//$('#dtfont').attr('color','#000000');
			}
		}
		//$('#ope').val("toDefault");
		//$('#oid').val(oid);
		//$('#myform')[0].submit();
	}
	
	function goUpDefault(){
		$('#myform')[0].submit();
	}
</script>
<style type="text/css">
	/* simple css-based tooltip */
.tooltip {
	background-color:#000;
	border:1px solid #fff;
	padding:10px 15px;
	width:200px;
	display:none;
	color:#fff;
	text-align:left;
	font-size:12px;

	/* outline radius for mozilla/firefox only */
	-moz-box-shadow:0 0 10px #000;
	-webkit-box-shadow:0 0 10px #000;
}
</style>
</head>
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
<body>
<div style="margin:0 auto; width:1024px;">
  <iframe scrolling="no" src="head.jsp" width=100% height=99 frameborder=0></iframe >
</div>
<div style="padding:0; margin:0; width:1024px; margin:0 auto;">
  <div id="content">
    <div class="menu">
      <ul>
        <li><a class="hide" href="#"><strong>机构配置</strong></a>
          <!--[if lte IE 6]>
<a href="#"><strong>机构配置</strong>
<table><tr><td>
<![endif]-->
          <ul>
          	<li><a href="orgconfig.do?ope=toTopOrg" >顶层配置</a></li>
        	<li><a href="orgconfig.do?ope=toOrgList" >逐级配置</a></li>
        	<%--<li><a href="defaultdisplay.do?ope=toDefault" >机构显示方式</a></li>--%>
          </ul>
          <!--[if lte IE 6]>
</td></tr></table>
</a>
<![endif]-->
        </li>
        <li><a class="hide" href="#"><strong>工程配置</strong></a>
          <!--[if lte IE 6]>
<a href="#"><strong>工程配置</strong>
<table><tr><td>
<![endif]-->
          <ul>
          	<li><a href="storeconfig.do?ope=toStoreList" >仓储类型配置</a></li>
        	<li><a href="projectconfig.do?ope=toList" >企业项目配置</a></li>
        	<li><a href="configinfo.do?ope=toGetConfig" >详细配置查看</a></li>
          </ul>
          <!--[if lte IE 6]>
</td></tr></table>
</a>
<![endif]-->
        </li>
        <li><a class="hide" href="#"><strong>系统用户配置</strong></a>
          <!--[if lte IE 6]>
<a href="#"><strong>系统用户配置</strong>
<table><tr><td>
<![endif]-->
          <ul>
            <li><a href="userconfig.do?ope=toEngineer" >工程人员</a></li>
        	<li><a href="userconfig.do?ope=toSysadmin" >系统管理员</a></li>
          </ul>
          <!--[if lte IE 6]>
</td></tr></table>
</a>
<![endif]-->
        </li>
        <li><a class="hide" href="#"><strong>菜单配置</strong></a>
      <!--[if lte IE 6]>
<a href="#"><strong>链接配置</strong>
<table><tr><td>
<![endif]-->
      <ul>
        <li><a href="linktype.do?ope=toLinktype" >自定义菜单</a></li>
      </ul>
      <!--[if lte IE 6]>
</td></tr></table>
</a>
<![endif]-->
    </li>
    <li><a class="hide" href="#"><strong>电话配置</strong></a>
      <!--[if lte IE 6]>
<a href="#"><strong>电话配置</strong>
<table><tr><td>
<![endif]-->
      <ul>
        <li><a href="phoneconfig.do?ope=toPhone" >号码设置</a></li>
      </ul>
      <!--[if lte IE 6]>
</td></tr></table>
</a>
<![endif]-->
    </li>
        <li style="float:right; height:28px; line-height:28px; margin-right:14px;color: #3a434a;"> <span id="time"></span> </li>
      </ul>
      <!-- clear the floats if required -->
      <div class="clear"> </div>
    </div>
     <form name="myform" id="myform" method="post" action="defaultdisplay.do">
  	<input type="hidden" name="ope" value="doDefault" id="ope" />
  	<input type="hidden" name="oid" id="oid" value="${orgObj==null?1:orgObj.oid}"/>
      <div id="left">
        <h1><img src="../images/admin/index/h1.gif" width="191" height="27" /></h1>
        <div class="dtree" id="divDrag" style="text-align:left; overflow:scroll; width:190px; height: 453px;">
          <label style="color:#FFF; "></label>
          <script type="text/javascript">	
				${tree}
				window.document.write(d);
				d.openTo(${orgObj==null?1:orgObj.oid},true) ;
	</script>
        </div>
      </div>
      <div id="right">
        <div id="con_right">
          <div><img src="../images/admin/index/icon.gif" width="9" height="10"  class="img_a" />
          <span id="wz">你当前所在位置为： ${orgObj.name } 默认显示方式的选择</span></div>
          <span id="showmap"><h3>选择页面默认显示方式</h3><br />
          <input type="radio" name="isShowmap" id="Nmap" value="${orgObj.isshowmap==0?0:1 }" ${orgObj.isshowmap!=2?"checked='checked'":""} /><font size="3">列表方式</font>
          <input type="radio" name="isShowmap" id="Ymap" value="2" ${orgObj.isshowmap==2?"checked='checked'":""} ${orgObj.isshowmap==0?"disabled='disabled'":"" }/><font id="dtfont" size="3" ${orgObj.isshowmap==0?"color='#dddddd'":"color='#000000'" }>地图方式</font>
          <br /><br /><span id="tip"><font color="blue">${tip }</font></span>
          <br /><br />
          <a href="javascript:goUpDefault();" target="_parent"><img src="../images/admin/index/icon_a.gif" width="86" height="23"  style="cursor:pointer;"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          
          <a href="javascript:gocancel();" target="_parent"><img src="../images/admin/index/icon_x.gif" width="86" height="23"  style="cursor:pointer;"/></a>
        	</span>
        </div>
      </div>
    </form>
  </div>
  <div>
    <iframe scrolling="no" src="footer.jsp" width=100% height=26 frameborder=0>6</iframe >
  </div>
</div>
</body>
</html>
