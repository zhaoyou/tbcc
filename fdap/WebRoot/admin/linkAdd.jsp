<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>菜单操作</title>
<link href="../css/admin/index/linkAdd.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="../images/logo.ico" />
<script type="text/javascript" src="../js/jquery-1.4.min.js"></script>
<style type="text/css">
/* common styling */
/* set up the overall width of the menu div, the font and the margins */
.menu {
	font-family: "宋体";
	width: 1024px;
	font-size: 12px;
	margin: 0 auto;
	background-image: url(../images/admin/head/bg_head03.jpg);
	background-repeat: repeat-x;
	height: 30px;
	padding: 0px;
	font-weight: normal;
	position: relative;
}
/* remove the bullets and set the margin and padding to zero for the unordered list */
.menu ul {
	padding: 0;
	margin: 0;
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


<script type="text/javascript">
		
		function goaction(url){
			$("#myform").action=url;
			$("#myform")[0].submit();
		}
		
		function gocancel(url,ope){
			$("#ope").val(ope);
			goaction(url);
		}
		
		//增加或修改探头时用到的方法
		function goAdd(url){
			var flag = false;
			$('form input:text').each(function(){
					if($(this).val()==""){
						flag = true;
						$(this).css('borderColor','red');
						$('#'+this.name+'Tip').css('color','red');
					}else{
						$(this).css('borderColor','gray');
						$('#'+this.name+'Tip').css('color','black');
					}
			});
			//验证是否有必须输入项为空
			if(flag){
				$('#tip').html( "请确保信息完整!" ) ;
				return ;
			}
			
			if($('#linkname').val().length>25){
				$('#linkname').css('borderColor','red');
				$('#tip').html( "菜单名称最长为25个字符" ) ;
				return;
			}else{
				$('#linkname').css('borderColor','gray');
			}
			
			if($('#linkurl').val().length>25){
				$('#linkurl').css('borderColor','red');
				$('#tip').html( "菜单地址最长为25个字符" ) ;
				return;
			}else{
				$('#linkurl').css('borderColor','gray');
			}
			
			goaction(url);
		}
		

</script>

</head>
<body>
<div style="margin:0 auto; width:1024px;">
  <iframe scrolling="no" src="head.jsp" width=100% height=99 frameborder=0></iframe >
</div>
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
    <li style="float:right; height:28px; line-height:28px; margin-right:14px;color: #3a434a;"><span id="time"></span></li>
  </ul>
  <!-- clear the floats if required -->
  <div class="clear"></div>
</div>
<div style="padding:0; margin:0; width:1024px; margin:0 auto;">
  <div id="content">
    <div id="center" >
    <div class="top" style="display:block;">
      <table id="tb" border="0" cellspacing="0" cellpadding="0" >
        <tr id="tb1" >
          <td colspan="7"><span style="text-align:left; float:left;padding-left:4px; margin-left:4px; color:#141414;"><img src=../images/admin/index/icon.gif width="9" height="10" /> 
          你当前所在位置为：菜单组配置=>${param.typename }的菜单配置=><c:if test="${linkinfo==null}">增加菜单</c:if><c:if test="${linkinfo!=null}">修改菜单</c:if></span></td>
        </tr>
      </table>
    </div>
    <div class="bottom">
      <div class="con_center">
        <form id="myform" name="myform" action="" method="post">
			<input type="hidden" name="ope" id="ope" value="${ope }" />
			<input type="hidden" name="linkid" id="linkid" value="${linkinfo.lid }" />
			<input type="hidden" name="typeid" id="typeid" value="${param.typeid }" />
			
			<p style="padding-top: 0px;padding-bottom: 8px;">&nbsp;</p>
          
          <p>
          	<label style="width: 60px;"></label>
            <label for="fdsafa" style="width:70px;">菜单名称：</label>
            <input name="linkname" id="linkname" type="text" value="${linkinfo!=null?linkinfo.name:'' }" class="input_a" style="width: 150px;"/>
            <span id="linknameTip" style="text-align:left; margin-left:5px;font-weight: bold;">*</span>
          </p>
          <p>
          	<label style="width: 60px;"></label>
            <label for="fdsafa" style="width:70px;">地 &nbsp;址：</label>
            <input name="linkurl" id="linkurl" type="text" value="${linkinfo!=null?linkinfo.url:'' }" class="input_a" style="width: 150px;"/>
            <span id="linkurlTip" style="text-align:left; margin-left:5px;font-weight: bold;">*</span>
          </p>
          <p style="padding-top: 2px;padding-right: 0px;padding-bottom:0px;padding-left: 0px; display:block;"><span style="float: none;">如：www.baidu.com</span></p>
          <p style="padding-top: 2px;padding-right: 0px;padding-bottom:0px;padding-left: 0px; display:block;">
          	<label style="width: 60px;"></label>
            <label for="fdsafa" style="width:70px;">组 菜 单：</label>
            <select id="ltid" name="ltid" style="width: 150px;float: left;">
            	<c:forEach var="type" items="${typelist}">
            		<option value="${type.ltid }" ${type.ltid==param.typeid?"selected='selected'":""}>${type.ltname }</option>
            	</c:forEach>
            </select>
            <label style="float: none;"></label>
          </p>
            <p style="padding-top: 2px;padding-right: 0px;padding-bottom:0px;padding-left: 0px; display:block;text-align: center;"><span id="tip" style="float: none;color: #f00;">${tip }&nbsp;</span><label style="width: 15px;"></label></p>
            <p style="padding-top: 2px;padding-right: 0px;padding-bottom: 20px;padding-left: 0px;">
            <input type="button" value="保存" class="but_a"  onclick="javascript:goAdd('linkconfig.do');"/>
            <input type="button" value="取消" class="but_b" onclick="javascript:gocancel('linkconfig.do','toLinkConfig');"/>
          </p>
        </form>
      </div>
    </div>
  </div>
  </div>
  <div>
    <iframe scrolling="no" src="footer.jsp" width=100% height=26 frameborder=0></iframe >
  </div>
</div>
</body>
</html>
