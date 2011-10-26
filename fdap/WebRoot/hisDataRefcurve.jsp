<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看${orgname }的仓库历史曲线</title>
<link href="css/index/refhis_curve.css" rel="stylesheet" type="text/css" />

	
	<script type="text/javascript" src="dwr/interface/realcar.js"></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	
	<script src="DatePicker/WdatePicker.js"></script>
	
	<script type="text/javascript" language="javascript">
	
		function isvalid(str){
				if(str.charAt(4)=="-" && str.charAt(7)=="-" &&
				   str.charAt(10)==" " && str.charAt(13)==":" && str.charAt(16)==":")
					return true ;
				return false ;
			}
		
		
		function goData(url,ope){
				document.myform.action = url ;
				document.getElementById("ope").value = ope ;
				document.myform.submit();
			}
			
		function changed(obj){
			var projectid=obj.options[obj.selectedIndex].value;
			realcar.getRefByProjectId(projectid,{
				callback:result_handler,
				errorHandler:error_handler,
				timeout:25000
				});
		}
		
		function result_handler(result){
			if(result!=null)
			{
				var str="<select name='refId' id='refId'>";
				for(var i in result){
					str+="<option value="+result[i].refId+">"+result[i].name+"</option>";
				}
				str+="</select>";
				document.getElementById("refselect").innerHTML=str;
				if(result.length!=0){
					document.getElementById("err").innerHTML="";
					}
				else {
					document.getElementById("err").innerHTML="该仓库下没有冷库";
				}
			}
			else{
				document.getElementById("err").innerHTML="获取失败";
			}
		}
		function error_handler(){
			document.getElementById("err").innerHTML="获取失败";
		}
		
		
		
		function dosearch(url,ope){
			var projectid=document.getElementById("projectid").value;
			var refId=document.getElementById("refId").value;
			
			var refstartTime=document.getElementById("refstartTime").value;
			var refendTime=document.getElementById("refendTime").value;
			
			if(projectid==""){
				window.alert("该企业下没有仓库，请核对无误后查询");
				return;
			}
			
			if(refId==""){
				window.alert("该仓库下没有冷库，请核对无误后查询");
				return;
			}
			
			if(refstartTime==""||refendTime==""){
				window.alert("请选择开始和结束时间");
				return;
			}
			if(!isvalid(refstartTime)||!isvalid(refendTime)){
				window.alert("请输入合法的日期格式");
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
  <div id="center" style="height:705px">
  	<div class="top">
      <table id="tb" border="0" cellspacing="0" cellpadding="0" >
        <tr id="tb1" >
          <td colspan="7"><span style="text-align:left; float:left;padding-left:4px; margin-left:4px; color:#141414;">
          <img src=images/index/icon.gif width="9" height="10" /> 当前位置：查看${orgname }的仓库历史数据</span>
          <a href="javascript:goData('org.do','toHigherOrg');">
          <img src="images/index/back.gif" width="58" height="21" 
        class="pho"/></a></td>
        </tr>
      </table>
    </div>
    <div class="bottom">
    <form action="" name="myform" id="myform" method="post">
		<input type="hidden" name="ope" id="ope" value=""/> 
		<input type="hidden" name="oid"	id="oid" value="${param.oid }"/>
    	
      <table id="tb" border="0" cellspacing="0" cellpadding="0">
        <tr bgcolor="#CCCCCC">
          <td height="28" colspan="7" bgcolor="#dff4fc"><table width="980" border="0" height="52">
                   <tr>
                <td height="23" colspan="8" style="border:none;">
                <table width="100%" style="height:32px; border-bottom-width: 1px;border-bottom-style: solid;border-bottom-color: #b6d6e6; background-color:#FFF;">
            <tr>
              <td width="110" height="24" style="border:none;"><%-- <a href="hisDataRef.jsp">--%><a href="#"><img src="images/index/alarm_e.gif" width="100" height="24" /></a></td>
              <td width="110" style="border:none;">
              <%-- <a href="hisDataStartUp.jsp"><img src="images/index/alarm_d.gif" width="100" height="24" /></a>--%>
              <a href="javascript:goData('carHis.do','toHisStartUp');"><img src="images/index/alarm_d.gif" width="100" height="24" /></a>
              </td>
              <td width="110" style="border:none;">&nbsp;</td>
              <td width="608" style="border:none;">&nbsp;</td>
            </tr>
          </table>
                </td>
              </tr>
              <tr>
                <td width="88" height="23" style="border:none;">企业名称：</td>
                <td width="152"  style="border:none; text-align:left;"><label>
                    <input name="orgname" id="orgname" type="text" value="${orgname }" size="21" disabled="disabled" style="text-align: center;background-color: #f1f4f8;"/>
                  </label></td>
                <td width="83" style="border:none;"><label>选择仓库： </label></td>
                <td width="149"  style="border:none; text-align:left;">
                	<select name="projectid" id="projectid" onchange="changed(this)">
                    <%--<option value="0">全部仓库</option> --%>
                    <c:forEach var="project" items="${prolist}">
    					<option value="${project.projectid }" ${checkedprojectid==project.projectid?"selected=selected":"" }>${project.name }</option>
    				</c:forEach>
                  </select></td>
                <td width="63"  style="border:none;">选择冷库：</td>
                <td width="136"  style="border:none;">
                <span id="refselect">
    			<select	name="refId" id="refId">
    				<c:forEach var="refref" items="${reflist}">
    					<option value="${refref.refId }" ${checkedrefId==refref.refId?"selected=selected":"" }>${refref.name }</option>
    				</c:forEach>
    			</select>
    			</span>
                  </td>
                <td width="110" style="border:none;"><font color='blue'><span id="err"></span></font></td>
                <td width="165" style="border:none;">&nbsp;</td>
              </tr>
              <tr>
                <td height="23" style="border:none;">开始时间：</td>
                <td  style="border:none; text-align:left;">
                <input  name="refstartTime" id="refstartTime" value="${param.refstartTime}"  class="Wdate" type="text" size="21"
										 onClick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:00',maxDate:'#F{$dp.$D(\'refendTime\')||\'2020-10-01\'}'})"/> 
        
               	<%--<input name="refstartTime" id="refstartTime" type="text" value="${refstartTime }" size="21" /> --%>
                </td>
                <td style="border:none;">结束时间：</td>
                <td  style="border:none; text-align:left;">
                <input name =refendTime id="refendTime" value="${param.refendTime}" class="Wdate" type="text" size="21"
									 onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'refstartTime\')}',maxDate:'2020-10-01'})"/>
          		
                <%--<input name="refendTime" id="refendTime" type="text" value="${refendTime }" size="21" />--%>
                </td>
                <td  style="border:none;" colspan="2">&nbsp;</td>
                <td style="border:none;" colspan="2"><img src="images/index/search.gif" width="45" height="20" style="cursor:pointer;" onclick="javascript:dosearch('refHis.do','doRefHisData');"/>
                &nbsp;&nbsp;<img src="images/index/hiscurve.gif" width="55" height="20" style="cursor:pointer;" onclick="javascript:dosearch('refHis.do','doRefHisCurve');"/></td>
                
              </tr>
            </table></td>
        </tr>
      </table>
      </form>
      
    </div>
    <div>
  		<iframe scrolling="no" src="fdap/hisref.html?oid=${param.oid }&refId=${checkedrefId }&startTime=${checkedrefstartTime}&endTime=${checkedrefendTime }" width=100% height="630"  frameborder=0></iframe >
	</div>
  </div>
</div>
<br/>
<div>
  <iframe scrolling="no" src="footer.jsp" width=100% height=26 frameborder=0></iframe >
</div>
</body>
</html>
