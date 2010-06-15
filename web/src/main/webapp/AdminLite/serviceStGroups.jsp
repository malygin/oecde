<%@page contentType="application/x-json"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:useBean id="ut" class="utils.factories.utilsFactory"/>
<c:set target="${ut}" property="id" value="${sessionScope[\"liteAdminItem\"].city.id}"/>
{ label: 'name',
  identifier: 'id',
   items: [

        <c:set var="spec" value="${ut.adminGroups}"/>
        <c:forEach items="${spec}" var="sp" varStatus="status">
            <c:if test="${!status.first}">,</c:if>
            { name:'${sp.key.name}', type: 'speciality', id: '${city_el.key}${sp.key}' , children: [
            <c:forEach items="${sp.value}" var="gName" varStatus="n">
                <c:if test="${!n.first}">,</c:if>
                { name:'${gName}', type: 'groupe', id: '${sp.key}${gName}', speciality: '${sp.key.id}'}
            </c:forEach>
               ]}
        </c:forEach>
]}