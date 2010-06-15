<%--
    Document   : forum
    Created on : 08.02.2010, 12:14:37
    Author     : KorgovVD
--%>

<%--<%@page contentType="text/html"%>--%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="disc" tagdir="/WEB-INF/tags/disc" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<script type="text/javascript" charset="UTF-8">
    dojo.require("dijit.form.Textarea");
    dojo.require("dijit.Editor");
    dojo.require("dijit._editor.plugins.AlwaysShowToolbar");
    dojo.require("dijit._editor.plugins.EnterKeyHandling");
    dojo.require("dijit._editor.plugins.TextColor");
    dojo.require("dijit._editor.plugins.LinkDialog");
    dojo.addOnLoad(commet_this_event());
    document.title = "ЦОО СГУ. Форум";
</script>
<script type="text/javascript" src="../scripts/discussion.js"></script>
<c:set var="fType" value="${param.type}"/>
<c:set var="id" value="${param.id}"/>
<c:if test="${fType eq 'teachfaq'}">
    <div class="head_main_math">
        <p>Технический форум</p>
    </div>
    <div style="margin: 10px; text-align: justify;">
        <img src="../images/MMike.jpg" style="float: left; margin: 0px 10px 10px 0px;"/>
        Этот форум посвящен техническим проблемам. Прежде чем, задать вопрос - посмотрите в разделе
        помощь - <a class="forum_into_text_links" href="main.jsp#help">список частых вопросов</a>. Возможно это вам поможет. Вы также может задать вопрос в закрытую <a class="forum_into_text_links" href="main.jsp#write_msg/id_send=1&type_send=2&type_let=1">тех поддержку</a>.
    </div>
</c:if>
<c:if test="${fType eq 'teachorg'}">
    <div class="head_main_math">
        <p>Организационный форум</p>
    </div>
    <div style="margin: 10px; text-align: justify;">
        <img src="../images/ivanovaNA.jpg" style="float: left; margin: 0px 10px 10px 0px;" alt=""/>
        <div style="float: right; width: 85%">
            Этот форум посвящен организационным вопросам. Прежде чем, задать вопрос - посмотрите в разделе помощь
            <a class="forum_into_text_links" href="main.jsp#help"> список частых вопросов</a>. Вы так же можете задать вопрос через систему сообщений
            <br/><a class="forum_into_text_links" href="main.jsp#admin/id=42">Наталье Александровне Ивановой</a>
        </div>
        <div class="otdelitel"></div>
    </div>
</c:if>
<div class="forum_${fType}">

    <div class="otdelitel"></div>
    <div id="new_com_form">
        <p style="font-size: 8pt; color: gray; margin: 0 0 0 5px; ">Текст Вашего вопроса:</p>

        <div id="com_form_container" align="center">

            <div id="for_ta_news_com"></div>
        </div>
        <div style="text-align: right; padding-right: 10px;">
            <a class="save_link" href="javascript:submit_com1('${id}','${fType}')">Отправить</a>
        </div>
    </div>
        <disc:root idObject="${id}" typeObject="${fType}"/>
</div>