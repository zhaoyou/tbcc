<%@ page language="java" pageEncoding="GBK" %>
<%@ include file="common/taglib.jsp" %>

<html>
  <head>
    <title>登陆页面</title>
 <LINK href="css/login.css" type=text/css rel=stylesheet>
 <style>
 	.input{ border-color:#FFCCCC; border-style:solid; border-width:1px}
	.input2{border-color:#FF66FF; border-style:solid; border-width:1px}
 </style>
 <link rel="Shortcut Icon" href="img/add/logo.ico" >
 <script type="text/javascript" src="script/common.js"></script>
</HEAD>
    
    
	<script type="text/javascript">

	
	/**
	 *	提交数据
	 */
		function submitButton(){
			var name = 	document.forms[0].elements["uname"].value ;
			var pass =	document.forms[0].elements["upassword"].value ;
			var code =	document.forms[0].elements["checkCode"].value ;
			var client = document.forms[0].elements["clientName"].value ;
			if(name=="" || pass=="" || code=="" || client=="" || name.replace(/(^\s*)|(\s*$)/g,"")=="" ||
				code.replace(/(^\s*)|(\s*$)/g,"")=="" || client.replace(/(^\s*)|(\s*$)/g,"")=="" )
			{
					window.alert("请确保信息完整!");
					return ;
			}
			document.actForm.submit();	
		}

 		/**
 		 *响应键盘entry
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
 		 * 	改变获得焦点边框颜色
 		 */
 		 function changeFocus(obj){
 		 	obj.style.borderColor = "pink" ;
 		 }
 		 
 		 /**
 		  *	改变失去焦点的边框颜色
 		  */
 		  function changeBlur(obj){
 		  	obj.style.borderColor = "#d2d2d2" ;
 		  }
 		  
 		  /**
 		  *	改变验证码图片
 		  */
 		  function changeImage(){
 			var c=document.getElementById('imgcode');
			c.setAttribute('src','common/image.jsp?'+Math.random());  	
			document.getElementById("checkCode").value = "" ;
			document.getElementById("checkCode").focus();
 		  }

	</script>
<BODY onkeypress=""  class=PageBody leftMargin=0 topMargin=0 MARGINHEIGHT="0" 
    MARGINWIDTH="0">
   
<CENTER>
<FORM name="actForm" action="user.do?ope=doLogin" method="post">

<TABLE height="100%" cellSpacing=0 cellPadding=0 border=0>
  <TBODY>
  <TR>
    <TD></TD></TR>
  <TR>
    <TD>
      <TABLE cellSpacing=0 cellPadding=0 border=0>
        <TBODY>
        <TR class=UpTr height=20>
          <TD></TD>
          <TD></TD>
          <TD class=VersionTitle align=right> 
          <br></TD>
        </TR>
        <TR class=UpTr height=65>
          <TD width=20></TD>
          <TD colSpan=2 ><img alt="" src="img/logo.gif"><br></TD></TR>
        <TR height=3>
          <TD  colSpan=3 ></TD></TR>
        <TR class=DownTr>
          <TD></TD>
          <TD>
            <TABLE height=204 cellSpacing=0 cellPadding=0 border=0>
              <TBODY>
              
              <TR height=18>
                <TD width=290  
                colSpan="2"></TD></TR>
              <TR>
                <TD class=LoginLine width=2></TD>
                <TD width=286>
                  <TABLE height="127" cellSpacing=0 cellPadding=0 width="288" 
                  border=0 style="width: 288px; height: 127px;">
                  
                    <TBODY>
                    
                    <TR height=10>
                      <TD colSpan=6 height="20" background="img/userLogin.gif"></TD></TR>
                      
                      <tr>
                      	<TD class=ItemTitleFont align=right height=25>账户名：</TD>
                      <TD width="120"><INPUT id="clientName"  class=inputFrm type=text 
                    name="clientName" value="${param.clientName }" onfocus="changeFocus(this);" onblur="changeBlur(this);" size="15"></TD>
                    <td></td>
                      </tr>
                      
                    <TR>
                      <TD class=ItemTitleFont align=right width=80 
                        height=25>用户名：</TD>
                      <TD width=120><INPUT id="uname" class="inputFrm"  value="${param["uname"]}" 
                        name="uname" onfocus="changeFocus(this);" onblur="changeBlur(this);" size="15"></TD>
                      <TD align=center rowSpan=2><span style="cursor: pointer"><img src="img/userLogin_button.gif" onclick="submitButton();"/><br></span></TD></TR>
                    
                    
                    <TR>                  
                      <TD class=ItemTitleFont align=right >密  码：</TD>
                      <TD width="120"><INPUT id="upassword"  class=inputFrm type=password 
                    name="upassword"  size="15" onfocus="changeFocus(this);" onblur="changeBlur(this);"></TD></TR>
                    
                    <TR>
                      <TD class=ItemTitleFont align=right height=25>验证码：</TD>
                      <TD width="120"  valign="middle"><input name="checkCode" id="checkCode" size="6" style="width: 50px" class="inputFrm"  onfocus="changeFocus(this);" onblur="changeBlur(this);"/>
                      <img id="imgcode" alt="验证码" src="common/image.jsp"  align="middle"/>
                      </TD>
                      <TD class=ItemTitleFont ><a href="javascript:changeImage()" style="text-decoration: none">&nbsp;&nbsp;换一张图</a></TD>
                      </TR>
                    
                    <!--  
                    <TR>
                      <TD class=ItemTitleFont align=right height=25></TD>
                      <TD><input name="checkCode"  class="inputFrm" onfocus="changeFocus(this);" onblur="changeBlur(this);"/></TD>
                      <TD></TD></TR>    
                    -->   
                    <TR>                   
                      <TD colspan="3"  align="center"><font color="red" size="2">${msg}</font></TD>
                      <TD></TD>
                      <TD></TD></TR></TBODY></TABLE>
                      
                      </TD>
                <TD class=LoginLine width=2></TD></TR>
              <TR height=11>
                <TD width=290 background="img/userLogin_down.gif" 
                colSpan=3 align="center"></TD></TR></TBODY></TABLE></TD>
          <TD width=228 background="img/logo_bg.gif"><br></TD></TR>
        <TR class=DownTr height=24>
          <TD></TD>
          <TD class=VersionTitle vAlign=bottom align=right 
           colSpan=2><A 
            class=close 
            href="" 
            target=_blank><br></A></TD>
        </TR></TBODY></TABLE></TD></TR>
  <TR height="3%">
    <TD>&lt;</TD></TR>
    				</TBODY>
    			</TABLE>
    		</FORM>
    	</CENTER>
   <%--下面是增加站点统计的javascript脚本 --%>
  <script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
try {
var pageTracker = _gat._getTracker("UA-15622024-1");
pageTracker._trackPageview();
} catch(err) {}</script>
</BODY></html>
