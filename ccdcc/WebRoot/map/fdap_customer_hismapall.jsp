<%@ page language="java"pageEncoding="gbk"%>
<%@page import="org.tbcc.biz.ProjectBiz"%>
<%@page import="org.tbcc.util.MySpringFactory"%>
<%@page import="org.tbcc.entity.TbccPrjType"%>
<%@page import="org.tbcc.biz.HisStartUpBiz"%>
<%@page import="java.util.Date"%>
<%@page import="org.tbcc.util.MyUtil"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>ok</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%--这个jsp页面是为了客户端软件而开发出来的页面,为了不需要登录系统、需要验证
		工程参数的合法性、这个jsp页面起一个控制作用类似与action
	 --%>

  </head>
  
  <body oncontextmenu="window.event.returnValue=false" >
   <%
   		String projectId = request.getParameter("projectId") ;
   		//String code = request.getParameter("code") ;
   		//String sid = request.getParameter("sid");
   		
   		String beginTime = request.getParameter("beginTime");
   		
   		if(projectId==null || projectId.equals("")||beginTime==null || beginTime.equals("")){
   			out.println("传递了非法的参数!");
   			return ;
   		}
   		
   		try{
   			Date d = MyUtil.getToDate(beginTime);
   		}catch(Exception e){
   			out.println("传递了非法的参数！");
   			return ;
   		}
   		
   		ProjectBiz projectBiz = (ProjectBiz)MySpringFactory.getInstance().getBean("projectBiz");
   		
   		TbccPrjType prjType =  projectBiz.getByProId(projectId) ;
   		
   		HisStartUpBiz startupBiz = (HisStartUpBiz)MySpringFactory.getInstance().getBean("startUpBiz");
   		
   		Long sid = startupBiz.getStartupidByBeginTime(projectId,beginTime);
   		
   		//if(prjType==null || prjType.getProjectAuthCode()==null ||  !prjType.getProjectAuthCode().equals(code)){
   		if(prjType==null || prjType.getProjectAuthCode()==null ){
   			out.println("工程参数验证失败 ！");
   			return  ;
   		}
   		
   		request.setAttribute("sid",sid);
   		request.setAttribute("projectName",prjType.getProjectName());
   		request.getRequestDispatcher("fdap_customer_hismapall2.jsp").forward(request,response);
    %>
  </body>
</html>
