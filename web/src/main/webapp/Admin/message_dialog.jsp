<%--
    Document   : message_dialog
    Created on : 15.04.2010, 11:52:12
    Author     : malyginav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "msgs.model.*" %>
<%@ page import = "admin.model.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "base.model.*" %>
<%@ page import = "login.model.*" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:useBean id="messg" class="msgs.model.Messg"/>


<script>
    dojo.require("dijit.form.Form");
    dojo.require("dojox.form.DropDownSelect");
    dojo.require("dijit.form.TextBox");
     dojo.require("dijit.form.Textarea");
    dojo.require("dijit.Editor");

</script>
<%
    Messg message = new Messg();
    message.setUser((UserItem) request.getSession().getAttribute("adminItem"));
    message.setType((UserType) request.getSession().getAttribute("type"));
   List<MessageItem> msgs= message.getDialog(Integer.parseInt((String)request.getParameter("id")),Integer.parseInt((String)request.getParameter("type")));

 // List<MessageItem> msgs= message.getDialog(172,1);

    %>
 <script type="text/javascript" src="../scripts/msgs.js" djConfig="parseOnLoad: true"></script>
<style type="text/css">
    @import "../style/DropDownSelect.css";
</style>
<div class="head_main_math">
    <p>Входящее Cообщение</p>
</div>
<jsp:setProperty name="messg" property="msgsList" value="<%=msgs %>" />



<div id="delete_message_success" style="display: none;" class="delete_message_success_from_reading">
   <p class="message_was_delited">Сообщение удалено!</p>
    Вы можете <a title="Данное сообщение будет восстановлено." class="recovery_link" href="javascript:void(0)" onClick="recovery_one_message(<c:out value="${message.id}"/>)">востановить его</a> или <a class="link_back_to_mes_list" title="Будет осуществлен переход к списку входящих сообщений, при этом данное сообщение будет безвозвратно удалено." href="main.jsp#msg">перейти к списку входящих писем!</a>
</div>
<div id="post_message_success" style="display: none;" class="post_message_success_from_reading">
   <p class="message_was_delited">Сообщение отправлено успешно!</p>
    Вы можете  <a class="link_back_to_mes_list" title="Будет осуществлен переход к списку входящих сообщений, при этом данное сообщение будет безвозвратно удалено." href="main.jsp#msg">перейти к списку входящих писем!</a>
</div>
    <c:forEach items="${messg.msgsList}" var="message" begin="0" varStatus="count">
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
                        <p><c:out value="${message.them}"/></p>
                    </td>
                </tr>
            </table>
            <table>
         <c:if test="${message.countFiles ne '0'}"> <img class="file_in_mes" src="../images/att1.jpg" width="10" height="13">
         
         </c:if> <a class="link_back_to_mes_list" href="#read_msg/id=${message.id}">посмотреть письмо полностью</a>
           </table>


        </div>
        <div class="otdelitel"></div>
    </div>
    <div class="new_letter_body">
        <div class="mess_tools_butt grey_button" style="background:white; border: none !important;">
           
           
            <div class="otdelitel"></div>
        </div>
        <div class="text_of_letter">
         <c:out escapeXml="false" value="${message.full_text}"/> </div>
     
      
    </div>
</div>

</c:forEach>
