<%-- 
    Document   : allTests
    Created on : 21.07.2009, 16:35:09
    Author     : KorgovVD
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<c:set var="se" value="${sessionScope[\"education\"]}"/>
<c:set var="s" value="${(param[\"s\"] eq 1)}"/>
<jsp:setProperty name="se" property="semestr" value="${s?1:0}" />

<script>
    dojo.require("dijit.ProgressBar");
    function hide_tr(id,link_id){
        if(dojo.byId(id).style.display != 'none')
            dojo.fx.wipeOut({node: id,duration: 250}).play();        
        else
            dojo.fx.wipeIn({node: id,duration: 250}).play();
        dojo.byId(link_id).setAttribute("class","show_hide_link_in_otch");
    }
</script>
<div class="head_main_math">
    <p>Результаты тестов</p>
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
        <c:forEach items="${se.testsResultsByKod}" var="res" varStatus="i">
            <c:set var="test" value="${res.key}"/>
            <c:set var="s" value="${true}"/>
            <tr  style="margin-left: 10px;" class="<c:out value="${(i.index %2 == 0)?\"chet_color_back\":\"\"}"/>">
                <td class="not_last_td_otch_page" >
                    <a href="javascript:void(0)" onclick="hide_tr('int_table<c:out value='${i.index}'/>',this)" class="show_hide_link_in_otch">
                        <c:out value="${test.title}"/>
                    </a>
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
                    Попытка <c:out value='${iterator.count}'/>
            <td class="td_otch_page1"></td>
                        <td class="td_otch_page1"><c:out value="${result.rightAnswers}"/> из <c:out value="${result.quantity}"/></td>
                        <td class="td_otch_page1"><c:out value="${result.begin}"/></td>
                        <td class="td_otch_page1">&nbsp;</td>
                    </tr>
                </c:forEach>
            </tbody>
        </c:forEach>
    </table>
</div>
