<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="header.jsp"></jsp:include>
<html>
  <head>
    
    <title>����������Ϣ����</title>
    
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
							<td><div class="page_title">����������ҵ���ǣ�<img src="img/add/club.JPG" alt=">>"><font size="2px">&nbsp;����������Ϣ����</font></div></td>
							<td align="right"><input class="common_button" type="button" value="������ҳ��" style="cursor: pointer;"
										onclick="javascript:window.location.href='branch.do?ope=toListByBranch&branchId=${param.branchId }'" /></td>
						 </tr>
						</table>
						
					<form  name="myform" action="carparam.do?ope=changeParam" method="post">
							<input type="hidden" name="branchId" id="branchId" value="${param.branchId }" />				
       	 <div id="navbar">
           	 <div id="header">
                
                		��ѡ����Ҫ���õĳ���:
                		<select name="proId" id="proId" onchange="myform.submit();">
											<c:forEach var="proj" items="${carprjList}">		
												<option value='${proj.projectId}' ${param.proId==proj.projectId ?"selected=selected":"" }>${proj.projectName }</option>"
											</c:forEach>
						</select>
                	
                 <span id="nullTip" style="font-size: 15;color: red;display: none;">
                    	&nbsp;&nbsp;&nbsp;��ѡ����Ҫ���õĳ���!
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
			                	<td>��ʼ��ַ:<input type="text" size="18" disabled="true" value="****" style="text-align: center;" /></td>
			                	<td>Ŀ�ĵ�ַ:<input type="text" size="18" disabled="true" value="****" style="text-align: center;"/></td>
			                	<td>�� �� ��:<input type="text" size="18" disabled="true" value="****" style="text-align: center;" /></td>
							</tr>
							<tr>
								<td>�� �� ��:<input type="text" size="18" disabled="true" value="****" style="text-align: center;"/></td>
								<td>�� �� ��:<input type="text" size="18" disabled="true" value="****" style="text-align: center;"/></td>
								<td>&nbsp;</td>
			                </tr>
			            </table>
      				  </div>
                    
					<hr style="color:#DCE1EF"/>
					<input type="hidden" name="q_action_id" id="q_action_id"/>
					<input type="hidden" name="u_action_id" id="u_action_id"/>
					<%--�û�������Ϣ���� --%>
					<input type="hidden" id="uid"  name="uid" value="${LoginUser.id }"/>
					<input type="hidden" id="uname" name="uname" value="${LoginUser.uname }"/>
					<input type="hidden" id="machine" name="machine" value="<%=request.getRemoteHost()%>"/>
					<input type="hidden" id="ext01" name="ext01" value="${LoginUser.client.transactionRole.roleId }"/>
					<input type="hidden" id="ext02" name="ext02" value="${LoginUser.client.transactionId }"/>
					
					
					<center>
					
					<div id="c_query" class="popupcontent" style="display: none">
						
							<img src="img/util/11.gif" >&nbsp;
							<span style="font-size:12px;cursor:pointer;" onclick="">
							<img src="img/util/24.gif" align="middle" height="12"/>ȡ��</span>	
						
					</div>
					
					
					<div id="c_update" class="popupcontent" style="display: none">					
							<img src="img/util/11.gif" >&nbsp;
							<span id="havetime" style="font-size:12px;cursor:pointer;"  >								
							</span>					
					</div>
					
					<br><br>
                    <input id="q_query" name="q_query" type="button" value=" ��ѯ " onclick=""   />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					
					<input id="u_update" name="u_update" type="button" value=" ���� " disabled="disabled" onclick=""  />&nbsp;
					
					<span  id="processpic_query"  style="display: none;" ><img   src="img/util/process.gif" alt="������" /></span>&nbsp;
					
								
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
