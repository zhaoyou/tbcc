<%@ page language="java"  pageEncoding="gbk"%>
<%@include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>�鿴�����ʷ����</title>
<link rel="Shortcut Icon" href="img/add/logo.ico" />
<link href="css/admin/tc.css" rel="stylesheet" type="text/css" />
<link href="css/admin/global2.css" rel="stylesheet" type="text/css" />
<style type="text/css">
		.labcss{font-size:14px; text-align:right;}
		.txtcss{width:180px; height:20px; font-size:13px; text-align:left;}
	</style>
<script type="text/javascript">
		function setSelect(){
				var select = document.getElementById("roleId");
				var num = document.getElementById("tem").value;
				if(num!='' && num!=null){
					document.getElementById(num).selected=true;
				}
		}
		
		
		function setBack(){
			var role = '${sessionScope.LoginUser.dataRoles.roleName}';
			if(role!='����Ա'){
				window.location.href='configUser.do?ope=forwordConfigUser';
			}else{
				window.location.href='configUser.do?ope=forwordSysUser';
			}
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
					return true;
				}
			}
		}
	</script>
</head>
<body>
<div>
  <iframe scrolling="no" src="common/header3.jsp" width=100% height=126 frameborder=0></iframe >
</div>
<form action="configUser.do?ope=upUser" method="post">
			<input type="hidden" id="tem" value="${requestScope.upUser.dataRoles.roleName}"/>
			<input type="hidden" name="clientId" value="${sessionScope.LoginUser.client.id}"/>
  			<input type="hidden" name="transactionRoleId" value="3"/>
  			<input type="hidden" name="uEnable" value="1"/>
  			
  			<input type="hidden" name="id" value="${requestScope.upUser.id}"/>
  			
<div id="right">
  <div id="top"><a href="#"><img src="images/admin/icon_c.gif" width="16" height="15" / class="tb4" />
  <strong>λ��:��ҳ</strong>&lt;�޸ķ�֧�û���Ϣ</a>
  </div>
  <div id="center">
    <div>�û��б�</div>
  </div>
  <div id="bottom">
    <fieldset id="login">
      <h4>�û����ã������ڽ����޸��û��Ĳ��������û�ԭ����ϢΪ��</h4>
      <table width="387" height="57" style="border:0;">
        <tr>
          <td width="110" height="24" valign="top"><label for="label">�û����ƣ�</label></td>
          <td width="172"><input type="text"  readonly  value="${requestScope.upUser.uname}" class="txtcss"/></td>
          <td width="202"><span>�����</span></td>
        </tr>
        <tr>
          <td height="24" valign="top"><label for="label">ԭ�û���ɫ��</label></td>
          <td><input type="text"  readonly  value="${requestScope.upUser.dataRoles.roleName}" class="txtcss"/></td>
          <td><span>�����</span></td>
        </tr>
      </table>  <h4>��������û��޸ĺ����������ϢΪ��</h4><table width="350" style="border:0;" >
        <tr>
          <td width="110" height="24" valign="top"><label for="label">���������û�����</label></td>
          <td width="174"><input type="text" id="uName" name="uName" class="txtcss"/></td>
          <td width="200"><span>�����</span></td>
        </tr>
        <tr>
          <td height="28" valign="top"><label for="label">�����������룺</label></td>
          <td><input type="password" id="uPassword" name="uPassword"  class="txtcss"/></td>
          <td><span>�����</span></td>
        </tr>
        <tr>
          <td height="25" valign="top" ><label for="label">�ٴ����������룺</label></td>
          <td><input type="password" id="uPassword2" class="txtcss"/></td>
          <td><span>�����</span></td>
        </tr>
        		<c:if test="${requestScope.upUser.dataRoles.roleName=='����Ա'}">
  					<input type="hidden" id="roleId" name="dataRoleId" value="${requestScope.upUser.dataRoles.roleId}" />
  				</c:if>
  				
  				<c:if test="${requestScope.upUser.dataRoles.roleName=='����Ա'}">
  					<input type="hidden" id="roleId" name="dataRoleId" value="${requestScope.upUser.dataRoles.roleId}" />
  				</c:if>
  				
  		<c:if test="${requestScope.upUser.dataRoles.roleName!='����Ա' && requestScope.upUser.dataRoles.roleName!='����Ա'}">
		       <tr>
		          <td height="25" valign="top" ><label for="label">����ѡ���ɫ����</label></td>
		          	<td>
						<select name="dataRoleId" id="roleId" class="txtcss">
										<option value ="-1">--��ѡ��--</option>
										<c:forEach items="${requestScope.rolesList}" var="role">
											<c:if test="${role.roleName!='����Ա'}">
												<option id="${role.roleId}" value="${role.roleId}" >${role.roleName}</option>
											</c:if>
										</c:forEach>
								</select>
					</td>
		          <td><span>�����</span></td>
		        </tr>
        </c:if>
        <tr>
          <td height="2px"></td>
          <td></td>
          <td>&nbsp;</td>
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
        <tr>
          <td height="21" ></td>
          <td><div>
              <input type="submit" value="����"  class="input_cc" onclick="return checkNull();" />
              <input type="button" value="����"  class="input_aa" onclick="setBack();" />
            </div></td>
          <td>&nbsp;</td>
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
