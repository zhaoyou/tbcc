<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看${orgname }的仓库历史数据</title>
<link href="css/index/his_data.css" rel="stylesheet" type="text/css" />

	
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
		
		function firstPage(url,ope){
			document.getElementById("refpage").value=1;
			goData(url,ope);
		}
		
		function lastPage(url,ope){
			document.getElementById("refpage").value=document.getElementById("refpagecount").value;
			goData(url,ope);
		}
	
		function nextPage(url,ope){
			var nextPages=document.getElementById("refpage").value;
			document.getElementById("refpage").value=parseInt(nextPages)+1;
			goData(url,ope);
		}
		
		function prePage(url,ope){
			var prePages=document.getElementById("refpage").value;
			document.getElementById("refpage").value=parseInt(prePages)-1;
			goData(url,ope);
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
				document.getElementById("msg").innerHTML="";
				window.alert("该企业下没有仓库，请核对无误后查询");
				return;
			}
			
			if(refId==""){
				document.getElementById("msg").innerHTML="";
				window.alert("该仓库下没有冷库，请核对无误后查询");
				return;
			}
			
			if(refstartTime==""||refendTime==""){
				document.getElementById("msg").innerHTML="";
				window.alert("请选择开始和结束时间");
				return;
			}
			if(!isvalid(refstartTime)||!isvalid(refendTime)){
				document.getElementById("msg").innerHTML="";
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
  <div id="center" style="height:660px">
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
    	
    	<input type="hidden" name="checkedprojectid" id="checkedprojectid" value="${checkedprojectid }"/>
    	<input type="hidden" name="checkedrefId" id="checkedrefId" value="${checkedrefId }"/>
    	<input type="hidden" name="checkedrefstartTime" id="checkedrefstartTime" value="${checkedrefstartTime }"/>
    	<input type="hidden" name="checkedrefendTime" id="checkedrefendTime" value="${checkedrefendTime }"/>
    	
    	<input type="hidden" id="refpage" name="refpage" value="${refpage }"/>
    	<input type="hidden" id="refpagecount" name="refpagecount" value="${refpagecount }"/>
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
                <input  name="refstartTime" id="refstartTime" value="${checkedrefstartTime}"  class="Wdate" type="text" size="21"
										 onClick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:00',maxDate:'#F{$dp.$D(\'refendTime\')||\'2020-10-01\'}'})"/> 
        
               	<%--<input name="refstartTime" id="refstartTime" type="text" value="${refstartTime }" size="21" /> --%>
                </td>
                <td style="border:none;">结束时间：</td>
                <td  style="border:none; text-align:left;">
                <input name =refendTime id="refendTime" value="${checkedrefendTime}" class="Wdate" type="text" size="21"
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
      <table id="tb" border="0" cellspacing="0" cellpadding="0" width="100%">
      
      	<tr class="altrow">
      	<td width="70" align="center">编号</td>
        <td width="120" align="center">更新时间</td>
        <c:forEach var="aiinfo" items="${refAiList}">
					<td width="60" align="center">
									${aiinfo.name }
					</td>
		</c:forEach>
		<logic:empty name="refAiList">
					<td width="300" align="center">
									探头
					</td>
		</logic:empty>
        <td width="80" align="center">报警状态</td>
        
      </tr>
      <logic:notEmpty name="refhisList">
				<%--<logic:iterate id="hisref" name="refhisList" indexId="irow">--%>
				<c:forEach var="hisref" items="${refhisList}" varStatus="irow">	
					<tr ${irow.count%2==0?"bgcolor='#f1f4f8'":"" }>
						<td align="center"  nowrap="nowrap">${irow.count+((refpage-1)*pagesize) }</td>
						<td align="center"  nowrap="nowrap">
						 	${hisref[fn:length(refAiList)]}
						</td>
						<c:forEach begin="1" end="${fn:length(refAiList) }" var="rrow">
						<td align="center" nowrap="nowrap">	
								${hisref[rrow-1]==-300?"--":hisref[rrow-1] }
						</td>
						</c:forEach>
						 <td align="center"  nowrap="nowrap">
						 	<c:if test="${hisref[fn:length(refAiList)+1]==0 }">
						 		<img src="images/index/29.gif" title='表示处于报警状态' width="16" height="10"/>
						 	</c:if>
						 	<c:if test="${hisref[fn:length(refAiList)+1]==1 }">
						 		<img src="images/index/28.gif" title='表示处于正常状态' width="16" height="10"/>
						 	</c:if>
						 </td>
					</tr>
					</c:forEach>
				<%--</logic:iterate>--%>
					
					<c:if test="${refpage==refpagecount&&refpage!=null&&refpagecount!=null }">
					
					<tr>
						<td>&nbsp;</td>
       					<c:forEach begin="1" end="${fn:length(refAiList) }">
       						<td>&nbsp;</td>
       					</c:forEach>
        				<td>&nbsp;</td>
        				<td>&nbsp;</td>
					</tr>
					
					<tr bgcolor="#f1f4f8">
						<td align="center">&nbsp;</td>
						<td align="center"><font color="blue" size="4">统计</font></td>
						<%--<logic:iterate id="aiinfo" name="refAiList">--%>
						<c:forEach var="aiinfo" items="${refAiList}">
       					<td align="center"><font color="blue" size="3">${aiinfo.name }</font></td>
        				<%-- <td align="center"><font color="blue" size="3">T2</font></td>
        				<td align="center"><font color="blue" size="3">T3</font></td>
        				<td align="center"><font color="blue" size="3">RH1</font></td> --%>
        				</c:forEach>
        				<%--</logic:iterate>--%>
        				<logic:empty name="refAiList">
						<td>探头</td>
						</logic:empty>
        				<td align="center">&nbsp;</td>
					</tr>
					
					
					<%--<logic:iterate id="MMA" name="refListMMA" indexId="outrow">--%>
					<c:forEach var="MMA" items="${refListMMA}" varStatus="outrow">
					<tr ${outrow.count%2==0?"bgcolor='#f1f4f8'":"" }>
						<td align="center"  nowrap="nowrap">&nbsp;</td>
						<td align="center"  nowrap="nowrap"><font color="blue" size="2">
						<c:if test="${outrow.count==1 }">最大值</c:if>
						<c:if test="${outrow.count==2 }">最小值</c:if>
						<c:if test="${outrow.count==3 }">平均值</c:if>
						</font></td>
						<%--<logic:iterate id="m" name="MMA" indexId="inrow">--%>
						<c:forEach var="m" items="${MMA}" varStatus="introw">
       					<td align="center"  nowrap="nowrap">${m==-300?"--":m }</td>
       					</c:forEach>
       	 				<%--</logic:iterate>--%>
       	 				
       	 				<td align="center"  nowrap="nowrap">&nbsp;</td>
					</tr>
					</c:forEach>
					<%--</logic:iterate>--%>
					</c:if>
        <tr>
          <td colspan="10"><table width="100%" border="0" cellpadding="0" cellspacing="0" >
              <tr >
                <td width="94" height="38" style="border: none;">&nbsp;</td>
                <td width="94" style="border: none;">&nbsp;</td>
                <td width="94" style="border: none;">&nbsp;</td>
                <td width="100" style="border: none;">
              	<c:if test="${refpage==1||refpage==null }">
    				<input type="button" value="首页" / class="inp_aaa" />
    			</c:if>
    			<c:if test="${refpage!=1&&refpage!=null }">
              		<input type="button" value="首页" / class="inp_aa" onclick="javascript:firstPage('refHis.do','doRefPage');"/>
              	</c:if>
              </td>
              <td width="100" style="border: none;">
              	<c:if test="${refpage==1||refpage==null }">
    				<input type="button" value="上一页" / class="inp_bbb" />
    			</c:if>
    			<c:if test="${refpage!=1&&refpage!=null }">
              		<input type="button" value="上一页" / class="inp_bb" onclick="javascript:prePage('refHis.do','doRefPage');"/>
              	</c:if>
              </td>
              <td width="100" style="border: none;">
              	<c:if test="${refpage==refpagecount }">
    				<input type="button" value="下一页" / class="inp_ccc" />
    			</c:if>
    			<c:if test="${refpage!=refpagecount }">
              		<input type="button" value="下一页" / class="inp_cc" onclick="javascript:nextPage('refHis.do','doRefPage');"/>
              	</c:if>
              </td>
              <td width="100" style="border: none;">
              	<c:if test="${refpage==refpagecount }">
    				<input type="button" value="尾页" / class="inp_ddd" />
    			</c:if>
    			<c:if test="${refpage!=refpagecount }">
              		<input type="button" value="尾页" / class="inp_dd" onclick="javascript:lastPage('refHis.do','doRefPage');"/>
              	</c:if>
              </td>
                <td width="94" style="border: none;">&nbsp;</td>
                <td width="94" style="border: none;">当前第<c:if test="${refpage==null }">0</c:if>${refpage }页</td>
                <td width="96" style="border: none;">共<c:if test="${refpagecount==null }">0</c:if>${refpagecount }页</td>
              </tr>
          </table></td>
        </tr>
        </logic:notEmpty>
      </table>
      <div align="center"><span id="msg">${msg }</span></div>
    </div>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
  </div>
</div>

<div>
  <iframe scrolling="no" src="footer.jsp" width=100% height=26 frameborder=0></iframe >
</div>
</body>
</html>
