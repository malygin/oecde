<%-- 
    Document   : cityForums
    Created on : 19.02.2010, 13:14:23
    Author     : KorgovVD
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<jsp:useBean id="cities" class="utils.springutil.CityBean"/>
<div class="head_main_math">
    <p>Городские форумы</p>
</div>
<div class="side_bar">
    <ol>
        <c:forEach items="${cities.list}" var="city">
            <li><a href="main.jsp#forum/type=stcity&id=${city.id}&name=${city.name}"><img src="../images/book.jpg">${city.name}</a></li>
        </c:forEach>
    </ol>
</div>