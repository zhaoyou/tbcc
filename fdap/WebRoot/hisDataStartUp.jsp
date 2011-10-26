<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看${orgname }的车载启停记录</title>
<link rel="Shortcut Icon" href="images/logo.ico" />
<link href="css/index/hiscar_startup.css" rel="stylesheet" type="text/css" />
<script src="DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
		function godetail(url,ope,sid,time1,time2,carrier){
			//var carrefid=document.getElementById("carrefId").value;
			//var checkedrefid=document.getElementById("checkedrefId").value;
			//if(carrefid!=checkedrefid){
			//	alert("已查询启停记录的车载与当前选择的车载不同，请重新查询或更改当前车载");
			//}
			//else{
				document.getElementById("sid").value=sid;
				document.getElementById("time1").value=time1;
				document.getElementById("time2").value=time2;
				document.getElementById("carrier").value=carrier;
				gosumb(url,ope);
			//}
		}
		
		function isvalid(str){
				if(str.charAt(4)=="-" && str.charAt(7)=="-" &&
				   str.charAt(10)==" " && str.charAt(13)==":" && str.charAt(16)==":")
					return true ;
				return false ;
			}
		
		function firstPage(url,ope){
			document.getElementById("suppage").value=1;
			gosumb(url,ope);
		}
		
		function lastPage(url,ope){
			document.getElementById("suppage").value=document.getElementById("suppagecount").value;
			gosumb(url,ope);
		}
	
		function nextPage(url,ope){
			var nextPages=document.getElementById("suppage").value;
			document.getElementById("suppage").value=parseInt(nextPages)+1;
			gosumb(url,ope);
		}
		
		function prePage(url,ope){
			var prePages=document.getElementById("suppage").value;
			document.getElementById("suppage").value=parseInt(prePages)-1;
			gosumb(url,ope);
		}
		
		function gosumb(url,ope){
			document.getElementById("ope").value=ope;
			document.getElementById("selectForm").action=url;
			document.selectForm.submit();
		}
		
		function dosearch(url,ope){
			var carrefId=document.getElementById("carrefId").value;
			if(carrefId==""){
				document.getElementById("msg").innerHTML="";
				window.alert('该企业下没有车载，请确认无误后查询');
				return;
			}
			var startTime=document.getElementById("startTime").value;
			var endTime=document.getElementById("endTime").value;
			if(startTime==""||endTime==""){
				document.getElementById("msg").innerHTML="";
				window.alert('请选择开始和结束时间');
				return;
			}
			if(!isvalid(startTime)||!isvalid(endTime)){
				document.getElementById("msg").innerHTML="";
				alert("请输入合法的日期格式");
				return;
			}
			gosumb(url,ope);
		}
		
		
		
		function goMap(url,ope,mapstartTime){
			document.getElementById("mapstartTime").value=mapstartTime;
			gosumb(url,ope);
		}
</script>

</head>
<body>

<div>
  <iframe scrolling="no" src="head.jsp" width=100% height=128 frameborder=0></iframe >
</div>
<div id="content">
  <div id="center" style="height:600px">
    <div class="top">
      <table width="985" border="0" cellpadding="0" cellspacing="0" id="tb" >
        <tr id="tb1" >
          <td width="983" colspan="7"><span style="text-align:left; float:left;padding-left:4px; margin-left:4px; color:#141414;"><img src=images/index/icon.gif width="9" height="10" /> 当前位置： 查看${orgname }的车载启停记录</span>
          <a href="javascript:gosumb('org.do','toHigherOrg');"><img src="images/index/back.gif" width="58" height="21" 
        class="pho"/></a></td>
        </tr>
      </table>
    </div>
    <div class="bottom">
    <form action="startUp.do" name="selectForm" id="selectForm" method="post">
	<input type="hidden" id="sid" name="sid" value=""/>
	<input type="hidden" id="mapstartTime" name="mapstartTime" value="" />
    <input type="hidden" id="oid" name="oid" value="${param.oid }"/>
   <%--  <input type="hidden" id="projectId" name="projectId" value="${projectId }"/> --%>
    <input type="hidden" name="time1" id="time1" value=""/>
    <input type="hidden" name="time2" id="time2" value=""/>
    <input type="hidden" name="ope" id="ope" value="doStartUpByRefAndtime"/>

	<input type="hidden" name="carrier" id="carrier" value="${carrier }" />
	
	<input type="hidden" name="checkedrefId" id="checkedrefId" value="${checkedrefId }"/>
	<input type="hidden" name="checkedstartTime" id="checkedstartTime" value="${checkedstartTime }"/>
	<input type="hidden" name="checkedendTime" id="checkedendTime" value="${checkedendTime }"/>
	
	<input type="hidden" name="suppagecount" id="suppagecount" value="${suppagecount }"/>
    <input type="hidden" name="suppage" id="suppage" value="${suppage }"/>
    
    <input type="hidden" name="pNO" id="pNO" value="${pNO }" />
    
      <table id="tb" border="0" cellspacing="0" cellpadding="0">
        <tr bgcolor="#CCCCCC">
          <td height="28" colspan="7" bgcolor="#dff4fc"><table width="100%" style="height:32px; border-bottom-width: 1px;border-bottom-style: solid;border-bottom-color: #b6d6e6; background-color:#FFF;">
            <tr>
              <td width="110" height="24" style="border:none;"><a href="javascript:gosumb('refHis.do','toRefHis');"><img src="images/index/alarm_e.gif" width="100" height="24" /></a></td>
              <td width="100" style="border:none;"><a href="#"><img src="images/index/alarm_d.gif" width="100" height="24" /></a></td>
              <td width="110" style="border:none;">&nbsp;</td>
              <td width="608" style="border:none;">&nbsp;</td>
            </tr>
          </table>
            <table width="980" border="0" height="27">
              <tr>
                <td width="82" height="23" style="border:none;">企业名称：</td>
                <td width="107"  style="border:none;"><label>
                    <%--<input name="textfield" type="text" value="A企业" size="15" /> --%>
                    <input name="orgname" id="orgname" type="text" value="${orgname }" size="15" readonly="readonly" style="text-align: center;background-color: #f1f4f8;" />
                  </label></td>
                <td width="66" style="border:none;"><label>选择车载: </label></td>
                <td width="150"  style="border:none;">
                <select name="carrefId" id="carrefId">
    			<c:forEach var="carref" items="${carreflist}">
    				<option value="${carref.refId }" ${checkedrefId==carref.refId?"selected=selected":"" }>${carref.name }</option>
    			</c:forEach>
    			</select>
                </td>
                <td width="66"  style="border:none;"><label>开始时间：</label></td>
                <td width="160"  style="border:none;">
                <input  name="startTime" id="startTime" value="${checkedstartTime}"  class="Wdate" type="text" size="21"
										 onClick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:00',maxDate:'#F{$dp.$D(\'endTime\')||\'2020-10-01\'}'})"/> 
        
                <%--  <input name="startTime" id="startTime" type="text" value="${startTime }" size="21" />--%>
                </td>
                <td width="68" style="border:none;"><label>结束时间:</label></td>
                <td width="162" style="border:none;">
                <input name ="endTime" id="endTime" value="${checkedendTime}" class="Wdate" type="text" size="21"
									 onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2020-10-01'})"/>
          		
                <%-- <input name="endTime" id="endTime" type="text" value="${endTime }" size="21" /> --%>
                </td>
                <td width="81" style="border:none;">
                <img src="images/index/search.gif" width="45" height="20" style="border:none;cursor:pointer;" onclick="javascript:dosearch('startUp.do','doStartUpByRefAndtime');"/>
                <%-- <input type="submit" name="Submit" value="" style=" border:none;width:45px; height:20px;cursor:pointer; background-image:url(images/index/search.gif)"/>--%>
                </td>
              </tr>
            </table></td>
        </tr>
      </table>
      </form>
      <table id="tb" border="0" cellspacing="0" cellpadding="0">
        <tr class="altrow" >
          <td width="66">编号</td>
          <td width="200">启动时间</td>
          <td width="204">结束时间</td>
          <td width="178">承运人</td>
          <td width="330">操作</td>
        </tr>
        
        <logic:notEmpty name="startList">
        <c:forEach var="startup" items="${startList}" varStatus="i">
      			<tr ${i.count%2==0?"bgcolor='#f1f4f8'":"" }>
					<td title="${startup.startUpId }">${i.count+((suppage-1)*pagesize) }</td>
					<td>${startup.startTime }</td>
					<td>${startup.endTime }</td>
					<td>${startup.carrier }</td>
					<td>
						<%-- <img  src="images/u80.gif"  title="车载历史数据" style="cursor:pointer" onclick="javascript:godetail('carHis.do','doCarhisbyStartup','${startup.startUpId}','${startup.startTime }','${startup.endTime }');"/>--%> 
						<a href="javascript:godetail('carHis.do','doCarhisbyStartup','${startup.startUpId}','${startup.startTime }','${startup.endTime }','${startup.carrier }');" style="text-decoration: none;">
							<img src="images/index/show_w.gif" title="车载历史数据" style="cursor:pointer" width="98" height="21" />
						</a>
						<a href="javascript:godetail('carHis.do','toHisCarcurve','${startup.startUpId}','${startup.startTime }','${startup.endTime }','${startup.carrier }');" style="text-decoration: none;">
							<img src="images/index/show_d.gif" title="车载历史曲线" style="cursor:pointer" width="78" height="21" />
						</a>
						<a href="javascript:goMap('carHis.do','toHisMap','${startup.startTime }');" style="text-decoration: none;">
							<img  src="images/index/show_gjhf.jpg" title="车载行驶历史轨迹回放"/> 
						</a>
						
					</td>
				</tr>
		</c:forEach>
        
        <tr>
          <td colspan="5"><table width="100%" border="0" cellpadding="0" cellspacing="0" >
            <tr >
              <td width="282" height="38" style="border: none;">&nbsp;</td>
              <td width="100" style="border: none;">
              	<c:if test="${suppage==1||suppage==null }">
    				<input type="button" value="首页" / class="inp_aaa" />
    			</c:if>
    			<c:if test="${suppage!=1&&suppage!=null }">
              		<input type="button" value="首页" / class="inp_aa" onclick="javascript:firstPage('startUp.do','doStartUpPage');"/>
              	</c:if>
              </td>
              <td width="100" style="border: none;">
              	<c:if test="${suppage==1||suppage==null }">
    				<input type="button" value="上一页" / class="inp_bbb" />
    			</c:if>
    			<c:if test="${suppage!=1&&suppage!=null }">
              		<input type="button" value="上一页" / class="inp_bb" onclick="javascript:prePage('startUp.do','doStartUpPage');"/>
              	</c:if>
              </td>
              <td width="100" style="border: none;">
              	<c:if test="${suppage==suppagecount }">
    				<input type="button" value="下一页" / class="inp_ccc" />
    			</c:if>
    			<c:if test="${suppage!=suppagecount }">
              		<input type="button" value="下一页" / class="inp_cc" onclick="javascript:nextPage('startUp.do','doStartUpPage');"/>
              	</c:if>
              </td>
              <td width="100" style="border: none;">
              	<c:if test="${suppage==suppagecount }">
    				<input type="button" value="尾页" / class="inp_ddd" />
    			</c:if>
    			<c:if test="${suppage!=suppagecount }">
              		<input type="button" value="尾页" / class="inp_dd" onclick="javascript:lastPage('startUp.do','doStartUpPage');"/>
              	</c:if>
              </td>
              <td width="94" style="border: none;">&nbsp;</td>
              <td width="94" style="border: none;">当前第<c:if test="${suppage==null }">0</c:if>${suppage }页</td>
              <td width="96" style="border: none;">共<c:if test="${suppagecount==null }">0</c:if>${suppagecount }页</td>
            </tr>
          </table></td>
        </tr>
        </logic:notEmpty>
      </table>
      <div align="center"><span id="msg">${msg }</span></div>
    </div>
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
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
