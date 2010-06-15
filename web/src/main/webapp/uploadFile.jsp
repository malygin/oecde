<%-- 
    Document   : uploadFile
    Created on : 10.12.2008, 15:00:00
    Author     : malyginav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" enctype="multipart/form-data" name="pictureForm">
            <input type="file" accept="images/*" name="file">
            <input class='ButtonLoad' type="button" value="Загрузить" onclick="getNewPhoto();">
        </form>
        <div align="center" id="fileLoader" style="display:none">
            <div dojoType="dijit.ProgressBar" style="width:200px" indeterminate="true"></div>           
            </div>
 <b id="error" style="display:none">Изображение не было загружено</b>
    </body>