<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
 <%@include  file="../WEB-INF/Teacher/jspf/setVariables.jspf" %>
<c:set var="module" value="${param[\"module\"]}"/>
<c:if test="${'0' eq param[\"s\"]}">
    <c:set var="module" value="${module + 5}"/>
</c:if>
<c:set target="${se}" property="semestr" value="${module}"/>
<c:set var="List" value="${se.groupAttendance}"/>

<form  id="Attend${module}" method="get" >
    <table class="attendance_list_students_tab" cellpadding="0" cellspacing="0">
        <tr style="background: #EFEFEF;">
            <td style="border-right: 1px solid silver; padding: 3px;"><div class="first_str_list"><p>ФИО студента</p></div></td>
            <td style="border-right: 1px solid silver;"align="center"><p>Не явился</p></td>
            <td align="center"><p>Явился</p></td>
        </tr>
        <c:forEach items="${List}" var="elList" varStatus="i">
            <c:choose>
                <c:when test="${i.index %2 ne 0}">
                    <c:set var="backgr" value="#EFEFEF"/>
                </c:when>
                <c:otherwise>
                    <c:set var="backgr" value="white"/>
                </c:otherwise>
            </c:choose>
            <c:set var="stI" value="${elList.key}"/>
            <tr style="background: ${backgr}">
                <td class="attandance_student_list"><div class="first_str_list"><a  class="show_hide_link_in_otch" href="main.jsp#student/id=<c:out value="${stI.id}"/>">${stI.surname} ${stI.name}. ${stI.second_name}.</a></div></td>
                <td class="attandance_student_list" align="center"><input name="${stI.id}" type="radio" value="0" checked/></td>
                <td class="attandance_student_list_last" align="center"><input name="${stI.id}" type="radio" value="1" ${elList.value}/></td>
            </tr>
            <c:set var="update" value="${elList.value eq \"checked\"}"/>
        </c:forEach>
    </table>
<input type="hidden" name="module" value="${se.semestr}">
<input type="hidden" name="disc" value="${se.disc}">
<input type="hidden" name="update" value="${update}"><!--hb-->
<div class="stand_button_new">
<div style="float: right; margin-right: 10px; margin-top: 10px;" dojoType="dijit.form.Button" onclick="attend(${module},this);">Сохранить</div>
</div>
<div class="otdelitel"></div>
</form>