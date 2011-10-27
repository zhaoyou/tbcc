<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="common/taglib.jsp" %>

<html>
	<head>
		<title>工程信息列表</title>
		<link href="css/style.css" rel="stylesheet" type="text/css">
		<LINK href="css/mainWin.css" type=text/css media=screen rel=stylesheet>
		<LINK href="css/mainWin2.css" type=text/css media=screen rel=stylesheet>
		<LINK href="css/desktop.css" type=text/css media=screen rel=stylesheet>
		<link href="css/login.css" type="text/css" rel="stylesheet">
		<link rel="Shortcut Icon" href="img/add/logo.ico" >
		<script type="text/javascript" src="script/common.js"></script>
		<script type="text/javascript" src="script/titleTime.js"></script>
		<style type="text/css">
			.STYLE1 {font-size: 12px}
        </style>

	<style type="css/text">
			.div_top{
				border-top: solid 1px #4986D4;
			}
		</style>
		
		<script type="text/javascript">
						//操作隐藏显示不同的系统的层	
				 function operateDiv(divid){    
	       		     if(document.getElementById(divid).style.display =='none')
	            		 document.getElementById(divid).style.display = 'block' ;
	        		else
	         		    document.getElementById(divid).style.display = 'none' ;
   				 }
   				 
   				 /**
   				 *   屏蔽JavaScript错误,开发阶段不需要调用、部署时才启用该方法
   				 **/
   				 function errorHandler(){
   				 	return true ;
   				 }	 
   				 window.onerror = errorHandler;
   				 
   				 /**
   				 *	跳转到制冷数据实时数据页面
   				 */
   				 function goRealCool(flag){
   				 	if(flag==true){
	   				 	var b = document.getElementById("branchId").value ;
	   				 	//原先的制冷页面
	   				 	//goPowerURL("realcool.do?ope=toRealCool&branchId="+b,flag) ;
	   				 	//跳转到新的制冷页面
	   				 	goPowerURL("realcool.do?ope=toRealCoolSys&branchId="+b,flag) ;
   				 	}
   				 }
   				 
   				 /**
   				 *	实现权限的跳转
   				 */
   				 function goPowerURL(url,flag){
   				 	if(flag==true){
   				 		window.location.href = url ;
   				 	}
   				 }
   				 
   				
		</script>
		<script type="text/javascript"></script>
	</head>
	<script>
	</script>
	<body onload="showtime();" bgcolor="#FFFFFF" leftmargin="0" topmargin="4" marginwidth="0" marginheight="0">
		<%--@include  file="header.jsp" --%>
		<jsp:include page="header.jsp" flush="true"></jsp:include>
		<!-- 保存该用户的分支ID -->
		<input type="hidden" id="branchId"  name="branchId" value="${param.branchId}"/>
		<table width="990" border="0" align="center" cellpadding="0" bgcolor="#FFFFF9"
			cellspacing="0" bordercolorlight="#CCCCCC" bordercolor="#FFFFFF" class="PageBody"
			bordercolordark="#FFFFFF" >
			<tr>
				<td width="990" colspan="3" valign="top"
					background="img/stock_index_08.gif">
						<div id="main" style="width:990px; height:100%; float:left" >
								<div class="page_title">您正在做的业务是：<img src="img/add/club.JPG" alt=">>"><font size="2px">&nbsp;查看所有工程信息</font></div>
								<div class="button_bar"></div>
							<div>
				</td>
			</tr>
			
			<tr> 
				<td>
					<table border=0 cellpadding=0 cellspacing=0 width="990" align="center" class="PageBody">
      <tr align="center">
        <td width="170"> 序号</td>
        <td width="220" > 工程名称 </td>
        <td width="220" > 工程代码 </td>
        <td width="380" > 操作 </td>
      </tr>
    </table>
    <hr>
	<table BORDER=0 CELLPADDING=0 CELLSPACING=0 WIDTH="990" align="center">
            <tr >
               <td width="170">
              <div align="center" ><input type="button" class="common_button" value="仓库工程"/></div></td>
			  <td width="220" ><div align="center"></div></td>
			  <td width="220" ><div align="center"></div></td>
                <td width="380" >
                <div align="center">
				<logic:notEmpty name="rList">
					<span style="display:${fn:contains(power,"冷库实时数据")==true?'inline':'none'} "  onclick="javascript:goPowerURL('realref.do?ope=toRealList&branchId=${param.branchId }',${fn:contains(power,"冷库实时数据")});">
					<button type="button" title="仓库实时数据"  class="common_button2" >
						<img  alt="仓库实时数据" src="img/menu/bt_feedback.gif" align="top" />实时 </button>&nbsp;	 
					</span>
					<span style="display:${fn:contains(power,"冷库历史数据")==true?'inline':'none'} " onclick="javascript:goPowerURL('hisref.do?ope=toHisRefList&branchId=${param.branchId }',${fn:contains(power,"冷库历史数据")})">
					<button type="button" title="仓库历史数据" class="common_button2"  >
						<img   alt="仓库历史数据"  align="top" src="img/menu/bt_detail.gif"   />历史</button>&nbsp;
					</span>
					<span  style="display:${fn:contains(power,"冷库历史曲线")==true?'inline':'none'} " onclick="javascript:goPowerURL('ccdcc/hisref.html?branchId=${param.branchId }',${fn:contains(power,"冷库历史曲线")})">			
					<button type="button" title="仓库历史曲线" class="common_button2" >
						<img  alt="仓库历史曲线"  src="img/menu/bt_orders.gif"  align="top"  />曲线</button>&nbsp;	 
					</span>
					<span  style="display:${fn:contains(power,"冷库实时图层")==true?'inline':'none'} " onclick="javascript:goPowerURL('realref.do?ope=toRealFloor&branchId=${param.branchId }',${fn:contains(power,"冷库实时图层")})">	
					<button type="button" title="仓库图层实时数据" class="common_button2" >
						<img  src="img/menu/bt_acti.gif"  alt="仓库图层实时数据"  align="top"  />图层</button>&nbsp;	
					</span>	
					<span  style="display:${fn:contains(power,"冷库制冷实时")==true?'inline':'none'} " onclick="javascript:goRealCool(${fn:contains(power,"冷库制冷实时")})">							
					<button type="button" title="制冷实时数据" class="common_button2" >
						<img  alt="制冷实时数据" src="img/menu/bt_confirm.gif" align="top"  />制冷</button>				            
					</span>
				</logic:notEmpty>
						</div>
					</td>
            </tr>
            <tbody>
            	<c:forEach var="item" items="${rList}" varStatus="index">
                <tr  align="center">
                    <td >
                    <div align="center">${index.count}            </div></td>
                    <td >
                    <div align="center">${item.projectName } </div></td>
                    <td >
                    <div align="center">${item.projectCode } </div></td>
                    <td align="center">
                      <div align="center"></div></td>
                </tr>
				</c:forEach>
				<logic:empty name="rList">
                <tr align="center">
                    <td colspan="4" >
                    <div align="center" style="color: blue" >没有配置仓库工程</div>
                    </td>      
                </tr>
                </logic:empty>
            </tbody>
    </table>
    <hr>
		<table BORDER=0 CELLPADDING=0 CELLSPACING=0 WIDTH="990" align="center">
            <tr >
               <td width="170" >
               <div align="center"><input type="button" class="common_button" value="车载工程"/></div>
               </td>
			  <td width="220" ><div align="center"></div></td>
			  <td width="220" ><div align="center"></div></td>
              <td width="380" >
              <div align="center">
             <logic:notEmpty name="cList">
             	<span  style="display:${fn:contains(power,"车载实时数据")==true?'inline':'none'} " onclick="javascript:goPowerURL('realcar.do?ope=toRealData&branchId=${param.branchId }',${fn:contains(power,"车载实时数据")})"  style="cursor:pointer">
			  	<button type="button" title="车载实时数据" class="common_button2">    			
			      	<img  alt=车载实时数据 src="img/menu/bt_feedback.gif" align="top" />实时	</button>&nbsp;
				</span>
				<span style="display:${fn:contains(power,"车载历史数据")==true?'inline':'none'} " onclick="javascript:goPowerURL('hiscar.do?ope=toHisCarStartUp&branchId=${param.branchId}',${fn:contains(power,"车载历史数据")})" style="cursor:pointer">
				<button type="button" title="车载历史启停" class="common_button2" >
					<img  alt="车载历史启停"  src="img/menu/bt_detail.gif"  align="top"/>历史 </button>	&nbsp;		 
				</span>
				<span  style="display:none" onclick="javascript:goPowerURL('carparam.do?ope=toParamConfig&branchId=${param.branchId}',${fn:contains(power,"车载")})">
				<button type="button" title="车载参数配置" class="common_button2" >
					<img alt="车载参数配置" src="img/menu/mbi_042.gif" align="top"/>参数
				</button>	
				</span> 
			</logic:notEmpty>		    	 
					    	  </div>				    	  
					 </td>
            </tr>
            <tbody>
            	<c:forEach var="item" items="${cList}" varStatus="index">
	                <tr  align="center">
	                    <td >
	                    <div align="center">${index.count }                   </div></td>
	                    <td >
	                    <div align="center">${item.projectName }</div></td>
	                    <td>
	                    <div align="center">${item.projectCode } </div></td>
	                    <td align="center" >
	                      <div align="center"></div></td>
	                </tr>
                </c:forEach>
                <logic:empty name="cList">
                	<tr  align="center">
                    <td colspan="4">
                    <div align="center" style="color: blue" >没有配置车载工程</div></td>
                </tr>
                </logic:empty>
                
            </tbody>
    </table>
    <hr>
        <table BORDER=0 CELLPADDING=0 CELLSPACING=0 WIDTH="990" align="center">
            <tr>
               <td width="170"  >
              <div align="center"><input type="button" class="common_button" value="小批零工程"/>  </div></td>
				<td width="220"  ><div align="center"></div></td>
			  <td width="220"  ><div align="center"></div></td>
                <td width="380" >
              <div align="center">
              
              <logic:notEmpty name="bList" scope="request">
			  	<button type="button" class="common_button2" title="小批零实时数据" style="display:${fn:contains(power,"小批零实时数据") } " onclick="javascript:goPowerURL('realbox.do?ope=toRealData&branchId=${param.branchId }',${fn:contains(power,"小批零实时数据") })" style="cursor:pointer">
						<img   alt="小批零实时数据" src="img/menu/bt_feedback.gif"  align="middle" />实时</button>&nbsp;
				<button type="button" class="common_button2" title="小批零历史启停" style="display:${fn:contains(power,"小批零历史数据") } " onclick="javascript:goPowerURL('hisbox.do?ope=toHisBoxStart&branchId=${param.branchId}',${fn:contains(power,"小批零历史数据")})" style="cursor:pointer">
						 <img  alt="小批零历史启停"  src="img/menu/bt_detail.gif"  align="middle"/>历史</button>				
			 </logic:notEmpty>
			 
			  </div></td>
            </tr>
            <tbody>
            	<c:forEach var="item" items="${bList}" varStatus="index">
	                <tr  align="center">
	                    <td >
	                    <div align="center">${index.count }                   </div></td>
	                    <td >
	                    <div align="center">${item.projectName }</div></td>
	                    <td >
	                    <div align="center">${item.projectCode }  </div></td>
	                    <td align="center" >
	                      <div align="center"></div></td>
	                </tr>
                </c:forEach>
                <logic:empty name="bList">
                	 <tr  align="center">
                    <td colspan="4">
                    <div align="center" style="color: blue">没有配置小批零工程  </div></td>
                </tr>
                </logic:empty>             
            </tbody>		
    </table>
	
	<logic:notEmpty name="branchList">
	<hr>
	 <table BORDER=0 CELLPADDING=0 CELLSPACING=0 WIDTH="990" align="center">
            <tr>
               <td width="169" >
              <div align="center"><input type="button" class="common_button" value="客户冷库系统"/></div></td>
			  <td width="220" ><div align="center"></div></td>
			  <td width="220" ><div align="center"></div></td>
                <td width="380"></td>
             	<td width="1"></td>
            </tr>
			
			
            <tbody>
            	<c:forEach var="branch" items="${branchList}" varStatus="index">
	                <tr  align="center">
	                    <td > <div align="center">${index.count } </div></td>
	                    <td > <div align="center">${branch.branchName }</div></td>    
						<td></td>            
	                    <td align="center" >
	                      <div align="center">
	                      <button type="button" title="冷库实时数据" style="display:${fn:contains(power,"客户冷库实时")==true?'inline':'none'} " class="common_button2" onclick="javascript:goPowerURL('realref.do?ope=toCustomerReal&branchId=${branch.branchId }&sbranchId=${param.branchId }',${fn:contains(power,"客户冷库实时")})">
								<img   alt="冷库实时数据" src="img/menu/bt_feedback.gif"  align="middle" />实时
							</button></div>
									</td>
	                </tr>
				    </c:forEach>
            </tbody>			
    </table>
	</logic:notEmpty>		  
			<!-- 	</td> </tr>     -->
				
				
				
				 			 	
			 <tr><td height="280"></td></tr>		
		</table>		
			<%--include file="footer.jsp" --%>
			 <jsp:include page="footer.jsp" flush="true"></jsp:include>		
	</body>
</HTML>
