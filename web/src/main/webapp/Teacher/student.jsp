<%--
    Document   : otchet1
    Created on : 20.05.2009, 11:25:29
    Author     : KorgovVD
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="student.model.*" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<c:set var="se" value="${sessionScope[\"education\"]}"/>
<jsp:useBean id="student" class="student.model.Student"/>

<c:set var="semestr" value="${param[\"s\"]}"/>
<c:set var="id" value="${param[\"id\"]}"/>
<c:set var="disc" value="${param[\"disc\"]}"/>

<c:set target="${student.studentI}" property="id" value="${id}"/>
<c:set var="studentI" value="${student.byId}"/>

    <c:if test="${studentI.name eq null || id eq null  }">
        <c:redirect url="mainContent.jsp"/>
    </c:if>

<jsp:setProperty name="se" property="studentI" value="${studentI}" />
<jsp:setProperty name="se" property="semestr" value="${semestr}" />

<script>
    dojo.require("dijit.ProgressBar");
    function hide_tr(id,link_id){
        if(dojo.byId(id).style.display != 'none'){
            dojo.fx.wipeOut({node: id,duration: 250}).play();
            dojo.byId(link_id).setAttribute("class","show_hide_link_in_otch");
        }
        else{
            dojo.fx.wipeIn({node: id,duration: 250}).play();
            dojo.byId(link_id).setAttribute("class","show_hide_link_in_otch");
        }
    }
    //changeHref(${se.studentI.spec.id},${se.studentI.gr},${se.semestr},'${se.disc}')
</script>

<div class="title">
    <p style="float: left;">Отчет по студенту</p> <span class="msg1" style="float: right; margin-top: 4px; margin-right: 10px;"><a href="javascript:history.back()"><<<вернуться назад</a></span>
    <div class="otdelitel"></div>
</div>

<div class="head_main_g">


    <div align="left" style="float: left; margin-right: 30px;">
        <table cellpadding="0" cellspacing="0">
            <tr>
                <td class="fotoborder">
                    <img style="border: 3px solid white;"  id="photo" src="../loadPhoto?id=<c:out value="${studentI.id}"/>&type=student&size=big">
                </td>
            </tr>
            <tr>
                <td>
                    <br>

                    <div style="text-align: center; padding-bottom: 10px;">
                        <a class='send_mes_link_st_page' href="main.jsp#write_msg/id_send=<c:out value="${studentI.id}"/>&type_send=4&type_let=1"><img style="float: left; margin-left: 10px;" src="../test_images/pero.png"> <p style="margin: 0px; margin-top: -3px;">Написать письмо</p> <div class="otdelitel"></div></a>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <br>

                    <div style="text-align: center; padding-bottom: 10px;">
                        <a class='send_mes_link_st_page' href="main.jsp#grades/gr=${studentI.gr}&sp=${studentI.spec.id}&s=${se.semestr}">
  <c:if test= "${sessionScope[\"teacherItem\"].id!='45555' && sessionScope[\"teacherItem\"].id!='123425298' && sessionScope[\"teacherItem\"].id!='128826026'}" >
					<p style="margin: 0px; margin-top: -3px;">Перейти к группе</p>
 </c:if>

						<div class="otdelitel"></div></a>
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
<div>
    <c:forEach items="${se.teachersDisciplines}" var="dis">
            <c:set var="dName" value="${dis.name}"/>
            <c:set var="dId" value="${dis.id}"/>
    <jsp:setProperty name="se" property="disc" value="${dId}" />
    <p class="fio_stud_tests_otch" align="center">${dName}</p>
    <p class="title_of_otch">
        Тесты
    </p>
    <table class="otch_main_tab" cellpadding="0" cellspacing="0">
        <tr>
            <td class="first_stolb_hat_main_tab">
                <p>Название</p>
            </td>
            <td class="second_stolb_hat_main_tab">
                <p>Попыток</p>
            </td>
            <td class="third_stolb_hat_main_tab">
                <p>Правильных ответов</p>
            </td>
            <td class="fourth_stolb_hat_main_tab">
                <p>Результат</p>
            </td>
            <td class="fiveth_stolb_hat_main_tab">
                <p>Способ оценивания</p>
            </td>
        </tr>
        <c:forEach items="${se.testsResultsByDisc}" var="res" varStatus="i">
            <c:set var="test" value="${res.key}"/>
            <c:choose>
                <c:when test="${i.index %2 == 0}">
                    <c:set var="style_background" value="chet_color_back"/>
                </c:when>
                <c:otherwise>
                    <c:set var="style_background" value=""/>
                </c:otherwise>
            </c:choose>
            <tr  style="margin-left: 10px;" class="${style_background}">
                <td class="not_last_td_otch_page" >
                    <a href="javascript:void(0)" onclick="hide_tr('int_table<c:out value='${se.disc}${i.index}'/>',this)" class="show_hide_link_in_otch">
                        <c:out value="${test.title}"/>
                    </a>
                </td>
                <td class="not_last_td_otch_page">
                    <p class="p_in_td_tab_otch">
                        <c:out value="${test.attempts}"/> из <c:out value="${test.count}"/>
                    </p>
                </td>
                <td class="not_last_td_otch_page">
                    <p class="p_in_td_tab_otch">
                        &nbsp;-&nbsp;
                    </p>
                </td>
                <td class="not_last_td_otch_page">
                    <p class="p_in_td_tab_otch">
                        <c:out value="${test.points}"/>
                    </p>
                </td>
                <td class="last_td_otch_page">
                    <p class="p_in_td_tab_otch">
                        <c:out value="${test.estimation}"/>
                    </p>
                </td>
            </tr>
            <tbody id="int_table<c:out value='${se.disc}${i.index}'/>" style="margin: 0px; display: none;">
                <c:forEach items="${res.value}" var="result" varStatus="iterator">
                    <tr>
                        <td class="td_otch_page1">
                        <c:choose>
                            <c:when test="${test.writable}">
                                <a class="show_hide_link_in_otch" href="#testpassing/id=${result.id}&s=${semestr}">Попытка <c:out value='${iterator.index+1}'/></a>
                            </c:when>
                            <c:otherwise>
                                Попытка <c:out value='${iterator.count}'/>
                            </c:otherwise>
                        </c:choose>
                        </td>
                        <td class="td_otch_page1">&nbsp;</td>
                        <td class="td_otch_page1"><c:out value="${result.rightAnswers}"/> из <c:out value="${result.quantity}"/></td>
                        <td class="td_otch_page1"><c:out value="${result.points}"/></td>
                        <td class="td_otch_page1">${result.begin}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </c:forEach>
    </table>



    <p class="title_of_otch">
        Контрольные работы
    </p>
    <div>
                <div class="white_title_">
                    <c:forEach items="${se.studentControlWorks.controlWorks}" var="cws" varStatus="j">
                        <div class="white_title">
                           <table class='tab_contr_works' cellpadding='0' cellspacing='0'>
                                <tr>
                                    <td class="gray_words_1_2" class="white_title" class="tab_contr_works_big">
                                        <c:choose>
                                            <c:when test="${cws.attemptsQuantity ne 0}">
                                                <a href="javascript:gggg('table_for_razv${se.disc+j.index}')" >Контрольная ${cws.cwNumber}</a>
                                            </c:when>
                                            <c:otherwise>
                                                <p style="color: #147BC4; margin: 0px;">Контрольная ${cws.cwNumber}</p>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="gray_words_1_2"></td>
                                    <td class="gray_words_3_4">${cws.attemptsQuantity}</td>
                                    <td class="gray_words_3_4" class='n'><div id="inp1" onselectstart="return false"
                                    <c:choose>
                                            <c:when test="${cws.progress eq \"1\"}">style="color: #C0C0C0;" >Не проверено</c:when>
                                            <c:when test="${cws.progress eq \"2\"}">style="color: #FF4500;" >Не зачтено</c:when>
                                            <c:when test="${cws.progress eq \"3\"}">style="color: #2CBA2D;" >Зачтено</c:when>
                                            <c:otherwise>style="color: #C0C0C0;" >Не проверено</c:otherwise>
                                    </c:choose>
                                     </div><input type="hidden" value="${cws.progress}" name="progress" id="progress"/>
                                     <input type="hidden" value="${cw.id}" name="kod" id="kod"/></td>
                                     <td class="gray_words_5"><a href='#write_msg/id_send=<c:out value="${cw.id}"/>'<c:if test="${cws.grade > 0}">style="display: none"</c:if>><img src='../images/2ms.jpg'></a></td>
                                </tr>
                                <tr style="display: none;" id="table_for_razv${se.disc+j.index}">
                                    <td colspan="5">
                                        <div style="margin:0px; paddig:0px;">
                                            <table>
                                                <c:forEach items="${cws.cwAttempt}" var="cwAt" varStatus="k">
                                                    <tr class="gray_words">
                                                        <td  class="gray_words_1_2" style="width:150px;">попытка ${k.count}</td>
                                                        <td  class="gray_words_1_2">${cwAt.attemptDate}</td>

                                                          <td  class="gray_words_3_4" <c:if test="${cwAt.filePath eq \"empty\"}">>В рукописном</c:if><c:if test="${cwAt.filePath ne \"empty\"}">onclick="readTask(${se.disc},${cws.id},${cwAt.id})"><a href="../CWfiles/${cwAt.filePath}" >загрузить</a></c:if></td>
                                                        <td  class="gray_words_3_4"></td>
                                                        <td  class="gray_words_5"></td>
                                                    </tr>
                                                </c:forEach>
                                            </table>
                                        </div>
                                    </td>
                                </tr>
                           </table>
                        </div>
                        </c:forEach>
                </div>
            </div>
            <br>
                <br>
            <hr>
    </c:forEach>
</div>

<!--на данный момент не производится вывод данных для данных таблиц потому они статичны и скрыты-->
<div class="hidden_element_of_page">
    <div>
        <p class="title_of_otch">
            Лабораторные работы и тренажеры
        </p>
        <table class="otch_main_tab" cellpadding="0" cellspacing="0">
            <tr>
                <td class="first_stolb_hat_main_tab"><p>Название</td>
                <td class="second_stolb_hat_main_tab" style="width: 200px;"><p>Процент выполнения</p></td>
                <td class="third_stolb_hat_main_tab"><p>Макс. кол-во баллов</p></td>
                <td class="fiveth_stolb_hat_main_tab"><p>Баллов получено</p></td>
            </tr>
            <tr class="chet_color_back">
                <td class="not_last_td_otch_page" >Лабораторная. Модуль 1</td>
                <td class="not_last_td_otch_page"><div dojoType="dijit.ProgressBar" progress="10"></div></td>
                <td class="not_last_td_otch_page" >20</td>
                <td class="last_td_otch_page" >15</td>
            </tr>
            <tr>
                <td class="not_last_td_otch_page" >Лабораторная. Модуль 1</td>
                <td class="not_last_td_otch_page"><div dojoType="dijit.ProgressBar" progress="40"></div></td>
                <td class="not_last_td_otch_page" >20</td>
                <td class="last_td_otch_page" >15</td>
            </tr>
        </table>
    </div>
    <div>
        <p class="title_of_otch">
            Контрольные и самостоятельные работы
        </p>
        <table class="otch_main_tab" cellpadding="0" cellspacing="0">
            <tr>
                <td class="first_stolb_hat_main_tab"><p>Название</td>
                <td class="second_stolb_hat_main_tab" style="width: 200px;"><p>Процент выполнения</p></td>
                <td class="third_stolb_hat_main_tab"><p>Макс. кол-во баллов</p></td>
                <td class="fiveth_stolb_hat_main_tab"><p>Баллов получено</p></td>
            </tr>
            <tr class="chet_color_back">
                <td class="not_last_td_otch_page" >Контрольная работа. Модуль 1</td>
                <td class="not_last_td_otch_page"><div dojoType="dijit.ProgressBar" progress="10"></div></td>
                <td class="not_last_td_otch_page" >20</td>
                <td class="last_td_otch_page" >15</td>
            </tr>
            <tr>
                <td class="not_last_td_otch_page" >Самостоятельная работа. Модуль 1</td>
                <td class="not_last_td_otch_page"><div dojoType="dijit.ProgressBar" progress="40"></div></td>
                <td class="not_last_td_otch_page" >20</td>
                <td class="last_td_otch_page" >15</td>
            </tr>
        </table>
    </div>
    <div>
        <p class="title_of_otch">
            Семинарские занятия
        </p>
        <table class="otch_main_tab" cellpadding="0" cellspacing="0">
            <tr>
                <td class="first_stolb_hat_main_tab"><p>Название</td>
                <td class="second_stolb_hat_main_tab" style="width: 200px;"><p>Процент выполнения</p></td>
                <td class="third_stolb_hat_main_tab"><p>Макс. кол-во баллов</p></td>
                <td class="fiveth_stolb_hat_main_tab"><p>Баллов получено</p></td>
            </tr>
            <tr class="chet_color_back">
                <td class="not_last_td_otch_page" >Семинар. Модуль 1</td>
                <td class="not_last_td_otch_page"><div dojoType="dijit.ProgressBar" progress="10"></div></td>
                <td class="not_last_td_otch_page" >20</td>
                <td class="last_td_otch_page" >15</td>
            </tr>
        </table>
    </div>
</div>