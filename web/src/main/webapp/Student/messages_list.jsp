

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import = "msgs.model.*" %>
<%@ page import = "student.model.*" %>
<%@ page import = "java.util.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<jsp:useBean id="messg" class="msgs.model.Messg"/>

<script type="text/javascript">
    dojo.require("dijit.form.Form");
    dojo.require("dijit.form.CheckBox");
    dojo.require("dijit.form.Textarea");
    dojo.require('dojo.fx');
    dojo.require("dijit.Dialog");
    dojo.require("dojo.fx.easing");

dojo.addOnLoad(function(){
dojo.query("#table_message > tr:nth-child(odd)").addClass("odd");})

</script>
 <script type="text/javascript" src="../scripts/msgs.js" djConfig="parseOnLoad: true"></script>
<style type="text/css">
    #DeleteDialog_underlay {
        background-color:#b6ddfa;
    }
</style>



<%
    Messg message = new Messg();
    StudentItem student=(StudentItem)session.getAttribute("studentItem");
    List<MessageItem> msgs = null;

    //это студент
    message.setTypeUser("4");
    //сообщений на странице
    int countOut = 22;

    //пометили какого типа список - входящий/исходящий/архивированный
    message.setTypeList((String)request.getParameter("list"));

     if (message.getTypeList().equals("in_student")){
       message.setType_sent(4);
     }else if(message.getTypeList().equals("in_teacher")){
       message.setType_sent(3);
     }else if(message.getTypeList().equals("in_admin")){
        message.setType_sent(2);
     }

    int count = message.getCountMsgs(Integer.parseInt(student.getId()));

    String currentPage = (String)request.getParameter("page");
    if(currentPage == null) currentPage = "1";

    if(message.getTypeList().equals("out")|| message.getTypeList().equals("in")|| message.getTypeList().equals("in_student")|| message.getTypeList().equals("in_teacher")|| message.getTypeList().equals("in_admin")||message.getTypeList().equals("arch")){
        msgs = message.getListByPage(Integer.parseInt(student.getId()), currentPage, countOut);
    }

    int pageCount = count/countOut;
    if( count%countOut != 0 ){
         pageCount++;
    }

%>
<script>
    var crtPage = <%=currentPage %>;
    var listT = "<%=message.getTypeList() %>";
    var userId = "<%=student.getId() %>";
</script>

<c:set target="${messg}" property="typeUser" value="<%=message.getTypeUser() %>"/>
<c:set target="${messg}" property="typeList" value="<%=message.getTypeList() %>"/>
<jsp:setProperty name="messg" property="msgsList" value="<%=msgs %>" />



<div class="head_main_math">
    <c:if test="${messg.typeList eq 'in'}">
       <p>Входящие сообщения</p>
    </c:if>
      <c:if test="${messg.typeList eq 'in_student'}">
        <p>Входящие сообщения от студентов</p>
    </c:if>
      <c:if test="${ messg.typeList eq 'in_teacher'}">
        <p>Входящие сообщения от коллег</p>
    </c:if>
      <c:if test="${messg.typeList eq 'in_admin'}">
        <p>Служебные  сообщения</p>
    </c:if>
    <c:if test="${messg.typeList eq 'out'}">
        <p>Исходящие сообщения</p>
    </c:if>
      <c:if test="${messg.typeList eq 'arch'}">
        <p>Заархивированные сообщения</p>
    </c:if>
</div>

<c:if test="${empty messg.msgsList}">
    <div class="no_messagies">В данной папке нет  сообщений.</div>
</c:if>

<c:if test="${!empty messg.msgsList}">

<div class="nav_links">
<%
        for( int i=1;i<=pageCount;i++ ){
            if(pageCount != 1)
                if( i == Integer.valueOf(currentPage))
                    out.println("&nbsp; <b> " + i + "</b>&nbsp;");
                else
                    out.println("&nbsp;<a " + i + "' onclick='toPage("+i+")'>" + i + "</a>&nbsp;");
        }
%>
</div>


<div class="nav_links" style="display: none;">
<%
        for( int i=1;i<=pageCount;i++ ){
            if(pageCount != 1)
                if( i == Integer.valueOf(currentPage))
                    out.println("&nbsp; <b> " + i + "</b>&nbsp;");
                else
                    out.println("&nbsp;<a href='javascript:toPage("+i+")' >" + i + "</a>&nbsp;");
        }
%>
</div>

 <c:if test="${messg.typeList eq 'in' || messg.typeList eq 'in_student' || messg.typeList eq 'in_teacher' || messg.typeList eq 'in_admin'}">

    <div class="mess_tools_butt grey_button" style="background:white">
        <div class="div_forChoice_butt">
            <a href="javascript:void(0)" onclick="Select_All()" class="tool_links_msg">Выделить все</a>
        </div>
        <div class="div_forArchDel_butt">
            <div style="float: left;"><a href="javascript:void(0)" class="tool_links_msg" onclick="action_del_arch('arch',null)">Архивировать</a>&nbsp;|&nbsp;</div>
            <div style="float: left;"><a href="javascript:void(0)" class="tool_links_msg" onclick="action_del_arch('del',null)">Удалить</a></div>
            <div class="otdelitel"></div>
        </div>
        <div class="otdelitel"></div>
    </div>



</c:if>
<c:if test="${messg.typeList eq 'arch' }">

    <div class="mess_tools_butt grey_button" style="background:white">
        <div class="div_forChoice_butt">
            <a href="javascript:void(0)" onclick="Select_All()" class="tool_links_msg">Выделить все</a>
        </div>
        <div class="div_forArchDel_butt">
            <div style="float: left;"><a href="javascript:void(0)" class="tool_links_msg" onclick="action_del_arch('arch',null)">Разархивировать</a>&nbsp;|&nbsp;</div>
            <div style="float: left;"><a href="javascript:void(0)" class="tool_links_msg" onclick="action_del_arch('del',null)">Удалить</a></div>
            <div class="otdelitel"></div>
        </div>
        <div class="otdelitel"></div>
    </div>
</c:if>


<div class="mess_list_content" id="mess_list_content">

                <c:forEach items="${messg.msgsList}" var="message" begin="0" varStatus="count">
                    <div class="message" onclick="select_div_mess(<c:out value="${message.id}"/>)" id="message_<c:out value="${message.id}"/>">
                        <c:if test="${messg.typeList ne 'out'}">
                             <c:choose>
                                <c:when test="${message.studentSender ne null}">
                                     <a href="#student/id=${message.studentSender.id}"> <img style="float: left; margin-right: 10px;" src='../loadPhoto?id=<c:out value="${message.studentSender.id}"/>&type=student&size=mini'></a>
                               </c:when>
                                <c:when test="${message.teacherSender ne null}">
                                    <img style="float: left; margin-right: 10px;"  src='../loadPhoto?id=<c:out value="${message.teacherSender.id}"/>&type=teacher&size=mini'>
                                </c:when>
                                 <c:when test="${message.adminSender ne null}">
                                    <img style="float: left; margin-right: 10px;"  src='../loadPhoto?id=<c:out value="${message.adminSender.id}"/>&type=admin&size=mini'>
                                  </c:when>
                                <c:otherwise>
                                    <img style="float: left; margin-right: 10px;"  src="../images/he_small.jpg">
                               </c:otherwise>
                             </c:choose>
                       </c:if>

        <table>
             <tr>
                <td  style="width: 300px;">
                  <c:if test="${messg.typeList eq 'in'}">
                                     <c:choose>
                                        <c:when test="${message.studentSender ne null}">
                                            <div class="text_whoom_mes">сообщение от студента</div>
                                        </c:when>
                                        <c:when test="${message.teacherSender ne null}">
                                            <div class="text_whoom_mes">сообщение от преподавателя</div>
                                        </c:when>
                                         <c:when test="${message.adminSender ne null}">
                                            <div class="text_whoom_mes">сообщение от администратора</div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="text_whoom_mes">Отправитель неизвестен</div>
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                                <c:if test="${messg.typeList eq 'out'}">
                                            <p> </p>
                                </c:if>

                                            <div class="text_tipe_mes"></div>
                </td>
                <td class="small_gray_text"><p style="color: black !important;"><c:out value="${fn:split(message.date, ' ')[1]}"/> &nbsp <c:out value="${fn:split(message.date, ' ')[0]}"/></p></td>
            </tr>

            <tr>
                <td colspan="2">
                  <c:if test="${messg.typeList ne 'out'}">
                     <c:choose>
                        <c:when test="${message.studentSender ne null}">
                         <a class="msg_author" href="#student/id=${message.studentSender.id}"> <c:out value="${message.studentSender.surname}"/> &nbsp<c:out value="${message.studentSender.name}"/></a>
                        </c:when>
                        <c:when test="${message.teacherSender ne null}">
                          <a class="msg_author" href="#msg">   <c:out value="${message.teacherSender.surname}"/> &nbsp <c:out value="${fn:substring(message.teacherSender.name,0,1)}"/>.<c:out value="${fn:substring(message.teacherSender.second_name,0,1)}"/>.</a>
                        </c:when>
                         <c:when test="${message.adminSender ne null}">
                          <a class="msg_author" href="#msg"><c:out value="${message.adminSender.surname}"/>&nbsp<c:out value="${message.adminSender.name}"/></a>
                        </c:when>
                        <c:otherwise>
                            <p>Отправитель неизвестен</p>
                        </c:otherwise>
                    </c:choose>
                    </c:if>
                        <c:if test="${message.countFiles ne '0'}"> <img class="file_in_mes" src="../images/att1.jpg" width="10" height="13"></c:if>
                </td>
            </tr>

           <tr>
                <td colspan="2" id="theme_of_letter_<c:out value="${message.id}"/>">
                   <c:if test="${messg.typeList ne 'out'}">
                     <p class="theme_of_res_letter"> <a class="link_theme_of_letter" href="#read_msg/id=<c:out value="${message.id}"/>" > <c:out value="${message.them}"/></a></p>
                   </c:if>
                    <c:if test="${messg.typeList eq 'out'}">
                      <p class="theme_of_res_letter"> <a class="link_theme_of_letter" href="#read_msg/id=<c:out value="${message.id}"/>" >  <c:out value="${message.them}"/></a></p>
                  </c:if>
               </td>

            </tr>

            <tr>
                <td colspan="2" style="padding-top: 2px; padding-bottom: 2px;">

                  <c:if test="${messg.typeList ne 'out'}">

                     <c:if test="${message.newMess eq '0'}">
                            <p class="text_of_letter_from_list"><a class="link_text_of_letter_new" href="#read_msg/id=<c:out value="${message.id}"/>" ><c:out value="${message.short_text}" escapeXml="false" /></a></p>
                       </c:if>
                        <c:if test="${message.newMess eq '1'}">
                            <p class="text_of_letter_from_list"><a class="link_text_of_letter" href="#read_msg/id=<c:out value="${message.id}"/>" ><c:out value="${message.short_text}" escapeXml="false" /></a></p>
                       </c:if>
                  </c:if>

                   <c:if test="${messg.typeList eq 'out'}">
                               <p class="text_of_letter_from_list"><a class="link_text_of_letter" href="#read_msg/id=<c:out value="${message.id}"/>" ><c:out value="${message.short_text}" escapeXml="false" /></a></p>
                    </c:if>

                </td>
            </tr>

              <tr>
                <td colspan="2">
                    <div align="center" id="requestResult_<c:out value="${message.id}"/>"></div>

                     <c:if test="${messg.typeList eq 'in' || messg.typeList eq 'in_student' || messg.typeList eq 'in_teacher' || messg.typeList eq 'in_admin'}">
                         <div style="float: left;"><a class="tool_links_msg" href="javascript:void(0);" onclick="show_form('answ_form_<c:out value="${message.id}"/>')">Быстрый ответ</a>&nbsp;|&nbsp;</div>
                         <div style="float: left;"><a href="javascript:void(0)" class="tool_links_msg" onclick="action_del_arch('arch', <c:out value="${message.id}"/>)">Архивировать</a>&nbsp;|&nbsp;</div>
                         <div style="float: left;"><a href="javascript:void(0)" class="tool_links_msg" onclick="action_del_arch('del', <c:out value="${message.id}"/>)">Удалить</a></div>
                    </c:if>

                     <c:if test="${messg.typeList eq 'arch'}">

                         <div style="float: left;"><a href="javascript:void(0)" class="tool_links_msg" onclick="action_del_arch('re_arch', <c:out value="${message.id}"/>)">Разархивировать</a>&nbsp;|&nbsp;</div>
                         <div style="float: left;"><a href="javascript:void(0)" class="tool_links_msg" onclick="action_del_arch('del', <c:out value="${message.id}"/>)">Удалить</a></div>
                    </c:if>

                    <div class="otdelitel"></div>
                    <div class="response_form" id="answ_form_<c:out value="${message.id}"/>" style="display: none;">
                                        <form>
                                            <input id="text_<c:out value="${message.id}"/>" dojoType="dijit.form.SimpleTextarea" style="width:320px; margin-right: 3px;" lines="10">
                                            <div style="float: right;"> <a class="tool_links_msg" href="javascript:void(0);" onclick="hide_form('answ_form_<c:out value="${message.id}"/>')">Закрыть</a> |
                                            <a class="tool_links_msg" href="javascript:void(0)" onclick="quickAnswer(<c:out value="${message.id}"/>,'list')" >Ответить</a></div>
                                            <div class="otdelitel"></div>
                                        </form>
                     </div>
                </td>
            </tr>

                 </table>
          <div class="otdelitel"></div>
      </div>
         </c:forEach>
     </div>

<c:if test="${messg.typeList eq 'in' || messg.typeList eq 'in_student' || messg.typeList eq 'in_teacher' || messg.typeList eq 'in_admin'}">
<div class="mess_tools_butt grey_button" style="background:white">
    <div class="div_forChoice_butt">
        <a href="javascript:void(0)" onclick="Select_All()" class="tool_links_msg">Выделить все</a>
    </div>
    <div class="div_forArchDel_butt">
        <div style="float: left;"><a href="javascript:void(0)" class="tool_links_msg" onclick="action_del_arch('arch', null)">Архивировать</a>&nbsp;|&nbsp;</div>
        <div style="float: left;"><a href="javascript:void(0)" class="tool_links_msg" onclick="action_del_arch('del', null)">Удалить</a></div>
        <div class="otdelitel"></div>
    </div>
    <div class="otdelitel"></div>
</div>
</c:if>

<c:if test="${messg.typeList eq 'arch' }">

    <div class="mess_tools_butt grey_button" style="background:white">
        <div class="div_forChoice_butt">
            <a href="javascript:void(0)" onclick="Select_All()" class="tool_links_msg">Выделить все</a>
        </div>
        <div class="div_forArchDel_butt">
            <div style="float: left;"><a href="javascript:void(0)" class="tool_links_msg" onclick="action_del_arch('rearch',null)">Разархивировать</a>&nbsp;|&nbsp;</div>
            <div style="float: left;"><a href="javascript:void(0)" class="tool_links_msg" onclick="action_del_arch('del',null)">Удалить</a></div>
            <div class="otdelitel"></div>
        </div>
        <div class="otdelitel"></div>
    </div>
</c:if>

<div class="nav_links">
<%
        for( int i=1;i<=pageCount;i++ ){
            if(pageCount != 1)
                if( i == Integer.valueOf(currentPage))
                    out.println("&nbsp; <b> " + i + "</b>&nbsp;");
                else
                    out.println("&nbsp;<a " + i + "' onclick='toPage("+i+")'>" + i + "</a>&nbsp;");
        }
%>
</div>
</c:if>
