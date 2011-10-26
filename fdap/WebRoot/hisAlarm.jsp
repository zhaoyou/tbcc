<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看${orgname }的历史报警</title>
<link href="css/index/c_alarm.css" rel="stylesheet" type="text/css" />


<script src="DatePicker/WdatePicker.js"></script>

<script type="text/javascript" language="javascript">

		
		function isvalid(str){
				if(str.charAt(4)=="-" && str.charAt(7)=="-" &&
				   str.charAt(10)==" " && str.charAt(13)==":" && str.charAt(16)==":")
					return true ;
				return false ;
			}
		
		function firstPage(url,ope){
			document.getElementById("alarmpage").value=1;
			goData(url,ope);
		}
		
		function lastPage(url,ope){
			document.getElementById("alarmpage").value=document.getElementById("alarmpagecount").value;
			goData(url,ope);
		}
	
		function nextPage(url,ope){
			var nextPages=document.getElementById("alarmpage").value;
			document.getElementById("alarmpage").value=parseInt(nextPages)+1;
			goData(url,ope);
		}
		
		function prePage(url,ope){
			var prePages=document.getElementById("alarmpage").value;
			document.getElementById("alarmpage").value=parseInt(prePages)-1;
			goData(url,ope);
		}
		
		function goData(url,ope){
			document.myform.action = url ;
			document.getElementById("ope").value = ope ;
			document.myform.submit();
		}

		function dosearch(url,ope){
			var alarmStartTime=document.getElementById("alarmStartTime").value;
			var alarmEndTime=document.getElementById("alarmEndTime").value;
			if(alarmStartTime==""||alarmEndTime==""){
				//document.getElementById("msg").innerHTML="<font color='red'>请选择开始和结束时间</font>";
				document.getElementById("msg").innerHTML="";
				alert("请选择开始和结束时间");
				return;
			}
			if(!isvalid(alarmStartTime)||!isvalid(alarmEndTime)){
				document.getElementById("msg").innerHTML="<font color='red'>请输入合法的日期格式</font>";
				alert("请输入合法的日期格式");
				return;
			}
			goData(url,ope);
		}

</script>


<link rel="Shortcut Icon" href="images/logo.ico" />

</head>
<body>
<div>
  <iframe scrolling="no" src="head.jsp" width=100% height=128 frameborder=0></iframe >
</div>

<div id="content">
  <div id="center" style="height:600px">
   
    <div class="top">
   
      <table id="tb" border="0" cellspacing="0" cellpadding="0" >
        <tr id="tb1" >
          <td colspan="7"><span style="text-align:left; float:left;padding-left:4px; margin-left:4px; color:#141414;"><img src=images/index/icon.gif width="9" height="10" /> 当前位置：查看${orgname }的历史报警</span>
          <a href="javascript:goData('org.do','toHigherOrg');"><img src="images/index/back.gif" width="58" height="21" 
        class="pho"/></a></td>
        </tr>
      </table>
    </div>
    <div class="bottom">
    <form action="" name="myform" id="myform" method="post">
		<input type="hidden" name="ope" id="ope" value=""/> 
		<input type="hidden" name="oid"	id="oid" value="${oid }"/>
		
		<input type="hidden" id="alarmpage" name="alarmpage" value="${alarmpage }"/>
    	<input type="hidden" id="alarmpagecount" name="alarmpagecount" value="${alarmpagecount }"/>
		
		<input type="hidden" id="chaiType" name="chaiType" value="${chaiType }"/>
		<input type="hidden" id="chalarmLevel" name="chalarmLevel" value="${chalarmLevel }"/>
		<input type="hidden" id="chprojectType" name="chprojectType" value="${chprojectType }"/>
		
		<input type="hidden" name="checkedalarmStartTime" id="checkedalarmStartTime" value="${checkedalarmStartTime }"/>
		<input type="hidden" name="checkedalarmEndTime" id="checkedalarmEndTime" value="${checkedalarmEndTime }"/>
		
		<input type="hidden" name="orgname" id="orgname" value="${orgname }"/>
      
      <table id="tb" border="0" cellspacing="0" cellpadding="0">
        <tr bgcolor="#CCCCCC">
          <td height="61" colspan="7" bgcolor="#e8f3fc" ><table width="980" border="0" height="59">
            <tr>
              <td width="70" height="31" style="border:none;">探头类型：</td>
              <td width="258"  style="border:none;">
              
              <table width="91%" border="1" style="border-color: white;">
                <tr>
                  <td width="60" height="25" style="border:none;"><input name="aiType" id="aiType" type="radio" value="0" 
                  ${(chaiType=='0'||(chaiType!='0'&&chaiType!='1'&&chaiType!='4'))?"checked='checked'":"" } style="border:none;text-align: left;" />
                    温度</td>
                  <td width="60" style="border:none;"><input name="aiType" id="aiType" type="radio" value="1" ${chaiType=='1'?"checked='checked'":"" } style="border:none;text-align: left;"/>
                    湿度</td>
                  <td width="60" style="border:none;"><input name="aiType" id="aiType" type="radio" value="4" ${chaiType=='4'?"checked='checked'":"" } style="border:none;text-align: left;"/>
                    全部</td>
                </tr>
              </table>
              </td>
              
              <%-- 
              <td width="92" style="border:none;">报警严重级别：</td>
              <td width="255"  style="border:none;">
              <table width="91%" border="1" style="border-color: white;">
                <tr>
                  <td width="60" height="25" style="border:none;text-align: left;"><input name="alarmLevel" id="alarmLevel" type="radio" value="4" ${(chalarmLevel=='4'||(chalarmLevel!='4'&&chalarmLevel!='1'&&chalarmLevel!='2'&&chalarmLevel!='3'))?"checked='checked'":"" } style="border:none;"/>
                    全部</td>
                  <td width="60" style="border:none;text-align: left;"><input name="alarmLevel" id="alarmLevel" type="radio" value="1" ${chalarmLevel=='1'?"checked='checked'":"" } style="border:none;" />
                    严重</td>
                  <td width="60" style="border:none;text-align: left;"><input name="alarmLevel" id="alarmLevel" type="radio" value="2" ${chalarmLevel=='2'?"checked='checked'":"" } style="border:none;"/>
                    普通</td>
                  <td width="60" style="border:none;text-align: left;"><input name="alarmLevel" id="alarmLevel" type="radio" value="3" ${chalarmLevel=='3'?"checked='checked'":"" } style="border:none;"/>
                    轻微</td>
                </tr>
              </table></td>--%>
              <td width="80" style="border:none;">&nbsp;</td>
              <td width="70"  style="border:none;">存储类型：</td>
              <td width="259"  style="border:none;">
              <table width="91%" border="1" style="border-color: white;">
                <tr>
                  <td width="60" height="25" style="border:none;text-align: left;"><input name="projectType" id="projectType" type="radio" value="4" 
                  ${(chprojectType=='4'||(chprojectType!='4'&&chprojectType!='1'&&chprojectType!='2'))?"checked='checked'":"" } style="border:none;text-align: left;" />
                    全部</td>
                  <td width="60" style="border:none;text-align: left;"><input name="projectType" id="projectType" type="radio" value="1" ${chprojectType=='1'?"checked='checked'":"" } style="border:none;"/>
                    仓库</td>
                  <td width="60" style="border:none;text-align: left;"><input name="projectType" id="projectType" type="radio" value="2" ${chprojectType=='2'?"checked='checked'":"" } style="border:none;"/>
                    车载</td>
                </tr>
              </table></td>
              
              <!-- 没有了报警级别选项后添加的 -->
              <td colspan="1" style="border:none;">&nbsp;</td>
            </tr>
            <tr>
              <td height="22" style="border:none;">开始时间：</td>
              <td style="border:none;text-align: left;">
              <input  name="alarmStartTime" id="alarmStartTime" value="${checkedalarmStartTime}"  class="Wdate" type="text" size="21" style="text-align:left; margin-left:13px; display:block;"
										 onClick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:00',maxDate:'#F{$dp.$D(\'alarmEndTime\')||\'2020-10-01\'}'})"/> 
        
             <%--<input name="alarmStartTime" id="alarmStartTime" type="text" value="${alarmStartTime }" size="21"
                style="text-align:left; margin-left:13px; display:block;"/> --%>
              </td>
              <td  style="border:none;">&nbsp;</td>
              
              <td style="border:none;">结束时间：</td>
              <td  style="border:none;text-align: left;">
              <input name ="alarmEndTime" id="alarmEndTime" value="${checkedalarmEndTime}" class="Wdate" type="text" size="21" style="text-align:left; margin-left:13px; display:block;"
									 onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'alarmStartTime\')}',maxDate:'2020-10-01'})"/>
          		
              <%--<input name="alarmEndTime" id="alarmEndTime" type="text" value="${alarmEndTime }" size="21" style="text-align:left; margin-left:13px; display:block;" />--%> 
              </td>
              <td  style="border:none;text-align: left;"><img src="images/index/sousuo.gif"  style="cursor: pointer;" width="45" height="20" onclick="javascript:dosearch('hisAlarm.do','doAlarmHis');"/></td>
              
            </tr>
          </table></td>
        </tr>
      </table>
      </form>
      <table id="tb" border="0" cellspacing="0" cellpadding="0">
        <tr class="altrow" >
          <td width="70">编号</td>
          <td width="220">仓库/车载</td>
          <%-- <td width="244">报警级别</td>--%>
          <td width="178">开始时间</td>
          <td width="270">结束时间</td>
          <td width="200">持续时间</td>
          <td width="270">详细信息</td>
        </tr>
        
        <logic:notEmpty name="alarmList">
      	<%--<logic:iterate id="hisalarm" name="alarmList" indexId="irow">--%>
      	<c:forEach var="hisalarm" items="${alarmList }" varStatus="irow">
      		<tr ${irow.count%2==0?"bgcolor='#f1f4f8'":"" }>
      			<td>${irow.count+(alarmpage-1)*pagesize }</td>
      			<td>${hisalarm.fdapaiinfo.fdapref.name }</td>
      			<%--<td>
      				<c:if test="${hisalarm.alarmLevel==1 }">
      					<font color='red'>严重报警</font>
      				</c:if>
      				<c:if test="${hisalarm.alarmLevel==2 }">
      					<font color='pink'>普通报警</font>
      				</c:if>
      				<c:if test="${hisalarm.alarmLevel==3 }">
      					<font color='orange'>轻微报警</font>
      				</c:if>
      			</td>--%>
      			
				<td>
					<bean:write name="hisalarm" property="startTime" format="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<bean:write name="hisalarm" property="endTime" format="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${hisalarm.lastTime }s
				</td>
				<td>
					${hisalarm.fdapaiinfo.name }:${hisalarm.alarmData }${hisalarm.alarmType==1||hisalarm.alarmType==2?"℃":"％" }
					<c:if test="${hisalarm.alarmType==1}"><font color='blue'>↓</font></c:if>
					<c:if test="${hisalarm.alarmType==2}"><font color='red'>↑</font></c:if>
					<c:if test="${hisalarm.alarmType==3}"><font color='blue'>↓</font></c:if>
					<c:if test="${hisalarm.alarmType==4}"><font color='red'>↑</font></c:if>(${hisalarm.alarmStandard }${hisalarm.alarmType==1||hisalarm.alarmType==2?"℃":"％" })
      			</td>
      		</tr>
      		</c:forEach>
		<%--</logic:iterate>--%>
        <tr>
          <td colspan="7"><table width="100%" border="0" cellpadding="0" cellspacing="0" >
            <tr >
              <td width="282" height="32" style="border: none;">&nbsp;</td>
              <td width="100" style="border: none;">
              	<c:if test="${alarmpage==1||alarmpage==null }">
    				<input type="button" value="首页" / class="inp_aaa"/>
    			</c:if>
    			<c:if test="${alarmpage!=1&&alarmpage!=null }">
              		<input type="button" value="首页" / class="inp_aa" onclick="javascript:firstPage('hisAlarm.do','doAlarmHisPage');"/>
              	</c:if>
              </td>
              <td width="100" style="border: none;">
              	<c:if test="${alarmpage==1||alarmpage==null }">
    				<input type="button" value="上一页" / class="inp_bbb"/>
    			</c:if>
    			<c:if test="${alarmpage!=1&&alarmpage!=null }">
              		<input type="button" value="上一页" / class="inp_bb" onclick="javascript:prePage('hisAlarm.do','doAlarmHisPage');"/>
              	</c:if>
              </td>
              <td width="100" style="border: none;">
              	<c:if test="${alarmpage==alarmpagecount }">
    				<input type="button" value="下一页" / class="inp_ccc"/>
    			</c:if>
    			<c:if test="${alarmpage!=alarmpagecount }">
              		<input type="button" value="下一页" / class="inp_cc" onclick="javascript:nextPage('hisAlarm.do','doAlarmHisPage');"/>
              	</c:if>
              </td>
              <td width="100" style="border: none;">
              	<c:if test="${alarmpage==alarmpagecount }">
    				<input type="button" value="尾页" / class="inp_ddd"/>
    			</c:if>
    			<c:if test="${alarmpage!=alarmpagecount }">
              		<input type="button" value="尾页" / class="inp_dd" onclick="javascript:lastPage('hisAlarm.do','doAlarmHisPage');"/>
              	</c:if>
              </td>
              <td width="94" style="border: none;">&nbsp;</td>
              <td width="94" style="border: none;">当前第<c:if test="${alarmpage==null }">0</c:if>${alarmpage }页</td>
              <td width="96" style="border: none;">共<c:if test="${alarmpagecount==null }">0</c:if>${alarmpagecount }页</td>
            </tr>
          </table></td>
          </tr>
          </logic:notEmpty>
      </table>
      <div align="center"><span id="msg">${msg }</span></div>
    </div>
  </div>
</div>
<div>
  <iframe scrolling="no" src="footer.jsp" width=100% height=26 frameborder=0></iframe >
</div>
</body>
</html>
