<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
    <html xmlns="http://www.w3.org/1999/xhtml">
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>ע��ɹ�</title>
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
      <div id="top"><a href="#"><img src="images/canku/icon_c.gif" width="16" height="15" / class="tb4"><strong>λ��:��ҳ</strong>&lt;�����ڽ����û�ע��</a></div>
      <div id="center">
        <div>�û�ע��</div>
      </div>
      <div id="bottom" style="width:750px; line-height:28px; margin:0 auto;">
     <p style="font-size:14px;">ע��ɹ�����л��ʹ��˼��Դ�����������磬�ͷ�������Ա���ᾡ��������ϵ����ͨ˼��Դ�������������ʻ���лл!</p>
     <p style="font-size:14px;"><span id="tt" style="color: red;">15</span>����Զ���ת����½ҳ�档�������û��Ӧ<a href="index.jsp">�����˴�</a>��ת��</p>
      </div>
    </div>
    <div class="clear"></div>
    <div>
      <iframe scrolling="no" src="common/footer2.jsp" width=100% height=43 frameborder=0></iframe >
    </div>
    </body>
</html>

