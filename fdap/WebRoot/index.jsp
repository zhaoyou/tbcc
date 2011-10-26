<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录系统</title>
<link href="css/login/login_01.css" rel="stylesheet" type="text/css" />
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
		
		
		/**
 		  *	改变验证码图片
 		  */
 		  function changeImage(){
 			var c=document.getElementById('img');
			c.setAttribute('src','common/image.jsp?'+Math.random());  	
			document.getElementById("checkCode").value = "" ;
			document.getElementById("checkCode").focus();
 		  }
		
</script>
</head>
<body>
<form name="myform" action="user.do"  id="myform" name="myform"  method="post">
<input type="hidden" name="ope" value="doLogin"/>
<div id="content">
    <div id="top"></div>
    <div id="bottom">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="4%" valign="top">&nbsp;</td>
          <td width="40%" height="193" valign="top">　　<img src="images/login_01/login_06.gif" width="194" height="110" style=" display:block;" /></td>
          <td width="56%" valign="top"><table width="100%" height="157" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="22%" height="27">帐户名：</td>
                <td width="78%"><span style="height:20px;">
                  <input type="text" name="clientName" id="clientName"  value="${param.clientName }" class="input_dd" />
                  </span></td>
              </tr>
              <tr>
                <td height="27">用户名：</td>
                <td><span style="height:20px;">
                  <input type="text" name="userName" id="userName" class="input_dd"  />
                  </span></td>
              </tr>
              <tr>
                <td height="27">密码：</td>
                <td><span style="height:20px;">
                  <input type="password" name="password" id="password" class="input_dd"  />
                  </span></td>
              </tr>
              <tr>
                <td height="27">验证码：</td>
                <td><span style="height:20px;">
                  <input type="text" name="checkCode"  id="checkCode" class="input_dd" style="width:60px;margin-top:1px;"  />
                  <img src="common/image.jsp" width="60" height="20" id="img" class="img"/>
                  </span><span onclick="changeImage();" style="cursor: pointer;display:block; float:left; margin-top:5px;" tabindex="6">换一张图</span></td>
              </tr>
              <tr>
                <td height="10" colspan="2" align="center" valign="middle"><font style="text-align:center;
                  display:block; color:#F00;"><span  id="myTip">${msg}</span>&nbsp;</font></td>
              </tr>
              <tr>
                <td height="10" colspan="2"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr style="height:30px;">
                      <td width="31">&nbsp;</td>
                      <td width="67"><img src="images/login_01/button_a.gif" width="53" height="27" onclick="doLogin();"  style="cursor:pointer;"/></td>
                      <td width="47">&nbsp;</td>
                      <td width="54"><img src="images/login_01/button_b.gif" width="53" height="27" onclick="doReset();" style="cursor:pointer;"/></td>
                          <td width="41"></td>
                      <td width="43" align="left">&nbsp;</td>
                    </tr>
                  </table></td>
              </tr>
            </table></td>
        </tr>
      </table>
    </div>
  </div>
</form>
</body>
</html>
