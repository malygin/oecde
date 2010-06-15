<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
 <%@include  file="../WEB-INF/Teacher/jspf/setVariables.jspf" %>
<script type="text/javascript">
    dojo.require("dijit.form.Button");
    dojo.require("dojo.fx");
    document.title = "ЦОО СГУ. Контрольные работы";
</script>
<script type="text/javascript">
    //console.debug(234)
    makeTabsVisible();
</script>

 <c:set var="title" value="Контрольные работы группы"/>

<c:set var="hashName" value="cw"/>
 <div style="margin: 5px;">
 <%@include  file="../WEB-INF/Teacher/jspf/header.jspf" %>
<c:set target="${se}" property="id" value="${cw}"/>
<c:set var="contrWorks" value="${se.groupControlWorks}"/>
<script type="text/javascript">
semestr = ${se.studentI.year*2-se.currentSemestr-se.semestr};
    disc = ${se.disc};
    dojo.query("input[type='text']").onfocus(function f443(evt){
        if(evt.target.value == '0')
            evt.target.value = ''
    })
    dojo.query("input[type='text']").onblur(function f424(evt){
        if(evt.target.value == ''){
            evt.target.value = '0';
        }
    })
</script>

<div class="GrayTitle">
    <div dojoType="dijit.TitlePane" title="Нажмите, чтобы добавить или убрать контрольную работу">
        <br>
        <table align="center"><tr>
        <td>  <button dojoType="dijit.form.Button" onclick="incrementCw(${studentI.spec.id},'plus')" class="stand_button_new" >
                Добавить контрольную
        </button></td>
        <td width="30%"><div>Сейчас у группы <b style="color:red">${cw}</b> контрольных</div></td>
        <td>  <button dojoType="dijit.form.Button" <c:if test="${cw eq 0}">disabled</c:if> onclick="incrementCw(${studentI.spec.id},'minus')" class="stand_button_new" >
                Убрать контрольную
        </button></td>
       </tr> </table>
<br>
    </div>
</div>
<div class="title" style="width: auto !important;">
<table cellspacing="0" cellpadding="0" class="gray_words">
    <tbody>
        <tr style="line-height: 22px;">
        <td class="gray_words_1_2">ФИО студента</td>
        <td class="gray_words_1_2">дата</td>
        <td class="gray_words_3_4">попытки</td>
        <td class="gray_words_3_4">оценка</td>
        <td class="gray_words_5"/>
        </tr>
    </tbody>
</table>
</div>
    <form name="cwValues" >
<c:forEach items="${contrWorks}" var="cw" varStatus="i">

    <div class="GrayTitle_big">
        <input type="hidden" name="kod" value="${cw.id}">
         <input type="hidden" name="s" value="${se.studentI.year*2-se.currentSemestr-se.semestr}">
                 <c:if test="${cw.rating.dateUpdate ne null}">
                    <c:set var="u" value="true"/>
                 </c:if>
        <div style="height: 20px; padding-top: 15px; border-bottom: 1px solid silver; border-top: 1px solid silver;"><b style='margin-left: 20px; '><a style='color: #6495ED;text-decoration:none' href='#student/id=${cw.id}'>${cw.surname} ${cw.name} ${cw.secondName}</a></b><span style="margin-top: -5px; margin-right: 15px; width: 95px; float: right;" >баллы: <input name="work" type="text" value="${cw.rating.controlWorkPoints}" size="3" maxlength="3"/></span></div>
            <div>
                <div class="white_title<c:if test="${i.last}">_</c:if>">
                    <c:forEach items="${cw.controlWorks}" var="cws" varStatus="j">
                        <div class="white_title">
                           <table class='tab_contr_works' cellpadding='0' cellspacing='0'>
                               <tr style="border-bottom:1px solid silver">
                                    <td class="gray_words_1_2" class="white_title" class="tab_contr_works_big">
                                        <c:choose>
                                            <c:when test="${cws.attemptsQuantity ne 0}">
                                                <a href="javascript:gggg('table_for_razv${i.index+j.index}')" >Контрольная ${cws.cwNumber}</a>
                                            </c:when>
                                            <c:otherwise>
                                                <p style="color: #147BC4; margin: 0px; text-align: center;">Контрольная ${cws.cwNumber}</p>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="gray_words_1_2"><a href='javascript:sendEmptyCw(${cw.id},${cws.cwNumber})' style='color: #3A97C7'>Отметить как рукописную</a></td>
                                    <td class="gray_words_3_4">${cws.attemptsQuantity}</td>
                                    <td class="gray_words_3_4" class='n'><div onselectstart="return false" onclick="text_g(this)"
                                    <c:choose>
                                            <c:when test="${cws.progress eq \"1\"}">style="color: #black; text-align: center;" >Не проверено</c:when>
                                            <c:when test="${cws.progress eq \"2\"}">style="color: #FF4500; text-align: center;" >Не зачтено</c:when>
                                            <c:when test="${cws.progress eq \"3\"}">style="color: #2CBA2D; text-align: center;" >Зачтено</c:when>
                                            <c:otherwise>style="color: #C0C0C0; text-align: center;" >Нет в наличии</c:otherwise>
                                      </c:choose>
                                            </div><input type="hidden" value="${cws.progress}" name="progress" id="progress"/>
                                     <input type="hidden" value="${cws.id}" name="id" id="id"/></td>
                                     <td class="gray_words_5"><a href='#write_msg/id_send=<c:out value="${cw.id}"/>&type_send=4&type_let=1'<c:if test="${cws.progress > 2}">style="display: none"</c:if>><img src='../images/2ms.jpg'></a></td>
                                </tr>
                                <tr id="table_for_razv${i.index+j.index}">
                                    <td colspan="5">
                                        <div style="margin:0px; paddig:0px;">
                                            <table cellpadding="0" cellspacing="0">
                                                <c:forEach items="${cws.cwAttempt}" var="cwAt" varStatus="k">
                                                    <tr class="gray_words">
                                                        <td  class="gray_words_1_2" style="width:150px;">попытка ${k.count} <c:if test="${!cwAt.read&&cwAt.filePath ne \"empty\"}"><span style="color: #3A97C7;font-size: smaller">(новая)</span></c:if></td>
                                                        <td  class="gray_words_1_2">${cwAt.attemptDate}</td>

                                                        <td  class="gray_words_3_4"
                                                            <c:if test="${cwAt.filePath eq \"empty\"}">>В рукописном</c:if><c:if test="${cwAt.filePath ne \"empty\"}">onclick="readTask(${se.disc},${cw.id},${cwAt.id})"><a href="../CWfiles/${cwAt.filePath}" >загрузить</a></c:if>
                                                        </td>

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
    </div>

</c:forEach>
        <input type="hidden" name="disc" value="${se.disc}">
             <input type="hidden" name="update" value="${u}">
             <input type="hidden" name="cwOnly" value="true">
    </form>
<br><br>
<div> <button dojoType="dijit.form.Button" onclick="sendResults" class="stand_button_new" style="float: right;">
                Сохранить
        </button>
<div class="otdelitel"></div></div>
</div>
