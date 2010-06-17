<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="disc" tagdir="/WEB-INF/tags/disc" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" charset="UTF-8">
    dojo.require("dijit.form.Textarea");
    dojo.require("dijit.Editor");
    dojo.require("dijit._editor.plugins.AlwaysShowToolbar");
    dojo.require("dijit._editor.plugins.EnterKeyHandling");
    dojo.require("dijit._editor.plugins.TextColor");
    dojo.require("dijit._editor.plugins.LinkDialog");
    dojo.require("dojox.image.Lightbox");
</script>

<script type="text/javascript" src="../scripts/discussion.js"></script>

<div dojoType="dijit.Dialog" id="preview" style="display: none;" title="Предварительный просмотр новости">
    <div id="divCont" style="overflow-y: scroll; width: 500px;">
        <p class="news_one_header" id="prevNewsHeader">

        </p>

        <p class="news_one_text" id="prevNewsText">

        </p>
    </div>
    <div style="border-top: 1px solid silver; padding: 5px;">
        <input type="button" value="Подтвердить" onclick="submitNews()"/>&nbsp;&nbsp;&nbsp;
        <input type="button" value="Отменить" onclick="discardChanges()">
    </div>
</div>

<div class="head_main_math">
    <a href="#newsList/pN=${pN}&count=${count} />">
        <p style="text-decoration: underline;">Новости</p>
    </a>
</div>

<p class="news_one_header">
    <b><c:out value="${item.timeOnPage}"/></b> | <c:out value="${item.header}"/>
</p>

<c:if test="${sessionScope['type'].id >= 3}">
    <p class="news_one_text">
        <c:out escapeXml="false" value="${item.fullText}"/>
    </p>
</c:if>

<c:if test="${sessionScope['type'].id < 3}">
    <div class="news_block">
        <form method="POST" name="editNews">
            <input type="text" style="width: 519px;" id="header" name="header" value="${item.header}"/><br>
            <textarea  style="width: 519px;" rows="5" id="announcement" name="announcement">${item.announcement}</textarea><br>

            <div name="main_text" dojoType="dijit.Editor" id="main_text"
                 plugins="['undo', 'redo','|','bold', 'italic', 'underline','|','indent', 'outdent', '|', 'justifyLeft', 'justifyRight', 'justifyCenter', 'justifyFull','|','createLink','|','foreColor','|','hiliteColor','|']">
                ${item.fullText}
            </div>

            <input type="hidden" id="fullText" name="fullText"/>
            <input type="hidden" id="idNews" name="idNews" value="${item.id}">
            <input type="button" style="margin-top: 3px; margin-left: 3px;" value="Изменить новость" onclick="showPrevNews()"/>
        </form>
    </div>
</c:if>

<p class="news_views" style="float: left;">Просмотров: <c:out value="${item.reviewNumber}"/></p>
            <span style="float: right; padding-right: 15px;">
                <a class="news_answers" href="javascript:commet_this_event()">Прокомментировать новость</a>
            </span>

<div class="otdelitel"></div>
<div style="display: none;" id="new_com_form">
    <p style="font-size: 8pt; color: gray; margin: 0 0 0 5px; ">Комментарий</p>

    <div id="com_form_container" align="center">

        <div id="for_ta_news_com"></div>
    </div>
    <div style="text-align: right; padding-right: 10px;">
        <a class="save_link" href="javascript:submit_com1('${item.id}','news')">Отправить</a>&nbsp;&nbsp;&nbsp;<a
            class="cancel_link"
            href="javascript:cancel_root_com()">Отмена</a>
    </div>
</div>
<disc:root idObject="${item.id}" typeObject="news"/>
</div>
