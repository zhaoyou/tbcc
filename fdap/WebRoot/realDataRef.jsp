<%@ page language="java"  pageEncoding="utf-8"%>
<%@include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${fdaporg.name }仓库实时数据页面</title>
<link rel="Shortcut Icon" href="images/logo.ico" />
<link href="css/index/real_car.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" language="javascript">
	
		//执行跳转
		function goback(url,method){
				document.myform.action = url ;
				document.getElementById('ope').value = method ;
				document.myform.submit() ;
		}
	
		//页面初始化
		function init(){
			
			var refsize = document.getElementById("refsize").value;
			if(refsize>8)
				document.getElementById("center").style.height=(52*refsize)+'px';
			
			var pro = document.getElementById("projectIds").value ;
			
			if(pro==null || pro==""){
				document.getElementById('container').innerHTML =  "<font color='blue'>没有发现相应的冷库工程...</font>" ;
				return ;
			}
			getData();
			window.setInterval("getData()",10000);
		
		}
	
			//采用原生态Ajax代码请求，返回冷库的历史数据
			var xmlHttpRequest = null  ;
			
			
			//创建xmlhttprequest  对象
			function createXMLHttpRequest(){
				 if (window.XMLHttpRequest) { 
     			   		xmlHttpRequest = new XMLHttpRequest();  // Mozilla、Firefox、Safari 
  				  }else if (window.ActiveXObject) { 
      				  xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP"); // Internet Explorer
  				  }		
  				  	
			}
			
			//获取请求的路径
			function getURL(){
				var p = document.getElementById('projectIds').value ;
				var url = "refReal.do?ope=doRealRef&p="+p+"&time="+new Date().getTime(); ;
				return url ;
			}
			
			
			
			//请求状态发生改变时处理函数
			function statusChangeHandler(){
				//表示服务器端已经处理完毕
				if(xmlHttpRequest.readyState==4 ){
					if(xmlHttpRequest.status==200){
						var r = xmlHttpRequest.responseText ;
						document.getElementById("container").innerHTML = r ;
				    }
			    }
			}
			
			
			
			//每个10秒调用的方法
			function getData(){
			    createXMLHttpRequest() ;
				xmlHttpRequest.onreadystatechange = statusChangeHandler ;
				xmlHttpRequest.open("GET",getURL());
				xmlHttpRequest.send(null);	
				
				
			}
</script>
</head>
<body onload="init();" >
<div>
  <iframe scrolling="no" src="head.jsp" width=100% height=128 frameborder=0></iframe >
</div>
<div style="padding:0;width:1024px; margin:0 auto;">
<form name="myform" id="myform" action="" method="post">
<input  type="hidden" name="ope" id="ope" value=""/> 
<input type="hidden" name="oid"  id="oid" value="${fdaporg.oid }"/>
<input type="hidden" name="projectIds"  id="projectIds" value="${projectIds }"/>
<input type="hidden" name="refsize" id="refsize" value="${refsize }" />
</form>
<div id="content">
  <div id="center">
    <div class="top">
      <table width="985" border="0" cellpadding="0" cellspacing="0" id="tb" >
        <tr id="tb1" >
          <td width="983" colspan="7">
          <span style="text-align:left; float:left;padding-left:4px; margin-left:4px; color:#141414;">
          <img src=images/index/icon.gif width="9" height="10" /> 当前位置： 查看${fdaporg.name }的仓库实时数据</span>
          <a href="javascript:goback('org.do','toHigherOrg')"><img src="images/index/back.gif" width="58" height="21" 
        class="pho"/></a>
        </td>
        </tr>
      </table>
    </div>
    <div class="bottom">
      <table id="tb" border="0" cellspacing="0" cellpadding="0">
        <tr >
          <td height="20" colspan="14" align="left" style="text-align:left;">
          <table width="100%" style="height:35px;">
              <tr>
                <td width="10%" style="border:none;"><a href="javascript:;"><img src="images/index/canku.gif" width="100" height="24" /></a></td>
                <td width="13%" style="border:none;"><a href="javascript:goback('carReal.do','toRealCar');"><img src="images/index/car.gif" width="100" height="24" /></a></td>
                <td width="77%" style="border:none;">&nbsp;</td>
              </tr>
            </table></td>
        </tr>
        <tr id="errorTip" style="display: none"><td colspan="20">
        <div style="color: red">无法获取到数据...</div></td></tr>
        
       	<tr><td colspan="21">
       		<div id="container"  align="left">正在获取数据...</div>
       		</td>
       	</tr>    
      </table>
    </div>
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
  </div>
</div>
</div>

<div>
  <iframe scrolling="no" src="footer.jsp" width=100% height=26 frameborder=0></iframe >
</div>
</body>
</html>
