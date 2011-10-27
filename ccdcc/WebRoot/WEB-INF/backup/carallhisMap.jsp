<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="common/taglib.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>车载历史轨迹追溯</title>

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
            var ploylineobj = null ;	//曲线对象
            var mytime = null ;
            
            
            
            //定义车载对象
            var projectName = "" ;		//车载的名称    
            var proId = "" ;
            var sid = "" ;
            
            
            //定义数据对象
            var myresult = new Array() ;		// 保存移动车载历史曲线数据源
            var index = 0 ;						//保存当前获取数据的索引
            var allresult =  new Array() ;		// 保存数据库中所有的数据
            var allsize = 0 ;					//保存所有记录的条数
            

            
            
            //定义当前的经纬度信息
            var current_lng = null ;
            var current_lat = null ;
            
            
            //变量值
            var t1 = null;
            var t2 = null ;
            var t3 = null ;
            var rh = null ;
            var current_time = null ;
            
            var alarmStatus = null ;
            
            var isBeginAddress = true ;
            
            /**
            *	获取数据库的数据集合
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
           *	处理返回来的结果
           */
            function resultHandler(data){
            	if(data==null || data.length==0){
            		document.getElementById('operateDiv').innerHTML = '当前车载的数据尚未上传...';	
            	}else{
            		allresult = data ;				//保存所有的数据源
            		allsize = data.length ;			//保存所有的数据条数	
            		index = 0 ;						//当前索引的标示归为0 
            		
           		 mytime =  window.setInterval("getDataFromArr()",1);				//获取单个数据
           		 document.getElementById('operateDiv').innerHTML = "正在绘制车载历史轨迹..." ;
            	}
            }
            
            /**
            *  异常处理程序
            */
            function errorHandler(){
            	document.getElementById('operateDiv').innerHTML ="读取车载历史数据失败...";
            }
            
            /**
            *	初始化地图对象
            */
            function mapInit(){
                var mapoption = new MMapOptions();
                mapoption.zoom = 10;//设置地图zoom级别  
                mapoption.center = new MLngLat(121.589641,31.248433,2);
                mapoption.toolbar = ROUND; //设置工具条  
                mapoption.toolbarPos = new MPoint(0, 0);
                mapoption.scale = null;
                mapoption.overviewMap = MINIMIZE; //设置鹰眼  
                mapoption.returnCoordType = COORD_TYPE_OFFSET;
                mapobj = new MMap("map", mapoption); //地图初始化  
                                     
                getData() ;//获取所有的数据集合
                
            }
            
             	 /**
				  * 直接在地图上增加一个label,标注公司的说明信息
			      */	
				function addAddressLabel(ln,la,msg){
					var labeloption = new MLabelOptions();
					//设置字体样式
					var fontstyle  = new MFontStyle() ;
					fontstyle.name = "宋体" ;
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
				|| righttop.latY<current_lat || leftbutton.latY+0.004 > current_lat ){
					
						mapobj.panTo(new MLngLat(current_lng,current_lat,2)) ;		
				}
					
			}
			
			
			//获取单个数据
			function getDataFromArr(){	
				
				while(index<allsize){
					var obj = allresult[index] ;
					
					
					var lng     = null ;
					var lat     = null 				
					var lng_dir = obj.longitude_dir ;
					var lat_dir = obj.latitude_dir ;
					
					//获取温湿度信息
					t1 = formatData(obj.ai1,0)  ;
					t2 = formatData(obj.ai2,0) ;
					t3 = formatData(obj.ai3,0);
					rh = formatData(obj.ai4,1);
					
					//获取时间和状态
					current_time = new Date(obj.updateTime).toLocaleString();				
					alarmStatus = obj.alarmStatus ;
					
					
					lng = formatLnglat(obj.longitude,0,lng_dir) ;
					lat = formatLnglat(obj.latitude,1,lat_dir) ;
					
				
					if(lng!=-300 && lat!=-300){
							
						//获取有效的经纬度信息
						current_lng = lng ;
						current_lat = lat ;
						 myresult.push(new MLngLat(current_lng,current_lat,2)) ;
						 
						 //默认显示为起始点位置
						 
							 setCenter() ;
							 if(isBeginAddress)
							 	addAddressLabel(current_lng,current_lat,"起始点") ;
						 	
						 	isBeginAddress = false ;
					
						//画曲线
               			 startDraw() ;
               			 
	 
               			 //根据报警状态、判断是否要加报警信息
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
			 *	判断当前数据是否已经绘制完毕
			 */
			 
			 function isFinish(){
			 	if(index==allsize){
        					addAddressLabel(current_lng,current_lat,"目的地");
						    window.clearInterval(mytime) ;
						    document.getElementById('operateDiv').innerHTML ="车载历史轨迹绘制完成..." ;
        		}
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
					addAlarm();
				}
				
				/**
				*		增加一个报警提示标注信息
				*/
				function addAlarm(){
				
					var tipMsg  = "" ;
					
					if(alarmStatus==1)
						tipMsg = "报警状态" ;
					else 
						tipMsg = "预警状态" ;
				
					var markeroption = new MMarkerOptions();
					var labeloption = new MLabelOptions();
					var fontstyle = new MFontStyle();
					
					var tip = new MTipOptions();
					
					
					fontstyle.name = "宋体";
					fontstyle.size = 20;
					fontstyle.color = 0x000000;
					fontstyle.bold = true;
					
					
					labeloption.fontStyle = fontstyle;
					labeloption.content = "T1: " + t1 + " T2: "+t2+" T3: " + t3 +"\n时间: "+current_time ;
					labeloption.hasBorder = true;
					labeloption.hasBackground = true;
					labeloption.backgroundColor = 0xffffff;
					
					tip.title = projectName+tipMsg;
					tip.content =" \nT1:" + t1 + "  T2:"+t2+"  T3:" + t3 +"\n时间: "+current_time  ;
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
						您正在做的业务是:
						<img src="img/add/club.JPG" alt=">>">
						<font size="2px">车载历史轨迹追溯</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="common_button" style="cursor:pointer;" onclick="javascript:goback();" id="back" type="button">
								返 回
							</button>	
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="operateDiv" style="color: black;font-size: 13px;background-color: rgb(227,222,28);text-align: center;">正在获取数据....</span>			
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
									车载名称：<input name="projectName"   id="projectName" value="${project.projectName }"disabled="disabled" style="text-align: center;"/>
									出 发 地：<input name="baddress" value="*****" disabled="disabled" style="text-align: center"/>
									目的地：<input name="eaddress" value="*****" disabled="disabled" style="text-align: center;"/>
									启动时间：<input name="startTime" value="${startup.btimeStr }"disabled="disabled" style="text-align: center;"/>
									结束时间：<input name="startTime" value="${startup.etimeDisplay }"disabled="disabled" style="text-align: center;"/></td>
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
