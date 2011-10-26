<%@ page language="java"  pageEncoding="utf-8"%>
<%@include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>页头</title>
<link href="css/head/head.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="images/logo.ico" />
<script type="text/javascript" src="js/common.js"></script>
	<style type="text/css"> 
		body,dl,dt,dd {margin:0;padding:0;}
		body {color:#FFF; font:12px "宋体";}
		ul{list-style-type: none;}
		#menu .nav_b {
		}
		a {color:#FFF; text-decoration:none;}
		#menu {
			width:1024px;
			padding-top:99px;
			margin-top: 0;
			margin-right: auto;
			margin-bottom: 0;
			margin-left: auto;
			background-image: url(images/head/head5.gif);
			background-repeat: no-repeat;
			background-position: 0 0;
			position: relative;
		 }
		#menu dl {
			text-align:center;
			position:relative;
			float:left;
			height:auto;
		}
		#menu div dl dt a {
			font-weight: bold;
			color: #333;
		}
		#menu p {
			position: absolute;
			left: 873px;
			top: 5px;
			background-image: url(images/head/icon_c.gif);
			background-repeat: no-repeat;
			background-position: 0px -1px;
			width: 111px;
			height: 16px;
		}
		#menu p span {
			margin-left: 25px;
			margin-top: 5px;
		}
		#menu img {
			display: block;
			height: 24px;
			width: 91px;
			position: absolute;
			left: 915px;
			top: 75px;
			cursor:pointer;
		}
		#menu dl dt {width:83px;}
		#menu dl dt a {
			padding:10px 0 4px 0;
			height:16px;
			display:block;
		}
		#menu dl dt a:hover {background-color:#F90;}
		#menu dl dd {width:83px; position:absolute; display:none; overflow:hidden;}
		#menu dl dd a{
			display:block;
			height:25px;
			line-height:25px;
			background-color:#333;
			filter:alpha(opacity=70);
			opacity: 0.7;
		}
		#menu dl dd a:hover {background-color:#222; filter:alpha(opacity=40);opacity: 0.4;}
		#menu dl dd a span,
		#menu dl dd a:hover span {position:relative;}
		#menu dl {
		}
	</style>
</head>
<body onload="showtime();">
		<div id="menu">
		  <p><span>欢迎您:${loginUser.name }</span></p>
		  <a href="user.do?ope=doLogout" target="_parent"><img src="images/head/z.gif" width="91" height="24" /></a>
		  <div style="height:30px; background-image:url(images/head/bg_head.jpg)">
		  <%-- <logic:notEmpty name="linkList">
		    	<dl>
		    		<dt><a href="javascript:void(0);">链接</a></dt>
		        	<dd>
		        		<c:forEach var="link" items="${linkList}" varStatus="irow">
				        	${irow.index>=4&&irow.index%4==0?"</dd></dl><dl><dt><a href='#'>链接</a></dt><dd>":"" }
				        	<A href="http://${link.url }" target="_blank"><span>${link.name }</span></A>
				        </c:forEach>       
		       	 	</dd>
				</dl>
			</logic:notEmpty>--%>
			
			<logic:notEmpty name="alltypeList">
				<c:forEach var="type" items="${alltypeList}" varStatus="irow">
			    	<dl>
			    		<dt><a href="javascript:void(0);">${type.ltname }</a></dt>
			        	<dd>
			        		<c:forEach var="link" items="${alllinkList}" varStatus="iirow">
			        			<c:if test="${type.ltid==link.ltid}">
					        		<A href="http://${link.url }" target="_blank"><span>${link.name }</span></A>
					        	</c:if>
					        </c:forEach>       
			       	 	</dd>
					</dl>
				</c:forEach>
			</logic:notEmpty>
			
			
			<dl style="float:right;">
		        <dt style="width:240px; height:30px; line-height:30px;"><font style="font-weight:normal; color:#333;"><span id="time"></span></font></dt>
		         
			</dl>
		</div>
		</div>
	<script>
		function ShowMenu(obj,d){
		        var dt = obj.parentNode;
		        var dd = dt.getElementsByTagName("dd")[0];
		        clearInterval(dd.timer);
		        if(d == 1){
		                clearTimeout(dt.timer);
		                if(dd.maxh && dd.maxh == dd.offsetHeight){
		                        return;
		                }else if(!dd.maxh){
		                        dd.style.top = '-' + dd.offsetHeight + 'px';
		                        dd.style.display = "block";
		                        dd.maxh = dd.offsetHeight;
		                        dd.style.height = '0px';
		                }
		                dd.timer = setInterval(function(){ddSlide(dd,1)},15);
		        }else{
		                dt.timer = setTimeout(function(){ddCollapse(dd)},50);
		        }        
		}
		function ddCollapse(c){c.timer = setInterval(function(){ddSlide(c,-1)},15);}
		function cancelHide(obj){
		        var dt = obj.parentNode;
		        var dd = dt.getElementsByTagName("dd")[0];
		        clearTimeout(dt.timer);
		        clearInterval(dd.timer);
		        if(dd.offsetHeight != dd.maxh){dd.timer = setInterval(function(){ddSlide(dd,1)},15);}
		}
		function ddSlide(c,d){
		        var currh = c.offsetHeight;
		        var h = (d!=1?(Math.ceil(currh/10)):(Math.ceil((c.maxh - currh)/10)));
		        var t = parseInt(c.style.top);
		        if(h < 0 && d == 1){h = 1;}
		        c.style.top = t - parseInt(h * d) + 'px';
		        c.style.height = currh + (h * d) + 'px';
		        if((currh == 0 && d == -1) || (currh == c.maxh && d == 1))clearInterval(c.timer);
		}
		for(var _dl = document.getElementById("menu").getElementsByTagName("dl"),i=-1,dl;dl=_dl[++i];){
		        var dt = dl.getElementsByTagName("dt")[0];
		        var dd = dl.getElementsByTagName("dd")[0];
		        dt.onmouseover= function(){return ShowMenu(this,1);}
		        dt.onmouseout = dd.onmouseout = function(){return ShowMenu(this,-1);}
		        dd.onmouseover= function(){return cancelHide(this);}
		}
		</script>
  </body>
</html>
