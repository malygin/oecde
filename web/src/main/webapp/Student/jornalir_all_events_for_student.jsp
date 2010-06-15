<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean class="journal.model.filter.StudentFilter" id="filter" />
<jsp:setProperty name="filter" property="cookies" value="${pageContext.request}"/>
<jsp:setProperty name="filter" property="umkList" value="${sessionScope[\"education\"].umkList}"/>
<jsp:setProperty name="filter" property="user" value="${sessionScope[\"studentItem\"]}"/>
<%@include file="../WEB-INF/Student/jspf/journal.jspf" %>
