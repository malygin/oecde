<%--
    Document   : librery_of_course
    Created on : 14.08.2009, 14:31:55
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<c:set var="course" value="${param[\"c\"]}"/>
<c:set var="semestr" value="${
            param[\"s\"]
}"/>
<script>makeTabsVisible()</script>
<c:if test="${course eq 1}">
    <c:if test="${semestr eq 0}">
        <div class="title" style="margin-top: -1px;">
            <div style="float: left; margin-left: 10px; margin-top: 2px;">Преподаватель</div>
            <div style="float: right; margin-right: 10px; margin-top: 2px;"><a class="top_link" href="javascript:changeS(${course},<c:out value="${param[\"s\"]}"/>)" id="anchor"> (<c:out value="${param[\"s\"] eq \"1\"?\"Зимний\":\"Летний\"}"/> семестр)</a></div>
            <div style="float: right; margin-right: 10px; margin-top: 2px;">Оценка</div>
            <div class="otdelitel"></div>
        </div>
        <div style="overflow-y: scroll; height: 549px;">
            <div class="GrayTitle_big">
                <div dojoType="dijit.TitlePane" title="<b style='margin-left: 20px; color: #197D1A;'>Математический анализ - (зачет)</b>">
                    <div>
                        <table class='' cellpadding="0" cellspacing="0"><tr></tr>
                            <tr>
                                <td style="width: 460px; padding-left: 10px; text-align: left; color: black;" class="rborder_for_grades_tab">
                                    Степанова Ольга Анатольевна
                                </td>
                                <td align="center" style="width: 60px; text-align: center; color: black;">
                                    <p class="zach_grade_stgrades" style="text-align: center;">Зачтено&nbsp;</p></td>
                                </tr>
                        </table>
                    </div>
                </div>
                <div dojoType="dijit.TitlePane" title="<b style='margin-left: 20px; color: #197D1A;'>Векторная алгебра - (зачет)</b>">
                    <div>
                        <table class='' cellpadding="0" cellspacing="0"><tr></tr>
                            <tr>
                                <td style="width: 460px; padding-left: 10px; text-align: left; color: black;" class="rborder_for_grades_tab">
                                    Игнатьева Анна Андреевна
                                </td>
                                <td align="center" style="width: 60px; text-align: center; color: black;">
                                    <p class="zach_grade_stgrades" style="text-align: center;">Зачтено&nbsp;</p></td>
                                </tr>
                        </table>
                    </div>
                </div>
                <div dojoType="dijit.TitlePane" title="<b style='margin-left: 20px; color: #197D1A;'>Теория вероятности - (зачет)</b>">
                    <div>
                        <table class='' cellpadding="0" cellspacing="0"><tr></tr>
                            <tr>
                                <td style="width: 460px; padding-left: 10px; text-align: left; color: black;" class="rborder_for_grades_tab">
                                    Аброськин Петр Владимирович
                                </td>
                                <td align="center" style="width: 60px; text-align: center; color: black;">
                                    <p class="zach_grade_stgrades" style="text-align: center;">Зачтено&nbsp;</p></td>
                                </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
    <c:if test="${semestr eq 1}">
        <div class="title" style="margin-top: -1px;">
            <div style="float: left; margin-left: 10px; margin-top: 2px;">Преподаватель</div>
            <div style="float: right; margin-right: 10px; margin-top: 2px;"><a class="top_link" href="javascript:changeS(${course},<c:out value="${param[\"s\"]}"/>)" id="anchor"> (<c:out value="${param[\"s\"] eq \"1\"?\"Зимний\":\"Летний\"}"/> семестр)</a></div>
            <div style="float: right; margin-right: 10px; margin-top: 2px;">Оценка</div>
            <div class="otdelitel"></div>
        </div>
        <div style="overflow-y: scroll; height: 549px;">
            <div class="GrayTitle_big">
                <div dojoType="dijit.TitlePane" title="<b style='margin-left: 20px; color: #197D1A;'>Философия - (Экзамен)</b>">
                    <div>
                        <table class='' cellpadding="0" cellspacing="0"><tr></tr>
                            <tr>
                                <td style="width: 460px; padding-left: 10px; text-align: left; color: black;" class="rborder_for_grades_tab">
                                    Андреева Ирина Алексеевна
                                </td>
                                <td align="center" style="width: 60px; text-align: center; color: black;">
                                    <p class="grade_stgrades" style="text-align: center;">5&nbsp;</p></td>
                                </tr>
                        </table>
                    </div>
                </div>
                <div dojoType="dijit.TitlePane" title="<b style='margin-left: 20px; color: #197D1A;'>Уравнения математической физики - (зачет)</b>">
                    <div>
                        <table class='' cellpadding="0" cellspacing="0"><tr></tr>
                            <tr>
                                <td style="width: 460px; padding-left: 10px; text-align: left; color: black;" class="rborder_for_grades_tab">
                                    Кондаков Ярослав Николаевич
                                </td>
                                <td align="center" style="width: 60px; text-align: center; color: black;">
                                    <p class="zach_grade_stgrades" style="text-align: center;">Зачтено&nbsp;</p></td>
                                </tr>
                        </table>
                    </div>
                </div>
                <div dojoType="dijit.TitlePane" title="<b style='margin-left: 20px; color: #197D1A;'>Механика - (зачет)</b>">
                    <div>
                        <table class='' cellpadding="0" cellspacing="0"><tr></tr>
                            <tr>
                                <td style="width: 460px; padding-left: 10px; text-align: left; color: black;" class="rborder_for_grades_tab">
                                    Рыжкова Оксана Дмитриевна
                                </td>
                                <td align="center" style="width: 60px; text-align: center; color: black;">
                                    <p class="zach_grade_stgrades" style="text-align: center;">Зачтено&nbsp;</p></td>
                                </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</c:if>
<c:if test="${course eq 2}">
    <c:if test="${semestr eq 0}">
        <div class="title" style="margin-top: -1px;">
            <div style="float: left; margin-left: 10px; margin-top: 2px;">Преподаватель</div>
            <div style="float: right; margin-right: 10px; margin-top: 2px;"><a class="top_link" href="javascript:changeS(${course},<c:out value="${param[\"s\"]}"/>)" id="anchor"> (<c:out value="${param[\"s\"] eq \"1\"?\"Зимний\":\"Летний\"}"/> семестр)</a></div>
            <div style="float: right; margin-right: 10px; margin-top: 2px;">Оценка</div>
            <div class="otdelitel"></div>
        </div>
        <div style="overflow-y: scroll; height: 549px;">
            <div class="GrayTitle_big">
                <div dojoType="dijit.TitlePane" title="<b style='margin-left: 20px; color: #197D1A;'>Линейная алгебра - (зачет)</b>">
                    <div>
                        <table class='' cellpadding="0" cellspacing="0"><tr></tr>
                            <tr>
                                <td style="width: 460px; padding-left: 10px; text-align: left; color: black;" class="rborder_for_grades_tab">
                                    Лопатин Андрей Игоревич
                                </td>
                                <td align="center" style="width: 60px; text-align: center; color: black;">
                                    <p class="zach_grade_stgrades" style="text-align: center;">Зачтено&nbsp;</p></td>
                                </tr>
                        </table>
                    </div>
                </div>
                <div dojoType="dijit.TitlePane" title="<b style='margin-left: 20px; color: #197D1A;'>Экономика - (зачет)</b>">
                    <div>
                        <table class='' cellpadding="0" cellspacing="0"><tr></tr>
                            <tr>
                                <td style="width: 460px; padding-left: 10px; text-align: left; color: black;" class="rborder_for_grades_tab">
                                    Федоров Сергей Владимирович
                                </td>
                                <td align="center" style="width: 60px; text-align: center; color: black;">
                                    <p class="zach_grade_stgrades" style="text-align: center;">Зачтено&nbsp;</p></td>
                                </tr>
                        </table>
                    </div>
                </div>
                <div dojoType="dijit.TitlePane" title="<b style='margin-left: 20px; color: #197D1A;'>Спецкурс 1 - (экзамен)</b>">
                    <div>
                        <table class='' cellpadding="0" cellspacing="0"><tr></tr>
                            <tr>
                                <td style="width: 460px; padding-left: 10px; text-align: left; color: black;" class="rborder_for_grades_tab">
                                    Романов Кирилл Яковлевич
                                </td>
                                <td align="center" style="width: 60px; text-align: center; color: black;">
                                    <p class="grade_stgrades" style="text-align: center;">5&nbsp;</p></td>
                                </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
    <c:if test="${semestr eq 1}">
        <div class="title" style="margin-top: -1px;">
            <div style="float: left; margin-left: 10px; margin-top: 2px;">Преподаватель</div>
            <div style="float: right; margin-right: 10px; margin-top: 2px;"><a class="top_link" href="javascript:changeS(${course},<c:out value="${param[\"s\"]}"/>)" id="anchor"> (<c:out value="${param[\"s\"] eq \"1\"?\"Зимний\":\"Летний\"}"/> семестр)</a></div>
            <div style="float: right; margin-right: 10px; margin-top: 2px;">Оценка</div>
            <div class="otdelitel"></div>
        </div>
        <div style="overflow-y: scroll; height: 549px;">
            <div class="GrayTitle_big">
                <div dojoType="dijit.TitlePane" title="<b style='margin-left: 20px; color: #197D1A;'>Спецкурс 1 - (зачет)</b>">
                    <div>
                        <table class='' cellpadding="0" cellspacing="0"><tr></tr>
                            <tr>
                                <td style="width: 460px; padding-left: 10px; text-align: left; color: black;" class="rborder_for_grades_tab">
                                  Лопатин Андрей Игоревич
                                </td>
                                <td align="center" style="width: 60px; text-align: center; color: black;">
                                    <p class="zach_grade_stgrades" style="text-align: center;">Зачтено&nbsp;</p></td>
                                </tr>
                        </table>
                    </div>
                </div>
                <div dojoType="dijit.TitlePane" title="<b style='margin-left: 20px; color: #197D1A;'>Объектно ориентированное программирование - (зачет)</b>">
                    <div>
                        <table class='' cellpadding="0" cellspacing="0"><tr></tr>
                            <tr>
                                <td style="width: 460px; padding-left: 10px; text-align: left; color: black;" class="rborder_for_grades_tab">
                                    Пронин Роман Павлович
                                </td>
                                <td align="center" style="width: 60px; text-align: center; color: black;">
                                    <p class="zach_grade_stgrades" style="text-align: center;">Зачтено&nbsp;</p></td>
                                </tr>
                        </table>
                    </div>
                </div>
                <div dojoType="dijit.TitlePane" title="<b style='margin-left: 20px; color: #197D1A;'>Психология - (экзамен)</b>">
                    <div>
                        <table class='' cellpadding="0" cellspacing="0"><tr></tr>
                            <tr>
                                <td style="width: 460px; padding-left: 10px; text-align: left; color: black;" class="rborder_for_grades_tab">
                                    Никитина Алефтина Витальевна
                                </td>
                                <td align="center" style="width: 60px; text-align: center; color: black;">
                                    <p class="grade_stgrades" style="text-align: center;">4&nbsp;</p></td>
                                </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</c:if>
<c:if test="${course eq 3}">
    <c:if test="${semestr eq 1}">
        <div class="title" style="margin-top: -1px;">
            <div style="float: left; margin-left: 10px; margin-top: 2px;">Преподаватель</div>
            <div style="float: right; margin-right: 10px; margin-top: 2px;"><a class="top_link" href="javascript:changeS(${course},<c:out value="${param[\"s\"]}"/>)" id="anchor"> (<c:out value="${param[\"s\"] eq \"1\"?\"Зимний\":\"Летний\"}"/> семестр)</a></div>
            <div style="float: right; margin-right: 10px; margin-top: 2px;">Оценка</div>
            <div class="otdelitel"></div>
        </div>
        <div style="overflow-y: scroll; height: 549px;">
            <div class="GrayTitle_big">
                <div dojoType="dijit.TitlePane" title="<b style='margin-left: 20px; color: #197D1A;'>Mатематика - (зачет)</b>">
                    <div>
                        <table class='' cellpadding="0" cellspacing="0"><tr></tr>
                            <tr>
                                <td style="width: 460px; padding-left: 10px; text-align: left; color: black;" class="rborder_for_grades_tab">
                                    Лунин Олег Георгиевич
                                </td>
                                <td align="center" style="width: 60px; text-align: center; color: black;">
                                    <p class="grade_stgrades" style="text-align: center;">4&nbsp;</p></td>
                                </tr>
                        </table>
                    </div>
                </div>
                <div dojoType="dijit.TitlePane" title="<b style='margin-left: 20px; color: #197D1A;'>Mеханика - (экзамен)</b>">
                    <div>
                        <table class='' cellpadding="0" cellspacing="0"><tr></tr>
                            <tr>
                                <td style="width: 460px; padding-left: 10px; text-align: left; color: black;" class="rborder_for_grades_tab">
                                    Иванов Афанасий Демьянович
                                </td>
                                <td align="center" style="width: 60px; text-align: center; color: black;">
                                    <p class="zach_grade_stgrades" style="text-align: center;">Зачтено&nbsp;</p></td>
                                </tr>
                        </table>
                    </div>
                </div>
                <div dojoType="dijit.TitlePane" title="<b style='margin-left: 20px; color: #197D1A;'>Информационные технологии - (экзамен)</b>">
                    <div>
                        <table class='' cellpadding="0" cellspacing="0"><tr></tr>
                            <tr>
                                <td style="width: 460px; padding-left: 10px; text-align: left; color: black;" class="rborder_for_grades_tab">
                                    Горчакова Валерия Константиновна
                                </td>
                                <td align="center" style="width: 60px; text-align: center; color: black;">
                                    <p class="zach_grade_stgrades" style="text-align: center;">Зачтено&nbsp;</p></td>
                                </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
    <c:if test="${semestr eq 0}">
        <div class="title" style="margin-top: -1px;">
            <div style="float: left; margin-left: 10px; margin-top: 2px;">Преподаватель</div>
            <div style="float: right; margin-right: 10px; margin-top: 2px;"><a class="top_link" href="javascript:changeS(${course},<c:out value="${param[\"s\"]}"/>)" id="anchor"> (<c:out value="${param[\"s\"] eq \"1\"?\"Зимний\":\"Летний\"}"/> семестр)</a></div>
            <div style="float: right; margin-right: 10px; margin-top: 2px;">Оценка</div>
            <div class="otdelitel"></div>
        </div>
        <div style="overflow-y: scroll; height: 549px;">
            <div class="GrayTitle_big">
                <div dojoType="dijit.TitlePane" title="<b style='margin-left: 20px; color: #197D1A;'>Mатематика - (экзамен)</b>">
                    <div>
                        <table class='' cellpadding="0" cellspacing="0"><tr></tr>
                            <tr>
                                <td style="width: 460px; padding-left: 10px; text-align: left; color: black;" class="rborder_for_grades_tab">
                                   Степанова Ольга Анатольевна
                                </td>
                                <td align="center" style="width: 60px; text-align: center; color: black;">
                                    <p class="grade_stgrades" style="text-align: center;">&nbsp;</p></td>
                                </tr>
                        </table>
                    </div>
                </div>
                <div dojoType="dijit.TitlePane" title="<b style='margin-left: 20px; color: #197D1A;'>Mеханика - (зачет)</b>">
                    <div>
                        <table class='' cellpadding="0" cellspacing="0"><tr></tr>
                            <tr>
                                <td style="width: 460px; padding-left: 10px; text-align: left; color: black;" class="rborder_for_grades_tab">
                                    Игнатьева Анна Андреевна
                                </td>
                                <td align="center" style="width: 60px; text-align: center; color: black;">
                                    <p class="
                                            zach_grade_stgrades
                                        <c:if test="${disc.rating.grade eq 'Не зачтено'}">
                                            nezach_grade_stgrades
                                        </c:if>
                                        <c:if test="${disc.rating.grade ne 'Не зачтено' || disc.rating.grade ne 'Зачтено'}">
                                            grade_stgrades
                                        </c:if>
                                        " style="text-align: center;">&nbsp;</p></td>
                                </tr>
                        </table>
                    </div>
                </div>
                <div dojoType="dijit.TitlePane" title="<b style='margin-left: 20px; color: #197D1A;'>Информационные технологии - (зачет)</b>">
                    <div>
                        <table class='' cellpadding="0" cellspacing="0"><tr></tr>
                            <tr>
                                <td style="width: 460px; padding-left: 10px; text-align: left; color: black;" class="rborder_for_grades_tab">
                                    Позднева Инна Павловна 
                                </td>
                                <td align="center" style="width: 60px; text-align: center; color: black;">
                                    <p class="zach_grade_stgrades" style="text-align: center;">&nbsp;</p></td>
                                </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        
    </c:if>
</c:if>
