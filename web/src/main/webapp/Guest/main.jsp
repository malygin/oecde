<%-- 
    Document   : test_page.jsp
    Created on : 24.02.2009, 13:11:53
    Author     : korgovvd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean class="guest.controller.GuestFilter" id="filter" />

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ЦОО СГУ</title>
        <link href="../style/studentStyles.css" rel="stylesheet" type="text/css">
        <link rel="icon" href="../favicon.ico" type="image/x-icon">
        <link rel="shortcut icon" href="favicon.ico">        
    </head>
    <c:set var="qw" value="${cookie[\"oecdo\"]}"/>
    <script type="text/javascript" src="../dojoroot/dojo/dojo.js" djConfig="parseOnLoad: true" djConfig="preventBackButtonFix: false"></script>
    <script type="text/javascript">
        dojo.require("dojo.parser");
        dojo.require("dojox.data.dom");
        dojo.require("dijit.TitlePane");
        dojo.require("dojox.layout.ContentPane");
        dojo.require("dojo.cookie");
        function changeContent(page,parameters){
            var path = "page.do";
            if(typeof(parameters) == 'undefined'||typeof(parameters)=='null'||parameters==""){
                parameters = ""
            }else{
                parameters = "&"+parameters
            }
            dijit.byId("contentPane").setHref(path+"?p="+page+parameters);
        }

        dojo.addOnLoad(function getPage(){
            var href = window.location.href;
            var a = window.location.hash;
            a = a.substring(1,a.length);
            a = a.split("/");
            dojo.connect(dijit.byId("contentPane"), "onDownloadError", function f20(e){
               if(e.status == "403")
                   window.location="../index.jsp";
               if(e.status == "404" || e.status == "500" || e.status == "302"){
                    dijit.byId("contentPane").cancel();
               }
           });
            var inter = setInterval(function f90(){
                if(href != window.location.href){
                    href = window.location.href;
                    var a = window.location.hash;
                    a = a.substring(1,a.length);
                    a = a.split("/");
                    dijit.byId("contentPane").cancel();
                    changeContent(a[0],a[1]);
                }
            },300);
            changeContent(a[0],a[1]);
        });
    </script>

    <body class="tundra"  Scrollbars=1>
        
        <div align="center">
            <div style="margin-top: 10px; margin-bottom: 10px; border: 1px solid #329192; background: #D9F1F4; width: 960px;">
                <p>
                    Вы зашли в систему под гостевой учетной записью. В данном режиме функционал системы ограничен!!!
                </p>
            </div>
            <div class="main_page">
                <div class="top_shadow">
                    <div class="bot_shadow">
                        <div class="l_t_angle">
                            <div class="r_t_angle">
                                <div class="r_b_angle">
                                    <div class="l_b_angle">
                                        <div class="inf_pole">
                                            <%@include  file="../WEB-INF/Guest/jspf/l_menu.jspf" %>
                                            <div class="right">
                                                <%@include  file="../WEB-INF/Guest/jspf/t_menu.jspf" %>
                                                <div class="right_inside">
                                                    <div>
                                                        <div class="inf">
                                                           <%@include  file="../WEB-INF/Guest/jspf/fio.jspf" %>
                                                            <div class="under_fio_line"></div>
    <div class="main_inf" id="contentPane" executeScripts="true" dojoType="dojox.layout.ContentPane" preload parseOnLoad preventCache refreshOnShow
    loadingMessage='<div align="center"  style="z-index: 100; position: relative; margin-top:110px;"><img src="../images/loader1.gif"></div>'>
    </div>
                                                        </div>
                                                        <%@include  file="../WEB-INF/Guest/jspf/r_menu.jspf" %>
                                                        <div align="centr"><div class="otdelitel"></div></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div align="centr"><div class="otdelitel"></div></div>
                                        </div>
                                        <%@include  file="../WEB-INF/Guest/jspf/b_menu.jspf" %>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
