<%@page contentType="application/x-json"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<c:set var="id" value="${sessionScope.test.id}"/>
    <c:if test="${id ne null}">
        { label: 'text',
          identifier: 'id',
          items: [
    <jsp:useBean id="test" class="test.model.Test"/>
    <c:set target="${test}" property="testI" value="${sessionScope.test}"/>
    <c:set var="testI" value="${test.testQuestions}"/>
    <c:set target="${pageContext.session}" property="maxInactiveInterval" value="${testI.time*60+10}"/>
         {id: 'test', testId:'<c:out value="${id}"/>', time:'<c:out value="${testI.time}"/>', quantity:'<c:out value="${testI.quantity}"/>'},
            <c:forEach var="question" items="${testI.questions}"  varStatus="status">
                   <c:if test="${!status.first}">,</c:if>
               {id: '<c:out value="${status.index}"/>', qid:'<c:out value="${question.id}"/>', text: '<c:out value="${question.title}"/>',
               type: '<c:out value="${question.type}"/>',
               answers:[
               <c:if test="${question.type ne \"text\" && question.type ne \"inlineText\" }">
                <c:forEach var="answer" items="${question.answers}" varStatus="aStatus">
                    <c:if test="${!aStatus.first}">,</c:if>
                 {id: '<c:out value="${answer.id}"/><c:if test="${answer.id eq \"0\"}">comparison<c:out value="${aStatus.index}"/><c:out value="${question.id}"/></c:if>',
                  text: '<c:out value="${answer.item}"/>'}
                  </c:forEach></c:if>
                   ]}
            </c:forEach>
        ]}
    </c:if>