<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>逐级配置</title>
<link href="../css/admin/index/orgList.css" rel="stylesheet" type="text/css" />
<link href="../css/admin/dtree.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="../images/logo.ico" />
<script type="text/javascript"  src="../js/admin/dtree.js"></script>
<script type="text/javascript" src="../js/jquery-1.4.min.js"></script>
<script type="text/javascript" src="../js/jquery-tool.js"></script>
<script type="text/javascript">

	$(document).ready(
		function (){
			$("span[name='codeTip']").tooltip({

			// place tooltip on the right edge
			position: "center right",
		
			// a little tweaking of the position
			offset: [-2, 10],
		
			// use the built-in fadeIn/fadeOut effect
			effect: "fade",
		
			// custom opacity setting
			opacity: 0.7

			});
		}
	) ;
	function goLowerOrg(oid){
		$('#oid').val(oid) ;
		$('#ope').val("toOrgList");
		$('#myform')[0].submit();
	}
	
	function goAddOrg(){
		$('#myform')[0].submit();
	}
	
	function goEditOrg(){
		var $chkOrg = $('form input:checkbox') ;
		if(!$chkOrg.length){
			window.alert('没有可选的机构');
			return  ;
		}
		var flag = false ;
		if(!$chkOrg.filter(":checked").length){
			window.alert('请选择一个编辑的机构');
			return  ;
		}
		
		if($chkOrg.filter(":checked").length>1){
			window.alert("一次只允许编辑一个机构");
			return ;
		}
		
		$('#ope').val('toEditOrg');
		$('#myform')[0].submit();
	}
	
	function goDelOrg(){
		var $chkOrg = $('form input:checkbox') ;
		if(!$chkOrg.length){
			window.alert('没有可选的机构');
			return  ;
		}
	
		if(!$chkOrg.filter(":checked").length){
			window.alert('请选择需要删除的机构');
			return  ;
		}
		
			
		

		if(window.confirm("所选机构(企业)下的工程、冷库、探头都会删除，你确定？")){
			$('#ope').val('doDelOrg');
			$('#myform')[0].submit();
		}
	}
	
	function isOrgQ(name){
		$('#tb').css('display','none');
		$('#wz').html("你当前所在位置为："+name+"(企业没有下级)");
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
     <form name="myform" id="myform" method="post" action="orgconfig.do">
  <input type="hidden" name="ope" value="toAddOrg" id="ope" />
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
          <span id="wz">你当前所在位置为： ${orgObj.name } 下级机构列表</span></div>
          <table border="0" cellpadding="0" cellspacing="0" id="tb" style="width:780px;">
            <tr class="altrow" >
              <td width="100">选择</td>
              <td width="156">机构名称</td>
              <td width="107">机构类型</td>
              <td width="133">机构账户名</td>
              <td width="150">机构电话</td>
              <td width="158">机构Email</td>
            </tr>
           <tbody>
          
          <c:forEach var="org" items="${orgList}" varStatus="irow">
	          <tr style="height:25px;" ${irow.index%2==0?"":"bgcolor='#f1f4f8'" }>
	            <td><input name="ckbOrg" type="checkbox" value="${org.oid }" /></td>
	            <td>${org.name }</td>
	            <td>
	            	<c:if test="${org.nodetype==1}">机构</c:if>
	            	<c:if test="${org.nodetype==2}">企业</c:if>
	            </td>
	            <td>${org.account }</td>
	            <td>${org.telephone }</td>
	            <td ><span name="codeTip" title="授权码：${org.authcode.code }">${org.email } </span></td>
	           
	          </tr>
          </c:forEach>
          </tbody>
          
            <tr>
              <td colspan="6"><br />
             		 ${msg }
                <table width="64%" class="oii">
                  <tr>
                    <td><a href="javascript:goAddOrg();" target="_parent"><img src="../images/admin/index/icon_f.gif" width="86" height="23"  style="cursor:pointer;"/></a></td>
                    <td><a href="javascript:goEditOrg();"><img src="../images/admin/index/icon_a.gif" width="86" height="23" /></a></td>
                    <td><a href="javascript:goDelOrg();"><img src="../images/admin/index/icon_b.gif" width="86" height="23" /></a></td>
                  </tr>
                </table>
                <br /></td>
            </tr>
          </table>
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
