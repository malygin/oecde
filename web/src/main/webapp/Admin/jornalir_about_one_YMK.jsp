<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean class="journal.model.filter.AdminFilterForOneUmk" id="filter" />
<jsp:setProperty name="filter" property="cookies" value="${pageContext.request}"/>
<%@include  file="../WEB-INF/Admin/jspf/journal.jspf"%>


