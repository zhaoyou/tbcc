<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>�ƶ�����ʵʱ���ݲ鿴</title>
<link rel="Shortcut Icon" href="img/add/logo.ico" />
<link href="css/chezai/tc.css" rel="stylesheet" type="text/css" />
<link href="css/chezai/input.css" rel="stylesheet" type="text/css" />

<script type='text/javascript' src='dwr/interface/real.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>

<script type="text/javascript">

	function golist(url,operate){
		document.getElementById('myform').action = url ;
		document.getElementById('ope').value = operate ;
		document.myform.submit();
	}
	
		//��ȡ����
			var mytimeout = null ;
			
			function getCarData(){
				var branchId = document.getElementById("branchId").value ;
				if(branchId==null || branchId==""){
					return ;
				}
				real.getRealCar(branchId,{
				callback:resultHandler,
				errorHandler:doerror,
				timeout:15000
				}) ;	
			}
			
			//������
			function doerror(message){
				document.getElementById("net").style.display = "block" ;
			}
			
			var i = 1 ;
			//����Ԫ���ֵ
			var  cellsFunction  = 
			[
				function(data){
					return i++ ;
				},
				
				function(data){
					return "�ƶ�����"
				},
				
				function(data){
					return data.projectName ;
				},
				
				function(data){				
				if(data.connectStatus==null || data.connectStatus==1)
					return "* * *" ;
				
				if(data.alarmStatus==null)
					return "* * *" ;	
					
					if(data.alarmStatus==0){	//Ԥ�� 0 
						return "<img src='img/menu/win/orange.jpg' width='12' height='12'  title='��ʾ�ն˴���Ԥ��״̬' />";
					}else if(data.alarmStatus==1){  //���� 1
						return "<img src='img/menu/win/red.gif' width='12' height='12'    title='��ʾ�ն˴��ڱ���״̬' />";
					}else{					  //����  2
						return "<img src='img/menu/win/blue.gif'  width='12' height='12'  title='��ʾ�ն˴�������״̬'/>" ;
					}
				},
				function(data){			
					if(data.connectStatus==null || data.connectStatus==1)
						return "* * *" ;
					if(data.runStatus==null)	
						return "* * * " ;
						
					if(data.runStatus==2){
						return "<img src='img/menu/doc2.gif'   width='18' height='16' title='��ʾ���ݼ�¼����'  />" ;		
					}else{
						return "<img src='img/menu/msie_doc_mo.gif'  width='18' height='16'  title='��ʾ���ݼ�¼ֹͣ'  />" ;
					}
				},
				function(data){
					if(data.connectStatus==null || data.connectStatus==1){
						return "* * *" ;
					}else{
						if(unloadStatus=-1){
							return "--";
						}
						else if(unloadStatus=1){
							return "����";
						}else{
							return "<font color='green'>ж��</font>";
						}
					}
				},
				function(data){
					if(data.connectStatus==null || data.connectStatus==1){
						return "�Ͽ�" ;
					}else{
						return "����" ;
					}
				},
				
				/**
				function(data){
					if(data.connectStatus==1)
						return "* * *" ;
					return data.latitudeStr ;
				},
				*/
				
				function(data){
					if(data.connectStatus==null || data.connectStatus==1)
						return "* * *" ;
						
					if(data.address==null)
						return "* * *" ;
					return data.address ;
				},
				
				function(data){
					
					if(data.ai1==null)
						return "* * *" ;
				
					if(data.connectStatus==null || data.connectStatus==1){
							return "* * *" ;
						}
					else{
							if(data.ai1==-300)
								return "--" ;
							if(data.alarmdatastr.split("")[6]=='1'&&data.alarmdatastr.split("")[7]=='1'){
								return data.ai1 ;
							}
							if(data.alarmdatastr.split("")[6]==0)
								return "<font color='red'>"+data.ai1 +"��</font>" ;
							
							if(data.alarmdatastr.split("")[7]==0)
								return "<font color='blue'>"+data.ai1 +"��</font>" ;
						}
						
					
				},
				function(data){
					if(data.ai2==null)
						return "* * *" ;
						
					if(data.connectStatus==null || data.connectStatus==1)
						return "* * *" ;
					else{
						if(data.ai2==-300)
							return "--" ;
						if(data.alarmdatastr.split("")[4]=='1'&&data.alarmdatastr.split("")[5]=='1'){
								return data.ai2 ;
							}
						if(data.alarmdatastr.split("")[4]==0)
								return "<font color='red'>"+data.ai2 +"��</font>" ;
							
						if(data.alarmdatastr.split("")[5]==0)
								return "<font color='blue'>"+data.ai2 +"��</font>" ;
					}	
				},
				function(data){
					if(data.ai3==null)
						return "* * *" ;
					if(data.connectStatus==null || data.connectStatus==1)
						return "* * *" ;
					else{
						if(data.ai3==-300)
							return "--" ;
						if(data.alarmdatastr.split("")[2]=='1'&&data.alarmdatastr.split("")[3]=='1'){
								return data.ai3 ;
							}
						if(data.alarmdatastr.split("")[2]==0)
								return "<font color='red'>"+data.ai3 +"��</font>" ;
							
						if(data.alarmdatastr.split("")[3]==0)
								return "<font color='blue'>"+data.ai3 +"��</font>" ;
					}
				
				},
				function(data){
					if(data.ai4==null)
						return "* * *";
					if(data.connectStatus==null || data.connectStatus==1)
						return "* * *" ;
					else{
						if(data.ai4==-300)
							return "--" ;
						if(data.alarmdatastr.split("")[0]=='1'&&data.alarmdatastr.split("")[1]=='1'){
								return data.ai4 ;
							}
						if(data.alarmdatastr.split("")[0]==0)
								return "<font color='red'>"+data.ai4 +"��</font>" ;
							
						if(data.alarmdatastr.split("")[1]==0)
								return "<font color='blue'>"+data.ai4 +"��</font>" ;
					}
				},
				
				function(data){
					if(data.connectStatus==null || data.connectStatus==1)
						return "* * *" ;
					else{
						if(data.carSpeed==-300||data.speedStatus==-1){
							return "--" ;
						}else if(data.speedStatus==1){
							return data.carSpeed;	
						}else if(data.speedStatus==0){
							return "<font color='red'>"+data.carSpeed+"��</font>";
						}
					}		
				},
				
				function(data){
				  if(document.getElementById("p").value=="true"){
				  
					if(data.connectStatus==null || data.connectStatus==1)
						return "<img title='ʵʱ��ͼ��ʾ' alt='ʵʱ��ͼ��ʾ' style='cursor:pointer' src='images/chezai/chart-up.png' align='middle'  />" ;
					else
					   return "<img title='ʵʱ��ͼ��ʾ' alt='ʵʱ��ͼ��ʾ' style='cursor:pointer' src='images/chezai/chart-up.png' align='middle' onclick=gotoMap('"+data.projectId+"') />" 
				   }else{
				   		document.getElementById("shishizhuizong").innerHTML = "" ;
				   		return "" ;
				   }
				}

			] ;
			
			//�ѷ��صĽ��
			function resultHandler(data){
			
				//�ȴ���div��ʧ
				document.getElementById('waitingDiv').style.display = "none" ;
			
			  //���ݿ�������Ӧ������
				if(data!=null){
					if(data.length>0){
						
						//������ݱ��
						DWRUtil.removeAllRows("container");
						
						i = 1 ;
						
						//��������ӵ����
						DWRUtil.addRows("container",data,cellsFunction,{escapeHtml:false ,
						cellCreator:function(options){
							var td = document.createElement("td");
							td.align = "center" ;
							return td ;
						},
						rowCreator:function(options){
							var tr = document.createElement("tr");
							tr.className="altrow"
							return tr ;
						}
						});											
					}
				}else{
						//��ձ������
						DWRUtil.removeAllRows("container");
						var c = "<tr><td col='12'>����ʵʱ������δ����</td></tr>" ;
						document.getElementById('container').innerHTML = c ;
				}
				
				//��ȡ�������
				document.getElementById('net').style.display = 'none';
				
			}
			
			
			/**
			*	ҳ���ʼ��
			*/
			
			function pageInit(){
				getCarData() ;
				mytimeout = window.setInterval("getCarData()",10000);// window.setTimeout("getCarData",10000);
			}
			
			/**
			*	��ת����ͼ��ʾ
			*/
			function gotoMap(pid){
				var f = document.getElementById("p").value ;

				if(f=="true"){
					document.getElementById('myform').action = "realcar.do" ;
					document.getElementById('ope').value = 'toRealMap'
					document.getElementById("proId").value = pid ;
					document.myform.submit() ;
				}
				
			}
</script>
</head>
 
<body onload="pageInit();">
<div>
  <iframe scrolling="no" src="common/header2.jsp" width=100% height=126 frameborder=0></iframe >
</div>
<form action="" name="myform" id="myform" method="post">
	<input type="hidden" name="branchId" id="branchId" value="${param.branchId }"/>
	<input type="hidden" name="ope" id="ope" value=""/>
	
    <input type="hidden" name="proId" id="proId" value=""/>
    <input type="hidden" name="p" id="p" value="${fn:contains(power,"����ʵʱ��ͼ")}" />
</form>
<div id="right">
  <div id="top"><a href="#"><img src="images/chezai/icon_c.gif" width="16" height="15" class="tb4"/>
  	<strong>λ��:��ҳ</strong>&lt;�����ڲ鿴����ʵʱ����</a>
  	<img src="img/util/back.gif" style="cursor: pointer;" onclick="javascript:golist('pro.do','toCarList');" width="48" height="20"  class="tb3"/>
  </div>
  <div align="center">
   <div id="net" style="background-color:rgb(227,222,28);font-family:'Arial Rounded MT Bold';width:195px;display:none;">
    &nbsp;&nbsp;��ǰ���緱æ,�޷���ȡʵʱ����...</div>
  </div>
  <div id="bottom">
    <table id="tb" width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr id="tb1">
        <td width="39">���</td>
        <td width="100">�ն�����</td>
        <td width="100">��Ŀ����</td>
        <td width="63">����״̬</td>
        <td width="63">����״̬</td>
        <td width="63">����״̬</td>
        <td width="75">����״̬</td>
        <td width="84">��ǰ��ַ</td>
        <td width="61">T1</td>
        <td width="61">T2</td>
        <td width="61">T3</td>
        <td width="61">RH1</td>
        <td width="63">�ٶ�(km/h)</td>
        <td width="66"><span id="shishizhuizong" style="display: inline">ʵʱ׷��</span></td>
      </tr>
      <tr>
      	<td colspan="14">
      		<div id="waitingDiv" style="display: inline">
      			<br/>
      			<img src="img/util/process.gif" title="���ݼ�����...."/>
      		</div>
      	</td>
      </tr>
      <tbody id="container">
								
	  </tbody>
    </table>
  </div>
  </div>
  <%-- ��ͼ����Ƭ <img src="images/chezai/chart-up.png" width="16" height="16" /> --%>
<div class="clear"></div>
<div>
  <iframe scrolling="no" src="common/footer2.jsp" width=100% height=43 frameborder=0></iframe >
</div>
</body>
</html>
