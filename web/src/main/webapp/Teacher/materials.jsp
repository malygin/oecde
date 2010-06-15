<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import = "umk.model.*" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import="teacher.model.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<c:set var="materials" value="${sessionScope[\"education\"]}"/>
<c:set target="${materials}" property="semestr" value="${materials.currentSemestr}" />
<script>
    document.title = "ЦОО СГУ. Изучаемые материалы";
    dojo.require("dijit.ProgressBar");
</script>

<div class="head_main_math">
    <p>Изучаемые материалы</p>
</div>
<div>
    <c:forEach items="${materials.teacherDisciplinesWithUmk}" var="course" varStatus="status">
                <div class="inf_math">
                    <div>
                        <div class='inf_math_reit'>
						         <c:if test= "${sessionScope[\"teacherItem\"].id!='45555' && sessionScope[\"teacherItem\"].id!='123425298' && sessionScope[\"teacherItem\"].id!='128826026'}" >
                            <p class="inf1" classname="inf1">
                               <c:out value="${status.count}"/>. <i><c:out value="${course.name}"/> </i><br/>
                                <a href="#course/id=<c:out value="${course.id}"/> " class="visco">(Просмотреть курс)</a> |
                                <a href="#events/umkId=<c:out value="${course.id}"/> " class="visco">(Просмотреть события)</a><br/>

                            </p>
							 </c:if>
                        </div>

                        <div align="centr"><div class="otdelitel"></div></div>
                    </div>
                    <div  class='raz_delete'></div>
                </div>
          </c:forEach>


    </div>
