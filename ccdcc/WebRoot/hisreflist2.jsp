<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>�鿴�ֿ���ʷ���ݲ鿴</title>
<link rel="Shortcut Icon" href="img/add/logo.ico" />
<link href="css/cangku/hisref_tc.css" rel="stylesheet" type="text/css" />
<link href="css/cangku/input1.css" rel="stylesheet" type="text/css" />
<script src="DatePicker/WdatePicker.js"></script>

		<script type="text/javascript" src="script/queryTime.js"></script>
		<script type="text/javascript" src="script/rExcel.js"></script>
		
		<script src="script/common.js"></script>

<script type='text/javascript' src='dwr/interface/real.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>


<script type="text/javascript">
			/**
			*	��תҳ��
			**/
			function golist(url,operate){
				document.getElementById('myform').action = url  ;
				document.getElementById('ope').value = operate ;
				document.myform.submit() ;
			}
	
	
			/**
			*	��ȡĳ������������б�
			*/
			function getRefList(obj){
				var pid = obj.value ;
				real.getRefListById(pid,{
					callback:doRefList ,
					errorHandler:function(e){window.alert("��ȡ����б�ʧ��"+e)},
					timeout:10000
				});
			}
			
			/**
			*	������⼯�ϡ���䵽�����б��
			*/
			
			function doRefList(data){			
				if(data!=null){
					DWRUtil.removeAllOptions("myrid");
					DWRUtil.addOptions("myrid",data,"id","refName");
				}
			}
			
			/**
			*	click query 
			**/
			
			function doquery(url,operate){
				document.getElementById('myform').action = url ;
				document.getElementById('ope').value = operate ;
				//ԭ��ʵ�ֵķ���
				queryData();
			}
</script>
</head>
<body>
<div>
  <iframe scrolling="no" src="common/header2.jsp" width=100% height=126 frameborder=0></iframe >
</div>
<form action="" name="myform" id="myform" method="post">
	<input type="hidden" name="branchId" id="branchId" value="${param.branchId }" />
	<input type="hidden" name="ope" id="ope" value=""/>
<div id="right">
  <div id="top"><a href="#">&nbsp; 
  </a><a href="#"><img width="16" height="15" src="images/canku/icon_c.gif" class="tb4" /> 
  <strong>λ��:��ҳ</strong>&lt;�����ڲ鿴�����ʷ���ݲ鿴</a>
  <img src="images/canku/back.gif" width="48" height="20" class="tb3"/>
  <img src="img/util/back.gif" width="48" height="20"  class="tb3" style="cursor: pointer" onclick="javascript:golist('pro.do','toRefList');"/>
  <img src="images/canku/chaxun.gif" width="58" height="21"  class="tb5" style="cursor: pointer" onclick="doquery('hisref.do','doHisRefData');"/>
  <img src="images/canku/da_confirm.gif" width="58" height="21"  class="tb6" style="cursor: pointer;display:${empty refDataList?"none":"inline" } "  onclick="printData(time1.value,time2.value)"/>
  </div>
  <div id="center"></div>
  <div id="center">
    <table id="bd3">
      <tr>
        <td width="7%"><h3 style="text-align:left; font-size:12px;padding-left:0px; padding-right:0px; width:70px; color:#454343;">ѡ��ֿ⣺</h3></td>
        <td width="12%">
        	<select name="myrefprojlist" id="myrefprojlist" style="width: 100px" onchange="getRefList(this);">
									<c:forEach items="${refPrjList}" var="proj">
										<option value="${proj.projectId }" 
										${proj.projectId eq param.myrefprojlist?"selected='selected'":" " } >
											${proj.projectName }
										</option>
									</c:forEach>
			</select>
         </td>
        <td width="7%"><h3 style="text-align:right; font-size:12px;padding-left:0px; padding-right:0px; width:70px; color:#454343;">ѡ����⣺</h3></td>
        <td width="12%">
        	<select name="myrid" id="myrid">
										<c:forEach items="${refList}" var="refInfo">
											<option value="${refInfo.id }" ${refInfo.id eq param.myrid?"selected='selected'":" " }>
												${refInfo.refName}
											</option>
										</c:forEach>
			</select>	
        </td>
        <td width="7%"><h3 style="text-align:right; font-size:12px;padding-left:0px; padding-right:0px; width:70px; color:#454343;">��ʼʱ�䣺</h3></td>
        <td width="14%">
       				<input name="time1" id="d4311" value="${param.time1}"
										class="Wdate" type="text"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})" />

        </td>
        <td width="7%"><h3 style="text-align:right; font-size:12px;padding-left:0px; padding-right:0px; width:70px; color:#454343;">��ֹʱ�䣺</h3></td>
        <td width="14%">
        			<input name="time2" id="d4312" value="${param.time2}"
										class="Wdate" type="text"
										onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',minDate:'#F{$dp.$D(\'d4311\')}'})" />

          </td>
        <td width="5%"><h3 style="text-align:right; font-size:12px;padding-left:0px; padding-right:0px; width:40px; color:#454343;">�����</h3></td>
        <td width="4%"><span style="float:left;">
          <input id="timevalue"  name="timevalue" type="text" size="4"
										value="${param.timevalue==null?"30":param.timevalue}" onkeypress="return isNumber(event)" />
          </span></td>
        <td width="10%"><span style="float:left;">
         <select id="timeType" style="width: 50px"  name="timeType" onchange="changeTimeType(this)">
										<option value="3" ${param.timeType==3? "selected=selected":" " }>
											��
										</option>
										<option value="2" ${param.timeType==2? "selected=selected":" " }>
											����
										</option>
										<option value="1" ${param.timeType==1? "selected=selected":" " }>
											Сʱ
										</option>
		</select>
          </span></td>
      </tr>
    </table>
  </div>
  <div id="bottom">
    <table id="tb" width="100%" border="0" cellspacing="0" cellpadding="0" >
      <tr id="tb1">
        <td width="120">ʱ��</td>
        <c:forEach var="aiinfo" items="${aiInfoList}">
					<td width="60">
									${aiinfo.portName }
					</td>
		</c:forEach>

		<logic:empty name="aiInfoList">
					<td width="250">
									̽ͷ
					</td>
		</logic:empty>
        <td width="80">��С�¶� </td>
        <td width="80">����¶�</td>
        <td width="80">ƽ���¶� </td>
        <td width="80">ƽ��ʪ�� </td>
        <td width="80">��Сʪ�� </td>
        <td width="80">���ʪ�� </td>
        <td width="65">����״̬</td>
      </tr>
      
      <%-- ���̽ͷ��ʵʱ���ݣ��Լ�ͳ����Ϣ --%>
      <logic:notEmpty name="refDataList">
							<logic:iterate id="hisdata" name="refDataList">
								<tr align="center">
									<%
											String[] aa = (String[]) hisdata;										
											for (int i = 0; i < aa.length; i++) {
												out.println("<td>" + aa[i] + "</td>");
											}
									%>
								</tr>
	 </logic:iterate>
							 <tr>
								<td colspan="20">
									<hr width="100%" color="#F4F4F4"/>
								</td>
							</tr>

							<tr align="center">
								<td align="center">
									<font size="4" color="blue">ͳ��</font>
								</td>
								<c:forEach var="aiinfo" items="${aiInfoList}">
									<TD align="center">
										<font color="blue" size="3">${aiinfo.portName }</font>
									</TD>
								</c:forEach>
							</tr>

							<tr align="center">
								<td align="center" style="color: blue; size: 3">
									���ֵ
								</td>

								<c:forEach var="maxdata" items="${max}">
									<td align="center">
										${maxdata==-300?"--":maxdata }
									</td>
								</c:forEach>
							</tr>

							<tr align="center">
								<td align="center" style="color: blue; size: 3">
									��Сֵ
								</td>
								<c:forEach var="mindata" items="${min}">
									<td align="center">
										${mindata==-300?"--":mindata}
									</td>
								</c:forEach>
							</tr>

							<tr align="center">
								<td align="center" style="color: blue; size: 3">
									ƽ��ֵ
								</td>
								<c:forEach var="avgdata" items="${avg}">
									<td align="center">
										${avgdata==-300?"--":avgdata }
									</td>
								</c:forEach>
							</tr>

							<%--ͳ��̽ͷ�ĸ��� --%>
							<bean:size id="aiLength" name="aiInfoList" />
							<input type="hidden" id="aiSize" name="aiSize" value="${aiLength }" />
						</logic:notEmpty>
						
				
      
      <%-- ��ǰ��Χû�����ݡ��Ҳ��ǵ�һ�ν����ҳ�� --%>
      <logic:empty name="refDataList">
							<tr>
								<td colspan="20" align="center">
									<div style="color: red;">
										${flag!=null?"":"ѡ���ʱ�䷶Χ��û������"}
									</div>
								</td>
							</tr>
	</logic:empty>
    </table>
    
    <logic:notEmpty name="refDataList">
    	<bean:size id="refDataLength" name="refDataList" />
    	<c:if test="${refDataLength<10}">
    		<div style="height: 400px">&nbsp;</div>
    	</c:if> 	
    </logic:notEmpty>
    
		<logic:empty name="refDataList">
			<div style="height: 400px">&nbsp;</div>
		</logic:empty>		
  </div>
</div>
<div class="clear"></div>
</form>
<div>
  <iframe scrolling="no" src="common/footer2.jsp" width=100% height=43 frameborder=0></iframe >
</div>
</body>
</html>
 