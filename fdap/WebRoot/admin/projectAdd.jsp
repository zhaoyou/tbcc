<%@ page language="java"  pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>企业工程操作</title>
<link href="../css/admin/index/projectAdd.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="../images/logo.ico" />
 <script type='text/javascript' src='../dwr/interface/realcar.js'></script>
 <script type='text/javascript' src='../dwr/engine.js'></script>
 <script type='text/javascript' src='../dwr/util.js'></script>
<script type="text/javascript" src="../js/jquery-1.4.min.js"></script>
<script type="text/javascript" >
		function saveValue(){
			var name = $('#projectName').val();
			if(name==""){
				$('#projectName').css('borderColor','red');
				$('#projectNameTip').css('color','red');
				$("#tipMsg").html("<font color='red'>工程名不允许为空</font>");
				return ;
			}else{
				$('#projectName').css('borderColor','gray');
				$('#projectNameTip').css('color','black');
			}
			
			if($('#projectName').val().length>25){
			$('#projectName').focus();
			$("#tipMsg").html("<font color='red'>工程名称最长为25个字符</font>");
			return;
			}
			
			var pType = $('#projectType').val();
			var projectNO = $('#projectNO').val();
			if(pType==2){
				if(projectNO==null||projectNO==""){
					$('#projectNO').css('borderColor','red');
					$('#projectNOTip').css('color','red');
					$("#tipMsg").html("<font color='red'>车载工程编号不允许为空</font>");
					return ;
				}
			}
			
			if($('#projectRemark').val().length>15){
				$('#projectRemark').focus();
				$("#tipMsg").html("<font color='red'>工程备注最长为15个字符</font>");
				return;
			}
			
			//验证工程名同名
			if($('#projectName_OK').val()==1){
				return ;
			}
			
			if($('#projectNO_OK').val()==1){
				return ;
			}
			
			$('#myform')[0].submit();
		}
		
		function checkExists(){
			var $projectName = $('#projectName');
			var $projectName_compare = $('#projectName_compare');
			var $oid = $('#oid');
			
			if($projectName.val()=="" || $projectName.val()==$projectName_compare.val()||$oid.val()==""){
				$("#tipMsg").html("");
				$('#projectName_OK').val("0") ;
				$projectName.css('border-color','gray');
				return ;
			}
			
			realcar.getExistsProject($projectName.val(),$oid.val(),{
				callback:function(result){
					if(result==null || result==true){
						$("#tipMsg").html("<font color='red'>该企业(或机构)下工程名 '"+$projectName.val()+"' 已存在</font>");
						$projectName.css('border-color','red');
						$('#projectName_OK').val("1") ;
					}else{
							$("#tipMsg").html("");
							$('#projectName_OK').val("0") ;
							$projectName.css('border-color','gray');
					}
				},
				errorHandler:function(){
					$('#tipMsg').html("<font color='red'>检测工程名失败</font>");
				},
				timeout:10000
			}) ;
		}
		
		
		function checkExistsNO(){
			var $projectNO = $('#projectNO');
			var $projectNO_compare = $('#projectNO_compare');
			
			if($projectNO.val()=="" || $projectNO.val()==$projectNO_compare.val()){
				$("#tipMsg").html("");
				$('#projectNO_OK').val("0") ;
				$projectNO.css('border-color','gray');
				return ;
			}
			
			realcar.getExistsProjectNO($projectNO.val(),{
				callback:function(result){
					if(result==null || result==true){
						$("#tipMsg").html("<font color='red'>工程编号 '"+$projectNO.val()+"' 已存在</font>");
						$projectNO.css('border-color','red');
						$('#projectNO_OK').val("1") ;
					}else{
							$("#tipMsg").html("");
							$('#projectNO_OK').val("0") ;
							$projectNO.css('border-color','gray');
					}
				},
				errorHandler:function(){
					$('#tipMsg').html("<font color='red'>检测工程编号失败</font>");
				},
				timeout:10000
			}) ;
		}
		
		
		function cancelValue(){
			$("#ope").val("toList");
			$("#myform")[0].submit();
		}
		
		function changeEg(){
			var pType = $('#projectType').val();
			if(pType==1){
				$('#eg').html('例:长江仓库');
				$('#trProjectNO').css('display','none');
			}else{
				$('#eg').html('例:沪AB1234');
				$('#trProjectNO').css('display','');
			}
		}
</script>
<style type="text/css">
/* common styling */
/* set up the overall width of the menu div, the font and the margins */
.menu {
font-family: "宋体";
	width:1024px;
	font-size: 12px;
	margin:0 auto;
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
          <img src=../images/admin/index/icon.gif width="9" height="10" /> 你当前所在位置为：  ${org.name }下<c:if test="${editObj==null}">增加</c:if><c:if test="${editObj!=null}">更新</c:if>工程信息</span></td>
        </tr>
      </table>
    </div>
    <div class="bottom">
      <div class="con_center">
        <form name="myform" id="myform" method="post" action="">
        <input type="hidden" name="ope" id="ope" value="${operater }"/>
        <input type="hidden" name="projectId" id="projectId" value="${editObj.projectid }" />
        <input type="hidden" name="oid" id="oid" value="${param.oid }"/>
          <table width="92%" height="162" align="center">
            <tr>
              <td width="20%" height="55" valign="top"><label for="fdsafa3">工程名称：</label></td>
              <td width="30%" align="left" valign="top">
              <table width="100%">
                <tr>
                  <td>
		              <input type="hidden" id="projectName_compare" name="projectName_compare" value="${editObj.name }" />
		              <input type="hidden" id="projectName_OK" name="projectName_OK" value="0" />
		              <input name="projectName" id="projectName" type="text" value="${editObj.name }" onblur="checkExists()" class="input_aa"/><span id="projectNameTip" style="float:left; margin-left:5px;font-weight: bold;">*</span>
	              </td>
	            </tr>
	            <tr>
	               <td>
              		<span id="eg" style="float:left;">${editObj.type==2?'例:沪AB1234':'例:长江仓库'}</span>
              	</td>
              		</tr>
              </table>
              </td>
              <td width="20%" valign="top"><label for="fdsafa7">工程类型：</label></td>
              <td width="30%" valign="top">
              	<c:if test="${editObj==null}">
	                <select name="projectType" id="projectType" onchange="changeEg()" class="select_a">
	                  <option value="1" >仓库工程</option>
	                  <option value="2" >车载工程</option>
	                </select>
                </c:if>
                <c:if test="${editObj!=null}">
	                <c:if test="${editObj.type==1}">
	                	<select name="projectType" id="projectType" class="select_a">
		                  <option value="1" >仓库工程</option>
		                </select>
	                </c:if>
	                <c:if test="${editObj.type==2}">
	                	<select name="projectType" id="projectType" class="select_a">
		                  <option value="2" >车载工程</option>
		                </select>
	                </c:if>
                </c:if>
                
                <span style="float:left; margin-left:5px;"> </span></td>
            </tr>
            <tr id="trProjectNO" style="height: 36px;display: ${editObj.type==2?'':'none' }">
            	<td height="36" valign="top"><label for="fdsafa8">工程编号：</label></td>
            	<td colspan="3" valign="top" >
            			<input type="hidden" id="projectNO_compare" name="projectNO_compare" value="${editObj.projectNO }" />
		              	<input type="hidden" id="projectNO_OK" name="projectNO_OK" value="0" />
            			<input name="projectNO" id="projectNO" value="${editObj.projectNO }" onblur="checkExistsNO();" type="text" class="input_aa"/>
            			<span id="projectNOTip" style="float:left; margin-left:5px;font-weight: bold;">*</span></td>
            </tr>
            <tr>
              <td height="36" valign="top"><label for="fdsafa8">工程备注：</label></td>
              <td colspan="3"><textarea name="projectRemark" id="projectRemark" style="width: 200px;height: 40px;" class="input_aa">${editObj.remark }</textarea>
              </td>
            </tr>
            <tr>
              <td height="130" colspan="4" align="center" valign="middle"><table width="64%" height="79" cellspacing="5" class="oii">
                  <tr>
                    <td colspan="3" ><span id="tipMsg">${tip }</span></td>
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
</div></div>
</body>
</html>
