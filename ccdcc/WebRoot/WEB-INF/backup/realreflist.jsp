<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="common/taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>���ʵʱ���ݲ鿴</title>
    
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
		 <link rel="Shortcut Icon" href="img/add/logo.ico" >
		 
		<script src="script/common.js"></script>	
		<script type="text/javascript" src="script/titleTime.js"></script>
		
		<script type="text/javascript">
			//����ԭ��̬Ajax�������󣬷���������ʷ����
			var xmlHttpRequest = null  ;
			
			
			//����xmlhttprequest  ����
			function createXMLHttpRequest(){
				 if (window.XMLHttpRequest) { 
     			   		xmlHttpRequest = new XMLHttpRequest();  // Mozilla��Firefox��Safari 
  				  }else if (window.ActiveXObject) { 
      				  xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP"); // Internet Explorer
  				  }		
  				  	
			}
			
			//��ȡ�����·��
			function getURL(){
				var branchId = document.getElementById("branchId").value ;
				var pid = document.getElementById("myrid").value ;
				var url = "realref.do?ope=doRealList&projectId="+pid+"&branchId="+branchId+"&time="+new Date().getTime(); ;
				return url ;
			}
			
			
			
			//����״̬�����ı�ʱ������
			function statusChangeHandler(){
				//��ʾ���������Ѿ��������
				if(xmlHttpRequest.readyState==4 ){
					if(xmlHttpRequest.status==200){
						var r = xmlHttpRequest.responseText ;
						document.getElementById("container").innerHTML = "" ;
						document.getElementById("container").innerHTML = r  ;
				    }
			    }
			}
			
			
			
			//ÿ��10����õķ���
			function getData(){
			    createXMLHttpRequest() ;			    
				xmlHttpRequest.onreadystatechange = statusChangeHandler ;
				xmlHttpRequest.open("GET",getURL());
				xmlHttpRequest.send(null);	
				window.setTimeout("getData()",10000); //ÿ��10����ˢ��һ��ҳ��		
				
			}
			//window.setInterval("getData()",10000);
		</script>
  </head>
  
 	<jsp:include page="header.jsp" flush="true"></jsp:include>
 	<body onload="showtime(),getData();" bgcolor="#FFFFFF" leftmargin="0" topmargin="4" marginwidth="0" marginheight="0">
 	<form action="" method="post">
 	<input type="hidden" name="branchId" id="branchId" value="${param.branchId}">
		<table width="990" border="0" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#CCCCCC" bordercolor="#FFFFFF"
			bordercolordark="#FFFFFF">
			<tr>
				<td width="990" height="550" colspan="3" valign="top"
					background="img/stock_index_08.gif">
						<div id="main" style="width:990px; height:100%; float:left" >
						<div class="page_title">����������ҵ���ǣ�<img src="img/add/club.JPG" alt=">>"><font size="2px">&nbsp;�ֿ�ʵʱ���ݲ鿴</font>&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;<input type="button" value="�� ��"  class="common_button"
						 onclick="javascript:window.location.href='branch.do?ope=toListByBranch&branchId=${param.branchId }'"/>
						</div>
						<div>
						<!--  -->
						��ѡ��ֿ⹤��:
							<select id="myrid" style="width: 120px;text-align: center">	
								<c:forEach var="proj" items="${refPrjList }">
									<option value="${proj.projectId}" 
									${param.myrid eq proj.projectId?"selected='selected'":" " }>${proj.projectName }</option>
								</c:forEach>							
							</select>
							<hr>
						</div>
						
						<div id="container" align="center"></div>
						
						</div> 
				</td>
			</tr>
			</table>
			</form>
  </body>
  <jsp:include page="footer.jsp" flush="true"></jsp:include>
</html>
