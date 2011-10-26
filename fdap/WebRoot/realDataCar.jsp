<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${fdaporg.name }车载实时数据</title>
<link href="css/index/real_car.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="images/logo.ico" />
 <script type='text/javascript' src='dwr/interface/realcar.js'></script>
 <script type='text/javascript' src='dwr/engine.js'></script>
 <script type='text/javascript' src='dwr/util.js'></script>
  
<script type="text/javascript" language="javascript" >

	var mytime = null ;
	
	var i = 1 ;

	//跳转页面
	function goback(url,method){
			document.myform.action = url ;
			document.getElementById('ope').value = method ;
			document.myform.submit() ;
	}
	
	//页面初始化调用程序
	function init(){
		var p = document.getElementById("projectIds").value ;
		if(p==null || p==""){
			document.getElementById("waitingTip").innerHTML = "<font color='blue'>没有发现相应的车载工程!</font>"  ;
			return  ;
		}
				
		realcar.getRealCarData(p,{
			callback:resultHandler,
			errorHandler:errorHandler,
			timeout:10000
		});
		
		mytime = window.setInterval(function(){
		realcar.getRealCarData(p,{
			callback:resultHandler,
			errorHandler:errorHandler,
			timeout:10000
		});},10000) ;
	}
	
	//错误处理程序
	function errorHandler(err){
		document.getElementById('errorTip').style.display = "inline" ;
	}
	
	//构造车载实时数据单元
	var cellfunction = [
		function(data){
			return i++ ;
		},
		
		function (data){
			return data.name ;
		},
		
		function(data){
			if(data.t1==null || data.t1=="-300")
				return "----" ;
			return data.t1 ;
		},
		
		function(data){
			if(data.t2==null || data.t2=="-300")
				return "----" ;
			return data.t2 ;
		},
		
		function(data){
			if(data.t3==null  || data.t3=="-300")
				return "----" ;
			return data.t3 ;
		},
		
		function(data){
		
			if(data.isalarm==null ){
				return "----" ;
			}
			
			if(data.isalarm=="0"){
				return "<img src='images/index/msie_doc_sel.gif' width='18' height='16'  alt='报警状态' title='报警状态' />" ;
			}else{
				return "<img src='images/index/msie_doc2.gif' width='18' height='16' alt='正常状态'  title='正常状态' />" ;
			}
		},
		
		function(data){
			if(data.projectNO==null||data.projectNO==""||data.isalarm==null)
				return "<img title='实时地图显示' alt='实时地图显示' src='images/index/chart-up.png' align='middle'/>" ;
			else
				return "<img title='实时地图显示' alt='实时地图显示' style='cursor:pointer' src='images/index/chart-up.png' align='middle' onclick=gotoMap('"+data.projectNO+"') />" ;
		}
	] ;
	
	
	
	
	function gotoMap(pNO){

			document.getElementById('myform').action = "carReal.do" ;
			document.getElementById('ope').value = 'toRealMap'
			document.getElementById("pNO").value = pNO ;
			document.myform.submit() ;
				
	}
	
	
	//车载实时数据处理程序
	function resultHandler(result){
		document.getElementById('waitingTip').innerHTML  = "" ;
		if(result!=null && result.length>0){
			//清空数据表
			DWRUtil.removeAllRows("container");
			i=1 ;
			//把数据添加到表格
			DWRUtil.addRows("container",result,cellfunction,{
				escapeHtml:false ,
				cellCreator:function(options){
					var td = document.createElement("td");
					td.align = "center";
					return td ;
				},
				rowCreator:function(options){
					var tr= document.createElement("tr");
					return tr ;
				}
			});
		}else{
			document.getElementById("waitingTip").innerHTML = "<font color='blue'>没有发现相应的车载工程!</font>"  ;
		}	
	}
</script>

</head>
<body onload="init();" >
<div>
  <iframe scrolling="no" src="head.jsp" width=100% height=128 frameborder=0></iframe >
</div>
<form name="myform" id="myform" action="" method="post">
<input  type="hidden" name="ope" id="ope" value=""/> 
<input type="hidden" name="oid"  id="oid" value="${fdaporg.oid }"/>

<input type="hidden" name="pNO"  id="pNO" value=""/>

<input  type="hidden" name="projectIds" id="projectIds" value="${projectIds }"/>
</form>
<div id="content">
  <div id="center">
    <div class="top">
      <table id="tb" border="0" cellspacing="0" cellpadding="0" >
        <tr id="tb1" >
          <td colspan="7"><span style="text-align:left; float:left;padding-left:4px; margin-left:4px; color:#141414;">
          <img src=images/index/icon.gif width="9" height="10" /> 当前位置：查看${fdaporg.name }的车载实时数据</span>
          <a href="javascript:goback('org.do','toHigherOrg')"><img src="images/index/back.gif" width="58" height="21" 
        class="pho"/></a></td>
        </tr>
      </table>
    </div>
    <div class="bottom">
      <table id="tb" border="0" cellspacing="0" cellpadding="0">
      <tr >
          <td height="20" colspan="15" align="left" style="text-align:left;">
          <table width="100%" style="height:35px;">
              <tr>
                <td width="10%" style="border:none;"><a href="javascript:goback('refReal.do','toRealRef');"><img src="images/index/canku.gif" width="100" height="24" /></a></td>
                <td width="13%" style="border:none;"><a href="javascript:;"><img  src="images/index/car.gif" width="100" height="24" /></a></td>
                <td width="77%" style="border:none;">&nbsp;</td>
              </tr>
            </table>
            </td>
        </tr>
        <tr id="errorTip" style="display: none" ><td colspan="6"><div  style="color: red">无法获取到数据...</div></td></tr>
        <tr class="altrow" >
          <td width="66">编号</td>
          <td width="186"><div align="center">车牌号码</div></td>
          <td width="188"><div align="center">T1</div></td>
          <td width="130"><div align="center">T2</div></td>
          <td width="147"><div align="center">T3</div></td>
          <td width="161"><div align="center">是否报警</div></td>
          <td width="121"><div align="center">实时追踪</div></td>
        </tr>
       
        <tbody id="container">
        </tbody>  
      </table>
    </div>
   <div align="center" ><div id="waitingTip">正在获取数据....</div></div>
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
  </div>
</div>
<div>
  <iframe scrolling="no" src="footer.jsp" width=100% height=26 frameborder=0></iframe >
</div>
</body>
</html>
