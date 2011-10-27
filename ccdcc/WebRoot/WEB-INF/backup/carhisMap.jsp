<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="common/taglib.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>车载历史轨迹回放</title>

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
        	//返回历史启停记录页面
        	function goback(){
        		document.myform.submit() ;
        	}
        </script>
		<script type="text/javascript">
		
			//定义地图对象
            var mapobj = null;			//地图对象    
            var circleobj = null ;		//车载圆对象        
            var ploylineobj = null ;	//曲线对象
            var mytime = null ;
            
            var carobj = null ;			//车载图片对象
            
            
            //定义车载对象
            var projectName = "" ;		//车载的名称    
            var proId = "" ;
            var sid = "" ;
            
            
            //定义数据对象
            var myresult = new Array() ;		// 保存移动车载历史曲线数据源
            var index = 0 ;						//保存当前获取数据的索引
            var allresult =  new Array() ;		// 保存数据库中所有的数据
            var allsize = 0 ;					//保存所有记录的条数
            var pinglv = 0 ;
            
            //控制车载地图的绘制频率
            var isBreak = false ;		//判断当前的状态是否处于暂停状态
            var currentpinlv = 2 ;		//保存当前的绘制频率 2(快) 4(中)  6(慢)	
            var countpinlv = 2 ;		//保存当前的累积数，是否满足当前的频率数，如果满足、则绘制地图曲线、如果不满足，则等待下一次循环
            
            
            //定义当前的经纬度信息
            var current_lng = null ;
            var current_lat = null ;
            
            
            //变量值
            var t1 = null;
            var t2 = null ;
            var t3 = null ;
            var rh = null ;
            var	currentTime = null ;
            
            var alarmStatus = null ;
            
            /**
        	*  	获取一些基本的参数  
        	**/
          	function getParam(){
          		proId = document.getElementById("proId").value ;
             	sid = document.getElementById("sid").value ;
             	projectName = document.getElementById("projectName").value ;
          	}
            
            /**
            *	获取数据库的数据集合
            */
            function getData(){   	
             	real.getHisCarData(proId,sid,{
             		callback:resultHandler,
             		errorHandler:errorHandler,
             		timeout:15000
             	}) ;
            }
           
           /**
           *	处理返回来的结果
           */
            function resultHandler(data){
            	if(data==null || data.length==0){
            		window.alert('当前车载的数据尚未上传...');	
            		document.getElementById("startbutton").disabled = true ;
					document.getElementById("breakbutton").disabled = true ;
					document.getElementById("endbutton").disabled = true ;	
            	}else{
            		allresult = data ;				//保存所有的数据源
            		allsize = data.length ;			//保存所有的数据条数	
            		index = 0 ;						//当前索引的标示归为0 
            	}
            }
            
            /**
            *  异常处理程序
            */
            function errorHandler(msg){
            	window.alert("加载车载历史数据失败..."+msg);
            }
            
            /**
            *	初始化地图对象
            */
            function mapInit(){
                var mapoption = new MMapOptions();
                mapoption.zoom = 15;//设置地图zoom级别  
                mapoption.center = new MLngLat(121.589641,31.248433,2);
                mapoption.toolbar = ROUND; //设置工具条  
                mapoption.toolbarPos = new MPoint(0, 0);
                mapoption.scale = null;
                mapoption.overviewMap = MINIMIZE; //设置鹰眼  
                mapoption.returnCoordType = COORD_TYPE_OFFSET;
                mapobj = new MMap("map", mapoption); //地图初始化  
                
                getParam() ;	//获取车载的基本信息
                
                addLabel();		//加公司标注信息
                
                //这里更改车载的图片  drawCircle() ;
                addMarker() ;  //绘制车载图片
                         
               	startDraw() ;	//开始绘制地图曲线
                
                getData() ;//获取所有的数据集合
                
            }
            
           		 /**
				 * 直接在地图上增加一个label,标注公司的说明信息
				 */	
				function addLabel(){
					var labeloption = new MLabelOptions();
					//设置字体样式
					var fontstyle  = new MFontStyle() ;
					fontstyle.name = "宋体" ;
					fontstyle.size = 12 ;
					fontstyle.color = 0x000000 ;
					fontstyle.bold = true ;
					
					labeloption.fontStyle = fontstyle ;
					
					labeloption.content = " 上海思博源冷链科技有限公司 " ;
					labeloption.hasBorder = true ;
					labeloption.hasBackground = true ;
					labeloption.backgroundColor = 0xFFCCCC ;
					
					labelobj = new MLabel(new MLngLat(121.589641,31.248433,2),labeloption) ;
					
					mapobj.addOverlay(labelobj,false) ;
				}
            
            
            
            /**
			 * 在地图中心画一个形似车载的小圆
			 */
			function drawCircle(){
				var myarray = new Array() ;
				myarray.push(new MLngLat(current_lng,current_lat,2)) ;		//实例化中心坐标、默认为圆似小车载的中心位置
				
				
				var areastyle = new MAreaStyle() ;		//设置圆形区域的样式
				
				areastyle.borderStyle.thickness = 3 ;
				areastyle.borderStyle.color = 0xFFC0CB ;
				areastyle.borderStyle.alpha = 0.7 ;
				areastyle.borderStyle.lineType = LINE_SOLID ;
				
				areastyle.fillStyle.color = 0x006600 ;
				areastyle.fillStyle.alpha = 0.5 ;
				
				var tip = new MTipOptions() ;			//设置圆形区域的提示属性
				tip.title = projectName ;
				tip.content = projectName+"行驶轨迹.." ;			//当前时刻车载的提示信息
				tip.anchor = new MPoint(0,0) ;
				tip.hasShadow = false ;
				
				
				var lableoption = new MLabelOptions() ;	 //设置显示的文本提示
				var fontstyle = new MFontStyle() ;
				fontstyle.name = "宋体"  ;
				fontstyle.size = 16 ;
				fontstyle.color = 0x000000 ;
				fontstyle.bold = true ;
				
				lableoption.fontStyle = fontstyle ;
				lableoption.hasBorder = true ;
				lableoption.hasBackground  = true ;
				lableoption.backgroundColor = 0x0078ff ;
				lableoption.content = "T1: "+t1+" T2: "+t2+"\nT3: "+t3 +"\n时间: "+currentTime;
				
				var areaoption = new MAreaOptions()	 ;	//圆形属性设置
				areaoption.tipOption = tip ;
				areaoption.areaStyle = areastyle ;
				areaoption.labelOption = lableoption ;
				areaoption.labelPosition = new MPoint(0,0) ;
				areaoption.canShowTip = true ;
				areaoption.isDimorphic=true;		//可选项，是否具有二态，默认为false即没有二态  
  				areaoption.dimorphicColor=0x0400FF; 
				
				circleobj = new MCircle(myarray,5,areaoption) ;		//画圆形车载
				circleobj.id= "mycircleobj" ;	
				mapobj.addOverlay(circleobj,false ) ;
					
			}	
			
			
			/**
   			* 增加车载的图片信息 
   			*/
			  	function addMarker(){
					var markeroption = new MMarkerOptions();
					var labeloption = new MLabelOptions();
					var fontstyle = new MFontStyle();
					
					var tip = new MTipOptions();
					
					
					fontstyle.name = "宋体";
					fontstyle.size = 15;
					fontstyle.color = 0x000000;
					fontstyle.bold = true;
					
					
					labeloption.fontStyle = fontstyle;
					labeloption.content = "T1: " + t1 + " T2: "+t2+" \nT3: " + t3 +"\n时间:"+currentTime ;
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
			 * 画车载的移动曲线
			 */
			function startDraw(){			
				
				//设置提示文本
				var tip  = new MTipOptions();
				tip.title = projectName ;
				tip.anchor = new MPoint(0,0);
				tip.content = projectName+" 行驶轨迹..." ;
				
				
				//设置线条的属性
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
			 * 设置地图的中心坐标
			 */
			function setCenter(){
			
				var currentbounds = mapobj.getLngLatBounds();					//获取当前地图的西南、东北角的坐标
				
				if(currentbounds==null)
					return  ;
				
				var leftbutton = currentbounds.southWest ;		     //获取西南角坐标
				var righttop = currentbounds.northEast ;			//获取东北角坐标
				
				//a. 当前坐标的经度小于西南角的经度或者大于东北角的经度
				//b. 当前坐标的纬度小于西南角的纬度或者大于东北角的纬度
				// 只要a、b两个条件满足一个、怎移动地图。否则保持不变
				
			
				
				if(leftbutton.lngX>current_lng || righttop.lngX-0.01 <current_lng 
				|| righttop.latY<current_lat || leftbutton.latY+0.004 > current_lat )
				mapobj.panTo(new MLngLat(current_lng,current_lat,2)) ;
			}
			
			//*********************************处理车载的移动****************************************************
			//获取单个数据
			function getDataFromArr(){
			
				// 判断当前状态是否暂停
				if(isBreak){
					return ;
				}
				
				
				//判断当前的统计变量，是否等于播放频率
				if(countpinlv<currentpinlv){
					countpinlv = countpinlv + 2;
					return ;
				}else{
					countpinlv = 2 ;			//如果是等于当前的播放频率、则移动车载、同时统计变量复位。
				}
			
			
				while(index<allsize){
				
					//沿途各点、增加标注信息
					if(current_lng!=-300 && current_lat!=-300)
               			 addPoint();
				
					var obj = allresult[index] ;		//获取当前一条的记录
					
					//获取经纬度信息
					var lng     = obj.longitude ;
					var lat     = obj.latitude ;
					var lng_dir = obj.longitude_dir ;
					var lat_dir = obj.latitude_dir ;
					
					//获取温湿度信息
					t1 = formatData(obj.ai1,0)  ;
					t2 = formatData(obj.ai2,0) ;
					t3 = formatData(obj.ai3,0);
					rh = formatData(obj.ai4,1);
					
					//获取历史时间和报警状态
					currentTime = new Date(obj.updateTime).toLocaleString() ;	
					alarmStatus = obj.alarmStatus ;
					
					
					
					
					
					current_lng = formatLnglat(lng,0,lng_dir) ;
					current_lat = formatLnglat(lat,1,lat_dir) ;
					
				
					if(current_lng!=-300 && current_lat!=-300){
					
						 myresult.push(new MLngLat(current_lng,current_lat,2)) ;
						 
						 setCenter() ;
						 
						 
						 addMarker() ;  
						// drawCircle() ;  //更改车载的图片
						
               			 startDraw() ;
               			 //如果当前状态为报警，则打开提示信息         			 
               			 
               			 /**
               			 *	由于车载提示报警状态不明显，现在拿掉了，直接由标注的颜色显示表明报警状态
               			 * if(alarmStatus==1)
               			 *	openAlarmTip() ;
               			 **/
               			
               			 	
						 index++ ;
						 break ;
					}
					
					index ++ ;
				}
				
				//如果当前循环的变量大于总记录条数，则执行终止操作
				if(index==allsize){
					doEnd();
				}
			}
			
			
			//**********************************************************************************
			
			
			//点击开始按钮。开始启动
			function doStart(){
				if(isSelected()){
					clearVariable() ;
					
					mytime = window.setInterval("getDataFromArr()",2000) ;
						
					document.getElementById("startbutton").disabled = true ;
					document.getElementById("breakbutton").disabled = false ;
					document.getElementById("endbutton").disabled = false ;
					
					clearAllLayer();	//开始前、先清空所有的覆盖物
					
				}else{
					window.alert("请选择回放的速率!");
					return ;
				}
			}
											
			//点击暂停按钮。暂停车载地图曲线的绘制
			function doBreak(){
				
				//判断当前处于什么状态之中
				if(!isBreak){
						//暂停之后、开始按钮不可用、恢复、停止同样可用呀！		
						document.getElementById("startbutton").disabled = true ;
						document.getElementById("breakbutton").disabled = false ;
						document.getElementById("endbutton").disabled = false ;	
						
						document.getElementById("breakbutton").value = " 继 续 " ;
						isBreak = true ;
						
				}else{
						document.getElementById("startbutton").disabled = true ;
						document.getElementById("breakbutton").disabled = false ;
						document.getElementById("endbutton").disabled = false ;	
						document.getElementById("breakbutton").value = " 暂 停 " ;
						isBreak = false ;
				}
				
						
				
			}
			
			/**
			*	停止回放
			*/
			function doEnd(){
				document.getElementById("startbutton").disabled = false ;
				document.getElementById("breakbutton").disabled = true ;
				document.getElementById("endbutton").disabled = true ;
				window.clearInterval(mytime) ;		
				
				if(isBreak){
					isBreak = false ;
					document.getElementById("breakbutton").value = " 暂 停 " ;
					countpinlv  = 2 ;												//恢复累积频率
				}
								
			}
			
			
			/**
			*	清空地图上的所有覆盖物
			**/
			function clearAllLayer(){
				mapobj.removeAllOverlays();
			}
			
			//初始化变量已经数组集合
			function clearVariable(){
				index  = 0 ;	
				myresult = new Array();
				//var arr = ploylineobj.lnglatArr	 ;
				//arr = new Array();
				//ploylineobj.lnglatArr = new Array() ;
				//window.alert("曲线的数据源大小: "+ploylineobj.lnglatArr.length);
				//mapobj.updateOverlay(ploylineobj) ;
			}
			
			
			/**
			*	判断是否选择单选按钮
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
			
			//设置当前地图绘制的频率
			function setCurrentPinlv(obj){
				currentpinlv = parseInt(obj.value) ;			//设置当前的绘制频率为您选中的值
				countpinlv = 2 ;								//返回累积频率变量
			}
			
			
			
				/**
				*	处理经纬度小数点问题
				*  param(t): 0 经度  1 纬度
				*  param(dir): 代表东西经 、南北纬的方向
				* （系统存储默认 0为南纬 1为北纬  0为东经 1为西经）
				*  map 地图中  默认方向为东经、北纬 相反方向为数值取负
				*/
				
				function formatLnglat(d,t,dir){
					if(d==-300){
						return d ;
					}else{
						if(t==0){	//代表处理的是经度
							if(dir==1)
								return (lnglatHandler(d))*-1 ;
							return lnglatHandler(d) ;
						}else{		//代表处理的是纬度
							if(dir==0)
								return (lnglatHandler(d))*-1 ;
							return lnglatHandler(d) ;
						}
					}
				}
				
				/**
				*	处理经纬度信息121.21212 dd.mm.mm 转化为 dd.ddd
				*/
				function lnglatHandler(lnglat){
					var l = lnglat  ;
					var lq = parseInt(l/100) ;
					var lqq = (l-lq*100)/60 ;
					var lqqq = lq +lqq ;
					return lqqq ;
				}
				
				/**
				*处理温湿度-300问题,以及数据的格式化
				* t: 0代表温度  1 代表湿度
				*/	 
				function formatData(d,t){
					if(d==-300){
						return "----" ;
					}else{
						if(t==0){
							return d+"℃" ;
						}else{
							return d+"%" ;
						}
						
					}		
				}
			
				/**
				*	打开一个报警提示信息
				*/
				function openAlarmTip(){
				  	carobj.option.tipOption.content = "车载当前状态为报警..." ;
					mapobj.updateOverlay(carobj) ;
					mapobj.openOverlayTip("carobj") ;
				//	mapobj.openTip(new MLngLat(current_lng,current_lat,2),testtip) ;	
				} 
				
				
				/**
				*	设置车载图片上点击显示的文本
				**/
				function getTipText(){
					if(alarmStatus==1)
						return "车载当前为报警状态..." ;
					else if (alarmStatus==0)
						return "车载当前为预警状态..." ;
					else 
						return "车载当前为正常状态..." ;
				}
				
				/**
				*	增加所经过点的标注信息
				*/
				function addPoint(){
				
					var tipMsg = "" ;
					
					if(alarmStatus==1)
						tipMsg = "报警" ;
					else if (alarmStatus==0)
						tipMsg = "预警" ;
					else
						tipMsg = "正常" ;
					
				
					var markeroption = new MMarkerOptions();
					var labeloption = new MLabelOptions();
					var fontstyle = new MFontStyle();
					
					var tip = new MTipOptions();
					
					
					fontstyle.name = "宋体";
					fontstyle.size = 20;
					fontstyle.color = 0x000000;
					fontstyle.bold = true;
					
					
					labeloption.fontStyle = fontstyle;
					labeloption.content = "T1: " + t1 + " T2: "+t2+" T3: " + t3 +"\n时间: "+currentTime ;
					labeloption.hasBorder = true;
					labeloption.hasBackground = true;
					labeloption.backgroundColor = 0xffffff;
					
					
					
					tip.title = projectName+" 当前为"+tipMsg+"状态";
					tip.content =" \nT1:" + t1 + "  T2:"+t2+"  T3:" + t3 +"\n时间: "+currentTime  ;
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
					
					//如果报警、则显示红色的报警图片、预警显示为橙色
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
						您正在做的业务是:
						<img src="img/add/club.JPG" alt=">>">
						<font size="2px">车载历史轨迹回放</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="common_button" onclick="javascript:goback();" id="back" type="button">
								返 回
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
									<td>出 发 地：<input name="baddress" value="*****" disabled="disabled" style="text-align: center"/>
									目的地：<input name="eaddress" value="*****" disabled="disabled" style="text-align: center;"/>
									启动时间：<input name="startTime" value="${startup.btimeStr }"disabled="disabled" style="text-align: center;"/>
									结束时间：<input name="startTime" value="${startup.etimeDisplay }"disabled="disabled" style="text-align: center;"/></td>
								</tr>
								<tr>
									<td>车载名称：<input name="projectName"   id="projectName" value="${project.projectName }"disabled="disabled" style="text-align: center;"/>
									播放速率：<input type="radio" name="v" value="2" onfocus="setCurrentPinlv(this);"  checked="checked" >快  
											<input  type="radio" name="v" value="4" onfocus="setCurrentPinlv(this);" /> 中
									 		<input  type="radio" name="v" value="6" onfocus="setCurrentPinlv(this);"/> 慢
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" value=" 开 始 " class="common_button" id = "startbutton" onclick="doStart();"  />
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" value=" 暂 停 " class="common_button" id = "breakbutton" disabled="true" onclick="doBreak();"  />
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" value=" 停 止 " class="common_button" id="endbutton" disabled="true" onclick="doEnd();"/>
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
