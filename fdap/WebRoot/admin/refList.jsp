<%@ page language="java"  pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>工程冷库列表</title>
<link href="../css/admin/index/refList.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="../images/logo.ico" />
<script type="text/javascript" src="../js/jquery-1.4.min.js"></script>
<script type="text/javascript">

	function goAddRef(){
			if($('#ref_project_type').length){	
				if($('#ref_tr_id').length){
					window.alert("车载工程对应只有一个冷库！ 囧");
					return ;
				}
			}
				$("#ope").val("toAddRef");
				$("#myform")[0].submit();
	
	}
	
	function goEditRef(){
			var $chkRef = $('form input:checkbox') ;
				if(!$chkRef.length){
					window.alert('没有可选的冷库!');
					return  ;
				}
				var flag = false ;
				if(!$chkRef.filter(":checked").length){
					window.alert('请选择一个需要编辑的冷库!');
					return  ;
				}
				
				if($chkRef.filter(":checked").length>1){
					window.alert("一次只允许编辑一个冷库!");
					return ;
				}
				
				$('#ope').val("toEditRef");
				$('#myform')[0].submit();
	}
	
	function goDelRef(){
				var $chkRef = $('form input:checkbox') ;
			if(!$chkRef.length){
				window.alert('没有可选的冷库!');
				return  ;
			}
		
			if(!$chkRef.filter(":checked").length){
				window.alert('请选择需要删除的冷库!');
				return  ;
			}
			
			if($('#ref_project_type').length){
				window.alert("直接删除 ${project.name } 工程即可！");
				return ;
			}
	
			if(window.confirm("所选冷库下的探头都会删除，你确定？")){
				$('#ope').val('toDelRef');
				$('#myform')[0].submit();
			}
	}
	
	function goProjectList(){
		$('#myform').attr("action","projectconfig.do");
		$('#ope').val("toList");
		$("#myform")[0].submit();
	}
	
	function goAiList(refid){
		$("#refId_to_ai").val(refid);
		$("#myform").attr("action","aiconfig.do");
		$("#ope").val("toAiList");
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
<form name="myform" id="myform" action="refconfig.do" method="post">
<input type="hidden" name="ope" id="ope" value=""/>
<input type="hidden" name="projectId_to_ref" id="projectId_to_ref" value="${param.projectId_to_ref }"/>
<input type="hidden" name="oid" id="oid" value="${param.oid }"/>
<input type="hidden" name="refId_to_ai" id="refId_to_ai" value=""/>

<input type="hidden" name="orgname_to_project" id="orgname_to_project" value="${param.orgname_to_project}"/>
<input type="hidden" name="projectname_to_ref" id="projectname_to_ref" value="${project.name }"/>
<div id="content">
  <div id="center" >
    <div class="top" style="display:block;">
      <table id="tb" border="0" cellspacing="0" cellpadding="0" >
        <tr id="tb1" >
          <td colspan="7"><span style="text-align:left; float:left;padding-left:4px; margin-left:4px; color:#141414;"><img src=../images/admin/index/icon.gif width="9" height="10" /> 
          你当前所在位置为：${param.orgname_to_project}下工程配置 => ${project.name }下的冷库列表</span> <a href="javascript:goProjectList();" target="_parent"><img src="../images/admin/index/back.gif" width="58" height="21" class="pho"/></a></td>
        </tr>
      </table>
    </div>
    <div class="bottom">
      <table id="tb" border="0" cellspacing="0" cellpadding="0">
        <tr class="altrow" >
          <td  >选择 </td>
          <td>冷库名称</td>
          <td>储存类型</td>
          <td style="display: ${project.type==2?'none':''} ">楼层编号</td>
          <td style="display: ${project.type==2?'none':''} ">地面/地下</td>
          <td>是否激活</td>
          <td>冷库备注</td>
          <td>操作</td>
        </tr>
        <tbody>
        	<c:forEach var="r" items="${refList}" varStatus="irow">
        		<tr style="height:25px;" ${irow.index%2==0?"":"bgcolor='#f1f4f8'" } id="ref_tr_id">
			          <td><input name="chkRef" type="checkbox" id="chkRef" value="${r.refId }" /></td>
			          <td title="${r.refId }">${r.name }</td>
			          <td>${r.fdapstoretype.name }</td>
			          <td style="display: ${project.type==2?'none':''} "> ${r.floorNo }</td>
			          <td style="display: ${project.type==2?'none':''} ">${r.floorType==0?"地下":"地面" }</td>
			          <td align="left">${r.isactive==0?"激活":"未激活" }</td>
			          <td>${r.remark }</td>
			          <td><a href="javascript:goAiList(${r.refId });">配置探头</a></td>
    		    </tr>
        	</c:forEach> 		
        </tbody>     
        <tr>
          <td colspan="8">
           <br />
          	 <c:if test="${project.type==2}">  
          		<input type="hidden" name="ref_project_type" id="ref_project_type" value="${project.type }"/>      	
          		<%--<font color="red">注意: </font>请为${project.name } 工程配置唯一冷库(不允许删除喔).--%>
          	</c:if>    
            <br />
            <br/>
            <table width="64%" class="oii">
              <tr>         
                <td><a href="javascript:goAddRef();" target="_parent"><img src="../images/admin/index/icon_f.gif" width="86" height="23"  style="cursor:pointer;"/></a></td>
                <td><a href="javascript:goEditRef();"><img src="../images/admin/index/icon_a.gif" width="86" height="23" /></a></td>
                <td><a href="javascript:goDelRef();"><img src="../images/admin/index/icon_b.gif" width="86" height="23" /></a></td>
              </tr>
            </table>
            <br />
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
</div></form>
<div>
  <iframe scrolling="no" src="footer.jsp" width=100% height=26 frameborder=0></iframe >
</div>
</div>
</body>
</html>
