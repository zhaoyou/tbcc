<%@ page language="java"  pageEncoding="GBK"%>
<%@include file="common/taglib.jsp" %>
<jsp:include page="cheader.jsp" flush="true"></jsp:include>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>�����û�</title>
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
				errStr += '�û�������Ϊ�գ�';
			}
			
			if(document.getElementById("uPassword").value==""||document.getElementById("uPassword").value==null){
				errStr += '\n�û����벻��Ϊ�գ�';
			}
			
			if(document.getElementById("uPasswordsecond").value==""||document.getElementById("uPasswordsecond").value==null){
				errStr += '\n�ظ����벻��Ϊ�գ�';
			}
			
			if(document.getElementById("roleId").value==""||document.getElementById("roleId").value==null||document.getElementById("roleId").value=="-1"){
				errStr += '\n�û���ɫ����Ϊ�գ�';
			}
			
			if(document.getElementById("uName").value!="" && document.getElementById("uName").value!=null){
				if (!/^\w{4,25}$/.test(document.getElementById("uName").value)){
					errStr += "\n�����û�����������ĸ�����֣��ҳ�����4��25֮��";
				}
			}
			
			if(document.getElementById("uPassword").value!="" && document.getElementById("uPassword").value!=null){
				if (!/^\w{4,25}$/.test(document.getElementById("uPassword").value)){
					errStr += "\n���������������ĸ�����֣��ҳ�����4��25֮��";
				}
			}
			
			if(document.getElementById("uPassword").value!="" && document.getElementById("uPasswordsecond").value!=""){
				if(document.getElementById("uPassword").value!=document.getElementById("uPasswordsecond").value){
					errStr += '\n������������벻ƥ�䣡';
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
  						<div class="labcss">�û����ã������ڽ����޸��û��Ĳ������������û���Ϣ��</div>
  					</td>
  				</tr>
  				
  				<tr>
  					<td><div class="labcss">�û����ƣ�</div></td>
  					<td><input type="text" id="uName" name="uName" class="txtcss">�����</td>
  				</tr>
  					
  				<tr>
  					<td style="labcss"><div class="labcss">�û����룺</div></td>
  					<td><input type="password" id="uPassword" name="uPassword" class="txtcss">�����</td>
  				</tr>
  				
  				<tr>
  					<td style="labcss"><div class="labcss">�ظ����룺</div></td>
  					<td><input type="password" id="uPasswordsecond" name="uPasswordsecond" class="txtcss">�����</td>
  				</tr>
  					
  				<tr>
  					<td style="labcss"><div class="labcss">�û���ɫ��</div></td>
  					<td>
						<select name="hqRoleId" id="roleId"  class="txtcss">
								<option value ="-1">--��ѡ��--</option>
								<c:forEach items="${requestScope.rolesList}" var="role">
									<c:if test="${role.name!='����Ա'}">
										<option id="${role.id}" value="${role.id}" >${role.name}</option>
									</c:if>
								</c:forEach>
						</select>�����
					</td>
  				</tr>
  				<tr>
  					<td ></td>
  					<td>
  						<input type="submit" value="����" onclick="return checkNull();">
  						<input type="button" value="����" onclick="javascript:window.location.href='configHqUser.do?ope=forwordConfigHqUser'">
					</td>
  				</tr>
  				<tr>
  					<td colspan="2" align="center" height="30px">
  						<c:if test="${requestScope.tip=='����ɹ���'}">
  							<div>����ɹ���</div>
  						</c:if>
  						<c:if test="${requestScope.tip=='����ʧ�ܣ�'}">
  							<div style="color:red">����ʧ�ܣ�</div>
  						</c:if>
  						<c:if test="${requestScope.tip=='�û���������������ѡ��һ���µ��û�����'}">
  							<div style="color:red">�û���������������ѡ��һ���µ��û�����</div>
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
