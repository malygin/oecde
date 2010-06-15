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
<c:set target="${edu}" property="disc" value="${param[\"disc\"]}"/> <!--чтобы сслыка на возвращение к странице список студентов работала необходимо в test совать дисциплину. я ее беру из адресной строки в которую она попадает со страницы тестов группы через ссылку на данную страницу-->
<c:set var="discipline" value="${edu.disciplineForStudent}"/>
<script>
    document.title="ЦОО СГУ. Просмотр прохождения теста"
</script>
<div class="head_main_math">
    <p>Отчеты по тестам</p>
</div>
<div class="title"><p style="float: left;">${discipline.name} ${discipline.type}</p> <span class="msg1" style="float: right; margin-top: 4px; margin-right: 10px;"><a href="#grtests/gr=${studentI.gr}&sp=${studentI.spec.id}&disc=${discipline.id}&s=${semestr}"><<<вернуться назад</a></span></div>
<div>
    <p class="fio_stud_tests_otch">
        ${studentI.surname} ${studentI.name} ${studentI.second_name}
    </p>
    <p class="facult_stud_tests_otch">
        ${studentI.faculty} факультет, группа ${studentI.gr}
    </p>
</div>

<br>
<p align="center" class="title_of_otch">
    <a style="color:gray;text-decoration:none" href="test.jsp?id=${testI.id}">${testI.title}</a>
</p>
<table style="float: left; margin-left: 10px; margin-top: 10px; margin-right: 30px;">
    <tr>
        <td>Вопросов: </td><td>${testI.quantity}</td>
    </tr>
    <tr>
        <td>Верных ответов:</td><td>${testI.rightAnswers}</td>
    </tr>
    <tr>
        <td>Способ оценивания:&nbsp;&nbsp;&nbsp;&nbsp;</td><td>${testI.estimation}</td>
    </tr>
</table>
<table style="margin-top: 10px;">
    <tr>
        <td>Время прохождения:</td><td>${testI.time} минуты</td>
    </tr>
    <tr>
        <td>Начало прохождения:&nbsp;&nbsp;&nbsp;&nbsp;</td><td>${testI.begin}</td>
    </tr>
    <tr>
        <td>Тип теста:</td><td> <c:choose>
                <c:when test="${testI.type eq '0'}">Обычный</c:when>
                <c:when test="${testI.type eq '1'}">Пробный</c:when>
                <c:when test="${testI.type eq '2'}">Итоговый</c:when>
            </c:choose>
        </td>
    </tr>
</table>
<div class="otdelitel"></div>
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
            <td class="question_icon_otch">${i.count}. ${quest.title}</td>
        </tr>
        <tr>
            <td>
                &nbsp;
            </td>
            <td>
                    <table style="text-align: center; font-size: 10pt;">
                <c:choose>
                        <c:when test="${quest.type eq 'radio' || quest.type eq 'check'}">
                            <c:forEach items="${quest.answers}" var="answer" varStatus="j">
                                <tr>
                                    <td style="width: 15px;">
                                        <c:if test="${answer.answer eq '1'}">
                                            <img src="<c:out value="${(quest.type eq 'radio')?\"../dojoroot/dijit/themes/tundra/images/radioButtonActive.png\":\"../dojoroot/dijit/themes/tundra/images/checkboxActive.png\"}"/>" title="Выбранный ответ">
                                        </c:if>
                                        <c:if test="${answer.answer ne '1'}">
                                            <img src="<c:out value="${(quest.type eq 'radio')?\"../dojoroot/dijit/themes/tundra/images/radioButtonEnabled.png\":\"../dojoroot/dijit/themes/tundra/images/checkboxEnabled.png\"}"/>" title="Выбранный ответ">
                                        </c:if>
                                    </td>
                                    <td style="width: 15px;">
                                        <c:if test="${answer.rightAnswer eq '1'}">
                                            <img src="../images/correct_answer.gif" title="Верный ответ">
                                        </c:if>
                                    </td>
                                    <td style="text-align: left;">${j.count}. ${answer.item}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:when test="${quest.type eq 'text'}">
                            <tr>
                                <td>${quest.answers[0].rightAnswer}</td>
                                <td style="padding-left: 10px;" class="write_answer_test_otch"> ${quest.answers[0].answer}</td>
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
                                       
                                    >${j.count}. ${answer.item}</td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                </c:choose>
                    </table>
            </td>
    </c:forEach>
</table>
