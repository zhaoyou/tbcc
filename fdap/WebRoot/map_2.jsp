<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="js/common.js"></script>
<title>武汉市冷链监控</title>
<link rel="Shortcut Icon" href="images/logo.ico" />
 <script type='text/javascript' src='dwr/interface/realcar.js'></script>
 <script type='text/javascript' src='dwr/engine.js'></script>
 <script type='text/javascript' src='dwr/util.js'></script>
 
 
<script type="text/javascript"> 
			
			function goOrg(oid){
					document.getElementById('oid').value = oid ;
					document.myform.submit() ;
				}
				
				
				function goHighOrg(oid){
					document.getElementById('ope').value = "toHigherOrg" ;
					document.getElementById('oid').value = oid ;
					document.myform.submit() ;
				}
				
				
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
<link href="css/index/map_2.css" rel="stylesheet" type="text/css" />
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
          <td width="1145" colspan="7"><span style="text-align:left; float:left; padding-left:8px; margin-left:4px; color:#141414;"> 
          <img src=images/icon.gif width="9" height="10" /> 当前位置：湖北省域图</span>
        
           <span id="errorTip" style="color: red;display: none" >获取报警数据失败 ...</span> 
       <span style="display: ${isshowmap==0?'none':'inline' }"><a href="org.do?ope=toChangeDisplay&oid=${oid }&showmap=1" style="text-decoration: none;">列表显示</a></span>
      <span style="display:  ${loginUser.fdaporg.oid==oid?'none':'inline'}">
          <a href="javascript:goHighOrg('${oid }')"><img src="images/index/back.gif" width="58" height="21"   class="pho"/></a>
          </span>
        </td>
        
        </tr>
      </table>
    </div>
    <div id="Logo">
      <div class="img_b" id="5_light" style="display: none" > </div>
      <div class="img_c" id="6_light" style="display: none" ></div>
      <table id="tb" width="986" border="0" cellspacing="0" cellpadding="0">
        <tr bgcolor="#f1f4f8">
          <td width="984" colspan="7" bgcolor="#FFFFFF"><img src="images/index/map.gif" alt="fdsaf" name="fasdf" width="800" height="777" hspace="25" vspace="25" border="0" usemap="#Map" id="fasdf" />
            <map name="Map" id="Map" ait="dd">
              <area shape="poly" coords="687,186,672,190,661,191,654,198,650,202,658,209,660,215,656,218,655,225,645,222,641,222,635,225,632,220,629,217,625,214,624,213,623,205,617,200,614,201,612,206,611,211,608,216,604,216,598,216,591,213,595,210,593,206,589,206,587,207,579,209,575,209,568,211,566,212,565,215,566,219,567,224,569,227,570,231,563,232,558,229,556,229,548,235,540,237,534,240,523,245,519,250,519,263,516,272,511,286,508,295,501,302,483,309,471,315,459,321,461,329,464,338,459,343,462,353,469,370,475,378,486,386,498,403,501,414,507,425,513,432,519,436,528,441,535,442,545,438,558,433,572,428,585,425,596,414,612,410,638,403,650,402,657,394,641,382,632,378,631,366,623,355,637,344,647,338,665,326,672,305,679,307,687,311,692,309,695,305,701,304,705,313,712,313,712,297,719,290,723,297,726,294,729,291,733,285,728,278,729,266,739,263,749,264,753,272,756,267,761,268,766,261,774,255,746,235,738,234,726,224,722,216,725,211,721,204,721,196,717,181,711,201" 
              href="javascript:goOrg('6');"  />
              <area shape="poly" coords="307,27,314,16,322,9,328,6,336,14,343,16,348,22,351,24,355,28,358,27,366,24,371,27,378,31,388,30,392,29,402,31,412,32,416,34,409,42,409,48,407,55,402,60,397,65,397,70,399,78,407,85,415,89,424,90,426,96,432,98,434,101,428,104,433,109,441,115,447,119,455,121,463,127,466,134,470,139,467,144,468,150,466,160,459,170,456,177,454,184,459,196,467,197,476,202,481,208,488,213,497,217,510,223,511,232,517,241,517,252,516,264,512,278,509,286,503,301,482,309,473,313,465,315,458,319,455,325,457,332,460,336,456,341,455,345,459,351,461,358,466,368,472,377,463,381,449,377,436,375,421,377,408,381,402,374,394,372,385,375,377,381,371,383,366,381,359,378,355,386,349,382,343,379,336,382,332,385,326,391,320,387,313,382,300,377,291,371,292,347,284,339,281,322,286,309,281,299,277,286,273,273,274,251,272,236,282,224,281,217,275,208,277,202,271,193,275,188,274,182,287,172,284,163,284,154,283,144,294,135,301,127,306,121,313,112,311,97,304,93,297,93,289,83,284,75,281,67,289,59,288,52,289,44,294,38,304,30,306,26,309,20,312,18,318,9" 
              href="javascript:goOrg('5');" />
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
            </map></td>
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
