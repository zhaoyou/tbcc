<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录系统</title>
<link href="css/login/login.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="images/logo.ico" />
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
		function doReset(){
			document.getElementById("clientName").value = "" ;
			document.getElementById("userName").value = "" ;
			document.getElementById("password").value = "" ;
			document.getElementById("checkCode").value = "" ;
		}
		
		
		/**
 		 *响应键盘entry
 		 */
 		function enterSubmit(e){
 			
 			if(getBrowser()==1){
 				if(event.keyCode==13)
					doLogin() ;
 			}else{
 				if(e.which==13)
 					doLogin() ;
 			}
 			
		
		}
		
		document.onkeydown = enterSubmit ;
		
		function doLogin(){
			var c = document.getElementById("clientName").value ;
			var u  = document.getElementById("userName").value  ;
			var p = document.getElementById("password").value  ;
			var code = document.getElementById("checkCode").value ;
			if(c=="" || u=="" || p=="" || code==""){
				
				document.getElementById("myTip").innerHTML = "请确保信息完整!" ;
				return ;
			}
			document.getElementById("myTip").innerHTML = "" ;
			document.myform.submit() ;
		}
</script>
</head>
<body>
<form name="myform" action="user.do"  id="myform" name="myform"  method="post">
<input type="hidden" name="ope" value="doLogin"/>
<div class="center">
<div class="center">
<table width="100%" cellpadding="0" cellspacing="0">
  <tr>
    <td height="50">&nbsp;</td>
  </tr>
  <tr>
    <td height="64"><table width="100%" border="0" cellpadding="5" cellspacing="0">
      <tr>
        <td width="35%" style="height:30px;"><img src="images/login/21_05.gif" width="95" height="32" /></td>
        <td width="65%" valign="middle" style="height:20px;">
       <input type="text" name="clientName" id="clientName"  value="${param.clientName }" class="input_dd" style="margin-top:7px;" /></td>
      </tr>
      <tr>
        <td><img src="images/login/21_07.gif" width="95" height="31" /></td>
        <td valign="middle">
        <input type="text" name="userName" class="input_dd" id="userName" style="margin-top:6px;" /></td>
      </tr>
      <tr>
        <td><img src="images/login/21_13.gif" width="95" height="32" /></td>
        <td valign="middle"><input type="password" name="password" id="password" class="input_dd" style="margin-top:6px;"/></td>
      </tr>
      <tr>
        <td><img src="images/login/21_15.gif" width="95" height="32" /></td>
        <td valign="middle">
        <input type="text"  name="checkCode"  id="checkCode" class="input_dd" style="width:60px; float:left;" />
         <img src="common/image.jsp" width="60" height="20" class="img"/> 
        </td>
      </tr>
      <tr>
        <td height="12" colspan="2"><span  id="myTip" class="font">${msg}</span></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="101" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr style="height:30px;">
        <td width="44">&nbsp;</td>
        <td width="77"><img src="images/login/button_a.gif" width="77" height="26" onclick="doLogin();" style="cursor: pointer;" /></td>
        <td width="34">&nbsp;</td>
        <td width="77"><img src="images/login/button_b.gif" width="77" height="26" onclick="doReset();" style="cursor: pointer;"  /></td>
        <td width="41">&nbsp;</td>
        </tr>
      </table></td>
  </tr>
  </table>

</div>
</div>
</form>
</body>
</html>
