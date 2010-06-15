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
    <div class=list style="margin-top:10px; //margin-left: 10px;">
             <ol>
                    <!--<img src="<=newsI.getImg_big() %>">-->
                    <li style=" background:  #F9FFFF; border: solid 1px silver; margin-left: 10px; margin-right: 10px; padding-bottom: 10px; padding-left:0px;">
                    <p class='ostup_sleva'><a style="float: right;" class="teh_a" style="TEXT-DECORATION: underline;" href="#news/page=<%=currentPage %>">Вернуться к списку новостей</a></p>
                    <div class="otdelitel"></div>
                    <p class='ostup_sleva'><b>Дата: &nbsp</b><%=newsI.getDate() %></p>
                     <p class='ostup_sleva'><b>Тема: &nbsp</b><%=newsI.getThem() %></p>
                    <p class='ostup_sleva'><%=newsI.getFull_text() %></p>
                    <p class='ostup_sleva'><%=newsI.getAuthor() %></p>
                    <p class='ostup_sleva'><a style="float: right;" class="teh_a" style="TEXT-DECORATION: underline;" href="#news/page=<%=currentPage %>">Вернуться к списку новостей</a></p>
                    <div class="otdelitel"></div>
             </li>
              </ol>
       </div>
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

<div class="nav_links">
<%
        for( int i=1;i<=pageCount;i++ ){
            if(pageCount != 1)
                if( i == Integer.valueOf(currentPage))
                    out.println("&nbsp; <b> " + i + "</b>&nbsp;");
                else
                    out.println("&nbsp;<a href='#news/page=" + i + "'>" + i + "</a>&nbsp;");
        }
%>
</div>

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
      
       
                    <!--<img src="<=newsI.getImg_small() %>">--> <!--не знаю зачем это надо но так как ничего не подружается, притом портит вид в некоторых браузерах, закоментировал-->
              <li style="<%=cvet %>">
                    <p class='ostup_sleva'><b>Дата: &nbsp</b><%=newsI.getDate() %></p>
                    <p class='ostup_sleva'><b>Тема: &nbsp</b><%=newsI.getThem() %></p>
                    <p class='ostup_sleva'><b>Анонс: &nbsp</b><%=newsI.getAnons() %></p>
                    <p class='ostup_sleva'><a class="teh_a" href="#news/page=<%=currentPage %>&id=<%=newsI.getId() %>">Читать далее...</a></p>
              </li>
          
<%
    beg++;
        }

%>
    </ol>
</div>

        <div class="nav_links">
<%
        for( int i=1;i<=pageCount;i++ ){
            if(pageCount != 1)
                if( i == Integer.valueOf(currentPage))
                    out.println("&nbsp; <b>" + i + "</b>&nbsp;");
                else
                    out.println("&nbsp;<a href='#news/page=" + i + "'>" + i + "</a>&nbsp;");
        }
%>
</div>
<%    }
  
%>
