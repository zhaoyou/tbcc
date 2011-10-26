<%@ page language="java"  pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>企业详细的配置信息</title>
<link href="../css/admin/index/configinfo.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="../images/logo.ico" />
<script type="text/javascript" src="../js/jquery-1.4.min.js"></script>
<script type="text/javascript">

	function getStr(){
		var str = $("#str").val();
		if(str==null||str==''){
			alert("请确认是否配置了企业");
			return;
		}
		var strs = str.replace(/[@]/g,"\r\n");
		//alert(strs);
		copyToClipboard(strs);
		alert("复制成功！");
	}
	
	function copyToClipboard(txt) {
			//alert(window.clipboardData);
		     if(window.clipboardData) {
		             window.clipboardData.clearData();
		             window.clipboardData.setData("Text", txt);
		     } else if(navigator.userAgent.indexOf("Opera") != -1) {
		          window.location = txt;
		     } else if (window.netscape) {
		          try {
		               netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
		          } catch(e){
		               alert("被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将'signed.applets.codebase_principal_support'设置为'true'");
		          }
		          var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
		          if (!clip)
		               return;
		          var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
		          if (!trans)
		               return;
		          trans.addDataFlavor('text/unicode');
		          var str = new Object();
		          var len = new Object();
		          var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
		          var copytext = txt;
		          str.data = copytext;
		          trans.setTransferData("text/unicode",str,copytext.length*2);
		          var clipid = Components.interfaces.nsIClipboard;
		          if (!clip)
		               return;
		          clip.setData(trans,null,clipid.kGlobalClipboard);
		          
	     	}
		}

	function doconfig(){
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
<form name="myform" id="myform" action="configinfo.do" method="post">
<input type="hidden" name="ope" id="ope" value="toGetConfig"/>
<input type="hidden" name="str" id="str" value="${str }" />
<div id="content">
  <div id="center" >
    <div class="top" style="display:block;">
      <table id="tb" border="0" cellspacing="0" cellpadding="0" >
        <tr id="tb1" >
          <td colspan="7"><span style="text-align:left; float:left;padding-left:4px; margin-left:4px; color:#141414;"><img src=../images/admin/index/icon.gif width="9" height="10" /> 
          你当前所在位置为：企业详细的配置信息</td>
        </tr>
      </table>
    </div>
    <div class="bottom">
            <table height="28" style="border:1px solid #b6d6e6;
            background:#dff4fc" id="tb" >
              <tr>
              	<td width="150" style="border: none;">&nbsp;</td>
                <td width="72" height="22" style="border: none;">企业名称：</td>
                <td width="131" style="border: none; "><span style="border: none;width:130px;">
                  <select id="oid" name="oid" style="width:125px;">
                  		<c:forEach var="orgs" items="${orglist}" >
                  			<option value="${orgs.oid }" ${org.oid==orgs.oid?"selected='selected'":"" }>${orgs.name }</option>
                  		</c:forEach>
                  </select>
                  </span></td>
                <td width="120" style="border: none;"><a href="javascript:doconfig();" style="border: none; width:130px;"><img src="../images/admin/index/search.gif" width="45" height="20"  style="cursor:pointer;"/></a></td>
                <td width="159" style="border: none;">企业授权码：</td>
                <td width="164" style="border: none; width:130px;"><span style="border: none; ">
                  <input type="text" class="input" readonly="readonly" value="${org.authcode.code }"  style="width:160px;background-color: #f1f4f8;" />
                  </span></td>
                <td width="154" style="border: none;text-align: center;"><a href="javascript:getStr();">复制到EXCEL</a></td>
              </tr>
            </table>
            <table width="100%" height="28" border="0" cellpadding="0" cellspacing="0" id="tb" style="border:0px solid #b6d6e6;
            ";>
              <tr>
                <td width="486" height="22" align="left" style="border: none;"><table  border="0" cellpadding="0" cellspacing="0" id="tb5" style="width:480px; display:block;">
                    <tr class="altrow" >
                      <td width="220" height="22" >冷库名称</td>
                      <td width="100">探头名称</td>
                      <td width="100">编号</td>
                    </tr>
                    <logic:notEmpty name="refconfiglist">
	                    <c:forEach var="refconfig" items="${refconfiglist}" varStatus="irow">
        					<tr style="height:28px;" ${irow.index%2==0?"":"bgcolor='#f1f4f8'"}>
        						<td>${refconfig.refName }</td>
        						<td>${refconfig.aiName }</td>
        						<td>${refconfig.reid }</td>
	                    	</tr>
	                    </c:forEach>
                    </logic:notEmpty>
                    <tr>
                      <td colspan="3"><br />
                        <br />
                        <br />
                        <br /></td>
                    </tr>
                  </table></td>
                <td valign="top" style="border: none; width:18px; ">&nbsp;</td>
                <td width="468" align="right" valign="top" style="border: none; "><table border="0" cellpadding="0" cellspacing="0" id="tb2" style="width:480px;">
                    <tr class="altrow" >
                      <td width="212" height="22" >车载名称 </td>
                      <td width="80">探头名称</td>
                      <td width="80">编号</td>
                      <td width="80">工程编号</td>
                    </tr>
                    <logic:notEmpty name="carconfiglist">
	                    <c:forEach var="carconfig" items="${carconfiglist}" varStatus="iirow">
        					<tr style="height:28px;" ${iirow.index%2==0?"":"bgcolor='#f1f4f8'"}>
        						<td>${carconfig.refName }</td>
        						<td>${carconfig.aiName }</td>
        						<td>${carconfig.reid }</td>
        						<td>${carconfig.projectNo }</td>
	                    	</tr>
	                    </c:forEach>
                    </logic:notEmpty>
                    <tr>
                      <td colspan="4"><br />
                        <br />
                        <br />
                        <br /></td>
                    </tr>
                  </table></td>
              </tr>
            </table>
            <span style="text-align: center;color: red;font-size: 15px;">${tip }</span>
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
          </div>
        </div>
      </div>
      </form>
<div>
  <iframe scrolling="no" src="footer.jsp" width=100% height=26 frameborder=0></iframe >
</div>
</div>
</body>
</html>
