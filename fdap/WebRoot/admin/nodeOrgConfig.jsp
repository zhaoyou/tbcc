<%@ page language="java"  pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>机构信息配置</title>
<link href="../css/admin/index/orgConfig.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="../images/logo.ico" />
<link rel="stylesheet" href="../css/admin/overlay/appleoverlay.css" type="text/css" />
<link href="../css/admin/dtree.css" rel="stylesheet" type="text/css" />
 <script type='text/javascript' src='../dwr/interface/realcar.js'></script>
 <script type='text/javascript' src='../dwr/engine.js'></script>
 <script type='text/javascript' src='../dwr/util.js'></script>
 
 <script type="text/javascript"  src="../js/admin/dtree.js"></script>
 
 <script type="text/javascript" src="../js/jquery-1.4.min.js"></script>
 <script type="text/javascript" src="../js/jquery-tool.js"></script>
 <script type="text/javascript">
 		$(function(){
				$("#me").overlay({effect: 'apple'});
			}) ;
 
 	//验证电话邮箱参数
 	var flag1 = false ;
 	/*var p1 = /[^\-\d]/g;
	var p2 = /(\.)(\d*)\1/g;
	var p3 = /(\d{11})\d*$/g;
	var p4 = /[^\w]/g;
		
	function check(o) {
		o.value = o.value.replace(p1,"").replace(p3,"$1").replace(p2,"$1$2");
	}*/
 
 	function checkW(str){
 		var w1 = /^([a-zA-Z_][\w]*)$/;
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
		var orgPhone = $("#orgPhone");
		if(orgPhone.val()==""){
			$("#tipMsg").html("&nbsp;");
			orgPhone.css('border-color','gray');
			flag1 = false;
			return ;
		}
		var mobile=/^((13[0-9]{1})|150|151|159|153|187|189){1}\d{8}$/;
		var phone = /^(\d{3,4})(\d{7,8})$/;
		if(!mobile.test(orgPhone.val())&&!phone.test(orgPhone.val())){
			//alert('提示\n\n请输入有效的电话号码！');
			$("#tipMsg").html("<font color='red'>请输入有效的电话号码！</font>");
			orgPhone.css('border-color','red');
			flag1 = true;
		}else{
			$("#tipMsg").html("&nbsp;");
			orgPhone.css('border-color','gray');
			flag1 = false;
		}
	}
 
	function savaValue(){ 
		var flag = false;
		/*$('form input:text').each(function (i,ele){
				if($(ele).val()==""){
					$("#"+this.name+"Tip").show();
					flag = true ;
				}
				else{
					$("#"+this.name+"Tip").hide();
				}
		}) ;*/
		
		//alert($('#showmap').val());
		
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
		
		//验证电话
		checkphone();
		//验证邮箱
		if(!flag1){
			checkmail();
		}
		
		if(flag1){
			return ;
		}
		
		if($('#orgName').val().length>25){
			$('#orgName').focus();
			$("#tipMsg").html("<font color='red'>机构/企业名称最长为25个字符</font>");
			return;
		}
		
		if($('#orgAccount').val().length>30){
			$('#orgAccount').focus();
			$("#tipMsg").html("<font color='red'>机构/企业账户最长为30个字符</font>");
			return;
		}
		if($('#selectOrgtype').val()==2&&$('#nodetype').val()!=2){
			$("#tipMsg").html("<font color='red'>当前上级机构下只能创建企业</font>");
			return;
		}
		if($('#selectOrgtype').val()==1&&$('#nodetype').val()!=1){
			$("#tipMsg").html("<font color='red'>当前上级机构下只能创建机构</font>");
			return;
		}
		//判断是否验证账户同名
		if($('#orgAccount_isok').val()==1||$('#orgName_OK').val()==1){
			return;
		}
		
		//判断当前的操作新增还是更新
		if($('#operater').val()=="add"){
			$('#ope').val('doAddOrg');
		}else{
			$('#ope').val('doEditOrg');
		}
		$('#myform')[0].submit();
	}
	
	function cancelValue(){
		$('#ope').val('toOrgList');
		$('#myform')[0].submit();
	}
	
	function checkExists(){
		var $orgAccount = $('#orgAccount');
		var $orgAccount_compare = $('#orgAccount_compare');
		
		if($orgAccount.val()=="" || $orgAccount.val()==$orgAccount_compare.val()){
			$('#orgAccount_isok').val("0") ;
			$('#existsAccountTip').hide();
			$orgAccount.css('border-color','gray');
			$("#tipMsg").html("&nbsp;");
			return ;
		}
		
		if(checkW($orgAccount.val())){
			//alert("账户只能是以字母开头的字母、数字和下划线组成的字符串");
			$("#tipMsg").html("<font color='red'>账户只能由字母、数字和下划线组成，不能数字开头</font>");
			$('#orgAccount_isok').val('1');
			$orgAccount.css('border-color','red');
			return ;
		}
		
		
		realcar.getExistsAccount($orgAccount.val(),{
			callback:function(result){
				if(result==null || result==true){
					//$('#existsAccountTip').show();
					$('#tipMsg').html("<font color='red'>系统中已存在机构/企业账户'"+$orgAccount.val()+"'</font>");
					$orgAccount.css('border-color','red');
					$('#orgAccount_isok').val("1") ;
				}else{
						$('#orgAccount_isok').val("0") ;
						//$('#existsAccountTip').hide();
						$orgAccount.css('border-color','gray');
						$("#tipMsg").html("&nbsp;");
				}
			},
			errorHandler:function(){
				$('#tipMsg').html("<font color='red'>检测账户名失败</font>");
			},
			timeout:1500
		}) ;	
	}

	function checkOrgExists(){
	
		var $orgName=$('#orgName');
		var $orgName_old = $('#orgName_old');
		var $oid = $('#oid');//实际代表上级机构
		//alert($orgName.val()+" "+$orgName_old.val()+" "+$oid.val());
		if($orgName.val()==""||$oid.val()==""||$orgName.val()==$orgName_old.val()){
			$orgName.css('border-color','gray');
			$('#orgName_OK').val("0");
			$('#tipMsg').html("&nbsp;");
			return;
		}
		realcar.getExistsOrg($orgName.val(),$oid.val(),{
			callback:function(result){
				if(result==null||result==true){
					$('#tipMsg').html("<font color='red'>该机构下已存在企业名称'"+$orgName.val()+"'</font>");
					$orgName.css('border-color','red');
					$('#orgName_OK').val("1");
				}else{
					$orgName.css('border-color','gray');
					$('#orgName_OK').val("0");
					$('#tipMsg').html("&nbsp;");
				}
			},
			errorHandler:function(){
				$('#tipMsg').html("<font color='red'>检测机构(或企业)名失败</font>");
			},
			timeout:10000
		});
	}
	
	function select_parent_org(id,name,type){
		if($('#editId').val()==id){
			alert("不能选择当前机构作为上级机构");
			return;
		}
		$('#selectOrgtype').val(type);
		$('#oid').val(id) ;
		$("#UpOrgName").val(name) ;	
		$('#me').overlay().close();
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
	font-weight: normal;
	padding: 0px;
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
	display: block;
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
	pandding-top: 15px;
	pandding-left: 0px;
	width:150px;
	height:30px;
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
<div style="padding:0; margin:0; width:1024px; margin:0 auto;">
<div id="content">
  <div id="center" >
    <div class="top" style="display:block;">
      <table id="tb" border="0" cellspacing="0" cellpadding="0" >
        <tr id="tb1" >
          <td colspan="7"><span style="text-align:left; float:left;padding-left:4px; margin-left:4px; color:#141414;"><img src=../images/admin/index/icon.gif width="9" height="10" /> 你当前所在位置为： <c:if test="${orgEditObj==null}">增加</c:if><c:if test="${orgEditObj!=null}">更新</c:if>机构或企业配置</span></td>
        </tr>
      </table>
    </div>
    <div class="bottom">
      <div class="con_center">
      <form name="myform" id="myform" method="post" action="orgconfig.do">
        <input type="hidden" name="oid" id="oid" value="${orgObj.oid }" />
        <input type="hidden" name="ope" id="ope" value=""/>
        <input type="hidden" name="operater" id="operater" value="${operater }"/>
        <input type="hidden" name="editId" id="editId" value="${orgEditObj.oid }"/>
        <input type="hidden" id="selectOrgtype" value="" />
          <table width="92%" height="248" align="center">
            <tr>
              <td width="20%" height="55"><label for="fdsafa">机构/企业名称：</label></td>
              <td width="31%">
              <input name="orgName_old" id="orgName_old" type="hidden" value="${orgEditObj.name }" />
              <input name="orgName_OK" id="orgName_OK" type="hidden" value="0" />
              <input name="orgName" id="orgName" type="text" value="${orgEditObj.name }" class="input_aa" onblur="checkOrgExists()"/>
                <span id="orgNameTip" style="float:left; margin-left:5px;font-weight: bold;">* </span></td>
              <td width="19%"><label for="fdsafa6">机构/企业电话：</label></td>
              <td width="30%">
              <input name="orgPhone" id="orgPhone" type="text" onblur="checkphone()" value="${orgEditObj.telephone }" class="input_aa" style="width:115px;"/>
              <span id="orgPhoneTip" style="float:left; margin-left:5px;color: red;display: none"></span></td>
            </tr>
            <tr>
              <td height="108"><label for="fdsafa2">机构/企业账户：</label></td>
              <td>
               <input type="hidden" name="orgAccount_compare" id="orgAccount_compare"  value="${orgEditObj.account }" />
               <input type="hidden" name="orgAccount_isok"  id="orgAccount_isok" value="0"/>
              <input name="orgAccount" id="orgAccount"  type="text"  value="${orgEditObj.account }" class="input_aa" onblur="checkExists()"/>
               <span id="orgAccountTip" style="float:left; margin-left:5px;font-weight: bold;">* </span><span id="existsAccountTip"  style="color: red;display: none">已经重名</span></td>
              <td><label for="fdsafa4">机构/企业邮箱：</label></td>
              <td>
				<input name="orgEmail" id="orgEmail" type="text" onblur="checkmail()" value="${orgEditObj.email }" class="input_aa" style="width:115px;"/>
              	<span id="orgEmailTip" style="float:left; margin-left:5px;color: red;display: none"></span>
			</td>
            </tr>
            <tr>
              <td height="51"><label for="fdsafa5" style="text-align:right;"> 机构类型：</label></td>
              <td>
                <c:if test="${orgEditObj==null&&orgtype==null}">
                	<select name="nodetype" id="nodetype" class="select_a">
	                  <option value="1" >机构</option>
	                  <option value="2" >企业</option>
	                </select>
                </c:if>
                <c:if test="${orgEditObj!=null||orgtype!=null}">
	                <c:if test="${orgEditObj.nodetype==1||orgtype==1}">
	                	<select name="nodetype" id="nodetype" class="select_a">
		                  <option value="1"} >机构</option>
		                </select>
	                </c:if>
	                <c:if test="${orgEditObj.nodetype==2||orgtype==2}">
	                	<select name="nodetype" id="nodetype" class="select_a">
		                  <option value="2" >企业</option>
		                </select>
	                </c:if>
                </c:if>
                
               <span id="orgTypeTip" style="float:left; margin-left:5px;color: red;display: none">* </span></td>
              <td><label for="fdsafa3" style="text-align:right;">上级机构：</label></td>
              <td><input name="upOrgName" id="UpOrgName" type="text" value="${orgObj.name }"  class="input_aa"   readonly="readonly" style="width:115px;background-color:white; border-left: none;border-right: none;border-top: none;" />
              <img src="../images/admin/overlay/s_org.gif" rel="#photo2" id="me" alt="select" style="cursor: pointer;"></img>
                <span id="upOrgNameTip" style="float:left; margin-left:5px;color: red;display: none">* </span></td>
            </tr>
            
            <tr>
              <td colspan="4" align="center" valign="middle"><table width="64%" border="0" cellpadding="0" cellspacing="5" class="oii">
                  <tr>
                    <td height="33" colspan="3" valign="top">
                    <span  id="tipMsg" style="padding-top: 10px;padding-right: 0px;padding-bottom:4px;padding-left: 0px; display:block; ">${tip }&nbsp;</span></td>
                    </tr>
                  <tr>
                    <td><a href="javascript:savaValue();" target="_parent"><img src="../images/admin/index/icon_d.gif" width="86" height="23"  style="cursor:pointer;"/></a></td>
                    <td>&nbsp;</td>
                    <td><a href="javascript:cancelValue();" target="_parent"><img src="../images/admin/index/icon_q.gif" width="86" height="23" /></a></td>
                  </tr>
                </table></td>
            </tr>
          </table>
        </form>
      </div>
    </div>
  </div>
</div>
<div class="apple_overlay" id="photo2" > 
	<div class="details" align="left" style="overflow:auto;height:190px" > 
		<center><h2>请选择上级机构</h2></center><br/> 
		<script type="text/javascript">
			${tree }
			document.write(d) ;
			d.openTo(${orgObj.oid},true) ;
		</script>
 			
 			
	</div> 
</div> 
<div>
  <iframe scrolling="no" src="footer.jsp" width=100% height=26 frameborder=0></iframe >
</div>
</div>
</body>
</html>
