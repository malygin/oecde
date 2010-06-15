<%--
    Document   : grades_new
    Created on : 22.07.2009, 11:18:38
    Author     : KorgovVD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<c:set var="se" value="${sessionScope[\"education\"]}"/>
<c:set var="s" value="${(param[\"s\"] eq 1)}"/>
<c:set target="${se}" property="semestr" value="${s?1:0}" />

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
    <p>Мои оценки
        <c:choose>
      <c:when test="${s}">
          переэкзаменовка
          <span style=" margin-right: 15px; width: 120px; float: right;" ><a class='stmat_link' style="color: red;font-weight: bolder" href='#grades/s=0'>(летний семестр)</a></span>
      </c:when>
      <c:otherwise>
             летнего семестра
             <span style=" margin-right: 15px; width: 120px; float: right;" ><a class='stmat_link' style="color: red;font-weight: bolder" href='#grades/s=1'>(переэкзаменовка)</a></span>
      </c:otherwise>
    </c:choose>
    </p>
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
<c:forEach items="${se.studentGrades}" var="disc" varStatus="i">
    <div class="GrayTitle_big">
        <div dojoType="dijit.TitlePane" title="<b style='margin-left: 20px; color: #197D1A;'>${disc.name} - ${disc.type}</b>">
            <div>
                <table class='' cellpadding="0" cellspacing="0"><tr></tr>
                    <tr>
                        <td style="width: 170px; text-align: center;" class="rborder_for_grades_tab">
                            ${disc.teacherFio}
                        </td>
                        <td align="center" style="width: 25px;" class="rborder_for_grades_tab">
                            <c:out value="${disc.rating.work}"/> (${disc.worksNumber*100})
                        </td>
                        <td align="center" style="width: 25px;" class="rborder_for_grades_tab">
                            <c:out value="${disc.rating.reWork}"/>
                        </td>
                        <td align="center" style="width: 55px;" class="rborder_for_grades_tab">
                            <c:out value="${disc.rating.concludingWork}"/> (${disc.concludingWorksNumber*100})
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



<div class=list style="display: none;">
<table cellpadding="0" cellspacing="0"><tr style="height: 30px;">
<td align="center" style='background-color: #EFEFEF; border-bottom: 1px silver solid;width:86px'>Тесты</td>
<td align="center" style=' border-left: 1px silver solid;background-color: #EFEFEF; border-bottom: 1px silver solid;width:96px'>Итоговые тесты</td>
<td align="center" style=' border-left: 1px silver solid;background-color: #EFEFEF; border-bottom: 1px silver solid;width:90px'>К/р</td>
<td align="center" style=' border-left: 1px silver solid;background-color: #EFEFEF; border-bottom: 1px silver solid;width:90px'>С/р</td>
<td align="center" style=' border-left: 1px silver solid;background-color: #EFEFEF; border-bottom: 1px silver solid;width:80px'>Акт-ть</td>
<td align="center" style=' border-left: 1px silver solid;background-color: #EFEFEF; border-bottom: 1px silver solid;width:80px'>Оценка</td></tr>
</table><br>
<c:forEach items="${se.studentGrades}" var="disc">
    <div  class="disc_tab_name" ><span style="color:#0E689C"><c:out value="${disc.name}"/> - ${disc.type}</span><br>
    <span style='color:#505050'><c:out value="${disc.teacherFio}"/></span></div>
    <table class='' cellpadding="0" cellspacing="0"><tr></tr>
        <tr>
            <td align="center" style='background-color: #EFEFEF; border-bottom: 1px silver solid;width:43px; border-right: 1px solid silver;'>
                <c:out value="${disc.rating.work}"/>
            </td>
            <td align="center" style='background-color: #EFEFEF; border-bottom: 1px silver solid;width:43px'>
                <c:out value="${disc.rating.reWork}"/>
            </td>
            <td align="center" style='background-color: #EFEFEF;border-left: 1px silver solid; border-bottom: 1px silver solid;width:48px; border-right: 1px solid silver;'>
                <c:out value="${disc.rating.concludingWork}"/>
            </td>
            <td align="center" style='background-color: #EFEFEF; border-bottom: 1px silver solid;width:48px'>
                <c:out value="${disc.rating.concludingReWork}"/>
            </td>
            <td align="center" style=' border-left: 1px silver solid; background-color: #EFEFEF; border-bottom: 1px silver solid;width:90px'>
                <c:out value="${disc.rating.controlWorkPoints}"/>
            </td>
            <td align="center" style=' border-left: 1px silver solid; background-color: #EFEFEF; border-bottom: 1px silver solid;width:90px'>
                <c:out value="${disc.rating.lab}"/>
            </td>
            <td align="center" style=' border-left: 1px silver solid; background-color: #EFEFEF; border-bottom: 1px silver solid;width:80px'>
                <c:out value="${disc.rating.activity}"/>
            </td>
            <td align="center" style=' border-left: 1px silver solid; background-color: #EFEFEF; border-bottom: 1px silver solid;width:80px'>
                <p class="
                    <c:if test="${grade[4] eq 'Зачтено'}">
                        zach_grade_stgrades
                    </c:if>
                    <c:if test="${grade[4] eq 'Не зачтено'}">
                        nezach_grade_stgrades
                    </c:if>
                    "><c:out value="${disc.rating.grade}"/>&nbsp;</p></td>
            </tr>
    </table>
    <br>
</c:forEach>

 </div>
<div align="centr"><div class="otdelitel"></div></div>

</div>


<div class="prosto_tekst">
        <p class="help_part">Подсказки</p>
		<p class="comment">
	
	    Перед вами итоги <c:if test="${semestr eq 'w'}">зимней</c:if><c:if test="${semestr eq 's'}">летней</c:if> сессии по соответствующим дисциплинам.<br>
	В графе Итоговая оценка проставлены либо оценка, в случае если отчётность - экзамен,
	либо зачёт (незачёт) в случае отчётности - зачёт, по соответствующему предмету.<br>
	Если в графе Итоговая оценка пусто, то это означает, что преподаватель ещё не выставил результаты.<br>
	В графе Тесты стоят результаты прохождения тестов. Слева от "|" баллы за
	<c:if test="${semestr eq 'w'}">все тесты, справа - за переэкзаменовку</c:if><c:if test="${semestr eq 's'}">обычные тесты, справа - за итоговые тесты</c:if>.
	Форма постоянно обновляется.<br><br>
	<b>Примечание.</b> уточнить отчётность по дисциплине можно во вкладке "Учебный план на семестр".<br><br>
	
	<b>Тесты</b> - максимальный суммарный балл по тестам по данной дисциплине<br>
	<b>Контрольная работа</b> - контрольные работы соответственно учебному плану<br>
	<b>Самостоятельная работа</b> - любой другой вид работ, данный преподавателем (рефераты, лабораторные и т.д.)<br>
	<b>Активность</b> - оценивание студентов во время консультаций<br>
	<b>Итоговая оценка</b> - результирующая оценка по данной дисциплине<br>
	

	
	
	</p>
	<a class="help_part_href" onClick="javascript: scroll(0,0);">Наверх</a>
</div>