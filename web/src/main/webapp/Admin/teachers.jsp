<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page import = "teacher.model.*" %>
<%@ page import = "java.util.*" %>
<%
    Teacher t = new Teacher();
    List<TeacherItem> ListSpec = t.getAllTeachers();
%>
<script type="text/javascript">
    dojo.require("dojo.parser");
    dojo.require("dijit.TitlePane");
    document.title = "СГУ ЦОО. Список преподавателей"
</script>
<div class="head_main_math">
    <p>Список преподавателей</p>
</div>
<div>

    <div align="centr"><div class="otdelitel"></div></div>
</div>
<div class=list style="margin-top:10px">
    <ol>
        <%

            boolean i1=true;
            int stl;
            for(TeacherItem l:ListSpec){
                i1=!i1;
                stl=i1?1:2;
        %>
        <li class="first_back_<%=stl %>_stteachers">
            <div>
                <p class=fio_friend><a class="teh_a" href="anotherType.do?type=teacher&id=<%=l.getId() %>"><%=l.getSurname() %> <%=l.getName() %> <%=l.getSecond_name() %></a></p>
            </div>
            <div align="centr"><div class="otdelitel"></div></div>
        </li>
        <%
            }
        %>
    </ol>
</div>

