<%-- 
    Document   : librery_of_course
    Created on : 14.08.2009, 14:31:55
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script>
    dojo.require("dijit.layout.TabContainer");
    function changeS(course,semestr){
        dijit.byId("content"+course).setHref("gradesbookpage.jsp?c="+course+"&s="+(semestr ==1?0:1));
    }
</script>
<div class="head_main_math">
    <p>Зачетная книжка</p>
</div>
<div class="centered_tabs_of_tabContainer"  >
   <div id="mainTabContainer" doLayout="false"  dojoType="dijit.layout.TabContainer" style="width:522px; height:600px;">

        <div id="content1" dojoType="dojox.layout.ContentPane" href="gradesbookpage.jsp?c=1&s=1" title="1 курс" loadingMessage="" parseOnLoad >
        </div>
        <div id="content2" dojoType="dojox.layout.ContentPane" href="gradesbookpage.jsp?c=2&s=1" title="2 курс" loadingMessage="" parseOnLoad >
        </div>
        <div id="content3" dojoType="dojox.layout.ContentPane" href="gradesbookpage.jsp?c=3&s=1" title="3 курс" loadingMessage="" parseOnLoad >
        </div>
   </div>
</div>
