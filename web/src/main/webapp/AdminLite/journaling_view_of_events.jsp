<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean class="journal.model.filter.AdminFilter" id="filter" />
<jsp:setProperty name="filter" property="cookies" value="${pageContext.request}"/>
<%@include file="../WEB-INF/AdminLite/jspf/journal.jspf"%>