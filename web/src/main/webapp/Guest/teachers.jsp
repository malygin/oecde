<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<c:set var="action" value="${param[\"action\"]}"/>
 <c:choose>
    <c:when test="${action eq 'tonline'}">
        <c:set var="title" value="Преподаватели on-line"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Мои преподаватели"/>
    </c:otherwise>
</c:choose>
<script type="text/javascript">
    document.title = "СГУ ЦОО. ${title}"
</script>
<div class="head_main_math">
    <p>${title}</p>
</div>
<c:if test="${param.action ne 'tonline'}">
<div class=list style="margin-top:10px">
    <ol>
        <li class="first_back_1_stteachers">
            <div>
                <a href="javascript:alert('У вас недостаточно прав для использования данного функционала.')"><img class=pics src="../images/he.jpg" align=left width=20%></a>
                <p class=fio_friend><b>ФИО:</b> Степанова Ольга Анатольевна </p>
                <p><b>Департамент</b>&nbsp <div id="spec"></div></p>
                <p><b>Должность</b>&nbsp Доцент</p>
                <p>
                    <b>
                        <a class='teh_a'  href="javascript:alert('У вас недостаточно прав для использования данного функционала.')">
                            <img src="../images/pero.png"> Написать письмо
                        </a>
                    </b>
                </p>
            </div>
            <div align="centr"><div class="otdelitel"></div></div>
        </li>
        <li class="first_back_2_stteachers">
            <div>
                <a href="javascript:alert('У вас недостаточно прав для использования данного функционала.')"><img class=pics src="../images/he.jpg" align=left width=20%></a>
                <p class=fio_friend><b>ФИО:</b> Игнатьева Анна Андреевна </p>
                <p><b>Департамент</b>&nbsp <div id="spec"></div></p>
                <p><b>Должность</b>&nbsp Доцент</p>
                <p>
                    <b>
                        <a class='teh_a'  href="javascript:alert('У вас недостаточно прав для использования данного функционала.')">
                            <img src="../images/pero.png"> Написать письмо
                        </a>
                    </b>
                </p>
            </div>
            <div align="centr"><div class="otdelitel"></div></div>
        </li>
        <li class="first_back_1_stteachers">
            <div>
                <a href="javascript:alert('У вас недостаточно прав для использования данного функционала.')"><img class=pics src="../images/he.jpg" align=left width=20%></a>
                <p class=fio_friend><b>ФИО:</b> Позднева Инна Павловна </p>
                <p><b>Департамент</b>&nbsp <div id="spec"></div></p>
                <p><b>Должность</b>&nbsp Доцент</p>
                <p>
                    <b>
                        <a class='teh_a'  href="javascript:alert('У вас недостаточно прав для использования данного функционала.')">
                            <img src="../images/pero.png"> Написать письмо
                        </a>
                    </b>
                </p>
            </div>
            <div align="centr"><div class="otdelitel"></div></div>
        </li>
    </ol>
</div>
</c:if>
<c:if test="${param.action eq 'tonline'}"><div class=list style="margin-top:10px">
    <ol>
    <li class="first_back_1_stteachers">
            <div>
                <a href="javascript:alert('У вас недостаточно прав для использования данного функционала.')"><img class=pics src="../images/he.jpg" align=left width=20%></a>
                <p class=fio_friend><b>ФИО:</b> Позднева Инна Павловна </p>
                <p><b>Департамент</b>&nbsp <div id="spec"></div></p>
                <p><b>Должность</b>&nbsp Доцент</p>
                <p>
                    <b>
                        <a class='teh_a'  href="javascript:alert('У вас недостаточно прав для использования данного функционала.')">
                            <img src="../images/pero.png"> Написать письмо
                        </a>
                    </b>
                </p>
            </div>
            <div align="centr"><div class="otdelitel"></div></div>
        </li>    </ol>
</div>
</c:if>