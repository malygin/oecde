<%-- 
    Document   : test_page.jsp
    Created on : 24.02.2009, 13:11:53
    Author     : korgovvd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html>
    <head>
        <style type="text/css">
            @import "../samples/widget/templates/SmallChatWidget.css";
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ЦОО СГУ</title>
        <link href="../style/teacherStyles.css" rel="stylesheet" type="text/css">
        <link rel="icon" href="../favicon.ico" type="image/x-icon">
        <link rel="shortcut icon" href="../favicon.ico">        
    </head>
    <script type="text/javascript" src="../dojoroot/dojo/dojo.js" djConfig="parseOnLoad: true" djConfig="preventBackButtonFix: false"></script>
    <script type="text/javascript">
        dojo.require("dojo.parser");
        dojo.require("dojox.data.dom");
        dojo.require("dijit.TitlePane");
        dojo.require("dojox.layout.ContentPane");
        dojo.require("dojo.cookie");
        dojo.registerModulePath("samples", "../../samples");
        dojo.require("samples.widget.SmallChatWidget");
        dojo.require("samples.widget.ChatWidget");
    </script>
            <script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
try {
var pageTracker = _gat._getTracker("UA-5800747-2");
pageTracker._trackPageview();
} catch(err) {}</script>
    <script type="text/javascript" src="../scripts/main.js" ></script>
    <script type="text/javascript" src="../scripts/teacher.js" ></script>
<body class="tundra">
    <div align="center">
        <div class="main_page">
            <div class="top_shadow">
                <div class="bot_shadow">
                    <div class="l_t_angle">
                        <div class="r_t_angle">
                            <div class="r_b_angle">
                                <div class="l_b_angle">
                                    <div class="inf_pole">
                                        <%@include   file="../WEB-INF/Teacher/jspf/l_menu.jspf" %>
                                        <div class="right">
                                            <%@ include file="../WEB-INF/Teacher/jspf/t_menu.jspf"%>
                                            <div class="right_inside">
                                                <div>
                                                    <div class="inf">
                                                        <%@ include file="../WEB-INF/Teacher/jspf/fio.jspf"%>
                                                        <div class="under_fio_line"></div>
<div class="main_inf" id="contentPane"  executeScripts="true" dojoType="dojox.layout.ContentPane" parseOnLoad preventCache refreshOnShow
loadingMessage='<div align="center" id="loader" style="z-index: 100; position: relative; margin-top:110px; "><img src="../images/loader1.gif"></div>'>
</div>
                                                    </div>
                                                    <%@ include file="../WEB-INF/Teacher/jspf/r_menu.jspf"%>
                                                    <div align="centr"><div class="otdelitel"></div></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div align="centr"><div class="otdelitel"></div></div>
                                    </div>     
                                    <%@ include file="../WEB-INF/Teacher/jspf/b_menu.jspf" %>
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