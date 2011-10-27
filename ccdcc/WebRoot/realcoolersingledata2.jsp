<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>��������ʵʱ���ݲ鿴...</title>
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
			
			var id  = null ;
			
			//����������������ȡʵʱ���ݵĳɹ����
			var single_flag = true  ;
			var compress_flag = true ;
			var comdenser_flag = true ;
			var aircooler_flag = true ;
			
			
			/**
			*	�ж����е�״̬�Ƿ���ȷ
			*/
			
			function isAllOK(){
				
				 if( single_flag && comdenser_flag && compress_flag && aircooler_flag)
						document.getElementById("net").style.display = "none" ;
			}
			
			/**
			*	ҳ���ʼ������
			*/
			function pageInit(){
				id = document.getElementById("mysetid").value ;
				
				if(id==null || id =="")
					return ;
					
				getSingleData() ;
				getCompressorData() ;
				getCondenserData();
				getAirCoolerData();

				window.setInterval("getSingleData()",10000);
				window.setInterval("getCompressorData()",10000);
				window.setInterval("getCondenserData()", 10000);
				window.setInterval("getAirCoolerData()", 10000);
			}
			
			/**
			*	���������
			*/
			function doError(str){				
				document.getElementById("net").style.display = "block" ;
				 single_flag = false  ;
			     compress_flag = false ;
				 comdenser_flag = false ;
				 aircooler_flag = false ;
				
			}
			
			
			
			
			
			
			
			/**
			*	��ȡ���������ʵʱ����
			*/
			function getSingleData(){
				realcool.getSingleData(id,{
					callback:doSingleData,
					timeout:15000,
					errorHandler:doError
				});
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
			*	�������������ʵʱ����
			*/
			function doSingleData(data){
				if(data!=null){
					document.getElementById("duandian").innerHTML = getDisplayState(data.outageState);
					document.getElementById("duandianyujin").innerHTML = getDisplayAlarm(data.outageAlarm);
					document.getElementById("guozai").innerHTML = getDisplayState(data.overloadState);
					document.getElementById("guozaiyujin").innerHTML = getDisplayAlarm(data.overloadAlarm);
					document.getElementById("guzhang").innerHTML = getDisplayState(data.troubleState);
					document.getElementById("guzhangyujin").innerHTML = getDisplayAlarm(data.troubleAlarm);
				}else{
					document.getElementById("duandian").innerHTML = 		"----";
					document.getElementById("duandianyujin").innerHTML = 	"----";
					document.getElementById("guozai").innerHTML = 			"----";
					document.getElementById("guozaiyujin").innerHTML = 		"----";
					document.getElementById("guzhang").innerHTML = 			"----";
					document.getElementById("guzhangyujin").innerHTML =		"----";
				}
				
				//��ǰ�������
				single_flag = true ;
				
				//�ж����������Ƿ����
				isAllOK() ;
			}
			
			
			
			/**
			*	��ȡѹ������ʵʱ����
			*/
			function getCompressorData(){
				var ids = document.getElementById("compressorids").value ;
				if(ids==null || ids=="")
					return  ;
				realcool.getCompressorDataByIds(ids,{
					callback:doCompressorData ,
					timeout:15000,
					errorHandler:doError
				}) ;
			}
			
			
				function getCompressorName(data){
					return data.name ;
				}
				
				function getCompressorRun(data){
					if(data.activeState==2)
						return "����" ;
					else if (data.activeState==1)
						return "ֹͣ" ;
					else 
						return "----" ;
				}
				
				function getCompressorLowpres(data){
				
					if(data.lowpresState==2)
						return "����" ;
					else if (data.lowpresState==1)
						return "�ر�" ;
					else
						return "----" ;
				}
				
				
				function getCompreslowpresAlarm(data){
					if(data.lowpresAlarm==2)
						return "<img src='img/menu/win/blue.gif'>" ;
					else if (data.lowpresAlarm==0)
						return "<img src='img/menu/win/red.gif'>" ;
					else 
						return "----"  ;
				}
				
				function getCompressorHighPres(data){
					if(data.highpresState==2)
						return "����" ;
					else if (data.highpresState==1)
						return "�ر�" ;
					else 
						return "----" ;
				}
				
				
				function getCompressorHighPresAlarm(data){
					if(data.highpresAlarm==2)
						return "<img src='img/menu/win/blue.gif'>" ;
					else if (data.highpresAlarm==0)
						return "<img src='img/menu/win/red.gif'>" ;
					else
						return "----" ;
				}
				
				
				function getCompressorExhaust(data){
					if(data.exhaustValue==-300 || data.exhaustValue==null)
						return "----" ;
					return data.exhaustValue+"��" ;
				}
				
				
				function getCompressorExhaustAlarm(data){
					if(data.exhaustAlarm==2)
						return "<img src='img/menu/win/blue.gif'>" ;
					else if(data.exhaustAlarm==0)
						return "<img src='img/menu/win/red.gif'>" ;
					else
						return "----" ;
				}
				
				
				function getCompressorLoadState(data){
					if(data.overloadState==2)
						return "����" ;
					else if (data.overloadState==1)
						return "�ر�" ;
					else 
						return "----" ;
				}
				
				function getCompressLoadAlarm(data){
					if(data.overloadAlarm==2)
						return "<img src='img/menu/win/blue.gif'>" ;
					else if (data.overloadAlarm==0)
						return "<img src='img/menu/win/red.gif'>" ;
					return "----" ;
				}
				
			
			/**
			*	����ѹ������ʵʱ����
			*/
			function doCompressorData(data){
				if(data!=null){
					if(data.length>0){
						//DWRUtil.removeAllRows("Compressorcontainer");
						
						//��������ӵ����
						//DWRUtil.addRows("Compressorcontainer",data,cellsFunction,{escapeHtml:false ,
						//cellCreator:function(options){
						//	var td = document.createElement("td");
						//	td.align = "center" ;
						//	return td ;
						//},
						//rowCreator:function(options){
						//	var tr = document.createElement("tr");
						//	return tr ;
						//}
						//});
						
						var i = 0 ;
						
						for( ; i<data.length;i++){
							//���֧������ѹ����ʵʱ����
							if(i==6)
								break ;
							var r = data[i] ;
							document.getElementById("compressor"+i).innerHTML = getCompressorName(r);
							document.getElementById('compressor'+i).title = getCompressorName(r);
							document.getElementById('compressorrun_'+i).innerHTML = getCompressorRun(r);
							document.getElementById('compressorlow_'+i).innerHTML = getCompressorLowpres(r);
							document.getElementById('compressorlowalarm_'+i).innerHTML = getCompreslowpresAlarm(r);
							document.getElementById('compressorhigh_'+i).innerHTML = getCompressorHighPres(r);
							document.getElementById('compressorhighalarm_'+i).innerHTML = getCompressorHighPresAlarm(r);
							document.getElementById('compressorpaiqi_'+i).innerHTML = getCompressorExhaust(r);
							document.getElementById('compressorpaiqialarm_'+i).innerHTML = getCompressorExhaustAlarm(r);
							document.getElementById('compressoroverload_'+i).innerHTML = getCompressorLoadState(r);
							document.getElementById('compressoroverloadalarm_'+i).innerHTML = getCompressLoadAlarm(r);			
						}
						
						if(i!=6){
							for(;i<6;i++)
								document.getElementById('compressor'+i).innerHTML = "" ;
						}
												
					}		
				}
				//��ʶ��ǰ�������
				compress_flag = true ;
				//�ж����������Ƿ����
				isAllOK() ;
			}
			
			/**
			*	��ȡ��������ʵʱ����
			*/
			function getCondenserData(){
				var ids = document.getElementById("condenserids").value;
				if(ids==null || ids=="")
					return  ;
				realcool.getCondenserDataByIds(ids,{
					callback:doCondenserData,
					errorHandler:doError,
					timeout:15000
				}) ;
			}
			
			
			
				function getCondenserName(data){
					return data.name ;
				}
				
				function getCondenserPressure(data){
					var pState = data.pressureState ;
					if(pState==2)
						return "����" ;
					else if (pState==1)
						return "�ر�" ;
					else 
						return "----" ;
				}
				
				function getCondenserPressureAlarm(data){
					var pAlarm = data.pressureAlarm ;
					if(pAlarm==2)
						return "<img src='img/menu/win/blue.gif'>" ;
					else if (pAlarm==0)
						return "<img src='img/menu/win/red.gif'>" ;
					else 
						return "----" ;
				}
			
			
			/**
			*	������������ʵʱ����
			*/
			function doCondenserData(data){
				if(data!=null){
					if(data.length!=0){
						//DWRUtil.removeAllRows("Condensercontainer");
						
						//��������ӵ����
						//DWRUtil.addRows("Condensercontainer",data,cellsFunction2,{escapeHtml:false ,
						//cellCreator:function(options){
						//	var td = document.createElement("td");
						//	td.align = "center" ;
						//	return td ;
						//},
						//rowCreator:function(options){
						//	var tr = document.createElement("tr");
						//	return tr ;
						//}
						//});	
						
						var j=0 ;
						for(;j<data.length;j++){
							if(j==6)
								break ;
							var r = data[j] ;	
							document.getElementById('condenser'+j).innerHTML = getCondenserName(r);
							document.getElementById('condenser'+j).title = getCondenserName(r);
							document.getElementById('condenserpress_'+j).innerHTML = getCondenserPressure(r);
							document.getElementById('condenserpressalarm_'+j).innerHTML = getCondenserPressureAlarm(r); 
								
						}
						
						if(j!=6){
							for(;j<6;j++)
								document.getElementById('condenser'+j).innerHTML = "" ;
						}
					}
				}
					//��ʶ��ǰ�������
					comdenser_flag = true ;
					//�ж����������Ƿ����
					isAllOK() ;
			}
			
			/**
			*	��ȡ������ʵʱ����
			*/
			function getAirCoolerData(){
			
			var ids = document.getElementById("aircoolerids").value ;
				if(ids==null || ids=="")
					return  ;
				realcool.getAirCoolerDataByIds(ids,{
					callback:doAirCoolerData,
					errorHandler:doError,
					timeout:15000
				}) ;
			}
			
			
				function getAirCoolerName(data){
					return data.name ;
				}
				
				function getAirCoolerDefrost(data){
					var ds = data.defrostState ;
					if(ds==2)
						return "����" ;
					else if (ds==1)
						return "�ر�" ;
					else 
						return "----" ;
				}
				
				function getAirCoolerDefrostAlarm(data){
					var da = data.defrostAlarm ;
					if(da==2)
						return "<img src='img/menu/win/blue.gif'>" ;
					else if (da==0)
						return "<img src='img/menu/win/red.gif'>" ;
					else 
						return "----" ;
				}
			
			
			/**
			*	����������ʵʱ����
			*/
			
			function doAirCoolerData(data){
				if(data!=null){
					if(data.length!=0){
						//DWRUtil.removeAllRows("AirCoolercontainer");
						
						//��������ӵ����
						//DWRUtil.addRows("AirCoolercontainer",data,cellsFunction3,{escapeHtml:false ,
						//cellCreator:function(options){
						//	var td = document.createElement("td");
						//	td.align = "center" ;
						//	return td ;
						//},
						//rowCreator:function(options){
						//	var tr = document.createElement("tr");
						//	return tr ;
						//}
						//});	
						var k=0 ;
						for(;k<data.length;k++){
							if(k==6)
								break ;
							var r = data[k] ;
							document.getElementById('aircooler'+k).innerHTML = getAirCoolerName(r); 
							document.getElementById('aircooler'+k).title = getAirCoolerName(r);
							document.getElementById('aircoolerfrost_'+k).innerHTML = getAirCoolerDefrost(r);
							document.getElementById('aircoolerfrostalarm_'+k).innerHTML = getAirCoolerDefrostAlarm(r);
						}
						
						if(k!=6){
							for(;k<6;k++)
								document.getElementById('aircooler'+k).innerHTML = "" ;
						}
						
						
					}
				}
					//��ʶ��ǰ�����ɹ�
					aircooler_flag = true ;
				
					//�ж����������Ƿ����
					isAllOK() ;
			}
			
			/**
			*	��ʾ�����ر��
			**/
			function showhideobj(obj){
				if(document.getElementById(obj).style.display ==null || document.getElementById(obj).style.display =='none'){
					document.getElementById(obj).style.display ='block' ;
				}else{
					document.getElementById(obj).style.display ='none'
				}
			}
			
			//��ת������ϵͳʵʱ����ҳ��
			function goCoolerrealsys(){
				document.myform.action = "realcool.do?ope=toRealCoolSys" ;
				document.myform.submit() ;
			}
			
			//��ת����ͬ�Ļ���ʵʱ����ҳ��
			function goCompressorSet(id){
				document.getElementById('mysetid').value = id ;
				document.myform.action = "realcool.do?ope=toRealCoolSingleMul" ;
				document.myform.submit() ;
			}
			
			//��ת����ҳ��
			function goback(url,operate){
				document.myform.action = url+"?ope="+operate ;
				document.myform.submit() ;
			}
			
			
			
		</script>
</head>
<body onload="pageInit();">
<div>
     <iframe scrolling="no" src="common/header2.jsp" width=100% height=126 frameborder=0></iframe >  
		<%-- <jsp:include page="common/header2.jsp" flush="true"></jsp:include> --%>
</div>
<form action="" name="myform" method="post">
<input type="hidden" name="branchId" id="branchId" value="${param.branchId }"/>
<input type="hidden" name="mysetid" id="mysetid" value="${param.mysetid }"/>
<input type="hidden" name="compressorids" id="compressorids" value="${compressorStr}"/> 
<input type="hidden" name="aircoolerids"  id="aircoolerids" value="${aircoolerStr}"/> 
<input type="hidden" name="condenserids"  id="condenserids" value="${condenserStr}"/> 

<div id="left">
  <ul id="nav">
    <li class="tab"><a href="#"></a></li>
    <li class="tab_a"> <a href="javascript:goCoolerrealsys();"><img src="images/Refri/icon_e.gif" width="20" height="20"  />ϵͳ </a> </li>
 	<c:forEach items="${coollist}" var="comset">
    		<li class="tab_b" ><a href="javascript:goCompressorSet('${comset.id }');"><img src="images/Refri/jizu.gif" width="10" height="12" />${comset.csName }</a></li>
    </c:forEach>
    <li class="tab_c"><a href="#">gfsdgs</a></li>
  </ul>
</div>
<div id="line"><img src="images/index/icon2_090.gif" /></div>
<div id="right">

<div id="top"><a href="#"><img src="images/Refri/icon_c.gif" width="16" height="15"  class="tb4"/><strong>λ��:��ҳ</strong>&lt;��������ʵʱ���ݲ鿴</a><span>
<img src="img/util/back.gif" style="cursor: pointer;" onclick="goback('pro.do','toRefList');" width="48" height="20"  class="tb3"/></div>
<span id="net" style="color: red;display: none">��ȡ����ʵʱ����ʧ��,���Ժ�....</span>
  <div id="center"><a href="javascript:showhideobj('myCompressorSet');"><img src="images/Refri/button_s.gif" /></a></div>
  <div id="bottom">
  	<div id="myCompressorSet">
    <table id="tb" width="78%" border="0" cellspacing="0" cellpadding="0">
          <tr id="tb1">
            <td width="7%">&nbsp;</td>
            <td width="21%">��������</td>
            <td width="20%">�豸����</td>
            <td width="25%">��ͷ����</td>
            <td width="25%">��ע</td>
          </tr>
          <tr class="tb2">
            <td><img src="images/Refri/jizu.gif"></img></td>
            <td>${compressorset.csName }</td>
            <td>��������</td>
            <td>${compressorset.csCount }</td>
            <td>��</td>
          </tr>
        </table>
        </div>
  </div><div id="center"><a href="javascript:showhideobj('myCompressor');showhideobj('myCompressorSetReal')"><img src="images/Refri/button_r.gif" /></a></div>
  <div id="bottom">
  	<div id="myCompressorSetReal">
    <table id="tb" width="78%" border="0" cellspacing="0" cellpadding="0">
          <tr id="tb1">
            <td width="6%">&nbsp;</td>
            <td width="25%">����</td>
            <td width="32%">״̬</td>
            <td width="37%">Ԥ��</td>
          </tr>
          <tr class="tb2">
            <td><span class="orr"><img src="images/Refri/quexiang.png" width="22" height="22" /></span></td>
            <td>ȱ���</td>
            <td><div id="duandian"></div></td>
            <td><div id="duandianyujin"></div></td>
          </tr>
      <tr class="altrow">
            <td><span class="orr"><img src="images/Refri/guozai.gif" width="22" height="22" /></span></td>
            <td>���ر���</td>
            <td><div id="guozai"></div></td>
            <td><div id="guozaiyujin"></div></td>
      </tr>
          <tr class="tb2">
            <td><span class="orr"><img src="images/Refri/jizuguzhang.gif" width="22" height="22" /></span></td>
            <td>���ϱ���</td>
            <td><div id="guzhang"></div></td>
            <td><div id="guzhangyujin"></div></td>
          </tr>
    </table>
    </div>
    <div id="myCompressor">
    <table id="tb" width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr id="tb1">
            <td width="49">&nbsp;</td>
            <td width="120">��ͷ</td>
            <td colspan="2"><div style="overflow:hidden; text-overflow:ellipsis; width: 100px;height: 23px;" id="compressor0">----</div></td>
            <td colspan="2"><div style="overflow:hidden; text-overflow:ellipsis; width: 100px;height: 23px;" id="compressor1">----</div></td>
            <td colspan="2"><div style="overflow:hidden; text-overflow:ellipsis; width: 100px;height: 23px;" id="compressor2">----</div></td>
            <td colspan="2"><div style="overflow:hidden; text-overflow:ellipsis; width: 100px;height: 23px;" id="compressor3">----</div></td>
            <td colspan="2"><div style="overflow:hidden; text-overflow:ellipsis; width: 100px;height: 23px;" id="compressor4">----</div></td>
            <td colspan="2"><div style="overflow:hidden; text-overflow:ellipsis; width: 100px;height: 23px;" id="compressor5">----</div></td>
          </tr>
          <tr class="tb2">
            <td></td>
            <td>����</td>
            <td width="60">״̬</td>
            <td width="60">Ԥ��</td>
            <td width="60">״̬</td>
            <td width="60">Ԥ��</td>
            <td width="60">״̬</td>
            <td width="60">Ԥ��</td>
            <td width="60">״̬</td>
            <td width="60">Ԥ��</td>
            <td width="60">״̬</td>
            <td width="60">Ԥ��</td>
            <td width="60">״̬</td>
            <td width="60">Ԥ��</td>
          </tr>
          <tr class="altrow">
            <td><span class="orr"><img src="images/Refri/yunxing.gif" width="22" height="22" /></span></td>
            <td>����״̬</td>
            <td><div id="compressorrun_0"></div></td>
            <td>&nbsp;</td>
            <td><div id="compressorrun_1"></div></td>
            <td>&nbsp;</td>
            <td><div id="compressorrun_2"></div></td>
            <td>&nbsp;</td>
            <td><div id="compressorrun_3"></div></td>
            <td>&nbsp;</td>
            <td><div id="compressorrun_4"></div></td>
            <td>&nbsp;</td>
            <td><div id="compressorrun_5"></div></td>
            <td>&nbsp;</td>
          </tr>
          <tr class="tb2">
            <td><span class="orr"><img src="images/Refri/diya.gif" width="22" height="22" /></span></td>
            <td>��ѹ����</td>
            <td><div id="compressorlow_0"></div></td>
            <td><div id="compressorlowalarm_0"></div></td>
            <td><div id="compressorlow_1"></div></td>
            <td><div id="compressorlowalarm_1"></div></td>
            <td><div id="compressorlow_2"></div></td>
            <td><div id="compressorlowalarm_2"></div></td>
            <td><div id="compressorlow_3"></div></td>
            <td><div id="compressorlowalarm_3"></div></td>
            <td><div id="compressorlow_4"></div></td>
            <td><div id="compressorlowalarm_4"></div></td>
            <td><div id="compressorlow_5"></div></td>
            <td><div id="compressorlowalarm_5"></div></td>
          </tr>
          <tr class="tb2">
            <td><span class="orr"><img src="images/Refri/gaoya.gif" width="22" height="22" /></span></td>
            <td>��ѹ����</td>
            <td><div id="compressorhigh_0"></div></td>
            <td><div id="compressorhighalarm_0"></div></td>
            <td><div id="compressorhigh_1"></div></td>
            <td><div id="compressorhighalarm_1"></div></td>
            <td><div id="compressorhigh_2"></div></td>
            <td><div id="compressorhighalarm_2"></div></td>
            <td><div id="compressorhigh_3"></div></td>
            <td><div id="compressorhighalarm_3"></div></td>
            <td><div id="compressorhigh_4"></div></td>
            <td><div id="compressorhighalarm_4"></div></td>
            <td><div id="compressorhigh_5"></div></td>
            <td><div id="compressorhighalarm_5"></div></td>
          </tr>
          <tr class="tb2">
            <td><span class="orr"><img src="images/Refri/paiqi.gif" width="22" height="22" /></span></td>
            <td>�����¶�</td>
            <td><div id="compressorpaiqi_0"></div></td>
            <td><div id="compressorpaiqialarm_0"></div></td>
            <td><div id="compressorpaiqi_1"></div></td>
            <td><div id="compressorpaiqialarm_1"></div></td>
            <td><div id="compressorpaiqi_2"></div></td>
            <td><div id="compressorpaiqialarm_2"></div></td>
            <td><div id="compressorpaiqi_3"></div></td>
            <td><div id="compressorpaiqialarm_3"></div></td>
            <td><div id="compressorpaiqi_4"></div></td>
            <td><div id="compressorpaiqialarm_4"></div></td>
            <td><div id="compressorpaiqi_5"></div></td>
            <td><div id="compressorpaiqialarm_5"></div></td>
          </tr>
          <tr class="tb2">
            <td><span class="orr"><img src="images/Refri/guozai.gif" width="22" height="22" /></span></td>
            <td>���ر���</td>
            <td><div id="compressoroverload_0"></div></td>
            <td><div id="compressoroverloadalarm_0"></div></td>
            <td><div id="compressoroverload_1"></div></td>
            <td><div id="compressoroverloadalarm_1"></div></td>
            <td><div id="compressoroverload_2"></div></td>
            <td><div id="compressoroverloadalarm_2"></div></td>
            <td><div id="compressoroverload_3"></div></td>
            <td><div id="compressoroverloadalarm_3"></div></td>
            <td><div id="compressoroverload_4"></div></td>
            <td><div id="compressoroverloadalarm_4"></div></td>
            <td><div id="compressoroverload_5"></div></td>
            <td><div id="compressoroverloadalarm_5"></div></td>
          </tr>
    </table>
    </div>
  </div><div id="center"><a href="javascript:showhideobj('myCondenser');"><img src="images/Refri/button_g.gif" /></a></div>
  <div id="bottom">
  	<div id="myCondenser">
    <table id="tb" width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr id="tb1" style="line-height: 30">
        <td width="49">&nbsp;</td>
        <td width="120">������</td>
        <td colspan="2" ><div  style="overflow:hidden; text-overflow:ellipsis; width: 100px;height: 23px;" id="condenser0">----</div></td>
        <td colspan="2"><div style="overflow:hidden; text-overflow:ellipsis; width: 100px;height: 23px;" id="condenser1">----</div></td>
        <td colspan="2"><div style="overflow:hidden; text-overflow:ellipsis; width: 100px;height: 23px;" id="condenser2">----</div></td>
        <td colspan="2"><div style="overflow:hidden; text-overflow:ellipsis; width: 100px;height: 23px;" id="condenser3">----</div></td>
        <td colspan="2"><div style="overflow:hidden; text-overflow:ellipsis; width: 100px;height: 23px;" id="condenser4">----</div></td>
        <td colspan="2"><div style="overflow:hidden; text-overflow:ellipsis; width: 100px;height: 23px;" id="condenser5">----</div></td>
      </tr>
      <tr class="tb2">
        <td>&nbsp;</td>
        <td>����</td>
        <td width="60">״̬</td>
        <td width="60">Ԥ��</td>
        <td width="60">״̬</td>
        <td width="60">Ԥ��</td>
        <td width="60">״̬</td>
        <td width="60">Ԥ��</td>
        <td width="60">״̬</td>
        <td width="60">Ԥ��</td>
        <td width="60">״̬</td>
        <td width="60">Ԥ��</td>
        <td width="60">״̬</td>
        <td width="60">Ԥ��</td>
      </tr>
      <tr class="altrow">
        <td><span class="orr"><img src="images/Refri/yali.gif" width="22" height="22" /></span></td>
        <td>ѹ������</td>
        <td><div id="condenserpress_0"></div></td>
        <td><div id="condenserpressalarm_0"></div></td>
        <td><div id="condenserpress_1"></div></td>
        <td><div id="condenserpressalarm_1"></div></td>
        <td><div id="condenserpress_2"></div></td>
        <td><div id="condenserpressalarm_2"></div></td>
        <td><div id="condenserpress_3"></div></td>
        <td><div id="condenserpressalarm_3"></div></td>
        <td><div id="condenserpress_4"></div></td>
        <td><div id="condenserpressalarm_4"></div></td>
        <td><div id="condenserpress_5"></div></td>
        <td><div id="condenserpressalarm_5"></div></td>
      </tr>
    </table>
    </div>
  </div><div id="center"><a href="javascript:showhideobj('myAirCooler');"><img src="images/Refri/button_t.gif" /></a></div>
  <div id="bottom">
  <div id="myAirCooler">
    <table id="tb" width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr id="tb1">
        <td width="49">&nbsp;</td>
        <td width="120">����</td>
        <td colspan="2"><div style="overflow:hidden; text-overflow:ellipsis;" id="aircooler0">----</div></td>
        <td colspan="2"><div style="overflow:hidden; text-overflow:ellipsis;" id="aircooler1">----</div></td>
        <td colspan="2"><div style="overflow:hidden; text-overflow:ellipsis;" id="aircooler2">----</div></td>
        <td colspan="2"><div style="overflow:hidden; text-overflow:ellipsis;" id="aircooler3">----</div></td>
        <td colspan="2"><div style="overflow:hidden; text-overflow:ellipsis;" id="aircooler4">----</div></td>
        <td colspan="2"><div style="overflow:hidden; text-overflow:ellipsis;" id="aircooler5">----</div></td>
      </tr>
      <tr class="tb2">
        <td>&nbsp;</td>
        <td>����</td>
        <td width="60">״̬</td>
        <td width="60">Ԥ��</td>
        <td width="60">״̬</td>
        <td width="60">Ԥ��</td>
        <td width="60">״̬</td>
        <td width="60">Ԥ��</td>
        <td width="60">״̬</td>
        <td width="60">Ԥ��</td>
        <td width="60">״̬</td>
        <td width="60">Ԥ��</td>
        <td width="60">״̬</td>
        <td width="60">Ԥ��</td>
      </tr>
      <tr class="altrow">
        <td height="29"><span class="orr"><img src="images/Refri/chushuang.gif" width="25" height="27" /></span></td>
        <td>ѹ������</td>
        <td><div id="aircoolerfrost_0"></div></td>
        <td><div id="aircoolerfrostalarm_0"></div></td>
        <td><div id="aircoolerfrost_1"></div></td>
        <td><div id="aircoolerfrostalarm_1"></div></td>
        <td><div id="aircoolerfrost_2"></div></td>
        <td><div id="aircoolerfrostalarm_2"></div></td>
        <td><div id="aircoolerfrost_3"></div></td>
        <td><div id="aircoolerfrostalarm_3"></div></td>
        <td><div id="aircoolerfrost_4"></div></td>
        <td><div id="aircoolerfrostalarm_4"></div></td>
        <td><div id="aircoolerfrost_5"></div></td>
        <td><div id="aircoolerfrostalarm_5"></div></td>
      </tr>
    </table>
    </div>
  </div>
</div>
<div class="clear"></div>
</form>
<div><iframe scrolling="no" src="common/footer2.jsp" width=100% height=43 frameborder=0></iframe ></div>
</body>
</html>
