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
<c:set target="${teacher.teacherI}"  property="id" value="${teacherId}"/>
<c:set var="teacherI"  value="${teacher.byId}"/>
    <c:if test="${teacherI.name == null || teacherId == null }">
        <c:redirect url="mainContent.jsp"/>
    </c:if>
<div class=head_main_g_prep>
<div align=left id="photo">

      <div align="left" style="float: left; margin-right: 30px;">
        <table cellpadding="0" cellspacing="0">
            <tr>
                <td class="fotoborder">
                  <img align="left" src="../loadPhoto?id=<c:out value="${teacherI.id}"/>&type=teacher&size=big">
           </td>
            </tr>
            <tr>
                <td>
                    <br>

                    <div style="text-align: center; padding-bottom: 10px;">
                    <a class='teh_a'  href="main.jsp#write_msg/id_send=<c:out value="${teacherI.id}"/>&type_send=3&type_let=1"><img src="../images/pero.png"> Написать письмо</a>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</div>

<table  class=ankc>
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
</table>
</div>
</div>
</div>
</div>
<div class=events  style="">

</div><br>
