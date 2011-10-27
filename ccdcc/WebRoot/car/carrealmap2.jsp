<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>车载实时数据地图显示</title>
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
	
	 var mapobj = null;				//地图对象
            var circleobj = null  ;			//圆形对象
			var ploylineobj = null ;		//曲线对象
			var mytime = null ;				//定义一个时间定时器
			
			var carobj = null ;				//车载图片对象
			
			//定义车载的数据变量
			var alarmState = null ;			//1 报警  2 正常   0 预警
			var connectionState = null ;	//1 断开	 2 连接
			var runState = null ;			//1停止   3 启动
			var currentTime  = null ;
			
			//定义车载卸货状态标志
			var unloadStatus = null;
			
			//地图显示的属性
			var projectId = null ;
			var projectName = null ;
			var moveInterval = null ;
			
			//经纬度信息
			var lng = 121.555158  ; // 公司经度dbtype:12135.37846 		
			var lat = 31.233470  ; // 公司纬度 dbtype:3114.90598 
			
			//定义地图当前的经纬度信息
			var current_lng =	121.550902 ;  	//公司经度   (121.589641 建银)
			var current_lat =   31.235708 ;	//公司纬度  	(31.248433 建银)
			
			//定义经纬度的方向
			var lng_dir = null ;
			var lat_dir = null ;
			
			var myresult = new Array() ;			//保存所有的经纬度信息，以便于绘制曲线
		//	myresult.push(new MLngLat(current_lng,current_lat,2)) ;	//起点默认为地图中心坐标
			
			//模拟变量
			var x =0 ,y = 0  ;
			var t1 = null ;
			var t2 = null ;
			var t3 = null ;
			var rh = null ;
			
			
			//定义两点的基本的相差距离
			var BASEDISTANCE = 20   ;
			
			//标明是第一次绘实例化制轨迹对象
			var FIRSTDRAW = true ;
			
            /**
            *	初始化地图对象
            */
            function mapInit(){
                var mapoption = new MMapOptions();
                mapoption.zoom = 16;										//设置地图zoom级别  
                mapoption.center = new MLngLat(current_lng,current_lat,2);	//设置地图中心坐标经纬度
                mapoption.toolbar = ROUND; 									//设置缩放工具条的样式  	
                mapoption.toolbarPos = new MPoint(0, 0);					//设置缩放工具条的位置
                mapoption.scale = null;										//设置比例尺的是否存在
                mapoption.overviewMap = MINIMIZE; 							//设置鹰眼的显示状态  				
                mapoption.returnCoordType = COORD_TYPE_OFFSET;				//设置地图坐标的编码方式
                mapoption.centerCross = HIDE ;
                mapobj = new MMap("map", mapoption); 						//地图初始化 
                 
                getParam() ;						//获取车载工程的编号，已经名称、刷新时间间隔
                getData() ; 						//获取数据
                
               mytime =  window.setInterval("getData()",parseInt(moveInterval)) ;	//设置定时器，每隔10s调用 
           
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
				tip.content = getTipMessage() ;			//当前时刻车载的提示信息
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
				lableoption.content = "T1:"+t1+" T2:"+t2+"\nT3:"+t3+" RH1:"+rh ;
				
				var areaoption = new MAreaOptions()	 ;	//圆形属性设置
				areaoption.tipOption = tip ;
				areaoption.areaStyle = areastyle ;
				areaoption.labelOption = lableoption ;
				areaoption.labelPosition = new MPoint(0,0) ;
				areaoption.canShowTip = true ;
				areaoption.isDimorphic= true;		//可选项，是否具有二态，默认为false即没有二态  
  				areaoption.dimorphicColor=0x0400FF; 
				
				circleobj = new MCircle(myarray,20,areaoption) ;		//画圆形车载
				circleobj.id= "mycircleobj" ;	
				mapobj.addOverlay(circleobj,false) ;
					
			}		
			
			
			/**
   			* 增加一个标注信息 
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
					labeloption.content = "T1:" + t1 + "  T2:"+t2+" \nT3:" + t3 +(unloadStatus==0?" 正在卸货":"");
					labeloption.hasBorder = true;
					labeloption.hasBackground = true;
					
					//增加提示信息的背景颜色
					
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
				*		当车载的变化在指定的baseDistance范围内时
				**/
				function updateMarker(){
					
					if(carobj==null)
						return  ;
				
					//更新显示文本
					carobj.option.labelOption.content = "T1:" + t1 + "  T2:"+t2+" \nT3:" + t3+(unloadStatus==0?"  正在卸货":"")  ;
					
					//更新文本的背景颜色
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
					
					
					//更新车载的提示文本
					carobj.option.tipOption.content = getTipMessage() ;
					
					mapobj.updateOverlay(carobj) ;
				}
			
			/**
			 * 画车载的移动曲线
			 */
			function startDraw(){
				
		//		myresult.push(new MLngLat(lng+x,lat+y,2))	 ;		//增加数据源
				
				//设置提示文本
				var tip  = new MTipOptions();
				tip.title = projectName ;
				tip.anchor = new MPoint(0,0);
				tip.content = projectName+" 的行驶轨迹..." ;
				
				
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
				
				//原先直接是设置地图中心点、现在优化。只有在车载覆盖物移除当前地图所在区域时，才移动地图
				
				var currentbounds = mapobj.getLngLatBounds();					//获取当前地图的西南、东北角的坐标
				
				if(currentbounds==null)
					return  ;
				
				var leftbutton = currentbounds.southWest ;		     //获取西南角坐标
				var righttop = currentbounds.northEast ;			//获取东北角坐标
				
				//a. 当前坐标的经度小于西南角的经度或者大于东北角的经度
				//b. 当前坐标的纬度小于西南角的纬度或者大于东北角的纬度
				// 只要a、b两个条件满足一个、怎移动地图。否则保持不变
				
				//window.alert(current_lng);
				//window.alert(current_lat);
				//window.alert("SW: "+leftbutton.lngX);
				//window.alert("SW: "+leftbutton.latY);
				//window.alert("NE: "+righttop.lngX);
				//window.alert("NE: "+righttop.latY);
				
				//window.alert("小于经度："+(leftbutton.lngX>current_lng));
				//window.alert("大于经度："+(righttop.lngX <current_lng));
				//window.alert("大于纬度：" +(righttop.latY<current_lat));
				//window.alert("小于纬度: "+(leftbutton.latY > current_lat));
				
				if(leftbutton.lngX>current_lng || righttop.lngX-0.01 <current_lng 
				|| righttop.latY<current_lat || leftbutton.latY+0.004 > current_lat )	
				     mapobj.panTo(new MLngLat(current_lng,current_lat,2)) ;
			
			}
			
			
			
			/**
			*	获取参数值 projectId ,projectName
			*/
			function getParam(){
				var index = document.getElementById("proId").selectedIndex ;
			  	projectId = document.getElementById("proId").value ;
			  	moveInterval = document.getElementById("myinterval").value ;
			  	projectName = document.getElementById("proId").options[index].text ;
			}
			
			/**
			  *获取当前车载的实时数据
			  */
			  
			  function getData(){
			  if(projectId==null || projectId==""){
			  	window.alert("请选择需要查看的车载!");
			  	return ;
			  }
			  	real.getCarToMap(projectId,{
			  		callback:resultHandler,
			  		errorHandler:errHandler,
			  		timeout:15000
			  	});
			  
			  }
			  
			  
			  /**
			  *	处理车载实时数据
			  */
			  function resultHandler(data){
			  
			  	
			  	if(data!=null){
			  	
			  		var ISDRAWMARKER = false ;
			  	
			  		document.getElementById("net").style.display = "none" ;
			  	
			  		//后面的版本是根据配置显示车载，车载总是存在，其他的数据可能为空
			  		
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
			  	
			  		//格式化温湿度值
			  		t1 =formatData(parseFloat(data.ai1),0) ;
			  		t2 =formatData(parseFloat(data.ai2),0) ;
			  		t3 =formatData(parseFloat(data.ai3),0) ;
			  		rh =formatData(parseFloat(data.ai4),1) ;
			  		
			  		//获取车载的状态信息、以及车载当前时间
			  		alarmState = data.alarmStatus ;
			  		connectionState = data.connectStatus ;
			  		runState = data.runStatus ;
			  		currentTime = data.updateTime ;
			  		
			  		//获取车载卸货状态
			  		unloadStatus = data.unloadStatus;
			  		
			  		//获取车载经纬度信息
					lat_dir = data.latitude_dir ;
			  		lng_dir = data.longitude_dir ;  
			  		
			  		lng =formatLnglat(data.longitude,0) ;
			  		lat =formatLnglat(data.latitude,1) ;
			  	
			  	}	
			  				
			  		
			  		
			  		
			  		//如果当前gps信息正常，则添加到集合
			  		if(lng!=-300 && lat!=-300){
			  		 	
			  		 	//判断前后两点经纬度相差的实际距离是否大于基本的距离
			  		 	if(calcjuli([lat,lng],[current_lat,current_lng])>BASEDISTANCE)
			  		 	{
			  		 	
			  		 		ISDRAWMARKER = true ;
			  		 		
			  		 	 	current_lng = lng ;
			  		 		current_lat = lat ;
			  		 	
			  		 		//这是后面增加的判断车载在同一个地点显示问题
			  				 myresult.push(new MLngLat(current_lng,current_lat,2)) ;
			  				 
			  				
			  			 
			  			 }else{				//如果在指定的范围内、则更新这个参数
			  			 	updateMarker();
			  			 }
			  			 
			  			//更新地图轨迹的数据源myresult
			  			mapobj.updateOverlay(ploylineobj);
	  			 
			  		}	
			  		
			  		//设置地图的中心坐标
			  		setCenter() ;
			  		
			  		//画曲线，只有第一次才绘制，其他的情况更新
			  		if(FIRSTDRAW){
			  			startDraw() ;
			  		}
			  			
			  		//画车载，图像。如果超过一定范围，重新绘制
			  		if(ISDRAWMARKER){
			  			addMarker() ;		//drawCircle() ;
			  		}
			  			
			  		//设置显示的提示信息
			  		getDivTipMessage();	


					FIRSTDRAW = false ;
			  		 
			  	}else{
			  		window.alert("无法获取当前车载实时数据!");
			  	}
			 }
			  
			  /**
			  *	错误处理程序
			  */
			  function errHandler(e){
			  	document.getElementById("net").style.display = "block" ;
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
				*	处理经纬度小数点问题
				*  param(t): 0 经度  1 纬度
				* （系统存储默认 0为南纬 1为北纬  0为东经 1为西经）
				*  map 地图中  默认方向为东经、北纬 相反方向为数值取负
				*/
				
				function formatLnglat(d,t){
					if(d==-300){
						return d ;
					}else{
						if(t==0){	//代表处理的是经度
							if(lng_dir==1)
								return (lnglatHandler(d))*-1 ;
							return lnglatHandler(d) ;
						}else{		//代表处理的是纬度
							if(lat_dir==0)
								return (lnglatHandler(d))*-1 ;
							return lnglatHandler(d) ;
						}
					}
				}
				
				
				/**
				*	处理经纬度信息121.21212 dd.mm.mmm  转化为 dd.dddddd
				*/
				function lnglatHandler(lnglat){
					var l = lnglat  ;
					var lq = parseInt(l/100) ;
					var lqq = (l-lq*100)/60 ;
					var lqqq = lq +lqq ;
					return lqqq ;
				}
				
			/**
			 * 在车载中心打开一个提示信息，报告车载报警
			 */
			function openAlarmTip(){
				
				if(connectionState==2){
					var str = "" ;
					
					if(alarmState==2)
						return  ;
					
					if(alarmState==1)
						str = "当前车载处于报警状态..." ;
					if(alarmState==0)
						str = "当前车载处于预警状态..." ;
						
					if(str=="")
						return  ;
						
					carobj.option.tipOption.content = str ;
					mapobj.updateOverlay(carobj) ;
					mapobj.openOverlayTip("carobj") ;
				}
				
				
				
			}
			
			/**
			*		获取车载的提示信息
			*/
			function getTipMessage(){
				var str = "" ;
				if(connectionState==2){		
						if(alarmState==1){
							str = "当前车载处于报警状态..."+(unloadStatus==0?" 正在卸货":"");
						}else if (alarmState==0){
							str = "当前车载处于预警状态..."+(unloadStatus==0?" 正在卸货":"") ;
						}else{
							str = "当前车载处于正常状态..."+(unloadStatus==0?" 正在卸货":"") ;
						}
				}else{
					str = "当前车载已经断开连接..." ;
				}
				return str ;	
			}
			
			
			/**
			*	设置启动状态文本、已经连接状态文本
			*/
			function getDivTipMessage(){
				
				setDivText() ;   //设置连接状态和启动状态
				
				//由于弹出提示框的效果不明显、所以拿掉了。改为更改数据提示的背景颜色。					
				//openAlarmTip();  //判断是否要打开提示，预警或报警				
			}
			
			/**
			*	设置地图上方状态栏div显示的状态文本
			*/
			function setDivText(){
				var msg = "" ;
				var msg2 = "" ;
				
				if(connectionState==2){
					msg = " 连 接 " ;
				}else{
					msg = "<font color='red'> 断 开 </font>" ;
				}
				
				
				
				if(runState==2){
					msg2 = " 启 动 " ;
				}else{
					msg2 = "<font color='red'> 停 止 </font>" ;
				}
				
				document.getElementById("connectioninfo").innerHTML = msg ;
				document.getElementById("runinfo").innerHTML = msg2 ;
				
			}
			
			
			/**
			*	提交页面，查看另一辆车载行驶轨迹...
			*/
			function gotomap(){
				document.getElementById('myform').action = 'realcar.do' ;
				document.getElementById('ope').value = "doRealMap" ;
				document.myform.submit() ;
			}
			
			
			 /**
		  * 根据数值计算角度
		  * @param {Object} d 经纬度数值
		  */
		 function calcjiaodu(d){
		 	return d * Math.PI/180.0 ;	
		 }
		 
		 /**
		  * 根据两个经纬度数值计算距离
		  * @param {Object} f(lat,lng)	起始点位置	
		  * @param {Object} s(lat,lng)	终止点位置
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
  <div id="top"><a href="#"><img src="images/canku/icon_c.gif" width="16" height="15"  class="tb4"/><strong>位置:首页</strong>&lt;您正在查看车载实时数据地图显示</a>
  <img src="img/util/back.gif" style="cursor: pointer;" onclick="javascript:golist('realcar.do','toRealData');" width="48" height="20"  class="tb3"/></div>
  <div id="center">
    <table width="95%" id="bd3">
      <tr>
        <td width="10%"><h3 style="color:#454343; font-size:12px;  width:80px;">请选择车载：</h3></td>
        <td width="10%" align="left">
        	<select name="proId" id="proId" onchange="gotomap();">
								<c:forEach var="proj" items="${carprjList}">		
									<option value='${proj.projectId}' ${param.proId==proj.projectId?"selected=selected":"" }>${proj.projectName }</option>"
								</c:forEach>
			</select>  
         </td>
        <td width="50%">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <span style="color:#454343; font-size:12px; padding-left:0px; padding-right:0px;">当前车载连接状态:</span><span id="connectioninfo"><font color='red'> 断 开 </font></span>  
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <span style="color:#454343; font-size:12px; padding-left:0px; padding-right:0px;">当前车载运行状态:</span><span id="runinfo"><font color='red'> 停 止 </font></span>     
        </td>
        <td width="30%" >
        	<div id="net" style="background-color:rgb(227,222,28);font-size:12px;display:none;height: 6px"> &nbsp;&nbsp;当前网络繁忙,无法获取到实时数据...</div>
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
