<%@ page language="java"  pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<title>你查找的页面不存在喔 !</title> 
<link rel="Shortcut Icon" href="img/add/logo.ico" />
<link href="css/404/tc.css"rel="stylesheet" type="text/css" /> 
<script type="text/javascript">
	function pageInit(){
		window.setTimeout(function(){
			window.location.href = "user.do?ope=doLogout" ;
		},5000);
	}
</script>
</head> 
<body onload="pageInit();"> 
<div> 
  <iframe scrolling="no" src="common/header3.jsp" width=100% height=126 frameborder=0></iframe> 
</div> 
<div id="right"> 
  <div id="bottom"> 
    <table width="43%" border="1" align="center" cellpadding="2" cellspacing="2"> 
      <tr> 
        <td height="74" style="border:none;"><img src="images/404/404error.gif" width="400" height="66" /></td> 
      </tr> 
      <tr> 
        <td height="74" style="border:none;"><img src="images/404/404error3.gif" width="340" height="134" /></td> 
      </tr> 
      <tr style="height: 17px"> 
        <td height="17" valign="top" style="border:none;">&nbsp;</td> 
      </tr> 
    </table> 
  </div> 
</div> 
<div class="clear"></div> 
<div> 
  <iframe scrolling="no" src="common/footer2.jsp" width=100% height=43 frameborder=0></iframe> 
</div> 
</body> 
</html> 