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
				errStr+="�޸ĺ���û�������Ϊ�գ�";
			}
			if(uPassword==""){
				errStr+="\n�޸ĺ���û����벻��Ϊ�գ�"
			}
			if(uPassword2==""){
				errStr+="\n�ٴ��������벻��Ϊ�գ�"
			}
			if(document.getElementById("roleId")!=null){
				if(document.getElementById("roleId").value!="" && document.getElementById("roleId").value=="-1"){
					errStr+="\n�޸ĺ�Ľ�ɫ����Ϊ�գ�"
				}
			}
			
			if(uName!="" && uName!=null){
				if (!/^\w{4,25}$/.test(uName)){
					errStr += "\n�����û�����������ĸ�����֣��ҳ�����4��25֮��";
				}
			}
			
			if(uPassword!="" && uPassword!=null){
				if (!/^\w{4,25}$/.test(uPassword)){
					errStr += "\n���������������ĸ�����֣��ҳ�����4��25֮��";
				}
			}
			if(errStr!=""){
				alert(errStr);
				return false;
			}else{
				if(uPassword2!=uPassword){
					alert("������������벻ƥ�䣡");
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
  						�û����ã������ڽ����޸��û��Ĳ��������û�ԭ����ϢΪ��
  					</td>
  				</tr>
  				<tr>
  					<td colspan="2" height="25px"> <hr> </td>
  				</tr>
  				<tr>
  					<td><div class="labcss">ԭ�û�����</div></td>
  					<td><input type="text"  readonly  value="${requestScope.upUser.uname}" class="txtcss">��ֻ����</td>
  				</tr>
  				<tr>
  					<td><div class="labcss">ԭ�û���ɫ��</div></td>
  					<td><input type="text"  readonly  value="${requestScope.upUser.hqRoles.name}" class="txtcss">��ֻ����</td>
  				</tr>
  				<tr>
  					<td colspan="2" height="25px"> <hr> </td>
  				</tr>	
  				<tr>
  					<td colspan="2" height="25px" >
  						��������û��޸ĺ����������ϢΪ��
  					</td>
  				</tr>
  				<tr>
  					<td colspan="2" height="25px"> <hr> </td>
  				</tr>
  				<tr>
  					<td style="labcss"><div class="labcss">���������û�����</div></td>
  					<td><input type="text" id="uName" name="uName" class="txtcss">�����</td>
  				</tr>
  				<tr>
  					<td style="labcss"><div class="labcss">�����������룺</div></td>
  					<td><input type="password" id="uPassword" name="uPassword"  class="txtcss">�����</td>
  				</tr>
  				<tr>
  					<td style="labcss"><div class="labcss">�ٴ����������룺</div></td>
  					<td><input type="password" id="uPassword2" class="txtcss">�����</td>
  				</tr>
  				
  				
  				<c:if test="${requestScope.upUser.hqRoles.name=='����Ա'}">
  					<input type="hidden" id="roleId" name="hqRoleId" value="${requestScope.upUser.hqRoles.id}" >
  				</c:if>
  				<c:if test="${requestScope.upUser.hqRoles.name!='����Ա'}">
  				<tr>
  					<td style="labcss"><div class="labcss">�޸ĺ�Ľ�ɫ��</div></td>
  					<td>
						<select name="hqRoleId" id="roleId" class="txtcss">
								<option value ="-1">--��ѡ��--</option>
								<c:forEach items="${requestScope.rolesList}" var="role">
									<c:if test="${role.name!='����Ա'}">
										<option id="${role.id}" value="${role.id}" >${role.name}</option>
									</c:if>
								</c:forEach>
						</select>����ѡ��
					</td>
  				</tr>
  				</c:if>	
  				
  				<tr>
  					<td ></td>
  					<td>
  						<input type="submit" value="����" onclick="return checkNull();">
  						<input type="button" value="����" onclick="setBack();">
					</td>
  				</tr>
  				
  				<tr>
  					<td colspan="2" align="center" height="30px">
  						<c:if test="${requestScope.tip=='�޸ĳɹ���'}">
  							<div>�޸ĳɹ���</div>
  						</c:if>
  						<c:if test="${requestScope.tip=='�޸�ʧ�ܣ�'}">
  							<div style="width:200px; color:red">�޸�ʧ�ܣ�</div>
  						</c:if>
  						<c:if test="${requestScope.tip=='�޸ĺ���û���������������ѡ��һ���µ��û�����'}">
  							<div style="color:red">�޸ĺ���û���������������ѡ��һ���µ��û�����</div>
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
