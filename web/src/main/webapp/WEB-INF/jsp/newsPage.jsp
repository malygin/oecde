<%@page contentType="text/html" %>
<%@page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="d" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib tagdir="/WEB-INF/tags/disc" prefix="disc" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script type="text/javascript" charset="UTF-8">
    dojo.require("dijit.Editor");
    dojo.require("dijit.Dialog");
    dojo.require("dijit._editor.plugins.AlwaysShowToolbar");
    dojo.require("dijit._editor.plugins.EnterKeyHandling");
    dojo.require("dijit._editor.plugins.TextColor");
    dojo.require("dijit._editor.plugins.LinkDialog");
    function submitNews() {
        document.getElementById("fullText").value = dijit.byId("main_text").getValue(false); 
        dojo.xhrPost({
            form:dojo.doc.addNews,
            url: "../spring/news/newsAdd.jsp",
            load:function f4qw() {
                dijit.byId("contentPane").refresh()
            },
            error:function ff423(e) {
                console.debug(e)
            }
        });
    }

       dojo.require("dijit.Editor");


    function showPrevNews(){
        dojo.byId("prevNewsText").innerHTML = dijit.byId("main_text").editNode.innerHTML;
        dojo.byId("prevNewsHeader").innerHTML = dojo.byId("header").value;
        dijit.byId("preview").show();
        dojo.byId("divCont").style.height = dojo.byId("divCont").offsetParent.offsetHeight - 37 + "px";
    }

    function discardChanges(){
        dijit.byId("preview").hide();
    }
</script>

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
    <p>Новости </p>
</div>
<%--<c:out value="${sessionScope['type'].id}"/><br/>--%>
<%--<c:out value="${sessionScope['type']}"/><br/>--%>
<%--Проверяет, является ли пользователь администратором--%>
<c:if test="${sessionScope['type'].id < 3}">
    <div class="news_block">
        <form method="POST" name="addNews">
            <input style="width: 519px;" type="text" id="header" name="header" value="Заголовок"/><br>
            <textarea style="width: 519px;" rows="5" id="announcement" name="announcement">Анонс</textarea><br>

            <div name="main_text" dojoType="dijit.Editor" id="main_text"
                 plugins="['undo', 'redo','|','bold', 'italic', 'underline','|','indent', 'outdent', '|', 'justifyLeft', 'justifyRight', 'justifyCenter', 'justifyFull','|','createLink','|','foreColor','|','hiliteColor','|']">
            </div>

            <input type="hidden" id="fullText" name="fullText"/>
            <br>
            <input style="margin-top: 3px; margin-left: 3px;" type="button" value="Довавить новость" onclick="showPrevNews()"/>
        </form>
    </div>
</c:if>


<div class="news_block">
    <div class="nav_links">
        <div class="nav_links_1_und">
            <div class="nav_links_2_und">
                <c:if test="${pageCount ne 1}">
                    <c:forEach var="i" begin="1" end="${pageCount}">
                        <c:set var="p" value="<a href='#newsList/pN=${i}&count=10' >${i}</a>"/>
                        <c:if test="${i eq currentPage}">
                            <c:set var="p" value="${i}"/>
                        </c:if>
                        ${p}
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>
    <%--news автоматически подставлены контроллером--%>
    <c:forEach var="item" items="${news}">
        <div class="news_item">
            <p class="news_date">
                <c:out value="${item.timeInList}"/>
            </p>
            <a class="news_header" href="#newsFullText/id=${item.id}&pN=${pN}&count=${count}">
                <c:out value="${item.header}"/>
            </a>
            <c:if test="${sessionScope['type'].id >= 3}">
                <p class="news_text">
                    <c:out escapeXml="false" value="${item.announcement}"/>
                    <a class="news_full"
                       href="#newsFullText/id=${item.id}&pN=${pN}&count=${count}">
                        далее...
                    </a>
                </p>
            </c:if>

            <p class="news_comm_ans">
                <span>Комментарии</span>&nbsp;
                <c:if test="${item.commentNumber ne 0}">
                    <b><font color="red">(${item.commentNumber})</font></b> &nbsp</c:if>
               <c:if test="${item.commentNumber eq 0}">
                     <b>(${item.commentNumber})</b> &nbsp</c:if>
             
            </p>

            <p class="news_views">
               Просмотров:             <c:out value="${item.reviewNumber}"/>
            </p>
        </div>
    </c:forEach>
    <div class="nav_links">
        <div class="nav_links_1_und">
            <div class="nav_links_2_und">
                <c:if test="${pageCount ne 1}">
                    <c:forEach var="i" begin="1" end="${pageCount}">
                        <c:set var="p" value="<a href='#newsList/pN=${i}&count=10' >${i}</a>"/>
                        <c:if test="${i eq currentPage}">
                            <c:set var="p" value="${i}"/>
                        </c:if>
                        ${p}
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>

</div>
</div></div>
</div>
