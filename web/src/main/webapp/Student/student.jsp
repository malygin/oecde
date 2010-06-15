<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<jsp:useBean id="student" class="student.model.Student" scope="page"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    document.title = "ЦОО СГУ. Информация о студенте";
</script>
<div class=title>
<p>Информация о студенте</p>
</div>
<c:set var="studentId" value="${param[\"id\"]}"/>
<c:set target="${student.studentI}"  property="id" value="${studentId}"/>
<c:set var="studentI"  value="${student.byId}"/>
    <c:if test="${studentI.name eq null || studentId eq null  }">
        <c:redirect url="mainContent.jsp"/>
    </c:if>

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
					
                    <div style="text-align: center; padding-bottom: 10px;">
                        <a class='send_mes_link_st_page' href="main.jsp#write_msg/id_send=<c:out value="${studentI.id}"/>&type_send=4&type_let=1"><img style="float: left; margin-left: 10px;" src="../images/pero_student.png"> <p style="margin: 0px; margin-top: -3px;">Написать письмо</p> <div class="otdelitel"></div></a>
                    </div>
                </td>
            </tr>
        </table>
    </div>
	
	

    <table  class="ankc">
        <tr><td><b>Фамилия:</b>&nbsp </td><td><div id="surname"><p><c:out value="${studentI.surname}" /></p></div></td></tr>
        <tr><td><b>Имя:</b>&nbsp </td><td><div id="name"><p><c:out  value="${studentI.name}" /></p></div></td></tr>
        <tr><td><b>Отчество:</b>&nbsp </td><td><div id="second_name"><p><c:out  value="${studentI.second_name}" /></p></div></td></tr>
        <tr><td><b>Дата рождения:</b>&nbsp </td><td><div id="birth"><p><c:out  value="${studentI.birth}" /></p></div></td></tr>
        <tr><td><b>Факультет:</b>&nbsp </td><td><div id="faculty"><p><c:out  value="${studentI.faculty}" /></p></div></td></tr>
        <tr valign=top><td><b>Специальность:</b>&nbsp </td><td><div id="spec"><p><c:out  value="${studentI.spec.name}" /></p></div></td></tr>
        <tr><td><b>Курс:</b>&nbsp </td><td><div id="year"><p><c:out  value="${studentI.year}" /></p></div></td></tr>
        <tr><td><b>Группа</b>&nbsp </td><td><div id="gr"><p><c:out  value="${studentI.gr}" /></p></div></td></tr>
    </table>
    <div class="otdelitel"></div>

</div>
<!--<div class=head_main_g>
<div align=left id="photo"><img align="left" src="../loadPhoto?id=<c:out value="${studentI.id}"/>&type=student&size=big"></div>
<table  class="ankc">
<tr><td><b>Фамилия:</b>&nbsp </td><td><div id="surname"><p><c:out value="${studentI.surname}" /></p></div></td></tr>
<tr><td><b>Имя:</b>&nbsp </td><td><div id="name"><p><c:out  value="${studentI.name}" /></p></div></td></tr>
<tr><td><b>Отчество:</b>&nbsp </td><td><div id="second_name"><p><c:out  value="${studentI.second_name}" /></p></div></td></tr>
<tr><td><b>Дата рождения:</b>&nbsp </td><td><div id="birth"><p><c:out  value="${studentI.birth}" /></p></div></td></tr>
<tr><td><b>Факультет:</b>&nbsp </td><td><div id="faculty"><p><c:out  value="${studentI.faculty}" /></p></div></td></tr>
<tr valign=top><td><b>Специальность:</b>&nbsp </td><td><div id="spec"><p><c:out  value="${studentI.spec.name}" /></p></div></td></tr>
<tr><td><b>Курс:</b>&nbsp </td><td><div id="year"><p><c:out  value="${studentI.year}" /></p></div></td></tr>
<tr><td><b>Группа</b>&nbsp </td><td><div id="gr"><p><c:out  value="${studentI.gr}" /></p></div></td></tr>
</table>
</div>-->
<div class=edit_ssil><div class=GrayTitle id="gr_line">
<div dojoType="dijit.TitlePane" open="" title="Личная информация" id="lichka">
<div class=hobby style="display:inline;">
<table>
<tr><td class="VOrient_prep" valign=top><b class=n>Котактная информация</b></td><td valign=top><div id="contact_i"><p><c:out  value="${student.studentI.contact_i}" /></p></div></td></tr>
<tr><td class="VOrient_prep" valign=top><b class=n>Хобби</b></td><td valign=top><div id="hobbeis"><p><c:out  value="${student.studentI.hobbeis}" /></p></div></td></tr>
<tr><td class="VOrient_prep" valign=top><b class=n>О себе</b></td><td valign=top><div id="about_them"><p><c:out  value="${student.studentI.about_them}" /></p></div></td></tr>
</table>
</div>
</div>
</div>
</div>
<div class=events  style="">
</div><br>