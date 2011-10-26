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
		function goOrg(oid){
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
					str +="<td align='left' bgcolor='#99CCFF'><div>"+n+"发生报警: </div></td>" ;
					str +=" </tr>" ;
					str +="<tr><td ><font color='red'>"+tip+"</font></td></tr></table>" ;
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
	left: 15px;
	top: 40px;
}
-->
</style>
<link href="css/index/map3.css" rel="stylesheet" type="text/css" />
</head>
<body onload="init();">
<form action="org.do" method="post" name="myform" id="myform">
<div>
  <iframe scrolling="no" src="head.jsp" width=100% height=128 frameborder=0></iframe >
</div>

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
          <img src=images/icon.gif width="9" height="10" /> 当前位置：湖北省冷链监控</span> 
          <span id="errorTip" style="color: red;display: none" >获取报警数据失败 ...</span>
          </td>
        </tr>
      </table>
    </div>
    <table id="tb" width="90%" border="0" cellspacing="0" cellpadding="0">
      <tr bgcolor="#f1f4f8">
        <td colspan="7"><div id="Logo">
            <div class="img_b" id="4_light" style="display: none" ></div>
            <div class="img_c" id="2_light"  style="display: none"></div>
            <div class="img_a" id="3_light"  style="display: none"></div>
            <img src="images/index/u13.jpg" alt="" name="fasdf" width="962" height="637" hspace="25" vspace="25" border="0" usemap="#Map" id="fasdf" />
            <map name="Map" id="Map" ait="dd">
              <area shape="poly" coords="505,26" href="#" />
              <area shape="poly" coords="636,285" href="#" />
              <area shape="poly" coords="275,124" href="#" />
              <area shape="poly" coords="341,18" href="#" />
              <area shape="poly" coords="315,4" href="#" />
              <area shape="poly" coords="281,46" href="#" />
              <area shape="poly" coords="115,395" href="#" />
              <area shape="poly" coords="322,2" href="#" />
              <area shape="poly" coords="349,23" href="#" />
              <area shape="poly" coords="301,392" href="#" />
              <area shape="poly" coords="456,332,434,327" href="#" />
              <area shape="poly" coords="386,492" href="#" target="_self" alt="  " />
              <area shape="poly" coords="392,51" href="#" />
              <area shape="poly"     coords="514,276,521,274,527,272,532,275,535,278,539,279,544,279,546,273,548,269,553,268,560,273,566,282,575,285,584,286,593,293,601,297,606,299,613,303,617,308,622,312,622,317,621,322,621,329,619,337,614,342,609,347,603,350,598,355,594,360,589,361,583,361,579,364,573,367,569,372,562,370,557,358,551,360,543,363,541,367,537,371,536,376,536,382,535,389,531,397,526,407,521,407,517,408,511,407,509,400,504,394,498,398,494,399,490,393,487,385,485,378,483,372,481,363,477,355,477,345,474,337,469,332,469,323,466,312,469,308,470,296,475,287,497,279,508,276,503,274,501,277"
              href="javascript:goOrg('3');" />
              <area shape="poly" 
             coords="711,295,715,297,717,302,719,309,724,313,727,317,729,321,729,326,729,331,731,337,733,341,737,344,741,345,746,345,751,344,756,340,760,342,765,341,768,336,772,338,777,339,781,343,785,349,780,352,777,356,773,359,767,361,762,365,760,369,762,374,762,378,759,383,753,384,745,386,740,389,738,395,738,404,739,413,740,418,738,423,734,428,731,434,731,441,733,448,733,455,725,455,717,455,708,455,703,449,699,442,695,436,690,431,686,427,681,422,675,420,668,423,666,427,662,435,660,426,659,420,654,412,652,406,663,395,668,389,670,380,670,369,678,365,684,365,690,365,697,364,698,356,698,346,697,336,697,327,704,317,705,312,700,310,698,307,698,301,703,298,708,295" 
              href="javascript:goOrg('2');" />
              <area shape="poly"
              coords="469,401,470,406,474,411,476,415,475,419,481,424,480,430,474,431,468,426,466,424,464,428,458,429,456,427,454,422,449,418,447,419,442,423,435,428,433,436,431,444,427,449,420,447,413,447,407,450,402,455,400,459,410,465,418,469,429,473,444,472,456,473,466,478,473,483,479,489,484,497,492,502,500,505,506,510,509,514,515,509,520,505,526,505,533,506,539,506,545,505,548,503,551,500,555,497,562,492,566,489,571,492,574,499,574,506,576,514,579,522,585,525,593,523,598,518,603,514,606,509,610,504,617,497,620,494,625,488,628,482,632,477,641,470,649,467,656,468,662,464,663,456,667,455,680,450,686,448,679,439,668,437,659,443,649,446,634,448,628,446,620,442,617,438,609,440,599,441,589,441,580,437,569,438,566,442,564,447,558,451,550,452,542,451,534,446,532,440,530,432,528,427,525,424,525,417,521,412,512,412,507,413,505,410,504,406,502,403,496,402,490,402,486,396,484,390,482,382,477,383,473,390,471,399" 
               href="javascript:goOrg('4');" />
            </map>
            <div id="Layer2" class="location_a">
              <marquee behavior="scroll" direction="up" loop="-1" height="110px" scrollamount="2" truespeed="truespeed" onmouseover="this.stop();" onmouseout="this.start();"   >
             		<div id="tipDiv">
             		
             		<%--
              <table width="160" border="0">
                <tr>
                  <td align="left" bgcolor="#99CCFF"><div>东西湖区:</div></td>
                </tr>
                <tr>
                  <td>G企业 鄂10001 T1 温度超高 </td>
                </tr>
                <tr>
                  <td align="left">G企业 F1S1 T2 湿度超高</td>
                </tr>
              </table>
             		 --%>
             		
              </div>
              </marquee>
            </div>
          </div></td>
      </tr>
      <tr>
        <td colspan="7"><table width="100%" border="0" cellpadding="0" cellspacing="0" >
            <tr class="altrow_a">
              <td width="13%" style="border: none;">&nbsp;</td>
              <td width="7%" style="border: none;">&nbsp;</td>
              <td width="21%" style="border: none;"><a href="javascript:window.alert('暂未开通');"><img src="images/index/but_ee.gif" width="100" height="24" /></a></td>
              <td width="17%" style="border: none;"><a href="javascript:window.alert('暂未开通');"><img src="images/index/but_ff.gif" width="100" height="24" /></a></td>
              <td width="21%" style="border: none;"><a href="javascript:window.alert('暂未开通');"><img src="images/index/but_c.gif" width="100" height="24" /></a></td>
              <td width="8%" style="border: none;">&nbsp;</td>
              <td width="13%" style="border: none;">&nbsp;</td>
            </tr>
          </table></td>
      </tr>
    </table>
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
