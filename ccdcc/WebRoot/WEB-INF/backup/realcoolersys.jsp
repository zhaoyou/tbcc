<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ϵͳʵʱ���ݲ鿴</title>
<link rel="Shortcut Icon" href="img/add/logo.ico" />
<link href="css/Refri/Refri.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="script/titleTime.js"></script>
		
		 <script type='text/javascript' src='dwr/interface/realcool.js'></script>
  	    <script type='text/javascript' src='dwr/engine.js'></script>
  		<script type='text/javascript' src='dwr/util.js'></script>
<style type="text/css">

.datalist{
	border:1px solid #BDDEF7;
	border-collapse:collapse;
	background-color:#FFFFFF; 
	font-size:12px;
}
.datalist2 {
	border:1px solid #c6e2f7;	
	font-family:Arial;
	border-collapse:collapse;	
	background-color:#FFFFFF; 
	font-size:12px;
}
.datalist3 {
	border:1px solid #BDDEF7;
	border-collapse:collapse;	
	background-color:#FFFFFF;
	font-size:12px;
	text-align: left;
}
.STYLE1 {
	font-size: 12px;
	color: #2b7dbb;
	font-weight: bold;
}
.STYLE2 {color: #454343}
</style>
	<script type="text/javascript">

		
		/**
		*	��ת�Ļ���ҳ��
		**/
		function goCompressorSet(id){
			document.getElementById('mysetid').value = id ;
			document.myform.submit();
		}
		
		/**
		*	ҳ���ʼ��ʱ����
		**/
		function init(){
			getCoolerSystem() ; 
			window.setInterval("getCoolerSystem()",10000);
		}
		
			/**
			*	��ȡ����ϵͳʵʱ����
			*/
			function getCoolerSystem(){
				var parrayobj = document.getElementById("coolerprojectids").value ;
				var goquery = document.getElementById('if_query').value ;
					if(goquery ==0){
						return  ;
					}			
					var v = parrayobj.split(",") ;	
					realcool.getCoolerSysRealData(v,{
					callback:doCoolerSystem,
					timeout:15000,
					errorHandler:doError
				});
			}
			
			/**
			*	��������ϵͳʵʱ����
			*/
			function doCoolerSystem(data){
				if(data!=null){
					document.getElementById("fadianji").innerHTML = getDisplayState(data.dynamoState);
					document.getElementById("fadianjiyujin").innerHTML = getDisplayAlarm(data.dynamoAlarm);
					document.getElementById("xitongduandian").innerHTML = getDisplayState(data.sysoutageState);
					document.getElementById("xitongduandianyujin").innerHTML = getDisplayAlarm(data.sysoutageAlarm);	
				}else{
					document.getElementById("fadianji").innerHTML = "----";
					document.getElementById("fadianjiyujin").innerHTML = "----";
					document.getElementById("xitongduandian").innerHTML = "----";
					document.getElementById("xitongduandianyujin").innerHTML = "----";
				}
				document.getElementById('errorTip').style.display = "none" ;	
			}
			
			/**
			**	��ȡϵͳʵʱ���ݴ��������
			**/
			function doError(){
				document.getElementById('errorTip').style.display = "block" ;
			}
		
		/**
			*	����״̬��ʾ״̬����
			*/
			function getDisplayState(state){
				if(state==2)
					return "����" ;
				else if(state==1)
					return "�ر�" ;
				else 
					return "----" ;
			}
			
			/**
			*	���ݱ���״̬��ʾ�����ı�
			*/
			function getDisplayAlarm(alarm){
				if(alarm==2)
					return "<img src='img/menu/win/blue.gif'>" ;
				else if (alarm==0)
					return "<img src='img/menu/win/red.gif'>" ;
				else
					return "----" ;
			}
			
			/**
			*		����
			**/
		
			function goback(){
				document.myform.action = "branch.do?ope=toListByBranch" ;
				document.myform.submit() ;
			}

		
	</script>
</head>
<body onload="init();">
<div>
     <iframe scrolling="no" src="common/header2.jsp" width=100% height=126 frameborder=0></iframe >  
		<%-- <jsp:include page="common/header2.jsp" flush="true"></jsp:include> --%>
</div>
<form name="myform" action="realcool.do?ope=toRealCoolSingleMul" method="post">

	<input type="hidden" name="coolerprojectids" id="coolerprojectids" value="${refprojectids }"/>
	<input type="hidden" name="branchId" id="branchId" value="${param.branchId }"/>
	<input type="hidden" name="mysetid" id="mysetid" value=""/>
<div id="left" style="height: 450px">
  <ul id="nav">
    <li class="tab"><a href="#"></a></li>
    <li class="tab_a"> <a href="#"><img src="images/Refri/icon_e.gif" width="20" height="20" />ϵͳ </a> </li>
   
    <c:forEach items="${coollist}" var="comset">
    		<li class="tab_b"  ><a href="javascript:goCompressorSet('${comset.id }');"><img src="images/Refri/jizu.gif" width="20" height="20" />${comset.csName }</a></li>
    </c:forEach>
    
    <li class="tab_c"><a href="#">gfsdgs</a></li>
  </ul>
</div>
<div id="line" style="height: 450px"><img src="images/index/icon2_090.gif" /></div>
<div id="right" style="background-color: white;height: 450px"><table width="100%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td><table width="100%" align="left" class="datalist">
    <tr>
      <td width="767" height="10" colspan="4" background="images/Refri/menu_bga.gif">
      
     <div id="top"><img src="images/Refri/icon_c.gif" width="16" height="15" / class="tb4"><strong>λ��:��ҳ</strong>&lt;�鿴����ϵͳʵʱ����<span>
	 <img src="images/Refri/back.gif" style="cursor: pointer;" onclick="goback();" width="48" height="20"  class="tb3"></div>
	 
      </td>
    </tr>
  </table></td>
  </tr>
  <tr>
    <td><input type="hidden" id="if_query" name="if_query" value="${coollist==null?0:1 }"/><span id="noInfo" style="color: red;display:${coollist==null?'block':'none' }">û��������Ӧ�������豸...</span><table width="100%" class="datalist" style="display: ${ coollist==null?'none':'block'}">
      
      <tr class="altrows">
        <td width="300" colspan="4" align="left">
        	<div align="left"><img src="images/Refri/button_e.gif"/><span id="errorTip" style="display: none;color: red">��ȡʵʱ����ʧ��,���Ժ�...</span></div>	
        </td>    
      </tr>
      <tr>
        <td colspan="4"><table width="280" align="left" class="datalist2">
          <tr>
            <th width="25" class="datalist" scope="col">&nbsp;</th>
            <th width="89" scope="col">ϵͳ����</th>
            <th width="58" scope="col">���</th>
            <th width="88" scope="col">����</th>
          </tr>
          <tr>
            <td><img src="images/Refri/quexiang.png"></img></td>
            <td>ϵͳ�ϵ�</td>
            <td><div id="fadianji">----</div>							
            <td><div id="fadianjiyujin">----</div></td>
          </tr>
          <tr class="altrow">
            <td><img src="images/Refri/�����.png"></img></td>
            <td>���������</td>
            <td><div id="xitongduandian">----</div></td>
            <td><div id="xitongduandianyujin">----</div></td>
          </tr>     
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td></td>
  </tr>
  
</table>


</div><div class="clear"></div>
</form>
<div><iframe scrolling="no" src="common/footer2.jsp" width=100% height=43 frameborder=0></iframe ></div>
</body>
</html>
