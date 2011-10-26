<%@ page language="java"   pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>冷库仓储类型信息</title>
<link href="../css/admin/index/refParamAdd.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="../images/logo.ico" />
<script type="text/javascript" src="../js/jquery-1.4.min.js"></script>
<script type="text/javascript">
		
		function checkdigit(o){
			if(o.value=="")return;
			var p = /^[0-9]*[1-9][0-9]*$/;
			if(!p.test(o.value)||o.value<0||o.value>60){
				o.style.borderColor="red";
				$('#tipMsg').html( "<font color='red'>延时必须为0到60之间的正整数!</font>" ) ;
			}
			else{
				o.style.borderColor="gray";
				$('#tipMsg').html("&nbsp;") ;
			}
		}
		function checkT(o) {
			if(o.value=="")return;
			var p = /^(-?\d+)(\.\d+)?$/;
			if(!p.test(o.value)||o.value<-30||o.value>50){
				o.style.borderColor="red";
				$('#tipMsg').html( "<font color='red'>温度上下限必须为-30与50之间的实数!</font>" ) ;
			}
			else{
				o.style.borderColor="gray";
				$('#tipMsg').html("&nbsp;") ;
			}
		}
		
		function checkR(o) {
			if(o.value=="")return;
			var p = /^(-?\d+)(\.\d+)?$/;
			if(!p.test(o.value)||o.value<0||o.value>100){
				o.style.borderColor="red";
				$('#tipMsg').html( "<font color='red'>湿度上下限必须为0与100之间的实数!</font>" ) ;
			}
			else{
				o.style.borderColor="gray";
				$('#tipMsg').html("&nbsp;") ;
			}
		}

		function savaValue(){
			var $input = $("form input:text");
			var d = /^[0-9]*[1-9][0-9]*$/;
			var flag = false ;
			
			$input.each(function(){
				if($(this).val()==""){
					flag = true ;
					$(this).css('borderColor','red');
					$("#"+this.name+"Tip").css('color','red');
					
				}else{ 
					$(this).css('borderColor','gray');
					$("#"+this.name+"Tip").css('color','black');
				}
			});
			
			//信息是否完整，数据验证是否通过
			if(flag){
				$('#tipMsg').html( "<font color='red'>请确保信息完整!</font>" ) ;
				return ;
			}
			
			//验证数据是否在范围之内的标志
			var flag1 = false;
			//验证数据是否在范围之内
			$input.each(function(){
						if($(this).attr("name")!="storeTypeName"){
							if(!isFinite($(this).val()))   {
								flag1 = true;
								$(this).focus();
								$(this).css('borderColor','red');
								$('#tipMsg').html("<font color='red'>请确保输入信息为数字!</font>");
								return false;
							}
							var leng = $(this).attr("name").length;
							if($(this).attr("name").substring(leng-5,leng)=="_time"){
								if(!d.test($(this).val())||$(this).val()<0||$(this).val()>60){
									flag1 = true;
									$(this).css('borderColor','red');
									$(this).focus();
									$('#tipMsg').html( "<font color='red'>延时必须为0与60之间的正整数!</font>" ) ;
									return false;
								}
							}else if($(this).attr("name").substring(0,3)=="rh_"){
									if($(this).val()<0||$(this).val()>100){
										flag1 = true;
										$(this).css('borderColor','red');
										$(this).focus();
										$('#tipMsg').html( "<font color='red'>湿度上下限必须为0与100之间的实数!</font>" ) ;
										return false;
									}
								}else if($(this).val()<-30||$(this).val()>50){
									flag1 = true;
									$(this).css('borderColor','red');
									$(this).focus();
									$('#tipMsg').html( "<font color='red'>温度上下限必须为-30与50之间的实数!</font>" ) ;
									return false;
								}
						}
			});
			if(flag1){return;}
			if(parseFloat($('#t_lower').val())>=parseFloat($('#t_high').val())){
				$('#t_lower').css('borderColor','red');
				$('#t_high').css('borderColor','red');
				$('#tipMsg').html( "<font color='red'>温度上限不能小于下限！</font>" ) ;
				return;
			}
			if(parseInt($('#rh_lower').val())>=parseInt($('#rh_high').val())){
				$('#rh_lower').css('borderColor','red');
				$('#rh_high').css('borderColor','red');
				$('#tipMsg').html( "<font color='red'>湿度上限不能小于下限！</font>" ) ;
				return;
			}
			
			if($('#storeTypeName').val().length>25){
				$('#storeTypeName').focus();
				$("#tipMsg").html("<font color='red'>仓储名称最长为25个字符</font>");
				return;
			}
			
			$('#myform')[0].submit();
			
		}
		
		function cancelValue(){
			$('#ope').val("toStoreList");
			$('#myform')[0].submit();
		}
</script>
<style type="text/css">
/* common styling */
/* set up the overall width of the menu div, the font and the margins */
.menu {
	font-family: "宋体";
	width:1024px;
	font-size: 12px;
	margin-top: 0px;
	margin-right: auto;
	margin-bottom: 0px;
	margin-left: auto;
	background-image: url(../images/admin/head/bg_head03.jpg);
	background-repeat: repeat-x;
	height: 30px;
	padding: 0px;
	font-weight: normal;
}
/* remove the bullets and set the margin and padding to zero for the unordered list */
.menu ul {
	padding:0;
	margin:0;
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
        	<%--<li><a href="defaultdisplay.do?ope=toDefault" >机构显示方式</a></li>--%>
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
    <li style="float:right; height:28px; line-height:28px; margin-right:14px;color: #3a434a;">
     <span id="time"></span>
    </li>
  </ul>
  <!-- clear the floats if required -->
  <div class="clear"> </div>
</div>
<div style="padding:0; margin:0; width:1024px; margin:0 auto;">
  <div id="content">
    <div id="center" >
    <div class="top" style="display:block;">
      <table id="tb" border="0" cellspacing="0" cellpadding="0" >
        <tr id="tb1" >
          <td colspan="7"><span style="text-align:left; float:left;padding-left:4px; margin-left:4px; color:#141414;"><img src=../images/admin/index/icon.gif width="9" height="10" /> 你当前所在位置为： <c:if test="${storetype==null}">增加</c:if><c:if test="${storetype!=null}">更新</c:if>仓储类型</span></td>
        </tr>
      </table>
    </div>
    <div class="bottom">
      <div class="con_center">
        <form action="storeconfig.do" method="post" name="myform" id="myform">
        	<input type="hidden" name="ope" id="ope" value="${operater }"/>
			<input type="hidden" name="storetypeid" id="storetypeid" value="${storetype.storeid }"/>
          <table width="45%" border="0" align="center" cellpadding="0" cellspacing="0" style="border:0; margin:0 auto;">
            <tr>
              <td width="18%"><label for="fdsafa2" style="width:90px;">仓储类型名称：</label></td>
              <td width="22%"><input name="storeTypeName" id="storeTypeName" type="text" value="${storetype.name }" class="input_aa"/></td>
              <td width="22%"><span id="storeTypeNameTip" style="display: block; float: left; margin-left:2px; line-height: 18px; font-weight: bold;">*</span></td>
              </tr>
          </table>
          <br />
     
          <table width="17%" border="0" align="center" cellpadding="0" cellspacing="0" style=" border:0;margin:0 auto;">
            <tr>
              <td width="18%"><label for="fdsafa2" style="width:70px;">温度参数</label></td>
              </tr>
          </table>
         <p>
            <label for="fdsafa" style="width:70px;">温度下限：</label>
            <input id="t_lower" name="t_lower" type="text" onblur="checkT(this)" value="${storetype.templowerlimit }" class="input_a"/><span style="display: block;float: left;margin-left:2px;line-height: 18px;">℃</span>
            <span id="t_lowerTip" style="display: block;float: left;margin-left:2px;line-height: 18px;font-weight: bold;">*</span>
            <label for="fdsafa" style="margin-left:20px;">温度下限延时：</label>
            <input name="t_lower_time" type="text" onblur="checkdigit(this)" value="${storetype.templowerdelay }" class="input_a"/><span style="display: block;float: left;margin-left:2px;line-height: 18px;">min</span>
            <span id="t_lower_timeTip"  style="display: block;float: left;margin-left:2px;line-height: 18px;font-weight: bold;">*</span>
          </p>
          <p>
            <label for="fdsafa" style="width:70px;">温度上限：</label>
            <input id="t_high" name="t_high" type="text" onblur="checkT(this)" value="${storetype.temphighlimit }"  class="input_a"/><span style="display: block;float: left;margin-left:2px;line-height: 18px;">℃</span>
            <span id="t_highTip" style="display: block;float: left;margin-left:2px;line-height: 18px;font-weight: bold;">*</span>
            <label for="fdsafa" style="margin-left:20px;">温度上限延时：</label>
            <input name="t_high_time" type="text" onblur="checkdigit(this)" value="${storetype.temphighdelay }" class="input_a"/><span style="display: block;float: left;margin-left:2px;line-height: 18px;">min</span>
            <span id="t_high_timeTip" style="display: block;float: left;margin-left:2px;line-height: 18px;font-weight: bold;">*</span>
            <br />
          </p>
          <table width="17%" border="0" align="center" cellpadding="0" cellspacing="0" style="border:0; margin:0 auto;">
            <tr>
              <td width="18%"><label for="fdsafa4" style="width:70px;margin:0 auto;">湿度参数</label></td>
            </tr>
          </table>
          <p>
            <label for="fdsafa" style="width:70px;">湿度下限：</label>
            <input id="rh_lower" name="rh_lower" type="text" onblur="checkR(this)" value="${storetype.humlowerlimit }" class="input_a"/><span style="display: block;float: left;margin-left:2px;line-height: 18px;">％</span>
            <span id="rh_lowerTip" style="display: block;float: left;margin-left:2px;line-height: 18px;font-weight: bold;">*</span>
            <label for="fdsafa" style="margin-left:20px;">湿度下限延时：</label>
            <input name="rh_lower_time" type="text" onblur="checkdigit(this)" value="${storetype.humlowerdelay }"  class="input_a"/><span style="display: block;float: left;margin-left:2px;line-height: 18px;">min</span>
            <span id="rh_lower_timeTip" style="display: block;float: left;margin-left:2px;line-height: 18px;font-weight: bold;">*</span>
          </p>
          <p>
            <label for="fdsafa" style="width:70px;">湿度上限：</label>
            <input id="rh_high" name="rh_high" type="text" onblur="checkR(this)" value="${storetype.humhighlimit }"  class="input_a"/><span style="display: block;float: left;margin-left:2px;line-height: 18px;">％</span>
            <span id="rh_highTip" style="display: block;float: left;margin-left:2px;line-height: 18px;font-weight: bold;">*</span>
            <label for="fdsafa" style="margin-left:20px;">湿度上限延时：</label>
            <input name="rh_high_time" type="text" onblur="checkdigit(this)" value="${storetype.humhighdelay }"   class="input_a"/><span style="display: block;float: left;margin-left:2px;line-height: 18px;">min</span>
            <span id="rh_high_timeTip" style="display: block;float: left;margin-left:2px;line-height: 18px;font-weight: bold;">*</span>
          </p>
          <p style="padding-top: 5px; padding-right: 0px; padding-bottom:0px; padding-left: 0px; display:block;"><span id="tipMsg" style="float: none;">&nbsp;</span></p>
            <p style="padding-top: 5px;padding-right: 0px;padding-bottom:2px;padding-left: 0px; margin:0 auto; text-align:center;">
            <span style="width: 20px;">&nbsp;</span>
            <input type="button" value="保存" class="but_a"  onclick="javascript:savaValue();"/>
            <input type="button" value="取消" class="but_b" onclick="javascript:cancelValue();" />
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
