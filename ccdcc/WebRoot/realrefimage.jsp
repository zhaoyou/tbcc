<%@ page language="java"  pageEncoding="gbk"%>
<%@include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>�ֿ�ʵʱ����ͼ����ʾ</title>
<link rel="Shortcut Icon" href="img/add/logo.ico" />
<link href="css/cangku/input1.css" rel="stylesheet" type="text/css" />
<link href="css/cangku/map1.css" rel="stylesheet" type="text/css" />


		<script type='text/javascript' src='dwr/interface/real.js'></script>
        <script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript">
    	var projectId = null
    	var myInterval=null;
    	//����һ������������ʾ��ͼƬ�Ƿ��Ѿ�����(Ϊ�˽�����ٻ��������Ǽ���ͼƬ)
    	
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
    		var path = result[0]; 			//���path="no_picture" 
    		//����ͼƬ��λ��
    		setPath(path);
    		
    		if(result.length==0){
    			alert("ͼ��û�м��ͷ��");
    			return false;
    		}          
    		//��λ��ʾ����
    		var temp = 0; //����������ȥ����temp��Ϊ25 ��ԭ��ʼֵΪ145��//ҳ���޸ĺ�temp�Ѿ�����Ϊ0 ��
    		
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
   				
   				
				if(cData=="����"){
   					objdiv.innerHTML = cName+"<font color='red'>"+cData+"</font>"; 
   				}else if(cData=="Ԥ��"){
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
    	*���ó�ʼԪ�ص�λ��
    	*��������ҳ�����Ҫ�����������
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
    	
    	*/
    	
    	
    	/**
    	*	���ݲ�ͬ�Ĳֿ⹤�̣���ȡ��ͬ��¥��ͼƬ
    	*/
    	
    	function getImageList(projId){ 
    		real.getProjectImages(projId,{
				callback:result_handler,
				errorHandler:error_handler,
				timeout:25000
				});
				
    	}
    	
    	/**
    	 *	�����ص�¥��ͼƬ
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
    	 	window.alert("�޷���ȡ�ֿ�¥���ͼƬ��Ϣ...");
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
    	 
    	 //�����ص�¥��ʵʱ���ݡ�������Ӧ��̽ͷ����
    	function resultHandler(result){ 
    		if(result!=null && result!=""){ 
    			mysplit(result);
    		}
    	}
    	
    	//ʵʱ���ݴ��������
    	function errorHandler(e){
    	 	document.getElementById('net').style.display = 'inline' ;
    	 }
    	 
    	//��ȡ¥���ʵʱ����
    	 function getData(){ 
    	 		getRealData();
    	 		myInterval = window.setInterval("getRealData()",10000);
    	 }
    	 
    	 //�ı䲻ͬ��¥��
    	 function changeImage(){
    	 		window.clearInterval(myInterval);
    	 		isSetUp = true ;					//ÿ�θı�ͼƬ���Ǳ�����ԭ��Ϊ�˽��ͼƬÿ�ζ����¼���
    	 		myInterval=null;
    	 		getRealData();
    	 		myInterval = window.setInterval("getRealData()",10000);
    	 }
    	 
    	 //������ҳ��
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
  <div id="top"><a href="#"><img src="images/canku/icon_c.gif" width="16" height="15"  class="tb4"/><strong>λ��:��ҳ</strong>&lt;�����ڲ鿴��ͬ¥���ʵʱ����</a>
  <img src="img/util/back.gif" width="48" height="20" style="cursor: pointer;" onclick="golist();"  class="tb3"/></div>
  <div id="center">
    <table width="81%" height="29" id="bd3">
      <tr>
        <td width="8%"><h3 style="text-align:left; font-size:12px;padding-left:0px; padding-right:0px; width:65px; color:#454343;">ѡ��ֿ⣺</h3></td>
        <td width="20%">
        	<select name="project" id="project" style="width: 120" onchange="getImageList(this.value);" >
				
				<c:forEach var="proj" items="${projList}">
					<option value="${proj.projectId}" >${proj.projectName}</option>					
				</c:forEach>
			</select>
        </td>
        <td>&nbsp;</td>
        <td width="8%"><h3 style="text-align:right; font-size:12px;padding-left:0px; padding-right:0px; width:65px; color:#454343;">ѡ����⣺</h3></td>
        <td width="20%">
        	<select name="image" id="image" style="width: 120" onchange="changeImage()" >
				<c:forEach var="image" items="${imageList}">
					<option value="${image.id }">${image.imageName }</option>					
				</c:forEach>
			</select>
        </td>
       	<td  width="44%" ><div id="net" style="background-color:rgb(227,222,28);font-family:'����';display:none;">
    &nbsp;&nbsp;��ʱ�޷���ȡͼ��ʵʱ����...</div></td>
      </tr>
    </table>
  </div>
  <div id="bottom">
    <div id="main" style="position:absolute; z-index:100;width:990px;height:673px; "> <img id="img" src="" width=100% height=100% alt="û����Ӧ��ͼ��" /> </div>
    <div id="contain" style="position:absolute; z-index:110; width:990px; height:673px;"></div>
  </div>
</div>
<div class="clear"></div>
<div>
  <iframe scrolling="no" src="common/footer2.jsp" width=100% height=43 frameborder=0></iframe >
</div>
</body>
</html>
