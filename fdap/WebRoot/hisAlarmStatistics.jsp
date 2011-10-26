<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>报警事件的统计</title>
<link href="css/index/hiscar_startup.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="images/logo.ico" />

<script type="text/javascript" language="javascript">
	function goback(){
		document.myform.submit() ;
	}
</script>

</head>
<body>
<div>
  <iframe scrolling="no" src="head.jsp" width=100% height=128 frameborder=0></iframe >
</div>
<form action="org.do" method="post" name="myform" id="myform">
	<input type="hidden" id="ope" name="ope" value="toOrg"/>
	<input type="hidden" id="oid" name="oid" value="${oid }" />
</form>
<div id="content">
  <div id="center">
    <div class="top">
      <table width="985" border="0" cellpadding="0" cellspacing="0" id="tb" >
        <tr id="tb1" >
          <td width="983" colspan="7"><span style="text-align:left; float:left;padding-left:4px; margin-left:4px; color:#141414;"><img src=images/index/icon.gif width="9" height="10" /> 当前位置： ${param.orgName_statistics }下的历史报警统计</span>
          <a href="javascript:goback();"><img src="images/index/back.gif" width="58" height="21" 
        class="pho"/></a></td>
        </tr>
      </table>
    </div>
    <div class="bottom" style="text-align:center; margin-top:0px; font-size:14px; "></div>
   	<div><iframe src="fdap/fdap1.html?oid=${oid }" width="1000" height="500" scrolling="no" style="border: none;"></iframe></div>
  </div>
</div>
<div>
  <iframe scrolling="no" src="footer.jsp" width=100% height=26 frameborder=0></iframe >
</div>
</body>
</html>
