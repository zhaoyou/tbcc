<%@ page language="java"  pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${name==null }">增加用户</c:if><c:if test="${name!=null }">修改用户</c:if></title>
<link href="../css/admin/index/orgUserAdd.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="../images/logo.ico" />
<script type="text/javascript" src="../js/jquery-1.4.min.js"></script>
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

	<script type="text/javascript">
		var flag = false;
		//var p1 = /[^\w]/g;
		function check(o){
			if(o.value=="")return;
			var w1 = /^([a-zA-Z_][\w]*)$/;
			if(!w1.test(o.value)){
				o.style.borderColor="red";
				$("#tipMsg").html("<font color='red'>用户名由字母、数字和下划线组成不能数字开头</font>");
				flag = true;
			}else{
				o.style.borderColor="gray";
				$("#tipMsg").html("&nbsp;");
				flag = false;
			}
			//o.value = o.value.replace(p1,"");
		}
		
		function goadd(){
			var $name = $('#name');
			var $password = $('#password');
			var $password1 = $('#password1');
			//验证用户名和密码是否为空的标志
			var flag1 = false;
			if($name.val()==""){
				flag1 = true ;
				$name.css('borderColor','red');
				$("#nameTip").css('color','red');
			}else{
				$name.css('borderColor','gray');
				$("#nameTip").css('color','black');
			}
			if($password.val()==""){
				flag1 = true ;
				$password.css('borderColor','red');
				$("#passwordTip").css('color','red');
			}else{
				$password.css('borderColor','gray');
				$("#passwordTip").css('color','black');
			}
			if($password1.val()==""){
				flag1 = true ;
				$password1.css('borderColor','red');
				$("#password1Tip").css('color','red');
			}else{
				$password1.css('borderColor','gray');
				$("#password1Tip").css('color','black');
			}
			if(flag1){
				$("#tipMsg").html("<font color='red'>请确保用户信息填写完整</font>");
				return;
			}
			
			//用户名是否为验证通过
			if(flag){
				return;
			}
			
			if($name.val().length>25){
				$("#tipMsg").html("<font color='red'>用户名称最长为25个字符</font>");
				return;
			}
			
			if($password.val()!=$password1.val()){
				$("#tipMsg").html("<font color='red'>两次输入密码不相同</font>");
				return;
			}
			
			if($password.val().length>20){
				$("#tipMsg").html("<font color='red'>密码长度最大为20</font>");
				return;
			}
			
			goaction();
		}
		
		function docancel(){
			$('#ope').val("backToOrgUser");
			goaction();
		}
		
		function goaction(){
			$('#myform')[0].submit();
		}
	
	</script>


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
    <div class="top" style="display:block;">
      <table id="tb" border="0" cellspacing="0" cellpadding="0" >
        <tr id="tb1" >
          <td colspan="7"><span style="text-align:left; float:left;padding-left:4px; margin-left:4px; color:#141414;"><img src=../images/admin/index/icon.gif width="9" height="10" />
          你当前所在位置为： ${org.name }下<c:if test="${name==null }">增加用户</c:if><c:if test="${name!=null }">修改用户</c:if></span></td>
        </tr>
      </table>
    </div>
    <div class="bottom">
      <div class="con_center">
        <form id="myform" name="myform" method="post" action="userconfig.do">
       		<input type="hidden" id="ope" name="ope" value="${ope }" />
       		<input type="hidden" id="oid" name="oid" value="${oid }" />
       		<input type="hidden" id="uid" name="uid" value="${uid }" />
       		<input type="hidden" id="oldname" name="oldname" value="${name }" />
       		<%-- <input type="hidden" id="oldpassword" name="oldpassword" value="${oldpassword }" />--%>
          <p>
            <label for="fdsafa">用 户 名：</label>
            <input id="name" name="name" type="textfield" value="${name }" onblur="check(this)" class="input_a" /><span id="nameTip" style="float:left; margin-left:5px;font-weight: bold;">*</span>
          </p>
          <p>
            <label for="fdsafa">用户密码：</label>
            <input id="password" name="password" value="${oldpassword }" type="password" class="input_a"/><span id="passwordTip" style="float:left; margin-left:5px;font-weight: bold;">*</span>
          </p>
           <p>
            <label for="fdsafa">确认密码：</label>
            <input id="password1" name="password1" value="${oldpassword }" type="password" class="input_a"/><span id="password1Tip" style="float:left; margin-left:5px;font-weight: bold;">*</span>
          </p>
          <p>
            <label for="fdsafa">所属机构：</label>
            <input name="orgname" id="orgname" type="textfield" style="background-color: #f1f4f8;" disabled="disabled" value="${org.name }" class="input_a"/>
          </p>
          <p style="margin-top:0px;padding-right: 0px;padding-bottom: 12px;padding-left: 0px;">
            <label for="fdsafa">用户角色：</label>
            <select id="rid" name="rid" class="select_a">
            	<option value="3">查看角色</option>
            </select>
          </p>
          <p style="margin-top:5px;	padding-right: 0px;padding-bottom: 1px;padding-left: 0px;"><span id="tipMsg" style="float: none;">${tip }&nbsp;</span></p>
          <p style="margin-top:-8px;">
            <input type="button" value="保存" class="but_a"  onclick="javascript:goadd();"/>
            <input type="button" value="取消" class="but_b" onclick="javascript:docancel();"/>
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
