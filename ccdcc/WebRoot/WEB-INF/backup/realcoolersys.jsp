<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统实时数据查看</title>
<link rel="Shortcut Icon" href="img/add/logo.ico" />
<link href="css/Refri/Refri.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="script/titleTime.js"></script>
		
		 <script type='text/javascript' src='dwr/interface/realcool.js'></script>
  	    <script type='text/javascript' src='dwr/engine.js'></script>
  		<script type='text/javascript' src='dwr/util.js'></script>
<style type="text/css">

.datalist{
	border:1px solid #BDDEF7;
	border-collapse:collapse;
	background-color:#FFFFFF; 
	font-size:12px;
}
.datalist2 {
	border:1px solid #c6e2f7;	
	font-family:Arial;
	border-collapse:collapse;	
	background-color:#FFFFFF; 
	font-size:12px;
}
.datalist3 {
	border:1px solid #BDDEF7;
	border-collapse:collapse;	
	background-color:#FFFFFF;
	font-size:12px;
	text-align: left;
}
.STYLE1 {
	font-size: 12px;
	color: #2b7dbb;
	font-weight: bold;
}
.STYLE2 {color: #454343}
</style>
	<script type="text/javascript">

		
		/**
		*	跳转的机组页面
		**/
		function goCompressorSet(id){
			document.getElementById('mysetid').value = id ;
			document.myform.submit();
		}
		
		/**
		*	页面初始化时调用
		**/
		function init(){
			getCoolerSystem() ; 
			window.setInterval("getCoolerSystem()",10000);
		}
		
			/**
			*	获取制冷系统实时数据
			*/
			function getCoolerSystem(){
				var parrayobj = document.getElementById("coolerprojectids").value ;
				var goquery = document.getElementById('if_query').value ;
					if(goquery ==0){
						return  ;
					}			
					var v = parrayobj.split(",") ;	
					realcool.getCoolerSysRealData(v,{
					callback:doCoolerSystem,
					timeout:15000,
					errorHandler:doError
				});
			}
			
			/**
			*	处理制冷系统实时数据
			*/
			function doCoolerSystem(data){
				if(data!=null){
					document.getElementById("fadianji").innerHTML = getDisplayState(data.dynamoState);
					document.getElementById("fadianjiyujin").innerHTML = getDisplayAlarm(data.dynamoAlarm);
					document.getElementById("xitongduandian").innerHTML = getDisplayState(data.sysoutageState);
					document.getElementById("xitongduandianyujin").innerHTML = getDisplayAlarm(data.sysoutageAlarm);	
				}else{
					document.getElementById("fadianji").innerHTML = "----";
					document.getElementById("fadianjiyujin").innerHTML = "----";
					document.getElementById("xitongduandian").innerHTML = "----";
					document.getElementById("xitongduandianyujin").innerHTML = "----";
				}
				document.getElementById('errorTip').style.display = "none" ;	
			}
			
			/**
			**	获取系统实时数据错误处理程序
			**/
			function doError(){
				document.getElementById('errorTip').style.display = "block" ;
			}
		
		/**
			*	根据状态显示状态文字
			*/
			function getDisplayState(state){
				if(state==2)
					return "开启" ;
				else if(state==1)
					return "关闭" ;
				else 
					return "----" ;
			}
			
			/**
			*	根据报警状态显示报警文本
			*/
			function getDisplayAlarm(alarm){
				if(alarm==2)
					return "<img src='img/menu/win/blue.gif'>" ;
				else if (alarm==0)
					return "<img src='img/menu/win/red.gif'>" ;
				else
					return "----" ;
			}
			
			/**
			*		返回
			**/
		
			function goback(){
				document.myform.action = "branch.do?ope=toListByBranch" ;
				document.myform.submit() ;
			}

		
	</script>
</head>
<body onload="init();">
<div>
     <iframe scrolling="no" src="common/header2.jsp" width=100% height=126 frameborder=0></iframe >  
		<%-- <jsp:include page="common/header2.jsp" flush="true"></jsp:include> --%>
</div>
<form name="myform" action="realcool.do?ope=toRealCoolSingleMul" method="post">

	<input type="hidden" name="coolerprojectids" id="coolerprojectids" value="${refprojectids }"/>
	<input type="hidden" name="branchId" id="branchId" value="${param.branchId }"/>
	<input type="hidden" name="mysetid" id="mysetid" value=""/>
<div id="left" style="height: 450px">
  <ul id="nav">
    <li class="tab"><a href="#"></a></li>
    <li class="tab_a"> <a href="#"><img src="images/Refri/icon_e.gif" width="20" height="20" />系统 </a> </li>
   
    <c:forEach items="${coollist}" var="comset">
    		<li class="tab_b"  ><a href="javascript:goCompressorSet('${comset.id }');"><img src="images/Refri/jizu.gif" width="20" height="20" />${comset.csName }</a></li>
    </c:forEach>
    
    <li class="tab_c"><a href="#">gfsdgs</a></li>
  </ul>
</div>
<div id="line" style="height: 450px"><img src="images/index/icon2_090.gif" /></div>
<div id="right" style="background-color: white;height: 450px"><table width="100%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td><table width="100%" align="left" class="datalist">
    <tr>
      <td width="767" height="10" colspan="4" background="images/Refri/menu_bga.gif">
      
     <div id="top"><img src="images/Refri/icon_c.gif" width="16" height="15" / class="tb4"><strong>位置:首页</strong>&lt;查看制冷系统实时数据<span>
	 <img src="images/Refri/back.gif" style="cursor: pointer;" onclick="goback();" width="48" height="20"  class="tb3"></div>
	 
      </td>
    </tr>
  </table></td>
  </tr>
  <tr>
    <td><input type="hidden" id="if_query" name="if_query" value="${coollist==null?0:1 }"/><span id="noInfo" style="color: red;display:${coollist==null?'block':'none' }">没有配置相应的制冷设备...</span><table width="100%" class="datalist" style="display: ${ coollist==null?'none':'block'}">
      
      <tr class="altrows">
        <td width="300" colspan="4" align="left">
        	<div align="left"><img src="images/Refri/button_e.gif"/><span id="errorTip" style="display: none;color: red">读取实时数据失败,请稍后...</span></div>	
        </td>    
      </tr>
      <tr>
        <td colspan="4"><table width="280" align="left" class="datalist2">
          <tr>
            <th width="25" class="datalist" scope="col">&nbsp;</th>
            <th width="89" scope="col">系统属性</th>
            <th width="58" scope="col">监控</th>
            <th width="88" scope="col">报警</th>
          </tr>
          <tr>
            <td><img src="images/Refri/quexiang.png"></img></td>
            <td>系统断电</td>
            <td><div id="fadianji">----</div>							
            <td><div id="fadianjiyujin">----</div></td>
          </tr>
          <tr class="altrow">
            <td><img src="images/Refri/发电机.png"></img></td>
            <td>发电机故障</td>
            <td><div id="xitongduandian">----</div></td>
            <td><div id="xitongduandianyujin">----</div></td>
          </tr>     
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td></td>
  </tr>
  
</table>


</div><div class="clear"></div>
</form>
<div><iframe scrolling="no" src="common/footer2.jsp" width=100% height=43 frameborder=0></iframe ></div>
</body>
</html>
