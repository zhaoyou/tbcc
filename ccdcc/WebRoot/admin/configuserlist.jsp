<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>�鿴�����ʷ����</title>
<link rel="Shortcut Icon" href="img/add/logo.ico" />
<link href="css/admin/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
		function setTop(){ 
			if (window.navigator.userAgent.indexOf("MSIE") >= 1){
				document.getElementById("mainBody").style.top = 0;		
			}else{
				document.getElementById("mainBody").style.top = -20;
				document.getElementById("foot").style.top = -20;
			}
		}
		
		
		function checkDelId(){
				var id = document.getElementsByName("id");
				if(id.length>0){
					var delId = "";
					for(var i=0;i<id.length;i++){ 
					 	var tem = document.getElementById(id[i].value);
						if(id[i].checked && tem.value=='����Ա'){
							alert("����Ա�û����ܱ�ɾ����");
							return false;
						}else if (id[i].checked) {
							delId += id[i].value + "-";
						}
					}
					if(delId != ""){
						if(confirm("ȷ��Ҫɾ����")){
							var delStr = delId.substring(0,delId.length-1);
							document.getElementById("delStr").value = delStr;
							document.del.submit();
						}
					}else{
						alert("������ѡ��ѡ��ť��Ȼ����ִ��ɾ����");
						return false;
					}
				}else{
					alert("û�п�ɾ���Ķ���");
					return false;
				}
			}
			
			
			function updateUser(){
				var id = document.getElementsByName("id");
				var upId = null;
				if(id.length>0){
					var count = 0;
					for(var i=0;i<id.length;i++){ 
						if(id[i].checked){
							 count++;
							 upId = id[i].value;
					    }
					}
					if(count>1){
						alert("��ֻ��ѡ�񵥸���ѡ��ť��Ȼ������޸ģ�");
						return false;
					}
					if(count<1){
						alert("��ѡ�񵥸���ѡ��ť��Ȼ���ٽ����޸ģ�");
						return false;
					}
					if(count==1){ 
						window.location.href='configUser.do?ope=forwordUpUser&upId='+upId;
					}
				}else{
					alert("û�п��޸ĵĶ���")
					return false;
				}
			}
	</script>
</head>
<body>
<div>
  <iframe scrolling="no" src="common/header3.jsp" width=100% height=126 frameborder=0></iframe >
</div>
<form name="del" id="del" action="configUser.do?ope=delUser" method="post">
<div id="right">
  <div id="top"><a href="#"><img src="images/canku/icon_c.gif" width="16" height="15"  class="tb4"/>
  <strong>λ��:��ҳ</strong>&lt;��֧�û�����</a>
 	</div>
  <div id="center">
    <div>�û��б�</div>
  </div>
  <div id="bottom">
    <table width="85%" border="0" align="center" cellpadding="0" cellspacing="0" id="tb">
      <tr id="tb22">
        <td width="53">�븴ѡ</td>
        <td width="97">�û���</td>
        <td width="111">����</td>
        <td width="100">�����˻�</td>
        <td width="81">������ɫ</td>
        <td width="200">����ʱ��</td>
        <td width="101">��¼��־</td>
      </tr>
      <c:forEach items="${requestScope.userList}" var="user">
  				<tr>
  					<td class="datasn">
  						<input type="checkbox" name="id" value="${user.id}"/>
  					</td>
  					<td class="data">${user.uname}</td>
  					<td class="data">
  						<input type="password" disabled="disabled" value="${user.upassword}" style="text-align: center;" />
  					</td>
  					<td class="data">${user.client.clientName}</td>
  					<td class="data">
  						<input type="text" id="${user.id}" name="role" value="${user.dataRoles.roleName}" class="pw" disabled="disabled" style="text-align: center;"/>
  					</td>
  					<td class="datatime">${user.ucreated}</td>
  					<td><a  href="alarmlogs.do?ope=toLogsList&userName=${user.uname }">�鿴</a></td>
  				</tr>
  		</c:forEach>
    
      <tr>
        <td colspan="7">
        <div id="center">
    <div>
    <img src="images/admin/an0060.gif" width="45" height="19" style="cursor: pointer;" onclick="javascript:window.location.href='configUser.do?ope=forwordAddUser'" />
    <img src="images/admin/an0058.gif" width="45" height="19" style="cursor: pointer;" onclick="updateUser();" />
    <img src="images/admin/an0059.gif" width="45" height="19" style="cursor: pointer;" onclick="checkDelId();" />
    </div>
  </div>
        
        
        
        </td>
      </tr>
    </table>
  </div>
  
</div>
 <input type="hidden" id="delStr" name="delString"/>
 </form>
<div class="clear"></div>
<div>
  <iframe scrolling="no" src="common/footer2.jsp" width=100% height=43 frameborder=0></iframe >
</div>
</body>
</html>
