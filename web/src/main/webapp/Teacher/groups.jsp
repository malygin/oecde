<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<script type="text/javascript">
    dojo.require("dijit.Tree");
    dojo.require("dojo.data.ItemFileReadStore");
    document.title = "ЦОО СГУ. Список групп";
</script>
<%--
<div class=head_main_math>
    <p> Список студентов  </p>
</div>
<div align=center>
    <div align="center"  class="stmat_links" >
        <c:set var="semestr" value="${param[\"s\"]}"/>
        <c:choose>
            <c:when test="${semestr == '1'}">
                <c:set var="semestr" value="1"/>
                <ol style='float: center;'>
                    <li>
                        <a class='stmat_link' href='#groups/s=0'>Показать группы летнего семестра</a>
                    </li>
                    <li>
                        <img src="../images/stmat_bord.jpg">
                    </li>
                    <li  class='disab_link' classname='disab_link'>
                        <p>Группы зимнего семестра</p>
                    </li>
                </ol>
            </c:when>
            <c:otherwise>
                <c:set var="semestr" value="0"/>
                <ol style='float: center;'>
                    <li  class='disab_link' classname='disab_link' >
                        <p>Группы летнего семестра</p>
                    </li>
                    <li>
                        <img src="../images/stmat_bord.jpg">
                    </li>
                    <li>
                        <a class='stmat_link' href='#groups/s=1'>Показать группы зимнего семестра</a>
                    </li>
                </ol>
            </c:otherwise>
        </c:choose>
        <div align="centr"><div class="otdelitel"></div></div>
    </div>
</div>
<div align="centr"><div class="otdelitel"></div></div>

<div id=listtr>
    <div dojoType="dojo.data.ItemFileReadStore" jsId="groupeStore" url="getTeacherSpecAndGroups.view?semestr=<c:out value="${semestr}"/>"></div>
    <div class='stud_tree'>
        <div dojoType="dijit.Tree" id="navigationTree" class="w3tree"
                                openOnClick="true" showRoot="false" store="groupeStore" width=auto>
            <script type="dojo/method" event="onClick" args="item">
                 if(groupeStore.getValue(item, "type") == "groupe"){
                     window.location = "#grades/gr="+item.name.toString().substring(0, 3)+"&sp="+item.sp+"&s=<c:out value="${semestr}"/>";
                 }
            </script>
        </div>
    </div>
</div>
--%>
<c:set var="materials" value="${sessionScope[\"education\"]}"/>

<c:set var="semestr" value="${param[\"s\"]}"/>
<c:set var="summer" value="${(param[\"s\"] eq 0)}"/>
<table cellpadding="0" cellspacing="0" style="width: 218px; border-bottom: 1px solid #5EBED9; margin-bottom: 3px; margin-top: 4px; font-size: 9pt;">
    <c:choose>
        <c:when test="${summer}">
            <tr id="summerFst">
                <td align="center">
                    <p style="margin: 0px; color: silver;">Летний семестр</p>
                </td>
                <td align="center">
                    <a class="notActsemestr" href="javascript:ChangeSemestr(1)">Зимний семестр</a>
                </td>
            </tr>
        </c:when>
        <c:otherwise>
            <tr id="summerFst1">
                <td align="center">
                    <a class="notActsemestr" href="javascript:ChangeSemestr(0)">Летний семестр</a>
                </td>
                <td align="center">
                    <p style="margin: 0px; color: silver;">Зимний семестр</p>
                </td>
            </tr>
        </c:otherwise>
    </c:choose>
</table>
<div style="border: 1px solid silver; margin: 5px; padding: 5px; height: 450px; overflow-x:hidden;">
    <div id=listtr>
        <div dojoType="dojo.data.ItemFileReadStore" jsId="groupeStore" url="getTeacherSpecAndGroups.view?semestr=<c:out value="${semestr}"/>"></div>
        <div class='menu_tree'>
            <div dojoType="dijit.Tree" id="navigationTree" class="w3tree"
                                    openOnClick="true" showRoot="false" store="groupeStore" width=auto>
                <script type="dojo/method" event="onClick" args="item">
                     if(groupeStore.getValue(item, "type") == "groupe"){
                         window.location = "#grades/gr="+item.name.toString().substring(0, 3)+"&sp="+item.sp+"&s="+${semestr}+"&t=grades";
                     }                     
                </script>
            </div>
        </div>
    </div>
</div>