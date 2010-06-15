<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%--
<c:set var="se" value="${sessionScope[\"education\"]}"/>
<c:set target="${se}" property="semestr" value="${se.currentSemestr}" />



<jsp:useBean class="journal.model.filter.StudentNewsLineFilter" id="filter" />
<c:set target="${filter}" property="umkList" value="${sessionScope[\"education\"].umkList}"/>
<c:set target="${filter}" property="userId" value="${sessionScope[\"studentItem\"].id}"/>


<jsp:useBean class="journal.Journal" id="journal"/>
<jsp:setProperty name="journal" property="filter" value="${filter}"/>

<c:set var="events" value="${journal.events}"/>--%>


<script>
    dojo.require("dijit.form.Button");
    dojo.require("dijit.form.Form");
    dojo.require("dijit.form.TextBox");
    dojo.require("dijit.form.Textarea");
    dojo.require("dijit.Dialog");
    dojo.require("dojo.io.iframe");
    dojo.require("dijit.InlineEditBox");
    dojo.require("dijit.ProgressBar");
    document.title = "ЦОО СГУ. Главная страница";
</script>
<div class="title">
    <p>Информация о студенте</p>
</div>
<div class="head_main_g">
    <div align="left" style="float: left; margin-right: 30px;">
        <table cellpadding="0" cellspacing="0">
            <tr>
                <td class="fotoborder">
                    <img style="border: 3px solid white;"  id="photo" src="../images/he.jpg">
                </td>
            </tr>
            <tr>
                <td>
                    <br>

                    <div class="load_photo_student_main" onclick="alert('У вас недостаточно прав для использования данного функционала.');">
                        <div dojoType="dijit.form.DropDownButton" class='Opa' disabled>
                            <p style='padding-top:-5px;'>Загрузить фото</p>
                            <div dojoType="dijit.TooltipDialog" title="Загрузка новой фотографии" id="tooltipDlg" open="false" href="">
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </div>
    <table  class="ankc">
        <tr><td><b>Фамилия:</b>&nbsp </td><td><div id="surname"><p>Иванов</p></div></td></tr>
        <tr><td><b>Имя:</b>&nbsp </td><td><div id="name"><p>Василий</p></div></td></tr>
        <tr><td><b>Отчество:</b>&nbsp </td><td><div id="second_name"><p>Петрович</p></div></td></tr>
        <tr><td><b>Дата рождения:</b>&nbsp </td><td><div id="birth"><p>1985-10-19</p></div></td></tr>
        <tr><td><b>Факультет:</b>&nbsp </td><td><div id="faculty"><p>Механико-Математический</p></div></td></tr>
        <tr valign=top><td><b>Специальность:</b>&nbsp </td><td><div id="spec"><p>Механика</p></div></td></tr>
        <tr><td><b>Курс:</b>&nbsp </td><td><div id="year"><p>3</p></div></td></tr>
        <tr><td><b>Группа</b>&nbsp </td><td><div id="gr"><p>331</p></div></td></tr>
    </table>
     <div class="otdelitel"></div>
    <%--<div class="load_photo_student_main">
        <div dojoType="dijit.form.DropDownButton" class='Opa'>
            <p style='padding-top:-5px;'>Загрузить фото</p>
            <div dojoType="dijit.TooltipDialog" title="Загрузка новой фотографии" id="tooltipDlg" open="false" href="../uploadFile.jsp">
            </div>
        </div>
    </div>--%>
</div>
<div class="edit_ssil">
    <div class="GrayTitle" id="gr_line">
        <div id="lichka" dojoType="dijit.TitlePane" open="" title="Личная информация">
            <div id="hobby" class="hobby" style="display:inline;">
                <table class="black_inline">
                    <tr><td class="VOrient_prep" valign=top><b class=n>Котактная информация</b></td><td valign=top><div dojoType="dijit.InlineEditBox"  autoSave="true" title="Нажмите, чтобы изменить контактную информацию" id="contact_i" class="contact_i"><c:out  value="${student.studentI.contact_i}" /></div></td></tr>
                    <tr><td class="VOrient_prep" valign=top><b class=n>Хобби</b></td><td valign=top><div dojoType="dijit.InlineEditBox"  autoSave="true" title="Нажмите, чтобы изменить хобби" id="hobbeis" class="hobbies"><c:out  value="${student.studentI.hobbeis}" /></div></td></tr>
                    <tr><td class="VOrient_prep" valign=top><b class=n>О себе</b></td><td valign=top><div dojoType="dijit.InlineEditBox"  autoSave="true" title="Нажмите, чтобы изменить информацию о себе" id="about_them" class="about_them"><c:out  value="${student.studentI.about_them}" /></div></td></tr>
                </table>
            </div>
        </div>
    </div> 
</div>
<div class=GrayTitle>
    <div dojoType="dijit.TitlePane" open="" title="Последние события">
        <div class="today_daybefore_all" >
            <div style="text-align: right; margin-right: 15px; margin-top: -4px;">
                <!--    <p class="for_jornalir_text_date" ><a href="">Сегодня </a>|&nbsp;</p>
                <p class="for_jornalir_text_date" ><a href="">Вчера </a>|&nbsp;</p>
                <p class="for_jornalir_text_date" ><a href="">Все</a></p>-->
            </div>

            <table cellspacing="0">
                <tbody>
                    <tr style="background: #EFEFFF;">
                        <td class="for_jornalir_img">
                            <img  src="../images/com_pic_1.jpg">
                        </td>
                        <td class="for_sobit_main_text" style="width: 356px;"><p class="p_for_jornalir">Преподаватель <a href="main.jsp#teacher">Позднева Инна Павловна</a> довыставил(а) оценки группе 331 специальности "Механика" </p></td>
                        <td class="for_jornalir_time"><p class="p_for_jornalir">12-12-2009</p></td>
                    </tr>
                    <tr>
                        <td class="for_jornalir_img">
                            <img  src="../images/engineer.png">
                        </td>
                        <td class="for_sobit_main_text" style="width: 356px;"><p class="p_for_jornalir"><a href="javascript:alert('У Вас недостаточно прав, для использования данного функционала!');">Добавлена новость "Обновления за февраль и система форумов".</a> </p></td>
                        <td class="for_jornalir_time"><p class="p_for_jornalir">12-12-2009</p></td>
                    </tr>
                    <tr>
                        <td class="for_jornalir_img">
                            <img  src="../images/engineer.png">
                        </td>
                        <td class="for_sobit_main_text" style="width: 356px;"><p class="p_for_jornalir"><p class="p_for_jornalir"><font color="#0000ff"><span style="background-color: rgb(255, 255, 255);">Уважаемые студенты!</span></font><br><font color="#ff4500">Режим "переэкзаменовки" завершен. Прохождение тестовых заданий и отправка контрольных работ приостановлены.</font><br><font color="#000000">Следите за выставлением отметок и "новостями" по поводу летнего семестра.</font><br></p> </p></td>
                        <td class="for_jornalir_time"><p class="p_for_jornalir">12-12-2009</p></td>
                    </tr>
                    
                </tbody>
            </table>
        </div>
    </div>
</div>

<%--
<div class=GrayTitle>
    <div dojoType="dijit.TitlePane" open="" title="Последние события">
        <p class="p_sobit_main_verx_ssilk"><a href="#events">Перейти к полному списку событий</a></p>
        <table cellspacing="0" width="490px;" style="margin: 5px auto;">
            <tbody>
                <c:forEach items="${events}" begin="0" var="event" varStatus="numb">
                    <tr  <c:if test="${numb.index %2 == 0}"> style="background: #f0f7fd;"</c:if>>
                        <td class="for_jornalir_img">
                        <c:if test="${event.userType.id == 2}">
                            <img  src="../images/engineer.png">
                        </c:if>
                        <c:if test="${event.userType.id == 3}">
                            <img  src="../images/com_pic_1.png">
                        </c:if>
                        <c:if test="${event.userType.id == 4}">
                            <img  src="../images/user-group.png">
                        </c:if>
                    </td>
                        <td class="for_sobit_main_text"><p class="p_sobit_main">${event}</p></td>
                        <td class="for_sobit_main_time"><p class="p_sobit_main">${event.timeString}</p></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>--%>
<div class="chengable_block" style="margin-right:10px">
    <div class="title">
        <p>Моя Группа</p>
    </div>

<table cellspacing="0" cellpadding="0" bgcolor="none" align="left" class='reit_table'>
    <tbody>
        <tr class="hat">
            <td class="justify" style='padding-left: 20px; border-bottom: 1px solid silver; background-color: white;'>Имя</td>
            <td align="center" width='200'style='border-bottom: 1px solid silver; background-color: white' >Рейтинг по Всем предметам</td>
        </tr>
        <tr bgcolor='white'>
            <td align='justify' valign='center' style='color:#75C6F5; padding-left: 10px;'>
                <a class='coo' href="javascript:alert('У вас недостаточно прав для использования данного функционала.')">Дмитриева Дарья Олеговна</a>
            </td>
            <td align="center" style="color: gray">
                1300
            </td>
        </tr>
        <tr bgcolor='#E5EDFF'>
            <td align='justify' valign='center' style='color:#75C6F5; padding-left: 10px;'>
                Иванов Василий Петрович
            </td>
            <td align="center" style="color: gray">
                1000
            </td>
        </tr>
        
        <tr bgcolor='white'>
            <td align='justify' valign='center' style='color:#75C6F5; padding-left: 10px;'>
                <a class='coo' href="javascript:alert('У вас недостаточно прав для использования данного функционала.')">Сидоров Геннадий Александрович</a>
            </td>
            <td align="center" style="color: gray">
                200
            </td>
        </tr>
         <tr bgcolor='white'>
            <td align='justify' valign='center' style='color:#75C6F5; padding-left: 10px;'>
    <a class='coo' href="javascript:alert('У вас недостаточно прав для использования данного функционала.')">Тепляков Андрей Витальевич</a>
            </td>
            <td align="center" style="color: gray">
                200
            </td>
        </tr>
       
    </tbody>
</table>



</div>
