<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>�鿴¥��ʵʱ����</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link type="text/css"  href="css/style.css" rel="stylesheet">
	<script type="text/javascript" src="script/titleTime.js"></script>
		 <script type='text/javascript' src='dwr/interface/real.js'></script>
        <script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
	
	<script type="text/javascript">
    	var projectId = null
    	var myInterval=null;
    	function setPath(path){
    		var p = document.getElementById("project").value ; 
    		var pathPrefix = "img/project/"+p+"/" ;
    		document.getElementById('img').src=pathPrefix+path; 
    	}
    	function mysplit(str){
    		var wid = screen.width;
    		var result = str.split("|");
    		var path = result[0]; 			//���path="no_picture" 
    		//����ͼƬ��λ��
    		setPath(path);
    		if(result.length==0){
    			alert("ͼ��û�м��ͷ��");
    			return false;
    		}          
    		//��λ��ʾ����
    		var temp = 145; //����������ȥ����temp��Ϊ25 ��ԭ��ʼֵΪ145��
    		
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
   				objdiv.style.top = Number(cTop)+temp;
   				if(wid-1024>0){
   					objdiv.style.left = Number(cLeft)+(wid-1024)/2;
   				}else{
   					objdiv.style.left = Number(cLeft);
   				};	
   				
   				
				if(cData=="����"){
   					objdiv.innerHTML = cName+"<font color='red'>"+cData+"</font>"; 
   				}else if(cData=="Ԥ��"){
   					objdiv.innerHTML = cName+"<font color='orange'>"+cData+"</font>";
   				}else{
					objdiv.innerHTML = showData; 
                }
   										
				
				container.appendChild(objdiv);
    		}
    	}
    		
    	function locate(){
    		var wid = screen.width;  		
    		var headdiv = document.getElementById("head");
    		var head2div = document.getElementById("head2");
			var head3div = document.getElementById("head3");
    		var maindiv = document.getElementById("main"); 
    		var contain = document.getElementById("contain");
    		 
    		headdiv.style.top=0;		
    		head2div.style.top=80;
			head3div.style.top=120;		// ����������ȥ���󣬶���Ϊ0��maindiv.style.top= 25
    		maindiv.style.top=145;		// ԭ��ʼֵΪheaddiv.style.top=0;head2div.style.top=80;head3div.style.top=120;main.style.top=145
//    		contain.style.top=145;
    		if(wid-1024>0){	
    			headdiv.style.left= (wid-1024)/2;
    			head2div.style.left= (wid-1024)/2;
				head3div.style.left= (wid-1024)/2;
    			maindiv.style.left= (wid-1024)/2;
    		}   		
		
    	}
    	
    	
    	/**
    	*	��������,ˢ������
    	*/
    	
    	function getImageList(projId){ 
    		real.getProjectImages(projId,{
				callback:result_handler,
				errorHandler:error_handler,
				timeout:25000
				});
				
    	}
    	
    	/**
    	 *	������
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
    	 *	�����쳣
    	 **/
    	 function error_handler(){
    	 	window.alert("���緱æ�����Ժ�鿴...");
    	 }
    	 
    	 /**
    	   *	����ͼ��
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
    	function resultHandler(result){ 
    		if(result!=null && result!=""){ 
    			mysplit(result);
    		}
    	}
    	function errorHandler(){
    	 	window.alert("���緱æ�����Ժ�鿴...");
    	 }
    	 
    	 function getData(){ 
    	 		getRealData();
    	 		myInterval = window.setInterval("getRealData()",10000);
    	 }
    	 
    	 function changeImage(){
    	 		window.clearInterval(myInterval);
    	 		myInterval=null;
    	 		getRealData();
    	 		myInterval = window.setInterval("getRealData()",10000);
    	 }
    </script>
	</head>
	
	<body id="content" onload="showtime();locate();getData();">
	
		<input type="hidden" id="branchId" name="branchId" value="${param.branchId }"/>
		<input type="hidden" id="projectId" name="projectId" value="${projectId!=null?projectId:param.projectId}"/>
		<div id="head"  style="z-index:70;position:absolute; width:1006px;height:80px;background-image: url(img/project/head.jpg);">
				<div style="float: right"><a target="_parent" title="ע��" href="user.do?ope=doLogout"><img border="0" src="img/stock_index_02.gif"/></a>
				<span style="cursor: pointer"><img border="0" onclick="window.close();" src="img/stock_index_04.gif" /></span></div>
		</div>
		<div id="head2" style="position:absolute; background-image:url(img/project/head2.jpg);z-index:80;width:1006px;height:40px;">
				<div id="time" style="padding-top:15px; padding-right:20px; float: right; text-align: left;color: white;"></div>
				<div id="userInfo" style="padding-top:15px; padding-right:28px; float: right; text-align: left;color: white;">${userInfo}</div>	
		</div>
		
		<div id="head3" style="position:absolute; background-color:#BCDDFE;width:1006px;height:25px;z-index: 100">
				<div  style="float:left" class="page_title" style="text-align: left">����������ҵ���ǣ�<img src="img/add/club.JPG" alt=">>"><font  style="font-size: 12px">&nbsp;�鿴��ͬ¥���ʵʱ����
				&nbsp;&nbsp;��ѡ�񹤳�:</font>
				</div>
				<div id="projDiv" style="float:left">
				<select name="project" id="project" style="width: 120" onchange="getImageList(this.value);" >
				
				<c:forEach var="proj" items="${projList}">
					<option value="${proj.projectId}" >${proj.projectName}</option>					
				</c:forEach>
				</select>
				</div>
				<div style="float:left" class="page_title" >&nbsp;
				<font  style="font-size: 12px">&nbsp;��ѡ��ͼ��:</font>
				</div>
				<div id="imgDiv" style="float:left">
				<select name="image" id="image" style="width: 120" onchange="changeImage()" >
				<c:forEach var="image" items="${imageList}">
					<option value="${image.id }">${image.imageName }</option>					
				</c:forEach>
				</select>
				</div>
				<div style="float:left">&nbsp;
				<input type="button" class="common_button" onclick="javascript:window.location.href='branch.do?ope=toListByBranch&branchId=${param.branchId }'"
									value="������ҳ��"/>
				</div>
		</div>
		<div id="main" style="position:absolute; z-index:100;width:1006px;height:673px; ">
			<img id="img" src="" width=100% height=100% alt="û����Ӧ��ͼ��" >
		</div>
		<div id="contain" style="position:absolute; z-index:110;width:1006px; 673px;">
		</div>
			
		
		
	</body>
</html>
