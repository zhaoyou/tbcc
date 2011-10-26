<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>工程冷库操作</title>
<link href="../css/admin/index/refAdd.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="../images/logo.ico" />

 <script type='text/javascript' src='../dwr/interface/realcar.js'></script>
 <script type='text/javascript' src='../dwr/engine.js'></script>
 <script type='text/javascript' src='../dwr/util.js'></script>
<script type="text/javascript" src="../js/jquery-1.4.min.js"></script>

<script type="text/javascript">

	//var p1 = /[^\d]/g;
	//楼层编号是否验证通过
	var flag = false;
	function check(o){
		//o.value = o.value.replace(p1,"");
		if(o.value=="")return;
			var p = /^[0-9]*[1-9][0-9]*$/;
			if(!p.test(o.value)){
				o.style.borderColor="red";
				flag = true;
				$('#tipMsg').html( "<font color='red'>楼层编号必须为正整数!</font>" ) ;
			}
			else{
				flag = false;
				o.style.borderColor="gray";
				$('#tipMsg').html("") ;
			}
	}
	
	function saveValue(){
		var $refName = $("#refName");
		var $storeTypeId = $("#storeTypeId");
		var $floorNo = $("#floorNo");
		
		if($storeTypeId.val()=="" ){
			$("#tipMsg").html("<font color='red'>请确认是否配置了仓储类型！</font>");
			return;
		}
		//验证冷库名称和楼层编号是否输入为空的标志
		var flag1 = false;
		if($refName.val()=="" ){
			flag1 = true;
			$refName.css('borderColor','red');
			$('#refNameTip').css('color','red');
			
		}else{
			$refName.css('borderColor','gray');
			$('#refNameTip').css('color','black');
		}
		
		if($('#projectType').val()==1&&$floorNo.val()==""){
			flag1 = true;
			$floorNo.css('borderColor','red');
			$('#floorNoTip').css('color','red');
		}else{
			$floorNo.css('borderColor','gray');
			$('#floorNoTip').css('color','black');
		}
		
		if(flag1){
			$("#tipMsg").html("<font color='red'>请确保信息完整!</font>");
			return;
		}
		
		if($refName.val().length>25){
			$('#refName').focus();
			$("#tipMsg").html("<font color='red'>冷库名称最长为25个字符</font>");
			return;
		}
		
		if($floorNo.val().length>5){
			$('#floorNo').focus();
			$("#tipMsg").html("<font color='red'>楼层编号最长为5位数字</font>");
			return;
		}
		
		if(flag){
			$('#floorNo').css('borderColor','red');
			$('#tipMsg').html( "<font color='red'>楼层编号必须为正整数!</font>" ) ;
			return;
		}
		
		//判断是否验证账户同名
		if($('#refName_OK').val()==1){
			return ;
		}
		
		$("#storeTypeName").val($("#storeTypeId")[0].options[$("#storeTypeId")[0].selectedIndex].text) ;
		
		$("#myform")[0].submit();
	}
	
	function checkExists(){
			var $refName = $('#refName');
			var $refName_compare = $('#refName_compare');
			var $projectId = $('#projectId_to_ref');
			
			if($refName.val()=="" || $refName.val()==$refName_compare.val()||$projectId.val()==""){
				$("#tipMsg").html("");
				$('#refName_OK').val("0") ;
				$refName.css('border-color','gray');
				return ;
			}
			
			realcar.getExistsRef($refName.val(),$projectId.val(),{
				callback:function(result){
					if(result==null || result==true){
						$("#tipMsg").html("<font color='red'>该工程下冷库名 '"+$refName.val()+"' 已存在</font>");
						$refName.css('border-color','red');
						$('#refName_OK').val("1") ;
					}else{
							$("#tipMsg").html("");
							$('#refName_OK').val("0") ;
							$refName.css('border-color','gray');
					}
				},
				errorHandler:function(){
					$('#tipMsg').html("<font color='red'>检测冷库名失败</font>");
				},
				timeout:10000
			}) ;
		}
	
	function cancelValue(){
			$("#ope").val("toList");
			$("#myform")[0].submit();
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
    <li style="float:right; height:28px; line-height:28px; margin-right:14px;color: #3a434a;"> 
    	<span id="time"></span>
    </li>
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
            <td colspan="7"><span style="text-align:left; float:left;padding-left:4px; margin-left:4px; color:#141414;">
            <img src=../images/admin/index/icon.gif width="9" height="10" /> 你当前所在位置为： ${param.orgname_to_project}下工程配置 => ${project.name }下<c:if test="${editObj==null}">增加</c:if><c:if test="${editObj!=null}">更新</c:if>冷库信息</span></td>
          </tr>
        </table>
      </div>
      <div class="bottom">
        <div class="con_center">
          <form name="myform" id="myform" method="post" action="refconfig.do">
          <input type="hidden" name="ope" id="ope" value="${operater }" />
           <input type="hidden" name="oid" id="oid" value="${param.oid }" />
           <input type="hidden" name="projectId_to_ref" id="projectId_to_ref" value="${param.projectId_to_ref }" />
           <input type="hidden" name="refId" id="refId" value="${editObj.refId }" />
           
           <input type="hidden" name="orgname_to_project" id="orgname_to_project" value="${param.orgname_to_project}"/>
			<input type="hidden" name="projectname_to_ref" id="projectname_to_ref" value="${project.name }"/>
           
           <input type="hidden" name="projectType" id="projectType" value="${project.type }" />
           
           <input type="hidden" name="storeid_compare" id="storeid_compare" value="${editObj.fdapstoretype.storeid }" />
           
            <table width="81%" height="240" align="center">
              <tr>
                <td width="20%" height="55"><label for="fdsafa">冷库名称：</label></td>
                <td width="32%">
                 <input type="hidden" id="refName_compare" name="refName_compare" value="${editObj.name }" />
              	 <input type="hidden" id="refName_OK" name="refName_OK" value="0" />
              	<input name="refName" id="refName" type="text" ${project.type==2?"readonly='readonly' style='background-color: #f1f4f8;'":"" }  value="${project.type==2?project.name:editObj.name }" onblur="checkExists()" class="input_aa"/>
                  <span id="refNameTip" style="float:left; margin-left:5px;font-weight: bold;">* </span></td>
                <td width="17%"><label for="fdsafa6" style="width:70px;">仓储类型：</label></td>
                <td width="31%">
                <input type="hidden" name="storeTypeName" id="storeTypeName" />
                <select name="storeTypeId" id="storeTypeId" class="select_a" style="width:91px;">
                	<c:forEach var="store" items="${storeTypeList}">
                			 <option value="${store.storeid }"  ${ store.storeid==editObj.fdapstoretype.storeid?"selected='selected'":"" }>${store.name }</option>
                	</c:forEach>             
                  </select>
                  <span style="float:left; margin-left:5px;color:#F00;"> </span></td>
              </tr>
              <tr style="display: ${project.type==2?'none':'line' };">
                <td height="108"><label for="fdsafa2">楼层类型：</label></td>
                <td>
                <select name="floorTypeId" id="floorTypeId" class="select_a">
                    <option value="0" ${editObj.floorType==0?"selected='selected'":"" }>地下</option>
                    <option value="1" ${editObj.floorType==1?"selected='selected'":"" }>地面</option>
                  </select>
                  <span style="float:left; margin-left:5px;color:#F00;"> </span></td>
                <td><label for="fdsafa4" style="width:70px;">楼层编号：</label></td>
                <td><input name="floorNo" id="floorNo" type="text" onblur="check(this)" value="1" class="input_aa" style="width:85px;"/>
                  <span id="floorNoTip" style="float:left; margin-left:5px;font-weight: bold;">* </span></td>
              </tr>
              <tr>
                <td><label for="fdsafa5">是否激活：</label></td>
                <td><input name="isactive"  type="checkbox" id="isactive" style="float:left;" value="0" ${editObj.isactive==0?"checked='checked'":"" }  />
                  <span style="float:left; line-height:18px; height:18px;margin-left:10px; display:block;">激活</span></td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
              </tr>
              <tr>
              	<td valign="top"><label for="fdsafa5">冷库备注：</label></td>
                <td colspan="3" valign="top"><textarea name="refRemark" id="refRemark" style="width: 300px;height: 40px;" class="input_aa">${editObj.remark }</textarea>
                  <span style="float:left; margin-left:5px;color:#F00;"> </span></td>
              </tr>
              <tr>
                <td colspan="4" align="center"><table width="64%" class="oii">
                    <tr>
                      <td colspan="3"><span id="tipMsg"></span></td>
                    </tr>
                    <tr>
                      <td colspan="3" style="height:8px;"></td>
                    </tr>
                    <tr>
                      <td><a href="javascript:saveValue();" target="_parent"><img src="../images/admin/index/icon_d.gif" width="86" height="23"  style="cursor:pointer;"/></a></td>
                      <td>&nbsp;</td>
                      <td><a href="javascript:cancelValue();"><img src="../images/admin/index/icon_q.gif" width="86" height="23" /></a></td>
                    </tr>
                  </table></td>
              </tr>
            </table>
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
