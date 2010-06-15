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
<div class=head_main_g>
<div align="left" style="float: left; margin-right: 30px;">
    <table cellpadding="0" cellspacing="0">
        <tr>
            <td class="fotoborder">
                <img style="border: 3px solid white;"  id="photo" src="../images/he.jpg">
            </td>
        </tr>
    </table>
</div>
<table  class="ankc">
<tr><td><b>Фамилия:</b>&nbsp </td><td><div id="surname"><p>Дмитриева</p></div></td></tr>
<tr><td><b>Имя:</b>&nbsp </td><td><div id="name"><p>Дарья</p></div></td></tr>
<tr><td><b>Отчество:</b>&nbsp </td><td><div id="second_name"><p>Олеговна</p></div></td></tr>
<tr><td><b>Дата рождения:</b>&nbsp </td><td><div id="birth"><p>1989-12-12</p></div></td></tr>
<tr><td><b>Факультет:</b>&nbsp </td><td><div id="faculty"><p>Механико-Математический</p></div></td></tr>
<tr valign=top><td><b>Специальность:</b>&nbsp </td><td><div id="spec"><p>Прикладная информатика в экономике</p></div></td></tr>
<tr><td><b>Курс:</b>&nbsp </td><td><div id="year"><p>3</p></div></td></tr>
<tr><td><b>Группа</b>&nbsp </td><td><div id="gr"><p>321</p></div></td></tr>
</table>
</div>
<div class=edit_ssil><div class=GrayTitle id="gr_line">
<div dojoType="dijit.TitlePane" open="" title="Личная информация" id="lichka">
<div class=hobby style="display:inline;">
<table>
<tr><td class="VOrient_prep" valign=top><b class=n>Котактная информация</b></td><td valign=top><div id="contact_i"><p>81234567890</p></div></td></tr>
<tr><td class="VOrient_prep" valign=top><b class=n>Хобби</b></td><td valign=top><div id="hobbeis"><p>Вышивание крестиком</p></div></td></tr>
<tr><td class="VOrient_prep" valign=top><b class=n>О себе</b></td><td valign=top><div id="about_them"><p>Студент</p></div></td></tr>
</table>
</div>
</div>
</div>
</div>
<div class=events  style="">
</div><br>