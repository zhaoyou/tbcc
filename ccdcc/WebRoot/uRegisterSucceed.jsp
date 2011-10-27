<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
    <html xmlns="http://www.w3.org/1999/xhtml">
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>注册成功</title>
    <link rel="Shortcut Icon" href="img/add/logo.ico" />
    
    <link href="css/admin/sign.css" rel="stylesheet" type="text/css" />
    
    <script type="text/javascript">
		function pageInit(){
			var val= parseInt(document.getElementById('tt').innerHTML);
			document.getElementById('tt').innerHTML = val-1;
				if(val-1==0){
					window.location.href = "index.jsp" ;
				}
			window.setTimeout("pageInit()",1000);
		}
	</script>
    </head>
    <body onload="pageInit();">
    <div>
      <iframe scrolling="no" src="common/header_register.jsp" width=100% height=126 frameborder=0></iframe >
    </div>
    <div id="right">
      <div id="top"><a href="#"><img src="images/canku/icon_c.gif" width="16" height="15" / class="tb4"><strong>位置:首页</strong>&lt;您正在进行用户注册</a></div>
      <div id="center">
        <div>用户注册</div>
      </div>
      <div id="bottom" style="width:750px; line-height:28px; margin:0 auto;">
     <p style="font-size:14px;">注册成功，感谢您使用思博源冷链服务网络，客服中心人员将会尽快与您联系，开通思博源冷链服务中心帐户，谢谢!</p>
     <p style="font-size:14px;"><span id="tt" style="color: red;">15</span>秒后自动跳转到登陆页面。如果长期没反应<a href="index.jsp">请点击此处</a>跳转。</p>
      </div>
    </div>
    <div class="clear"></div>
    <div>
      <iframe scrolling="no" src="common/footer2.jsp" width=100% height=43 frameborder=0></iframe >
    </div>
    </body>
</html>

