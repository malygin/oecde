<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
 <%@include  file="../WEB-INF/Teacher/jspf/setVariables.jspf" %>
<script>
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
<script type="text/javascript">
    //console.debug(234)
    makeTabsVisible();
</script>
 <c:set var="title" value="Отчёт по группе"/>
<div style="margin: 5px;">
<c:set var="hashName" value="tests"/>
 <%@include  file="../WEB-INF/Teacher/jspf/header.jspf" %>

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
        <c:forEach items="${se.groupTestResults}" var="stud" varStatus="iiter">
            <c:set var="st" value="${stud.key}"/>
            <tr>
                <td colspan="5" style="border-bottom: 1px solid silver;">
                    <div>
                        <p class="title_of_otch" style="border: 0px; font-size: 10pt;">
                           <a  class="show_hide_link_in_otch" href="main.jsp#student/id=<c:out value="${st.id}"/>"> <c:out value="${st.surname}"/> <c:out value="${st.name}"/> <c:out value="${st.second_name}"/></a>
                        </p>
                    </div>
                </td>
            </tr>

            <c:forEach items="${stud.value}" var="test" varStatus="iter">
                    <c:if test="${i.index %2 == 0}">
                        <c:set var="style_background" value="chet_color_back"/>
                    </c:if>
                <tr  style="margin-left: 10px;" class="${style_background}">
                    <td class="not_last_td_otch_page" >
                        <a href="javascript:void(0)" onclick="hide_tr('int_table${iiter.index}${iter.index}',this)" class="show_hide_link_in_otch">
                            <c:out value="${test.key.title}"/>
                        </a>
                    </td>
                    <td class="not_last_td_otch_page">
                        <p class="p_in_td_tab_otch">
                            <c:out value="${test.key.attempts}"/> из <c:out value="${test.key.count}"/>
                        </p>
                    </td>
                    <td class="not_last_td_otch_page">
                        <p class="p_in_td_tab_otch">
                            &nbsp;-&nbsp;
                        </p>
                    </td>
                    <td class="not_last_td_otch_page">
                        <p class="p_in_td_tab_otch">
                            <c:out value="${test.key.points}"/>
                        </p>
                    </td>
                    <td class="last_td_otch_page">
                        <p class="p_in_td_tab_otch">
                            <c:out value="${test.key.estimation}"/>
                        </p>
                    </td>
                </tr>
                <tbody id='int_table${iiter.index}${iter.index}' style="margin: 0px; display: none;">
                    <c:forEach items="${test.value}" var="result" varStatus="iterator">
                        <tr>
                            <td class="td_otch_page1">
                                <c:choose>
                                    <c:when test="${test.key.writable}">
                                        <a class="show_hide_link_in_otch" href="#testpassing/id=${result.id}&kod=${stud.key.id}&s=${semestr}&disc=${disc}">Попытка <c:out value='${iterator.count}'/></a>
                                    </c:when>
                                    <c:otherwise>
                                        Попытка <c:out value='${iterator.count}'/>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="td_otch_page1">&nbsp;</td>
                            <td class="td_otch_page1"><c:out value="${result.rightAnswers}"/> из <c:out value="${result.quantity}"/></td>
                            <td class="td_otch_page1"><c:out value="${result.points}"/></td>
                            <td class="td_otch_page1">${result.begin}</td>
                        </tr>
                    </c:forEach>
                </tbody><c:set var="style_background" value=""/>
            </c:forEach>
        </c:forEach>
    </table>
</div>
</div>