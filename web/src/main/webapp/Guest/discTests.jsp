<%-- 
    Document   : discTests
    Created on : 21.07.2009, 16:59:42
    Author     : KorgovVD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="student.model.*" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>


<c:set var="disc" value="${param[\"d\"]}"/>

<c:set var="se" value="${sessionScope[\"education\"]}"/>
<jsp:setProperty name="se" property="semestr" value="${se.currentSemestr}" />
<jsp:setProperty name="se" property="disc" value="${disc}" />
<c:set var="discipline" value="${se.disciplineForStudent}"/>
<script>
    dojo.require("dijit.ProgressBar");
    function hide_tr(id,link_id){
        if(dojo.byId(id).style.display != 'none'){
            dojo.fx.wipeOut({node: id,duration: 250}).play();
            dojo.byId(link_id).setAttribute("class","show_hide_link_in_otch");
        }
        else{
            dojo.fx.wipeIn({node: id,duration: 250}).play();
            dojo.byId(link_id).setAttribute("class","show_hide_link_in_otch");
        }
    }
</script>
<div class="head_main_math">
    <p>Результаты тестов по дисциплине</p> 
</div>
<div>
    <p class="fio_stud_tests_otch" style="float: left;">
        ${discipline.name} ${discipline.type} ${discipline.id} ${disc}
    </p>
    <span class="msg1" style="float: right; margin-top: 10px; margin-right: 10px;"><a href="#mat"><<<вернуться назад</a></span>
   <div class="otdelitel"></div>
</div>
<div>

    <table class="otch_main_tab" cellpadding="0" cellspacing="0">
        <tr>
            <td class="first_stolb_hat_main_tab">
                <p>Название</p>
            </td>
            <td class="second_stolb_hat_main_tab">
                <p>Попыток</p>
            </td>
            <td class="third_stolb_hat_main_tab">
                <p>Правильных ответов</p>
            </td>
            <td class="fourth_stolb_hat_main_tab">
                <p>Результат</p>
            </td>
            <td class="fiveth_stolb_hat_main_tab">
                <p>Способ оценивания</p>
            </td>
        </tr>
        <c:forEach items="${se.testsResultsByDisc}" var="res" varStatus="i">
            <c:set var="test" value="${res.key}"/>
            <c:choose>
                <c:when test="${i.index %2 == 0}">
                    <c:set var="style_background" value="chet_color_back"/>
                </c:when>
                <c:otherwise>
                    <c:set var="style_background" value=""/>
                </c:otherwise>
            </c:choose>
            <tr  style="margin-left: 10px;" class="${style_background}">
                <td class="not_last_td_otch_page" >
                    <c:if test="${empty res.value}">
                        <c:out value="${test.title}"/>
                    </c:if>
                    <c:if test="${!empty res.value}">
                        <a href="javascript:void(0)" onclick="hide_tr('int_table<c:out value='${i.index}'/>',this)" class="show_hide_link_in_otch">
                            <c:out value="${test.title}"/>
                        </a>
                    </c:if>
                </td>
                <td class="not_last_td_otch_page">
                    <p class="p_in_td_tab_otch">
                        <c:out value="${test.attempts}"/> из <c:out value="${test.count}"/>
                    </p>
                </td>
                <td class="not_last_td_otch_page">
                    <p class="p_in_td_tab_otch">
                        &nbsp;-&nbsp;
                    </p>
                </td>
                <td class="not_last_td_otch_page">
                    <p class="p_in_td_tab_otch">
                        <c:out value="${test.points}"/>
                    </p>
                </td>
                <td class="last_td_otch_page">
                    <p class="p_in_td_tab_otch">
                        <c:out value="${test.estimation}"/>
                    </p>
                </td>
            </tr>
            <tbody id="int_table<c:out value='${i.index}'/>" style="margin: 0px; display: none;">
                <c:forEach items="${res.value}" var="result" varStatus="iterator">
                    <tr>
                        <td class="td_otch_page1">Попытка <c:out value="${iterator.index+1}"/></td>
                        <td class="td_otch_page1">&nbsp;</td>
                        <td class="td_otch_page1"><c:out value="${result.rightAnswers}"/> из <c:out value="${result.quantity}"/></td>
                        <td class="td_otch_page1"><c:out value="${result.points}"/></td>
                        <td class="td_otch_page1">&nbsp;</td>
                    </tr>
                </c:forEach>
            </tbody>
        </c:forEach>
    </table>
</div>
