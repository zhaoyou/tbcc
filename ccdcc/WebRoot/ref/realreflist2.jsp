<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>�ֿ�ʵʱ���ݲ鿴</title>
<link href="css/cangku/cssrellist.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="img/add/logo.ico" />
<script type="text/javascript">
	function golist(url,operate){
		document.getElementById('myform').action = url ;
		document.getElementById('ope').value = operate ;
		document.myform.submit();
	}
	
	
	/**
	*	��ת���ֿ�ϵͳҳ��
	**/
	function gosyslist(url,operate,projectId){
		document.getElementById('projectId').value = projectId ;
		golist(url,operate);
	}
	
	/**
	*	��ת����ͬ��¥��ҳ��
	**/
	function gofloorlist(url,operate,projectId,ftype,fno){
		document.getElementById('projectId').value = projectId ;
		document.getElementById('floorType').value = ftype ;
		document.getElementById('floorNo').value = fno ;
		golist(url,operate);
	}
	
	/**
	*	ҳ���ʼ��ʱִ�з���
	**/
	
	function init(){
			branchId =  document.getElementById("branchId").value ;
		 	pid 	 = 	document.getElementById("projectId").value ;
			ftype	 =  document.getElementById('floorType').value ;
			fno 	 =  document.getElementById('floorNo').value ;
					
			if(branchId==null || branchId=="" || pid==null || pid=="" || ftype==null || ftype=="" || fno==null || fno=="" ){
				return  ;
			}
						
		getData() ;
		window.setInterval("getData()",10000); 	//ÿ��10���ӻ�ȡһ�����ʵʱ����
	}
	
	
			//����ԭ��̬Ajax�������󣬷�������ʵʱ����
			var xmlHttpRequest = null  ;
			var branchId = null ;
			var pid = null ;
			var ftype =	null ;
			var fno =	null ;
			
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
				
				var url = "realref.do?ope=doRefFloor&projectId="+pid+"&floorType="+ftype+"&floorNo="+fno+"&branchId="+branchId+"&time="+new Date().getTime(); 
				return url ;
			}
			
			
			
			//����״̬�����ı�ʱ������
			function statusChangeHandler(){
				//��ʾ���������Ѿ��������
				if(xmlHttpRequest.readyState==4 ){
					if(xmlHttpRequest.status==200){
						var r = xmlHttpRequest.responseText ;
						document.getElementById("redDataContainer").innerHTML = "" ;
						document.getElementById("redDataContainer").innerHTML = r  ;
						document.getElementById('waitingDiv').style.display = "none" ;
				    }
			    }
			}
			
			
			
			//ÿ��10����õķ���
			function getData(){
			    createXMLHttpRequest() ;			    
				xmlHttpRequest.onreadystatechange = statusChangeHandler ;
				xmlHttpRequest.open("GET",getURL());
				xmlHttpRequest.send(null);	
			
			}
			
	
</script>
</head>
 
<body onload="init();">
<div>
  <iframe scrolling="no" src="common/header2.jsp" width=100% height=126 frameborder=0></iframe >
</div>
<form action="" id="myform" name="myform" method="post" >
	<input type="hidden" name="ope" id="ope" value=""/>
	<input type="hidden" name="projectId" id="projectId" value="${param.projectId }" />
	
	<input type="hidden" name="floorType" id="floorType" value="${param.floorType }"/>
	<input type="hidden" name="floorNo"   id="floorNo"   value="${param.floorNo }"/>	
	<input type="hidden" name="branchId"  id="branchId"  value="${param.branchId }"/>
</form>
<div id="left">
  <ul id="nav">
    <li class="tab">&nbsp;</li>
    <c:forEach  var="refPrj" items="${refPrjList}">
    	<li class="tab_a"> <a href="#"><img src="images/canku/s.gif" width="20" height="22" />${refPrj.projectName }</a> </li>
    	<li class="tab_b"><a href="javascript:gosyslist('realref.do','toRefSys','${refPrj.projectId }');"><img src="images/canku/icon_a.gif" width="22" height="17"  class="right"/>�ֿ�ϵͳ���</a></li>
    	<c:forEach var="floor" items="${floorList}">
    		<c:if test="${refPrj.projectId==floor.project}">			
    			<li class="tab_b"><a href="javascript:gofloorlist('realref.do','toRefFloor','${refPrj.projectId }',${floor.floorType },${floor.floorNo })"><img src="images/canku/icon_b.gif" width="18" height="20"  class="right"/>${floor.floorName }</a></li>
    		</c:if>
    	</c:forEach>
    	
    </c:forEach>
    <%--
    <li class="tab_a"> <a href="#"><img src="images/canku/s.gif" width="20" height="22" />�ֿ�A</a> </li>
    <li class="tab_b"><a href="#"><img src="images/canku/icon_a.gif" width="22" height="17"  class="right"/>F1��</a></li>
    <li class="tab_b"><a href="#"><img src="images/canku/icon_b.gif" width="18" height="20"  class="right"/>F2��</a></li>
    <li class="tab_b"><a href="#"><img src="images/canku/icon_b.gif" width="18" height="20"  class="right"/>ϵͳ</a></li>
    <li class="tab_a"> <a href="#"><img src="images/canku/s.gif" width="20" height="22" />�ֿ�B</a> </li>
    <li class="tab_b"><a href="#"><img src="images/canku/icon_a.gif" width="22" height="17"  class="right"/>F1��</a></li>
    <li class="tab_b"><a href="#"><img src="images/canku/icon_b.gif" width="18" height="20"  class="right"/>F2��</a></li>
    <li class="tab_b"><a href="#"><img src="images/canku/icon_b.gif" width="18" height="20"  class="right"/>ϵͳ</a></li>
     --%>
    <li class="tab_c"><a href="#">&nbsp;</a></li>
  </ul>
</div>
<div id="line"><img src="images/canku/icon2_090.gif" /></div>
<div id="right">
  <div id="top">
  <a href="#"><img src="images/chezai/icon_c.gif" width="16" height="15"  class="tb4"/><strong>λ��:��ҳ</strong>&lt;�����ڲ鿴�Ĳֿ�ʵʱ����</a>
  <span><img src="img/util/back.gif" style="cursor: pointer;" onclick="javascript:golist('pro.do','toRefList')" width="48" height="20"  class="tb3" /></span></div>
  <div id="bottom">
    <div id="center"><span>${floorName }</span></div>
   <div id="waitingDiv" align="center"><img src="img/util/process.gif" title="���ݼ�����...."/></div>
   <div id="redDataContainer" >
    </div>
    
  </div>
</div>
<div class="clear"></div>
<div>
  <iframe scrolling="no" src="common/footer2.jsp" width=100% height=43 frameborder=0></iframe >
</div>
</body>
</html>