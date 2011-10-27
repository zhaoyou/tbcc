<%@ page language="java"  pageEncoding="GBK"%>
<%@include file="common/taglib.jsp" %>
<jsp:include page="cheader.jsp" flush="true"></jsp:include>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>更新用户</title>
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
		function setBack(){
			var role = '${sessionScope.LoginUser.hqRoles.name}';
			window.location.href='configHqUser.do?ope=forwordConfigHqUser';
		}
		
		function checkNull(){
			var uName = document.getElementById("uName").value;
			var uPassword = document.getElementById("uPassword").value;
			var uPassword2 = document.getElementById("uPassword2").value;
			
			
			var errStr="";
			if(uName==""){
				errStr+="修改后的用户名不能为空！";
			}
			if(uPassword==""){
				errStr+="\n修改后的用户密码不能为空！"
			}
			if(uPassword2==""){
				errStr+="\n再次输入密码不能为空！"
			}
			if(document.getElementById("roleId")!=null){
				if(document.getElementById("roleId").value!="" && document.getElementById("roleId").value=="-1"){
					errStr+="\n修改后的角色不能为空！"
				}
			}
			
			if(uName!="" && uName!=null){
				if (!/^\w{4,25}$/.test(uName)){
					errStr += "\n您的用户名必须是字母和数字，且长度在4到25之间";
				}
			}
			
			if(uPassword!="" && uPassword!=null){
				if (!/^\w{4,25}$/.test(uPassword)){
					errStr += "\n您的密码必须是字母和数字，且长度在4到25之间";
				}
			}
			if(errStr!=""){
				alert(errStr);
				return false;
			}else{
				if(uPassword2!=uPassword){
					alert("二次输入的密码不匹配！");
					return false;
				}else{
					return ture;
				}
			}
		}
	</script>
  </head>
  		
  <body onload="showtime();">
    	<center>
    		<input type="hidden" id="tem" value="${requestScope.upUser.hqRoles.name}">
  			<div style="background-image:url('img/stock_index_08.gif'); width:990px; height: 550px">
  			<form action="configHqUser.do?ope=upHqUser" method="post">
  			<input type="hidden" name="clientId" value="${sessionScope.LoginUser.client.id}">
  			<input type="hidden" name="uEnable" value="1">
  			
  			<input type="hidden" name="id" value="${requestScope.upUser.id}">
  			<table >
  				<tr>
  					<td colspan="2" height="40px"></td>
  				</tr>
  				<tr>
  					<td colspan="2" height="25px" align="center">
  						用户您好，您正在进行修改用户的操作。该用户原有信息为：
  					</td>
  				</tr>
  				<tr>
  					<td colspan="2" height="25px"> <hr> </td>
  				</tr>
  				<tr>
  					<td><div class="labcss">原用户名：</div></td>
  					<td><input type="text"  readonly  value="${requestScope.upUser.uname}" class="txtcss">（只读）</td>
  				</tr>
  				<tr>
  					<td><div class="labcss">原用户角色：</div></td>
  					<td><input type="text"  readonly  value="${requestScope.upUser.hqRoles.name}" class="txtcss">（只读）</td>
  				</tr>
  				<tr>
  					<td colspan="2" height="25px"> <hr> </td>
  				</tr>	
  				<tr>
  					<td colspan="2" height="25px" >
  						请输入该用户修改后的所有新信息为：
  					</td>
  				</tr>
  				<tr>
  					<td colspan="2" height="25px"> <hr> </td>
  				</tr>
  				<tr>
  					<td style="labcss"><div class="labcss">请输入新用户名：</div></td>
  					<td><input type="text" id="uName" name="uName" class="txtcss">（必填）</td>
  				</tr>
  				<tr>
  					<td style="labcss"><div class="labcss">请输入新密码：</div></td>
  					<td><input type="password" id="uPassword" name="uPassword"  class="txtcss">（必填）</td>
  				</tr>
  				<tr>
  					<td style="labcss"><div class="labcss">再次输入新密码：</div></td>
  					<td><input type="password" id="uPassword2" class="txtcss">（必填）</td>
  				</tr>
  				
  				
  				<c:if test="${requestScope.upUser.hqRoles.name=='管理员'}">
  					<input type="hidden" id="roleId" name="hqRoleId" value="${requestScope.upUser.hqRoles.id}" >
  				</c:if>
  				<c:if test="${requestScope.upUser.hqRoles.name!='管理员'}">
  				<tr>
  					<td style="labcss"><div class="labcss">修改后的角色：</div></td>
  					<td>
						<select name="hqRoleId" id="roleId" class="txtcss">
								<option value ="-1">--请选择--</option>
								<c:forEach items="${requestScope.rolesList}" var="role">
									<c:if test="${role.name!='管理员'}">
										<option id="${role.id}" value="${role.id}" >${role.name}</option>
									</c:if>
								</c:forEach>
						</select>（必选）
					</td>
  				</tr>
  				</c:if>	
  				
  				<tr>
  					<td ></td>
  					<td>
  						<input type="submit" value="保存" onclick="return checkNull();">
  						<input type="button" value="返回" onclick="setBack();">
					</td>
  				</tr>
  				
  				<tr>
  					<td colspan="2" align="center" height="30px">
  						<c:if test="${requestScope.tip=='修改成功！'}">
  							<div>修改成功！</div>
  						</c:if>
  						<c:if test="${requestScope.tip=='修改失败！'}">
  							<div style="width:200px; color:red">修改失败！</div>
  						</c:if>
  						<c:if test="${requestScope.tip=='修改后的用户名重名，请重新选择一个新的用户名！'}">
  							<div style="color:red">修改后的用户名重名，请重新选择一个新的用户名！</div>
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
