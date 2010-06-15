<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@include  file="../WEB-INF/Teacher/jspf/setVariables.jspf" %>
<script>
    document.title = "ЦОО СГУ. Посещаемость";
    dojo.require("dijit.form.Button");
    dojo.require("dijit.layout.ContentPane");
    dojo.require("dijit.layout.TabContainer");
</script>
<script type="text/javascript">
    //console.debug(234)
    makeTabsVisible();
</script>
<c:set var="title" value="Посещаемость группы"/>
<c:set var="hashName" value="attendance"/>
<div style="margin: 5px;">
<%@include  file="../WEB-INF/Teacher/jspf/header.jspf" %>
<div style="height: 20px;" align="center" class="requestResult">
<div id="succesResult"></div>
</div>
<div id="attendanceTabContainer" dojoType="dijit.layout.TabContainer" style="cwidth:522px; height: 700px">
                            
      <div id="3" dojoType="dijit.layout.ContentPane" selected="true"  title="Занятие 1" href="attendance_content.jsp?gr=${se.studentI.gr}&sp=${se.studentI.spec.id}&disc=${se.disc}&module=3&s=${se.semestr}" loadingMessage="" refreshOnShow>
     
      </div>
      <div id="4" dojoType="dijit.layout.ContentPane" title="Занятие 2" href="attendance_content.jsp?gr=${se.studentI.gr}&sp=${se.studentI.spec.id}&disc=${se.disc}&module=4&s=${se.semestr}" loadingMessage="" refreshOnShow>
     
      </div>
      <div id="5" dojoType="dijit.layout.ContentPane" title="Занятие 3" selected="true"  href="attendance_content.jsp?gr=${se.studentI.gr}&sp=${se.studentI.spec.id}&disc=${se.disc}&module=5&s=${se.semestr}" loadingMessage="" refreshOnShow>
     
      </div>
</div>
</div>