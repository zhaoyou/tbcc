<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>���ع����б�</title>
<link rel="Shortcut Icon" href="img/add/logo.ico" />
<link href="css/chezai/chezai.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function golist(url,operate){		
		document.getElementById('myform').action = url ;
		document.getElementById('ope').value= operate ;
		document.myform.submit() ;
	}
</script>
</head>
<body>
<div>

<iframe scrolling="no" src="common/header2.jsp" width=100% height=126 frameborder=0></iframe > </div>
<form name="myform" id="myform" method="post" action="">
	<input type="hidden" name="branchId" id="branchId" value="${param.branchId }"/>
	<input type="hidden" name="ope" id="ope" value=""/>
</form>
<div id="left">
  <ul id="nav">

    <li class="tab">&nbsp;</li>
    <li class="tab_a"> <a href="javascript:golist('branch.do','toMain');"><img src="images/chezai/s.gif" width="20" height="22" />��ҳ</a> </li>
    <li class="tab_b"><a href="javascript:golist('pro.do','toRefList');"><img src="images/chezai/icon_a.gif" width="22" height="17" />�ֿ⹤��</a></li>
    <li class="tab_b"><a href="javascript:golist('pro.do','toCarList')"><img src="images/chezai/icon_b.gif" width="18" height="20" />���ع���</a></li>
    <%-- 
    <li class="tab_b"><a href="javascript:window.alert('�ݲ��ṩС�������!');"><img src="images/chezai/icon.gif" width="19" height="20" />С���㹤��</a></li>
    --%>
    <li class="tab_c"><a href="#">&nbsp;</a></li>

  </ul>
</div>
<div id="line"><img src="images/index/icon2_090.gif" /></div>
<div id="right"><table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td><table width="100%" align="left" class="datalist">
    <tr>
      <td width="767" height="10" colspan="4" background="images/Refri/menu_bga.gif"><div align="right"><img src="images/Refri/icon_c.gif" width="16" height="15"  class="pic"/><span ><strong>λ��:��ҳ&lt;����������ҵ���ǣ�</strong>�鿴���г��ع�����Ϣ</span></div></td>
    </tr>

    

    
  </table></td>
  </tr>
  
  <tr>
    <td>
    <logic:notEmpty name="cList">
    <table width="100%" class="datalist">
      <tr class="altrows">
        <td width="444" colspan="4" align="left">
        <div align="left" style="display:inline;">
        <img src="images/chezai/bt_feedback.gif" alt="ʵʱ����" style="cursor: pointer" onclick="javascript:${fn:contains(power,"����ʵʱ����")==true?"golist('realcar.do','toRealData');":"alert('��δ��ͨ����ʵʱ���ݹ���');"}" />
        </div>
        <div align="left" style="display:inline; " >
        <img src="images/chezai/bt_detail.gif" alt="��ʷ����"  style="cursor: pointer" onclick="javascript:${fn:contains(power,"������ʷ����")==true?"golist('hiscar.do','toHisCarStartUp');":"alert('��δ��ͨ������ʷ���ݹ���');"}" />
        </div>
        <div align="left"></div>
        <div align="left"></div> 
        <div align="left"></div>         </td>

      </tr>
      <tr>
        <td colspan="4"><table width="75%" align="left" class="datalist2">
          <tr>
            <th width="28" class="datalist4" scope="col">&nbsp;</th>
            <th width="120" scope="col">��������</th>
            <th width="88" scope="col">���̴���</th>
            <th width="335" scope="col">��ע</th>
          </tr>
          <c:forEach var="p" items="${cList}" varStatus="i">
          	<tr>
	            <td>${i.count }</td>
	            <td>${p.projectName }</td>
	            <td>${p.projectCode }</td>
	            <td>${p.projectNote }</td>
         	 </tr>
          </c:forEach>
          

         
        </table></td>
      </tr>
    </table>
    </logic:notEmpty>
    <logic:empty name="cList">
    	<div style="color: red;font-size: 14px" align="center">û��������Ӧ���ع���!</div>
    </logic:empty>
    </td>
  </tr>
  
  

</table>


</div><div class="clear"></div>
<div><iframe scrolling="no" src="common/footer2.jsp" width=100% height=43 frameborder=0></iframe ></div>
</body>
</html>
