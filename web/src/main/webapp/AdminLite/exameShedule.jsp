<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:useBean id="ut" class="utils.factories.utilsFactory"/>
<c:set target="${ut}" property="id" value="${sessionScope[\"liteAdminItem\"].city.id}"/>

<script type="text/javascript">
		dojo.require("dojo.parser");
        document.title="СГУ ЦОО. Расписание на сессию"
</script>
<div class=head_main_math>
    <p>Расписание на сессию</p>
</div>
<div class=list style="margin-top:10px">
    <ol>
        <c:set var="spec" value="${ut.adminGroups}"/>
        <c:set var="temp" value="0"/>
        <c:forEach items="${spec}" var="sp" varStatus="status">
            <c:forEach items="${sp.value}" var="gName" varStatus="n">
                <c:set value="${fn:substring(gName, 0,1)}" var="year" />
                <c:set var="temp2" value="${sp.key.id}${year}"/>
                <c:if test="${temp2 ne temp}">
        <li class="first_back_${n.index%2+1}_stteachers">
            <div align="center">
                <a  class="teh_a" href="../files/${year}/<c:out value="${sessionScope.liteAdminItem.city.engName}"/>${year}${sp.key.shortName}.xls">${sp.key.name}, ${year}курс</a>

            </div>
                </li>
                </c:if>
                <c:set var="temp" value="${sp.key.id}${year}"/>
            </c:forEach>

        </c:forEach>
                </ol>
</div>
