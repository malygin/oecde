<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.springframework.context.ApplicationContext" %>
<%@page import="org.springframework.context.support.FileSystemXmlApplicationContext" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
ApplicationContext c = new FileSystemXmlApplicationContext("classpath:org/sgu/oecde/applicationContext.xml");
for (Object o:c.getBeanDefinitionNames()){
    out.println(o);
    out.println("<br>");
}
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    </body>
</html>
