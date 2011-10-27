<%@ page language="java"  pageEncoding="GBK"%>
<%@include file="common/taglib.jsp" %>
<jsp:include page="cheader.jsp" flush="true"></jsp:include>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>增加用户</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="script/titleTime.js"></script>
	<style type="text/css">
		.labcss{font-size:14px; text-align:right;}
		.txtcss{width:180px; height:20px; font-size:13px; text-align:left;}
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
  		
  <body onload="showtime();">
    	<center>
  			<div style="background-image:url('img/stock_index_08.gif'); width:990px; height: 550px">
  			<form action="configHqUser.do?ope=addHqUser" method="post">
  			<input type="hidden" name="clientId" value="${sessionScope.LoginUser.client.id}">
  			<input type="hidden" name="uEnable" value="1">
  			<table >
  				<tr>
  					<td colspan="2" height="60px"></td>
  				</tr>
  				<tr>
  					<td colspan="2" height="60px">
  						<div class="labcss">用户您好，您正在进行修改用户的操作，请输入用户信息：</div>
  					</td>
  				</tr>
  				
  				<tr>
  					<td><div class="labcss">用户名称：</div></td>
  					<td><input type="text" id="uName" name="uName" class="txtcss">（必填）</td>
  				</tr>
  					
  				<tr>
  					<td style="labcss"><div class="labcss">用户密码：</div></td>
  					<td><input type="password" id="uPassword" name="uPassword" class="txtcss">（必填）</td>
  				</tr>
  				
  				<tr>
  					<td style="labcss"><div class="labcss">重复密码：</div></td>
  					<td><input type="password" id="uPasswordsecond" name="uPasswordsecond" class="txtcss">（必填）</td>
  				</tr>
  					
  				<tr>
  					<td style="labcss"><div class="labcss">用户角色：</div></td>
  					<td>
						<select name="hqRoleId" id="roleId"  class="txtcss">
								<option value ="-1">--请选择--</option>
								<c:forEach items="${requestScope.rolesList}" var="role">
									<c:if test="${role.name!='管理员'}">
										<option id="${role.id}" value="${role.id}" >${role.name}</option>
									</c:if>
								</c:forEach>
						</select>（必填）
					</td>
  				</tr>
  				<tr>
  					<td ></td>
  					<td>
  						<input type="submit" value="保存" onclick="return checkNull();">
  						<input type="button" value="返回" onclick="javascript:window.location.href='configHqUser.do?ope=forwordConfigHqUser'">
					</td>
  				</tr>
  				<tr>
  					<td colspan="2" align="center" height="30px">
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
  			</table>
  			</form>
  			</div>
  		</center>
  </body>
  <jsp:include page="footer.jsp" flush="true"></jsp:include>
</html>
