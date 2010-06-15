<%-- 
    Document   : education_test
    Created on : 07.08.2009, 15:32:07
    Author     : marushina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<c:set var="ed" value="${sessionScope[\"education\"]}"/>
<c:set var="s" value="${(param[\"s\"] eq 1)}"/>
<c:set target="${ed}" property="semestr" value="${(s?1:0)}" />
<c:set target="${ed}" property="id" value="1" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<div class="title_my_education">
    <i>Мои тесты</i>
</div>
<div class="my_educ_text">
    <p> Тесты - это
контроль вашей промежуточной успеваемости.
Следует учитывать, что у каждого теста есть количество попыток прохождения и временной промежуток, когда он доступен.
Успешное прохождение тестов - важный шаг к получению хорошей итоговой оценки.
 </p>
    
	<a class="pereexzam" href="javascript:dojo.attr(dijit.byId('test'),'href','education_test.jsp?s=<c:out value="${s?0:1}"/>')"><c:out value="${(s)?\"Текущий семестр\":\"Переэкзаменовка\"}"/></a></p>
	<p class="educ_red_tetx">Тесты:
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
