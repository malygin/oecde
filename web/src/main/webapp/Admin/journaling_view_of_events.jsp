<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean type="org.sgu.oecde.journal.filter.AdminFilter" id="filter" />
<jsp:setProperty name="filter" property="cookies" value="${pageContext.request}"/>
<%@include file="../WEB-INF/Admin/jspf/journal.jspf"%>