<%-- 
    Document   : first_course
    Created on : 14.08.2009, 14:36:47
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   <c:set var="course" value="${param[\"c\"]}"/>
 <c:set var="semestr" value="${
            (course*2) - param[\"s\"]
}"/>
<c:set var="materials" value="${sessionScope[\"education\"]}"/>
<c:set target="${materials}" property="semestr" value="${semestr}"/>
<script>
    makeTabsVisible()
</script>
<div class="title_my_education semester_link">
    <i>Перед Вами материалы <b id="semestr${course}"><c:out value="${param[\"s\"] eq \"1\"?\"зимнего\":\"летнего\"}"/></b> семестра </i><a href="javascript:changeLibraryContent(${course},<c:out value="${param[\"s\"]}"/> )" onclick="" id="link${course}">(показать <c:out value="${param[\"s\"] eq \"1\"?\"летний\":\"зимний\"}"/> семестр)</a>
</div>

<div>
    <ol class="lib_course_list">
        <c:forEach items="${materials.studentsDisciplinesForLibrary}" var="disc" >
        <li>
            <p>${disc.umk.name} <a class="pasp_links_lib" href="main.jsp#course/id=${disc.umk.id}">(Просмотреть курс)</a></p>
            <p class="lib_prep_course">Автор: <span class="lib_author_course"><c:forEach items="${disc.umk.authors}" var="auth" varStatus="i">${auth.surname} ${fn:substring(auth.name,0,1)}. ${fn:substring(auth.secondName,0,1)}.<c:if test="${!i.last}">,</c:if> </c:forEach></span></p>
        </li>
</c:forEach>
    </ol>
</div>