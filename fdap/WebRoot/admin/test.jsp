<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>测试树的路径</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="../js/admin/dtree.js"></script>
	<link rel="stylesheet" href="../css/admin/dtree.css" type="text/css"/>
	<script type="text/javascript">
			
			
				var d = new dTree('d') ;
				d.add(1,-1,'武汉药监局',"javascript:window.alert('top')") ;
				d.add(2,1,'黄陵区药监局',"javascript:window.alert('2')") ;
				d.add(3,1,'黄岗区药监局',"javascript:window.alert('3')") ;
				d.add(4,1,'新安区监局',"javascript:window.alert('4')") ;
				d.add(5,2,'A企业',"javascript:window.alert('5')") ;
				d.add(6,2,'B企业',"javascript:window.alert('6')") ;
				d.add(7,3,'C企业',"javascript:window.alert('7')") ;
				d.add(8,3,'D企业',"javascript:window.alert('8')") ;
				d.add(9,4,'E企业',"javascript:window.alert('9')") ;
				
				window.document.write(d);
	
		</script>

  </head>
  
  <body>
   
  </body>
</html>
