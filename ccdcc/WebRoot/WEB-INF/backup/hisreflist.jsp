<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="common/taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>


		<title>冷库历史数据查询</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link rel="Shortcut Icon" href="img/add/logo.ico">
		<link href="css/style.css" rel="stylesheet" type="text/css">
		<LINK href="css/mainWin.css" type=text/css media=screen rel=stylesheet>
		<LINK href="css/mainWin2.css" type=text/css media=screen
			rel=stylesheet>
		<LINK href="css/desktop.css" type=text/css media=screen rel=stylesheet>

		<script src="script/common.js"></script>
		<script src="DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="script/titleTime.js"></script>
		<script type="text/javascript" src="script/queryTime.js"></script>
		<script type="text/javascript" src="script/rExcel.js"></script>
		
		<script type='text/javascript' src='dwr/interface/real.js'></script>
  		<script type='text/javascript' src='dwr/engine.js'></script>
 		<script type='text/javascript' src='dwr/util.js'></script>

		
		<script type="text/javascript">
			
			/**
			*	获取某个工程下冷库列表
			*/
			function getRefList(obj){
				var pid = obj.value ;
				real.getRefListById(pid,{
					callback:doRefList ,
					errorHandler:function(e){window.alert("获取冷库列表失败"+e)},
					timeout:10000
				});
			}
			
			/**
			*	处理冷库集合、填充到下拉列表框
			*/
			
			function doRefList(data){
				
				if(data!=null){
					DWRUtil.removeAllOptions("myrid");
					DWRUtil.addOptions("myrid",data,"id","refName");
				}
				
			}
			
			/**
			*	页面初始化
			*/
			function init(){
				
			}
			
		</script>
	</head>

	<body onload="showtime(),init();">
		<jsp:include page="header.jsp" flush="true"></jsp:include>
		<table width="990" border="0" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#CCCCCC" bordercolor="#FFFFFF"
			bordercolordark="#FFFFFF">

			<td width="990" height="550" colspan="3" valign="top"
				background="img/stock_index_08.gif">
				<div id="main" style="width: 990px; height: 100%; float: left">
					<div class="page_title">
						您正在做的业务是：
						<img src="img/add/club.JPG" alt=">>">
						<font size="2px">&nbsp;冷库历史数据查看</font>
					</div>
					<form name="myform" action="hisref.do?ope=doHisRefData"
						method="post">
						<div class="button_bar">
							<input type="hidden" id="branchId"  name="branchId" value="${param.branchId }">
							<input type="button" class="common_button" onClick="queryData();"
								value="查询" />
							&nbsp;
							<input type="button" class="common_button" ${empty
								refDataList?
								"disabled='disabled'
								":"" } 
								 name="out_word1"
								onclick="printData(time1.value,time2.value)" value="打印" />
							&nbsp;
							<input type="button" class="common_button"
								onClick="javascript:window.location.href='branch.do?ope=toListByBranch&branchId=${param.branchId }'"
								value="返回主页面" />


						</div>
						<table class="query_form_table">

							<tr>
								<th width="120">
									选择仓库:
								</th>
								<td>
									<select name="myrefprojlist" id="myrefprojlist" style="width: 100px" onchange="getRefList(this);">
									<c:forEach items="${refPrjList}" var="proj">
										<option value="${proj.projectId }" 
										${proj.projectId eq param.myrefprojlist?"selected='selected'":" " } >
											${proj.projectName }
										</option>
									</c:forEach>
									</select>
								</td>
								<th>
									选择冷库:
								</th>
								<td>
									<select name="myrid" id="myrid">
										<c:forEach items="${refList}" var="refInfo">
											<option value="${refInfo.id }" ${refInfo.id eq
												param.myrid?"selected='selected'":" " }>
												${refInfo.refName}
											</option>
										</c:forEach>
									</select>
								</td>

								<th>
									起始时间:
								</th>
								<td colspan="3">
									<input name="time1" id="d4311" value="${param.time1}"
										class="Wdate" type="text"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})" />
								</td>
								<th>
									终止时间:
								</th>
								<td colspan="3">
									<input name="time2" id="d4312" value="${param.time2}"
										class="Wdate" type="text"
										onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',minDate:'#F{$dp.$D(\'d4311\')}'})" />
								</td>
								<th >
									间隔:
								</th>
								<td>
									<input id="timevalue"  name="timevalue" type="text" size="4"
										value="${param.timevalue==null?"30":param.timevalue}" onkeypress="return isNumber(event)" />
									<select id="timeType" name="timeType" onchange="changeTimeType(this)">
										<option value="3" ${param.timeType==3? "selected=selected":" " }>
											秒
										</option>
										<option value="2" ${param.timeType==2? "selected=selected":" " }>
											分钟
										</option>
										<option value="1" ${param.timeType==1? "selected=selected":" " }>
											小时
										</option>
									</select>
								</td>
							</tr>
						</table>
					</form>

					<TABLE BORDER=0 CELLPADDING=0 CELLSPACING=0 WIDTH="990"
						align="center" id="data" name="data">
						<TR HEIGHT=23 CLASS=Page_tools_bar>
							<TD CLASS=Page_title align="center">
								时间
							</TD>
							<c:forEach var="aiinfo" items="${aiInfoList}">
								<TD CLASS=Page_title align="center">
									${aiinfo.portName }
								</TD>
							</c:forEach>

							<logic:empty name="aiInfoList">
								<td CLASS=Page_title align="center">
									探头
								</td>
							</logic:empty>

							<TD CLASS=Page_title align="center">
								最大温度
							</TD>
							<TD CLASS=Page_title align="center">
								最小温度
							</TD>
							<TD CLASS=Page_title align="center">
								平均温度
							</TD>
							<TD CLASS=Page_title align="center">
								最大湿度
							</TD>
							<TD CLASS=Page_title align="center">
								最小湿度
							</TD>
							<TD CLASS=Page_title align="center">
								平均湿度
							</TD>
							<TD CLASS=Page_title align="center">
								报警状态
							</TD>
						</TR>


						<logic:notEmpty name="refDataList">
							<logic:iterate id="hisdata" name="refDataList">
								<tr align="center">
									<%
										String[] aa = (String[])null ;
											for (int i = 0; i < aa.length; i++) {
												out.println("<td>" + aa[i] + "</td>");
											}
									%>
								</tr>
							</logic:iterate>
							<tr>
								<td colspan="20">
									<hr width="100%" color="pink">
								</td>
							</tr>

							<tr align="center">
								<td align="center">
									<font size="4" color="blue">统计</font>
								</td>
								<c:forEach var="aiinfo" items="${aiInfoList}">
									<TD align="center">
										<font color="blue" size="3">${aiinfo.portName }</font>
									</TD>
								</c:forEach>
							</tr>

							<tr align="center">
								<td align="center" style="color: blue; size: 3">
									最大值
								</td>

								<c:forEach var="maxdata" items="${max}">
									<td align="center">
										${maxdata==-300?"--":maxdata }
									</td>
								</c:forEach>
							</tr>

							<tr align="center">
								<td align="center" style="color: blue; size: 3">
									最小值
								</td>
								<c:forEach var="mindata" items="${min}">
									<td align="center">
										${mindata==-300?"--":mindata}
									</td>
								</c:forEach>
							</tr>

							<tr align="center">
								<td align="center" style="color: blue; size: 3">
									平均值
								</td>
								<c:forEach var="avgdata" items="${avg}">
									<td align="center">
										${avgdata==-300?"--":avgdata }
									</td>
								</c:forEach>
							</tr>

							<%--统计信息 --%>
							<bean:size id="aiLength" name="aiInfoList" />
							<input type="hidden" id="aiSize" name="aiSize" value="${aiLength }" />
						</logic:notEmpty>
						
						<logic:empty name="refDataList">
							<tr>
								<td colspan="20" align="center">
									<div style="color: red;">
										${flag!=null?"":"选择的时间范围内没有数据"}
									</div>
								</td>
							</tr>
						</logic:empty>
					</TABLE>

				</div>
			</td>
		</table>

	</body>
	<jsp:include page="footer.jsp" flush="true"></jsp:include>
</html>
