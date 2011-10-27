<%@ page language="java"  pageEncoding="GBK"%>
<%@include file="common/taglib.jsp" %>
<jsp:include page="header.jsp" flush="true"></jsp:include>

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
	</script>
  </head>
  		
  <body onload="showtime();">
    	<center>
  		<div id="mainBody" style="position:relative; background-image:url('img/stock_index_08.gif'); width:990px; height: 570px;">
  			<div id="main" style="position:relative; width:990px; height:20px; float:left" >
					<div style="text-align:left; text-size:13px; text-weight:bolder" >您正在做的业务是：<img src="img/add/club.JPG" alt=">>"><font size="2px">&nbsp;查看系统中的所有管理员用户</font></div>
			</div>
			<div style="position:relative; width:990px; height: 550px; float:left; FILTER: progid:DXImageTransform.Microsoft.Gradient(gradientType=0,startColorStr=#BCDDFE,endColorStr=#FFFFFF);">
  			  <input type="hidden" id="delStr" name="delString">
  			  <table >
  				<tr>
  					<td colspan="7" style="height:25px; text-align:center; text-size:14px; font-weight:bolder;">用户列表</td>
  				</tr>
  				<tr style="background-color:#E0E0E0;">
  					<td colspan='7'>
  						<table >
  							<tr>
  							<td class="headsn">序号</td>
  							<td class="head">用户名</td>
  							<td class="head">密码</td>
  				    		<td class="head">所属账户</td>
  				    		<td class="head">所属企业</td>
  				    		<td class="head">所属角色</td>
  							<td class="headtime">创建时间</td>
  							</tr>
  						</table>
  					</td>
  				</tr>
  				<%int i=1; %>
  				<c:forEach items="${requestScope.adminList}" var="user">
  				<tr>
  					<td class="datasn">
  						<%=i++%>
  					</td>
  					<td class="data">${user.user.uname}</td>
  					<td class="data">${user.user.upassword}</td>
  					<td class="data">${user.user.client.clientName}</td>
  					<td class="data">${user.branchType.branchName}</td>
  					<td class="data">${user.user.dataRoles.roleName}</td>
  					<td class="datatime">${user.user.ucreated}</td>
  				</tr>
  				</c:forEach>
  			</table>
  			<hr style="width:720px">
  			<div style="font-size:13px">
  				工程员${sessionScope.LoginUser.uname}，你好。如果你要修改自己的用户名与密码，请点击这里
  			    &nbsp;<a href="javascript: window.location.href='configUser.do?ope=forwordUpUser&upId=${sessionScope.LoginUser.id}'">修改</a>
  			</div>
  			</div>
  			
  		</div>
  		</center>
  		
  </body>
  <div id="foot" style="position:relative;">
  	  <jsp:include page="footer.jsp" flush="true"></jsp:include>
  </div>
</html>
