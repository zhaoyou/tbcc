<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="common/taglib.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>������ʷ�켣�ط�</title>

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
            var circleobj = null ;		//����Բ����        
            var ploylineobj = null ;	//���߶���
            var mytime = null ;
            
            var carobj = null ;			//����ͼƬ����
            
            
            //���峵�ض���
            var projectName = "" ;		//���ص�����    
            var proId = "" ;
            var sid = "" ;
            
            
            //�������ݶ���
            var myresult = new Array() ;		// �����ƶ�������ʷ��������Դ
            var index = 0 ;						//���浱ǰ��ȡ���ݵ�����
            var allresult =  new Array() ;		// �������ݿ������е�����
            var allsize = 0 ;					//�������м�¼������
            var pinglv = 0 ;
            
            //���Ƴ��ص�ͼ�Ļ���Ƶ��
            var isBreak = false ;		//�жϵ�ǰ��״̬�Ƿ�����ͣ״̬
            var currentpinlv = 2 ;		//���浱ǰ�Ļ���Ƶ�� 2(��) 4(��)  6(��)	
            var countpinlv = 2 ;		//���浱ǰ���ۻ������Ƿ����㵱ǰ��Ƶ������������㡢����Ƶ�ͼ���ߡ���������㣬��ȴ���һ��ѭ��
            
            
            //���嵱ǰ�ľ�γ����Ϣ
            var current_lng = null ;
            var current_lat = null ;
            
            
            //����ֵ
            var t1 = null;
            var t2 = null ;
            var t3 = null ;
            var rh = null ;
            var	currentTime = null ;
            
            var alarmStatus = null ;
            
            /**
        	*  	��ȡһЩ�����Ĳ���  
        	**/
          	function getParam(){
          		proId = document.getElementById("proId").value ;
             	sid = document.getElementById("sid").value ;
             	projectName = document.getElementById("projectName").value ;
          	}
            
            /**
            *	��ȡ���ݿ�����ݼ���
            */
            function getData(){   	
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
            		window.alert('��ǰ���ص�������δ�ϴ�...');	
            		document.getElementById("startbutton").disabled = true ;
					document.getElementById("breakbutton").disabled = true ;
					document.getElementById("endbutton").disabled = true ;	
            	}else{
            		allresult = data ;				//�������е�����Դ
            		allsize = data.length ;			//�������е���������	
            		index = 0 ;						//��ǰ�����ı�ʾ��Ϊ0 
            	}
            }
            
            /**
            *  �쳣�������
            */
            function errorHandler(msg){
            	window.alert("���س�����ʷ����ʧ��..."+msg);
            }
            
            /**
            *	��ʼ����ͼ����
            */
            function mapInit(){
                var mapoption = new MMapOptions();
                mapoption.zoom = 15;//���õ�ͼzoom����  
                mapoption.center = new MLngLat(121.589641,31.248433,2);
                mapoption.toolbar = ROUND; //���ù�����  
                mapoption.toolbarPos = new MPoint(0, 0);
                mapoption.scale = null;
                mapoption.overviewMap = MINIMIZE; //����ӥ��  
                mapoption.returnCoordType = COORD_TYPE_OFFSET;
                mapobj = new MMap("map", mapoption); //��ͼ��ʼ��  
                
                getParam() ;	//��ȡ���صĻ�����Ϣ
                
                addLabel();		//�ӹ�˾��ע��Ϣ
                
                //������ĳ��ص�ͼƬ  drawCircle() ;
                addMarker() ;  //���Ƴ���ͼƬ
                         
               	startDraw() ;	//��ʼ���Ƶ�ͼ����
                
                getData() ;//��ȡ���е����ݼ���
                
            }
            
           		 /**
				 * ֱ���ڵ�ͼ������һ��label,��ע��˾��˵����Ϣ
				 */	
				function addLabel(){
					var labeloption = new MLabelOptions();
					//����������ʽ
					var fontstyle  = new MFontStyle() ;
					fontstyle.name = "����" ;
					fontstyle.size = 12 ;
					fontstyle.color = 0x000000 ;
					fontstyle.bold = true ;
					
					labeloption.fontStyle = fontstyle ;
					
					labeloption.content = " �Ϻ�˼��Դ�����Ƽ����޹�˾ " ;
					labeloption.hasBorder = true ;
					labeloption.hasBackground = true ;
					labeloption.backgroundColor = 0xFFCCCC ;
					
					labelobj = new MLabel(new MLngLat(121.589641,31.248433,2),labeloption) ;
					
					mapobj.addOverlay(labelobj,false) ;
				}
            
            
            
            /**
			 * �ڵ�ͼ���Ļ�һ�����Ƴ��ص�СԲ
			 */
			function drawCircle(){
				var myarray = new Array() ;
				myarray.push(new MLngLat(current_lng,current_lat,2)) ;		//ʵ�����������ꡢĬ��ΪԲ��С���ص�����λ��
				
				
				var areastyle = new MAreaStyle() ;		//����Բ���������ʽ
				
				areastyle.borderStyle.thickness = 3 ;
				areastyle.borderStyle.color = 0xFFC0CB ;
				areastyle.borderStyle.alpha = 0.7 ;
				areastyle.borderStyle.lineType = LINE_SOLID ;
				
				areastyle.fillStyle.color = 0x006600 ;
				areastyle.fillStyle.alpha = 0.5 ;
				
				var tip = new MTipOptions() ;			//����Բ���������ʾ����
				tip.title = projectName ;
				tip.content = projectName+"��ʻ�켣.." ;			//��ǰʱ�̳��ص���ʾ��Ϣ
				tip.anchor = new MPoint(0,0) ;
				tip.hasShadow = false ;
				
				
				var lableoption = new MLabelOptions() ;	 //������ʾ���ı���ʾ
				var fontstyle = new MFontStyle() ;
				fontstyle.name = "����"  ;
				fontstyle.size = 16 ;
				fontstyle.color = 0x000000 ;
				fontstyle.bold = true ;
				
				lableoption.fontStyle = fontstyle ;
				lableoption.hasBorder = true ;
				lableoption.hasBackground  = true ;
				lableoption.backgroundColor = 0x0078ff ;
				lableoption.content = "T1: "+t1+" T2: "+t2+"\nT3: "+t3 +"\nʱ��: "+currentTime;
				
				var areaoption = new MAreaOptions()	 ;	//Բ����������
				areaoption.tipOption = tip ;
				areaoption.areaStyle = areastyle ;
				areaoption.labelOption = lableoption ;
				areaoption.labelPosition = new MPoint(0,0) ;
				areaoption.canShowTip = true ;
				areaoption.isDimorphic=true;		//��ѡ��Ƿ���ж�̬��Ĭ��Ϊfalse��û�ж�̬  
  				areaoption.dimorphicColor=0x0400FF; 
				
				circleobj = new MCircle(myarray,5,areaoption) ;		//��Բ�γ���
				circleobj.id= "mycircleobj" ;	
				mapobj.addOverlay(circleobj,false ) ;
					
			}	
			
			
			/**
   			* ���ӳ��ص�ͼƬ��Ϣ 
   			*/
			  	function addMarker(){
					var markeroption = new MMarkerOptions();
					var labeloption = new MLabelOptions();
					var fontstyle = new MFontStyle();
					
					var tip = new MTipOptions();
					
					
					fontstyle.name = "����";
					fontstyle.size = 15;
					fontstyle.color = 0x000000;
					fontstyle.bold = true;
					
					
					labeloption.fontStyle = fontstyle;
					labeloption.content = "T1: " + t1 + " T2: "+t2+" \nT3: " + t3 +"\nʱ��:"+currentTime ;
					labeloption.hasBorder = true;
					labeloption.hasBackground = true;
					labeloption.backgroundColor = 0xffffff;
					
					tip.title = projectName;
					tip.content = getTipText();
					
					var fs = new MFontStyle();
					fs.color = 0xffffff;
					tip.contentFontStyle = fs;
					
					markeroption.labelOption = labeloption;
					markeroption.labelPosition = new MPoint(17, -48);
					
					markeroption.tipOption = tip;
					markeroption.imageAlign = 8;
					markeroption.canShowTip = true;
					markeroption.imageUrl = "http://www.thermoberg.com/ccdcc/img/map/car2.gif" ;
					
					carobj = new MMarker(new MLngLat(current_lng,current_lat,2 ),markeroption);
					carobj.id = "carobj";
					mapobj.addOverlay(carobj);
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
				|| righttop.latY<current_lat || leftbutton.latY+0.004 > current_lat )
				mapobj.panTo(new MLngLat(current_lng,current_lat,2)) ;
			}
			
			//*********************************�����ص��ƶ�****************************************************
			//��ȡ��������
			function getDataFromArr(){
			
				// �жϵ�ǰ״̬�Ƿ���ͣ
				if(isBreak){
					return ;
				}
				
				
				//�жϵ�ǰ��ͳ�Ʊ������Ƿ���ڲ���Ƶ��
				if(countpinlv<currentpinlv){
					countpinlv = countpinlv + 2;
					return ;
				}else{
					countpinlv = 2 ;			//����ǵ��ڵ�ǰ�Ĳ���Ƶ�ʡ����ƶ����ء�ͬʱͳ�Ʊ�����λ��
				}
			
			
				while(index<allsize){
				
					//��;���㡢���ӱ�ע��Ϣ
					if(current_lng!=-300 && current_lat!=-300)
               			 addPoint();
				
					var obj = allresult[index] ;		//��ȡ��ǰһ���ļ�¼
					
					//��ȡ��γ����Ϣ
					var lng     = obj.longitude ;
					var lat     = obj.latitude ;
					var lng_dir = obj.longitude_dir ;
					var lat_dir = obj.latitude_dir ;
					
					//��ȡ��ʪ����Ϣ
					t1 = formatData(obj.ai1,0)  ;
					t2 = formatData(obj.ai2,0) ;
					t3 = formatData(obj.ai3,0);
					rh = formatData(obj.ai4,1);
					
					//��ȡ��ʷʱ��ͱ���״̬
					currentTime = new Date(obj.updateTime).toLocaleString() ;	
					alarmStatus = obj.alarmStatus ;
					
					
					
					
					
					current_lng = formatLnglat(lng,0,lng_dir) ;
					current_lat = formatLnglat(lat,1,lat_dir) ;
					
				
					if(current_lng!=-300 && current_lat!=-300){
					
						 myresult.push(new MLngLat(current_lng,current_lat,2)) ;
						 
						 setCenter() ;
						 
						 
						 addMarker() ;  
						// drawCircle() ;  //���ĳ��ص�ͼƬ
						
               			 startDraw() ;
               			 //�����ǰ״̬Ϊ�����������ʾ��Ϣ         			 
               			 
               			 /**
               			 *	���ڳ�����ʾ����״̬�����ԣ������õ��ˣ�ֱ���ɱ�ע����ɫ��ʾ��������״̬
               			 * if(alarmStatus==1)
               			 *	openAlarmTip() ;
               			 **/
               			
               			 	
						 index++ ;
						 break ;
					}
					
					index ++ ;
				}
				
				//�����ǰѭ���ı��������ܼ�¼��������ִ����ֹ����
				if(index==allsize){
					doEnd();
				}
			}
			
			
			//**********************************************************************************
			
			
			//�����ʼ��ť����ʼ����
			function doStart(){
				if(isSelected()){
					clearVariable() ;
					
					mytime = window.setInterval("getDataFromArr()",2000) ;
						
					document.getElementById("startbutton").disabled = true ;
					document.getElementById("breakbutton").disabled = false ;
					document.getElementById("endbutton").disabled = false ;
					
					clearAllLayer();	//��ʼǰ����������еĸ�����
					
				}else{
					window.alert("��ѡ��طŵ�����!");
					return ;
				}
			}
											
			//�����ͣ��ť����ͣ���ص�ͼ���ߵĻ���
			function doBreak(){
				
				//�жϵ�ǰ����ʲô״̬֮��
				if(!isBreak){
						//��֮ͣ�󡢿�ʼ��ť�����á��ָ���ֹͣͬ������ѽ��		
						document.getElementById("startbutton").disabled = true ;
						document.getElementById("breakbutton").disabled = false ;
						document.getElementById("endbutton").disabled = false ;	
						
						document.getElementById("breakbutton").value = " �� �� " ;
						isBreak = true ;
						
				}else{
						document.getElementById("startbutton").disabled = true ;
						document.getElementById("breakbutton").disabled = false ;
						document.getElementById("endbutton").disabled = false ;	
						document.getElementById("breakbutton").value = " �� ͣ " ;
						isBreak = false ;
				}
				
						
				
			}
			
			/**
			*	ֹͣ�ط�
			*/
			function doEnd(){
				document.getElementById("startbutton").disabled = false ;
				document.getElementById("breakbutton").disabled = true ;
				document.getElementById("endbutton").disabled = true ;
				window.clearInterval(mytime) ;		
				
				if(isBreak){
					isBreak = false ;
					document.getElementById("breakbutton").value = " �� ͣ " ;
					countpinlv  = 2 ;												//�ָ��ۻ�Ƶ��
				}
								
			}
			
			
			/**
			*	��յ�ͼ�ϵ����и�����
			**/
			function clearAllLayer(){
				mapobj.removeAllOverlays();
			}
			
			//��ʼ�������Ѿ����鼯��
			function clearVariable(){
				index  = 0 ;	
				myresult = new Array();
				//var arr = ploylineobj.lnglatArr	 ;
				//arr = new Array();
				//ploylineobj.lnglatArr = new Array() ;
				//window.alert("���ߵ�����Դ��С: "+ploylineobj.lnglatArr.length);
				//mapobj.updateOverlay(ploylineobj) ;
			}
			
			
			/**
			*	�ж��Ƿ�ѡ��ѡ��ť
			*/
			
			function isSelected(){
				var su = document.getElementsByName("v");
				for(var k =0 ;k<su.length ;k++){
					if(su[k].checked==true){
						pinglv = su[k].value ;
						return true ;
					}
				}
				return false ;
			}
			
			//���õ�ǰ��ͼ���Ƶ�Ƶ��
			function setCurrentPinlv(obj){
				currentpinlv = parseInt(obj.value) ;			//���õ�ǰ�Ļ���Ƶ��Ϊ��ѡ�е�ֵ
				countpinlv = 2 ;								//�����ۻ�Ƶ�ʱ���
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
				  	carobj.option.tipOption.content = "���ص�ǰ״̬Ϊ����..." ;
					mapobj.updateOverlay(carobj) ;
					mapobj.openOverlayTip("carobj") ;
				//	mapobj.openTip(new MLngLat(current_lng,current_lat,2),testtip) ;	
				} 
				
				
				/**
				*	���ó���ͼƬ�ϵ����ʾ���ı�
				**/
				function getTipText(){
					if(alarmStatus==1)
						return "���ص�ǰΪ����״̬..." ;
					else if (alarmStatus==0)
						return "���ص�ǰΪԤ��״̬..." ;
					else 
						return "���ص�ǰΪ����״̬..." ;
				}
				
				/**
				*	������������ı�ע��Ϣ
				*/
				function addPoint(){
				
					var tipMsg = "" ;
					
					if(alarmStatus==1)
						tipMsg = "����" ;
					else if (alarmStatus==0)
						tipMsg = "Ԥ��" ;
					else
						tipMsg = "����" ;
					
				
					var markeroption = new MMarkerOptions();
					var labeloption = new MLabelOptions();
					var fontstyle = new MFontStyle();
					
					var tip = new MTipOptions();
					
					
					fontstyle.name = "����";
					fontstyle.size = 20;
					fontstyle.color = 0x000000;
					fontstyle.bold = true;
					
					
					labeloption.fontStyle = fontstyle;
					labeloption.content = "T1: " + t1 + " T2: "+t2+" T3: " + t3 +"\nʱ��: "+currentTime ;
					labeloption.hasBorder = true;
					labeloption.hasBackground = true;
					labeloption.backgroundColor = 0xffffff;
					
					
					
					tip.title = projectName+" ��ǰΪ"+tipMsg+"״̬";
					tip.content =" \nT1:" + t1 + "  T2:"+t2+"  T3:" + t3 +"\nʱ��: "+currentTime  ;
					tip.contentFontStyle.size = 15 ;
					
				//	if(alarmStatus!=1){						
				//		tip.contentFontStyle.color = 0xffffff;
				//	}else{					
				//		tip.contentFontStyle.color = 0xFF0000;
				//	}
					
				//	markeroption.labelOption = labeloption;
				//	markeroption.labelPosition = new MPoint(17, -34);
					
					markeroption.tipOption = tip;
					markeroption.imageAlign = 8;
					markeroption.canShowTip = true;
					
					//�������������ʾ��ɫ�ı���ͼƬ��Ԥ����ʾΪ��ɫ
					if(alarmStatus==1)
						markeroption.imageUrl = "http://www.thermoberg.com/ccdcc/img/map/alarm2.gif" ;			
					if(alarmStatus==0)
						markeroption.imageUrl = "http://www.thermoberg.com/ccdcc/img/map/preAlarm.gif" ;
					
					
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
						<font size="2px">������ʷ�켣�ط�</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="common_button" onclick="javascript:goback();" id="back" type="button">
								�� ��
							</button>				
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
									<td>�� �� �أ�<input name="baddress" value="*****" disabled="disabled" style="text-align: center"/>
									Ŀ�ĵأ�<input name="eaddress" value="*****" disabled="disabled" style="text-align: center;"/>
									����ʱ�䣺<input name="startTime" value="${startup.btimeStr }"disabled="disabled" style="text-align: center;"/>
									����ʱ�䣺<input name="startTime" value="${startup.etimeDisplay }"disabled="disabled" style="text-align: center;"/></td>
								</tr>
								<tr>
									<td>�������ƣ�<input name="projectName"   id="projectName" value="${project.projectName }"disabled="disabled" style="text-align: center;"/>
									�������ʣ�<input type="radio" name="v" value="2" onfocus="setCurrentPinlv(this);"  checked="checked" >��  
											<input  type="radio" name="v" value="4" onfocus="setCurrentPinlv(this);" /> ��
									 		<input  type="radio" name="v" value="6" onfocus="setCurrentPinlv(this);"/> ��
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" value=" �� ʼ " class="common_button" id = "startbutton" onclick="doStart();"  />
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" value=" �� ͣ " class="common_button" id = "breakbutton" disabled="true" onclick="doBreak();"  />
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" value=" ͣ ֹ " class="common_button" id="endbutton" disabled="true" onclick="doEnd();"/>
									</td>
								</tr>
								<tr></tr>
							</table>
						
					</form>
					<div id="map" class="view"
						style="width: 990px; height: 480px; border:solid; border-color: gray">
					</div>
				</div>
			</td>
		</table>
		<jsp:include page="footer.jsp" flush="true"></jsp:include>
	</body>
</html>
