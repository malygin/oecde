<%--
    Document   : controlworks_last
    Created on : 08.07.2009, 11:46:01
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="materials" value="${sessionScope[\"education\"]}"/>
<c:set var="s" value="${(param[\"s\"] eq 1)}"/>
<c:set target="${materials}" property="semestr" value="${s?1:0}" />
<c:set var="contrWorks" value="${materials.studentControlWorks}"/>


<script type="text/javascript">
    dojo.require("dojo.fx");
    dojo.require("dijit.Dialog");
    dojo.require("dojo.io.iframe");

    dojo.addOnLoad(
        function _dialogClassChanger(){
            var _class = "dijitDialog standart_style_of_dojo_dialog dijitContentPane";
            dojo.byId("cw_sender").setAttribute('class',_class);
            dojo.byId("cw_sender_content").style.display = "block";
        }
    );
    function gggg(id1){
        if(dojo.byId(id1).style.display == "none"){
            dojo.fx.wipeIn({node: id1, duration: 0}).play();
        }else{
            dojo.fx.wipeOut({node: id1, duration: 0}).play();
        }
    }

    function copyVal(){
        var _val = dojo.byId("realFileInput").value;
        dojo.byId("psevdoFileInput").value = _val;
    }
</script>

<div class="head_main_math">
<p>Мои контрольные работы

    <c:choose>
      <c:when test="${s}">
          переэкзаменовка
          <span style="margin-right: 5px; width: 120px; float: right;" ><a style="color: red;font-weight: bolder" class='stmat_link' href='#cw/s=0'>(летний семестр)</a></span>
      </c:when>
      <c:otherwise>
             летнего семестра
             <span style=" margin-right: 5px; width: 115px; float: right;" ><a style="color: red;font-weight: bolder" class='stmat_link' href='#cw/s=1'>(переэкзаменовка)</a></span>
      </c:otherwise>
    </c:choose>
</p>
</div>

<div class="title">
    <table class="gray_words" cellpadding='0' cellspacing='0'>
        <tr>
            <td class="gray_words_1_2">Преподаватель</td>
            <td class="gray_words_1_2" style="width: 150px;">&nbsp;</td>
            <td class="gray_words_3_4">попытки</td>
            <td class="gray_words_3_4">оценка</td>
            <td class="gray_words_5"></td>
        </tr>
    </table>
</div>
<c:forEach items="${contrWorks}" var="cw" varStatus="i">

    <div class="GrayTitle_big">
        <div dojoType="dijit.TitlePane" title="<b style='margin-left: 20px; color: #6495ED;'>${cw.name}</b>">
            <div>
                <div class="white_title<c:if test="${i.last}">_</c:if>">
                    <table class='tab_contr_works' cellpadding='0' cellspacing='0'>
                        <c:forEach items="${cw.controlWorks}" var="cws" varStatus="j">
                            <tr>
                                <c:if test="${j.index eq 0}">
                                    <td style="border-right: 1px solid silver; width: 150px;">${cw.teacherFio}</td>
                                </c:if>
                                <c:if test="${j.index ne 0}">
                                    <td style="border-right: 1px solid silver; width: 150px;"></td>
                                </c:if>
                                <td class="gray_words_1_2<c:if test="${!j.last}">_</c:if>" class="white_title" class='tab_contr_works_big'>
                                    <c:choose>
                                        <c:when test="${cws.attemptsQuantity ne 0}">
                                            <a href="javascript:gggg('table_for_razv${i.count}${j.count}')">Контрольная ${cws.cwNumber}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <p style="color: #147BC4; margin: 0px;">Контрольная ${cws.cwNumber}</p>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td class="gray_words_3_4<c:if test="${!j.last}">_</c:if>">${cws.attemptsQuantity}</td>
                                <td class="gray_words_3_4<c:if test="${!j.last}">_</c:if>" class='n'><div
                                <c:choose>
                                    <c:when test="${cws.progress eq \"1\"}">style="color: #black;" >На проверке</c:when>
                                    <c:when test="${cws.progress eq \"2\"}">style="color: #FF4500;" >Не зачтено</c:when>
                                    <c:when test="${cws.progress eq \"3\"}">style="color: #2CBA2D;" >Зачтено</c:when>
                                </c:choose></div>
                                </td>
                                <c:set var="bool" value="${cw.teacherFio eq \"\" ||
                                       cw.teacherFio eq \"Иванов В. А.\" ||
                                       cw.teacherFio eq \"Луньков А. Д.\" ||
                                       cw.teacherFio eq \"Позднева С. П.\" ||
                                       cw.teacherFio eq \"Непочатова И. С.\" ||
                                       cw.teacherFio eq \"Кузнецова И. А.\" ||
                                       cw.teacherFio eq \"Игнатьев М. Ю.\" ||
                                       cw.teacherFio eq \"Гусев В. И.\" ||
                                       cw.id eq \"15\"
                                       }"/>
                                <td class="gray_words_5<c:if test="${!j.last}">_</c:if>"><a href="javascript:ShowLoadFormCW(${cw.id}, ${cws.cwNumber},${s?1:0})" <c:if test="${cws.progress eq \"3\" || s || bool}">style="display:none"</c:if>><img src='../images/2ms.jpg'></a><c:if test="${bool}">В рукописном</c:if></td>
                            </tr>
                            <tr style="display: none;" id="table_for_razv${i.count}${j.count}">
                                <td colspan="5">
                                    <div >
                                        <table>
                                            <c:forEach items="${cws.cwAttempt}" var="cwAt" varStatus="k">
                                                <tr class="gray_words">
                                                    <td  class="gray_words_1_2">попытка ${k.count}</td>
                                                    <td  class="gray_words_1_2">${cwAt.attemptDate}</td>
                                                    <td  class="gray_words_3_4"><c:if test="${cwAt.filePath eq \"empty\"}">В рукописном</c:if><c:if test="${cwAt.filePath ne \"empty\"}"><a href="../CWfiles/${cwAt.filePath}" >загрузить</a></c:if></td>
                                                    <td  class="gray_words_3_4"><c:if test="${cwAt.read}">На проверке</c:if></td>
                                                    <td  class="gray_words_5"></td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>

</c:forEach>
<div dojoType="dijit.Dialog" id="cw_sender" title="Отправка контрольной работы" style="display: none;">
    <div id="cw_sender_content" class="cw_sender_content">
        <div class="attention_text_container_for_cw_upload">
            <p class="attention_text_for_cw_upload">
                <span style="color: red;">Внимание!</span> Перед вами интерфейс загрузки контрольной работы. Вы должны выбрать файл, в котором находится ваша контрольная
                и нажать на кнопку "Загрузить". Будьте Внимательны, этот файл засчитается как попытка отправки контрольной.
                Если после отправки в списке контрольной не появится еще одна попытка - перезагрузите страницу. В случае проблем - обращайтесь
                в службу техподдержки в правом меню.
            </p>
        </div>
        <br>
        <form id="ControlWorkForm" name="ControlWorkForm" method="post" enctype="multipart/form-data">
            <div class="" style=" margin: 0px; padding: 0px; float: left; height: 30px; position: relative;">
                <input id="realFileInput" class="" style="position: relative; z-index: 2; opacity: 0; filter: alpha(opacity=0);" type="file" name="file" onchange="copyVal()">
                <div style="position: absolute; z-index: 1; top: 0; left: 0;">
                    <div><input id="psevdoFileInput" type="text" style="float: left; margin-right: 5px; width: 185px;"> <input type="image" src="../images/UpLoadFileButton.png" style="margin-top: 3px; height: 16px; width: 16px;">
                    <div class="otdelitel"></div></div>
                </div>
            </div>
            <input class='uploadBtn' type="button" value="Загрузить" onclick="LoadControlWork();" style="width: 85px;">
            <div class="otdelitel"></div>
        </form>
    </div>
</div>

    <div align="centr"><div class="otdelitel"></div></div>

	<div class="prosto_tekst">
        <p class="help_part">Подсказки</p>
		<p class="comment">
            <b>Важно!</b><br>Перед тем как грузить контрольную работу - проверьте ее название, в нем не должно быть точек, запятых,
            слешей и прочих служебных символов. Хороший пример "Контрольная работа по социологии".<br><br>
            Перед вами интерфейс загрузки контрольных работ.<br>
            1. Кликнув на название контрольной, например "Контрольная 1", вы увидите список ваших
            попыток с датами, кликнув на "Загрузить" вы можете загрузить их на свой компьютер.
            <br>2. Чтобы добавить новую контрольную нужно нажать на иконку письма с зеленой стрелочкой
            в появившимся интерфейсе необходимо нажав на икону с желтой папкой выбрать нужный файл.
            Потом необходимо нажать на кнопку "Загрузить" и подождать.<br>
            <b>Внимание!</b> Файлы не должны быть слишком большими (не более 10 мегабайт).
            Если вам необходимо отправить файл больше по размеру - обратитесь к администратору.
            После того, как вы загрузить контрольную - преподаватель получит к ней доступ,сможет ее загрузить,
            проверить и написать вам через систему сообщений о том, что нужно исправить, либо поставит оценку.
            О том, что вам поставлена оценка, вы узнаете в личной ленте событий.<br>
			Если во время отправки произошла ошибка  - обратитесь к администратору в "Круглосуточной техподдержке" в левом меню.


        </p>
		<a class="help_part_href" onClick="javascript: scroll(0,0);">Наверх</a>
    </div>	