<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="common/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看${param.orgname }的车载启停记录-&gt;查看历史数据</title>
<link href="css/index/hiscar_data.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	
		function firstPage(url,ope){
			document.getElementById("page").value=1;
			goAction(url,ope);
		}
		
		function lastPage(url,ope){
			document.getElementById("page").value=document.getElementById("carpagecount").value;
			goAction(url,ope);
		}
	
		function nextPage(url,ope){
			var nextPages=document.getElementById("page").value;
			document.getElementById("page").value=parseInt(nextPages)+1;
			goAction(url,ope);
		}
		
		function prePage(url,ope){
			var prePages=document.getElementById("page").value;
			document.getElementById("page").value=parseInt(prePages)-1;
			goAction(url,ope);
		}
		
		function goAction(url,ope){
			document.getElementById("ope").value=ope;
			document.getElementById("mypageform").action=url;
			document.mypageform.submit();
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
          <td colspan="7"><span style="text-align:left; float:left;padding-left:4px; margin-left:4px; color:#141414;">
          <img src=images/index/icon.gif width="9" height="10" /> 当前位置：查看${param.orgname }的车载启停记录-&gt;查看历史数据</span>
          <a href="javascript:goAction('startUp.do','doStartUpPage');">
          <img src="images/index/back.gif" width="58" height="21" 
        class="pho"/></a></td>
        </tr>
      </table>
    </div>
    <div class="bottom">
    <form action="" method="post" id="mypageform" name="mypageform">
	
	<input type="hidden" name="suppagecount" id="suppagecount" value="${param.suppagecount }"/>
    <input type="hidden" name="suppage" id="suppage" value="${param.suppage }"/>
	<input type="hidden" name="checkedstartTime" id="checkedstartTime" value="${param.checkedstartTime }"/>
    <input type="hidden" name="checkedendTime" id="checkedendTime" value="${param.checkedendTime }"/>
	<input type="hidden" id="sid" name="sid" value="${param.sid }"/>
 	<input type="hidden" id="oid" name="oid" value="${param.oid }"/>
   	<input type="hidden" id="checkedrefId" name="checkedrefId" value="${param.checkedrefId }"/>
   	<input type="hidden" id="pNO" name="pNO" value="${param.pNO }" />
   	
   	<input type="hidden" name="orgname" id="orgname" value="${param.orgname }"/>
   	
    <input type="hidden" name="ope" id="ope" value=""/>
    <input type="hidden" name="carpagecount" id="carpagecount" value="${carpagecount }"/>
    <input type="hidden" name="page" id="page" value="${page }"/>
      <table id="tb" border="0" cellspacing="0" cellpadding="0">
        <tr bgcolor="#CCCCCC">
          <td height="28" colspan="7" bgcolor="#dff4fc"><table width="980" border="0" height="27">
              <tr>
                <td width="69" height="23" style="border:none;">车载名称：</td>
                <td width="102"  style="border:none;"><input name="carrefname" id="carrefname" type="text" value="${carrefname }" size="16" readonly="readonly" style="text-align: center;background-color: #f1f4f8;"/></td>
                <td width="70" style="border:none;">开始时间：</td>
                <td width="127"  style="border:none;"><input name="time1" id="time1" type="text" value="${param.time1 }" size="21" readonly="readonly" style="text-align: center;background-color: #f1f4f8;"/></td>
                <td width="65"  style="border:none;">结束时间：</td>
                <td width="141"  style="border:none;"><input name="time2" id="time2" type="text" value="${param.time2 }" size="21" readonly="readonly" style="text-align: center;background-color: #f1f4f8;"/></td>
                <td width="56" style="border:none;">承运人：</td>
                <td width="130" style="border:none;"><input name="carrier" id="carrier" type="text" value="${param.carrier }" size="16" readonly="readonly" style="text-align: center;background-color: #f1f4f8;"/></td>
                <td width="97" style="border:none;"><img src="images/index/sousuo.gif" width="45" height="20" style="display:none;"/></td>
              </tr>
            </table></td>
        </tr>
      </table>
      </form>
      <table id="tb" border="0" cellspacing="0" cellpadding="0">
        <tr class="altrow" >
          <td width="66">编号</td>
          <td width="220">时间</td>
          <td width="244">T1</td>
          <td width="178">T2</td>
          <td width="270">T3</td>
          <td width="270">报警状态</td>
        </tr>
        
        <logic:notEmpty name="hiscarList">
				<logic:iterate id="hiscar" name="hiscarList" indexId="irow">
				
					<tr ${irow%2==0?"bgcolor='#f1f4f8'":"" }>
						<td align="center"  nowrap="nowrap">${(page-1)*pagesize+irow+1 }</td>
						<td align="center"  nowrap="nowrap">
							<%-- <bean:write name="hiscar" property="updateTime" format="yyyy-MM-dd HH:mm:ss"/>--%>
							<bean:write name="hiscar" property="time" format="yyyy-MM-dd HH:mm:ss"/>
						</td>
						
						<td align="center" nowrap>	
							${hiscar.t1==-300?"--":hiscar.t1 }
						</td>
						
						<td align="center" nowrap="nowrap">
							${hiscar.t2==-300?"--":hiscar.t2 }
						</td>
						
						<td align="center"  nowrap="nowrap">
							${hiscar.t3==-300?"--":hiscar.t3 }
						</td>
						
						 <td align="center"  nowrap="nowrap">
						 <c:if test="${hiscar.isAlarm==0}">
						 	<img src="images/index/29.gif" title='表示处于报警状态' width="16" height="10"/>
						 </c:if>
						 <c:if test="${hiscar.isAlarm==1}">
						 	<img src="images/index/28.gif" title='表示处于正常状态' width="16" height="10"/>
						 </c:if>
						 </td>
					</tr>
				</logic:iterate>
					
					<c:if test="${page==carpagecount }">
					
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
       					<td>&nbsp;</td>
        				<td>&nbsp;</td>
        				<td>&nbsp;</td>
        				<td>&nbsp;</td>
					</tr>
					
					
					<tr bgcolor='#f1f4f8'>
						<td>&nbsp;</td>
						<td align="center"><font color="blue" size="4">统计</font></td>
       					<td align="center"><font color="blue" size="3">T1</font></td>
        				<td align="center"><font color="blue" size="3">T2</font></td>
        				<td align="center"><font color="blue" size="3">T3</font></td>
        				<td align="center">&nbsp;</td>
					</tr>
					
					
					<logic:iterate id="MMA" name="carlistMMA" indexId="outrow" length="3">
					<tr ${outrow%2==1?"bgcolor='#f1f4f8'":"" }>
						<td>&nbsp;</td>
						<td align="center"  nowrap="nowrap"><font color="blue" size="2">
						<c:if test="${outrow==0 }">最大值</c:if>
						<c:if test="${outrow==1 }">最小值</c:if>
						<c:if test="${outrow==2 }">平均值</c:if>
						</font></td>
						<logic:iterate id="m" name="MMA" indexId="inrow">
       					<td align="center"  nowrap="nowrap">${m==-300?"--":m }</td>
       	 				</logic:iterate>
       	 				<td align="center">&nbsp;</td>
					</tr>
					 </logic:iterate>
					</c:if>
        <tr>
          <td colspan="6"><table width="100%" border="0" cellpadding="0" cellspacing="0" >
            <tr >
              <td width="94" height="38" style="border: none;">&nbsp;</td>
              <td width="94" style="border: none;">&nbsp;</td>
              <td width="94" style="border: none;">&nbsp;</td>
              <td width="100" style="border: none;">
              	<c:if test="${page==1||page==null }">
    				<input type="button" value="首页" / class="inp_aaa"/>
    			</c:if>
    			<c:if test="${page!=1&&page!=null }">
              		<input type="button" value="首页" / class="inp_aa" onclick="javascript:firstPage('carHis.do','doCarHisPage');"/>
              	</c:if>
              </td>
              <td width="100" style="border: none;">
              	<c:if test="${page==1||page==null }">
    				<input type="button" value="上一页" / class="inp_bbb" />
    			</c:if>
    			<c:if test="${page!=1&&page!=null }">
              		<input type="button" value="上一页" / class="inp_bb" onclick="javascript:prePage('carHis.do','doCarHisPage');"/>
              	</c:if>
              </td>
              <td width="100" style="border: none;">
              	<c:if test="${page==carpagecount }">
    				<input type="button" value="下一页" / class="inp_ccc" />
    			</c:if>
    			<c:if test="${page!=carpagecount }">
              		<input type="button" value="下一页" / class="inp_cc" onclick="javascript:nextPage('carHis.do','doCarHisPage');"/>
              	</c:if>
              </td>
              <td width="100" style="border: none;">
              	<c:if test="${page==carpagecount }">
    				<input type="button" value="尾页" / class="inp_ddd" />
    			</c:if>
    			<c:if test="${page!=carpagecount }">
              		<input type="button" value="尾页" / class="inp_dd" onclick="javascript:lastPage('carHis.do','doCarHisPage');"/>
              	</c:if>
              </td>
              <td width="94" style="border: none;">&nbsp;</td>
              <td width="94" style="border: none;">当前第${page }页</td>
              <td width="96" style="border: none;">共${carpagecount }页</td>
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
