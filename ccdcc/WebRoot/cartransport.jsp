<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="header.jsp"></jsp:include>
<html>
  <head>
    
    <title>车载运输信息配置</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/style.css" rel="stylesheet" type="text/css">
		<LINK href="css/mainWin.css" type=text/css media=screen rel=stylesheet>
		<LINK href="css/mainWin2.css" type=text/css media=screen rel=stylesheet>
		<LINK href="css/desktop.css" type=text/css media=screen rel=stylesheet>
		<link href="css/page/carparam.css" type="text/css" rel="stylesheet"/>
		 <link rel="Shortcut Icon" href="img/add/logo.ico" >
		 <script type="text/javascript" src="script/titleTime.js"></script>
		 <script type="text/javascript" src="script/common.js"> </script>
		 <script type="text/javascript" src="script/carstransport.js"></script>
  </head>
  
  <body> 
    <table width="990" border="0" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#CCCCCC" bordercolor="#FFFFFF"
			bordercolordark="#FFFFFF">
			<tr>
			<td width="990" height="450" colspan="3" valign="top"
					background="img/stock_index_08.gif">
					<div id="main" style="width:990px; height:100%; float:left" >
						<table width="950">
						 <tr>
							<td><div class="page_title">您正在做的业务是：<img src="img/add/club.JPG" alt=">>"><font size="2px">&nbsp;车载运输信息配置</font></div></td>
							<td align="right"><input class="common_button" type="button" value="返回主页面" style="cursor: pointer;"
										onclick="javascript:window.location.href='branch.do?ope=toListByBranch&branchId=${param.branchId }'" /></td>
						 </tr>
						</table>
						
					<form  name="myform" action="carparam.do?ope=changeParam" method="post">
							<input type="hidden" name="branchId" id="branchId" value="${param.branchId }" />				
       	 <div id="navbar">
           	 <div id="header">
                
                		请选择需要配置的车载:
                		<select name="proId" id="proId" onchange="myform.submit();">
											<c:forEach var="proj" items="${carprjList}">		
												<option value='${proj.projectId}' ${param.proId==proj.projectId ?"selected=selected":"" }>${proj.projectName }</option>"
											</c:forEach>
						</select>
                	
                 <span id="nullTip" style="font-size: 15;color: red;display: none;">
                    	&nbsp;&nbsp;&nbsp;请选择需要配置的车载!
                  </span>
            </div>
            
            <div id="content1" class="content">
                <div class="contentHeader">
               	&nbsp;
                </div>
                <div class="contentMain">				
                    
                     <div id="transporttable">
			            <table>			            		
			                <tr>
			                	<td>起始地址:<input type="text" size="18" disabled="true" value="****" style="text-align: center;" /></td>
			                	<td>目的地址:<input type="text" size="18" disabled="true" value="****" style="text-align: center;"/></td>
			                	<td>发 货 人:<input type="text" size="18" disabled="true" value="****" style="text-align: center;" /></td>
							</tr>
							<tr>
								<td>承 运 人:<input type="text" size="18" disabled="true" value="****" style="text-align: center;"/></td>
								<td>收 货 人:<input type="text" size="18" disabled="true" value="****" style="text-align: center;"/></td>
								<td>&nbsp;</td>
			                </tr>
			            </table>
      				  </div>
                    
					<hr style="color:#DCE1EF"/>
					<input type="hidden" name="q_action_id" id="q_action_id"/>
					<input type="hidden" name="u_action_id" id="u_action_id"/>
					<%--用户操作信息配置 --%>
					<input type="hidden" id="uid"  name="uid" value="${LoginUser.id }"/>
					<input type="hidden" id="uname" name="uname" value="${LoginUser.uname }"/>
					<input type="hidden" id="machine" name="machine" value="<%=request.getRemoteHost()%>"/>
					<input type="hidden" id="ext01" name="ext01" value="${LoginUser.client.transactionRole.roleId }"/>
					<input type="hidden" id="ext02" name="ext02" value="${LoginUser.client.transactionId }"/>
					
					
					<center>
					
					<div id="c_query" class="popupcontent" style="display: none">
						
							<img src="img/util/11.gif" >&nbsp;
							<span style="font-size:12px;cursor:pointer;" onclick="">
							<img src="img/util/24.gif" align="middle" height="12"/>取消</span>	
						
					</div>
					
					
					<div id="c_update" class="popupcontent" style="display: none">					
							<img src="img/util/11.gif" >&nbsp;
							<span id="havetime" style="font-size:12px;cursor:pointer;"  >								
							</span>					
					</div>
					
					<br><br>
                    <input id="q_query" name="q_query" type="button" value=" 查询 " onclick=""   />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					
					<input id="u_update" name="u_update" type="button" value=" 更新 " disabled="disabled" onclick=""  />&nbsp;
					
					<span  id="processpic_query"  style="display: none;" ><img   src="img/util/process.gif" alt="操作中" /></span>&nbsp;
					
								
					<span  id="failpic_query"     style="display: inline;"></span>&nbsp;
					
					<span  id="successpic_query"  style="display: inline;"></span>
                              			
               		</center>
               		
               		
                </div>
            </div>			
        </div>
			
						</form>		
				  </div>
				</td>
			</tr>
		</table>
  </body>
</html>
<jsp:include page="footer.jsp" flush="true"></jsp:include>
