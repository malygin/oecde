<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
 <%@include  file="../WEB-INF/Teacher/jspf/setVariables.jspf" %>
<script type="text/javascript">
    dojo.require("dijit.form.TextBox");
    dojo.require("dijit.form.Button");
    dojo.require("dijit.form.Form");
    document.title = "ЦОО СГУ. Выставление оценок";
</script>

 <c:set var="title" value="Оценки группы"/>
 <c:set var="hashName" value="grades"/>
 <c:set var="max" value="${tNum*100+ctNum*100+200+(cw>0?100:0)}"/>
<form  name="grades" dojoType="dijit.form.Form" method="get">
<div style="margin: 5px;">
    <%@include  file="../WEB-INF/Teacher/jspf/header.jspf" %>
     <script type="text/javascript" src="../scripts/grades.js" ></script>
    <table align="center">
         <tr>
             <td align="center">
                 <div>
				    <a href="http://oec.sgu.ru/service_oec/short_report.php?semestr=<c:out value="${se.semestr}"/>&spec_id=<c:out value="${spec.id}"/>&groupe=<c:out value="${param[\"gr\"]}"/>&discipline_id=<c:out value="${disc}"/>">Скачать ведомость</a>
                 </div>
             </td>
             <td>
                 &nbsp;&nbsp;&nbsp;
             </td>
             <td align="center">
                 <div>
                   <a href="http://oec.sgu.ru/service_oec/short_report2.php?semestr=<c:out value="${se.semestr}"/>&spec_id=<c:out value="${spec.id}"/>&groupe=<c:out value="${param[\"gr\"]}"/>&discipline_id=<c:out value="${disc}"/>">Скачать Отчет (с баллами)</a>
                </div>
             </td>
         </tr>
     </table>
    <table id="table" class="newStudentsListTable" cellpadding="0" cellspacing="0">
        <tr class="newStudentsListTableBGCl">
            <td width="100">Студент</td>
            <td width="55" colspan="2">Тесты (${tNum*100})</td>
            <td width="55" colspan="2" title="Итоговый тест">И/Т (${ctNum*100})</td>
            <td width="57" colspan="2" title="Контрольные работы">К/р (100)</td>
            <td width="55" title="Самостоятельные работы">С/р (100)</td>
            <td width="56" colspan="2" title="Активность">Ак-ть (100)</td>
            <td width="56">Сумма (<span id="max">${max}</span>)</td>
            <td width="61" class="last">Оценка</td>
        </tr>
        <c:set var="u" value="false"/>
        <c:forEach items="${se.groupPointsAndGrades}" var="st" varStatus="i">
            <c:if test="${i.index %2 != 0}">
                <c:set var="style_background" value="newStudentsListTableBGCl"/>
            </c:if>
            <tr class="${style_background}">
                <td>
                    <div style="text-align: center;" class="stLinkfromGrades">
                        <a title="Нажмите, чтобы посмотреть результаты тестов" href="#student/id=${st.id}&s=${se.semestr}">
                            ${st.surname} <c:out value="${fn:substring(st.name,0,1)}" />. <c:out value="${fn:substring(st.second_name,0,1)}" />.
                        </a>
                    </div>
                </td>
                <td id="testP">${st.rating.work}</td>
                <td id="rTestP" style="color: #A535D7">${st.rating.reWork}</td>
                <td id="cTestP">${st.rating.concludingWork}</td>
                <td id="cRTestP" style="color: #A535D7">${st.rating.concludingReWork}</td>
                <td>
                    <c:if test="${st.rating.controlWorkGrade eq \"зачтено\"}">
                        <b style="color:#2CBA2D">З</b>
                    </c:if>
                    <c:if test="${st.rating.controlWorkGrade eq \"не зачтено\"}">
                        <b style="color:#FF4500;">Н/З</b>
                    </c:if>
                </td>
                <td width="32">
                    <input maxlength = '3' type="text" name="work" value="${st.rating.controlWorkPoints}" />
                </td>
                <td width="32">
                    <input maxlength = '3' type="text" name="lab" value="${st.rating.lab}" />
                </td>
                <td id="visits">
                    ${st.rating.visits}
                </td>
                <td width="32">
                    <input maxlength = '3' type="text" name="activity" value="${st.rating.activity}" />
                </td>
                <td style="padding: 0px;" class="fat_border">
                    <p style="margin: 0px; padding: 0px;">
                        <b>
                            <div id="sum" style="text-align: center !important;color:
                               <c:if test="${st.rating.sum<=max*0.3}">#FF6769;</c:if>
                               <c:if test="${st.rating.sum>max*0.3&&st.rating.sum<max*0.6}">#d89108;</c:if>
                               <c:if test="${st.rating.sum>=max*0.6}">#43DC46;</c:if>
                           ">
                                ${st.rating.sum}
                            </div>
                        </b>
                    </p>
                </td>
                <td class="last">
                    <input maxlength = '3' type="text" name="result" value="${st.rating.grade}" />
                </td>
                <input type="hidden" name="kod" value="${st.id}"/>
                <input type="hidden" name="s" value="${st.year*2-se.currentSemestr-se.semestr}"/>
                <c:if test="${st.rating.dateUpdate ne null}">
                    <c:set var="u" value="true"/>
                </c:if>
            </tr>
            <c:set var="style_background" value=""/>
        </c:forEach>
    </table>
    <div class="stand_button_new" style="margin: 0px;  text-align: center; margin-top: 10px; padding-bottom: 10px;" align="center">
        <table style="text-align: center;" align="center">
            <tr>
                <td>
                    <div dojoType="dijit.form.Button" onclick="sendGrades">Сохранить</div>
                </td>
                <td id="request_result">
                </td>
            </tr>
        </table>
        <input type="hidden" id="update" name="update" value="${u}">
        <input type="hidden" name="cwOnly" value="false">
        <input type="hidden" name="disc" value="${se.disc}">
        <input type="hidden" name="specId" value="${spec.id}">
        <input type="hidden" name="groupId" value="${se.studentI.gr}">
    </div>
</div>
</form>
<div class="otch_p"><br>
    <p>Для выставленя оценок студентам следуйте следующим инструкциям:</p>
    <p>1. В поле напротив фамилии студента (к/р, с/р, активность) введите любое желаемое количество баллов от 0 до 100;</p>
    <p>2. В столбце сумма появится общее количество очков;</p>
    <p>3. По результатам суммы поставьте желаемую оценку в последнем столдбце:</p>
    <p> - если отчетностью по данной дисциплине является экзамен, то оценка выставляется от 2 до 5;</p>
    <p> - если отчетностью является зачет, то 0 - незачет, 1 - зачет;</p>
    <p>Рекомендуемая оценка не является обязательной</p>
    <p>4. Перейдите к выставлению оценок следующему студенту;</p>
    <p>5. После выставления оценок студентам для их сохранения нажмите на кнопку сохранить;</p>
    <br>
    <p><b>Примечание</b></p>
    <p>К/р - контрольные работы соответственно учебному плану</p>
    <p>С/р - любой другой вид работ, данный преподавателем (рефераты, доклады и т.д.)</p>
    <p>Акт-ть - Активность - оценивание студентов во время консультаций</p>
</div>
<%--
<c:set var="hashName" value="grades"/>
<form  name="grades" dojoType="dijit.form.Form" method="get">
    <div style="margin: 5px;">
        <%@include  file="../WEB-INF/Teacher/jspf/header.jspf" %>
        <table class="newStudentsListTable" cellpadding="0" cellspacing="0">
            <tr class="newStudentsListTableBGCl">
                <td width="100">Фио студента</td>
                <td width="55" colspan="2">Тесты (${tNum*100})</td>
                <td width="55" colspan="2">И/Т (${ctNum*100})</td>
                <td width="57" colspan="2">К/р (100)</td>
                <td width="55">С/р (100)</td>
                <td width="56" colspan="2">Ак-ть (100)</td>
                <td width="56">Сумма (<span id="max">${max}</span>)</td>
                <td width="61" class="last">Оценка</td>
            </tr>
            <c:set var="u" value="false"/>
            <c:forEach items="${se.groupPointsAndGrades}" var="st" varStatus="i">
                <c:if test="${i.index %2 != 0}">
                    <c:set var="style_background" value="newStudentsListTableBGCl"/>
                </c:if>
                <tr class="${style_background}">
                    <td>
                        <div style="text-align: center;" class="stLinkfromGrades">
                            <a title="Нажмите, чтобы посмотреть результаты тестов" href="#student/id=${st.id}&s=${se.semestr}">
                                ${st.surname} <c:out value="${fn:substring(st.name,0,1)}" />. <c:out value="${fn:substring(st.second_name,0,1)}" />.
                            </a>
                        </div>
                    </td>
                    <td>${st.rating.work}</td>
                    <td style="color: #A535D7">${st.rating.reWork}</td>
                    <td>${st.rating.concludingWork}</td>
                    <td style="color: #A535D7">${st.rating.concludingReWork}</td>
                    <td>
                        <c:if test="${st.rating.controlWorkGrade eq \"зачтено\"}">
                            <b style="color:#2CBA2D">З</b>
                        </c:if>
                        <c:if test="${st.rating.controlWorkGrade eq \"не зачтено\"}">
                            <b style="color:#FF4500;">Н/З</b>
                        </c:if>
                    </td>
                    <td width="32"><input type="text" name="work" value="${st.rating.controlWorkPoints}" /></td>
                    <td width="32"><input type="text" name="lab" value="${st.rating.lab}" /></td>
                    <td>${st.rating.visits}</td>
                    <td width="32"><input type="text" name="activity" value="${st.rating.activity}" /></td>
                    <td>
                        <p style="margin: 0px; padding: 0px;">
                            <b>
                                <div id="sum" style="text-align: center !important;color:
                                   <c:if test="${st.rating.sum<=max*0.3}">#FF6769;</c:if>
                                   <c:if test="${st.rating.sum>max*0.3&&st.rating.sum<max*0.6}">#d89108;</c:if>
                                   <c:if test="${st.rating.sum>=max*0.6}">#43DC46;</c:if>
                               ">
                                    ${st.rating.sum}
                                </div>
                            </b>
                        </p>
                    </td>
                    <td class="last"><input type="text"/></td>
                </tr>
                <c:set var="style_background" value=""/>
            </c:forEach>
        </table>
    </div>
    <div class="stand_button_new" style="margin: 0px;  text-align: center; margin-top: 10px; padding-bottom: 10px;" align="center">
        <table style="text-align: center;" align="center">
            <tr>
                <td>
                <div dojoType="dijit.form.Button" onclick="sendGrades">Сохранить</div>
                </td>
                <td id="request_result">
                    
                </td>
            </tr>
        </table>
        <input type="hidden" id="update" name="update" value="${u}">
        <input type="hidden" name="cwOnly" value="false">
        <input type="hidden" name="disc" value="${se.disc}">
        <input type="hidden" name="specId" value="${spec.id}">
        <input type="hidden" name="groupId" value="${se.studentI.gr}">

    </div>
</form>
<div class="otch_p"><br>
    <p>Для выставленя оценок студентам следуйте следующим инструкциям:</p>
    <p>1. В поле напротив фамилии студента (к/р, с/р, активность) введите любое желаемое количество баллов от 0 до 100;</p>
    <p>2. В столбце сумма появится общее количество очков;</p>
    <p>3. По результатам суммы поставьте желаемую оценку в последнем столдбце:</p>
    <p> - если отчетностью по данной дисциплине является экзамен, то оценка выставляется от 2 до 5;</p>
    <p> - если отчетностью является зачет, то 0 - незачет, 1 - зачет;</p>
    <p>Рекомендуемая оценка не является обязательной</p>
    <p>4. Перейдите к выставлению оценок следующему студенту;</p>
    <p>5. После выставления оценок студентам для их сохранения нажмите на кнопку сохранить;</p>
    <br>
    <p><b>Примечание</b></p>
    <p>К/р - контрольные работы соответственно учебному плану</p>
    <p>С/р - любой другой вид работ, данный преподавателем (рефераты, доклады и т.д.)</p>
    <p>Акт-ть - Активность - оценивание студентов во время консультаций</p>
</div>
--%>
