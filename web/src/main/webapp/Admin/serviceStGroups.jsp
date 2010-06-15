<%@page contentType="application/x-json"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:useBean id="ut" class="utils.factories.utilsFactory"/>
{ label: 'name',
  identifier: 'id',
   items: [

<c:set var="city" value="${ut.allGroups}"/>
    <c:forEach items="${city}" var="city_el" varStatus="i">
        <c:if test="${!i.first}">,</c:if>
        { name:'${city_el.key}', type: 'city', id:'${city_el.key}', children: [
        <c:set var="spec" value="${city_el.value}"/>
        <c:forEach items="${spec}" var="sp" varStatus="status">
            <c:if test="${!status.first}">,</c:if>
            { name:'${sp.key}', type: 'speciality', id: '${city_el.key}${sp.key}' , children: [
            <c:forEach items="${sp.value}" var="group" varStatus="n">
                <c:set value="${fn:split(group, ' ')}" var="grsubs" />
                <c:set var="gName" value="${grsubs[0]}"/>
                <c:set var="spId" value="${grsubs[1]}"/>
                <c:if test="${!n.first}">,</c:if>
                { name:'${gName}', type: 'groupe', id: '${city_el.key} ${sp.key}${gName}', speciality: '${spId}'}
            </c:forEach>
               ]}
        </c:forEach>
        ]}
    </c:forEach>
]}
