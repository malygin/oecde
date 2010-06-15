<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
 <%@include  file="../WEB-INF/Teacher/jspf/setVariables.jspf" %>
<script type="text/javascript">
    dojo.require("dijit.form.TextBox");
    document.title = "ЦОО СГУ. Выставление оценок";
</script>
     <script type="text/javascript" src="../scripts/grades.js" ></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    dojo.require("dijit.layout.TabContainer");
    dojo.require("dijit.form.Button");
    dojo.require("dijit.form.Form");
</script>
<c:set var="title" value="Оценки группы"/>
<div class="head_main_math">
    <p>${studentI.gr}-я группа</p>
</div>

<script type="text/javascript">
    var hide = dojo.connect(dijit.byId("contentPane"),'_onLoadHandler',function fdq(){
        makeTabsVisible();
        dojo.disconnect(hide);
    });
</script>
<c:set var="max" value="${tNum*100+ctNum*100+200+(cw>0?100:0)}"/>
<div style="padding: 5px 5px;">
    <div class="newGradesInterfaceTabs" nested doLayout id="mainTabContainer" dojoType="dijit.layout.TabContainer" style="margin-left:-1px;width:514px;height:1000px">
        <div id="tab_grades" <c:if test="${param.t ne 'grades'}">style="visibility:hidden"</c:if> refreshOnShow href="grades.jsp?gr=<c:out value="${param[\"gr\"]}"/>&sp=<c:out value="${param[\"sp\"]}"/>&s=<c:out value="${param[\"s\"]}"/>&disc=${disc}" dojoType="dojox.layout.ContentPane" title="Выставление оценок">
        </div>
        <div id="tab_tests" <c:if test="${param.t eq 'tests'}">selected="true"</c:if> refreshOnShow preload="false" <c:if test="${param.t ne 'tests'}">style="visibility:hidden"</c:if> href="groupTestResults.jsp?gr=<c:out value="${param[\"gr\"]}"/>&sp=<c:out value="${param[\"sp\"]}"/>&s=<c:out value="${param[\"s\"]}"/>&disc=${disc}" dojoType="dijit.layout.ContentPane" title="Тесты" >
        </div>
        <div id="tab_cw" <c:if test="${param.t eq 'cw'}">selected="true"</c:if> refreshOnShow preload="false" <c:if test="${param.t ne 'cw'}">style="visibility:hidden"</c:if> href="controlworks_teacher.jsp?gr=<c:out value="${param[\"gr\"]}"/>&sp=<c:out value="${param[\"sp\"]}"/>&s=<c:out value="${param[\"s\"]}"/>&disc=${disc}" dojoType="dojox.layout.ContentPane" title="Контр. работы">
        </div>
        <div id="tab_attendance" <c:if test="${param.t eq 'attendance'}">selected="true"</c:if> refreshOnShow preload="false" <c:if test="${param.t ne 'attendance'}">style="visibility:hidden"</c:if> href="attendance.jsp?gr=<c:out value="${param[\"gr\"]}"/>&sp=<c:out value="${param[\"sp\"]}"/>&s=<c:out value="${param[\"s\"]}"/>&disc=${disc}" dojoType="dijit.layout.ContentPane" title="Посещаемость">
        </div>
    </div>
</div>