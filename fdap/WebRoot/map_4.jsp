<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 

 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="text/javascript" src="js/common.js"></script>
<title>荆门区冷链监控</title>
<link rel="Shortcut Icon" href="images/logo.ico" />
 <script type='text/javascript' src='dwr/interface/realcar.js'></script>
 <script type='text/javascript' src='dwr/engine.js'></script>
 <script type='text/javascript' src='dwr/util.js'></script>
<link rel="Shortcut Icon" href="images/logo.ico" />

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
						//ûбʾ
						 document.getElementById('tipDiv').innerHTML = "" ;
						 hideDiv() ;
					}else{
							hideDiv() ;
							var ta = "";
							for(var i =0;i<result.length;i++){
							
								if(result[i].oid==5){
									 document.getElementById('5_light').style.display = "inline" ;						
									 ta += buildTable("",result[i].msg) ;							
								}else if(result[i].oid==6){
									 document.getElementById('6_light').style.display = "inline" ;
									  ta += buildTable("",result[i].msg) ;
								}
								
							}
							document.getElementById('tipDiv').innerHTML = ta ;
								
					}		
				}
				
				function hideDiv(){
						document.getElementById('6_light').style.display = "none" ;
						  document.getElementById('5_light').style.display = "none" ;		
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
<link href="css/index/map_4.css" rel="stylesheet" type="text/css" />
</head>
<body onload="init();" >
<div id="Layer2">
  <marquee behavior="scroll" direction="up" loop="-1" height="110px" scrollamount="2" truespeed="truespeed" onmouseover="this.stop();" onmouseout="this.start();"   >
  	<div id="tipDiv"></div>
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
          <td width="1145" colspan="7"><span style="text-align:left; float:left; padding-left:8px; margin-left:4px; color:#141414;"> <img src=images/icon.gif width="9" height="10" /> 当前位置：荆门市区域图</span><a href="map2.html"><img src="images/index/back.gif" width="58" height="21" 
        class="pho"/></a> <span id="errorTip" style="color: red;display: none" >获取报警数据失败 ...</span>
        
   <span style="display:  ${loginUser.fdaporg.oid==oid?'none':'inline'}">
          <a href="javascript:goHighOrg('${oid }')"><img src="images/index/back.gif" width="58" height="21"   class="pho"/></a>
          </span>   
         </td>
      
        </tr>
      </table>
    </div>
    <div id="Logo">
      <div class="img_b" id="7_light" style="display: none" ></div>
      <div class="img_c" id="8_light" style="display: none" ></div>
      <table id="tb" width="986" border="0" cellspacing="0" cellpadding="0">
        <tr bgcolor="#f1f4f8">
          <td width="984" colspan="7" bgcolor="#FFFFFF"><img src="images/index/jm.jpg" alt="fdsaf" name="fasdf" width="541" height="466" hspace="25" vspace="25" border="0" usemap="#Map" id="fasdf" />
            <map name="Map" id="Map" ait="dd">
              <area shape="poly" coords="675,360,674,344,675,322,680,314,676,306,688,297,692,304,695,313,697,321,701,332,704,334,708,342,719,345,728,343,737,340,747,339,751,336,760,347,753,356,743,361,737,370,737,374,736,383,726,383,719,385,713,394,713,405,714,416,714,424,710,431,709,439,710,450,707,455,694,452,687,451,679,445,668,433,658,421,652,418,644,421,641,431,631,415,629,408,649,375,657,368,673,363" href="#" />
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
              <area shape="poly" coords="117,89,116,96,114,100,116,104,115,110,117,114,120,112,121,117,122,122,127,129,133,126,137,121,135,126,138,131,142,135,145,138,149,143,154,142,152,149,153,154,158,159,162,160,165,163,168,169,163,170,159,174,154,179,153,186,155,192,155,199,157,205,161,205,164,202,167,200,167,206,168,210,170,216,173,218,167,220,165,223,162,226,149,228,142,224,151,214,147,204,142,200,137,199,136,203,134,209,130,211,129,212,126,215,123,213,117,209,112,207,106,207,102,210,105,214,104,218,106,224,108,228,107,235,109,238,111,244,113,250,116,255,109,261,103,264,98,268,97,272,92,275,86,269,85,249,82,242,81,233,77,225,70,216,70,208,58,204,54,198,71,193,68,186,62,177,62,168,59,162,57,158,49,160,44,151,50,143,65,141,63,135,61,124,62,117,62,111,63,103,68,96,65,88,70,83,77,80,87,83,99,84,104,79,109,76,115,77"
               href="javascript:goOrg('7');"/>
              <area shape="poly" coords="331,151,325,146,321,142,318,137,319,130,322,124,324,123,330,124,332,120,332,117,337,115,346,113,351,107,349,102,347,91,347,86,350,84,354,83,358,85,364,87,368,83,370,82,376,87,383,87,387,85,387,91,394,95,402,98,406,102,417,108,421,113,428,116,433,119,437,120,444,128,449,133,456,129,460,133,461,140,465,146,470,149,476,155,478,163,478,173,478,181,475,189,474,199,472,207,467,210,461,216,457,222,451,222,451,229,450,234,445,241,440,245,439,252,438,256,428,253,419,253,414,260,409,265,406,269,402,275,399,280,394,279,385,284,380,280,372,277,366,279,366,285,365,290,356,290,351,290,347,290,342,296,335,303,328,307,325,315,320,312,317,318,310,312,302,300,302,288,294,271,286,258,282,250,280,234,281,226,299,215,309,205,312,199,312,189,310,182" 
              href="javascript:goOrg('8');"  />
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
