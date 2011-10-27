<%@ page language="java"  pageEncoding="GBK"%>
<%@include file="common/taglib.jsp" %>
<jsp:include page="cheader.jsp" flush="true"></jsp:include>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户配置</title>
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
						if(id[i].checked && tem.value=='管理员'){
							alert("管理员用户不能被删除！");
							return false;
						}else if (id[i].checked) {
							delId += id[i].value + "-";
						}
					}
					if(delId != ""){
						if(confirm("确定要删除吗？")){
							var delStr = delId.substring(0,delId.length-1);
							document.getElementById("delStr").value = delStr;
							document.del.submit();
						}
					}else{
						alert("请首先选择复选按钮，然后再执行删除！");
						return false;
					}
				}else{
					alert("没有可删除的对象！");
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
						alert("你只能选择单个复选按钮，然后进行修改！");
						return false;
					}
					if(count<1){
						alert("请选择单个复选按钮，然后再进行修改！");
						return false;
					}
					if(count==1){ 
						window.location.href='configHqUser.do?ope=forwordUpHqUser&upId='+upId;
					}
				}else{
					alert("没有可修改的对象！")
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
					<div style="text-align:left; text-size:13px; text-weight:bolder" >您正在做的业务是：<img src="img/add/club.JPG" alt=">>"><font size="2px">&nbsp;进行用户配置</font></div>
			</div>
			<div style="position:relative; width:990px; height: 550px; float:left; FILTER: progid:DXImageTransform.Microsoft.Gradient(gradientType=0,startColorStr=#BCDDFE,endColorStr=#FFFFFF);">
  			  <input type="hidden" id="delStr" name="delString">
  			  <table >
  				<tr>
  					<td colspan="6" style="height:25px; text-align:center; text-size:14px; font-weight:bolder;">用户列表</td>
  				</tr>
  				<tr style="background-color:#E0E0E0;">
  					<td colspan='6'>
  						<table >
  							<tr>
  							<td class="headsn">请复选</td>
  							<td class="head">用户名</td>
  							<td class="head">密码</td>
  				    		<td class="head">所属账户</td>
  				    		<td class="head">所属角色</td>
  							<td class="headtime">创建时间</td>
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
  			  <input type="button" value="增加" onclick="javascript:window.location.href='configHqUser.do?ope=forwordAddHqUser'">
  			  <input type="button" value="修改" onclick="updateUser();">
  			  <input type="button" value="删除" onclick="checkDelId();">	
  			</div>
  			</form>
  		</div>
  		</center>
  		
  </body>
  <div id="foot" style="position:relative;">
  	  <jsp:include page="footer.jsp" flush="true"></jsp:include>
  </div>
</html>
