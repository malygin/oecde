<%-- 
    Document   : education_it_test
    Created on : 07.08.2009, 15:39:21
    Author     : marushina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<c:set var="ed" value="${sessionScope[\"education\"]}"/>
<c:set var="s" value="${(param[\"s\"] eq 1)}"/>
<c:set target="${ed}" property="semestr" value="${(s?1:0)}" />
<!--подгружаются только итоговые тесты-->
<c:set target="${ed}" property="id" value="2" />
<div class="title_my_education">
    <i>Мои итоговые тесты</i>
</div>
<div class="my_educ_text">
    <p>Итоговые тесты - это контроль вашей общей успеваемости за семестр и основной показатель, который используют преподаватели
        при выставлении итоговой оценки. Их следутет проходить
обязательно. Они доступны в конце семестра и подходить к их прохождению слудуе хорошо подготовившись.</p>
    <a class="pereexzam" href="javascript:dojo.attr(dijit.byId('cTest'),'href','education_it_test.jsp?s=<c:out value="${s?0:1}"/>')"><c:out value="${(s)?\"Текущий семестр\":\"Переэкзаменовка\"}"/></a>
	<p class="educ_red_tetx">Итоговые тесты:</p>
<table class="tests_tab_in_my_educ" cellpadding="0" cellspacing="0">
    <tr>
        <td class="hat_of_tests_result_in_my_educ" style=" border-right: 1px solid silver;">Название теста</td>
        <td class="hat_of_tests_result_in_my_educ">Результат</td>
    </tr>
 <c:forEach items="${ed.badPassedTests}"  var="test">
    <tr>
        <td style="text-align: left; padding: 3px 0px 3px 0px; width: 432px; border-bottom: 1px dotted silver; border-right: 1px dotted silver;">
            <img class='pasp_img_test' src='../images/pic.jpg'>
           <!-- <a href="test.jsp?id=${test.id}" class="pasp_links" style="margin: 0px;">${test.title}.</a>-->
            ${test.title}
        </td>
        <td style="text-align: center; color: #777777; border-bottom: 1px dotted silver;">
            ${test.points}
        </td>
    </tr>
</c:forEach>
</table>
</div>