<%--
    Document   : controlWorksList
    Created on : 07.12.2009, 10:20:12
    Author     : KorgovVD
--%>

<%@page contentType="text/html" pageEncoding="windows-1251"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:useBean id="cwl" class="teacher.education.ControlWorksList" />
<c:set var="se" value="${sessionScope[\"education\"]}"/>
<c:set target="${cwl}" property="year" value="${se.currentYear}"/>
<c:set target="${cwl}" property="semestr" value="${se.currentSemestr}"/>
<c:set target="${cwl}" property="id" value="${sessionScope[\"teacherItem\"].id}"/>
<c:set target="${cwl}" property="length"  value="20" />
<c:set target="${cwl}" property="page" value="${param[\"page\"]}"/>
<c:set var="maxPageNumber" value="${cwl.maxPageNumber}"/>
<c:set var="semestr" value="${param[\"sem\"]}"/>

<div class="head_main_math">
    <p>
        Список контрольных работ
    </p>
</div>
<div align="center" style="color: #03b63a;">
    <c:set var="st" value="3"/>
    <c:set var="page" value="${cwl.intPage}"/>
    <c:forEach begin="1" end="${maxPageNumber}" step="1" var="counter">

        <c:if test="${(counter eq (st + 1)) and (page gt (2 * st + 1))}">...</c:if>
        <c:if test="${(counter eq (end -begin - st)) and (page lt (maxPageNumber - 2 * st))}">...</c:if>

        <c:if test="${(counter le st) or ((counter le (page + st)) and (counter ge (page - st))) or (counter gt (maxPageNumber - st))}">
            <c:choose>
                <c:when test="${counter == page}">
                    ${counter}
                </c:when>
                <c:otherwise>
                    <a class="pages_journalir" href="#cwl/page=${counter}">${counter}</a>
                </c:otherwise>
            </c:choose>
        </c:if>
    </c:forEach>
</div>
<div class="today_daybefore_all" style=" margin: 10px 0 0 0; padding: 0px;" >
    <div align="center">
        <table cellspacing="0" style="width: 500px; border-top: 3px double silver; margin:0px;">
            <tr>
                <td style="border-bottom: 1px solid silver; text-align: center; font-size: 11pt; padding: 5px 0 3px 0;">Название дисциплины</td>
                <td style="border-bottom: 1px solid silver; text-align: center; font-size: 11pt; padding: 5px 0 3px 0;">Фио студента</td>
                <td style="border-bottom: 1px solid silver; font-size: 11pt; padding: 5px 0 3px 0;">группа</td>
            </tr>
            <c:forEach items="${cwl.cwWithStudents}" var="hm" varStatus="numb">
                <c:set var="color" value="white"/>
                <c:if test="${numb.index %2 == 0}"><c:set var="color" value="#e4f5e9"/></c:if>
                <tr>
                    <td style="padding-bottom: 5px; padding-left: 10px; background: ${color}; width: 300px; "><p class="p_for_jornalir" style="margin: 0px; font-size: 11pt; color: #6B8E23;">${hm.key[0].name}</p></td>
                    <td rowspan="2" style="background: ${color}; padding-bottom: 5px; text-align: center; border-bottom: 1px solid silver;"><p class="p_for_jornalir" style="margin: 0px;"><span class="msg1"><a href="#student/id=${hm.value.id}">${hm.value.surname} ${hm.value.name} ${hm.value.secondName} </a></span></p></td>
                    <td rowspan="2" style="background: ${color}; padding: 0 10px 5px 0; text-align: center; border-bottom: 1px solid silver;"><div class="msg1"><a href="#grades/gr=${hm.value.gr}&sp=${hm.value.spec.id}&s=0&disc=${hm.key[0].id}&t=cw">(${hm.value.gr}гр.)</a></div></td>
                </tr>
                <c:set var="stCw" value="${hm.value.controlWorks[0]}"/>
                <tr>
                    <td style=" background: ${color}; padding: 0px 0px 5px 10px; border-bottom: 1px solid silver; padding-left: 10px;">
                        Работа № ${stCw.cwNumber}
                        <c:set var="cwAt" value="${stCw.cwAttempt[0]}"/>

                        <c:choose>
                            <c:when test="${stCw.progress eq \"2\"}"><b style="color: #FF4500;">(Не зачтено)</b></c:when>
                            <c:when test="${stCw.progress eq \"3\"}"><b style="color: #2CBA2D;">(Зачтено)</b></c:when>
                        </c:choose>
                            <span onclick="readTask(${hm.key[0].id},${hm.value.id},${cwAt.id})"><a href="../CWfiles/${cwAt.filePath}" <c:if test="${!stCw.cwAttempt[0].read}">class="to_load_cw"</c:if> <c:if test="${stCw.cwAttempt[0].read}"> title="Данная контрольная работа уже скачана" class="top_link"</c:if><c:if test="${!stCw.cwAttempt[0].read}"> title="Данная контрольная работа еще не скачана"</c:if>>Скачать</a><span style="padding-left: 10px;"><c:out value="${fn:substring(cwAt.attemptDate, 0, 10)}"/></span></span>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<div align="center" style="color: #03b63a;">
    <c:set var="st" value="3"/>
    <c:set var="page" value="${cwl.intPage}"/>
    <c:forEach begin="1" end="${maxPageNumber}" step="1" var="counter">

        <c:if test="${(counter eq (st + 1)) and (page gt (2 * st + 1))}">...</c:if>
        <c:if test="${(counter eq (end -begin - st)) and (page lt (maxPageNumber - 2 * st))}">...</c:if>

        <c:if test="${(counter le st) or ((counter le (page + st)) and (counter ge (page - st))) or (counter gt (maxPageNumber - st))}">
            <c:choose>
                <c:when test="${counter == page}">
                    ${counter}
                </c:when>
                <c:otherwise>
                    <a class="pages_journalir" href="#cwl/page=${counter}">${counter}</a>
                </c:otherwise>
            </c:choose>
        </c:if>
    </c:forEach>
</div>