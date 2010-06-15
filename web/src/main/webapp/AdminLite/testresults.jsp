<%--
    Document   : allTests
    Created on : 21.07.2009, 16:35:09
    Author     : KorgovVD
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<jsp:useBean id="se" class="student.education.StudentEducationFactory" scope="page"/>
<c:set target="${se}" value="${sessionScope.management.studentI}" property="studentI"/>
<c:set target="${se}" property="semestr" value="${param[\"s\"]}"/>

<script type="text/javascript">
    dojo.require("dijit.ProgressBar");
</script>

<div class="title">
    <table class="gray_words" cellpadding='0' cellspacing='0' style="height:22px; margin-left: 30px;">
        <tr>
            <td style="width: 102px; text-align: center;">Попыток</td>
            <td style="width: 140px; text-align: center;">Правильных ответов</td>
            <td style="width: 130px; text-align: center;">Результат</td>
            <td style="width: 130px; text-align: center;">Способ оценивания</td>
        </tr>
    </table>
</div>
<div style="overflow: hidden; overflow-y: scroll;">
<table cellpadding="0" cellspacing="0" style="">
    <c:forEach items="${se.testsResultsByKod}" var="res" varStatus="i">
        <c:set var="test" value="${res.key}"/>
        <c:set var="s" value="${true}"/>
        <tr>
            <td>
                <div class="GrayTitle_big">
                    <div class="tests_titlePanes">
                    <div dojoType="dijit.TitlePane" title="<b><c:out value='${test.title}'/></b>">
                        <div>
                            <table class='' cellpadding="0" cellspacing="0" style=" text-align:center;">
                                <tr>
                                    <td style="width: 20px; text-align: center;">
                                        <div style="color: silver; font-size: 12pt; font-weight: bolder; text-align: center; cursor: pointer;" onClick="hide_tr('id'+<c:out value='${i.count}'/>,this)" id="kk">+</div>
                                    </td>
                                    <td style="width: 102px; text-align: center;">
                                        <c:out value="${test.attempts}"/> из <c:out value="${test.count}"/>
                                    </td>
                                    <td style="width: 140px; text-align: center;">
                                        &nbsp;-&nbsp;
                                    </td>
                                    <td style="width: 130px; text-align: center;">
                                        <c:out value="${test.points}"/>
                                    </td>
                                    <td style="width: 111px; text-align: center;">
                                        <c:out value="${test.estimation}"/>
                                    </td>
                                </tr>
                                <tr style="display: none;" id="id${i.count}">
                                    <td colspan="6">
                                        <table cellpadding="0" cellspacing="0" style="width: 100%;">
                                            <c:forEach items="${res.value}" var="result" varStatus="iterator">
                                                <tr>
                                                    <td style="width: 20px; height: 23px; border-top: 1px solid silver;"></td>
                                                    <td style="width: 100px; text-align: center; border-top: 1px solid silver;" class="white_title">
                                                        Попытка <c:out value='${iterator.count}'/>
                                                    </td>
                                                    <td style="width: 140px; text-align: center; border-top: 1px solid silver;"><c:out value="${result.rightAnswers}"/> из <c:out value="${result.quantity}"/></td>
                                                    <td style="width: 235px; text-align: center; border-top: 1px solid silver;" class="gray_words_1_2"><c:out value="${result.begin}"/></td>

                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    </div>
                </div>
            </td>
        </tr>
    </c:forEach>
</table>




</div>
