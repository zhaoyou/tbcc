<%@ page language="java"  pageEncoding="gbk"%>
<%@include file="common/taglib.jsp" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="header.jsp"></jsp:include>
<html>
  <head>
    <title>车载参数配置页面</title>
    
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
		 <script type="text/javascript" src="script/carparam.js"></script>
		 <script type='text/javascript' src='dwr/interface/configOperate.js'></script>
  		<script type='text/javascript' src='dwr/engine.js'></script>
		 <script type='text/javascript' src='dwr/util.js'></script>
		 <script type="text/javascript" src="script/titleTime.js"></script>
		 <script type="text/javascript" src="script/common.js"> </script>
		 
  </head>
  
  <body onload="showtime();getPersonInfo();">
  	<table width="990" border="0" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#CCCCCC" bordercolor="#FFFFFF"
			bordercolordark="#FFFFFF">
			<tr>
			<td width="990" height="450" colspan="3" valign="top"
					background="img/stock_index_08.gif">
					<div id="main" style="width:990px; height:100%; float:left" >
						<table width="950">
						 <tr>
							<td><div class="page_title">您正在做的业务是：<img src="img/add/club.JPG" alt=">>"><font size="2px">&nbsp;车载参数配置</font></div></td>
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
                    <table width="880">
                        <tr>
                            <td>温度预警下限:<span><input id="q3" name="q3" type="text" disabled="true"  value="****" size="8"  style="text-align:center;" />℃</span>
                            	<input type="hidden" name="qq3" id="qq3"/>
                            </td>
							<td>&nbsp;&nbsp;&nbsp;</td>
                            <td>温度预警下限延时:<span><input id="q4" name="q4" type="text" disabled="true" value="****" size="8"  style="text-align:center;" />S</span>
                            	<input type="hidden" name="qq4" id="qq4"/>
                            </td>
                           
                            <td>&nbsp;&nbsp;&nbsp;湿度预警下限:<span><input id="q7" name="q7" type="text" disabled="true" value="****" size="8"  style="text-align:center;" />%</span>
                            	<input type="hidden" name="qq7" id="qq7"/>
                            </td>
							<td>&nbsp;&nbsp;&nbsp;</td>
							<td>湿度预警下限延时:<span><input id="q8" name="q8" type="text" disabled="true" value="**** " size="8"  style="text-align:center;" />S</span>
							<input type="hidden" name="qq8" id="qq8"/>
							</td>
                            
                        </tr>
						
						<tr>
						<td>  温度预警上限:<span><input id="q1" name="q1" type="text" disabled="true" value="****" size="8"  style="text-align:center;"    />℃ </span>
							<input type="hidden" name="qq1" id="qq1"/>
						</td>
						<td>&nbsp;&nbsp;&nbsp;</td>
						<td>温度预警上限延时:<span><input id="q2" name="q2" type="text" disabled="true" value="****" size="8" style="text-align:center;" />S</span>
							<input type="hidden" name="qq2" id="qq2"/>
						</td>						
						
						<td>&nbsp;&nbsp;&nbsp;湿度预警上限:<span><input id="q5" name="q5" type="text" disabled="true" value="****" size="8"  style="text-align:center;" />%</span>
							<input type="hidden" name="qq5" id="qq5"/>
						</td>
						<td>&nbsp;&nbsp;&nbsp;</td>
						<td>湿度预警上限延时:<span><input id="q6" name="q6" type="text" disabled="true" value="****" size="8"  style="text-align:center;" />S</span>
							<input type="hidden" name="qq6" id="qq6"/>
						</td>
						</tr>
							
						<tr>
							<td colspan="6">&nbsp;</td>
						</tr>	
					
					<%--下面是报警的数据 --%>
							
						 <tr>
                            <td>温度报警下限:<span><input id="q11" name="q11" type="text" disabled="true" value="****" size="8"  style="text-align:center;" />℃</span>
                           	<input type="hidden" name="qq11" id="qq11"/>
                            </td>
							<td>&nbsp;&nbsp;&nbsp;</td>
                            <td>温度报警下限延时:<span><input id="q12" name="q12" type="text" disabled="true" value="**** " size="8"  style="text-align:center;" />S</span>
                            	<input type="hidden" name="qq12" id="qq12"/>
                            </td>
                            
                            <td>&nbsp;&nbsp;&nbsp;湿度报警下限:<span><input id="q15" name="q15" type="text" disabled="true" value="****" size="8"  style="text-align:center;" />%</span>
                            	<input type="hidden" name="qq15" id="qq15"/>
                            </td>
							<td>&nbsp;&nbsp;&nbsp;</td>
							<td>湿度报警下限延时:<span><input id="q16" name="q16" type="text" disabled="true" value="**** " size="8"  style="text-align:center;" />S</span>
								<input type="hidden" name="qq16" id="qq16"/>
							</td>
	
                            
                        </tr>
						
						<tr>
						<td>  温度报警上限:<span><input  id="q9" name="q9" type="text" disabled="true" value="****" size="8"  style="text-align:center;" />℃ </span>
							<input type="hidden" name="qq9" id="qq9"/>
						</td>
						<td>&nbsp;&nbsp;&nbsp;</td>
						<td>  温度报警上限延时:<span><input id="q10" name="q10" type="text" disabled="true" value="**** " size="8"  style="text-align:center;" />S</span>
							<input type="hidden" name="qq10" id="qq10"/>
						</td>
						
						<td>&nbsp;&nbsp;&nbsp;湿度报警上限:<span><input id="q13" name="q13" type="text" disabled="true" value="****" size="8"  style="text-align:center;" />%</span>
							<input type="hidden" name="qq13" id="qq13"/>
						</td>
							<td>&nbsp;&nbsp;&nbsp;</td>
						<td>湿度报警上限延时:<span><input id="q14" name="q14" type="text" disabled="true" value="****" size="8"  style="text-align:center;" />S</span>
						<input type="hidden" name="qq14" id="qq14"/>
						</td>
						</tr>
					
                    </table>
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
							<span style="font-size:12px;cursor:pointer;" onclick="doCancle();">
							<img src="img/util/24.gif" align="middle" height="12"/>取消</span>	
						
					</div>
					
					
					<div id="c_update" class="popupcontent" style="display: none">					
							<img src="img/util/11.gif" >&nbsp;
							<span id="havetime" style="font-size:12px;cursor:pointer;"  >								
							</span>					
					</div>
					
					<br><br>
                    <input id="q_query" name="q_query" type="button" value=" 查询 " onclick="getCarParam_pre();"   />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					
					<input id="u_update" name="u_update" type="button" value=" 更新 " disabled="disabled" onclick="updateParams();"  />&nbsp;
					
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
<%@include file="footer.jsp" %>
