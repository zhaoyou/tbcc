<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="common/taglib.jsp" %>

<html>
	<head>
		<title>������Ϣ�б�</title>
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
						//����������ʾ��ͬ��ϵͳ�Ĳ�	
				 function operateDiv(divid){    
	       		     if(document.getElementById(divid).style.display =='none')
	            		 document.getElementById(divid).style.display = 'block' ;
	        		else
	         		    document.getElementById(divid).style.display = 'none' ;
   				 }
   				 
   				 /**
   				 *   ����JavaScript����,�����׶β���Ҫ���á�����ʱ�����ø÷���
   				 **/
   				 function errorHandler(){
   				 	return true ;
   				 }	 
   				 window.onerror = errorHandler;
   				 
   				 /**
   				 *	��ת����������ʵʱ����ҳ��
   				 */
   				 function goRealCool(flag){
   				 	if(flag==true){
	   				 	var b = document.getElementById("branchId").value ;
	   				 	//ԭ�ȵ�����ҳ��
	   				 	//goPowerURL("realcool.do?ope=toRealCool&branchId="+b,flag) ;
	   				 	//��ת���µ�����ҳ��
	   				 	goPowerURL("realcool.do?ope=toRealCoolSys&branchId="+b,flag) ;
   				 	}
   				 }
   				 
   				 /**
   				 *	ʵ��Ȩ�޵���ת
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
		<!-- ������û��ķ�֧ID -->
		<input type="hidden" id="branchId"  name="branchId" value="${param.branchId}"/>
		<table width="990" border="0" align="center" cellpadding="0" bgcolor="#FFFFF9"
			cellspacing="0" bordercolorlight="#CCCCCC" bordercolor="#FFFFFF" class="PageBody"
			bordercolordark="#FFFFFF" >
			<tr>
				<td width="990" colspan="3" valign="top"
					background="img/stock_index_08.gif">
						<div id="main" style="width:990px; height:100%; float:left" >
								<div class="page_title">����������ҵ���ǣ�<img src="img/add/club.JPG" alt=">>"><font size="2px">&nbsp;�鿴���й�����Ϣ</font></div>
								<div class="button_bar"></div>
							<div>
				</td>
			</tr>
			
			<tr> 
				<td>
					<table border=0 cellpadding=0 cellspacing=0 width="990" align="center" class="PageBody">
      <tr align="center">
        <td width="170"> ���</td>
        <td width="220" > �������� </td>
        <td width="220" > ���̴��� </td>
        <td width="380" > ���� </td>
      </tr>
    </table>
    <hr>
	<table BORDER=0 CELLPADDING=0 CELLSPACING=0 WIDTH="990" align="center">
            <tr >
               <td width="170">
              <div align="center" ><input type="button" class="common_button" value="�ֿ⹤��"/></div></td>
			  <td width="220" ><div align="center"></div></td>
			  <td width="220" ><div align="center"></div></td>
                <td width="380" >
                <div align="center">
				<logic:notEmpty name="rList">
					<span style="display:${fn:contains(power,"���ʵʱ����")==true?'inline':'none'} "  onclick="javascript:goPowerURL('realref.do?ope=toRealList&branchId=${param.branchId }',${fn:contains(power,"���ʵʱ����")});">
					<button type="button" title="�ֿ�ʵʱ����"  class="common_button2" >
						<img  alt="�ֿ�ʵʱ����" src="img/menu/bt_feedback.gif" align="top" />ʵʱ </button>&nbsp;	 
					</span>
					<span style="display:${fn:contains(power,"�����ʷ����")==true?'inline':'none'} " onclick="javascript:goPowerURL('hisref.do?ope=toHisRefList&branchId=${param.branchId }',${fn:contains(power,"�����ʷ����")})">
					<button type="button" title="�ֿ���ʷ����" class="common_button2"  >
						<img   alt="�ֿ���ʷ����"  align="top" src="img/menu/bt_detail.gif"   />��ʷ</button>&nbsp;
					</span>
					<span  style="display:${fn:contains(power,"�����ʷ����")==true?'inline':'none'} " onclick="javascript:goPowerURL('ccdcc/hisref.html?branchId=${param.branchId }',${fn:contains(power,"�����ʷ����")})">			
					<button type="button" title="�ֿ���ʷ����" class="common_button2" >
						<img  alt="�ֿ���ʷ����"  src="img/menu/bt_orders.gif"  align="top"  />����</button>&nbsp;	 
					</span>
					<span  style="display:${fn:contains(power,"���ʵʱͼ��")==true?'inline':'none'} " onclick="javascript:goPowerURL('realref.do?ope=toRealFloor&branchId=${param.branchId }',${fn:contains(power,"���ʵʱͼ��")})">	
					<button type="button" title="�ֿ�ͼ��ʵʱ����" class="common_button2" >
						<img  src="img/menu/bt_acti.gif"  alt="�ֿ�ͼ��ʵʱ����"  align="top"  />ͼ��</button>&nbsp;	
					</span>	
					<span  style="display:${fn:contains(power,"�������ʵʱ")==true?'inline':'none'} " onclick="javascript:goRealCool(${fn:contains(power,"�������ʵʱ")})">							
					<button type="button" title="����ʵʱ����" class="common_button2" >
						<img  alt="����ʵʱ����" src="img/menu/bt_confirm.gif" align="top"  />����</button>				            
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
                    <div align="center" style="color: blue" >û�����òֿ⹤��</div>
                    </td>      
                </tr>
                </logic:empty>
            </tbody>
    </table>
    <hr>
		<table BORDER=0 CELLPADDING=0 CELLSPACING=0 WIDTH="990" align="center">
            <tr >
               <td width="170" >
               <div align="center"><input type="button" class="common_button" value="���ع���"/></div>
               </td>
			  <td width="220" ><div align="center"></div></td>
			  <td width="220" ><div align="center"></div></td>
              <td width="380" >
              <div align="center">
             <logic:notEmpty name="cList">
             	<span  style="display:${fn:contains(power,"����ʵʱ����")==true?'inline':'none'} " onclick="javascript:goPowerURL('realcar.do?ope=toRealData&branchId=${param.branchId }',${fn:contains(power,"����ʵʱ����")})"  style="cursor:pointer">
			  	<button type="button" title="����ʵʱ����" class="common_button2">    			
			      	<img  alt=����ʵʱ���� src="img/menu/bt_feedback.gif" align="top" />ʵʱ	</button>&nbsp;
				</span>
				<span style="display:${fn:contains(power,"������ʷ����")==true?'inline':'none'} " onclick="javascript:goPowerURL('hiscar.do?ope=toHisCarStartUp&branchId=${param.branchId}',${fn:contains(power,"������ʷ����")})" style="cursor:pointer">
				<button type="button" title="������ʷ��ͣ" class="common_button2" >
					<img  alt="������ʷ��ͣ"  src="img/menu/bt_detail.gif"  align="top"/>��ʷ </button>	&nbsp;		 
				</span>
				<span  style="display:none" onclick="javascript:goPowerURL('carparam.do?ope=toParamConfig&branchId=${param.branchId}',${fn:contains(power,"����")})">
				<button type="button" title="���ز�������" class="common_button2" >
					<img alt="���ز�������" src="img/menu/mbi_042.gif" align="top"/>����
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
                    <div align="center" style="color: blue" >û�����ó��ع���</div></td>
                </tr>
                </logic:empty>
                
            </tbody>
    </table>
    <hr>
        <table BORDER=0 CELLPADDING=0 CELLSPACING=0 WIDTH="990" align="center">
            <tr>
               <td width="170"  >
              <div align="center"><input type="button" class="common_button" value="С���㹤��"/>  </div></td>
				<td width="220"  ><div align="center"></div></td>
			  <td width="220"  ><div align="center"></div></td>
                <td width="380" >
              <div align="center">
              
              <logic:notEmpty name="bList" scope="request">
			  	<button type="button" class="common_button2" title="С����ʵʱ����" style="display:${fn:contains(power,"С����ʵʱ����") } " onclick="javascript:goPowerURL('realbox.do?ope=toRealData&branchId=${param.branchId }',${fn:contains(power,"С����ʵʱ����") })" style="cursor:pointer">
						<img   alt="С����ʵʱ����" src="img/menu/bt_feedback.gif"  align="middle" />ʵʱ</button>&nbsp;
				<button type="button" class="common_button2" title="С������ʷ��ͣ" style="display:${fn:contains(power,"С������ʷ����") } " onclick="javascript:goPowerURL('hisbox.do?ope=toHisBoxStart&branchId=${param.branchId}',${fn:contains(power,"С������ʷ����")})" style="cursor:pointer">
						 <img  alt="С������ʷ��ͣ"  src="img/menu/bt_detail.gif"  align="middle"/>��ʷ</button>				
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
                    <div align="center" style="color: blue">û������С���㹤��  </div></td>
                </tr>
                </logic:empty>             
            </tbody>		
    </table>
	
	<logic:notEmpty name="branchList">
	<hr>
	 <table BORDER=0 CELLPADDING=0 CELLSPACING=0 WIDTH="990" align="center">
            <tr>
               <td width="169" >
              <div align="center"><input type="button" class="common_button" value="�ͻ����ϵͳ"/></div></td>
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
	                      <button type="button" title="���ʵʱ����" style="display:${fn:contains(power,"�ͻ����ʵʱ")==true?'inline':'none'} " class="common_button2" onclick="javascript:goPowerURL('realref.do?ope=toCustomerReal&branchId=${branch.branchId }&sbranchId=${param.branchId }',${fn:contains(power,"�ͻ����ʵʱ")})">
								<img   alt="���ʵʱ����" src="img/menu/bt_feedback.gif"  align="middle" />ʵʱ
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
