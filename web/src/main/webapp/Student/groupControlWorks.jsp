<%--
    Document   : list_of_group
    Created on : 13.08.2009, 14:44:09
    Author     : marushina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<c:set var="educ" value="${sessionScope[\"education\"]}"/>
<c:set var="s" value="${(param[\"s\"] eq 1)}"/>
<c:set target="${educ}" property="semestr" value="${s?1:0}" />
<c:set var="list" value="${educ.controlWorkInfo4Group}"/>

<div class="head_main_math">
    <p>Контрольные работы одногруппников</p>
</div>
<c:forEach items="${list}" var="eList">
    <div class="GrayTitle_big">
        <div dojoType="dijit.TitlePane" title="<div class='lesson_cont_gr'><b>${eList.key.name}</b></div>">
            <div class="white_title little_white">
                <c:forEach items="${eList.value}" var="stList">
                    <!--НЕОБХОДИМО сделать проверку на то есть ли контрольная или нет. чтоб не дергалось при жмякании-->
                    <div dojoType="dijit.TitlePane" title="<div class='student_cont_gr'>${stList.surname} ${stList.name} ${stList.secondName}</div>" open="false">
                        <div id="table_for_razv"  class="otstup_snizu_tab_for_razv">
                             <table class="pane" id="pane" cellpadding='0' cellspacing='0'>
                                 <c:forEach items="${stList.controlWorks}" var="cw" varStatus="i">
                                    <tr class="gray_words">
                                        <td  class="gray_words_1_2">
                                            Контрольная ${i.count}
                                        </td>
                                        <td  class="green_tetx_cont_gr">
                <c:choose>
                    <c:when test="${cw.progress eq \"2\"}"><td  class="gray_words_left" style="color: #FF4500;">нет зачтена</td>
                    </c:when>
                    <c:when test="${cw.progress eq \"1\"}"><td  class="blue_tetx_cont_gr">в наличии</td>
                    </c:when>
                    <c:when test="${cw.progress eq \"3\"}"> <td  class="green_tetx_cont_gr">зачтена</td>
                    </c:when>
                </c:choose>
                                </c:forEach>
                             </table>
                        </div>
                   </div>
                </c:forEach>
            </div>
        </div>
    </div>
</c:forEach>