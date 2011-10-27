<%@ page language="java"  pageEncoding="gbk"%>
<%@include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>仓库实时数据图层显示</title>
<link rel="Shortcut Icon" href="img/add/logo.ico" />
<link href="css/cangku/input1.css" rel="stylesheet" type="text/css" />
<link href="css/cangku/map1.css" rel="stylesheet" type="text/css" />


		<script type='text/javascript' src='dwr/interface/real.js'></script>
        <script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript">
    	var projectId = null
    	var myInterval=null;
    	//定义一个变量用来标示，图片是否已经设置(为了解决网速缓慢，总是加载图片)
    	
    	var isSetUp = true ;
    	function setPath(path){
    		var p = document.getElementById("project").value ; 
    		var pathPrefix = "img/project/"+p+"/" ;
				if(isSetUp){   
    				document.getElementById('img').src=pathPrefix+path; 
    				isSetUp = false ;
    			}
    		
    			
    	}
    	
    	function mysplit(str){
    		
    		var wid = screen.width;
    		var result = str.split("|");
    		var path = result[0]; 			//如果path="no_picture" 
    		//设置图片的位置
    		setPath(path);
    		
    		if(result.length==0){
    			alert("图层没有监控头！");
    			return false;
    		}          
    		//定位显示数据
    		var temp = 0; //上面两个层去掉后，temp置为25 （原初始值为145）//页面修改后temp已经设置为0 了
    		
    		var container = document.getElementById("contain");
    				
				while(container.firstChild!=null){
					container.removeChild(container.firstChild);
    			}
    		  		
    		
    		for(var i=1;i<result.length;i++){
    			var controlData=result[i].split(",");
    			var cTop = controlData[0]; 
    			var cLeft = controlData[1];
    			var cHeight = controlData[2];     
    			var cWidth = controlData[3];
    			var cName = controlData[4];
    			var cUnit = controlData[5];
    			var cData= controlData[6];
    			
    			var showData = cName+cData+cUnit; 
    		
    			var objdiv = document.createElement("DIV");
    			objdiv.style.zIndex = 110;  
    			//objdiv.style.background = '#FFFF00';
				objdiv.style.fontWeight = 'bold';
   				objdiv.style.visibility = 'visible';
   				objdiv.style.height = Number(cHeight);
   				objdiv.style.width = Number(cWidth);   			
    			objdiv.style.position = "absolute"; 
   				objdiv.style.top = (Number(cTop)+temp)+"px";
   				//if(wid-1024>0){
   				//	objdiv.style.left = Number(cLeft)+(wid-1024)/2;
   				//}else{
   					objdiv.style.left = (Number(cLeft))+"px";
   				//};	
   				
   				
				if(cData=="报警"){
   					objdiv.innerHTML = cName+"<font color='red'>"+cData+"</font>"; 
   				}else if(cData=="预警"){
   					objdiv.innerHTML = cName+"<font color='orange'>"+cData+"</font>";
   				}else{
					objdiv.innerHTML = showData; 
                }
   										
				//window.alert('left: '+ Number(cLeft)+' top: '+Number(cTop)+temp);
				container.appendChild(objdiv);
    		}
    			document.getElementById('net').style.display = 'none' ;
    	}
    		
    	/**
    	*设置初始元素的位置
    	*重新引用页面后不需要考虑这个问题
    	**/	
    	
    	/**
    	function locate(){
    		var wid = screen.width;  		
    		var headdiv = document.getElementById("head");
    		var head2div = document.getElementById("head2");
			var head3div = document.getElementById("head3");
    		var maindiv = document.getElementById("main"); 
    		var contain = document.getElementById("contain");
    		 
    		headdiv.style.top=0;		
    		head2div.style.top=80;
			head3div.style.top=120;		// 上面两个层去掉后，都置为0，maindiv.style.top= 25
    		maindiv.style.top=145;		// 原初始值为headdiv.style.top=0;head2div.style.top=80;head3div.style.top=120;main.style.top=145
//    		contain.style.top=145;
    		if(wid-1024>0){	
    			headdiv.style.left= (wid-1024)/2;
    			head2div.style.left= (wid-1024)/2;
				head3div.style.left= (wid-1024)/2;
    			maindiv.style.left= (wid-1024)/2;
    		}   		
		
    	}
    	
    	*/
    	
    	
    	/**
    	*	根据不同的仓库工程，获取不同的楼层图片
    	*/
    	
    	function getImageList(projId){ 
    		real.getProjectImages(projId,{
				callback:result_handler,
				errorHandler:error_handler,
				timeout:25000
				});
				
    	}
    	
    	/**
    	 *	处理返回的楼层图片
    	 **/
    	function result_handler(result){ 
    		var imgDiv = document.getElementById("imgDiv");
    		var divhtml = "<select name='image' id='image' style='width: 120' onchange='changeImage()'>";
    		if(result!=null && result!=""){
    			for(var i=0; i<result.length; i++){
    				divhtml += "<option value="+result[i].id+">"+result[i].imageName+"</option>"
    			}
    		}
    		divhtml += "</select>";
    		imgDiv.innerHTML = divhtml;
    		
    		if(myInterval!=null){
					window.clearInterval(myInterval);
			}
    	 	myInterval=null;
    	 	getRealData();
    	 	myInterval = window.setInterval("getRealData()",10000);
    	}
    	
    	/**
    	 *	处理异常
    	 **/
    	 function error_handler(){
    	 	window.alert("无法获取仓库楼层的图片信息...");
    	 }
    	 
    	 
    	 
    	 /**
    	   *	更改图层
    	   **/
    	   
    	
    	 function getRealData(){
    	 	var imgId = document.getElementById("image").value;
    		var projectId = document.getElementById("project").value;  
    	 	if(imgId!=null && imgId!=""){
    	 		real.getRealFloor(imgId,projectId,{
					callback:resultHandler,
					errorHandler:errorHandler,
					timeout:25000
				});
			}
    	 }
    	 
    	 //处理返回的楼层实时数据、解析相应的探头数据
    	function resultHandler(result){ 
    		if(result!=null && result!=""){ 
    			mysplit(result);
    		}
    	}
    	
    	//实时数据错误处理程序
    	function errorHandler(e){
    	 	document.getElementById('net').style.display = 'inline' ;
    	 }
    	 
    	//获取楼层的实时数据
    	 function getData(){ 
    	 		getRealData();
    	 		myInterval = window.setInterval("getRealData()",10000);
    	 }
    	 
    	 //改变不同的楼层
    	 function changeImage(){
    	 		window.clearInterval(myInterval);
    	 		isSetUp = true ;					//每次改变图片后，是变量还原，为了解决图片每次都重新加载
    	 		myInterval=null;
    	 		getRealData();
    	 		myInterval = window.setInterval("getRealData()",10000);
    	 }
    	 
    	 //返回主页面
    	 function golist(){
    	 	document.myform.submit() ;
    	 }
    </script>
    
</head>
<body onload="getData();" >
<div>
  <iframe scrolling="no" src="common/header2.jsp" width=100% height=126 frameborder=0></iframe >
</div>
<form name="myform" id="myform" action="pro.do" method="post">
<input type="hidden" name="ope" id="ope" value="toRefList"/>
<input type="hidden" id="branchId" name="branchId" value="${param.branchId }"/>
<input type="hidden" id="projectId" name="projectId" value="${projectId!=null?projectId:param.projectId}"/>
</form>
<div id="right">
  <div id="top"><a href="#"><img src="images/canku/icon_c.gif" width="16" height="15"  class="tb4"/><strong>位置:首页</strong>&lt;您正在查看不同楼层的实时数据</a>
  <img src="img/util/back.gif" width="48" height="20" style="cursor: pointer;" onclick="golist();"  class="tb3"/></div>
  <div id="center">
    <table width="81%" height="29" id="bd3">
      <tr>
        <td width="8%"><h3 style="text-align:left; font-size:12px;padding-left:0px; padding-right:0px; width:65px; color:#454343;">选择仓库：</h3></td>
        <td width="20%">
        	<select name="project" id="project" style="width: 120" onchange="getImageList(this.value);" >
				
				<c:forEach var="proj" items="${projList}">
					<option value="${proj.projectId}" >${proj.projectName}</option>					
				</c:forEach>
			</select>
        </td>
        <td>&nbsp;</td>
        <td width="8%"><h3 style="text-align:right; font-size:12px;padding-left:0px; padding-right:0px; width:65px; color:#454343;">选择冷库：</h3></td>
        <td width="20%">
        	<select name="image" id="image" style="width: 120" onchange="changeImage()" >
				<c:forEach var="image" items="${imageList}">
					<option value="${image.id }">${image.imageName }</option>					
				</c:forEach>
			</select>
        </td>
       	<td  width="44%" ><div id="net" style="background-color:rgb(227,222,28);font-family:'楷体';display:none;">
    &nbsp;&nbsp;暂时无法获取图层实时数据...</div></td>
      </tr>
    </table>
  </div>
  <div id="bottom">
    <div id="main" style="position:absolute; z-index:100;width:990px;height:673px; "> <img id="img" src="" width=100% height=100% alt="没有相应的图层" /> </div>
    <div id="contain" style="position:absolute; z-index:110; width:990px; height:673px;"></div>
  </div>
</div>
<div class="clear"></div>
<div>
  <iframe scrolling="no" src="common/footer2.jsp" width=100% height=43 frameborder=0></iframe >
</div>
</body>
</html>
