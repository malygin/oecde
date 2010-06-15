<%--
    Document   : grades_new
    Created on : 22.07.2009, 11:18:38
    Author     : KorgovVD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<jsp:useBean id="se" class="student.education.StudentEducationFactory" scope="page"/>
<c:set target="${se}" value="${sessionScope.management.studentI}" property="studentI"/>
<c:set target="${se}" property="semestr" value="${param[\"s\"]}"/>
<div class="title">
    <table class="gray_words" cellpadding='0' cellspacing='0' style="height:22px; margin-left: 10px;">
        <tr>
            <td style="width: 170px;">Преподаватель</td>
            <td style="width: 50px;">Тесты</td>
            <td style="width: 110px;">Итоговые тесты</td>
            <td style="width: 50px;">К/р</td>
            <td style="width: 50px;">С/р</td>
            <td style="width: 50px;">Акт-ть</td>
            <td style="width: 60px;">Оценка</td>
        </tr>
    </table>
</div>
<div style="overflow-y: scroll; height: 917px;">
<c:forEach items="${se.studentGrades}" var="disc" varStatus="i">
    <div class="GrayTitle_big">
        <div dojoType="dijit.TitlePane" title="<b style='margin-left: 20px; color: #197D1A;'>${disc.name} - ${disc.type}</b>">
            <div>
                <table class='' cellpadding="0" cellspacing="0">
                    <tr>
                        <td style="width: 170px; text-align: center;" class="rborder_for_grades_tab">
                            ${disc.teacherFio}
                        </td>
                        <td align="center" style="width: 25px;" class="rborder_for_grades_tab">
                            <c:out value="${disc.rating.work}"/>
                        </td>
                        <td align="center" style="width: 25px;" class="rborder_for_grades_tab">
                            <c:out value="${disc.rating.reWork}"/>
                        </td>
                        <td align="center" style="width: 55px;" class="rborder_for_grades_tab">
                            <c:out value="${disc.rating.concludingWork}"/>
                        </td>
                        <td align="center" style="width: 55px;" class="rborder_for_grades_tab">
                            <c:out value="${disc.rating.concludingReWork}"/>
                        </td>
                        <td align="center" style="width: 50px;" class="rborder_for_grades_tab">
                            <c:out value="${disc.rating.controlWorkPoints}"/>
                        </td>
                        <td align="center" style="width: 50px;" class="rborder_for_grades_tab">
                            <c:out value="${disc.rating.lab}"/>
                        </td>
                        <td align="center" style="width: 50px;" class="rborder_for_grades_tab">
                            <c:out value="${disc.rating.activity}"/>
                        </td>
                        <td align="center" style="width: 60px;">
                            <p class="
                                <c:if test="${disc.rating.grade eq 'Зачтено'}">
                                    zach_grade_stgrades
                                </c:if>
                                <c:if test="${disc.rating.grade eq 'Не зачтено'}">
                                    nezach_grade_stgrades
                                </c:if>
                                <c:if test="${disc.rating.grade ne 'Не зачтено' || disc.rating.grade ne 'Зачтено'}">
                                    grade_stgrades
                                </c:if>
                                "><c:out value="${disc.rating.grade}"/>&nbsp;</p></td>
                        </tr>
                </table>
            </div>
        </div>
    </div>

</c:forEach>
</div>
<div align="centr"><div class="otdelitel"></div></div>