<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<jsp:useBean id="test" class="test.model.Test" scope="page"/>
<jsp:useBean id="student" class="student.model.Student"/>
<c:set var="edu" value="${sessionScope[\"education\"]}"/>
<c:set target="${test.testI}" property="kod" value="${param[\"id\"]}"/>
<c:set var="semestr" value="${param[\"s\"]}"/>
<c:set var="testI" value="${test.fullTestResults}"/>
<c:set var="sId" value="${testI.kod}"/>
<c:set target="${student.studentI}" property="id" value="${sId}"/>
<c:set var="studentI" value="${student.byId}"/>
<c:set target="${edu}" property="studentI" value="${studentI}"/>
<c:set target="${edu}" property="semestr" value="${semestr}"/>
<div class="head_main_math">
    <p>Отчеты по тестам</p>
</div>
<c:set var="discipline" value="${edu.disciplineForStudent}"/>
${discipline}
<div class="title"><p style="float: left;">${discipline.name} ${discipline.type}</p> <span class="msg1" style="float: right; margin-top: 4px; margin-right: 10px;"><a href="#testresults"><<<вернуться назад</a></span></div>

<br>
<p align="center" class="title_of_otch">
    ${testI.title}
</p>
<table style="margin-left: 10px; margin-top: 10px;">
    <tr>
        <td>Вопросов: </td><td>${testI.quantity}</td>
    </tr>
    <tr>
        <td>Верных ответов:</td><td>${testI.rightAnswers}</td>
    </tr>
    <tr>
        <td>Способ оценивания:&nbsp;&nbsp;</td><td>${testI.estimation}</td>
    </tr>
    <tr class="experiments_otch_tab">
        <td>Попытка </td><td><b style="color: black; margin:0px; font-weight: normal;">1</b>&nbsp;|&nbsp;<a href="#">2</a>&nbsp;|&nbsp;<a href="#">3</a></td>
    </tr>
</table>
<br>
<p class="title_of_otch" style="color: silver;">
    Список вопросов
</p>
<table style="margin-top: 10px; margin-left: 10px; width: 502px; margin-right: 10px;" cellpadding="0" cellspacing="0">
    <c:forEach items="${testI.questions}" var="quest" varStatus="i">
        <tr>
            <td class="true_false_icon_test_otch">
                <c:if test="${quest.result eq '0'}">
                    <img src="../images/no.png">
                </c:if>
                <c:if test="${quest.result ne '0'}">
                    <img src="../images/chk.gif">
                </c:if>
            </td>
            <td class="question_icon_otch">${i.index+1}. ${quest.title}</td>
        </tr>
        <tr>
            <td>
                &nbsp;
            </td>
            <td>
                    <table style="text-align: center; font-size: 10pt;">
                <c:choose>
                        <c:when test="${quest.type eq 'radio' || 'check'}">
                            <c:forEach items="${quest.answers}" var="answer" varStatus="j">
                                <tr>
                                    <td style="width: 15px;">
                                        <c:if test="${answer.answer eq '1'}">
                                            <img src="../images/chk.gif">
                                        </c:if>
                                    </td>
                                    <td style="text-align: left;
                                        <c:if test="${answer.rightAnswer eq '1'}">
                                             color:red;
                                        </c:if>"
                                    >${j.index+1}. ${answer.item}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:when test="${quest.type eq 'text'}">
                            <tr>
                                <td>${answer.item}</td>
                                <td style="padding-left: 10px;" class="write_answer_test_otch"> ${answer.answer}</td>
                            </tr>
                        </c:when>
                        <c:when test="${quest.type eq 'comparison'}">
                            <c:forEach items="${quest.answers}" var="answer">
                                <tr>
                                    <td>${answer.item}</td>
                                    <td style="padding-left: 30px;" class="wrong_answer_test_otch">${answer.answer}</td>
                                    <td style="padding-left: 10px;" class="write_answer_test_otch">${answer.rightAnswer}</td>
                                </tr>
                            </c:forEach>
                        </c:when>

                        <c:otherwise>
                            <c:forEach items="${quest.answers}" var="answer" varStatus="j">
                                <tr>
                                    <td style="width: 15px;">
                                        &nbsp;
                                    </td>
                                    <td style="text-align: left;"

                                    >${j.index+1}. ${answer.item}</td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                </c:choose>
                    </table>
            </td>
    </c:forEach>
</table>
