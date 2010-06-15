<%--
    Document   : grades_new
    Created on : 22.07.2009, 11:18:38
    Author     : KorgovVD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<script type="text/javascript">
        document.title = "ЦОО СГУ. Учебный план";
		dojo.require("dojo.parser");
		dojo.require("dojo.fx");


		function scroller(){
			var heig=document.getElementById('chengable_block1').offsetHeight;
			console.debug(heig);
			window.scroll(200,heig);
		}
    </script>


<div class="head_main_math">
    <p>Мои оценки</p>
</div>

<a class="help_part_href" onClick="scroller()">Подсказка</a>
<div id="chengable_block1">
<div class="title">
    <table class="gray_words" cellpadding='0' cellspacing='0' style="height:22px; margin: 0px; padding: 0px;">
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
    <div class="GrayTitle_big">
        <div dojoType="dijit.TitlePane" title="<b style='margin-left: 20px; color: #197D1A;'>Механика - (Экзамен)</b>">
            <div>
                <table class='' cellpadding="0" cellspacing="0"><tr></tr>
                    <tr>
                        <td style="width: 170px; text-align: center;" class="rborder_for_grades_tab">
                            Степанова Ольга Анатольевна
                        </td>
                        <td align="center" style="width: 25px;" class="rborder_for_grades_tab">
                            86
                        </td>
                        <td align="center" style="width: 25px;" class="rborder_for_grades_tab">
                            85
                        </td>
                        <td align="center" style="width: 55px;" class="rborder_for_grades_tab">
                            45
                        </td>
                        <td align="center" style="width: 55px;" class="rborder_for_grades_tab">
                            56
                        </td>
                        <td align="center" style="width: 50px;" class="rborder_for_grades_tab">
                            79
                        </td>
                        <td align="center" style="width: 50px;" class="rborder_for_grades_tab">
                            70
                        </td>
                        <td align="center" style="width: 50px;" class="rborder_for_grades_tab">
                            13
                        </td>
                        <td align="center" style="width: 60px;">
                            <p class="grade_stgrades
                                <c:if test="${disc.rating.grade eq 'Зачтено'}">
                                </c:if>
                                <c:if test="${disc.rating.grade eq 'Не зачтено'}">
                                    nezach_grade_stgrades
                                </c:if>
                                <c:if test="${disc.rating.grade ne 'Не зачтено' || disc.rating.grade ne 'Зачтено'}">
                                    grade_stgrades
                                </c:if>
                                ">4&nbsp;</p></td>
                        </tr>
                </table>
            </div>
        </div>
        <div dojoType="dijit.TitlePane" title="<b style='margin-left: 20px; color: #197D1A;'>Математика - (Зачет)</b>">
            <div>
                <table class='' cellpadding="0" cellspacing="0"><tr></tr>
                    <tr>
                        <td style="width: 170px; text-align: center;" class="rborder_for_grades_tab">
                             Игнатьева Анна Андреевна
                        </td>
                        <td align="center" style="width: 25px;" class="rborder_for_grades_tab">
                            71
                        </td>
                        <td align="center" style="width: 25px;" class="rborder_for_grades_tab">
                            62
                        </td>
                        <td align="center" style="width: 55px;" class="rborder_for_grades_tab">
                            60
                        </td>
                        <td align="center" style="width: 55px;" class="rborder_for_grades_tab">
                            56
                        </td>
                        <td align="center" style="width: 50px;" class="rborder_for_grades_tab">
                            79
                        </td>
                        <td align="center" style="width: 50px;" class="rborder_for_grades_tab">
                            70
                        </td>
                        <td align="center" style="width: 50px;" class="rborder_for_grades_tab">
                            13
                        </td>
                        <td align="center" style="width: 60px;">
                            <p class="zach_grade_stgrades">Зачтено&nbsp;</p></td>
                        </tr>
                </table>
            </div>
        </div>
        <div dojoType="dijit.TitlePane" title="<b style='margin-left: 20px; color: #197D1A;'>Информационные технологии - (Экзамен)</b>">
            <div>
                <table class='' cellpadding="0" cellspacing="0"><tr></tr>
                    <tr>
                        <td style="width: 170px; text-align: center;" class="rborder_for_grades_tab">
                           Позднева Инна Павловна
                        </td>
                        <td align="center" style="width: 25px;" class="rborder_for_grades_tab">
                            89
                        </td>
                        <td align="center" style="width: 25px;" class="rborder_for_grades_tab">
                            78
                        </td>
                        <td align="center" style="width: 55px;" class="rborder_for_grades_tab">
                            78
                        </td>
                        <td align="center" style="width: 55px;" class="rborder_for_grades_tab">
                            56
                        </td>
                        <td align="center" style="width: 50px;" class="rborder_for_grades_tab">
                            79
                        </td>
                        <td align="center" style="width: 50px;" class="rborder_for_grades_tab">
                            70
                        </td>
                        <td align="center" style="width: 50px;" class="rborder_for_grades_tab">
                            10
                        </td>
                        <td align="center" style="width: 60px;">
                            <p class="grade_stgrades">4&nbsp;</p></td>
                        </tr>
                </table>
            </div>
        </div>
    </div>
</div>





<div class="prosto_tekst">
        <p class="help_part">Подсказки</p>
		<p class="comment">

	    Перед вами итоги <c:if test="${semestr eq 'w'}">зимней</c:if><c:if test="${semestr eq 's'}">летней</c:if> сессии по соответствующим дисциплинам.<br>
	В графе Итоговая оценка проставлены либо оценка, в случае если отчётность - экзамен,
	либо зачёт (незачёт) в случае отчётности - зачёт, по соответствующему предмету.<br>
	Если в графе Итоговая оценка пусто, то это означает, что преподаватель ещё не выставил результаты.<br>
	В графе Тесты стоят результаты прохождения тестов. <br><br>
	<b>Примечание.</b> уточнить отчётность по дисциплине можно во вкладке "Учебный план на семестр".<br><br>

	<b>Тесты</b> - максимальный суммарный балл по тестам по данной дисциплине<br>
	<b>Контрольная работа</b> - контрольные работы соответственно учебному плану<br>
	<b>Самостоятельная работа</b> - любой другой вид работ, данный преподавателем (рефераты, лабораторные и т.д.)<br>
	<b>Активность</b> - оценивание студентов во время консультаций<br>
	<b>Итоговая оценка</b> - результирующая оценка по данной дисциплине<br>




	</p>
	<a class="help_part_href" onClick="javascript: scroll(0,0);">Наверх</a>
</div>
