<%@ page language="java"  pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>企业工程列表</title>
<link href="../css/admin/index/projectList.css" rel="stylesheet" type="text/css" />
<link href="../css/admin/dtree.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"  src="../js/admin/dtree.js"></script>
<link rel="Shortcut Icon" href="../images/logo.ico" />
<script type="text/javascript" src="../js/jquery-1.4.min.js"></script>
<script type="text/javascript">

		function goProjectList(oid){
			$('#tb').css('display','block');
			$('#oid').val(oid);
			$('#ope').val("toList");
			$('#myform')[0].submit();
		}
		
		function goAddPro(){
			var $oid = $('#oid').val();
			if($oid==null || $oid=="1"){
				window.alert("请从左边选择一个企业后再添加工程!");
				return ;
			}	
			$("#ope").val("toAddProject");
			$("#myform")[0].submit();
			
		}
		
		function goEditPro(){
				var $chkproject = $('form input:checkbox') ;
				if(!$chkproject.length){
					window.alert('没有可选的工程');
					return  ;
				}
				var flag = false ;
				if(!$chkproject.filter(":checked").length){
					window.alert('请选择一个需要编辑的工程');
					return  ;
				}
				
				if($chkproject.filter(":checked").length>1){
					window.alert("一次只允许编辑一个工程");
					return ;
				}
				
				$('#ope').val("toEditProject");
				$('#myform')[0].submit();
		}
		
		function goDelPro(){
			var $chkproject = $('form input:checkbox') ;
			if(!$chkproject.length){
				window.alert('没有可选的工程');
				return  ;
			}
		
			if(!$chkproject.filter(":checked").length){
				window.alert('请选择需要删除的工程');
				return  ;
			}
	
			if(window.confirm("所选工程下的冷库、探头、都会删除，你确定？")){
				$('#ope').val('toDelProject');
				$('#myform')[0].submit();
			}
		}
		
		function goRefList(pid){
				$("#projectId_to_ref").val(pid);
				$("#myform").attr("action","refconfig.do");
				$("#ope").val("toList");
				$("#myform")[0].submit();
		}	
		
		function clickJG(oid){
			d.openTo(oid);
			$('#tb').css('display','none');
			$('#wz').html("你当前所在位置为： 工程配置(只有公司企业才有工程)");
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
</head>
<body>
<div style="margin:0 auto; width:1024px; height:auto;">
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
        <li style="float:right; height:28px; line-height:28px; margin-right:14px;color: #3a434a;"><span id="time"></span> </li>
      </ul>
      <!-- clear the floats if required -->
      <div class="clear"> </div>
    </div>
    <form name="myform" id="myform" method="post" action="projectconfig.do">
    <input type="hidden" name="ope" id="ope" value="" />
    <input type="hidden" name="oid" id="oid" value="${orgObj==null?"1":orgObj.oid }"/>
    <input type="hidden" name="orgname_to_project" id="orgname_to_project" value="${orgObj.name}"/>
    <input type="hidden" name="projectId_to_ref" id="projectId_to_ref" value=""/>
      <div id="left">
        <h1><img src="../images/admin/index/h1.gif" width="191" height="27" /></h1>
        <div class="dtree" id="divDrag" style="text-align:left; overflow:scroll; width:190px; height: 453px;">
          <label style="color:#FFF; "></label>
          <script type="text/javascript">		
					${tree}
					window.document.write(d);
					d.openTo(${orgObj==null?"1":orgObj.oid});
	</script>
        </div>
      </div>
      <div id="right" >
        <div id='con_right'>
          <div><img src="../images/admin/index/icon.gif" width="9" height="10" class="img_a"/><span id="wz">你当前所在位置为： ${orgObj==null?"企业":orgObj.name }下工程配置</span><br />
          </div>
          <table border="0" cellpadding="0" cellspacing="0" id="tb" style="width:780px;${orgObj!=null&&orgObj.nodetype==2?'display: block;':'display: none;' }">
            <tr class="altrow">
            	<td colspan="5">仓库</td>
            </tr>
            <tr class="altrow" >
              <td width="137">选择</td>
              <td width="173">工程名称</td>
              <td width="146">工程类型</td>
              <td width="160">工程备注</td>
              <td width="158">操作</td>
            </tr>
            <tbody>
            	<c:forEach var="p" items="${projectList}" varStatus="irow">
            			<tr style="height:25px;display: ${p.type==1?'':'none' }" ${irow.index%2==0?"":"bgcolor='#f1f4f8'" }>
				              <td><input type="checkbox" name="chkproject" id="chkproject" value="${p.projectid }"/></td>
				              <td>${p.name }</td>
				              <td>${p.type==1?"仓库工程":"车载工程" }</td>
				              <td>${p.remark }</td>
				              <td><a href="javascript:goRefList(${p.projectid });">配置仓库</a></td>
           				 </tr>
            	</c:forEach>            
            </tbody>
            <tr>
            	<td colspan="5">&nbsp;</td>
            </tr>
            <tr>
            	<td colspan="5">
            		<table border="0" cellpadding="0" cellspacing="0" width="100%">
            			<tr class="altrow">
			            	<td colspan="6">车载</td>
			            </tr>
            			<tr class="altrow" >
			              <td width="137">选择</td>
			              <td width="173">工程名称</td>
			              <td width="116">工程类型</td>
			              <td width="160">工程备注</td>
			              <td width="80">工程编号</td>
			              <td width="108">操作</td>
			            </tr>
			            <tbody>
			            	<c:forEach var="p1" items="${projectList}" varStatus="irow1">
			            			<tr style="height:25px;display: ${p1.type==2?'':'none' };" ${irow1.index%2==0?"":"bgcolor='#f1f4f8'" }>
							              <td><input type="checkbox" name="chkproject" id="chkproject" value="${p1.projectid }"/></td>
							              <td>${p1.name }</td>
							              <td>${p1.type==1?"仓库工程":"车载工程" }</td>
							              <td>${p1.remark }</td>
							              <td>${p1.projectNO }</td>
							              <td><a href="javascript:goRefList(${p1.projectid });">配置车载</a></td>
			           				 </tr>
			            	</c:forEach>            
			            </tbody>
            		</table>
            	</td>
            </tr>
            
            <tr>
              <td colspan="5"><br />
                <table width="64%" class="oii">
                  <tr>
                    <td><a href="javascript:goAddPro();" target="_parent"><img src="../images/admin/index/icon_f.gif" width="86" height="23"  style="cursor:pointer;"/></a></td>
                    <td><a href="javascript:goEditPro();"><img src="../images/admin/index/icon_a.gif" width="86" height="23" /></a></td>
                    <td><a href="javascript:goDelPro();"><img src="../images/admin/index/icon_b.gif" width="86" height="23" /></a></td>
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
