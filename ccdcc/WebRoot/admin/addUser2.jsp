<%@ page language="java"  pageEncoding="gbk"%>
<%@include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>���ӷ�֧�û�</title>
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
  <strong>λ��:��ҳ</strong>&lt;���ӷ�֧�û�</a>
  </div>
  <div id="center">
    <div>�û��б�</div>
  </div>
  <div id="bottom">
    <fieldset id="login">
      <h4>�û����ã������ڽ����޸��û��Ĳ������������û���Ϣ��</h4>
      <table width="418" style="border:0;">
        <tr>
          <td width="73" height="24" valign="top"><label for="label">�û����ƣ�</label></td>
          <td width="166"><input type="text" id="uName" name="uName" class="txtcss"/></td>
          <td width="95">�����</td>
        </tr>
        <tr>
          <td height="28" valign="top"><label for="label">�û����룺</label></td>
          <td><input type="password" id="uPassword" name="uPassword" class="txtcss"/></td>
          <td>�����</td>
        </tr>
        <tr>
          <td height="25" valign="top" ><label for="label">�ظ����룺</label></td>
          <td><input type="password" id="uPasswordsecond" name="uPasswordsecond" class="txtcss"/></td>
          <td>�����</td>
        </tr>
        <tr>
          <td height="28" valign="top" ><label for="label">ѡ���ɫ:</label></td>
          <td>
          	<select name="dataRoleId" id="roleId"  class="txtcss" >
								<option value ="-1">--��ѡ��--</option>
								<c:forEach items="${requestScope.rolesList}" var="role">
									<c:if test="${role.roleName!='����Ա'}">
										<option id="${role.roleId}" value="${role.roleId}" >${role.roleName}</option>
									</c:if>
								</c:forEach>
						</select>
          </td>
          <td>�����</td>
        </tr>
        <tr>
          <td height="2px"></td>
          <td></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
        	<td colspan="3">
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
        <tr>
          <td height="21" ></td>
          <td><div>
              <input type="submit" value="����" onclick="return checkNull();"  class="input_cc" />
              <input type="button" value="����"  class="input_aa" onclick="javascript:window.location.href='configUser.do?ope=forwordConfigUser'" />
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
