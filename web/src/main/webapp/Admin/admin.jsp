<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="admin" class="admin.model.Admin" scope="page"/>

<script type="text/javascript">
    dojo.require("dojox.layout.ContentPane");
    document.title = "ЦОО СГУ. Информация об админе";
</script>

<div class=title>
    <p>Информация об админе</p>
</div>
<c:set var="adminId" value="${param[\"id\"]}"/>
<c:set target="${admin.adminI}"  property="id" value="${adminId}"/>
<c:set var="adminI"  value="${admin.byId}"/>
    <c:if test="${adminI.name eq null || adminI eq null  }">
        <c:redirect url="mainContent.jsp"/>
    </c:if>
<div class=head_main_g_prep>


    <div align="left" style="float: left; margin-right: 30px;">
        <table cellpadding="0" cellspacing="0">
            <tr>
                <td class="fotoborder">
                    <img style="border: 3px solid white;"  id="photo" align="left" src="../loadPhoto?id=<c:out value="${adminI.id}"/>&type=admin&size=big"/>
                </td>
            </tr>
            <tr>
                <td>
                    <br>


                    <div style="text-align: center; padding-bottom: 10px;">
                        <a class='send_mes_link_st_page' style="color:black" href="main.jsp#write_msg/id_send=<c:out value="${adminI.id}"/>&type_send=2&type_let=1"><img style="float: left; margin-left: 10px;" src="../images/pero_student.png"> <p style="margin: 0px; margin-top: -3px;">Написать письмо</p> <div class="otdelitel"></div></a>
                    </div>
                </td>
            </tr>
        </table>
    </div>



    <table  class="ankc">
        <tr><td><b>Фамилия:</b>&nbsp </td><td><div id="surname"> <c:out value="${adminI.surname}"/>  </div></td></tr>
        <tr><td><b>Имя:</b>&nbsp </td><td><div id="name"><c:out value="${adminI.name}"/></div></td></tr>
        <tr><td><b>Очество:</b>&nbsp </td><td><div id="second_name"><c:out value="${adminI.secondName}"/></div></td></tr>
        <tr><td><b>Доп. информация</b>&nbsp </td><td><div id="info"><c:out value="${adminI.info}"/></div></td></tr>
        <tr><td></td></tr>
        <tr><td></td></tr>
        <tr valign=top><td></td></tr>
        <tr valign=top><td><b><br></b>&nbsp </td><td></td></tr>
    </table>
    <div class="otdelitel"></div>
</div>
