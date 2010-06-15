<%-- 
    Document   : education_umk
    Created on : 07.08.2009, 11:08:27
    Author     : marushina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<c:set var="ed" value="${sessionScope[\"education\"]}"/>
<c:set var="s" value="${(param[\"s\"] eq 1)}"/>
<c:set target="${ed}" property="semestr" value="${(s?1:0)}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<div class="title_my_education">
    <i>Дисциплины</i>
</div>
<div class="my_educ_text">
    <p>Здесь собрана информация по всем вашим  Учебно-методическим материалам (УМК). <br>
        Ваша итоговая оценка складывается из многих факторов. Один из них - работа с УМК.
На портале внедрена  система контроля посещаемости и времени работы с курсами, которая будет доступна преподавателям,
поэтому к ним надо относится внимательно. <br>
 УМК состоит из нескольких модулей, поэтому материал
рекомендуется читать постепенно, а не в последний вечер при прохождении итогового теста. Информация о том, как распределена
активность вашего изучения по времени, тоже будет доступна преподавателю и может повлиять на итоговую оценку. </p>
     <a class="pereexzam" href="javascript:dojo.attr(dijit.byId('umk'),'href','education_umk.jsp?s=<c:out value="${s?0:1}"/>')">
            <c:out value="${(s)?\"Текущий семестр\":\"Переэкзаменовка\"}"/>
        </a>
	
	<p class="educ_red_tetx">Дисциплины
    
       
    </p>
 <c:forEach items="${ed.lowGradedDisciplines}"  var="disc">
    <div><img class='pasp_img_test' src='../images/pic.jpg'>
        <div class="educ_umk_text">
            <a href="" class="pasp_links">${disc.name}</a><br>
            <div class="educ_content_title">
                <ol class="my_educ_disc_reason">
                    <c:if test="${disc.rating eq null}">
                        <li>дисциплина не оценена. баллов нет</li>
                    </c:if>
                    <c:if test="${disc.rating.grade eq null}">
                        <li>дисциплина не оценена</li>
                    </c:if>
                    <c:if test="${disc.rating.grade eq \"0\"}">
                        <li>дисциплина не зачтена</li>
                    </c:if>
                    <c:if test="${disc.rating.grade eq \"2\"}">
                        <li>по дисциплине неуд</li>
                    </c:if>
                    <c:if test="${disc.rating.sum < 175}">
                        <li>мало баллов - ${disc.rating.sum}</li>
                    </c:if>
                    <c:if test="${disc.rating.visits eq 0}">
                        <li>мало посещений</li>
                    </c:if>
                </ol>
            </div>
        </div>
    </div>
 </c:forEach>
    
</div>
