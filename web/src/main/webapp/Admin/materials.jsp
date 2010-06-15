<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<c:set var="materials" value="${sessionScope[\"management\"]}"/>

    <script type="text/javascript">
        document.title = "ЦОО СГУ. Изучаемые материалы";
    </script>
    <div class="head_main_math">
        <p>Изучаемые материалы</p>
    </div>
    <div style="visibility:visible">
        <c:set var="list" value="${materials.disciplines}"/>

        <c:forEach items="${list}" var="course" varStatus="status">
            <c:set var="umk" value="${course.umk}"/>
                <%@include file="../WEB-INF/Admin/umksList.jspf" %>
        </c:forEach>
    </div>
