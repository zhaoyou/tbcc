 <%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="js/common.js"></script>
<title>湖北省冷链监控</title>
<link rel="Shortcut Icon" href="images/logo.ico" />
 <script type='text/javascript' src='dwr/interface/realcar.js'></script>
 <script type='text/javascript' src='dwr/engine.js'></script>
 <script type='text/javascript' src='dwr/util.js'></script>
<script type="text/javascript"> 
			//תҳ
			function goOrg(oid){
					document.getElementById('oid').value = oid ;
					document.myform.submit() ;
				}
				
				//ϼҳ
				function goHighOrg(oid){
					document.getElementById('ope').value = "toHigherOrg" ;
					document.getElementById('oid').value = oid ;
					document.myform.submit() ;
				}
				
				//ҳʼ
				function init(){
					var id = 	document.getElementById('sourceId').value  ;
							if(id==""){
								return ;
							}
							
					realcar.getAlarmTip(id,{
						callback:resultHandler,
						errorHandler:errorHandler,
						timeout:10000
					});
					
					window.setInterval(function (){
						realcar.getAlarmTip(id,{
						callback:resultHandler,
						errorHandler:errorHandler,
						timeout:10000
					});
					},10000);
							
				}
				
					function resultHandler(result){
					document.getElementById('errorTip').style.display = "none" ;
					
					if(result==null || result.length==0){
						//如果没有报警、则不提示
						 document.getElementById('tipDiv').innerHTML = "" ;
						 hideDiv() ;
					}else{
							hideDiv() ;
							var ta = "";
							for(var i =0;i<result.length;i++){
							
									if(document.getElementById(result[i].oid+"_light")!=null){
										document.getElementById(result[i].oid+'_light').style.display = "inline" ;	
								}
								
								ta +=buildTable(result[i].orgName,result[i].msg);
								
							}
							document.getElementById('tipDiv').innerHTML = ta ;
								
					}		
				}
				
				function hideDiv(){
						var ids = document.getElementById("ids").value ;
					if(ids==null || ids==""){
						return ;
					}
					
					var idslist = ids.split(",");
					
					for(var i=0;i<idslist.length;i++){
						if(document.getElementById(idslist[i]+"_light")!=null){
							document.getElementById(idslist[i]+"_light").style.display = "none" ;
						}
					}	
				}
				
				function buildTable(n,tip){
					var str = "<table width='160'  border='0'>" ;
					str +="<tr>" ;
					str +="<td align='left' bgcolor='#99CCFF'><div>"+n+"</div></td>" ;
					str +=" </tr>" ;
					str +="<tr><td>"+tip+"</td></tr></table>" ;
					return str ;
				}
				
				function errorHandler(err){
					document.getElementById('errorTip').style.display = "inline" ;
				}
				
				
				
</script>
<style type="text/css">
<!--
#Layer2 {
	position:absolute;
	width:163px;
	height:110px;
	z-index:3;
	display:block;
	padding-top: 204px;
	padding-left: 65px;
}
-->
</style>
<link href="css/index/map_1.css" rel="stylesheet" type="text/css" />
</head>
<body onload="init();" >
<div id="Layer2">
  <marquee behavior="scroll" direction="up" loop="-1" height="110px" scrollamount="2" truespeed="truespeed" onmouseover="this.stop();" onmouseout="this.start();"   >
  <div id="tipDiv">
  
  </div>
  </marquee>
</div>
<div>
  <iframe scrolling="no" src="head.jsp" width=100% height=128 frameborder=0></iframe >
</div>
<form action="org.do" method="post" name="myform" id="myform">
  <input type="hidden" name="ope" id="ope" value="toOrg"/>
  <input type="hidden" name="sourceId" id="sourceId" value="${oid }"/>
  <input type="hidden" name="oid" id="oid" value=""/>
  <input type="hidden" name="ids" id="ids" value="${ids }"/>
</form>
<div id="content">
  <div id="center">
    <div class="top" style="position:relative; display:block;">
      <table id="tb" width="90%" border="0" cellspacing="0" cellpadding="0" style="position:relative;" >
        <tr id="tb1">
          <td width="1145" colspan="7"><span style="text-align:left; float:left; padding-left:8px; margin-left:4px; color:#141414;">
           <img src=images/icon.gif width="9" height="10" /> 当前位置：湖北省区域图</span>
        
           <span id="errorTip" style="color: red;display: none" >获取报警数据失败 ...</span>
        <span style="display: ${isshowmap==0?'none':'inline' }"><a href="org.do?ope=toChangeDisplay&oid=${oid }&showmap=1" style="text-decoration: none;">列表显示</a></span>
         <span style="display:  none;"> <a href="javascript:goHighOrg('2')"><img src="images/index/back.gif" width="58" height="21"   class="pho"/></a> </span></td>
        </tr>
      </table>
    </div>
    <div id="Logo">
      <div class="img_b" id="4_light" style="display: none"></div>
      <div class="img_c" id="2_light" style="display: none"></div>
      <table id="tb" width="986" border="0" cellspacing="0" cellpadding="0">
        <tr bgcolor="#f1f4f8">
          <td width="984" colspan="7" bgcolor="#FFFFFF"><img src="images/index/u13.jpg" alt="fdsaf" name="fasdf" width="962" height="637" hspace="25" vspace="25" border="0" usemap="#Map" id="fasdf" />
            <map name="Map" id="Map" ait="dd">
              <area shape="poly" coords="672,359,671,343,672,321,677,313,673,305,685,296,689,303,692,312,694,320,698,331,701,333,705,341,716,344,725,342,734,339,744,338,748,335,757,346,750,355,740,360,734,369,734,373,733,382,723,382,716,384,710,393,710,404,711,415,711,423,707,430,706,438,707,449,704,454,691,451,684,450,676,444,665,432,655,420,649,417,641,420,638,430,628,414,626,407,649,365,654,367,670,362"
               href="javascript:goOrg('2');"  />
              <area shape="poly" coords="497,30" href="#" />
              <area shape="poly" coords="626,327" href="#" />
              <area shape="poly" coords="271,143" href="#" />
              <area shape="poly" coords="336,21" href="#" />
              <area shape="poly" coords="310,5" href="#" />
              <area shape="poly" coords="276,53" href="#" />
              <area shape="poly" coords="113,453" href="#" />
              <area shape="poly" coords="317,3" href="#" />
              <area shape="poly" coords="344,27" href="#" />
              <area shape="poly" coords="296,449" href="#" />
              <area shape="poly" coords="448,380,427,375"
               href="#" />
              <area shape="poly" coords="380,564"
               href="#" target="_self" alt="  " />
              <area shape="poly" coords="386,59" href="#" />
              <area shape="poly" coords="506,272,513,276,520,280,527,276,527,270,535,268,540,276,542,282,549,286,555,286,560,287,567,291,573,298,582,301,587,304,593,311,598,319,598,330,594,337,589,343,584,346,580,351,574,356,572,361,563,361,555,361,551,364,550,368,544,370,541,373,535,361,531,360,526,360,521,361,518,365,518,370,516,376,514,380,516,383,514,390,511,396,506,403,505,411,500,408,493,409,489,403,485,397,479,397,476,400,472,397,468,386,465,376,463,368,461,364,460,359,458,354,458,347,457,342,454,337,452,333,451,323,451,317,449,310,452,305,452,296,457,288,463,286,467,286,470,286,476,284,479,282,481,279,483,277,485,275,494,275"
               href="javascript:goOrg('4');" />
            </map>
            </td>
        </tr>
        <tr>
          <td colspan="7"><table width="100%" border="0" cellpadding="0" cellspacing="0" >
              <tr class="altrow_a">
                <td width="13%" style="border: none;">&nbsp;</td>
                <td width="7%" style="border: none;">&nbsp;</td>
                <td width="21%" style="border: none;"><a href="javascript:window.alert('暂未开通');"><img src="images/index/but_ee.gif" width="100" height="24" /></a></td>
                <td width="17%" style="border: none;"><a href="javascript:window.alert('暂未开通');"><img src="images/index/but_ff.gif" width="100" height="24" /></a></td>
                <td width="21%" style="border: none;"><a href="javascript:window.alert('暂未开通');"><img src="images/index/but_c.gif" width="100" height="24" /></a></td>
                <td width="8%"   style="border: none;">&nbsp;</td>
                <td width="13%" style="border: none;">&nbsp;</td>
              </tr>
            </table></td>
        </tr>
      </table>
    </div>
  </div>
</div>
<div>
  <iframe scrolling="no" src="footer.jsp" width=100% height=22 frameborder=0></iframe >
</div>
<map name="Map" ait="dd">
  <area shape="poly" coords="605,412,621,409,635,405,647,403,657,398,652,388,645,382,633,382,631,367,621,354,621,348,630,343,656,335,662,324,667,309,670,305,676,305,684,311,694,307,700,305,710,311,710,303,717,299,718,292,725,293,728,293,732,285,732,280,730,272,738,265,748,265,754,271,756,268,763,269,757,263,770,259,778,253,767,249,756,244,745,238,738,232,728,227,722,221,723,214,722,205,721,200,720,193,715,178,698,197,690,193,686,186,673,190,663,190,655,194,644,223,634,223,626,219,619,208,615,199,608,200,604,211,598,215,595,207,594,202,580,205,571,209,563,211,564,221,560,229,552,226,537,235,520,240,516,253,514,275,508,287,506,298,479,311,458,319,460,331,461,339,458,342,472,374,478,383,487,393,502,418,517,438,530,441,568,432" href="#" />
</map>
</body>
</html>
