<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>������ʷ�켣׷��</title>
<link rel="Shortcut Icon" href="img/add/logo.ico" />
<link href="css/map/tc.css" rel="stylesheet" type="text/css" />
<link href="css/chezai/input1.css" rel="stylesheet" type="text/css" />

		<script type='text/javascript' src='dwr/interface/real.js'></script>
 		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
			
			
<script type="text/javascript"
			src="http://app.mapabc.com/apis?&t=flashmap&v=2.3.1&key=30d03c0ba5c60850be655229ede0f001c17751ecce42fb81df6077eaa1031fb01a4a18c4a07047f0">
  </script>
  
<script type="text/javascript">
	function golist(){
		document.myform.submit() ;
	}
	
			//�����ͼ����
            var mapobj = null;			//��ͼ����     
            var ploylineobj = null ;	//���߶���
            var mytime = null ;
            
            
            
            //���峵�ض���
            var projectName = "" ;		//���ص�����    
            var proId = "" ;
            var sid = "" ;
            
            
            //�������ݶ���
            var myresult = new Array() ;		// �����ƶ�������ʷ��������Դ
            var index = 0 ;						//���浱ǰ��ȡ���ݵ�����
            var allresult =  new Array() ;		// �������ݿ������е�����
            var allsize = 0 ;					//�������м�¼������
            

            
            
            //���嵱ǰ�ľ�γ����Ϣ
            var current_lng = null ;
            var current_lat = null ;
            
            
            //����ֵ
            var t1 = null;
            var t2 = null ;
            var t3 = null ;
            var rh = null ;
            var current_time = null ;
            
            var alarmStatus = null ;
            
            var unloadStatus = null;
            
            var isBeginAddress = true ;
            
            /**
            *	��ȡ���ݿ�����ݼ���
            */
            function getData(){
             	proId = document.getElementById("proId").value ;
             	sid = document.getElementById("sid").value ;
             	projectName = document.getElementById("projectName").value ;
             	real.getHisCarData(proId,sid,{
             		callback:resultHandler,
             		errorHandler:errorHandler,
             		timeout:15000
             	}) ;
            }
           
           /**
           *	���������Ľ��
           */
            function resultHandler(data){
            	if(data==null || data.length==0){
            		document.getElementById('operateDiv').innerHTML = '��ǰ���ص�������δ�ϴ�...';	
            	}else{
            		allresult = data ;				//�������е�����Դ
            		allsize = data.length ;			//�������е���������	
            		index = 0 ;						//��ǰ�����ı�ʾ��Ϊ0 
            		
           		 mytime =  window.setInterval("getDataFromArr()",1);				//��ȡ��������
           		 document.getElementById('operateDiv').innerHTML = "���ڻ��Ƴ�����ʷ�켣..." ;
            	}
            }
            
            /**
            *  �쳣�������
            */
            function errorHandler(){
            	document.getElementById('operateDiv').innerHTML ="��ȡ������ʷ����ʧ��...";
            }
            
            /**
            *	��ʼ����ͼ����
            */
            function mapInit(){
                var mapoption = new MMapOptions();
                mapoption.zoom = 15;//���õ�ͼzoom����  
                mapoption.center = new MLngLat(121.550902,31.235708,2);
                mapoption.toolbar = ROUND; //���ù�����  
                mapoption.toolbarPos = new MPoint(0, 0);
                mapoption.scale = null;
                mapoption.overviewMap = MINIMIZE; //����ӥ��  
                mapoption.returnCoordType = COORD_TYPE_OFFSET;
                mapobj = new MMap("map", mapoption); //��ͼ��ʼ��  
                                     
                getData() ;//��ȡ���е����ݼ���
                
            }
            
             	 /**
				  * ֱ���ڵ�ͼ������һ��label,��ע��˾��˵����Ϣ
			      */	
				function addAddressLabel(ln,la,msg){
					var labeloption = new MLabelOptions();
					//����������ʽ
					var fontstyle  = new MFontStyle() ;
					fontstyle.name = "����" ;
					fontstyle.size = 12 ;
					fontstyle.color = 0x000000 ;
					fontstyle.bold = true ;
					
					labeloption.fontStyle = fontstyle ;
					
					labeloption.content = msg ;
					labeloption.hasBorder = true ;
					labeloption.hasBackground = true ;
					labeloption.backgroundColor = 0xFFCCCC ;
					
					labelobj = new MLabel(new MLngLat(ln,la,2),labeloption) ;
					
					mapobj.addOverlay(labelobj,false) ;
				}
            
           
			
	
			
			
			/**
			 * �����ص��ƶ�����
			 */
			function startDraw(){			
				
				//������ʾ�ı�
				var tip  = new MTipOptions();
				tip.title = projectName ;
				tip.anchor = new MPoint(0,0);
				tip.content = projectName+" ��ʻ�켣..." ;
				
				
				//��������������
				var myline = new MLineOptions();
				myline.tipOption = tip ;
				myline.lineStyle.lineType = LINE_SOLID ;
				myline.canShowTip = true ;
				myline.isDimorphic = true ;
				myline.dimorphicColor=0x8900FF  ;
				
				ploylineobj = new MPolyline(myresult,myline) ;
				ploylineobj.id = "myployline" ;
				mapobj.addOverlay(ploylineobj,false) ;
								
			}
			
			
			/**
			 * ���õ�ͼ����������
			 */
			function setCenter(){
			
				/*var currentbounds = mapobj.getLngLatBounds();					//��ȡ��ǰ��ͼ�����ϡ������ǵ�����
				
				if(currentbounds==null)
					return  ;
				
				var leftbutton = currentbounds.southWest ;		     //��ȡ���Ͻ�����
				var righttop = currentbounds.northEast ;			//��ȡ����������
				
				//a. ��ǰ����ľ���С�����Ͻǵľ��Ȼ��ߴ��ڶ����ǵľ���
				//b. ��ǰ�����γ��С�����Ͻǵ�γ�Ȼ��ߴ��ڶ����ǵ�γ��
				// ֻҪa��b������������һ�������ƶ���ͼ�����򱣳ֲ���
				
			
				
				if(leftbutton.lngX>current_lng || righttop.lngX-0.01 <current_lng 
				|| righttop.latY<current_lat || leftbutton.latY+0.004 > current_lat ){*/
					
						mapobj.panTo(new MLngLat(current_lng,current_lat,2)) ;		
				//}
					
			}
			
			
			//��ȡ��������
			function getDataFromArr(){	
				
				while(index<allsize){
					var obj = allresult[index] ;
					
					
					var lng     = null ;
					var lat     = null 				
					var lng_dir = obj.longitude_dir ;
					var lat_dir = obj.latitude_dir ;
					
					//��ȡ��ʪ����Ϣ
					t1 = formatData(obj.ai1,0)  ;
					t2 = formatData(obj.ai2,0) ;
					t3 = formatData(obj.ai3,0);
					rh = formatData(obj.ai4,1);
					
					//��ȡʱ���״̬
					current_time = new Date(obj.updateTime).toLocaleString();				
					alarmStatus = obj.alarmStatus ;
					//��ȡж��״̬
					unloadStatus = obj.unloadStatus;
					
					lng = formatLnglat(obj.longitude,0,lng_dir) ;
					lat = formatLnglat(obj.latitude,1,lat_dir) ;
					
				
					if(lng!=-300 && lat!=-300){
							
						//��ȡ��Ч�ľ�γ����Ϣ
						current_lng = lng ;
						current_lat = lat ;
						 myresult.push(new MLngLat(current_lng,current_lat,2)) ;
						 
						 //Ĭ����ʾΪ��ʼ��λ��
						 
							
							 if(isBeginAddress){
							  	setCenter() ;
							 	addAddressLabel(current_lng,current_lat,"��ʼ��") ;
						 	}
						 	isBeginAddress = false ;
					
						//������
               			 startDraw() ;
               			 
	 
               			 //���ݱ���״̬���ж��Ƿ�Ҫ�ӱ�����Ϣ
             			if(alarmStatus==1 || alarmStatus==0 || unloadStatus==0)
               			 	openAlarmTip() ;
        				
        				index ++ ;
        				isFinish() ;
        				break ;
               			 
					}
					
					index ++ ;			
					isFinish() ;		
				}
					
			}
			
			/**
			 *	�жϵ�ǰ�����Ƿ��Ѿ��������
			 */
			 
			 function isFinish(){
			 	if(index==allsize){
        					addAddressLabel(current_lng,current_lat,"��ֹ��");
						    window.clearInterval(mytime) ;
						    document.getElementById('operateDiv').innerHTML ="������ʷ�켣�������..." ;
        		}
			 }
			
				/**
				*	����γ��С��������
				*  param(t): 0 ����  1 γ��
				*  param(dir): �������� ���ϱ�γ�ķ���
				* ��ϵͳ�洢Ĭ�� 0Ϊ��γ 1Ϊ��γ  0Ϊ���� 1Ϊ������
				*  map ��ͼ��  Ĭ�Ϸ���Ϊ��������γ �෴����Ϊ��ֵȡ��
				*/
				
				function formatLnglat(d,t,dir){
					if(d==-300){
						return d ;
					}else{
						if(t==0){	//��������Ǿ���
							if(dir==1)
								return (lnglatHandler(d))*-1 ;
							return lnglatHandler(d) ;
						}else{		//���������γ��
							if(dir==0)
								return (lnglatHandler(d))*-1 ;
							return lnglatHandler(d) ;
						}
					}
				}
				
				/**
				*	����γ����Ϣ121.21212 dd.mm.mm ת��Ϊ dd.ddd
				*/
				function lnglatHandler(lnglat){
					var l = lnglat  ;
					var lq = parseInt(l/100) ;
					var lqq = (l-lq*100)/60 ;
					var lqqq = lq +lqq ;
					return lqqq ;
				}
				
				/**
				*������ʪ��-300����,�Լ����ݵĸ�ʽ��
				* t: 0�����¶�  1 ����ʪ��
				*/	 
				function formatData(d,t){
					if(d==-300){
						return "----" ;
					}else{
						if(t==0){
							return d+"��" ;
						}else{
							return d+"%" ;
						}
						
					}		
				}
			
				/**
				*	��һ��������ʾ��Ϣ
				*/
				function openAlarmTip(){
					addAlarm();
				}
				
				/**
				*		����һ��������ʾ��ע��Ϣ
				*/
				function addAlarm(){
				
					var tipMsg  = "" ;
					
					if(alarmStatus==1)
						tipMsg = "����״̬"+(unloadStatus==0?"  ����ж��":"");
					else if(alarmStatus==0)
						tipMsg = "Ԥ��״̬"+(unloadStatus==0?"  ����ж��":"") ;
					else tipMsg = "����״̬"+(unloadStatus==0?"  ����ж��":"") ;
				
					var markeroption = new MMarkerOptions();
					var labeloption = new MLabelOptions();
					var fontstyle = new MFontStyle();
					
					var tip = new MTipOptions();
					
					
					fontstyle.name = "����";
					fontstyle.size = 20;
					fontstyle.color = 0x000000;
					fontstyle.bold = true;
					
					
					labeloption.fontStyle = fontstyle;
					labeloption.content = "T1: " + t1 + " T2: "+t2+" T3: " + t3 +"\nʱ��: "+current_time ;
					labeloption.hasBorder = true;
					labeloption.hasBackground = true;
					labeloption.backgroundColor = 0xffffff;
					
					tip.title = projectName+tipMsg;
					tip.content =" \nT1:" + t1 + "  T2:"+t2+"  T3:" + t3 +"\nʱ��: "+current_time  ;
					tip.contentFontStyle.size = 15 ;
					tip.contentFontStyle.color = 0xffffff;
					
				//	markeroption.labelOption = labeloption;
					markeroption.labelPosition = new MPoint(17, -34);
					
					markeroption.tipOption = tip;
					markeroption.imageAlign = 8;
					markeroption.canShowTip = true;
					//markeroption.imageUrl = "http://www.thermoberg.com/ccdc/img/menu/car.jpg" ;
					if(unloadStatus!=0){
						if(alarmStatus==0)
							markeroption.imageUrl = "http://www.thermoberg.com/ccdcc/img/map/preAlarm.gif" ;
						if(alarmStatus==1)
							markeroption.imageUrl = "http://www.thermoberg.com/ccdcc/img/map/alarm2.gif";
					}else{
						if(alarmStatus==0)
							markeroption.imageUrl = "http://www.thermoberg.com/ccdcc/img/map/alarm4.gif" ;
						else if(alarmStatus==1)
							markeroption.imageUrl = "http://www.thermoberg.com/ccdcc/img/map/alarm3.gif";
						else markeroption.imageUrl = "http://www.thermoberg.com/ccdcc/img/map/alarm5.gif";
					}
					var carobjmarker = new MMarker(new MLngLat(current_lng,current_lat,2 ),markeroption);
					carobjmarker.id = "carobjmarker"+index;
					mapobj.addOverlay(carobjmarker);
				}
</script>
</head>
<body onload="mapInit();">
<div>
  <iframe scrolling="no" src="common/header2.jsp" width=100% height=126 frameborder=0></iframe >
</div>
<form name="myform" id="myform" action="startup.do"
						method="post">
							<input type="hidden" id="ope" name="ope" value="doStartUpCar"/>
							<input type="hidden" id="branchId" name="branchId" value="${param.branchId }"/>
							<input type="hidden" id="proId" name="proId" value="${param.proId }"/>
							<input type="hidden" id="sid"  name="sid" value="${startup.id }"/>
							<input type="hidden" id="time1" name="time1" value="${param.t1 }" />
							<input type="hidden" id="time2" name="time2" value="${param.t2 }" />
</form>
<div id="right">
  <div id="top"><a href="#"><img src="images/canku/icon_c.gif" width="16" height="15"  class="tb4"/>
  <strong>λ��:��ҳ</strong>&lt;�����ڲ鿴������ʷ�켣׷�� </a>
  <img src="images/canku/back.gif" width="48" height="20"  class="tb3"/>
  <img src="img/util/back.gif" width="48" height="20"  class="tb3" style="cursor:pointer;" onclick="golist();"/></div>
  <div id="center"><span id="operateDiv" style="color: black;font-size: 13px;background-color: rgb(227,222,28);text-align: center;display: none">���ڻ�ȡ����....</span> </div>
  <div id="center">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" id="bd3">
      <tr>
        <td  height="32" align="left">
        <h3 style="color:#454343; font-size:12px; padding-left:0px; padding-right:0px;">
        <span style="color:#454343; font-size:12px; float:left; padding-left:0px; padding-right:0px;align=">�������ƣ�</span></h3></td>
        <td >     
        <input name="projectName" size="15"   id="projectName" value="${project.projectName }"disabled="disabled" style="text-align: center;"/> 
        </td>
        <td ><h3 style="color:#454343; font-size:12px; padding-left:0px; padding-right:0px; text-align:right;"> �����أ�</h3></td>
        <td >
        <input name="baddress" size="12" value="*****" disabled="disabled" style="text-align: center"/>
        </td>
        <td ><h3 style="color:#454343; font-size:12px; padding-left:0px; padding-right:0px;">Ŀ�ĵأ�</h3></td>
        <td  align="left">
        <input name="eaddress" size="12" value="*****" disabled="disabled" style="text-align: center;"/>
        </td>
         <td  align="left"><h3 style="color:#454343; font-size:12px; padding-left:0px; padding-right:0px;">��ʼʱ�䣺</h3></td>
        <td>
        	<input size="20" name="startTime" value="${startup.btimeStr }"disabled="disabled" style="text-align: center;"/>
        </td>
        <td  align="left"><h3 style="color:#454343; font-size:12px; padding-left:0px; padding-right:0px;">����ʱ�䣺</h3></td>
        <td  align="left">
          <input name="startTime" size="20" value="${startup.etimeDisplay }"disabled="disabled" style="text-align: center;"/></td>
      </tr>
    </table>
  </div>
  <div id="bottom">
  	<div id="map" class="view" style="width: 985px; height: 510px;">
         </div>
  </div>
</div>
<div class="clear"></div>
<div>
  <iframe scrolling="no" src="common/footer2.jsp" width=100% height=43 frameborder=0></iframe >
</div>
</body>
</html>
