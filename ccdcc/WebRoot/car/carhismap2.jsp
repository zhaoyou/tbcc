<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>������ʷ��ͼ�켣�ط�</title>
<link rel="Shortcut Icon" href="img/add/logo.ico" />
<link href="css/chezai/input1.css" rel="stylesheet" type="text/css" />
<link href="css/map/tc.css" rel="stylesheet" type="text/css" />


		<script type='text/javascript' src='dwr/interface/real.js'></script>
 		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		
<script type="text/javascript"
			src="http://app.mapabc.com/apis?&t=flashmap&v=2.3.1&key=30d03c0ba5c60850be655229ede0f001c17751ecce42fb81df6077eaa1031fb01a4a18c4a07047f0">
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
            
            var unloadStatus = null;
            
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
                mapoption.center = new MLngLat(121.550902,31.235708,2);
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
					
					labelobj = new MLabel(new MLngLat(121.550902,31.235708,2),labeloption) ;
					
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
					labeloption.content = "T1: " + t1 + " T2: "+t2+" \nT3: " + t3 +(unloadStatus==0?" ����ж��":"")+"\nʱ��:"+currentTime ;
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
					if(unloadStatus!=0){
						markeroption.imageUrl = "http://www.thermoberg.com/ccdcc/img/map/car2.gif" ;
					}else{
						markeroption.imageUrl = "http://www.thermoberg.com/ccdcc/img/map/carxiehuo.gif" ;
					}
					
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
					
					unloadStatus = obj.unloadStatus;
					//alert( "dadfaerfe"+unloadStatus+" "+(unloadStatus==0?"ж��":"����") );
					
					
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
						return "���ص�ǰΪ����״̬..." +(unloadStatus==0?" ����ж��":"");
					else if (alarmStatus==0)
						return "���ص�ǰΪԤ��״̬..." +(unloadStatus==0?" ����ж��":"");
					else 
						return "���ص�ǰΪ����״̬..." +(unloadStatus==0?" ����ж��":"");
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
					
					
					
					tip.title = projectName+" ��ǰΪ"+tipMsg+"״̬"+(unloadStatus==0?"  ����ж��":"");
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
					if(unloadStatus!=0){
						if(alarmStatus==1)
							markeroption.imageUrl = "http://www.thermoberg.com/ccdcc/img/map/alarm2.gif" ;			
						if(alarmStatus==0)
							markeroption.imageUrl = "http://www.thermoberg.com/ccdcc/img/map/preAlarm.gif" ;
					}else{
						if(alarmStatus==1){
							markeroption.imageUrl = "http://www.thermoberg.com/ccdcc/img/map/alarm3.gif" ;	
						}else if(alarmStatus==0){
							markeroption.imageUrl = "http://www.thermoberg.com/ccdcc/img/map/alarm4.gif" ;
						}else{
							markeroption.imageUrl = "http://www.thermoberg.com/ccdcc/img/map/alarm5.gif" ;
						}
					}
					
					var carobjmarker = new MMarker(new MLngLat(current_lng,current_lat,2 ),markeroption);
					carobjmarker.id = "carobjmarker"+index;
					mapobj.addOverlay(carobjmarker);
				}
            
            function golist(){
            	document.myform.submit() ;
            }
</script>
</head>
<body onload="mapInit();">
<div>
  <iframe scrolling="no" src="common/header2.jsp" width=100% height=126 frameborder=0></iframe >
</div>
<form action="startup.do" method="post" name="myform" id="myform">
							<input type="hidden" id="ope" name="ope" value="doStartUpCar"/>
							<input type="hidden" id="branchId" name="branchId" value="${param.branchId }"/>
							<input type="hidden" id="proId" name="proId" value="${param.proId }"/>
							<input type="hidden" id="sid"  name="sid" value="${startup.id }"/>
							<input type="hidden" id="time1" name="time1" value="${param.t1 }" />
							<input type="hidden" id="time2" name="time2" value="${param.t2 }" />
</form>


<div id="right">
  <div id="top"><a href="#"><img src="images/canku/icon_c.gif" width="16" height="15"  class="tb4"/>
  <strong>λ��:��ҳ</strong>&lt;�����ڲ鿴������ʷ�켣�ط� </a>
  <img src="images/canku/back.gif" width="48" height="20"  class="tb3"/>
  <img src="img/util/back.gif" width="48" height="20"  class="tb3" alt="����" style="cursor: pointer" onclick="javascript:golist();"/></div>
  <div id="center">
    <table width="85%" id="bd3">
      <tr>
        <td width="72" height="22" align="left"><h3 style="color:#454343; font-size:12px; padding-left:0px; padding-right:0px;">�����أ�</h3></td>
        <td width="125">
        <input type="text" class="input" value="***" maxlength="9"  disabled="disabled" style="width:120px;text-align: center"  />
        </td>
        <td width="72"><h3 style="color:#454343; font-size:12px; padding-left:0px; padding-right:0px; text-align:right;">Ŀ�ĵأ�</h3></td>
        <td width="159">
        <input type="text" class="input" value="***" maxlength="9"  disabled="disabled" style="width:120px;text-align: center" />
        
        </td>
        <td width="72"><h3 style="color:#454343; font-size:12px; padding-left:0px; padding-right:0px;">����ʱ�䣺</h3></td>
        <td width="131" align="left">
        <input type="text" class="input" value="${startup.btimeStr }" disabled="disabled"  style="width:120px;text-align: center" /></td>
        
        <td width="72" align="left"><h3 style="color:#454343; font-size:12px; padding-left:0px; padding-right:0px;">����ʱ�䣺</h3></td>
        <td width="200" align="left">
        
        <input type="text" class="input" value="${startup.etimeDisplay }" disabled="disabled" maxlength="9" style="width:120px;text-align: center" />
        
        </td>
      </tr>
    </table>
  </div>
  <div id="center">
    <table width="78%" id="bd3">
      <tr>
        <td width="76" height="22"><h3 style="color:#454343; font-size:12px; padding-left:0px; padding-right:0px;">�������ƣ�</h3></td>
        <td width="140"><input type="text" class="input" name="projectName"   id="projectName" value="${project.projectName }"disabled="disabled" style="text-align: center;"/></td>
        <td width="76"><h3 style="color:#454343; font-size:12px; padding-left:0px; padding-right:0px; text-align:right;">�������ʣ�</h3></td>
        <td width="58">
        <input type="radio" name="v" value="2" onfocus="setCurrentPinlv(this);"  checked="checked" />��  </td>
        <td width="38"><input  type="radio" name="v" value="4" onfocus="setCurrentPinlv(this);" /> ��</td>
        <td width="67"><input  type="radio" name="v" value="6" onfocus="setCurrentPinlv(this);"/> ��</td>
        <td width="80"><input type="button" value=" ��ʼ " class="common_button" id = "startbutton" onclick="doStart();"  /></td>
        <td width="79"><input type="button" value=" ��ͣ " class="common_button" id = "breakbutton" disabled="true" onclick="doBreak();"  /></td>
        <td width="85"><input type="button" value=" ֹͣ " class="common_button" id="endbutton" disabled="true" onclick="doEnd();"/></td>
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
