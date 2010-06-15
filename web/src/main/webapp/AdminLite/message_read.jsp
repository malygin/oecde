

<%--
    Document   : message_write
    Created on : 15.05.2009, 15:34:12
    Author     : korgovvd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:useBean id="messg" class="msgs.model.Messg"/>
 <c:set target="${messg.messgI}"  property="id"  value="${pageContext.request.parameterMap[\"id\"][0]}"/>
 <c:set var="message" value="${messg.msgById}"/>
  <jsp:setProperty name="messg" property="user"  value="${sessionScope[\"liteAdminItem\"]}"/>
 <jsp:setProperty name="messg" property="type"  value="${sessionScope[\"type\"]}"/>
 <jsp:getProperty name="messg" property="read"/>
<script>
    dojo.require("dijit.form.Form");
    dojo.require("dojox.form.DropDownSelect");
    dojo.require("dijit.form.TextBox");
     dojo.require("dijit.form.Textarea");
    dojo.require("dijit.Editor");

</script>
 <script type="text/javascript" src="../scripts/msgs.js" djConfig="parseOnLoad: true"></script>
<style type="text/css">
    @import "../style/DropDownSelect.css";
</style>
<div class="head_main_math">
    <p>Входящее Cообщение</p>
</div>

<c:if test="${message.them == null || pageContext.request.parameterMap[\"id\"][0] == null}">

    <div class=tree>
        <br/>
        <p style="color: #777777;  font-size: 14pt;  font-weight: bold; margin: 0px; font-family: Arial; "> &nbsp&nbsp  Извините, но по Вашему запросу ничего не найдено</p>

    </div>
</c:if>

<c:if test="${message.them != null && param[\"id\"] != null}">
<div id="delete_message_success" style="display: none;" class="delete_message_success_from_reading">
   <p class="message_was_delited">Сообщение удалено!</p>
    Вы можете <a title="Данное сообщение будет восстановлено." class="recovery_link" href="javascript:void(0)" onClick="recovery_one_message(<c:out value="${message.id}"/>)">востановить его</a> или <a class="link_back_to_mes_list" title="Будет осуществлен переход к списку входящих сообщений, при этом данное сообщение будет безвозвратно удалено." href="main.jsp#msg">перейти к списку входящих писем!</a>
</div>
<div id="post_message_success" style="display: none;" class="post_message_success_from_reading">
   <p class="message_was_delited">Сообщение отправлено успешно!</p>
    Вы можете  <a class="link_back_to_mes_list" title="Будет осуществлен переход к списку входящих сообщений, при этом данное сообщение будет безвозвратно удалено." href="main.jsp#msg">перейти к списку входящих писем!</a>
</div>

<div id="message_content">
    <div class="author_info">

           <c:choose>
                                <c:when test="${message.studentSender ne null}">
                                    <img style="float: left; margin-right: 10px;" src='../loadPhoto?id=<c:out value="${message.studentSender.id}"/>&type=student&size=medium'>
                               </c:when>
                                <c:when test="${message.teacherSender ne null}">
                                    <img style="float: left; margin-right: 10px;"  src='../loadPhoto?id=<c:out value="${message.teacherSender.id}"/>&type=teacher&size=medium'>
                                </c:when>
                                 <c:when test="${message.adminSender ne null}">
                                    <img style="float: left; margin-right: 10px;" src='../loadPhoto?id=<c:out value="${message.adminSender.id}"/>&type=admin&size=medium'>
                                  </c:when>
                                <c:otherwise>
                                    <img style="float: left; margin-right: 10px;"  src="../images/he_small.jpg">
                               </c:otherwise>
             </c:choose>

        <div class="author_fio">
            <table>
                <tr>
                    <td>
                        <b>От кого:&nbsp;&nbsp;&nbsp;</b>
                    </td>
                    <td>
                        <p>

                      <c:choose>
                        <c:when test="${message.studentSender ne null}">
                            <a class="msg_author" href="#student/id=${message.studentSender.id}"> <c:out value="${message.studentSender.surname}"/> &nbsp<c:out value="${message.studentSender.name}"/></a>
                         </c:when>
                        <c:when test="${message.teacherSender ne null}">
                          <a class="msg_author" href="#teacher/id=${message.teacherSender.id}">   <c:out value="${message.teacherSender.surname}"/> &nbsp <c:out value="${fn:substring(message.teacherSender.name,0,1)}"/>.<c:out value="${fn:substring(message.teacherSender.second_name,0,1)}"/>.</a>
                        </c:when>
                         <c:when test="${message.adminSender ne null}">
                          <a class="msg_author" href="#admin/id=${message.adminSender.id}"><c:out value="${message.adminSender.surname}"/>&nbsp<c:out value="${message.adminSender.name}"/></a>
                        </c:when>
                        <c:otherwise>
                            <p>Отправитель неизвестен</p>
                        </c:otherwise>
                    </c:choose>

                        </p>
                    </td>
                </tr>
                <tr>
                    <td>
                        <b>Дата:</b>
                    </td>
                    <td>

                          <p><c:out value="${message.date}"/> </p>
                    </td>
                </tr>
                <tr>
                    <td>
                        <b>Тема:</b>
                    </td>
                    <td>
                        <p class="theme_of_new_letter"><c:out value="${message.them}"/></p>
                    </td>
                </tr>
            </table>
            <table>
             <c:if test="${!empty message.files}">
             <c:forEach items="${message.files}" var="file">

                    <tr>
                    <td>
                        <img src="../images/att1.jpg">&nbsp;
                    </td>

                    <td>
                       <a class="load_added_file" href="../messfiles/<c:out value="${file.name}"/>">Скачать файл</a> &nbsp;&nbsp;
                    </td>
                </tr>


                    </c:forEach>
                </div>
            </c:if>
           </table>


        </div>
        <div class="otdelitel"></div>
    </div>
    <div class="new_letter_body">
        <div class="mess_tools_butt grey_button" style="background:white; border: none !important;">
            <div class="div_forChoice_butt">
                <a href="javascript:void(0)" onclick="action_one_message('arch',<c:out value="${message.id}"/>)" class="tool_links_msg">Архивировать</a>
            </div>
            <div class="div_forArchDel_butt">

                <div style="float: left;"><a href="javascript:void(0)" onclick="action_one_message('del',<c:out value="${message.id}"/>)" class="tool_links_msg">Удалить</a></div>
                <div class="otdelitel"></div>
            </div>
            <div class="otdelitel"></div>
        </div>
        <div class="text_of_letter">
         <c:out escapeXml="false" value="${message.full_text}"/> </div>
        <div class="reply_area"><p class="fast_answer">Быстрый ответ |
           <c:choose>
                                <c:when test="${message.studentSender ne null}">
                                     <a href="main.jsp#write_msg/id_send=<c:out value="${message.studentSender.id}"/>&type_send=4&type_let=1">Полный режим </a>
                                </c:when>
                                <c:when test="${message.teacherSender ne null}">
                                     <a href="main.jsp#write_msg/id_send=<c:out value="${message.teacherSender.id}"/>&type_send=3&type_let=1">Полный режим </a>
                                </c:when>
                                 <c:when test="${message.adminSender ne null}">
                                     <a href="main.jsp#write_msg/id_send=<c:out value="${message.adminSender.id}"/>&type_send=2&type_let=1">Полный режим </a>
                                  </c:when>
                                <c:otherwise>
                                   <a href="main.jsp#write_msg">Полный режим </a>
                               </c:otherwise>
             </c:choose>



        </p>
             <textarea rows="7" onclick="remove_time_text('text_<c:out  value="${message.id}"/>', '(Ваше сообщение здесь)', 1)" id="text_<c:out  value="${message.id}"/>" dojoType="dijit.form.Textarea" style="width: 480px; height: 150px; max-height: 150px;">(Ваше сообщение здесь)</textarea>

        </div>
        <div class="mess_tools_butt grey_button" style="background:white; border: none !important;">
            <div class="div_forChoice_butt">
                <a href="javascript:void(0)" onclick="action_one_message('arch',<c:out value="${message.id}"/>)" class="tool_links_msg">Архивировать</a>
            </div>
            <div class="div_forArchDel_butt">
                <div style="float: left;"><a href="javascript:void(0)" class="tool_links_msg" onclick="quickAnswer(<c:out value="${message.id}"/>, 'alone')">Ответить</a>&nbsp;|&nbsp;</div>
                <div style="float: left;"><a href="javascript:void(0)" class="tool_links_msg" onclick="action_one_message('del',<c:out value="${message.id}"/>)">Удалить</a></div>
                <div class="otdelitel"></div>
            </div>
            <div class="otdelitel"></div>
        </div>
    </div>
</div>

 </c:if>
