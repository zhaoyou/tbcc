<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>��⹤���б�</title>
		<link rel="Shortcut Icon" href="img/add/logo.ico" />
		<style type="text/css">
.datalist {
	border: 1px solid #BDDEF7;
	border-collapse: collapse;
	background-color: #FFFFFF;
	font-size: 12px;
}

.datalist2 {
	border: 1px solid #c6e2f7;
	font-family: Arial;
	border-collapse: collapse;
	background-color: #FFFFFF;
	font-size: 12px;
}

.datalist3 {
	border: 1px solid #BDDEF7;
	border-collapse: collapse;
	background-color: #FFFFFF;
	font-size: 12px;
	text-align: left;
}

.STYLE1 {
	font-size: 12px;
	color: #2b7dbb;
	font-weight: bold;
}

.STYLE2 {
	color: #454343
}
</style>
		<script type="text/javascript">
	function golist(url,operate){		
		document.getElementById('myform').action = url ;
		document.getElementById('ope').value= operate ;
		document.myform.submit() ;
	}
	
	function gorefcustomer(cbranchId){
			//�ѵ�ǰ�ͻ���branchid����������ķ�֧��ʾ
			document.customerform.branchId.value = cbranchId ;
			document.customerform.submit() ;
	}
	
	

</script>
		<link href="css/cangku/cangku.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div>

			<iframe scrolling="no" src="common/header2.jsp" width=100% height=126
				frameborder=0></iframe>
		</div>

		<form name="myform" id="myform" method="post" action="">
			<input type="hidden" name="branchId" id="branchId"	value="${param.branchId}" />
			<input type="hidden" name="ope" id="ope" value="" />
		</form>
		
		
		<div id="left">
			<ul id="nav">
				<li class="tab">
					<a href="#"></a>
				</li>
				<li class="tab_a">
					<a href="javascript:golist('branch.do','toMain');"><img
							src="images/canku/s.gif" width="20" height="22" />��ҳ</a>
				</li>
				<li class="tab_b">
					<a href="javascript:golist('pro.do','toRefList');"><img
							src="images/canku/icon_a.gif" width="22" height="17" />�ֿ⹤��</a>
				</li>
				<li class="tab_b">
					<a href="javascript:golist('pro.do','toCarList');"><img
							src="images/canku/icon_b.gif" width="18" height="20" />���ع���</a>
				</li>

				<%--
				<li class="tab_b">
					<a href="javascript:window.alert('�ݲ��ṩС�������!');"><img
							src="images/canku/icon.gif" width="19" height="20" />С���㹤��</a>
				</li>
				 --%>
				
				<li class="tab_c">
					<a href="#">&nbsp;</a>
				</li>
			</ul>
		</div>
		<div id="line">
			<img src="images/index/icon2_090.gif" />
		</div>
		<div id="right">
<div id="top">
<a href="#"><img src="images/canku/icon_c.gif" width="16" height="15"  class="tb4"/><strong>λ��:��ҳ</strong>&lt;�鿴���вֿ⹤����Ϣ</a><span></div>

	<logic:notEmpty name="rList">
			<div id="center">
				<%-- <a href="javascript:golist('realref.do','toDefaultRefSys');"><img src="images/canku/bt_feedback.gif" style="display:${fn:contains(power,"���ʵʱ����")==true?'inline':'none'}" /></a>
				<a href="javascript:golist('hisref.do','toHisRefList');"><img src="images/canku/bt_detail.gif" style="display:${fn:contains(power,"�����ʷ����")==true?'inline':'none'} " /></a>
				<a href="javascript:golist('hisref.do','toCurve');"><img src="images/canku/bt_orders.gif" style="display:${fn:contains(power,"�����ʷ����")==true?'inline':'none'} " /></a>
				<a href="javascript:golist('realref.do','toRealFloorfuck');"><img src="images/canku/bt_acti.gif" style="display:${fn:contains(power,"���ʵʱͼ��")==true?'inline':'none'} " /></a>
				<a href="javascript:golist('realcool.do','toRealCoolSys');"><img src="images/canku/bt_confirm.gif"  style="display:${fn:contains(power,"�������ʵʱ")==true?'inline':'none'} " /></a>--%>
				
				<a href="javascript:${fn:contains(power,"���ʵʱ����")==true?"golist('realref.do','toDefaultRefSys');":"alert('��δ��ͨ���ʵʱ���ݹ���');"}"><img src="images/canku/bt_feedback.gif" style="display:inline;" /></a>
				<a href="javascript:${fn:contains(power,"�����ʷ����")==true?"golist('hisref.do','toHisRefList');":"alert('��δ��ͨ�����ʷ���ݹ���');"}"><img src="images/canku/bt_detail.gif" style="display:inline; " /></a>
				<a href="javascript:${fn:contains(power,"�����ʷ����")==true?"golist('hisref.do','toCurve');":"alert('��δ��ͨ�����ʷ���߹���');"}"><img src="images/canku/bt_orders.gif" style="display:inline; " /></a>
				<a href="javascript:${fn:contains(power,"���ʵʱͼ��")==true?"golist('realref.do','toRealFloorfuck');":"alert('��δ��ͨ���ʵʱͼ�㹦��');"}"><img src="images/canku/bt_acti.gif" style="display:inline; " /></a>
				<a href="javascript:${fn:contains(power,"�������ʵʱ")==true?"golist('realcool.do','toRealCoolSys');":"alert('��δ��ͨ�������ʵʱ����');"}"><img src="images/canku/bt_confirm.gif"  style="display:inline;" /></a>
				
			</div>
	      <div id="bottom">
	        <table id="tb" width="80%" border="0" cellspacing="0" cellpadding="0">
	          <tr id="tb1">
	            <td width="6%">&nbsp;</td>
	            <td width="33%">��������</td>
	            <td width="37%">���̴���</td>
	            <td width="24%">��ע</td>
	          </tr>
	        	 <c:forEach var="p" items="${rList}" varStatus="i">
							<tr>
								<td>	${i.count }	</td>
								<td>	${p.projectName }</td>
								<td>	${p.projectCode }</td>
								<td>	${p.projectNote }</td>
							</tr>
				</c:forEach>
	        </table>      
	  </div>
	</logic:notEmpty>

				<logic:empty name="rList">
							<div style="color: red; font-size: 14px" align="center">
								û��������Ӧ�ֿ⹤��!
							</div>
				</logic:empty>
	<logic:notEmpty name="branchList">
		<form name="customerform" id="customerform" action="realref.do?ope=toDefaultRefSysCus" method="post">
		<input type="hidden" name="branchId" id="branchId" value=""/>	
		<input type="hidden" name="sbranchId" id="sbranchId" value="${param.branchId}" />
		 <div style="font-size: 14px;color:olive" >�ͻ���ص����ϵͳ</div>	 
		 <div id="bottom">
	        <table id="tb" width="60%" border="0" cellspacing="0" cellpadding="0">
	          <tr id="tb1">
	            <td width="6%">���</td>
	            <td width="33%">�ͻ�����</td>
	            <td width="27%">����</td>
	          </tr>
	        	<c:forEach var="b" items="${branchList}" varStatus="index">
							<tr>
								<td>	${index.count }	</td>
								<td>	${b.branchName }</td>
								<td >
									<span style="display:${fn:contains(power,"�ͻ����ʵʱ")==true?'inline':'none'} "><a href="javascript:gorefcustomer(${ b.branchId});"><img src="images/canku/bt_feedback.gif" /></a>
									</span>
									</td>
							</tr>
				</c:forEach>
	        </table>      
	  </div>
	  </form>
	</logic:notEmpty>					
						
</div>
<div class="clear"></div>
		<div>
			<iframe scrolling="no" src="common/footer2.jsp" width=100% height=43
				frameborder=0></iframe>
		</div>
	</body>
</html>
