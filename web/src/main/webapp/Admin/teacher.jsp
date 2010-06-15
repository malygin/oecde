<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<jsp:useBean id="teacher" class="teacher.model.Teacher" scope="page"/>
<jsp:useBean id="edu" class="teacher.education.TeacherEducationFactory" scope="page"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    dojo.require("dojo.parser");
    dojo.require("dijit.TitlePane");
    dojo.require("dijit.form.Button");
    dojo.require("dijit.form.Form");
    dojo.require("dijit.form.TextBox");
    dojo.require("dijit.ProgressBar");
    document.title="СГУ ЦОО. Информация о преподавателе"

var groupReport = function(percent){
	return "Оценено: " + Math.floor((percent*this.maximum)) + " из " + this.maximum + " групп";
}

var cwReport = function(percent){
	return "Просмотрено: " + Math.floor((percent*this.maximum)) + " из " + this.maximum + " работ";
}
</script>
<div class=title>
<p>Информация о преподавателе</p>
</div>
<c:set var="teacherId" value="${param[\"id\"]}"/>
<c:set target="${teacher.teacherI}"  property="id" value="${teacherId}"/>
<c:set var="teacherI"  value="${teacher.byId}"/>
    <c:if test="${teacherI.name == null || teacherId == null }">
        <c:redirect url="mainContent.jsp"/>
    </c:if>
<c:set target="${edu}"  property="teacher" value="${teacherI}"/>
<div class=head_main_g>


<div align="left" style="float: left; margin-right: 30px;">
        <table cellpadding="0" cellspacing="0">
            <tr>
                <td>
                    <div class="fotoborder" style="width: 106px !important;margin-left: auto; margin-right: auto; border: 1px solid #4DB7F1;">
                    <img style="border: 3px solid white;"  id="photo" src="../loadPhoto?id=<c:out value="${teacherI.id}"/>&type=teacher&size=medium">
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <br>
                    <div style="text-align: center; padding-bottom: 10px;">
                        <p align="center"><a class='send_mes_link_st_page'  href='anotherType.do?type=teacher&id=${teacherI.id}' >Зайти под преподавателем</a></p>
                        <p align="center"><a class='send_mes_link_st_page' href="main.jsp#write_msg/id_send=<c:out value="${teacherI.id}"/>&type_send=3&type_let=1">Написать письмо</a></p>
                        <p align="center"><a class='send_mes_link_st_page' href="javascript:deletePhoto(<c:out value="${teacherI.id},'teacher')"/>">Удалить фото</a></p>
                        <p id="error" align="center" style="color:white;visibility:hidden">Возникла ошибка</p>
                     </div>
                </td>
            </tr>
        </table>
    </div>

<table  class=ankc>
<tr><td><b>Айди:</b>&nbsp </td><td><div id="surname"><c:out value="${teacherI.id}" /></div></td></tr>
<tr><td><b>Фамилия:</b>&nbsp </td><td><div id="surname"><c:out value="${teacherI.surname}" /></div></td></tr>
<tr><td><b>Имя:</b>&nbsp </td><td><div id="name"><c:out value="${teacherI.name}" /></div></td></tr>
<tr><td><b>Очество:</b>&nbsp </td><td><div id="second_name"><c:out value="${teacherI.second_name}" /></div></td></tr>
<tr><td><b>Дата рождения:</b>&nbsp </td><td><div id="birth"><c:out value="${teacherI.birth}" /></div></td></tr>
<tr valign=top><td><b>Отдел</b>&nbsp </td><td><div id="spec"><c:out value="${teacherI.depart}" /></div></td></tr>
<tr><td><b>Должность:</b>&nbsp </td><td><div id="job"><c:out value="${teacherI.job}" /></div></td></tr>
<tr><td><b></b> </td><td></td></tr>
</table>

<div class="otdelitel"></div>
</div>
<div class=edit_ssil><div class=GrayTitle id="gr_line">
<div dojoType="dijit.TitlePane" open="" title="Личная информация" id="lichka">
<div class=hobby style="display:inline;">
<table>
<tr><td class=VOrient_prep valign=top><b class=n>Контактная информация</b></td><td valign=top><div id="contact_i"><c:out value="${teacherI.contact_i}" /></div></td></tr>
<tr><td class=VOrient_prep valign=top><b class=n>Хобби</b></td><td valign=top><div id="hobbeis"><c:out value="${teacherI.hobbeis}" /></div></td></tr>
<tr><td class=VOrient_prep valign=top><b class=n>О себе</b></td><td valign=top><div id="about_them"><c:out value="${teacherI.about_them}" /></div></td></tr>
<tr><td class=VOrient_prep valign=top><b class=n>Логин</b></td><td valign=top><div id="hobbeis"><c:out value="${teacherI.login}" /></div></td></tr>
<tr><td class=VOrient_prep valign=top><b class=n>Пароль</b></td><td valign=top><div id="about_them"><c:out value="${teacherI.pass}" /></div></td></tr>
</table>
</div>
</div>
</div>
</div>
<div class=events  style="">

</div><br>
<c:set var="teach" value="${edu.statisctic}"/>

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
                <br> Количество прочитанных заданий - <b style="color:red">${teach.statistic.readTasks}</b> раза
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

