<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="common/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
		<title>��½ϵͳ</title>
		<link rel="Shortcut Icon" href="img/add/logo.ico" />
		<link href="css/login/login.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="script/common.js"></script>
		<script type="text/javascript">
	
	/**
	 *	�ύ����
	 */
		function submitButton(){
			var name = 	document.getElementById("uname").value ;
			var pass =	document.getElementById("upassword").value ;
			var code =	document.getElementById("checkCode").value ;
			var client = document.getElementById("clientName").value ;
			if(name=="" || pass=="" || code=="" || client=="" || name.replace(/(^\s*)|(\s*$)/g,"")=="" ||
				code.replace(/(^\s*)|(\s*$)/g,"")=="" || client.replace(/(^\s*)|(\s*$)/g,"")=="" )
			{
					
					document.getElementById('msgtip').innerHTML= "��ȷ����Ϣ����!";			
					return ;
			}
			document.getElementById('msgtip').innerHTML= "" ;
			document.actForm.submit();	
		}

 		/**
 		 *��Ӧ����entry
 		 */
 		function enterSubmit(e){
 			
 			if(getBrowser()==1){
 				if(event.keyCode==13)
					submitButton() ;
 			}else{
 				if(e.which==13)
 					submitButton() ;
 			}
 			
		
		}
		
		document.onkeydown = enterSubmit ;
 		
 		/**
 		 * 	�ı��ý���߿���ɫ
 		 */
 		 function changeFocus(obj){
 		 	obj.style.borderColor = "pink" ;
 		 }
 		 
 		 /**
 		  *	�ı�ʧȥ����ı߿���ɫ
 		  */
 		  function changeBlur(obj){
 		  	obj.style.borderColor = "#d2d2d2" ;
 		  }
 		  
 		  /**
 		  *	�ı���֤��ͼƬ
 		  */
 		  function changeImage(){
 			var c=document.getElementById('imgcode');
			c.setAttribute('src','common/image.jsp?'+Math.random());  	
			document.getElementById("checkCode").value = "" ;
			document.getElementById("checkCode").focus();
 		  }
</script>
	</head>
	<body>
		<div id="header"></div>
		<div id="con_left"></div>
		<div id="con_center">
			<h2>
				fdasf
			</h2>
			<div id="img"></div>
			<div id="left"></div>
			<div id="login">
				<form name="actForm" action="user.do?ope=doLogin" method="post">
					<h1 id="logotitle"></h1>
					<table>
						<tr>
							<td><label for="">
							�˻�����
						</label></td>
							<td><input name="clientName" value="${param.clientName }"  
							id="clientName" type="text" class="input_aa" style="height: 17px" tabindex="1"
							onfocus="changeFocus(this);" onblur="changeBlur(this);" /></td>
						</tr>
						<tr>
							<td><label for="">
							�û�����
						</label></td>
							<td><input name="uname" id="uname" value="${param.uname}"  type="text" class="input_aa"
							onfocus="changeFocus(this);" onblur="changeBlur(this);" style="height: 17px"  tabindex="2" /></td>
						</tr>
						<tr>
							<td><label for="">
							�� �룺
						</label></td>
							<td><input name="upassword" id="upassword" type="password"
							class="input_aa" onfocus="changeFocus(this);" style="height: 17px" tabindex="3"
							onblur="changeBlur(this);" /></td>
						</tr>
						<tr>
							<td><label for="">
							�����룺
						</label></td>
							<td><input name="checkCode" id="checkCode" type="text"  style="height: 16px"
							class="input_bb" onfocus="changeFocus(this);" tabindex="4"
							onblur="changeBlur(this);" />
						<img src="common/image.jsp" id="imgcode" width="44" height="16" align="middle" />
						<span onclick="changeImage();" style="cursor: pointer;" tabindex="6">��һ��ͼ</span></td>
						</tr>
					</table>
				</form>

			</div>
			<div class="clear"></div>
			<div id="topline">
				
					<table width="430" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="251" height="22">
							<div align="right" id="msgtip" style="color: red;">
								${msg}
							</div>
						</td>
					  <td width="24"><img src="images/login/button_b.gif" width="87" height="22"
								style="cursor: pointer;" onclick="to('userRegister.jsp')" tabindex="7" /></td>
						<td width="155" align="left">
							<img src="images/login/button_a.gif" width="87" height="22"
								style="cursor: pointer;" onclick="submitButton();" tabindex="7" />
						</td>
					</tr>
				</table>
					
					
					
					
			
				<%-- <table width="430" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="251">
							<div align="right" id="msgtip" style="color: red;">
								${msg}
							</div>
						</td>
						<td width="24"></td>
						<td width="155" align="left">
							<img src="../images/login/button_a.gif" width="87" height="22"
								style="cursor: pointer;" onclick="submitButton();" tabindex="7" />
						</td>
					</tr>
				</table>--%>
				
				
				<div>
				</div>
			</div>
		</div>
		<div id="con_right"></div>
		<div class="clear"></div>
		<div id="footer">
			<p>
				�Ϻ�˼��Դ�����Ƽ����޹�˾��Ȩ����Copyright 2008-2010
			</p>
			<p>
				����绰����021-50326758��
			</p>
		</div>
		<%--����������վ��ͳ�Ƶ�javascript�ű� --%>
		<script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
		<script type="text/javascript">
try {
var pageTracker = _gat._getTracker("UA-15622024-1");
pageTracker._trackPageview();
} catch(err) {}</script>
	</body>
</html>
