<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
    dojo.require("dijit.form.Form");
    dojo.require("dojox.layout.ContentPane");
    dojo.require("dijit.form.CheckBox");
    dojo.require("dijit.form.Button");
    dojo.require("dijit.form.DateTextBox");
    dojo.require("dijit.Dialog");
    dojo.require("dojo.io.iframe");
    dojo.require("dijit.ProgressBar");
    document.title = "ЦОО СГУ. Главная страница";
</script>

<div class=title>
    <p>Информация</p>
</div>
<div class=head_main_g_prep>


    <div align="left" style="float: left; margin-right: 30px;">
        <table cellpadding="0" cellspacing="0">
            <tr>
                <td class="fotoborder">
                    <img style="border: 3px solid white;"  id="photo" align="left" src="../loadPhoto?id=<c:out value="${sessionScope[\"adminItem\"].id}"/>&type=admin&size=big"/>
                </td>
            </tr>
            <tr>
                <td>
                    <br>

                    <div class="load_photo_student_main">
                        <div dojoType="dijit.form.DropDownButton" class='Opa'>
                            <p style='padding-top:-5px;'>Загрузить фото</p>
                            <div dojoType="dijit.TooltipDialog" title="Загрузка новой фотографии" id="tooltipDlg" open="false" href="../uploadFile.jsp">
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </div>



    <table  class="ankc">
        <tr><td><b>Фамилия:</b>&nbsp </td><td><div id="surname"> <c:out value="${sessionScope[\"adminItem\"].surname}"/>  </div></td></tr>
        <tr><td><b>Имя:</b>&nbsp </td><td><div id="name"><c:out value="${sessionScope[\"adminItem\"].name}"/></div></td></tr>
        <tr><td><b>Очество:</b>&nbsp </td><td><div id="second_name"><c:out value="${sessionScope[\"adminItem\"].secondName}"/></div></td></tr>
        <tr><td></td></tr>
        <tr><td></td></tr>
        <tr valign=top><td></td></tr>
        <tr valign=top><td><b><br></b>&nbsp </td><td></td></tr>
    </table>
    <div class="otdelitel"></div>
</div>
<%@include file="journaling_view_of_events.jsp" %>
