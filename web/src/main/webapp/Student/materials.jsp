<%--
    Document   : st_mat_content
    Created on : 03.03.2009, 12:26:41
    Author     : KorgovVD
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="materials" value="${sessionScope[\"education\"]}"/>
<c:set var="s" value="${(param[\"s\"] eq 1)}"/>
<c:set target="${materials}" property="semestr" value="${s?1:0}" />
<script>
    document.title = "ЦОО СГУ. Изучаемые материалы";
    dojo.require("dijit.ProgressBar");
    dojo.addOnLoad(function(){
        dojo.query("*[id^='prBar_']").forEach(function(pr){
            if(pr.attributes[0].nodeValue < 30){
                dojo.byId("prBarCont_"+pr.id).setAttribute("class","red_progressBar");
            }
            if(pr.attributes[0].nodeValue < 60 && pr.attributes[0].nodeValue >= 30){
                dojo.byId("prBarCont_"+pr.id).setAttribute("class","yellow_progressBar");
            }
            if(pr.attributes[0].nodeValue >= 60){
                dojo.byId("prBarCont_"+pr.id).setAttribute("class","green_progressBar");
            }
        });
        dojo.byId("mat_container").style.display = "block";
    });
</script>

<div class="head_main_math">
    <p>Изучаемые материалы

    <c:choose>
      <c:when test="${s}">
          переэкзаменовка
          <span style=" margin-right: 15px; width: 120px; float: right;" ><a class='stmat_link' style="color: red;font-weight: bolder" href='#mat/s=0'>(летний семестр)</a></span>
      </c:when>
      <c:otherwise>
             летнего семестра
             <span style=" margin-right: 15px; width: 120px; float: right;" ><a class='stmat_link'  style="color: red;font-weight: bolder" href='#mat/s=1'>(переэкзаменовка)</a></span>
      </c:otherwise>
    </c:choose>

        </p>
</div>
<div id="mat_container" >
    <c:forEach items="${materials.studentsDisciplines}" var="disc" varStatus="numb" >
        <div class="inf_math">
            <div class="inf_math">
                <a class="material_name" href="#course/id=${disc.umk.id}">${disc.umk.name}</a>
                <p class="teacher_name"><b>Автор:</b> <c:forEach items="${disc.umk.authors}" var="auth" varStatus="i">${auth.surname} ${fn:substring(auth.name,0,1)}. ${fn:substring(auth.secondName,0,1)}.<c:if test="${!i.last}">,</c:if> </c:forEach></p>
                <div class="dop_inf">
                    <p class="finished_tests">Пройденные тесты: ${disc.umk.passedTests} из ${disc.umk.testsCount} <a class="more_inf_link" href="#course/id=${disc.umk.id}&tt=1">перейти к тестам</a></p>
                    <p class="test_results">Результаты тестирования: <b class="grade_points_in_materials"><c:out value="${(disc.rating.work eq 0)?(disc.rating.reWork):(disc.rating.work)}"/></b> баллов <a class="more_inf_link" href="#discresults/d=${disc.id}&s=${s?1:0}">подробнее</a></p>
                    <p class="final_test">Итоговый тест: <b class="grade_points_in_materials"><c:out value="${(disc.rating.concludingWork eq 0)?(disc.rating.concludingReWork):(disc.rating.concludingWork)}"/></b> баллов</p>
                </div>
                <br>
                <div class="materials_page" id="prBarCont_prBar_${numb.index}">
                    <div id="prBar_${numb.index}" dojoType="dijit.ProgressBar" progress="${disc.rating.sum/(3+disc.concludingWorksNumber+disc.worksNumber)}"></div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>