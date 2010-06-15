<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>


<jsp:useBean class="journal.model.filter.TeacherFilterForOneUmk" id="filter" />

<jsp:useBean class="journal.Journal" id="journal"/>
<jsp:setProperty name="journal" property="filter" value="${filter}"/>

<c:set var="events" value="${journal.events}"/>


<script type="text/javascript">
    dojo.require("dijit.form.Button");
    dojo.require("dijit.form.Form");
    dojo.require("dijit.form.TextBox");
    dojo.require("dijit.form.Textarea");
    dojo.require("dijit.Dialog");
    dojo.require("dojo.io.iframe");
    dojo.require("dijit.ProgressBar");
    document.title = "ЦОО СГУ. Главная страница";
</script>

<div class="title">
   <p>Информация о преподавателе</p>
</div>
<div class="head_main_g_prep">


    <div align="left" style="float: left; margin-right: 30px;">
        <table cellpadding="0" cellspacing="0">
            <tr>
                <td class="fotoborder">
                    <img style="border: 3px solid white;" id="photo" align="left" src="../loadPhoto?id=<c:out value="${sessionScope[\"teacherItem\"].id }"/>&type=teacher&size=big">
                </td>
            </tr>
            <tr>
                <td>
                    <br>

                    <div class="load_photo_student_main">
                        <div dojoType="dijit.form.DropDownButton" class='Opa'>
                            <p style='padding-top:-5px;'>Загрузить фото</p>
                            <div dojoType="dijit.TooltipDialog" title="Загрузка новой фотографии" id="tooltipDlg" open="false" href="../uploadFile.jsp">
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </div>



    <table  class="ankc">
      <tr><td><b>Фамилия:</b>&nbsp </td><td><div id="surname"><c:out value="${sessionScope[\"teacherItem\"].surname }"/></div></td></tr>
      <tr><td><b>Имя:</b>&nbsp </td><td><div id="name"><c:out  value="${sessionScope[\"teacherItem\"].name}" /></div></td></tr>
      <tr><td><b>Очество:</b>&nbsp </td><td><div id="second_name"><c:out  value="${sessionScope[\"teacherItem\"].second_name}" /></div></td></tr>
      <tr><td><b>Дата рождения:</b>&nbsp </td><td><div id="birth"><c:out  value="${sessionScope[\"teacherItem\"].birth}" /></div></td></tr>
      <tr><td><b>Департамент</b>&nbsp </td><td><div id="faculty"><c:out  value="${sessionScope[\"teacherItem\"].depart}" /></div></td></tr>
      <tr><td><b>Должность</b>&nbsp </td><td><div id="spec"><c:out  value="${sessionScope[\"teacherItem\"].job}" /></div></td></tr>
      <tr><td><b><br></b>&nbsp </td><td></td></tr>
    </table>
    <div class="otdelitel"></div>
</div>
<div class="edit_ssil">
        <div class="GrayTitle" id="gr_line">
            <div id="lichka" dojoType="dijit.TitlePane" open="" title="Личная информация">
                <div class="hobby" style="display:inline;">
                   <table>
                      <tr><td class="VOrient_prep" valign=top><b class=n>Контакты</b></td><td valign=top><div id="contact_i"><c:out  value="${sessionScope[\"teacherItem\"].contact_i}" /></div></td></tr>
                      <tr><td class="VOrient_prep" valign=top><b class=n>Научные интересы</b></td><td valign=top><div id="hobbeis"><c:out  value="${sessionScope[\"teacherItem\"].hobbeis}" /></div></td></tr>
                      <tr><td class="VOrient_prep" valign=top><b class=n>О себе</b></td><td valign=top><div id="about_them"><c:out  value="${sessionScope[\"teacherItem\"].about_them}" /></div></td></tr>
                   </table>
                </div>

            </div>
        </div>
</div>

<div class="title">
    <p>
        Последние 5 контрольных
    </p>
</div>
                   
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:useBean id="cwl" class="teacher.education.ControlWorksList" />
<c:set var="se" value="${sessionScope[\"education\"]}"/>
<c:set target="${cwl}" property="year" value="${se.currentYear}"/>
<c:set target="${cwl}" property="semestr" value="${se.currentSemestr}"/>
<c:set target="${cwl}" property="id" value="${sessionScope[\"teacherItem\"].id}"/>
<c:set target="${cwl}" property="length"  value="5" />
<c:set target="${cwl}" property="page" value="1"/>
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
                    <td rowspan="2" style="background: ${color}; padding: 0 10px 5px 0; text-align: center; border-bottom: 1px solid silver;"><div class="msg1"><a href="#cw/gr=${hm.value.gr}&sp=${hm.value.spec.id}&s=0">(${hm.value.gr}гр.)</a></div></td>
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
<c:set var="se" value="${sessionScope[\"education\"]}"/>
<c:set var="teach" value="${se.statisctic}"/>

<div class="title">
    <p>
        Статистика
    </p>
</div>
<div>
    <div style="padding: 10px 17px 10px 17px;">
        <div>
            <p class="title_before_prbar">
                Всего групп/оценено
            </p>
        </div>
        <div class="green_prBar">
            <div class="progress_bar_position" maximum="${teach.statistic.groupsCount}" report="groupReport" progress="${teach.statistic.gradedGroupsCount}" dojoType="dijit.ProgressBar"></div>
        </div>
        <div>
            <p class="little_p">
                <br> Занятий прочитано <b style="color:red">${teach.statistic.readTasks}</b> раза
            </p>
        </div>
    </div>
    <div style="margin: 10px 17px 10px 17px; border-top: 1px solid silver; padding-top: 10px;">
        <div>
            <p class="title_before_prbar">
                 Контрольных прислано/просмотрено
            </p>
        </div>
        <div class="materials_page">
            <div class="progress_bar_position" maximum="${teach.statistic.cwAttemptsCount}" report="cwReport" progress="${teach.statistic.readCwCount}" dojoType="dijit.ProgressBar"></div>
        </div>
        <div>
            <p class="little_p">
             <br> Количество пройденныт тестов - <b style="color:red">${teach.statistic.testPassed}</b> прохождения
            </p>
        </div>
    </div>
</div>
