<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<jsp:useBean id="teacher" class="teacher.model.Teacher" scope="page"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    dojo.require("dojo.parser");
    dojo.require("dijit.TitlePane");
    dojo.require("dijit.form.Button");
    dojo.require("dijit.form.Form");
    dojo.require("dijit.form.TextBox");
    document.title="СГУ ЦОО. Информация о преподавателе"
</script>
<div class=title>
<p>Информация о преподавателе</p>
</div>
<c:set var="teacherId" value="${param[\"id\"]}"/>
<%--<c:set target="${teacher.teacherI}"  property="id" value="${teacherId}"/>
<c:set var="teacherI"  value="${teacher.byId}"/>
    <c:if test="${teacherI.name == null || teacherId == null }">
        <c:redirect url="mainContent.jsp"/>
    </c:if>--%>
<div class=head_main_g_prep>
<div align="left" style="float: left; margin-right: 30px;">
        <table cellpadding="0" cellspacing="0">
            <tr>
                <td class="fotoborder">
                    <img align="left" style="border: 3px solid white;"   src="../images/he.jpg"/>
                </td>
            </tr>
            <tr>
                <td>
                    <br>


                    <div style="text-align: center; padding-bottom: 10px;">
                        <a class='send_mes_link_st_page' style="color:black" href="javascript:alert('У Вас недостаточно прав, для использования данного функционала!');"><img style="float: left; margin-left: 10px;" src="../images/pero_student.png"> <p style="margin: 0px; margin-top: -3px;">Написать письмо</p> <div class="otdelitel"></div></a>
                    </div>
                </td>
            </tr>
        </table>
    </div>

<table  class=ankc>
<tr><td><b>Фамилия:</b>&nbsp </td><td><div id="surname">Позднева</div></td></tr>
<tr><td><b>Имя:</b>&nbsp </td><td><div id="name">Инна</div></td></tr>
<tr><td><b>Очество:</b>&nbsp </td><td><div id="second_name">Павловна</div></td></tr>
<tr><td><b>Дата рождения:</b>&nbsp </td><td><div id="birth">1965-12-12</div></td></tr>
<tr valign=top><td><b>Отдел</b>&nbsp </td><td><div id="spec"></div></td></tr>
<tr><td><b>Должность:</b>&nbsp </td><td><div id="job">Доцент</div></td></tr>
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
</table>
</div>
</div>
</div>
</div>
<div class=events  style="">

</div><br>
