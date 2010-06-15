<%-- 
    Document   : librery_of_course
    Created on : 14.08.2009, 14:31:55
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
   <c:set var="course" value="${param[\"c\"]}"/>
 <c:set var="semestr" value="${
            (course*2) - param[\"s\"]
}"/>
<c:set var="ed" value="${sessionScope[\"education\"]}"/>
<c:set target="${ed}" property="semestr" value="${semestr}"/>
<script>makeTabsVisible()</script>
<div class="title" style="margin-top: -1px;">
    <div style="float: left; margin-left: 10px; margin-top: 2px;">Преподаватель</div>
    <div style="float: right; margin-right: 10px; margin-top: 2px;"><a class="top_link" href="javascript:changeS(${course},<c:out value="${param[\"s\"]}"/>)" id="anchor"> (<c:out value="${param[\"s\"] eq \"1\"?\"Зимний\":\"Летний\"}"/> семестр)</a></div>
    <div style="float: right; margin-right: 10px; margin-top: 2px;">Оценка</div>
    <div class="otdelitel"></div>
</div>
<div style="overflow-y: scroll; height: 549px;">
    <c:forEach items="${ed.oldGrades}" var="disc" >
        <div class="GrayTitle_big">
            <div dojoType="dijit.TitlePane" title="<b style='margin-left: 20px; color: #197D1A;'>${disc.name} - ${disc.type}</b>">
                <div>
                    <table class='' cellpadding="0" cellspacing="0"><tr></tr>
                        <tr>
                            <td style="width: 460px; padding-left: 10px; text-align: left; color: black;" class="rborder_for_grades_tab">
                                ${disc.teacherFio}
                            </td>
                            <td align="center" style="width: 60px; text-align: center; color: black;">
                                <p class="<c:if test="${disc.rating.grade eq 'Зачтено'}">
                                        zach_grade_stgrades
                                    </c:if>
                                    <c:if test="${disc.rating.grade eq 'Не зачтено'}">
                                        nezach_grade_stgrades
                                    </c:if>
                                    <c:if test="${disc.rating.grade ne 'Не зачтено' || disc.rating.grade ne 'Зачтено'}">
                                        grade_stgrades
                                    </c:if>
                                    " style="text-align: center;">${disc.rating.grade}&nbsp;</p></td>
                            </tr>
                    </table>
                </div>
            </div>
        </div>
    </c:forEach>
</div>