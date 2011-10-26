<%@ page language="java"  pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>冷库探头列表</title>
<link href="../css/admin/index/aiList.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="../images/logo.ico" />

<script type="text/javascript">
	
		function goaction(url,ope){
			document.getElementById("myform").action=url;
			document.getElementById("ope").value=ope;
			document.myform.submit();
		}
		
		function goAdd(url,ope){
			var proType = document.getElementById("proType").value;
			if(proType==2){
				var size = document.getElementById("aiListSize").value;
				if(size>=3){
					document.getElementById("tip").innerHTML="<font color='red'>车载最多三个温度探头，不能再添加</font>";
					return;
				}
			}
			goaction(url,ope);
		}
		
		function godelete(url,ope){
			var chkAi = document.getElementsByName("chkAi");
			if(chkAi.length>0){
				var idStr="";
				for(var i=0;i<chkAi.length;i++){
					if(chkAi[i].checked){
						idStr+=","+chkAi[i].value;
					}
				}
				if(idStr!=""){
					if(confirm("确定要删除该探头？")){
						var delStr = idStr.substring(1,idStr.length);
						document.getElementById("delStr").value=delStr;
						//alert(delStr);
						goaction(url,ope);
					}
				}
				else{
					alert("请选择需要删除的探头");
					return;
				}
			}
			else{
				alert("没有可删除的探头");
				return;
			}
		}
		
		function goupdate(url,ope){
			var chkAi = document.getElementsByName("chkAi");
			if(chkAi.length>0){
				var count=0;
				var upId;
				for(var i=0;i<chkAi.length;i++){
					if(chkAi[i].checked){
						count++;
						upId=chkAi[i].value;
					}
				}
				if(count==0){
					alert("请选择需要修改的探头");
					return;
				}
				if(count>1){
					alert("只可以选择一个探头进行修改");
					return;
				}
				if(count==1){
					document.getElementById("aiguid").value = upId;
					//alert(upId);
					goaction(url,ope);
				}
			}
			else{
				alert("没有可修改的探头");
				return;
			}
		}
	
	function goRefList(url,ope){
		goaction(url,ope);
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

<form id="myform" name="myform" action="" method="post">
	<input type="hidden" name="ope" id="ope" value="" />
	<input type="hidden" id="oid" name="oid" value="${param.oid }" />
	<input type="hidden" name="projectId_to_ref" id="projectId_to_ref" value="${param.projectId_to_ref }"/>
	<input type="hidden" name="refId_to_ai" id="refId_to_ai" value="${param.refId_to_ai }"/>
	<input type="hidden" name="delStr" id="delStr" value="" />
	<input type="hidden" name="aiguid" id="aiguid" value="" />
	
	<input type="hidden" name="aiListSize" id="aiListSize" value="${fn:length(aiList)}" />
	<input type="hidden" name="proType" id="proType" value="${ref.fdapproject.type }" />
	
	<input type="hidden" name="orgname_to_project" id="orgname_to_project" value="${param.orgname_to_project}"/>
	<input type="hidden" name="projectname_to_ref" id="projectname_to_ref" value="${param.projectname_to_ref }"/>
</form>

<div style="padding:0; margin:0; width:1024px; margin:0 auto;">
<div id="content">
  <div id="center" >
    <div class="top" style="display:block;">
      <table id="tb" border="0" cellspacing="0" cellpadding="0" >
        <tr id="tb1" >
          <td colspan="7"><span style="text-align:left; float:left;padding-left:4px; margin-left:4px; color:#141414;"><img src=../images/admin/index/icon.gif width="9" height="10" /> 
          你当前所在位置为：${param.orgname_to_project}下工程配置 => ${param.projectname_to_ref }下的冷库列表 => ${ref.name }下的探头列表</span> <a href="javascript:goRefList('refconfig.do','toList');" target="_parent"><img src="../images/admin/index/back.gif" width="58" height="21" class="pho"/></a></td>
        </tr>
      </table>
    </div>
    <div class="bottom">
      <table id="tb" border="0" cellspacing="0" cellpadding="0">
        <tr class="altrow" >
          <td width="90">选择</td>
          <td width="110">探头名称</td>
          <td width="116">探头类型</td>
          <td width="90">下限</td>
          <td width="94">下限延时(min)</td>
          <%-- <td width="84">下下限</td>
          <td width="99">下下限延时</td>--%>
          <td width="100">上限</td>
          <td width="100">上限延时(min)</td>
          <%--<td width="104">上上限</td>
          <td width="110">上上限延时</td>--%>
        </tr>
        <tbody>
        	<logic:notEmpty name="aiList">
	        	<c:forEach var="ai" items="${aiList}" varStatus="irow">
	        		<tr style="height:25px;" ${irow.index%2==0?"":"bgcolor='#f1f4f8'"}>
	        		  <td><input name="chkAi" type="checkbox" id="chkAi" value="${ai.aiguid }" /></td>
			          <td><span title="${ai.reid }">${ai.name }</span></td>
			          <td><c:if test="${ai.selftype==0}">温度</c:if><c:if test="${ai.selftype==1}">湿度</c:if></td>
			          <td>${ai.lowerlimit }</td>
			          <td>${ai.lowerdelay }</td>
			          <%--<td>${ai.minlowerlimit }</td>
			          <td>${ai.minlowerdelay }</td>--%>
			          <td>${ai.highlimit }</td>
			          <td>${ai.highdelay }</td>
			          <%--<td>${ai.maxhighlimit }</td>
			          <td>${ai.maxhighdelay }</td>--%>
			        </tr>
	        	</c:forEach>
        	</logic:notEmpty>
        </tbody>
        	
        <tr>
          <td colspan="11"><div id="tip">${tip }<br /></div>
            
            <table width="64%" class="oii">
              <tr>
                <td><a href="javascript:goAdd('aiconfig.do','toAiAdd');" target="_parent"><img src="../images/admin/index/icon_f.gif" width="86" height="23"  style="cursor:pointer;"/></a></td>
                <td><a href="javascript:goupdate('aiconfig.do','toAiUp');"><img src="../images/admin/index/icon_a.gif" width="86" height="23" style="cursor:pointer;" /></a></td>
                <td><a href="javascript:godelete('aiconfig.do','doAiDel');"><img src="../images/admin/index/icon_b.gif" width="86" height="23" style="cursor:pointer;" /></a></td>
              </tr>
            </table>
            
            <br /></td>
        </tr>
      </table>
      <br />
      <br />
      <br />
      <br />
      <br />
    </div>
  </div>
</div>
<div>
  <iframe scrolling="no" src="footer.jsp" width=100% height=26 frameborder=0></iframe >
</div>
</div>
</body>
</html>
