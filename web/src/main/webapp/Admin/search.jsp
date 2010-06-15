<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<c:set var="keyWord" value="${param[\"w\"]}"/>
<c:set target="${pageContext.request}" property="characterEncoding" value="utf-8"/>
<jsp:useBean id="search" class="search.SearchEngine" scope="page"/>
<jsp:setProperty name="search" property="keywords" value="${keyWord}"/>
<c:set var="keyWord" value="${search.keywords}"/>
<c:set var="sType" value="${param[\"searchtype\"]}"/>
<c:set var="searchForStudent" value="${sType == 'student'}"/>
<c:set var="searchForTeacher" value="${sType == 'teacher'}"/>
<c:set var="searchForAdmin" value="${sType == 'admin'}"/>
<c:set var="searchForUMK" value="${sType == 'umk'}"/>
<c:if test="${!searchForStudent && !searchForTeacher && !searchForAdmin && !searchForUMK}">
    <c:set var="searchForStudent"  value="true"/>
</c:if>
<script type="text/javascript">
    document.title = "ЦОО СГУ. Поиск";
</script>
<div class=head_main_math>
    <p>Результат поиска</p>
</div>
<div class=list style="margin-top:10px">
            <ol>
    <c:choose>
        <c:when test="${searchForStudent}">
            <c:set var="list" value="${search.students}" />
                <%@include file="../WEB-INF/Admin/studentsList.jspf" %>
        </c:when>
        <c:when test="${searchForTeacher}">
            <c:set var="list" value="${search.teachers}" />
                <%@include file="../WEB-INF/Admin/teachersList.jspf" %>
        </c:when>
        <c:when test="${searchForAdmin}">
            <c:set var="list" value="${search.admins}" />
        </c:when>
        <c:when test="${searchForUMK}">
            <c:set var="list" value="${search.umk}" />
        <c:forEach items="${list}"  var="umk" varStatus="status">
                <%@include file="../WEB-INF/Admin/umksList.jspf" %>
        </c:forEach>
        </c:when>
    </c:choose>
            </ol>
</div>

