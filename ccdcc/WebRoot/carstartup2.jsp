<%@ page language="java"  pageEncoding="gbk"%>
<%@include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看移动车载的启停记录</title>
<link rel="Shortcut Icon" href="img/add/logo.ico" />
<link href="css/chezai/input1.css" rel="stylesheet" type="text/css" />
<link href="css/chezai/tcd.css" rel="stylesheet" type="text/css" />
<script src="DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function golist(url,operate){
	
			document.getElementById("myform").action = url ;
			document.getElementById("ope").value = operate ;
			document.myform.submit() ;
	}
	
	      function getStartData(){
				var time1 = document.getElementById("d4311").value ;
				var time2 = document.getElementById("d4312").value ;
				if(time1=="" || time2=="" ){
					window.alert("请选择起始时间!");
					return  ;
				}
				
				if(!(isvalid(time1) && isvalid(time2)))
				{
					window.alert("请输入合法的日期格式!");
					return  ;
				}
							
				var t1 = buildDate(time1);
				var t2 = buildDate(time2);
				
				if((t2-t1)/1000/60/60/24>30){
					window.alert("起始时间间隔不能超过30天!");
					return  ;
				}
				
				//查询车载历史起停记录
				golist('startup.do','doStartUpCar');
				
			}
			
			function isvalid(str){
				if(str.charAt(4)=="-" && str.charAt(7)=="-" &&
				   str.charAt(10)==" " && str.charAt(13)==":" && str.charAt(16)==":")
					return true ;
				return false ;
			}
			
			/**
			*	根据一个字符串yyyy-MM-dd HH:mm:ss构造一个Date对象
			*/
			function buildDate(str){
			var t2 = str.split(" ");
			var date1 = t2[0].split("-");
			var date2 = t2[1].split(":");								
		    var day = new Date(date1[0],date1[1],date1[2],date2[0],date2[1],date2[2]);
			return day ;
		}
		
		
			/*** 转到历史数据页面
			*/
			
			function goHis(sid,flag){
				if(flag==true){
					document.getElementById("sid").value = sid ;
					document.getElementById('')
				}
			}
			
				/**
   				 *	实现权限的跳转
   				 */
   				 function goPowerURL(url,flag){
   				 	if(flag==true){
   				 		window.location.href = url ;
   				 	}
   				 }
   				 
   				 
   				 /**
   				 **	跳转到历史数据对应的不同详细页面去
   				 **/
   				 
   				 function godetail(url,operate,sid,cando){
   				 	if(cando==true){
   				 		document.getElementById('sid').value = sid ;
   				 		document.getElementById('selectForm').action = url+"?ope="+operate ;
   				 		document.selectForm.submit() ;
   				 		
   				 	}
   				 }
</script>
</head>
<body>
<div>
  <iframe scrolling="no" src="common/header2.jsp" width=100% height=126 frameborder=0></iframe >
</div>
<div id="right">
  <div id="top"><a href="#"><img src="images/chezai/icon_c.gif" width="16" height="15"  class="tb4"/><strong>位置:首页</strong>&lt;您正在查看车载启停数据</a>
  <img src="images/chezai/back.gif" width="48" height="20"  class="tb3"/>
  <img src="img/util/back.gif" width="48" height="20"  class="tb3" style="cursor: pointer;" onclick="javascript:golist('pro.do','toCarList');"/>
  <img src="images/chezai/bt_detail2.gif" width="98" height="21"  class="tb5" style="cursor: pointer;" onclick="javascript:getStartData();"/>
  </div>
  <form action="" name="myform" id="myform" method="post">
  <input type="hidden" id="branchId" name="branchId" value = "${param.branchId }"/>
  <input type="hidden" id="ope" name="ope" value =""/>				
  <div id="center">
    <table width="70%" id="bd3">
      <tr>
        <td width="73" ><h3 style="color:#454343; font-size:12px; float:left; padding-left:0px; padding-right:0px;">选择车载：</h3></td>
        <td width="140">
       	  <select name="proId" id="proId">
			<c:forEach var="proj" items="${carprjList}">		
				<option value='${proj.projectId}' ${param.proId==proj.projectId?"selected=selected":"" }>${proj.projectName }</option>
			</c:forEach>
		  </select>
          </td>
        <td width="63" style="width:65px;"><h3 style="color:#454343; font-size:12px; padding-left:0px; padding-right:0px; align=right">起始时间：</h3></td>
        <td width="128">
        	<input  name="time1" id="d4311" value="${param.time1}"  class="Wdate" type="text"
										 onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})"/> 
        </td>
        <td width="110" style="width:65px;"><h3 style="color:#454343; font-size:12px; padding-left:0px; padding-right:0px;">终止时间：</h3></td>
        <td width="128" align="left"><label>
            <input name ="time2" id="d4312" value="${param.time2}" class="Wdate" type="text"
									 onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01'})"/>
          </label></td>
      </tr>
    </table>
  </div>
  </form>
  <form name="selectForm" action="" id="selectForm" method="post">
  <div id="bottom">
    <table id="tb" width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr id="tb1">
        <td width="38">编号</td>
        <td width="128">开启时间</td>
        <td width="131">结束时间 </td>
        <td width="61">出发地 </td>
        <td width="89">目的地 </td>
        <td width="70">报警门限</td>
        <td width="84">发货人</td>
        <td width="90">承运人 </td>
        <td width="70">收货人</td>
        <td width="120">状态</td>
        <td width="120">操作 </td>
      </tr>
      <c:forEach var="startup" items="${startList}" varStatus="i">
      			<tr ${i.count%2==0?"class='altrow'":"" }>
					<td>${i.count }</td>
					<td>${startup.btimeStr }</td>
					<td>${startup.etimeStr }</td>
					<td>${startup.beginAddress }</td>
					<td>${startup.endAddress }</td>
					<td>
						<%-- 
						<c:choose>
							<c:when test="${startup.tlimitType==1}">
								2-8℃
							</c:when>
							<c:when test="${startup.tlimitType==2}">
								2-20℃
							</c:when>
							<c:when test="${startup.tlimitType==3}">
								2-30℃
							</c:when>
							<c:otherwise>
								----
							</c:otherwise>
						</c:choose>
					--%>
						${startup.tdwlimit }-${startup.tuplimit }℃
					</td>
					<td>${startup.shipper }</td>
					<td>${startup.carrier }</td>
					<td>${startup.receiver }</td>
					<td>${startup.updateStatus==2?"上传完成":"未完成上传" }</td>
					<td>
						<img  src="img/menu/bt_plan.gif"  title="车载历史数据" style="cursor:pointer" onclick="javascript:godetail('hiscar.do','toHisCarByStart','${startup.id}',true);"/> 
        				<img  src="img/menu/bt_orders.gif" title="车载历史曲线"   style="cursor:pointer;display:${fn:contains(power,"车载历史曲线")==true?'inline':'none'}"  onclick="javascript:godetail('hiscar.do','toCurve','${startup.id}',true);"/>
        				<img  src="img/menu/bt_deal.gif" title="车载行驶历史轨迹回放" style="cursor:pointer;display:${fn:contains(power,"车载历史回放")==true?'inline':'none'}" onclick="javascript:godetail('hiscar.do','toHisMap',${startup.id },true);"/> 
        				<img  src="img/menu/bt_relay.gif" title="车载行驶历史轨迹追溯"  style="cursor:pointer;display:${fn:contains(power,"车载历史追溯")==true?'inline':'none'} " onclick="javascript:godetail('hiscar.do','toAllHisMap',${startup.id },true);"/>
        	 		</td>
					</tr>
	  </c:forEach>
	  
	 
	  <%--如果当前没有起停记录、则显示提示信息 --%> 
	  <logic:empty name="startList">
					<tr align="center"><td colspan="11">${flag==null?" ":"<font color='red' size='2'>选择时间范围内没有相应的启停记录</font>" }</td></tr>
	   				<tr><td colspan="11" height="400px"></td></tr>
	   </logic:empty>
	   
	    
	    <%--为了也没风格统一，根据记录条数设置行高 --%>
	    <logic:notEmpty name="startList">
	    	<bean:size id="rheight" name="startList" />
	    	<c:if test="${rheight lt 10}">
	    		<tr><td colspan="11" height="250px"></td></tr>
	    	</c:if>
	    	
	    	<c:if test="${rheight ge 10 }">
	    		<tr><td colspan="11" height="110px"></td></tr>
	    	</c:if>
	    </logic:notEmpty>
    </table>
  </div>
  	
  	<input type="hidden" id="branchId" name="branchId" value="${param.branchId }"/> 
    <input type="hidden" id="t1"  name="t1" value="${param.time1 }"/>
    <input type="hidden" id="t2" name="t2" value="${param.time2 }"/>
    <input type="hidden" id="sid" name="sid" value=""/>
	<input type="hidden" id="proId" name="proId" value="${param.proId }"/>		
  </form>
</div>
<div class="clear"></div>
<div>
  <iframe scrolling="no" src="common/footer2.jsp" width=100% height=43 frameborder=0></iframe >
</div>
</body>
</html>
