<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>机构列表页面</title>
<link href="css/index/company.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="images/logo.ico" />


 <script type='text/javascript' src='dwr/interface/realcar.js'></script>
 <script type='text/javascript' src='dwr/engine.js'></script>
 <script type='text/javascript' src='dwr/util.js'></script>
 
<script type="text/javascript" language="javascript">
			
				//返回上级地图
				function goHighOrg(){
					document.myform.submit() ;
				}
				
				//机构查看
				function goData(url,method,id){
						document.myform.action = url ;
						document.getElementById("ope").value = method ;
						document.getElementById("oid").value = id ;
						document.myform.submit();
				}
				
				//页面初始化
				function init(){
					var oid = document.getElementById('oid').value ;
					if(oid==null || oid==""){
						return  ;
					}
					
					realcar.getAlarmTip(oid,{
						callback:resultHandler,
						errorHandler:error_Handler,
						timeout:10000
					});
					
				
					
					window.setInterval(function (){
						realcar.getAlarmTip(oid,{
						callback:resultHandler,
						errorHandler:error_Handler,
						timeout:10000
					});
					},10000);
					
				
				}
				
				//处理结果
				function resultHandler(result){
					document.getElementById('errorTip').style.display = "none" ;
					var ids = document.getElementById("ids").value ;
					if(ids==null || ids==""){
						return ;
					}
					var idslist = ids.split(",");
					//alert(idslist.length);
					/*if(result!=null && result.length!=0){
						for(var i =0;i<result.length;i++){
							var b = document.getElementById("alarmLightDiv"+result[i].oid);
							if(b!=null&&b!="undefine"){
								document.getElementById("alarmLightDiv"+result[i].oid).innerHTML = "<img src='images/index/29.gif' width='16' height='10'  alt='报警状态' />" ;	
							}
						}
					}*/
					for(var i=0;i<idslist.length;i++){
						var b = document.getElementById("alarmLightDiv"+idslist[i]);
						if(b!=null&&b!="undefine"){
							var f = true;
							if(result!=null&&result.length!=0){
								for(var j =0;j<result.length;j++){
									//alert(result[j].oid);
									if(result[j].oid==idslist[i]){
										f = false;
									}
								}
							}
							if(f){
								b.innerHTML = "<img src='images/index/28.gif' width='16' height='10' alt='正常状态' title='正常状态' />" ;
							}else{
								b.innerHTML = "<img src='images/index/29.gif' width='16' height='10'  alt='报警状态' title='报警状态' />" ;
							}
						}
					}
				}
				
				//错误处理程序
				function error_Handler(err){
						//document.getElementById('errorTip').innerHTML = err;
						document.getElementById('errorTip').style.display = "inline" ;		
				}
				
				
				//查看下级机构的历史报警统计
				function gohisAlarmCount(url,method){
						document.myform.action = url ;
						document.getElementById("ope").value = method ;
						document.myform.submit();
				}
</script>
<style type="text/css">
	#content #center .altrow_a {
	background-image: url(images/index/bg3.gif);
	background-repeat: repeat-x;
	background-position: 0px 0px;
	height: 40px;
	}

</style>
</head>
<body onload="init();">
<div>
  <iframe scrolling="no" src="head.jsp" width=100% height=128 frameborder=0></iframe >
</div>
<form action="org.do" name="myform" id="myform" method="post">
<input type="hidden" name="ope"  id="ope"  value="toHigherOrg"/>
<input  type="hidden" name="oid"  id="oid" value="${oid }"/>
<input type="hidden" name="ids" id="ids" value="${ids }"/>
<input type="hidden" name="orgName_statistics" id="orgName_statistics" value="${orgName }" />
</form>
<div id="content">
  <div id="center" >
    <div class="top" style="position:relative; display:block;">
      <table id="tb" border="0" cellspacing="0" cellpadding="0" >
        <tr id="tb1" >
          <td colspan="7"><span style="text-align:left; float:left;padding-left:4px; margin-left:4px; color:#141414;">
          <img src=images/index/icon.gif width="9" height="10" /> 当前位置：${orgName }下的机构信息列表
          	<span id="errorTip" style="color: red;display: none;" >(获取报警数据失败 ...)</span>
          </span>
          <%-- 
          <!-- 暂时不用机构企业地图 -->
          <span style="display: ${isshowmap==0?'none':'inline' }"><a href="org.do?ope=toChangeDisplay&oid=${oid }&showmap=2" style="text-decoration: none;">地图显示</a></span>--%>
          <span style="display:  ${loginUser.fdaporg.oid==oid?'none':'inline'}">
          <a href="javascript:goHighOrg();"><img src="images/index/back.gif" width="38" height="21" 
        	class="pho"/></a>
        	</span>
        </td>
        </tr>
      </table>
    </div>
    <div class="bottom">
      
      <table id="tb" border="0" cellspacing="0" cellpadding="0">
        <tr class="altrow" >
          <td width="81">编号</td>
          <td width="175">机构名称</td>
          <td width="164">机构账户</td>
          <td width="141">当前报警状态</td>
          <td width="417">操作</td>
        </tr>
        
        <c:forEach var="org" items="${orgList}" varStatus="i">
        	 <tr style="height:28px;" ${ i.count %2==0?"bgcolor='#f1f4f8'":"" }>
		          <td>${i.count }</td>
			          <td>${org.name }</td>
			          <td>${org.account }</td>
			          <td>
			          <div id="alarmLightDiv${org.oid }">
			          	<img src="images/index/28.gif" width="16" height="10" alt='正常状态' title='正常状态' />
			          </div>			  
			          </td>
			          <td><table width="100%" border="0" cellpadding="3" cellspacing="5">
			            <tr>
			              <td width="65"  style="border:none; text-align:center;"><a href="javascript:goData('org.do','toOrg',${org.oid });"><img src="images/index/show_jg.gif" width="75" height="21" /></a></td>
			              <td width="65" align="center" style="border:none; text-align:center;"></td>
			            </tr>
			          </table></td>
		        </tr>
        </c:forEach>
      </table>
    </div>
    <%--控制页面的显示高度 --%>
 
    <logic:notEmpty name="orgList" scope="request">
    		<bean:size id="rowSize" name="orgList"/>
    		<logic:lessEqual value="10" name="rowSize">
    				<div style="height: 340px;"></div>
    		</logic:lessEqual>
    		<logic:greaterThan value="10" name="rowSize">
    				<div style="height: 100px;"></div>
    		</logic:greaterThan>
    </logic:notEmpty>
    
    <logic:empty name="orgList" scope="request">
    		<div style="height: 400px"></div>
    </logic:empty>
    <!-- 统计功能暂时不要 -->
    <%-- 
    <table width="100%" border="0" cellpadding="0" cellspacing="0" style="margin-bottom: 5px;">
            <tr class="altrow_a">
              <td width="13%" style="border: none;">&nbsp;</td>
              <td width="7%" style="border: none;">&nbsp;</td>
              <td width="21%" style="border: none;"><a href="javascript:gohisAlarmCount('hisAlarm.do','tohisAlarmCount');"><img src="images/index/but_ee.gif" width="100" height="24" /></a></td>
              <td width="17%" style="border: none;"><a href="javascript:window.alert('暂未开通');"><img src="images/index/but_ff.gif" width="100" height="24" /></a></td>
              <td width="21%" style="border: none;"><a href="javascript:window.alert('暂未开通');"><img src="images/index/but_c.gif" width="100" height="24" /></a></td>
              <td width="8%" style="border: none;">&nbsp;</td>
              <td width="13%" style="border: none;">&nbsp;</td>
            </tr>
  </table>
  --%>
  
  
  
  </div>
  
</div>
<div>
  <iframe scrolling="no" src="footer.jsp" width=100% height=26 frameborder=0></iframe >
</div>
</body>
</html>
