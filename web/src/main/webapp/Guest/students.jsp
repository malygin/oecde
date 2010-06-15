<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<c:set var="student" value="${sessionScope[\"student\"]}" />
<c:set var="action" value="${param[\"a\"]}"/>

<c:choose>
    <c:when test="${action eq 'studonline'}">
        <c:set var="title" value="Студенты on-line"/>
        <%--<c:set var="list" value="${student.usersOnline}"/>--%>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Мои одногруппники"/>
        <%--<c:set var="list" value="${student.studentsList}"/>--%>
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
        <li class="first_back_1_stteachers">
            <div>
                <div style="height: 140px; width: 110px; float: left;" >
                    <a href='javascript:alert('У вас недостаточно прав для использования данного функционала.')'>
                        <img src='../images/he.jpg' width=91%>
                    </a>
                </div>
                <p class=fio_friend><b>ФИО:</b> Дмитриева Дарья Олеговна</p>
                <p><b>Факультет:</b> Механико-Математический</p>
                <p class=ostup_sleva><b>Специальность:</b> Механика</p>

                <a class='teh_a'  href="javascript:alert('У вас недостаточно прав для использования данного функционала.')">
                    <img src="../images/pero.png"> Написать письмо
                </a>
            </div>
            <div align="centr"><div class="otdelitel"></div></div>
        </li>
        <li class="first_back_1_stteachers">
            <div>
                <div style="height: 140px; width: 110px; float: left;" >
                    <a href='javascript:alert('У вас недостаточно прав для использования данного функционала.')'>
                        <img src='../images/he.jpg' width=91%>
                    </a>
                </div>
                <p class=fio_friend><b>ФИО:</b> Сидоров Геннадий Александрович</p>
                <p><b>Факультет:</b> Механико-Математический</p>
                <p class=ostup_sleva><b>Специальность:</b> Механика</p>

                <a class='teh_a'  href="javascript:alert('У вас недостаточно прав для использования данного функционала.')">
                    <img src="../images/pero.png"> Написать письмо
                </a>
            </div>
            <div align="centr"><div class="otdelitel"></div></div>
        </li>
        <li class="first_back_1_stteachers">
            <div>
                <div style="height: 140px; width: 110px; float: left;" >
                    <a href='javascript:alert('У вас недостаточно прав для использования данного функционала.')'>
                        <img src='../images/he.jpg' width=91%>
                    </a>
                </div>
                <p class=fio_friend><b>ФИО:</b> Тепляков Андрей Витальевич</p>
                <p><b>Факультет:</b> Механико-Математический</p>
                <p class=ostup_sleva><b>Специальность:</b> Механика</p>

                <a class='teh_a'  href="javascript:alert('У вас недостаточно прав для использования данного функционала.')">
                    <img src="../images/pero.png"> Написать письмо
                </a>
            </div>
            <div align="centr"><div class="otdelitel"></div></div>
        </li>

    </ol>
</div>
