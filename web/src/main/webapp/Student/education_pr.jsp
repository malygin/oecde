<%-- 
    Document   : education_pr
    Created on : 07.08.2009, 15:45:00
    Author     :marushina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<c:set var="ed" value="${sessionScope[\"education\"]}"/>
<c:set var="s" value="${(param[\"s\"] eq 1)}"/>
<c:set target="${ed}" property="semestr" value="${(s?1:0)}" />
<div class="title_my_education">
    <i>Прочее</i>
</div>
<div class="my_educ_text">
    <p><b><i>Активность</i></b></p>
    <p>Активность - это ваше участие в конференциях. Ведь только там преподаватель сможет
увидеть вас. Баллы за активность выставляются за кажый курс в отдельности. Эти баллы также учитываются
преподавателем в конце семестра при проставлении оценки или зачета.</p>

    <p><b><i>Самостоятельные работы</i></b></p>
    <p>Самостоятельные работы - это любой вид работ, проделанных студентом самостоятельно.
        Преподаватель может дать  вам самостоятельную работу и
        учесть ее результат   в конце семестра при проставлении оценки
или зачета</p>
<a class="pereexzam" href="javascript:dojo.attr(dijit.byId('misc'),'href','education_pr.jsp?s=<c:out value="${s?0:1}"/>')"><c:out value="${(s)?\"Текущий семестр\":\"Переэкзаменовка\"}"/></a>
<p class="educ_red_tetx">Дисциплины </p>
 <c:forEach items="${ed.lowGradedDisciplines}"  var="disc">
<div>
    <c:if test="${disc.rating eq null || disc.rating.lab  eq \"0\" || disc.rating.activity eq \"0\"}">
        <img class='pasp_img_test' src='../images/pic.jpg'>
        <div class="educ_umk_text">
            <a href="" class="pasp_links">${disc.name}</a><br>
        </div>
    </c:if>
    <div class="educ_content_title_activ">
        <ol class="my_educ_disc_reason">
            <c:if test="${disc.rating eq null}">
                 <li>баллов нет</li>
            </c:if>
            <c:if test="${disc.rating.lab eq \"0\"}">
                 <li>отсутствуют самостоятельные работы</li>
            </c:if>
            <c:if test="${disc.rating.activity eq \"0\"}">
                 <li>низкая активность</li>
            </c:if>
        </ol>
    </div>
</div>
 </c:forEach>

</div>