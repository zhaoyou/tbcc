<%@ page language="java"  pageEncoding="GBK"%>
<%@include file="common/taglib.jsp" %>
<jsp:include page="cheader.jsp" flush="true"></jsp:include>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>�û�����</title>
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
		.headsn{width:50px; font-size:14px; font-weight:bolder; text-align:center;}
		.headtime{width:140px; font-size:14px; font-weight:bolder; text-align:center;}
		.head{width:100px; font-size:14px; font-weight:bolder; text-align:center;}
		.datasn{width:50px; font-size:12px; text-align:center;}
		.datatime{width:140px; font-size:12px; text-align:center;}
		.data{width:100px; font-size:12px; text-align:center;}
		.pw{width:100px; text-align:center; background-color: transparent; border-style: none;}
	</style>
	
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
						window.location.href='configHqUser.do?ope=forwordUpHqUser&upId='+upId;
					}
				}else{
					alert("û�п��޸ĵĶ���")
					return false;
				}
			}
	</script>
  </head>
  		
  <body onload="showtime();setTop();">
    	<center>
  		<div id="mainBody" style="position:relative; background-image:url('img/stock_index_08.gif'); width:990px; height: 570px;">
  			<form name="del" action="configHqUser.do?ope=delHqUser" method="post">
  			<div id="main" style="position:relative; width:990px; height:20px; float:left" >
					<div style="text-align:left; text-size:13px; text-weight:bolder" >����������ҵ���ǣ�<img src="img/add/club.JPG" alt=">>"><font size="2px">&nbsp;�����û�����</font></div>
			</div>
			<div style="position:relative; width:990px; height: 550px; float:left; FILTER: progid:DXImageTransform.Microsoft.Gradient(gradientType=0,startColorStr=#BCDDFE,endColorStr=#FFFFFF);">
  			  <input type="hidden" id="delStr" name="delString">
  			  <table >
  				<tr>
  					<td colspan="6" style="height:25px; text-align:center; text-size:14px; font-weight:bolder;">�û��б�</td>
  				</tr>
  				<tr style="background-color:#E0E0E0;">
  					<td colspan='6'>
  						<table >
  							<tr>
  							<td class="headsn">�븴ѡ</td>
  							<td class="head">�û���</td>
  							<td class="head">����</td>
  				    		<td class="head">�����˻�</td>
  				    		<td class="head">������ɫ</td>
  							<td class="headtime">����ʱ��</td>
  							</tr>
  						</table>
  					</td>
  				</tr>
  				<c:forEach items="${requestScope.userList}" var="user">
  				<tr>
  					<td class="datasn">
  						<input type="checkbox" name="id" value="${user.id}">
  					</td>
  					<td class="data">${user.uname}</td>
  					<td class="data">
  						<input type="password" value="${user.upassword}" class="pw" readonly>
  					</td>
  					<td class="data">${user.client.clientName}</td>
  					<td class="data">
  						<input type="text" id="${user.id}" name="role" value="${user.hqRoles.name}" class="pw" readonly>
  					</td>
  					<td class="datatime">${user.ucreated}</td>
  				</tr>
  				</c:forEach>
  			</table>
  			  <hr style="width:620px">
  			  <input type="button" value="����" onclick="javascript:window.location.href='configHqUser.do?ope=forwordAddHqUser'">
  			  <input type="button" value="�޸�" onclick="updateUser();">
  			  <input type="button" value="ɾ��" onclick="checkDelId();">	
  			</div>
  			</form>
  		</div>
  		</center>
  		
  </body>
  <div id="foot" style="position:relative;">
  	  <jsp:include page="footer.jsp" flush="true"></jsp:include>
  </div>
</html>
