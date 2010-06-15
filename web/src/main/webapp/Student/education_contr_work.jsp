<%-- 
    Document   : education_contr_work
    Created on : 07.08.2009, 14:15:44
    Author     : marushina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<c:set var="ed" value="${sessionScope[\"education\"]}"/>
<c:set var="s" value="${(param[\"s\"] eq 1)}"/>
<c:set target="${ed}" property="semestr" value="${(s?1:0)}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<div class="title_my_education">
    <i>Мои контрольные работы</i>
</div>
<div class="my_educ_text">
    <p>Своевременное и качественное выполнение контрольных  работ - это важная часть вашего обучения.<p>
<p> Контрольные работы присутствуют в учебных планах и могут добавлять самими преподавателями.  
 Отправлять их следует только посредством возможностей портала.

<p>Вся необходимая информация о том  зачтена контрольная или нет, список попыток с возможностью скачать предыдущие попытки, а так же
    возможность отправить новую попытку можно в разделе  <a class="link_in_text" href="#cw">“Мои контрольные”</a>
Преподаватель может попросить вас переделать контрольные с конкретнми инструкциями, поэтому следует проверять
 <a class="link_in_text" href="#msg">входящие сообщения</a>  .</p>
    
	<a class="pereexzam" href="javascript:dojo.attr(dijit.byId('cw'),'href','education_contr_work.jsp?s=<c:out value="${s?0:1}"/>')"><c:out value="${(s)?\"Текущий семестр\":\"Переэкзаменовка\"}"/></a></p>
	<p class="educ_red_tetx">Контрольные работы: 
    

 <c:forEach items="${ed.lowGradedControlWorks}"  var="disc">
        <div><img class='pasp_img_test' src='../images/pic.jpg'><div class="educ_umk_text"><a href="#" class="pasp_links">
            ${disc.name}
            </a></div></div>
            
        <c:forEach items="${disc.controlWorks}"  var="cw">
            <div class="educ_content_title">
            <div class="educ_umk_text"><a href="#" class="pasp_links">
            Контрольная работа ${cw.cwNumber}
            <span style="color: #4c64cb;">
            <c:if test="${cw.progress eq 0}">
                контрольная не отправлена
            </c:if>
            <c:if test="${cw.progress eq 1}">
                контрольная на проверке
            </c:if>
            </span>
            </a>
            </div>
            </div>
        </c:forEach>
 </c:forEach>

</div>