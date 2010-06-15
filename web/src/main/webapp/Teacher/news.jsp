<%@page contentType="text/html"%>
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
