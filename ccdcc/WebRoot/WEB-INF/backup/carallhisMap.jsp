<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="common/taglib.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>������ʷ�켣׷��</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link href="css/style.css" rel="stylesheet" type="text/css">
		<LINK href="css/mainWin.css" type=text/css media=screen
			rel=stylesheet>
		<LINK href="css/mainWin2.css" type=text/css media=screen
			rel=stylesheet>
		<LINK href="css/desktop.css" type=text/css media=screen
			rel=stylesheet>
		<link rel="Shortcut Icon" href="img/add/logo.ico">
		<script src="script/common.js"></script>
		<script type="text/javascript" src="script/titleTime.js"></script>
		<script type="text/javascript"
			src="http://app.mapabc.com/apis?&t=flashmap&v=2.3.1&key=de69148208cf12939f72a018a26b8423aa23752271a878349d006460faafca20f1b818ea84ebb9a6">
        </script>
        
        <script type='text/javascript' src='dwr/interface/real.js'></script>
 		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
			
        <script type="text/javascript">
        	//������ʷ��ͣ��¼ҳ��
        	function goback(){
        		document.myform.submit() ;
        	}
        </script>
		<script type="text/javascript">
		
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
                mapoption.zoom = 10;//���õ�ͼzoom����  
                mapoption.center = new MLngLat(121.589641,31.248433,2);
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
			
				var currentbounds = mapobj.getLngLatBounds();					//��ȡ��ǰ��ͼ�����ϡ������ǵ�����
				
				if(currentbounds==null)
					return  ;
				
				var leftbutton = currentbounds.southWest ;		     //��ȡ���Ͻ�����
				var righttop = currentbounds.northEast ;			//��ȡ����������
				
				//a. ��ǰ����ľ���С�����Ͻǵľ��Ȼ��ߴ��ڶ����ǵľ���
				//b. ��ǰ�����γ��С�����Ͻǵ�γ�Ȼ��ߴ��ڶ����ǵ�γ��
				// ֻҪa��b������������һ�������ƶ���ͼ�����򱣳ֲ���
				
			
				
				if(leftbutton.lngX>current_lng || righttop.lngX-0.01 <current_lng 
				|| righttop.latY<current_lat || leftbutton.latY+0.004 > current_lat ){
					
						mapobj.panTo(new MLngLat(current_lng,current_lat,2)) ;		
				}
					
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
					
					
					lng = formatLnglat(obj.longitude,0,lng_dir) ;
					lat = formatLnglat(obj.latitude,1,lat_dir) ;
					
				
					if(lng!=-300 && lat!=-300){
							
						//��ȡ��Ч�ľ�γ����Ϣ
						current_lng = lng ;
						current_lat = lat ;
						 myresult.push(new MLngLat(current_lng,current_lat,2)) ;
						 
						 //Ĭ����ʾΪ��ʼ��λ��
						 
							 setCenter() ;
							 if(isBeginAddress)
							 	addAddressLabel(current_lng,current_lat,"��ʼ��") ;
						 	
						 	isBeginAddress = false ;
					
						//������
               			 startDraw() ;
               			 
	 
               			 //���ݱ���״̬���ж��Ƿ�Ҫ�ӱ�����Ϣ
             			if(alarmStatus==1 || alarmStatus==0)
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
        					addAddressLabel(current_lng,current_lat,"Ŀ�ĵ�");
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
						tipMsg = "����״̬" ;
					else 
						tipMsg = "Ԥ��״̬" ;
				
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
					
					if(alarmStatus==0)
						markeroption.imageUrl = "http://www.thermoberg.com/ccdcc/img/map/preAlarm.gif" ;
					if(alarmStatus==1)
						markeroption.imageUrl = "http://www.thermoberg.com/ccdcc/img/map/alarm2.gif"
					
					var carobjmarker = new MMarker(new MLngLat(current_lng,current_lat,2 ),markeroption);
					carobjmarker.id = "carobjmarker"+index;
					mapobj.addOverlay(carobjmarker);
				}
  	 
        </script>
	</head>

	<body onload="showtime(),mapInit()">
		<jsp:include page="header.jsp" flush="true"></jsp:include>
		<table width="990" border="0" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#CCCCCC" bordercolor="#FFFFFF"
			bordercolordark="#FFFFFF">
			<td width="990" height="550" colspan="3" valign="top"
				background="img/stock_index_08.gif">
				<div id="main" style="width: 990px; height: 100%; float: left">
					<div class="page_title">
						����������ҵ����:
						<img src="img/add/club.JPG" alt=">>">
						<font size="2px">������ʷ�켣׷��</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="common_button" style="cursor:pointer;" onclick="javascript:goback();" id="back" type="button">
								�� ��
							</button>	
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="operateDiv" style="color: black;font-size: 13px;background-color: rgb(227,222,28);text-align: center;">���ڻ�ȡ����....</span>			
					</div>
					<form name="myform" action="startup.do?ope=doStartUpCar"
						method="post">
							<input type="hidden" id="branchId" name="branchId" value="${param.branchId }">
							<input type="hidden" id="proId" name="proId" value="${param.proId }"/>
							<input type="hidden" id="sid"  name="sid" value="${startup.id }"/>
							<input type="hidden" id="time1" name="time1" value="${param.time1 }" />
							<input type="hidden" id="time2" name="time2" value="${param.time2 }" />
							<table>
								<tr>
									<td>
									�������ƣ�<input name="projectName"   id="projectName" value="${project.projectName }"disabled="disabled" style="text-align: center;"/>
									�� �� �أ�<input name="baddress" value="*****" disabled="disabled" style="text-align: center"/>
									Ŀ�ĵأ�<input name="eaddress" value="*****" disabled="disabled" style="text-align: center;"/>
									����ʱ�䣺<input name="startTime" value="${startup.btimeStr }"disabled="disabled" style="text-align: center;"/>
									����ʱ�䣺<input name="startTime" value="${startup.etimeDisplay }"disabled="disabled" style="text-align: center;"/></td>
								</tr>
								<tr>
									<td>
									</td>
								</tr>
								<tr></tr>
							</table>
						
					</form>
					<div id="map" class="view"
						style="width: 990px; height: 500px; border:solid; border-color: gray">
					</div>
				</div>
			</td>
		</table>
		<jsp:include page="footer.jsp" flush="true"></jsp:include>
	</body>
</html>
