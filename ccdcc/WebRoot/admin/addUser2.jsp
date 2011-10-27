<%@ page language="java"  pageEncoding="gbk"%>
<%@include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>增加分支用户</title>
<link rel="Shortcut Icon" href="img/add/logo.ico" />
<link href="css/admin/tc.css" rel="stylesheet" type="text/css" />
<link href="css/admin/global.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	.labcss{font-size:12px; text-align:right;}
		.txtcss{width:180px; height:20px; font-size:13px; text-align:left;border: 1px solid #7F9DB9;
	width: 154px;
	height: 18px;
	color: #222222;
	font-size: 12px;
	margin-bottom: 5px;
	background-color: #FFF;
	line-height: 18px;
	display: block;
	margin-top: 4px;
	margin-right: 2px;
	margin-left: 8px;
	padding: 0px;}
</style>
<script type="text/javascript">
		function checkNull(){
			var errStr = "";
			if(document.getElementById("uName").value==""||document.getElementById("uName").value==null){
				errStr += '用户名不能为空！';
			}
			if(document.getElementById("uPassword").value==""||document.getElementById("uPassword").value==null){
				errStr += '\n用户密码不能为空！';
			}
			
			if(document.getElementById("uPasswordsecond").value==""||document.getElementById("uPasswordsecond").value==null){
				errStr += '\n重复密码不能为空！';
			}
			
			if(document.getElementById("roleId").value==""||document.getElementById("roleId").value==null||document.getElementById("roleId").value=="-1"){
				errStr += '\n用户角色不能为空！';
			}
			
			if(document.getElementById("uName").value!="" && document.getElementById("uName").value!=null){
				if (!/^\w{4,25}$/.test(document.getElementById("uName").value)){
					errStr += "\n您的用户名必须是字母和数字，且长度在4到25之间";
				}
			}
			
			if(document.getElementById("uPassword").value!="" && document.getElementById("uPassword").value!=null){
				if (!/^\w{4,25}$/.test(document.getElementById("uPassword").value)){
					errStr += "\n您的密码必须是字母和数字，且长度在4到25之间";
				}
			}
			
			if(document.getElementById("uPassword").value!="" && document.getElementById("uPasswordsecond").value!=""){
				if(document.getElementById("uPassword").value!=document.getElementById("uPasswordsecond").value){
					errStr += '\n二次输入的密码不匹配！';
				}
			}
			
			if(errStr==""){
				return true;
			}else{
				alert(errStr);
				return false;
			}
		}
	</script>
</head>
<body>
<div>
  <iframe scrolling="no" src="common/header3.jsp" width=100% height=126 frameborder=0></iframe >
</div>
<form action="configUser.do?ope=addUser" method="post">

			<input type="hidden" name="clientId" value="${sessionScope.LoginUser.client.id}"/>
  			<input type="hidden" name="transactionRoleId" value="3"/>
  			<!-- <input type="hidden" name="transactionId" value=""> -->	
  			<input type="hidden" name="uEnable" value="1"/>
<div id="right">
  <div id="top"><a href="#"><img src="images/admin/icon_c.gif" width="16" height="15"  class="tb4" />
  <strong>位置:首页</strong>&lt;增加分支用户</a>
  </div>
  <div id="center">
    <div>用户列表</div>
  </div>
  <div id="bottom">
    <fieldset id="login">
      <h4>用户您好，您正在进行修改用户的操作，请输入用户信息：</h4>
      <table width="418" style="border:0;">
        <tr>
          <td width="73" height="24" valign="top"><label for="label">用户名称：</label></td>
          <td width="166"><input type="text" id="uName" name="uName" class="txtcss"/></td>
          <td width="95">（必填）</td>
        </tr>
        <tr>
          <td height="28" valign="top"><label for="label">用户密码：</label></td>
          <td><input type="password" id="uPassword" name="uPassword" class="txtcss"/></td>
          <td>（必填）</td>
        </tr>
        <tr>
          <td height="25" valign="top" ><label for="label">重复密码：</label></td>
          <td><input type="password" id="uPasswordsecond" name="uPasswordsecond" class="txtcss"/></td>
          <td>（必填）</td>
        </tr>
        <tr>
          <td height="28" valign="top" ><label for="label">选择角色:</label></td>
          <td>
          	<select name="dataRoleId" id="roleId"  class="txtcss" >
								<option value ="-1">--请选择--</option>
								<c:forEach items="${requestScope.rolesList}" var="role">
									<c:if test="${role.roleName!='管理员'}">
										<option id="${role.roleId}" value="${role.roleId}" >${role.roleName}</option>
									</c:if>
								</c:forEach>
						</select>
          </td>
          <td>（必填）</td>
        </tr>
        <tr>
          <td height="2px"></td>
          <td></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
        	<td colspan="3">
        			<c:if test="${requestScope.tip=='保存成功！'}">
  							<div>保存成功！</div>
  						</c:if>
  						<c:if test="${requestScope.tip=='保存失败！'}">
  							<div style="color:red">保存失败！</div>
  						</c:if>
  						<c:if test="${requestScope.tip=='用户名重名，请重新选择一个新的用户名！'}">
  							<div style="color:red">用户名重名，请重新选择一个新的用户名！</div>
  				</c:if>
        	</td>
        </tr>
        <tr>
          <td height="21" ></td>
          <td><div>
              <input type="submit" value="保存" onclick="return checkNull();"  class="input_cc" />
              <input type="button" value="返回"  class="input_aa" onclick="javascript:window.location.href='configUser.do?ope=forwordConfigUser'" />
            </div></td>
          <td>
				&nbsp;
		</td>
        </tr>
        <tr>
          <td></td>
        </tr>
        <tr>
          <td colspan="3" align="center" height="2"></td>
        </tr>
      </table>
    </fieldset>
  </div>
</div>
</form>
<div class="clear"></div>
<div>
  <iframe scrolling="no" src="common/footer2.jsp" width=100% height=43 frameborder=0></iframe >
</div>
</body>
</html>
