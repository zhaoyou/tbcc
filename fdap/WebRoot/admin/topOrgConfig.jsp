<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>顶层机构信息配置</title>
<link href="../css/admin/index/topOrgConfig.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="../images/logo.ico" />
 <script type='text/javascript' src='../dwr/interface/realcar.js'></script>
 <script type='text/javascript' src='../dwr/engine.js'></script>
 <script type='text/javascript' src='../dwr/util.js'></script>
 <script type="text/javascript" src="../js/jquery-1.4.min.js"></script>
<script type="text/javascript">
	var flag1 = false;
	
	/*var p1 = /[^\-\d]/g;
	var p2 = /(\.)(\d*)\1/g;
	var p3 = /(\d{11})\d*$/g;
	var p4 = /[^\w]/g;
		
	function check(o) {
		o.value = o.value.replace(p1,"").replace(p3,"$1").replace(p2,"$1$2");
	}*/
	
	function checkW(str){
		var w1 = /^([a-zA-Z_][\w]*)$/;
		//alert(!w1.test(str));
		//o.value = o.value.replace(p4,"");
		return !w1.test(str);
	}
	
	function checkmail()
	{
		var temp = $("#orgEmail");
		if(temp.val()==""){	
			$("#tipMsg").html("&nbsp;");
			temp.css('border-color','gray');
			flag1 = false;
			return;	
		}
		//对电子邮件的验证
		var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		if(!myreg.test(temp.val())||temp.val().length>30)
		{
			//alert('提示\n\n请输入有效的E_mail！');
			$("#tipMsg").html("<font color='red'>请输入有效的E_mail！</font>");
			temp.css('border-color','red');
			flag1 = true;
		}else{
			$("#tipMsg").html("&nbsp;");
			temp.css('border-color','gray');
			flag1 = false;
		}
	}
	
	function checkphone(){
		var $orgPhone = $("#orgPhone");
		if($orgPhone.val()==""){
			$("#tipMsg").html("&nbsp;");
			$orgPhone.css('border-color','gray');
			flag1 = false;
			return ;
		}
		var mobile=/^((13[0-9]{1})|150|151|159|153|187|189){1}\d{8}$/;
		var phone = /^(\d{3,4})(\d{7,8})$/;
		if(!mobile.test($orgPhone.val())&&!phone.test($orgPhone.val())){
			//alert('提示\n\n请输入有效的电话号码！');
			$("#tipMsg").html("<font color='red'>请输入有效的电话号码！</font>");
			$orgPhone.css('border-color','red');
			flag1 = true;
		}else{
			$("#tipMsg").html("&nbsp;");
			$orgPhone.css('border-color','gray');
			flag1 = false;
		}
	}
	
	function cancelValue(){
		$('#myform').attr('action','orgconfig.do?ope=goAdmin') ;
		$('#myform')[0].submit();
	}
	
	function saveValue(){
		var flag = false;
		/*$('form input:text').each(function(i,ele){
			if($(this).val()==""){
				$("#"+this.name+"Tip").show();
			    flag = true ;
			}
			else{
				$("#"+this.name+"Tip").hide();
			}
		}) ;*/
		
		if($('#orgName').val()==""){
			$('#orgName').css('borderColor','red');
			$("#orgNameTip").css('color','red');
			flag = true ;
		}else{
			$('#orgName').css('borderColor','gray');
			$("#orgNameTip").css('color','black');
		}
		
		if($('#orgAccount').val()==""){
			$('#orgAccount').css('borderColor','red');
			$("#orgAccountTip").css('color','red');
			flag = true ;
		}else{
			$('#orgAccount').css('borderColor','gray');
			$("#orgAccountTip").css('color','black');
		}
		
		if(flag){
			$("#tipMsg").html("<font color='red'>请确保信息完整</font>");
			return ;
		}
		
		//验证电话号码
		checkphone();
		
		//验证邮箱
		if(!flag1){
			checkmail();
		}
		
		//参数是否合法
		if(flag1){
			return ;
		}
		
		if($('#orgName').val().length>25){
			$('#orgName').focus();
			$("#tipMsg").html("<font color='red'>顶级机构名称最长为25个字符</font>");
			return;
		}
		
		if($('#orgAccount').val().length>30){
			$('#orgAccount').focus();
			$("#tipMsg").html("<font color='red'>顶级机构账户最长为30个字符</font>");
			return;
		}
		
		//账户是否验证
		if(	$('#orgAccount_isok').val()=="1"){
				return  ;
		}
		
		$('#myform').get(0).submit();
	}
	
	function checkExists(){
		var $orgAccount = $('#orgAccount');
		var $orgAccount_compare = $('#orgAccount_compare');
		
		if($orgAccount.val()=="" || $orgAccount_compare.val()==$orgAccount.val()){
			$('#existsAccountTip').hide();
			$orgAccount.css('border-color','gray');
			$('#orgAccount_isok').val('0');
			$("#tipMsg").html("&nbsp;");
			return ;
		}
		
		if(checkW($orgAccount.val())){
			//alert("账户只能是以字母开头的字母、数字和下划线组成的字符串");
			$('#orgAccount_isok').val('1');
			$orgAccount.css('border-color','red');
			$("#tipMsg").html("<font color='red'>账户只能由字母、数字和下划线组成，不能数字开头</font>");
			return ;
		}
		/*else{
			$('#orgAccount_isok').val('0');
			$orgAccount.css('border-color','gray');
			//$("#tipMsg").html("&nbsp;");
		}*/
		
		realcar.getExistsAccount($orgAccount.val(),{
			callback:function(result){
				if(result==null || result==true){
					$('#existsAccountTip').show();
					$orgAccount.css('border-color','red');
					$('#orgAccount_isok').val('1');
				}else{
						$('#existsAccountTip').hide();
						$orgAccount.css('border-color','gray');
						$('#orgAccount_isok').val('0');
						$("#tipMsg").html("&nbsp;");
				}
			},
			errorHandler:function(){
				$("#tipMsg").html("<font color='red'>无法检测账户是否同名</font>");
			},
			timeout:10000
		}) ;	
	}
</script>
<style type="text/css">
/* common styling */
/* set up the overall width of the menu div, the font and the margins */
.menu {
	font-family: "宋体";
	width:1024px;
	font-size: 12px;
	margin-top: 0px;
	margin-right: auto;
	margin-bottom: 0px;
	margin-left: auto;
	background-image: url(../images/admin/head/bg_head03.jpg);
	background-repeat: repeat-x;
	height: 30px;
	padding: 0px;
	font-weight: normal;
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
    <div class="clear"> </div>
  </div>
  <div id="content">
    <div id="center" >
      <div class="top" style="display: block;">
        <table id="tb" border="0" cellspacing="0" cellpadding="0" >
          <tr id="tb1" >
            <td colspan="7"><span style="text-align:left; float:left;padding-left:4px; margin-left:4px; color:#141414;"><img src=../images/admin/index/icon.gif width="9" height="10" /> 你当前所在位置为： 顶级机构信息</span></td>
          </tr>
        </table>
      </div>
      <div class="bottom">
        <div class="con_center">
         <form name="myform" id="myform" method="post" action="orgconfig.do?ope=doTopOrg">
          <input type="hidden" id="oid" name="oid" value="${topOrg.oid }"/>
        	
            <p>
              <label for="fdsafa">顶级机构名称：</label>
              <input name="orgName" id="orgName" value="${topOrg.name }" class="input_a"/>
              <span id="orgNameTip" style="color: black;font-weight: bold;">*</span> <span class="text"></span>
            </p>
            <p>
              <label for="fdsafa">顶级机构账户：</label>
              <input name="orgAccount" id="orgAccount"  value="${topOrg.account }" class="input_a" onblur="checkExists();"/>
              <span id="orgAccountTip" style="color: black;font-weight: bold;">*</span> 
              <input type="hidden" name="orgAccount_compare" id="orgAccount_compare"  value="${topOrg.account }" />
              <input type="hidden" name="orgAccount_isok"  id="orgAccount_isok" value="0"/>
               <span id="existsAccountTip" class="text" style="color: red;display: none">已经重名</span>
            <p>
              <label for="fdsafa">顶级机构电话：</label>
               <input name="orgPhone" id="orgPhone" onblur="checkphone()" value="${topOrg.telephone }" class="input_a"/>
              <span id="orgPhoneTip"  style="color: red;display: none">*</span> <span class="text"></span>
            <p>
              <label for="fdsafa">顶级机构邮箱：</label>
              <input name="orgEmail" id="orgEmail" onblur="checkmail()" value="${topOrg.email }" class="input_a"/>
             <span id="orgEmailTip" style="color: red;display: none">*</span> <span class="text"></span>
            <p>
              <label for="fdsafa">顶级机构类型：</label>
              <input name="orgType" id="orgType" style="text-align: center;background-color: #f1f4f8;" disabled="disabled"  value="顶级机构" class="input_a"/>
              <span style="color: red;"></span>  <span class="text"></span></p>
            <p id="tipMsg" style="padding-top: 5px;padding-right: 0px;padding-bottom:0px;padding-left: 0px; display:block; ">${tip }&nbsp;</p>
            <p style="padding-top: 8px;padding-right: 0px;padding-bottom:2px;padding-left: 0px; margin:0 auto; text-align:center;">
              <input name="input2" type="button" value="保存" onclick="javascript:saveValue();"  class="but_a" />
              <input name="input" type="button" value="取消" onclick="javascript:cancelValue() ;"  class="but_b" />
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
