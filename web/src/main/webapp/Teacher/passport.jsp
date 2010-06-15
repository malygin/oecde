<%-- 
    Document   : stpassport_new
    Created on : 03.08.2009, 12:30:04
    Author     : korgovvd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
        <link href="../style/studentStyles.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="materials" value="${sessionScope[\"education\"]}"/>
<c:set target="${materials}" property="id" value="${param[\"id\"]}"/>
<c:set var="mat" value="${materials.umk}"/>
<c:if test="${mat eq null}">
<div class="head_main_math">
    <p>Ошибка</p>
</div>
<div class="course_error">
        <div class="course_error_img"><img src="../images/Warning_course.png"></div>
        <div class="course_error_text">Извините, курс не найден</div>
</div>
<div align="right" class="error_course_back_link">
    <a href="#mat"><<<вернуться к списку материалов</a>
</div>
</c:if>
<c:if test="${mat ne null}">
<div class="head_main_math">
    <p>${mat.name}</p>
</div>
<script>
    document.title = "ЦОО СГУ. ${mat.name}";
    dojo.require("dijit.ProgressBar");
</script>
<div>
<div class="title">
    <div style="float: left; margin-top: 5px; margin-left: 10px; color: gray;">Учебник</div>
    <span class="msg1" style="float: right; margin-top: 4px; margin-right: 10px;">
        <a href="#mat"><<<вернуться к учебным материалам</a>
    </span>
    <div class="otdelitel"></div>
</div>
<div class="pasp_title_content">
    <div class="pasp_conten">
        <div class="pasp_titles">
            <c:forEach items="${mat.module}" var="modu" varStatus="i">
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
<c:forEach items="${mat.tests}" var="test" varStatus="i">
        <div class="pasp_titles">
            <div>
                        <img class='pasp_img_test' src='../images/chk.gif'>
                        <div class='pasp_title_text'>
                            <a href='test.jsp?id=${test.id}' class="test_link">${test.title}</a>
                        </div>
                <div class='otdelitel'></div>
            </div>
            <ol class="pasp_list">
                <li class="test_text">Количество вопросов ${test.quantity}</li>
                <li class="test_text">Дата проведения: с ${test.begin} по ${test.end}</li>
                <li class="test_text">Количество попыток: ${test.attempts} из ${test.count}</li>
                <li class="test_text">Пробные попытки: ${test.trialAttempts}  из ${test.trial} </li>
                <li class="test_text">Время: ${test.time} минут</li>
            </ol>
        </div>
</c:forEach>
    </div>
</div>
</div></c:if>