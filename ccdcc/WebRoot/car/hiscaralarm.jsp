<%@ page language="java" import="java.util.*,org.tbcc.entity.TbccBaseHisCar,java.text.DecimalFormat" pageEncoding="gbk"%>
<%@include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>移动车载历史数据查看</title>
<link rel="Shortcut Icon" href="img/add/logo.ico" />
<link href="css/chezai/tcd.css" rel="stylesheet" type="text/css" />
<link href="css/chezai/input5.css" rel="stylesheet" type="text/css" />
<script src="DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="script/cExcel.js"></script>
<script src="script/common.js"></script>

<script type="text/javascript">
	function golist(url,operate){
		document.myform.time1.value = document.getElementById('t1').value ;
		document.myform.time2.value = document.getElementById('t2').value ;
		goAction(url,operate);
	}
	
	function goAction(url,operate){
		document.getElementById('ope').value = operate ;
		document.getElementById('myform').action = url ;
		document.myform.submit();
	}
	
	
		function query(){
				document.getElementById('myform').action = 'hiscaralarm.do' ;
				document.getElementById('ope').value = 'doHisCarAlarm' ;
				document.myform.submit() ;
			}
			
	function save(){
		var size = document.getElementById('listsize').value;
		
		var id_,cause_,measure_,dutier_,remark_;
		var str;
		for(var i=0;i<size;i++){
			id_=document.getElementById("id"+i).value;
			cause_=document.getElementById("cause"+i).value;
			measure_=document.getElementById("measure"+i).value;
			dutier_=document.getElementById("dutier"+i).value;
			remark_=document.getElementById("remark"+i).value;
			if(i==0) str=id_+","+cause_+","+measure_+","+dutier_+","+remark_;
			else str=str+";"+id_+","+cause_+","+measure_+","+dutier_+","+remark_;
		}
		document.getElementById("saveStr").value=str;
		goAction("hiscaralarm.do","doSaveCarHisAlarm");
	}
		
	
</script>

</head>
<body>
<div>
  <iframe scrolling="no" src="common/header2.jsp" width=100% height=126 frameborder=0></iframe >
</div>
<form action="" method="post" name="myform" id="myform">	
<input type="hidden" name="branchId" id="branchId" value="${param.branchId }" />
<input type="hidden" id="ope" name="ope" value =""/>
<input type="hidden" name="t1" id="t1" value="${param.t1 }"/>
<input type="hidden" name="t2" id="t2"  value="${param.t2 }"/>
<input type="hidden" id="sid" name="sid" value="${param.sid}"/>
<input type="hidden" id="proId" name="proId" value="${project==null?param.proId:project.projectId }"/>

<input type="hidden" id="listsize" name="listsize" value="${fn:length(hiscaralarmList) }" />

<input type="hidden" id="saveStr" name="saveStr" value="" />

<div id="right">
  <div id="top"><a href="#"><img src="images/chezai/icon_c.gif" width="16" height="15"  class="tb4"/>
  <strong>位置:首页</strong>&lt;您正在查看移动车载历史数据查看</a>
  <img src="images/chezai/back.gif" width="48" height="20" class="tb3"/>
  <img src="img/util/back.gif" width="48" height="20"  class="tb3" style="cursor: pointer;" onclick="javascript:golist('startup.do','doStartUpCar');"/>
  <img src="images/chezai/chaxun.gif" width="58" height="21"  class="tb5" style="cursor: pointer;" onclick="javascript:query();" />
  <img src="images/chezai/save.gif" width="58" height="21"  class="tb6" style="cursor: pointer; display:${hiscaralarmList==null?'none':'inline' }" onclick="save();"/></div>
  <div id="center"></div>
  <div id="center">
    <table width="100%" id="bd3">
      <tr>
        <td width="65" style="width:65px;">
        <h3 style="text-align:left; font-size:12px;padding-left:0px; padding-right:0px; width:65px; color:#454343;">车载名称：</h3></td>
        <td style="width:160px;">
        <input type="text"  id="proName" name="proName" value="${project==null?param.proName:project.projectName }" readonly="readonly" 
									></td>
									
        <td width="63" style="width:65px;">
        <h3 style="text-align:left; font-size:12px;padding-left:0px; padding-right:0px; width:65px; color:#454343;">起始时间：</h3></td>
        <td style="width:160px;">
        <input  name="time1" id="d4311" value="${startup==null?param.time1:startup.btimeStr}"  class="Wdate" type="text"
									 readonly="readonly"/> 
          
          </td>
        <td width="65" style="width:65px;">
        <h3 style="text-align:left; font-size:12px;padding-left:0px; padding-right:0px; width:65px; color:#454343;">终止时间：</h3></td>
        <td style="width:160px;">
       		<input name ="time2" id="d4312" value="${startup==null?param.time2:startup.etimeDisplay}" class="Wdate" type="text"
									 readonly="readonly"/>
          </td>
        <td style="width:85px;">&nbsp;</td>
        <td width="183"><span style="float:left;">
         &nbsp;</span></td>
      </tr>
    </table>
  </div>
  <div id="center">
    <table width="100%" id="bd3">
      <tr>
        <td width="65" style="width:65px;"><h3 style="text-align:left; font-size:12px;padding-left:0px; padding-right:0px; width:65px; color:#454343;">出发地：</h3></td>
        <td style="width:160px;">
        
        <input name="beginaddr" id="beginaddr" value="${startup==null?param.beginaddr:startup.beginAddress }" readonly="readonly"/>
        
        </td>
        <td width="63" style="width:65px;"><h3 style="text-align:left; font-size:12px;padding-left:0px; padding-right:0px; width:65px; color:#454343;">目的地：</h3></td>
        <td style="width:160px;">
        <input name="endaddr" id="endaddr" value="${startup==null?param.endaddr:startup.endAddress }" readonly="readonly" />
          </td>
          
        <td width="65" style="width:65px;"><h3 style="text-align:left; font-size:12px;padding-left:0px; padding-right:0px; width:65px; color:#454343;">承运人：</h3></td>
        <td style="width:160px;">
        
        <input name="carrier" id="carrier" value="${startup==null?param.carrier:startup.carrier }" readonly="readonly" />
          
          </td>
        <td style="width:85px;">&nbsp;</td>
        <td width="163"><span style="float:left;">
          &nbsp;
          </span></td>
      </tr>
    </table>
  </div>
  <div id="bottom">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" id="tb"  >
      <tr id="tb1">
        <td width="30">序号</td>
        <td width="120">起始时间</td>
        <td width="120">终止时间</td>
        <td width="120">事件</td>
        <td width="120">报警原因</td>
        <td width="120">采取措施</td>
        <td width="60">责任人</td>
        <td width="210">备注</td>
      </tr>
      <logic:notEmpty name="hiscaralarmList">
				<logic:iterate id="hiscaralarm" name="hiscaralarmList" indexId="irow">
				
					<tr ${irow%2==0?"class='altrow'":"" }>
						<td align="center"  nowrap="nowrap">${irow+1 }<input type="hidden" id="id${irow }" value="${hiscaralarm.id }" /></td>
						<td align="center"  nowrap="nowrap">
							<bean:write name="hiscaralarm" property="startTime" format="yyyy-MM-dd HH:mm:ss"/>
						</td>
						
						<td align="center"  nowrap="nowrap">
							<bean:write name="hiscaralarm" property="endTime" format="yyyy-MM-dd HH:mm:ss"/>
						</td>
						
						<td align="center" nowrap>${hiscaralarm.events }</td>
						
						<td align="center" nowrap><input id="cause${irow }" type="text" style="width: 125px;" value="${hiscaralarm.cause }" /></td>
						
						<td align="center" nowrap><input id="measure${irow }" type="text" style="width: 125px;" value="${hiscaralarm.measure }" /></td>
						
						<td align="center" nowrap><input id="dutier${irow }" type="text" style="width: 65px;" value="${hiscaralarm.dutier }" /></td>
						
						<td align="center" nowrap><input id="remark${irow }" type="text" style="width: 215px;" value="${hiscaralarm.remark }" /></td>
						
						
						<%-- 
						<td align="center" nowrap="nowrap">
						${hiscar.latitudeStr }
						
						</td>
						
						<td align="center" nowrap="nowrap">
						${hiscar.longitudeStr }				
						</td>
						 --%>
					</tr>
				</logic:iterate>			
				
			</logic:notEmpty>		
    </table>
    <div>
    		<logic:empty name="hiscaralarmList">
    			<div style="height: 450px">&nbsp;</div>
			</logic:empty>
			
				<%--为了也没风格统一，根据记录条数设置行高 --%>
			    <logic:notEmpty name="hiscaralarmList">
			    	<bean:size id="rheight" name="hiscaralarmList" />
			    	
			    	<c:if test="${rheight lt 10}">
			    		<div style="height: 250px">&nbsp;</div>
			    	</c:if>
			    	
			    	<c:if test="${rheight ge 10 }">
			    		<div style="height: 110px">&nbsp;</div>
			    	</c:if>
			    	
			    </logic:notEmpty>
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
