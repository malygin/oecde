<%--<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import = "news.model.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "utils.*" %>
 <div class="head_main_math">
      <p>Новости</p>
 </div>

<%
    News news = new News();
    NewsItem newsI;
    String id = (String)request.getParameter("id");
    String currentPage = (String)request.getParameter("page");
        if(currentPage == null)
            currentPage = "1";

    if(id != null){
        List list = news.getNewsById(Integer.valueOf(id));
        newsI = (NewsItem)list.get(0);

%>
	<p class='ostup_sleva1'><a style="float: right;" class="teh_a" style="TEXT-DECORATION: underline;" href="#news/page=<%=currentPage %>">Вернуться к списку новостей</a></p>
    <div class=list style="margin-top:10px; //margin-left: 10px;">
             <ol>
                   <!-- <img src="<=newsI.getImg_big() %>">-->
              <li style=" background:  #F9FFFF; border-bottom: solid 1px silver; border-top: solid 1px silver; border-left: solid 1px silver;border-right: solid 1px silver;margin-left: 10px; margin-right: 10px; padding-bottom: 10px; padding-left:20px; padding-right: 10px;">

                     <div align="centr"><div class="otdelitel"></div></div>
					<div class='ostup_sleva1' style="color:#71C5F4;float:left;font-size:11pt;font-weight:bolder;margin-bottom:0;margin-left:-30px;margin-right:0;margin-top:10px;"><%=newsI.getThem() %></div>
                    <div class='ostup_sleva1' style="display: block;color:#71C5F4;float:right;margin-bottom:0;margin-left:0;margin-right:0px;margin-top:10px;" class="news_name_date"><%=newsI.getDate() %></div>
                    <div style="clear: both;"></div>
                    <p class='ostup_sleva1' style="margin: 0px 30px 10px;"><%=newsI.getFull_text() %></p>
                    <!--<p class='ostup_sleva1' style="margin: 0px 30px 10px;"><%=newsI.getAuthor() %></p>-->

                     <div align="centr"><div class="otdelitel"></div></div>
             </li>
              </ol>
       </div>
	<p class='ostup_sleva1'><a style="float: right;" class="teh_a" style="TEXT-DECORATION: underline;" href="#news/page=<%=currentPage %>">Вернуться к списку новостей</a></p>
<%    }
     else{
        int countOut = 5;
        int begin;
        int end;

        int count = news.getAllNewsCount();
        int pageCount = count/countOut;
        if( count%countOut != 0 ){
            pageCount++;
        }


%>


 <div class=list_news>
    <ol>
 <%     begin = Integer.valueOf(currentPage)*countOut-countOut+1;
        end = Integer.valueOf(currentPage)*countOut;

        ArrayList<NewsItem> newsList = news.getNewsBetween(begin, end);
        int beg=0;
        String cvet;
        for(Object n:newsList){
            newsI = (NewsItem)n;
            if(beg%2 == 0)
                cvet="background:  #F9FFFF; border-bottom: solid 1px silver; border-top: solid 1px silver; border-left: solid 1px silver;border-right: solid 1px silver;margin-left: 10px; margin-right: 10px; padding-left:0px;";
            else
                cvet="background:  white; border-bottom: solid 1px silver; border-top: solid 1px silver; border-left: solid 1px silver;border-right: solid 1px silver;margin-left: 10px; margin-right: 10px; padding-left:0px;";
%>


                    <img style="display: none;" src="<%=newsI.getImg_small() %>">
              <li style="<%=cvet %>">
                    <div>
                        <div style="margin: 0px; color: #71C5F4; margin-right: 10px; margin-top: 10px; float: right;"><%=newsI.getDate() %></div>
                        <div class="otdelitel"></div>
                        <div style="margin: 0px; margin-left: 10px; margin-top: 10px; float: left; font-size: 11pt; color: #71C5F4; font-weight: bolder;"><%=newsI.getThem() %></div>

                        <div class="otdelitel"></div>
                    </div>
                    <div style="margin: 0px; margin-bottom: 10px; margin-left: 20px; margin-right: 20px;"><%=newsI.getAnons() %><a style="margin-left: 10px;" class="more_news" title="Читать подробнее новость" href="#news/page=<%=currentPage %>&id=<%=newsI.getId() %>">подробнее</a></div>

              </li>

<%
    beg++;
        }

%>
    </ol>
</div>

        <div class="nav_links"><div class="nav_links_1_und"><div class="nav_links_2_und">
<%
        for( int i=1;i<=pageCount;i++ ){
            if(pageCount != 1)
                if( i == Integer.valueOf(currentPage))
                    out.println("&nbsp; <b>" + i + "</b>&nbsp;");
                else
                    out.println("&nbsp;<a href='#news/page=" + i + "'>" + i + "</a>&nbsp;");
        }
%>
</div></div></div>
<%    }

%>
--%>

<%@ page import="news1.dao.NewsDAO" %>
<%@ page import="utils.springutil.SpringContext" %>
<%@page contentType="text/html" %>
<%@page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib tagdir="/WEB-INF/tags/disc" prefix="disc" %>

<jsp:useBean id="newsEngine" class="news1.NewsEngine"/>

<c:set var="id" value="${param[\"id\"]}"/>
<c:set var="newsCount" value="1"/>
<c:set var="pageCount" value="1"/>
<c:if test="${param.page ne null}">
    <c:set var="pageCount" value="${param.page}"/>
</c:if>

<c:set var="currentPage" value="1"/>
<c:if test="${param.page ne null}">
    <c:set var="currentPage" value="${param.page}"/>
</c:if>


<script type="text/javascript">
    
</script>

<c:choose>
    <c:when test="${id eq null}">
        <div class="head_main_math">
            <p>Новости </p>
        </div>

        <div class="news_block">
            <c:set var="start" value="${currentPage*5}"/>
            <c:if test="${currentPage eq 1}">
                <c:set var="start" value="1"/>
            </c:if>
            <c:forEach var="item" begin="${start}"  items="${newsEngine.allNews}">
                <c:set var="newsCount" value="${newsCount+1}"/>
                <c:if test="${newsCount eq 5}">
                    <c:set var="pageCount" value="${pageCount+1}"/>
                    <c:set var="newsCount" value="1"/>
                </c:if>
                <div class="news_item">
                    <p class="news_date">
                        <c:out value="${item.timeInList}"/>
                    </p>
                    <a class="news_header" href="">
                        <c:out value="${item.header}"/>
                    </a>
                    <p class="news_text">
                        <c:out value="${item.announcement}"/>
                        <a class="news_full" href="#news/page=<c:out value='${currentPage}'/>&id=<c:out value='${item.id}'/>">
                            далее...
                        </a>
                    </p>
                    <p class="news_comm_ans">
                        <span>Комментарии</span>&nbsp;
                        <b>()</b> &nbsp;
                    </p>
                    <p class="news_views">
                        Просмотров: <c:out value="${item.reviewNumber}"/>
                    </p>
                </div>
            </c:forEach>
            <c:if test="${pageCount ne 1}">
                <c:forEach var="i" begin="1" end="${pageCount}">
                    <c:set var="p" value="<a href='#news/page=${i}' >${i}</a>"/>
                    <c:if test="${i eq currentPage}">
                        <c:set var="p" value="${i}"/>
                    </c:if>
                    ${p}
                </c:forEach>
            </c:if>
        </div>
    </c:when>
    <c:otherwise>
        <script type="text/javascript">
            dojo.require("dijit.form.Textarea");
            function create_text_area(n, text, cName, id){
                var ta = new dijit.form.Textarea({
                    name: n,
                    value: text,
                    class: cName
                },id);
            }
            function show_commF(name, id){
                create_text_area("ta"+name, "", "coment_ta", name+id+id);
                dojo.attr(name+id,"style","display: block; border: 1px solid silver; margin: 5px ; padding: 3px; text-align: center;");
            }
            function hidme(name,id){
                dojo.attr(name+id,"style","display: none;");
                dijit.byId(name + id +id).destroy();
                div = document.createElement("div");
                div.id = name+id+id;
                dojo.byId(name+id+id+id).appendChild(div);
            }
            function redact_comm(name, id,old_value){
                create_text_area("ta"+name, old_value, "coment_ta", name+id);
                dojo.byId("coment_old_text"+id).style.display = "none";
                dojo.attr("paren_c"+id,"style", 'display: block;');

            }
            function com_changing_cancel(id, type){
                if(type == false){
                    new_val = dijit.byId("container_for_ta"+id).value;
                    dojo.byId("coment_old_text"+id).innerHTML = new_val;
                }
                    dijit.byId("container_for_ta"+id).destroy();
                    dojo.byId("paren_c"+id).style.display = "none";
                    dojo.byId("coment_old_text"+id).style.display = "block";
                    div = document.createElement("div");
                    div.id = "container_for_ta"+id;
                    dojo.byId("ccont"+id).appendChild(div);
            }
            function commet_this_event(){
                create_text_area("new_root_comment", "", "coment_ta", "for_ta_news_com");
                dojo.attr("new_com_form","style","display: block; margin: 5px 0 0 0; padding: 5px 5px 0 5px; border: none; border-top: 1px solid silver;");
            }
            function cancel_root_com(){
                dojo.byId("new_com_form").style.display = "none";
                dijit.byId("for_ta_news_com").destroy();
                div = document.createElement("div");
                div.id = "for_ta_news_com";
                dojo.byId("com_form_container").appendChild(div);
            }
            function actionResult(name,id,actCont){
                hidme(name,id);
                dojo.byId(actCont+id).style.display = "block";
                var g = setTimeout(function res(){dojo.byId(actCont+id).style.display = "none"},1000);
            }
        </script>
        <jsp:setProperty name="newsEngine" property="id" value="${id}"/>
        <c:set var="item" value="${newsEngine.byId}"/>
        <div class="head_main_math">
            <a href="#news/page=<c:out value='${currentPage}' />">
                <p style="text-decoration: underline;">Новости</p>
            </a>
        </div>
        <div class="news_block">
            <p class="news_one_header">
                <b><c:out value="${item.timeInList}"/></b> |  <c:out value="${item.header}"/>
            </p>
            <p class="news_one_text">
                <c:out value="${item.fullText}"/>
            </p>
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
                    <a class="save_link" href="javascript:submit_com()">Отправить</a>&nbsp;&nbsp;&nbsp;<a class="cancel_link" href="javascript:cancel_root_com()">Отмена</a>
                </div>
            </div>
            <%--<p class="news_gallery">
                <img src="../images/img.jpg"><img src="../images/img.jpg"><img src="../images/img.jpg">
                <div class="otdelitel">
                </div>
            </p>--%>
            <disc:root idObject="${item.id}" typeObject="news"/>
        </div>
    </c:otherwise>
</c:choose>