<%--
    Document   : controlworks_last
    Created on : 08.07.2009, 11:46:01
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="se" class="student.education.StudentEducationFactory" scope="page"/>
<c:set target="${se}" value="${sessionScope.management.studentI}" property="studentI"/>
<c:set target="${se}" property="semestr" value="${param[\"s\"]}"/>
<c:set var="contrWorks" value="${se.studentControlWorks}"/>



<div class="title">
    <table class="gray_words" cellpadding='0' cellspacing='0' style="margin-left: 10px; margin-top: 4px;">
        <tr>
            <td class="gray_words_1_2">Преподаватель</td>
            <td class="gray_words_1_2" style="width: 150px;">&nbsp;</td>
            <td class="gray_words_3_4">Попытки</td>
            <td class="gray_words_3_4">Оценка</td>
            <td class="gray_words_5"></td>
        </tr>
    </table>
</div>
<div style="overflow-y: scroll; height: 917px;">
<c:forEach items="${contrWorks}" var="cw" varStatus="i">

    <div class="GrayTitle_big">
        <div dojoType="dijit.TitlePane" title="<b style='margin-left: 20px; color: #6495ED;'>${cw.name}</b>">
            <div>
                <div class="white_title<c:if test="${i.last}">_</c:if>">
                    <table class='tab_contr_works' cellpadding='0' cellspacing='0' style="width: 100%;">
                        <c:forEach items="${cw.controlWorks}" var="cws" varStatus="j">
                            <tr>
                                <c:if test="${j.index eq 0}">
                                    <td style="border-right: 1px solid silver; width: 150px; text-align: center;">${cw.teacherFio}</td>
                                </c:if>
                                <c:if test="${j.index ne 0}">
                                    <td style="border-right: 1px solid silver; width: 150px; text-align: center;"></td>
                                </c:if>
                                    <td class="gray_words_1_2<c:if test="${!j.last}">_</c:if>" class="white_title" class='tab_contr_works_big' style=" text-align: center;">
                                    <c:choose>
                                        <c:when test="${cws.attemptsQuantity ne 0}">
                                            <a href="javascript:void(0)" onclick="gggg('table_for_razv${i.index}${j.index}')">Контрольная ${cws.cwNumber}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <p style="color: #147BC4; margin: 0px; text-align: center;">Контрольная ${cws.cwNumber}</p>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td class="gray_words_3_4<c:if test="${!j.last}">_</c:if>">${cws.attemptsQuantity}</td>
                            <td class="gray_words_3_4<c:if test="${!j.last}">_</c:if>" class='n'><div
                                <c:choose>
                                    <c:when test="${cws.progress eq \"1\"}">style="color: #black;" >На проверке</c:when>
                                    <c:when test="${cws.progress eq \"2\"}">style="color: #FF4500;" >Не зачтено</c:when>
                                    <c:when test="${cws.progress eq \"3\"}">style="color: #2CBA2D;" >Зачтено</c:when>
                                </c:choose></div>
                                </td>
                                <td class="gray_words_5<c:if test="${!j.last}">_</c:if>"><a href='javascript:sendEmptyCw(${se.studentI.id},${cws.cwNumber},${se.studentI.year*2-se.semestr},${cw.id})' ><%--<img alt="отметить как рукописную" src='../images/2ms.jpg'>--%></a></td>
                            </tr>
                            <tr id="table_for_razv${i.index}${j.index}" style="display: none;">
                                <td colspan="5">
                                    <div>
                                        <table cellpadding="0" cellspacing="0" style="width: 100%;">
                                            <c:forEach items="${cws.cwAttempt}" var="cwAt" varStatus="k">
                                                <tr class="gray_words">
                                                    <td  class="gray_words_1_2" style="width: 150px; text-align: center; border-top: 1px solid silver;">попытка ${k.count}</td>
                                                    <td  class="gray_words_1_2" style="border-top: 1px solid silver; text-align: center;">${cwAt.attemptDate}</td>
                                                    <td  class="gray_words_3_4" style="border-top: 1px solid silver; text-align: center;"><c:if test="${cwAt.filePath eq \"empty\"}">В рукописном
                                                             (<a href="javascript:deleteEmptyCw(${cwAt.id},${cws.attemptsQuantity>1?'0':(cws.id)});">удалить</a>)
                                                        </c:if><c:if test="${cwAt.filePath ne \"empty\"}"><a href="../CWfiles/${cwAt.filePath}" >загрузить</a></c:if></td>
                                                    <td  class="gray_words_3_4" style="border-top: 1px solid silver;"><c:if test="${cwAt.read}">На проверке</c:if></td>
                                                    <td class="gray_words_5" style="border-top: 1px solid silver;"></td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>

</c:forEach>
</div>