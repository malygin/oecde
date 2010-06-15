<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<jsp:useBean id="student" class="student.model.Student" scope="page"/>
<c:set var="mng" value="${sessionScope[\"management\"]}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    document.title = "ЦОО СГУ. Информация о студенте";
    dojo.require("dijit.layout.TabContainer");
    dojo.require("dijit.form.Form");
    function hide_tr(id,link_id){
        if(dojo.byId(id).style.display != 'none'){
            dojo.fx.wipeOut({
                node: id,
                duration: 250,
                onEnd: function _09(){
                    link_id.innerHTML = "";
                    link_id.innerHTML = "+";
                }
            }).play();
        }else{
            dojo.fx.wipeIn({
                node: id,
                duration: 250,
                onBegin: function _09(){
                    link_id.innerHTML = "";
                    link_id.innerHTML = "-";
                }
            }).play();
        }
            //dojo.byId(link_id).setAttribute("class","show_hide_link_in_otch");
    }
    function gggg(id1){
        if(dojo.byId(id1).style.display == "none"){
            dojo.fx.wipeIn({node: id1, duration: 0}).play();
        }else{
            dojo.fx.wipeOut({node: id1, duration: 0}).play();
        }
    }
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

<c:set target="${mng}" property="studentI" value="${studentI}"/>
<div class="head_main_g">
<div align="left" style="float: left; margin-right: 30px;">
        <table cellpadding="0" cellspacing="0">
            <tr>
                <td>
                    <div class="fotoborder" style="width: 106px !important;margin-left: auto; margin-right: auto; border: 1px solid #4DB7F1;">
                    <img style="border: 3px solid white;"  id="photo" src="../loadPhoto?id=<c:out value="${studentI.id}"/>&type=student&size=medium">
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <br>
                    <div style="text-align: center; padding-bottom: 10px;">
                        <p align="center"><a class='send_mes_link_st_page'  href='#stEvents/userId=${studentI.id}' >Просмотреть события студента</a></p>
                        <p align="center"><a class='send_mes_link_st_page' href="main.jsp#write_msg/id_send=<c:out value="${studentI.id}"/>&type_send=4&type_let=1">Написать письмо</a></p>
                        <p id="error" align="center" style="color:white;visibility:hidden">Возникла ошибка</p>
                     </div>
                </td>
            </tr>
        </table>
    </div>
<table  class="ankc">


<tr><td><b>Айди:</b>&nbsp </td><td><div id="surname"><p><c:out value="${studentI.id}" /></p></div></td></tr>
<tr><td><b>Фамилия:</b>&nbsp </td><td><div id="surname"><p><c:out value="${studentI.surname}" /></p></div></td></tr>
<tr><td><b>Имя:</b>&nbsp </td><td><div id="name"><p><c:out  value="${studentI.name}" /></p></div></td></tr>
<tr><td><b>Отчество:</b>&nbsp </td><td><div id="second_name"><p><c:out  value="${studentI.second_name}" /></p></div></td></tr>
<tr><td><b>Дата рождения:</b>&nbsp </td><td><div id="birth"><p><c:out  value="${studentI.birth}" /></p></div></td></tr>
<tr><td><b>Факультет:</b>&nbsp </td><td><div id="faculty"><p><c:out  value="${studentI.faculty}" /></p></div></td></tr>
<tr valign=top><td><b>Специальность:</b>&nbsp </td><td><div id="spec"><p><c:out  value="${studentI.spec.name}" /> (<c:out  value="${studentI.spec.id}" />)</p></div></td></tr>
<tr><td><b>Курс:</b>&nbsp </td><td><div id="year"><p><c:out  value="${studentI.year}" /></p></div></td></tr>
<tr><td><b>Группа:</b>&nbsp </td><td><div id="gr"><p><c:out  value="${studentI.gr}" /></p></div></td></tr>
<tr><td><b>Город:</b>&nbsp </td><td><div id="gr"><p><c:out  value="${studentI.city.name}" /> (<c:out  value="${studentI.city.id}" />)</p></div></td></tr>
</table>
</div>

<div class=edit_ssil><div class=GrayTitle id="gr_line">
<div dojoType="dijit.TitlePane" open="false" title="Личная информация" id="lichka">
<div class=hobby style="display:inline;">
<table>
<tr><td class="VOrient_prep" valign=top><b class=n>Котактная информация</b></td><td valign=top><div id="contact_i"><p><c:out  value="${studentI.contact_i}" /></p></div></td></tr>
<tr><td class="VOrient_prep" valign=top><b class=n>Хобби</b></td><td valign=top><div id="hobbeis"><p><c:out  value="${studentI.hobbeis}" /></p></div></td></tr>
<tr><td class="VOrient_prep" valign=top><b class=n>О себе</b></td><td valign=top><div id="about_them"><p><c:out  value="${studentI.about_them}" /></p></div></td></tr>
<tr><td class="VOrient_prep" valign=top><b class=n>Домашний телефон</b></td><td valign=top><div id="phone"><p><c:out  value="${studentI.phone_home}" /></p></div></td></tr>
<tr><td class="VOrient_prep" valign=top><b class=n>Мобильный телефон</b></td><td valign=top><div id="cellphone"><p><c:out  value="${studentI.phone_mobile}" /></p></div></td></tr>
<tr><td class="VOrient_prep" valign=top><b class=n>ICQ</b></td><td valign=top><div id="icq"><p><c:out  value="${studentI.icq}" /></p></div></td></tr>
<tr><td class="VOrient_prep" valign=top><b class=n>Логин</b></td><td valign=top><div id="cellphone"><p><c:out  value="${studentI.login}" /></p></div></td></tr>
<tr><td class="VOrient_prep" valign=top><b class=n>Пароль</b></td><td valign=top><div id="icq"><p><c:out  value="${studentI.pass}" /></p></div></td></tr>
</table>
</div>
</div>
</div>
</div>


<div class="centered_tabs_of_tabContainer">
   <div id="mainTabContainer" tabStrip="true" nested="true" doLayout dojoType="dijit.layout.TabContainer"  style="width:522px; height:1000px;" >

      <div id="gTabContainer" class="ws_selecting" tabStrip="true"  doLayout dojoType="dijit.layout.TabContainer"  title="Оценки" style="width:50px; height:990px;" >
         <div id="grades" dojoType="dojox.layout.ContentPane" selected href="grades.jsp?s=0&id=<c:out  value="${studentI.id}"/>" title="Текущий семестр" loadingMessage="" parseOnLoad selected></div>
          <div id="reGrades" dojoType="dojox.layout.ContentPane" href="grades.jsp?s=1&id=<c:out  value="${studentI.id}"/>" title="Предыдущий семестр" loadingMessage="" parseOnLoad selected></div>
      </div>

      <div id="cWTabContainer" class="ws_selecting" tabStrip="true"  doLayout dojoType="dijit.layout.TabContainer"  title="Контрольные" style="width:522px; height:990px;" >
         <div id="cw" dojoType="dijit.layout.ContentPane" selected href="controlworks_last.jsp?s=0&id=<c:out  value="${studentI.id}"/>" title="Текущий семестр" loadingMessage="" parseOnLoad refreshOnShow preload="false" ></div>
         <div id="reCw" dojoType="dijit.layout.ContentPane" href="controlworks_last.jsp?s=1&id=<c:out  value="${studentI.id}"/>" title="Предыдущий семестр" loadingMessage="" parseOnLoad refreshOnShow preload="false" ></div>
       </div>

       <div id="tTabContainer" class="ws_selecting" tabStrip="true" doLayout dojoType="dijit.layout.TabContainer"  title="Тесты" style="width:522px; height:990px;" >
            <div id="tests" dojoType="dijit.layout.ContentPane" selected href="testresults.jsp?s=0&id=<c:out  value="${studentI.id}"/>" title="Текущий семестр" loadingMessage="" parseOnLoad refreshOnShow preload="false" ></div>
           <div id="retests" dojoType="dijit.layout.ContentPane" href="testresults.jsp?s=1&id=<c:out  value="${studentI.id}"/>" title="Предыдущий семестр" loadingMessage="" parseOnLoad refreshOnShow preload="false" ></div>
       </div>
  </div>
</div>