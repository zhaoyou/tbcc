<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>总部分支关系页面</title>
<link href="css/fenzhi/tc.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="img/add/logo.ico" />


	<script type="text/javascript" src="script/dtree/dtree.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/dtree/dtree.css"/>

	<script type="text/javascript">
	
		function golist(url,operate){
			document.myform.action = url ;
			document.getElementById('ope').value = operate ;
			document.myform.submit() ;
		}
		
		
		function gobranch(url,operate,bid){			
			document.getElementById('branchId').value = bid ;
			golist(url,operate);
		}
	</script>
	


</head>
<body>
<div>
  	<iframe scrolling="no" src="common/header3.jsp" width=100% height=126 frameborder=0></iframe >
</div>
<div id="right">
  <div id="top"><a href="#"><img src="images/fenzhi/icon_c.gif" width="16" height="15"  class="tb4"/><strong>位置:首页</strong>&lt;选择分支登陆系统</a></div>
  <div id="bottom">
    <table width="50%" border="0" align="center" cellpadding="0" cellspacing="0" id="tb4">
      <tr>
        <td colspan="4"><span class="t">
          <h3>请选择一个分支进入</h3>
          </span></td>
        <td colspan="4"></td>
      </tr>
      <tr>
      <tr>
        <td colspan="4"></td>
        <td colspan="4"></td>
      </tr>
      <tr>
        <td width="8"></td>
        <td colspan="2"></td>
        <td width="8"></td>
      </tr>
      <tr>
        <td width="8" height="8"><img src="images/fenzhi/index1_23.gif" width="8" height="29" /></td>
        <td colspan="2" background="images/fenzhi/index1_24.gif">&nbsp;</td>
        <td width="8" height="8"><img src="images/fenzhi/index1_26.gif" width="8" height="29" /></td>
      </tr>
      <tr>
        <td background="images/fenzhi/index1_45.gif">&nbsp;</td>
        <td colspan="2" bgcolor="#FFFFFF" style="padding:0 2px 0 2px; vertical-align:top;height:300px;">
        <div>
        	<div > 
			<script type="text/javascript">
					
		<!--
		var d = new dTree('d');
		//设置状态栏
		d.config.useStatusText=true;
		//设置是不是关闭同一层的其他节点
		d.config.closeSameLevel=false;
		//是不是可以使用cookie
		d.config.useCookies=false;
		//默认增加根节点
		d.add(0,-1,'上海思博源冷链科技有限公司');
		${tree}
		document.write(d);
		//-->
			</script>   
		</div>
		</div>
        </td>
        <td background="images/fenzhi/index1_47.gif"></td>
      </tr>
      <tr>
        <td width="8" height="15" background="images/fenzhi/index1_45.gif">&nbsp;</td>
        <td width="358">&nbsp;</td>
        <td width="118">
        <img src="images/fenzhi/bt_feedback.gif" width="78" height="21" style="cursor:pointer;" onclick="javascript:golist('user.do','doLogout');"  />
        </td>
        <td width="8" height="8" background="images/fenzhi/index1_47.gif">&nbsp;</td>
      </tr>
      <tr>
        <td width="8" height="15" background="images/fenzhi/index1_45.gif">&nbsp;</td>
        <td colspan="2">&nbsp;</td>
        <td width="8" height="8" background="images/fenzhi/index1_47.gif">&nbsp;</td>
      </tr>
      <tr>
        <td height="8"><img src="images/fenzhi/index1_91.gif" width="8" height="8" /></td>
        <td colspan="2" background="images/fenzhi/index1_92.gif"></td>
        <td height="8"><img src="images/fenzhi/index1_93.gif" width="8" height="8" /></td>
      </tr>
    </table>
  </div>
  	<form action="" name="myform" id="myform" method="post">
		<input type="hidden" id="ope" name="ope" value=""/>
		<input type="hidden" id="branchId" name="branchId" value =""/>
	</form>
</div>
<div class="clear"></div>
<div>
  <iframe scrolling="no" src="common/footer2.jsp" width=100% height=43 frameborder=0></iframe >
</div>
</body>
</html>

