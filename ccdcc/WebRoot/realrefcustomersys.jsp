<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>仓库系统实时数据查看</title>
<link href="css/cangku/css.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="img/add/logo.ico" />
<script type='text/javascript' src='dwr/interface/real.js'></script>
  	    <script type='text/javascript' src='dwr/engine.js'></script>
  		<script type='text/javascript' src='dwr/util.js'></script>
<script type="text/javascript">
	
	/**
	*	提交页面
	**/
	function golist(url,operate){
		document.getElementById('myform').action = url ;
		document.getElementById('ope').value = operate ;
		document.myform.submit();
	}
	
	/**
	*	跳转到仓库系统页面
	**/
	function gosyslist(url,operate,projectId){
		document.getElementById('projectId').value = projectId ;
		golist(url,operate);
	}
	
	/**
	*	跳转到不同的楼层页面
	**/
	function gofloorlist(url,operate,projectId,ftype,fno){
		document.getElementById('projectId').value = projectId ;
		document.getElementById('floorType').value = ftype ;
		document.getElementById('floorNo').value = fno ;
		golist(url,operate);
	}
	
	/**
	**	由客户仓库实时数据页面跳转到，自身冷库列表主页面，此时需要注意的是
	*	需要有sbranchId 变成当前的branchId
	**/
	function gorefList(url,operate){
		document.getElementById('branchId').value = document.getElementById('sbranchId').value ;
		golist(url,operate);
	}
	/**
	**	页面初始化
	**/
	function init(){
		var p  = document.getElementById('projectId').value;
		//如果获取不到工程参数、直接返回
		if(p==null || p==""){
				return  ;
		}
		
		//获取实时数据
		real.getRealRefSys(p,{
			callback:result_handler ,
			errorHandler:error_handler,
			timeout:15000
		}) ;
		
		//每隔10s获取数据
		window.setInterval(function(){
		real.getRealRefSys(p,{
			callback:result_handler ,
			errorHandler:error_handler,
			timeout:15000
		}) ;
		},10000);
		
	}
	
	/**
	*	result[0] connection
	*	result[1] duandian
	*	result[2] shengguang
	**/
	function result_handler(result){
		document.getElementById('errorTip').style.display = "none" ;
		if(result==null || result.length==0){
			return  ;
		}else{
			setConnection(result[0]) ;
			setState(result[0],result[1],'duandian');
			setState(result[0],result[2],'shengguang');
		}
		
		
	}
	
	/**
	*	设置连接状态
	**/
	function setConnection(con){
		if(con==2){
			document.getElementById('lianjie').innerHTML = "连接" ;
		}else if (con==1){
			document.getElementById('lianjie').innerHTML = "断开" ;
		}else{
			document.getElementById('lianjie').innerHTML = "----" ;
		}
	}
	
	/**
	*	设置报警状态
	**/
	function setState(con,state,ele){
	
		if(con==2){					//正常连接			
			if(state==2){			//正常
				document.getElementById(ele).style.display = "none" ;
				document.getElementById(ele+'_normal').style.display = "inline" ;
				document.getElementById(ele+'_alarm').style.display = "none" ;
				document.getElementById(ele+'_yujing').style.display = "none" ;
			}else if(state==1){		//报警
				document.getElementById(ele).style.display = "none" ;
				document.getElementById(ele+'_alarm').style.display = "inline" ;
				document.getElementById(ele+'_normal').style.display = "none" ;			
				document.getElementById(ele+'_yujing').style.display = "none" ;
			}else if (state ==0){	 //预警
				document.getElementById(ele).style.display = "none" ;
				document.getElementById(ele+'_yujing').style.display = "inline" ;
				document.getElementById(ele+'_alarm').style.display = "none" ;
				document.getElementById(ele+'_normal').style.display = "none" ;			
			}else{					//状态不可用
				document.getElementById(ele).style.display = "block" ;	
				document.getElementById(ele+'_normal').style.display = "none" ;
				document.getElementById(ele+'_alarm').style.display = "none" ;
				document.getElementById(ele+'_yujing').style.display = "none" ;
			}
					
		}else {						//连接断开或不开用
			document.getElementById(ele).style.display = "inline" ;	
			document.getElementById(ele+'_normal').style.display = "none" ;
			document.getElementById(ele+'_alarm').style.display = "none" ;
			document.getElementById(ele+'_yujing').style.display = "none" ;
		}
	}
	
	
	/**
	*		错误处理程序了
	**/
	function error_handler(){
		document.getElementById('errorTip').style.display = "inline" ;
	}
</script>
</head>
 
<body onload="init();">
<div>
  <iframe scrolling="no" src="common/header2.jsp" width=100% height=126 frameborder=0></iframe >
</div>
<form action="" id="myform" name="myform" method="post" >
	<input type="hidden" name="ope" id="ope" value=""/>
	<input type="hidden" name="projectId" id="projectId" value="${projectId }" />
	
	<input type="hidden" name="floorType" id="floorType" value=""/>
	<input type="hidden" name="floorNo"   id="floorNo"   value=""/>	
	<input type="hidden" name="branchId"  id="branchId"  value="${param.branchId }"/>
	<input type="hidden" name="sbranchId" id="sbranchId" value="${param.sbranchId }"/>
</form>
<div id="left">
  <ul id="nav">
    <li class="tab">&nbsp;</li>
    <c:forEach  var="refPrj" items="${refPrjList}">
    	<li class="tab_a"> <a href="#"><img src="images/canku/s.gif" width="20" height="22" />${refPrj.projectName }</a> </li>
    	
    	<li class="tab_b"><a href="javascript:gosyslist('realref.do','toRefSysCus','${refPrj.projectId }');"><img src="images/canku/icon_a.gif" width="22" height="17"  class="right"/>仓库系统监测</a></li>
    	<c:forEach var="floor" items="${floorList}">
    		<c:if test="${refPrj.projectId==floor.project}">			
    			<li class="tab_b"><a href="javascript:gofloorlist('realref.do','toRefFloorCus','${refPrj.projectId }',${floor.floorType },${floor.floorNo })"><img src="images/canku/icon_b.gif" width="18" height="20"  class="right"/>${floor.floorName }</a></li>
    		</c:if>
    	</c:forEach>
    	
    </c:forEach>
    <%--
    <li class="tab_a"> <a href="#"><img src="images/canku/s.gif" width="20" height="22" />仓库A</a> </li>
    <li class="tab_b"><a href="#"><img src="images/canku/icon_a.gif" width="22" height="17"  class="right"/>F1层</a></li>
    <li class="tab_b"><a href="#"><img src="images/canku/icon_b.gif" width="18" height="20"  class="right"/>F2层</a></li>
    <li class="tab_b"><a href="#"><img src="images/canku/icon_b.gif" width="18" height="20"  class="right"/>系统</a></li>
    <li class="tab_a"> <a href="#"><img src="images/canku/s.gif" width="20" height="22" />仓库B</a> </li>
    <li class="tab_b"><a href="#"><img src="images/canku/icon_a.gif" width="22" height="17"  class="right"/>F1层</a></li>
    <li class="tab_b"><a href="#"><img src="images/canku/icon_b.gif" width="18" height="20"  class="right"/>F2层</a></li>
    <li class="tab_b"><a href="#"><img src="images/canku/icon_b.gif" width="18" height="20"  class="right"/>系统</a></li>
     --%>
    <li class="tab_c"><a href="#">&nbsp;</a></li>
  </ul>
</div>
<div id="line"><img src="images/canku/icon2_090.gif" /></div>
<div id="right"> 
  <div id="top">
  <a href="#"><img src="images/chezai/icon_c.gif" width="16" height="15"  class="tb4"/><strong>位置:首页</strong>&lt;查看仓库系统实时数据</a>
  <span><img src="img/util/back.gif" style="cursor: pointer;" onclick="javascript:gorefList('pro.do','toRefList')" width="48" height="20"  class="tb3" /></span></div>
  <div id="bottom"> 
  	<div id="errorTip" style="color: red;font-size: 13px;display: none">无法获取到仓库系统实时数据,请稍后...</div>
    <div id="center"><span>${projectName }系统监测</span></div> 
    <table id="tb" width="80%" border="0" cellspacing="0" cellpadding="0"> 
      <tr id="tb1"> 
        <td width="297">监测</td> 
        <td width="314">报警</td> 
      </tr> 
      <tr> 
        <td>连接状态</td> 
        <td><span id="lianjie">----</span></td> 
      </tr> 
      <tr class="altrow"> 
        <td>断电报警</td> 
        <td align="center"><span id="duandian">----</span><img id="duandian_normal" title="正常" src="img/menu/win/blue.gif" style="display: none"></img><img id="duandian_yujing" title="预警" src="img/menu/win/orange.gif" style="display: none"></img><img id="duandian_alarm" title="报警" src="img/menu/win/red.gif" style="display: none"></img></td> 
      </tr> 
      <tr> 
        <td>声光报警</td> 
        <td align="center"><span id="shengguang">----</span><img id="shengguang_normal" title="正常" src="img/menu/win/blue.gif" style="display: none"></img><img id="shengguang_yujing" title="预警" src="img/menu/win/orange.gif" style="display: none"></img><img id="shengguang_alarm" title="报警"  src="img/menu/win/red.gif" style="display: none"></img></td> 
      </tr> 
    </table> 
  </div> 
</div> 
<div class="clear"></div> 
<div> 
  <iframe scrolling="no" src="common/footer2.jsp" width=100% height=43 frameborder=0></iframe> 
</div> 
</body> 
</html> 