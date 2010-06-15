<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<jsp:useBean id="student" class="student.model.Student"/>
<c:set var="action" value="${param[\"action\"]}"/>
<c:set var="group" value="${param[\"gr\"]}"/>
<c:set var="sp" value="${param[\"sp\"]}"/>

<c:choose>
    <c:when test="${action eq 'studonline'}">
        <c:set target="${student.studentI}" property="id" value="0" />
        <c:set var="title" value="Студенты on-line"/>
        <c:set var="list" value="${student.usersOnline}"/>
    </c:when>
    <c:when test="${sp ne null && sp ne '' && group ne null && goup ne ''}">
        <jsp:useBean id="specI" class="plan.model.SpecItem"/>
        <c:set target="${specI}" property="id" value="${sp}" />
        <c:set target="${student.studentI}" property="spec" value="${specI}" />
        <c:set target="${student.studentI}" property="gr" value="${group}" />
        <c:set var="title" value="Список группы ${group}"/>
        <c:set var="list" value="${student.studentsList}"/>
    </c:when>
    <c:otherwise>
        <c:redirect url="Groups.jsp"/>
    </c:otherwise>
</c:choose>
<script type="text/javascript">
		dojo.require("dojo.parser");
        document.title="СГУ ЦОО. ${title}"
</script>
<div class=head_main_math>
    <p>${title}</p>
</div>
<div class=list style="margin-top:10px">
	<ol>
<%@include file="../WEB-INF/Admin/studentsList.jspf" %>
    </ol>
</div>
