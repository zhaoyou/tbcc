<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>机构地图信息页面</title>
    <link rel="Shortcut Icon" href="../images/logo.ico" />
	<script type="text/javascript" src="../js/admin/script.js""></script>
	<script type="text/javascript" src="../js/common.js"></script>
	
	<script type="text/javascript">
		function fuzhi(){
			var str = document.getElementById("str");
			str.select();
			var Strs = str.value.replace(/[@]/g,"\r\n");
			copyToClipboard(Strs);
			alert("复制成功！");
			//alert(str.value);
		}
		
		function copyToClipboard(txt) {
			//alert(window.clipboardData);
		     if(window.clipboardData) {
		             window.clipboardData.clearData();
		             window.clipboardData.setData("Text", txt);
		     } else if(navigator.userAgent.indexOf("Opera") != -1) {
		          window.location = txt;
		     } else if (window.netscape) {
		          try {
		               netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
		          } catch(e){
		               alert("被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将'signed.applets.codebase_principal_support'设置为'true'");
		          }
		          var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
		          if (!clip)
		               return;
		          var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
		          if (!trans)
		               return;
		          trans.addDataFlavor('text/unicode');
		          var str = new Object();
		          var len = new Object();
		          var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
		          var copytext = txt;
		          str.data = copytext;
		          trans.setTransferData("text/unicode",str,copytext.length*2);
		          var clipid = Components.interfaces.nsIClipboard;
		          if (!clip)
		               return;
		          clip.setData(trans,null,clipid.kGlobalClipboard);
		          
	     	}
		}
		
		
		
		function createTable() {
	       var t = document.createElement('table');
	       var b = document.createElement('tbody');
	       var str = document.getElementById("str").value;
	       var sz = str.split(/[@]/g);
	       if(sz.length==0){
	       		document.getElementById('tab').innerHTML="<font color='red'>还没有配置机构</font>";
	       		return;
	       }
	       var sz1 = null;
	       //alert(sz.length+" "+sz[1].split("\t").length);
	       //alert(str);
	       for (var i = 0; i < sz.length; i++) {
	        var r = document.createElement('tr');
	        sz1 = sz[i].split("\t");
	        for (var j = 0; j < sz1.length; j++) {
	         var c = document.createElement('td');
	         var m = document.createTextNode(sz1[j]);
	          c.appendChild(m);
	          r.appendChild(c);
	        }
	        b.appendChild(r);
	       }
	       t.appendChild(b);
	       document.getElementById('tab').appendChild(t);
	      t.setAttribute('border', '1');
	   }
	</script>
	
	<style type="text/css">
		*{
			margin: auto;
			padding-top: 1px;
			padding-left: auto;
			padding-bottom: 1px;
			padding-right: auto;
		}
		body {
			text-align: center;
			font-family: "宋体";
		}
		#head {
			width:1024px;
			margin-right: auto;
			margin-left: auto;
			margin-top: 0px;
			margin-bottom: 0px;
			height: 99px;
		}
		#head #banner {
			height:99px;
			background-image: url(../images/admin/head/head4.gif);
			background-repeat: no-repeat;
			position: relative;
		}
		
		#content {
			width:1024px;
			margin-top: 0px;
			margin-right: auto;
			margin-bottom: 0px;
			margin-left: auto;
			height: 480px;
			background-image: url(../images/admin/index/line01.gif);
			background-repeat: repeat-x;
			background-position: 0px 0px;
			padding-top: 2px;
			background-color: #d7eefe;;
			z-index:-9999px;
			float: left;
		}
		
		.menu {
			font-family: "宋体";
			width:1024px;
			font-size: 12px;
			margin-top: 0px;
			margin-right: 0;
			margin-bottom: 0px;
			margin-left: 0;
			background-image: url(../images/admin/head/bg_head03.jpg);
			background-repeat: repeat-x;
			height: 30px;
			padding: 0px;
			font-weight: normal;
			position:relative;
		}
		.menu ul {
			padding:0;
			margin:0;
			list-style-type: none;
		}
		
		#content #center .bottom {
			height: 420px;
			background-repeat: repeat-x;
			padding-top: auto;
			padding-bottom: auto;
			margin-top: 0px;
			margin-bottom: 0px;
			padding-right: 10px;
			padding-left: 10px;
			background-image: url(../images/admin/index/line04.gif);
			margin-right: auto;
			margin-left: auto;
		}
		
		#content #center {
			height: auto;
			width: 1006px;
			margin-top: 10px;
			margin-right: auto;
			margin-bottom: 10px;
			margin-left: auto;
			border: 1px solid #0892d1;
			padding-top: 5px;
			background-color: #FFF;
			background-repeat: repeat-x;
			background-position: 0px 0px;
			background-image: url(../images/admin/index/line04.gif);
		}
		
		#content #center #tb1 {
			line-height:26px;
			text-align:center;
			border:1px solid #096;
			background-image: url(../images/admin/index/but_b.gif);
			background-repeat: repeat-x;
			height: 26px;
			background-position: 0px 0px;
		}
		#content #center #tb {
			width: 980px;
			margin-top: 0px;
			margin-right: auto;
			margin-bottom: 0px;
			margin-left: auto;
			text-align: center;
		}
		#content #center #tb tr {
			line-height: 23px;
			height: 23px;
		}
		#content #center #tb #tb1 {
			line-height:24px;
			text-align:center;
			border:1px solid #096;
			background-image: url(../images/admin/index/8_17.gif);
			background-repeat: repeat-x;
			height: 24px;
			background-position: 0px 0px;
		}
		#content #center #tb, td {
			border:1px solid #b6d6e6;
			border-collapse:collapse;
			text-align: center;
			margin-top: 0px;
			margin-right: auto;
			margin-left: auto;
			margin-bottom: 5px;
			line-height: 22px;
		}
	</style>
  </head>
  
  <body onload="showInfo();createTable();" >
<div id="head">
  <div id="banner">
    
  </div>
 </div>
 
 	<div style="padding:0; margin:0; width:1024px; margin:0 auto;">
 		<div class="menu">
		      <ul>
		        <li style="float:right; height:28px; line-height:28px; margin-right:14px;color: #3a434a;"> <span id="time"></span> </li>
		      </ul>
		      <!-- clear the floats if required -->
		      <div class="clear"> </div>
		    </div>
  		<div id="content">
		    <div id="center" >
		    
		    
		    <div class="top" style="display:block;">
		      <table id="tb" border="0" cellspacing="0" cellpadding="0" >
		        <tr id="tb1" >
		          <td colspan="7"><span style="text-align:left; float:left;padding-left:4px; margin-left:4px; color:#141414;"><img src=../images/admin/index/icon.gif width="9" height="10" /> 你当前位置为： 机构地图信息</span></td>
		        </tr>
		      </table>
		    </div>
		    
		    
 	<div class="bottom">
 
  	
  	<br />
  	<input id="str" type="hidden" value="${Str }" />
  	<br />
  	<div id="tab" style="display:block;">
	  
  	</div>
  	每一行对应一个地图页面，以“map_编号”命名 。例如：编号为 1 的湖北省药监局就是:map_1.html。
  	<br />
    <br />
    <input type="button" value="复制到excel" onclick="fuzhi()"/>
    
    	</div>
    </div>
    </div>
    	  <div>
		    <iframe scrolling="no" src="footer.jsp" width=100% height=26 frameborder=0>6</iframe >
		  </div>
	</div>
  </body>
</html>
