<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>����ʵʱ���ݵ�ͼ��ʾ</title>
<link href="css/map/tc.css" rel="stylesheet" type="text/css" />
<link href="css/chezai/input1.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="img/add/logo.ico" />

		<script src="script/common.js"></script>	
		
		<script type='text/javascript' src='dwr/interface/real.js'></script>
 		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>	

<script type="text/javascript" src="http://app.mapabc.com/apis?&t=flashmap&v=2.3.1&key=30d03c0ba5c60850be655229ede0f001c17751ecce42fb81df6077eaa1031fb01a4a18c4a07047f0">
</script>
<script type="text/javascript">

	function golist(url,operate){
		document.getElementById('myform').action = url ;
		document.getElementById('ope').value = operate ;
		document.myform.submit();
	}
	
	 var mapobj = null;				//��ͼ����
            var circleobj = null  ;			//Բ�ζ���
			var ploylineobj = null ;		//���߶���
			var mytime = null ;				//����һ��ʱ�䶨ʱ��
			
			var carobj = null ;				//����ͼƬ����
			
			//���峵�ص����ݱ���
			var alarmState = null ;			//1 ����  2 ����   0 Ԥ��
			var connectionState = null ;	//1 �Ͽ�	 2 ����
			var runState = null ;			//1ֹͣ   3 ����
			var currentTime  = null ;
			
			//���峵��ж��״̬��־
			var unloadStatus = null;
			
			//��ͼ��ʾ������
			var projectId = null ;
			var projectName = null ;
			var moveInterval = null ;
			
			//��γ����Ϣ
			var lng = 121.555158  ; // ��˾����dbtype:12135.37846 		
			var lat = 31.233470  ; // ��˾γ�� dbtype:3114.90598 
			
			//�����ͼ��ǰ�ľ�γ����Ϣ
			var current_lng =	121.550902 ;  	//��˾����   (121.589641 ����)
			var current_lat =   31.235708 ;	//��˾γ��  	(31.248433 ����)
			
			//���徭γ�ȵķ���
			var lng_dir = null ;
			var lat_dir = null ;
			
			var myresult = new Array() ;			//�������еľ�γ����Ϣ���Ա��ڻ�������
		//	myresult.push(new MLngLat(current_lng,current_lat,2)) ;	//���Ĭ��Ϊ��ͼ��������
			
			//ģ�����
			var x =0 ,y = 0  ;
			var t1 = null ;
			var t2 = null ;
			var t3 = null ;
			var rh = null ;
			
			
			//��������Ļ�����������
			var BASEDISTANCE = 20   ;
			
			//�����ǵ�һ�λ�ʵ�����ƹ켣����
			var FIRSTDRAW = true ;
			
            /**
            *	��ʼ����ͼ����
            */
            function mapInit(){
                var mapoption = new MMapOptions();
                mapoption.zoom = 16;										//���õ�ͼzoom����  
                mapoption.center = new MLngLat(current_lng,current_lat,2);	//���õ�ͼ�������꾭γ��
                mapoption.toolbar = ROUND; 									//�������Ź���������ʽ  	
                mapoption.toolbarPos = new MPoint(0, 0);					//�������Ź�������λ��
                mapoption.scale = null;										//���ñ����ߵ��Ƿ����
                mapoption.overviewMap = MINIMIZE; 							//����ӥ�۵���ʾ״̬  				
                mapoption.returnCoordType = COORD_TYPE_OFFSET;				//���õ�ͼ����ı��뷽ʽ
                mapoption.centerCross = HIDE ;
                mapobj = new MMap("map", mapoption); 						//��ͼ��ʼ�� 
                 
                getParam() ;						//��ȡ���ع��̵ı�ţ��Ѿ����ơ�ˢ��ʱ����
                getData() ; 						//��ȡ����
                
               mytime =  window.setInterval("getData()",parseInt(moveInterval)) ;	//���ö�ʱ����ÿ��10s���� 
           
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
				tip.content = getTipMessage() ;			//��ǰʱ�̳��ص���ʾ��Ϣ
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
				lableoption.content = "T1:"+t1+" T2:"+t2+"\nT3:"+t3+" RH1:"+rh ;
				
				var areaoption = new MAreaOptions()	 ;	//Բ����������
				areaoption.tipOption = tip ;
				areaoption.areaStyle = areastyle ;
				areaoption.labelOption = lableoption ;
				areaoption.labelPosition = new MPoint(0,0) ;
				areaoption.canShowTip = true ;
				areaoption.isDimorphic= true;		//��ѡ��Ƿ���ж�̬��Ĭ��Ϊfalse��û�ж�̬  
  				areaoption.dimorphicColor=0x0400FF; 
				
				circleobj = new MCircle(myarray,20,areaoption) ;		//��Բ�γ���
				circleobj.id= "mycircleobj" ;	
				mapobj.addOverlay(circleobj,false) ;
					
			}		
			
			
			/**
   			* ����һ����ע��Ϣ 
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
					labeloption.content = "T1:" + t1 + "  T2:"+t2+" \nT3:" + t3 +(unloadStatus==0?" ����ж��":"");
					labeloption.hasBorder = true;
					labeloption.hasBackground = true;
					
					//������ʾ��Ϣ�ı�����ɫ
					
					if(alarmState==1)
						labeloption.backgroundColor = 0xFF0000;
					else if (alarmState==0)
						labeloption.backgroundColor = 0xFF6633;
					else 
						labeloption.backgroundColor = 0xffffff;
						
						
						
					
					tip.title = projectName;
					tip.content = getTipMessage() ;	
					
					var fs = new MFontStyle();
					fs.color = 0xffffff;
					tip.contentFontStyle = fs;
					
					markeroption.labelOption = labeloption;
					markeroption.labelPosition = new MPoint(17, -34);
					
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
				*		�����صı仯��ָ����baseDistance��Χ��ʱ
				**/
				function updateMarker(){
					
					if(carobj==null)
						return  ;
				
					//������ʾ�ı�
					carobj.option.labelOption.content = "T1:" + t1 + "  T2:"+t2+" \nT3:" + t3+(unloadStatus==0?"  ����ж��":"")  ;
					
					//�����ı��ı�����ɫ
					if(alarmState==1)
						carobj.option.labelOption.backgroundColor = 0xFF0000;
					else if (alarmState==0)
						carobj.option.labelOption.backgroundColor = 0xFF6633;
					else 
						carobj.option.labelOption.backgroundColor = 0xffffff;
						
					
					if(unloadStatus!=0){
						carobj.option.imageUrl = "http://www.thermoberg.com/ccdcc/img/map/car2.gif" ;
					}else{
						carobj.option.imageUrl = "http://www.thermoberg.com/ccdcc/img/map/carxiehuo.gif" ;
					}
					
					
					//���³��ص���ʾ�ı�
					carobj.option.tipOption.content = getTipMessage() ;
					
					mapobj.updateOverlay(carobj) ;
				}
			
			/**
			 * �����ص��ƶ�����
			 */
			function startDraw(){
				
		//		myresult.push(new MLngLat(lng+x,lat+y,2))	 ;		//��������Դ
				
				//������ʾ�ı�
				var tip  = new MTipOptions();
				tip.title = projectName ;
				tip.anchor = new MPoint(0,0);
				tip.content = projectName+" ����ʻ�켣..." ;
				
				
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
				
				//ԭ��ֱ�������õ�ͼ���ĵ㡢�����Ż���ֻ���ڳ��ظ������Ƴ���ǰ��ͼ��������ʱ�����ƶ���ͼ
				
				var currentbounds = mapobj.getLngLatBounds();					//��ȡ��ǰ��ͼ�����ϡ������ǵ�����
				
				if(currentbounds==null)
					return  ;
				
				var leftbutton = currentbounds.southWest ;		     //��ȡ���Ͻ�����
				var righttop = currentbounds.northEast ;			//��ȡ����������
				
				//a. ��ǰ����ľ���С�����Ͻǵľ��Ȼ��ߴ��ڶ����ǵľ���
				//b. ��ǰ�����γ��С�����Ͻǵ�γ�Ȼ��ߴ��ڶ����ǵ�γ��
				// ֻҪa��b������������һ�������ƶ���ͼ�����򱣳ֲ���
				
				//window.alert(current_lng);
				//window.alert(current_lat);
				//window.alert("SW: "+leftbutton.lngX);
				//window.alert("SW: "+leftbutton.latY);
				//window.alert("NE: "+righttop.lngX);
				//window.alert("NE: "+righttop.latY);
				
				//window.alert("С�ھ��ȣ�"+(leftbutton.lngX>current_lng));
				//window.alert("���ھ��ȣ�"+(righttop.lngX <current_lng));
				//window.alert("����γ�ȣ�" +(righttop.latY<current_lat));
				//window.alert("С��γ��: "+(leftbutton.latY > current_lat));
				
				if(leftbutton.lngX>current_lng || righttop.lngX-0.01 <current_lng 
				|| righttop.latY<current_lat || leftbutton.latY+0.004 > current_lat )	
				     mapobj.panTo(new MLngLat(current_lng,current_lat,2)) ;
			
			}
			
			
			
			/**
			*	��ȡ����ֵ projectId ,projectName
			*/
			function getParam(){
				var index = document.getElementById("proId").selectedIndex ;
			  	projectId = document.getElementById("proId").value ;
			  	moveInterval = document.getElementById("myinterval").value ;
			  	projectName = document.getElementById("proId").options[index].text ;
			}
			
			/**
			  *��ȡ��ǰ���ص�ʵʱ����
			  */
			  
			  function getData(){
			  if(projectId==null || projectId==""){
			  	window.alert("��ѡ����Ҫ�鿴�ĳ���!");
			  	return ;
			  }
			  	real.getCarToMap(projectId,{
			  		callback:resultHandler,
			  		errorHandler:errHandler,
			  		timeout:15000
			  	});
			  
			  }
			  
			  
			  /**
			  *	������ʵʱ����
			  */
			  function resultHandler(data){
			  
			  	
			  	if(data!=null){
			  	
			  		var ISDRAWMARKER = false ;
			  	
			  		document.getElementById("net").style.display = "none" ;
			  	
			  		//����İ汾�Ǹ���������ʾ���أ��������Ǵ��ڣ����������ݿ���Ϊ��
			  		
			  		if(data.ai1==null || data.ai2==null || data.ai3==null || data.ai4==null || 
			  		 data.alarmStatus==null || data.connectStatus==null || data.runStatus==null ||
			  		 data.updateTime==null){
			  		 	t1 = "----" ;
			  		 	t2 = "----" ;
			  		 	t3 = "----" ;
			  		 	rh = "----" ;
			  		 	connectionState = 1 ;
			  		 	runState = 1 ;
			  		 	alarmState = 2 ;
			  		 	currentTime = new Date().toLocaleString() ;
			  		 	lat_dir = 1 ;
			  		 	lng_dir = 0 ;
			  		 	
			  		 	lng = -300 ;
			  		 	lat = -300 ;
			  		 	
			  		 	unloadStatus = -1;
			  		 }else{
			  	
			  		//��ʽ����ʪ��ֵ
			  		t1 =formatData(parseFloat(data.ai1),0) ;
			  		t2 =formatData(parseFloat(data.ai2),0) ;
			  		t3 =formatData(parseFloat(data.ai3),0) ;
			  		rh =formatData(parseFloat(data.ai4),1) ;
			  		
			  		//��ȡ���ص�״̬��Ϣ���Լ����ص�ǰʱ��
			  		alarmState = data.alarmStatus ;
			  		connectionState = data.connectStatus ;
			  		runState = data.runStatus ;
			  		currentTime = data.updateTime ;
			  		
			  		//��ȡ����ж��״̬
			  		unloadStatus = data.unloadStatus;
			  		
			  		//��ȡ���ؾ�γ����Ϣ
					lat_dir = data.latitude_dir ;
			  		lng_dir = data.longitude_dir ;  
			  		
			  		lng =formatLnglat(data.longitude,0) ;
			  		lat =formatLnglat(data.latitude,1) ;
			  	
			  	}	
			  				
			  		
			  		
			  		
			  		//�����ǰgps��Ϣ����������ӵ�����
			  		if(lng!=-300 && lat!=-300){
			  		 	
			  		 	//�ж�ǰ�����㾭γ������ʵ�ʾ����Ƿ���ڻ����ľ���
			  		 	if(calcjuli([lat,lng],[current_lat,current_lng])>BASEDISTANCE)
			  		 	{
			  		 	
			  		 		ISDRAWMARKER = true ;
			  		 		
			  		 	 	current_lng = lng ;
			  		 		current_lat = lat ;
			  		 	
			  		 		//���Ǻ������ӵ��жϳ�����ͬһ���ص���ʾ����
			  				 myresult.push(new MLngLat(current_lng,current_lat,2)) ;
			  				 
			  				
			  			 
			  			 }else{				//�����ָ���ķ�Χ�ڡ�������������
			  			 	updateMarker();
			  			 }
			  			 
			  			//���µ�ͼ�켣������Դmyresult
			  			mapobj.updateOverlay(ploylineobj);
	  			 
			  		}	
			  		
			  		//���õ�ͼ����������
			  		setCenter() ;
			  		
			  		//�����ߣ�ֻ�е�һ�βŻ��ƣ��������������
			  		if(FIRSTDRAW){
			  			startDraw() ;
			  		}
			  			
			  		//�����أ�ͼ���������һ����Χ�����»���
			  		if(ISDRAWMARKER){
			  			addMarker() ;		//drawCircle() ;
			  		}
			  			
			  		//������ʾ����ʾ��Ϣ
			  		getDivTipMessage();	


					FIRSTDRAW = false ;
			  		 
			  	}else{
			  		window.alert("�޷���ȡ��ǰ����ʵʱ����!");
			  	}
			 }
			  
			  /**
			  *	���������
			  */
			  function errHandler(e){
			  	document.getElementById("net").style.display = "block" ;
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
				*	����γ��С��������
				*  param(t): 0 ����  1 γ��
				* ��ϵͳ�洢Ĭ�� 0Ϊ��γ 1Ϊ��γ  0Ϊ���� 1Ϊ������
				*  map ��ͼ��  Ĭ�Ϸ���Ϊ��������γ �෴����Ϊ��ֵȡ��
				*/
				
				function formatLnglat(d,t){
					if(d==-300){
						return d ;
					}else{
						if(t==0){	//��������Ǿ���
							if(lng_dir==1)
								return (lnglatHandler(d))*-1 ;
							return lnglatHandler(d) ;
						}else{		//���������γ��
							if(lat_dir==0)
								return (lnglatHandler(d))*-1 ;
							return lnglatHandler(d) ;
						}
					}
				}
				
				
				/**
				*	����γ����Ϣ121.21212 dd.mm.mmm  ת��Ϊ dd.dddddd
				*/
				function lnglatHandler(lnglat){
					var l = lnglat  ;
					var lq = parseInt(l/100) ;
					var lqq = (l-lq*100)/60 ;
					var lqqq = lq +lqq ;
					return lqqq ;
				}
				
			/**
			 * �ڳ������Ĵ�һ����ʾ��Ϣ�����泵�ر���
			 */
			function openAlarmTip(){
				
				if(connectionState==2){
					var str = "" ;
					
					if(alarmState==2)
						return  ;
					
					if(alarmState==1)
						str = "��ǰ���ش��ڱ���״̬..." ;
					if(alarmState==0)
						str = "��ǰ���ش���Ԥ��״̬..." ;
						
					if(str=="")
						return  ;
						
					carobj.option.tipOption.content = str ;
					mapobj.updateOverlay(carobj) ;
					mapobj.openOverlayTip("carobj") ;
				}
				
				
				
			}
			
			/**
			*		��ȡ���ص���ʾ��Ϣ
			*/
			function getTipMessage(){
				var str = "" ;
				if(connectionState==2){		
						if(alarmState==1){
							str = "��ǰ���ش��ڱ���״̬..."+(unloadStatus==0?" ����ж��":"");
						}else if (alarmState==0){
							str = "��ǰ���ش���Ԥ��״̬..."+(unloadStatus==0?" ����ж��":"") ;
						}else{
							str = "��ǰ���ش�������״̬..."+(unloadStatus==0?" ����ж��":"") ;
						}
				}else{
					str = "��ǰ�����Ѿ��Ͽ�����..." ;
				}
				return str ;	
			}
			
			
			/**
			*	��������״̬�ı����Ѿ�����״̬�ı�
			*/
			function getDivTipMessage(){
				
				setDivText() ;   //��������״̬������״̬
				
				//���ڵ�����ʾ���Ч�������ԡ������õ��ˡ���Ϊ����������ʾ�ı�����ɫ��					
				//openAlarmTip();  //�ж��Ƿ�Ҫ����ʾ��Ԥ���򱨾�				
			}
			
			/**
			*	���õ�ͼ�Ϸ�״̬��div��ʾ��״̬�ı�
			*/
			function setDivText(){
				var msg = "" ;
				var msg2 = "" ;
				
				if(connectionState==2){
					msg = " �� �� " ;
				}else{
					msg = "<font color='red'> �� �� </font>" ;
				}
				
				
				
				if(runState==2){
					msg2 = " �� �� " ;
				}else{
					msg2 = "<font color='red'> ͣ ֹ </font>" ;
				}
				
				document.getElementById("connectioninfo").innerHTML = msg ;
				document.getElementById("runinfo").innerHTML = msg2 ;
				
			}
			
			
			/**
			*	�ύҳ�棬�鿴��һ��������ʻ�켣...
			*/
			function gotomap(){
				document.getElementById('myform').action = 'realcar.do' ;
				document.getElementById('ope').value = "doRealMap" ;
				document.myform.submit() ;
			}
			
			
			 /**
		  * ������ֵ����Ƕ�
		  * @param {Object} d ��γ����ֵ
		  */
		 function calcjiaodu(d){
		 	return d * Math.PI/180.0 ;	
		 }
		 
		 /**
		  * ����������γ����ֵ�������
		  * @param {Object} f(lat,lng)	��ʼ��λ��	
		  * @param {Object} s(lat,lng)	��ֹ��λ��
		  */
		 function calcjuli(f,s){
		 	var flng = calcjiaodu(f[1]);
			var flat = calcjiaodu(f[0]);
			var slng = calcjiaodu(s[1]);
			var slat = calcjiaodu(s[0]);
			
			var r = Math.sin(flat)*Math.sin(slat);
			r += Math.cos(flat)*Math.cos(slat)*Math.cos(flng-slng);
			return Math.acos(r)*EARTH_RADIUS_METER ;
			
		 }
		 
		 var EARTH_RADIUS_METER = 6378137.0 ;
			  
            
</script>

</head>
<body onload="mapInit();">
<div>
  <iframe scrolling="no" src="common/header2.jsp" width=100% height=126 frameborder=0></iframe >
</div>
<form id="myform" name="myform" action="" method="post">
<input type="hidden" name="branchId" id="branchId" value="${param.branchId }"/>
<input type="hidden" name="ope" id="ope" value=""/>
 <input type="hidden" name="myinterval" id="myinterval" value="10000"/>
<div id="right">
  <div id="top"><a href="#"><img src="images/canku/icon_c.gif" width="16" height="15"  class="tb4"/><strong>λ��:��ҳ</strong>&lt;�����ڲ鿴����ʵʱ���ݵ�ͼ��ʾ</a>
  <img src="img/util/back.gif" style="cursor: pointer;" onclick="javascript:golist('realcar.do','toRealData');" width="48" height="20"  class="tb3"/></div>
  <div id="center">
    <table width="95%" id="bd3">
      <tr>
        <td width="10%"><h3 style="color:#454343; font-size:12px;  width:80px;">��ѡ���أ�</h3></td>
        <td width="10%" align="left">
        	<select name="proId" id="proId" onchange="gotomap();">
								<c:forEach var="proj" items="${carprjList}">		
									<option value='${proj.projectId}' ${param.proId==proj.projectId?"selected=selected":"" }>${proj.projectName }</option>"
								</c:forEach>
			</select>  
         </td>
        <td width="50%">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <span style="color:#454343; font-size:12px; padding-left:0px; padding-right:0px;">��ǰ��������״̬:</span><span id="connectioninfo"><font color='red'> �� �� </font></span>  
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <span style="color:#454343; font-size:12px; padding-left:0px; padding-right:0px;">��ǰ��������״̬:</span><span id="runinfo"><font color='red'> ͣ ֹ </font></span>     
        </td>
        <td width="30%" >
        	<div id="net" style="background-color:rgb(227,222,28);font-size:12px;display:none;height: 6px"> &nbsp;&nbsp;��ǰ���緱æ,�޷���ȡ��ʵʱ����...</div>
        </td>
      </tr>
      
      	
      
    </table>
  </div>
  
  
  <div id="bottom">
	<div id="map" class="view" style="width: 985px; height: 510px;">
         </div>
  </div>
  
</div>
</form>
<div class="clear"></div>
<div>
  <iframe scrolling="no" src="common/footer2.jsp" width=100% height=43 frameborder=0></iframe >
</div>
</body>
</html>
