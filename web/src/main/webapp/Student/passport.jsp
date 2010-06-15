<%--
    Document   : stpassport_new
    Created on : 03.08.2009, 12:30:04
    Author     : korgovvd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="materials" value="${sessionScope[\"education\"]}"/>
<c:set target="${materials}" property="id" value="${param[\"id\"]}"/>
<c:set var="mat" value="${materials.umk}"/>
<c:if test="${mat eq null}">
<script>
    document.title = "ЦОО СГУ. Ошибка";
</script>
<div class="head_main_math">
    <p>Ошибка</p>
</div>
<div class="course_error">
        <div class="course_error_img"><img src="../images/Warning_course.png"></div>
        <div class="course_error_text">Извините, курс не найден или не доступен</div>
</div>
<div align="right" class="error_course_back_link">
    <a href="#mat"><<<вернуться к списку материалов</a>
</div>
</c:if>
<c:if test="${mat ne null}">
<div class="head_main_math">
    <p>${mat.umk.name}</p>
</div>
<script>
    document.title = "ЦОО СГУ. ${mat.umk.name}";
    dojo.require("dijit.ProgressBar");
</script>
<div>
<div class="title">
    <div style="float: left; margin-top: 5px; margin-left: 10px; color: gray;">Учебник</div>
    <span class="msg1" style="float: right; margin-top: 4px; margin-right: 10px;">
        <a href="#mat/s=${sessionScope.studentItem.year*2-materials.currentSemestr - materials.semestr}"><<<вернуться к учебным материалам</a>
    </span>
    <div class="otdelitel"></div>
</div>
<div class="pasp_title_content">
    <div class="pasp_conten">
        <div class="pasp_titles">
            <c:forEach items="${mat.umk.module}" var="modu" varStatus="i">
                <div dojoType="dijit.TitlePane" <c:if test="${param[\"tt\"] eq \"1\"}">open="false"</c:if> title="<div ><img class='pasp_img' src='../images/pic.jpg'><div class='pasp_title_text'>${modu.name}</div><div class='otdelitel'></div></div>">
                    <ol class="pasp_list">
                        <c:forEach items="${modu.tasks}" var="task">
                            <li><a href="exbook.jsp#${task.id}" class="pasp_links">${task.name}</a></li>
                        </c:forEach>
                    </ol>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
    <div class="title" name="test">
    <p>Тесты</p>
</div>

<div class="pasp_title_content">
    <div class="pasp_conten">
<c:forEach items="${mat.umk.tests}" var="test" varStatus="i">
            <div class="pasp_titles">
            <div>
                <c:choose>
                    <c:when test="${(test.attempts eq test.count)|| test.type eq \"2\" || materials.semestr ne (sessionScope.studentItem.year*2-materials.currentSemestr)}">
                        <img height='15' width='14' class='pasp_img_test' src='../images/no.png'>
                        <div class='pasp_title_text passiv_test'>
                            ${test.title}
                        </div>
                    </c:when>
                    <c:otherwise>
                        <img class='pasp_img_test' src='../images/chk.gif'>
                        <div class='pasp_title_text'>
                            <a href='test.jsp?id=${test.id}' class="test_link">${test.title}</a>
                        </div>
                    </c:otherwise>
                </c:choose>
                <div class='otdelitel'></div>
            </div>
            <ol class="pasp_list">
                <li class="test_text">
                <c:choose>
                    <c:when test="${materials.semestr eq (sessionScope.studentItem.year*2-materials.currentSemestr)}">
                        <c:if test="${test.attempts eq test.count}">
                            Тест недоступен: исчерпано количество попыток
                        </c:if>
                        <c:if test="${test.type eq \"2\"}">
                            Итоговый тест недоступен
                        </c:if>
                        <c:if test="${test.trialAttempts  eq test.trial}">
                            Пробные попытки исчерпаны!
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        Переэкзаменовка окончена
                    </c:otherwise>
                </c:choose>
                 </li>
                <li class="test_text">Количество вопросов ${test.quantity}</li>
                <li class="test_text">Количество попыток: ${test.attempts} из ${test.count}</li>
                <li class="test_text">Пробные попытки: ${test.trialAttempts}  из ${test.trial} </li>
                <li class="test_text">Время: ${test.time} минут</li>
            </ol>
        </div>
</c:forEach>
    </div>
    </div></c:if>
</div>
