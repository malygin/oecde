<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<c:set var="student" value="${sessionScope[\"student\"]}" />
<c:set var="action" value="${param[\"a\"]}"/>

<c:choose>
    <c:when test="${action eq 'studonline'}">
        <c:set var="title" value="Студенты on-line"/>
        <c:set var="list" value="${student.usersOnline}"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Мои одногруппники"/>
        <c:set var="list" value="${student.studentsList}"/>
    </c:otherwise>
</c:choose>
<script type="text/javascript">
    dojo.require("dojo.parser");
    dojo.require("dijit.TitlePane");
    dojo.require("dijit.form.Button");
    dojo.require("dijit.form.Form");
    dojo.require("dijit.form.ValidationTextBox");
    document.title = "СГУ ЦОО. ${title}"
</script>
<div class="head_main_math">
    <p>${title}</p>
</div>
<div class=list style="margin-top:10px">
    <ol>
        <c:forEach items="${list}" var="stud" varStatus="i">
            <c:if test="${stud.id ne sessionScope[\"studentItem\"].id}">
            <c:choose>
                <c:when test="${i.index %2 eq 0}">
                    <c:set var="st1" value="1"/>
                </c:when>
                <c:otherwise>
                    <c:set var="st1" value="2"/>
                </c:otherwise>
            </c:choose>
            <li class="first_back_<c:out value="${st1}"/>_stteachers">
                <div>
                    <div style="height: 140px; width: 110px; float: left;" >
                        <a href='main.jsp#student/id=${stud.id}'>
                            <img src='../loadPhoto?id=${stud.id}&type=student&size=medium'>
                        </a>
                    </div>
                    <p class=fio_friend><b>ФИО:</b> ${stud.surname} ${stud.name} ${stud.second_name}</p>
                    <p><b>Факультет:</b> ${stud.faculty}</p>
                    <p class=ostup_sleva><b>Специальность:</b> ${stud.spec.name}</p>
                    
                    <a class='teh_a'  href="main.jsp#write_msg/id_send=<c:out value="${stud.id}"/>&type_send=4&type_let=1"><img src="../images/pero.png"> Написать письмо</a>
                </div>
                <div align="centr"><div class="otdelitel"></div></div>
            </li>
            </c:if>
        </c:forEach>
    </ol>
</div>
