<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>�ֿ�ϵͳʵʱ���ݲ鿴</title>
<link href="css/cangku/css.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="img/add/logo.ico" />
<script type='text/javascript' src='dwr/interface/real.js'></script>
  	    <script type='text/javascript' src='dwr/engine.js'></script>
  		<script type='text/javascript' src='dwr/util.js'></script>
<script type="text/javascript">
	
	/**
	*	�ύҳ��
	**/
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
	**	�ɿͻ��ֿ�ʵʱ����ҳ����ת������������б���ҳ�棬��ʱ��Ҫע�����
	*	��Ҫ��sbranchId ��ɵ�ǰ��branchId
	**/
	function gorefList(url,operate){
		document.getElementById('branchId').value = document.getElementById('sbranchId').value ;
		golist(url,operate);
	}
	/**
	**	ҳ���ʼ��
	**/
	function init(){
		var p  = document.getElementById('projectId').value;
		//�����ȡ�������̲�����ֱ�ӷ���
		if(p==null || p==""){
				return  ;
		}
		
		//��ȡʵʱ����
		real.getRealRefSys(p,{
			callback:result_handler ,
			errorHandler:error_handler,
			timeout:15000
		}) ;
		
		//ÿ��10s��ȡ����
		window.setInterval(function(){
		real.getRealRefSys(p,{
			callback:result_handler ,
			errorHandler:error_handler,
			timeout:15000
		}) ;
		},10000);
		
	}
	
	/**
	*	result[0] connection
	*	result[1] duandian
	*	result[2] shengguang
	**/
	function result_handler(result){
		document.getElementById('errorTip').style.display = "none" ;
		if(result==null || result.length==0){
			return  ;
		}else{
			setConnection(result[0]) ;
			setState(result[0],result[1],'duandian');
			setState(result[0],result[2],'shengguang');
		}
		
		
	}
	
	/**
	*	��������״̬
	**/
	function setConnection(con){
		if(con==2){
			document.getElementById('lianjie').innerHTML = "����" ;
		}else if (con==1){
			document.getElementById('lianjie').innerHTML = "�Ͽ�" ;
		}else{
			document.getElementById('lianjie').innerHTML = "----" ;
		}
	}
	
	/**
	*	���ñ���״̬
	**/
	function setState(con,state,ele){
	
		if(con==2){					//��������			
			if(state==2){			//����
				document.getElementById(ele).style.display = "none" ;
				document.getElementById(ele+'_normal').style.display = "inline" ;
				document.getElementById(ele+'_alarm').style.display = "none" ;
				document.getElementById(ele+'_yujing').style.display = "none" ;
			}else if(state==1){		//����
				document.getElementById(ele).style.display = "none" ;
				document.getElementById(ele+'_alarm').style.display = "inline" ;
				document.getElementById(ele+'_normal').style.display = "none" ;			
				document.getElementById(ele+'_yujing').style.display = "none" ;
			}else if (state ==0){	 //Ԥ��
				document.getElementById(ele).style.display = "none" ;
				document.getElementById(ele+'_yujing').style.display = "inline" ;
				document.getElementById(ele+'_alarm').style.display = "none" ;
				document.getElementById(ele+'_normal').style.display = "none" ;			
			}else{					//״̬������
				document.getElementById(ele).style.display = "block" ;	
				document.getElementById(ele+'_normal').style.display = "none" ;
				document.getElementById(ele+'_alarm').style.display = "none" ;
				document.getElementById(ele+'_yujing').style.display = "none" ;
			}
					
		}else {						//���ӶϿ��򲻿���
			document.getElementById(ele).style.display = "inline" ;	
			document.getElementById(ele+'_normal').style.display = "none" ;
			document.getElementById(ele+'_alarm').style.display = "none" ;
			document.getElementById(ele+'_yujing').style.display = "none" ;
		}
	}
	
	
	/**
	*		�����������
	**/
	function error_handler(){
		document.getElementById('errorTip').style.display = "inline" ;
	}
</script>
</head>
 
<body onload="init();">
<div>
  <iframe scrolling="no" src="common/header2.jsp" width=100% height=126 frameborder=0></iframe >
</div>
<form action="" id="myform" name="myform" method="post" >
	<input type="hidden" name="ope" id="ope" value=""/>
	<input type="hidden" name="projectId" id="projectId" value="${projectId }" />
	
	<input type="hidden" name="floorType" id="floorType" value=""/>
	<input type="hidden" name="floorNo"   id="floorNo"   value=""/>	
	<input type="hidden" name="branchId"  id="branchId"  value="${param.branchId }"/>
	<input type="hidden" name="sbranchId" id="sbranchId" value="${param.sbranchId }"/>
</form>
<div id="left">
  <ul id="nav">
    <li class="tab">&nbsp;</li>
    <c:forEach  var="refPrj" items="${refPrjList}">
    	<li class="tab_a"> <a href="#"><img src="images/canku/s.gif" width="20" height="22" />${refPrj.projectName }</a> </li>
    	
    	<li class="tab_b"><a href="javascript:gosyslist('realref.do','toRefSysCus','${refPrj.projectId }');"><img src="images/canku/icon_a.gif" width="22" height="17"  class="right"/>�ֿ�ϵͳ���</a></li>
    	<c:forEach var="floor" items="${floorList}">
    		<c:if test="${refPrj.projectId==floor.project}">			
    			<li class="tab_b"><a href="javascript:gofloorlist('realref.do','toRefFloorCus','${refPrj.projectId }',${floor.floorType },${floor.floorNo })"><img src="images/canku/icon_b.gif" width="18" height="20"  class="right"/>${floor.floorName }</a></li>
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
  <a href="#"><img src="images/chezai/icon_c.gif" width="16" height="15"  class="tb4"/><strong>λ��:��ҳ</strong>&lt;�鿴�ֿ�ϵͳʵʱ����</a>
  <span><img src="img/util/back.gif" style="cursor: pointer;" onclick="javascript:gorefList('pro.do','toRefList')" width="48" height="20"  class="tb3" /></span></div>
  <div id="bottom"> 
  	<div id="errorTip" style="color: red;font-size: 13px;display: none">�޷���ȡ���ֿ�ϵͳʵʱ����,���Ժ�...</div>
    <div id="center"><span>${projectName }ϵͳ���</span></div> 
    <table id="tb" width="80%" border="0" cellspacing="0" cellpadding="0"> 
      <tr id="tb1"> 
        <td width="297">���</td> 
        <td width="314">����</td> 
      </tr> 
      <tr> 
        <td>����״̬</td> 
        <td><span id="lianjie">----</span></td> 
      </tr> 
      <tr class="altrow"> 
        <td>�ϵ籨��</td> 
        <td align="center"><span id="duandian">----</span><img id="duandian_normal" title="����" src="img/menu/win/blue.gif" style="display: none"></img><img id="duandian_yujing" title="Ԥ��" src="img/menu/win/orange.gif" style="display: none"></img><img id="duandian_alarm" title="����" src="img/menu/win/red.gif" style="display: none"></img></td> 
      </tr> 
      <tr> 
        <td>���ⱨ��</td> 
        <td align="center"><span id="shengguang">----</span><img id="shengguang_normal" title="����" src="img/menu/win/blue.gif" style="display: none"></img><img id="shengguang_yujing" title="Ԥ��" src="img/menu/win/orange.gif" style="display: none"></img><img id="shengguang_alarm" title="����"  src="img/menu/win/red.gif" style="display: none"></img></td> 
      </tr> 
    </table> 
  </div> 
</div> 
<div class="clear"></div> 
<div> 
  <iframe scrolling="no" src="common/footer2.jsp" width=100% height=43 frameborder=0></iframe> 
</div> 
</body> 
</html> 