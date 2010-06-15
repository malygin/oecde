<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<c:set var="se" value="${sessionScope[\"education\"]}"/>
<c:set target="${se}" property="semestr" value="0" />



<jsp:useBean class="journal.model.filter.StudentNewsLineFilter" id="filter" />
<c:set target="${filter}" property="umkList" value="${sessionScope[\"education\"].umkList}"/>
<c:set target="${filter}" property="userId" value="${sessionScope[\"studentItem\"].id}"/>


<jsp:useBean class="journal.Journal" id="journal"/>
<jsp:setProperty name="journal" property="filter" value="${filter}"/>

<c:set var="events" value="${journal.events}"/>


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
                    <img style="border: 3px solid white;"  id="photo" src="../loadPhoto?id=<c:out value="${student.studentI.id}"/>&type=student&size=big">
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
        <tr><td><b>Фамилия:</b>&nbsp </td><td><div id="surname"><p><c:out value="${sessionScope[\"studentItem\"].surname}" /></p></div></td></tr>
        <tr><td><b>Имя:</b>&nbsp </td><td><div id="name"><p><c:out  value="${sessionScope[\"studentItem\"].name}" /></p></div></td></tr>
        <tr><td><b>Отчество:</b>&nbsp </td><td><div id="second_name"><p><c:out  value="${sessionScope[\"studentItem\"].second_name}" /></p></div></td></tr>
        <tr><td><b>Дата рождения:</b>&nbsp </td><td><div id="birth"><p><c:out  value="${sessionScope[\"studentItem\"].birth}" /></p></div></td></tr>
        <tr><td><b>Факультет:</b>&nbsp </td><td><div id="faculty"><p><c:out  value="${sessionScope[\"studentItem\"].faculty}" /></p></div></td></tr>
        <tr valign=top><td><b>Специальность:</b>&nbsp </td><td><div id="spec"><p><c:out  value="${sessionScope[\"studentItem\"].spec.name}" /></p></div></td></tr>
        <tr><td><b>Курс:</b>&nbsp </td><td><div id="year"><p><c:out  value="${sessionScope[\"studentItem\"].year}" /></p></div></td></tr>
        <tr><td><b>Группа</b>&nbsp </td><td><div id="gr"><p><c:out  value="${sessionScope[\"studentItem\"].gr}" /></p></div></td></tr>
    </table>
    <div class="otdelitel"></div>

</div>
<div class="edit_ssil">
    <div class="GrayTitle" id="gr_line">
        <div id="lichka" dojoType="dijit.TitlePane" open="" title="Личная информация">
            <div id="hobby" class="hobby" style="display:inline;">
                <table class="black_inline">
                    <!--<tr><td class="VOrient_prep" valign=top><b class=n>Котактная информация</b></td><td valign=top><div dojoType="dijit.InlineEditBox" onChange="save_info(this.id,arguments[0])"  autoSave="true" title="Нажмите, чтобы изменить контактную информацию" id="contact_i" class="contact_i"><c:out  value="${student.studentI.contact_i}" /></div></td></tr>-->
                    <tr><td class="VOrient_prep" valign=top><b class=n>Хобби</b></td><td valign=top><div dojoType="dijit.InlineEditBox" onChange="save_info(this.id,arguments[0])"  autoSave="true" title="Нажмите, чтобы изменить хобби" id="hobbeis" class="hobbies"><c:out  value="${student.studentI.hobbeis}" /></div></td></tr>
                    <tr><td class="VOrient_prep" valign=top><b class=n>О себе</b></td><td valign=top><div dojoType="dijit.InlineEditBox" onChange="save_info(this.id,arguments[0])"  autoSave="true" title="Нажмите, чтобы изменить информацию о себе" id="about_them" class="about_them"><c:out  value="${student.studentI.about_them}" /></div></td></tr>
                </table>
            </div>
        </div>
    </div>
</div>
<div class=GrayTitle>
    <div dojoType="dijit.TitlePane" open="" title="Последние события">
        <p class="p_sobit_main_verx_ssilk"><a href="#events">Перейти к полному списку событий</a></p>
        <table cellspacing="0" width="490px;" style="margin: 5px auto;" class="latest_students_events">
            <tbody>
                <c:forEach items="${events}" begin="0" var="event" varStatus="numb">
                    <tr  <c:if test="${numb.index %2 == 0}"> style="background: #EFEFFF;"</c:if>>
						<td>&nbsp;&nbsp;&nbsp;</td>
                        <td class="for_jornalir_img">
                            <img src="../images/evLineIco/event${event.eventType.id}.png">
			</td>
                        <td class="for_sobit_main_text"><p class="p_sobit_main">${event}</p></td>
                        <td class="for_sobit_main_time"><p class="p_sobit_main">${event.timeString}</p></td>
			<td>&nbsp;&nbsp;&nbsp;</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div class="chengable_block" style="margin-right:10px">
    <div class="title">
        <p>Моя Группа</p>
    </div>

<table cellspacing="0" cellpadding="0" bgcolor="none" align="left" class='reit_table'>
        <tbody>
            <tr class="hat" >
                <td class="justify" style='padding-left: 20px; border-bottom: 1px solid silver; background-color: white;'>Имя</td>
                <td align="center" width='200'style='border-bottom: 1px solid silver; background-color: white' >Рейтинг по Всем предметам</td>
            </tr>
            <c:forEach items="${se.groupRating}" var="rs">
                <tr bgcolor='<c:if test="${rs.id eq student.studentI.id}">#E5EDFF</c:if><c:if test="${rs.id ne student.studentI.id}">white</c:if>'>
                    <td align='justify' valign='center' style='color:#75C6F5; padding-left: 10px;'>
                        <c:if test="${rs.id eq student.studentI.id}">${rs.surname} ${rs.name}</c:if>
                        <c:if test="${rs.id ne student.studentI.id}"><a class='coo' href="#student/id=<c:out value="${rs.id}"/>">${rs.surname} ${rs.name}</a></c:if>
                    </td>
                    <td align="center" style="color: gray">
                        ${rs.rating.sum}
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>



</div>
