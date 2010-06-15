<%@page contentType="application/x-json" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<c:set var="ed" value="${sessionScope[\"management\"]}"/>
<c:set target="${ed}" property="id" value="${param[\"id\"]}" />
<c:set var="umk" value="${ed.task}"/>
<c:set var="module" value="${umk.module}"/>
<c:set var="tasks" value="${module.tasks}"/>
<c:if test="${tasks ne null && ! empty tasks}">
  {status: 'success','module':'${module.id}','umk':'${umk.id}',name:'${umk.name}', text:'${module.name}', tasks:[
      <c:forEach items="${tasks}" var="task" varStatus="i">
      {id:'${task.id}', text:'${task.name}', address:'${umk.directory}/${task.address}'}<c:if test="${!i.last}">,</c:if>
       </c:forEach>
  ]}
</c:if>
<c:if test="${tasks eq null &&  empty tasks}">
    {status:'fail'}
</c:if>