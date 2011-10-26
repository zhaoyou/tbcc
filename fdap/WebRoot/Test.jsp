<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${fdaporg.name }车载实时地图</title>
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
	
</script>

</head>
<body>
<div>
  <iframe scrolling="no" src="head.jsp" width=100% height=128 frameborder=0></iframe >
</div>
<form name="myform" id="myform" action="" method="post">
<input  type="hidden" name="ope" id="ope" value=""/> 
<input type="hidden" name="oid"  id="oid" value="${fdaporg.oid }"/>
<input  type="hidden" name="projectIds" id="projectIds" value="${projectIds }"/>
</form>
<div id="content">
  <div id="center" style="height: 600px;">
    <div class="top">
      <table id="tb" border="0" cellspacing="0" cellpadding="0" >
        <tr id="tb1" >
          <td colspan="7"><span style="text-align:left; float:left;padding-left:4px; margin-left:4px; color:#141414;">
          <img src=images/index/icon.gif width="9" height="10" /> 当前位置：查看${fdaporg.name }的车载实时地图</span>
          <a href="javascript:goback('org.do','toHigherOrg')"><img src="images/index/back.gif" width="58" height="21" 
        class="pho"/></a></td>
        </tr>
      </table>
    </div>
    <div class="bottom"	style="padding-top:0px; margin-top:0px;">
      	<iframe scrolling="no" src="http://www.thermoberg.com/ccdcc/map/customer_realmap.jsp?projectId=3010&code=003D-3F4A-810B-A2C4-591F-843A-0834-6B8A" width=100% height=540 frameborder=0></iframe >
    </div>
  </div>
</div>
<div>
  <iframe scrolling="no" src="footer.jsp" width=100% height=26 frameborder=0></iframe >
</div>
</body>
</html>
