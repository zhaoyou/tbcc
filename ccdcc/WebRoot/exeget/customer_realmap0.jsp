<%@ page language="java"pageEncoding="gbk"%>
<%@page import="org.tbcc.biz.ProjectBiz"%>
<%@page import="org.tbcc.util.MySpringFactory"%>
<%@page import="org.tbcc.entity.TbccPrjType"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>ok</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%--���jspҳ����Ϊ�˿ͻ������������������ҳ��,Ϊ�˲���Ҫ��¼ϵͳ����Ҫ��֤
		���̲����ĺϷ��ԡ����jspҳ����һ����������������action
	 --%>

  </head>
  
  <body oncontextmenu="window.event.returnValue=false" >
   <%
   		String projectId = request.getParameter("projectId") ;
   		
   		if(projectId==null || projectId.equals("") ){
   			out.println("�����˷Ƿ��Ĺ��̲���!");
   			return ;
   		}
   		ProjectBiz projectBiz = (ProjectBiz)MySpringFactory.getInstance().getBean("projectBiz");
   		
   		TbccPrjType prjType =  projectBiz.getByProId(projectId) ;
   		
   		if(prjType==null  ){
   			out.println("������֤ʧ�� ��");
   			return  ;
   		}
   		
   		request.setAttribute("projectName",prjType.getProjectName());
   		request.getRequestDispatcher("customer_realmap1.jsp").forward(request,response);
    %>
  </body>
</html>
