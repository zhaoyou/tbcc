<%@ page language="java"  pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>机构(或企业)下的用户列表</title>
<link href="../css/admin/index/orgUserList.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="../images/logo.ico" />
<link href="../css/admin/dtree.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"  src="../js/admin/dtree.js"></script>

	<script type="text/javascript" src="../dwr/interface/realcar.js"></script>
	<script type='text/javascript' src='../dwr/engine.js'></script>
	<script type='text/javascript' src='../dwr/util.js'></script>


<style type="text/css">
/* common styling */
/* set up the overall width of the menu div, the font and the margins */
.menu {
	font-family: "宋体";
	
	font-size: 12px;
	margin-top: 0px;
	margin-right: 0;
	margin-bottom: 0px;
	margin-left: 0;
	background-image: url(../images/admin/head/bg_head03.jpg);
	background-repeat: repeat-x;
	height: 30px;
	padding: 0px;
	font-weight: normal;
	position:relative;
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
.dtree{font-size: 13px;}
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
		
		function getuser(oid,name){
			document.getElementById("oid").value=oid;
			document.getElementById("orgname").innerHTML=name;
			document.getElementById("oname").value=name;
			d.openTo(oid);
			realcar.getUserByOid(oid,{
				callback:result_handler,
				errorHandler:error_handler,
				timeout:25000
				});
		}
		var cellfunction = [
			function (data){
				return "<input name='userid' type='checkbox' id='userid' value='"+data.userid+"' />" ;
			},
			function (data){
				return data.name ;
			},
			function (data){
				var str="";
				for(var i=0;i<data.password.length;i++){
					str+="*";
				}
				return str ;
			},
			function (data){
				return data.fdaprole.name;
			}
		] ;
		
		function result_handler(result){
			if(result!=null && result.length>0){
				document.getElementById('tip').innerHTML ="";
				DWRUtil.removeAllRows("tbd");
		
				//把数据添加到表格
				DWRUtil.addRows("tbd",result,cellfunction,{
					escapeHtml:false ,
					cellCreator:function(options){
						var td = document.createElement("td");
						td.align = "center";
						return td ;
					},
					rowCreator:function(options){
						var tr= document.createElement("tr");
						tr.bgColor=options.rowIndex%2==0?"#ffffff":"#f1f4f8";
						tr.style.height='25px';
						return tr ;
					}
				});
			}else{			//如果没有用户信息
				DWRUtil.removeAllRows("tbd");
				var orgname = document.getElementById("oname").value;
				document.getElementById('tip').innerHTML = "<font color='blue'>"+orgname+"目前还没有用户</font>" ;
			}
		}
		
		
		function error_handler(){
			document.getElementById("tip").innerHTML="<font color='red'>获取用户列表失败</font>";
		}
		
		function goaction(url,ope){
			document.getElementById("myform").action=url;
			document.getElementById("ope").value=ope;
			document.myform.submit();
		}
		
		function goAdd(url,ope){
			var oid=document.getElementById("oid").value;
			if(oid==null||oid==""){
				alert("请选择一个机构或者企业");
				return;
			}
			goaction(url,ope);
		}
		
		function godelete(url,ope){
			var userid = document.getElementsByName("userid");
			if(userid.length>0){
				var idStr="";
				for(var i=0;i<userid.length;i++){
					if(userid[i].checked){
						idStr+="-"+userid[i].value;
					}
				}
				if(idStr!=""){
					if(confirm("确定要删除该用户？")){
						var delStr = idStr.substring(1,idStr.length);
						document.getElementById("delStr").value=delStr;
						//alert(delStr);
						goaction(url,ope);
					}
				}
				else{
					alert("请选择需要删除的用户");
					return;
				}
			}
			else{
				alert("没有可删除的用户");
				return;
			}
		}
		
		function goupdate(url,ope){
			var userid = document.getElementsByName("userid");
			if(userid.length>0){
				var count=0;
				var upId;
				for(var i=0;i<userid.length;i++){
					if(userid[i].checked){
						count++;
						upId=userid[i].value;
					}
				}
				if(count==0){
					alert("请选择需要修改的用户");
					return;
				}
				if(count>1){
					alert("一次只允许编辑一个用户");
					return;
				}
				if(count==1){
					document.getElementById("uid").value = upId;
					goaction(url,ope);
				}
			}
			else{
				alert("没有可修改的用户");
				return;
			}
		}
		
	</script>

</head>
<body>
<div style="margin:0 auto; width:1024px;">
  <iframe scrolling="no" src="head.jsp" width=100% height=99 frameborder=0></iframe >
</div>
<form action="" id="myform" name="myform" method="post">
  		<input type="hidden" id="ope" name="ope" value="" />
  		<input type="hidden" id="oid" name="oid" value="${oid }" />
  		<input type="hidden" id="delStr" name="delStr" value="" />
  		<input type="hidden" id="uid" name="uid" value="" />
  		<input type="hidden" id="oname" name="oname" value="" />
</form>
<div style="padding:0; margin:0; width:1024px; margin:0 auto;">
  <div class="menu">
    <ul>
      <li><a class="hide" href="userconfig.do?ope=toOrgUser"><strong>用户管理</strong></a>
     
        <!--[if lte IE 6]>
<a href="userconfig.do?ope=toOrgUser"><strong>用户管理</strong>
<table><tr><td>
<![endif]-->
        <!--[if lte IE 6]>
</td></tr></table>
</a>
<![endif]-->
      </li>
       <li><a class="hide" href="#"><strong>医院配置</strong></a>
       <ul>
        <li><a href="hospital.do?ope=toList" >医院列表</a></li>
        <li><a href="hospital.do?ope=toAdd" >新增医院</a></li>
      </ul>
      <!--[if lte IE 6]>
</td></tr></table>
</a>
<![endif]-->
</li>
      <li style="float:right; height:28px; line-height:28px; margin-right:14px;color: #3a434a;"><span id="time"></span></li>
    </ul>
    <!-- clear the floats if required -->
    <div class="clear"> </div>
  </div>
  <div id="content">
    <form>
       <div id="left" style="height: 480px; ">
        <h1><img src="../images/admin/index/h1.gif" width="191" height="27" /></h1>
        <div class="dtree" id="divDrag" style="text-align: left;overflow: scroll; width: 190px; height: 453px;">
          <label style="color:#FFF; width: 240px;"></label>
          <script type="text/javascript">	
				<!--
				var d= new dTree('d');
				//设置状态栏
				d.config.useStatusText=true;
				//设置是不是关闭同一层的其他节点
				d.config.closeSameLevel=false;
				//是不是可以使用cookie
				d.config.useCookies=false;
				
				${tree }
				document.write(d);
				${open }
				//-->
			</script>
        </div>
      </div>
      <div id="right">
        <div id=con_right>
          <div><img src="../images/admin/index/icon.gif" width="9" height="10" / class="img_a"><span>你当前所在位置为： <span id="orgname" style="float: none;">企业</span>的用户列表</span><br />
          </div>
          <table border="0" cellpadding="0" cellspacing="0" id="tb" style="width:780px;">
            <tr class="altrow" >
              <td width="137">用户选择</td>
              <td width="146">用户名称</td>
              <td width="173">用户密码</td>
              <td width="160">用户角色</td>
            </tr>
            <tbody id="tbd">
	          	<logic:notEmpty name="orguserlist">
		          	<logic:iterate id="orguser" name="orguserlist" indexId="irow">
		          		<tr style="height:25px;" ${irow%2==0?"":"bgcolor='#f1f4f8'" }>
		            		<td><input name='userid' type='checkbox' id='userid' value="${orguser.userid }"/></td>
		            		<td>${orguser.name }</td>
		            		<td>
		            			<c:forEach begin="1" end="${fn:length(orguser.password)}">*</c:forEach>
		            		</td>
		            		<td>${orguser.fdaprole.name }</td>
		         		</tr>
		          	</logic:iterate>
	          	</logic:notEmpty>
          	</tbody>
            <tr>
              <td colspan="4"><span id="tip">${tip }</span><br />
                <table width="64%" class="oii">
                  <tr>
                    <td><img src="../images/admin/index/icon_c.gif" width="86" height="23" onclick="javascript:goAdd('userconfig.do','toOrgUserAdd');" style="cursor:pointer;" /></td>
                  	<td><img src="../images/admin/index/icon_a.gif" width="86" height="23" onclick="javascript:goupdate('userconfig.do','toOrgUserUp');" style="cursor: pointer;" /></td>
                  	<td><img src="../images/admin/index/icon_b.gif" width="86" height="23" onclick="javascript:godelete('userconfig.do','doOrgUserDelete');" style="cursor: pointer;" /></td>
                </tr>
                </table>
                <br /></td>
            </tr>
          </table>
        </div>
      </div>
    </form>
  </div>
  <div>
    <iframe scrolling="no" src="footer.jsp" width=100% height=26 frameborder=0>6</iframe >
  </div>
</div>
</body>
</html>
