<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<c:set var="action" value="${param[\"action\"]}"/>
 <c:choose>
    <c:when test="${action eq 'tonline'}">
        <jsp:useBean id="teachers" class="teacher.model.Teacher"/>
        <c:set target="${teachers.teacherI}" property="id" value="0"/>
        <c:set var="title" value="Преподаватели on-line"/>
        <c:set var="teachersList" value="${teachers.usersOnline}"/>
    </c:when>
    <c:otherwise>
        <c:set var="s" value="${(param[\"s\"] eq 1)}"/>
        <c:set var="student" value="${sessionScope[\"education\"]}" />
        <c:set var="title" value="Мои преподаватели"/>
        <c:set target="${student}" property="semestr" value="${s?1:0}" />
        <c:set var="teachersList" value="${student.teachersList}"/>
    </c:otherwise>
</c:choose>
<script type="text/javascript">
    document.title = "СГУ ЦОО. ${title}"
</script>
<div class="head_main_math">
    <p>${title}
    <c:if test="${action ne 'tonline'}">
        <c:choose>
          <c:when test="${s}">
              переэкзаменовка
              <span style=" margin-right: 15px; width: 120px; float: right;" ><a class='stmat_link' style="color: red;font-weight: bolder" href='#teachers/s=0'>(летний семестр)</a></span>
          </c:when>
          <c:otherwise>
                 летнего семестра
                 <span style=" margin-right: 15px; width: 120px; float: right;" ><a class='stmat_link'  style="color: red;font-weight: bolder" href='#teachers/s=1'>(переэкзаменовка)</a></span>
          </c:otherwise>
        </c:choose>
     </c:if></p>
</div>

<div class=list style="margin-top:10px">
    <ol>
        <c:forEach items="${teachersList}" var="tea" varStatus="i">
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
                    <a href="#teacher/id=${tea.id}"><img class=pics src="../loadPhoto?id=${tea.id}&type=teacher&size=big" align=left width=20%></a>
                    <p class=fio_friend><b>ФИО:</b> ${tea.surname} ${tea.name} ${tea.second_name} </p>
                    <p><b>Департамент</b>&nbsp</p><p><b>Должность</b>&nbsp ${tea.job}</p>
                    <p><b>  <a class='teh_a'  href="main.jsp#write_msg/id_send=<c:out value="${tea.id}"/>&type_send=3&type_let=1"><img src="../images/pero.png"> Написать письмо</a>
              </b></p>
                </div>
                <div align="centr"><div class="otdelitel"></div></div>
            </li>
        </c:forEach>
    </ol>
</div>
