<%@ page language="java"  pageEncoding="utf-8"%>

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
			//跳转页面
			function goOrg(oid){
					document.getElementById('oid').value = oid ;
					document.myform.submit() ;
				}
				
				//返回上级页面
				function goHighOrg(oid){
					document.getElementById('ope').value = "toHigherOrg" ;
					document.getElementById('oid').value = oid ;
					document.myform.submit() ;
				}
				
				//页面初始化
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
					str +="<td align='left' bgcolor='#99CCFF'><div>"+n+"发生报警:</div></td>" ;
					str +=" </tr>" ;
					str +="<tr><td><font color='red'>"+tip+"</font></td></tr></table>" ;
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
<link href="css/index/map1.css" rel="stylesheet" type="text/css" />
</head>
<body onload="init();">
<div id="Layer2">
  <marquee behavior="scroll" direction="up" loop="-1" height="110px" scrollamount="2" truespeed="truespeed" onmouseover="this.stop();" onmouseout="this.start();"   >
  	<div id="tipDiv">
  	<%--
  			<table width="160" border="0" >
    <tr>
      <td align="left" bgcolor="#99CCFF"><div>黄陵区:</div></td>
    </tr>
    <tr>
      <td>A企业 F1C2 T1 温度超高 </td>
    </tr>
    <tr>
      <td>A企业 F2S1 RH1 湿度超低</td>
    </tr>
  </table>
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
          <img src=images/icon.gif width="9" height="10" /> 当前位置：武汉市冷链监控 </span>
          
           <span id="errorTip" style="color: red;display: none" >获取报警数据失败 ...</span>
           
          <span style="display:  ${loginUser.fdaporg.oid==oid?'none':'inline'}">
          <a href="javascript:goHighOrg('${oid }')"><img src="images/index/back.gif" width="58" height="21"   class="pho"/></a>
          </span>
        </td>
        </tr>
      </table>
    </div>
    <div id="Logo">
      <div class="img_a" id="5_light" style="display: none" ></div>
      <div class="img_b" id="6_light" style="display: none"></div>
      <table id="tb" width="986" border="0" cellspacing="0" cellpadding="0">
        <tr bgcolor="#f1f4f8">
          <td width="984" colspan="7" bgcolor="#FFFFFF"><img src="images/index/map.gif" alt="fdsaf" name="fasdf" width="800" height="777" hspace="25" vspace="25" border="0" usemap="#Map" id="fasdf" />
            <map name="Map" id="Map" ait="dd">
              <area shape="poly" coords="402,59" href="#" />
              <area shape="poly" coords="396,564" href="#" target="_self" alt="  " />
              <area shape="poly" coords="467,380,445,375" href="#" />
              <area shape="poly" coords="309,449" href="#" />
              <area shape="poly" coords="358,27" href="#" />
              <area shape="poly" coords="330,3" href="#" />
              <area shape="poly" coords="118,453" href="#" />
              <area shape="poly" coords="288,53" href="#" />
              <area shape="poly" coords="323,5" href="#" />
              <area shape="poly" coords="350,21" href="#" />
              <area shape="poly" coords="282,143" href="#" />
              <area shape="poly" coords="652,327" href="#" />
              <area shape="poly" coords="687,186,672,190,661,191,654,198,650,202,658,209,660,215,656,218,655,225,645,222,641,222,635,225,632,220,629,217,625,214,624,213,623,205,617,200,614,201,612,206,611,211,608,216,604,216,598,216,591,213,595,210,593,206,589,206,587,207,579,209,575,209,568,211,566,212,565,215,566,219,567,224,569,227,570,231,563,232,558,229,556,229,548,235,540,237,534,240,523,245,519,250,519,263,516,272,511,286,508,295,501,302,483,309,471,315,459,321,461,329,464,338,459,343,462,353,469,370,475,378,486,386,498,403,501,414,507,425,513,432,519,436,528,441,535,442,545,438,558,433,572,428,585,425,596,414,612,410,638,403,650,402,657,394,641,382,632,378,631,366,623,355,637,344,647,338,665,326,672,305,679,307,687,311,692,309,695,305,701,304,705,313,712,313,712,297,719,290,723,297,726,294,729,291,733,285,728,278,729,266,739,263,749,264,753,272,756,267,761,268,766,261,774,255,746,235,738,234,726,224,722,216,725,211,721,204,721,196,717,181,711,201"
               href="javascript:goOrg('6');" target="_self" />
              <area shape="poly" coords="307,26,314,15,322,8,328,5,336,13,343,15,348,21,351,23,355,27,358,26,366,23,371,26,378,30,388,29,392,28,402,30,412,31,416,33,409,41,409,47,407,54,402,59,397,64,397,69,399,77,407,84,415,88,424,89,426,95,432,97,434,100,428,103,433,108,441,114,447,118,455,120,463,126,466,133,470,138,467,143,468,149,466,159,459,169,456,176,454,183,459,195,467,196,476,201,481,207,488,212,497,216,510,222,511,231,517,240,517,251,516,263,512,277,509,285,503,300,482,308,473,312,465,314,458,318,455,324,457,331,460,335,456,340,455,344,459,350,461,357,466,367,472,376,463,380,449,376,436,374,421,376,408,380,402,373,394,371,385,374,377,380,371,382,366,380,359,377,355,385,349,381,343,378,336,381,332,384,326,390,320,386,313,381,300,376,291,370,292,346,284,338,281,321,286,308,281,298,277,285,273,272,274,250,272,235,282,223,281,216,275,207,277,201,271,192,275,187,274,181,287,171,284,162,284,153,283,143,294,134,301,126,306,120,313,111,311,96,304,92,297,92,289,82,284,74,281,66,289,58,288,51,289,43,294,37,304,29,306,25,309,19,312,17,318,8"
               href="javascript:goOrg('5');" />
              <area shape="poly" coords="518,30" href="#" />
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
