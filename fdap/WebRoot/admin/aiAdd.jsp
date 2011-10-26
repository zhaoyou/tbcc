<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>冷库探头操作</title>
<link href="../css/admin/index/aiAdd.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="../images/logo.ico" />
<script type="text/javascript" src="../js/jquery-1.4.min.js"></script>
<style type="text/css">
/* common styling */
/* set up the overall width of the menu div, the font and the margins */
.menu {
	font-family: "宋体";
	width: 1024px;
	font-size: 12px;
	margin: 0 auto;
	background-image: url(../images/admin/head/bg_head03.jpg);
	background-repeat: repeat-x;
	height: 30px;
	padding: 0px;
	font-weight: normal;
	position: relative;
}
/* remove the bullets and set the margin and padding to zero for the unordered list */
.menu ul {
	padding: 0;
	margin: 0;
	list-style-type: none;
}
/* float the list so that the items are in a line and their position relative so that the drop down list will appear in the right place underneath each list item */
.menu ul li {
	float:left;
	position:relative;
}
/* style the links to be 104px wide by 30px high with a top and right border 1px solid white. Set the background color and the font size. */
.menu ul li a, .menu ul li a:visited {
	display:block;
	text-align:center;
	text-decoration:none;
	width:125px;
	height:28px;
	color:#2B2B2B;
	border:1px solid #fff;
	border-width:1px 1px 0 0;
	line-height:28px;
	font-size:12px;
	font-family: "宋体";
}
.menu ul li a {
	background-image: url(../images/admin/head/bg_head03.jpg);
	background-repeat: repeat-x;
	height: 28px;
}
.menu ul li a:visited {
	display:block;
	color:#000;
	font-weight: normal;
}
/* make the dropdown ul invisible */
.menu ul li ul {
	display: none;
	font-weight:bold;
}
/* specific to non IE browsers */
/* set the background and foreground color of the main menu li on hover */
.menu ul li:hover a {
	color:#000;
	background-image: url(../images/admin/head/bg_head02.jpg);
	background-repeat: repeat-x;
	font-weight: bold;
}
/* make the sub menu ul visible and position it beneath the main menu list item */
.menu ul li:hover ul {
	display:block;
	position:absolute;
	top:29px;
	left:0;
	width:105px;
}
/* style the background and foreground color of the submenu links */
.menu ul li:hover ul li a {
	display:block;
	color:#000;
	background: #E6E6E6;
	font-weight:normal;
	font-family:"宋体";
}
/* style the background and forground colors of the links on hover */
.menu ul li:hover ul li a:hover {
	color:#000;
	background: #029AFF;
	font-weight:normal;
}
</style>
<!--[if lte IE 6]>
<style type="text/css">
/* styling specific to Internet Explorer IE5.5 and IE6. Yet to see if IE7 handles li:hover */
/* Get rid of any default table style */
table {
border-collapse:collapse;
margin:0; 
padding:0;
}
/* ignore the link used by 'other browsers' */
.menu ul li a.hide, .menu ul li a:visited.hide {
display:none;
}
/* set the background and foreground color of the main menu link on hover */
.menu ul li a:hover {
color:#000;
	background-image: url(../images/admin/head/bg_head02.jpg);
	background-repeat: repeat-x;
	font-weight: bold;
}
/* make the sub menu ul visible and position it beneath the main menu list item */
.menu ul li a:hover ul {
display:block; 
position:absolute; 
top:29px; 
left:0; 
width:150px;
}
/* style the background and foreground color of the submenu links */
.menu ul li a:hover ul li a {
background:#E6E6E6;
color:#000;
font-weight:normal;
}
/* style the background and forground colors of the links on hover */
.menu ul li a:hover ul li a:hover {
background:#09f; 
color:#000;
font-weight:normal;
font-family:"宋体";
}
</style>
<![endif]-->


<script type="text/javascript">
		//var flag1 = false;
		//var p1 = /[^\-\d\.]/g;
		//var p2 = /(\.\d{2})\d*$/g;
		//var p3 = /(\d{3})\1*/g;
		//var p4 = /(\.)(\d*)\1/g;
		//var p5 = /[^\d]/g;
		function checkdigit(o){
			if(o.value=="")return;
			var p = /^[0-9]*[1-9][0-9]*$/;
			if(!p.test(o.value)||o.value<0||o.value>60){
				o.style.borderColor="red";
				$('#tip').html( "<font color='red'>延时必须为0与60之间的正整数!</font>" ) ;
				//flag1 = true;
			}
			else{
				o.style.borderColor="gray";
				$('#tip').html("&nbsp;") ;
				//flag1 = false;
			}
			//o.value = o.value.replace(p5,"");
		}
		function check(o) {
			if(o.value=="")return;
			var p = /^(-?\d+)(\.\d+)?$/;
			var selftype = $('#selftype').val();
			
			if(selftype==0&&(o.value<-30||o.value>50||!p.test(o.value))){
				o.style.borderColor="red";
				$('#tip').html( "<font color='red'>温度上下限必须为-30与50之间的实数!</font>" ) ;
				//flag1 = true;
			}else if(selftype==1&&(o.value<0||o.value>100||!p.test(o.value))){
				o.style.borderColor="red";
				$('#tip').html( "<font color='red'>湿度上下限必须为0与100之间的实数!</font>" ) ;
			}
			else{
				o.style.borderColor="gray";
				$('#tip').html("&nbsp;") ;
				//flag1 = false;
			}
		   // o.value = o.value.replace(p1,"").replace(p2,"$1").replace(p4,"$1$2");
		}
		
		function goaction(url){
			$("#myform").action=url;
			
			$("#myform")[0].submit();
		}
		
		function gocancel(url,ope){
			$("#ope").val(ope);
			goaction(url);
		}
		
		/*function goAdd(url){
			var d = /^[0-9]*[1-9][0-9]*$/;
			var f = /^(-?\d+)(\.\d+)?$/;
			var name = document.getElementById('name').value;
			var lowerlimit = document.getElementById("lowerlimit").value;
			var lowerdelay = document.getElementById("lowerdelay").value;
			//var minlowerlimit = document.getElementById("minlowerlimit").value;
			//var minlowerdelay= document.getElementById("minlowerdelay").value;
			var highlimit = document.getElementById("highlimit").value;
			var highdelay = document.getElementById("highdelay").value;
			//var maxhighlimit = document.getElementById("maxhighlimit").value;
			//var maxhighdelay = document.getElementById("maxhighdelay").value;
			
			//alert(name.length);
			if(name==null||name==""||lowerlimit==null||lowerlimit==""||lowerdelay==null||lowerdelay==""
			||highlimit==null||highlimit==""||highdelay==null||highdelay==""){
				document.getElementById("tip").innerHTML="<font color='red'>请确保信息完整</font>";
				return;
			}
			
			if(name.length>25){
				document.getElementById("tip").innerHTML="<font color='red'>探头名称最长为25个字符</font>";
				return;
			}
			
			var typeobj = document.getElementById("selftype");
			var selftype = typeobj.options[typeobj.selectedIndex].value;
			if(selftype==0){
				//if(minlowerlimit==null||minlowerlimit==""||minlowerdelay==null||minlowerdelay==""
				//||maxhighlimit==null||maxhighlimit==""||maxhighdelay==null||maxhighdelay==""){
				//	document.getElementById("tip").innerHTML="<font color='red'>请确保信息完整</font>";
				//	return;
				//}
				//if(!(d.test(minlowerdelay)&&d.test(maxhighdelay)&&f.test(minlowerlimit)&&f.test(maxhighlimit)&&d.test(lowerdelay)&&d.test(highdelay)&&f.test(lowerlimit)&&f.test(highlimit)
				//&&minlowerdelay>0&&minlowerdelay<60&&maxhighdelay>0&&maxhighdelay<60&&lowerdelay>0&&lowerdelay<60&&highdelay>0&&highdelay<60&&lowerlimit>-30&&lowerlimit<50
				//&&highlimit>-30&&highlimit<50&&minlowerlimit>-30&&minlowerlimit<50&&maxhighlimit>-30&&maxhighlimit<50)){
					//document.getElementById("tip").innerHTML="<font color='red'>请确保温度上下限范围在-30到50之间，延时上下限范围在0到60之间</font>";
					//return;
				//}
				if(!(d.test(lowerdelay)&&d.test(highdelay)&&f.test(lowerlimit)&&f.test(highlimit)
				&&lowerdelay>0&&lowerdelay<60&&highdelay>0&&highdelay<60&&lowerlimit>-30&&lowerlimit<50
				&&highlimit>-30&&highlimit<50)){
					document.getElementById("tip").innerHTML="<font color='red'>请确保温度上下限范围在-30到50之间，延时上下限范围在0到60之间</font>";
					return;
				}
			}
			else{
				if(!(d.test(lowerdelay)&&d.test(highdelay)&&f.test(lowerlimit)&&f.test(highlimit)&&lowerdelay>0&&lowerdelay<60&&highdelay>0&&highdelay<60&&lowerlimit>0&&lowerlimit<100&&highlimit>0&&highlimit<100)){
					document.getElementById("tip").innerHTML="<font color='red'>请确保湿度上下限范围在0到100之间，延时上下限范围在0到60之间</font>";
					return;
				}
			}
			document.getElementById("tip").innerHTML="&nbsp;";
			//数据验证是否通过
			//if(flag1){
				//return;
			//}
			goaction(url);
		}*/
		
		//增加或修改探头时用到的方法
		function goAdd(url){
			var flag = false;
			var d = /^[0-9]*[1-9][0-9]*$/;
			$('form input:text').each(function(){
				if($(this).attr("name")!="reid"){
					if($(this).val()==""){
						flag = true;
						$(this).css('borderColor','red');
						$('#'+this.name+'Tip').css('color','red');
					}else{
						$(this).css('borderColor','gray');
						$('#'+this.name+'Tip').css('color','black');
					}
				}
			});
			//验证是否有必须输入项为空
			if(flag){
				$('#tip').html( "请确保信息完整!" ) ;
				return ;
			}
			
			if($('#name').val().length>25){
				$('#tip').html( "探头名称最长为25个字符" ) ;
				return;
			}
			
			var selftype = $('#selftype').val();
			var flag1 = false;
			$('form input:text').each(function(){
				if($(this).attr("name")!="name"){
						if($(this).attr("name")!="reid"&&!isFinite($(this).val()))   {
								flag1 = true;
								$(this).focus();
								$(this).css('borderColor','red');
								$('#tip').html("<font color='red'>请确保输入信息为数字!</font>");
								return false;
							}
							var leng = $(this).attr("name").length;
							if($(this).attr("name")=="reid"){
								if($('#reidSelect').val()!='1'){
									if($(this).val()==""){
											flag1 = true;
											$(this).css('borderColor','red');
											$(this).focus();
											$('#tip').html( "<font color='red'>探头编号不能为空!</font>" ) ;
											return false;
									}else if(!d.test($(this).val())){
											flag1 = true;
											$(this).css('borderColor','red');
											$(this).focus();
											$('#tip').html( "<font color='red'>探头编号必须为正整数!</font>" ) ;
											return false;
									}else if($(this).val()>32767){
										flag1 = true;
										$(this).css('borderColor','red');
										$(this).focus();
										$('#tip').html( "<font color='red'>探头编号不能大于32767!</font>" );
									}
								}
							}else if($(this).attr("name").substring(leng-5,leng)=="delay"){
								if(!d.test($(this).val())||$(this).val()<0||$(this).val()>60){
									flag1 = true;
									$(this).css('borderColor','red');
									$(this).focus();
									$('#tip').html( "<font color='red'>延时必须为0与60之间的正整数!</font>" ) ;
									return false;
								}
							}else if(selftype==1){
									if($(this).val()<0||$(this).val()>100){
										flag1 = true;
										$(this).css('borderColor','red');
										$(this).focus();
										$('#tip').html( "<font color='red'>湿度上下限必须为0与100之间的实数!</font>" ) ;
										return false;
									}
								}else if($(this).val()<-30||$(this).val()>50){
									flag1 = true;
									$(this).css('borderColor','red');
									$(this).focus();
									$('#tip').html( "<font color='red'>温度上下限必须为-30与50之间的实数!</font>" ) ;
									return false;
								}
				}
			});
			if(flag1){return;}
			if(parseFloat($('#lowerlimit').val())>=parseFloat($('#highlimit').val())){
				$('#lowerlimit').css('borderColor','red');
				$('#highlimit').css('borderColor','red');
				$('#tip').html( "<font color='red'>上限不能小于下限！</font>" ) ;
				return;
			}
			goaction(url);
		}
		
		
		function changed(obj){
			//var selftype=obj.options[obj.selectedIndex].value;
			var selftype = $('#selftype').val();
			//document.getElementById("tip").innerHTML="&nbsp;";
			$('#tip').html("&nbsp;") ;
			
			//var storetypeStr = document.getElementById("storetypeStr").value;
			var storetypeStr = $('#storetypeStr').val();
			var sstr = storetypeStr.split(",");
			if(selftype==0){
				$("#lowerlimit").val(sstr[0]);
				$("#lowerdelay").val(sstr[1]);
				$("#highlimit").val(sstr[2]);
				$("#highdelay").val(sstr[3]);
				$("#llimittip").html("℃");
				$("#hlimittip").html("℃");
				/*document.getElementById("lowerlimit").value=sstr[0];
				document.getElementById("lowerdelay").value=sstr[1];
				document.getElementById("highlimit").value=sstr[2];
				document.getElementById("highdelay").value=sstr[3];*/
				/*document.getElementById("lowerlimit").value=sstr[0];
				document.getElementById("lowerdelay").value=sstr[1];
				document.getElementById("minlowerlimit").disabled=false;
				document.getElementById("minlowerlimit").style.backgroundColor="#ffffff";
				document.getElementById("minlowerlimit").value=sstr[2];
				document.getElementById("minlowerdelay").disabled=false;
				document.getElementById("minlowerdelay").style.backgroundColor="#ffffff";
				document.getElementById("minlowerdelay").value=sstr[3];
				document.getElementById("highlimit").value=sstr[4];
				document.getElementById("highdelay").value=sstr[5];
				document.getElementById("maxhighlimit").disabled=false;
				document.getElementById("maxhighlimit").style.backgroundColor="#ffffff";
				document.getElementById("maxhighlimit").value=sstr[6];
				document.getElementById("maxhighdelay").disabled=false;
				document.getElementById("maxhighdelay").style.backgroundColor="#ffffff";
				document.getElementById("maxhighdelay").value=sstr[7];
				document.getElementById("llimittip").innerHTML="℃";
				document.getElementById("hlimittip").innerHTML="℃";*/
			}
			else{
				$("#lowerlimit").val(sstr[4]);
				$("#lowerdelay").val(sstr[5]);
				$("#highlimit").val(sstr[6]);
				$("#highdelay").val(sstr[7]);
				$("#llimittip").html("％");
				$("#hlimittip").html("％");
				/*document.getElementById("lowerlimit").value=sstr[4];
				document.getElementById("lowerdelay").value=sstr[5];
				document.getElementById("highlimit").value=sstr[6];
				document.getElementById("highdelay").value=sstr[7];
				document.getElementById("lowerlimit").value=sstr[8];
				document.getElementById("lowerdelay").value=sstr[9];
				document.getElementById("minlowerlimit").value="";
				document.getElementById("minlowerlimit").style.backgroundColor="#f1f4f8";
				document.getElementById("minlowerlimit").disabled=true;
				document.getElementById("minlowerdelay").value="";
				document.getElementById("minlowerdelay").style.backgroundColor="#f1f4f8";
				document.getElementById("minlowerdelay").disabled=true;
				document.getElementById("highlimit").value=sstr[10];
				document.getElementById("highdelay").value=sstr[11];
				document.getElementById("maxhighlimit").value="";
				document.getElementById("maxhighlimit").style.backgroundColor="#f1f4f8";
				document.getElementById("maxhighlimit").disabled=true;
				document.getElementById("maxhighdelay").value="";
				document.getElementById("maxhighdelay").style.backgroundColor="#f1f4f8";
				document.getElementById("maxhighdelay").disabled=true;
				document.getElementById("llimittip").innerHTML="％";
				document.getElementById("hlimittip").innerHTML="％";*/
			}
		}

		function checkReid(o){
			if(o.value=="")return;
			var p = /^[0-9]*[1-9][0-9]*$/;
			if(!p.test(o.value)){
				o.style.borderColor="red";
				$('#tip').html( "<font color='red'>探头编号必须为正整数!</font>" ) ;
				//flag1 = true;
			}
			else{
				o.style.borderColor="gray";
				$('#tip').html("&nbsp;") ;
				//flag1 = false;
			}
			//o.value = o.value.replace(p5,"");
		}


		function selectReid(){
			if($('#reidSelect').val()==1){
				$('#reid').val("");
				$('#reid').css('background-color','#f1f4f8');
				$('#reid').attr("readonly",true);
			}else if($('#reidSelect').val()==2){
				$('#reid').css('background-color','#ffffff');
				$('#reid').attr("readonly",false);
			}else{
				$('#reid').val($('#preid').val());
				$('#reid').css('background-color','#f1f4f8');
				$('#reid').attr("readonly",true);
			}
		}

</script>

</head>
<body>
<div style="margin:0 auto; width:1024px;">
  <iframe scrolling="no" src="head.jsp" width=100% height=99 frameborder=0></iframe >
</div>
<div class="menu">
  <ul>
    <li><a class="hide" href="#"><strong>机构配置</strong></a>
      <!--[if lte IE 6]>
<a href="#"><strong>机构配置</strong>
<table><tr><td>
<![endif]-->
      <ul>
        <li><a href="orgconfig.do?ope=toTopOrg" >顶层配置</a></li>
        <li><a href="orgconfig.do?ope=toOrgList" >逐级配置</a></li>
        <%-- <li><a href="defaultdisplay.do?ope=toDefault" >机构显示方式</a></li>--%>
      </ul>
      <!--[if lte IE 6]>
</td></tr></table>
</a>
<![endif]-->
    </li>
    <li><a class="hide" href="#"><strong>工程配置</strong></a>
      <!--[if lte IE 6]>
<a href="#"><strong>工程配置</strong>
<table><tr><td>
<![endif]-->
      <ul>
        <li><a href="storeconfig.do?ope=toStoreList" >仓储类型配置</a></li>
        <li><a href="projectconfig.do?ope=toList" >企业项目配置</a></li>
        <li><a href="configinfo.do?ope=toGetConfig" >详细配置查看</a></li>
      </ul>
      <!--[if lte IE 6]>
</td></tr></table>
</a>
<![endif]-->
    </li>
    <li><a class="hide" href="#"><strong>系统用户配置</strong></a>
      <!--[if lte IE 6]>
<a href="#"><strong>系统用户配置</strong>
<table><tr><td>
<![endif]-->
      <ul>
        <li><a href="userconfig.do?ope=toEngineer" >工程人员</a></li>
        <li><a href="userconfig.do?ope=toSysadmin" >系统管理员</a></li>
      </ul>
      <!--[if lte IE 6]>
</td></tr></table>
</a>
<![endif]-->
    </li>
    <li><a class="hide" href="#"><strong>菜单配置</strong></a>
      <!--[if lte IE 6]>
<a href="#"><strong>链接配置</strong>
<table><tr><td>
<![endif]-->
      <ul>
        <li><a href="linktype.do?ope=toLinktype" >自定义菜单</a></li>
      </ul>
      <!--[if lte IE 6]>
</td></tr></table>
</a>
<![endif]-->
    </li>
    <li><a class="hide" href="#"><strong>电话配置</strong></a>
      <!--[if lte IE 6]>
<a href="#"><strong>电话配置</strong>
<table><tr><td>
<![endif]-->
      <ul>
        <li><a href="phoneconfig.do?ope=toPhone" >号码设置</a></li>
      </ul>
      <!--[if lte IE 6]>
</td></tr></table>
</a>
<![endif]-->
    </li>
    <li style="float:right; height:28px; line-height:28px; margin-right:14px;color: #3a434a;"><span id="time"></span></li>
  </ul>
  <!-- clear the floats if required -->
  <div class="clear"></div>
</div>
<div style="padding:0; margin:0; width:1024px; margin:0 auto;">
  <div id="content">
    <div id="center" >
    <div class="top" style="display:block;">
      <table id="tb" border="0" cellspacing="0" cellpadding="0" >
        <tr id="tb1" >
          <td colspan="7"><span style="text-align:left; float:left;padding-left:4px; margin-left:4px; color:#141414;"><img src=../images/admin/index/icon.gif width="9" height="10" /> 
          你当前所在位置为：${param.orgname_to_project}下工程配置 => ${param.projectname_to_ref }下的冷库列表 => ${ref.name }下<c:if test="${aiinfo==null}">增加探头</c:if><c:if test="${aiinfo!=null}">修改探头</c:if></span></td>
        </tr>
      </table>
    </div>
    <div class="bottom">
      <div class="con_center">
        <form id="myform" name="myform" action="" method="post">
			<input type="hidden" name="ope" id="ope" value="${ope }" />
			<input type="hidden" id="oid" name="oid" value="${param.oid }" />
			<input type="hidden" name="projectId_to_ref" id="projectId_to_ref" value="${param.projectId_to_ref }"/>
			<input type="hidden" name="refId_to_ai" id="refId_to_ai" value="${param.refId_to_ai }"/>
			<input type="hidden" name="storetypeStr" id="storetypeStr" value="${storetypeStr }" />
			<input type="hidden" name="aiguid" id="aiguid" value="${aiinfo.aiguid }" />
			
			<input type="hidden" name="orgname_to_project" id="orgname_to_project" value="${param.orgname_to_project}"/>
			<input type="hidden" name="projectname_to_ref" id="projectname_to_ref" value="${param.projectname_to_ref }"/>
			
			<input type="hidden" name="preid" id="preid" value="${aiinfo.reid }" />
			<p style="padding-top: 0px;padding-bottom: 8px;">&nbsp;</p>
          <table width="100%" border="0" cellpadding="0" cellspacing="0" style="text-align:left; border:0;">
            <tr>
              <td width="17%"><label for="fdsafa2" style="width:70px;">探头名称：</label></td>
              <td width="26%"><input name="name" id="name" type="text" value="${aiinfo.name }" class="input_aa" style="width: 75px;"/><span id="nameTip" style="text-align:left; margin-left:2px;font-weight: bold;">*</span></td>
              <td width="1%">&nbsp;</td>
              <td width="25%" align="left"><label for="fdsfa" style="width:85px; text-align:right;">探头类型：</label></td>
              <td width="22%" align="left" style="text-align:left;">
	              <c:if test="${ref.fdapproject.type==1}">
		              <c:if test="${aiinfo==null}">
			              <select name="selftype" id="selftype" class="select_a" onchange="changed(this)" style="width: 81px;">
			                  <option value="0">温度</option>
			                  <option value="1">湿度</option>
			              </select>
		              </c:if>
		              <c:if test="${aiinfo!=null}">
		              	<c:if test="${aiinfo.selftype==0}">
		              		<select name="selftype" id="selftype" class="select_a" style="width: 81px;">
			                  <option value="0">温度</option>
			              	</select>
		              	</c:if>
		              	<c:if test="${aiinfo.selftype==1}">
		              		<select name="selftype" id="selftype" class="select_a" style="width: 81px;">
			                  <option value="1">湿度</option>
			              	</select>
		              	</c:if>
		              </c:if>
	              </c:if>
	              <c:if test="${ref.fdapproject.type==2}">
	              		<select name="selftype" id="selftype" class="select_a" style="width: 81px;">
			                  <option value="0">温度</option>
			            </select>
	              </c:if>
              </td>
              <td width="11%">&nbsp;</td>
            </tr>
          </table>
          
          <p>
            <label for="fdsafa" style="width:70px;">报警下限：</label>
            <input name="lowerlimit" id="lowerlimit" type="text" onblur="check(this)" value="${storetype!=null?storetype.templowerlimit:aiinfo.lowerlimit }" class="input_a" style="width: 75px;"/>
            <span id="llimittip" style="margin-left: 0px;line-height: 18px;">${(storetype!=null||aiinfo.selftype==0)?'℃':'％' }</span>
            <span id="lowerlimitTip" style="text-align:left; margin-left:5px;font-weight: bold;">*</span>
            <label for="fdsafa" style="margin-left:20px;">报警下限延时：</label>
            <input name="lowerdelay" id="lowerdelay" type="text" onblur="checkdigit(this)" value="${storetype!=null?storetype.templowerdelay:aiinfo.lowerdelay }" class="input_a" style="width: 75px;"/><span style="margin-left: 0px;line-height: 18px;">min</span>
            <span id="lowerdelayTip" style="text-align:left; margin-left:5px;font-weight: bold;">*</span>
          </p>
          <%-- <p>
            <label for="fdsafa" style="width:70px;">下下限：</label>
            <input name="minlowerlimit" id="minlowerlimit" type="text" onblur="check(this)" ${storetype==null&&aiinfo.minlowerlimit==null?"disabled='disabled' style='background-color: #f1f4f8;'":"" } value="${storetype!=null?storetype.tempminlowerlimit:aiinfo.minlowerlimit}" class="input_a"/><span>℃</span>
            <label for="fdsafa" style="margin-left:20px;">下下限延时：</label>
            <input name="minlowerdelay" id="minlowerdelay" type="text" onblur="checkdigit(this)" ${storetype==null&&aiinfo.minlowerdelay==null?"disabled='disabled' style='background-color: #f1f4f8;'":"" } value="${storetype!=null?storetype.tempminlowerdelay:aiinfo.minlowerdelay }" class="input_a"/><span>min</span>
          </p>--%>
          <p>
            <label for="fdsafa" style="width:70px;">报警上限：</label>
            <input name="highlimit" id="highlimit" type="text" onblur="check(this)" value="${storetype!=null?storetype.temphighlimit:aiinfo.highlimit }" class="input_a" style="width: 75px;"/><span id="hlimittip" style="margin-left: 0px;line-height: 18px;">${(storetype!=null||aiinfo.selftype==0)?'℃':'％' }</span>
            <span id="highlimitTip" style="text-align:left; margin-left:5px;font-weight: bold;">*</span>
            <label for="fdsafa" style="margin-left:20px;">报警上限延时：</label>
            <input name="highdelay" id="highdelay" type="text" onblur="checkdigit(this)" value="${storetype!=null?storetype.temphighdelay:aiinfo.highdelay }" class="input_a" style="width: 75px;"/><span style="margin-left: 0px;line-height: 18px;">min</span>
            <span id="highdelayTip" style="text-align:left; margin-left:5px;font-weight: bold;">*</span>
          </p>
          <%--<p>
            <label for="fdsafa" style="width:70px;">上上限：</label>
            <input name="maxhighlimit" id="maxhighlimit" type="text" onblur="check(this)" ${storetype==null&&aiinfo.maxhighlimit==null?"disabled='disabled' style='background-color: #f1f4f8;'":"" } value="${storetype!=null?storetype.tempmaxhighlimit:aiinfo.maxhighlimit }" class="input_a"/><span>℃</span>
            <label for="fdsafa" style="margin-left:20px;">上上限延时：</label>
            <input name="maxhighdelay" id="maxhighdelay" type="text" onblur="checkdigit(this)" ${storetype==null&&aiinfo.maxhighdelay==null?"disabled='disabled' style='background-color: #f1f4f8;'":"" } value="${storetype!=null?storetype.tempmaxhighdelay:aiinfo.maxhighdelay }" class="input_a"/><span>min</span>
          </p>--%>
          <p>
            <label for="fdsafa" style="width:70px;">探头编号：</label>
            <input name="reid" id="reid" type="text" onblur="checkReid(this)" readonly="readonly" value="${aiinfo.reid }" class="input_a" style="background-color: #f1f4f8;width: 75px;"/>
            <c:if test="${aiinfo!=null}">
            	<select id="reidSelect" name="reidSelect" onchange="selectReid()" style="float: left;">
	            	<option value='3'>原值</option>
	            	<option value="1">自动生成</option>
	            	<option value="2">编辑</option>
            	</select>
            </c:if>
            <c:if test="${aiinfo==null}">
            	<select id="reidSelect" name="reidSelect" onchange="selectReid()" style="float: left;">
	            	<option value="1">自动生成</option>
	            	<option value="2">自定义</option>
            	</select>
            </c:if>
          </p>
          <p style="padding-top: 2px;padding-right: 0px;padding-bottom:0px;padding-left: 0px; display:block;text-align: center; "><span id="tip" style="float: none;color: #f00;">${tip }&nbsp;</span></p>
            <p style="padding-top: 2px;padding-right: 0px;padding-bottom: 20px;padding-left: 0px;">
            <input type="button" value="保存" class="but_a"  onclick="javascript:goAdd('aiconfig.do');"/>
            <input type="button" value="取消" class="but_b" onclick="javascript:gocancel('aiconfig.do','toAiList');"/>
          </p>
        </form>
      </div>
    </div>
  </div>
  </div>
  <div>
    <iframe scrolling="no" src="footer.jsp" width=100% height=26 frameborder=0></iframe >
  </div>
</div>
</body>
</html>
